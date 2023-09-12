package com.my.product.dto;


import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product {

	private String prodNo;		// 상품 번호
	private String prodName;	// 상품 이름
	private int prodPrice;		// 상품 가격
//	private Integer prodPrice; // 참조 자료형으로 변경
	// -> 더 폭 넓게 사용이 가능
		
	public Product(String prodNo, String prodName) {
		this(prodNo, prodName, 0);
	} // Product()
	
	public boolean equals(String no) {
		return this.prodNo.equals(no);
	}

	@Override
	public int hashCode() {
		return Objects.hash(prodNo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
//		if (getClass() != obj.getClass())
//			return false;
		// 🔽 풀어서!
		Class currentClass = this.getClass();
		Class paramClass = obj.getClass();
		if(currentClass != paramClass) {
			return false;
		} // if
		
		Product other = (Product) obj;
		return Objects.equals(prodNo, other.prodNo);

	} // equals()

} // end class

/*
public Product(String prodNo, String prodName, int prodPrice){
	
	if(prodNo.length() != 5) {
		System.out.println("상품 번호는 5자리이어야 합니다");
		
	} else {
		this.prodNo = prodNo;
		this.prodName = prodName;
		this.prodPrice = prodPrice;
	} // if-else
	
} // Product()
*/