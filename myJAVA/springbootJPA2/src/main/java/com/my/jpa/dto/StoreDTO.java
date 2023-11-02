package com.my.jpa.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter @NoArgsConstructor @AllArgsConstructor
// @Builder
public class StoreDTO {

	private String corNo;
	
	private String name;
	
	// 이거 안쓰면 JSON 문자열로 formatting 안되어서 그냥 숫자로 보임
	@JsonFormat(pattern = "yy/MM/dd", timezone = "Asia/seoul")
	private Date dt; // java.util.Date
	
} // end class
