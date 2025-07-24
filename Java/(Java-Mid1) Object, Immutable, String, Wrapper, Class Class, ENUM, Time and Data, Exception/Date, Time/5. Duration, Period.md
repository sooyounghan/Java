-----
### 기간, 시간의 간격 - Duration, Period
-----
1. Period, Duration 은 시간의 간격(기간)을 표현하는데 사용
2. 시간의 간격은 영어로 amount of time(시간의 양)으로 불림
3. Period : 두 날짜 사이의 간격을 년, 월, 일 단위로 나타냄
```java
public class Period {
     private final int years;
     private final int months;
     private final int days;
}
```
   - PeriodMain
```java
package time;

import java.time.LocalDate;
import java.time.Period;

public class PeriodMain {
    public static void main(String[] args) {
        // 생성
        Period period = Period.ofDays(10);
        System.out.println("period = " + period);
        
        // 계산에 사용
        LocalDate currentDate = LocalDate.of(2030, 1, 1);
        LocalDate plusDate = currentDate.plus(period);
        System.out.println("현재 날짜 = " + currentDate);
        System.out.println("더한 날짜 = " + plusDate);
        
        // 기간 차이
        LocalDate startDate = LocalDate.of(2023, 1, 1);
        LocalDate endDate = LocalDate.of(2023, 4, 2);

        Period between = Period.between(startDate, endDate);
        System.out.println("기간 : " + between.getMonths() + "개월 " + between.getDays() + "일");
    }
}
```
   - 실행 결과
```
period = P10D
현재 날짜 = 2030-01-01
더한 날짜 = 2030-01-11
기간 : 3개월 1일
```
   - 생성
     + of() : 특정 기간을 지정해서 Period 생성
       * of(년, 월, 일)
       * ofDays()
       * ofMonths()
       * ofYears()

   - 계산에 사용 : 2030년 1월 1일에 10일을 더하면 2030년 1월 11일이 된다고 표현할 때 특정 날짜에 10일이라는 기간을 더할 수 있음
   - 기간 차이 : 2023년 1월 1일과 2023년 4월 2일간의 차이는 3개월 1일이다라고 표현할 때 특정 날짜의 차이를 구하면 기간이 됨

   - Period.between(startDate, endDate)와 같이 특정 날짜의 차이를 구하면 Period가 반환

4. Duration : 두 시간 사이의 간격을 시, 분, 초(나노초) 단위로 나타냄
```java
public class Duration {
     private final long seconds;
     private final int nanos;
}
```
   - 내부에서 초를 기반으로 시, 분, 초를 계산해서 사용
      + 1분 = 60초
      + 1시간 = 3600초

   - DurationMain
```java
package time;

import java.time.Duration;
import java.time.LocalTime;

public class DurationMain {
    public static void main(String[] args) {
        // 생성
        Duration duration = Duration.ofMinutes(30);
        System.out.println("duration = " + duration);

        LocalTime lt = LocalTime.of(1, 0);
        System.out.println("기준 시간 = " + lt);
        
        // 계산에 사용
        LocalTime plusTime = lt.plus(duration);
        System.out.println("더한 시간 = " + plusTime);
        
        // 시간 차이
        LocalTime start = LocalTime.of(9, 0);
        LocalTime end = LocalTime.of(10, 0);

        Duration between = Duration.between(start, end);
        System.out.println("차이 : " + between.getSeconds() + "초");
        System.out.println("근무 시간 : " + between.toHours() + "시간 " + between.toMinutesPart() + "분");
    }
}
```
   - 실행 결과
```
duration = PT30M
기준 시간 = 01:00
더한 시간 = 01:30
차이 : 3600초
근무 시간 : 1시간 0분
```

  - 생성
    + of() : 특정 시간을 지정해서 Duration 생성
       * of(지정)
       * ofSeconds()
       * ofMinutes()
       * ofHours()

  - 계산에 사용 : 1:00에 30분을 더하면 1:30이 된다라고 표현할 때 특정 시간에 30분이라는 시간(시간의 간격)을 더할 수 있음
 
  - 시간 차이 : 9시와 10시의 차이는 1시간이라고 표현할 때 시간의 차이를 구할 수 있음
  - Duration.between(start, end)와 같이 특정 시간의 차이를 구하면 Duration 반환
    
<div align="center">
<img src="https://github.com/user-attachments/assets/42f4908e-44d0-4a71-af59-5568e1242e0e">
</div>
