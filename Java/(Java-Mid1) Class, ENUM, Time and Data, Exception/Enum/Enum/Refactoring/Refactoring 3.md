-----
### 열거형 - 리팩토링 3
-----
1. 할인율 계산 - DiscountService
```java
public class DiscountService {
   public int discount(Grade grade, int price) {
       return price * grade.getDiscountPercent() / 100;
   }
}
```
   - 이 코드를 보면 할인율 계산을 위해 Grade가 가지고 있는 데이터인 discountPercent의 값을 꺼내서 사용
   - 결국 Grade의 데이터인 discountPercent를 할인율 계산에 사용
   - 객체지향 관점에서 이렇게 자신의 데이터를 외부에 노출하는 것 보다는, Grade 클래스가 자신의 할인율을 어떻게 계산하는지 스스로 관리하는 것이 캡슐화 원칙에 더 맞음

2. Grade 클래스 안으로 discount() 메서드를 이동
   - Grade (/enumeration/ref3)
```java
package enumeration.ref3;

public enum Grade {
    BASIC(10), GOLD(20), DIAMOND(30);

    private final int discountPercent;

    Grade(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }
    
    // 추가
    public int discount(int price) {
        return price * discountPercent / 100;
    }
}
```
   - Grade 내부에 discount() 메서드를 만들어서, 이제 할인율을 스스로 계산

3. DiscountService
```java
package enumeration.ref3;

public class DiscountService {
    public int discount(Grade grade, int price) {
        return grade.discount(price);
    }
}
```
   - 할인율 계산은 이제 Grade가 스스로 처리
   - 따라서 DiscountService.discount() 메서드는 단순히 Grade.discount()를 호출하기만 하면 됨

4. EnumRefMain3_1
```java
package enumeration.ref3;

public class EnumRefMain3_1 {
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

5. DiscountService 제거
   - Grade 가 스스로 할인율을 계산하면서 DiscountService 클래스가 더는 필요하지 않게 됨
   - EnumRefMain3_2
```java
package enumeration.ref3;

public class EnumRefMain3_2 {
    public static void main(String[] args) {
        int price = 10000;

        System.out.println("BASIC 등급의 할인 금액 : " + Grade.BASIC.discount(price));
        System.out.println("GOLD 등급의 할인 금액 : " + Grade.GOLD.discount(price));
        System.out.println("DIAMOND 등급의 할인 금액 : " + Grade.DIAMOND.discount(price));
    }
}
```
   - 실행 결과
```
BASIC 등급의 할인 금액 : 1000
GOLD 등급의 할인 금액 : 2000
DIAMOND 등급의 할인 금액 : 3000
```
   - 각각의 등급별로 자신의 discount()를 직접 호출하면 할인율을 구할 수 있음

6. 중복 제거 : 출력 부분 중복 제거
   - EnumRefMain3_3
```java
package enumeration.ref3;

public class EnumRefMain3_3 {
    public static void main(String[] args) {
        int price = 10000;

        printDiscount(Grade.BASIC, price);
        printDiscount(Grade.GOLD, price);
        printDiscount(Grade.DIAMOND, price);
    }

    private static void printDiscount(Grade grade, int price) {
        System.out.println(grade.name() + " 등급의 할인 금액 : " + grade.discount(price));
    }
}
```
   - 실행 결과
```
BASIC 등급의 할인 금액 : 1000
GOLD 등급의 할인 금액 : 2000
DIAMOND 등급의 할인 금액 : 3000
```
   - grade.name() 을 통해서 ENUM의 상수 이름을 사용 가능

7. ENUM 목록
   - 이후에 새로운 등급이 추가되더라도 main() 코드의 변경 없이 모든 등급의 할인을 출력
   - EnumRefMain3_4
```java
package enumeration.ref3;

public class EnumRefMain3_4 {
    public static void main(String[] args) {
        int price = 10000;

        Grade[] grades = Grade.values();
        for (Grade grade : grades) {
            printDiscount(grade, price);
        }
    }

    private static void printDiscount(Grade grade, int price) {
        System.out.println(grade.name() + " 등급의 할인 금액 : " + grade.discount(price));
    }
}
```
   - 실행 결과
```
BASIC 등급의 할인 금액 : 1000
GOLD 등급의 할인 금액 : 2000
DIAMOND 등급의 할인 금액 : 3000
```
   - Grade.values()를 사용하면 Grade 열거형의 모든 상수를 배열로 구할 수 있음
