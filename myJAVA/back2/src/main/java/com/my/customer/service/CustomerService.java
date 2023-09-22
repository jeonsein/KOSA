package com.my.customer.service;

import com.my.customer.dao.CustomerOracleMybatisRepository;
import com.my.customer.dao.CustomerRepository;
import com.my.customer.dto.Customer;
import com.my.exception.AddException;
import com.my.exception.FindException;

public class CustomerService {

	// DAO 호출
	private CustomerRepository repository;
	// static 변수로 미리 객체 생성
	private static CustomerService service = new CustomerService();
	private CustomerService() {
//		repository = new CustomerOracleRepository();
		repository = new CustomerOracleMybatisRepository();
	} // CustomerService
	public static CustomerService getInstance() {
		return service;
	} // getInstance()
	
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
	
//	-------------------------------------------------
	
	/**
	 * id에 해당하는 고객이 존재하지 않으면 FindException 발생한다.
	 * @param id
	 * @throws FindException
	 */
	public void idDupChk(String id) throws FindException {
		repository.selectById(id);
	} // idDupChk
	
//	-------------------------------------------------	
	
	/**
	 * Customer 객체로 회원을 가입.
	 * @param c
	 */
	public void signup(Customer c) throws AddException {
		repository.insert(c);
	} // signup()
	
} // end class
