-----
### StringBuilder - 가변 String
-----
1. 불변인 String 클래스의 단점
  - 두 문자를 더하는 경우 다음과 같이 작동
```java
"A" + "B"

String("A") + String("B") // 문자는 String 타입
String("A").concat(String("B"))// 문자의 더하기는 concat을 사용
new String("AB") // String은 불변이므로, 따라서 새로운 객체가 생성
```

2. 불변인 String의 내부 값은 변경할 수 없음
   - 따라서 변경된 값을 기반으로 새로운 String 객체를 생성
   - 더 많은 문자를 더하는 경우
```java
String str = "A" + "B" + "C" + "D";
String str = String("A") + String("B") + String("C") + String("D");
String str = new String("AB") + String("C") + String("D");
String str = new String("ABC") + String("D");
String str = new String("ABCD");
```
   - 이 경우 총 3개의 String 클래스가 추가로 생성
   - 그런데 문제는 중간에 만들어진 new String("AB") , new String("ABC")는 사용되지 않음
   - 최종적으로 만들어진 new String("ABCD")만 사용
   - 결과적으로 중간에 만들어진 new String("AB"), new String("ABC")는 제대로 사용되지도 않고, 이후 GC의 대상이 됨

3. 불변인 String 클래스의 단점은 문자를 더하거나 변경할 때 마다 계속해서 새로운 객체를 생성해야 한다는 점
   - 문자를 자주 더하거나 변경해야 하는 상황이라면 더 많은 String 객체를 만들고, GC해야 함
   - 결과적으로 컴퓨터의 CPU, 메모리 자원을 더 많이 사용하게 됨
   - 그리고 문자열의 크기가 클수록, 문자열을 더 자주 변경할수록 시스템의 자원을 더 많이 소모함

4. 참고 : 실제로는 문자열을 다룰 때 자바가 내부에서 최적화를 적용

5. StringBuilder
   - 바로 불변이 아닌 가변 String이 존재하면 됨
   - 가변은 내부의 값을 바로 변경하면 되기 때문에 새로운 객체를 생성할 필요가 없음
   - 따라서 성능과 메모리 사용면에서 불변보다 더 효율적
   - 이런 문제를 해결하기 위해 자바는 StringBuilder라는 가변 String 을 제공
   - 물론 가변의 경우 사이드 이펙트에 주의해서 사용해야 함
   - StringBuilder는 내부에 final이 아닌 변경할 수 있는 byte[]을 가지고 있음
```java
public final class StringBuilder {
     char[] value; // 자바 9 이전
     byte[] value; // 자바 9 이후

     // 여러 메서드
     public StringBuilder append(String str) {...}

      public int length() {...}
     ...
}
```
   - 실제로는 상속 관계에 있고 부모 클래스인 AbstractStringBuilder에 value 속성과 length() 메서드가 있음

6. StringBuilder 사용 예 - StringBuilderMain1_1 (/lang/string/builder)
```java
package lang.string.builder;

public class StringBuilderMain1_1 {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        
        sb.append("A");
        sb.append("B");
        sb.append("C");
        sb.append("D");

        System.out.println("sb = " + sb);
        
        sb.insert(4, "Java");
        System.out.println("insert = " + sb);
        
        sb.delete(4, 8);
        System.out.println("delete = " + sb);
        
        sb.reverse();
        System.out.println("reverse = " + sb);
        
        // StringBuilder -> String
        String string = sb.toString();
        System.out.println("string = " + string);
    }
}
```
   - StringBuilder 객체를 생성
   - append() 메서드를 사용해 여러 문자열을 추가
   - insert() 메서드로 특정 위치에 문자열을 삽입
   - delete () 메서드로 특정 범위의 문자열을 삭제
   - reverse() 메서드로 문자열을 뒤집음
   - 마지막으로 toString 메소드를 사용해 StringBuilder의 결과를 기반으로 String을 생성해서 반환
   - 실행 결과
```
sb = ABCD
insert = ABCDJava
delete = ABCD
reverse = DCBA
string = DCBA
```

7. 가변(Mutable) vs 불변(Immutable)
   - String은 불변하므로, 즉, 한 번 생성되면 그 내용을 변경할 수 없음
   - 따라서 문자열에 변화를 주려고 할 때마다 새로운 String 객체가 생성되고, 기존 객체는 버려짐
   - 이 과정에서 메모리와 처리 시간을 더 많이 소모함
   - 반면에, StringBuilder는 가변적이므로, 하나의 StringBuilder 객체 안에서 문자열을 추가, 삭제, 수정할 수 있으며, 이때마다 새로운 객체를 생성하지 않음 이로 인해 메모리 사용을 줄이고 성능을 향상시킬 수 있음 (단, 사이드 이펙트를 주의해야 함)
   - StringBuilder는 보통 문자열을 변경하는 동안만 사용하다가 문자열 변경이 끝나면 안전한(불변) String으로 변환하는 것이 좋음
