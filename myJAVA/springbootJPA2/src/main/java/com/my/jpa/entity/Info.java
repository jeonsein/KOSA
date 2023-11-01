package com.my.jpa.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Builder // 생성자 사용하면 코드 수정이 용이하지 않기때문에 Builder 사용!
@Entity
@Table(name="info_tbl") 	// 사용할 테이블명: info_tbl
@SequenceGenerator(	name = "info_seq_generator",
					sequenceName = "info_seq",
					initialValue = 1,
					allocationSize = 1)
public class Info {

	@Id
	@Column(name="info_no") // PK 역할용 컬럼명: info_no
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
							   generator="info_seq_generator")
	private Long infoNo;	// 주문기본번호: 일련번호용 객체 - info_seq 시퀀스 객체
	
	@Column(name="info_id") // 컬렴명: info_id
	private String infoId;
	
	@Column(name="info_dt") // 컬렴명: info_dt
	private  Date infoDt;
	
	@OneToMany (fetch = FetchType.EAGER,   // Info 검색되면 lines도 항상 같이 검색될 수 있도록
				mappedBy = "id.lineNo",
				cascade = CascadeType.ALL) // Info의 모든 영속성이 변화될 때 lines도 같이 따라갈 수 있도록 설정
	// @JoinColumn(name="line_no", nullable = false) // -> inner join으로 검색해옴
	private List<Line> lines;
	
} // end class
