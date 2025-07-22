-----
### 래퍼 클래스 - 오토 박싱
-----
1. 오토 박싱 - Autoboxing
   - AutoboxingMain1
```java
package lang.wrapper;

public class AutoboxingMain1 {
    public static void main(String[] args) {
        // Primitive -> Wrapper
        int value = 7;
        Integer boxedValue = Integer.valueOf(value);

        // Wrapper -> Primitive
        int unboxedValue = boxedValue.intValue();

        System.out.println("boxedValue = " + boxedValue);
        System.out.println("unboxedValue = " + unboxedValue);
    }
}
```
   - 실행 결과
```
boxedValue = 7
unboxedValue = 7
```

   - 박싱: valueOf()
   - 언박싱: xxxValue() (예: intValue() , doubleValue())

2. 기본형을 래퍼 클래스로 변환하거나 또는 래퍼 클래스를 기본형으로 변환하는 일이 자주 발생
   - 자바는 이런 문제를 해결하기 위해 자바 1.5부터 오토 박싱(Auto-boxing), 오토 언박싱(Auto-Unboxing)을 지원
   - AutoboxingMain2
```java
package lang.wrapper;

public class AutoboxingMain2 {
    public static void main(String[] args) {
        // Primitive -> Wrapper
        int value = 7;
        Integer boxedValue = value; // 오토 박싱 (Auto-Boxing)

        // Wrapper -> Primitive
        int unboxedValue = boxedValue; // 오토 언박싱 (Auto-Unboxing)

        System.out.println("boxedValue = " + boxedValue);
        System.out.println("unboxedValue = " + unboxedValue);
    }
}
```
   - 실행 결과
```
boxedValue = 7
unboxedValue = 7
```
   - 오토 박싱과 오토 언박싱은 컴파일러가 개발자 대신 valueOf, xxxValue()등의 코드를 추가해주는 기능
   - 덕분에 기본형과 래퍼형을 서로 편리하게 변환할 수 있음
    - 따라서 AutoboxingMain1 과 AutoboxingMain2는 동일하게 작동
```java
Integer boxedValue = value; // 오토 박싱(Auto-boxing)
Integer boxedValue = Integer.valueOf(value); // 컴파일 단계에서 추가

int unboxedValue = boxedValue; // 오토 언박싱(Auto-Unboxing)
int unboxedValue = boxedValue.intValue(); // 컴파일 단계에서 추가
```
