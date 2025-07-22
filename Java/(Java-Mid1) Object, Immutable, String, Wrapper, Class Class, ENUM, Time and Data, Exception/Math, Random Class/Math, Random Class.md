-----
### Math, Random 클래스
-----
1. Math 클래스
   - Math 는 수 많은 수학 문제를 해결해주는 클래스
   - 실제 필요할 때 검색하거나 API 문서를 찾아볼 것
   - 기본 연산 메서드
      + abs(x) : 절대값
      + max(a, b) : 최대값
      + min(a, b) : 최소값
   - 지수 및 로그 연산 메서드
      + exp(x) : e^x 계산
      + log(x) : 자연 로그
      + log10(x) : 로그 10
      + pow(a, b) : a의 b 제곱
   - 반올림 및 정밀도 메서드
      + ceil(x) : 올림
      + floor(x) : 내림
      + rint(x) : 가장 가까운 정수로 반올림
      + round(x) : 반올림
   - 삼각 함수 메서드
      + sin(x) : 사인
      + cos(x) : 코사인
      + tan(x) : 탄젠트
  - 기타 유용한 메서드
      + sqrt(x) : 제곱근
      + cbrt(x) : 세제곱근
      + random() : 0.0과 1.0 사이의 무작위 값 생성

  - MathMain (/lang/math)
```java
package lang.math;

public class MathMain {
    public static void main(String[] args) {
        // 기본 연산 메서드
        System.out.println("Math.max(10, 20) = " + Math.max(10, 20)); // 최댓값
        System.out.println("Math.min(10, 20) = " + Math.min(10, 20)); // 최솟값
        System.out.println("abs(-10) = " + Math.abs(-10)); // 절댓값
        
        // 반올림 및 정밀도 메서드
        System.out.println("Math.ceil(2.1) = " + Math.ceil(2.1)); // 올림
        System.out.println("Math.floor(2.7) = " + Math.floor(2.7)); // 내림
        System.out.println("Math.round(2.5) = " + Math.round(2.5)); // 반올림
        
        // 기타 유형 메서드
        System.out.println("Math.sqrt(4) = " + Math.sqrt(4)); // 제곱근
        System.out.println("Math.random() = " + Math.random()); // 0.0 ~ 1.0 사이의 double 값
    }
}
```
   - 실행 결과
```
Math.max(10, 20) = 20
Math.min(10, 20) = 10
abs(-10) = 10
Math.ceil(2.1) = 3.0
Math.floor(2.7) = 2.0
Math.round(2.5) = 3
Math.sqrt(4) = 2.0
Math.random() = 0.1266463887024768
```
   - 참고 : 아주 정밀한 숫자와 반올림 계산이 필요하다면 BigDecimal을 검색

2. Random 클래스
   - 랜덤의 경우 Math.random()을 사용해도 되지만 Random 클래스를 사용하면 더욱 다양한 랜덤값을 구할 수 있음
   - 참고로 Math.random()도 내부에서는 Random 클래스를 사용
   - 참고로 Random 클래스는 java.util 패키지 소속
   - RandomMain
```java
package lang.math;

import java.util.Random;

public class RandomMain {
    public static void main(String[] args) {
        Random random = new Random();
        // Random random = new Random(1); // seed가 같으면 Random 결과는 동일

        int randomInt = random.nextInt();
        System.out.println("randomInt = " + randomInt);

        double randomDouble = random.nextDouble();// 0.0d ~ 1.0d
        System.out.println("randomDouble = " + randomDouble);

        boolean randomBoolean = random.nextBoolean();
        System.out.println("randomBoolean = " + randomBoolean);
        
        // 범위 조회
        int randomRange1 = random.nextInt(10);// 0 ~ 9까지 출력
        System.out.println("0 ~ 9 : " + randomRange1);
        
        int randomRange2 = random.nextInt(10) + 1; // 1 ~ 10까지 출력
        System.out.println("1 ~ 10 : " + randomRange2);
    }
}
```
   - 실행 결과 (항상 다름)
```
randomInt = -2022792510
randomDouble = 0.915021029132912
randomBoolean = false
0 ~ 9 : 2
1 ~ 10 : 3
```
   - random.nextInt() : 랜덤 int 값을 반환
   - nextDouble() : 0.0d ~ 1.0d 사이의 랜덤 double 값을 반환
   - nextBoolean() : 랜덤 boolean 값을 반환
   - 💡 nextInt(int bound) : 0 ~ bound 미만의 숫자를 랜덤으로 반환 (예를 들어서 3을 입력하면 0, 1, 2를 반환)
     + 1부터 특정 숫자의 int 범위를 구하는 경우 nextInt(int bound)의 결과에 +1을 하면 됨

   - 씨드 - Seed
      + 랜덤은 내부에서 씨드(Seed)값을 사용해서 랜덤 값을 구함
      + 그런데 이 씨드 값이 같으면 항상 같은 결과가 출력
```java
// Random random = new Random();
Random random = new Random(1); // seed가 같으면 Random의 결과가 같음
```
   - 실행 결과 : Seed가 같으면 실행 결과는 반복 실행해도 같음
```
randomInt = -2022792510
randomDouble = 0.915021029132912
randomBoolean = false
0 ~ 9 : 2
1 ~ 10 : 3
```
   - new Random() : 생성자를 비워두면 내부에서 System.nanoTime()에 여러가지 복잡한 알고리즘을 섞어서 씨드값을 생성하므로, 따라서 반복 실행해도 결과가 항상 달라짐
   - new Random(int seed) : 생성자에 씨드 값을 직접 전달할 수 있으며, 씨드 값이 같으면 여러번 반복 실행해도 실행 결과가 같음
      + 이렇게 씨드 값을 직접 사용하면 결과 값이 항상 같기 때문에 결과가 달라지는 랜덤 값을 구할 수 없음
      + 하지만 결과가 고정되기 때문에 테스트 코드 같은 곳에서 같은 결과를 검증할 수 있음
      + 참고로 마인크래프트 같은 게임은 게임을 시작할 때 지형을 랜덤으로 생성하는데, 같은 씨드값을 설정하면 같은 지형을 생성할 수 있음
