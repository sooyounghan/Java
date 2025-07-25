-----
### 정적 중첩 클래스의 활용
-----
1. 정적 중첩 클래스 리팩토링 전 - NetworkMessage (/nested/nested/ex1)
```java
package nested.nested.ex1;

// Network 객체 안에서만 사용 가능
public class NetworkMessage {
    private String content;
    
    public NetworkMessage(String content) {
        this.content = content;
    }
    
    public void print() {
        System.out.println(content);
    }
}
```
   - NetworkMessage는 Network 객체 안에서만 사용되는 객체

2. Network
```java
package nested.nested.ex1;

public class Network {
    public void sendMessage(String test) {
        NetworkMessage networkMessage = new NetworkMessage(test);
        networkMessage.print();
    }
}
```
   - text를 입력 받아서 NetworkMessage를 생성하고 출력하는 단순한 기능을 제공

3. NetworkMain
```java
package nested.nested.ex1;

public class NetworkMain {
    public static void main(String[] args) {
        Network network = new Network();
        network.sendMessage("hello java");
    }
}
```
   - Network를 생성하고 network.sendMessage()를 통해 메시지를 전달
   - NetworkMain은 오직 Network 클래스만 사용 : NetworkMessage 클래스는 전혀 사용하지 않음
   - NetworkMessage는 오직 Network 내부에서만 사용
   - 실행 결과
```
hello java
```
   - ex1 패키지를 열어보면 다음 두 클래스가 보일 것 (main은 제외)
      + Network
      + NetworkMessage
    
   - Network 관련 라이브러리를 사용하기 위해서 ex1 패키지를 열어본 개발자는 아마도 두 클래스를 모두 확인해볼 것
   - 그리고 해당 패키지를 처음 확인한 개발자는 Network와 NetworkMessage를 둘 다 사용해야 할 지 생각할 것
   - NetworkMessage 에 메시지를 담아서 Network 에 전달해야 할지와 같은 여러가지 생각을 하거나, 아니면 NetworkMessage가 다른 여러 클래스에서 사용되는 것이라고 생각할 것


4. 정적 중첩 클래스로 리팩토링 후 - Network (/nested/nested/ex2)
```java
package nested.nested.ex2;

public class Network {
    public void sendMessage(String test) {
        NetworkMessage networkMessage = new NetworkMessage(test);
        networkMessage.print();
    }
    
    private static class NetworkMessage {
        private String content;
        
        public NetworkMessage(String content) {
            this.content = content;
        }
        
        public void print() {
            System.out.println(content);
        }
    }
}
```
   - NetworkMessage 클래스를 Network 클래스 안에 중첩해서 생성
   - NetworkMessage의 접근 제어자를 private 설정 : 따라서 외부에서 NetworkMessage에 접근할 수 없음
     + 예) new Network.NetworkMessage()처럼 접근할 수 없음

   - NetworkMain
```java
package nested.nested.ex2;

public class NetworkMain {
    public static void main(String[] args) {
        Network network = new Network();
        network.sendMessage("hello java");
    }
}
```
   - 실행 결과
```
hello java
```

   - ex2 패키지를 열어보면 다음 하나의 클래스가 보일 것 (main 은 제외) : Network
   - Network 관련 라이브러리를 사용하기 위해서 ex2 패키지를 열어본 개발자는 해당 클래스만 확인할 것
     + 추가로 NetworkMessage가 중첩 클래스에 private 접근 제어자로 되어 있는 것을 보고, Network 내부에서만 단독으로 사용하는 클래스라고 바로 인지할 수 있음

5. 중첩 클래스의 접근
   - 💡 나의 클래스에 포함된 중첩 클래스가 아니라 다른 곳에 있는 중첩 클래스에 접근할 때는 바깥클래스.중첩클래스로 접근해야 함
```java
NestedOuter.Nested nested = new NestedOuter.Nested();
```

   - 나의 클래스에 포함된 중첩 클래스에 접근할 때는 바깥 클래스 이름을 적지 않아도 됨
```java
public class Network {
   public void sendMessage(String text) {
       NetworkMessage networkMessage = new NetworkMessage(text);
   }

   private static class NetworkMessage {...}
}
```
   - 중첩 클래스(내부 클래스 포함)는 그 용도가 자신이 소속된 바깥 클래스 안에서 사용되는 것
   - 따라서 자신이 소속된 바깥 클래스가 아닌 외부에서 생성하고 사용하고 있다면, 이미 중첩 클래스의 용도에 맞지 않을 수 있음
   - 이때는 중첩 클래스를 밖으로 빼는 것이 더 나은 선택
