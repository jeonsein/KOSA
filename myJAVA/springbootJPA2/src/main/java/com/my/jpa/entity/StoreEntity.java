package com.my.jpa.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor

// @Setter
@Getter

@Builder
@DynamicInsert	// INSERT될 때, Null이 아닌 컬럼들만 사용할 수 있음
@Entity
@Table(name="store_tbl")
public class StoreEntity {
	
	@Id
	@Column(name = "st_corno")
	private String corNo; //사업자번호
	
	@Column(name = "st_name")
	private String name; //상호
	
	@Column(name = "st_dt")
	@ColumnDefault(value = "SYSDATE") // 원하는 default 값 설정 가능! (import = hibernate)
	private Date dt;//등록일자
	
	/**
	 * 상호명을 변경한다
	 * @param name
	 */
	public void modifyName(String name) {
		this.name = name;
	} // modifyName()
	
} // end class