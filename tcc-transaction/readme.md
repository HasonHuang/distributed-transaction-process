# TCC 补偿事务

## 适用范围

- 强隔离性、严格一致性要求的业务活动
- 适用于执行时间较短的业务（比如处理账户、收费等业务）

## 模块说明
- tcc-account：用户模块
- tcc-order：订单模块
- tcc-capital：资金账户模块
- tcc-integral：积分模块
- tcc-framework：tcc框架，整合了tcc-transaction v1.1.5版本，并解决了一些问题，如：Kryo反序列化兼容`spring-boot-devtools`

## 示例演示

### 初始化

1. 下载[完整项目](../../distributed-transaction-process)
2. 执行[dbscripts](dbscripts)目录下的sql脚本（PostgreSQL）创建数据库。
3. 修改`tcc-account`的TCC数据源、业务系统数据源
4. 修改`tcc-integral`的TCC数据源、业务系统数据源

### 单层嵌套事务

#### 业务流程

1. 打开注册页面 http://localhost:9000/users/register
2. 输入用户信息并注册
3. 系统创建用户，同时创建资金账户和积分账户，其中一个创建失败都将会引起事务回退；

创建资金账户和创建积分账户的操作，都在`tcc-account`项目中调用执行，该业务属于单层事务嵌套。

#### 启动顺序

1. 启动 ms-discovery-eureka
2. 启动 tcc-account、tcc-integral、tcc-capital

### 多层嵌套事务

1. 打开充值页面 http://localhost:9000/users/recharge
2. 输入用户名和充值金额进行充值
3. `tcc-account`调用微服务`tcc-capital`生成订单并增加资金，后者操作成功后调用积分服务`tcc-integral`增加积分。

调用链 `tcc-account` -> `tcc-capital` -> `tcc-integral`，该业务属于多层事务嵌套。

#### 启动顺序

1. 启动 ms-discovery-eureka
2. 启动 tcc-account、tcc-integral、tcc-capital

## 一些问题

1. TCC框架的`TransactionManager`在回滚时，在下面的标示的位置异常退出，从而无法更新事务，会导致事务补偿操作的参数不是最新的（如果TRY方法中修改了参数的情况）。

```java
/**
 * 回滚事务.
 */
public void rollback() {

    Transaction transaction = getCurrentTransaction();
    transaction.changeStatus(TransactionStatus.CANCELLING);
    
    // !! 这里发生异常，退出JVM !!

    transactionConfigurator.getTransactionRepository().update(transaction);
    
    try {
        LOG.info("==>transaction begin rollback()");
        transaction.rollback();
        transactionConfigurator.getTransactionRepository().delete(transaction);
    } catch (Throwable rollbackException) {
        LOG.error("compensable transaction rollback failed.", rollbackException);
        throw new CancellingException(rollbackException);
    }
}
```