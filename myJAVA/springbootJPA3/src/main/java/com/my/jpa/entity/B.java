package com.my.jpa.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @NoArgsConstructor @AllArgsConstructor
@ToString

@Entity
@Table(name = "b_tbl") // 게시글
@SequenceGenerator(
		   name = 
		       "B_SEQ_GENERATOR", // 사용할 sequence 이름
		   sequenceName =
		         "b_seq", // 실제 데이터베이스 sequence 이름
		   initialValue = 1, allocationSize = 1
		)

public class B {
	@Id
	@Column(name = "b_no")
    @GeneratedValue(
       strategy = GenerationType.SEQUENCE,
       generator =
             "B_SEQ_GENERATOR" // 위의 sequence 이름
    )
	private Long bNo;
	
	//@Column어노테이션을 설정하지 않으면 멤버변수와 같은 이름의
	//컬럼이 자동 만들어진다.
	
	@ManyToOne()	//다대일
	@JoinColumn(name = "b_id", nullable = false) //외래키 지정 어노테이션
	private C bC; //게시글을 쓴 작성자정보
	
	//fetch = FetchType.EAGER대신
	//메서드에 @Transactional @Commit 추가할 수 있다
//	@OneToMany()
	@OneToMany(fetch = FetchType.EAGER, 
			cascade = CascadeType.REMOVE)
			//cascade = CascadeType.ALL)
	@JoinColumn(name = "rb_no")
	private List<R> rs;
}
