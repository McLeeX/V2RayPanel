create table if not exists user
(
    id       int auto_increment comment '用户id',
    name     varchar(255) comment '用户名',
    email    varchar(255) comment '用户邮箱',
    password varchar(255) comment '密码',
    role     int comment '角色',
    constraint user_pk primary key (id)
);

comment on table user is '用户表';
