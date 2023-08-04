package com.my.product.dao;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;
import com.my.product.dto.Product;

import lombok.extern.log4j.Log4j2;


@Log4j2
public class ProductDAOArray implements ProductDAOInterface {
	
	private Product[] products = new Product[5]; // 상품 저장소
	
	private int totalCnt = 0; // 저장된 상품 수 (상품 수 0으로 자동 초기화)
	
	public void insert(Product product) throws AddException {

//		if(totalCnt == products.length) {
//			
//			System.out.println("저장소가 꽉 찼습니다");
//			
//			return;
			
//		} // if
		
		for(int i = 0; i < totalCnt; i++) {
			if (products[i].getProdNo().equals(product.getProdNo() )) {
//				
//				log.info("이미 존재하는 상품입니다!");
//				return;
				// 위 코드 대신에 예외 떠넘기기 🔽🔽
				throw new AddException("이미 존재하는 상품입니다!");
			
			} // if
		} // for
		
		try {
			
			products[totalCnt] = product;
			
			totalCnt++; // GOODCODE
			
			// catch로 가기 전에 ++하고 넘어감!
			// products[totalCnt++] = product; // BADCOCDE
			
		} catch (ArrayIndexOutOfBoundsException e) {
//			log.info("\n저장소가 꽉 찼습니다. \n저장된 상품 수: " + totalCnt);
		
			// 강제로 예외를 발생시킴
			throw new AddException("\n저장소가 꽉 찼습니다. \n저장된 상품 수: " + totalCnt);
		} // try-catch
		
	} // insert
	
	@Override
	public Product selectByProdNo(String prodNo) throws FindException {
	
		// 강사님 코드
		for(int i = 0; i < totalCnt; i++) {
			
			Product savedP = products[i];  // 이미 저장된 상품 
			
			if(savedP.getProdNo().equals(prodNo)) {
				return savedP;
			} // if
			
		} // for
		
//		return null;
		
		throw new FindException("상품이 없습니다.");
	} // selectByNo
	
	@Override
	public Product[] selectAll() throws FindException {

		// 강사님 코드
		if( totalCnt == 0 ) {
//			return null;
			throw new FindException("상품이 한개도 없습니다.");
		} // if
		
		Product[] all = new Product[totalCnt];
		
		for(int i = 0; i < totalCnt; i++) {
			all[i] = products[i];
		} // for
		
		return all;
		
	} // selectAll()
	
	@Override
	public void update(Product p) throws ModifyException {

		if ( p == null ) {
			throw new ModifyException("변경할 상품이 없습니다.");
		} // if

		for(int i=0; i<totalCnt; i++) {
			if(products[i].getProdNo().equals(p.getProdNo())) {

				if(p.getProdName() != null) {
					products[i].setProdName(p.getProdName());
				} // inner-if
				
				if(p.getProdPrice() != 0) {
					products[i].setProdPrice(p.getProdPrice());
				} // inner-if
				
			} // outter-if
		} // for
		
	} // update()

	@Override
	public void delete(String prodNo) throws RemoveException {
		
		if ( prodNo == null ) {
			throw new RemoveException("삭제할 상품이 없습니다.");
		} // if

		for(int i = 0; i < totalCnt; i++) {
			if (products[i].getProdNo().equals(prodNo)) {
				
				for(int j = i; j < totalCnt-1; j++) {
					products[j] = products[j+1];
				} // for
				
				products[totalCnt-1] = null;
				
				totalCnt--;
				
			} else if(totalCnt == 0) {
				System.out.println("삭제할 상품이 없습니다.");
			} //if-else
		} // for
		
	} // delete()

} // end class
