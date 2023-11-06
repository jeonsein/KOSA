package com.my.board.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import com.my.board.entity.ReplyEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Builder
@DynamicInsert
@Entity
@Table(name="board")
@SequenceGenerator(	name = "board_seq_generator",
					sequenceName = "board_seq",	// 실제 DB 시퀀스 이름
					initialValue = 1,
					allocationSize = 1)
public class BoardEntity {

	@Id
	@Column(name="board_no")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
	   							generator="board_seq_generator")
	private Integer boardNo;
	
	@Column(name="board_id")
	private String boardId;
	
	@Column(name="board_title")
	private String boardTitle;
	
	@Column(name="board_content")
	@NotEmpty(message = "글 내용은 반드시 입력하세요")
	@Size(max = 10, message = "글 내용은 최대 10자리까지만 가능합니다")
	private String boardContent;
	
	@Column(name="board_dt")
	@ColumnDefault(value = "SYSDATE")
	private Date boardDt;
	
	@Transient
	private Integer replycnt; // 답글 수
	
} // end class