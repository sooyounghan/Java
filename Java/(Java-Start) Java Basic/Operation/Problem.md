-----
### 문제와 풀이
-----
1. 문제 1 - int와 평균
   - 클래스 이름은 OperationEx1 (/operator/ex)
      + num1 , num2 , num3 라는 이름의 세 개의 int 변수를 선언하고, 각각 10, 20, 30으로 초기화
      + 세 변수의 합을 계산하고, 그 결과를 sum 이라는 이름의 int 변수에 저장
      + 세 변수의 평균을 계산하고, 그 결과를 average 라는 이름의 int 변수에 저장 (평균 계산 시 소수점 이하의 결과는 버림)
      + sum 과 average 변수의 값을 출력
   - 참고 : 💡 자바에서 int 끼리의 나눗셈은 자동으로 소수점 이하를 버림
```java
package operator.ex;

public class OperationEx1 {
    public static void main(String[] args) {
        int num1 = 10;
        int num2 = 20;
        int num3 = 30;

        int sum = num1 + num2 + num3;
        int average = sum / 3; // int 끼리의 나눗셈은 자동으로 소수점 이하 버림

        System.out.println("sum = " + sum);
        System.out.println("average = " + average);
    }
}
```
  - 실행 결과
```
sum = 60
average = 20
```

2. 문제 2 - double의 평균
   - 클래스 이름 : OperationEx2
```java
// 다음 double 변수들을 선언하고 그 합과 평균을 출력하는 프로그램을 작성
double val1 = 1.5;
double val2 = 2.5;
double val3 = 3.5;
```
```java
package operator.ex;

public class OperationEx2 {
    public static void main(String[] args) {
        double val1 = 1.5;
        double val2 = 2.5;
        double val3 = 3.5;

        double sum = val1 + val2 + val3;
        double average = (sum / 3);

        System.out.println("sum = " + sum);
        System.out.println("average = " + average);
    }
}
```
  - 실행 결과
```
sum = 7.5
average = 2.5
```

3. 문제 3 - 합격 범위
   - 클래스 이름 : OperationEx3
     + int 형 변수 score를 선언
     + score가 80점 이상이고, 100점 이하이면 true를 출력하고, 아니면 false를 출력
```java
package operator.ex;

public class OperationEx3 {
    public static void main(String[] args) {
        int score = 80;
        
        boolean result = score >= 80 && score <= 100;

        System.out.println("result = " + result);
    }
}
```
  - 실행 결과
```
result = true
```
