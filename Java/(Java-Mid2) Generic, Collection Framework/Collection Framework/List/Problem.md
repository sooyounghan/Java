-----
### 문제와 풀이1
-----
1. 문제1 - 배열을 리스트로 변경하기
  - 문제 설명
    + ArrayEx1 는 배열을 사용 : 이 코드를 배열 대신에 리스트를 사용하도록 변경
    + 다음 코드와 실행 결과를 참고해서 리스트를 사용하는 ListEx1 클래스를 생성
  - ArrayEx1 (collection/list/test/ex1)
```java
package collection.list.test.ex1;

public class ArrayEx1 {
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
  - 실행 결과
```
점수 총합: 350
점수 평균: 70.0
```
  - ListEx1
```java
package collection.list.test.ex1;

import java.util.ArrayList;

public class ListEx1 {
    public static void main(String[] args) {
        ArrayList<Integer> students = new ArrayList<>();

        students.add(90);
        students.add(80);
        students.add(70);
        students.add(60);
        students.add(50);

        int total = 0;
        for (int i = 0; i < students.size(); i++) {
            total += students.get(i);
        }
        double average = (double) total / students.size();
        System.out.println("점수 총합: " + total);
        System.out.println("점수 평균: " + average);
    }
}
```

2. 문제2 - 리스트의 입력과 출력
  - 문제 설명
    + 사용자에게 n개의 정수를 입력받아서 List에 저장하고, 입력 순서대로 출력
    + 0을 입력하면 입력을 종료하고 결과를 출력
    + 출력시 출력 포멧은 1, 2, 3, 4, 5와 같이 , 쉼표를 사용해서 구분하고, 마지막에는 쉼표를 넣지 않아야 함
    + 실행 결과 예시를 참고
  - 실행 결과
```
n개의 정수를 입력하세요 (종료 0)
1
2
3
4
5
0
출력
1, 2, 3, 4, 5
```
  - ListEx2
```java
package collection.list.test.ex1;

import java.util.ArrayList;
import java.util.Scanner;

public class ListEx2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> numbers = new ArrayList();

        System.out.println("n개의 정수를 입력하세요. (종료 0)");
        while(true) {
            int input = scanner.nextInt();

            if(input == 0) {
                break;
            }

            numbers.add(input);
        }

        System.out.println("출력");
        for(int i = 0; i < numbers.size(); i++) {
            System.out.print(numbers.get(i));

            if(i < numbers.size() - 1) {
                System.out.print(", ");
            }
        }
    }
}
```

3. 문제3 - 합계와 평균
  - 사용자에게 n개의 정수를 입력받아서 List에 보관하고, 보관한 정수의 합계와 평균을 계산하는 프로그램을 작성
  - 실행 결과 예시
```
n개의 정수를 입력하세요 (종료 0)
1
2
3
4
5
0
입력한 정수의 합계: 15
입력한 정수의 평균: 3.0
```
  - ListEx3
```java
package collection.list.test.ex1;

import java.util.ArrayList;
import java.util.Scanner;

public class ListEx3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> numbers = new ArrayList();

        System.out.println("n개의 정수를 입력하세요. (종료 0)");
        while(true) {
            int input = scanner.nextInt();

            if(input == 0) {
                break;
            }

            numbers.add(input);
        }

        int sum = 0;
        /*
        for(int i = 0; i < numbers.size(); i++) {
            sum += numbers.get(i);
        }
        */

        for (Integer number : numbers) {
            sum += number;
        }
        
        double average = (double) sum / numbers.size();

        System.out.println("입력한 정수의 합계 : " + sum);
        System.out.println("입력한 정수의 평균 : " + average);
    }
}
```
