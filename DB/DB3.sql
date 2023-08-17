-- #JOIN#

-- 사번, 이름, 부서번호
SELECT employee_id, first_name, department_id
FROM employees; -- 107명
-- 부서번호, 부서명
SELECT department_id, department_name
FROM departments; -- 27개
-- ? employees 테이블의 정보 = 사번, 이름, 부서번호, departments 테이블의 정보 = 부서명

-- 사원의 사번, 이름, 부서번호, 부서명을 출력하시오. (107 * 27의 결과값이 출력됨 - 사원 하나당 부서 각각을 배치했기 때문)
SELECT employee_id, first_name, department_id,
       department_id, department_name
FROM employees, departments; 

-- #Oracle JOIN법
-- 사원의 사번, 이름, 부서번호, 부서명을 출력하시오. (106건)
SELECT employee_id, first_name, e.department_id,
       d.department_id, department_name
FROM employees e, departments d -- 테이블 이름에 별칭 부여
                                -- -> 이후부터는 처리 순서상 FROM절에서 사용한 별칭만을 사용해야 함
                                -- -> heading에는 별칭 붙어서 나오지 않음!
WHERE e.department_id = d.department_id;

-- #표준화된 ANSI JOIN법
-- 1) JOIN ON(INNER JOIN) -------------
-- 사원의 사번, 이름, 부서번호, 부서명을 출력하시오. (106건)
SELECT employee_id, first_name, e.department_id,
       d.department_id, department_name
FROM employees e JOIN departments d ON (e.department_id = d.department_id);

-- 사원의 사번, 이름, 부서번호, 부서명, 부서가 속한 도시명을 출력하시오.
-- 정보 확인
SELECT department_id FROM employees WHERE employee_id = 100; -- 사원 정보 확인(90)
SELECT department_name, location_id FROM departments WHERE department_name = 'Executive'; -- 부서 정보 확인(Executive, 1700)
SELECT city FROM locations WHERE location_id = 1700; -- 도시 정보 확인(Seattle)

SELECT employee_id, first_name, e.department_id    -- employees 테이블
       , d.department_id, department_name          -- departments 테이블
       , city                                      -- locations 테이블
FROM employees e JOIN departments d ON (e.department_id = d.department_id)
                 JOIN locations l ON (d.location_id = l.location_id);
                 
-- 2) NATURAL JOIN(INNER JOIN) -------------
-- 사원의 사번, 이름, 직무번호, 직무명을 출력하시오.
-- JOIN ON
SELECT employee_id, first_name
       , j.job_id, job_title
FROM employees e JOIN jobs j ON (e.job_id = j.job_id);
-- ? 바꾸기
-- NATURAL JOIN
SELECT employee_id, first_name
       , job_id, job_title
FROM employees NATURAL JOIN jobs;

-- 사원의 사번, 이름, 부서번호, 부서명을 출력하시오. (32건)
SELECT employee_id, first_name,
       department_id, department_name
FROM employees NATURAL JOIN departments;
-- EMPLOYEES 테이블의 컬럼 2개(MANAGER_ID-사원을 관리하는 관리자번호, DEPARTMENT_ID)와 DEPARTMENTS 테이블의 컬럼 2개(MANAGER_ID-부서장, DEPARTMENT_ID-부서장)가
-- 겹치기 때문에 결과가 이상하게 나옴
-- 32건의 결과 = 나의 바로 직속 상사가 부서장인 사원들만 추출한 것임
-- 해당 문제는 NATURAL JOIN을 사용해서는 안됨!

-- 3) JOIN USING(INNER JOIN) -------------
-- 사원의 사번, 이름, 부서번호, 부서명을 출력하시오. (106건)
SELECT employee_id, first_name,
       department_id, department_name
FROM employees JOIN departments USING (department_id);
-- USING절은 ON절을 사용하면서, NATURAL의 문제에 대한 절충안임!

-- 4) OUTER JOIN -------------
-- 사원의 사번, 이름, 부서번호, 부서명을 출력하시오. (107건)
-- 단, 부서 없는 사원도 출력하시오.
SELECT employee_id, first_name,
       d.department_id, department_name -- 스키마명.컬럼이름
FROM employees e LEFT JOIN departments d ON (e.department_id = d.department_id);
-- LEFT OUTER JOIN임! OUTER 생략
-- employees 테이블을 기준으로 두었기 때문에 LEFT! departments 테이블 기준으로 하고 싶다면 RIGHT!

-- # 오라클 전용 OUTER JOIN
-- 사원의 사번, 이름, 부서번호, 부서명을 출력하시오. (107건)
-- 단, 부서 없는 사원도 출력하시오.
SELECT employee_id, first_name,
       d.department_id, department_name -- 스키마명.컬럼이름
FROM employees e, departments d
WHERE e.department_id = d.department_id(+); -- 부족한 쪽(기준이 되지 않는 쪽)에 더하기를 함

-- 사원의 사번, 이름, 부서번호, 부서명을 출력하시오. (122건)
-- 단, 사원이 없는 부서도 출력하시오.
SELECT employee_id, first_name,
       d.department_id, department_name
FROM employees e RIGHT JOIN departments d ON (e.department_id = d.department_id);

-- 사원의 사번, 이름, 부서번호, 부서명을 출력하시오. (123건)
-- 단, 부서 없는 사원과 사원이 없는 부서도 출력하시오.
SELECT employee_id, first_name,
       d.department_id, department_name
FROM employees e FULL JOIN departments d ON (e.department_id = d.department_id);

-- 5) SELF JOIN -------------
-- 사원의 사번, 이름, 해당 사원을 관리하는 관리자 번호, 관리자 이름을 출력하시오. -- 106건
SELECT e.employee_id "사번", e.first_name "이름"
      ,m.manager_id "관리자번호", m.first_name "관리자이름" -- m.employee_id 혹은 m.manager_id 사용하면 댐
FROM employees e JOIN employees m ON (e.manager_id = m.employee_id);

-- 사원의 사번, 이름, 해당 사원을 관리하는 관리자 번호, 관리자 이름을 출력하시오. -- 107건
-- 단, 관리자가 없는 사원도 출력하시오.
SELECT e.employee_id "사번", e.first_name "이름"
      ,m.manager_id "관리자번호", m.first_name "관리자이름"
FROM employees e LEFT JOIN employees m ON (e.manager_id = m.employee_id);

-- ---------------------------------------------------------
-- #UNION#

-- UNION: 여러 테이블의 행들을 결합함!

-- 합집합 : UNION, UNION ALL
-- 교집합 : INTERSECT
-- 차집합 : MINUS

-- 집합연산자
-- A. 사원의 사번, 이전 직무번호를 출력하시오
-- B. 사원의 사번, 직무번호를 출력하시오
SELECT employee_id, job_id
FROM job_history -- A
UNION
SELECT employee_id, job_id
FROM employees; -- B
-- A 먼저 추출 이후 B 추출

SELECT employee_id, job_id
FROM job_history -- A
UNION ALL
SELECT employee_id, job_id
FROM employees; -- B

-- 이전 직무와 다른 직무를 가진 사원들의 사번, 현재 직무를 출력하시오.
SELECT employee_id, job_id
FROM employees -- 현재
MINUS
SELECT employee_id, job_id
FROM job_history; -- 이전

-- 이전 직무와 같은 직무를 가진 사원들의 사번, 현재 직무를 출력하시오.
SELECT employee_id, job_id
FROM employees -- 현재
INTERSECT
SELECT employee_id, job_id
FROM job_history; -- 이전

-- ---------------------------------------------------------
-- #Sub Query#

-- 최대 급여자의 사번, 이름, 급여를 출력하시오.
-- 1) 최대 급여를 계산한다.
SELECT MAX(salary)
FROM employees;
-- 2) 1)과 같은 급여를 받는 사원의 사번, 이름, 급여를 출력하시오.
SELECT employee_id, first_name, salary
FROM employees
WHERE salary = (SELECT MAX(salary) 
                FROM employees
);

-- 사원들의 평균 급여보다 많은 급여를 받는 급여자의 사번, 이름, 급여를 출력하시오.
-- 1) 평균 급여를 계산
SELECT AVG(salary)
FROM employees;
-- 2) 1)보다 많은 급여를 받는 사원의 사번, 이름, 급여를 출력하시오.
SELECT employee_id, first_name, salary
FROM employees
WHERE salary > (SELECT AVG(salary)
                 FROM employees
);

-- 부서별 최대 급여를 받는 사원의 사번, 이름, 급여를 출력하시오.
-- 1. 부서별 최대 급여를 계산한다
SELECT department_id, MAX(salary) -- 최대 급여를 받는 사원의 사번
       -- , employee_id, first_name -- 얘네 때문에 오류
FROM employees
GROUP BY department_id; -- 부서별
-- 2. 1)과 같은 급여를 받는 사원의 사번, 이름, 급여를 출력하시오.
SELECT department_id, employee_id, first_name, salary
FROM employees
-- SELECT 내부에 또 SELECT -> SubQuery
-- WHERE salary = 1번 절차!! ?
WHERE salary = (SELECT department_id, MAX(salary)
                FROM employees
                GROUP BY department_id
);
-- ORA-00913: too many values

-- #Multi Row SubQuery: IN은 =ANY 와 동일함!
-- IN 사용
-- 부서별 최대 급여를 받는 사원의 사번, 이름, 급여를 출력하시오.
SELECT department_id, employee_id, first_name, salary
FROM employees
WHERE salary IN (SELECT department_id, MAX(salary)
                FROM employees
                GROUP BY department_id
);
-- ORA-00913: too many values -> 서브 쿼리가 반환해주는 컬럼의 갯수가 2개임
--                            ->  메인 쿼리에서의 비교 대상이 될 컬럼의 갯수도 맞춰줘야 함!
SELECT department_id, employee_id, first_name, salary
FROM employees
WHERE (department_id, salary) IN (SELECT department_id, MAX(salary)
                FROM employees
                GROUP BY department_id
);

-- 관리자 번호와 관리자 이름을 출력하시오.
-- 부하직원이 있는 관리자의 관리자 번호와 이름을 출력하시오.
SELECT employee_id, first_name
FROM employees
WHERE employee_id IN (SELECT manager_id -- SubQuery 결과 107건
                      FROM employees
);
--SELECT employee_id, first_name
--FROM employees
--WHERE employee_id IN (null,100,102,103,101,108...);

-- 부하직원이 없는 관리자의 관리자 번호와 이름을 출력하시오.
SELECT employee_id, first_name
FROM employees
WHERE employee_id NOT IN (SELECT manager_id
                          FROM employees
);
--SELECT employee_id, first_name
--FROM employees
--WHERE employee_id NOT IN (null,100,102,103,101,108...);
-- ??????
-- NOT IN에서 null 해결 방안
-- 1) NVL() 사용
SELECT employee_id, first_name
FROM employees
WHERE employee_id NOT IN (SELECT NVL(manager_id, -1)
                          FROM employees
);
-- 2)WHERE절 조건 추가해서 IS NOT NULL 사용
SELECT employee_id, first_name
FROM employees
WHERE employee_id NOT IN (SELECT manager_id
                          FROM employees
                          WHERE manager_id IS NOT NULL
);



-- ---------------------------------------------------------

-- 1.부서의 부서번호, 부서가 속한 지역의 도시명(city), 국가명(country_name)을 출력하시오.
SELECT d.department_id, department_name, city, country_name         
FROM departments d JOIN locations l ON (d.location_id = l.location_id)
                   JOIN countries c ON (l.country_id = c.country_id);

-- 2. 사원의 사번, 부서번호, 부서명, 직무번호, 직무명을 출력하시오.
-- 직무명에 'Manager'를 포함한 사원들만 출력하시오.
-- 직무번호순, 부서명으로 오름차순하시오.
SELECT e.employee_id, d.department_id, d.department_name, j.job_id, j.job_title
FROM employees e LEFT JOIN departments d ON(e.department_id = d.department_id)
                 LEFT JOIN jobs j ON(e.job_id = j.job_id)
WHERE job_title LIKE '%Manager%'
ORDER BY job_id, department_name;
-- 교수님이 LEFT 안써도 된다고 하셨다 -ㅇ- b
SELECT e.employee_id, d.department_id, d.department_name, j.job_id, j.job_title
FROM employees e JOIN departments d ON(e.department_id = d.department_id)
                 JOIN jobs j ON(e.job_id = j.job_id)
WHERE job_title LIKE '%Manager%'
ORDER BY job_id, department_name;

-- 3. 부서별 부서번호, 부서명, 사원수, 평균급여를 출력하시오.
SELECT d.department_id, d.department_name, COUNT(*), ROUND(AVG(salary))
FROM employees e JOIN departments d ON(e.department_id = d.department_id)
GROUP BY d.department_id, department_name;

-- 4. 부서별 사원수가 10명이상인 부서들의 부서별 부서번호, 부서명, 사원수, 평균급여를 출력하시오.
SELECT d.department_id, d.department_name, COUNT(*), ROUND(AVG(salary))
FROM employees e JOIN departments d ON(e.department_id = d.department_id)
HAVING COUNT(*) >= 10
GROUP BY d.department_id, department_name;

-- 5. 사원의 부서번호와 관리자의 부서번호가 일치하지않는 사원들 사번, 부서번호를 출력하시오 .
-- 사번순으로 오름차순 정렬하시오.
SELECT e.employee_id, e.department_id
FROM employees e LEFT JOIN employees m ON (e.manager_id = m.employee_id)
WHERE e.department_id <> m.department_id
ORDER BY employee_id;

-- 6. 각 도시에 있는 부서수를 출력하시오.
SELECT l.city, count(d.department_id)
FROM departments d JOIN locations l ON(d.location_id = l.location_id)
GROUP BY l.city;

-- 7.각 도시에 있는 부서수를 출력하시오. 단, 부서가 없는 도시도 모두 출력하시오.
SELECT l.city, count(d.department_id)
FROM departments d RIGHT JOIN locations l ON(d.location_id = l.location_id)
GROUP BY l.city;
