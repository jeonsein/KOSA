package com.my.board.control;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.my.board.dto.Reply;
import com.my.board.service.BoardService;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;

// RESTful 구성할 때는 쿼리 스트링 사용 X 요청 전달 데이터는 모두 JSON 형태로
@RestController
@RequestMapping("/board") // 모든 요청 방식에 관계없음
public class BoardController {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());

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

	// -----------------------------------------------------

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
	//DELETE /board/1
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

	// -----------------------------------------------------

	// 답글 작성
	// POST /board/reply/1
	// POST /board/reply/1/9
	@PostMapping(value= {"reply/{boardNo}/{parentNo}", "reply/{boardNo}"}, produces="application/json;charset=UTF-8")
	public ResponseEntity<?> writeReply(@PathVariable int boardNo,
										@PathVariable(name = "parentNo") Optional<Integer> optParentNo,
										@RequestBody Reply reply) throws AddException {

		try {
			reply.setReplyBoardNo(boardNo);
			
			optParentNo.ifPresent(parentNo -> {
				reply.setReplyParentNo(parentNo);
			});
			
			if(!optParentNo.isPresent()) {	// parentNo가 없는 경우 -- 일반 답글 쓰기
				service.writeReply(reply);
			} else {						// parentNo가 있는 경우 -- 답글의 답글 쓰기
				Integer parentNo = optParentNo.get();
				service.writeReply(reply);
			} // if-else
			
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.getMessage();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} // try-catch

	} // writeReply()
	
	// 답글 수정
	// PUT     /board/reply/15
	@PutMapping(value="reply/{replyNo}")
	public ResponseEntity<?> modifyReply(@PathVariable int replyNo,
										 @RequestBody Reply reply) throws ModifyException {
		
		reply.setReplyNo(replyNo);
		
		try {
			service.modifyReply(reply);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.getMessage();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} // try-catch
		
	} // modifyReply()
	
	// 답글 삭제
	// DELETE  /board/reply/9
	@DeleteMapping(value="reply/{replyNo}")
	public ResponseEntity<?> removeReply(@PathVariable int replyNo) {
		
		try {
			service.removeReply(replyNo);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.getMessage();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} // try-catch
	} // removeReply()
	
} // end class

/*
	// POST /board/reply/1
	// POST /board/reply/1/9
	@PostMapping(value= {"reply/{boardNo}/{parentNo}", "reply/{boardNo}"})
	public ResponseEntity<?> writeReply(@PathVariable int boardNo,
										@PathVariable(name = "parentNo") Optional<Integer> optParentNo),
										@RequestBody Reply reply) throws AddException {

		System.out.println(reply);
		
		reply.setReplyBoardNo(boardNo);
		optParentNo.ifPresent(parentNo -> {
			reply.setReplyParentNo(parentNo);
		});
		
		service.writeReply(reply);

		return new ResponseEntity<>(HttpStatus.OK);

	} // writeReply()
 */