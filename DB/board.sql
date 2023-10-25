-- �Խñ� ���̺�
CREATE table board(
board_no number, -- �Խñ� ��ȣ
board_title varchar2(50), -- �Խñ� ����
board_content varchar2(100), -- �Խñ� ����
board_id varchar2(5), -- �Խñ� �ۼ���
board_dt date); -- �Խñ� �ۼ���


ALTER TABLE board
ADD CONSTRAINT board_no_pk PRIMARY KEY(board_no)
ADD CONSTRAINT board_id_fk FOREIGN KEY(board_id) REFERENCES customer(id);


ALTER TABLE board
MODIFY board_dt default SYSDATE;

-- ********************************************

-- ��� ���̺�
DROP TABLE board_reply;

CREATE TABLE board_reply(
reply_no number, -- ��� ��ȣ
reply_board_no number, -- �Խñ� ��ȣ
reply_parent_no number, -- �θ� ��� ��ȣ (null�� ��� = ���, null�� �ƴ� ��� = ����� ���)
reply_content varchar2(100), -- ��� ����
reply_id varchar2(5), -- ��� �ۼ���
reply_dt date default sysdate); -- ��� �ۼ�����

ALTER TABLE board_reply
ADD CONSTRAINT reply_no_pk PRIMARY KEY(reply_no)
ADD CONSTRAINT reply_board_no_fk FOREIGN KEY(reply_board_no) REFERENCES board(board_no) ON DELETE CASCADE
ADD CONSTRAINT reply_parent_no_fk FOREIGN KEY(reply_parent_no) REFERENCES board_reply(reply_no) ON DELETE CASCADE
ADD CONSTRAINT reply_id FOREIGN KEY(reply_id) REFERENCES customer(id);
-- ON DELETE CASCADE = �Խñ� ��ȣ�� �ش��ϴ� �Խñ��� ������ ���, �Խñ� ��ȣ�� �����ϰ� �ִ� ��� ��ȣ�� �����ǵ��� �ϴ� ��
-- �θ� ���̺��� ���� �����Ǹ� �ڽ� ���̺����� ���� ������ �� �ֵ��� �ڽ��ʿ��ٰ� ���� ������ ����

-- ********************************************

-- ������ ����
CREATE SEQUENCE board_seq;
CREATE SEQUENCE reply_seq;

-- ������ ����
DROP SEQUENCE board_seq;
DROP SEQUENCE reply_seq;

COMMIT;

-- ********************************************

-- �Խñ� �߰�
INSERT INTO board(board_no, board_title, board_content, board_id) 
VALUES(board_seq.NEXTVAL, '����1', '����1', 'sein');
INSERT INTO board(board_no, board_title, board_content, board_id) 
VALUES(board_seq.NEXTVAL, '����2', '����2', '010');
INSERT INTO board(board_no, board_title, board_content, board_id) 
VALUES(board_seq.NEXTVAL, '����3', '����3', 'seng');

SELECT * FROM board;

-- ********************************************

-- ��� �߰�

-- 1������ ���
INSERT INTO board_reply(reply_no, reply_board_no, reply_parent_no, reply_content, reply_id)
VALUES (reply_seq.NEXTVAL, 1, NULL, '����1�� ���1', 'seng');

-- 1������ ���(��۹�ȣ[1])�� ���
INSERT INTO board_reply(reply_no, reply_board_no, reply_parent_no, reply_content, reply_id)
VALUES (reply_seq.NEXTVAL, 1, 1, '����1�� ���1�� ���1', 'seng');
-- 1������ ���(��۹�ȣ[1])�� ����� ���
INSERT INTO board_reply(reply_no, reply_board_no, reply_parent_no, reply_content, reply_id)
VALUES (reply_seq.NEXTVAL, 1, 1, '����1�� ���1�� ���2', 'seng');

-- 1������ ���(��۹�ȣ[2])�� ���
INSERT INTO board_reply(reply_no, reply_board_no, reply_parent_no, reply_content, reply_id)
VALUES (reply_seq.NEXTVAL, 1, 2, '����1�� ���1�� ���1�� ���', 'seng');

SELECT * FROM board_reply;

-- ********************************************

-- ������ ����
SELECT LEVEL, r.* -- LEVEL = �� ������ depth�� ��Ÿ��(0���� ����)
FROM board_reply r
START WITH reply_parent_no IS NULL
CONNECT BY PRIOR reply_no = reply_parent_no;

-- ********************************************

-- �Խñ� �� ��ȸ
SELECT *
FROM board b LEFT JOIN 
(SELECT level,r1.* FROM board_reply r1 START WITH reply_parent_no IS NULL CONNECT BY PRIOR reply_no =  reply_parent_no 
 ORDER SIBLINGS BY reply_no DESC)r
ON b.board_no = r.reply_board_no
WHERE board_no = 1;

-- �Խñ� ��� ��ȸ         GET      /board/list
SELECT b.*, 
      (SELECT COUNT(*)
       FROM board_reply
       WHERE reply_board_no=b.board_no)replycnt -- ��� ������ ī�����ؼ� ������
FROM board  b
ORDER BY board_no DESC;

commit;
