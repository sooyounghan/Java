-----
### 타입 안전 열거형 패턴
-----
1. 타입 안전 열거형 패턴 (Type-Safe Enum Pattern)
   - Enum : Enumeration의 줄임말로, 열거라는 뜻으로, 어떤 항목을 나열하는 것을 뜻함
   - 회원 등급인 BASIC, GOLD, DIAMOND를 나열하는 것
   - 중요한 것은 타입 안전 열거형 패턴을 사용하면 이렇게 나열한 항목만 사용할 수 있다는 것이 핵심이며, 나열한 항목이 아닌 것은 사용할 수 없음
   - 쉽게 이야기해서 앞서본 String 처럼 아무런 문자열이나 다 사용할 수 있는 것이 아니라, 나열한 항목인 BASIC, GOLD, DIAMOND만 안전하게 사용할 수 있다는 것

2. ClassGrade (/enumeration/ex2)
```java
package enumeration.ex2;

public class ClassGrade {
    public static final ClassGrade BASIC = new ClassGrade();
    public static final ClassGrade GOLD = new ClassGrade();
    public static final ClassGrade DIAMOND = new ClassGrade();
}
```
   - 먼저 회원 등급을 다루는 클래스를 만들고, 각각의 회원 등급별로 상수를 선언
   - 이때 각각의 상수마다 별도의 인스턴스를 생성하고, 생성한 인스턴스를 대입  
   - 각각을 상수로 선언하기 위해 static, final 을 사용
      + static을 사용해서 상수를 메서드 영역에 선언
      + final을 사용해서 인스턴스(참조값)를 변경할 수 없게 함
<div align="center">
<img src="https://github.com/user-attachments/assets/3fcf0706-dcd9-4cd9-b586-ecd6d80d36e0">
</div>

3. ClassRefMain
```java
package enumeration.ex2;

public class ClassRefMain {
    public static void main(String[] args) {
        System.out.println("class BASIC = " + ClassGrade.BASIC.getClass());
        System.out.println("class GOLD = " + ClassGrade.GOLD.getClass());
        System.out.println("class DIAMOND = " + ClassGrade.DIAMOND.getClass());

        System.out.println("ref BASIC = " + ClassGrade.BASIC); 
        System.out.println("ref GOLD = " + ClassGrade.GOLD);
        System.out.println("ref DIAMOND = " + ClassGrade.DIAMOND);
    }
}
```
   - 실행 결과
```
class BASIC = class enumeration.ex2.ClassGrade
class GOLD = class enumeration.ex2.ClassGrade
class DIAMOND = class enumeration.ex2.ClassGrade

ref BASIC = enumeration.ex2.ClassGrade@1d81eb93
ref GOLD = enumeration.ex2.ClassGrade@7291c18f
ref DIAMOND = enumeration.ex2.ClassGrade@34a245ab
```

   - 각각의 상수는 모두 ClassGrade 타입을 기반으로 인스턴스를 만들었기 때문에 getClass()의 결과는 모두 ClassGrade
   - 각각의 상수는 모두 서로 각각 다른 ClassGrade 인스턴스를 참조하기 때문에 참조값이 다르게 출력
   - static 이므로 애플리케이션 로딩 시점에 다음과 같이 3개의 ClassGrade 인스턴스가 생성되고, 각각의 상수는 같은 ClassGrade 타입의 서로 다른 인스턴스의 참조값
      + ClassGrade BASIC : x001
      + ClassGrade GOLD : x002
      + ClassGrade DIAMOND : x003

   - BASIC, GOLD, DIAMOND를 상수로 열거
   - 이제 ClassGrade 타입을 사용할 때는 앞서 열거한 상수들만 사용
   - DiscountService
```java
package enumeration.ex2;

import enumeration.ex1.StringGrade;

public class DiscountService {
    public int discount(ClassGrade classGrade, int price) {
        int discountPercent = 0;

        if(classGrade == ClassGrade.BASIC) {
            discountPercent = 10;
        } else if(classGrade == ClassGrade.GOLD) {
            discountPercent = 20;
        } else if(classGrade == ClassGrade.DIAMOND) {
            discountPercent = 30;
        } else {
            System.out.println("할인 X");
        }

        return price * discountPercent / 100;
    }
}
```
   - discount() 메서드는 매개변수로 ClassGrade 클래스를 사용
   - 값을 비교할 때는 classGrade == ClassGrade.BASIC 와 같이 == 참조값 비교를 사용 
      + 매개변수에 넘어오는 인수도 ClassGrade가 가진 상수 중에 하나를 사용
      + 따라서 열거한 상수의 참조값으로 비교(==)

   - ClassGradeEx2_1
```java
package enumeration.ex2;

public class ClassGradeEx2_1 {
    public static void main(String[] args) {
        int price = 10000;

        DiscountService discountService = new DiscountService();

        int basic = discountService.discount(ClassGrade.BASIC, price);
        int gold = discountService.discount(ClassGrade.GOLD, price);
        int diamond = discountService.discount(ClassGrade.DIAMOND, price);

        System.out.println("BASIC 등급 할인 금액 : " + basic);
        System.out.println("GOLD 등급 할인 금액 : " + gold);
        System.out.println("DIAMOND 등급 할인 금액 : " + diamond);
    }
}
```
   - 실행 결과
```
BASIC 등급 할인 금액 : 1000
GOLD 등급 할인 금액 : 2000
DIAMOND 등급 할인 금액 : 3000
```

4. private 생성자
   - 이 방식은 외부에서 임의로 ClassGrade 의 인스턴스를 생성할 수 있다는 문제가 발생
   - ClssGradeEx2_2
```java
package enumeration.ex2;

public class StringGradeEx2_2 {
    public static void main(String[] args) {
        int price = 10000;

        DiscountService discountService = new DiscountService();

        ClassGrade newClassGrade = new ClassGrade(); // 생성자를 private로 막아야 함
        int result = discountService.discount(newClassGrade, price);
        System.out.println("newClassGrade 등급 할인 금액 : " + result);
    }
}
```
   - 실행 결과
```
할인 X
newClassGrade 등급 할인 금액 : 0
```
   - 이 문제를 해결하려면 외부에서 ClassGrade 를 생성할 수 없도록 막으면 됨
   - 기본 생성자를 private으로 변경
```java
package enumeration.ex2;

public class ClassGrade {
    public static final ClassGrade BASIC = new ClassGrade();
    public static final ClassGrade GOLD = new ClassGrade();
    public static final ClassGrade DIAMOND = new ClassGrade();
    
    // private 생성자 추가
    private ClassGrade() {}
}
```
```java
package enumeration.ex2;

public class StringGradeEx2_2 {
    public static void main(String[] args) {
        int price = 10000;

        DiscountService discountService = new DiscountService();

        /*
        ClassGrade newClassGrade = new ClassGrade(); // 생성자를 private로 막아야 함
        int result = discountService.discount(newClassGrade, price);
        System.out.println("newClassGrade 등급 할인 금액 : " + result); 
        */
    }
}
```
   - private 생성자를 사용해서 외부에서 ClassGrade를 임의로 생성하지 못하게 막음
   - private 생성자 덕분에 ClassGrade의 인스턴스를 생성하는 것은 ClassGrade 클래스 내부에서만 할 수 있음
   - 앞서 정의한 상수들은 ClassGrade 클래스 내부에서 ClassGrade 객체를 생성
   - 이제 ClassGrade 인스턴스를 사용할 때는 ClassGrade 내부에 정의한 상수를 사용해야 하며, 그렇지 않으면 컴파일 오류가 발생
   - 쉽게 이야기해서 ClassGrade 타입에 값을 전달할 때는 우리가 앞서 열거한 BASIC, GOLD, DIAMOND 상수만 사용할 수 있음
   - 이렇게 private 생성자까지 사용하면 타입 안전 열거형 패턴을 완성할 수 있음

5. 타입 안전 열거형 패턴(Type-Safe Enum Pattern)의 장점
   - 타입 안정성 향상 : 정해진 객체만 사용할 수 있기 때문에, 잘못된 값을 입력하는 문제를 근본적으로 방지 가능
   - 데이터 일관성 : 정해진 객체만 사용하므로 데이터의 일관성이 보장
   - 제한된 인스턴스 생성 : 클래스는 사전에 정의된 몇 개의 인스턴스만 생성하고, 외부에서는 이 인스턴스들만 사용할 수 있도록 하며, 이를 통해 미리 정의된 값들만 사용하도록 보장
   - 타입 안전성 : 이 패턴을 사용하면, 잘못된 값이 할당되거나 사용되는 것을 컴파일 시점에 방지할 수 있음
     + 예를 들어, 특정 메서드가 특정 열거형 타입의 값을 요구한다면, 오직 그 타입의 인스턴스만 전달할 수 있음
     + 여기서는 메서드의 매개변수로 ClassGrade를 사용하는 경우, 앞서 열거한 BASIC, GOLD, DIAMOND만 사용할 수 있음

6. 단점
   - 이 패턴을 구현하려면 다음과 같이 많은 코드를 작성
   - 그리고 private 생성자를 추가하는 등 유의해야 하는 부분들도 존재
```java
package enumeration.ex2;

public class ClassGrade {
    public static final ClassGrade BASIC = new ClassGrade();
    public static final ClassGrade GOLD = new ClassGrade();
    public static final ClassGrade DIAMOND = new ClassGrade();
    
    // private 생성자 추가
    private ClassGrade() {}
}
```
