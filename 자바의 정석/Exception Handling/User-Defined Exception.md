-----
### 사용자 정의 예외
-----
1. 기존의 정의된 예외 클래스 외 필요에 따라 프로그래머가 새로운 예외 클래스를 정의하여 사용 가능
2. 보통 Exception클래스 또는 RuntimeException 클래스로부터 상속받아 클래스를 만들지만, 필요에 따라 알맞은 예외 클래스 선택 가능
```java
class MyException extends Exception {
    MyException(String msg) { // 문자열을 매개변수로 받는 생성자
        super(msg); // 조상인 Exception 클래스의 생성자 호출
    }
}
```

  - Exception 클래스로부터 상속받아 MyException 클래스 생성

3. 사용자정의 예외 클래스도 메세지를 저장할 수 있으려면, String을 매개변수로 받는 생성자를 추가해줘야함

```java
class MyException extends Exception {
    // 에러 코드 값을 저장하기 위한 필드 추가
    private final int ERR_CODE; // 생성자를 통해 초기화

    MyException(String msg, int errCode) { // 생성자
        super(msg);
        ERR_CODE = errCode;
    }

    MyException(String msg) { // 생성자
        this(msg, 100); // ERR_CODE를 100(기본값)으로 초기화
    }

    public int getErrCode() { // 에러코드를 얻을 수 있는 메서드 추가
        return ERR_CODE; // 해당 메서드는 주로 getMessage()와 함께 사용
    }
}
```
  - 에러코드 값도 저장할 수 있도록 추가

4. 기존 예외 클래스는 주로 Exception 예외를 상속받아 'checked예외'로 작성하는 경우가 많았지만, 최근에는 예외처리를 선택적으로 할 수 있도록 'RuntimeException'을 상속받아 작성
5. checked예외는 반드시 예외처리를 해줘야하므로, 예외처리가 불필요한 경우에도 try-catch문을 넣어 코드가 복잡해짐

```java
class NewExceptionTest {
	public static void main(String args[]) {
		try {
			startInstall();		// 프로그램 설치에 필요한 준비
			copyFiles();			// 파일들을 복사
		} catch (SpaceException e)	{
			System.out.println("에러 메세지: " + e.getMessage());
			e.printStackTrace();
			System.out.println("공간을 확보한 후 다시 설치하시길 바랍니다.");
		} catch (MemoryException me)	{
			System.out.println("에러 메세지 : " + me.getMessage());
			me.printStackTrace();
			System.gc();		//  Garbage Collection을 수행하여 메모리를 늘려줌
			System.out.println("다시 설치를 시도하세요.");
		} finally {
			deleteTempFiles();	// 프로그램 설치에 사용된 임시파일 삭제
		} // try
	} // main

   static void startInstall() throws SpaceException, MemoryException { 
		if(!enoughSpace()) 		// 충분한 설치 공간이 없을 때
			throw new SpaceException("설치할 공간이 부족합니다.");
		if (!enoughMemory())		// 충분한 메모리가 없을 때
			throw new MemoryException("메모리가 부족합니다.");
   } // startInstall

   static void copyFiles() { /* 파일을 복사하는 코드 */ }
   static void deleteTempFiles() { /* 임시파일들을 삭제하는 코드 */}
   
   static boolean enoughSpace()   {
		// 설치하는데 필요한 공간이 있는지 확인하는 코드 작성
		return false;
   }
   static boolean enoughMemory() {
		// 설치하는데 필요한 메모리 공간이 있는지 확인하는 코드 작성
		return true;
   }
} // ExceptionTest

class SpaceException extends Exception {
	SpaceException(String msg) {
	   super(msg);	
   }
} 

class MemoryException extends Exception {
	MemoryException(String msg) {
	   super(msg);	
   }
}
```
<div align="center">
<img src="https://github.com/sooyounghan/HTTP/assets/34672301/e7e2a6ff-4b5c-4a73-9a88-dfe47df3f8e9">
</div>

