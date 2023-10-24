package control;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {config.MyApplicationContext.class,
								 config.MyMVCContext.class})
// WebApplicationContext 생성 
@WebAppConfiguration
class CustomerControllerTest {

	@Autowired
	WebApplicationContext ctx;
	
	MockMvc mockMvc; // 스프링 MVC를 테스트할 수 있는 모의(가짜)객체
	
	@Test
	void test() {
		mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
		assertNotNull(mockMvc);
	} // test()
	
	@Test
	@DisplayName("아이디 중복 체크 테스트")
	void testIddupchk() throws Exception {
		String id = "010";
		String url = "/iddupchk?id=" + id;
		
		mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
		
		MockHttpServletRequestBuilder msrb;
		msrb = MockMvcRequestBuilders.get(url); // get방식으로 요청
		
		ResultActions actions = mockMvc.perform(msrb); // 응답
		actions.andExpect(MockMvcResultMatchers.status().isOk());// 응답상태코드가 200으로 예상
	
	} // testIddupchk()

} // end class
