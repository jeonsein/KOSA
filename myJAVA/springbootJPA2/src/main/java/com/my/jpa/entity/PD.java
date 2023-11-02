package com.my.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="pd_tbl")
public class PD {

	@Id
	@Column(name="pd_no")
	private String pdNo;	// 상품 번호
	
	@Column(name="pd_cal")
	private int cal;
	
	@Column(name="pd_na")
	private int na;
	
	// 자식쪽: @ManyToOne 역할
	@OneToOne
	@JoinColumn(name="pd_no")
	// @MapsId("pdNo")
	private P p; // 연관관계를 위한 멤버변수
	
} // end class
