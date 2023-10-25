package com.my.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.board.dao.BoardOracleRepository;
import com.my.board.dto.Board;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;

@Service
public class BoardService {

	@Autowired
	private BoardOracleRepository repository;
	
	/**
	 * 전체 게시글을 조회
	 * @return
	 * @throws FindException
	 */
	public List<Board> findAll() throws FindException {
		return repository.selectAll();
	} // findAll()
	
	/**
	 * 게시글 번호에 해당하는 상세 정보를 반환
	 * @param boardNo
	 * @return
	 * @throws FindException
	 */
	public Board findByBoardNo(int boardNo) throws FindException {
		return repository.selectByBoardNo(boardNo);
	} // findByBoardNo()
	
	/**
	 * 글 작성
	 * @param board
	 * @throws AddException
	 */
	public void write(Board board) throws AddException {
		repository.insertBoard(board);
	} // write()
	
	/**
	 * 글 수정
	 * @param board
	 * @throws ModifyException
	 */
	public void modify(Board board) throws ModifyException {
		repository.updateBoard(board);
	} // modify()
	
	/**
	 * 글 삭제
	 * @param boardNo
	 * @throws RemoveException
	 */
	public void remove(int boardNo) throws RemoveException {
		repository.deleteByBoardNo(boardNo);
	} // remove()
	
} // end class
