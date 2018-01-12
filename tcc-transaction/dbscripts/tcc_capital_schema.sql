
create database "tcc_capital";

create sequence capital_id_seq;

CREATE TABLE public.capital_account
(
  id bigint NOT NULL DEFAULT nextval('capital_id_seq'::regclass),
   balance_amount numeric(12,2) NOT NULL DEFAULT 0,
   user_id bigint NOT NULL,
  CONSTRAINT capital_pkey PRIMARY KEY (id),
  CONSTRAINT unique_user_id UNIQUE (user_id)
)
WITH (
  OIDS = FALSE
)
;
COMMENT ON COLUMN public.capital_account.id IS '资金账户ID';
COMMENT ON COLUMN public.capital_account.balance_amount IS '余额';
COMMENT ON COLUMN public.capital_account.user_id IS '用户ID';

CREATE TABLE public.capital_order
(
  id bigserial NOT NULL, -- 资金账户ID
  user_id bigint NOT NULL, -- 用户ID
  merchant_order_no character varying(32), -- 商家订单号
  amount numeric(12,2) NOT NULL, -- 金额
  status varchar(20) NOT NULL, -- 状态
  CONSTRAINT capital_order_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.capital_account
  OWNER TO postgres;
COMMENT ON COLUMN public.capital_order.id IS '资金账户订单id';
COMMENT ON COLUMN public.capital_order.user_id IS '用户ID';
COMMENT ON COLUMN public.capital_order.merchant_order_no IS '商家订单号';
COMMENT ON COLUMN public.capital_order.amount IS '金额';
COMMENT ON COLUMN public.capital_order.status IS '状态';

