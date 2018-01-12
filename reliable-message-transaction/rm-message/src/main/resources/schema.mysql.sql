drop table if exists message;

create table message
(
   id                   character varying(50) not null,
   message_id           character varying(50) not null comment '消息id',
   message_data_type    character varying(10) not null comment '消息数据类型：json, xml',
   message_body         text not null comment '消息内容',
   message_retry_count  smallint not null default 0 comment '消息重发次数',
   consumer_queue       character varying(100) not null comment '消费队列',
   is_dead              smallint not null default 0 comment '是否死亡',
   status               character varying(20) not null comment '状态：待确认、待发送、已发送',
   version              integer default 0 comment '版本号',
   creator              character varying(100) comment '创建者',
   remark               character varying(200) comment '备注',
   create_time          datetime not null comment '创建时间',
   edit_time            datetime not null comment '修改时间',
   field1               character varying(200) comment '扩展字段1',
   field2               character varying(200) comment '扩展字段2',
   field3               character varying(200) comment '扩展字段3',
   primary key (id),
   key unique_message_message_id (message_id)
);

alter table message comment '事务消息表';

drop table if exists user;

create table user
(
   id                   bigint unsigned not null auto_increment comment '用户ID',
   username             varchar(20) comment '用户名',
   password             varchar(32) comment '用户密码',
   balance              numeric(12,2) not null default 0 comment '余额',
   point                int not null default 0 comment '积分值',
   create_time          timestamp comment '创建时间',
   modified_time        timestamp comment '修改时间',
   primary key (id)
);
