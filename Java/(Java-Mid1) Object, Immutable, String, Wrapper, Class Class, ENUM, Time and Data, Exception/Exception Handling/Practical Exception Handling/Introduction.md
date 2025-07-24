-----
### 실무 예외 처리 방안 1 - 설명
-----
1. 처리할 수 없는 예외
   - 예를 들어서 상대 네트워크 서버에 문제가 발생해서 통신이 불가능하거나, 데이터베이스 서버에 문제가 발생해서 접이 안되면, 애플리케이션에서 연결 오류, 데이터베이스 접속 실패와 같은 예외가 발생
   - 이렇게 시스템 오류 때문에 발생한 예외들은 대부분 예외를 잡아도 해결할 수 있는 것이 거의 없음
   - 예외를 잡아서 다시 호출을 시도해도 같은 오류가 반복될 뿐임
   - 이런 경우 고객에게는 "현재 시스템에 문제가 있습니다."라는 오류 메시지를 보여주고, 만약 웹이라면 오류 페이지를 보여주면 됨
   - 그리고 내부 개발자가 문제 상황을 빠르게 인지할 수 있도록, 오류에 대한 로그를 남겨두어야 함

2. 체크 예외의 부담
   - 체크 예외는 개발자가 실수로 놓칠 수 있는 예외들을 컴파일러가 체크해주기 때문에 오래전부터 많이 사용됨
   - 그런데 앞서 설명한 것 처럼 처리할 수 없는 예외가 많아지고, 또 프로그램이 점점 복잡해지면서 체크 예외를 사용하는 것이 점점 더 부담스러워짐

3. 체크 예외 이용 시나리오
<div align="center">
<img src="https://github.com/user-attachments/assets/58250132-d60c-4e62-96a4-a57364fb9b2f">
</div>

   - 실무에서는 수 많은 라이브러리를 사용하고, 또 다양한 외부 시스템과 연동
   - 사용하는 각각의 클래스들이 자신만의 예외를 모두 체크 예외로 만들어서 전달한다고 가정

<div align="center">
<img src="https://github.com/user-attachments/assets/3a68c23f-dfa7-4486-a64c-d9a96db26772">
</div>

   - Service는 호출하는 곳에서 던지는 체크 예외들을 처리해야 함
   - 만약 처리할 수 없다면 밖으로 던져야 함

4. 모든 체크 예외를 잡아서 처리하는 예시
```java
try {
} catch (NetworkException) {...}
} catch (DatabaseException) {...}
} catch (XxxException) {...}
```
   - 그런데 앞서 설명했듯이 상대 네트워크 서버가 내려갔거나, 데이터베이스 서버에 문제가 발생한 경우 Service에서 예외를 잡아도 복구할 수 없음
   - Service 에서는 어차피 본인이 처리할 수 없는 예외들이기 때문에 밖으로 던지는 것이 더 나은 결정

5. 모든 체크 예외를 던지는 예시
```java
class Service {
     void sendMessage(String data) throws NetworkException, DatabaseException, ... {
           ...
     }
}
```
   - 이렇게 모든 체크예외를 하나씩 다 밖으로 던져야함
   - 라이브러리가 늘어날 수 록 다루어야 하는 예외도 더 많아짐 : 개발자 입장에서 이것은 상당히 번거로운 일

6. 문제는 여기서 끝이 아님 : 만약 중간에 Facade 라는 클래스가 있다고 가정
<div align="center">
<img src="https://github.com/user-attachments/assets/8a1870ff-1aeb-408f-b99a-32301f2f74c3">
</div>

   - Facade 클래스에서도 이런 예외들을 복구할 수 없음 : Facade 클래스도 예외를 밖으로 던져야 함
   - 결국 중간에 모든 클래스에서 예외를 계속 밖으로 던지는 지저분한 코드가 만들어짐
   - throws로 발견한 모든 예외를 다 밖으로 던지는 것
```java
class Facade {
     void send() throws NetworkException, DatabaseException, ...
}

class Service {
     void sendMessage(String data) throws NetworkException, DatabaseException, ...
}
```

7. throws Exception
  - 개발자는 본인이 다룰 수 없는 수 많은 체크 예외 지옥에 빠지게 됨
```java
class Facade {
     void send() throws Exception
}

class Service {
     void sendMessage(String data) throws Exception
}
```
   - Exception은 애플리케이션에서 일반적으로 다루는 모든 예외의 부모
   - 따라서 이렇게 한 줄만 넣으면 모든 예외를 다 던질 수 있음
   - 이렇게 하면 Exception은 물론이고 그 하위 타입인 NetworkException, DatabaseException도 함께 던지게 됨
   - 그리고 이후에 예외가 추가되더라도 throws Exception은 변경하지 않고 그대로 유지할 수 있음
   - 코드가 깔끔해지는 것 같지만 이 방법에는 치명적인 문제가 있음

8. throws Exception의 문제
   - Exception은 최상위 타입이므로 모든 체크 예외를 다 밖으로 던지는 문제가 발생
   - 결과적으로 체크 예외의 최상위 타입인 Exception을 던지게 되면 다른 체크 예외를 체크할 수 있는 기능이 무효화 되고, 중요한 체크 예외를 다 놓치게 됨
   - 중간에 중요한 체크 예외가 발생해도 컴파일러는 Exception을 던지기 때문에 문법에 맞다고 판단해서 컴파일 오류가 발생하지 않음
   - 이렇게 하면 모든 예외를 다 던지기 때문에 체크 예외를 의도한 대로 사용하는 것이 아님
   - 따라서 꼭 필요한 경우가 아니면 이렇게 Exception 자체를 밖으로 던지는 것은 좋지 않은 방법

9. 문제 정리
   - 처리할 수 없는 예외 : 예외를 잡아서 복구할 수 있는 예외보다 복구할 수 없는 예외가 더 많음
   - 체크 예외의 부담 : 처리할 수 없는 예외는 밖으로 던져야 함
     + 체크 예외이므로 throws에 던질 대상을 일일이 명시해야 함
     + 사실 Service를 개발하는 개발자 입장에서 수 많은 라이브러리에서 쏟아지는 모든 예외를 다 다루고 싶지는 않을 것
     + 특히 본인이 해결할 수 도 없는 모든 예외를 다 다루고 싶지는 않을 것이므로, 본인이 해결할 수 있는 예외만 잡아서 처리하고, 본인이 해결할 수 없는 예외는 신경쓰지 않는 것이 더 나은 선택일 수 있음

10. 언체크(런타임) 예외 사용 사나리오
<div align="center">
<img src="https://github.com/user-attachments/assets/9879d224-f704-4d13-bfbd-c3629c2635ef">
</div>

   - 이번에는 Service에서 호출하는 클래스들이 언체크(런타임) 예외를 전달한다고 가정
   - NetworkException, DatabaseException 은 잡아도 복구할 수 없음 : 언체크 예외이므로 이런 경우 무시하면 됨
   - 언체크 예외를 던지는 예시
```java
class Service {
     void sendMessage(String data) {
         ...
     }
}
```
   - 언체크 예외이므로 throws를 선언하지 않아도 됨
   - 사용하는 라이브러리가 늘어나서 언체크 예외가 늘어도 본인이 필요한 예외만 잡으면 되고, throws를 늘리지 않아도 됨

11. 일부 언체크 예외를 잡아서 처리하는 예시
```java
try {
} catch (XxxException) {...}
```
   - 앞서 설명했듯이 상대 네트워크 서버가 내려갔거나, 데이터베이스 서버에 문제가 발생한 경우 Service에서 예외를 잡아도 복구할 수 없음
   - Service에서는 어차피 본인이 처리할 수 없는 예외들이기 때문에 밖으로 던지는 것이 더 나은 결정
   -  언체크 예외는 잡지 않으면 throws 선언이 없어도 자동으로 밖으로 던짐
   - 만약 일부 언체크 예외를 잡아서 처리할 수 있다면 잡아서 처리하면 됨

12. 예외 공통 처리
    - 이렇게 처리할 수 없는 예외들은 중간에 여러곳에서 나누어 처리하기 보다는 예외를 공통으로 처리할 수 있는 곳을 만들어서 한 곳에서 해결하면 됨
    - 어차피 해결할 수 없는 예외들이기 때문에 이런 경우 고객에게는 현재 시스템에 문제가 있습니다. 라고 오류 메시지를 보여주고, 만약 웹이라면 오류 페이지를 보여주면 됨
    - 그리고 내부 개발자가 지금의 문제 상황을 빠르게 인지할 수 있도록, 오류에 대한 로그를 남겨두면 됨
    - 이런 부분은 공통 처리가 가능
