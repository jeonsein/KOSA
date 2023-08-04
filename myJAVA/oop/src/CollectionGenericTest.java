import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import lombok.extern.log4j.Log4j2;


@Log4j2
public class CollectionGenericTest {
	
	public static void test(Collection<String> c) {
		
		// 요소 추가
		c.add("sengna");
		c.add("jjang");
		c.add("sengna");
		c.add("baegopa");
		
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
	
	public static void main(String[] args) {
		
		// ArrayList
		Collection<String> c = new ArrayList<String>(); // c에는 String type만 저장 가능
		log.info("# ArrayList");
		test(c);
		
		log.info("--------------------");
		
		// HashSet
		log.info("# HashSet");
		test(new HashSet<String>());
		
	} // end main
	
} // end class
