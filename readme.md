# 分布式事务解决方案

常见的解决方案有：可靠消息最终一致性、最大努力通知型、TCC补偿性，项目示例基于`Spring Cloud`实现可靠消息最终一致性和TCC补偿性。

## 模块说明

- ms-core：公共模块
- ms-discovery-eureka：服务注册与发现
- reliable-message-transaction：可靠消息最终一致性的示例
- tcc-transaction：TCC补偿性的示例
