package com.my.board.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.my.board.dto.Board;
import com.my.board.service.BoardService;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;

// RESTful 구성할 때는 쿼리 스트링 사용 X 요청 전달 데이터는 모두 JSON 형태로
@RestController
@RequestMapping("/board") // 모든 요청 방식에 관계없음
public class BoardController {

	@Autowired
	private BoardService service;

	// 게시글 전체보기용 메소드
	@GetMapping("/list") // = /board/list
	public List<Board> list() throws FindException {	
		return service.findAll();
	} // list()

	//	@GetMapping("1")
	@GetMapping("/{boardNo}")	// /board/1, /board/2
	public Board info(@PathVariable int boardNo) throws FindException {	// n번 번호에 대한 게시글 상세보기
		return service.findByBoardNo(boardNo);
	} // info()
	
	// 글 작성
	@PostMapping(value="", produces="application/json;charset=UTF-8")
	public ResponseEntity<?> write(@RequestBody Board board) throws AddException {
		try {
			service.write(board);
			
			return new ResponseEntity<>(HttpStatus.OK);
		} catch(Exception e) {
			e.getMessage();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} // try-catch
		
	} // write()
	
	// 글 수정
	@PutMapping(value="/{boardNo}", produces="application/json;charset=UTF-8")
	public ResponseEntity<?> modify(@PathVariable int boardNo,
									@RequestBody Board board) throws ModifyException {
		
		try {
			board.setBoardNo(boardNo);	// 전달 안되는 자료들은 setter 써서 셋팅해주기
			service.modify(board);
			
			return new ResponseEntity<>(HttpStatus.OK);
		} catch(Exception e) {
			e.getMessage();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} // try-catch
		
	} // modifY()
	
	// 글 삭제
	@DeleteMapping(value="/{boardNo}", produces="application/json;charset=UTF-8")
	public ResponseEntity<?> remove(@PathVariable int boardNo) throws RemoveException {
		
		try {
			service.remove(boardNo);			
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.getMessage();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} // try-catch
		
	} // remove()
	
} // end class
