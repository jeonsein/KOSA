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