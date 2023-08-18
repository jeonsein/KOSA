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

-- DML
-- 2. ������ �߰�
INSERT INTO t_a(one, three, five) VALUES (1, 'A', SYSDATE);
INSERT INTO t_a VALUES(2, 2.2, 'B', 'B', '23/01/01');
INSERT INTO t_a VALUES(3, null, 'C', '', '23/01/01');

-- CHAR �ڷ���: ���� ����, VARCHAR2 �ڷ���: ���� ����
SELECT three, LENGTH(three), four, LENGTH(four) FROM t_a;

-- 3. ���� ���̺� �����ؼ� ���̺� ����
CREATE TABLE t_a_copy AS SELECT * FROM t_a;
SELECT * FROM t_a_copy;

CREATE TABLE t_a_copy2 AS SELECT one, three FROM t_a;
SELECT * FROM t_a_copy2;

-- ������ �����ؼ� ���̺� ����
CREATE TABLE t_a_copy3 AS SELECT * FROM t_a WHERE 1 = 2; 
-- WHERE���� ���ǿ� �������� �ʴ� ���� �ֱ� -> ���̺��� ������ �����ؿ��� ��(������)�� �������� ����!
SELECT * FROM t_a_copy3;

-- 4. ���̺� ���� ����
-- 1) ���� ���̺� �÷� �߰��ϱ�
ALTER TABLE t_a ADD six number;
-- �ڷ��� number, �ڸ����� �������� ���� -> number�� ǥ���� �� �ִ� �ִ� �ڸ����� !
-- ADD ����ϸ� ���ο� �÷��� ������ �÷����� �߰���!!

-- 2) �÷� �̸� ����
ALTER TABLE t_a RENAME COLUMN six TO six2;

-- 3) �÷� ����
ALTER TABLE t_a DROP COLUMN six2;

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


