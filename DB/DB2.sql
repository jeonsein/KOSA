-- ����Ŭ ���� �Լ�

-- A) ������ �Լ�
-- 1. ������ �Լ�: MOD() ������ �� , ROUND() �ݿø�, TRUNC() �Ҽ��� ���ϸ� ����, CEIL() �Ҽ��� �ø� �Լ�, FLOOR() �Ҽ��� �Ʒ� ���� �Լ�

-- #1. ROUND(), �ݿø�
SELECT ROUND(1234.567) -- 1235 => ROUND(1234.567, 0)
      ,ROUND(1234.567, 1) -- �Ҽ��� ���� �� �ڸ����� �ݿø�! -> ��° �ڸ����� �ݿø� -- 1234.6
      ,ROUND(1234.567, -1) -- ���� �ڸ����� �ݿø�! -- 1230
FROM dual; -- test�� ���̺� dual

-- #2. TRUNC, ������
SELECT TRUNC(1234.567) -- 1234
      ,TRUNC(1234.567, 0) -- 1234
      ,TRUNC(1234.567, 1) -- 1234.5
      ,TRUNC(1234.567, -1) -- 1230
FROM dual;

-- #3. CEIL() & FLOOR(), �ø�&����
SELECT CEIL(1234.1) -- 1235
      ,FLOOR(1234.1) -- 1234
FROM dual;

-- ����� ���, �޿�, �����, �Ǳ޿�, �Ǳ޿�/12(�ǿ���)�� ����Ѵ�. ��, �ǿ����� �Ҽ��� ���� 2�ڸ����� �ݿø��Ѵ�.
-- �Ǳ޿�: �޿� + (�޿�*�����) -- NVL() Ȱ��!
-- �ǿ���: �Ǳ޿�/12
SELECT employee_id "���", salary "�޿�", commission_pct "�����"
			 ,salary + (salary * NVL(commission_pct, 0)) AS "�Ǳ޿�"
             -- ,(salary + (salary * NVL(commission_pct, 0))) / 12 AS "�ǿ���" -- �ݿø� X
             ,ROUND((salary + (salary * NVL(commission_pct, 0))) / 12, 1) AS "�ǿ���" -- �Ҽ��� ���� 2�ڸ����� �ݿø�
FROM employees;

-- -----------------------------

-- 2. ������ �Լ�: LENGTH() ������ ���� �˷��ִ� �Լ�, INSTR() �ڹ��� indexOf()�� ���! ���ڿ����� Ư�� ������ ��ġ�� ��ȯ���ִ� �Լ�
--                ,SUBSTR() ���ڿ� �ڸ��� �Լ�, LPAD() ���ʿ��� ���� ����� & RPAD() �����ʿ��� ���� ����� �Լ�
--                ,TRIM() ���� ���� �Լ� (LTRIM / RTRIM)

-- #1. LENGTH()
SELECT LENGTH('HELLOORACLE') --11
      ,LENGTH('�ȳ��ϼ���') -- 5
      ,LENGTHB('�ȳ��ϼ���') -- LENGTHB = ����Ʈ �� ��ȯ -- 15
FROM dual;

-- #2. INSTR()
SELECT INSTR('HELLOORACLE', 'L') -- L�� ���� ��ġ ��ȯ -- 3
      ,INSTR('HELLOORACLE', 'L', 5) -- 5��° ���ں��� L���ڸ� ã�� ����! -- 10
      ,INSTR('HELLOORACLE', 'H') -- 1
FROM dual;

SELECT INSTR('HELLOORACLE', 'X') -- ���� ���� ã�� -- �ش� ���ڸ� ã�� ���ϸ� 0��ȯ
FROM dual;

-- #3. SUBSTR()
SELECT SUBSTR('HELLOORACLE', 2, 3) -- ELL
FROM dual;

-- #4. LPAD() & RPAD()
SELECT LPAD('HELLO', 8, '*') -- ũ�� 8, ���� ������ ���ʺ��� *�� ä��� -- ***HELLO
      ,RPAD('SENGNA', 8, '*')
FROM dual;

-- #5. LTRIM() & RTRIM()
-- BEGIN, END ���ڿ� �̾��� -> BEGIN   HELLO   END
SELECT 'BEGIN' || LTRIM('   HELLO   ') || 'END' -- BEGINHELLO   END
      ,'BEGIN' || RTRIM('   HELLO   ') || 'END' -- BEGIN   HELLOEND
      ,'BEGIN' || TRIM('   HELLO   ') || 'END' -- BEGINHELLOEND -- ����+������ ���� �� ����
FROM dual;

-- ����� �̸� �� �� ��° ���ڰ� ��ҹ��� ���� ���� 'e'�� ������� ���, �̸��� ����Ͻÿ�.
SELECT employee_id AS ���, first_name AS �̸�
FROM employees
WHERE INSTR(UPPER(first_name), 'E') = 2;

-- -----------------------------

-- 3. ��¥�� �Լ�: SYSDATE(), MONTHS_BETWEEN() ���ڿ� ���� ������ �������� ��ȯ
-- ��¥�� �����ʹ� +, - ������ ������!
-- ��¥ + ���� -> ��¥���� ��ȯ
-- ��¥ - ���� -> ��¥���� ��ȯ
-- ��¥ - ��¥ -> ���ڰ��� ��ȯ

-- #1 SYSDATE()
SELECT SYSDATE
FROM dual;

-- #2 MONTHS_BETWEEN()
SELECT MONTHS_BETWEEN(SYSDATE, '23/12/16') -- 12�� 16�ϱ����� ���� ���� �� ��� -- 4
      ,MONTHS_BETWEEN(SYSDATE, '23/01/01') -- 1�� 1�Ϻ��� 8�� 16�ϱ����� �ð� ���
FROM dual;

-- #3. ADD_MONTHS()      
SELECT ADD_MONTHS(SYSDATE, 1) "1���� �� ��¥" -- ���� ���� �Ѵ� �� -- 23/09/16
      ,ADD_MONTHS(SYSDATE, -6) "6���� �� ��¥" -- ���� ���� 6���� �� -- 23/02/16
FROM dual;

-- #4. LAST_DAY()      
SELECT LAST_DAY('23/02/16') "2�� ������ ����" -- ���� 2���� ������ ���� -- 23/02/28
FROM dual;

-- #5. NEXT_DAY()
SELECT NEXT_DAY(SYSDATE, '�Ͽ���') "�Ͽ����� �ش� ��¥" -- ���ڱ��� �ٰ��� �Ͽ��Ͽ� �ش糯¥
FROM dual;

-- #6. ��¥�� ������ ����
SELECT SYSDATE
      ,SYSDATE + 1 "���� ��¥"
      ,SYSDATE - 1 "���� ��¥"
--      ,SYSDATE - '23/01/01' -- error: The specified number was invalid. 
      -- ��¥ Ÿ���� �ƴ�, ���� Ÿ������ �����߱� ������ �߻��� ���� ?
      -- (MONTH_BETWEEN������ '23/01/01'�� ���� Ÿ�Կ��� ��¥ Ÿ������ ��ȯ�ؼ� ����߱� ������ ���� X)
      -- �������� ��¥������ ��ȯ�ϴ� �۾� �߰��ؼ� ���� �����ϱ�! ?
FROM dual;

-- -----------------------------

-- 4. ����ȯ �Լ�
-- �ڵ� ����ȯ: ������ <-> ������ <-> ��¥��
-- ������ <-> ������
SELECT * FROM employees WHERE department_id = 30; -- OK
SELECT * FROM employees WHERE department_id = '30'; -- �������� ���������� �ڵ� ����ȯ��! -- OK
SELECT * FROM employees WHERE department_id = '030'; -- �������� ���������� �ڵ� ����ȯ��! -- OK
-- ������ <-> ��¥��
SELECT * FROM employees WHERE hire_date = '03/05/18'; -- �������� ��¥������ �ڵ� ����ȯ��! -- OK

-- ���� ����ȯ: TO_DATE(), TO_CHAR(), TO_NUMBER()
-- #1. TO_DATE(): ������ -> ��¥������
SELECT SYSDATE
      ,SYSDATE - TO_DATE('23/01/01') "�ϼ�(�Ҽ��� ����)"
      ,TRUNC(SYSDATE - TO_DATE('23/01/01')) "�ϼ�(�Ҽ��� ����)"
FROM dual;
SELECT TO_DATE('23/12/22') -- 23/12/22
      ,TO_DATE('23-12-22') -- 23/12/22 -> '/' default
      ,TO_DATE('12-22-23 09:10:35','mm-dd-yy HH24:mi:ss') -- ��/��/������ �ٲٰ� �ð� �߰� -- HH:24 = 24�ð� ����, HH = 12�ð� ����
FROM dual;

-- #2. TO_CHAR(): 1) ��¥�� -> ����������, 2) ������ -> ����������
-- 1) ��¥�� -> ����������
SELECT TO_CHAR(SYSDATE) -- 23/08/16
      ,TO_CHAR(SYSDATE, 'yy/mm/dd HH24:mi:ss') -- �ð����� ���� -- 23/08/16 11:44:30
      ,TO_CHAR(SYSDATE, 'yy/mm/dd HH24:mi:ss day') -- ���ϱ��� ���� -- 23/08/16 11:44:30 ������
FROM dual;
-- 2) ������ -> ����������
SELECT TO_CHAR(1234.5, '9,999.9') -- '1,234.5'
      ,TO_CHAR(1234567.8, '9,999.9') -- ũ�Ⱑ ������ ���ڰ� �� ǥ������ ���ϰ� #���� ǥ���
      -- ���� �ڸ������� ���� ���� �ڸ����� ��� #���� ǥ���!!
      ,TO_CHAR(1234567.8, '9,999,999.9')
FROM dual;
-- 9���� vs 0����(�ڸ��� ����)
SELECT TO_CHAR(1234.5, '9,999,999.9') -- 1234.5
      ,TO_CHAR(1234.5, '0,000,000.0') -- 0,001,234.5
      -- ������ �ڸ����� �����
      ,TO_CHAR(1234.5, '0,000,000.00') -- 0,001,234.50
FROM dual;
-- ��ȭ��ȣ
SELECT TO_CHAR(1234.5, 'L9,999,999.00') -- '(��)1,234.50'
FROM dual;

-- #3. TO_NUMBER(): ������ -> ����������
-- 9����
SELECT TO_NUMBER('1,234.5', '9,999.9') -- 1234.5
      ,TO_NUMBER('1,234,567.8', '9,999,999,999.9') -- ���� ���ڿ� ������ �� ū �ڸ����� ������!  -- 1234567.8
      -- ? ���� �ڸ������� ���� ���� �ڸ����� �ʿ���!
FROM dual;

-- ����� ���, �Ի���, ���ñ����� �ٹ��ϼ�, 22�� 12�� 31�ϱ����� �ٹ��ϼ��� ����Ͻÿ�.
-- �ٹ��ϼ�: ���� ��¥ - �Ի���
SELECT employee_id "���", hire_date "�Ի���"
      ,TRUNC(SYSDATE - hire_date) "���ñ����� �ٹ��ϼ�" -- (��¥ - ��¥)
      ,TRUNC(TO_DATE('22/12/31') - hire_date) "22/12/31������ �ٹ��ϼ�"
FROM employees;

-- 08�� �Ի��ڵ��� ���, �Ի�����, �Ի������ ����Ͻÿ�.
SELECT employee_id "���", hire_date "�Ի���", TO_CHAR(hire_date, 'day') "�Ի����"
FROM employees
WHERE TO_CHAR(hire_date, 'mm') = 08;

-- -----------------------------

-- 5. NULL ���� �Լ�: NVL(,), NVL2(,,), NULLIF(,)

-- NVL2(,,)
SELECT NVL2(commission_pct, '��������', '�������')
FROM employees;

-- NULLIF(,)
SELECT NULLIF(10, 10) -- null
      ,NULLIF('hello', 'hi') -- hello
FROM dual;

-- NULL ���� �� ������: IS NULL, IS NOT NULL
SELECT employee_id, department_id
FROM employees
WHERE department_id = NULL; -- ����� 0��, ������ ����� ����! NULL�� �� �����ڷ� =�� ����ؼ��� �ȵ�!
-- #1. IS NULL
SELECT employee_id, department_id
FROM employees
WHERE department_id IS NULL; -- �μ� ��ġ�� ���� ���� ��� -> 1��

-- #2. IS NOT NULL
-- <> ������� �ʱ�!
SELECT employee_id, department_id
FROM employees
WHERE department_id <> NULL;

-- IS NOT NULL
SELECT employee_id, department_id
FROM employees
WHERE department_id IS NOT NULL; -- 106��

-- -----------------------------

-- 6. �Ϲ� �Լ�: DECODE(), CASE��

-- #1. DECODE()
SELECT DECODE(commission_pct, null, 0, commission_pct)
      ,NVL(commission_pct, 0) -- �� DECODE() �Լ� ���� ���� �����
FROM employees;

SELECT DECODE(commission_pct, null, '��������', '�������')
      ,NVL2(commission_pct, '�������', '��������') -- �� DECODE() �Լ� ���� ���� �����
FROM employees;

-- ���� ������ '�������'�� ����ϰ�, ������ 0.1~0.5�� ��� 'B���' �� ���� ��쿡�� 'A���'
SELECT employee_id, commission_pct
      ,DECODE(commission_pct, null, '�������', 0.1, 'B���', 'A���')
FROM employees;

-- #2. CASE��
-- #2-1
-- CASE�÷� WHEN ��1 THEN ���
--          WHEN ��2 THEN
--		    ELSE -- ��1�� �ƴϰ� ��2�� �ƴ� ���� ��µ�
-- END

-- #2-2
-- CASE WHEN ���ǽ� THEN
--		WHEN ���ǽ� THEN
--		ELSE
-- END

-- ������ 0.1~0.5�� ��� 'B���' �� ���� ��쿡�� 'A���'
SELECT employee_id, commission_pct
      ,CASE commission_pct WHEN 0.1 THEN 'B���'
                           ELSE 'A���'
      END
FROM employees;


-- ���� ������ '�������'�� ����ϰ�, 
-- ������ 0.1~0.19������ 'F���'
--       0.2~0.29������ 'E���'
--       0.3~0.39������ 'D���'
--       0.4~0.49������ 'C���'
--       0.5~0.59������ 'B���'
--       0.6 �̻��� 'A���'
SELECT employee_id, commission_pct
      ,CASE WHEN commission_pct >= 0.6 THEN 'A���'
            WHEN commission_pct >= 0.5 THEN 'B���'
            WHEN commission_pct >= 0.4 THEN 'C���'
            WHEN commission_pct >= 0.3 THEN 'D���'
            WHEN commission_pct >= 0.2 THEN 'E���'
            WHEN commission_pct >= 0.1 THEN 'F���'
            WHEN commission_pct IS NULL THEN '�������' -- ELSE���� ����
      -- ELSE '�������'
      END
FROM employees;

-- ---------------------------------------------------------

-- B) �������Լ�: COUNT(), SUM(), AVG(), MAX(), MIN()
-- COUNT()
SELECT COUNT(*) "��ü�����" -- 107
      ,COUNT(commission_pct) "����޴»����" -- 35
      ,COUNT(department_id) "�μ���ġ���������" -- 106 (��ġ�������ѻ���� = 1)
FROM employees;

-- SUM()
SELECT SUM(salary) "�ѱ޿�"
FROM employees;

-- AVG()
SELECT AVG(salary) "��ձ޿�"
FROM employees;
-- SUM()�̳� AVG()�� ���� �ڷ����� ����� ������

-- MAX()& MIN()
SELECT MAX(salary) "�ִ�޿�", MIN(salary) "�ּұ޿�"
FROM employees;

-- ---------------------------------------------------------

-- C) �׷�ȭ - GROUP BY: GROUP BY������ ����� �÷��� �����Լ��� �Բ� SELECT������ ����� ������!!!

-- �μ��� �μ���ȣ, ������� ����Ͻÿ�.
SELECT department_id "�μ���ȣ"
       , COUNT(*) "�μ��������"
       , MAX(salary) "�μ����ִ�޿�"
       , AVG(salary) "�μ�����ձ޿�"
FROM employees
GROUP BY department_id; -- MAP �ڷ� ������ ��� (key = �μ���ȣ, value = �μ���ȣ�� �ش��ϴ� ��)

-- �μ��� �μ���ȣ�� �μ��� �ִ�޿�, �ִ�޿��� �̸��� ����Ͻÿ�.
SELECT department_id "�μ���ȣ"
       , MAX(salary) "�μ����ִ�޿�"
       , first_name "�ִ�޿��� �̸�"
FROM employees
GROUP BY department_id;

-- �μ���, �������� 
-- �μ���ȣ, ������ȣ, ������� ����Ͻÿ�.
SELECT department_id "�μ���ȣ", job_id "������", COUNT(*) "�����"
FROM employees
GROUP BY department_id, job_id -- ��׷� department_id, �ұ׷� job_id
-- ORDER BY department_id; -- �÷������� ��������
ORDER BY department_id, COUNT(*); -- �μ���ȣ ���� ����� ���� job_id���� ���� ���

-- �׷캰 �Ұ�, �հ�: ROLLUP()
-- ����� ������ ��� �Ұ�, �հ�: CUBE()
-- �μ���, �������� �μ���ȣ, ������ȣ, ������� ����Ͻÿ�.
SELECT department_id "�μ���ȣ", job_id "������", COUNT(*) "�����"
FROM employees
-- GROUP BY ROLLUP( department_id, job_id)
GROUP BY CUBE( department_id, job_id)
ORDER BY department_id, COUNT(*);


-- 1_�׷� ����: HAVING - �����Լ���밡��
-- 2_�Ϲ� ����: WHERE - �����Լ����Ұ���

-- 30, 50�� �μ��� �μ��� �μ���ȣ, ��ձ޿�, �ִ�޿��� ����Ͻÿ�.
SELECT department_id "�μ���ȣ"
       , AVG(salary) "�μ�����ձ޿�"
       , MAX(salary) "�μ����ִ�޿�"
FROM employees
-- WHERE department_id = 30 OR department_id = 50
WHERE department_id IN(30, 50)
GROUP BY department_id
ORDER BY department_id;

-- ��� �μ��� �μ��� �μ���ȣ, ��ձ޿�, �ִ�޿��� ����Ͻÿ�.
-- ��, �μ��� ���� ������� ������� �ʴ´�.
SELECT department_id "�μ���ȣ"
       , TRUNC(AVG(salary), 0) "�μ�����ձ޿�"
       , TRUNC(MAX(salary), 0) "�μ����ִ�޿�"
FROM employees
WHERE department_id IS NOT NULL
GROUP BY department_id;
-- ���Ʒ� ���� (�׷��� �Ϲ� ������ ���� WHERE�� ����ϴ� ���� ������)
SELECT department_id "�μ���ȣ"
       , TRUNC(AVG(salary), 0) "�μ�����ձ޿�"
       , TRUNC(MAX(salary), 0) "�μ����ִ�޿�"
FROM employees
GROUP BY department_id
HAVING department_id IS NOT NULL;

-- �μ��� �μ���ȣ, ��ձ޿�, �ִ�޿�, �ּұ޿��� ����Ͻÿ�
-- ��ձ޿��� 10000�̻��� �μ��� ����Ͻÿ�
SELECT department_id "�μ���ȣ"
       , AVG(salary) "�μ�����ձ޿�"
       , MAX(salary) "�μ����ִ�޿�"
       , MIN(salary) "�μ����ּұ޿�"
FROM employees
GROUP BY department_id
HAVING AVG(salary) >= 10000;

-- ---------------------------------------------------------

-- D) �����ϱ�
-- SELECT ó�� ����: FROM ��? WHERE ��? GROUP BY ��? HAVING ��? SELECT ��? ORDER BY

-- ��� ������ ��µǴ� ��ó�� ��������, �Է� ������ ��µǰ� ����
SELECT employee_id, hire_date, salary 
FROM employees;

-- �Ի� ���ڰ� ���� ������� ����Ͻÿ�.
SELECT employee_id, hire_date, salary 
FROM employees
-- ORDER BY hire_date -- default = ��������
ORDER BY hire_date ASC; -- ASC = �������� -- 102���� ���� ���� �Ի���

-- �޿��� ���� ������� ����Ͻÿ�.
SELECT employee_id, hire_date, salary 
FROM employees
ORDER BY salary DESC; -- DESC = ��������

-- �Ի� ���ڰ� ���� ������� ����Ͻÿ�. �Ի� ���ڰ� ���� ���, �޿��� ���� ������� ����Ͻÿ�.
SELECT employee_id, hire_date, salary 
FROM employees
ORDER BY hire_date ASC, salary DESC; -- 2�� ����