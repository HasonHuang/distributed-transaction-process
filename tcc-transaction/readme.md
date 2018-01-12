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

### 单层嵌套事务

#### 业务流程

1. 打开注册页面 http://localhost:9000/users/register
2. 输入用户信息并注册
3. 系统创建用户，同时创建资金账户和积分账户；

#### 启动顺序

1. 启动 ms-discovery-eureka
2. 启动 tcc-account、tcc-integral

### 多层嵌套事务

2. 用户充值：用户为资金账户充值，生成订单，充值成功后增加账户资金，修改相应的积分。

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