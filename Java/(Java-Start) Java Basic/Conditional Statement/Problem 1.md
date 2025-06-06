-----
### 문제와 풀이 1
-----
1. 문제 : "학점 계산하기"
  - 학생의 점수를 기반으로 학점을 출력하는 자바 프로그램을 작성
  - 다음과 같은 기준을 따름
    + 90점 이상 : "A"
    + 80점 이상 90점 미만 : "B"
    + 70점 이상 80점 미만 : "C"
    + 60점 이상 70점 미만 : "D"
    + 60점 미만 : "F"
  - 점수는 변수(int score)로 지정하고, 해당 변수를 기반으로 학점을 출력
  - 출력 예시
```
score : 95
출력: 학점은 A입니다.

score : 85
출력: 학점은 B입니다.

score : 75
출력: 학점은 C입니다.

score : 65
출력: 학점은 D입니다.

score : 55
출력: 학점은 F입니다.
```
  - ScoreEx (/cond/ex)
```java
package cond.ex;

public class ScoreEx {
    public static void main(String[] args) {
        int score = 85;

        if(score >= 90) {
            System.out.println("출력 : 학점은 A입니다.");
        } else if(score >= 80) {
            System.out.println("출력 : 학점은 B입니다.");
        } else if(score >= 70) {
            System.out.println("출력 : 학점은 C입니다.");
        } else if(score >= 60) {
            System.out.println("출력 : 학점은 D입니다.");
        } else {
            System.out.println("출력 : 학점은 F입니다.");
        }
    }
}
```

2. 문제 : 거리에 따른 운송 수단 선택하기
   - 주어진 거리에 따라 가장 적합한 운송 수단을 선택하는 프로그램을 작성
   - 다음과 같은 기준을 따름
     + 거리가 1km 이하이면 : "도보"
     + 거리가 10km 이하이면 : "자전거"
     + 거리가 100km 이하이면 : "자동차"
     + 거리가 100km 초과이면 : "비행기"
   - 거리는 변수(int distance)로 지정하고, 해당 변수를 기반으로 운송 수단을 출력
   - 출력 예시
```
distance: 1
출력 : 도보를 이용하세요.

distance: 5
출력 : 자전거를 이용하세요.

distance: 25
출력 : 자동차를 이용하세요.

distance: 150
출력 : 비행기를 이용하세요.
```

   - DistanceEx
```java
package cond.ex;

public class DistanceEx {
    public static void main(String[] args) {
        int distance = 25;

        if(distance <= 1) {
            System.out.println("도보를 이용하세요.");
        } else if(distance <= 10) {
            System.out.println("자전거를 이용하세요.");
        } else if(distance <= 100) {
            System.out.println("자돋차를 이용하세요.");
        } else {
            System.out.println("비행기를 이용하세요.");
        }
    }
}
```

3. 문제 : 환율 계산하기
   - 특정 금액을 미국 달러에서 한국 원으로 변환하는 프로그램을 작성
   - 환율은 1달러당 1300원이라고 가정
   - 다음과 같은 기준을 따름
     + 달러가 0미만이면 : "잘못된 금액입니다."
     + 달러가 0일 때 : "환전할 금액이 없습니다."
     + 달러가 0 초과일 때 : "환전 금액은 (계산된 원화 금액)원입니다."
       
   - 금액은 변수(int dollar)로 지정하고, 해당 변수를 기반으로 한국 원으로의 환전 금액을 출력
   - 출력 예시
```
dollar: -5
출력: 잘못된 금액입니다.

dollar: 0
출력: 환전할 금액이 없습니다.

dollar: 10
출력: 환전 금액은 13000원입니다.
```
   - ExchangeRateEx
```java
package cond.ex;

public class ExchangeEx {
    public static void main(String[] args) {
        int dollar = 10;

        if(dollar < 0) {
            System.out.println("잘못된 금액입니다.");
        } else if (dollar == 0) {
            System.out.println("환전할 금액이 없습니다.");
        } else {
            int won = dollar * 1300;
            System.out.println("환전 금액은 " + won + "원 입니다.");
        }
    }
}
```
