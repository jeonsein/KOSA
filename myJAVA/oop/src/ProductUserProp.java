import java.io.IOException;
import java.util.List;
import java.util.Properties;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;
import com.my.product.dao.ProductDAOInterface;
import com.my.product.dto.Product;

public class ProductUserProp {
	java.util.Scanner sc = new java.util.Scanner(System.in); 
	//ProductDAOArray dao = new ProductDAOArray();
	
//	ProductDAOInterface dao = new ProductDAOArray();
//	ProductDAOInterface dao = new ProductDAOList();
	ProductDAOInterface dao;
	ProductUserProp(){
		//dao = new ProductDAOList();
		
		Properties env = new Properties();
		try {
			env.load(ProductUserProp.class.getResourceAsStream("my.properties"));
			String className = env.getProperty("product.dao");
//			Class clazz = Class.forName(className);
			
			Class clazz = Class.forName(className);//
			
			Object obj = clazz.getDeclaredConstructor().newInstance();
			dao = (ProductDAOInterface)obj;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SecurityException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
				
	}
	
	void findAll() {
		System.out.println(">>상품 전체목록<<");
		try {
			//Product[] all1 = dao.selectAll();
			Object obj = dao.selectAll();
			if(obj instanceof Product[]) {
				Product[] all1 = (Product[])obj;				
				for(int i = 0; i< all1.length; i++){
					Product p = all1[i];
					//System.out.println(p.prodNo + ":" + p.prodName + ":" + p.prodPrice);
					System.out.println(p.getProdNo() + ":" + p.getProdName() + ":" + p.getProdPrice());
				}
			}else if(obj instanceof List) {
				List<Product> list = (List)obj;
				for(int i=0; i<list.size(); i++) {
					Product p = list.get(i);
					System.out.println(p.getProdNo() + ":" + p.getProdName() + ":" + p.getProdPrice());
				}
			}

		}catch(FindException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("----------------");
	}
	void findByProdNo() {
		System.out.println(">>상품 번호로 검색<<");

		System.out.println("상품번호를 입력하세요");
		String value = sc.nextLine();
		Product p;
		try {
			p = dao.selectByProdNo(value);
			System.out.println(p.getProdNo() +"번호 상품의 상품명:" + p.getProdName() + ", 가격:" + p.getProdPrice());

			System.out.print(">>수정-1, 삭제-2, 뒤로가기-그외의값");
			String opt = sc.nextLine();
			if(opt.equals("1")) { //수정
				modify(p);
			}else if(opt.equals("2")) { //삭제
				remove(p);
			}
		}catch(FindException e) {
			System.out.println(e.getMessage());
		}
	}
	
	void modify(Product p) {
		Product modifiedP = new Product();
		modifiedP.setProdNo(p.getProdNo());
		
		System.out.println(">>" + p.getProdNo()+" 상품 수정<<");
		System.out.print( "변경할 상품명[" + p.getProdName()+ "], 변경안하려면 enter를 누르세요:");
		String prodName = sc.nextLine();
		if("".equals(prodName)) { //변경안함
			prodName = null;
		}
		modifiedP.setProdName(prodName);
		
		System.out.print("변경할 상품가격[" + p.getProdPrice() +"], 변경안하려면 enter를 누르세요:");
		String prodPrice = sc.nextLine();
		if("".equals(prodPrice)) { //변경안함
			prodPrice = "0";
		}
		modifiedP.setProdPrice(Integer.parseInt(prodPrice));
		try {
			dao.update(modifiedP);
			System.out.println("수정되었습니다");
		} catch (ModifyException e) {
			System.out.println(e.getMessage());
		}
	}
	
	void remove(Product p) {
		System.out.println(">>" + p.getProdNo()+" 상품 삭제<<");
		try {
			dao.delete(p.getProdNo());
		} catch (RemoveException e) {
			System.out.println(e.getMessage());
		}
	}
	void add() {
		System.out.println(">>상품추가<<");
		System.out.print("상품번호를 입력하세요:");
		String prodNo = sc.nextLine();
		System.out.print("상품명를 입력하세요:");
		String prodName = sc.nextLine();
		System.out.print("상품가격을 입력하세요:");
		String prodPrice = sc.nextLine();
		Product p = new Product(prodNo, prodName, Integer.parseInt(prodPrice));

		try {
			dao.insert(p);
		} catch (AddException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}		
	}
	public static void main(String[] args) {
		ProductUserProp user = new ProductUserProp();
		while(true) {
			System.out.println("작업을 선택하세요: 전체목록-1, 상품번호로 검색-2, 상품추가-3, 종료-9");
			String opt = user.sc.nextLine();
			
			if(opt.equals("1")){
				user.findAll();
			}else if(opt.equals("2")) {
				user.findByProdNo();
			}else if(opt.equals("3")) {
				user.add();
			}else if(opt.equals("9")) {
				break;
			}
		}
		
	}
}
