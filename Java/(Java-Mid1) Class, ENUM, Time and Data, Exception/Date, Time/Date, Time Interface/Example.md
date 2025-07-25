-----
### 날짜와 시간 조회하고 조작하기 1
-----
1. 날짜와 시간 조회하기
   - 날짜와 시간을 조회하려면 날짜와 시간 항목중에 어떤 필드를 조회할지 선택
   - 이 때 날짜와 시간의 필드를 뜻하는 ChronoField 사용
   - GetTimeMain
```java
package time;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

public class GetTimeMain {
    public static void main(String[] args) {
        LocalDateTime dt = LocalDateTime.of(2030, 1, 1, 13, 30, 59);
        System.out.println("YEAR = " + dt.get(ChronoField.YEAR));
        System.out.println("MONTH_OF_YEAR = " + dt.get(ChronoField.MONTH_OF_YEAR));
        System.out.println("DAY_OF_MONTH = " + dt.get(ChronoField.DAY_OF_MONTH));
        System.out.println("HOUR_OF_DAY = " + dt.get(ChronoField.HOUR_OF_DAY));
        System.out.println("MINUTE_OF_HOUR = " + dt.get(ChronoField.MINUTE_OF_HOUR));
        System.out.println("SECOND_OF_MINUTE = " + dt.get(ChronoField.SECOND_OF_MINUTE));

        System.out.println("편의 메서드 사용");
        System.out.println("YEAR = " + dt.getYear());
        System.out.println("MONTH_OF_YEAR = " + dt.getMonthValue());
        System.out.println("DAY_OF_MONTH = " + dt.getDayOfMonth());
        System.out.println("HOUR_OF_DAY = " + dt.getHour());
        System.out.println("MINUTE_OF_HOUR = " + dt.getMinute());
        System.out.println("SECOND_OF_MINUTE = " + dt.getSecond());
        
        System.out.println("편의 메서드에 없음");
        System.out.println("MINUTE_OF_DAY = " + dt.get(ChronoField.MINUTE_OF_DAY));
        System.out.println("SECOND_OF_DAY = " + dt.get(ChronoField.SECOND_OF_DAY));
    }
}
```
   - 실행 결과
```
YEAR = 2030
MONTH_OF_YEAR = 1
DAY_OF_MONTH = 1
HOUR_OF_DAY = 13
MINUTE_OF_HOUR = 30
SECOND_OF_MINUTE = 59

편의 메서드 사용
YEAR = 2030
MONTH_OF_YEAR = 1
DAY_OF_MONTH = 1
HOUR_OF_DAY = 13
MINUTE_OF_HOUR = 30
SECOND_OF_MINUTE = 59

편의 메서드에 없음
MINUTE_OF_DAY = 810
SECOND_OF_DAY = 48659
```

  - TemporalAccessor.get(TemporalField field)
    + LocalDateTime을 포함한 특정 시점의 시간을 제공하는 클래스는 모두 TemporalAccessor 인터페이스를 구현
    + TemporalAccessor는 특정 시점의 시간을 조회하는 기능을 제공
    + get(TemporalField field)을 호출할 때 어떤 날짜와 시간 필드를 조회할 지 TemporalField의 구현인 ChronoField를 인수로 전달

  - 편의 메서드 사용
    + get(TemporalField field)을 사용하면 코드가 길어지고 번거롭기 때문에 자주 사용하는 조회 필드는 간단한 편의 메서드를 제공
    + dt.get(ChronoField.DAY_OF_MONTH)) → dt.getDayOfMonth()

  - 편의 메서드에 없음
    + 자주 사용하지 않는 특별한 기능은 편의 메서드를 제공하지 않음
    + 편의 메서드를 사용하는 것이 가독성이 좋기 때문에 일반적으로는 편의 메서드를 사용하고, 편의 메서드가 없는 경우 get(TemporalField field) 사용

2. 날짜와 시간 조작하기
   - 날짜와 시간을 조작하려면 어떤 시간 단위(Unit)를 변경할 지 선택해야 함
   - 이 때 날짜와 시간의 단위를 뜻하는 ChronoUnit이 사용
   - ChangeTimePlusMain
```java
package time;

import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class ChangeTimePlusMain {
    public static void main(String[] args) {
        LocalDateTime dt = LocalDateTime.of(2018, 1, 1, 13, 30, 5);
        System.out.println("dt = " + dt);

        LocalDateTime plusDt1 = dt.plus(10, ChronoUnit.YEARS);
        System.out.println("plusDt1 = " + plusDt1);

        LocalDateTime plusDt2 = dt.plusYears(10);
        System.out.println("plusDt2 = " + plusDt2);

        Period period = Period.ofYears(10);
        LocalDateTime plushDt3 = dt.plus(period);
        System.out.println("plushDt3 = " + plushDt3);
    }
}
```
  - 실행 결과
```
dt = 2018-01-01T13:30:05
plusDt1 = 2028-01-01T13:30:05
plusDt2 = 2028-01-01T13:30:05
plushDt3 = 2028-01-01T13:30:05
```

   - Temporal plus(long amountToAdd, TemporalUnit unit)
     + LocalDateTime을 포함한 특정 시점의 시간을 제공하는 클래스는 모두 Temporal 인터페이스를 구현
     + Temporal은 특정 시점의 시간을 조작하는 기능을 제공
     + plus(long amountToAdd, TemporalUnit unit)를 호출할 때 더하기 할 숫자와 시간의 단위(Unit)를 전달하면 되는데, 이때 TemporalUnit의 구현인 ChronoUnit을 인수로 전달하면 됨
     + 불변이므로 반환 값을 받아야 하며, 참고로 minus()도 존재

   - 편의 메서드 사용
     + 자주 사용하는 메서드는 편의 메서드가 제공 : dt.plus(10, ChronoUnit.YEARS) → dt.plusYears(10)

   - Period를 사용한 조작 : Period나 Duration은 기간(시간의 간격)을 뜻하는데, 특정 시점의 시간에 기간을 더할 수 있음

3. 정리
   - 시간을 조회하고 조작하는 부분을 보면 TemporalAccessor.get(), Temporal.plus()와 같은 인터페이스를 통해 특정 구현 클래스와 무관하게 아주 일관성 있는 시간 조회, 조작 기능을 제공하는 것을 확인할 수 있음
   - 덕분에 LocalDateTime, LocalDate, LocalTime, ZonedDateTime, Instant 와 같은 수 많은 구현에 관계없이 일관성 있는 방법으로 시간을 조회하고 조작할 수 있음

4. 하지만 모든 시간 필드를 다 조회할 수 있는 것은 아님
   - InSupportedMain1
```java
package time;

import java.time.LocalDate;
import java.time.temporal.ChronoField;

public class InSupportedMain1 {
    public static void main(String[] args) {
        LocalDate now = LocalDate.now();
        int minute = now.get(ChronoField.SECOND_OF_MINUTE);
        System.out.println("minute = " + minute);
    }
}
```
  - 실행 결과
```
Exception in thread "main"
java.time.temporal.UnsupportedTemporalTypeException: Unsupported field : SecondOfMinute
```
   - LocalDate는 날짜 정보만 가지고 있고, 분에 대한 정보는 없음
   - 따라서 분에 대한 정보를 조회하려고 하면 예외가 발생
   - 이런 문제를 예방하기 위해 TemporalAccessor와 Temporal 인터페이스는 현재 타입에서 특정 시간 단위나 필드를 사용할 수 있는지 확인할 수 있는 메서드를 제공

   - TemporalAccessor
```java
boolean isSupported(TemporalField field);
```
   - Temporal
```java
boolean isSupported(TemporalUnit unit);
```
   - InSupportedMain2
```java
package time;

import java.time.LocalDate;
import java.time.temporal.ChronoField;

public class InSupportedMain2 {
    public static void main(String[] args) {
        LocalDate now = LocalDate.now();
        boolean supported = now.isSupported(ChronoField.SECOND_OF_MINUTE);

        System.out.println("supported = " + supported);

        if(supported) {
            int minute = now.get(ChronoField.SECOND_OF_MINUTE);
            System.out.println("minute = " + minute);
        }
    }
}
```
   - 실행 결과
```
supported = false
```
   - LocalDate는 분의 초 필드를 지원하지 않으므로 ChronoField.SECOND_OF_MINUTE를 조회하면 false를 반환

