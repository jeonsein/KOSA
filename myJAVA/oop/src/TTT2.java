import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class TTT2 {

	public static void main(String[] args) {
		// Set 컬렉션 생성
		Set<String> set = new HashSet<>();
		set.add("1");
		set.add("2");
		set.add("3");

		// Stream을 이용한 요소 반복 처리
		Stream<String> stream = set.stream(); // 스트림 획득
		stream.forEach( log::info );
		// result
		// 1
		// 2
		// 3

		// .forEach(log::info); // 내부 순회!
	}
	
}
