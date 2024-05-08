-----
### finally 블록
-----
1. 예외 발생 여부에 상관없이 실행되어야할 코드를 포함시킬 목적으로 사용
2. try-catch문 끝에 선택적으로 사용 가능하며, try-catch-finally 순서
```java
try {
    // 예외가 발생할 가능성이 있는 문장
} catch(Exception1 e1) {
    // 예외처리 위한 문장
} finally {
    // 예외 발생 여부에 관계 없이 항상 수행되어야할 문장
    // finally 블럭은 try-catch문 맨 마지막에 위치해야함
}
```

3. 예외 발생 경우, try → catch → finally 순으로 실행, 예외가 발생하지 않은 경우 try → finally 순으로 실행
```java
class FinallyTest {
	public static void main(String args[]) {
		try {
			startInstall();		// 프로그램 설치에 필요한 준비
			copyFiles();		// 파일들을 복사
			deleteTempFiles();	// 프로그램 설치에 사용된 임시파일들 삭제
		} catch (Exception e) {
			e.printStackTrace();
		    	deleteTempFiles();   // 프로그램 설치에 상요된 임시파일들 삭제
		} // try-catch
	} // main

   static void startInstall() { 
        /* 프로그램 설치에 필요한 준비를 하는 코드 작성 */ 
   }
   static void copyFiles() { /* 파일들을 복사하는 코드 작성 */ }
   static void deleteTempFiles() { /* 임시파일들을 삭제하는 코드 작성 */ }
}
```
  - 프로그램 설치를 위한 준비한 후, 파일들을 복사하고, 설치가 완료되면 프로그램을 설치하는데 사용된 임시파일 삭제하는 순서
  - 프로그램 설치과정 중 예외가 발생하더라도, 설치에 사용된 임시파일들을 삭제하도록 catch블럭에 deleteTempFiles() 메서드 추가
  - 결국, try 블럭 문장 수행 동안 (프로그램 설치 과정), 예외 발생 여부 관계없이 deleteTempFiles()메서드는 반드시 실행되어야함

```java
class FinallyTest2 {
	public static void main(String args[]) {
		try {
			startInstall();		// 프로그램 설치에 필요한 준비
			copyFiles();		// 파일들을 복사
		} catch (Exception e)	{
			e.printStackTrace();
		} finally {
			deleteTempFiles();	// 프로그램 설치에 사용된 임시파일들 삭제
		} // try-catch
	}	// main

	static void startInstall() { 
		/* 프로그램 설치에 필요한 준비를 하는 코드 작성 */ 
  }
  static void copyFiles() { /* 파일들을 복사하는 코드 작성 */ }
  static void deleteTempFiles() { /* 임시파일들을 삭제하는 코드 작성 */ }
}
```

-----
### finally 블록 예제
-----
```java
class FinallyTest3 {
	public static void main(String args[]) {
  		// method1()은 static메서드 이므로 인스턴스 생성없이 직접 호출 가능
  		FinallyTest3.method1();		
      System.out.println("method1()의 수행을 마치고 main로 돌아왔습니다.");
	}	// main

	static void method1() {
  		try {
  			System.out.println("method1()이 호출되었습니다.");
  			return;		// 현재 실행중인 메서드 종료
  		}	catch (Exception e)	{
  			e.printStackTrace();
  		} finally {
  			System.out.println("method1()의 finally블럭이 실행되었습니다.");
  		}
	}	// method1
}
```
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/d197fad1-133d-469f-9065-3a8ae60b72ee">
</div>

1. try블럭에서 return문이 실행되는 경우에도 fianlly블럭의 문장들이 먼저 실행된 후, 현재 실행 중 메서드 종료
2. 이와 마찬 가지로, catch 블럭의 문장 수행 중 return문을 만나도 fianlly블럭의 문장들은 수행
