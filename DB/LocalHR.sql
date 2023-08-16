-- �޿��� 5000�̻� 20000������ ����� ���, �̸�, �޿��� ����Ͻÿ�.
SELECT employee_id, first_name, salary
FROM employees
WHERE 5000<=salary AND salary<=20000;

-- �μ� ��ȣ�� 30��, 50���� ����� ���, �̸�, �μ� ��ȣ�� ����Ͻÿ�.
SELECT employee_id, first_name, department_id
FROM employees
WHERE department_id=30 OR department_id=50;

-- �޿��� 5000�̻� 20000�����̰�, �μ� ��ȣ�� 30, 50���� ��� ��, ����� ���, �̸�, �޿�, �μ� ��ȣ�� ��� (6��)
SELECT employee_id, first_name, salary, department_id
FROM employees
WHERE (department_id = 30 OR department_id = 50) AND (5000 <= salary AND salary <= 20000);

-- BETWEEN������: AND ���� ��� ����!
-- �޿��� 5000�̻� 20000������ ����� ���, �̸�, �޿��� ����Ͻÿ�.
SELECT employee_id, first_name, salary
FROM employees
WHERE salary BETWEEN 5000 AND 20000;

-- IN������: OR ���� ��� ����
-- �μ� ��ȣ�� 30��, 50���� ����� ���, �̸�, �μ� ��ȣ�� ����Ͻÿ�.
SELECT employee_id, first_name, department_id
FROM employees
WHERE department_id IN(30, 50);

-- BETWEEN�� IN����!
-- �޿��� 5000�̻� 20000�����̰�, �μ� ��ȣ�� 30, 50���� ��� ��, ����� ���, �̸�, �޿�, �μ� ��ȣ�� ��� (6��)
SELECT employee_id, first_name, salary, department_id
FROM employees
WHERE department_id IN(30, 50) AND salary BETWEEN 5000 AND 20000;

-- LIKE ������: �������� -%,_
-- �Ի� �⵵�� 03�⵵�� ������� ����Ͻÿ�.
-- 2003/01/01 ~ 2003/12/31 ������ �Ի��ڵ��� ����ϴ� SQL ����!
SELECT *
FROM employees
-- WHERE hire_date BETWEEN '03/01/01' AND '03/12/31';
-- ????
WHERE hire_date LIKE '03%';
-- 03* = 0�� �̻��� �����̸� 03���θ� �����ϸ� �ȴٴ� �ǹ�!

-- LIKE ������: �������� -%,_
-- �������� Manager�� ������ �������� ����Ͻÿ�.
-- �ڿ��� Manager�� ������ ��!
SELECT *
FROM jobs
WHERE job_title LIKE '%Manager';

-- LIKE ������: �������� -%,_
-- ������� �� ��° ���ڰ� 'e'�� ������� ����Ͻÿ�.
-- �̸��� �ι�° ���ڴ� ������ e! (ù ���ڶ� �������� ���� �͵� ���� X)
SELECT *
FROM employees
WHERE first_name LIKE '_e%';
