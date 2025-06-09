-----
### Scanner
-----
1. System.out을 통해서 출력을 했듯이, System.in을 통해서 사용자의 입력을 받을 수 있음
2. 자바가 제공하는 System.in을 통해서 사용자 입력을 받으려면 여러 과정을 거쳐야해서 복잡하고 어려움
3. 자바는 이런 문제를 해결하기 위해 Scanner 라는 클래스를 제공
4. Scanner1 (/scanner)
```java
package scanner;

import java.util.Scanner;

public class Scanner1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("문자열을 입력하세요. : ");
        String str = scanner.nextLine(); // 입력을 String으로 가져옴
        System.out.println("입력한 문자열 : " + str);

        System.out.println("정수를 입력하세요. : ");
        int intValue = scanner.nextInt();
        System.out.println("입력한 정수 : " + intValue); // 입력을 int로 가져옴

        System.out.println("실수를 입력하세요 : ");
        double doubleValue = scanner.nextDouble(); // 입력을 double로 가져옴
        System.out.println("입력한 실수 : " + doubleValue);
    }
}
```
  - Scanner scanner = new Scanner(System.in);
      + Scanner 의 기능을 사용하기 위해 new 를 사용해서 Scanner를 만든다 정도로 이해
      + Scanner 는 System.in 을 사용해서 사용자의 입력을 편리하게 받도록 도와줌
      + Scanner scanner 코드는 scanner 변수를 선언하는 것
      + 이제부터 scanner 변수를 통해서 scanner 를 사용할 수 있음
  
   - scanner.nextLine() : 엔터(\n)을 입력할 때 까지 문자를 가져옴 
   - scanner.nextInt() : 입력을 int형으로 가져오며, 정수 입력에 사용
   - scanner.nextDouble() : 입력을 double 형으로 가져오며, 실수 입력에 사용
   - 출력 예시
```
문자열을 입력하세요. : hello
입력한 문자열 : hello
정수를 입력하세요. : 10
입력한 정수 : 10
실수를 입력하세요 : 1.5
입력한 실수 : 1.5
```

5. 주의 - 다른 타입 입력시 오류
  - 타입이 다르면 오류가 발생
  - 예제와 같이 숫자에 문자를 입력하면 오류가 발생
```
문자열을 입력하세요 : hello
입력한 문자열 : hello
정수를 입력하세요 : 백만원
Exception in thread "main" java.util.InputMismatchException
    at java.base/java.util.Scanner.throwFor(Scanner.java:939)
    at java.base/java.util.Scanner.next(Scanner.java:1594)
    at java.base/java.util.Scanner.nextInt(Scanner.java:2258)
    at java.base/java.util.Scanner.nextInt(Scanner.java:2212)
    at scanner.Scanner1.main(Scanner1.java:15)
```

6. print() vs println()
   - 다음 코드를 보면 println()이 아니라 print()를 사용
```java
System.out.print("문자열을 입력하세요 : ")
```

   - print() : 출력하고 다음 라인으로 넘기지 않음
```java
System.out.print("hello");
System.out.print("world");

// 결과 : helloworld
```
   - println() : 출력하고 다음 라인으로 넘김
```java
System.out.println("hello");
System.out.println("world");

// 결과 :
// hello
// world
```

  - 엔터 키를 치면 (\n) 이라는 문자를 남기는 것
  - new line character, 한글로 줄바꿈 문자 또는 개행 문자 : 이름 그대로 새로운 라인으로 넘기라는 뜻으로, 콘솔에서는 이 문자를 보고 다음 라인으로 넘김
  - println()은 print()의 마지막에 \n을 추가
  - 따라서 다음 코드는 println()과 같음
```java
System.out.print("hello\n");
System.out.print("world\n");

// 결과 :
hello
world
```
