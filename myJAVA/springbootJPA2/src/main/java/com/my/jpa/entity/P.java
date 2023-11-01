package com.my.jpa.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor

@Entity
@Table(name="p_tbl")
/**
 * 상품
 */
public class P {
	
	@Id
	@Column(name="p_no")
	private String pNo;// 상품번호

	@Column(name="p_name")
	private String pName; //상품명
	
	// 부모쪽: @OneToMany역할
	@OneToOne(mappedBy = "p",
			  cascade = CascadeType.ALL)
	private PD pd;

} // end class