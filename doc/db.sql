-- ============================================================
-- AllSpark 官网数据库初始化脚本
-- 执行方式：mysql -uroot -p < doc/db.sql
-- 说明：会创建数据库 allspark、报名表 user、运营端管理员表 admin_user，
--       并初始化默认管理员 admin / allspark520（上线后请立即修改密码）。
-- ============================================================

create database if not exists allspark default character set utf8mb4;
use allspark;

-- 1. 报名用户表
create table if not exists user
(
    `user_id`     int auto_increment primary key comment '主键id',
    `user_name`   varchar(15) not null comment '名字',
    `qq_number`   varchar(15) not null unique comment 'QQ号',
    `grade`       tinyint(1)  not null comment '年级(0-3)',
    `direction`   varchar(10) not null comment '方向选择 前端 / 后端 / 产品经理',
    `progress`    text        null comment '学习进展',
    `create_time` timestamp   not null default current_timestamp comment '填写申请表的时间'
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 comment '储存普通用户信息的表';

-- 2. 运营端管理员表
create table if not exists admin_user
(
    `admin_id`    int auto_increment primary key comment '管理员id',
    `username`    varchar(30) not null unique comment '登录名',
    `password`    varchar(64) not null comment '密码 sha256(固定盐allspark + 用户名 + ":" + 明文)',
    `nickname`    varchar(30) null comment '昵称',
    `create_time` timestamp   not null default current_timestamp comment '创建时间'
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 comment '运营端管理员表';

-- 3. 初始化默认管理员：admin / allspark520
insert into admin_user(username, password, nickname)
select 'admin', '4386b402d4601805482f3406ea340bc742a4701429a66c59751add5a096c064e', '管理员'
where not exists (select 1 from admin_user where username = 'admin');