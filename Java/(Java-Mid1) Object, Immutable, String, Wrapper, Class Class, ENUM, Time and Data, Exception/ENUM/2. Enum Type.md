-----
### 열거형 - Enum Type
-----
1. 자바는 타입 안전 열거형 패턴(Type-Safe Enum Pattern)을 매우 편리하게 사용할 수 있는 열거형(Enum Type)을 제공
2. 쉽게 이야기해서 자바의 열거형은 앞서 배운 타입 안전 열거형 패턴을 쉽게 사용할 수 있도록 프로그래밍 언어에서 지원하는 것
3. 영어인 enum은 enumeration의 줄임말인데, 번역하면 열거라는 뜻이고, 어떤 항목을 나열하는 것을 뜻함
   - Enumeration은 일련의 명명된 상수들의 집합을 정의하는 것을 의미
   - 프로그래밍에서는 이러한 상수들을 사용하여 코드 내에서 미리 정의된 값들의 집합을 나타냄
3. 쉽게 이야기해서 회원의 등급은 상수로 정의한 BASIC, GOLD, DIAMOND만 사용할 수 있다는 뜻
4. 자바의 enum은 타입 안전성을 제공하고, 코드의 가독성을 높이며, 예상 가능한 값들의 집합을 표현하는 데 사용
5. Grade (/enumeration/ex3)
```java
package enumeration.ex3;

public enum Grade {
    BASIC, GOLD, DIAMOND
}
```
   - 열거형을 정의할 때는 class 대신에 enum 사용 : 원하는 상수의 이름을 나열하면 됨
   - 앞서 직접 ClassGrade를 구현할 때와는 비교가 되지 않을 정도로 편리
   - 자바의 열거형으로 작성한 Grade는 다음 코드와 거의 같음
```java
public class Grade extends Enum {
     public static final Grade BASIC = new Grade();
     public static final Grade GOLD = new Grade();
     public static final Grade DIAMOND = new Grade();

     // private 생성자 추가
     private Grade() {}
}
```
   - 열거형도 클래스
   - 열거형은 자동으로 java.lang.Enum을 상속 받음
   - 외부에서 임의로 생성할 수 없음
<div align="center">
<img src="https://github.com/user-attachments/assets/3bae012a-5687-4364-af1e-eec254a2156b">
</div>

   - EnumRefMain
```java
package enumeration.ex3;

public class EnumRefMain {
    public static void main(String[] args) {
        System.out.println("class BASIC = " + Grade.BASIC.getClass());
        System.out.println("class GOLD = " + Grade.GOLD.getClass());
        System.out.println("class DIAMOND = " + Grade.DIAMOND.getClass());

        System.out.println("ref BASIC = " + refValue(Grade.BASIC));
        System.out.println("ref GOLD = " + refValue(Grade.GOLD));
        System.out.println("ref DIAMOND = " + refValue(Grade.DIAMOND));
    }
    
    private static String refValue(Grade grade) {
        return Integer.toHexString(System.identityHashCode(grade));
    }
}
```
   - 실행 결과
```
class BASIC = class enumeration.ex3.Grade
class GOLD = class enumeration.ex3.Grade
class DIAMOND = class enumeration.ex3.Grade

ref BASIC = 1d81eb93
ref GOLD = 7291c18f
ref DIAMOND = 34a245ab
```

   - 실행 결과를 보면 상수들이 열거형으로 선언한 타입인 Grade 타입을 사용하는 것을 확인할 수 있음
   - 그리고 각각의 인스턴스도 서로 다른 것을 확인할 수 있음
   - 참고로 열거형은 toString()을 재정의 하기 때문에 참조값을 직접 확인할 수 없음 : 참조값을 구하기 위해 refValue()를 생성
     + System.identityHashCode(grade) : 자바가 관리하는 객체의 참조값을 숫자로 반환    
     + Integer.toHexString() : 숫자를 16진수로 변환, 우리가 일반적으로 확인하는 참조값은 16진수
   - 열거형도 클래스이므로, 열거형을 제공하기 위해 제약이 추가된 클래스라 생각하면 됨

6. DiscountService
```java
package enumeration.ex3;

public class DiscountService {
    public int discount(Grade grade, int price) {
        int discountPercent = 0;
        
        // enum switch 변경 가능
        if (grade == Grade.BASIC) {
            discountPercent = 10;
        } else if (grade == Grade.GOLD) {
            discountPercent = 20;
        } else if (grade == Grade.DIAMOND) {
            discountPercent = 30;
        } else {
            System.out.println("할인 X");
        }
        
        return price * discountPercent / 100;
    }
}
```

7. EnumEx3_1
```java
package enumeration.ex3;

public class EnumEx3_1 {
    public static void main(String[] args) {
        int price = 10000;
        
        DiscountService discountService = new DiscountService();
        
        int basic = discountService.discount(Grade.BASIC, price);
        int gold = discountService.discount(Grade.GOLD, price);
        int diamond = discountService.discount(Grade.DIAMOND, price);

        System.out.println("BASIC 등급의 할인 금액 : " + basic);
        System.out.println("GOLD 등급의 할인 금액 : " + gold);
        System.out.println("DIAMOND 등급의 할인 금액 : " + diamond);
    }
}
```
  - 실행 결과
```
BASIC 등급의 할인 금액 : 1000
GOLD 등급의 할인 금액 : 2000
DIAMOND 등급의 할인 금액 : 3000
```
   - 열거형의 사용법이 앞서 타입 안전 열거형 패턴을 직접 구현한 코드와 같은 것을 확인 할 수 있음
   - 참고로 열거형은 switch 문에 사용할 수 있는 장점도 존재

8. 열거형은 외부 생성 불가 - EnumEx3_2
```java
package enumeration.ex3;

public class EnumEx3_2 {
    public static void main(String[] args) {
        int price = 10000;

        DiscountService discountService = new DiscountService();

        /*
         Grade myGrade = new Grade(); //enum 생성 불가
         double result = discountService.discount(myGrade, price);
         System.out.println("result price: " + result);
        */
    }
}
```
  - enum은 열거형 내부에서 상수로 지정하는 것 외에 직접 생성이 불가능 : 생성할 경우 컴파일 오류가 발생
```
오류 메시지 : enum classes may not be instantiated
```

8. 열거형(ENUM)의 장점
   - 타입 안정성 향상 : 열거형은 사전에 정의된 상수들로만 구성되므로, 유효하지 않은 값이 입력될 가능성이 없음 (이런 경우 컴파일 오류가 발생)
   - 간결성 및 일관성 : 열거형을 사용하면 코드가 더 간결하고 명확해지며, 데이터의 일관성이 보장
   - 확장성 : 새로운 회원 등급을 타입을 추가하고 싶을 때, ENUM에 새로운 상수를 추가하기만 하면 됨

9. 참고 : 열거형을 사용하는 경우 static import를 적절하게 사용하면 더 읽기 좋은 코드를 만들 수 있음
