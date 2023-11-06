package com.my.board.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.board.dao.BoardOracleRepository;
import com.my.board.dto.Board;
import com.my.board.dto.Reply;
import com.my.board.entity.BoardEntity;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;

import lombok.extern.slf4j.Slf4j;

@Service
public class BoardService {

	@Autowired
	private BoardOracleRepository repository;
	private BoardService service;

	private ModelMapper mapper = new ModelMapper();
	
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
	
	/**
	 * 답글 작성
	 * @param reply
	 * @throws AddException
	 */
	public void writeReply(Reply reply) throws AddException {
		repository.insertReply(reply);
	} // writeReply()
	
	/**
	 * 답글 수정
	 * @param reply
	 * @throws ModifyException
	 */
	public void modifyReply(Reply reply) throws ModifyException {
		repository.updateReply(reply);
	} // modifyReply()
	
	/**
	 * 답글 삭제
	 * @param boardReplyNo
	 * @throws RemoveException
	 */
	public void removeReply(int boardReplyNo) throws RemoveException {
		repository.deleteReply(boardReplyNo);
	} // removeReply()
	
	// -------------------------------------------------------------
	
	// Board DTO -> Entity
	public BoardEntity dtoToVO() {
		
		Board boardDTO = new Board();
		
		mapper.getConfiguration()
					.setMatchingStrategy(MatchingStrategies.STANDARD)
					.setFieldAccessLevel(AccessLevel.PRIVATE)
					.setFieldMatchingEnabled(true);
		
		Object source = boardDTO;
		Class<BoardEntity> destinationType = BoardEntity.class;
		BoardEntity boardEntity = mapper.map(source, destinationType);
		
		return boardEntity;
	}
	
	// Board Entity -> DTO
	public Board voToDto() {
		
		BoardEntity boardEntity = new BoardEntity();
		
		mapper.getConfiguration()
					.setMatchingStrategy(MatchingStrategies.STANDARD)
					.setFieldAccessLevel(AccessLevel.PRIVATE)
					.setFieldMatchingEnabled(true);
		
		Object source = boardEntity;
		Class<Board> destinationType = Board.class;
		Board boardDto = mapper.map(source, destinationType);
		
		return boardDto;
	}
	
	// 전체검색결과조회
	// Board Entity -> DTO
	public List<Board> voToDTO_findAll() {
		
		List<BoardEntity> boardEntity = repository.findAll();
		
		mapper.getConfiguration()
					.setMatchingStrategy(MatchingStrategies.STANDARD)
					.setFieldAccessLevel(AccessLevel.PRIVATE)
					.setFieldMatchingEnabled(true);
		
		List<Board> listDTO = 
				mapper.map(boardEntity, new TypeToken<List<Board>>() {}.getType());
		
		return listDTO;
		
	} // voToDTO_findAll()
	
	// 게시글 번호로 게시글 목록 상세 조회
	// Board Entity ->  DTO
	public Board voToDTO_findById(Integer BoardNo) {
		
		Optional<BoardEntity> boardEntity = repository.findById(BoardNo);
		
		mapper.getConfiguration()
					.setMatchingStrategy(MatchingStrategies.STANDARD)
					.setFieldAccessLevel(AccessLevel.PRIVATE)
					.setFieldMatchingEnabled(true);
		
		Object source = boardEntity;
		Class<Board> destinationType = Board.class;
		Board boardDTO = mapper.map(source, destinationType);
		
		return boardDTO;
		
	} // voToDTO_findById()
	
} // end class
