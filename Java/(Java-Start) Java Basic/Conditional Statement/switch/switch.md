-----
### Switch 문
-----
1. 문제
   - 회원 등급에 따라 다른 쿠폰을 발급하는 프로그램을 작성
   - 이 프로그램은 int grade라는 변수를 사용하며, 회원 등급(grade)에 따라 다음의 쿠폰을 발급해야 함
      + 1등급 : 쿠폰 1000
      + 2등급 : 쿠폰 2000
      + 3등급 : 쿠폰 3000
      + 위의 등급이 아닐 경우 : 쿠폰 500
   - 각 쿠폰이 할당된 후에는 "발급받은 쿠폰 " + 쿠폰값을 출력
   - 2등급 사용자 출력 예) 발급받은 쿠폰 2000

2. Switch1
```java
package cond;

public class Switch1 {
    public static void main(String[] args) {
        // Grade 1 : 1000, 2 : 2000, 3 : 3000, 나머지 : 500
        
        int grade = 2;
        
        int coupon;
        
        if (grade == 1) {
            coupon = 1000;
        } else if (grade == 2) {
            coupon = 2000;
        } else if (grade == 3) {
            coupon = 3000;
        } else {
            coupon = 500;
        }

        System.out.println("발급받은 쿠폰 " + coupon);
    }
}
```
   - 실행 결과
```
발급받은 쿠폰 2000
```

3. switch문
   - switch문은 앞서 배운 if문을 조금 더 편리하게 사용할 수 있는 기능
   - 참고로 if문은 비교 연산자를 사용할 수 있지만, switch문은 단순히 값이 같은지만 비교할 수 있음
   - switch 문은 조건식에 해당하는 특정 값으로 실행할 코드를 선택
```java
switch (조건식) {

     case value1:
         // 조건식의 결과 값이 value1일 때 실행되는 코드
         break;

     case value2:
         // 조건식의 결과 값이 value2일 때 실행되는 코드
         break;

     default:
         // 조건식의 결과 값이 위의 어떤 값에도 해당하지 않을 때 실행되는 코드
}
```
   - 조건식의 결과 값이 어떤 case의 값과 일치하면 해당 case의 코드를 실행
   - break문은 현재 실행 중인 코드를 끝내고 switch문을 빠져나가게 하는 역할
   - 만약 break문이 없으면, 일치하는 case 이후의 모든 case 코드들이 순서대로 실행
   - default는 조건식의 결과값이 모든 case의 값과 일치하지 않을 때 실행
     + if문의 else와 같으며, default 구문은 선택
   - if, else - if, else 구조와 동일

4. 앞서 작성한 코드를 switch 문으로 변경 - Switch2
```java
package cond;

public class Switch2 {
    public static void main(String[] args) {
        // Grade 1 : 1000, 2 : 2000, 3 : 3000, 나머지 : 500

        int grade = 2;

        int coupon;

        switch (grade) {
            case 1 :
                coupon = 1000;
                break;

            case 2 :
                coupon = 2000;
                break;

            case 3 :
                coupon = 3000;
                break;

            default :
                coupon = 500;
        }

        System.out.println("발급받은 쿠폰 " + coupon);
    }
}
```
  - 실행 결과
```
발급받은 쿠폰 2000
```

5. break문이 없을 때
   - break문이 없으면 어떻게 되는지 확인하기 위해 조건을 변경
   - 비즈니스 요구사항이 변경 : 2등급도 3등급과 같이 3000원 쿠폰을 제공
   - Switch3
```java
package cond;

public class Switch3 {
    public static void main(String[] args) {
        // Grade 1 : 1000, 2 : 3000 (변경), 3 : 3000, 나머지 : 500

        int grade = 2;

        int coupon;

        switch (grade) {
            case 1 :
                coupon = 1000;
                break;

            case 2 :
            case 3 :
                coupon = 3000;
                break;

            default :
                coupon = 500;
        }

        System.out.println("발급받은 쿠폰 " + coupon);
    }
}
```
   - 예를 들어서 grade가 2등급이면 먼저 case 2가 실행
   - 그런데 case 2에는 break 문이 없음
     + 그러면 중단하지 않고 바로 다음에 있는 case 3의 코드를 실행
     + 여기서 coupon = 3000;을 수행하고 break문을 만나서 switch 문 밖으로 빠져나감
     + "발급받은 쿠폰 3000"이 출력

6. if문 vs switch문
   - switch문의 조건식을 넣는 부분을 잘 보면, x > 10 과 같은 참 / 거짓의 결과가 나오는 조건이 아니라, 단순히 값만 넣을 수 있음
   - switch문은 조건식이 특정 case와 같은지만 체크할 수 있음
   - 쉽게 이야기해서 값이 같은지 확인하는 연산만 가능 (문자도 가능)
   - 반면에 if문은 참 거짓의 결과가 나오는 조건식을 자유롭게 적을 수 있음 (예) x > 10, x == 10)
   - 정리하자면 swtich문 없이 if문만 사용해도 됨
   - 하지만 특정 값에 따라 코드를 실행할 때는 switch문을 사용하면 if문 보다 간결한 코드를 작성할 수 있음

7. 💡 자바 14 - 새로운 switch문
   - switch문은 if문 보다 조금 덜 복잡한 것 같지만, 그래도 코드가 기대보다 깔끔하게 나오지는 않음
   - 이런 문제를 해결하고자 자바 14부터는 새로운 switch문이 정식 도입
```java
package cond;

public class Switch4 {
    public static void main(String[] args) {
        // Grade 1 : 1000, 2 : 2000, 3 : 3000, 나머지 : 500
        int grade = 2;

        int coupon = switch (grade) {
            case 1 -> 1000;
            case 2 -> 2000;
            case 3 -> 3000;
            default -> 500;
        };
        System.out.println("발급받은 쿠폰 " + coupon);
    }
}
```
   - 기존 switch문 과의 차이
     + -> 를 사용
     + 선택된 데이터를 반환할 수 있음
