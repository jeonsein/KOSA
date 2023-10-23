package com.my.customer.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.my.customer.dto.Customer;
import com.my.exception.AddException;
import com.my.exception.FindException;

@Repository(value = "customerDAO")
public class CustomerOracleMybatisRepository implements CustomerRepository {
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	@Override
	public Customer selectById(String id) throws FindException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			Customer c = 
					(Customer)session.selectOne("com.my.customer.CustomerMapper.selectById",  id);
			if(c != null) { 
				return c;
			} else {
				throw new FindException("고객이 없습니다");
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
		
	}
	
	@Override
	// 해당 프로퍼티의 목적은 언제 롤백을 할 것인지임! 
	// = AddException 형태의 예외 발생한 경우 그 즉시 롤백!
	@Transactional(rollbackFor = AddException.class) // 어노테이션으로 트랜잭션 관리!
	public void insert(Customer c) throws AddException {
		
		SqlSession session = null;
		
		try {
			session = sqlSessionFactory.openSession();
			
			session.insert("com.my.customer.CustomerMapper.insert", c);
//			session.commit();
		} catch(Exception e) {
//			session.rollback();
			throw new AddException(e.getMessage());
		} finally {
			if(session != null) {
				session.close();
			} // if
		} // try-catch-finally
		
	} // insert()
	
} // end class
