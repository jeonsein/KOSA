-- ������ ���̺� ���� + ���� �߰�
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
-- CHECK�� UNIQUE���� NULL�� ���� ������! NULL�� ���� ���Ϸ��� NOT NULL ��� ��!
ALTER TABLE product ADD CONSTRAINT prod_price_ck CHECK(prod_price>=0);

-- #3 order_info
CREATE TABLE order_info(
    order_no number,
    order_id varchar2(5),
    order_dt DATE DEFAULT SYSDATE
);
ALTER TABLE order_info ADD CONSTRAINT order_info_no_pk PRIMARY KEY(order_no);
ALTER TABLE order_info ADD CONSTRAINT order_id_fk FOREIGN KEY(order_id) REFERENCES customer(id); 
-- customer�� id �÷��� �����ϰڴ�!

-- #4 order_line
CREATE TABLE order_line(
    order_line_no number,
    order_prod_no varchar2(5),
    order_quantity number(3)
);
-- ����Ű ����!! ���̺� �����θ� ����!
ALTER TABLE order_line ADD CONSTRAINT order_line_pk PRIMARY KEY(order_line_no, order_prod_no);
ALTER TABLE order_line ADD CONSTRAINT order_prod_no_fk FOREIGN KEY(order_prod_no) REFERENCES product(prod_no);
ALTER TABLE order_line ADD CONSTRAINT order_line_no_fk FOREIGN KEY(order_line_no) REFERENCES order_info(order_no);
-- --------------------------------------
-- # �������������� ����ִ� Dictionary�� �� ��ü: USER_CONSTRAINTS
SELECT * FROM USER_CONSTRAINTS;
SELECT * FROM user_constraints WHERE table_name = 'CUSTOMER';
-- --------------------------------------
-- # ������ ����
-- INSERT INTO customer(id, pwd, name) VALUES('A',NULL, 'A'); -- 'NULL'�̳� NULL�̴� �� ����!!
-- �� pwd�� NOT NULL ���� ���� �ɷ��ֱ� ������ ORA-01400: cannot insert NULL into ("HR"."CUSTOMER"."PWD")
-- # customer
INSERT INTO customer(id, pwd, name) VALUES('B','b', 'B'); -- OK
INSERT INTO customer(id, pwd, name) VALUES('B','b11', 'B11'); -- id�� �ߺ� �ȵ�! ORA-00001: unique constraint (HR.CUSTOMER_ID_PK) violated
INSERT INTO customer VALUES('A', 'a', 'A'); -- OK
INSERT INTO customer VALUES('C', 'c', 'C'); -- OK

-- # product
INSERT INTO product(prod_no, prod_name, prod_price) VALUES('C0001', '�Ƹ޸�ī��', 1000);
INSERT INTO product(prod_no, prod_name, prod_price) VALUES('C0002', '���̽��Ƹ޸�ī��', 1000);
INSERT INTO product(prod_no, prod_name, prod_price) VALUES('C0003', '��', 1500);
INSERT INTO product(prod_no, prod_name, prod_price) VALUES('C0004', '���̽���', 1500);
INSERT INTO product(prod_no, prod_name, prod_price) VALUES('C0005', 'īǪġ��', 1500);

-- # order_info
INSERT INTO order_info(order_no, order_id) VALUES(1, 'A'); -- OK
INSERT INTO order_info(order_no, order_id) VALUES(1, 'X'); -- ORA-00001: unique constraint (HR.ORDER_INFO_NO_PK) violated
INSERT INTO order_info(order_no, order_id) VALUES(2, 'X'); -- ORA-02291: integrity constraint (HR.ORDER_ID_FK) violated - parent key not found
INSERT INTO order_info(order_no, order_id) VALUES(2, 'B'); -- OK
INSERT INTO order_info(order_no, order_id) VALUES(3, 'A'); -- OK
SELECT * FROM order_info;

-- # order_line
INSERT INTO order_line(order_line_no, order_prod_no, order_quantity) VALUES(0, 'C0001', 1);
-- �� ORA-02291: integrity constraint (HR.ORDER_LINE_NO_FK) violated - parent key not found
-- �ֹ���ȣ 0�� �������� ����! 1~3�� �߰��س���!
INSERT INTO order_line(order_line_no, order_prod_no, order_quantity) VALUES(1, 'X', 1);
-- �� ORA-02291: integrity constraint (HR.ORDER_PROD_NO_FK) violated - parent key not found
INSERT INTO order_line(order_line_no, order_prod_no, order_quantity) VALUES(1, 'C0001', 2); -- OK
INSERT INTO order_line(order_line_no, order_prod_no, order_quantity) VALUES(1, 'C0001', 2);
-- �� ORA-00001: unique constraint (HR.ORDER_LINE_PK) violated (�ߺ��̶� ����)
INSERT INTO order_line(order_line_no, order_prod_no, order_quantity) VALUES(2, NULL, 9);
-- �� SQL ����: ORA-01400: cannot insert NULL into ("HR"."ORDER_LINE"."ORDER_PROD_NO")
INSERT INTO order_line(order_line_no, order_prod_no, order_quantity) VALUES(NULL, 'C0001', 9);
-- �� SQL ����: ORA-01400: cannot insert NULL into ("HR"."ORDER_LINE"."ORDER_LINE_NO")
INSERT INTO order_line(order_line_no, order_prod_no, order_quantity) VALUES(2, 'C0001', 4); -- OK
INSERT INTO order_line(order_line_no, order_prod_no, order_quantity) VALUES(2, 'C0002', 1); -- OK
INSERT INTO order_line(order_line_no, order_prod_no, order_quantity) VALUES(2, 'C0005', 1); -- OK
INSERT INTO order_line(order_line_no, order_prod_no, order_quantity) VALUES(3, 'C0002', 1); -- OK
-- --------------------------------------
-- # �ڷ� ����
UPDATE product SET prod_no = 'F0001', prod_name = '�ٳ���' WHERE prod_no = 'C0003'; -- OK
-- �� ���� �ȵ� ��ǰ�� ����

-- FK ���� ���� ����
UPDATE product SET prod_no = 'X' WHERE prod_no = 'C0001'; 
-- �� ������ ��ǰ�� ����
-- ORA-02292: integrity constraint (HR.ORDER_PROD_NO_FK) violated - child record found
-- ���Ἲ ���� ���� ����! PK�� ������ ����ϴ� �÷��� �������� ����!

UPDATE product SET prod_price = prod_price + 100 WHERE prod_no = 'C0001'; -- OK

-- CHECK ���� ���� ����
UPDATE product SET prod_price = -1 WHERE prod_no = 'F0001';
-- ORA-02290: check constraint (HR.PROD_PRICE_CK) violated
-- --------------------------------------
-- # �ڷ� ����
-- ���� �ȵ� ��ǰ�� ����
DELETE product WHERE prod_no = 'C0004'; -- OK

-- ������ ��ǰ�� ����
DELETE product WHERE prod_no = 'C0001';
-- FK ���� ���� ����
-- ORA-02292: integrity constraint (HR.ORDER_PROD_NO_FK) violated - child record found
-- ���࿡ �ڽ��ʿ� ON DELETE CASCADE �ɼ� �ָ� ���� ������! �� (���� X)
-- ex)
-- ALTER TABLE order_line 
-- ADD CONSTRAINT order_prod_no_fk 
-- FOREIGN KEY(order_prod_no) REFERENCES product(prod_no) ON DELETE CASCADE;

-- --------------------------------------
-- # �� ��ü
-- SELECT��!
SELECT info.order_no, order_id, order_prod_no, prod_name, prod_price,
		   order_quantity, order_dt
FROM order_info info JOIN order_line line ON(info.order_no = line.order_line_no)
					 JOIN product p ON(line.order_prod_no = p.prod_no);

-- �� SQL���� ��� �� ��ü�� ����!
CREATE VIEW vw_order 
AS SELECT info.order_no, order_id,order_prod_no, prod_name, prod_price,
			order_quantity, order_dt
FROM order_info info JOIN order_line line ON (info.order_no =  line.order_line_no)
                     JOIN product p ON (line.order_prod_no = p.prod_no);

-- �� SQL ������ ���� ȿ��
SELECT * FROM vw_order;

-- ���� ������ ���� ALTER ����� �Ұ���! VIEW�� CREATE OR REPLACE VIEW ����ؾ� ��!
CREATE OR REPLACE VIEW vw_order
AS SELECT info.order_no "�ֹ���ȣ", order_id "�ֹ��ھ��̵�",
					order_prod_no "��ǰ��ȣ", prod_name "��ǰ��", prod_price "��ǰ����",
					order_quantity "�ֹ�����", order_dt "�ֹ�����"
FROM order_info info JOIN order_line line ON (info.order_no =  line.order_line_no)
										 JOIN product p ON (line.order_prod_no = p.prod_no);

SELECT �ֹ���ȣ FROM vw_order; -- OK
SELECT order_no FROM vw_order; -- ERROR

-- �� ����
DROP VIEW vw_order;

COMMIT;