package com.my.customer.dao;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.my.customer.dto.Customer;
import com.my.exception.AddException;
import com.my.exception.FindException;

public class CustomerOracleMybatisRepository implements CustomerRepository {
	private SqlSessionFactory sqlSessionFactory;
	public CustomerOracleMybatisRepository() {
		String resource = "com/my/sql/mybatis-config.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public Customer selectById(String id) throws FindException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			Customer c = 
					(Customer)session.selectOne("com.my.customer.CustomerMapper.selectById",  id);
			if(c != null) { 
				return c;
			}else {
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
	public void insert(Customer c) throws AddException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			session.insert("com.my.customer.CustomerMapper.insert", c);
			session.commit();
		}catch(Exception e) {
			session.rollback();
			throw new AddException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
		
	}
	
	
	
}
