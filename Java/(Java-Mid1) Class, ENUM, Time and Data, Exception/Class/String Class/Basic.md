-----
### String 클래스 - 기본
-----
1. 자바에서 문자를 다루는 대표적인 타입은 char, String 2가지가 존재
2. CharArrayMain (/lang/string)
```java
package lang.string;

public class CharArrayMain {
    public static void main(String[] args) {
        char[] charArr = new char[]{'h', 'e', 'l', 'l', 'o'};
        System.out.println(charArr);
        
        String str = "hello";
        System.out.println("str = " + str);
    }
}
```
   - 실행 결과
```
hello
str = hello
```
   - 기본형인 char는 문자 하나를 다룰 때 사용
     + char를 사용해서 여러 문자를 나열하려면 char[] 을 사용해야 함
   - 하지만 이렇게 char[]을 직접 다루는 방법은 매우 불편하기 때문에 자바는 문자열을 매우 편리하게 다룰 수 있는 String 클래스를 제공

3. String 클래스를 통해 문자열을 생성하는 방법 2가지
   - StringBasicMain
```java
package lang.string;

public class StringBasicMain {
    public static void main(String[] args) {
        String str1 = "hello";
        String str2 = new String("hello");

        System.out.println("str1 = " + str1);
        System.out.println("str2 = " + str2);
    }
}
```
   - 쌍따옴표 사용 : "hello"
   - 객체 생성 : new String("hello");

4. String은 클래스로서, int, boolean 같은 기본형이 아니라 참조형
   - 따라서 str1 변수에는 String 인스턴스의 참조값만 들어갈 수 있음
```java
String str1 = "hello";
```
   - 문자열은 매우 자주 사용되므로, 그래서 편의상 쌍따옴표로 문자열을 감싸면 자바 언어에서 new String("hello")와 같이 변경해줌
   - 이 경우 실제로는 성능 최적화를 위해 문자열 풀을 사용
```java
String str1 = "hello"; // 기존
String str1 = new String("hello"); // 변경
```

5. String 클래스 구조
```java
public final class String {
     // 문자열 보관
     private final char[] value; // 자바 9 이전
     private final byte[] value; // 자바 9 이후

     // 여러 메서드
     public String concat(String str) {...}
     public int length() {...}
     ...
}
```
   - 클래스이므로 속성과 기능을 가짐
   - 속성(필드)
```java
private final char[] value;
```
   - 여기에는 String의 실제 문자열 값이 보관
   - 문자 데이터 자체는 char[]에 보관
   - String 클래스는 개발자가 직접 다루기 불편한 char[]을 내부에 감추고 String 클래스를 사용하는 개발자가 편리하게 문자열을 다룰 수 있도록 다양한 기능을 제공
   - 그리고 메서드 제공을 넘어서 자바 언어 차원에서도 여러 편의 문법을 제공

6. 참고 : 자바 9 이후 String 클래스 변경 사항
   - 자바 9부터 String 클래스에서 char[] 대신에 byte[]을 사용
```java
private final byte[] value;
```
   - 자바에서 문자 하나를 표현하는 char는 2byte를 차지
   - 그런데 영어, 숫자는 보통 1byte로 표현이 가능
   - 그래서 단순 영어, 숫자로만 표현된 경우 1byte 를 사용하고(정확히는 Latin-1 인코딩의 경우 1byte 사용), 그렇지 않은 나머지의 경우 2byte인 UTF-16 인코딩을 사용
   - 따라서 메모리를 더 효율적으로 사용할 수 있게 변경됨

7. 기능 (메서드)
  - String 클래스는 문자열로 처리할 수 있는 다양한 기능을 제공
  - 기능이 방대하므로 필요한 기능이 있으면 검색하거나 API 문서를 찾아볼 것
  - 주요 메서드는 다음과 같음
     + length() : 문자열의 길이를 반환
     + charAt(int index) : 특정 인덱스의 문자를 반환
     + substring(int beginIndex, int endIndex) : 문자열의 부분 문자열을 반환
     + indexOf(String str) : 특정 문자열이 시작되는 인덱스를 반환
     + toLowerCase() , toUpperCase() : 문자열을 소문자 또는 대문자로 변환
     + trim() : 문자열 양 끝의 공백을 제거
     + concat(String str) : 문자열을 더함

8. String 클래스와 참조형
   - String은 클래스 : 기본형이 아니라 참조형
   - 참조형은 변수에 계산할 수 있는 값이 들어있는 것이 아니라 x001 과 같이 계산할 수 없는 참조값이 들어있음
   - 따라서 원칙적으로 + 같은 연산을 사용할 수 없음
   - StringConcatMain
```java
package lang.string;

public class StringConcatMain {
    public static void main(String[] args) {
        String a = "hello";
        String b = " java";
        
        String result1 = a.concat(b);
        String result2 = a + b;
        System.out.println("result1 = " + result1);
        System.out.println("result2 = " + result2);
    }
}
```
   - 자바에서 문자열을 더할 때는 String 이 제공하는 concat() 과 같은 메서드를 사용해야 함
   - 하지만 문자열은 너무 자주 다루어지기 때문에 자바 언어에서 편의상 특별히 + 연산을 제공

   - 실행 결과
```
result1 = hello java
result2 = hello java
```

