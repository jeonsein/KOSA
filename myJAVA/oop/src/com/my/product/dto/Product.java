package com.my.product.dto;

public class Product {
	
 	private String prodNo;		// 상품 번호
	
	private String prodName;	// 상품 이름
	
	private int prodPrice;		// 상품 가격
	
	
	
	public Product () {};
	
	public Product(String prodNo, String prodName) {
		this(prodNo, prodName, 0);
	}
	
	public Product(String prodNo, String prodName, int prodPrice) {
		this.prodNo = prodNo;
		this.prodName = prodName;
		this.prodPrice = prodPrice;
	};
	
	
	public void setProdNo(String prodNo) {
		
		if(prodNo.length() != 5) {
			System.out.println("상품 번호는 5자리이어야 합니다.");
			return;
		}	
		this.prodNo = prodNo;			
	}
	
	public String getProdNo() {
		return prodNo;
	}
	
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	
	public String getProdName() {
		return prodName;
	}
	
	public void setProdPrice(int prodPrice) {
		this.prodPrice = prodPrice;
	}

	public int getProdPrice() {
		return prodPrice;
	}

}
