
package org.mengyun.tcctransaction.support;

import org.mengyun.tcctransaction.recover.RecoverConfig;
import org.mengyun.tcctransaction.TransactionManager;
import org.mengyun.tcctransaction.TransactionRepository;

/**
 * 事务配置器接口
 * Created by changmingxie on 11/10/15.
 */
public interface TransactionConfigurator {

	/**
	 * 获取事务管理器.
	 * @return
	 */
    public TransactionManager getTransactionManager();

    /**
     * 获取事务库.
     * @return
     */
    public TransactionRepository getTransactionRepository();

    /**
     * 获取事务恢复配置.
     * @return
     */
    public RecoverConfig getRecoverConfig();

}
