-----
### String 클래스 - 불변 객체
-----
1. String은 불변 객체
2. 따라서 생성 이후에 절대로 내부의 문자열 값을 변경할 수 없음
3. StringImmutable1
```java
package lang.string.equals;

public class StringImmutable1 {
    public static void main(String[] args) {
        String str = "hello";
        str.concat(" java");
        System.out.println("str = " + str);
    }
}
```
   - String.concat() 메서드를 사용하면 기존 문자열에 새로운 문자열을 연결해서 합칠 수 있음
   - 실행 결과
```
str = hello
```

4. StringImmutable2
```java
package lang.string.equals;

public class StringImmutable2 {
    public static void main(String[] args) {
        String str1 = "hello";
        String str2 = str1.concat(" java");
        System.out.println("str1 = " + str1);
        System.out.println("str2 = " + str2);
    }
}
```
   - String은 불변 객체
   - 따라서 변경이 필요한 경우 기존 값을 변경하지 않고, 대신에 새로운 결과를 만들어서 반환
   - 실행 결과
```
str1 = hello
str2 = hello java
```

<div align="center">
<img src="https://github.com/user-attachments/assets/390f04ee-9f12-4fe2-83cf-84f46dd5633d">
</div>

   - String.concat()은 내부에서 새로운 String 객체를 만들어서 반환
   - 따라서 불변과 기존 객체의 값을 유지

5. String이 불변으로 설계된 이유
  - 문자열 풀에 있는 String 인스턴스의 값이 중간에 변경되면 같은 문자열을 참고하는 다른 변수의 값도 함께 변경
<div align="center">
<img src="https://github.com/user-attachments/assets/2f0ec0cc-652a-4d98-b5a9-2aa7d5651d34">
</div>

   - String은 자바 내부에서 문자열 풀을 통해 최적화
   - 만약 String 내부의 값을 변경할 수 있다면, 기존에 문자열 풀에서 같은 문자를 참조하는 변수의 모든 문자가 함께 변경되어 버리는 문제가 발생
   - 다음의 경우 str3이 참조하는 문자를 변경하면 str4의 문자도 함께 변경되는 사이드 이펙트 문제가 발생
```java
String str3 = "hello"
String str4 = "hello"
```

   - String 클래스는 불변으로 설계되어서 이런 사이드 이펙트 문제가 발생하지 않음
