
package org.mengyun.tcctransaction.api;

/**
 * 事务状态枚举.
 * Created by changmingxie on 10/28/15.
 */
public enum TransactionStatus {

	/**
	 * 尝试中:1.
	 */
    TRYING(1), 
    
    /**
     * 确认中:2.
     */
    CONFIRMING(2), 
    
    /**
     * 取消中:3.
     */
    CANCELLING(3);

    private int id;

     TransactionStatus(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static TransactionStatus valueOf(int id) {

        switch (id) {
            case 1:
                return TRYING;
            case 2:
                return CONFIRMING;
            default:
                return CANCELLING;
        }
    }

}
