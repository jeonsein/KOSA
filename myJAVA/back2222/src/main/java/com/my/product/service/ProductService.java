package com.my.product.service;

import java.util.List;

import com.my.exception.FindException;
import com.my.product.dao.ProductOracleMybatisRepository;
import com.my.product.dao.ProductOracleRepository;
import com.my.product.dao.ProductRepository;
import com.my.product.dto.Product;
import com.my.util.PageGroup;

public class ProductService {
	private ProductRepository repository;
	private static ProductService service = new ProductService();
	private ProductService() {
		repository = new ProductOracleMybatisRepository();//new ProductOracleRepository();
	}
	public static ProductService getInstance() {
		System.out.println("ProductService.getInstance()호출됨");
		return service;
	}
	public PageGroup<Product> findAll(int currentPage) throws FindException{
		if(currentPage < 1) {
			currentPage = 1;
		}
		
		int cntPerPage = 3; //한페이지당 보여줄 목록 수 
		                    
		//currentPage        //1  2  3  4
		int startRow;        //1  4  7  10
		int endRow;          //3  6  9  12 
		//TODO
		endRow = currentPage * cntPerPage;
		startRow = ( currentPage -1 ) *cntPerPage + 1;
		//return repository.selectAll(startRow, endRow);
		
		List<Product> list = repository.selectAll(startRow, endRow);		
		int totalCnt = repository.selectCount();		
		PageGroup<Product> pg = new PageGroup<>(list, currentPage, totalCnt);
		return pg;
	}
	
	public Product findByProdNo(String prodNo) throws FindException{
		return repository.selectByProdNo(prodNo);
	}
	
}
