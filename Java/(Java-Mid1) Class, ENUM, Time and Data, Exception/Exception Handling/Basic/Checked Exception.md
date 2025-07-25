-----
### 자바 예외 처리 3 - 체크 예외
-----
1. Exception과 그 하위 예외는 모두 컴파일러가 체크하는 체크 예외
   - 단, RuntimeException은 예외

2. 체크 예외는 잡아서 처리하거나, 또는 밖으로 던지도록 선언해야 함
   - 그렇지 않으면 컴파일 오류가 발생

3. 체크 예외 전체 코드
   - MyCheckedException (/exception/basic/checkes)
```java
package exception.basic.checked;

/**
 * Exception을 상속받는 예외 : 체크 예외
 */
public class MyCheckedException extends Exception {
    public MyCheckedException(String message) {
        super(message);
    }
}
```
  - 예외 클래스를 만들려면 예외를 상속 받으면 됨
  - Exception을 상속받은 예외는 체크 예외가 됨

  - Client
```java
package exception.basic.checked;

public class Client {
    public void call() throws MyCheckedException {
        throw new MyCheckedException("ex");
    }
}
```
   - 💡 throw 예외 라고 하면 새로운 예외를 발생시킬 수 있음 : 예외도 객체이기 때문에 객체를 먼저 new로 생성하고 예외를 발생시켜야 함
   - 💡 throws 예외 : 발생시킨 예외를 메서드 밖으로 던질 때 사용하는 키워드

   - Service
```java
package exception.basic.checked;

/**
 * Checked 예외는 예외를 잡아서 처리 또는 던지거나 둘 중 하나를 필수적으로 선택
 */
public class Service {
    Client client = new Client();

    /**
     * 예외를 잡아서 처리하는 코드
     */
    public void callCatch() {
        try {
            client.call();
        } catch (MyCheckedException e) {
            // 예외 처리 로직
            System.out.println("예외 처리, message = " + e.getMessage());
        }
        System.out.println("정상 흐름");
    }

    /**
     * 체크 예외를 밖으로 던지는 코드 : 밖으로 던지려면 throws 예외를 메서드에 필수로 선언
     */
    public void callThrow() throws MyCheckedException {
        client.call();
    }
}
```

4. Exception을 상속받은 예외는 체크 예외
```java
public class MyCheckedException extends Exception {
     public MyCheckedException(String message) {
         super(message);
     }
}
```
   - MyCheckedException는 Exception을 상속받음 : Exception을 상속받으면 체크 예외가 됨
   - 참고로 RuntimeException을 상속받으면 언체크 예외가 됨 
   - 예외가 제공하는 기본 기능이 있는데, 그 중에 오류 메시지를 보관하는 기능도 존재 : 생성자를 통해서 해당 기능을 그대로 사용하면 편리

<div align="center">
<img src="https://github.com/user-attachments/assets/3fe7d9fa-e158-4e6d-ab3c-f52643098417">
</div>

  - 💡 super(message)로 전달한 메시지는 Throwable에 있는 detailMessage에 보관
   - getMessage()를 통해 조회할 수 있음

5. 예외를 잡아서 처리
<div align="center">
<img src="https://github.com/user-attachments/assets/9acf796a-d0bb-4ff8-a63f-9e356c041162">
</div>

   - CheckedCatchMain
```java
package exception.basic.checked;

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
정상 흐름
정상 종료
```

   - service.callCatch()에서 예외를 처리했기 때문에 main() 메서드까지 예외가 올라오지 않음
   - main()에서 출력하는 "정상 종료" 문구가 출력된 것을 확인
   - 실행 순서를 분석
      + main() → service.callCatch() → client.call() [예외 발생, 던짐]
      + main() ← service.callCatch() [예외 처리] ← client.call()
      + main() [정상 흐름] ← service.callCatch() ← client.call()
    
   - Client.call()에서 MyCheckedException 예외가 발생하고, 그 예외를 Service.callCatch()에서 잡음
```java
public void callCatch() {
     try {
         client.call();
     } catch (MyCheckedException e) {
         // 예외 처리 로직
         System.out.println("예외 처리, message=" + e.getMessage());
     }
     System.out.println("정상 흐름");
}
```
   - 실행 결과
```
예외 처리, message=ex
System.out.println("정상 흐름");
```
   - 이 부분의 실행 결과를 보면 catch 부분이 작동한 것을 확인할 수 있음
   - catch로 예외를 잡아서 처리하고 나면 코드가 catch 블럭의 다음 라인으로 넘어가서 정상 흐름으로 작동

   - 체크 예외를 잡아서 처리하는 코드 - callCatch()
```java
 try {
     client.call();
 } catch (MyCheckedException e) {
     // 예외 처리 로직
     System.out.println("예외 처리, message=" + e.getMessage());
 }
 System.out.println("정상 흐름");
```
   - 예외를 잡아서 처리하려면 try ~ catch(..) 를 사용해서 예외를 잡으면 됨
   - try 코드 블럭에서 발생하는 예외를 잡아서 catch로 넘김
      + 만약 try에서 잡은 예외가 catch의 대상에 없으면 예외를 잡을 수 없음
      + 이때는 예외를 밖으로 던져야 함
   - 여기서는 MyCheckedException 예외를 catch로 잡아서 처리
   - catch는 해당 타입과 그 하위 타입을 모두 잡을 수 있음
```java
public void callCatch() {
     try {
         client.call();
     } catch (MyCheckedException e) {
         // 예외 처리 로직
     }
     System.out.println("정상 흐름");
}
```
   - catch에 MyCheckedException의 상위 타입인 Exception을 적어주어도 MyCheckedException을 잡을 수 있음
   - catch에 예외를 지정하면 해당 예외와 그 하위 타입 예외를 모두 잡아줌
   - 물론 정확하게 MyCheckedException만 잡고 싶다면 catch에 MyCheckedException을 적어주어야 함
   - 예외도 객체이기 때문에 다형성이 적용

6. 예외를 처리 하지 않고 던지기
<div align="center">
<img src="https://github.com/user-attachments/assets/bce609fd-f646-4780-add3-b073920736e8">
</div>

   - CheckedThrowMain
```java
package exception.basic.checked;

public class CheckedThrowMain {
    public static void main(String[] args) throws MyCheckedException {
        Service service = new Service();

        service.callThrow();

        System.out.println("정상 종료");
    }
}
```
   - 실행 결과
```
Exception in thread "main" exception.basic.checked.MyCheckedException: ex
	at exception.basic.checked.Client.call(Client.java:5)
	at exception.basic.checked.Service.callThrow(Service.java:26)
	at exception.basic.checked.CheckedThrowMain.main(CheckedThrowMain.java:7)
```

   - Service.callThrow() 안에서 예외를 처리하지 않고, 밖으로 던졌기 때문에 예외가 main() 메서드까지 올라옴
   - main()의 service.callThrow()를 호출하는 곳에서 예외를 잡아서 처리하지 못했기 때문에 여기서 예외가 main() 밖으로 던져짐
   - 따라서 main()에 있는 service.callThrow() 메서드 다음에 있는 "정상 종료"가 출력되지 않음
   - 예외가 main() 밖으로 던져지면 예외 정보와 스택 트레이스(Stack Trace)를 출력하고 프로그램이 종료
   - 스택 트레이스 정보를 활용하면 예외가 어디서 발생했는지, 그리고 어떤 경로를 거쳐서 넘어왔는지 확인할 수 있음
   - 실행 순서를 분석
     + main() → service.callThrow() → client.call() [예외 발생, 던짐]
     + main() ← service.callThrow() [예외 던짐] ← client.call()
     + main() [예외 던짐] ← service.callThrow() ← client.call()

   - 체크 예외를 밖으로 던지는 코드
```java
public void callThrow() throws MyCheckedException {
     client.call();
}
```
   - 체크 예외를 처리할 수 없을 때는 throws 키워드를 사용해서, method() throws 예외와 같이 밖으로 던질 예외를 필수로 지정해주어야 함
   - 여기서는 MyCheckedException을 밖으로 던지도록 지정해줌
   - 체크 예외를 밖으로 던지지 않으면 컴파일 오류 발생
```java
public void callThrow() {
     client.call();
}
```
   - throws를 지정하지 않으면 컴파일 오류가 발생
```  
java: unreported exception exception.basic.checked.MyCheckedException; must be caught or declared to be thrown
```
   - client.call()을 보면 throws MyCheckedException가 선언
   - 따라서, client.call() 을 호출하는 쪽에서 예외를 잡아서 처리하든, 던지든 선택해야 함
     + 참고로 MyCheckedException는 체크 예외
   - 체크 예외는 잡아서 직접 처리하거나 또는 밖으로 던지거나 둘중 하나를 개발자가 직접 명시적으로 처리해야함
   - 체크 예외는 try ~ catch로 잡아서 처리하거나 또는 throws를 지정해서 예외를 밖으로 던진다는 선언을 필수로 해주어야 함
   - 참고로 체크 예외를 밖으로 던지는 경우에도 해당 타입과 그 하위 타입을 모두 던질 수 있음
```java
public void callThrow() throws Exception {
     client.call();
}
```
   - throws에 MyCheckedException의 상위 타입인 Exception을 적어주어도 MyCheckedException을 던질 수 있음
   - throws에 지정한 타입과 그 하위 타입 예외를 밖으로 던짐
   - 물론 정확하게 MyCheckedException만 밖으로 던지고 싶다면 throws에 MyCheckedException을 적어주어야 함
   - 예외도 객체이기 때문에 다형성이 적용
     
7. 체크 예외의 장단점
    - 체크 예외는 예외를 잡아서 처리할 수 없을 때, 예외를 밖으로 던지는 throws 예외를 필수로 선언해야 함
    - 그렇지않으면 컴파일 오류가 발생하는데, 이것 때문에 장점과 단점이 동시에 존재
    - 장점 : 개발자가 실수로 예외를 누락하지 않도록 컴파일러를 통해 문제를 잡아주는 훌륭한 안전 장치
      + 이를 통해 개발자는 어떤 체크 예외가 발생하는지 쉽게 파악할 수 있음
    - 단점 : 하지만 실제로는 개발자가 모든 체크 예외를 반드시 잡거나 던지도록 처리해야 하기 때문에, 너무 번거로운 일이 됨
      + 크게 신경쓰고 싶지 않은 예외까지 모두 챙겨야 함

8. 정리
  - 체크 예외는 잡아서 직접 처리하거나 또는 밖으로 던지거나 둘중 하나를 개발자가 직접 명시적으로 처리해야함
  - 그렇지 않으면 컴파일 오류가 발생
