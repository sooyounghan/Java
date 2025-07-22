-----
### 문자열과 타입 안전성 1
-----
1. 열거형이 만들어진 근본적인 이유 - 예제
    - 비즈니스 요구사항
       + 고객은 3등급으로 나누고, 상품 구매시 등급별로 할인을 적용
       + 할인 시 소수점 이하는 버림
          * BASIC 10% 할인
          * GOLD 20% 할인
          * DIAMOND 30% 할인
       + 예) GOLD 유저가 10000원을 구매하면 할인 대상 금액은 2000원
    - 예제 구현 (DiscountService (/enumeation/ex0))
       + 회원 등급과 가격을 입력하면 할인 금액을 계산해주는 클래스 생성 (예를 들어서 GOLD , 10000원을 입력하면 할인 대상 금액인 2000원을 반환)
```java
package enumeration.ex0;

public class DiscountService {
    public int discount(String grade, int price) {
        int discountPercent = 0;
        
        if(grade.equals("BASIC")) {
            discountPercent = 10;
        } else if(grade.equals("GOLD")) {
            discountPercent = 20;
        } else if(grade.equals("DIAMOND")) {
            discountPercent = 30;
        } else {
            System.out.println(grade + " : 할인 X");
        }
        
        return price * discountPercent / 100;
    }
}
```
   - price * discountPercent / 100 : 가격 * 할인율 / 100 을 계산하면 할인 금액을 구할 수 있음
   - 회원 등급 외 다른 값이 입력되면 할인 X를 출력 : 이 경우 discountPercent가 0이므로 할인 금액도 0원으로 계산
   - 예제를 단순화하기 위해 회원 등급에 null은 입력되지 않는다고 가정
   - StringGradeEx0_1
```java
package enumeration.ex0;

public class StringGradeEx0_1 {
    public static void main(String[] args) {
        int price = 10000;

        DiscountService discountService = new DiscountService();

        int basic = discountService.discount("BASIC", price);
        int gold = discountService.discount("GOLD", price);
        int diamond = discountService.discount("DIAMOND", price);

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

   - 실행 결과를 보면 각각의 회원 등급에 맞는 할인이 적용된 것을 확인할 수 있음
   - 그런데 지금과 같이 단순히 문자열을 입력하는 방식은, 오타가 발생하기 쉽고, 유효하지 않는 값이 입력될 수 있음
```java
package enumeration.ex0;

public class StringGradeEx0_2 {
    public static void main(String[] args) {
        int price = 10000;

        DiscountService discountService = new DiscountService();

        // 존재하지 않는 등급
        int vip = discountService.discount("VIP", price);
        System.out.println("VIP 등급 할인 금액 : " + vip);
        
        // 오타
        int diamondd = discountService.discount("DIAMONDD", price);
        System.out.println("DIAMONDD 등급 할인 금액 : " + diamondd);
        
        // 소문자 입력
        int gold = discountService.discount("gold", price);
        System.out.println("gold 등급 할인 금액 : " + gold);
    }
}
```
   - 실행 결과
```
VIP : 할인 X
VIP 등급 할인 금액 : 0

DIAMONDD : 할인 X
DIAMONDD 등급 할인 금액 : 0

gold : 할인 X
gold 등급 할인 금액 : 0
```

   - 예제에서는 다음과 같은 문제가 발생
      + 존재하지 않는 VIP라는 등급을 입력
      + 오타 : DIAMOND 마지막에 D가 하나 추가
      + 소문자 입력 : 등급은 모두 대문자인데, 소문자를 입력

   - 등급에 문자열을 사용하는 지금의 방식은 다음과 같은 문제가 있음
      + 타입 안정성 부족 : 문자열은 오타가 발생하기 쉽고, 유효하지 않은 값이 입력될 수 있음
      + 데이터 일관성 : "GOLD", "gold", "Gold" 등 다양한 형식으로 문자열을 입력할 수 있어 일관성이 떨어짐
   
   - String 사용 시 타입 안정성 부족 문제
      + 값의 제한 부족 : String으로 상태나 카테고리를 표현하면, 잘못된 문자열을 실수로 입력할 가능성이 있음
        * 예를들어, "Monday", "Tuesday" 등을 나타내는 데 String을 사용한다면, 오타("Munday")나 잘못된 값("Funday")이 입력될 위험이 있음
      + 컴파일 시 오류 감지 불가 : 이러한 잘못된 값은 컴파일 시에는 감지되지 않고, 런타임에서만 문제가 발견되기 때문에 디버깅이 어려워질 수 있음
      + 이런 문제를 해결하려면 특정 범위로 값을 제한해야 함 (예를 들어, BASIC, GOLD, DIAMOND라는 정확한 문자만 discount() 메서드에 전달되어야 함. 하지만 String 은 어떤 문자열이든 받을 수 있기 때문에 자바 문법 관점에서는 아무런 문제가 없으므로, 결국 String 타입을 사용해서는 문제를 해결할 수 없음)
    
-----
### 문자열과 타입 안전성 2
-----
1. 대안으로 문자열 상수를 사용 : 상수는 미리 정의한 변수명을 사용할 수 있기 때문에 문자열을 직접 사용하는 것 보다는 더 안전
2. StringGrade (/enumeration/ex1)
```java
package enumeration.ex1;

public class StringGrade {
    public static final String BASIC = "BASIC";
    public static final String GOLD = "GOLD";
    public static final String DIAMOND = "DIAMOND";
}
```

3. DiscountService
```java
package enumeration.ex1;

public class DiscountService {
    public int discount(String grade, int price) {
        int discountPercent = 0;

        if(grade.equals(StringGrade.BASIC)) {
            discountPercent = 10;
        } else if(grade.equals(StringGrade.GOLD)) {
            discountPercent = 20;
        } else if(grade.equals(StringGrade.DIAMOND)) {
            discountPercent = 30;
        } else {
            System.out.println(grade + " : 할인 X");
        }

        return price * discountPercent / 100;
    }
}
```

4. StringGradeEx1_1
```java
package enumeration.ex1;

public class StringGradeEx1_1 {
    public static void main(String[] args) {
        int price = 10000;

        DiscountService discountService = new DiscountService();
        
        int basic = discountService.discount(StringGrade.BASIC, price);
        int gold = discountService.discount(StringGrade.GOLD, price);
        int diamond = discountService.discount(StringGrade.DIAMOND, price);

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

  - 문자열 상수를 사용한 덕분에 전체적으로 코드가 더 명확해짐
  - 그리고 discount()에 인자를 전달할 때도 StringGrade 가 제공하는 문자열 상수를 사용하면 됨
  - 더 좋은 점은 만약 실수로 상수의 이름을 잘못 입력하면 컴파일 시점에 오류가 발생한다는 점
  - 따라서 오류를 쉽고 빠르게 찾을 수 있음
  - 하지만 문자열 상수를 사용해도, 지금까지 발생한 문제들을 근본적으로 해결할 수 는 없음
    + 왜냐하면 String 타입은 어떤 문자열이든 입력할 수 있기 때문임
    + 실수로 StringGrade에 있는 문자열 상수를 사용하지 않고, 다음과 같이 직접 문자열을 사용해도 막을 수 있는 방법이 없음

4. StringGradeEx1_2
```java
package enumeration.ex1;

public class StringGradeEx1_2 {
    public static void main(String[] args) {
        int price = 10000;

        DiscountService discountService = new DiscountService();
        
        // 존재하지 않는 등급
        int vip = discountService.discount("VIP", price);
        System.out.println("VIP 등급 할인 금액 : " + vip);

        // 오타
        int diamondd = discountService.discount("DIAMONDD", price);
        System.out.println("DIAMONDD 등급 할인 금액 : " + diamondd);

        // 소문자 입력
        int gold = discountService.discount("gold", price);
        System.out.println("gold 등급 할인 금액 : " + gold);
    }
}
```
  - 실행 결과
```
VIP : 할인 X
VIP 등급 할인 금액 : 0

DIAMONDD : 할인 X
DIAMONDD 등급 할인 금액 : 0

gold : 할인 X
gold 등급 할인 금액 : 0
```

  - 그리고 사용해야 하는 문자열 상수가 어디에 있는지 discount()를 호출하는 개발자가 알 수 없음
  - 다음 코드를 보면 분명 String은 다 입력할 수 있다고 되어있음
```java
public int discount(String grade, int price) {}
```
   - 결국 누군가 주석을 잘 남겨두어서, StringGrade에 있는 상수를 사용해달라고 해야 함
   - 물론 이렇게 해도 누군가는 주석을 깜박하고 문자열을 직접 입력할 수 있음
