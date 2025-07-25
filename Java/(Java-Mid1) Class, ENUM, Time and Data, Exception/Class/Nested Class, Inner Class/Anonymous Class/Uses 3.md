-----
### 익명 클래스 활용 3
-----
1. 지역 클래스를 사용해서 같은 기능 구현
2. Ex1RefMainV2
```java
package nested.anonymous.ex;

import java.util.Random;

// 지역 클래스 사용
public class Ex1RefMainV2 {
    public static void hello(Process process) {
        System.out.println("프로그램 시작");

        // 코드 조각 시작
        process.run();
        // 코드 조각 종료

        System.out.println("프로그램 종료");
    }

    public static void main(String[] args) {
        class Dice implements Process {
            @Override
            public void run() {
                int randomValue = new Random().nextInt(6) + 1;
                System.out.println("주사위 = " + randomValue);
            }
        }

        class Sum implements Process {
            @Override
            public void run() {
                for(int i = 1; i <= 3; i++) {
                    System.out.println("i = " + i);
                }
            }
        }
        
        Process dice = new Dice();
        Process sum = new Sum();

        System.out.println("Hello 실행");
        hello(dice);
        hello(sum);
    }
}
```
   - 실행 결과는 기존과 동일

2. 익명 클래스 사용 1
   - 앞의 지역 클래스는 간단히 한번만 생성해서 사용
   - 이런 경우 익명 클래스로 변경할 수 있음
   - Ex1RefMainV3
```java
package nested.anonymous.ex;

import java.util.Random;

// 지역 클래스 사용
public class Ex1RefMainV3 {
    public static void hello(Process process) {
        System.out.println("프로그램 시작");

        // 코드 조각 시작
        process.run();
        // 코드 조각 종료

        System.out.println("프로그램 종료");
    }

    public static void main(String[] args) {
        Process dice = new Process() {
            @Override
            public void run() {
                int randomValue = new Random().nextInt(6) + 1;
                System.out.println("주사위 = " + randomValue);
            }
        };

        Process sum = new Process() {
            @Override
            public void run() {
                for(int i = 1; i <= 3; i++) {
                    System.out.println("i = " + i);
                }
            }
        };

        System.out.println("Hello 실행");
        hello(dice);
        hello(sum);
    }
}
```
   - 실행 결과는 기존과 동일

3. 익명 클래스 2 - 참조값 직접 전달
   - 익명 클래스의 참조값을 변수에 담아둘 필요 없이, 인수로 바로 전달할 수 있음
   - Ex1RefMainV4
```java
package nested.anonymous.ex;

import java.util.Random;

// 익명 클래스 참조 바로 전달
public class Ex1RefMainV4 {
    public static void hello(Process process) {
        System.out.println("프로그램 시작");

        // 코드 조각 시작
        process.run();
        // 코드 조각 종료

        System.out.println("프로그램 종료");
    }

    public static void main(String[] args) {
        hello(new Process() {
            @Override
            public void run() {
                int randomValue = new Random().nextInt(6) + 1;
                System.out.println("주사위 = " + randomValue);
            }
        });

        hello(new Process() {
            @Override
            public void run() {
                for(int i = 1; i <= 3; i++) {
                    System.out.println("i = " + i);
                }
            }
        });
    }
}
```
  - 실행 결과는 기존과 동일

4. 람다 (Lambda)
   - 자바8 이전까지 메서드에 인수로 전달할 수 있는 것은 크게 2가지
     + int, double과 같은 기본형 타입
     + Process Member와 같은 참조형 타입 (인스턴스)
   - 결국 메서드에 인수로 전달할 수 있는 것은 간단한 데이터나, 인스턴스의 참조
```java
public void runDice() {
     int randomValue = new Random().nextInt(6) + 1;
     System.out.println("주사위 = " + randomValue);
}
public void runSum() {
     for (int i = 1; i <= 3; i++) {
         System.out.println("i = " + i);
     }
}
```

```java
hello(메서드 전달: runDice())
hello(메서드 전달: runRun())
```
   - 자바8에 들어서면서 큰 변화가 있었는데 바로 메서드(더 정확히는 함수)를 인수로 전달할 수 있게 됨 : 이것을 간단히 람다(Lambda)

5. 리팩토링 - 람다
```java
package nested.anonymous.ex;

import java.util.Random;

// 람다 사용
public class Ex1RefMainV5 {
    public static void hello(Process process) {
        System.out.println("프로그램 시작");

        // 코드 조각 시작
        process.run();
        // 코드 조각 종료

        System.out.println("프로그램 종료");
    }

    public static void main(String[] args) {
        hello(() -> {
            int randomValue = new Random().nextInt(6) + 1;
            System.out.println("주사위 = " + randomValue);
        });

        hello(() -> {
            for(int i = 1; i <= 3; i++) {
                System.out.println("i = " + i);
            }
        });
    }
}
```
  - 코드를 보면 클래스나 인스턴스를 정의하지 않고, 메서드(더 정확히는 함수)의 코드 블럭을 직접 전달하는 것을 확인할 수 있음
