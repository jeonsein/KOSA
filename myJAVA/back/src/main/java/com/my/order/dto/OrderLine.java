package com.my.order.dto;

import com.my.product.dto.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderLine {

	private Integer orderLineNo;
//	private String orderProdNo;
	private Product orderP;
	private Integer orderQuantity;
	
} // end class
