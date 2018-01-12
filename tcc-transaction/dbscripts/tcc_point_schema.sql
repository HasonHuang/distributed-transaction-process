create database tcc_point;

create sequence point_id_seq;

CREATE TABLE public.point
(
  id bigint NOT NULL DEFAULT nextval('point_id_seq'::regclass), -- 积分ID
  user_id bigint, -- 用户ID
  value bigint NOT NULL DEFAULT 0, -- 积分
  CONSTRAINT ponit_pkey PRIMARY KEY (id),
  CONSTRAINT unique_user_id UNIQUE (user_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.point
  OWNER TO postgres;
COMMENT ON COLUMN public.point.id IS '积分ID';
COMMENT ON COLUMN public.point.user_id IS '用户ID';
COMMENT ON COLUMN public.point.value IS '积分';