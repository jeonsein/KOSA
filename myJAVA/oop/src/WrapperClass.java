import lombok.extern.log4j.Log4j2;

@Log4j2
public class WrapperClass {

	public static void main(String[] args) {
		
		int i = 100;
		// 기본형(int) -> 참조형(Integer) -> Object로 UpCasting
		Object obj = Integer.valueOf(i);
		
		log.info("Integer.valueOf(i) : " + obj);
		
		obj = i; // AutoBoxing
				 // Compile 시, Integer.valueOf(i);로 변환됨!
		
		log.info("obj = i : " + obj);
		
		int j;
		// Object를 DownCasting
		// UnBoxing : {Integer(참조형) -> int(기본형)}
		j = ((Integer)obj).intValue();
		log.info("j = ((Integer)obj).intValue() : " + j);
		j = (Integer)obj; // AutoUnBoxing됨!
						  // Compile 시, ((Integer)obj).valueOf();로 변환됨!
		log.info("j = (Integer)obj : " + j);
		
	} // end main
	
} // end class
