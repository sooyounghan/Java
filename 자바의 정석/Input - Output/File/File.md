-----
### File
-----
1. 자바에서는 File 클래스를 통해 파일과 디렉토리를 다룰 수 있도록 제공
  - 따라서, File 인스턴스는 '파일 또는 디렉토리'

2. File의 생성자와 경로와 관련된 메서드
<div align="center">
<img src="https://github.com/sooyounghan/Data-Base/assets/34672301/e15e5195-e25b-4c7d-a3bd-0e2d855479b3">
</div>

3. 경로와 관련된 File의 멤버변수
<div align="center">
<img src="https://github.com/sooyounghan/Data-Base/assets/34672301/16d102b5-251f-4cbb-9d0e-fbd427982296">
</div>

4. 파일의 경로(Path)와 디렉토리나 파일의 이름을 구분하는데 사용되는 구분자가 OS마다 다를 수 있기 때문에, OS 독립적으로 프로그램을 작성하기 위해서는 멤버변수를 알아야 함
  - 다른 OS 구분자를 사용하면 오류 발생 가능
```java
import java.io.*;

class FileEx1 {
	public static void main(String[] args) throws IOException
	{
		File f = new File("c:\\jdk1.8\\work\\ch15\\FileEx1.java");
		String fileName = f.getName();
		int pos = fileName.lastIndexOf(".");

		System.out.println("경로를 제외한 파일 이름 - " + f.getName());
		System.out.println("확장자를 제외한 파일 이름 - " + fileName.substring(0,pos));
		System.out.println("확장자 - " + fileName.substring(pos+1));

		System.out.println("경로를 포함한 파일 이름 - "		+ f.getPath());
		System.out.println("파일의 절대 경로        - "	+ f.getAbsolutePath());
		System.out.println("파일의 정규 경로        - "	+ f.getCanonicalPath());
		System.out.println("파일에 속해 있는 디렉토리 - "	+ f.getParent());
		System.out.println();
		System.out.println("File.pathSeparator - "		+ File.pathSeparator);
		System.out.println("File.pathSeparatorChar - "  + File.pathSeparatorChar);
		System.out.println("File.separator - "		+ File.separator);
		System.out.println("File.separatorChar - "	+ File.separatorChar);
		System.out.println();
		System.out.println("user.dir=" + System.getProperty("user.dir"));
		System.out.println("sun.boot.class.path=" + System.getProperty("sun.boot.class.path"));
	}
}
```
<div align="Center">
<img src="https://github.com/sooyounghan/Data-Base/assets/34672301/ebff01de-9268-4b32-9c3e-2701d6c3eaee">
</div>

-----
### 경로와 디렉토리
-----
1. 절대경로 (Absolute Path)는 파일 시스템의 루트(Root)로부터 시작하는 파일의 전체 경로를 의미
   - OS에 따라 다르지만, 하나의 파일에 대해 둘 이상의 절대 경로가 존재할 수 있음
   - 현재 디렉토리를 의미하는 '.'와 같은 기호나 링크가 포함하고 있는 경우에 이에 해당

2. 정규경로 (Canonical Path)는 기호나 링크 등을 포함하지 않는 유일한 경로를 의미

```
A. C:\jdk1.8\work\ch15\FileEx1.java
B. 또 다른 절대경로 : C:\jdk1.8\work\ch15\.\FileEx1.java
C. 정규경로 : A. C:\jdk1.8\work\ch15\FileEx1.java (오직 하나)
```

3. 시스템 속성 중 user.dir의 값을 확인하면 현재 프로그램이 실행 중인 디렉토리를 알 수 있음
4. 또한, OS 시스템 변수로 설정하는 classpath 외에 sun.boot.class.path라는 시스템 속성에 기본적인 classpath가 있어서 기본적인 경로들은 이미 설정되어 있음

5. 예시) File f = new File("c:\\jdk1.8\\work\\ch15\\FileEx1.java");
   - 다른 생성자를 이용해 File 인스턴스 생성 가능
```java
File f = new File("c:\\jdk1.8\\work\\ch15", "FileEx1.java");
또는
File dir = new File("c:\\jdk1.8\\work\\ch15");
File f = new File(dir, "FileEx1.java");
```
  - 💡 File 인스턴스를 생성했다고 해서, 파일이나 디렉토리가 생성되는 것이 아님
  - 💡 파일명이나 디렉토리명으로 지정된 문자열이 유효하지 않더라도 컴파일 에러나 예외를 발생시키지 않음

6. 새로운 파일을 생성하기 위해 File 인스턴스를 생성한 다음, 출력 스트림을 생성하거나 createNewFile()를 호출해야함
```java
A. 이미 존재하는 파일 참조
: File f = new File("c:\\jdk1.8\\work\\ch15", "FileEx1.java");

B. 기존에 없는 파일을 새로 생성
: File f = new File("c:\\jdk1.8\\work\\ch15", "FileEx1.java");
  f.createNewFile();
```

-----
### File의 메서드
-----
<div align="center">
<img src="https://github.com/sooyounghan/Data-Base/assets/34672301/2513731d-1ed9-4405-bb3e-ea205b008751">
<img src="https://github.com/sooyounghan/Data-Base/assets/34672301/a7681ac4-1eb6-4c83-a3ae-2f4c566bd52f">
</div>

-----
### File 예제
-----
1. 지정한 디렉토리(폴더)에 포함된 파일과 디렉토리 목록을 보여주는 예제
```java
import java.io.*;

class FileEx2 {
	public static void main(String[] args) {
		if(args.length != 1) {
			System.out.println("USAGE : java FileEx2 DIRECTORY");
			System.exit(0);
		}

		File f = new File(args[0]);

		if(!f.exists() || !f.isDirectory()) {
			System.out.println("유효하지 않은 디렉토리입니다..");
			System.exit(0);
		} 

		File[] files = f.listFiles();

		for(int i=0; i < files.length; i++) {
			String fileName = files[i].getName();
			System.out.println(files[i].isDirectory() ? "["+fileName+"]" : fileName);
		}
	} // main
}
```

2. 현재 디렉토리에 속한 파일과 디렉토리의 이름과 크기 등 상세정보를 보여주는 예제
```java
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

class FileEx4 {
	public static void main(String[] args){
		String currDir = System.getProperty("user.dir");
		File dir = new File(currDir);

		File[] files = dir.listFiles();

		for(int i=0; i < files.length; i++) {
			File f = files[i];
			String name = f.getName();
			SimpleDateFormat df =new SimpleDateFormat("yyyy-MM-dd HH:mma");
			String attribute = "";
			String size = "";

			if(files[i].isDirectory()) {
				attribute = "DIR";
			} else {
				size = f.length() + "";
				attribute  = f.canRead()  ? "R" : " ";	
				attribute += f.canWrite() ? "W" : " ";
				attribute += f.isHidden() ? "H" : " ";	
			}

			System.out.printf("%s %3s %6s %s%n", df.format(new Date(f.lastModified())), attribute, size, name );
		}
	}
} // end of class
```

3. FilenameFilter를 구현해서 String[] list(FilenameFilter filter)와 함께 사용해서 특정 조건에 맞는 파일 목록을 얻는 방법을 보여주는 예제
   - FilenameFilter 인터페이스 : accept 메서드 하나만 선언되어있으며, 이를 구현하면 됨
```java
public interface FilenameFilter {
	boolean accept(File dir, String name);
}
```
```java
import java.io.*;

class FileEx7 {
	public static void main(String[] args) throws Exception {
		if(args.length != 1) {
			System.out.println("USAGE : java FileEx7 pattern");
			System.exit(0);
		}

		String currDir = System.getProperty("user.dir");

		File dir = new File(currDir);
		final String pattern = args[0];

             // String[] list (FilenameFilter filter)
		String[] files = dir.list(new FilenameFilter() { 
			public boolean accept(File dir, String name) {
				return name.indexOf(pattern) != -1;
			}
		});

		for(int i=0; i < files.length; i++) {
			System.out.println(files[i]);
		}
	} // end of main
} // end of class
```

4. renameTo(File f)를 이용해 파일의 이름을 바꾸는 예제
```java
import java.io.*; 

class FileEx9 { 
	public static void main(String[] args) { 
		if (args.length != 1) { 
			System.out.println("Usage: java FileEx9 DIRECTORY"); 
			System.exit(0); 
		} 

		File dir = new File(args[0]); 

		if(!dir.exists() || !dir.isDirectory()) {
			System.out.println("유효하지 않은 디렉토리입니다.");
			System.exit(0);
		} 

		File[] list = dir.listFiles(); 

		for (int i = 0; i < list.length; i++) { 
			String fileName = list[i].getName(); 
			// 파일명
			String newFileName = "0000" + fileName; 
			newFileName = newFileName.substring(newFileName.length() - 7); 
			list[i].renameTo(new File(dir, newFileName)); 
		} 
	} // end of main 
} // end of FileEx9 class  
```
   - 파일명이 숫자로 되어있을 때, 앞에 '0000'을 붙인 다음 substring()으로 이름의 길이를 맞춰 줌
   - 파일이름이 1.jpg, 2.jpg와 같이 숫자로 되어 있으면, 파일 이름으로 정렬하면 1.jpg 다음에 2.jpg가 아닌 11.jpg가 오게 되므로 이를 해결하기 위해 설정

5. 지정한 파일을 지정한 크기로 잘라서 여러 개의 파일로 만드는 에제와 나눈 파일을 다시 합치는 예제
```java
import java.io.*;

class FileSplit {
	public static void main(String[] args) {
		if (args.length < 2) {
			System.out.println("USAGE : java FileSplit filename SIZE_KB");
			System.exit(0); // 프로그램 종료
		}

		final int VOLUME = Integer.parseInt(args[1]) * 1000;

		try {
			String filename = args[0];

			FileInputStream     fis = new FileInputStream(filename);
			BufferedInputStream bis = new BufferedInputStream(fis);

			FileOutputStream     fos = null;
			BufferedOutputStream bos = null;

			int data = 0;
			int i = 0;
			int number = 0;

			while((data = bis.read()) != -1) {
				if (i%VOLUME==0) {
					if (i!=0) {
						bos.close();
					}

					fos = new FileOutputStream(filename + "_."+ ++number);
					bos = new BufferedOutputStream(fos);
				}
				bos.write(data);
				i++;
			}

			bis.close();
			bos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	// end of try-catch
	} // end of main
} // end of class FileSplit
```
```java
import java.io.*;

class FileMerge {
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("USAGE : java FileMerge filename");
			System.exit(0); // 프로그램 종료
		}

		String mergeFilename = args[0];

		try {

			File tempFile = File.createTempFile("~mergetemp",".tmp");
			tempFile.deleteOnExit();

			FileOutputStream     fos = new FileOutputStream(tempFile);
			BufferedOutputStream bos = new BufferedOutputStream(fos);

			BufferedInputStream bis = null;

			int number = 1;

			File f = new File(mergeFilename + "_." + number);

			while(f.exists()) {
				f.setReadOnly();	// 작업 중 파일의 내용이 변경되지 않도록 함
				bis = new BufferedInputStream(new FileInputStream(f));

				int data = 0;
				while((data = bis.read()) != -1) {
					bos.write(data);
				}

				bis.close();

				f = new File(mergeFilename + "_." + ++number);
			} // while

			bos.close();

			File oldFile = new File(mergeFilename);
            
			if(oldFile.exists()) oldFile.delete();
			
			tempFile.renameTo(oldFile);
		} catch (IOException e) {}
	} // main
} // class
```
   - 작업할 임시 파일을 새로 만들고 프로그램 종료 시 자동 삭제하도록 설정
   - 프로그램 실행 도중 사용자에 의해 중단되거나 했을 때, 파일이 합쳐지는 과정에서 생성된 불완전한 파일이 생성되는 것을 방지하기 위해 임시 파일 사용
```java
File tempFile = File.createTempFile("~mergetemp",".tmp");
tempFile.deleteOnExit();
```

   - 파일을 합치는 작업이 온전히 마치고 나면, 기존 파일을 삭제하고 임시 파일의 이름을 기존 파일의 이름으로 변경
   - 임시 파일이 생성되는 곳은 createTempFile 메서드에서 지정 가능하나, 지정하지 않으면 시스템 속성인 'java.io.tmpdir'에 지정된 디렉토리가 됨
   - 따라서, System.getProperty("java.io.tmpdir")를 출력해보면, 임시 디렉토리의 위치 확인 가능
```java
File oldFile = new File(mergeFilename);
if(oldFile.exists()) oldFile.delete();
tempFile.renameTo(oldFile);
```
   - 작업을 마치고 나면, 기존 파일은 삭제하고 임시 파일의 이름을 기존 파일의 이름으로 변경
