-----
### 삼항 연산자
-----
1. if 문을 사용할 때 다음과 같이 단순히 참과 거짓에 따라 특정 값을 구하는 경우 존재
   - CondOp1
```java
package cond;

public class CondOp1 {
    public static void main(String[] args) {
        int age = 18;

        String status;

        if(age >= 18) {
            status = "성인";
        } else {
            status = "미성년자";
        }

        System.out.println("age = " + age + ", status = " + status);
    }
}
```
   - 실행 결과 (age = 18)
```
age = 18, status = 성인
```
   - 실행 결과 (age = 17)
```
age = 17, status = 미성년자
```
   - 참과 거짓에 따라 status 변수의 값이 달라짐

2. 단순히 참과 거짓에 따라서 특정 값을 구하는 경우. 삼항 연산자 또는 조건 연산자라고 불리는 ?: 연산자를 사용 가능
    - 이 연산자를 사용하면 if문과 비교해서 코드를 단순화 할 수 있음
    - CondOp2
```java
package cond;

public class CondOp2 {
    public static void main(String[] args) {
        int age = 18;

        String status = (age >= 18) ? "성인" : "미성년자";

        System.out.println("age = " + age + ", status = " + status);
    }
}
```
   - 실행 결과 분석
```java
String status = (age >= 18) ? "성인" : "미성년자"; // age=18
String status = (true) ? "성인" : "미성년자"; // 조건이 참이므로 참 표현식 부분이 선택된다.
String status = "성인"; // 결과
```

3. 삼항 연산자
```java
(조건) ? 참_표현식 : 거짓_표현식
```
   - 삼항 연산자는 항이 3개라는 뜻
   - 조건, 참_표현식, 거짓_표현식 이렇게 항이 3개
   - 자바에서 유일하게 항이 3개인 연산자여서 삼항 연산자라고 함
   - 또는 특정 조건에 따라 결과가 나오기 때문에 조건 연산자라고도 함
   - 조건에 만족하면 참_표현식이 실행되고, 조건에 만족하지 않으면 거짓_표현식이 실행 (앞의 if, else 문과 유사)
   - if문 처럼 코드 블럭을 넣을 수 있는 것이 아니라 단순한 표현식만 넣을 수 있음
   - 삼항 연산자 없이 if문만 사용해도 되지만, 단순히 참과 거짓에 따라서 특정 값을 구하는 삼항 연산자를 사용하면 if문 보다 간결한 코드를 작성할 수 있음
