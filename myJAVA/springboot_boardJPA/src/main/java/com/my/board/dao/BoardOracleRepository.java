package com.my.board.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.my.board.entity.Board;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;

public interface BoardOracleRepository extends JpaRepository<Board, Integer>  {

	/**
	 * 게시글 목록을 검색한다(전체 게시글 목록을 검색할 메소드)
	 * @return 게시글 목록
	 * @throws FindException
	 */
	@Query(value = "SELECT b.*, \r\n"
			+ "      	(SELECT COUNT(*) FROM board_reply WHERE reply_board_no=b.board_no) replycnt\r\n"
			+ "		FROM board b\r\n"
			+ "		ORDER BY board_no DESC",
			nativeQuery = true)
	public List<Board> selectAll();
	
	/**
	 * 게시글 번호로 게시글 목록 상세 조회한다
	 * @param boardNo
	 * @return Board 객체
	 * @throws FindException
	 */
	@Query(value = "		SELECT *\r\n"
			+ "		FROM board b LEFT JOIN \r\n"
			+ "			(SELECT level,r1.* FROM board_reply r1 START WITH reply_parent_no IS NULL CONNECT BY PRIOR reply_no =  reply_parent_no \r\n"
			+ "		 	ORDER SIBLINGS BY reply_no DESC)r\r\n"
			+ "			ON b.board_no = r.reply_board_no\r\n"
			+ "		WHERE :board_no",
			nativeQuery = true)
	public Board selectByBoardNo(Integer boardNo);
	
	/**
	 * 반환형을 Optional) 게시글 번호로 게시글 목록 상세 조회한다  
	 * @param boardNo
	 * @return
	 * @throws FindException
	 */
	/*
	public Optional<Board> selectByBoardNoOptional(int boardNo) throws FindException{
		SqlSession session = null;
		
		try {
			session = sqlSessionFactory.openSession();
			
			Board b =  session.selectOne("com.my.board.BoardMapper.selectByBoardNo", boardNo);
			Optional<Board> optB = Optional.of(b); // Optional.of()로 일반 객체를 Optional화 하게됨.
												   // 이때 인자가 null일 경우, NPE 발생함
			
			return optB;
		} catch(Exception e) {
			e.printStackTrace();
			
			throw new FindException(e.getMessage());
		} finally {
			if(session != null) {
				session.close();
			} // if
		} // try-catch-finally
	} // selectByBoardNoOptional()
	*/
	
	/**
	 * 글 작성
	 * @param board
	 * @throws AddException
	 */
	/*
	@Query(value = "		INSERT INTO board(board_no, board_title, board_content, board_id) \r\n"
			+ "		VALUES(board_seq.NEXTVAL, #{boardTitle}, #{boardContent}, #{boardId})",
			nativeQuery = true)
	public void insertBoard(Board board);
	*/
	@Query(value = "INSERT INTO board(board_no, board_title, board_content, board_id) " +
            		"VALUES(board_seq.NEXTVAL, :boardTitle, :boardContent, :boardId)",
            			nativeQuery = true)
	public void insertBoard(@Param("boardTitle") String boardTitle, 
							@Param("boardContent") String boardContent, 
							@Param("boardId") String boardId);
	
	
	/**
	 * 글 수정
	 * @param board
	 * @throws ModifyException
	 */
	/*
	@Query(value = "		UPDATE board\r\n"
			+ "		SET board_content = #{boardContent}\r\n"
			+ "		WHERE board_no = #{boardNo}",
			nativeQuery = true)
	public void updateBoard(Board board);
	*/
	@Query(value = "UPDATE board " +
            		"SET board_content = :boardContent " +
            		"WHERE board_no = :boardNo",
            nativeQuery = true)
	public void updateBoard(@Param("boardContent") String boardContent, @Param("boardNo") Integer boardNo);
	
	/**
	 * 글 삭제
	 * @param boardNo
	 * @throws RemoveException
	 */
	@Query(value = "DELETE\r\n"
					+ "FROM board\r\n"
					+ "WHERE board_no = :boardNo",
			nativeQuery = true)
	public void deleteByBoardNo(@Param("boardNo") Integer boardNo);
	
	/**
	 * 답글 작성
	 * @param reply
	 * @throws AddException
	 */
	/*
	@Query(value = "		INSERT INTO board_reply (reply_no, reply_board_no, reply_parent_no, reply_content, reply_id)\r\n"
			+ "		VALUES (reply_seq.NEXTVAL, #{replyBoardNo}, #{replyParentNo}, #{replyContent}, #{replyId})",
			nativeQuery = true)
	public void insertReply(Reply reply);
	*/
	@Query(value = "INSERT INTO board_reply (reply_no, reply_board_no, reply_parent_no, reply_content, reply_id) " +
            "VALUES (reply_seq.NEXTVAL, :replyBoardNo, :replyParentNo, :replyContent, :replyId)",
    nativeQuery = true)
	public void insertReply(@Param("replyBoardNo") Integer replyBoardNo,
	                     @Param("replyParentNo") Integer replyParentNo,
	                     @Param("replyContent") String replyContent,
	                     @Param("replyId") String replyId);
	
	/**
	 * 답글 수정
	 * @param reply
	 * @throws ModifyException
	 */
	@Query(value = "UPDATE board_reply\r\n"
					+ "SET reply_content = :replyContent\r\n"
					+ "WHERE reply_no = :replyNo",
			nativeQuery = true)
	public void updateReply(@Param("replyContent") String replyContent, 
							@Param("replyNo") Integer replyNo);
	
	/**
	 * 답글 삭제
	 * @param replyNo
	 * @throws RemoveException
	 */
	@Query(value = "DELETE board_reply\r\n"
					+ "WHERE reply_no = :replyNo",
			nativeQuery = true)
	public void deleteReply(@Param("replyNo") Integer replyNo);
	
} // end class
