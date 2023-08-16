-- 오라클 내장 함수

-- A) 단일행 함수
-- 1. 숫자형 함수: MOD() 나머지 값 , ROUND() 반올림, TRUNC() 소숫점 이하를 버림, CEIL() 소수점 올림 함수, FLOOR() 소수점 아래 무시 함수

-- #1. ROUND(), 반올림
SELECT ROUND(1234.567) -- 1235 => ROUND(1234.567, 0)
      ,ROUND(1234.567, 1) -- 소수점 이하 한 자리까지 반올림! -> 둘째 자리에서 반올림 -- 1234.6
      ,ROUND(1234.567, -1) -- 일의 자리에서 반올림! -- 1230
FROM dual; -- test용 테이블 dual

-- #2. TRUNC, 버리기
SELECT TRUNC(1234.567) -- 1234
      ,TRUNC(1234.567, 0) -- 1234
      ,TRUNC(1234.567, 1) -- 1234.5
      ,TRUNC(1234.567, -1) -- 1230
FROM dual;

-- #3. CEIL() & FLOOR(), 올림&내림
SELECT CEIL(1234.1) -- 1235
      ,FLOOR(1234.1) -- 1234
FROM dual;

-- 사원의 사번, 급여, 수당률, 실급여, 실급여/12(실월급)을 출력한다. 단, 실월급은 소숫점 이하 2자리에서 반올림한다.
-- 실급여: 급여 + (급여*수당률) -- NVL() 활용!
-- 실월급: 실급여/12
SELECT employee_id "사번", salary "급여", commission_pct "수당률"
			 ,salary + (salary * NVL(commission_pct, 0)) AS "실급여"
             -- ,(salary + (salary * NVL(commission_pct, 0))) / 12 AS "실월급" -- 반올림 X
             ,ROUND((salary + (salary * NVL(commission_pct, 0))) / 12, 1) AS "실월급" -- 소숫점 이하 2자리에서 반올림
FROM employees;

-- -----------------------------

-- 2. 문자형 함수: LENGTH() 문자의 길이 알려주는 함수, INSTR() 자바의 indexOf()와 비슷! 문자열에서 특정 문자의 위치를 반환해주는 함수
--                ,SUBSTR() 문자열 자르기 함수, LPAD() 왼쪽에다 문자 덧대기 & RPAD() 오른쪽에다 문자 덧대기 함수
--                ,TRIM() 공백 제거 함수 (LTRIM / RTRIM)

-- #1. LENGTH()
SELECT LENGTH('HELLOORACLE') --11
      ,LENGTH('안녕하세요') -- 5
      ,LENGTHB('안녕하세요') -- LENGTHB = 바이트 수 반환 -- 15
FROM dual;

-- #2. INSTR()
SELECT INSTR('HELLOORACLE', 'L') -- L의 시작 위치 반환 -- 3
      ,INSTR('HELLOORACLE', 'L', 5) -- 5번째 문자부터 L문자를 찾기 시작! -- 10
      ,INSTR('HELLOORACLE', 'H') -- 1
FROM dual;

SELECT INSTR('HELLOORACLE', 'X') -- 없는 문자 찾기 -- 해당 문자를 찾기 못하면 0반환
FROM dual;

-- #3. SUBSTR()
SELECT SUBSTR('HELLOORACLE', 2, 3) -- ELL
FROM dual;

-- #4. LPAD() & RPAD()
SELECT LPAD('HELLO', 8, '*') -- 크기 8, 남는 공간은 왼쪽부터 *로 채우기 -- ***HELLO
      ,RPAD('SENGNA', 8, '*')
FROM dual;

-- #5. LTRIM() & RTRIM()
-- BEGIN, END 문자열 이어줌 -> BEGIN   HELLO   END
SELECT 'BEGIN' || LTRIM('   HELLO   ') || 'END' -- BEGINHELLO   END
      ,'BEGIN' || RTRIM('   HELLO   ') || 'END' -- BEGIN   HELLOEND
      ,'BEGIN' || TRIM('   HELLO   ') || 'END' -- BEGINHELLOEND -- 왼쪽+오른쪽 공백 다 제거
FROM dual;

-- 사원의 이름 중 두 번째 글자가 대소문자 구분 없이 'e'인 사원들의 사번, 이름을 출력하시오.
SELECT employee_id AS 사번, first_name AS 이름
FROM employees
WHERE INSTR(UPPER(first_name), 'E') = 2;

-- -----------------------------

-- 3. 날짜형 함수: SYSDATE(), MONTHS_BETWEEN() 일자와 일자 사이의 개월수를 반환
-- 날짜형 데이터는 +, - 연산이 가능함!
-- 날짜 + 숫자 -> 날짜값을 반환
-- 날짜 - 숫자 -> 날짜값을 반환
-- 날짜 - 날짜 -> 숫자값을 반환

-- #1 SYSDATE()
SELECT SYSDATE
FROM dual;

-- #2 MONTHS_BETWEEN()
SELECT MONTHS_BETWEEN(SYSDATE, '23/12/16') -- 12월 16일까지의 남은 개월 수 계산 -- 4
      ,MONTHS_BETWEEN(SYSDATE, '23/01/01') -- 1월 1일부터 8월 16일까지의 시간 계산
FROM dual;

-- #3. ADD_MONTHS()      
SELECT ADD_MONTHS(SYSDATE, 1) "1개월 후 날짜" -- 오늘 기준 한달 후 -- 23/09/16
      ,ADD_MONTHS(SYSDATE, -6) "6개월 전 날짜" -- 오늘 기준 6개월 전 -- 23/02/16
FROM dual;

-- #4. LAST_DAY()      
SELECT LAST_DAY('23/02/16') "2월 마지막 일자" -- 올해 2월의 마지막 일자 -- 23/02/28
FROM dual;

-- #5. NEXT_DAY()
SELECT NEXT_DAY(SYSDATE, '일요일') "일요일의 해당 날짜" -- 일자기준 다가올 일요일에 해당날짜
FROM dual;

-- #6. 날짜형 데이터 연산
SELECT SYSDATE
      ,SYSDATE + 1 "내일 날짜"
      ,SYSDATE - 1 "어제 날짜"
--      ,SYSDATE - '23/01/01' -- error: The specified number was invalid. 
      -- 날짜 타입이 아닌, 문자 타입으로 이해했기 때문에 발생한 오류 ?
      -- (MONTH_BETWEEN에서는 '23/01/01'을 문자 타입에서 날짜 타입으로 변환해서 사용했기 때문에 오류 X)
      -- 문자형을 날짜형으로 변환하는 작업 추가해서 오류 수정하기! ?
FROM dual;

-- -----------------------------

-- 4. 형변환 함수
-- 자동 형변환: 숫자형 <-> 문자형 <-> 날짜형
-- 숫자형 <-> 문자형
SELECT * FROM employees WHERE department_id = 30; -- OK
SELECT * FROM employees WHERE department_id = '30'; -- 문자형이 숫자형으로 자동 형변환됨! -- OK
SELECT * FROM employees WHERE department_id = '030'; -- 문자형이 숫자형으로 자동 형변환됨! -- OK
-- 문자형 <-> 날짜형
SELECT * FROM employees WHERE hire_date = '03/05/18'; -- 문자형이 날짜형으로 자동 형변환됨! -- OK

-- 강제 형변환: TO_DATE(), TO_CHAR(), TO_NUMBER()
-- #1. TO_DATE(): 문자형 -> 날짜형으로
SELECT SYSDATE
      ,SYSDATE - TO_DATE('23/01/01') "일수(소수점 포함)"
      ,TRUNC(SYSDATE - TO_DATE('23/01/01')) "일수(소수점 버림)"
FROM dual;
SELECT TO_DATE('23/12/22') -- 23/12/22
      ,TO_DATE('23-12-22') -- 23/12/22 -> '/' default
      ,TO_DATE('12-22-23 09:10:35','mm-dd-yy HH24:mi:ss') -- 월/일/년으로 바꾸고 시간 추가 -- HH:24 = 24시간 기준, HH = 12시간 기준
FROM dual;

-- #2. TO_CHAR(): 1) 날짜형 -> 문자형으로, 2) 숫자형 -> 문자형으로
-- 1) 날짜형 -> 문자형으로
SELECT TO_CHAR(SYSDATE) -- 23/08/16
      ,TO_CHAR(SYSDATE, 'yy/mm/dd HH24:mi:ss') -- 시간까지 보기 -- 23/08/16 11:44:30
      ,TO_CHAR(SYSDATE, 'yy/mm/dd HH24:mi:ss day') -- 요일까지 보기 -- 23/08/16 11:44:30 수요일
FROM dual;
-- 2) 숫자형 -> 문자형으로
SELECT TO_CHAR(1234.5, '9,999.9') -- '1,234.5'
      ,TO_CHAR(1234567.8, '9,999.9') -- 크기가 작으면 숫자가 다 표현되지 못하고 #으로 표기됨
      -- 숫자 자릿수보다 작은 패턴 자릿수인 경우 #으로 표기됨!!
      ,TO_CHAR(1234567.8, '9,999,999.9')
FROM dual;
-- 9패턴 vs 0패턴(자릿수 고정)
SELECT TO_CHAR(1234.5, '9,999,999.9') -- 1234.5
      ,TO_CHAR(1234.5, '0,000,000.0') -- 0,001,234.5
      -- 고정된 자릿수를 사용함
      ,TO_CHAR(1234.5, '0,000,000.00') -- 0,001,234.50
FROM dual;
-- 통화기호
SELECT TO_CHAR(1234.5, 'L9,999,999.00') -- '(원)1,234.50'
FROM dual;

-- #3. TO_NUMBER(): 문자형 -> 숫자형으로
-- 9패턴
SELECT TO_NUMBER('1,234.5', '9,999.9') -- 1234.5
      ,TO_NUMBER('1,234,567.8', '9,999,999,999.9') -- 실제 문자열 값보다 더 큰 자릿수로 포맷팅!  -- 1234567.8
      -- ? 문자 자릿수보다 많은 패턴 자릿수가 필요함!
FROM dual;

-- 사원의 사번, 입사일, 오늘까지의 근무일수, 22년 12월 31일까지의 근무일수를 출력하시오.
-- 근무일수: 현재 날짜 - 입사일
SELECT employee_id "사번", hire_date "입사일"
      ,TRUNC(SYSDATE - hire_date) "오늘까지의 근무일수" -- (날짜 - 날짜)
      ,TRUNC(TO_DATE('22/12/31') - hire_date) "22/12/31까지의 근무일수"
FROM employees;

-- 08월 입사자들의 사번, 입사일자, 입사요일을 출력하시오.
SELECT employee_id "사번", hire_date "입사일", TO_CHAR(hire_date, 'day') "입사요일"
FROM employees
WHERE TO_CHAR(hire_date, 'mm') = 08;

-- -----------------------------

-- 5. NULL 관련 함수: NVL(,), NVL2(,,), NULLIF(,)

-- NVL2(,,)
SELECT NVL2(commission_pct, '수당있음', '수당없음')
FROM employees;

-- NULLIF(,)
SELECT NULLIF(10, 10) -- null
      ,NULLIF('hello', 'hi') -- hello
FROM dual;

-- NULL 관련 비교 연산자: IS NULL, IS NOT NULL
SELECT employee_id, department_id
FROM employees
WHERE department_id = NULL; -- 결과행 0건, 비교행의 결과가 없음! NULL은 비교 연산자로 =을 사용해서는 안됨!
-- #1. IS NULL
SELECT employee_id, department_id
FROM employees
WHERE department_id IS NULL; -- 부서 배치를 받지 못한 사원 -> 1건

-- #2. IS NOT NULL
-- <> 사용하지 않기!
SELECT employee_id, department_id
FROM employees
WHERE department_id <> NULL;

-- IS NOT NULL
SELECT employee_id, department_id
FROM employees
WHERE department_id IS NOT NULL; -- 106건

-- -----------------------------

-- 6. 일반 함수: DECODE(), CASE절

-- #1. DECODE()
SELECT DECODE(commission_pct, null, 0, commission_pct)
      ,NVL(commission_pct, 0) -- 위 DECODE() 함수 사용과 동일 결과값
FROM employees;

SELECT DECODE(commission_pct, null, '수당있음', '수당없음')
      ,NVL2(commission_pct, '수당없음', '수당있음') -- 위 DECODE() 함수 사용과 동일 결과값
FROM employees;

-- 수당 없으면 '수당없음'을 출력하고, 수당이 0.1~0.5인 경우 'B등급' 그 외의 경우에는 'A등급'
SELECT employee_id, commission_pct
      ,DECODE(commission_pct, null, '수당없음', 0.1, 'B등급', 'A등급')
FROM employees;

-- #2. CASE절
-- #2-1
-- CASE컬럼 WHEN 값1 THEN 출력
--          WHEN 값2 THEN
--		    ELSE -- 값1도 아니고 값2도 아닌 값이 출력됨
-- END

-- #2-2
-- CASE WHEN 조건식 THEN
--		WHEN 조건식 THEN
--		ELSE
-- END

-- 수당이 0.1~0.5인 경우 'B등급' 그 외의 경우에는 'A등급'
SELECT employee_id, commission_pct
      ,CASE commission_pct WHEN 0.1 THEN 'B등급'
                           ELSE 'A등급'
      END
FROM employees;


-- 수당 없으면 '수당없음'을 출력하고, 
-- 수당이 0.1~0.19까지는 'F등급'
--       0.2~0.29까지는 'E등급'
--       0.3~0.39까지는 'D등급'
--       0.4~0.49까지는 'C등급'
--       0.5~0.59까지는 'B등급'
--       0.6 이상은 'A등급'
SELECT employee_id, commission_pct
      ,CASE WHEN commission_pct >= 0.6 THEN 'A등급'
            WHEN commission_pct >= 0.5 THEN 'B등급'
            WHEN commission_pct >= 0.4 THEN 'C등급'
            WHEN commission_pct >= 0.3 THEN 'D등급'
            WHEN commission_pct >= 0.2 THEN 'E등급'
            WHEN commission_pct >= 0.1 THEN 'F등급'
            WHEN commission_pct IS NULL THEN '수당없음' -- ELSE절과 동일
      -- ELSE '수당없음'
      END
FROM employees;

-- ---------------------------------------------------------

-- B) 다중행함수: COUNT(), SUM(), AVG(), MAX(), MIN()
-- COUNT()
SELECT COUNT(*) "전체사원수" -- 107
      ,COUNT(commission_pct) "수당받는사원수" -- 35
      ,COUNT(department_id) "부서배치받은사원수" -- 106 (배치받지못한사원수 = 1)
FROM employees;

-- SUM()
SELECT SUM(salary) "총급여"
FROM employees;

-- AVG()
SELECT AVG(salary) "평균급여"
FROM employees;
-- SUM()이나 AVG()는 숫자 자료형만 사용이 가능함

-- MAX()& MIN()
SELECT MAX(salary) "최대급여", MIN(salary) "최소급여"
FROM employees;

-- ---------------------------------------------------------

-- C) 그룹화 - GROUP BY: GROUP BY절에서 사용한 컬럼만 집계함수와 함께 SELECT절에서 사용이 가능함!!!

-- 부서별 부서번호, 사원수를 출력하시오.
SELECT department_id "부서번호"
       , COUNT(*) "부서별사원수"
       , MAX(salary) "부서의최대급여"
       , AVG(salary) "부서의평균급여"
FROM employees
GROUP BY department_id; -- MAP 자료 구조를 사용 (key = 부서번호, value = 부서번호에 해당하는 값)

-- 부서별 부서번호와 부서의 최대급여, 최대급여자 이름을 출력하시오.
SELECT department_id "부서번호"
       , MAX(salary) "부서의최대급여"
       , first_name "최대급여자 이름"
FROM employees
GROUP BY department_id;

-- 부서별, 직무별로 
-- 부서번호, 직무번호, 사원수를 출력하시오.
SELECT department_id "부서번호", job_id "직무명", COUNT(*) "사원수"
FROM employees
GROUP BY department_id, job_id -- 대그룹 department_id, 소그룹 job_id
-- ORDER BY department_id; -- 컬럼순으로 오름차순
ORDER BY department_id, COUNT(*); -- 부서번호 별로 사원수 적은 job_id부터 먼저 출력

-- 그룹별 소계, 합계: ROLLUP()
-- 계산이 가능한 모든 소계, 합계: CUBE()
-- 부서별, 직무별로 부서번호, 직무번호, 사원수를 출력하시오.
SELECT department_id "부서번호", job_id "직무명", COUNT(*) "사원수"
FROM employees
-- GROUP BY ROLLUP( department_id, job_id)
GROUP BY CUBE( department_id, job_id)
ORDER BY department_id, COUNT(*);


-- 1_그룹 조건: HAVING - 집계함수사용가능
-- 2_일반 조건: WHERE - 집계함수사용불가능

-- 30, 50번 부서의 부서별 부서번호, 평균급여, 최대급여를 출력하시오.
SELECT department_id "부서번호"
       , AVG(salary) "부서의평균급여"
       , MAX(salary) "부서의최대급여"
FROM employees
-- WHERE department_id = 30 OR department_id = 50
WHERE department_id IN(30, 50)
GROUP BY department_id
ORDER BY department_id;

-- 모든 부서의 부서별 부서번호, 평균급여, 최대급여를 출력하시오.
-- 단, 부서가 없는 사원들은 출력하지 않는다.
SELECT department_id "부서번호"
       , TRUNC(AVG(salary), 0) "부서의평균급여"
       , TRUNC(MAX(salary), 0) "부서의최대급여"
FROM employees
WHERE department_id IS NOT NULL
GROUP BY department_id;
-- 위아래 동일 (그러나 일반 조건인 경우는 WHERE절 사용하는 것을 권장함)
SELECT department_id "부서번호"
       , TRUNC(AVG(salary), 0) "부서의평균급여"
       , TRUNC(MAX(salary), 0) "부서의최대급여"
FROM employees
GROUP BY department_id
HAVING department_id IS NOT NULL;

-- 부서의 부서번호, 평균급여, 최대급여, 최소급여를 출력하시오
-- 평균급여가 10000이상인 부서만 출력하시오
SELECT department_id "부서번호"
       , AVG(salary) "부서의평균급여"
       , MAX(salary) "부서의최대급여"
       , MIN(salary) "부서의최소급여"
FROM employees
GROUP BY department_id
HAVING AVG(salary) >= 10000;

-- ---------------------------------------------------------

-- D) 정렬하기
-- SELECT 처리 순서: FROM ▶? WHERE ▶? GROUP BY ▶? HAVING ▶? SELECT ▶? ORDER BY

-- 사번 순으로 출력되는 것처럼 보이지만, 입력 순으로 출력되고 있음
SELECT employee_id, hire_date, salary 
FROM employees;

-- 입사 일자가 빠른 순서대로 출력하시오.
SELECT employee_id, hire_date, salary 
FROM employees
-- ORDER BY hire_date -- default = 오름차순
ORDER BY hire_date ASC; -- ASC = 오름차순 -- 102번이 제일 빨리 입사함

-- 급여가 많은 사원부터 출력하시오.
SELECT employee_id, hire_date, salary 
FROM employees
ORDER BY salary DESC; -- DESC = 내림차순

-- 입사 일자가 빠른 순서대로 출력하시오. 입사 일자가 같은 경우, 급여가 많은 사원부터 출력하시오.
SELECT employee_id, hire_date, salary 
FROM employees
ORDER BY hire_date ASC, salary DESC; -- 2차 정렬