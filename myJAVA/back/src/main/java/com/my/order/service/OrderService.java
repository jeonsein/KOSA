package com.my.order.service;

import java.util.List;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.order.dao.OrderOracleRepository;
import com.my.order.dao.OrderRepository;
import com.my.order.dto.OrderInfo;

public class OrderService {

	private OrderRepository repository;
	
	public OrderService() {
		repository = new OrderOracleRepository();
	} // OrderService
	
//	------------------------------------------------------------------
	
	/**
	 * 주문을 추가한다.
	 * @param info
	 * @throws AddException
	 */
	public void add(OrderInfo info) throws AddException {
		repository.insert(info);
	} // add()
	
//	------------------------------------------------------------------

	/**
	 * 로그인된 아이디가 주문한 목록을 조회한다
	 * @param loginedId
	 * @return
	 * @throws FindException
	 */
	public List<OrderInfo> findById(String loginedId) throws FindException{
		return repository.selectById(loginedId);
	} // findById()
	
} // end class
