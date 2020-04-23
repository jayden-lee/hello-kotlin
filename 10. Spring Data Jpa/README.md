## Spec
- Server : UTC Timezone
- Database : UTC Timezone

## DDL
```sql
drop table if exists user;
create table user
(
    user_id         BIGINT      NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '사용자 ID',
    username        varchar(30) NOT NULL COMMENT '이름',
    kst_withdraw_at datetime    NOT NULL COMMENT '탈퇴일시',
    created_at      timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일시',
    updated_at      timestamp   NULL     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일시'
) ENGINE = INNODB CHARACTER SET utf8mb4 COMMENT = '사용자';
```

## Table Data
```text
user_id | username | kst_withdraw_at | created_at | updated_at
1	User 1	2020-04-24 00:28:21	2020-04-23 15:28:21	2020-04-23 15:28:21
2	User 2	2020-04-24 00:28:21	2020-04-23 15:28:21	2020-04-23 15:28:21
3	User 3	2020-04-24 00:28:21	2020-04-23 15:28:21	2020-04-23 15:28:21
4	User 4	2020-04-24 00:28:21	2020-04-23 15:28:21	2020-04-23 15:28:21
```

## Api Data
```json
{
    "id": 1,
    "username": "User 1",
    "kst_withdraw_at": "2020-04-24T00:28:21",
    "created_at": "2020-04-23T15:28:21",
    "updated_at": "2020-04-23T15:28:21"
}
```
