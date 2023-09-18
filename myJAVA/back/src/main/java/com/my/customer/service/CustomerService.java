package com.my.customer.service;

import com.my.customer.dao.CustomerOracleRepository;
import com.my.customer.dao.CustomerRepository;
import com.my.customer.dto.Customer;
import com.my.exception.FindException;

public class CustomerService {

	// DAO 호출
	private CustomerRepository repository;
	
	public CustomerService() {
		repository = new CustomerOracleRepository();
	} // CustomerService
	
//	------------------------------------------------------------
	
	/**
	 * 아이디와 비밀번호에 일치하는 고객정보가 존재한다면 반환값이 없음!
	 * 											존재하지 않으면 FindException 발생!
	 * @param id 아이디
	 * @param pwd 비밀번호
	 * @throws FindException 
	 */
	public void login(String id, String pwd) throws FindException {
		
		try {
			Customer c = repository.selectById(id); // selectById에서 예외 발생 시, catch절로!
			
			if(!c.getPwd().equals(pwd)) {
				throw new FindException(); // throw하면 catch절로 이동!
			} // if
		} catch(FindException e) {
			throw new FindException("로그인 실패");
		} // try-catch
		
	} // login()
	
} // end class
