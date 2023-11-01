package com.my.jpa.entity;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @NoArgsConstructor @AllArgsConstructor
@ToString
@Entity //엔터티객체
@Table(name = "a_tbl")

/*For inserting, 
 *    should this entity use dynamic sql generation 
 *    where only non-null columns get referenced in the prepared sql statement?
    ex) 
    insert 
    into  a_tbl
        (a_2,      a_1) 
    values
        (?,        ?)
 */
@DynamicInsert

/*
 * For updating, 
 *     should this entity use dynamic sql generation 
 *     where only changed columns get referenced in theprepared sql statement? 
    ex)
    update
        a_tbl 
    set
        a_2=? 
    where
        a_1=?
 */
@DynamicUpdate
public class A {
	@Id
	@Column(length = 5)
	private String a_1;
	
	@Column(nullable=false, precision = 5, scale = 2)
	@ColumnDefault(value = "1")
	//private Integer a_2; // 자릿수 보장 못함
	private BigDecimal a_2;
	
//	@Column
//	@JsonFormat(pattern = "yy/MM/dd", timezone = "Asia/Seoul")
//	@CreationTimestamp
	@ColumnDefault(value = "SYSDATE")
	private Date a_3; // java.sql.Date를 사용함.
	
	private String a4;
	
	@Transient
	private String test;
	
} // end class
