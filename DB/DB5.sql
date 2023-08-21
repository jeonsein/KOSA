-- ������ ���̺� ���� + ���� �߰�
CREATE TABLE customer(
    ID varchar2(5),
    PWD varchar2(5),
    NAME varchar2(5)
);
ALTER TABLE customer ADD CONSTRAINT customer_id_pk PRIMARY KEY(id);
ALTER TABLE customer MODIFY pwd NOT NULL;

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

-- --------------------------------------