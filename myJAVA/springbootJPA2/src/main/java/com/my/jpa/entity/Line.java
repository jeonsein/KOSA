package com.my.jpa.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor

@Entity
@Table(name="line_tbl")
public class Line {
	
	@EmbeddedId
	private LineEmbedded id = new LineEmbedded();
	
	@ManyToOne
	@JoinColumn(name = "line_no") // 실제 테이블과도 연결
	@MapsId("lineNo") // 어떤 PK와 참조를 할지 결정 (복합키 클래스인 LineEmbedded와도 연결)
	private Info info;
	
	@ManyToOne
	@JoinColumn(name = "linep_no")
	@MapsId("pNo")
	private P lineP;
	
	@Column(name="line_q")
	private int lineQuantity;
	
} // end class