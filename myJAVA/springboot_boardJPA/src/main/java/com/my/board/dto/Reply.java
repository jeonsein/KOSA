package com.my.board.dto;

import java.util.Date;

import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Setter @Getter @NoArgsConstructor @AllArgsConstructor 
@ToString
public class Reply {

	private Integer replyNo;
	private Integer replyBoardNo;
	private Integer replyParentNo;
	private String replyContent;
	private String replyId;
	private Date replyDt;
	private Integer level;
	
} // end class