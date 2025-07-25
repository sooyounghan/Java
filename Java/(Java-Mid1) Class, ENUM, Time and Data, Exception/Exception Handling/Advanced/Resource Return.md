-----
### 예외 처리 도입 4 - 리소스 반환 문제
-----
1. 현재 구조에서 disconnect()를 항상 호출하려면 다음과 같이 생각할 수 있음
   - NetworkServiceV2_4
```java
package exception.ex2;

public class NetworkServiceV2_4 {
    public void sendMessage(String data) {
        String address = "http://example.com";

        NetworkClientV2 client = new NetworkClientV2(address);
        client.initError(data);

        try {
            client.connect();
            client.send(data);
        } catch (NetworkClientExceptionV2 e) {
            System.out.println("[오류] 코드 : " + e.getErrorCode() + ", 메세지 : " + e.getMessage());
        }

        // NetworkClientExceptionV2이 아닌 다른 예외가 발생해서 예외가 밖으로 던져지면 무시
        client.disconnect();
    }
}
```
   - 이 코드를 보면 예외 처리가 끝난 다음에 정상 흐름의 마지막에 client.disconnect() 호출
   - 이렇게 하면 예외가 모두 처리되었기 때문에 client.disconnect()가 항상 호출될 것 같음

2. MainV2 - 코드 변경
```java
package exception.ex2;

import exception.ex1.NetworkServiceV1_3;

import java.util.Scanner;

public class MainV2 {
    public static void main(String[] args) throws NetworkClientExceptionV2 {
        // NetworkServiceV2_1 networkService = new NetworkServiceV2_1();
        // NetworkServiceV2_2 networkService = new NetworkServiceV2_2();
        // NetworkServiceV2_3 networkService = new NetworkServiceV2_3();
        NetworkServiceV2_4 networkService = new NetworkServiceV2_4();

        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("전송할 문자 : ");
            String input = scanner.nextLine();

            if (input.equals("exit")) {
                break;
            }

            networkService.sendMessage(input);
            System.out.println();
        }

        System.out.println("프로그램을 정상 종료합니다.");
    }
}
```
   - 실행 결과
```
전송할 문자 : hello
http://example.com 서버 연결 성공
http://example.com 서버에 전송 : hello
http://example.com 서버 연결 해제

전송할 문자 : error1
[오류] 코드 : connectError, 메세지 : http://example.com 서버 연결 실패
http://example.com 서버 연결 해제

전송할 문자 : error2
http://example.com 서버 연결 성공
[오류] 코드 : sendError, 메세지 : http://example.com 서버에 데이터 전송 실패
http://example.com 서버 연결 해제

전송할 문자 : exit
프로그램을 정상 종료합니다.
```

3. 문제점 : catch 에서 잡을 수 없는 예외가 발생할 때
4. NetworkClientV2 - 코드 변경
```java
package exception.ex2;

public class NetworkClientV2 {
    private final String address;

    private boolean connectError;
    private boolean sendError;


    public NetworkClientV2(String address) {
        this.address = address;
    }

    public void connect() throws NetworkClientExceptionV2 {
        if(connectError) {
            throw new NetworkClientExceptionV2("connectError", address + " 서버 연결 실패");
        }

        // 연결 성공
        System.out.println(address + " 서버 연결 성공");
    }

    public void send(String data) throws NetworkClientExceptionV2 {
        if(sendError) {
            // throw new NetworkClientExceptionV2("sendError", address + " 서버에 데이터 전송 실패");
            throw new RuntimeException("ex"); // 중간에 다른 예외가 발생했다고 가정
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
}
```
   - NetworkClientV2.send()에서 발생하는 예외를 자바가 기본으로 제공하는 RuntimeException으로 변경하고 실행
   - catch에서 NetworkClientExceptionV2은 잡을 수 있지만 새로 등장한 RuntimeException은 잡을 수 없음

   - 실행 결과
```
전송할 문자: error1
[오류] 코드: connectError, 메시지: http://example.com 서버 연결 실패
http://example.com 서버 연결 해제

전송할 문자: error2
http://example.com 서버 연결 성공
Exception in thread "main" java.lang.RuntimeException: ex
  at exception.ex2.NetworkClientV2.send(NetworkClientV2.java:25)
  at exception.ex2.NetworkServiceV2_4.sendMessage(NetworkServiceV2_4.java:13)
  at exception.ex2.MainV2.main(MainV2.java:22)
```

   - error1의 경우 : client.connect()에서 NetworkClientExceptionV2 예외가 발생하기 때문에 바로 catch로 이동해서 정상 흐름을 이어감
   - error2의 경우 : client.send()에서 RuntimeException이 발생 - 이 예외는 catch 의 대상이 아니므로 잡지 않고 즉시 밖으로 던져짐
```java
try {
     client.connect();
     client.send(data); // 1. RuntimeException은 catch 대상이 아님. 예외가 밖으로 던져짐
} catch (NetworkClientExceptionV2 e) {
     System.out.println("[오류] 코드: " + e.getErrorCode() + ", 메시지: " + e.getMessage());
}

client.disconnect(); // 2. 이 코드는 호출되지 않음
```
   - client.disconnect() 가 호출되지 않는 문제가 발생

5. 사용 후에 반드시 disconnect()를 호출해서 연결 해제를 보장하는 것은 쉽지 않음
   - 왜냐하면 정상적인 상황, 예외 상황 그리고 어디선가 모르는 예외를 밖으로 던지는 상황까지 모든 것을 고려해야 함
   - 하지만 앞서 보았듯이 지금과 같은 구조로는 항상 disconnect()와 같은 코드를 호출하는 것이 매우 어렵고 실수로 놓칠 가능성이 높음

