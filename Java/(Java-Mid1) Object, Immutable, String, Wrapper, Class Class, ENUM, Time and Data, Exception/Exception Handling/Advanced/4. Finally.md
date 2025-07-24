-----
### 예외 처리 도입 5 - finally
-----
1. 자바는 어떤 경우라도 반드시 호출되는 finally 기능을 제공
```java
try {
   // 정상 흐름
} catch {
   // 예외 흐름
} finally {
   // 반드시 호출해야 하는 마무리 흐름
}
```

2. try ~ catch ~ finally 구조는 정상 흐름, 예외 흐름, 마무리 흐름을 제공
   - 여기서 try를 시작하기만 하면, finally 코드 블럭은 어떤 경우라도 반드시 호출
   - 심지어 try, catch 안에서 잡을 수 없는 예외가 발생해도 finally는 반드시 호출

3. 정리
   - 정상 흐름 → finally
   - 예외 catch → finally
   - 예외 던짐 → finally
     + finally 코드 블럭이 끝나고 나서 이후에 예외가 밖으로 던져짐

4. finally 블럭은 반드시 호출 : 따라서 주로 try 에서 사용한 자원을 해제할 때 주로 사용
5. NetworkServiceV2_5
```java
package exception.ex2;

public class NetworkServiceV2_5 {
    public void sendMessage(String data) {
        String address = "http://example.com";

        NetworkClientV2 client = new NetworkClientV2(address);
        client.initError(data);

        try {
            client.connect();
            client.send(data);
        } catch (NetworkClientExceptionV2 e) {
            System.out.println("[오류] 코드 : " + e.getErrorCode() + ", 메세지 : " + e.getMessage());
        } finally {
            client.disconnect();
        }
    }
}
```

6. MainV2 - 코드 변경
```java
package exception.ex2;

import exception.ex1.NetworkServiceV1_3;

import java.util.Scanner;

public class MainV2 {
    public static void main(String[] args) throws NetworkClientExceptionV2 {
        // NetworkServiceV2_1 networkService = new NetworkServiceV2_1();
        // NetworkServiceV2_2 networkService = new NetworkServiceV2_2();
        // NetworkServiceV2_3 networkService = new NetworkServiceV2_3();
        // NetworkServiceV2_4 networkService = new NetworkServiceV2_4();
        NetworkServiceV2_5 networkService = new NetworkServiceV2_5();

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
전송할 문자: hello
https://example.com 서버 연결 성공
https://example.com 서버에 데이터 전송: hello
https://example.com 서버 연결 해제

전송할 문자: error1
[오류] 코드: connectError, 메시지: https://example.com 서버 연결 실패
https://example.com 서버 연결 해제

전송할 문자: error2
https://example.com 서버 연결 성공
[오류] 코드: sendError, 메시지: https://example.com 서버에 데이터 전송 실패: error2
https://example.com 서버 연결 해제

전송할 문자: exit
프로그램을 정상 종료합니다.
```

7. 처리할 수 없는 예외와 finally
   - try, catch 안에서 처리할 수 없는 예외가 발생해도 finally 는 반드시 호출
   - 앞서 본 예제와 같이 NetworkClientV2.send(data)에서 처리할 수 없는 RuntimeException이 발생했다고 가정
   - NetworkClientV2 - 코드 변경
```java
public void send(String data) throws NetworkClientExceptionV2 {
     if (sendError) {
         // throw new NetworkClientExceptionV2("sendError", address + " 서버에 데이터 전송 실패: " + data);

         // 중간에 다른 예외가 발생했다고 가정
         throw new RuntimeException("ex");
     }

     // 전송 성공
     System.out.println(address + " 서버에 데이터 전송: " + data);
}
```
   - 실행 결과
```
전송할 문자: error2
https://example.com 서버 연결 성공
https://example.com 서버 연결 해제
Exception in thread "main" java.lang.RuntimeException: ex
    at exception.ex2.NetworkClientV2.send(NetworkClientV2.java:24)
    at exception.ex2.NetworkServiceV2_5.sendMessage(NetworkServiceV2_5.java:13)
    at exception.ex2.MainV2.main(MainV2.java:22)
```

  - 실행 결과를 보면 예외를 밖으로 던지는 경우에도 서버 연결 해제에 성공하는 것을 확인할 수 있음
  - 💡 catch에서 잡을 수 없는 예외가 발생해서, 예외를 밖으로 던지는 경우에도 finally를 먼저 호출하고 나서 예외를 밖으로 던짐

8. try ~ finally
    - catch 없이 try ~ finally 만 사용할 수도 있음
```java
try {
     client.connect();
     client.send(data);
} finally {
     client.disconnect();
}
```
   - 예외를 직접 잡아서 처리할 일이 없다면 이렇게 사용하면 됨 : 이렇게 하면 예외를 밖으로 던지는 경우에도 finally 호출이 보장

9. 정리
   - 자바 예외 처리는 try ~ catch ~ finally 구조를 사용해서 처리할 수 있음
   - 이점
      + 정상 흐름과 예외 흐름을 분리해서, 코드를 읽기 쉽게 만듬
      + 사용한 자원을 항상 반환할 수 있도록 보장
