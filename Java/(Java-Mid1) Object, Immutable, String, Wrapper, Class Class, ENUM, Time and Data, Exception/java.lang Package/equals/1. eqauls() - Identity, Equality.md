-----
### equals() - 1. 동일성과 동등성
-----
1. Object 는 동등성 비교를 위한 equals() 메서드를 제공
2. 자바는 두 객체가 같다라는 표현을 2가지로 분리해서 제공
   - 동일성(Identity) : == 연산자를 사용해서 두 객체의 참조가 동일한 객체를 가리키고 있는지 확인
   - 동등성(Equality) : equals() 메서드를 사용하여 두 객체가 논리적으로 동등한지 확인

3. 단어 정리
   - "동일" : 완전히 같음을 의미
   - "동등" : 같은 가치나 수준을 의미하지만 그 형태나 외관 등이 완전히 같지는 않을 수 있음
   - 쉽게 이야기해서 동일성은 물리적으로 같은 메모리에 있는 객체 인스턴스인지 참조값을 확인하는 것이고, 동등성은 논리적으로 같은지 확인하는 것
   - 동일성은 자바 머신 기준이고 메모리의 참조가 기준이므로 물리적이며,  반면 동등성은 보통 사람이 생각하는 논리적인 기준에 맞추어 비교

4. 예를 들어 같은 회원 번호를 가진 회원 객체가 2개 있다고 가정
```java
User a = new User("id-100") // 참조 x001
User b = new User("id-100") // 참조 x002
```
  - 이 경우 물리적으로 다른 메모리에 있는 다른 객체이지만, 회원 번호를 기준으로 생각해보면 논리적으로는 같은 회원으로 볼 수 있음 (주민등록번호가 같다고 가정해도 됨)
   - 따라서 동일성은 다르지만, 동등성은 같음

5 문자의 경우도 동일
```java
String s1 = "hello";
String s2 = "hello";
```
   - 이 경우 물리적으로는 각각의 "hello" 문자열이 다른 메모리에 존재할 수 있지만, 논리적으로는 같은 "hello" 라는 문자열 (사실 이 경우 자바가 같은 메모리를 사용하도록 최적화)
   - 예제를 통해서 동일성과 동등성을 비교

6. UserV1 (/lang/object/eqauls)
```java
package lang.object.equals;

public class UserV1 {
    private String id;

    public UserV1(String id) {
        this.id = id;
    }
}
```

7. EqualsMainV1
```java
package lang.object.equals;

public class EqualsMainV1 {
    public static void main(String[] args) {
        UserV1 user1 = new UserV1("id-100");
        UserV1 user2 = new UserV1("id-100");

        System.out.println("identity = " + (user1 == user2));
        System.out.println("eqaulity = " + (user1.equals(user2)));
    }
}
```
  - 실행 결과
```
identity = false
eqaulity = false
```

<div align="center">
<img src="https://github.com/user-attachments/assets/3f289d11-3eb9-41b6-b793-b28794350ea8">
</div>

   - 동일성 비교
```java
user1 == user2
x001 == x002

false // 결과
```

   - 동등성 비교 : Object.equals() 메서드
```java
public boolean equals(Object obj) {
     return (this == obj);
}
```
   - Object가 기본으로 제공하는 equals()는 ==으로 동일성 비교를 제공
   - equals 실행 순서 예시
```java
user1.equals(user2)

return (user1 == user2) // Object.equals 메서드 안

return (x001 == x002) // Object.equals 메서드 안

return false

false
```

8. 동등성이라는 개념은 각각의 클래스 마다 다름
9. 어떤 클래스는 주민등록번호를 기반으로 동등성을 처리할 수 있고, 어떤 클래스는 고객의 연락처를 기반으로 동등성을 처리할 수 있음
10. 어떤 클래스는 회원 번호를 기반으로 동등성을 처리할 수 있음
11. 따라서 동등성 비교를 사용하고 싶으면 equals() 메서드를 재정의해야 하며, 그렇지 않으면 Object 는 동일성 비교를 기본으로 제공
