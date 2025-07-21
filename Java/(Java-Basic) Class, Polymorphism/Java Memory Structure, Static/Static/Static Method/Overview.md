-----
### static 메서드 1
-----
1. 특정 문자열을 꾸며주는 간단한 기능을 구현
   - 예를 들어서 "hello" 라는 문자열 앞 뒤에 *을 붙여서 "```*hello*```"와 같이 꾸며주는 기능

2. 인스턴스 메서드 : DecoUtil1 (/static2)
```java
package static2;

public class DecoUtil1 {
    public String deco(String str) {
        String result = "*" + str + "*";
        return result;
    }
}
```
  - deco()는 문자열을 꾸미는 기능을 제공
  - 문자열이 들어오면 앞 뒤에 *을 붙여서 반환

  - DecoMain1
```java
package static2;

public class DecoMain1 {
    public static void main(String[] args) {
        String s = "hello java";
        DecoUtil1 utils = new DecoUtil1();
        String deco = utils.deco(s);

        System.out.println("before = " + s);
        System.out.println("after = " + deco);
    }
}
```
  - 실행 결과
```
before = hello java
after = *hello java*
```
  - 앞서 개발한 deco() 메서드를 호출하기 위해서는 DecoUtil1 의 인스턴스를 먼저 생성
  - 그런데 deco()라는 기능은 멤버 변수도 없고, 단순히 기능만 제공할 뿐임
  - 인스턴스가 필요한 이유는 멤버 변수(인스턴스 변수)등을 사용하는 목적이 큰데, 이 메서드는 사용하는 인스턴스 변수도 없고 단순히 기능만 제공

3. static 메서드
   - DecoMain2
```java
package static2;

public class DecoUtil2 {
    public static String deco(String str) {
        String result = "*" + str + "*";
        return result;
    }
}
```
  - DecoMain2
```java
package static2;

public class DecoMain2 {
    public static void main(String[] args) {
        String s = "hello java";
        String deco = DecoUtil2.deco(s);

        System.out.println("before = " + s);
        System.out.println("after = " + deco);
    }
}
```
  - 실행 결과
```
before = hello java
after = *hello java*
```
  - static이 붙은 정적 메서드는 객체 생성 없이 클래스명 + .(dot) + 메서드 명으로 바로 호출할 수 있음
  - 정적 메서드 덕분에 불필요한 객체 생성 없이 편리하게 메서드를 사용
  
4. 클래스 메서드
   - 메서드 앞에도 static을 붙일 수 있음
   - 이를 정적 메서드 또는 클래스 메서드라고 함
   - 정적 메서드라는 용어는 static 이 정적이라는 뜻이기 때문이고, 클래스 메서드라는 용어는 인스턴스 생성 없이 마치 클래스에 있는 메서드를 바로 호출하는 것 처럼 느껴지기 때문임

5. 인스턴스 메서드
   - static이 붙지 않은 메서드는 인스턴스를 생성해야 호출할 수 있음
   - 이를 인스턴스 메서드라 함
