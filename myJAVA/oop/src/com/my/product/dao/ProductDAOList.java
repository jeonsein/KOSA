package com.my.product.dao;

import java.util.ArrayList;
import java.util.List;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;
import com.my.product.dto.Product;

public class ProductDAOList implements ProductDAOInterface {
	
	private List<Product> products;
	
	public ProductDAOList() {
		products = new ArrayList<>();
	} // ProductDAOList()
	
//	----------------------------
	
	@Override
	public void insert(Product product) throws AddException {
		
		for(Product p : products) {
			
			if(p.getProdNo().equals(product.getProdNo())) {
				throw new AddException("이미 존재하는 상품입니다.");
			} // if
			
		} // enhanced for
		
		products.add(product);
	} // insert()

//	----------------------------
	
	@Override
	public Product selectByProdNo(String no) throws FindException {
		
		for(Product p : products) {
			
			if(p.getProdNo().equals(no)) {
				return p;
			} // if
			
		} // enhanced for
		
		throw new FindException("상품이 없습니다.");
		
	} // selectByProdNo()

	
//	----------------------------
	
	@Override
	public Object selectAll() throws FindException {
		
		if(products.size() == 0) {
			throw new FindException("상품이 한 개도 없습니다.");
		} // if
		
		// 저장한 상품들만 존재함(null값 X , 비어있는 인덱스 X)
		return products;
		
	} // selectAll()

//	----------------------------
	
	@Override
	public void update(Product p) throws ModifyException {
		
		for(Product savedP : products) {
			
			if(savedP.getProdNo().equals(p.getProdNo())) {
				
				if(p.getProdName() != null) {
					savedP.setProdName(p.getProdName());
				} // inner-if
				
				if(p.getProdPrice() != 0) {
					savedP.setProdPrice(p.getProdPrice());
				} // inner-if
				
				return;
				
			} // outter-if
			
		} // for
		
		throw new ModifyException("해당 상품 번호에 해당하는 상품이 없습니다.");
		
	} // update()

//	----------------------------
	
	@Override
	public void delete(String prodNo) throws RemoveException {
		
		for(Product savedP : products) {
			
			if(savedP.getProdNo().equals(prodNo)) {
				
				products.remove(savedP);
				
				return;
				
			} // if
			
			throw new RemoveException("해당 상품 번호에 해당하는 상품이 없습니다.");
			
		} // for
		
	} // delete()
	
} // end class
