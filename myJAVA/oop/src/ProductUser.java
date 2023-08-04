import java.util.Scanner;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.product.dao.ProductDAOArray;
import com.my.product.dao.ProductDAOInterface;
import com.my.product.dto.Product;

import lombok.extern.log4j.Log4j2;


@Log4j2
public class ProductUser {
	
	Scanner sc = new Scanner(System.in);
	
//	ProductDAOArray dao = new ProductDAOArray();
	
//	ProductDAOInterface dao = new ProductDAOList();
	 ProductDAOInterface dao = new ProductDAOArray();
	
	public void findAll() throws FindException {
		
		System.out.println(">>상품 전체목록<<");
		
		Product[] all1 = dao.selectAll();
		
		if(all1 == null) {
			System.out.println("상품이 없습니다"); //출력됨
		} else {
			for(Product p: all1) {
				System.out.println(p.getProdNo() + ":" + p.getProdName() + ":" + p.getProdPrice());
			} // for
		} // if-else
		
		System.out.println("----------------");
		
	} // findAll()
	
	public void findByProdNo() throws FindException {

		System.out.println(">>상품 번호로 검색<<");
		System.out.println("상품번호를 입력하세요.");
		String prodNo = sc.nextLine();  // 밑에는 user.sc.nextLine이라고하고 여긴 sc.nextLine이라고 생략하는건가
										// 여기는 this.sc.nextLine이 생략 된것
										// 객체의 밖에서 sc를 user라는 참조 변수로 찾아가야하기 때문에 user.이 붙음
		Product p;
		
		p = dao.selectByProdNo(prodNo);
		
		if(p == null) {
			System.out.println("상품이 없습니다"); 
		} else {
			//출력됨
			System.out.println(p.getProdNo() +"번호 상품의 상품명:" + p.getProdName() + ", 가격:" + p.getProdPrice());
		}
		
	} // findByProdNo()
	
	public void add() {
		
		System.out.println(">>상품 추가<<");
//		Product p1 = new Product();

		System.out.println("상품번호를 입력하세요:");
		String prodNo = sc.nextLine();

		System.out.println("상품명을 입력하세요:");
		String prodName = sc.nextLine();

		System.out.println("상품가격을 입력하세요:");
		String prodPrice = sc.nextLine();

		
//		p1.prodNo = prodNo;
//		p1.prodName = prodName;
//		p1.prodPrice = prodPrice; // p1.prodPrice : int타입, prodPrice : String 타입
//								  // String -> int : Integer.parseInt(String);
//		p1.prodPrice = Integer.parseInt(prodPrice);
	
		Product p = new Product(
				prodNo, prodName, Integer.parseInt(prodPrice));
		
		try {
			dao.insert(p);
		} catch (AddException e) {
			e.printStackTrace();
			log.info("e.getMessage(): " + e.getMessage());
		} // try-catch
		
		System.out.println("prodNo : " + p.getProdNo());
		System.out.println("prodName : " + p.getProdName());
		System.out.println("prodPrice : " + p.getProdPrice());
		
	} // add()
	
	public void update() throws ModifyException {

		System.out.println("수정할 상품의 번호를 입력하세요");
		String prodNo = sc.nextLine();

		if ( prodNo == null ) { // prodNo가 저장된 상품과 같지 않다면..으로 하고싶은데..
			System.out.println("수정할 상품이 없습니다.");
		} else {
			System.out.println("상품의 이름을 입력하세요");
			String prodName = sc.nextLine();

			System.out.println("상품의 가격을 입력하세요 ");
			String prodPrice = sc.nextLine();

			Product p = new Product(prodNo, prodName, Integer.parseInt(prodPrice));

			dao.update(p);
		}

	}
	
	public void delete() {

		System.out.println("삭제할 상품의 상품번호를 입력하세요.");
		String prodNo = sc.nextLine();

		dao.delete();
	}
	
	
	public static void main(String[] args) throws AddException, FindException, ModifyException {	
		
		ProductUser user = new ProductUser(); // non-static 필드로 선언된 scanner를 사용하기위해 객체 먼저 생성
		
		while(true) {
		
			System.out.println
			("작업을 선택하세요 : 상품전체목록-1, 상품번호로검색-2, 상품추가-3, 상품수정-4, 종료-9");
			String opt = user.sc.nextLine(); 
			
			if(opt.equals("1")) {
				user.findAll();
			} else if(opt.equals("2")) {
				user.findByProdNo();
			} else if(opt.equals("3")) {
				user.add();
			} else if(opt.equals("4")) {
				user.update();
			} else if(opt.equals("9")) {
				break;
			} // if-else
		
		} // while
		
	} // main
	
} // end class