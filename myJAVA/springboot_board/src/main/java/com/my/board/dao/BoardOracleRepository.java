package com.my.board.dao;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.my.board.dto.Board;
import com.my.board.dto.Reply;
import com.my.board.dto.Reply;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;


@Repository
public class BoardOracleRepository {

	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	/**
	 * 게시글 목록을 검색한다(전체 게시글 목록을 검색할 메소드)
	 * @return 게시글 목록
	 * @throws FindException
	 */
	public List<Board> selectAll() throws FindException {
		SqlSession session = null;
		
		try {
			session = sqlSessionFactory.openSession();
			
			List<Board> list = 
					session.selectList("com.my.board.BoardMapper.selectAll");
			
			return list;
		} catch(Exception e) {
			throw new FindException(e.getMessage());
		} finally {
			if(session != null) {
				session.close();
			} // if
		} // try-catch-finally
	} // selectAll()
	
	/**
	 * 게시글 번호로 게시글 목록 상세 조회한다
	 * @param boardNo
	 * @return Board 객체
	 * @throws FindException
	 */
	public Board selectByBoardNo(int boardNo) throws FindException {
		SqlSession session = null;
		
		try {
			session = sqlSessionFactory.openSession();
			
			Board b = 
					session.selectOne("com.my.board.BoardMapper.selectByBoardNo", boardNo);
			
			return b;
		} catch(Exception e) {
			throw new FindException(e.getMessage());
		} finally {
			if(session != null) {
				session.close();
			} // if
		} // try-catch-finally
	} // selectByBoardNo()
	
	/**
	 * 반환형을 Optional) 게시글 번호로 게시글 목록 상세 조회한다  
	 * @param boardNo
	 * @return
	 * @throws FindException
	 */
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
	
	/**
	 * 글 작성
	 * @param board
	 * @throws AddException
	 */
	public void insertBoard(Board board) throws AddException {
		SqlSession session = null;
		
		try {
			session = sqlSessionFactory.openSession();
			
			session.insert("com.my.board.BoardMapper.insertBoard", board);
		} catch(Exception e) {
			e.printStackTrace();
			throw new AddException(e.getMessage());
		} finally {
			if(session != null) {
				session.close();
			} // if
		} // try-catch-finally
	} // insertBoard()
	
	/**
	 * 글 수정
	 * @param board
	 * @throws ModifyException
	 */
	public void updateBoard(Board board) throws ModifyException {
		SqlSession session = null;
		
		try {
			session = sqlSessionFactory.openSession();
			
			session.update("com.my.board.BoardMapper.updateBoard", board);
		} catch(Exception e) {
			e.printStackTrace();
			throw new ModifyException(e.getMessage());
		} finally {
			if(session != null) {
				session.close();
			} // if
		} // try-catch-finally
	} // updateBoard()
	
	/**
	 * 글 삭제
	 * @param boardNo
	 * @throws RemoveException
	 */
	public void deleteByBoardNo(int boardNo) throws RemoveException {
		SqlSession session = null;
		
		try {
			session = sqlSessionFactory.openSession();
			
			session.delete("com.my.board.BoardMapper.deleteByBoardNo", boardNo);
		} catch(Exception e) {
			e.printStackTrace();
			throw new RemoveException(e.getMessage());
		} finally {
			if(session != null) {
				session.close();
			} // if
		} // try-catch-finally
	} // deleteByBoardNo()
	
	/**
	 * 답글 작성
	 * @param reply
	 * @throws AddException
	 */
	public void insertReply(Reply reply) throws AddException {
		SqlSession session = null;
		
		try {
			session = sqlSessionFactory.openSession();
			
			session.insert("com.my.board.BoardMapper.insertReply", reply);
		} catch(Exception e) {
			session.rollback();
			e.printStackTrace();
			throw new AddException(e.getMessage());
		} finally {
			if(session != null) {
				session.close();
			} // if
		} // try-catch-finally
	} // insertReply()
	
	/**
	 * 답글 수정
	 * @param reply
	 * @throws ModifyException
	 */
	public void updateReply(Reply reply) throws ModifyException {
		SqlSession session = null;
		
		try {
			session = sqlSessionFactory.openSession();
			
			session.update("com.my.board.BoardMapper.updateReply", reply);
		} catch(Exception e) {
			e.printStackTrace();
			throw new ModifyException(e.getMessage());
		} finally {
			if(session != null) {
				session.close();
			} // if
		} // try-catch-finally
	} // updateReply()
	
	/**
	 * 답글 삭제
	 * @param replyNo
	 * @throws RemoveException
	 */
	public void deleteReply(int replyNo) throws RemoveException {
		SqlSession session = null;
		
		try {
			session = sqlSessionFactory.openSession();
			
			session.delete("com.my.board.BoardMapper.deleteReply", replyNo);
		} catch(Exception e) {
			e.printStackTrace();
			throw new RemoveException(e.getMessage());
		} finally {
			if(session != null) {
				session.close();
			} // if
		} // try-catch-finally
	} // deleteReply()
	
} // end class
