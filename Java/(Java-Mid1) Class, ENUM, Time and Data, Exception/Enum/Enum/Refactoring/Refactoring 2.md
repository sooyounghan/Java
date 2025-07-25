-----
### 열거형 - 리팩토링 2
-----
1. 열거형도 클래스 : 앞서 했던 리팩토링을 열거형인 Grade에 동일하게 적용
   - Grade (/enumeration/ref2)
```java
package enumeration.ref2;

public enum Grade {
    BASIC(10), GOLD(20), DIAMOND(30);
    
    private final int discountPercent;
    
    Grade(int discountPercent) {
        this.discountPercent = discountPercent;
    }
    
    public int getDiscountPercent() {
        return discountPercent;
    }
}
```
   - discountPercent 필드를 추가하고, 생성자를 통해서 필드에 값을 저장한
   - 💡 열거형은 상수로 지정하는 것 외에 일반적인 방법으로 생성이 불가능
     + 따라서 생성자에 접근제어자를 선언할수 없게 막혀있음 : private 이라고 생각하면 됨
   - 💡 BASIC(10)과 같이 상수 마지막에 괄호를 열고 생성자에 맞는 인수를 전달하면 적절한 생성자가 호출
   - 값을 조회하기 위해 getDiscountPercent() 메서드를 추가 : 열거형도 클래스이므로 메서드를 추가 가능

2. DiscountService
```java
package enumeration.ref2;

public class DiscountService {
    public int discount(Grade grade, int price) {
        return price * grade.getDiscountPercent() / 100;
    }
}
```
   - 기존에 있던 if 문이 완전히 제거되고, 단순한 할인율 계산 로직만 남음

3. EnumRefMain2
```java
package enumeration.ref2;

public class EnumRefMain2 {
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
