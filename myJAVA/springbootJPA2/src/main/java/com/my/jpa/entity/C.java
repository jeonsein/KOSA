package com.my.jpa.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @NoArgsConstructor @AllArgsConstructor
//@ToString
@Builder

@Entity
@Table(name="c_tbl") 
/**
 * 작성자
 */
public class C {
	
	@Id
	@Column(name="c_id")
	private String cId;     // 아이디
	
	@Column(name="c_name")
	private String cName;	// 이름
	
	// mappedBy를 사용하려면 정확히 양방향에서 사용하고있는, Join Column하고 있는 자식쪽의 멤버변수를 줘야함
	@OneToMany //(mappedBy = "bC")
	@JoinColumn(name = "b_id")
	private List<B> bs;		// 게시글들 (회원 입장에서 작성한 게시글들임!)
	// bs 변수를 사용하고자 할 때 Lazy Loading을 유지해주면 됨 (FetchType.LAZY)
	
} // end class