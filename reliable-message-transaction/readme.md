# 可靠消息最终一致性

## 约束

被动方的处理结果不影响主动方的处理结果，被动方的消息处理操作是幂等操作

## 适用范围

- 消息数据独立存储、独立伸缩，降低业务系统与消息系统间的耦合
- 对最终一致性时间敏感度较高，降低业务被动方实现成本

## 模块说明

- rm-account：用户模块
- rm-account-point： 积分模块，为了简单演示，积分账户与用户使用同一张表
- rm-account-web：门户模块，展示web页面，如：注册页面等
- rm-message：可靠消息服务模块，包含了：消息状态确认子系统和消息恢复子系统，为了简单展示融合到可靠消息服务模块中。
- rm-message-consumer：消息业务消费模端。如：获取队列元素后增加用户积分。

> 完整的可靠消息服务模块应该包含：消息管理子系统、消息状态确认子系统、消息恢复子系统和实时消息子系统（如RabbitMQ）。此示例没有实现消息管理子系统，该系统可以查看、干预事务操作。

## 示例演示

### 示例说明

1. 打开注册页面，http://localhost:8001/register
2. 填写用户名和密码进行注册
3. 注册成功会返回JSON格式的用户信息

> 运行rm-account-web中的`MockConcurrent`模拟高并发注册。

### 启动顺序

1. 启动 ms-discovery-eureka
2. 启动 rm-message、rm-account、rm-account-web、rm-account-point
3. 启动 rm-message-consumer
