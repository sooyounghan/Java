-----
### 열거형 - 주요 메서드
-----
1. 모든 열거형은 java.lang.Enum 클래스를 자동으로 상속 받음 : 따라서 해당 클래스가 제공하는 기능들을 사용 가능
2. EnumMethodMain
```java
package enumeration.ex3;

import java.util.Arrays;

public class EnumMethodMain {
    public static void main(String[] args) {
        // 모든 ENUM 반환
        Grade[] values = Grade.values();

        System.out.println("values = " + Arrays.toString(values));

        for (Grade value : values) {
            System.out.println("name = " + value.name() + ", ordinal = " + value.ordinal());
        }
        
        // String -> ENUM 변환 : 잘못된 문자면 IllegalArgumentException 발생
        String input = "GOLD";
        Grade gold = Grade.valueOf(input);
        System.out.println("gold = " + gold); // toString() 오버라이딩 가능
    }
}
```
  - 실행 결과
```
name = BASIC, ordinal = 0
name = GOLD, ordinal = 1
name = DIAMOND, ordinal = 2
gold = GOLD
```
   - Arrays.toString() : 배열의 참조값이 아니라 배열 내부의 값을 출력할 때 사용

3. ENUM - 주요 메서드
   - values() : 모든 ENUM 상수를 포함하는 배열을 반환
   - valueOf(String name) : 주어진 이름과 일치하는 ENUM 상수를 반환
   - name() : ENUM 상수의 이름을 문자열로 반환
   - ordinal() : ENUM 상수의 선언 순서(0부터 시작)를 반환
   - toString() : ENUM 상수의 이름을 문자열로 반환
     + name() 메서드와 유사하지만, toString()은 직접 오버라이드 할 수 있음

4. 주의 : ordinal()은 가급적 사용하지 않는 것이 좋음
   - ordinal()의 값은 가급적 사용하지 않는 것이 좋음
      + 왜냐하면 이 값을 사용하다가 중간에 상수를 선언하는 위치가 변경되면 전체 상수의 위치가 모두 변경될 수 있기 때문임
   - 예를 들어 중간에 BASIC 다음에 SILVER 등급이 추가되는 경우 GOLD, DIAMOND 의 값이 하나씩 추가
      + 기존
        * BASIC : 0
        * GOLD : 1
        * DIAMOND : 2
      + 추가
        * BASIC : 0
        * SILVER : 1
        * GOLD : 2
        * DIAMOND : 3
   - 기존 GOLD의 ordinal() 값인 1을 데이터베이스나 파일에 저장하고 있었는데, 중간에 SILVER가 추가되면 데이터베이스나 파일에 있는 값은 그대로 1로 유지되지만, 애플리케이션 상에서 GOLD는 2가 되고, SILVER는 1이 됨
   - 쉽게 이야기해서 ordinal()의 값을 사용하면 기존 GOLD 회원이 갑자기 SILVER가 되는 큰 버그가 발생할 수 있음

5. 열거형 정리
   - 열거형은 java.lang.Enum를 자동(강제)으로 상속 받음
   - 열거형은 이미 java.lang.Enum을 상속 받았기 때문에 추가로 다른 클래스를 상속을 받을 수 없음
   - 열거형은 인터페이스를 구현할 수 있음
   - 열거형에 추상 메서드를 선언하고, 구현할 수 있음
   - 이 경우 익명 클래스와 같은 방식을 사용
