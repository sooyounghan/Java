-----
### 문제와 풀이 1
-----
1. 문제 - 배열을 사용하도록 변경
  - 다음 문제를 배열을 사용해서 개선
```java
package array.ex;
public class ArrayEx1Ref {
   public static void main(String[] args) {
       int[] students = {90, 80, 70, 60, 50};
       int total = 0;

       for (int i = 0; i < students.length; i++) {
           total += students[i];
       }

       double average = (double) total / students.length;

       System.out.println("점수 총합: " + total);
       System.out.println("점수 평균: " + average);
   }
}
```
  - ArrayEx1 (/array/ex)
```java
package array.ex;

public class ArrayEx1 {
    public static void main(String[] args) {
        int[] students = { 90, 80, 70, 60, 50 };
        int total = 0;

        for(int i = 0; i < students.length; i++) {
            total += students[i];
        }

        double average = (double) total / students.length;

        System.out.println("점수 총합 : " + total);
        System.out.println("점수 평균 : " + average);
    }
}
```
  - 실행 결과
```
점수 총합 : 350
점수 평균 : 70.0
```

2. 문제 - 배열의 입력과 출력
  - 사용자에게 5개의 정수를 입력받아서 배열에 저장하고, 입력 순서대로 출력
  - 출력시 출력 포멧은 1, 2, 3, 4, 5와 같이 , 쉼표를 사용해서 구분하고, 마지막에는 쉼표를 넣지 않아야 함
  - 실행 결과 예시를 참고
```
5개의 정수를 입력하세요:
1
2
3
4
5
출력
1, 2, 3, 4, 5
```
  - ArrayEx2
```java
package array.ex;

import java.util.Scanner;

public class ArrayEx2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] numbers = new int[5];

        System.out.println("5개의 정수를 입력하세요 : ");

        for(int i = 0; i < numbers.length; i++) {
            numbers[i] = scanner.nextInt();
        }

        System.out.println("출력");
        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i]);

            if(i < numbers.length - 1) {
                System.out.print(", ");
            }
        }
    }
}
```

3. 문제 - 배열과 역순 출력
  - 사용자에게 5개의 정수를 입력받아서 배열에 저장하고, 입력받은 순서의 반대인 역순으로 출력
  - 출력시 출력 포멧은 5, 4, 3, 2, 1과 같이, 쉼표를 사용해서 구분하고, 마지막에는 쉼표를 넣지 않아야 함
  - 실행 결과 예시
```
5개의 정수를 입력하세요:
1
2
3
4
5
입력한 정수를 역순으로 출력:
5, 4, 3, 2, 1
```
  - ArrayEx3
```java
package array.ex;

import java.util.Scanner;

public class ArrayEx3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] numbers = new int[5];

        System.out.println("5개의 정수를 입력하세요 : ");

        for(int i = 0; i < numbers.length; i++) {
            numbers[i] = scanner.nextInt();
        }

        System.out.println("출력");
        for (int i = numbers.length - 1; i >= 0; i--) {
            System.out.print(numbers[i]);

            if(i > 0) {
                System.out.print(", ");
            }
        }
    }
}
```

4. 문제 - 합계와 평균
   - 사용자에게 5개의 정수를 입력받아서 이들 정수의 합계와 평균을 계산하는 프로그램을 작성
   - 실행 결과 예시
```
5개의 정수를 입력하세요:
1
2
3
4
5
입력한 정수의 합계: 15
입력한 정수의 평균: 3.0
```
   - ArrayEx4
```java
package array.ex;

import java.util.Scanner;

public class ArrayEx4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] numbers = new int[5];
        int sum = 0;

        System.out.println("5개의 정수를 입력하세요 : ");

        for(int i = 0; i < numbers.length; i++) {
            numbers[i] = scanner.nextInt();
        }

        for (int i = 0; i < numbers.length; i++) {
            sum += numbers[i];
        }

        double average = (double) sum / numbers.length;

        System.out.println("입력한 정수의 합계 : " + sum);
        System.out.println("입력한 정수의 평균 : " + average);
    }
}
```

5. 문제 - 합계와 평균 2
   - 이전 문제에서 입력받을 숫자의 개수를 입력받도록 개선
   - 실행 결과 예시
```
입력받을 숫자의 개수를 입력하세요:3
3개의 정수를 입력하세요:
1
2
3
입력한 정수의 합계: 6
입력한 정수의 평균: 2.0
```
```
입력받을 숫자의 개수를 입력하세요:5
5개의 정수를 입력하세요:
1
2
3
4
5
입력한 정수의 합계: 15
입력한 정수의 평균: 3.0
```
   - ArrayEx5
```java
package array.ex;

import java.util.Scanner;

public class ArrayEx5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        System.out.print("입력받을 숫자의 개수를 입력하세요 : ");
        int count = scanner.nextInt();
        int[] numbers = new int[count];
        int sum = 0;
        double average;

        System.out.println(count + "개의 정수를 입력하세요 : ");
        for(int i = 0; i < numbers.length; i++) {
            numbers[i] = scanner.nextInt();
            sum += numbers[i];
        }

        average = (double) sum / numbers.length;

        System.out.println("입력한 정수의 합계 : " + sum);
        System.out.println("입력한 정수의 평균 : " + average);
    }
}
```

