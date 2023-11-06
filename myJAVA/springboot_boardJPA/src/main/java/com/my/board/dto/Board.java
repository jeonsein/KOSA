package com.my.board.dto;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Setter @Getter @NoArgsConstructor @AllArgsConstructor 
@ToString
public class Board {

	private Integer boardNo;
	
	private String boardTitle;
	
	@NotEmpty(message = "글 내용은 반드시 입력하세요")
	@Size(max = 10, message = "글 내용은 최대 10자리까지만 가능합니다")
	private String boardContent;
	
	@NotEmpty(message = "글 작성자 아이디는 반드시 입력하세요")
	private String boardId;
	
	private Date boardDt;
	
	// 게시글 하나에 답글은 여러개일 수 있음
	private List<Reply> replies; // 답글 목록
	
	private Integer replycnt; // 답글 수
	
} // end class