-----
### Number 클래스
-----
1. 추상클래스로, 내부적으로 숫자를 멤버변수로 갖는 래퍼 클래스들의 조상
2. 래퍼 클래스의 상속 계층도
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/9d1f2dee-113c-4fe2-aac4-584237e4fa20">
</div>

  - 기본형 중에서 숫자와 관련된 래퍼 클래스들은 모두 Number클래스의 자손

3. 이 외에도 Number 클래스 자손으로 BigInteger와 BigDecimal 등이 존재
   - BigInteger : long으로도 다룰 수 없는 큰 범위의 정수
   - BigDecimal : double로도 다룰 수 없는 큰 범위의 부동 소수점수를 처리하기 위한 것
   - 즉, 연산자의 역할을 대신해서 다양한 메서드 제공
  
4. 실제 Number 클래스의 실제 소스 (객체가 가지고 있는 값을 숫자와 관련된 기본형으로 변환하는 메서드들 정의)
```java
public abstract class Number implements java.io.Serializable {
    public abstract int intValue();

    public abstract long longValue();

    public abstract float floatValue();

    public abstract double doubleValue();

    public byte byteValue() {
        return (byte)intValue();
    }

    public short shortValue() {
        return (short)intValue();
    }
}
```
