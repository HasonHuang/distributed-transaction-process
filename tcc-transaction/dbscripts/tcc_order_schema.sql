
create database tcc_order;

create sequence order_id_seq;

CREATE TABLE public.order
(
  id bigint NOT NULL DEFAULT nextval('order_id_seq'::regclass), -- 订单号
  user_id bigint NOT NULL, -- 用户ID
  amount numeric(12,2) NOT NULL, -- 金额
  status varchar(20) NOT NULL, -- 状态
  CONSTRAINT order_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.order
  OWNER TO postgres;
COMMENT ON COLUMN public.order.id IS '资金账户订单id';
COMMENT ON COLUMN public.order.user_id IS '用户ID';
COMMENT ON COLUMN public.order.amount IS '金额';
COMMENT ON COLUMN public.order.status IS '状态';