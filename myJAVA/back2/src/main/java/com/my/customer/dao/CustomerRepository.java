package com.my.customer.dao;

import com.my.customer.dto.Customer;
import com.my.exception.AddException;
import com.my.exception.FindException;

public interface CustomerRepository {

	/**
	 * 아이디에 해당하는 고객을 검색, 반환한다.
	 * @param id 아이디
	 * @return 고객 객체
	 * @throws FindException 아이디에 해당하는 고객이 없거나,
	 * 						  DB와의 연결 실패하면 예외가 발생한다.
	 */
	Customer selectById(String id) throws FindException;
	
	/**
	 * 고객을 추가한다.
	 * @param c 고객 객체
	 * @throws AddException 제약 조건에 위배되거나,
	 * 						DB와의 연결 실패하면 예외가 발생한다.
	 */
	void insert(Customer c) throws AddException;
	
} // end interface
