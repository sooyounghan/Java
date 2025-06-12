-----
### 반복문 시작
-----
1. 반복문은 이름 그대로 특정 코드를 반복해서 실행할 때 사용
2. 자바는 다음 3가지 종류의 반복문을 제공 : while , do - while , for
3. While1_1 (/loop)
```java
package loop;

public class While1_1 {
    public static void main(String[] args) {
        int count = 0;

        count = count + 1;
        System.out.println("현재 숫자는 : " + count);
        count = count + 1;
        System.out.println("현재 숫자는 : " + count);
        count = count + 1;
        System.out.println("현재 숫자는 : " + count);
    }
}
```
  - 출력
```
현재 숫자는 : 1
현재 숫자는 : 2
현재 숫자는 : 3
```
   - 단순히 count 에 값을 1씩 3번 더하는 단순한 예제 : 최종 결과는 3
   - count = count + 1 은 증감 연산자(++)를 사용해서 다음과 같이 개선할 수 있음
```java
// 개선
count++;
System.out.println("현재 숫자는 : " + count);

count++;
System.out.println("현재 숫자는 : " + count);

count++;
System.out.println("현재 숫자는 : " + count);
```
   - 하지만 같은 코드가 3번 반복되고 있음
   - 이렇게 특정 코드를 반복해서 실행할 때 사용하는 것이 바로 반복문
