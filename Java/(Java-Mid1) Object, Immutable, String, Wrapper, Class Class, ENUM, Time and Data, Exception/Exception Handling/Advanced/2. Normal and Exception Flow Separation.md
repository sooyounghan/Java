-----
### 예외 처리 도입 3 - 정상, 예외 흐름 분리
-----
1. 예외 처리의 try ~ catch 기능을 제대로 사용해서 정상 흐름과 예외 흐름이 섞여 있는 문제를 해결
2. NetworkServiceV2_3
```java
package exception.ex2;

public class NetworkServiceV2_3 {
    public void sendMessage(String data) {
        String address = "http://example.com";

        NetworkClientV2 client = new NetworkClientV2(address);
        client.initError(data); 

        try {
            client.connect();
            client.send(data);
            client.disconnect(); // 예외 발생 시 무시
        } catch (NetworkClientExceptionV2 e) {
            System.out.println("[오류] 코드 : " + e.getErrorCode() + ", 메세지 : " + e.getMessage());
        }
    }
}
```
   - 하나의 try 안에 정상 흐름을 모두 보관
   - 그리고 예외 부분은 catch 블럭에서 해결
   - 이렇게 하면 정상 흐름은 try 블럭에 들어가고, 예외 흐름은 catch 블럭으로 명확하게 분리할 수 있음

3. MainV2 코드 변경
```java
package exception.ex2;

import exception.ex1.NetworkServiceV1_3;

import java.util.Scanner;

public class MainV2 {
    public static void main(String[] args) throws NetworkClientExceptionV2 {
        // NetworkServiceV2_1 networkService = new NetworkServiceV2_1();
        // NetworkServiceV2_2 networkService = new NetworkServiceV2_2();
        NetworkServiceV2_3 networkService = new NetworkServiceV2_3();

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
프로그램을 정상 종료합니다.
```

4. 해결된 문제
   - 자바의 예외 처리 메커니즘과 try, catch 구조 덕분에 정상 흐름은 try 블럭에 모아서 처리하고, 예외 흐름은 catch 블럭에 별도로 모아서 처리 가능
   - 덕분에 정상 흐름과 예외 흐름을 명확하게 분리해서 코드를 더 쉽게 읽을 수 있게 됨

5. 남은 문제
   - 사용 후에는 반드시 disconnect()를 호출해서 연결을 해제해야 함
   - 앞서 이야기했듯이 외부 연결과 같은 자바 외부의 자원은 자동으로 해제가 되지않으므로, 따라서 외부 자원을 사용한 후에는 연결을 해제해서 외부 자원을 반드시 반납해야 함
