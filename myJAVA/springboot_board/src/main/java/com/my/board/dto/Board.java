package com.my.board.dto;

import java.util.Date;
import java.util.List;

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
	private String boardContent;
	private String boardId;
	private Date boardDt;
	
	// 게시글 하나에 답글은 여러개일 수 있음
	private List<Reply> replies; // 답글 목록
	private Integer replycnt; // 답글 수
	
} // end class
