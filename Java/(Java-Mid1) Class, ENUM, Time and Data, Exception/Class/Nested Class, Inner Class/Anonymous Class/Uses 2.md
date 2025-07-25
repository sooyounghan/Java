-----
### 익명 클래스 활용 2
-----
1. 리팩토링 전 - Ex1Main
```java
package nested.anonymous.ex;

import java.util.Random;

public class Ex1Main {
    public static void helloDice() {
        System.out.println("프로그램 시작");

        // 코드 조각 시작
        int randomValue = new Random().nextInt(6) + 1;
        System.out.println("주사위 = " + randomValue);
        // 코드 조각 종료

        System.out.println("프로그램 종료");
    }

    public static void helloSum() {
        System.out.println("프로그램 시작");

        // 코드 조각 시작
        for(int i = 1; i <= 3;i++) {
            System.out.println("i = " + i);
        }
        // 코드 조각 종료

        System.out.println("프로그램 종료");
    }

    public static void main(String[] args) {
        helloDice();
        helloSum();
    }
}
```
  - 실행 결과
```
프로그램 시작
주사위 = 6
프로그램 종료

프로그램 시작
i = 1
i = 2
i = 3
프로그램 종료
```
   - 이 코드를 앞에서 리팩토링 한 예와 같이 하나의 메서드에서 실행할 수 있도록 리팩토링
   - 참고로 앞의 문제는 변하는 문자열을 외부에서 전달하면
   - 이번에는 문자열 같은 데이터가 아니라 코드 조각을 전달

2. 리팩토링 후
   - Process
```java
package nested.anonymous.ex;

public interface Process {
    void run();
}
```
   - Ex1RefMain1V1
```java
package nested.anonymous.ex;

import java.util.Random;

// 정적 중첩 클래스 사용
public class Ex1RefMain {
    public static void hello(Process process) {
        System.out.println("프로그램 시작");

        // 코드 조각 시작
        process.run();
        // 코드 조각 종료
        
        System.out.println("프로그램 종료");
    }

    static class Dice implements Process {
        @Override
        public void run() {
            int randomValue = new Random().nextInt(6) + 1;
            System.out.println("주사위 = " + randomValue);
        }
    }

    static class Sum implements Process {
        @Override
        public void run() {
            for(int i = 1; i <= 3; i++) {
                System.out.println("i = " + i);
            }
        }
    }

    public static void main(String[] args) {
        Process dice = new Dice();
        Process sum = new Sum();
        
        System.out.println("Hello 실행");
        hello(dice);
        hello(sum);
    }
}
```
   - 데이터를 전달하는 수준을 넘어, 코드 조각을 전달해야 함
   - 리팩토링 전
```java
public static void helloDice() {
    System.out.println("프로그램 시작");

    // 코드 조각 시작
    int randomValue = new Random().nextInt(6) + 1;
    System.out.println("주사위 = " + randomValue);
    // 코드 조각 종료

    System.out.println("프로그램 종료");
}

public static void helloSum() {
    System.out.println("프로그램 시작");

    // 코드 조각 시작
    for(int i = 1; i <= 3;i++) {
        System.out.println("i = " + i);
    }
    // 코드 조각 종료

    System.out.println("프로그램 종료");
}
```
  - 리팩토링 진행 단계
```java
public static void hello(Process process) {
    System.out.println("프로그램 시작");

    // 코드 조각 시작
    int randomValue = new Random().nextInt(6) + 1;
    System.out.println("주사위 = " + randomValue);
    // 코드 조각 종료

    // 코드 조각 시작
    for(int i = 1; i <= 3;i++) {
        System.out.println("i = " + i);
    }
    // 코드 조각 종료

    System.out.println("프로그램 종료");
}
```
   - 프로그램 시작, 프로그램 종료를 출력하는 부분은 변하지 않는 부분
   - 코드 조각을 시작하고 종료하는 부분은 변하는 부분
   - 결국 코드 조각을 시작하고 종료하는 부분을 외부에서 전달 받아야 함 
   - 코드 조각은 보통 메서드(함수)에 정의
     + 따라서 코드 조각을 전달하기 위해서는 메서드가 필요
     + 인스턴스를 전달하고, 인스턴스에 있는 메서드를 호출하면 됨

   - 이 문제를 해결하기 위해 인터페이스를 정의하고 구현 클래스 생성
```java
public interface Process {
     void run();
}

static class Dice implements Process {
     @Override
     public void run() {
         int randomValue = new Random().nextInt(6) + 1;
         System.out.println("주사위 = " + randomValue);
     }
}

static class Sum implements Process {
     @Override
     public void run() {
         for (int i = 1; i <= 3; i++) {
             System.out.println("i = " + i);
         }
     }
}
```
   - Dice, Sum 각각의 클래스는 Process 인터페이스를 구현하고 run() 메서드에 필요한 코드 조각을 구현
   - 여기서는 정적 중첩 클래스를 사용 (물론 정적 중첩 클래스가 아니라 외부에 클래스를 직접 만들어도 됨)

   - 리팩토링 완료
```java
public static void hello(Process process) {
     System.out.println("프로그램 시작"); // 변하지 않는 부분

     // 코드 조각 시작
     process.run();
     // 코드 조각 종료

     System.out.println("프로그램 종료"); // 변하지 않는 부분
}
```
   - Process process 매개변수를 통해 인스턴스를 전달할 수 있음
   - 이 인스턴스의 run() 메서드를 실행하면 필요한 코드 조각을 실행할 수 있음
   - 이 때 다형성을 활용해서 외부에서 전달되는 인스턴스에 따라 각각 다른 코드 조각이 실행
```java
public static void main(String[] args) {
     Process dice = new Dice();
     Process sum = new Sum();

     System.out.println("Hello 실행");
     hello(dice);
     hello(sum);
}
```
   - hello()를 호출할 때 어떤 인스턴스를 전달하는 가에 따라서 다른 결과가 실행
   - hello(dice)를 호출하면 주사위 로직이, hello(sum) 을 호출하면 계산 로직이 수행

   - 실행 결과
```
Hello 실행

프로그램 시작
주사위 = 5 // 랜덤
프로그램 종료

프로그램 시작
i = 1
i = 2
i = 3
프로그램 종료
```
   - 문자열 같은 데이터를 메서드에 전달할 때는 String, int 와 같은 각 데이터에 맞는 타입을 전달하면 됨
   - 코드 조각을 메서드에 전달할 때는 인스턴스를 전달하고 해당 인스턴스에 있는 메서드를 호출하면 됨
