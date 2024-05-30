-----
### Period, Duration
-----
1. Period : 날짜의 차이를 계산
2. Duration : 시간의 차이를 계산
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/e05b7730-3974-427e-9444-d93d6cfed8c6">
</div>

-----
### between()
-----
1. data1과 date2의 차이를 나타내는 Period는 between()으로 얻을 수 있음
```java
LocalDate date1 = LocalDate.of(2014, 1, 1);
LocalDate date2 = LocalDate.of(2015, 12, 31);

Period pe = Period.between(date1, date2);
```

2. date1이 date2보다 날짜 상 이전이면 양수, 이후면 음수로 Period에 저장
3. 시간 차이를 구할 때는 Duration을 사용하는 것 외에는 동일
```java
LocalTime time1 = LocalTime.of(00, 00, 00);
LocalTime time2 = LocalTime.of(12, 34, 56);

Duration du = Duration.between(time1, time2);
```

-----
### get()
-----
1. Period, Duration에서 특정 필드의 값을 얻을 때 get() 사용
```java
long year = pe.get(ChronoUnit.YEARS); // int getYears()
long month = pe.get(ChronoUnit.MONTHS); // int getMonths()
long day = pe.get(ChronoUnit.DAYS); // int getDays(0

long sec = du.get(ChronoUnit.SECONDS); // long getSeconds()
long nano = du.get(ChronoUnit.NANOS); // int getNano()
```

2. Period와 달리, Duration에는 getHours(), getMinutes()와 같은 메서드가 없음
  - getUnits()라는 메서드로 get()에 사용할 수 있는 ChronoUnit 종류 확인 가능
```java
System.out.println(pe.getUnits()); // [Years, Months, Days]
System.out.println(du.getUnits()); // [Seconds, Nanos]
```
  - Duration에는 ChronoUnit.SECONDS와 Chrono.NANOS 밖에 사용할 수 없으므로, 다음과 같이 계산
```java
long hour = du.getSeconds() / 3600;
long min = (du.getSeconds() - (hour * 3600)) / 60;
long sec = (du.getSeconds() - (hour * 3600) - (min * 60)) % 60;
int nano = du.getNano();
```

  - Duration을 LocalTime으로 변환 후, LocalTime이 가지고 있는 get메서드들을 사용하는 것이 더 간단함
```java
LocalTime tmpTime = LocalTime.of(0, 0).plusSeconds(du.getSeconds());

int hour = tmpTime.getHour();
int min = tmpTime.getMinute();
int sec = tmpTime.getSecond();
int nano = du.getNano();
```

-----
### between()과 until()
-----
1. 둘은 동일한 역할을 하나 차이가 존재
   - between() : static 메서드
   - until() : 인스턴스 메서드
```java
// Period pe = Period.between(today, myBirthDay);
Period pe = today.until(myBirthDay);
long dday = today.until(myBirthDay, ChronoUnit.DAYS);
```

2. Period는 년월일을 분리해서 저장하기 때문에, D-day를 구하는 경우에는 두 개의 매개변수를 받는 until()을 사용하는 것이 나음
3. 날짜가 아닌 시간에도 until()을 사용할 수 있지만, Duration을 반환하는 until()은 없음

```java
long sec = LocalTime.now().until(endTime, ChornoUnit.SECONDS);
```

-----
### of(), with()
-----
1. Period에는 of(), ofYears(), ofMonths(), ofWeeks(), ofDays() 등 존재
2. Duration에는 of(), ofHours(), ofMinutes(), ofSeconds() 등 존재
```java
Period pe = Period.of(1, 12, 31); // 1년 12개월 31일

Duration du = Duration.of(60, ChronoUnit.SECONDS); // 60초
// Duration du = Duration.ofSeconds(60); // 위 문장과 동일
```

3. 특정 필드의 값을 변경하는 with()도 존재
```java
pe = pe.withYears(2); // 1년에서 2년으로 변경 (withMonths(), withDays()도 존재)
du = du.withSeconds(120); // 60초에서 120초로 변경 (withNanos()도 존재)
```

-----
### 사칙연산, 비교연산, 기타 메서드
-----
1. plus(), minus() 외에 곱셈과 나눗셈을 위한 메서드도 존재
```java
pe = pe.minusYears(1).multipliedBy(2); // 1년을 빼고, 2배를 곱함
du = du.plusHours(1).dividedBy(60); // 1시간을 더하고, 60으로 나눔
```

2. Period에는 나눗셈을 위한 메서드가 없는데, Period는 날짜의 기간을 표현하기 위한 것이므로 나눗셈을 위한 메서드가 유용하지 않기 때문에 넣지 않음
3. 또한, 음수인지 확인하는 isNegative()와 0인지 확인하는 isZero() 존재
   - 두 날짜 또는 시간을 비교할 때, 사용하면 어느 쪽이 앞인지 또는 같은지 알아낼 수 있음
```java
boolean sameDate = Period.between(date1, date2).isZero();

boolean isBefore = Duration.between(time1, time2).isNegative();
```

4. 부호를 반대로 변경하는 negate()와 부호를 없애는 abs()도 존재
```java
du = du.abs();
```
   - Period에는 abs()가 없어서 다음과 같이 변경해서 사용
```java
if(du.isNegative())
  du = du.negate();
````

5. Period에는 normalized()라는 메서드 존재
   - 월(month)의 값이 12를 넘지 않게, 즉, 1년 13개월을 2년 1개월로 변경
   - 일(day)의 길이는 일정하지 않으므로 그대로 놔둠
```java
pe = Period.of(1, 13, 32).normalized(); // 1년 13개월 32일 → 2년 1개월 32일
```

-----
### 다른 단위로 변환 - toTotalMonths(), toDays(), toHours(), toMinutes()
-----
1. 이름이 to로 시작하는 메서드들이 있는데, 이들은 Period와 Duration을 다른 단위의 값으로 변한하는 데 사용
2. get()은 특정 필드의 값을 그대로 가져오지만, 아래 메서드들은 특정 단위로 변환한 결과를 반환
  - Period와 Duration의 변환 메서드
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/5c6b9548-12d7-43eb-aa61-a0f97f10de0c">
</div>

  - 반환 타입이 모두 정수 타입(long)인데, 지정된 단위 이하 값들은 모두 버림

3. 참고로, LocalDate의 toEpochDay()라는 메서드는 Epoch Day인 '1970-01-01'부터 날짜를 세어서 반환
   - 이 메서드를 사용하면 Period를 이용하지 않고도, 두 날짜간의 일수를 편리하게 계산 가능
   - 단, 두 날짜 모두 Epoch Day 이후의 것이어야 함
```java
LocalDate date1 = LocalDate.of(2015, 11, 28);
LocalDate date1 = LocalDate.of(2015, 11, 29);

long period = date2.toEpochDay() - date1.toEpochDay(); // 1
```

4. LocalTime에도 다음과 같은 메서드가 있어서, Duration을 사용하지 않고도, 위와 같이 뺄셈으로 시간 차 계산 가능
```java
int toSecondOfDay()
int toNanoOfDay()
```

-----
### 예제
-----
```java
import java.time.*;
import java.time.temporal.*;

class NewTimeEx4 {
	public static void main(String[] args) {
		LocalDate date1 = LocalDate.of(2014,  1,  1);
		LocalDate date2 = LocalDate.of(2015, 12, 31);

		Period pe = Period.between(date1, date2);
		
		System.out.println("date1="+date1);
		System.out.println("date2="+date2);
		System.out.println("pe="+pe);
	
		System.out.println("YEAR=" +pe.get(ChronoUnit.YEARS)); 
		System.out.println("MONTH="+pe.get(ChronoUnit.MONTHS)); 
		System.out.println("DAY="  +pe.get(ChronoUnit.DAYS));  

		LocalTime time1 = LocalTime.of( 0, 0, 0); 
		LocalTime time2 = LocalTime.of(12,34,56); // 12시 34분 56초

		Duration du = Duration.between(time1, time2);

		System.out.println("time1="+time1);
		System.out.println("time2="+time2);
		System.out.println("du="+du);

		LocalTime tmpTime = LocalTime.of(0,0).plusSeconds(du.getSeconds());

		System.out.println("HOUR="  +tmpTime.getHour());
		System.out.println("MINUTE="+tmpTime.getMinute());
		System.out.println("SECOND="+tmpTime.getSecond());
		System.out.println("NANO="  +tmpTime.getNano());
	}
}
```
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/c4ee510a-b5a6-4f4a-b101-f3d62358b550">
</div>
