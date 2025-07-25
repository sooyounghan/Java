-----
### 날짜와 시간 조회하고 조작하기 2
-----
1. 날짜와 시간을 조작하는 with() 메서드
   - ChangeTimeWithMain
```java
package time;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;

public class ChangeTimeWithMain {
    public static void main(String[] args) {
        LocalDateTime dt = LocalDateTime.of(2018, 1, 1, 13, 30, 59);
        System.out.println("dt = " + dt);

        LocalDateTime changeDt1 = dt.with(ChronoField.YEAR, 2020);
        System.out.println("changeDt1 = " + changeDt1);

        LocalDateTime changeDt2 = dt.withYear(2020);
        System.out.println("changeDt2 = " + changeDt2);
        
        // TemporalAdjuster 사용 
        
        // 다음주 금요일
        LocalDateTime with1 = dt.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        System.out.println("기준 날짜 = " + dt);
        System.out.println("다음 금요일 = " + with1);
        
        // 이번 달 마지막 일요일
        LocalDateTime with2 = dt.with(TemporalAdjusters.lastInMonth(DayOfWeek.SUNDAY));
        System.out.println("같은 달 마지막 일요일 = " + with2);
    }
}
```
   - 실행 결과
```
dt = 2018-01-01T13:30:59
changeDt1 = 2020-01-01T13:30:59
changeDt2 = 2020-01-01T13:30:59
기준 날짜 = 2018-01-01T13:30:59
다음 금요일 = 2018-01-05T13:30:59
같은 달 마지막 일요일 = 2018-01-28T13:30:59
```

  - Temporal with(TemporalField field, long newValue)
     + Temporal.with()를 사용하면 날짜와 시간의 특정 필드의 값만 변경할 수 있음
     + 불변이므로 반환 값을 받아야 함

   - 편의 메서드 : 자주 사용하는 메서드는 편의 메서드가 제공
     + dt.with(ChronoField.YEAR, 2020) → dt.withYear(2020)

   - TemporalAdjuster 사용
      + with()는 아주 단순한 날짜만 변경할 수 있음
      + 다음 금요일, 이번 달의 마지막 일요일 같은 복잡한 날짜를 계산하고 싶다면 TemporalAdjuster 사용
   - TemporalAdjuster 인터페이스
```java
public interface TemporalAdjuster {
 Temporal adjustInto(Temporal temporal);
}
```
   - 원래대로 하면 이 인터페이스를 직접 구현해야겠지만, 자바는 이미 필요한 구현체들을 TemporalAdjusters에 다 만들어둠
   - 단순히 구현체들을 모아둔 TemporalAdjusters 사용
      + TemporalAdjusters.next(DayOfWeek.FRIDAY) : 다음 금요일
      + TemporalAdjusters.lastInMonth(DayOfWeek.SUNDAY) : 이번 달의 마지막 일요일
    
2. DayOfWeek : 월, 화, 수, 목, 금, 토, 일을 나타내는 열거형

3. TemporalAdjusters 클래스가 제공하는 주요 기능
<div align="center">
<img src="https://github.com/user-attachments/assets/e34d0234-a398-4b1b-9a71-7053ad7ca490">
</div>

