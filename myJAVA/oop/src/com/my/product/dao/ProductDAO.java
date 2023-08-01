package com.my.product.dao;

import com.my.product.dto.Product;

public class ProductDAO {
	
	private Product[] products = new Product[5]; // 상품 저장소
	
	private int totalCnt = 0; // 저장된 상품 수
	
	/**
	 * 상품의 번호, 이름, 가격을 저장소에 저장한다. 저장소가 꽉찬 경우(5개) "저장소가 꽉찼습니다"메시지가 출력된다.
	 * @param product 상품
	 */
	public void insert(Product product) {

		if(totalCnt == products.length) {
			
			System.out.println("저장소가 꽉찼습니다");
			
		} else {
			
		products[totalCnt] = product;
		totalCnt++;
		
		}
		
	} // insert
	
	/**
	 * 상품 번호에 해당 상품을 저장소에서 검색하여 반환한다.
	 * @param prodNo 상품번호
	 * @return 상품객체, 단 번호에 해당 상품을 찾지 못하면 null을 반환한다.
	 */
	public Product selectByProdNo(String prodNo) {
	
		// 강사님 코드
		for(int i = 0; i < totalCnt; i++) {
			
			Product savedP = products[i];  // 이미 저장된 상품 
			
			if(savedP.getProdNo().equals(prodNo)) {
				return savedP;
			} // if
			
		} // for
		
		return null;
	} // selectByNo
	
	/**
	 * 저장소에 저장된 상품들만 반환한다.
	 * @return 상품객체들, 단 저장소에 저장된 상품이 한개도 없으면 null을 반환한다.
	 */
	public Product[] selectAll() {

		// 강사님 코드
		if( totalCnt == 0 ) {
			return null;
		}
		
		Product[] all = new Product[totalCnt];
		
		for(int i = 0; i < totalCnt; i++) {
			all[i] = products[i];
		}
		
		return all;
		
	} // selectAll()

} // end class
