
CREATE DATABASE "tcc_user";

create sequence user_id_seq;

CREATE TABLE public."user"
(
  id bigint NOT NULL DEFAULT nextval('user_id_seq'::regclass), -- 用户ID
  username character varying(20) NOT NULL DEFAULT NULL::character varying, -- 用户名
  password character varying(32) NOT NULL DEFAULT NULL::character varying, -- 用户密码
  create_time timestamp with time zone NOT NULL DEFAULT now(), -- 创建时间
  modified_time timestamp with time zone NOT NULL DEFAULT now(), -- 修改时间
  CONSTRAINT user_pkey PRIMARY KEY (id),
  CONSTRAINT unique_username UNIQUE (username)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public."user"
  OWNER TO postgres;
COMMENT ON COLUMN public."user".id IS '用户ID';
COMMENT ON COLUMN public."user".username IS '用户名';
COMMENT ON COLUMN public."user".password IS '用户密码';
COMMENT ON COLUMN public."user".create_time IS '创建时间';
COMMENT ON COLUMN public."user".modified_time IS '修改时间';

