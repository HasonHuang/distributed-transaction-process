DROP TABLE IF EXISTS public."user" ;

CREATE TABLE "public"."user" (
  "id" bigserial NOT NULL,
  "username" varchar(20) COLLATE "pg_catalog"."default" NOT NULL DEFAULT NULL,
  "password" varchar(32) COLLATE "pg_catalog"."default" NOT NULL DEFAULT NULL,
  "balance" numeric(12,2) NOT NULL DEFAULT 0,
  "point" int4 NOT NULL DEFAULT 0,
  "create_time" timestamp with time zone NOT NULL DEFAULT now(),
  "modified_time" timestamp with time zone NOT NULL DEFAULT now(),
  CONSTRAINT "user_pkey" PRIMARY KEY ("id")
)
;

ALTER TABLE "public"."user"
  OWNER TO "postgres";

COMMENT ON COLUMN "public"."user"."id" IS '用户ID';

COMMENT ON COLUMN "public"."user"."username" IS '用户名';

COMMENT ON COLUMN "public"."user"."password" IS '用户密码';

COMMENT ON COLUMN "public"."user"."create_time" IS '创建时间';

COMMENT ON COLUMN "public"."user"."modified_time" IS '修改时间';

COMMENT ON COLUMN "public"."user"."balance" IS '余额';

COMMENT ON COLUMN "public"."user"."point" IS '积分值';




DROP TABLE IF EXISTS public.message;
CREATE TABLE public.message
(
  id character varying(50) NOT NULL,
  message_id character varying(50) NOT NULL, -- 消息id
  message_data_type character varying(10) NOT NULL, -- 消息数据类型：json, xml
  message_body text NOT NULL, -- 消息内容
  message_retry_count smallint NOT NULL DEFAULT 0, -- 消息重发次数
  consumer_queue character varying(100) NOT NULL, -- 消费队列
  is_dead smallint NOT NULL DEFAULT 0, -- 是否死亡
  status character varying(20) NOT NULL, -- 状态：待确认、待发送、已发送
  version integer DEFAULT 0, -- 版本号
  creator character varying(100), -- 创建者
  remark character varying(200), -- 备注
  create_time timestamp with time zone NOT NULL, -- 创建时间
  edit_time timestamp with time zone NOT NULL, -- 修改时间
  field1 character varying(200), -- 扩展字段1
  field2 character varying(200), -- 扩展字段2
  field3 character varying(200), -- 扩展字段3
  CONSTRAINT message_pkey_id PRIMARY KEY (id),
  CONSTRAINT unique_message_message_id UNIQUE (message_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.message
  OWNER TO postgres;
COMMENT ON TABLE public.message
  IS '事务消息表';
COMMENT ON COLUMN public.message.message_id IS '消息id';
COMMENT ON COLUMN public.message.message_data_type IS '消息数据类型：json, xml';
COMMENT ON COLUMN public.message.message_body IS '消息内容';
COMMENT ON COLUMN public.message.message_retry_count IS '消息重发次数';
COMMENT ON COLUMN public.message.consumer_queue IS '消费队列';
COMMENT ON COLUMN public.message.is_dead IS '是否死亡';
COMMENT ON COLUMN public.message.status IS '状态：待确认、待发送、已发送';
COMMENT ON COLUMN public.message.version IS '版本号';
COMMENT ON COLUMN public.message.creator IS '创建者';
COMMENT ON COLUMN public.message.remark IS '备注';
COMMENT ON COLUMN public.message.create_time IS '创建时间';
COMMENT ON COLUMN public.message.edit_time IS '修改时间';
COMMENT ON COLUMN public.message.field1 IS '扩展字段1';
COMMENT ON COLUMN public.message.field2 IS '扩展字段2';
COMMENT ON COLUMN public.message.field3 IS '扩展字段3';