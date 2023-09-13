package com.my.product.service;

import java.util.List;

import com.my.exception.FindException;
import com.my.product.dao.ProductOracleRepository;
import com.my.product.dao.ProductRepository;
import com.my.product.dto.PageGroup;
import com.my.product.dto.Product;

public class ProductService {
	
	// DAO 호출
	private ProductRepository repository;
	
	public ProductService() {
		repository =new ProductOracleRepository();
	} // ProductService(0
	
	// 만약 DTO 타입으로 리턴하지 못한다면, MAP 타입으로 만들어서 리턴하면 됨!
	public PageGroup<Product> findAll(int currentPage) throws FindException {
		
		if(currentPage < 1) {
			currentPage = 1;
		}
		
		int cntPerPage = 3; // 한 페이지당 보여줄 목록 수
		
		// currentPage		// 1  2  3  4
		int startRow = 0;	// 1  4  7  10
		int endRow = 0;		// 3  6  9  12
		
		// TODO 계산
		startRow = (currentPage - 1) * cntPerPage + 1;
		endRow = currentPage * cntPerPage;
		
//		return repository.selectAll(startRow, endRow);
		List<Product> list = repository.selectAll(startRow, endRow);
		
		int totalCnt = repository.selectCount(); // 카운트 함수로 전체 행수 얻어오기
		
		PageGroup<Product> pg = new PageGroup<>(list, currentPage, totalCnt);
		return pg;
		
	} // findAll()
	
	public Product findByProdNo(String prodNo) throws FindException {
		return repository.selectByProdNo(prodNo);
	} // findByProdNo()
	
} // end class

/* PageGourp의 생성자가 대신해줄 것임
int totalPage;	// 총 페이지 수 계산
// TODO

int cntPerPageGroup = 2; // 페이지 그룹에 보여줄 페이지 목록 수		

// currentPage		// 1  2  3  4  5
int startPage;		// 1  1  3  3  5
int endPage;		// 2  2  4  4  6
// TODO
*/
