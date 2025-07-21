<img width="2014" height="942" alt="10" src="https://github.com/user-attachments/assets/dc97bbf7-c8a1-4ba7-9f7c-c6c42624912f" />-----
### 불변 객체 - 도입
-----
1. 공유된 객체의 값을 변경한 것에 문제가 발생
   - 앞의 예를 떠올려보면 a, b 는 처음 시점에는 둘다 "서울" 이라는 주소를 사용해야 함
   - 그리고 이후에 b의 주소를 "부산"으로 변경해야 함
```java
Address a = new Address("서울");
Address b = a;
```

   - 따라서 처음에는 b = a와 같이 "서울" 이라는 Address 인스턴스를 a, b 가 함께 사용하는 것이, 다음 코드와 같이 서로 다른 인스턴스를 사용하는 것 보다 메모리와 성능상 더 효율적
   - 인스턴스가 하나이니 메모리가 절약되고, 인스턴스를 하나 생성하지 않아도 되니 생성 시간이 줄어서 성능상 효율적
```java
Address a = new Address("서울");
Address b = new Address("서울");
```

   - 여기까지는 Address b = a 와 같이 공유 참조를 사용해도 아무런 문제가 없으며, 오히려 더 효율적
   - 이후에 b가 공유 참조하는 인스턴스의 값을 변경하기 때문에 문제가 발생
```java
b.setValue("부산"); // b의 값을 부산으로 변경해야함
System.out.println("부산 -> b");
System.out.println("a = " + a); // 사이드 이펙트 발생
System.out.println("b = " + b);
```

   - 자바에서 여러 참조형 변수가 하나의 객체(인스턴스)를 참조하는 공유 참조 문제는 피할 수 없음
   - 기본형과 다르게 참조형인 객체는 처음부터 여러 참조형 변수에서 공유될 수 있도록 설계되었으므로, 문제가 아님
   - 문제의 직접적인 원인은 공유될 수 있는 Address 객체의 값을 어디선가 변경했기 때문임
   - 만약 Address 객체의 값을 변경하지 못하게 설계했다면 이런 사이드 이펙트 자체가 발생하지 않을 것

2. 불변 객체 도입
   - 불변 객체(Immutable Object) : 객체의 상태(객체 내부의 값, 필드, 멤버 변수)가 변하지 않는 객체
   - ImmtuableAdress
```java
package lang.immutable.address;

public class ImmutableAddress {
    private final String value;

    public ImmutableAddress(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "ImmutableAddress{" +
                "value='" + value + '\'' +
                '}';
    }
}
```
   - 내부 값이 변경되면 안되므로, 따라서 value의 필드를 final로 선언
   - 값을 변경할 수 있는 setValue()를 제거
   - 이 클래스는 생성자를 통해서만 값을 설정할 수 있고, 이후에는 값을 변경하는 것이 불가능
   - 불변 클래스를 만드는 방법은 아주 단순 : 어떻게든 필드 값을 변경할 수 없게 클래스를 설계
   - RefMain2
```java
package lang.immutable.address;

public class RefMain2 {
    public static void main(String[] args) {
        ImmutableAddress a = new ImmutableAddress("서울");
        ImmutableAddress b = a;

        System.out.println("a = " + a);
        System.out.println("b = " + b);
        
        // b.setValue("부산"); // 컴파이 오류 발생
        b = new ImmutableAddress("부산");
        System.out.println("부산 -> b");
        System.out.println("a = " + a);
        System.out.println("b = " + b);
    }
}
```
   - ImmutableAddress의 경우 값을 변경할 수 있는 b.setValue() 메서드 자체가 제거
   - 이제 ImmutableAddress 인스턴스의 값을 변경할 수 있는 방법은 없음
   - ImmutableAddress 를 사용하는 개발자는 값을 변경하려고 시도하다가, 값을 변경하는 것이 불가능하다는 사실을 알고, 이 객체가 불변 객체인 사실을 알 수 있음
      + 예를 들어 b.setValue("부산") 을 호출하려고 했는데, 해당 메서드가 없다는 사실을 컴파일 오류를 통해 인지
   - 따라서 어쩔 수 없이 새로운 ImmutableAddress("부산") 인스턴스를 생성해서 b에 대입
   - 결과적으로 a, b 는 서로 다른 인스턴스를 참조하고, a가 참조하던 ImmutableAddress는 그대로 유지

   - 실행 결과
```
a = ImmutableAddress{value='서울'}
b = ImmutableAddress{value='서울'}
부산 -> b
a = ImmutableAddress{value='서울'}
b = ImmutableAddress{value='부산'}
```

<div align="center">
<img src="https://github.com/user-attachments/assets/6c1bfd5c-e91b-4b5c-87ca-105e13fb75f0">
</div>

   - 자바에서 객체의 공유 참조는 막을 수 없음

<div align="center">
<img src="https://github.com/user-attachments/assets/c03cf810-7ac8-445e-80c8-5b307f7e0b13">
</div>

   - ImmutableAddress는 불변 객체이므로, 따라서 값을 변경할 수 없음

<div align="center">
<img src="https://github.com/user-attachments/assets/00bed039-e13d-4025-a065-a2e2370c0182">
</div>

   - ImmutableAddress은 불변 객체이므로 b가 참조하는 인스턴스의 값을 서울에서 부산으로 변경하려면 새로운 인스턴스를 생성해서 할당해야 함

3. 정리
   - 불변이라는 단순한 제약을 사용해서 사이드 이펙트라는 큰 문제를 막을 수 있음
   - 객체의 공유 참조는 막을 수 없음
     + 그래서 객체의 값을 변경하면 다른 곳에서 참조하는 변수의 값도 함께 변경되는 사이드 이펙트가 발생
     + 사이드 이펙트가 발생하면 안되는 상황이라면 불변 객체를 만들어서 사용하면 됨
     + 불변 객체는 값을 변경할 수 없기 때문에 사이드 이펙트가 원천 차단
   - 불변 객체는 값을 변경할 수 없음
     + 따라서 불변 객체의 값을 변경하고 싶다면 변경하고 싶은 값으로 새로운 불변 객체를 생성해야 함
     + 이렇게 하면 기존 변수들이 참조하는 값에는 영향을 주지 않음

4. 참고 - 가변(Mutable) 객체 vs 불변(Immutable) 객체
   - 가변 : 이름 그대로 처음 만든 이후 상태가 변할 수 있다는 뜻 (사전적으로 사물의 모양이나 성질이 달라질 수 있다는 뜻)  
   - 불변 : 이름 그대로 처음 만든 이후 상태가 변하지 않는다는 뜻 (사전적으로 사물의 모양이나 성질이 달라질 수 없다는 뜻)
   - Address는 가변 클래스로, 이 클래스로 객체를 생성하면 가변 객체가 됨
   - ImmutableAddress는 불변 클래스 : 이 클래스로 객체를 생성하면 불변 객체가 됨
