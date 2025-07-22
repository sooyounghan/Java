-----
### String 최적화
-----
1. 자바의 String 최적화
   - 자바 컴파일러는 다음과 같이 문자열 리터럴을 더하는 부분을 자동으로 합쳐줌
   - 문자열 리터럴 최적화 - 컴파일 전
```java
String helloWorld = "Hello, " + "World!";
```
   - 문자열 리터럴 최적화 - 컴파일 후
```java
String helloWorld = "Hello, World!";
```
   - 따라서 런타임에 별도의 문자열 결합 연산을 수행하지 않기 때문에 성능이 향상

2. String 변수 최적화
  - 문자열 변수의 경우 그 안에 어떤 값이 들어있는지 컴파일 시점에는 알 수 없기 때문에 단순하게 합칠 수 없음
```java
String result = str1 + str2;
```
  - 이런 경우 예를 들면 다음과 같이 최적화를 수행 (최적화 방식은 자바 버전에 따라 달라짐)
```java
String result = new StringBuilder().append(str1).append(str2).toString();
```
  - 참고 : 자바 9부터는 StringConcatFactory 를 사용해서 최적화를 수행
  - 이렇듯 자바가 최적화를 처리해주기 때문에 지금처럼 간단한 경우에는 StringBuilder 를 사용하지 않아도 됨
  - 대신에 문자열 더하기 연산( + )을 사용하면 충분

3. String 최적화가 어려운 경우 : 문자열을 루프안에서 문자열을 더하는 경우는 최적화가 이루어지지 않음
   - LoopStringMain
```java
package lang.string.builder;

public class LoopStringMain {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        
        String result = "";
        
        for(int i = 0; i < 100000; i++) {
            result += "hello Java";
        }
        
        long endTime = System.currentTimeMillis();

        System.out.println("result = " + result);
        System.out.println("time = " + (endTime - startTime) + " ms");
    }
}
```
  - 대략 다음과 같이 최적화 발생 (최적화는 자바 버전에 따라 다름)
```java
String result = "";

for (int i = 0; i < 100000; i++) {
   result = new StringBuilder().append(result).append("Hello Java").toString();
}
```
   - 반복문의 루프 내부에서는 최적화가 되는 것 처럼 보이지만, 반복 횟수만큼 객체를 생성해야 함
   - 반복문 내에서의 문자열 연결은, 런타임에 연결할 문자열의 개수와 내용이 결정
   - 이런 경우, 컴파일러는 얼마나 많은 반복이 일어날지, 각 반복에서 문자열이 어떻게 변할지 예측할 수 없으므로, 따라서, 이런 상황에서는 최적화가 어려움
   - StringBuilder는 물론이고, 아마도 대략 반복 횟수인 100,000번의 String 객체를 생성했을 것
   - 실행 결과
```
result = Hello Java Hello Java ....
time = 6985 ms
```
  - 1000ms = 1초

   - 이럴 때는 직접 StringBuilder를 사용
```java
package lang.string.builder;

public class LoopStringBuilderMain {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < 100000; i++) {
            sb.append("Hello Java ");
        }

        String result = sb.toString();
        long endTime = System.currentTimeMillis();

        System.out.println("result = " + result);
        System.out.println("time = " + (endTime - startTime) + " ms");
    }
}
```
  - 실행 결과
```
result = Hello Java Hello Java ....
time = 10 ms
```

4. 정리
   - 문자열을 합칠 때 대부분의 경우 최적화가 되므로 + 연산을 사용
   - StringBuilder를 직접 사용하는 것이 더 좋은 경우
      + 반복문에서 반복해서 문자를 연결할 때
      + 조건문을 통해 동적으로 문자열을 조합할 때
      + 복잡한 문자열의 특정 부분을 변경해야 할 때
      + 매우 긴 대용량 문자열을 다룰 때

5. 참고 : StringBuilder vs StringBuffer
   - StringBuilder와 똑같은 기능을 수행하는 StringBuffer 클래스도 존재
   - StringBuffer는 내부에 동기화가 되어 있어서, 멀티 스레드 상황에 안전하지만 동기화 오버헤드로 인해 성능이 느림
   - StringBuilder는 멀티 쓰레드 상황에 안전하지 않지만 동기화 오버헤드가 없으므로 속도가 빠름
