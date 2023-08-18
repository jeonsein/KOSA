-- #INLINE VIEW: FROM������ ����ϴ� SubQuery#
-- ����� ���, �̸�, �޿��� ����Ͻÿ�. ��, �޿��� ���� �޴� ������ ����Ͻÿ�.
SELECT employee_id, first_name, salary
FROM employees
ORDER BY salary DESC;

-- #ROWNUM: ���ȣ(����Ŭ�� ����Ǿ� ����)
-- ����� ���ȣ, ���, �̸�, �޿��� ����Ͻÿ�. ��, �޿��� ���� �޴� ������ ����Ͻÿ�.
SELECT ROWNUM, employee_id, first_name, salary
FROM employees
ORDER BY salary DESC;
-- ROWNUM ���� ?
SELECT ROWNUM, employee_id, first_name, salary
FROM (SELECT employee_id, first_name, salary -- INLINE VIEW
      FROM employees
      ORDER BY salary DESC);

-- #TOP-N Query
-- ����� ���ȣ, ���, �̸�, �޿��� ����Ͻÿ�. ��, �޿��� ���� �޴� ��� TOP5�� ����Ͻÿ�.
SELECT ROWNUM, employee_id, first_name, salary
FROM (SELECT employee_id, first_name, salary -- INLINE VIEW
      FROM employees
      ORDER BY salary DESC)
WHERE ROWNUM BETWEEN 1 AND 5;

-- ����� ���ȣ, ���, �̸�, �޿��� ����Ͻÿ�. ���� �޿��� �޴� ������� �����Ͽ� 11~20��° ����� ����Ѵ�.
SELECT ROWNUM, employee_id, first_name, salary
FROM (SELECT employee_id, first_name, salary
      FROM employees
      ORDER BY salary DESC)
WHERE ROWNUM BETWEEN 11 AND 20; -- 0
-- ROWNUM�� 1���� �����ϱ� ������ ? �̷��� ����ؼ��� �ȵ�!
-- ����Ǿ� �ִ� SUDO �÷��� ROWNUM�� �������� ����ϰ� �ֱ� ������,
-- �ݺ����� ������ �������� ROWNUM�� ��� 1�� ������
-- �ذ��ϱ� ���ؼ��� INLINE VIEW�� �� �� �� �ʿ���! ?
SELECT *
FROM (SELECT ROWNUM rn, employee_id, first_name, salary -- INLINE VIEW#1 2.ROWNUM �߱�
      FROM (SELECT employee_id, first_name, salary -- INLINE VIEW#2, 1.�����ϰ�
            FROM employees
            ORDER BY salary DESC)
      )
WHERE rn BETWEEN 11 AND 20;
-- WHERE ROWNUM BETWEEN 11 AND 20; -- ���ʿ��� ����� ROWNUM�� �̹� ���ǰ� ������ ������,
-- ROWNUM�� ��Ī�� �༭ ����ϱ�!
-- ��Ī �ѹ� �� �ֱ�! ?
SELECT *
FROM (SELECT ROWNUM rn, a.*
      FROM (SELECT *
            FROM employees
            ORDER BY salary DESC) a -- INLINE VIEW�� ���� ��Ī
      )
WHERE rn BETWEEN 11 AND 20;

-- ---------------
-- #Scalar Query: SELECT������ ����ϴ� SubQuery#
-- ����� ���, �μ���ȣ, �μ����� ����Ͻÿ�.
-- 1. JOIN���� �ذ�
SELECT employee_id, e.department_id, department_name
FROM employees e JOIN departments d ON(e.department_id = d.department_id);
-- 2. Scalar Query�� �ذ�
SELECT employee_id, department_id
        , (SELECT department_name
           FROM departments
           WHERE department_id = e.department_id
           -- WHERE departments.department_id
           -- WHERE department_id = employees.department_id
           )
FROM employees e;

-- ---------------
-- �μ����� 'IT'�� �μ��� �ٹ��ϴ� ������� ���, �̸��� ����Ͻÿ�.
SELECT employee_id, first_name
FROM employees e JOIN departments d ON(e.department_id = d.department_id)
WHERE department_name = 'IT';
-- �� ���̺�� �ذ� �����ϱ⿡ JOIN �ʿ� X
SELECT employee_id, first_name
FROM employees
WHERE department_id = (SELECT department_id
                       FROM departments
                       WHERE department_name = 'IT'
                       );


-- �̸��� 'Bruce'�� ����� ���� �μ��� �ٹ��ϴ� ������� ���, �̸��� ����Ͻÿ�.
-- �ĳ�
SELECT employee_id, first_name
FROM employees
WHERE department_id = (SELECT department_id 
                       FROM employees
                       WHERE first_name = 'Bruce'
                       )
      AND first_name <> 'Bruce';
-- ������
SELECT employee_id, first_name
FROM employees
WHERE department_id = (SELECT department_id
                       FROM employees
                       WHERE first_name = 'Bruce'
                       )
      AND first_name <> 'Bruce';
      
-- �̸��� 'Bruce'�� ����� ���� �μ��� �ٹ��ϸ鼭 �μ� ��� �޿����� ���� �޿��� �޴�
-- ������� ���, �̸��� ����Ͻÿ�.
--SELECT employee_id, first_name, salary
--FROM employees
--WHERE department_id = (SELECT department_id 
--                       FROM employees 
--                       WHERE first_name = 'Bruce')
--      AND first_name <> 'Bruce'
--      AND salary > (SELECT AVG(salary)
--                    FROM employees e JOIN departments d ON(e.department_id = d.department_id) 
--                    WHERE department_name = 'IT'
--                    );
-- ���� �߰���!!
SELECT employee_id, first_name
FROM employees
WHERE department_id = (SELECT department_id 
                      FROM employees 
                      WHERE first_name = 'Bruce')
      AND first_name <> 'Bruce'
      AND salary > (SELECT AVG(salary) 
                    FROM employees
                    WHERE department_id = (SELECT department_id 
                                           FROM employees 
                                           WHERE first_name = 'Bruce')
                    );
-- ������
SELECT employee_id, first_name, salary
FROM employees e
WHERE salary > (SELECT AVG(salary) 
                FROM employees
                WHERE department_id = e.department_id
                 )
      AND department_id = (SELECT department_id
                           FROM employees
                           WHERE first_name = 'Bruce');
-- ��ȣ ������ ���� ? ���� �������� ����� ��Ī�� ���� �������� ����ϴ� ��!

-- IT �μ� ����� �޿�
SELECT employee_id, first_name, salary
FROM employees e JOIN departments d ON(e.department_id = d.department_id) 
WHERE department_name = 'IT';

-- IT �μ� ��� �޿�
SELECT AVG(salary)
FROM employees e JOIN departments d ON(e.department_id = d.department_id) 
WHERE department_name = 'IT'

-- ----------------------------------------------------------------------

-- DDL
-- 1. ���̺� ����
CREATE TABLE t_a(one NUMBER(5),
                 two NUMBER(5,2),
                 three CHAR(3),
                 four VARCHAR2(3),
                 five DATE );

-- -----------------------
-- DML
-- 2. ������ �߰�
INSERT INTO t_a(one, three, five) VALUES (1, 'A', SYSDATE);
INSERT INTO t_a VALUES(2, 2.2, 'B', 'B', '23/01/01');
INSERT INTO t_a VALUES(3, null, 'C', '', '23/01/01');

-- CHAR �ڷ���: ���� ����, VARCHAR2 �ڷ���: ���� ����
SELECT three, LENGTH(three), four, LENGTH(four) FROM t_a;

-- -----------------------
-- 3. ���� ���̺� �����ؼ� ���̺� ����
CREATE TABLE t_a_copy AS SELECT * FROM t_a;
SELECT * FROM t_a_copy;

CREATE TABLE t_a_copy2 AS SELECT one, three FROM t_a;
SELECT * FROM t_a_copy2;

-- ������ �����ؼ� ���̺� ����
CREATE TABLE t_a_copy3 AS SELECT * FROM t_a WHERE 1 = 2; 
-- WHERE���� ���ǿ� �������� �ʴ� ���� �ֱ� -> ���̺��� ������ �����ؿ��� ��(������)�� �������� ����!
SELECT * FROM t_a_copy3;

-- -----------------------
-- 4. ���̺� ���� ����
-- 1) ���� ���̺� �÷� �߰��ϱ�
ALTER TABLE t_a ADD six number;
-- �ڷ��� number, �ڸ����� �������� ���� -> number�� ǥ���� �� �ִ� �ִ� �ڸ����� !
-- ADD ����ϸ� ���ο� �÷��� ������ �÷����� �߰���!!

-- 2) �÷� �̸� ����
ALTER TABLE t_a RENAME COLUMN six TO six2;

-- 3) �÷� ����
ALTER TABLE t_a DROP COLUMN six;

-- 4) �÷��� �ڷ��� ���� or �ڸ��� ����
ALTER TABLE t_a MODIFY four VARCHAR2(10);
-- four VARCHAR2(3) -> four VARCHAR2(10)�� �ڸ��� ����
-- ������ �̹� ���� ����ִ� �÷��� �ڷ����� �ٸ� �ڷ������� ������ �� ����!

-- 5) ���̺� ����
DROP TABLE t_a_copy3;

-- 6) ������ ����
SELECT * FROM t_a;

-- t_a ���̺��� two �÷����� null�� �ƴϸ� two �÷����� 1.5�� �����Ѵ�!
UPDATE t_a
SET two = two*1.5 -- UPDATE SET�������� =�� ���Կ����ڷ� ����! �ٸ� �������� �� �񱳿����ڷ� ����!!
WHERE two IS NOT NULL;

-- t_a ���̺��� two �÷����� null�̸� one �÷����� 1 �����ϰ�, five �÷����� 1�� �����Ѵ�!
UPDATE t_a
SET one = one+1, five = five-1
WHERE two IS NULL;

-- 7) ������ ����
-- t_a ���̺��� one �÷����� 4�� ���� �����Ѵ�.
DELETE t_a
WHERE one = 4;

-- t_a ���̺��� five �÷����� ������¥(23/08/17)�� ���� �����Ѵ�.
DELETE t_a
WHERE TO_CHAR(five, 'yy/mm/dd') = '23/08/17';
-- ���ڷ� �����ϰ� �����ؾ� ��!! 
-- SYSDATE�� �ú��� ������ �ֱ� ������, �ʿ��� ������ �����ͼ� �� �ؾ���!
DELETE t_a
WHERE TO_CHAR(five, 'yy/mm/dd') = TO_CHAR(SYSDATE-1, 'yy/mm/dd'); -- SYSDATE ��������!

-- ----------------------------------------------------------------------

-- ���Ἲ ���� ����: ���� ����(Data Integrity) �����͸� ���� ���� ������ ������ �� �ֵ�!
-- ���̺� ���� �� Ȥ�� ���̺� ���� ������ �� ���� ������ ������!

-- 1. UNIQUE: �ߺ� �ȵ�
-- 2. NOT NULL: NULL ���� �ȵ�
-- 3. PRIMARY KEY: UNIQUE + NOT NULL
-- 4. CHECK: Ư������ ����
-- 5. FOREGIN KEY: �θ�ƼƼ�� PK�� ����

-- -----------------------
--id1, p1, n1, id1@a.com, 1
--id2, p2, n2, id2@a.com, 1
--id3, p3, '', id3@a.com, 1
--id4, p4, n2, '', -1
-- -> �ĺ� Ű�� �߿��� �ߺ����� �ʰ� null�� �ƴ� ���� id �÷��� �ĺ��ڷ� ����ؾ� ��! -> PK(�ֽĺ���:�⺻Ű)

CREATE TABLE t_b(
    id varchar2(5), -- ���̵�_PRIMARY KEY
    pwd varchar2(5), -- ���_NOT NULL
    name varchar2(30), -- �̸�_�������Ǿ���
    email varchar2(30), -- �̸���_UNIQUE
    status number(1) -- ����(���̵� Ȱ�� ������, �޸� �������� ��)_CHECK
                     -- -1: Ż��, 0: �޸�, 1: Ȱ��
);

-- ���� ���� ���� ��� (���̺� ���� ���� ���� ����� ���� - ������ ����)
-- !! NOT NULL ���� ������ �ݵ��!! �÷� �����θ� ���� ���� ������ ������!!

-- 1) ���̺� ���� ���� ���� �����ϱ�
-- ���̺� ���� ����, ���� ���� ���Ǹ� ���� ����!
CREATE TABLE t_b(
    id varchar2(5),
    pwd varchar2(5),
    email varchar2(30),
    status number(1),
    CONSTRAINT t_b_id_pk     PRIMARY KEY(id),
    CONSTRAINT t_b_email_uq  UNIQUE(email),
    CONSTRAINT t_b_status_ck CHECK(status IN (-1, 0, 1))
);

-- 2) �÷� ���� ���� ���� �����ϱ�
-- �÷� ����鼭 ���� ���ǵ� ���� �߰��ϱ�!
-- ���� ������ �̸��� ���� ������! ���Ǹ� �����ص� OK
CREATE TABLE t_b(
    id varchar2(5) CONSTRAINT t_b_id_pk         PRIMARY KEY(id),
    pwd varchar2(5)                             NOT NULL(pwd),
    email varchar2(30) CONSTRAINT t_b_email_uq  UNIQUE(email),
    status number(1) CONSTRAINT t_b_status_ck   CHECK(status IN (-1, 0, 1))
);

-- 3) �̹� ������� �ִ� ���̺�(�����͵� �������)�� ���� ���Ǹ� �߰��ϱ�
-- ALTER ��ɾ� ��� -> ���̺� ���� �������� ���� ������ �߰��ؾ� ��!
-- ���̺� ���� ���� �������� �߰��ϱ�!
CREATE TABLE t_b(
    id varchar2(5),
    pwd varchar2(5),
    email varchar2(30),
    status number(1),
);

-- id ���� ���� �߰�
ALTER TABLE t_b
ADD CONSTRAINT t_b_id_pk PRIMARY KEY(id);
-- email�̶� status ���� ���� �߰�
ALTER TABLE t_b
ADD CONSTRAINT t_b_email_uq UNIQUE(email)
ADD CONSTRAINT t_b_status_ck CHECK(status IN (-1, 0, 1));
-- pwd�� NOT NULL ���� ����(�÷� �����θ� ����)�� �߰� -> ADD CONSTRAINT �Ұ���! MODIFY��!
ALTER TABLE t_b
MODIFY pwd NOT NULL;

select * from t_b;

-- ---------------------------------
COMMIT;