select *
from tab;
-- 회원 테이블
create table student
(
    id       varchar2(12)  not null,
    pass     varchar2(12)  not null,
    name     varchar2(12)  not null,
    phone1   varchar2(3)   not null,
    phone2   varchar2(4)   not null,
    phone3   varchar2(4)   not null,
    email    varchar2(50)  not null,
    zipcode  varchar2(7)   not null,
    address1 varchar2(120) not null,
    address2 varchar2(50)  not null,
    constraint student_pk primary key (id)
);

-- 우편번호 테이블
create table zipcode
(
    seq     number(5)    not null,
    zipcode varchar2(7)  not null,
    sido    varchar2(6)  not null,
    gugun   varchar2(30) not null,
    dong    varchar2(50) not null,
    ri      varchar2(80),
    bunji   varchar2(20),
    constraint zipcode_pk primary key (seq)
)