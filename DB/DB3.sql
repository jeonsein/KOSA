-- #JOIN#

-- ���, �̸�, �μ���ȣ
SELECT employee_id, first_name, department_id
FROM employees; -- 107��
-- �μ���ȣ, �μ���
SELECT department_id, department_name
FROM departments; -- 27��
-- ? employees ���̺��� ���� = ���, �̸�, �μ���ȣ, departments ���̺��� ���� = �μ���

-- ����� ���, �̸�, �μ���ȣ, �μ����� ����Ͻÿ�. (107 * 27�� ������� ��µ� - ��� �ϳ��� �μ� ������ ��ġ�߱� ����)
SELECT employee_id, first_name, department_id,
       department_id, department_name
FROM employees, departments; 

-- #Oracle JOIN��
-- ����� ���, �̸�, �μ���ȣ, �μ����� ����Ͻÿ�. (106��)
SELECT employee_id, first_name, e.department_id,
       d.department_id, department_name
FROM employees e, departments d -- ���̺� �̸��� ��Ī �ο�
                                -- -> ���ĺ��ʹ� ó�� ������ FROM������ ����� ��Ī���� ����ؾ� ��
                                -- -> heading���� ��Ī �پ ������ ����!
WHERE e.department_id = d.department_id;

-- #ǥ��ȭ�� ANSI JOIN��
-- 1) JOIN ON(INNER JOIN) -------------
-- ����� ���, �̸�, �μ���ȣ, �μ����� ����Ͻÿ�. (106��)
SELECT employee_id, first_name, e.department_id,
       d.department_id, department_name
FROM employees e JOIN departments d ON (e.department_id = d.department_id);

-- ����� ���, �̸�, �μ���ȣ, �μ���, �μ��� ���� ���ø��� ����Ͻÿ�.
-- ���� Ȯ��
SELECT department_id FROM employees WHERE employee_id = 100; -- ��� ���� Ȯ��(90)
SELECT department_name, location_id FROM departments WHERE department_name = 'Executive'; -- �μ� ���� Ȯ��(Executive, 1700)
SELECT city FROM locations WHERE location_id = 1700; -- ���� ���� Ȯ��(Seattle)

SELECT employee_id, first_name, e.department_id    -- employees ���̺�
       , d.department_id, department_name          -- departments ���̺�
       , city                                      -- locations ���̺�
FROM employees e JOIN departments d ON (e.department_id = d.department_id)
                 JOIN locations l ON (d.location_id = l.location_id);
                 
-- 2) NATURAL JOIN(INNER JOIN) -------------
-- ����� ���, �̸�, ������ȣ, �������� ����Ͻÿ�.
-- JOIN ON
SELECT employee_id, first_name
       , j.job_id, job_title
FROM employees e JOIN jobs j ON (e.job_id = j.job_id);
-- ? �ٲٱ�
-- NATURAL JOIN
SELECT employee_id, first_name
       , job_id, job_title
FROM employees NATURAL JOIN jobs;

-- ����� ���, �̸�, �μ���ȣ, �μ����� ����Ͻÿ�. (32��)
SELECT employee_id, first_name,
       department_id, department_name
FROM employees NATURAL JOIN departments;
-- EMPLOYEES ���̺��� �÷� 2��(MANAGER_ID-����� �����ϴ� �����ڹ�ȣ, DEPARTMENT_ID)�� DEPARTMENTS ���̺��� �÷� 2��(MANAGER_ID-�μ���, DEPARTMENT_ID-�μ���)��
-- ��ġ�� ������ ����� �̻��ϰ� ����
-- 32���� ��� = ���� �ٷ� ���� ��簡 �μ����� ����鸸 ������ ����
-- �ش� ������ NATURAL JOIN�� ����ؼ��� �ȵ�!

-- 3) JOIN USING(INNER JOIN) -------------
-- ����� ���, �̸�, �μ���ȣ, �μ����� ����Ͻÿ�. (106��)
SELECT employee_id, first_name,
       department_id, department_name
FROM employees JOIN departments USING (department_id);
-- USING���� ON���� ����ϸ鼭, NATURAL�� ������ ���� �������!

-- 4) OUTER JOIN -------------
-- ����� ���, �̸�, �μ���ȣ, �μ����� ����Ͻÿ�. (107��)
-- ��, �μ� ���� ����� ����Ͻÿ�.
SELECT employee_id, first_name,
       d.department_id, department_name -- ��Ű����.�÷��̸�
FROM employees e LEFT JOIN departments d ON (e.department_id = d.department_id);
-- LEFT OUTER JOIN��! OUTER ����
-- employees ���̺��� �������� �ξ��� ������ LEFT! departments ���̺� �������� �ϰ� �ʹٸ� RIGHT!

-- # ����Ŭ ���� OUTER JOIN
-- ����� ���, �̸�, �μ���ȣ, �μ����� ����Ͻÿ�. (107��)
-- ��, �μ� ���� ����� ����Ͻÿ�.
SELECT employee_id, first_name,
       d.department_id, department_name -- ��Ű����.�÷��̸�
FROM employees e, departments d
WHERE e.department_id = d.department_id(+); -- ������ ��(������ ���� �ʴ� ��)�� ���ϱ⸦ ��

-- ����� ���, �̸�, �μ���ȣ, �μ����� ����Ͻÿ�. (122��)
-- ��, ����� ���� �μ��� ����Ͻÿ�.
SELECT employee_id, first_name,
       d.department_id, department_name
FROM employees e RIGHT JOIN departments d ON (e.department_id = d.department_id);

-- ����� ���, �̸�, �μ���ȣ, �μ����� ����Ͻÿ�. (123��)
-- ��, �μ� ���� ����� ����� ���� �μ��� ����Ͻÿ�.
SELECT employee_id, first_name,
       d.department_id, department_name
FROM employees e FULL JOIN departments d ON (e.department_id = d.department_id);

-- 5) SELF JOIN -------------
-- ����� ���, �̸�, �ش� ����� �����ϴ� ������ ��ȣ, ������ �̸��� ����Ͻÿ�. -- 106��
SELECT e.employee_id "���", e.first_name "�̸�"
      ,m.manager_id "�����ڹ�ȣ", m.first_name "�������̸�" -- m.employee_id Ȥ�� m.manager_id ����ϸ� ��
FROM employees e JOIN employees m ON (e.manager_id = m.employee_id);

-- ����� ���, �̸�, �ش� ����� �����ϴ� ������ ��ȣ, ������ �̸��� ����Ͻÿ�. -- 107��
-- ��, �����ڰ� ���� ����� ����Ͻÿ�.
SELECT e.employee_id "���", e.first_name "�̸�"
      ,m.manager_id "�����ڹ�ȣ", m.first_name "�������̸�"
FROM employees e LEFT JOIN employees m ON (e.manager_id = m.employee_id);

-- ---------------------------------------------------------
-- #UNION#

-- UNION: ���� ���̺��� ����� ������!

-- ������ : UNION, UNION ALL
-- ������ : INTERSECT
-- ������ : MINUS

-- ���տ�����
-- A. ����� ���, ���� ������ȣ�� ����Ͻÿ�
-- B. ����� ���, ������ȣ�� ����Ͻÿ�
SELECT employee_id, job_id
FROM job_history -- A
UNION
SELECT employee_id, job_id
FROM employees; -- B
-- A ���� ���� ���� B ����

SELECT employee_id, job_id
FROM job_history -- A
UNION ALL
SELECT employee_id, job_id
FROM employees; -- B

-- ���� ������ �ٸ� ������ ���� ������� ���, ���� ������ ����Ͻÿ�.
SELECT employee_id, job_id
FROM employees -- ����
MINUS
SELECT employee_id, job_id
FROM job_history; -- ����

-- ���� ������ ���� ������ ���� ������� ���, ���� ������ ����Ͻÿ�.
SELECT employee_id, job_id
FROM employees -- ����
INTERSECT
SELECT employee_id, job_id
FROM job_history; -- ����

-- ---------------------------------------------------------
-- #Sub Query#

-- �ִ� �޿����� ���, �̸�, �޿��� ����Ͻÿ�.
-- 1) �ִ� �޿��� ����Ѵ�.
SELECT MAX(salary)
FROM employees;
-- 2) 1)�� ���� �޿��� �޴� ����� ���, �̸�, �޿��� ����Ͻÿ�.
SELECT employee_id, first_name, salary
FROM employees
WHERE salary = (SELECT MAX(salary) 
                FROM employees
);

-- ������� ��� �޿����� ���� �޿��� �޴� �޿����� ���, �̸�, �޿��� ����Ͻÿ�.
-- 1) ��� �޿��� ���
SELECT AVG(salary)
FROM employees;
-- 2) 1)���� ���� �޿��� �޴� ����� ���, �̸�, �޿��� ����Ͻÿ�.
SELECT employee_id, first_name, salary
FROM employees
WHERE salary > (SELECT AVG(salary)
                 FROM employees
);

-- �μ��� �ִ� �޿��� �޴� ����� ���, �̸�, �޿��� ����Ͻÿ�.
-- 1. �μ��� �ִ� �޿��� ����Ѵ�
SELECT department_id, MAX(salary) -- �ִ� �޿��� �޴� ����� ���
       -- , employee_id, first_name -- ��� ������ ����
FROM employees
GROUP BY department_id; -- �μ���
-- 2. 1)�� ���� �޿��� �޴� ����� ���, �̸�, �޿��� ����Ͻÿ�.
SELECT department_id, employee_id, first_name, salary
FROM employees
-- SELECT ���ο� �� SELECT -> SubQuery
-- WHERE salary = 1�� ����!! ?
WHERE salary = (SELECT department_id, MAX(salary)
                FROM employees
                GROUP BY department_id
);
-- ORA-00913: too many values

-- #Multi Row SubQuery: IN�� =ANY �� ������!
-- IN ���
-- �μ��� �ִ� �޿��� �޴� ����� ���, �̸�, �޿��� ����Ͻÿ�.
SELECT department_id, employee_id, first_name, salary
FROM employees
WHERE salary IN (SELECT department_id, MAX(salary)
                FROM employees
                GROUP BY department_id
);
-- ORA-00913: too many values -> ���� ������ ��ȯ���ִ� �÷��� ������ 2����
--                            ->  ���� ���������� �� ����� �� �÷��� ������ ������� ��!
SELECT department_id, employee_id, first_name, salary
FROM employees
WHERE (department_id, salary) IN (SELECT department_id, MAX(salary)
                FROM employees
                GROUP BY department_id
);

-- ������ ��ȣ�� ������ �̸��� ����Ͻÿ�.
-- ���������� �ִ� �������� ������ ��ȣ�� �̸��� ����Ͻÿ�.
SELECT employee_id, first_name
FROM employees
WHERE employee_id IN (SELECT manager_id -- SubQuery ��� 107��
                      FROM employees
);
--SELECT employee_id, first_name
--FROM employees
--WHERE employee_id IN (null,100,102,103,101,108...);

-- ���������� ���� �������� ������ ��ȣ�� �̸��� ����Ͻÿ�.
SELECT employee_id, first_name
FROM employees
WHERE employee_id NOT IN (SELECT manager_id
                          FROM employees
);
--SELECT employee_id, first_name
--FROM employees
--WHERE employee_id NOT IN (null,100,102,103,101,108...);
-- ??????
-- NOT IN���� null �ذ� ���
-- 1) NVL() ���
SELECT employee_id, first_name
FROM employees
WHERE employee_id NOT IN (SELECT NVL(manager_id, -1)
                          FROM employees
);
-- 2)WHERE�� ���� �߰��ؼ� IS NOT NULL ���
SELECT employee_id, first_name
FROM employees
WHERE employee_id NOT IN (SELECT manager_id
                          FROM employees
                          WHERE manager_id IS NOT NULL
);



-- ---------------------------------------------------------

-- 1.�μ��� �μ���ȣ, �μ��� ���� ������ ���ø�(city), ������(country_name)�� ����Ͻÿ�.
SELECT d.department_id, department_name, city, country_name         
FROM departments d JOIN locations l ON (d.location_id = l.location_id)
                   JOIN countries c ON (l.country_id = c.country_id);

-- 2. ����� ���, �μ���ȣ, �μ���, ������ȣ, �������� ����Ͻÿ�.
-- ������ 'Manager'�� ������ ����鸸 ����Ͻÿ�.
-- ������ȣ��, �μ������� ���������Ͻÿ�.
SELECT e.employee_id, d.department_id, d.department_name, j.job_id, j.job_title
FROM employees e LEFT JOIN departments d ON(e.department_id = d.department_id)
                 LEFT JOIN jobs j ON(e.job_id = j.job_id)
WHERE job_title LIKE '%Manager%'
ORDER BY job_id, department_name;
-- �������� LEFT �Ƚᵵ �ȴٰ� �ϼ̴� -��- b
SELECT e.employee_id, d.department_id, d.department_name, j.job_id, j.job_title
FROM employees e JOIN departments d ON(e.department_id = d.department_id)
                 JOIN jobs j ON(e.job_id = j.job_id)
WHERE job_title LIKE '%Manager%'
ORDER BY job_id, department_name;

-- 3. �μ��� �μ���ȣ, �μ���, �����, ��ձ޿��� ����Ͻÿ�.
SELECT d.department_id, d.department_name, COUNT(*), ROUND(AVG(salary))
FROM employees e JOIN departments d ON(e.department_id = d.department_id)
GROUP BY d.department_id, department_name;

-- 4. �μ��� ������� 10���̻��� �μ����� �μ��� �μ���ȣ, �μ���, �����, ��ձ޿��� ����Ͻÿ�.
SELECT d.department_id, d.department_name, COUNT(*), ROUND(AVG(salary))
FROM employees e JOIN departments d ON(e.department_id = d.department_id)
HAVING COUNT(*) >= 10
GROUP BY d.department_id, department_name;

-- 5. ����� �μ���ȣ�� �������� �μ���ȣ�� ��ġ�����ʴ� ����� ���, �μ���ȣ�� ����Ͻÿ� .
-- ��������� �������� �����Ͻÿ�.
SELECT e.employee_id, e.department_id
FROM employees e LEFT JOIN employees m ON (e.manager_id = m.employee_id)
WHERE e.department_id <> m.department_id
ORDER BY employee_id;

-- 6. �� ���ÿ� �ִ� �μ����� ����Ͻÿ�.
SELECT l.city, count(d.department_id)
FROM departments d JOIN locations l ON(d.location_id = l.location_id)
GROUP BY l.city;

-- 7.�� ���ÿ� �ִ� �μ����� ����Ͻÿ�. ��, �μ��� ���� ���õ� ��� ����Ͻÿ�.
SELECT l.city, count(d.department_id)
FROM departments d RIGHT JOIN locations l ON(d.location_id = l.location_id)
GROUP BY l.city;
