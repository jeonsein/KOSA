-- 게시글 테이블
CREATE table board(
board_no number, -- 게시글 번호
board_title varchar2(50), -- 게시글 제목
board_content varchar2(100), -- 게시글 내용
board_id varchar2(5), -- 게시글 작성자
board_dt date); -- 게시글 작성일


ALTER TABLE board
ADD CONSTRAINT board_no_pk PRIMARY KEY(board_no)
ADD CONSTRAINT board_id_fk FOREIGN KEY(board_id) REFERENCES customer(id);


ALTER TABLE board
MODIFY board_dt default SYSDATE;

-- ********************************************

-- 답글 테이블
DROP TABLE board_reply;

CREATE TABLE board_reply(
reply_no number, -- 답글 번호
reply_board_no number, -- 게시글 번호
reply_parent_no number, -- 부모 답글 번호 (null인 경우 = 답글, null이 아닌 경우 = 답글의 답글)
reply_content varchar2(100), -- 답글 내용
reply_id varchar2(5), -- 답글 작성자
reply_dt date default sysdate); -- 답글 작성일자

ALTER TABLE board_reply
ADD CONSTRAINT reply_no_pk PRIMARY KEY(reply_no)
ADD CONSTRAINT reply_board_no_fk FOREIGN KEY(reply_board_no) REFERENCES board(board_no) ON DELETE CASCADE
ADD CONSTRAINT reply_parent_no_fk FOREIGN KEY(reply_parent_no) REFERENCES board_reply(reply_no) ON DELETE CASCADE
ADD CONSTRAINT reply_id FOREIGN KEY(reply_id) REFERENCES customer(id);
-- ON DELETE CASCADE = 게시글 번호에 해당하는 게시글이 삭제된 경우, 게시글 번호를 참조하고 있는 답글 번호가 삭제되도록 하는 것
-- 부모 테이블에서 행이 삭제되면 자식 테이블에서도 행이 삭제될 수 있도록 자식쪽에다가 제약 조건을 설정

-- ********************************************

-- 시퀀스 생성
CREATE SEQUENCE board_seq;
CREATE SEQUENCE reply_seq;

-- 시퀀스 삭제
DROP SEQUENCE board_seq;
DROP SEQUENCE reply_seq;

COMMIT;

-- ********************************************

-- 게시글 추가
INSERT INTO board(board_no, board_title, board_content, board_id) 
VALUES(board_seq.NEXTVAL, '제목1', '내용1', 'sein');
INSERT INTO board(board_no, board_title, board_content, board_id) 
VALUES(board_seq.NEXTVAL, '제목2', '내용2', '010');
INSERT INTO board(board_no, board_title, board_content, board_id) 
VALUES(board_seq.NEXTVAL, '제목3', '내용3', 'seng');

SELECT * FROM board;

-- ********************************************

-- 답글 추가

-- 1번글의 답글
INSERT INTO board_reply(reply_no, reply_board_no, reply_parent_no, reply_content, reply_id)
VALUES (reply_seq.NEXTVAL, 1, NULL, '제목1의 답글1', 'seng');

-- 1번글의 답글(답글번호[1])의 답글
INSERT INTO board_reply(reply_no, reply_board_no, reply_parent_no, reply_content, reply_id)
VALUES (reply_seq.NEXTVAL, 1, 1, '제목1의 답글1의 답글1', 'seng');
-- 1번글의 답글(답글번호[1])의 답글의 답글
INSERT INTO board_reply(reply_no, reply_board_no, reply_parent_no, reply_content, reply_id)
VALUES (reply_seq.NEXTVAL, 1, 1, '제목1의 답글1의 답글2', 'seng');

-- 1번글의 답글(답글번호[2])의 답글
INSERT INTO board_reply(reply_no, reply_board_no, reply_parent_no, reply_content, reply_id)
VALUES (reply_seq.NEXTVAL, 1, 2, '제목1의 답글1의 답글1의 답글', 'seng');

SELECT * FROM board_reply;

-- ********************************************

-- 계층형 쿼리
SELECT LEVEL, r.* -- LEVEL = 그 계층의 depth를 나타냄(0부터 시작)
FROM board_reply r
START WITH reply_parent_no IS NULL
CONNECT BY PRIOR reply_no = reply_parent_no;

-- ********************************************

-- 게시글 상세 조회
SELECT *
FROM board b LEFT JOIN 
(SELECT level,r1.* FROM board_reply r1 START WITH reply_parent_no IS NULL CONNECT BY PRIOR reply_no =  reply_parent_no 
 ORDER SIBLINGS BY reply_no DESC)r
ON b.board_no = r.reply_board_no
WHERE board_no = 1;

-- 게시글 목록 조회         GET      /board/list
SELECT b.*, 
      (SELECT COUNT(*)
       FROM board_reply
       WHERE reply_board_no=b.board_no)replycnt -- 답글 갯수도 카운팅해서 가져옴
FROM board  b
ORDER BY board_no DESC;

commit;
