package com.my.product.dao;

import com.my.exception.AddException;
import com.my.product.dto.Product;

public interface ProductDAOInterface {

	/**
	 * 상품의 번호, 이름, 가격을 저장소에 저장한다. 저장소가 꽉찬 경우(5개) "저장소가 꽉찼습니다"메시지가 출력된다.
	 * @param product 상품
	 */	
	// public abstract void insert(Product product);와 같음
	void insert(Product product) throws AddException;

	/**
	 * 상품 번호에 해당 상품을 저장소에서 검색하여 반환한다.
	 * @param prodNo 상품번호
	 * @return 상품객체, 단 번호에 해당 상품을 찾지 못하면 null을 반환한다.
	 */
	Product selectByProdNo(String no);
	
	/**
	 * 저장소에 저장된 상품들만 반환한다.
	 * @return 상품객체들, 단 저장소에 저장된 상품이 한개도 없으면 null을 반환한다.
	 */
	Product[] selectAll(); // Product 배열 타입으로 반환
	
	
	
} // end interface
