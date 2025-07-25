-----
### 예외 계층 1 - 시작
-----
<div align="center">
<img src="https://github.com/user-attachments/assets/8cc96736-0811-42a8-9124-61b68dab0034">
</div>

1. 예외를 단순히 오류 코드로 분류하는 것이 아니라, 예외를 계층화해서 다양하게 만들면 더 세밀하게 예외를 처리할 수 있음
   - NetworkClientExceptionV3 : NetworkClient에서 발생하는 모든 예외는 이 예외의 자식
   - ConnectExceptionV3 : 연결 실패시 발생하는 예외
     + 내부에 연결을 시도한 address를 보관
   - SendExceptionV3 : 전송 실패시 발생하는 예외
     + 내부에 전송을 시도한 데이터인 sendData를 보관

2. 이렇게 예외를 계층화할 때의 장점
   - 자바에서 예외는 객체 : 따라서 부모 예외를 잡거나 던지면, 자식 예외도 함께 잡거나 던질 수 있음
     + 예를 들어서 NetworkClientExceptionV3 예외를 잡으면 그 하위인 ConnectExceptionV3, SendExceptionV3 예외도 함께 잡을 수 있음
   - 특정 예외를 잡아서 처리하고 싶으면 ConnectExceptionV3, SendExceptionV3 와 같은 하위 예외를 잡아서 처리하면 됨

3. NetworkClientExceptionV3 (/exception/ex3/exception)
```java
package exception.ex3.exception;

public class NetworkClientExceptionV3 extends Exception {
    public NetworkClientExceptionV3(String message) {
        super(message);
    }
}
```
  - NetworkClient에서 발생하는 모든 예외는 이 예외를 부모로 하도록 설계

4. ConnectionExceptionV3
```java
package exception.ex3.exception;

public class ConnectExceptionV3 extends NetworkClientExceptionV3 {
    private final String address;

    public ConnectExceptionV3(String message, String address) {
        super(message);
        this.address = address;
    }
    
    public String getAddress() {
        return address;
    }
}
```
   - ConnectExceptionV3 : 연결 실패시 발생하는 예외
     + 내부에 연결을 시도한 address를 보관
   - NetworkClientExceptionV3를 상속

5. SendExceptionV3
```java
package exception.ex3.exception;

public class SendExceptionV3 extends NetworkClientExceptionV3 {
    private final String sendData;

    public SendExceptionV3(String message, String sendData) {
        super(message);
        this.sendData = sendData;
    }
    
    public String getSendData() {
        return sendData;
    }
}
```
   - SendExceptionV3 : 전송 실패시 발생하는 예외
     + 내부에 전송을 시도한 데이터인 sendData 보관
   - NetworkClientExceptionV3를 상속

6. NetworkClientV3
```java
package exception.ex3;

import exception.ex3.exception.ConnectExceptionV3;
import exception.ex3.exception.SendExceptionV3;

public class NetworkClientV3 {
    private final String address;

    private boolean connectError;
    private boolean sendError;


    public NetworkClientV3(String address) {
        this.address = address;
    }

    public void connect() throws ConnectExceptionV3 {
        if(connectError) {
            throw new ConnectExceptionV3(address, address + " 서버 연결 실패");
        }

        // 연결 성공
        System.out.println(address + " 서버 연결 성공");
    }

    public void send(String data) throws SendExceptionV3 {
        if(sendError) {
            throw new SendExceptionV3(data, address + " 서버에 데이터 전송 실패");
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
   - 예외는 별도의 패키지에 정의 (import 주의)
   - 오류 코드로 어떤 문제가 발생했는지 이해하는 것이 아니라 예외 그 자체로 어떤 오류가 발생했는지 알 수 있음
   - 연결 관련 오류 발생하면 ConnectExceptionV3를 던지고, 전송 관련 오류가 발생하면 SendExceptionV3를 던짐

7. NetworkServiceV3_1
```java
package exception.ex3;

import exception.ex3.exception.ConnectExceptionV3;
import exception.ex3.exception.SendExceptionV3;

public class NetworkServiceV3_1 {
    public void sendMessage(String data) {
        String address = "http://example.com";

        NetworkClientV3 client = new NetworkClientV3(address);
        client.initError(data); // 추가

        try {
            client.connect();
            client.send(data);
        } catch (ConnectExceptionV3 e) {
            System.out.println("[연결 오류] 주소 : " + e.getAddress() + ", 메시지 : " + e.getMessage());
        } catch (SendExceptionV3 e) {
            System.out.println("[연결 오류] 주소 : " + e.getSendData() + ", 메시지 : " + e.getMessage());
        } finally {
            client.disconnect();
        }
    }
}
```
   - 예외 클래스를 각각의 예외 상황에 맞추어 만들면, 각 필요에 맞는 예외를 잡아서 처리할 수 있음
   - 예를 들면 e.getAddress(), e.getSendData()와 같이 각각의 예외 클래스가 가지는 고유의 기능을 활용할 수 있음
   - catch (ConnectExceptionV3 e) : 연결 예외를 잡고, 해당 예외가 제공하는 기능을 사용해서 정보를 출력
   - catch (SendExceptionV3 e) : 전송 예외를 잡고, 해당 예외가 제공하는 기능을 사용해서 정보를 출력

8. MainV3
```java
package exception.ex3;

import java.util.Scanner;

public class MainV3 {
    public static void main(String[] args) {
        NetworkServiceV3_1 networkService = new NetworkServiceV3_1();

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
[전송 오류] 전송 데이터: error2, 메시지: https://example.com 서버에 데이터 전송 실패 : error2
https://example.com 서버 연결 해제

전송할 문자: exit
프로그램을 정상 종료합니다.
```
  - 실행 결과를 보면 ConnectExceptionV3, SendExceptionV3이 발생한 각각의 경우에 출력된 오류 메시지가 다른 것을 확인할 수 있음
