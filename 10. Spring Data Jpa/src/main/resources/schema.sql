drop table if exists user;
create table user
(
    user_id         BIGINT      NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '사용자 ID',
    username        varchar(30) NOT NULL COMMENT '이름',
    kst_withdraw_at datetime    NOT NULL COMMENT '탈퇴일시',
    created_at      timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일시',
    updated_at      timestamp   NULL     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일시'
) ENGINE = INNODB CHARACTER SET utf8mb4 COMMENT = '사용자';