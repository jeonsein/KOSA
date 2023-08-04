package com.my.product.dao;

import com.my.exception.ModifyException;
import com.my.exception.RemoveException;
import com.my.product.dto.Product;

public class ProductDAOList implements ProductDAOInterface {

	@Override
	public void insert(Product product) {
		
	}

	@Override
	public Product selectByProdNo(String no) {
		return null;
	}

	@Override
	public Product[] selectAll() {
		return null;
	}

	@Override
	public void update(Product p) throws ModifyException {
		
	}

	@Override
	public void delete(String prodNo) throws RemoveException {
		
	}
	
} // end class
