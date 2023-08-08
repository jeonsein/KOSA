import java.io.File;
import java.io.IOException;

public class FileTest {
	
	public static void main(String[] args) {
			
		File f;
		
		// D 드라이브에 대한 정보 획득 가능
		f = new File("D:\\");
		
		if(f.isDirectory()) { // if(f.isDirectory() == true) { 와 동일

			/*
			// Directory가 가지고 있는 하위 경로 및 파일명들 String형으로 반환
			String[] list = f.list();
			
			for(String sub : list) {
				System.out.println(sub);
			} // for
			*/
			
//			------------------------------
			
			/*
			File[] files = f.listFiles();
			
			for(File file : files) {
				String name = file.getName();
				
				if(file.isFile()) {	// D:\\ 경로 상의 하위 file들
					long fileSize = file.length(); // 파일 사이즈
					long lastModifiedTime = file.lastModified(); // 파일 최종 사용 시간
				
					SimpleDateFormat sdf = 
							new SimpleDateFormat("yyyy-MM-dd a hh:mm");
					
					String formatStr = 
							sdf.format(new Date(lastModifiedTime));
					
					System.out.println(name + "\t" + formatStr + "\t" + fileSize);
					System.out.println("---------------------------------");
				} else {			// D:\\ 경로 상의 하위 directories~
					System.out.println("[" + name + "]");
				} // if-else
				
			} // for
			*/
			
		} // if
		
//		------------------------------
		
		// # 폴더 만들기
		String folder = "D:\\attache";
		
		File file = new File(folder);
		
		// = if(file.exists() == false)
		if(!file.exists()) {	// folder가 존재 X
			boolean result = file.mkdir();
			
			if(result == true) {
				System.out.println("디렉토리명: " + folder + "가 생성되었습니다.");
			} else {
				System.out.println("디렉토리명: " + folder + "가 생성되지 않았습니다.");
			} // inner-if-else
			
		} else {			// folder가 존재 O
			System.out.println("디렉토리명: " + folder + "가 이미 존재합니다.");
		} // outter-if-else
		
		// # 파일 만들기
		String fileName = "sengna.txt";
		
		File file1 = new File(file, fileName);
		
		try {
			
			if(!file.exists()) {	// 파일 없을 때만 생성
				file1.createNewFile(); // 껍데기 파일 생성! (내용 X라 크기 0kb)
				// 내용 쓰려면 .write() 사용!
				
				System.out.println("파일명: " + fileName + "이 생성되었습니다.");
			} else {
				System.out.println("파일명: " + fileName + "이 이미 존재합니다.");
			} // if-else
			
		} catch (IOException e) {
			e.printStackTrace();
		} // try-catch
		
	} // end main
	
} // end class
