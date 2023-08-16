-- 급여가 5000이상 20000이하인 사원의 사번, 이름, 급여를 출력하시오.
SELECT employee_id, first_name, salary
FROM employees
WHERE 5000<=salary AND salary<=20000;

-- 부서 번호가 30번, 50번인 사원의 사번, 이름, 부서 번호를 출력하시오.
SELECT employee_id, first_name, department_id
FROM employees
WHERE department_id=30 OR department_id=50;

-- 급여가 5000이상 20000이하이고, 부서 번호가 30, 50번인 사원 중, 사원의 사번, 이름, 급여, 부서 번호를 출력 (6건)
SELECT employee_id, first_name, salary, department_id
FROM employees
WHERE (department_id = 30 OR department_id = 50) AND (5000 <= salary AND salary <= 20000);

-- BETWEEN연산자: AND 연산 축약 가능!
-- 급여가 5000이상 20000이하인 사원의 사번, 이름, 급여를 출력하시오.
SELECT employee_id, first_name, salary
FROM employees
WHERE salary BETWEEN 5000 AND 20000;

-- IN연산자: OR 연산 축약 가능
-- 부서 번호가 30번, 50번인 사원의 사번, 이름, 부서 번호를 출력하시오.
SELECT employee_id, first_name, department_id
FROM employees
WHERE department_id IN(30, 50);

-- BETWEEN과 IN연산!
-- 급여가 5000이상 20000이하이고, 부서 번호가 30, 50번인 사원 중, 사원의 사번, 이름, 급여, 부서 번호를 출력 (6건)
SELECT employee_id, first_name, salary, department_id
FROM employees
WHERE department_id IN(30, 50) AND salary BETWEEN 5000 AND 20000;

-- LIKE 연산자: 패턴종류 -%,_
-- 입사 년도가 03년도인 사원들을 출력하시오.
-- 2003/01/01 ~ 2003/12/31 사이의 입사자들을 출력하는 SQL 구문!
SELECT *
FROM employees
-- WHERE hire_date BETWEEN '03/01/01' AND '03/12/31';
-- ????
WHERE hire_date LIKE '03%';
-- 03* = 0개 이상의 문자이며 03으로만 시작하면 된다는 의미!

-- LIKE 연산자: 패턴종류 -%,_
-- 직무명이 Manager로 끝나는 직무들을 출력하시오.
-- 뒤에는 Manager로 끝나야 함!
SELECT *
FROM jobs
WHERE job_title LIKE '%Manager';

-- LIKE 연산자: 패턴종류 -%,_
-- 사원명의 두 번째 글자가 'e'인 사원들을 출력하시오.
-- 이름의 두번째 글자는 무조건 e! (첫 글자랑 나머지는 뭐가 와도 관계 X)
SELECT *
FROM employees
WHERE first_name LIKE '_e%';
