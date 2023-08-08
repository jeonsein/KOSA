package com.my.product.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;
import com.my.product.dto.Product;


public class ProductDAOFile implements ProductDAOInterface {
	
	private String fileName = "D:\\products.txt";
	
	public ProductDAOFile() {
		createFile();
	} // contructor
	
	// 해당 클래스 내부에서만 사용할 것이기 때문에 private
	private void createFile() {
		File file = new File(fileName);
		
		if(!file.exists()) {	// file 없으면 file 생성!
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			} // try-catch
		} // if
	} // createFile()
	
	/* 셍나 코드
	@Override
	public void insert(Product product) throws AddException {
		
	    String directoryPath = "D:\\"; // 경로 지정
	    String fileName = "products.txt"; // 파일 이름 지정

	    // 파일에 저장
	    String contents =
	            product.getProdNo() + ":" + product.getProdName() + ":" + product.getProdPrice();

	    FileWriter fileWriter = null;
	    BufferedWriter bufferedWriter = null;
	    BufferedReader bufferedReader = null;

	    try {
	        File file = new File(directoryPath, fileName);

	        if (!file.exists()) {	// 파일 없으면 생성
	            file.createNewFile();
	        } // if

	        fileWriter = new FileWriter(file, true);
	        bufferedWriter = new BufferedWriter(fileWriter); // 한글 깨짐 때문에 사용
	        bufferedReader = new BufferedReader(new FileReader(file));

	        bufferedWriter.write(contents);
	        bufferedWriter.newLine();	// 개행

	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            // 자원 해제
	            if (bufferedWriter != null) {
	                bufferedWriter.close();
	            } // if
	            
	            if (fileWriter != null) {
	                fileWriter.close();
	            } // if
	        } catch (IOException e) {
	            e.printStackTrace();
	        } // try-catch
	    } // try-catch-finally
	} // insert()
	*/

	// 강사님
	@Override
	public void insert(Product product) throws AddException {
		
		try {
			selectByProdNo(product.getProdNo());
			throw new AddException("이미 존재하는 상품입니다.");
		} catch (FindException e) {
//			e.printStackTrace();
			FileWriter fw = null;
			
			try {
				fw = new FileWriter(fileName, true);
				
				String prodStr = 
						product.getProdNo() + ":" + product.getProdName() + ":" + product.getProdPrice() + "\n";
				
				fw.write(prodStr);
				
			} catch (IOException e1) {
//				e1.printStackTrace();
			} finally {
				if(fw != null) {
					try {
						fw.close();
					} catch (IOException e1) {
//						e1.printStackTrace();
					} // try-catch
				} // if
			} // try-catch-finally
			
		} // try-catch
		
	} // end insert()
	
	@Override
	public Product selectByProdNo(String no) throws FindException {
		Scanner sc = null;
		
		try {
			sc = new Scanner(new FileInputStream(fileName));
			
			while(sc.hasNextLine()) {
				String line = sc.nextLine(); // 파일의 내용 줄 단위로 읽어옴

				String[] arr = line.split(":");
				
				String prodNo = arr[0];
				
				if(prodNo.equals(no)) {
					return new Product(prodNo, arr[1], Integer.parseInt(arr[2]));
				} // if 
				
			} // while
			
			throw new FindException("입력하신 상품 번호에 해당하는 상품이 없습니다.");
			
		} catch (FileNotFoundException e) {
//			e.printStackTrace();
			createFile();
			throw new FindException("입력하신 상품 번호에 해당하는 상품이 없습니다.");
		} finally {
			if(sc != null) {
				sc.close();
			} // if
		} // try-catch-finally
		
	} // selectByProdNo()

	@Override
	public Object selectAll() throws FindException {
		
		List<Product> all = new ArrayList<>();
		
		Scanner sc = null;
		
		try {
			sc = new Scanner(new FileInputStream(fileName));

			// .hasNextLine()
			// -> true if and only if this scanner has another line of input
			while(sc.hasNextLine()) {
				String line = sc.nextLine(); // 파일의 내용 줄 단위로 읽어옴
				
				String[] arr = line.split(":");
				
				String prodNo = arr[0];
				String prodName = arr[1];
				int prodPrice = Integer.parseInt(arr[2]);
				
				Product p = new Product(prodNo, prodName, prodPrice);
				
				// list에 넣기!
				all.add(p);
				
			} // while
			
			// 상품이 없는 경우
			if(all.size() == 0) {
				throw new FindException("상품이 존재하지 않습니다.");
			} // if
			
			return all; // 상품 존재하는 경우
			
		} catch (FileNotFoundException e) {
//			e.printStackTrace();
			createFile();
			
			throw new FindException("상품이 존재하지 않습니다.");
		} finally {
			if(sc != null) {
				sc.close();
			} // if
		} // try-catch-finally
		
	} // selectAll()

	@Override
	public void update(Product p) throws ModifyException {
		
	}

	@Override
	public void delete(String prodNo) throws RemoveException {
		
	}
	
} // end class
