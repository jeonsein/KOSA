package com.my.product.dto;


import java.util.Objects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
public class Product {

	private String prodNo;		// 상품 번호
	private String prodName;	// 상품 이름
	private int prodPrice;		// 상품 가격
	
//	---------------
	
	public Product(String prodNo, String prodName) {
		this(prodNo, prodName, 0);
	} // Product()
	
//	---------------
	
	public Product(String prodNo, String prodName, int prodPrice){
		
		if(prodNo.length() != 5) {
			System.out.println("상품 번호는 5자리이어야 합니다");
			
		} else {
			this.prodNo = prodNo;
			this.prodName = prodName;
			this.prodPrice = prodPrice;
		} // if-else
		
	} // Product()
	
//	---------------
	
	// public boolean equals(Object obj) {}
//	public boolean equals(Product p) {
//		// 문자열 비교 반환
//		return this.prodNo.equals(p.prodNo);
//	} // equals()
//	
//	@Override
//	public boolean equals(Object obj) {
//		
//		if(obj == null) {
//			return false;
//		} // if
//		
//		if(obj instanceof Product) {
//			Product product = (Product)obj;
//			
//			if(this.prodNo.equals(product.prodNo)) {
//				return true;
//			} // inner-if
//			
//		} // outter-if
//		return false;
//	} // equals()
	
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
	
	public void setProdNo(String prodNo) {
		if(prodNo.length() != 5) {
			System.out.println("상품번호는 5자리이어야합니다");
			return;
		}
		this.prodNo = prodNo;
	}
	
	public String getProdNo() {
//		return "prodNo의 암호화";				
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
	
} // end class


/*
package com.my.product.dto;

import java.util.Objects;

public class Product {
	private String prodNo;
	private String prodName;
	private int prodPrice;
	
	public Product(){}
	public Product(String prodNo, String prodName){
//		this.prodNo = prodNo;
//		this.prodName = prodName;		
		this(prodNo, prodName, 0);
	}
	public Product(String prodNo, String prodName, int prodPrice){
		if(prodNo.length() != 5) {
			System.out.println("상품번호는 5자리이어야합니다");
			
		}else {
			this.prodNo = prodNo;
			this.prodName = prodName;
			this.prodPrice = prodPrice;
		}
	}
	

	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		if(obj instanceof Product) {
			Product product = (Product)obj;
			if(this.prodNo.equals(product.prodNo)) {
				return true;
			}
		}
		return false;
	}
	public boolean equals(String no) {
		return this.prodNo.equals(no);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(prodNo);
	}
	
	public void setProdNo(String prodNo) {
		if(prodNo.length() != 5) {
			System.out.println("상품번호는 5자리이어야합니다");
			return;
		}
		this.prodNo = prodNo;
	}
	public String getProdNo() {
//		return "prodNo의 암호화";				
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

*/