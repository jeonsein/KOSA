package com.my.board;

import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class SpringbootBoardApplicationTests {

//	Logger log = LoggerFactory.getILoggerFactory(getClass());
	
	@Autowired
	Environment env;
	
	// application-<profile>.properties 파일에 중복된 프로퍼티
	@Value("${my.info.name}")
	private String name;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	@DisplayName("Environment")
	void testEnv() {
		log.error("현재사용중인 profile={}", Arrays.toString(env.getActiveProfiles()));
	}

}
