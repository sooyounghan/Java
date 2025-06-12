-----
### break, continue
-----
1. break와 continue는 반복문에서 사용할 수 있는 키워드
2. break는 반복문을 즉시 종료하고 나감
3. continue는 반복문의 나머지 부분을 건너뛰고 다음 반복으로 진행하는데 사용
4. 참고로 while, do - while, for와 같은 모든 반복문에서 사용할 수 있음
5. break
```java
while(조건식) {
   코드1;
   break; // 즉시 while문 종료로 이동
   코드2;
}
// while문 종료
```
  - break 를 만나면 코드2 가 실행되지 않고 while문이 종료

6. continue
```java
while(조건식) {
     코드1;
     continue; // 즉시 조건식으로 이동
     코드2;
}
```
   - continue를 만나면 코드2 가 실행되지 않고 다시 조건식으로 이동
   - 조건식이 참이면 while문을 실행

7. 문제 : 1부터 시작해서 숫자를 계속 누적해서 더하다가 합계가 10보다 처음으로 큰 값
   - 1 + 2 + 3 ... 계속 더하다가 처음으로 합이 10보다 큰 경우를 찾으면 됨
   - Break1
```java
package loop;

public class Break1 {
    public static void main(String[] args) {
        int sum = 0;
        int i = 1;

        while(true) {
            sum += i;

            if (sum > 10) {
                System.out.println("합이 10보다 크면 종료 : i = " + sum);
                break;
            }

            i++;
        }
    }
}
```
  - 조건식을 잘 보면 true라고 설정 : 조건이 항상 참이기 때문에 이렇게 두면 while문은 무한 반복
  - 물론 break문이 있기 때문에 중간에 빠져나올 수 있음
  - 만약 sum > 10 조건을 만족하면 결과를 출력하고, break를 사용해서 while 문을 빠져나감
  - 실행 결과
```
합이 10보다 크면 종료 : i = 15
```

8. 문제 : 1부터 5까지 숫자를 출력하는데, 숫자가 3일 때는 출력을 건너뛰기
   - Continue1
```java
package loop;

public class Continue1 {
    public static void main(String[] args) {
        int i = 1;

        while(i <= 5) {
            if(i == 3) {
                i++;
                continue;
            }

            System.out.println(i);
            i++;
        }
    }
}
```
  - i==3 인 경우 i를 하나 증가하고 continue를 실행
  - 따라서 이 경우에는 i를 출력하지 않고 바로 while (i<= 5) 조건식으로 이동
  - 실행 결과
```
1
2
4
5
```
   - 3일 때는 출력하지 않은 것을 확인
