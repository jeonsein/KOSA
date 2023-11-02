package com.my.jpa.dao;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.my.jpa.dto.StoreDTO;
import com.my.jpa.entity.StoreEntity;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class StoreEntityTest {
	
	@Autowired
	StoreEntityRepository r;

	@Test
	void test1Insert() {
		
//		 StoreEntity s = new StoreEntity();
		StoreEntity s = StoreEntity
				.builder()
				.corNo("사업자번호1")
				.name("음식점1")
				.build();
		
		log.error("[ INSERT용 엔티티객체 ] 사업자 번호: {}, 상호명: {}" + s.getCorNo(), s.getName());
		
		// save() 호출 이후 문제 없으면 auto commit
		r.save(s);
		
	} // test1Insert()
	
	@Test
	void test2Update() {
		
		String corNo = "사업자번호1";
		
		Optional<StoreEntity> optS = r.findById(corNo);
		
		StoreEntity s = optS.get();
		s.modifyName("다른음식점");	// dirtyCheck
		
		r.save(s);
		
	} // test2Update()
	
	@Test
	void test3Delete() {
		String corNo = "사업자번호1";
		
		r.deleteById(corNo);
	} // test3Delete()
	
	// -------------------------------------
	
	/*
	@Test
	void test4DtoToVo() {	// DTO -> VO
		
		// DTO 세팅
		StoreDTO dto = StoreDTO
						.builder()
						.corNo("사업자번호2")
						.name("음식점2")
						.dt(new java.util.Date())	// java.util.Date (DTO이기 때문에)
						.build();
		
		// DTO를 Entity로
		StoreEntity entity = StoreEntity
						.builder()
						.corNo(dto.getCorNo())
						.name(dto.getName())
						.dt(new java.sql.Date(dto.getDt().getTime()))
						.build();
		
	} // test4DtoToVo()
	*/
	
	@Test
	void test5DtoToVo_ModelMapper() {
		
		/*
		StoreDTO dto = StoreDTO
						.builder()
						.corNo("사업자번호2")
						.name("음식점2")
						.dt(new java.util.Date())	// java.util.Date (DTO이기 때문에)
						.build();
		*/
		
		StoreDTO dto = new StoreDTO();
		
		dto.setCorNo("사업자번호2");
		dto.setName("음식점2");
		dto.setDt(new java.util.Date());
		
		ModelMapper mapper = new ModelMapper();
		// mapper 설정
		mapper.getConfiguration()
					.setMatchingStrategy(MatchingStrategies.STANDARD) // 얘는 기본 STANDARD임 (멤버변수의 순서가 달라도 매핑해줌)
					.setFieldAccessLevel(AccessLevel.PRIVATE)
					.setFieldMatchingEnabled(true);
		
		Object source = dto;	// 첫번째 인자(source object to map from)
		Class<StoreEntity> destinationType = StoreEntity.class; // 두번째 인자(변환될 자료형)
		
		// DTO -> VO
		// destinationType으로 풀 매핑된 객체를 반환(fully mapped instance of destinationType)
		StoreEntity entity = mapper.map(source, destinationType);
		
		log.error("[ entity ] corNo: {}, name: {}, dt: {}",
								entity.getCorNo(),
								entity.getName(),
								entity.getDt()
								);
		
	} // test5DtoToVo_ModelMapper()
	
	@Test
	void test6VoToDto_ModelMapper() {
		
		String corNo = "사업자번호1";
		
		Optional<StoreEntity> optS = r.findById(corNo);
		StoreEntity sEntity = optS.get();
		
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration()
					.setMatchingStrategy(MatchingStrategies.STANDARD)
					.setFieldAccessLevel(AccessLevel.PRIVATE)
					.setFieldMatchingEnabled(true);
		
		Object source = sEntity;
		
		Class<StoreDTO> destinationType = StoreDTO.class;
		
		StoreDTO dto = mapper.map(source, destinationType); // VO -> DTO
		/*
		StoreDTO dto = mapper
							.typeMap(null, null)	// 멤버변수가 다른 경우 이런 프로퍼티를 사용해주기!
							.addMapping(null, null);
		*/
		
		log.error("\n[ dto ] corNo: {}, name: {}, dt: {}",
							dto.getCorNo(),
							dto.getName(),
							dto.getDt()
							);
		
	} // test6VoToDto_ModelMapper()
	
} // end class
