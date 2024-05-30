-----
### TemporalAdjusters 클래스
-----
1. 자주 쓰일만한 날짜 계산 메서드들을 정의해 놓은 클래스
```java
LocalDate today = LocalDate.now();

LocalDate nextMonday = today.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
```

2. TemporalAdjusters의 메서드
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/45375004-f136-4ae0-823e-4ed27db1dc34">
</div>

-----
### TemporalAdjuster 구현
-----
1. 필요에 따라, 자주 사용되는 날짜 계산을 해주는 메서드를 직접 구현 가능
2. LocalDate의 with()는 다음과 같이 정의되어있으며, TemporalAdjuster 인터페이스를 구현한 클래스의 객체를 매개변수로 제공해야함
```java
LocalDate with(TemporalAdjuster adjuster)
```

3. with()는 LocalTime, LocalDateTime, ZonedDateTime, Instant 등 대부분의 날짜와 시간에 관련된 클래스에 포함되어 있음
4. TemporalAdjuster 인터페이스는 다음과 같이 추상 메서드 하나만 정의되어있으므로, 이 메서드만 구현하면 됨
```java
@FunctionInterface
public interface TemporalAdjuster {
    Temporal adjustInto(Temporal temporal);
}
```
  - 실제로 구현해야 하는 것은 adjustInto()지만, TemporalAdjuster와 같이 사용해야 하는 메서드는 with()
  - adjustInto()는 내부적으로만 사용할 의도로 작성된 것이기 때문에, with() 사용

5. 날짜와 시간에 관련된 대부분의 클래스는 Temporal 인터페이스를 구현했으므로 adjustInto()의 매개변수가 될 수 있음
6. 예시) 특정 날짜로부터 2일 후의 날짜를 계산하는 DayAfterTomorrow
```java
class DayAfterTomorrow implements TemporalAdjuster {
    @Override
    public Temporal adjustInto(Temporal temporal) {
          return temporal.plus(2, ChronoUnit.DAYS); // 2일을 더함
    }
}
```

7. 예시
```java
import java.time.*;
import java.time.temporal.*;
import static java.time.DayOfWeek.*;
import static java.time.temporal.TemporalAdjusters.*;

class DayAfterTomorrow implements TemporalAdjuster {
	@Override
	public Temporal adjustInto(Temporal temporal) {
		return temporal.plus(2, ChronoUnit.DAYS);	
	}
}

class NewTimeEx3 {
	public static void main(String[] args) {
		LocalDate today = LocalDate.now();
		LocalDate date  = today.with(new DayAfterTomorrow());

		p(today); // System.out.println(today);
		p(date);

		p(today.with(firstDayOfNextMonth()));        // 다음 달의 첫 날
		p(today.with(firstDayOfMonth()));            // 이 달의 첫날
		p(today.with(lastDayOfMonth()));             // 이 달의 마지막 날
		p(today.with(firstInMonth(TUESDAY)));        // 이 달의 첫번째 화요일
		p(today.with(lastInMonth(TUESDAY)));         // 이 달의 마지막 화요일
		p(today.with(previous(TUESDAY)));            // 지난 주 화요일
		p(today.with(previousOrSame(TUESDAY)));      // 지난 주 화요일(오늘 포함)
		p(today.with(next(TUESDAY)));                // 다음 주 화요일
		p(today.with(nextOrSame(TUESDAY)));          // 다음 주 화요일(오늘 포함)
		p(today.with(dayOfWeekInMonth(4, TUESDAY))); // 이 달의 4번째 화요일
	}

	static void p(Object obj) { // 라인의 길이를 줄이기 위해 새로 정의한 메서드
		System.out.println(obj);
	}
}
```
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/74baed73-d260-4141-aca4-df6fe740bcc2">
</div>
