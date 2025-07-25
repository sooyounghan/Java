-----
### 열거형 - 리팩토링 1
-----
1. DiscountService.discount() 코드
```java
if (classGrade == ClassGrade.BASIC) {
     discountPercent = 10;
} else if (classGrade == ClassGrade.GOLD) {
     discountPercent = 20;
} else if (classGrade == ClassGrade.DIAMOND) {
     discountPercent = 30;
} else {
     System.out.println("할인 X");
}
```
   - 불필요한 if문을 제거
   - 이 코드에서 할인율(discountPercent)은 각각의 회원 등급별로 판단
     + 할인율은 결국 회원 등급을 따라가므로, 회원 등급 클래스가 할인율(discountPercent)을 가지고 관리하도록 변경

2. ClassGrade (/enumeration/ref1)
```java
package enumeration.ref1;

public class ClassGrade {
    public static final ClassGrade BASIC = new ClassGrade(10);
    public static final ClassGrade GOLD = new ClassGrade(20);
    public static final ClassGrade DIAMOND = new ClassGrade(30);

    private final int discountPercent;
    
    private ClassGrade(int discountPercent) {
        this.discountPercent = discountPercent;
    }
    
    public int getDiscountPercent() {
        return discountPercent;
    }
}
```
   - ClassGrade에 할인율(discountPercent) 필드를 추가 및 조회 메서드도 추가
   - 생성자를 통해서만 discountPercent를 설정하도록 했고, 중간에 이 값이 변하지 않도록 불변으로 설계
   - 정리하면 상수를 정의할 때 각각의 등급에 따른 할인율(discountPercent)이 정해짐

3. DiscountService
```java
package enumeration.ref1;

public class DiscountService {
    public int discount(ClassGrade classGrade, int price) {
        return price * classGrade.getDiscountPercent() / 100;
    }
}
```
   - 기존에 있던 if문이 완전히 제거되고, 단순한 할인율 계산 로직만 남음
   - 기존에는 if문을 통해서 회원의 등급을 찾고, 각 등급 별로 discountPercent 의 값을 지정
   - 변경된 코드에서는 if 문을 사용할 이유가 없음
     + 단순히 회원 등급안에 있는 getDiscountPercent() 메서드를 호출하면 인수로 넘어온 회원 등급의 할인율을 바로 구할 수 있음

4. ClassGradeRefMain1
```java
package enumeration.ref1;

public class ClassGradeRefMain1 {
    public static void main(String[] args) {
        int price = 10000;

        DiscountService discountService = new DiscountService();

        int basic = discountService.discount(ClassGrade.BASIC, price);
        int gold = discountService.discount(ClassGrade.GOLD, price);
        int diamond = discountService.discount(ClassGrade.DIAMOND, price);

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
