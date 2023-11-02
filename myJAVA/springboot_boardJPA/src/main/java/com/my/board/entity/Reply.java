package com.my.board.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter @NoArgsConstructor @AllArgsConstructor

@DynamicInsert
@Entity
@Table(name="board_reply")
@SequenceGenerator(	name = "board_reply_seq_generator",
					sequenceName = "reply_seq",
					initialValue = 1,
					allocationSize = 1)
public class Reply {

	@Id
	@Column(name="reply_no")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
					generator="board_reply_seq_generator")
	private Integer replyNo;
	
	@ManyToOne
	@JoinColumn(name="reply_board_no")
	private Integer replyBoardNo;
	
//	@ManyToOne
//	@JoinColumn(name="reply_parent_no")
//	private Integer replyParentNo;
	@ManyToOne
	@JoinColumn(name="reply_parent_no")
	private Reply replyParentNo;
	
	@Column(name="reply_content")
	private String replyContent;
	
	@ManyToOne
	@JoinColumn(name="reply_id")
	private String replyId;
	
	@Column(name="reply_dt")
	@ColumnDefault(value = "SYSDATE")
	private Date replyDt;
	
	private Integer level;

} // end class

/*
1. CUSTOMER와 BOARD: 일대다
한 명의 고객은 여러 게시물을 작성 O
BOARD 테이블의 BOARD_ID 필드

2. CUSTOMER와 BOARD_REPLY: 일대다
한 명의 고객은 여러 댓글을 작성 O
BOARD_REPLY 테이블의 REPLY_ID 필드

3. BOARD와 BOARD_REPLY: 일대다
한 게시물에 여러 댓글 O
BOARD_REPLY 테이블의 REPLY_BOARD_NO 필드
*/