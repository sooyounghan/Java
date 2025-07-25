-----
### 날짜와 시간의 핵심 인터페이스
-----
1. 날짜와 시간은 특정 시점의 시간(시각)과 시간의 간격(기간)으로 나눌 수 있음
<div align="center">
<img src="https://github.com/user-attachments/assets/8e5c2a07-cbcb-428e-894a-ebd682659ab6">
</div>

2. 특정 시점의 시간 : Temporal (TemporalAccessor 포함) 인터페이스를 구현  
   - 구현으로 LocalDateTime, LocalDate, LocalTime, ZonedDateTime, OffsetDateTime, Instant 등 존재

3. 시간의 간격(기간) : TemporalAmount 인터페이스를 구현  
   - 구현으로 Period, Duration 

4. TemporalAccessor 인터페이스
   - 날짜와 시간을 읽기 위한 기본 인터페이스
   - 이 인터페이스는 특정 시점의 날짜와 시간 정보를 읽을 수 있는 최소한의 기능을 제공

5. Temporal 인터페이스
   - TemporalAccessor의 하위 인터페이스로, 날짜와 시간을 조작(추가, 빼기 등)하기 위한 기능을 제공
   - 이를 통해 날짜와 시간을 변경하거나 조정할 수 있음
   
6. 간단히 말하면, TemporalAccessor는 읽기 전용 접근을, Temporal은 읽기와 쓰기(조작) 모두를 지원

7. TemporalAmount 인터페이스
   - 시간의 간격(시간의 양, 기간)을 나타내며, 날짜와 시간 객체에 적용하여 그 객체를 조정할 수 있음
   - 예를 들어, 특정 날짜에 일정 기간을 더하거나 빼는 데 사용

8. 시간의 단위와 시간 필드
   - 시간의 단위를 뜻하는 TemporalUnit (ChronoUnit)
   - 시간의 각 필드를 뜻하는 TemporalField (ChronoField)
<div align="center">
<img src="https://github.com/user-attachments/assets/59285ee7-b8dd-4f6d-ad6f-e9b479dfbb62">
</div>

   - 시간의 단위 : TemporalUnit, ChronoUnit
      + TemporalUnit 인터페이스는 날짜와 시간을 측정하는 단위를 나타내며, 주로 사용되는 구현체는 java.time.temporal.ChronoUnit 열거형으로 구현
      + ChronoUnit은 다양한 시간 단위를 제공
      + 여기서 Unit 이라는 뜻을 번역하면 단위 : 따라서 시간의 단위 하나하나를 나타냄
<div align="center">
<img src="https://github.com/user-attachments/assets/d96dc259-1d86-4ff9-917d-157cc6eb40f4">
<img src="https://github.com/user-attachments/assets/97d70747-d074-40e2-9c67-1ba78c706aa9">
<img src="https://github.com/user-attachments/assets/ecf114d4-55ca-40a2-aadc-96d0734bcc5d">
<img src="https://github.com/user-attachments/assets/5a7c3595-1f67-4c71-83b9-5f6df7baeba5">
</div>

  - ChronoUnitMain
```java
package time;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class ChronoUnitMain {
    public static void main(String[] args) {
        ChronoUnit[] values = ChronoUnit.values();
        for (ChronoUnit value : values) {
            System.out.println("value = " + value);
        }

        System.out.println("HOURS = " + ChronoUnit.HOURS);
        System.out.println("HOURS.duration = " + ChronoUnit.HOURS.getDuration().getSeconds());

        System.out.println("DAYS = " + ChronoUnit.DAYS);
        System.out.println("DAYS.duration = " + ChronoUnit.DAYS.getDuration().getSeconds());
        
        // 차이 구하기
        LocalTime lt1 = LocalTime.of(1, 10, 0);
        LocalTime lt2 = LocalTime.of(1, 20, 0);

        long secondsBetween = ChronoUnit.SECONDS.between(lt1, lt2);
        System.out.println("secondsBetween = " + secondsBetween);

        long minutesBetween = ChronoUnit.MINUTES.between(lt1, lt2);
        System.out.println("minutesBetween = " + minutesBetween);
    }
}
```
   - 실행 결과
```
value = Nanos
value = Micros
value = Millis
value = Seconds
value = Minutes
value = Hours
value = HalfDays
value = Days
value = Weeks
value = Months
value = Years
value = Decades
value = Centuries
value = Millennia
value = Eras
value = Forever

HOURS = Hours
HOURS.duration = 3600

DAYS = Days
DAYS.duration = 86400

secondsBetween = 600
minutesBetween = 10
```
   - ChronoUnit을 사용하면 두 날짜 또는 시간 사이의 차이를 해당 단위로 쉽게 계산할 수 있음
   - 예제 코드에서는 두 LocalTime 객체 사이의 차이를 초, 분 단위로 구함

   - 시간 필드 : ChronoField
     + ChronoField는 날짜 및 시간을 나타내는 데 사용되는 열거형
     + 이 열거형은 다양한 필드를 통해 날짜와 시간의 특정 부분을 나타냄
     + 여기에는 연도, 월, 일, 시간, 분 등이 포함
     + TemporalField 인터페이스는 날짜와 시간을 나타내는데 사용 (주로 사용되는 구현체는 java.time.temporal.ChronoField 열거형으로 구현)
     + ChronoField는 다양한 필드를 통해 날짜와 시간의 특정 부분을 나타내며, 여기에는 연도, 월, 일, 시간, 분 등이 포함
     + 여기서 필드(Field)라는 뜻이 날짜와 시간 중에 있는 특정 필드들을 뜻함 
        * 예를 들어 2024년 8월 16일이라고 하면 각각의 필드 - (YEAR : 2024, MONTH_OF_YEAR : 8, DAY_OF_MONTH : 16)
     + 단순히 시간의 단위 하나하나를 뜻하는 ChronoUnit과는 다름
     + ChronoField 를 사용해야 날짜와 시간의 각 필드 중에 원하는 데이터를 조회할 수 있음
<div align="center">
<img src="https://github.com/user-attachments/assets/26158ae7-0fca-4e32-9662-4be604625735">
<img src="https://github.com/user-attachments/assets/2f848cb2-16f6-46d8-ae29-b2e61d345fb0">
<img src="https://github.com/user-attachments/assets/6bfb4125-6953-4333-aa15-e3384da954d9">
<img src="https://github.com/user-attachments/assets/1b0361b2-265f-4353-9a5f-2944b9829ba7">
<img src="https://github.com/user-attachments/assets/af809422-6683-4441-8914-a533dc3ba085">
<img src="https://github.com/user-attachments/assets/dfa88232-7846-4d4f-927a-698ff193f50a">
</div>

  - ChronoFieldMain
```java
package time;

import java.time.temporal.ChronoField;

public class ChronoFieldMain {
    public static void main(String[] args) {
        ChronoField[] values = ChronoField.values();

        for (ChronoField value : values) {
            System.out.println(value + ", range = " + value.range());
        }

        System.out.println("MONTH_OF_YEAR.range() = " + ChronoField.MONTH_OF_YEAR.range());
        System.out.println("DAY_OF_MONTH.range() = " + ChronoField.DAY_OF_MONTH.range());
    }
}
```
  - 실행 결과
```
NanoOfSecond, range = 0 - 999999999
NanoOfDay, range = 0 - 86399999999999
MicroOfSecond, range = 0 - 999999
MicroOfDay, range = 0 - 86399999999
MilliOfSecond, range = 0 - 999
MilliOfDay, range = 0 - 86399999
SecondOfMinute, range = 0 - 59
SecondOfDay, range = 0 - 86399
MinuteOfHour, range = 0 - 59
MinuteOfDay, range = 0 - 1439
HourOfAmPm, range = 0 - 11
ClockHourOfAmPm, range = 1 - 12
HourOfDay, range = 0 - 23
ClockHourOfDay, range = 1 - 24
AmPmOfDay, range = 0 - 1
DayOfWeek, range = 1 - 7
AlignedDayOfWeekInMonth, range = 1 - 7
AlignedDayOfWeekInYear, range = 1 - 7
DayOfMonth, range = 1 - 28/31
DayOfYear, range = 1 - 365/366
EpochDay, range = -365243219162 - 365241780471
AlignedWeekOfMonth, range = 1 - 4/5
AlignedWeekOfYear, range = 1 - 53
MonthOfYear, range = 1 - 12
ProlepticMonth, range = -11999999988 - 11999999999
YearOfEra, range = 1 - 999999999/1000000000
Year, range = -999999999 - 999999999
Era, range = 0 - 1
InstantSeconds, range = -9223372036854775808 - 9223372036854775807
OffsetSeconds, range = -64800 - 64800
MONTH_OF_YEAR.range() = 1 - 12
DAY_OF_MONTH.range() = 1 - 28/31
```

9. 정리
  - TemporalUnit(ChronoUnit), TemporalField(ChronoField)는 단독으로 사용하기 보다는 주로 날짜와 시간을 조회하거나 조작할 때 사용
