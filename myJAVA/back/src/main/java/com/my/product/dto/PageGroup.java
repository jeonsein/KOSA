package com.my.product.dto;

import java.util.List;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;


@Log4j2
@ToString
@Getter
public class PageGroup <T> {

	// 상수는 주로 대문자로 표기함
	public static final int CNT_PER_PAGE = 3;
	public static final int CNT_PER_PAGEGROUP = 2;
	
	private List<T> list; // 상품 목록들
	private int totalCnt; // 카운트 함수로 전체 행수 얻어온거 14
	private int currentPage; // 현재 페이지 1 2 3 4 5 6
	private int totalPage; // 총 페이지 4
	private int startPage; // 시작 페이지 1 1 3 3 5 5
	private int endPage;   // 끝 페이지 2 2 4 4 6 6
	
	// 생성자
	public PageGroup(List<T> list, int currentPage, int totalCnt) {
		
		this.list = list;
		this.currentPage = currentPage;
		this.totalCnt = totalCnt;
		
		// TODO 총 페이지 수 계산
		this.totalPage = (int) Math.ceil((float) totalCnt / CNT_PER_PAGE);
	    
		if(currentPage < totalPage) {
			// TODO 시작, 끝 페이지 수 계산
		    this.startPage = ((currentPage - 1) / CNT_PER_PAGEGROUP) * CNT_PER_PAGEGROUP + 1;
		    this.endPage = startPage + CNT_PER_PAGEGROUP - 1;
		    
			if(endPage > totalPage) {
				endPage = totalPage;
			} // inner-if
			
		} // outer-if
		
	} // constructor
	
//	------------------------------------------------------
	
	// Test용 main()
	public static void main(String[] args) {
		int cp = 5; // currentPage (현재 보려는 페이지)
		int tc = 14; // totalCnt (총 상품 수)
		
		PageGroup pg = new PageGroup(null, cp, tc);
		
		log.info("pg.getTotalPage(): " + pg.getTotalPage());
		log.info("pg.getStartPage(): " + pg.getStartPage());
		log.info("pg.getEndPage(): " + pg.getEndPage());
		
	} // end main
	
} // end class
