-----
### 문제와 풀이 1
-----
1. 문제 - 이름 나이 입력받고 출력하기
   - 사용자로부터 입력받은 이름과 나이를 출력
   - 출력 형태 : "당신의 이름은 [이름]이고, 나이는 [나이]살입니다." 이어야 함
   - 실행 결과 예시
```
당신의 이름을 입력하세요 : 자바
당신의 나이를 입력하세요 : 30
당신의 이름은 자바이고, 나이는 30살입니다.
```
  - ScannerEx1 (/scanner/ex)
```java
package scanner.ex;

import java.util.Scanner;

public class ScannerEx1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("당신의 이름을 입력하세요. : ");
        String name = scanner.nextLine(); // 이름 입력

        System.out.print("당신의 나이를 입력하세요. : ");
        int age = scanner.nextInt(); // 나이 입력 (나이는 정수이므로 nextInt())

        System.out.println("당신의 이름은 " + name + "이고, 나이는 " + age + "살 입니다.");
    }
}
```

2. 홀수, 짝수
   - 사용자로부터 하나의 정수를 입력받고, 이 정수가 홀수인지 짝수인지 판별하는 프로그램을 작성
   - 실행 결과 예시
```
하나의 정수를 입력하세요 : 1
입력한 숫자 1는 홀수입니다.
```
```
하나의 정수를 입력하세요 : 4
입력한 숫자 4는 짝수입니다.
```
  - ScannerEx2
```java
package scanner.ex;

import java.util.Scanner;

public class ScannerEx2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("하나의 정수를 입력하세요. : ");
        int number = scanner.nextInt();

        if(number % 2 == 0) {
            System.out.println("입력한 숫자 " + number + "은 짝수입니다.");
        } else {
            System.out.println("입력한 숫자 " + number + "은 홀수입니다.");
        }
    }
}
```

3. 문제 - 음식점 주문
    - 사용자로부터 음식의 이름(foodName), 가격(foodPrice), 그리고 수량(foodQuantity)을 입력받아, 주문한 음식의 총 가격을 계산하고 출력하는 프로그램을 작성
    - 음식의 총 가격을 계산 : 총 가격은 각 음식의 가격(foodPrice)과 수량(foodQuantity)을 곱한 값이며, 이를 totalPrice 라는 이름의 변수에 저장
    - 주문 정보와 총 가격을 출력 : 출력 형태는 "[음식 이름] [수량]개를 주문하셨습니다. 총 가격은 [총 가격]원입니다." 이어야 함
    - 실행 결과 예시
```
음식 이름을 입력해주세요 : 피자
음식의 가격을 입력해주세요 : 20000
음식의 수량을 입력해주세요 : 2
피자 2개를 주문하셨습니다. 총 가격은 40000원입니다.
```
   - ScannerEx3
```java
package scanner.ex;

import java.util.Scanner;

public class ScannerEx3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("음식 이름을 입력해주세요. : ");
        String foodName = scanner.nextLine();

        System.out.print("음식의 가격을 입력해주세요. : ");
        int foodPrice = scanner.nextInt();

        System.out.print("음식의 수량을 입력해주세요. : ");
        int foodQuantity = scanner.nextInt();

        int totalPrice = foodPrice * foodQuantity;

        System.out.println(foodName + " " + foodQuantity + "개를 주문하셨습니다. 총 가격은 " + totalPrice + "입니다.");
    }
}
```

4. 문제 - 구구단 출력
    - 사용자로부터 하나의 정수 n 을 입력받고, 입력받은 정수 n 의 구구단을 출력하는 프로그램을 작성
    - 실행 결과 예시
```
구구단의 단 수를 입력해주세요: 8
8단의 구구단:
8 x 1 = 8
8 x 2 = 16
8 x 3 = 24
8 x 4 = 32
8 x 5 = 40
8 x 6 = 48
8 x 7 = 56
8 x 8 = 64
8 x 9 = 72
```
   - ScannerEx4
```java
package scanner.ex;

import java.util.Scanner;

public class ScannerEx4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("구구단의 단 수를 입력해주세요. : ");
        int n = scanner.nextInt();

        System.out.println(n + "단의 구구단 : ");

        for(int i = 1; i <= 9; i++) {
            System.out.println(n + " x " + i + " = " + (n * i));
        }
    }
}
```
