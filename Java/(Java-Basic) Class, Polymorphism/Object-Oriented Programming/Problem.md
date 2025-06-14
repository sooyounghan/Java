-----
### 문제와 풀이
-----
1. 문제 1 - 절차 지향 직사각형 프로그램을 객체 지향으로 변경하기
   - 다음은 직사각형의 넓이(Area), 둘레 길이(Perimeter), 정사각형 여부(square)를 구하는 프로그램
   - 절차 지향 프로그래밍 방식으로 되어 있는 코드를 객체 지향 프로그래밍 방식으로 변경
      + Rectangle 클래스를 생성
      + RectangleOopMain에 해당 클래스를 사용하는 main() 코드를 만들 것

   - 절차 지향 코드 : RectangleProceduralMain (/oop/ex)
```java
package oop.ex;

public class RectangleProceduralMain {
    public static void main(String[] args) {
        int width = 5;
        int height = 8;

        int area = calculateArea(width, height);
        System.out.println("넓이: " + area);

        int perimeter = calculatePerimeter(width, height);
        System.out.println("둘레 길이: " + perimeter);

        boolean square = isSquare(width, height);
        System.out.println("정사각형 여부: " + square);
    }

    static int calculateArea(int width, int height) {
        return width * height;
    }

    static int calculatePerimeter(int width, int height) {
        return 2 * (width + height);
    }

    static boolean isSquare(int width, int height) {
        return width == height;
    }
}
```
  - 실행 결과
```
넓이: 40
둘레 길이: 26
정사각형 여부: false
```

   - Rectangle
```java
package oop.ex;

public class Rectangle {
    int width;
    int height;

    int calculateArea() {
        return width * height;
    }

    int calculatePerimeter() {
        return 2 * (width + height);
    }

    boolean isSquare() {
        return width == height;
    }
}
```

   - RectangleOopMain
```java
package oop.ex;

public class RectangleOopMain {
    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle();
        rectangle.width = 5;
        rectangle.height = 8;

        int area = rectangle.calculateArea();
        System.out.println("넓이: " + area);

        int perimeter = rectangle.calculatePerimeter();
        System.out.println("둘레 길이: " + perimeter);

        boolean square = rectangle.isSquare();
        System.out.println("정사각형 여부: " + square);
    }
}
```

-----
### 문제 2 - 문제2 - 객체 지향 계좌
-----
1. 은행 계좌를 객체로 설계
2. Account 클래스
   - int balance : 잔액
   - deposit(int amount) : 입금 메서드 (입금 시 잔액이 증가)
   - withdraw(int amount) : 출금 메서드 (출금 시 잔액이 감소)
      + 만약 잔액이 부족하면 잔액 부족을 출력해야 함
   - AccountMain 클래스를 만들고 main() 메서드를 통해 프로그램을 시작
     + 계좌에 10000원을 입금
     + 계좌에서 9000원을 출금
     + 계좌에서 2000원을 출금 시도 : 잔액 부족 출력을 확인
     + 잔고를 출력 : 잔고: 1000
   - 실행 결과
```
잔액 부족
잔고: 1000
```

3. Account
```java
package oop.ex;

public class Account {
    int balance; // 잔액

    void deposit(int amount) {
        balance += amount;
    }

    void withdraw(int amount) {
        if (balance >= amount) {
            balance -= amount;
        } else {
            System.out.println("잔액 부족");
        }
    }
}
```

4. AccountMain
```java
package oop.ex;

public class AccountMain {
    public static void main(String[] args) {
        Account account = new Account();

        account.deposit(10000);
        account.withdraw(9000);
        account.withdraw(2000);

        System.out.println("잔고 : " + account.balance);
    }
}
```
