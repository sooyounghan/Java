-----
### 래퍼 클래스 - 자바 래퍼 클래스
-----
1. 지금까지 설명한 래퍼 클래스는 기본형을 객체로 감싸서 더 편리하게 사용하도록 도와주기 때문에 상당히 유용
2. 쉽게 이야기해서 래퍼 클래스는 기본형의 객체 버전
3. 자바는 기본형에 대응하는 래퍼 클래스를 기본으로 제공
   - byte → Byte
   - short → Short
   - int → Integer
   - long → Long
   - float → Float
   - double → Double
   - char → Character
   - boolean → Boolean

4. 자바가 제공하는 기본 래퍼 클래스는 다음과 같은 특징을 가짐
   - 불변
   - equals로 비교
  
5. WrapperClassMain
```java
package lang.wrapper;

public class WrapperClassMain {
    public static void main(String[] args) {
        Integer newInteger = new Integer(10); // 미래에 삭제 예정 (대신 valueOf() 사용)
        Integer integerObj = Integer.valueOf(10); // -128 ~ 127 자주 사용하는 값 재사용, 불변
        Long longObj = Long.valueOf(100);
        Double doubleObj = Double.valueOf(10.5);

        System.out.println("newInteger = " + newInteger);
        System.out.println("integerObj = " + integerObj);
        System.out.println("longObj = " + longObj);
        System.out.println("doubleObj = " + doubleObj);

        System.out.println("내부 값 읽기");
        int intValue = integerObj.intValue();
        System.out.println("intValue = " + intValue);

        long longValue = longObj.longValue();
        System.out.println("longValue = " + longValue);
        
        double doubleValue = doubleObj.doubleValue();
        System.out.println("doubleValue = " + doubleValue);

        System.out.println("비교");
        System.out.println("== : " + (newInteger == integerObj));
        System.out.println("eqauls : " + newInteger.equals(integerObj));
    }
}
```
   - 실행 결과
```
newInteger = 10
integerObj = 10
longObj = 100
doubleObj = 10.5

내부 값 읽기
intValue = 10
longValue = 100
doubleValue = 10.5

비교
== : false
eqauls : true
```

 6. 래퍼 클래스 생성 - 박싱(Boxing)
    - 기본형을 래퍼 클래스로 변경하는 것을 마치 박스에 물건을 넣은 것 같다고 해서 박싱(Boxing)
        + new Integer(10) 은 직접 사용하면 안 됨 : 작동은 하지만, 향후 자바에서 제거될 예정
        + 대신에 Integer.valueOf(10) 사용 : 내부에서 new Integer(10)을 사용해서 객체를 생성하고 돌려줌
  
    - 추가로 Integer.valueOf()에는 성능 최적화 기능 존재 : 개발자들이 일반적으로 자주 사용하는 -128 ~ 127 범위의 Integer 클래스를 미리 생성해줌
        + 해당 범위의 값을 조회하면 미리 생성된 Integer 객체를 반환
        + 해당 범위의 값이 없으면 new Integer()를 호출
        + 마치 문자열 풀과 비슷하게 자주 사용하는 숫자를 미리 생성해두고 재사용
        + 참고로 이런 최적화 방식은 미래에 더 나은 방식으로 변경될 수 있음

7. intValue() - 언박싱(Unboxing)
   - 래퍼 클래스에 들어있는 기본형 값을 다시 꺼내는 메서드
   - 박스에 들어있는 물건을 꺼내는 것 같다고 해서 언박싱(Unboxing)

8. 비교는 equals() 사용
   - 래퍼 클래스는 객체이기 때문에 == 비교를 하면 인스턴스의 참조값을 비교
   - 래퍼 클래스는 내부의 값을 비교하도록 equals()를 재정의 해둠
   - 따라서 값을 비교하려면 equals()를 사용해야 함

9. 참고로 래퍼 클래스는 객체를 그대로 출력해도 내부에 있는 값을 문자로 출력하도록 toString()을 재정의
