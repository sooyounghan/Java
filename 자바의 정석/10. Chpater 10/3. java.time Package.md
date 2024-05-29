-----
### java.time Package
-----
1. java.time 패키지와 서브 패키지들
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/388c19fe-1147-44c3-855e-ecc54497ac5d">
</div>

2. String 클래스처럼 불변(Immutable)이므로, 날짜와 시간을 변경하는 메서드들은 기존의 객체를 변경하는 대신 항상 변경된 새로운 객체 반환
3. 기존 Calendar 클래스는 변경 가능하므로, 멀티 쓰레드 환경에서 안전하지 못함
  - 멀티 쓰레드 환경에서는 동시에 여러 쓰레드가 같은 객체에 접근할 수 있기 때문에, 변경 가능한 객체는 데이터가 잘못될 가능성이 있음

-----
### java.time 패키지의 핵심 클래스
-----
1. 날짜와 시간을 별도의 클래스로 구분
2. 시간을 표현할 때는 LocalTime 클래스를 사용
3. 날짜를 표현할 때는 LocalDate 클래스를 사용
4. 날짜와 시간이 모두 필요할 때는 LocalDateTime 클래스를 사용
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/7e5ac488-8055-4433-92d2-29d319a32936">
</div>

5. 여기에 시간대(Time-Zone)까지 다뤄야 한다면, ZonedDateTime 클래스를 사용
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/9c9165b1-a598-4f15-8ee3-66242ba0182e">
</div>

6. Calendar 클래스는 ZonedDateTime 클래스처럼, 날짜와 시간 그리고 시간대까지 모두 가지고 있음
7. Date 클래스와 유사한 클래스로는 Instant 클래스 존재 (날짜와 시간을 초 단위(정확히는 나노초)로 표현)
   - 타임 스탬프(Time-Stamp) : 날짜와 시간을 초 단위로 표현한 값 (즉, 날짜와 시간을 하나의 정수로 표현)

8. 날짜와 시간의 간격을 표현하기 위한 클래스 - Period, Duration
   - Period : 두 날짜간의 차이를 표현
   - Duration : 시간의 차이를 표현
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/761fc0aa-e869-4f87-a5c3-d0e985c0d7df">
</div>

-----
### 객체 생성하기 - now(), of()
-----
1. java.time 패키지에 속한 클래스의 객체를 생성하는 가장 기본적인 방법
2. now()는 현재 날짜와 시간을 저장하는 객체 생성
```java
LocalDate date = LocalDate.now(); // 2024-05-29
LocalTime time = LocalTime.now(); // 16:33:03.758
LocalDateTime dateTime = LocalDateTime.now(); // 2024-05-29T16:33:03.758
ZonedDateTime dateTimeInKr = ZonedDateTime.now(); // 2024-05-29T16:33:03.758+09:00[Asia/Seoul]
```

3. of()는 단순히 해당 필드의 값을 순서대로 지정해주기만 하면 됨
```java
LocalDate date = LocalDate.of(2015, 11, 23); // 2015년 11월 23일
LocalTime time = LocalTime.of(23, 59, 59); // 23시 59분 59초

LocalDateTime dateTime = LocalDateTime.of(date, time);
ZonedDateTime zDateTime = ZonedDateTime.of(dateTime, zoneId.of("Asia/Seoul"));
```

-----
### Temporal과 TemporalAmount
-----
1. LocalDate, LocalTime, LocalDateTime, ZonedDateTime, Instant 등 날짜와 시간을 표현하기 위한 클래스들은 모두 Temporal, TemporalAccessor, TemproalAdjuster 인터페이스를 구현
2. Duration, Period는 TemporalAmount 인터페이스를 구현
3. Temporal은 대부분 날짜와 시간을 위한 것
  - Temporal과 Chrono 의미가 모두 시간이지만, time 대신 이러한 용어를 쓰는 이유는 시간(시분초)과 더 큰 개념인 시간(년월일시분초)을 구분하기 위함

-----
### TemporalUnit과 TemporalField
-----
1. TemporalUnit : 날짜와 시간의 단위를 정의해놓은 인터페이스
   - 이 인터페이스를 구현한 것이 열거형 ChronoUnit
2. TemproalField : 년, 월, 일 등 날짜와 시간의 필드를 정의해놓은 인터테이스
   - 이 인터페이스를 구현한 것이 열거형 ChrnonoField
```java
LocalTime now = LocalTime.now(); // 현재 시간
int minute = now.getMinute(); // 현재 시간에서 분(minute)만 추출
// int minute = now.get(ChronoField.MINUTE_OF_HOUR); // 위의 문장과 동일
```

3. 날짜와 시간에서 특정 필드의 값만 얻을 때는 get()이나, get으로 시작하는 이름의 메서드 이용
4. 특정 날짜와 시간에서 지정된 단위의 값을 더하거나 뺄 때는, plus() 또는 minus()에 값과 함께 열거형 ChronoUnit 사용
```
int get(TemporalField field)
LocalDate plus(long amountToAdd, TemporalUnit unit)
```

```java
LocalDate today = LocalDate.now(); // 오늘
LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS); // 오늘에 1일을 더함
LocalDate tomorrow = today.plusDays(1); // 위의 문장과 동일
```

5. 특정 TemporalField나 TemporalUnit을 사용할 수 있는지 확인하는 메서드는 다음과 같음 (이 메서드들은 날짜와 시간을 표현하는데 사용하는 모든 클래스에 포함)
```java
boolean isSupported(TemporalUnit unit) // Temporal에 정의
boolean isSupported(TemporalField field) // TemporalAccessor에 정의
```
