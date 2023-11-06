package com.my.board.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

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
public class ReplyEntity {

	@Id
	@Column(name="reply_no")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
					generator="board_reply_seq_generator")
	private Integer replyNo;
	
	@ManyToOne
	@JoinColumn(name="reply_board_no")
	private BoardEntity replyBoardNo;

	@Column(name="reply_parent_no")
	private Integer replyParentNo;

	
	@Column(name="reply_content")
	private String replyContent;
	
	private String replyId;
	
	@Column(name="reply_dt")
	@ColumnDefault(value = "SYSDATE")
	private Date replyDt;
	
	@OneToMany
	@JoinColumn(name="reply_parent_no")
	private List<ReplyEntity> replies;
	
	@Transient
	private Integer level;

} // end class