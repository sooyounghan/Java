-----
### 문제와 풀이 2
-----
1. 문제 - 가장 작은 수, 큰 수 찾기
   - 사용자로부터 n개의 정수를 입력받아 배열에 저장한 후, 배열 내에서 가장 작은 수와 가장 큰 수를 찾아 출력하는 프로그램을 작성
   - 실행 결과 예시를 참고
```
입력받을 숫자의 개수를 입력하세요 : 3
3개의 정수를 입력하세요:
1
2
5
가장 작은 정수 : 1
가장 큰 정수 : 5
```
   - ArrayEx6
```java
package array.ex;

import java.util.Scanner;

public class ArrayEx6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("입력받을 숫자의 개수를 입력하세요. : ");
        int n = scanner.nextInt();

        int[] numbers = new int[n];
        int minNumber, maxNumber;
        
        System.out.println(n + "개의 정수를 입려하세요. : ");
        for(int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }

        minNumber = maxNumber = numbers[0];
        for(int i = 1; i < n; i++) {
            if(numbers[i] < minNumber) {
                minNumber = numbers[i];
            }

            if(numbers[i] > maxNumber) {
                maxNumber = numbers[i];
            }
        }

        System.out.println("가장 작은 정수 : " + minNumber);
        System.out.println("가장 큰 정수 : " + maxNumber);
    }
}
```

2. 문제 - 2차원 배열1
    - 사용자로부터 4명 학생의 국어, 수학, 영어 점수를 입력받아 각 학생의 총점과 평균을 계산하는 프로그램을 작성
    - 2차원 배열을 사용하고, 실행 결과 예시를 참고
```
1번 학생의 성적을 입력하세요. : 
국어 점수 : 100
영어 점수 : 80
수학 점수 : 70

2번 학생의 성적을 입력하세요. : 
국어 점수 : 30
영어 점수 : 40
수학 점수 : 50

3번 학생의 성적을 입력하세요. : 
국어 점수 : 60
영어 점수 : 70
수학 점수 : 50

4번 학생의 성적을 입력하세요. : 
국어 점수 : 90
영어 점수 : 100
수학 점수 : 80

1번 학생의 총점 : 250, 평균 : 83.33333333333333
2번 학생의 총점 : 120, 평균 : 40.0
3번 학생의 총점 : 180, 평균 : 60.0
4번 학생의 총점 : 270, 평균 : 90.0
```
   - ArrayEx7
```java
package array.ex;

import java.util.Scanner;

public class ArrayEx7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[][] scores = new int[4][3];
        String[] subjects = { "국어", "영어", "수학" };

        for(int i = 0; i < scores.length; i++) {
            System.out.println((i + 1) + "번 학생의 성적을 입력하세요. : ");

            for (int j = 0; j < subjects.length; j++) {
                System.out.print(subjects[j] + " 점수 : ");
                scores[i][j] = scanner.nextInt();
            }
        }

        for(int i = 0; i < scores.length; i++) {
            int total = 0;
            double average;

            for(int j = 0; j < subjects.length; j++) {
                total += scores[i][j];
            }

            average = (double) total / subjects.length;

            System.out.println((i + 1) + "번 학생의 총점 : " + total + ", 평균 : " + average);
        }
    }
}
```

3. 문제 3 - 2차원 배열 2
   - 이전 문제에서 학생수를 입력받도록 개선
   - 실행 결과 예시를 참고
```
학생 수를 입력하세요. : 3
1번 학생의 성적을 입력하세요. : 
국어 점수 : 10
영어 점수 : 20
수학 점수 : 30
2번 학생의 성적을 입력하세요. : 
국어 점수 : 10
영어 점수 : 10
수학 점수 : 10
3번 학생의 성적을 입력하세요. : 
국어 점수 : 20
영어 점수 : 20
수학 점수 : 20
1번 학생의 총점 : 60, 평균 : 20.0
2번 학생의 총점 : 30, 평균 : 10.0
3번 학생의 총점 : 60, 평균 : 20.0
```
  - ArrayEx8
```java
package array.ex;

import java.util.Scanner;

public class ArrayEx8 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] subjects = { "국어", "영어", "수학" };

        System.out.print("학생 수를 입력하세요. : ");
        int studentCount = scanner.nextInt();
        int[][] scores = new int[studentCount][3];

        for(int i = 0; i < studentCount; i++) {
            System.out.println((i + 1) + "번 학생의 성적을 입력하세요. : ");

            for (int j = 0; j < subjects.length; j++) {
                System.out.print(subjects[j] + " 점수 : ");
                scores[i][j] = scanner.nextInt();
            }
        }

        for(int i = 0; i < studentCount; i++) {
            int total = 0;
            double average;

            for(int j = 0; j < subjects.length; j++) {
                total += scores[i][j];
            }

            average = (double) total / subjects.length;

            System.out.println((i + 1) + "번 학생의 총점 : " + total + ", 평균 : " + average);
        }
    }
}
```
