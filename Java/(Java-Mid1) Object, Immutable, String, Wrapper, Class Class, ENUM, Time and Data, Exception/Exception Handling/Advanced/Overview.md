-----
### 예외 처리 도입 1 - 시작
-----
<div align="center">
<img src="https://github.com/user-attachments/assets/36bfb2df-37f7-4436-b7ba-2b52a23ea825">
</div>

1. 문제점
   - 정상 흐름과 예외 흐름이 섞여 있기 때문에 코드를 한눈에 이해하기 어려움
   - 심지어 예외 흐름이 더 많은 코드 분량을 차지

<div align="center">
<img src="https://github.com/user-attachments/assets/4bb21f1f-abad-474a-9854-61687c97e66d">
</div>

2. NetworkClientExceptionV2 (/exception/ex2)
```java
package exception.ex2;

public class NetworkClientExceptionV2 extends Exception {
    private String errorCode;

    public NetworkClientExceptionV2(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
    
    public String getErrorCode() {
        return errorCode;
    }
}
```
   - 예외도 객체 : 따라서 필요한 필드와 메서드를 가질 수 있음
   - 오류 코드
      + 이전에는 오류 코드(errorCode)를 반환 값으로 리턴해서, 어떤 오류가 발생했는지 구분
      + 여기서는 어떤 종류의 오류가 발생했는지 구분하기 위해 예외 안에 오류 코드를 보관
  
   - 오류 메시지
      + 오류 메시지(message)에는 어떤 오류가 발생했는지 보고 이해할 수 있는 설명을 저장
      + 오류 메시지는 상위 클래스인 Throwable에서 기본으로 제공하는 기능을 사용

3. NetworkClientV2
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
            throw new NetworkClientExceptionV2("sendError", address + " 서버에 데이터 전송 실패");
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
   - 기존의 코드와 대부분 같지만, 오류가 발생했을 때 오류 코드를 반환하는 것이 아니라 예외를 던짐
   - 따라서 반환 값을 사용하지 않아도 되므로, 여기서는 반환 값을 void로 처리
   - 이전에는 반환 값으로 성공, 실패 여부를 확인해야 했지만, 예외 처리 덕분에 메서드가 정상 종료되면 성공이고, 예외가 던져지면 예외를 통해 실패를 확인할 수 있음
   - 오류가 발생하면, 예외 객체를 만들고 거기에 오류 코드와 오류 메시지를 저장
   - 그리고 만든 예외 객체를 throw를 통해 던짐

4. NetworkServiceV2_1
```java
package exception.ex2;

import exception.ex1.NetworkClientV1;

public class NetworkServiceV2_1 {
    public void sendMessage(String data) throws NetworkClientExceptionV2 {
        String address = "http://example.com";

        NetworkClientV2 client = new NetworkClientV2(address);
        client.initError(data); // 추가

        client.connect();
        client.send(data);
        client.disconnect();
    }
}
```
  - 여기서는 예외를 별도로 처리하지 않고, throws를 통해 밖으로 던짐

5. MainV2
```java
package exception.ex2;

import exception.ex1.NetworkServiceV1_3;

import java.util.Scanner;

public class MainV2 {
    public static void main(String[] args) throws NetworkClientExceptionV2 {
        NetworkServiceV2_1 networkService = new NetworkServiceV2_1();

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
   - 예외를 처리하지 않고, throws를 통해 밖으로 던짐
   - 실행 결과
```
전송할 문자 : hello
http://example.com 서버 연결 성공
http://example.com 서버에 전송 : hello
http://example.com 서버 연결 해제

전송할 문자 : exit
프로그램을 정상 종료합니다.
```
```
전송할 문자 : error1
Exception in thread "main" exception.ex2.NetworkClientExceptionV2: http://example.com 서버 연결 실패
	at exception.ex2.NetworkClientV2.connect(NetworkClientV2.java:16)
	at exception.ex2.NetworkServiceV2_1.sendMessage(NetworkServiceV2_1.java:12)
	at exception.ex2.MainV2.main(MainV2.java:21)
```
   - error1이면 연결 실패가 발생
   - 모든 곳에서 발생한 예외를 잡지 않았기 때문에 결과적으로 main() 밖으로 예외가 던져짐
   - main() 밖으로 예외가 던져지면 예외 메시지와 예외를 추적할 수 있는 스택 트레이스를 출력하고 프로그램을 종료
```
전송할 문자 : error2
http://example.com 서버 연결 성공
Exception in thread "main" exception.ex2.NetworkClientExceptionV2: http://example.com 서버에 데이터 전송 실패
	at exception.ex2.NetworkClientV2.send(NetworkClientV2.java:25)
	at exception.ex2.NetworkServiceV2_1.sendMessage(NetworkServiceV2_1.java:13)
	at exception.ex2.MainV2.main(MainV2.java:21)
```
  - error2이면 데이터 전송 실패
  - 모든 곳에서 발생한 예외를 잡지 않았기 때문에 결과적으로 main() 밖으로 예외가 던져짐
  - main() 밖으로 예외가 던져지면 예외 메시지와 예외를 추적할 수 있는 스택 트레이스를 출력하고 프로그램을 종료

6. 남은 문제
   - 예외 처리를 도입했지만, 아직 예외가 복구되지 않음 : 따라서 예외가 발생하면 발생하면 프로그램이 종료
   - 사용 후에는 반드시 disconnect()를 호출해서 연결을 해제해야 함
