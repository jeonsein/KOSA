class Shape {  // 컴파일시 class Shape extends Object로 바뀜
	// 필드
	protected double area;

	public double getArea() {
		return area;
	}
	
	// 부모쪽에 method 생성 후, 필요한 경우에만 자식 class에서 method 재정의!
	void makeArea() {
		
	}

	public String toString() {		
		return "도형의 면적은 " + area + " 입니다.";
	}

} // end Shape class


class Circle extends Shape {
	
	private int radius;

	public Circle() {}; // 생성자
	
	public  Circle(int radius) {
		this.radius = radius;
	}
	
//	public Circle(double area) {
//		this.Area = area;
//	}
	
	public int getRadius() {
		return radius;
	}
	
	public void makeArea() {
		area = 3.14 * radius * radius;
	}
	
	public String toString() {		
		return "반지름이 " + radius + "인 Circle "+ super.toString();
	}
	
} // end Circle class


class Rectangle extends Shape {

	private int width;
	private int height;
	
	public Rectangle() {
		
	} // 생성자
	
	public Rectangle(int width, int height) {
		this.width = width;
		this.height = height;
	}

	// Rectangle용 method 재정의
	public void makeArea() {
		area = width * height;
	}
	
	public String toString() {		
		return "가로 " + width + " 세로 " + height + "인 Rectangle "+ super.toString();
	}
	
} // end Rectangle class


public class ShapeTest {	// public class ShapeTest extends Object{}

	public static void main(String[] args) {
		
//		Circle c = new Circle(5);			// 반지름이 5인 원객체
//		System.out.println(c.getRadius());	// 5
//		
//		c.makeArea(); 	// 원의 면적을 계산한다.
//		System.out.println(c.getArea());
//		
//		Rectangle r = new Rectangle(3, 4); // 가로3, 세로4인 사각형 객체
//		r.makeArea();	// 사각형의 면적을 계산한다
//		System.out.println(r.getArea()); // 면적값 출력 12.0
		
//		-------------------------------------
		
		Shape[] shapes = new Shape[5];
		
		// Upcasting
		shapes[0] = new Circle(5);
		shapes[1] = new Rectangle(3, 4);
		
		
		System.out.println(shapes[4]); // shapes[4].toStirng()가 자동 호출 null
		for(int i = 0; i < 2; i++) {
			shapes[i].makeArea();
			
			System.out.println("shapes[i].getArea(): " + shapes[i].getArea());
			
			// Shape클래스이름@Hashcode값
			System.out.println("shapes[i].toString(): " + shapes[i].toString());

			// Shape Class의 toString()
			System.out.println(shapes[i]); // shapes[i].toString()가 자동 호출
			
//			사용자 입장에서 불편함 🔽
//			if(shapes[i] instanceof Circle) {				
//				Circle c = (Circle)shapes[i]; // Downcasting
//				c.makeArea();
//				
//				System.out.println("shapes[i].getArea(): " + shapes[i].getArea());
//				
//			} else if (shapes[i] instanceof Rectangle) {
//				Rectangle r = (Rectangle)shapes[i];
//				r.makeArea();
//				
//				System.out.println("r.getArea(): " + r.getArea());
//			} // if-else
			
//			🔽 ClassCastException 🔽
//			Circle c = (Circle)shapes[i]; // Downcasting
//			c.makeArea();
//			
//			System.out.println("shapes[i].getArea(): " + shapes[i].getArea());

//			원래 상태대로 Downcasting 하려면,
//			부모 상태로 Upcasting된것만 Downcasting 가능, 
//			원래 타입으로만 Downcasting이 가능
			
		} // for

	} // main

} // end ShapeTest class
