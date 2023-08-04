package com.my.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Product {
	
 	private String prodNo;		// 상품 번호
	
	private String prodName;	// 상품 이름
	
	private int prodPrice;		// 상품 가격
	
	public Product(String prodNo, String prodName) {
		this(prodNo, prodName, 0);
	} // Product()
	
	public void setProdNo(String prodNo) {
		
		if(prodNo.length() != 5) {
			System.out.println("상품 번호는 5자리이어야 합니다.");
			
			return;
		} // if
		
		this.prodNo = prodNo;
		
	} // setProdNo()

} // end class
