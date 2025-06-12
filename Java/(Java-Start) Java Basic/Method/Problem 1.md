-----
### 문제 - 평균값 리팩토링
-----
1. 문제 - 평균값 리펙토링
   - Method1Ex
```java
package method.ex;

public class MethodEx1 {
    public static void main(String[] args) {
        int a = 1;
        int b = 2;
        int c = 3;

        int sum = a + b + c;
        double average = sum / 3.0;

        System.out.println("평균값: " + average);

        int x = 15;
        int y = 25;
        int z = 35;

        sum = x + y + z;
        average = sum / 3.0;

        System.out.println("평균값: " + average);
    }
}
```
   - 리팩토링
```java
package method.ex;

public class MethodEx1 {
    public static void main(String[] args) {
        int a = 1;
        int b = 2;
        int c = 3;

        System.out.println("평균값: " + average(a, b, c));

        int x = 15;
        int y = 25;
        int z = 35;
        System.out.println("평균값: " + average(x, y, z));
    }

    public static double average(int a, int b, int c) {
        int sum = a + b + c;
        return sum / 3.0;
    }
}
```
  - 실행 결과
```
평균값: 2.0
평균값: 25.0
```

2. 문제 - 반복 출력 리펙토링
  - 다음은 특정 숫자만큼 같은 메시지를 반복 출력하는 기능
```java
package method.ex;

public class MethodEx2 {
    public static void main(String[] args) {
        
        String message = "Hello, world!";
        
        for (int i = 0; i < 3; i++) {
            System.out.println(message);
        }
        
        for (int i = 0; i < 5; i++) {
            System.out.println(message);
        }
        
        for (int i = 0; i < 7; i++) {
            System.out.println(message);
        }
    }
}
```
  - 리팩토링
```java
package method.ex;

public class MethodEx2 {
    public static void main(String[] args) {

        String message = "Hello, world!";

        printMessage(message, 3);
        printMessage(message, 5);
        printMessage(message, 7);
    }

    public static void printMessage(String message, int times) {
        for (int i = 0; i < times; i++) {
            System.out.println(message);
        }
        System.out.println();
    }
}
```

3. 문제 - 입출금 리펙토링
  - 다음은 입금, 출금을 나타내는 코드
  - 입금(deposit)과, 출금(withdraw)을 메서드로 만들어서 리펙토링
```java
package method.ex;

public class MethodEx3 {
    public static void main(String[] args) {
        int balance = 10000;
        
        // 입금 1000
        int depositAmount = 1000;
        balance += depositAmount;
        System.out.println(depositAmount + "원을 입금하였습니다. 현재 잔액 : " + balance + "원");
        
        // 출금 2000
        int withdrawAmount = 2000;
        
        if (balance >= withdrawAmount) { 
            balance -= withdrawAmount;
            
            System.out.println(amount + "원을 출금하였습니다. 현재 잔액 : " + balance + "원");
        } else {
            System.out.println(withdrawAmount + "원을 출금하려 했으나 잔액이 부족합니다.");
        }

        System.out.println("최종 잔액 : " + balance + "원");
    }
}
```
  - 리팩토링 - MethodEx3
```java
package method.ex;

public class MethodEx3 {
    public static void main(String[] args) {
        int balance = 10000;

        // 입금 1000
        balance = deposit(balance, 1000);

        // 출금 2000
        balance = withdraw(balance, 2000);
        
        System.out.println("최종 잔액 : " + balance + "원");
    }

    public static int deposit(int balance, int amount) {
        balance += amount;
        System.out.println(amount + "원을 입금하였습니다. 현재 잔액 : " + balance + "원");
        return balance;
    }

    public static int withdraw(int balance, int amount) {
        if (balance >= amount) {
            balance -= amount;

            System.out.println(amount + "원을 출금하였습니다. 현재 잔액 : " + balance + "원");
        } else {
            System.out.println(amount + "원을 출금하려 했으나 잔액이 부족합니다.");
        }

        return balance;
    }
}
```
  - 실행 결과
```
1000원을 입금하였습니다. 현재 잔액 : 11000원
2000원을 출금하였습니다. 현재 잔액 9000원
최종 잔액 : 9000원
```

  - 리펙토링 결과를 보면 main()은 세세한 코드가 아니라 전체 구조를 한눈에 볼 수 있게 됨
  - 더 자세히 알고 싶으면 해당 메서드를 찾아서 들어가면 됨
  - 그리고 입금과 출금 부분이 메서드로 명확하게 분리되었기 때문에 이후에 변경 사항이 발생하면 관련된 메서드만 수정하면 됨
  - 특정 메서드로 수정 범위가 한정되기 때문에 더 유지보수 하기 좋음
  - 💡 이런 리펙토링을 메서드 추출(Extract Method)
    + 메서드를 재사용하는 목적이 아니어도 괜찮음
    + 메서드를 적절하게 사용해서 분류하면 구조적으로 읽기 쉽고 유지보수 하기 좋은 코드를 만들 수 있음
    + 그리고 메서드의 이름 덕분에 프로그램을 더 읽기 좋게 만들 수 있음
