package com.my.jpa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:/db.properties")
public class MyApplicationContext {
	
	@Value("${spring.datasource.hikari.driver-class-name}")
	private String hikariDriverClassName;
	
	@Autowired
	Environment env;
	
	@Bean
	@ConfigurationProperties("spring.datasource.hikari")
	public HikariConfig hikariConfig() {
		HikariConfig config = new HikariConfig();
		return config;
	}
	
	@Bean 
	public HikariDataSource dataSourceHikari() {
		return new HikariDataSource(hikariConfig());
	} // dataSourceHikari()
	
	
	@Bean
	public DataSourceTransactionManager transactionManager() {
		DataSourceTransactionManager tx = new DataSourceTransactionManager();
		tx.setDataSource(dataSourceHikari());
		
		return tx;
	} // DataSourceTransactionManager()
	
} // end class
