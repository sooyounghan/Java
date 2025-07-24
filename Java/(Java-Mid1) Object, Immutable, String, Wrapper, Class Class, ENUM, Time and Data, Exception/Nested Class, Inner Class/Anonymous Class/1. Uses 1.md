-----
### 익명 클래스 활용 1
-----
1. 리팩토링 전 - Ex0Main (/nested/anonymous/ex)
```java
package nested.anonymous.ex;

public class Ex0Main {
    public static void helloJava() {
        System.out.println("프로그램 시작");
        System.out.println("Hello Java");
        System.out.println("프로그램 종료");
    }
    
    public static void helloSpring() {
        System.out.println("프로그램 시작");
        System.out.println("Hello Spring");
        System.out.println("프로그램 종료");
    }

    public static void main(String[] args) {
        helloJava();
        helloSpring();
    }
}
```
  - 실행 결과
```
프로그램 시작
Hello Java
프로그램 종료

프로그램 시작
Hello Spring
프로그램 종료
```
   - 이 코드의 중복이 보일 것 : 코드를 리팩토링해서 코드의 중복을 제거
   - helloJava(), helloSpring() 메서드를 하나로 통합

2. 리팩토링 후 - Ex0RefMain
```java
package nested.anonymous.ex;

public class Ex0RefMain {
    public static void hello(String str) {
        System.out.println("프로그램 시작");
        System.out.println(str);
        System.out.println("프로그램 종료");
    }

    public static void main(String[] args) {
        hello("hello Java");
        hello("hello Spring");
    }
}
```
  - 실행 결과
```
프로그램 시작
Hello Java
프로그램 종료

프로그램 시작
Hello Spring
프로그램 종료
```
   - 기존 코드에서 변하는 부분과 변하지 않는 부분을 분리
```java
public static void helloJava() {
   System.out.println("프로그램 시작"); // 변하지 않는 부분
   System.out.println("Hello Java"); // 변하는 부분
   System.out.println("프로그램 종료"); // 변하지 않는 부분
}
public static void helloSpring() {
   System.out.println("프로그램 시작"); // 변하지 않는 부분
   System.out.println("Hello Spring"); // 변하는 부분
   System.out.println("프로그램 종료"); // 변하지 않는 부분
}
```
  - 핵심은 변하는 부분과 변하지 않는 부분을 분리하는 것
  - 변하는 부분은 그대로 유지하고 변하지 않는 부분을 어떻게 해결할 것 인가에 집중하면
```java
public static void hello() {
     System.out.println("프로그램 시작"); // 변하지 않는 부분
    
     // 변하는 부분 시작
     System.out.println("Hello Java");
     System.out.println("Hello Spring");
     // 변하는 부분 종료
    
     System.out.println("프로그램 종료"); // 변하지 않는 부분
}
```
   - 여기서 "Hello Java", "Hello Spring"와 같은 문자열은 상황에 따라서 변함
   - 여기서는 상황에 따라 변하는 문자열 데이터를 다음과 같이 외부에서 전달 받아서 출력하면 됨
```java
public static void hello(String str) {
   System.out.println("프로그램 시작"); // 변하지 않는 부분
   System.out.println(str); // str : 변하는 부분
   System.out.println("프로그램 종료"); // 변하지 않는 부분
}
```
   - 변하지 않는 부분은 그대로 유지하고, 변하는 문자열은 외부에서 전달 받아서 처리
   - 단순한 문제였지만 프로그래밍에서 중복을 제거하고, 좋은 코드를 유지하는 핵심은 변하는 부분과 변하지 않는 부분을 분리하는 것
   - 여기서는 변하지 않는 "프로그램 시작", "프로그램 종료"를 출력하는 부분은 그대로 유지하고, 상황에 따라 변화가 필요한 문자열은 외부에서 전달 받아서 처리
   - 이렇게 변하는 부분과 변하지 않는 부분을 분리하고, 변하는 부분을 외부에서 전달 받으면, 메서드(함수)의 재사용성을 높일 수 있음
   - 리팩토링 전과 후를 비교 : hello(String str) 함수의 재사용성은 매우 높아짐
   - 여기서 핵심은 변하는 부분을 메서드(함수) 내부에서 가지고 있는 것이 아니라, 외부에서 전달 받는다는 점
