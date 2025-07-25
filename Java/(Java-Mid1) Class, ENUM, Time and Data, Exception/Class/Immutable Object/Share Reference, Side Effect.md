-----
### 공유 참조와 사이드 이펙트
------
1. 사이드 이펙트(Side Effect) : 프로그래밍에서 어떤 계산이 주된 작업 외에 추가적인 부수 효과를 일으키는 것
2. 앞서 b의 값을 부산으로 변경한 코드를 다시 분석
```java
b.setValue("부산"); // b의 값을 부산으로 변경해야함
System.out.println("부산 -> b");
System.out.println("a = " + a); // 사이드 이펙트 발생
System.out.println("b = " + b);
```

<div align="center">
<img src="https://github.com/user-attachments/assets/21707e67-b8e9-473f-b684-b8752ded25f9">
</div>

   - 개발자는 b의 주소값을 서울에서 부산으로 변경할 의도로 값 변경을 시도
   - 하지만 a, b 는 같은 인스턴스를 참조 : 따라서 a의 값도 함께 부산으로 변경되어 버림

3. 이렇게 주된 작업 외에 추가적인 부수 효과를 일으키는 것을 사이드 이펙트
   - 프로그래밍에서 사이드 이펙트는 보통 부정적인 의미로 사용되는데, 사이드 이펙트는 프로그램의 특정 부분에서 발생한 변경이 의도치 않게 다른 부분에 영향을 미치는 경우에 발생
   - 이로 인해 디버깅이 어려워지고 코드의 안정성이 저하될 수 있음

4. 사이드 이펙트 해결 방안
   - 생각해보면 문제의 해결방안은 아주 단순 : 다음과 같이 a와 b가 처음부터 서로 다른 인스턴스를 참조
```java
Address a = new Address("서울");
Address b = new Address("서울");
```
   - RefMain1_2
```java
package lang.immutable.address;

public class RefMain1_2 {
    public static void main(String[] args) {
        // 참조형 변수는 하나의 인스턴스 공유 가능
        Address a = new Address("서울");
        Address b = new Address("서울");

        System.out.println("a = " + a);
        System.out.println("b = " + b);

        b.setValue("부산"); // b의 값을 부산으로 변경
        System.out.println("부산 -> b");
        System.out.println("a = " + a);
        System.out.println("b = " + b);
    }
}
```
   - 실행 결과
```
a = Address{value='서울'}
b = Address{value='서울'}
부산 -> b
a = Address{value='서울'}
b = Address{value='부산'}
```
  - 실행 결과를 보면 b의 주소값만 부산으로 변경된 것을 확인

<div align="center">
<img src="https://github.com/user-attachments/assets/c549dddf-78f7-455e-8ba6-f37398fa372b">
</div>

  - a와 b는 서로 다른 Address 인스턴스를 참조

<div align="center">
<img src="https://github.com/user-attachments/assets/d620cdaf-77a3-4d6f-8159-2addfe1f2aab">
</div>

  - a와 b는 서로 다른 인스턴스를 참조
  - 따라서 b가 참조하는 인스턴스의 값을 변경해도 a에는 영향을 주지 않음

5. 여러 변수가 하나의 객체를 공유하는 것을 막을 방법은 없음
   - 지금까지 발생한 모든 문제는 같은 객체(인스턴스)를 변수 a, b 가 함께 공유하기 때문에 발생
   - 따라서 객체를 공유하지 않으면 문제가 해결
   - 여기서 변수 a, b 가 서로 각각 다른 주소지로 변경할 수 있어야 하는데, 이렇게 하려면 서로 다른 객체를 참조하면 됨
   - 객체를 공유
```java
Address a = new Address("서울");
Address b = a;
```
   - 이 경우 a, b 둘다 같은 Address 인스턴스를 바라보기 때문에 한쪽의 주소만 부산으로 변경하는 것이 불가능
   - 객체를 공유 하지 않음
```java
Address a = new Address("서울");
Address b = new Address("서울");
```
   - 이 경우 a, b 는 서로 다른 Address 인스턴스를 바라보기 때문에 한쪽의 주소만 부산으로 변경하는 것이 가능
   - 이처럼 단순하게 서로 다른 객체를 참조해서, 같은 객체를 공유하지 않으면 문제가 해결
   - 쉽게 이야기해서 여러 변수가 하나의 객체를 공유하지 않으면 지금까지 설명한 문제들이 발생하지 않음  
   - 그런데 여기서 문제가 발생 : 하나의 객체를 여러 변수가 공유하지 않도록 강제로 막을 수 있는 방법이 없다는 것

6. 참조값의 공유를 막을 수 있는 방법이 없음
```java
Address a = new Address("서울");
Address b = a; // 참조값 대입을 막을 수 있는 방법이 없음
```
   - b = a 와 같은 코드를 작성하지 않도록 해서, 여러 변수가 하나의 참조값을 공유하지 않으면 문제가 해결될 것 같음
   - 하지만 Address 를 사용하는 개발자 입장에서 실수로 b = a 라고 해도 아무런 오류가 발생하지 않음
   - 왜냐하면 자바 문법상 Address b = a 와 같은 참조형 변수의 대입은 아무런 문제가 없기 때문이며, 다음과 같이 새로운 객체를 참조형 변수에 대입하든, 또는 기존 객체를 참조형 변수에 대입하든, 다음 두 코드 모두 자바 문법상 정상인 코드
```java
Address b = new Address("서울") // 새로운 객체 참조
Address b = a // 기존 객체 공유 참조
```
   - 참조값을 다른 변수에 대입하는 순간 여러 변수가 하나의 객체를 공유
   - 쉽게 이야기해서 객체의 공유를 막을 수 있는 방법이 없음
   - 기본형은 항상 값을 복사해서 대입하기 때문에 값이 절대로 공유되지 않지만, 참조형의 경우 참조값을 복사해서 대입하기 때문에 여러 변수에서 얼마든지 같은 객체를 공유할 수 있음
   - 객체의 공유가 꼭 필요할 때도 있지만, 때로는 공유하는 것이 지금과 같은 사이드 이펙트를 만드는 경우도 있음

7. RefMain1_3
```java
package lang.immutable.address;

public class RefMain1_3 {
    public static void main(String[] args) {
        // 참조형 변수는 하나의 인스턴스 공유 가능
        Address a = new Address("서울");
        Address b = a;

        System.out.println("a = " + a);
        System.out.println("b = " + b);

        change(b, "부산");
        System.out.println("a = " + a);
        System.out.println("b = " + b);
    }
    
    private static void change(Address address, String changeAddress) {
        System.out.println("주소 값을 변경합니다 -> " + changeAddress);
        address.setValue(changeAddress);
    }
}
```
   - 앞서 작성한 코드와 같은 코드
   - 단순히 change() 메서드만 하나 추가
   - 그리고 change() 메서드에서 Address 인스턴스에 있는 value 값을 변경
   - main() 메서드만 보면 a의 값이 함께 부산으로 변경된 이유를 찾기가 더 어려움
   - 실행 결과
```
a = Address{value='서울'}
b = Address{value='서울'}
주소 값을 변경합니다 -> 부산
a = Address{value='부산'}
b = Address{value='부산'}
```
