--테이블 검색
select*from tab;

--visit 테이블 생성
create table visit (
    no number not null,
    writer varchar2(20) not null,
    memo varchar2(4000) not null,
    regdate date not null,
    constraint VISIT_PK primary key(no)
);

--방명록 시퀀스 생성
create sequence visit_seq
start with 1
increment by 1
nomaxvalue
nocache
nocycle;