-----
### 예외 계층 2 - 활용
-----
1. 예외를 잡아서 처리할 때 예외 계층을 활용
2. NetworkClientV3에서 수 많은 예외를 발생한다고 가정
   - 이런 경우 모든 예외를 하나하나 다 잡아서 처리하는 것은 상당히 번거로울 것
   - 다음과 같이 예외를 처리하도록 구성
     + 연결 오류는 중요 : ConnectExceptionV3가 발생하면 다음과 같이 메시지를 명확하게 남기도록 할 것
        * 예) [연결 오류] 주소 : ...
     + NetworkClientV3을 사용하면서 발생하는 나머지 예외(NetworkClientExceptionV3 의 자식)는 단순하게 다음과 같이 출력
        * 예) [네트워크 오류] 메시지 : ...
     + 그 외에 예외가 발생하면 다음과 같이 출력
        * 예) [알 수 없는 오류] 메시지 : ...

3. NetworkServiceV3_2
```java
package exception.ex3;

import exception.ex3.exception.ConnectExceptionV3;
import exception.ex3.exception.SendExceptionV3;

public class NetworkServiceV3_2 {
    public void sendMessage(String data) {
        String address = "http://example.com";

        NetworkClientV3 client = new NetworkClientV3(address);
        client.initError(data);

        try {
            client.connect();
            client.send(data);
        } catch (ConnectExceptionV3 e) {
            System.out.println("[연결 오류] 주소 : " + e.getAddress() + ", 메시지 : " + e.getMessage());
        } catch (SendExceptionV3 e) {
            System.out.println("[네트워크 오류] 메세지 : " + e.getSendData() + ", 메시지 : " + e.getMessage());
        } catch (Exception e) {
            System.out.println("[알 수 없는 오류] 메세지 : " + e.getMessage());
        } finally {
            client.disconnect();
        }
    }
}
```
   - catch 는 순서대로 작동
   - ConnectExceptionV3 발생
```java
try {
    // 1. ConnectExceptionV3 발생
} catch (ConnectExceptionV3 e) {
    // 2. 잡아서 처리
} catch (NetworkClientExceptionV3 e) {

} catch (Exception e) {

}
```
   - SendExceptionV3 발생
```java
try {
    // 1. SendExceptionV3 발생
} catch (ConnectExceptionV3 e) {
    // 2. 대상이 다름
} catch (NetworkClientExceptionV3 e) {
    // 3.NetworkClientExceptionV3은 부모이므로 여기서 잡음
} catch (Exception e) {

}
```
  
  - NetworkClientExceptionV3 : SendExceptionV3의 부모
  - 부모 타입은 자식을 담을 수 있으므로, 따라서 NetworkClientExceptionV3을 잡으면 SendExceptionV3도 잡을 수 있음
  - RuntimeException 발생
```java
try {
    // 1. RuntimeException 발생
} catch (ConnectExceptionV3 e) {
    // 2. 대상이 다름
} catch (NetworkClientExceptionV3 e) {
    // 3.대상이 다름
} catch (Exception e) {
    // 4.Exception은 RuntimeException의 부모이므로 여기서 잡음
}
```
   - 모든 예외를 잡아서 처리하려면 마지막에 Exception을 두면 됨
   - 주의할 점은 예외가 발생했을 때 catch를 순서대로 실행하므로, 더 디테일한 자식을 먼저 잡아야 함

4. MainV3 - 코드 변경
```java
package exception.ex3;

import java.util.Scanner;

public class MainV3 {
    public static void main(String[] args) {
        // NetworkServiceV3_1 networkService = new NetworkServiceV3_1();
        NetworkServiceV3_2 networkService = new NetworkServiceV3_2();

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
[연결 오류] 주소: https://example.com, 메시지: https://example.com 서버 연결 실패
https://example.com 서버 연결 해제

전송할 문자: error2
https://example.com 서버 연결 성공
[네트워크 오류] 메시지: https://example.com 서버에 데이터 전송 실패: error2
https://example.com 서버 연결 해제

전송할 문자: exit
프로그램을 정상 종료합니다.
```
   - 예외 별로 다른 메시지가 출력되는 것을 확인할 수 있음

5. 여러 예외를 한번에 잡는 기능
   - 다음과 같이 | 를 사용해서 여러 예외를 한번에 잡을 수 있음
```java
try {
     client.connect();
     client.send(data);
} catch (ConnectExceptionV3 | SendExceptionV3 e) {
     System.out.println("[연결 또는 전송 오류] 주소: , 메시지: " + e.getMessage());
} finally {
     client.disconnect();
}
```
   - 참고로 이 경우 각 예외들의 공통 부모의 기능만 사용할 수 있음
   - 여기서는 NetworkClientExceptionV3 의 기능만 사용할 수 있음

6. 정리
   - 예외를 계층화하고 다양하게 만들면 더 세밀한 동작들을 깔끔하게 처리할 수 있음
   - 그리고 특정 분류의 공통 예외들도 한번에 catch 로 잡아서 처리할 수 있음
