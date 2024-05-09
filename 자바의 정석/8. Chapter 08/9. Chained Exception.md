-----
### 연결된 예외 (Chained Exception)
-----
1. 한 예외가 다른 예외를 발생시킬 수 있음
2. 예를 들어, 예외 A가 예외 B를 발생시켰다면, A를 B의 '원인 예외(Cause Exception)'
```java
try {
    startInstall(); // SpaceException 발생
    copyFiles();
} catch(SpaceException e) {
    InstallException ie = new InstallException("설치 중 예외발생"); // 예외 발생
    ie.initCause(e); // InstallException의 원인 예외를 SpaceException으로 지정
    throw ie;
} catch(MemoryException me) {
    ...
}
```
  - 먼저 InstallException을 생성한 후, initCause()로 SpaceException을 InstallException의 원인 예외로 등록하고, 이를 throw로 예외를 던짐

3. initCause()는 Exception 클래스의 조상인 Thorwable 클래스에 정의되어 있어서 모든 예외에서 사용 가능
```java
Throwable initCause(Throwable cause) // 지정한 예외를 원인 예외로 등록
Throwable getCause() // 원인 예외를 반환
```

4. 이렇게 하는 이유는 여러가지 예외를 하나의 큰 분류의 예외로 묶어서 다루기 위함임
   - 그렇다고, InstallException을 SpaceException과 MemoryException의 조상으로 해서 catch 블럭을 작성하면, 실제로 어떠한 예외가 발생한 것인지 알 수 없음
   - 또한, 이 둘의 상속관계를 변경해야 하는 문제 발생
   - 따라서, 예외가 원인 예외를 포함할 수 있도록 한 것 (두 예외는 상속관계가 아니어도 됨)

```java
public class Throwable impelements Serializable {
      ...
      private Throwable cause = this; // 객체 자신(this)을 원인 예외로 등록
}
```

5. 또 다른 이유는 checked예외를 unchecked예외로 바꿀 수 있도록 하기 위함
```java
static void startInstall() throws SpaceException, MemoryException {
    if(!enoughSpace())
        throw new SpaceException("설치할 공간이 부족합니다.");
    if(!enoughMemory())
        throw new MemoryException("메모리가 부족합니다.");
}
```

```java
static void startInstall() throws SpaceException {
    if(!enoughSpace())
        throw new SpaceException("설치할 공간이 부족합니다.");
    if(!enoughMemory())
        throw new RuntimeException(MemoryException("메모리가 부족합니다."));
}
```
  - MemoryException은 Exception의 자손이므로 반드시 예외 처리를 해야함
  - 이 예외를 RuntimeException으로 감싸버림으로 unchecked예외가 됨
  - 따라서, 더 이상 startInstall() 선언부에 MemoryException을 선언하지 않아도 됨
  - initCause() 대신 RuntimeException 생성자 이용
```java
RuntimeException(Throwable cause) // 원인 예외 등록 생성자
```

-----
### 연결된 예외 (Chained Exception) 예제
-----
```java
class ChainedExceptionEx {
	public static void main(String args[]) {
		try {
			install();
		} catch(InstallException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();		
		}
	} // main

	static void install() throws InstallException {
		try {
			startInstall();		// 프로그램  설치에 필요한 준비
			copyFiles();		// 파일들을 복사
		} catch (SpaceException e)	{
			InstallException ie = new InstallException("설치 중 예외 발생");
			ie.initCause(e);
			throw ie;
		} catch (MemoryException me) {
			InstallException ie = new InstallException("설치 중 예외 발생");
			ie.initCause(me);
			throw ie;
		} finally {
			deleteTempFiles();	// 프로그램 설치에 사용된 임시파일 삭제
		} // try
	}

static void startInstall() throws SpaceException, MemoryException { 
	if(!enoughSpace()) { 		// 충분한 설치 공간이 없을 때
		throw new SpaceException("설치할 공간이 부족합니다.");
	}

	if (!enoughMemory()) {		// 충분한 메모리가 없을 때
		throw new MemoryException("메모리가 부족합니다.");
//		throw new RuntimeException(new MemoryException("메모리가 부족합니다."));
	}
} // startInstall

   static void copyFiles() { /* 파일들을 복사하는 코드 */ }
   static void deleteTempFiles() { /* 임시파일들을 삭제하는 코드 */}
   
   static boolean enoughSpace()   {
		// 설치하는데 필요한 공간이 있는지 확인하는 코드
		return false;
   }
   static boolean enoughMemory() {
		// 설치하는데 필요한 메모리공간이 있는지 확인하는 코드
		return true;
   }
} // ExceptionTest

class InstallException extends Exception {
	InstallException(String msg) {
	   super(msg);	
   }
} 

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
<img src="https://github.com/sooyounghan/HTTP/assets/34672301/054fa07b-0073-4038-8385-c59ebb226aa6">
</div>

