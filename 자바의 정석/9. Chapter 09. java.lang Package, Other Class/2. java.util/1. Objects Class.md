-----
### java.util.Objects 클래스
-----
1. Object의 보조 클래스로, 모든 메서드가 static 메서드
2. 객체의 비교나 널(null) 체크에 유용
3. isNull() : 해당 객체가 널인지 확인해서 null이면 true, null이 아니면 false
4. nonNull() : isNull()과 정반대의 기능 수행 (=!Objects.isNull(obj))
```java
static boolean isNull(Object obj)
static boolean nonNull(Object obj)
```

 5. requireNonNull() : 해당 객체가 널이 아니어야 하는 경우에 사용
    - 만일 객체가 null : NullPointerException 발생
    - 두 번재 매개변수로 지정하는 문자열은 예외의 메세지
```java
static <T> T requireNonNull(T obj)
static <T> T requireNonNull(T obj, String message)
static <T> T requireNonNull(T obj, Supplier<String> messageSupplier)
```

   - 과거의 경우에는 매개변수 유효성 검사를 해야했으나, 이를 호출하여 간단하게 해결 가능
```java
void setName(String name) {
    if(name == null)
      throw new NullPointerException("name must not be null");

    this.name = name;
}
```

```java
void setName(String name) {
    this.name = Objects.requireNonNull(name, "name must not be null.");
}
```

6. Object 클래스에는 두 객체의 등가 비교를 위한 equals()가 존재하고, compare()는 없음
7. 하지만, Objects 클래스에는 compare()가 추가 : compare()는 두 비교 대상이 같으면 0, 크면 양수, 작으면 음수 반환
```java
static int compare(Object a, Object b, Comparator c)
```

  - 이 메서드는 a와 b 두 객체를 비교하는데, 두 객체를 비교하는데 사용할 기준이 필요하는데 이를 Comparator로 해결

8. Object 클래스와 마찬가지로 equals() 존재하는데, Objects 클래스의 equals()는 null 검사를 하지 않아도 됨
```java
static boolean equals(Object a, Object b)
static boolean deepEquals(Object a, Object b)
```

```java
if(a != null && a.equals(b)) { // a가 null 인지 반드시 확인
    ...
}
```
```java
if(Objects.equals(a, b)) { // 매개변수의 값이 null인지 확인할 필요가 업음
    ...
}
```
  - 즉, equals() 내부에 a와 b의 null 확인을 하므로 따로 이를 위한 조건식을 넣지 않아도 되며, 실제 코드는 다음과 같음
```java
public static boolean equals(Object a, Object b) {
      return (a == b) || (a != b && a.equals(b));
}
```

  - deepEquals()는 객체를 재귀적으로 비교하므로, 다차원 배열에서도 비교 가능
```java
String[][] str2D = new String[][]{{"aaa", "bbb"}, {"AAA", "BBB"}};
String[][] str2D2 = new String[][]{{"aaa", "bbb"}, {"AAA", "BBB"}};

System.out.println(Objects.equals(str2D, str2D)); // false
System.out.println(Objects.deepEquals(str2D, str2D)); // true
```

9. toString()도 equals()처럼, 내부적으로 널 검사를 함
```java
static String toString(Object o)
static String toString(Object o, String nullDefault)
```
  - 두 번째 메서드는 o가 null이면, 대신 사용할 값을 지정할 수 있어 유용

10. hashCode() : 내부적으로 null 검사 후 Object 클래스의 hashCode() 호출하며, null이면 0 반환
```java
static int hashCode(Object o)
static int hash(Object... values)
```
  - 보통은 클래스에 선언된 인스턴스 변수들의 hashCode()를 조합해서 반환하도록 hashCode() 오버라이딩

```java
import java.util.*;
import static java.util.Objects.*;  // Objects클래스 메서드를 static import

class ObjectsTest {
	public static void main(String[] args) {
		String[][] str2D   = new String[][]{{"aaa","bbb"},{"AAA","BBB"}};
		String[][] str2D_2 = new String[][]{{"aaa","bbb"},{"AAA","BBB"}};

		System.out.print("str2D  ={");
		for(String[] tmp : str2D) 
			System.out.print(Arrays.toString(tmp));
		System.out.println("}");

		System.out.print("str2D_2={");
		for(String[] tmp : str2D_2) 
			System.out.print(Arrays.toString(tmp));
		System.out.println("}");

		System.out.println("equals(str2D, str2D_2)="+Objects.equals(str2D, str2D_2));
		System.out.println("deepEquals(str2D, str2D_2)="+Objects.deepEquals(str2D, str2D_2));

		System.out.println("isNull(null) ="+isNull(null));
		System.out.println("nonNull(null)="+nonNull(null));
		System.out.println("hashCode(null)="+Objects.hashCode(null));
		System.out.println("toString(null)="+Objects.toString(null));
		System.out.println("toString(null, \"\")="+Objects.toString(null, ""));
	
		Comparator c = String.CASE_INSENSITIVE_ORDER;

	     System.out.println("compare(\"aa\",\"bb\")="+compare("aa","bb",c));
	     System.out.println("compare(\"bb\",\"aa\")="+compare("bb","aa",c));
	     System.out.println("compare(\"ab\",\"AB\")="+compare("ab","AB",c));
	}
}
```
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/1743dd0e-638d-4d35-9a97-64b6f6ab0212">
</div>

  - static import문을 사용했음에도 불구하고 Object 클래스와 메서드 이름이 같은 것은 충돌 발생
  - 즉, 컴파일러가 구별하지 못하므로, 클래스 이름을 붙여줘야 함
