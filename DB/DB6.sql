-- #1. Sequence 객체: 일련번호 발급용 객체
CREATE SEQUENCE order_seq; -- 생성

-- 생성 삭제
CREATE SEQUENCE test_seq;
DROP SEQUENCE test_seq;

-- Sequence 설정
CREATE SEQUENCE test_seq
START WITH 11
INCREMENT BY 2 -- 2씩 증가
MAXVALUE 30
MINVALUE 0 -- 최대값 넘어가면 0부터 재시작
CYCLE; -- 최소값으로 돌아감 (default 최소값 = 1)
-- ORA-04013: number to CACHE must be less than one cycle
-- CYCLE 수보다 CACHE 수가 더 크기 때문에 오류 발생함
-- = CACHE 사용하지 말라고 옵션 줘야함!
-- ▼▼▼
CREATE SEQUENCE test_seq
START WITH 11
INCREMENT BY 2 -- 2씩 증가
MAXVALUE 30
MINVALUE 0 -- 최대값 넘어가면 0부터 재시작
CYCLE
NOCACHE; -- CACHE 사용 안하는 옵션!

-- 일련번호 발급하기
SELECT test_seq.NEXTVAL FROM dual; -- 11
SELECT test_seq.NEXTVAL FROM dual; -- 13
SELECT test_seq.NEXTVAL FROM dual; -- 15
SELECT test_seq.NEXTVAL FROM dual; -- 17
SELECT test_seq.NEXTVAL FROM dual; -- 19
SELECT test_seq.NEXTVAL FROM dual; -- 21
SELECT test_seq.NEXTVAL FROM dual; -- 23
SELECT test_seq.NEXTVAL FROM dual; -- 25
SELECT test_seq.NEXTVAL FROM dual; -- 27
SELECT test_seq.NEXTVAL FROM dual; -- 29 (MAXVALUE = 30)
SELECT test_seq.NEXTVAL FROM dual; -- 0

-- 현재 일련번호 확인하기
SELECT test_seq.CURRVAL FROM dual;

-- 예제에 적용시키기 위해서 설정
SELECT * FROM order_info; -- 확인
DROP SEQUENCE order_seq; -- 시퀀스 삭제
CREATE SEQUENCE order_seq -- 시퀀스 재생성 (일련번호 4부터 시작)
START WITH 4;

-- 주문 기본 정보 추가
INSERT INTO order_info(order_no, order_id) 
VALUES(order_seq.NEXTVAL, 'A'); -- order_no에 sequence 객체 사용함!

-- 주문 상세 정보 추가
INSERT INTO order_line(order_line_no, order_prod_no, order_quantity) 
VALUES(order_seq.CURRVAL, 'C0001', 1);

INSERT INTO order_line(order_line_no, order_prod_no, order_quantity) 
VALUES(order_seq.CURRVAL, 'F0001', 2);

-- 롤백해보기
ROLLBACK;

SELECT * FROM order_info;
SELECT * FROM order_line; -- 확인
-- -------------------------------
-- #2. 트랜잭션: 작업단위
-- 계좌이체작업
-- 1) A계좌에서 100원이 출금
UPDATE account set balance = balance - 100 WHERE no = 'A'; -- 1건처리
-- 2) 100원이 B계좌로 입금
UPDATE account set balance = balance + 100 WHERE no = 'B'; -- 0건처리
-- DDL(CREATE, ALTER, DROP)은 트랜잭션 자동종료 ?
-- DML(INSERT, UPDATE, DELETE)은 트랜잭션 자동종료 ? → 수동종료가 필요함
--  - 수동종료#1: 복구(원상태로 복구하는 것) - ROLLBACK
--  - 수동종료#2: 완료 - COMMIT


commit;
rollback;