package com.my.board.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.my.board.dto.Board;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;


@SpringBootTest
class BoardRepositoryTest {

	@Autowired
	BoardOracleRepository repository;

	@Test
	@DisplayName("selectAll() 테스트 - 게시글 전체 검색 테스트")
	void testSelectAll() throws FindException {

		List<Board> list = repository.selectAll();

		int expectedSize = 3;
		assertEquals(expectedSize, list.size());
		// = Assertions.assertEquals(expectedSize, list.size());

	} // testSelectAll()

	// ---------------------

	@Test
	@DisplayName("testSelectByBoardNo() 테스트 - 게시글 번호 1번의 상세 조회")
	void testSelectByBoardNo() throws FindException {

		int boardNo = 1;
		String expectedTitle = "제목1";
		int expectedReplySize = 4;

		Board board = repository.selectByBoardNo(boardNo);

		Assertions.assertEquals(expectedTitle, board.getBoardTitle());
		Assertions.assertEquals(expectedReplySize, board.getReplies().size());

	} // testSelectByBoardNo()

	// ---------------------	

	@Test
	@DisplayName("testSelectByBoardNo1() 테스트 - 없는 게시글 번호로 상세조회")
	void testSelectByBoardNo1() throws FindException {

		int boardNo = 0;

		// BoardOracleRepository에서 null값을 반환함
		Board board = repository.selectByBoardNo(boardNo);

		Assertions.assertNull(board);

	} // testSelectByBoardNo1()

	// ---------------------

	@Test
	@DisplayName("testSelectByBoardNo2() 테스트 - 없는 게시글 번호로 상세조회 (Optional)")
	void testSelectByBoardNo2() throws FindException {

		int boardNo = 0;

		Board board = repository.selectByBoardNo(boardNo);
		Assertions.assertNull(board); // assertNull() = 인자로 사용한 값이 null일 것으로 단정 짓는다

		// FindException이 발생할 것이라고 단정 짓는다
		Assertions.assertThrows(FindException.class, () -> {
			repository.selectByBoardNoOptional(boardNo);
		});

	} // testSelectByBoardNo2()

	// ---------------------

	/*
	// 방법1
	@Test
	@DisplayName("글 작성 테스트")
	void write() throws AddException {

		Board board = new Board();

		board.setBoardTitle("셍나는");
		board.setBoardContent("널이제일싫어요^-^,,,");
		board.setBoardId("seng");		

		repository.insertBoard(board);

		try {
			List<Board> list = repository.selectAll();

			boolean flag = false;

			for(Board b1 : list) {
				if(b1.getBoardTitle().equals(board.getBoardTitle())) {
					flag = true;
					break;
				} // if
			} // for

			Assertions.assertTrue(flag);

		} catch (FindException e) {
			e.printStackTrace();
		} // try-catch

	} // write()
	 */

	// 방법2
	@Test
	@DisplayName("글 작성 테스트")
	void write() throws AddException {

		int beforeSize = 0;

		try {
			List<Board> list = repository.selectAll();
			beforeSize = list.size();
		} catch (Exception e) {
			e.printStackTrace();
		} // try-catch

		Board board = new Board();

		board.setBoardTitle("셍나는");
		board.setBoardContent("널이제일싫어요^-^,,,");
		board.setBoardId("seng");		

		repository.insertBoard(board);

		try {
			List<Board> list = repository.selectAll();
			Assertions.assertEquals(beforeSize + 1, list.size());

			boolean flag = false;

			for(Board b1 : list) {
				if(b1.getBoardTitle().equals(board.getBoardTitle())) {
					flag = true;
					break;
				} // if
			} // for

			Assertions.assertTrue(flag);

		} catch (FindException e) {
			e.printStackTrace();
		} // try-catch

		// int boardNo = 7;
		// String expectedBoardTitle = "다른거지롱";

		// 성공 테스트
		// Board b = repository.selectByBoardNo(boardNo);
		// assertEquals(board.getBoardTitle(), b.getBoardTitle());

		// 실패 테스트
		// Board b = repository.selectByBoardNo(boardNo);
		// assertEquals(expectedBoardTitle, b.getBoardTitle());

	} // write()

	/*
	// 방법3_셍나
	@Test
	@DisplayName("글 작성 테스트")
	void write() throws AddException {

		Board board = new Board();

		board.setBoardTitle("셍나는");
		board.setBoardContent("널이제일싫어요^-^,,,");
		board.setBoardId("seng");		

		repository.insertBoard(board);

		// int boardNo = 7;
		// String expectedBoardTitle = "다른거지롱";

		// 성공 테스트
		// Board b = repository.selectByBoardNo(boardNo);
		// assertEquals(board.getBoardTitle(), b.getBoardTitle());

		// 실패 테스트
		// Board b = repository.selectByBoardNo(boardNo);
		// assertEquals(expectedBoardTitle, b.getBoardTitle());

	} // write()
	 */

	// ---------------------

	@Test
	@DisplayName("글 수정 테스트")
	void modify() throws ModifyException, Exception {

		Board board = new Board();

		board.setBoardNo(7);
		board.setBoardContent("수정성공입니당짞짞짞");

		repository.updateBoard(board);

		int boardNo = 7;
		String expectedBoardContent = "졸려졸려졸려";

		// 성공 테스트
		// Board b = repository.selectByBoardNo(boardNo);
		// assertEquals(board.getBoardContent(), b.getBoardContent());

		// 실패 테스트
		// Board b = repository.selectByBoardNo(boardNo);
		// assertEquals(expectedBoardContent, b.getBoardContent());

	} // modify()

	// ---------------------

	@Test
	@DisplayName("글 삭제 테스트")
	void delete() throws RemoveException {

		Board board = new Board();

		int boardNo = 10;

		repository.deleteByBoardNo(boardNo);

		// 성공 테스트
		// assertDoesNotThrow() = 주어진 코드가 예외를 발생시키지 않는 것을 확인
		//		Assertions.assertDoesNotThrow(() -> {
		//			repository.deleteByBoardNo(boardNo);
		//		});

		// 실패 테스트
		// assertThrows() = 주어진 코드가 예외를 발생시키는 것을 확인
		Assertions.assertThrows(RemoveException.class, () -> {
			repository.deleteByBoardNo(boardNo);
		});

	} // delete()

} // end class