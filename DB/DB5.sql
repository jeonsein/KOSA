-- 예제용 테이블 생성 + 조건 추가
-- #1 customer
CREATE TABLE customer(
    ID varchar2(5),
    PWD varchar2(5),
    NAME varchar2(5)
);
ALTER TABLE customer ADD CONSTRAINT customer_id_pk PRIMARY KEY(id);
ALTER TABLE customer MODIFY pwd NOT NULL;

-- #2 product
CREATE TABLE product(
    prod_no varchar2(5),
    prod_name varchar2(50),
    prod_price varchar2(6),
    prod_mf_dt DATE,
    prod_detail varchar2(4000)
);
ALTER TABLE product ADD CONSTRAINT prod_no_pk PRIMARY KEY(prod_no);
ALTER TABLE product MODIFY prod_name NOT NULL;
-- CHECK나 UNIQUE에도 NULL값 저장 가능함! NULL값 저장 안하려면 NOT NULL 줘야 함!
ALTER TABLE product ADD CONSTRAINT prod_price_ck CHECK(prod_price>=0);

-- #3 order_info
CREATE TABLE order_info(
    order_no number,
    order_id varchar2(5),
    order_dt DATE DEFAULT SYSDATE
);
ALTER TABLE order_info ADD CONSTRAINT order_info_no_pk PRIMARY KEY(order_no);
ALTER TABLE order_info ADD CONSTRAINT order_id_fk FOREIGN KEY(order_id) REFERENCES customer(id); 
-- customer의 id 컬럼을 참조하겠다!

-- #4 order_line
CREATE TABLE order_line(
    order_line_no number,
    order_prod_no varchar2(5),
    order_quantity number(3)
);
-- 복합키 설정!! 테이블 레벨로만 가능!
ALTER TABLE order_line ADD CONSTRAINT order_line_pk PRIMARY KEY(order_line_no, order_prod_no);
ALTER TABLE order_line ADD CONSTRAINT order_prod_no_fk FOREIGN KEY(order_prod_no) REFERENCES product(prod_no);
ALTER TABLE order_line ADD CONSTRAINT order_line_no_fk FOREIGN KEY(order_line_no) REFERENCES order_info(order_no);
-- --------------------------------------
-- # 제약조건정보를 담고있는 Dictionary용 뷰 객체: USER_CONSTRAINTS
SELECT * FROM USER_CONSTRAINTS;
SELECT * FROM user_constraints WHERE table_name = 'CUSTOMER';
-- --------------------------------------
-- # 데이터 삽입
-- INSERT INTO customer(id, pwd, name) VALUES('A',NULL, 'A'); -- 'NULL'이나 NULL이다 다 널임!!
-- ▲ pwd에 NOT NULL 제약 조건 걸려있기 때문에 ORA-01400: cannot insert NULL into ("HR"."CUSTOMER"."PWD")
-- # customer
INSERT INTO customer(id, pwd, name) VALUES('B','b', 'B'); -- OK
INSERT INTO customer(id, pwd, name) VALUES('B','b11', 'B11'); -- id값 중복 안됨! ORA-00001: unique constraint (HR.CUSTOMER_ID_PK) violated
INSERT INTO customer VALUES('A', 'a', 'A'); -- OK
INSERT INTO customer VALUES('C', 'c', 'C'); -- OK

-- # product
INSERT INTO product(prod_no, prod_name, prod_price) VALUES('C0001', '아메리카노', 1000);
INSERT INTO product(prod_no, prod_name, prod_price) VALUES('C0002', '아이스아메리카노', 1000);
INSERT INTO product(prod_no, prod_name, prod_price) VALUES('C0003', '라떼', 1500);
INSERT INTO product(prod_no, prod_name, prod_price) VALUES('C0004', '아이스라떼', 1500);
INSERT INTO product(prod_no, prod_name, prod_price) VALUES('C0005', '카푸치노', 1500);

-- # order_info
INSERT INTO order_info(order_no, order_id) VALUES(1, 'A'); -- OK
INSERT INTO order_info(order_no, order_id) VALUES(1, 'X'); -- ORA-00001: unique constraint (HR.ORDER_INFO_NO_PK) violated
INSERT INTO order_info(order_no, order_id) VALUES(2, 'X'); -- ORA-02291: integrity constraint (HR.ORDER_ID_FK) violated - parent key not found
INSERT INTO order_info(order_no, order_id) VALUES(2, 'B'); -- OK
INSERT INTO order_info(order_no, order_id) VALUES(3, 'A'); -- OK
SELECT * FROM order_info;

-- # order_line
INSERT INTO order_line(order_line_no, order_prod_no, order_quantity) VALUES(0, 'C0001', 1);
-- ▲ ORA-02291: integrity constraint (HR.ORDER_LINE_NO_FK) violated - parent key not found
-- 주문번호 0은 존재하지 않음! 1~3만 추가해놨음!
INSERT INTO order_line(order_line_no, order_prod_no, order_quantity) VALUES(1, 'X', 1);
-- ▲ ORA-02291: integrity constraint (HR.ORDER_PROD_NO_FK) violated - parent key not found
INSERT INTO order_line(order_line_no, order_prod_no, order_quantity) VALUES(1, 'C0001', 2); -- OK
INSERT INTO order_line(order_line_no, order_prod_no, order_quantity) VALUES(1, 'C0001', 2);
-- ▲ ORA-00001: unique constraint (HR.ORDER_LINE_PK) violated (중복이라서 오류)
INSERT INTO order_line(order_line_no, order_prod_no, order_quantity) VALUES(2, NULL, 9);
-- ▲ SQL 오류: ORA-01400: cannot insert NULL into ("HR"."ORDER_LINE"."ORDER_PROD_NO")
INSERT INTO order_line(order_line_no, order_prod_no, order_quantity) VALUES(NULL, 'C0001', 9);
-- ▲ SQL 오류: ORA-01400: cannot insert NULL into ("HR"."ORDER_LINE"."ORDER_LINE_NO")
INSERT INTO order_line(order_line_no, order_prod_no, order_quantity) VALUES(2, 'C0001', 4); -- OK
INSERT INTO order_line(order_line_no, order_prod_no, order_quantity) VALUES(2, 'C0002', 1); -- OK
INSERT INTO order_line(order_line_no, order_prod_no, order_quantity) VALUES(2, 'C0005', 1); -- OK
INSERT INTO order_line(order_line_no, order_prod_no, order_quantity) VALUES(3, 'C0002', 1); -- OK
-- --------------------------------------
-- # 자료 수정
UPDATE product SET prod_no = 'F0001', prod_name = '바나나' WHERE prod_no = 'C0003'; -- OK
-- ▲ 참조 안된 상품을 수정

-- FK 제약 조건 위배
UPDATE product SET prod_no = 'X' WHERE prod_no = 'C0001'; 
-- ▲ 참조된 상품을 수정
-- ORA-02292: integrity constraint (HR.ORDER_PROD_NO_FK) violated - child record found
-- 무결성 제약 조건 위반! PK의 역할을 담당하는 컬럼은 변경하지 말기!

UPDATE product SET prod_price = prod_price + 100 WHERE prod_no = 'C0001'; -- OK

-- CHECK 제약 조건 위배
UPDATE product SET prod_price = -1 WHERE prod_no = 'F0001';
-- ORA-02290: check constraint (HR.PROD_PRICE_CK) violated
-- --------------------------------------
-- # 자료 삭제
-- 참조 안된 상품을 삭제
DELETE product WHERE prod_no = 'C0004'; -- OK

-- 참조된 상품을 삭제
DELETE product WHERE prod_no = 'C0001';
-- FK 제약 조건 위배
-- ORA-02292: integrity constraint (HR.ORDER_PROD_NO_FK) violated - child record found
-- 만약에 자식쪽에 ON DELETE CASCADE 옵션 주면 삭제 가능함! ▼ (권장 X)
-- ex)
-- ALTER TABLE order_line 
-- ADD CONSTRAINT order_prod_no_fk 
-- FOREIGN KEY(order_prod_no) REFERENCES product(prod_no) ON DELETE CASCADE;

-- --------------------------------------
-- # 뷰 객체
-- SELECT문!
SELECT info.order_no, order_id, order_prod_no, prod_name, prod_price,
		   order_quantity, order_dt
FROM order_info info JOIN order_line line ON(info.order_no = line.order_line_no)
					 JOIN product p ON(line.order_prod_no = p.prod_no);

-- 위 SQL구문 대신 뷰 객체를 생성!
CREATE VIEW vw_order 
AS SELECT info.order_no, order_id,order_prod_no, prod_name, prod_price,
			order_quantity, order_dt
FROM order_info info JOIN order_line line ON (info.order_no =  line.order_line_no)
                     JOIN product p ON (line.order_prod_no = p.prod_no);

-- 위 SQL 구문과 동일 효과
SELECT * FROM vw_order;

-- 구조 변경을 위한 ALTER 사용이 불가능! VIEW는 CREATE OR REPLACE VIEW 사용해야 함!
CREATE OR REPLACE VIEW vw_order
AS SELECT info.order_no "주문번호", order_id "주문자아이디",
					order_prod_no "상품번호", prod_name "상품명", prod_price "상품가격",
					order_quantity "주문수량", order_dt "주문일자"
FROM order_info info JOIN order_line line ON (info.order_no =  line.order_line_no)
										 JOIN product p ON (line.order_prod_no = p.prod_no);

SELECT 주문번호 FROM vw_order; -- OK
SELECT order_no FROM vw_order; -- ERROR

-- 뷰 삭제
DROP VIEW vw_order;

COMMIT;