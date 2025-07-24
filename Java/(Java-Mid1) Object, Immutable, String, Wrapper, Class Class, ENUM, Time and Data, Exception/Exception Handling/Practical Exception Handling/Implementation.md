-----
### 실무 예외 처리 방안 2 - 구현
-----
1. 언체크 예외로 만들고, 또 해결할 수 없는 예외들을 공통으로 처리
<div align="center">
<img src="https://github.com/user-attachments/assets/92672521-56ee-4771-b517-7a7f711dc9c6">
</div>

   - NetworkClientExceptionV4는 언체크 예외인 RuntimeException을 상속 받음
   - NetworkClientExceptionV4와 자식은 모두 언체크(런타임) 예외

2. NetworkClientExceptionV4 (/exception/ex4/exception)
```java
package exception.ex4.exception;

public class NetworkClientExceptionV4 extends RuntimeException {
    public NetworkClientExceptionV4(String message) {
        super(message);
    }
}
```

3. ConnectExceptionV4
```java
package exception.ex4.exception;

public class ConnectExceptionV4 extends NetworkClientExceptionV4 {
    private final String address;

    public ConnectExceptionV4(String address, String message) {
        super(message);
        this.address = address;
    }
    
    public String getAddress() {
        return address;
    }
}
```

4. SendExceptionV4
```java
package exception.ex4.exception;

public class SendExceptionV4 extends NetworkClientExceptionV4 {
    private final String sendData;

    public SendExceptionV4(String sendData, String message) {
        super(message);
        this.sendData = sendData;
    }

    public String getSendData() {
        return sendData;
    }
}
```

5. NetworkClientV4 (/exception/ex4)
```java
package exception.ex4;

import exception.ex4.exception.ConnectExceptionV4;
import exception.ex4.exception.SendExceptionV4;

public class NetworkClientV4 {
    private final String address;

    private boolean connectError;
    private boolean sendError;


    public NetworkClientV4(String address) {
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
}
```
   - 언체크 예외이므로 throws 를 사용하지 않음

6. NeworkServiceV4
```java
package exception.ex4;

public class NetworkServiceV4 {
    public void sendMessage(String data) {
        String address = "http://example.com";

        NetworkClientV4 client = new NetworkClientV4(address);
        client.initError(data); // 추가

        try {
            client.connect();
            client.send(data);
        }  finally {
            client.disconnect();
        }
    }
}
```
   - NetworkServiceV4는 발생하는 예외인 ConnectExceptionV4, SendExceptionV4를 잡아도 해당 오류들을 복구할 수 없으므로, 따라서 예외를 밖으로 던짐
   - 언체크 예외이므로 throws를 사용하지 않음
   - 사실 NetworkServiceV4 개발자 입장에서는 해당 예외들을 복구할 수 없음 : 따라서 해당 예외들을 생각하지 않는 것이 더 나은 선택일 수 있음
     + 해결할 수 없는 예외들은 다른 곳에서 공통으로 처리
   - 이런 방식 덕분에 NetworkServiceV4는 해결할 수 없는 예외 보다는 본인 스스로의 코드에 더 집중할 수 있음 : 따라서 코드가 깔끔해짐

7. MainV4
```java
package exception.ex4;


import exception.ex4.exception.SendExceptionV4;

import java.util.Scanner;

public class MainV4 {
    public static void main(String[] args) {
        NetworkServiceV4 networkService = new NetworkServiceV4();

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
   - 공통 예외 처리 : 여기에 예외를 공통으로 처리하는 부분이 존재
```java
try {
   networkService.sendMessage(input);
} catch (Exception e) { // 모든 예외를 잡아서 처리
   exceptionHandler(e);
}
```
  
  - Exception을 잡아서 지금까지 해결하지 못한 모든 예외를 여기서 공통으로 처리
     + Exception을 잡으면 필요한 모든 예외를 잡을 수 있음
   - 예외도 객체이므로 공통 처리 메서드인 exceptionHandler(e)에 예외 객체를 전달

  - exceptionHandler()
     + 해결할 수 없는 예외가 발생하면 사용자에게는 시스템 내에 알 수 없는 문제가 발생했다고 알리는 것이 좋음
     + 사용자가 디테일한 오류 코드나 오류 상황까지 모두 이해할 필요는 없음
       * 예를 들어서 사용자는 데이터베이스 연결이 안되서 오류가 발생한 것인지, 네트워크에 문제가 있어서 오류가 발생한 것인지 알 필요는 없음
       * 개발자는 빨리 문제를 찾고 디버깅 할 수 있도록 오류 메시지를 남겨두어야 함
     + 예외도 객체이므로 필요하면 instanceof와 같이 예외 객체의 타입을 확인해서 별도의 추가 처리를 할 수 있음

   - e.printStackTrace() : 예외 메시지와 스택 트레이스를 출력
     + 이 기능을 사용하면 예외가 발생한 지점을 역으로 추적할 수 있음
     + 참고로 예제에서는 e.printStackTrace(System.out)을 사용해서 표준 출력으로 보냄.  
     + e.printStackTrace()를 사용하면 System.err 이라는 표준 오류에 결과를 출력
     + IDE에서는 System.err 로 출력하면 출력 결과를 빨간색으로 보여줌 (일반적으로 이 방법을 사용)

8. 참고
   - System.out, System.err 둘다 결국 콘솔에 출력되지만, 서로 다른 흐름을 통해서 출력
   - 따라서 둘을 함께 사용하면 출력 순서를 보장하지 않으므로 출력 순서가 꼬여서 보일 수 있음
   - 실무에서는 System.out이나 System.err을 통해 콘솔에 무언가를 출력하기 보다는, 주로 Slf4J, logback 같은 별도의 로그 라이브러리를 사용해서 콘솔과 특정 파일에 함께 결과를 출력
   - 그런데 e.printStackTrace()를 직접 호출하면 결과가 콘솔에만 출력
   - 이렇게 되면 서버에서 로그를 확인하기 어려움
   - 서버에서는 파일로 로그를 확인해야 하므로, 따라서 콘솔에 바로 결과를 출력하는 e.printStackTrace()는 잘 사용하지 않음
   - 대신에 로그 라이브러리를 통해서 예외 스택 트레이스를 출력

9. 실행 결과
```
전송할 문자: hello
https://example.com 서버 연결 성공
https://example.com 서버에 데이터 전송: hello
https://example.com 서버 연결 해제

전송할 문자: error1
https://example.com 서버 연결 해제
사용자 메시지: 죄송합니다. 알 수 없는 문제가 발생했습니다.
==개발자용 디버깅 메시지==
exception.ex4.exception.ConnectExceptionV4: https://example.com 서버 연결 실패
at exception.ex4.NetworkClientV4.connect(NetworkClientV4.java:18)
at exception.ex4.NetworkServiceV4.sendMessage(NetworkServiceV4.java:12)
at exception.ex4.MainV4.main(MainV4.java:22)

전송할 문자: error2
https://example.com 서버 연결 성공
https://example.com 서버 연결 해제
사용자 메시지: 죄송합니다. 알 수 없는 문제가 발생했습니다.
==개발자용 디버깅 메시지==
exception.ex4.exception.SendExceptionV4: https://example.com 서버에 데이터 전송 실패: error2
at exception.ex4.NetworkClientV4.send(NetworkClientV4.java:26)
at exception.ex4.NetworkServiceV4.sendMessage(NetworkServiceV4.java:13)
at exception.ex4.MainV4.main(MainV4.java:22)
[전송 오류] 전송 데이터: error2

전송할 문자: exit
프로그램을 정상 종료합니다.
```
   - 실행 결과를 보면 공통 예외 처리가 잘 적용된 것을 확인할 수 있음
   - 추가로 "error2"의 경우 SendExceptionV4이발생하면서 [전송 오류]... 라는 추가 로그가 남은 것도 확인할 수 있음
