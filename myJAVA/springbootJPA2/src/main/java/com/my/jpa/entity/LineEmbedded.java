package com.my.jpa.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable // 
public class LineEmbedded implements Serializable { // 객체 직렬화 interface를 구현한 클래스
	
	@Column(name="line_no")
	private Long lineNo;
	
	@Column(name = "line_pno" )
	private String pNo;
	
} // end class