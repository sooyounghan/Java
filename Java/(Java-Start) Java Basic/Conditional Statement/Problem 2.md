-----
### 문제와 풀이 2
-----
1. 문제 : "평점에 따른 영화 추천하기"
   - 요청한 평점 이상의 영화를 찾아서 추천하는 프로그램을 작성
      + 어바웃타임 - 평점 9
      + 토이 스토리 - 평점 8
      + 고질라 - 평점 7
   - 평점 변수는 double rating 을 사용
   - if 문을 활용해서 문제를 풀 것
  - 출력 예시
```
rating: 9
출력 : '어바웃타임'을 추천합니다.

rating: 8
출력 : '어바웃타임'을 추천합니다.
       '토이 스토리'를 추천합니다.

rating: 7.1
출력: '어바웃타임'을 추천합니다.
      '토이 스토리'를 추천합니다.

rating: 7
출력: '어바웃타임'을 추천합니다.
      '토이 스토리'를 추천합니다.
      '고질라'를 추천합니다.
```
   - MovieRateEx
```java
package cond.ex;

public class MovieRateEx {
    public static void main(String[] args) {
        double rating = 7.1;

        if(rating <= 9) {
            System.out.println("'어바웃타임'을 추천합니다.");
        } if(rating <= 8) {
            System.out.println("'토이 스토리'를 추천합니다");
        } if(rating <= 7) {
            System.out.println("'고질라'를 추천합니다.");
        }
    }
}
```

2. 문제 : "학점에 따른 성취도 출력하기"
   - String grade 라는 문자열을 만들고, 학점에 따라 성취도를 출력하는 프로그램을 작성
   - 각 학점은 다음과 같은 성취도를 나타냄
      + "A" : "탁월한 성과입니다!"
      + "B" : "좋은 성과입니다!"
      + "C" : "준수한 성과입니다!"
      + "D" : "향상이 필요합니다."
      + "F" : "불합격입니다."
      + 나머지 : "잘못된 학점입니다."
   - switch 문을 사용해서 문제를 해결
   - 출력 예시
```
grade : "B"
출력: "좋은 성과입니다!"

grade : "A"
출력: "탁월한 성과입니다!"

grade : "F"
출력: "불합격입니다." 
```
   - GradeSwitchEx
```java
package cond.ex;

public class GradeSwitchEx {
    public static void main(String[] args) {
        String grade = "B";

        switch (grade) {
            case "A":
                System.out.println("탁월한 성과입니다!");
                break;

            case "B":
                System.out.println("좋은 성과입니다!");
                break;

            case "C":
                System.out.println("준수한 성과입니다!");
                break;

            case "D":
                System.out.println("향상이 필요합니다.");
                break;

            case "F":
                System.out.println("불합격입니다.");
                break;

            default:
                System.out.println("잘못된 학점입니다.");
                break;
        }
    }
}
```

3. 문제 : 더 큰 숫자 찾기
    - 두 개의 정수 변수 a와 b를 가지고 있음
    - a의 값은 10이고, b의 값은 20
    - 삼항 연산자를 사용하여 두 숫자 중 더 큰 숫자를 출력하는 코드를 작성
    - 출력 예시
```
더 큰 숫자는 20입니다.
```
   - CondOpEx
```java
package cond.ex;

public class CondOpEx {
    public static void main(String[] args) {
        int a = 10;
        int b = 20;

        int max = a > b ? a : b;

        System.out.println("더 큰 숫자는 " + max + "입니다.");
    }
}
```

4. 문제 : 홀수-짝수 찾기
   - 정수 x가 주어짐
   - x가 짝수이면 "짝수"를, x 가 홀수이면 "홀수"를 출력하는 프로그램을 작성
   - 삼항 연산자를 사용
     + 참고로 x % 2를 사용하면 홀수, 짝수를 쉽게 계산
   - 출력 예시
```
x: 2
출력: x = 2, 짝수

x: 3
출력: x = 3, 홀수
```
   - EvenOddEx
```java
package cond.ex;

public class EvenOddEx {
    public static void main(String[] args) {
        int x = 2;

        String result = (x % 2 == 0) ? "짝수" : "홀수";

        System.out.println("x = " + x + ", result = " + result);
    }
}
```
