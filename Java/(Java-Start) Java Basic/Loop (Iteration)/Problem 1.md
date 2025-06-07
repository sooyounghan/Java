-----
### 문제와 풀이 1
-----
1. 문제 : 자연수 출력
  - 처음 10개의 자연수를 출력하는 프로그램을 작성할 것 (이 때, count 라는 변수를 사용할 것)
  - while문, for문 2가지 버전
  - WhileEx1 (/loop/ex)
```java
package loop.ex;

public class WhileEx1 {
    public static void main(String[] args) {
        int count = 1;

        while (count <= 10) {
            System.out.println(count);

            count++;
        }
    }
}
```
   - ForEx1
```java
package loop.ex;

public class ForEx1 {
    public static void main(String[] args) {
        for(int count = 1; count <= 10; count++) {
            System.out.println(count);
        }
    }
}
```
   - 실행 결과
```
1
2
3
4
5
6
7
8
9
10
```

2. 문제 : 짝수 출력
   - 반복문을 사용하여 처음 10개의 짝수를 출력하는 프로그램을 작성해 볼 것 (이 때, num 이라는 변수를 사용하여 수를 표현)
   - while문, for문 2가지 버전의 정답
   - WhileEx2
```java
package loop.ex;

public class WhileEx2 {
    public static void main(String[] args) {
        int num = 2;
        int count = 1;

        while(count <= 10) {
            System.out.println(num);

            num += 2;
            count++;
        }
    }
}
```
   - ForEx2
```java
package loop.ex;

public class ForEx2 {
    public static void main(String[] args) {
        for(int count = 1, num = 2; count <= 10; count++, num += 2) {
            System.out.println(num);
        }
    }
}
```
  - 실행 결과
```
2
4
6
8
10
12
14
16
18
20
```

3. 문제 : 누적 합 계산
   - 반복문을 사용하여 1부터 max 까지의 합을 계산하고 출력하는 프로그램을 작성
   - 이때, sum 이라는 변수를 사용하여 누적 합을 표현하고, i 라는 변수를 사용하여 카운트(1부터 max까지 증가하는 변수)를 수행
   - while문, for문 2가지 버전의 정답
   - WhileEx3
```java
package loop.ex;

public class WhileEx3 {
    public static void main(String[] args) {
        int max = 100;

        int sum = 0;
        int i = 1;

        while(i <= max) {
            sum += i;
            i++;
        }

        System.out.println(sum);
    }
}
```
   - ForEx3
```java
package loop.ex;

public class ForEx3 {
    public static void main(String[] args) {
        int max = 100;
        int sum = 0;

        for(int i = 1; i <= max; i++) {
            sum += i;
        }

        System.out.println(sum);
    }
}
```
  - 실행 결과
```
5050
```
