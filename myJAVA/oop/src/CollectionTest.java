import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import com.my.customer.dto.Customer;
import com.my.product.dto.Product;

import lombok.extern.log4j.Log4j2;


@Log4j2
public class CollectionTest {
	
	public static void test(Collection c) {
		
		// 요소 추가
		c.add("sengna");
		c.add(Integer.valueOf(1216));
		c.add(Float.valueOf(19.97F));
		c.add("sengna");
		c.add(Boolean.valueOf(false));
		// 중복된 객체
		c.add(new Product("C0001", "배고파", 1000));
		c.add(new Product("C0001", "배곺파", 2000));
		
		log.info("저장된 요소의 개수: " + c.size());
		log.info("c.toString(): " + c); // c.toString() 자동 호출
		
		// 요소 삭제
		c.remove("sengna");
		
		log.info(">>>>>> sengna 객체 삭제!");
		
//		----------------------
		
//		Iterator it = c.iterator();
//		
//		while(it.hasNext()) { // 방문할 요소가 있는가?		
//			Object e = it.next(); // 요소 방문 -> 요소들을 Object 타입으로 받아옴
//			
//			log.info("저장된 요소: " + e);
//		} // while
		
		// 🔽🔽 while문 enhanced for문으로 변경해보기!🔽🔽
		
		for(Object e : c) {
			log.info("저장된 요소: " + e);
		} // enhanced for
		
//		----------------------
		
	} // test()
	
	public static void test(Map m) {
		
		m.put("one", new Date());
		m.put("two", new String("sengna"));
		m.put("one", Integer.valueOf(1216));
		m.put("four", Boolean.valueOf(false));
		m.put("five", Float.valueOf(19.97F));
		
		// hashcode() override 해뒀기 때문에 동일 객체로 인식함
		m.put(new Product("c0001", "배고파", 1000), "밥주세요");
		m.put(new Product("c0001", "배곺파", 2000), "밥줘");
		
		// hashcode() override 안해서 동일 객체로 인식 X
		m.put(new Customer("id1", "p1"), "고객1");
		m.put(new Customer("id1", "p2"), "고객2");
		
		log.info("저장된 요소의 개수: " + m.size());
		log.info("저장된 요소: " + m);
		
		Product p = new Product();
		
		p.setProdNo("c0001");
		log.info("c0001의 상품키 값은: " + m.get(p)); // 밥줘
		
	} // test()
	
	public static void main(String[] args) {
		
		// ArrayList
		Collection c = new ArrayList();
		log.info("# ArrayList");
		test(c);
		
		log.info("-----------------------------------");
		
		// HashSet
		log.info("# HashSet");
		test(new HashSet());
		
		log.info("-----------------------------------");
		
		// HashMap
		log.info("# HashMap");
		test(new HashMap());
		
	} // end main
	
} // end class