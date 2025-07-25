-----
### 예외 처리 도입 2 - 예외 복구
-----
1. 예외를 잡아서 예외 흐름을 정상 흐름으로 복구
2. NetworkServiceV2_2
```java
package exception.ex2;

public class NetworkServiceV2_2 {
    public void sendMessage(String data) {
        String address = "http://example.com";

        NetworkClientV2 client = new NetworkClientV2(address);
        client.initError(data); // 추가

        try {
            client.connect();
        } catch (NetworkClientExceptionV2 e) {
            System.out.println("[오류] 코드 : " + e.getErrorCode() + ", 메세지 : " + e.getMessage());
            return;
        }

        try {
            client.send(data);
        } catch (NetworkClientExceptionV2 e) {
            System.out.println("[오류] 코드 : " + e.getErrorCode() + ", 메세지 : " + e.getMessage());
            return;
        }
        client.disconnect();
    }
}
```
   - connect(), send()와 같이 예외가 발생할 수 있는 곳을 try ~ catch 를 사용해서 NetworkClientExceptionV2 예외를 잡음
   - 여기서는 예외를 잡으면 오류 코드와 예외 메시지를 출력
   - 예외를 잡아서 처리했기 때문에 이후에는 정상 흐름으로 복귀 : 여기서는 리턴을 사용해서 sendMessage() 메서드를 정상적으로 빠져나감

3. MainV2 - 코드 변경
```java
package exception.ex2;

import exception.ex1.NetworkServiceV1_3;

import java.util.Scanner;

public class MainV2 {
    public static void main(String[] args) throws NetworkClientExceptionV2 {
        // NetworkServiceV2_1 networkService = new NetworkServiceV2_1();
        NetworkServiceV2_2 networkService = new NetworkServiceV2_2();

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
http://example.com 서버 연결 성공
http://example.com 서버에 데이터 전송: hello
http://example.com 서버 연결 해제

전송할 문자: error1
[오류] 코드: connectError, 메시지: http://example.com 서버 연결 실패

전송할 문자: error2
http://example.com 서버 연결 성공
[오류] 코드: sendError, 메시지: http://example.com 서버에 데이터 전송 실패: error2

전송할 문자: exit
프로그램을 정상 종료합니다
```

4. 해결된 문제
   - 예외를 잡아서 처리 : 따라서 예외가 복구 되고, 프로그램도 계속 수행할 수 있음

5. 남은 문제
   - 예외 처리를 했지만 정상 흐름과 예외 흐름이 섞여 있어서 코드를 읽기 어려움
   - 사용 후에는 반드시 disconnect()를 호출해서 연결을 해제해야 함
