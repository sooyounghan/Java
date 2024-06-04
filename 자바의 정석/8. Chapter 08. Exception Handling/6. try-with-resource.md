-----
### 자동 자원 반환 - try - with - resource문
-----
1. JDK1.7부터 추가
2. 주로 입출력에 사용되는 클래스 중 사용한 후 꼭 닫아줘야하는 것, 즉 사용했던 자원(Resources)을 반환해야하는 것 존재
```java
try {
    fis = new FileInputStream("score.dat");
    dis = new DataInputStream(fis);
} catch(IOException ie) {
    ie.printStackTrace();
} finally {
    dis.close(); // 작업 중 예외가 발생하더라도, dis가 닫히도록 finally 블럭에 넣음
}
```
  - DataInputStream을 사용해 파일로부터 데이터를 읽는 코드
  - 데이터를 읽는 도중 예외가 발생하더라도 DataInputStream이 닫히도록 finally 블럭 안에 close()를 넣음
  - 하지만, close() 또한 예외가 발생할 수 있는 문제 발생
```java
try {
    fis = new FileInputStream("score.dat");
    dis = new DataInputStream(fis);
} catch(IOException ie) {
    ie.printStackTrace();
} finally {
    try {
        if(dis != null) {
            dis.close();
        }
    } catch(IOException ie) {
            ie.printStackTrace();
    }
}
```
  - finally 블럭 안 try - catch 문을 추가해 close()에서 발생할 수 있는 예외를 처리하도록 변경
  - 하지만, try블럭과 finally블럭 모두 예외가 발생하면, try 블럭의 예외는 무시
  - 이를 해결하기 위해 try-with-resources 문 추가

```java
// 괄호() 안에 두 문장을 넣을 경우 ';'로 구분
try (FileInputStream fis = new FileInputStream("score.dat");
     DataInputStream dis = new DataInputStream(fis)) {
        while(true) {
          score = dis.readInt();
          System.out.println(score);
          sum += score;
        }
} catch(EOFException e) {
    System.out.println("점수의 총합은 " + sum + "입니다.");
} catch(IOException ie) {
    ie.printStackTrace();
}
```

3. try-with-resource문의 괄호()안에 객체를 생성하는 문장을 넣으면, 이 객체는 따로 close()를 호출하지 않아도, try블럭을 벗어나는 순간 close()를 자동으로 호출
4. 그 다음, catch 블럭 또는 finally 블럭이 수행
5. try블럭의 괄호()안 변수 선언도 가능하며, 선언된 변수는 try 블럭 안에서만 사용 가능
6. 이처럼, try-with-resources문에 의해 자동으로 객체의 close()가 호출될 수 있으려면, 클래스가 AutoCloseable라는 인터페이스가 구현한 것이어야만 함
```java
public interface AutoCloseable {
    void close() throws Exception;
}
```

-----
### 자동 자원 반환 - try - with - resource문 예제
-----
```java
class TryWithResourceEx {
	public static void main(String args[]) {

		try (CloseableResource cr = new CloseableResource()) {
			cr.exceptionWork(false); // 예외가 발생하지 않음
 		} catch(WorkException e) {
			e.printStackTrace();
		} catch(CloseException e) {
			e.printStackTrace();
		}
		System.out.println();
	
		try (CloseableResource cr = new CloseableResource()) {
			cr.exceptionWork(true); // 예외가 발생
 		} catch(WorkException e) {
			e.printStackTrace();
		} catch(CloseException e) {
			e.printStackTrace();
		}	
	} // main
}

class CloseableResource implements AutoCloseable {
	public void exceptionWork(boolean exception) throws WorkException {
		System.out.println("exceptionWork("+exception+")가 호출");

		if(exception)
			throw new WorkException("WorkException 발생!!!");
	}

	public void close() throws CloseException {
		System.out.println("close()가 호출됨");
		throw new CloseException("CloseException 발생!!!");
	}
}

class WorkException extends Exception {
	WorkException(String msg) { super(msg); }
}

class CloseException extends Exception {
	CloseException(String msg) { super(msg); }
}
```
<div align="center">
<img src="https://github.com/sooyounghan/HTTP/assets/34672301/e3817cbc-2fdf-44c3-8dcd-d0fd44936989">
</div>

1. main메서드의 두 개의 try-catch문이 존재
  - 첫 번째 : close()에서만 예외가 발생
  - 두 번째 : exceptionWork()와 close()에서 모두 예외 발생

2. 첫 번째는 일반적인 예외가 발생했을 때와 같은 형태로 출력
3. 두 번째는 출력형태가 먼저 exceptionWork()에서 발생한 예외에 대한 내용이 출력되고, close()에서 발생한 예외는 '억제된(suppressed)'이라는 의미의 머리말과 함께 출력
4. 두 예외는 동시에 발생할 수 없기 때문에, 실제 발생한 예외를 WorkException으로 하고, CloseException을 억제된 예외로 다룸
5. 억제된 예외에 대한 정보는 실제로 발생한 예외인 WorkException에 저장
6. Throwable에는 다음과 같이 억제된 예외에 대한 메서드 정의
```java
void addSuppressed(Throwable exception) // 억제된 예외 추가
Throwable[] getSuppressed() // 억제된 예외(배열) 반환
```
