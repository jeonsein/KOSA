package com.my.board.control;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.my.board.dto.Board;

@RestController
@Validated
public class ValidTestController {
	
	@GetMapping(value = "/a", produces = "text/html;charset=utf-8")
	public String a(String no, int sal) {
		
		if( no == null ) { 
			return "no값을 반드시 입력하세요";
		} else if (no.length() < 3) {
			return "no는 3자리 이상으로 입력하세요";
		}
		
		return "성공";
		
	} // a
	
	@GetMapping(value = "/b", produces = "text/html;charset=utf-8")
	public String b(@NotNull(message="no값을 반드시 입력하세요")
					        @Size(min = 3, message="no는 3자리 이상으로 입력하세요") String no,
			  		      int sal) {

		return "성공";
	} // b
	
	// 요청전달데이터로 Board 사용
	@GetMapping(value = "/c", produces = "text/html;charset=utf-8")
	// 요청전달데이터들이 command객체의 값으로 자동 세팅이됨(handlerAdapter가 수행)
	public String c(@Validated Board b) {
		return "성공";
	}
	
	// JSON 문자열 형태로 요청전달데이터가 전달된다
	@GetMapping(value = "/d", produces = "text/html;charset=utf-8")
	// 요청전달데이터들이 command객체의 값으로 자동 세팅이됨(handlerAdapter가 수행)
	public String d(@Validated @RequestBody Board b) {
		return "성공";
	}

} // end class