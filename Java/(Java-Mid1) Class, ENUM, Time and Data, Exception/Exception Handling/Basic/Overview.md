-----
### 예외 처리가 필요한 이유 1 - 시작
-----
1. 예외 처리가 필요한 이유를 알아보기 위해 간단한 예제 프로그램 제작
2. 이 프로그램은 사용자의 입력을 받고, 입력 받은 문자를 외부 서버에 전송하는 프로그
3. 프로그램 구성도
<div align="center">
<img src="https://github.com/user-attachments/assets/a7af12a1-e470-4063-b9a5-6aa43b3c7d13">
</div>

4. 실행 예시
```
전송할 문자: hello
http://example.com 서버 연결 성공
http://example.com 서버에 데이터 전송: hello
http://example.com 서버 연결 해제
```

5. 클래스 설명
   - NetworkClient : 외부 서버와 연결하고, 데이터를 전송하고, 연결을 종료하는 기능을 제공
   - NetworkService : NetworkClient를 사용해서 데이터를 전송
     + NetworkClient를 사용하려면 연결, 전송, 연결 종료와 같은 복잡한 흐름을 제어해야 하는데, 이런 부분을 NetworkService가 담당
   - Main : 사용자의 입력을 받음
    + 전체 흐름 : Main을 통해 사용자의 입력을 받으면 사용자의 입력을 NetworkService에 전달
   - NetworkService는 NetworkClient를 사용해서 외부 서버에 연결하고, 데이터를 전송하고, 전송이 완료되면 연결을 종료

6. NetworkService 예시 코드
```java
String address = "http://example.com";

NetworkClientV1 client = new NetworkClientV1(address);

client.connect();
client.send(data);
client.disconnect();
```

7. NetworkClient 사용법
   - connect()를 먼저 호출해서 서버와 연결
   - send(data)로 연결된 서버에 메시지를 전송
   - disconnect()로 연결을 해제

8. NetworkClient 사용 시 주의 사항
   - connect()가 실패한 경우 send()를 호출하면 안 됨
   - 사용 후에는 반드시 disconnect()를 호출해서 연결을 해제해야 함
   - connect(), send() 호출에 오류가 있어도 disconnect()는 반드시 호출해야 함

9. NetworkServiceV0
   - NetworkClientV0 (/exception/ex0)
```java
package exception.ex0;

public class NetworkClientV0 {
    private final String address;

    public NetworkClientV0(String address) {
        this.address = address;
    }
    
    public String connect() {
        // 연결 성공
        System.out.println(address + " 서버 연결 성공");
        return "success";
    }
    
    public String send(String data) {
        // 전송 성공
        System.out.println(address + " 서버에 전송 : " + data);
        return "success";
    }
    
    public void disconnect() {
        System.out.println(address + " 서버 연결 해제");
    }
}
```
   - String address : 접속할 외부 서버 주소
   - connect() : 외부 서버에 연결
   - send(String data) : 연결한 외부 서버에 데이터를 전송
   - disconnect() : 외부 서버와 연결을 해제
   - 실제로 외부 네트워크에 접속하는 코드는 없지만, 외부 네트워크와 접속한다고 가정
   - NetworkServiceV0
```java
package exception.ex0;

public class NetworkServiceV0 {
    public void sendMessage(String data) {
        String address = "http://example.com";

        NetworkClientV0 client = new NetworkClientV0(address);

        client.connect();
        client.send(data);
        client.disconnect();
    }
}
```
   - NetworkService는 복잡한 NetworkClient 사용 로직을 처리
   - NetworkClient를 생성하고, 이때 접속할 외부 서버 주소도 함께 전달
   - NetworkClient를 사용하는데 필요한 connect(), send(data), disconnect()를 순서대로 호출
   - MainV0
```java
package exception.ex0;

import java.util.Scanner;

public class MainV0 {
    public static void main(String[] args) {
        NetworkServiceV0 networkService = new NetworkServiceV0();
        
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
   - 전송할 문자를 Scanner를 통해서 입력 받음
   - NetworkService.sendMessage()를 통해 메시지를 전달
   - exit를 입력하면 프로그램을 정상 종료
   - 실행 결과
```
전송할 문자 : hello
http://example.com 서버 연결 성공
http://example.com 서버에 전송 : hello
http://example.com 서버 연결 해제

전송할 문자 : exit
프로그램을 정상 종료합니다.
```

-----
### 예외 처리가 필요한 이유 2 - 오류 상황 만들기
-----
1. 외부 서버와 통신할 때는 다음과 같은 다양한 문제들이 발생
   - 외부 서버와 연결에 실패 (네트워크 오류 등)
   - 데이터 전송에 문제가 발생

2. 물론 실제 외부 서버와 통신하는 것이 아니기 때문에 이런 오류가 발생하지는 않음
   - 대신에 오류 상황을 가상으로 시뮬레이션

3. NetworkServiceV1_1 : 오류 상황을 시뮬레이션 할 수 있는 다음 코드를 생성
4. 오류 상황을 시뮬레이션 하기 위해 사용자의 입력 값을 활용
   - 연결 실패 : 사용자가 입력하는 문자에 "error1" 단어가 있으면 연결에 실패 (오류 코드는 "connectError")
   - 전송 실패 : 사용자가 입력하는 문자에 "error2" 단어가 있으면 데이터 전송에 실패 (오류 코드는 "sendError")

5. NetworkClientV1
```java
package exception.ex0;

public class NetworkClientV1 {
    private final String address;

    private boolean connectError;
    private boolean sendError;
    
    
    public NetworkClientV1(String address) {
        this.address = address;
    }

    public String connect() {
        if(connectError) {
            System.out.println(address + " 서버 연결 실패");
            return "connectError";
        }
        
        // 연결 성공
        System.out.println(address + " 서버 연결 성공");
        return "success";
    }

    public String send(String data) {
        if(sendError) {
            System.out.println(address + " 서버에 데이터 전송 실패 : " + data);
            return "sendError";
        }
        
        // 전송 성공
        System.out.println(address + " 서버에 전송 : " + data);
        return "success";
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
   - NetworkClientV1에는 connectError, sendError 필드가 존재
      + connectError : 이 필드의 값이 true가 되면 연결에 실패하고, connectError 오류 코드를 반환
      + sendError : 이 필드의 값이 true가 되면 데이터 전송에 실패하고, sendError 오류 코드를 반환
      + 문제가 없으면 success 코드를 반환

   - initError(String data)
      + 이 메서드를 통해서 connectError, sendError 필드의 값을 true 설정 가능
      + 사용자 입력 값에 "error1"이 있으면 connectError 오류가 발생하고, "error2"가 있으면 sendError 오류가 발생

6. NetworkServiceV1_1
```java
package exception.ex0;

public class NetworkServiceV1_1 {
    public void sendMessage(String data) {
        String address = "http://example.com";

        NetworkClientV1 client = new NetworkClientV1(address);
        client.initError(data); // 추가
        
        client.connect();
        client.send(data);
        client.disconnect();
    }
}
```
   - client.initError(data) : 사용자의 입력 값을 기반으로 오류를 활성화

7. MainV1
```java
package exception.ex0;

import java.util.Scanner;

public class MainV1 {
    public static void main(String[] args) {
        NetworkServiceV1_1 networkService = new NetworkServiceV1_1();

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
http://example.com 서버 연결 실패
http://example.com 서버에 전송 : error1
http://example.com 서버 연결 해제

전송할 문자 : error2
http://example.com 서버 연결 성공
http://example.com 서버에 데이터 전송 실패 : error2
http://example.com 서버 연결 해제

전송할 문자 : exit
프로그램을 정상 종료합니다.
```
   - error1 입력 : 서버 연결에 실패
   - error2 입력 : 데이터 전송에 실패

8. 남은 문제
   - 연결이 실패하면 데이터를 전송하지 않아야 하는데, 여기서는 데이터를 전송

9. 추가 요구 사항
   - 오류가 발생했을 때 어떤 오류가 발생했는지 자세한 내역을 남기면 이후 디버깅에 도움이 될 것
   - 오류 로그를 남겨야 함

-----
### 예외 처리가 필요한 이유 3 - 반환 값으로 예외 처리
-----
1. 연결에 실패하면 데이터를 전송하지 않아야 하는데, 여기서는 데이터를 전송함
2. 또한, 오류 로그를 남겨야 함
3. NetworkClientV1은 connectError, sendError와 같은 오류 코드를 문자열로 반환해주고 있음
   - 이 반환 값을 사용해서 예외 상황을 처리

4. NetworkServiceV1_2
```java
package exception.ex0;

public class NetworkServiceV1_2 {
    public void sendMessage(String data) {
        String address = "http://example.com";

        NetworkClientV1 client = new NetworkClientV1(address);
        client.initError(data); // 추가

        String connectResult = client.connect();
        if(isError(connectResult)) {
            System.out.println("[네트워크 오류 발생] 오류 코드 : " + connectResult);
            return;
        }

        String sendResult = client.send(data);
        if(isError(sendResult)) {
            System.out.println("[네트워크 오류 발생] 오류 코드 : " + sendResult);
            return;
        }
        
        client.disconnect();
    }
    
    private static boolean isError(String resultCode) {
        return !resultCode.equals("success");
    }
}
```
   - NetworkService는 NetworkClient를 사용하는 전체 흐름을 관리
   - 오류가 발생한 경우 오류 코드를 출력으로 남김
   - 오류가 발생한 경우 프로그램이 더 이상 진행되지 않도록 return 을 사용해서 중지
   - 따라서 연결에 실패하면 데이터를 전송하는 문제가 해결

5. MainV1 코드 변경
```java
package exception.ex0;

import java.util.Scanner;

public class MainV1 {
    public static void main(String[] args) {
        // NetworkServiceV1_1 networkService = new NetworkServiceV1_1();
        NetworkServiceV1_2 networkService = new NetworkServiceV1_2();

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
http://example.com 서버 연결 실패
[네트워크 오류 발생] 오류 코드 : connectError

전송할 문자 : error2
http://example.com 서버 연결 성공
http://example.com 서버에 데이터 전송 실패 : error2
[네트워크 오류 발생] 오류 코드 : sendError

전송할 문자 : exit
프로그램을 정상 종료합니다.
```

   - NetworkClient 사용시 주의 사항
     + connect()가 실패한 경우 send()를 호출하면 안 됨 : 해결
     + 사용 후에는 반드시 disconnect()를 호출해서 연결을 해제해야 함 : 해결 안됨
     + connect(), send() 호출에 오류가 있어도 disconnect()는 반드시 호출해야 함
   - connect()가 실패한 경우 send()를 호출하면 안되는 부분은 해결
   - 하지만 사용 후에는 disconnect()를 반드시 호출해야 하는 문제는 해결되지 않음
   - error2 를 보면 데이터 전송에 실패하는 경우, 연결이 해제 되지 않음
   - 계속 이렇게 두면 네트워크 연결 자원이 고갈될 수 있음

6. 참고
   - 자바의 경우 GC가 있기 때문에 JVM 메모리에 있는 인스턴스는 자동으로 해제할 수 있음
   - 하지만 외부 연결과 같은 자바 외부의 자원은 자동으로 해제가 되지 않음
   - 따라서 외부 자원을 사용한 후에는 연결을 해제해서 외부 자원을 반드시 반납해야 함

7. NetworkServiceV1_3
   - disconnect()를 반드시 호출하도록 코드를 작성
```java
package exception.ex0;

public class NetworkServiceV1_3 {
    public void sendMessage(String data) {
        NetworkClientV1 client = new NetworkClientV1("http://example.com");
        client.initError(data); // 추가

        String connectResult = client.connect();
        if(isError(connectResult)) {
            System.out.println("[네트워크 오류 발생] 오류 코드 : " + connectResult);
        } else {
            String sendResult = client.send(data);
            if (isError(sendResult)) {
                System.out.println("[네트워크 오류 발생] 오류 코드 : " + sendResult);
            }
        }
        client.disconnect();
    }

    private static boolean isError(String resultCode) {
        return !resultCode.equals("success");
    }
}
```
  - 프로그램에서 return 문을 제거하고 if문으로 적절한 분기를 사용
   - connect()에 성공해서 오류가 없는 경우에만 send()를 호출
   - 중간에 return 하지 않으므로 마지막에 있는 disconnect()를 호출할 수 있음
   - 연결에 실패해도 disconnect() 호출
   - 데이터 전송에 실패해도 disconnect() 호출

8. MainV1 - 코드 변경
```java
package exception.ex0;

import java.util.Scanner;

public class MainV1 {
    public static void main(String[] args) {
        // NetworkServiceV1_1 networkService = new NetworkServiceV1_1();
        // NetworkServiceV1_2 networkService = new NetworkServiceV1_2();
        NetworkServiceV1_3 networkService = new NetworkServiceV1_3();

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
http://example.com 서버 연결 실패
[네트워크 오류 발생] 오류 코드 : connectError
http://example.com 서버 연결 해제

전송할 문자 : error2
http://example.com 서버 연결 성공
http://example.com 서버에 데이터 전송 실패 : error2
[네트워크 오류 발생] 오류 코드 : sendError
http://example.com 서버 연결 해제

전송할 문자 : exit
프로그램을 정상 종료합니다.
```

-----
### 정상 흐름과 예외 흐름
-----
1. 그런데 반환 값으로 예외를 처리하는 NetworkServiceV1_2, NetworkServiceV1_3 와 같은 코드들을 보면 정상 흐름과 예외 흐름이 전혀 분리되어 있지 않음
2. 어떤 부분이 정상 흐름이고 어떤 부분이 예외 흐름인지 이해하기가 너무 어려우며, 심지어 예외 흐름을 처리하는 부분이 더 많음
3. 정상 흐름 코드
```java
client.connect();
client.send(data);
client.disconnect();
```

4. 정상 흐름은 연결하고, 데이터를 전송하고, 연결을 종료하면 끝으로, 매우 단순하고 직관적
5. 정상 흐름 + 예외 흐름이 섞여 있는 코드 : NetworkServiceV1_2 코드
```java
String connectResult = client.connect();
if(isError(connectResult)) {
    System.out.println("[네트워크 오류 발생] 오류 코드 : " + connectResult);
    return;
}

String sendResult = client.send(data);
if(isError(sendResult)) {
    System.out.println("[네트워크 오류 발생] 오류 코드 : " + sendResult);
    return;
}

client.disconnect();
```

6. 정상 흐름 + 예외 흐름이 섞여 있는 코드 : NetworkServiceV1_3 코드
```java
String connectResult = client.connect();
if(isError(connectResult)) {
    System.out.println("[네트워크 오류 발생] 오류 코드 : " + connectResult);
} else {
    String sendResult = client.send(data);
    if (isError(sendResult)) {
        System.out.println("[네트워크 오류 발생] 오류 코드 : " + sendResult);
    }
}
client.disconnect();
```

  - 정상 흐름과 예외 흐름이 섞여 있기 때문에 코드를 한눈에 이해하기 어려움
  - 쉽게 이야기해서 가장 중요한 정상 흐름이 한눈에 들어오지 않음
  - 심지어 예외 흐름이 더 많은 코드 분량을 차지
  - 이런 문제를 해결하기 위해 바로 예외 처리 메커니즘이 존재
