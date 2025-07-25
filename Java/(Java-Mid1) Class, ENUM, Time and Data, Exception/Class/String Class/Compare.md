-----
### String 클래스 - 비교
-----
1. String 클래스 비교할 때는 == 비교가 아니라 항상 equals() 비교를 해야함
   - 동일성(Identity) : == 연산자를 사용해서 두 객체의 참조가 동일한 객체를 가리키고 있는지 확인
   - 동등성(Equality) : equals() 메서드를 사용하여 두 객체가 논리적으로 같은지 확인

2. StringEqualsMain1 (/lang/string/equals)
```java
package lang.string.equals;

public class StringEqualsMain1 {
    public static void main(String[] args) {
        String str1 = new String("hello");
        String str2 = new String("hello");

        System.out.println("new String() == 비교 : " + (str1 == str2));
        System.out.println("new String() equals 비교 : " + (str1.equals(str2)));
        
        String str3 = "hello";
        String str4 = "hello";

        System.out.println("리터럴 == 비교 : " + (str3 == str4));
        System.out.println("리터럴 equals() 비교 : " + str3.equals(str4));
    }
}
```
   - 실행 결과
```
new String() == 비교 : false
new String() equals 비교 : true
리터럴 == 비교 : true
리터럴 equals() 비교 : true
```

<div align="center">
<img src="https://github.com/user-attachments/assets/fd711521-0125-45b5-8f22-fbf1ecd7217f">
</div>

   - str1과 str2는 new String()을 사용해서 각각 인스턴스를 생성
   - 서로 다른 인스턴스이므로 동일성(==) 비교에 실패
   - 둘은 내부에 같은 "hello" 값을 가지고 있기 때문에 논리적으로 같으므로, 동등성(equals()) 비교에 성공
   - 참고로 String 클래스는 내부 문자열 값을 비교하도록 equals() 메서드를 재정의 해둠

<div align="center">
<img src="https://github.com/user-attachments/assets/0c3507f7-e8e9-452d-8b6b-b374db44779f">
</div>

   - String str3 = "hello"와 같이 문자열 리터럴을 사용하는 경우 자바는 메모리 효율성과 성능 최적화를 위해 문자열 풀을 사용
   - 자바가 실행되는 시점에 클래스에 문자열 리터럴이 있으면 문자열 풀에 String 인스턴스를 미리 만들어둠
     + 이때 같은 문자열이 있으면 만들지 않음
     + String str3 = "hello"와 같이 문자열 리터럴을 사용하면 문자열 풀에서 "hello"라는 문자를 가진 String 인스턴스를 찾음
     + 그리고 찾은 인스턴스의 참조(x003)를 반환
     + String str4 = "hello"의 경우 "hello" 문자열 리터럴을 사용하므로 문자열 풀에서 str3과 같은 x003 참조를 사용
   - 문자열 풀 덕분에 같은 문자를 사용하는 경우 메모리 사용을 줄이고 문자를 만드는 시간도 줄어들기 때문에 성능도 최적화 할 수 있음
   - 따라서 문자열 리터럴을 사용하는 경우 같은 참조값을 가지므로 == 비교에 성공

3. 참고 : 풀(Pool)은 자원이 모여있는 곳을 의미
    - 프로그래밍에서 풀(Pool)은 공용 자원을 모아둔 곳  
    - 여러 곳에서 함께 사용할 수 있는 객체를 필요할 때 마다 생성하고, 제거하는 것은 비효율적
    - 대신에 이렇게 문자열 풀에 필요한 String 인스턴스를 미리 만들어두고 여러곳에서 재사용할 수 있다면 성능과 메모리를 더 최적화 할 수 있음
    - 참고로 문자열 풀은 힙 영역을 사용
    - 그리고 문자열 풀에서 문자를 찾을 때는 해시 알고리즘을 사용하기 때문에 매우 빠른 속도로 원하는 String 인스턴스를 찾을 수 있음

4. StringEqualsMain2
```java
package lang.string.equals;

public class StringEqualsMain2 {
    public static void main(String[] args) {
        String str1 = new String("hello");
        String str2 = new String("hello");

        System.out.println("메서드 호출 비교 1 : " + isSame(str1, str2));

        String str3 = "hello";
        String str4 = "hello";

        System.out.println("메서드 호출 비교 2 : " + isSame(str3, str4));
    }

    private static boolean isSame(String x, String y) {
        return x == y;
        // return x.equals(y);
    }
}
```
   - 실행 결과
```
메서드 호출 비교 1 : false
메서드 호출 비교 2 : true
```
   - main() 메서드를 만드는 개발자와 isSame() 메서드를 만드는 개발자가 서로 다르다고 가정
   - isSame() 의 경우 매개변수로 넘어오는 String 인스턴스가 new String() 으로 만들어진 것인지, 문자열 리터럴로 만들어 진 것인지 확인할 수 있는 방법이 없음
   - 따라서 문자열 비교는 항상 equals() 를 사용해서 동등성 비교를 해야함
