-- #INLINE VIEW: FROM절에서 사용하는 SubQuery#
-- 사원의 사번, 이름, 급여를 출력하시오. 단, 급여를 많이 받는 순으로 출력하시오.
SELECT employee_id, first_name, salary
FROM employees
ORDER BY salary DESC;

-- #ROWNUM: 행번호(오라클에 내장되어 있음)
-- 사원의 행번호, 사번, 이름, 급여를 출력하시오. 단, 급여를 많이 받는 순으로 출력하시오.
SELECT ROWNUM, employee_id, first_name, salary
FROM employees
ORDER BY salary DESC;
-- ROWNUM 정렬 ?
SELECT ROWNUM, employee_id, first_name, salary
FROM (SELECT employee_id, first_name, salary -- INLINE VIEW
      FROM employees
      ORDER BY salary DESC);

-- #TOP-N Query
-- 사원의 행번호, 사번, 이름, 급여를 출력하시오. 단, 급여를 많이 받는 사원 TOP5만 출력하시오.
SELECT ROWNUM, employee_id, first_name, salary
FROM (SELECT employee_id, first_name, salary -- INLINE VIEW
      FROM employees
      ORDER BY salary DESC)
WHERE ROWNUM BETWEEN 1 AND 5;

-- 사원의 행번호, 사번, 이름, 급여를 출력하시오. 많은 급여를 받는 사원부터 정렬하여 11~20번째 사원만 출력한다.
SELECT ROWNUM, employee_id, first_name, salary
FROM (SELECT employee_id, first_name, salary
      FROM employees
      ORDER BY salary DESC)
WHERE ROWNUM BETWEEN 11 AND 20; -- 0
-- ROWNUM이 1부터 시작하기 때문에 ? 이렇게 사용해서는 안됨!
-- 내장되어 있는 SUDO 컬럼인 ROWNUM을 기준으로 출력하고 있기 때문에,
-- 반복문을 끝까지 돌때까지 ROWNUM은 계속 1인 상태임
-- 해결하기 위해서는 INLINE VIEW가 한 번 더 필요함! ?
SELECT *
FROM (SELECT ROWNUM rn, employee_id, first_name, salary -- INLINE VIEW#1 2.ROWNUM 발급
      FROM (SELECT employee_id, first_name, salary -- INLINE VIEW#2, 1.정렬하고
            FROM employees
            ORDER BY salary DESC)
      )
WHERE rn BETWEEN 11 AND 20;
-- WHERE ROWNUM BETWEEN 11 AND 20; -- 안쪽에서 사용한 ROWNUM은 이미 사용되고 끝났기 때문에,
-- ROWNUM에 별칭을 줘서 사용하기!
-- 별칭 한번 더 주기! ?
SELECT *
FROM (SELECT ROWNUM rn, a.*
      FROM (SELECT *
            FROM employees
            ORDER BY salary DESC) a -- INLINE VIEW에 대한 별칭
      )
WHERE rn BETWEEN 11 AND 20;

-- ---------------
-- #Scalar Query: SELECT절에서 사용하는 SubQuery#
-- 사원의 사번, 부서번호, 부서명을 출력하시오.
-- 1. JOIN으로 해결
SELECT employee_id, e.department_id, department_name
FROM employees e JOIN departments d ON(e.department_id = d.department_id);
-- 2. Scalar Query로 해결
SELECT employee_id, department_id
        , (SELECT department_name
           FROM departments
           WHERE department_id = e.department_id
           -- WHERE departments.department_id
           -- WHERE department_id = employees.department_id
           )
FROM employees e;

-- ---------------
-- 부서명이 'IT'인 부서에 근무하는 사원들의 사번, 이름을 출력하시오.
SELECT employee_id, first_name
FROM employees e JOIN departments d ON(e.department_id = d.department_id)
WHERE department_name = 'IT';
-- 한 테이블로 해결 가능하기에 JOIN 필요 X
SELECT employee_id, first_name
FROM employees
WHERE department_id = (SELECT department_id
                       FROM departments
                       WHERE department_name = 'IT'
                       );


-- 이름이 'Bruce'인 사원과 같은 부서에 근무하는 사원들의 사번, 이름을 출력하시오.
-- 셍나
SELECT employee_id, first_name
FROM employees
WHERE department_id = (SELECT department_id 
                       FROM employees
                       WHERE first_name = 'Bruce'
                       )
      AND first_name <> 'Bruce';
-- 교수님
SELECT employee_id, first_name
FROM employees
WHERE department_id = (SELECT department_id
                       FROM employees
                       WHERE first_name = 'Bruce'
                       )
      AND first_name <> 'Bruce';
      
-- 이름이 'Bruce'인 사원과 같은 부서에 근무하면서 부서 평균 급여보다 많은 급여를 받는
-- 사원들의 사번, 이름을 출력하시오.
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
-- 조건 추가함!!
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
-- 교수님
SELECT employee_id, first_name, salary
FROM employees e
WHERE salary > (SELECT AVG(salary) 
                FROM employees
                WHERE department_id = e.department_id
                 )
      AND department_id = (SELECT department_id
                           FROM employees
                           WHERE first_name = 'Bruce');
-- 상호 연관성 쿼리 ? 메인 쿼리에서 사용한 별칭을 서브 쿼리에서 사용하는 것!

-- IT 부서 사람들 급여
SELECT employee_id, first_name, salary
FROM employees e JOIN departments d ON(e.department_id = d.department_id) 
WHERE department_name = 'IT';

-- IT 부서 평균 급여
SELECT AVG(salary)
FROM employees e JOIN departments d ON(e.department_id = d.department_id) 
WHERE department_name = 'IT'

-- ----------------------------------------------------------------------

-- DDL
-- 1. 테이블 생성
CREATE TABLE t_a(one NUMBER(5),
                 two NUMBER(5,2),
                 three CHAR(3),
                 four VARCHAR2(3),
                 five DATE );

-- -----------------------
-- DML
-- 2. 데이터 추가
INSERT INTO t_a(one, three, five) VALUES (1, 'A', SYSDATE);
INSERT INTO t_a VALUES(2, 2.2, 'B', 'B', '23/01/01');
INSERT INTO t_a VALUES(3, null, 'C', '', '23/01/01');

-- CHAR 자료형: 고정 길이, VARCHAR2 자료형: 가변 길이
SELECT three, LENGTH(three), four, LENGTH(four) FROM t_a;

-- -----------------------
-- 3. 기존 테이블 복사해서 테이블 생성
CREATE TABLE t_a_copy AS SELECT * FROM t_a;
SELECT * FROM t_a_copy;

CREATE TABLE t_a_copy2 AS SELECT one, three FROM t_a;
SELECT * FROM t_a_copy2;

-- 구조만 복사해서 테이블 생성
CREATE TABLE t_a_copy3 AS SELECT * FROM t_a WHERE 1 = 2; 
-- WHERE절에 조건에 만족하지 않는 조건 넣기 -> 테이블의 구조만 복사해오고 행(데이터)은 복사하지 않음!
SELECT * FROM t_a_copy3;

-- -----------------------
-- 4. 테이블 구조 변경
-- 1) 기존 테이블에 컬럼 추가하기
ALTER TABLE t_a ADD six number;
-- 자료형 number, 자릿수는 지정하지 않음 -> number가 표현할 수 있는 최대 자릿수로 !
-- ADD 사용하면 새로운 컬럼이 마지막 컬럼으로 추가됨!!

-- 2) 컬럼 이름 변경
ALTER TABLE t_a RENAME COLUMN six TO six2;

-- 3) 컬럼 삭제
ALTER TABLE t_a DROP COLUMN six;

-- 4) 컬럼의 자료형 변경 or 자릿수 변경
ALTER TABLE t_a MODIFY four VARCHAR2(10);
-- four VARCHAR2(3) -> four VARCHAR2(10)로 자릿수 변경
-- 기존에 이미 값이 들어있는 컬럼의 자료형을 다른 자료형으로 변경할 수 업음!

-- 5) 테이블 제거
DROP TABLE t_a_copy3;

-- 6) 데이터 수정
SELECT * FROM t_a;

-- t_a 테이블의 two 컬럼값이 null이 아니면 two 컬럼값을 1.5배 증가한다!
UPDATE t_a
SET two = two*1.5 -- UPDATE SET절에서만 =가 대입연산자로 사용됨! 다른 곳에서는 다 비교연산자로 쓰임!!
WHERE two IS NOT NULL;

-- t_a 테이블의 two 컬럼값이 null이면 one 컬럼값은 1 증가하고, five 컬럼값은 1일 감소한다!
UPDATE t_a
SET one = one+1, five = five-1
WHERE two IS NULL;

-- 7) 데이터 삭제
-- t_a 테이블의 one 컬럼값이 4인 행을 삭제한다.
DELETE t_a
WHERE one = 4;

-- t_a 테이블의 five 컬럼값이 어제날짜(23/08/17)인 행을 삭제한다.
DELETE t_a
WHERE TO_CHAR(five, 'yy/mm/dd') = '23/08/17';
-- 문자로 변경하고 삭제해야 함!! 
-- SYSDATE는 시분초 정보도 있기 때문에, 필요한 정보만 가져와서 비교 해야함!
DELETE t_a
WHERE TO_CHAR(five, 'yy/mm/dd') = TO_CHAR(SYSDATE-1, 'yy/mm/dd'); -- SYSDATE 기준으로!

-- ----------------------------------------------------------------------

-- 무결성 제약 조건: 결점 없는(Data Integrity) 데이터를 위한 제약 조건을 설정할 수 있따!
-- 테이블 생성 시 혹은 테이블 조건 변경할 때 조건 설정이 가능함!

-- 1. UNIQUE: 중복 안됨
-- 2. NOT NULL: NULL 포함 안됨
-- 3. PRIMARY KEY: UNIQUE + NOT NULL
-- 4. CHECK: 특정값만 포함
-- 5. FOREGIN KEY: 부모엔티티의 PK를 참조

-- -----------------------
--id1, p1, n1, id1@a.com, 1
--id2, p2, n2, id2@a.com, 1
--id3, p3, '', id3@a.com, 1
--id4, p4, n2, '', -1
-- -> 후보 키들 중에서 중복되지 않고 null이 아닌 값인 id 컬럼을 식별자로 사용해야 함! -> PK(주식별자:기본키)

CREATE TABLE t_b(
    id varchar2(5), -- 아이디_PRIMARY KEY
    pwd varchar2(5), -- 비번_NOT NULL
    name varchar2(30), -- 이름_제약조건없음
    email varchar2(30), -- 이메일_UNIQUE
    status number(1) -- 상태(아이디가 활동 중인지, 휴면 상태인지 등)_CHECK
                     -- -1: 탈퇴, 0: 휴면, 1: 활동
);

-- 제약 조건 설정 방법 (테이블 레벨 제약 조건 사용을 권장 - 가독성 조음)
-- !! NOT NULL 제약 조건은 반드시!! 컬럼 레벨로만 제약 조건 설정이 가능함!!

-- 1) 테이블 레벨 제약 조건 설정하기
-- 테이블 먼저 나열, 이후 제약 조건만 따로 나열!
CREATE TABLE t_b(
    id varchar2(5),
    pwd varchar2(5),
    email varchar2(30),
    status number(1),
    CONSTRAINT t_b_id_pk     PRIMARY KEY(id),
    CONSTRAINT t_b_email_uq  UNIQUE(email),
    CONSTRAINT t_b_status_ck CHECK(status IN (-1, 0, 1))
);

-- 2) 컬럼 레벨 제약 조건 설정하기
-- 컬럼 만들면서 제약 조건도 같이 추가하기!
-- 제약 조건의 이름은 생략 가능함! 조건만 나열해도 OK
CREATE TABLE t_b(
    id varchar2(5) CONSTRAINT t_b_id_pk         PRIMARY KEY(id),
    pwd varchar2(5)                             NOT NULL(pwd),
    email varchar2(30) CONSTRAINT t_b_email_uq  UNIQUE(email),
    status number(1) CONSTRAINT t_b_status_ck   CHECK(status IN (-1, 0, 1))
);

-- 3) 이미 만들어져 있는 테이블(데이터도 들어있음)에 제약 조건만 추가하기
-- ALTER 명령어 사용 -> 테이블 구조 변경으로 제약 조건을 추가해야 함!
-- 테이블 레벨 제약 조건으로 추가하기!
CREATE TABLE t_b(
    id varchar2(5),
    pwd varchar2(5),
    email varchar2(30),
    status number(1),
);

-- id 제약 조건 추가
ALTER TABLE t_b
ADD CONSTRAINT t_b_id_pk PRIMARY KEY(id);
-- email이랑 status 제약 조건 추가
ALTER TABLE t_b
ADD CONSTRAINT t_b_email_uq UNIQUE(email)
ADD CONSTRAINT t_b_status_ck CHECK(status IN (-1, 0, 1));
-- pwd에 NOT NULL 제약 조건(컬럼 레벨로만 가능)을 추가 -> ADD CONSTRAINT 불가능! MODIFY로!
ALTER TABLE t_b
MODIFY pwd NOT NULL;

select * from t_b;

-- ---------------------------------
COMMIT;