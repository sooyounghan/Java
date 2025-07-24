-----
### try - with - resources
-----
1. 애플리케이션에서 외부 자원을 사용하는 경우 반드시 외부 자원을 해제해야 함 : 따라서 finally 구문을 반드시 사용해야 함
```java
try {
     // 정상 흐름
} catch {
     // 예외 흐름
} finally {
     // 반드시 호출해야 하는 마무리 흐름
}
```

  - try에서 외부 자원을 사용하고, try가 끝나면 외부 자원을 반납하는 패턴이 반복되면서 자바에서는 try ~ with ~ resources라는 편의 기능을 자바 7에서 도입
  - 이름 그대로 try에서 자원을 함께 사용한다는 뜻임
    + 여기서 자원은 try가 끝나면 반드시 종료해서 반납해야 하는 외부 자원을 뜻함
  - 💡 이 기능을 사용하려면 먼저 AutoCloseable 인터페이스를 구현해야 함
```java
package java.lang;

public interface AutoCloseable {
     void close() throws Exception;
}
```
  - 이 인터페이스를 구현하면 try ~ with ~ resources를 사용할 때 try가 끝나는 시점에 close()가 자동으로 호출
  - 그리고 다음과 같이 try ~ with ~ resources 구문을 사용하면 됨
```java
try (Resource resource = new Resource()) {
   // 리소스를 사용하는 코드
}
````
  
2. NetworkClientV5 (/exception/ex4)
```java
package exception.ex4;

import exception.ex4.exception.ConnectExceptionV4;
import exception.ex4.exception.SendExceptionV4;

public class NetworkClientV5 implements AutoCloseable {
    private final String address;

    private boolean connectError;
    private boolean sendError;

    public NetworkClientV5(String address) {
        this.address = address;
    }

    public void connect() {
        if(connectError) {
            throw new ConnectExceptionV4(address, address + " 서버 연결 실패");
        }

        // 연결 성공
        System.out.println(address + " 서버 연결 성공");
    }

    public void send(String data) {
        if(sendError) {
            throw new SendExceptionV4(data, address + " 서버에 데이터 전송 실패");
        }

        // 전송 성공
        System.out.println(address + " 서버에 전송 : " + data);
    }

    public void disconnect() {
        System.out.println(address + " 서버 연결 해제");
    }

    public void initError(String data) {
        if(data.equals("error1")) {
            connectError = true;
        }

        if(data.equals("error2")) {
            sendError = true;
        }
    }

    @Override
    public void close() { // 이 메서드에서 예외를 던지지는 않으므로 인터페이스의 메서드에 있는 throws Exception은 제거
        System.out.println("NetworkClientV5.close");
        disconnect();
    }
}
```
   - implements AutoCloseable을 통해 AutoCloseable을 구현
   - 💡 close() : AutoCloseable 인터페이스가 제공하는 이 메서드는 try가 끝나면 자동으로 호출
     + 종료 시점에 자원을 반납하는 방법을 여기에 정의하면 됨
     + 참고로 이 메서드에서 예외를 던지지는 않으므로 인터페이스의 메서드에 있는 throws Exception은 제거

3. NetworkServiceV5
```java
package exception.ex4;

public class NetworkServiceV5 {
    public void sendMessage(String data) {
        String address = "http://example.com";

        try (NetworkClientV5 client = new NetworkClientV5(address)){
            client.initError(data);
            client.connect();
            client.send(data);
        } catch (Exception e) {
            System.out.println("[예외 확인] : " + e.getMessage());
            throw e;
        }
    }
}
```
   - try ~ with ~ resources 구문은 try 괄호 안에 사용할 자원을 명시
   - 이 자원은 try 블럭이 끝나면 자동으로 AutoCloseable.close()를 호출해서 자원을 해제
   - 참고로 여기서 catch 블럭 없이 try 블럭만 있어도 close() 는 호출
   - 여기서 catch 블럭은 단순히 발생한 예외를 잡아서 예외 메시지를 출력하고, 잡은 예외를 throw 를 사용해서 다시 밖으로 던짐

4. MainV4
```java
package exception.ex4;


import exception.ex2.NetworkClientExceptionV2;
import exception.ex4.exception.SendExceptionV4;

import java.util.Scanner;

public class MainV4 {
    public static void main(String[] args) throws NetworkClientExceptionV2 {
        NetworkServiceV5 networkService = new NetworkServiceV5(); // 변경

        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("전송할 문자 : ");
            String input = scanner.nextLine();

            if (input.equals("exit")) {
                break;
            }

            try {
                networkService.sendMessage(input);
            } catch (Exception e) { // 모든 예외를 잡아서 처리
                exceptionHandler(e);
            }
            System.out.println();
        }
        System.out.println("프로그램을 정상 종료합니다.");
    }

    // 공통 예외 처리
    private static void exceptionHandler(Exception e) {
        // 공통 처리
        System.out.println("사용자 메세지 : 죄송합니다, 알 수 없는 문제가 발생했습니다.");
        System.out.println("== 개발자용 디버깅 메세지 ==");
        e.printStackTrace(System.out); // StsckTrace 출력
        // e.printStackTrace(); // System.err에 스택 트레이스 출력

        // 필요 시, 예외 별 별도의 추가 처리 가능
        if (e instanceof SendExceptionV4 sendEx) {
            System.out.println("[전송 오류] 전송 데이터 : " + sendEx.getSendData());
        }
    }
}
```
   - 실행 결과
```
전송할 문자 : hello
http://example.com 서버 연결 성공
http://example.com 서버에 전송 : hello
NetworkClientV5.close
http://example.com 서버 연결 해제

전송할 문자 : error1
NetworkClientV5.close
http://example.com 서버 연결 해제
[예외 확인] : http://example.com 서버 연결 실패
사용자 메세지 : 죄송합니다, 알 수 없는 문제가 발생했습니다.
== 개발자용 디버깅 메세지 ==
exception.ex4.exception.ConnectExceptionV4: http://example.com 서버 연결 실패
	at exception.ex4.NetworkClientV5.connect(NetworkClientV5.java:18)
	at exception.ex4.NetworkServiceV5.sendMessage(NetworkServiceV5.java:9)
	at exception.ex4.MainV4.main(MainV4.java:24)

전송할 문자 : error2
http://example.com 서버 연결 성공
NetworkClientV5.close
http://example.com 서버 연결 해제
[예외 확인] : http://example.com 서버에 데이터 전송 실패
사용자 메세지 : 죄송합니다, 알 수 없는 문제가 발생했습니다.
== 개발자용 디버깅 메세지 ==
exception.ex4.exception.SendExceptionV4: http://example.com 서버에 데이터 전송 실패
	at exception.ex4.NetworkClientV5.send(NetworkClientV5.java:27)
	at exception.ex4.NetworkServiceV5.sendMessage(NetworkServiceV5.java:10)
	at exception.ex4.MainV4.main(MainV4.java:24)
[전송 오류] 전송 데이터 : error2

전송할 문자 : exit
프로그램을 정상 종료합니다.
```

   - try ~ with ~ resources 장점
     + 리소스 누수 방지 : 모든 리소스가 제대로 닫히도록 보장하며, 실수로 finally 블록을 적지 않거나, finally 블럭 안에서 자원 해제 코드를 누락하는 문제들을 예방할 수 있음
     + 코드 간결성 및 가독성 향상 : 명시적인 close() 호출이 필요 없어 코드가 더 간결하고 읽기 쉬워짐
     + 스코프 범위 한정 : 예를 들어 리소스로 사용되는 client 변수의 스코프가 try 블럭 안으로 한정되므로, 따라서 코드 유지보수가 더 쉬워짐
     + 조금 더 빠른 자원 해제 : 기존에는 try → catch →finally로 catch 이후에 자원을 반납했지만, try 블럭이 끝나면 즉시 close()를 호출
