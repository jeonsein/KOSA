-- #1. Sequence ��ü: �Ϸù�ȣ �߱޿� ��ü
CREATE SEQUENCE order_seq; -- ����

-- ���� ����
CREATE SEQUENCE test_seq;
DROP SEQUENCE test_seq;

-- Sequence ����
CREATE SEQUENCE test_seq
START WITH 11
INCREMENT BY 2 -- 2�� ����
MAXVALUE 30
MINVALUE 0 -- �ִ밪 �Ѿ�� 0���� �����
CYCLE; -- �ּҰ����� ���ư� (default �ּҰ� = 1)
-- ORA-04013: number to CACHE must be less than one cycle
-- CYCLE ������ CACHE ���� �� ũ�� ������ ���� �߻���
-- = CACHE ������� ����� �ɼ� �����!
-- ����
CREATE SEQUENCE test_seq
START WITH 11
INCREMENT BY 2 -- 2�� ����
MAXVALUE 30
MINVALUE 0 -- �ִ밪 �Ѿ�� 0���� �����
CYCLE
NOCACHE; -- CACHE ��� ���ϴ� �ɼ�!

-- �Ϸù�ȣ �߱��ϱ�
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

-- ���� �Ϸù�ȣ Ȯ���ϱ�
SELECT test_seq.CURRVAL FROM dual;

-- ������ �����Ű�� ���ؼ� ����
SELECT * FROM order_info; -- Ȯ��
DROP SEQUENCE order_seq; -- ������ ����
CREATE SEQUENCE order_seq -- ������ ����� (�Ϸù�ȣ 4���� ����)
START WITH 4;

-- �ֹ� �⺻ ���� �߰�
INSERT INTO order_info(order_no, order_id) 
VALUES(order_seq.NEXTVAL, 'A'); -- order_no�� sequence ��ü �����!

-- �ֹ� �� ���� �߰�
INSERT INTO order_line(order_line_no, order_prod_no, order_quantity) 
VALUES(order_seq.CURRVAL, 'C0001', 1);

INSERT INTO order_line(order_line_no, order_prod_no, order_quantity) 
VALUES(order_seq.CURRVAL, 'F0001', 2);

SELECT * FROM order_line; -- Ȯ��

commit;
rollback;