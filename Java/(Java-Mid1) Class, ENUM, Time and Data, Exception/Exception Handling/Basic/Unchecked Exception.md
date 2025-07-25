-----
### 자바 예외 처리 4 - 언체크 예외
-----
1. RuntimeException과 그 하위 예외는 언체크 예외로 분류
2. 언체크 예외는 말 그대로 컴파일러가 예외를 체크하지 않는다는 뜻
3. 언체크 예외는 체크 예외와 기본적으로 동일
   - 차이가 있다면 예외를 던지는 throws를 선언하지 않고, 생략할 수 있음
   - 생략한 경우 자동으로 예외를 던짐
4. 체크 예외 VS 언체크 예외  
   - 체크 예외 : 예외를 잡아서 처리하지 않으면 항상 throws 키워드를 사용해서 던지는 예외를 선언해야 함
   - 언체크 예외 : 예외를 잡아서 처리하지 않아도 throws 키워드를 생략할 수 있음

5. MyUncheckedException
```java
package exception.basic.unchecked;

/**
 * RuntimeException을 상속받는 예외 : 언체크 예외
 */
public class MyUncheckedException extends RuntimeException {
    public MyUncheckedException(String message) {
        super(message);
    }
}
```

6. Client
```java
package exception.basic.unchecked;

public class Client {
    public void call() throws MyUncheckedException {
        throw new MyUncheckedException("ex");
    }
}
```

7. Service
```java
package exception.basic.unchecked;

/**
 * Unchecked 예외는 예외를 잡아서 처리 또는 던지지 않아도 되며, 예외를 잡지 않으면 자동으로 밖으로 던짐
 */
public class Service {
    Client client = new Client();

    /**
     * 필요한 경우 예외를 잡아 처리
     */
    public void callCatch() {
        try {
            client.call();
        } catch (MyUncheckedException e) {
            // 예외 처리 로직
            System.out.println("예외 처리, message = " + e.getMessage());
        }
        System.out.println("정상 로직");
    }

    /**
     * 예외를 잡지 않아도 되며, 자연스럽게 상위로 넘어감
     * 체크 예외와 다르게 throws 예외 선언을 하지 않아도 됨
     * */
    public void callThrow() {
        client.call();
    }
}
```
  - 체크 예외와 실행 결과는 완전히 동일
  - UncheckCatchMain
```java
package exception.basic.unchecked;

public class CheckedCatchMain {
    public static void main(String[] args) {
        Service service = new Service();

        service.callCatch();

        System.out.println("정상 종료");
    }
}
```
  - 실행 결과
```
예외 처리, message = ex
정상 로직
정상 종료
```
  - UncheckThrowMain
```java
package exception.basic.unchecked;

public class CheckedThrowMain {
    public static void main(String[] args) {
        Service service = new Service();

        service.callThrow();

        System.out.println("정상 종료");
    }
}
```
   - 실행 결과
```
Exception in thread "main" exception.basic.unchecked.MyUncheckedException: ex
	at exception.basic.unchecked.Client.call(Client.java:5)
	at exception.basic.unchecked.Service.callThrow(Service.java:27)
	at exception.basic.unchecked.CheckedThrowMain.main(CheckedThrowMain.java:7)
```

  - 언체크 예외를 잡아서 처리하는 코드 - callCatch()
```java
try {
     client.call();
} catch (MyUncheckedException e) {
     // 예외 처리 로직
     log.info("error", e);
}

System.out.println("정상 로직");
```
   - 언체크 예외도 필요한 경우 예외를 잡아서 처리할 수 있음
   - 언체크 예외를 밖으로 던지는 코드
```java
public void callThrow() {
     client.call();
}
```
   - 언체크 예외는 체크 예외와 다르게 throws 예외를 선언하지 않아도 됨
   - 말 그대로 컴파일러가 이런 부분을 체크하지 않기 때문에 언체크 예외
   - 언체크 예외를 밖으로 던지는 코드 - 선언
```java
public class Client {
     public void call() throws MyUncheckedException {
         throw new MyUncheckedException("ex");
     }
}
```
   - 참고로 언체크 예외도 throws 예외를 선언해도 되며, 물론 생략할 수 있음
   - 언체크 예외는 주로 생략하지만, 중요한 예외의 경우 이렇게 선언해두면 해당 코드를 호출하는 개발자가 이런 예외가 발생한다는 점을 IDE를 통해 좀 더 편리하게 인지할 수 있음 (언체크 예외를 던진다고 선언한다고 해서 체크 예외 처럼 컴파일러를 통해서 체크할 수 있는 것은 아님. 단지, 해당 메서드를 호출하는 개발자가 IDE를 통해 호출 코드를 보고, 이런 예외가 발생한다고 인지할 수 있는 정도)

8. 언체크 예외의 장단점
   - 언체크 예외는 예외를 잡아서 처리할 수 없을 때, 예외를 밖으로 던지는 throws 예외를 생략할 수 있는데, 때문에 장점과 단점이 동시에 존재
   - 장점 : 신경쓰고 싶지 않은 언체크 예외를 무시 가능 : 체크 예외의 경우 처리할 수 없는 예외를 밖으로 던지려면 항상 throws 예외를 선언해야 하지만, 언체크 예외는 이 부분을 생략할 수 있음
   - 단점 : 언체크 예외는 개발자가 실수로 예외를 누락할 수 있지만, 반면에 체크 예외는 컴파일러를 통해 예외 누락을 잡아줌
  
9. 정리
   - 체크 예외와 언체크 예외의 차이는 예외를 처리할 수 없을 때 예외를 밖으로 던지는 부분에 있음
   - 이 부분을 필수로 선언 해야 하는가 생략할 수 있는가의 차이
