package com.hason.dtp.tcc.capital.service;

import com.hason.dtp.core.utils.result.Result;
import com.hason.dtp.tcc.account.entity.User;
import com.hason.dtp.tcc.capital.dao.CapitalAccountRepository;
import com.hason.dtp.tcc.capital.entity.CapitalAccount;
import com.hason.dtp.tcc.capital.entity.CapitalOrder;
import com.hason.dtp.tcc.capital.entity.constant.CapitalOrderStatus;
import com.hason.dtp.tcc.capital.service.client.PointServiceClient;
import com.hason.dtp.tcc.capital.service.client.proxy.PointServiceClientProxy;
import org.mengyun.tcctransaction.Compensable;
import org.mengyun.tcctransaction.api.TransactionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static com.hason.dtp.core.utils.CheckUtil.notFalse;
import static com.hason.dtp.core.utils.CheckUtil.notNull;
import static com.hason.dtp.tcc.capital.entity.constant.CapitalOrderStatus.FAIL;
import static com.hason.dtp.tcc.capital.entity.constant.CapitalOrderStatus.SUCCESS;

/**
 * 资金账户业务实现类
 *
 * @author Huanghs
 * @since 2.0
 * @date 2018/1/12
 */
@Service
public class CapitalAccountServiceImpl implements CapitalAccountService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CapitalAccountServiceImpl.class);

    @Autowired
    private PointServiceClientProxy pointServiceClientProxy;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CapitalAccountRepository capitalAccountRepository;

    @Compensable(confirmMethod = "confirmCreate", cancelMethod = "cancelCreate")
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CapitalAccount create(TransactionContext context, User user) {

        // 显式的事务上下文参数是框架的要求，在业务中无需用到，处理业务时忽略掉

        // 检验参数
        notNull(user, "arg.null", "用户");
        notNull(user.getId(), "arg.null", "用户");

        // 创建资金账户
        CapitalAccount account = new CapitalAccount();
        account.setBalanceAmount(BigDecimal.ZERO);
        account.setUserId(user.getId());
        capitalAccountRepository.save(account);

        return account;
    }

    @Transactional(readOnly = true)
    @Override
    public CapitalAccount confirmCreate(TransactionContext context, User user) {
        LOGGER.debug("开始确认用户[{}]资金信息", user.getUsername());
        // 实现幂等性
        // 已经插入记录了，确认方法无需做任何事情
        return capitalAccountRepository.findByUserId(user.getId());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public CapitalAccount cancelCreate(TransactionContext context, User user) {
        LOGGER.debug("开始取消用户[{}]资金信息", user.getUsername());
        // 实现幂等性
        CapitalAccount account = capitalAccountRepository.findByUserId(user.getId());
        if (account != null) {
            LOGGER.debug("删除资金账户[{}]", account.getId());
            capitalAccountRepository.delete(account.getId());
        }
        return account;
    }

    @Compensable(confirmMethod = "confirmRecharge", cancelMethod = "cancelRecharge")
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void recharge(TransactionContext context, CapitalOrder order) {

        // 检验参数
        notNull(order, "arg.null", "订单");
        notNull(order.getUserId(), "arg.null", "用户ID");
        notNull(order.getAmount(), "arg.null", "充值金额");

        CapitalAccount account = capitalAccountRepository.findByUserId(order.getUserId());
        notNull(account, "service.fail", "用户资金账户不存在");

        // 调用积分系统微服务，增加用户积分（充值多少钱赠送多少积分）
        // 注意：当前方法是与积分的事务的发起者，不能复用参数 context，否则被框架当作 NORMAL 常规方法执行，而不是 PROVIDER
        Result<?> result = pointServiceClientProxy.incr(null, order.getUserId(), order.getAmount().longValue());
        if (!result.success()) {
            notFalse(result.success(), "service.fail", result.getError().getMessage());
        }

        // 执行完 try 操作，资金账户仍未增加余额的，返回账户信息没太大意义
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void confirmRecharge(TransactionContext context, CapitalOrder order) {
        LOGGER.debug("开始确认用户[{}]的充值金额[{}]", order.getUserId(), order.getAmount());

        // 实现幂等性
        CapitalOrder latestOrder = orderService.get(order.getId());
        if (latestOrder.getStatus() == SUCCESS) {
            // 查看最新的订单，如果已经完成了，无需再处理
            return;
        }

        // 订单仍未完成，增加资金余额
        CapitalAccount account = capitalAccountRepository.findByUserId(order.getUserId());
        // 增加账户余额，在实际业务中需要注意并发的问题，可使用乐观锁解决
        account.setBalanceAmount(account.getBalanceAmount().add(order.getAmount()));
        capitalAccountRepository.save(account);

        // 完成订单
        orderService.changeStatus(order.getId(), SUCCESS);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void cancelRecharge(TransactionContext context, CapitalOrder order) {
        LOGGER.debug("开始撤销用户[{}]的充值金额[{}]", order.getUserId(), order.getAmount());

        // 实现幂等性
        CapitalOrder latest = orderService.get(order.getId());
        if (latest.getStatus() == FAIL) {
            // 查看最新的订单，如果已经撤销过，无需再处理
            return;
        }

        // 设置订单为失败
        orderService.changeStatus(order.getId(), FAIL);
    }
}
