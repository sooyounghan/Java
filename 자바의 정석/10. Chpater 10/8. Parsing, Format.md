-----
### 형식화(Formatting)
-----
1. 관련 클래스들은 java.time.format Package에 존재
2. 이 중, DateTimeFormatter 클래스가 핵심이며, 이 클래스는 자주 쓰이는 다양한 형식들을 기본적으로 정의하고 있음
3. 이 외의 형식이 필요하다면 직접 정의해서 사용 가능
```java
LocalDate date = LocalDate.of(2016, 1, 2);

String yyyymmdd = DateTimeFormatter.ISO_LOCAL_DATE.format(date); // "2016-01-02"

String yyyymmdd = date.format(DateTimeFormatter.ISO_LOCAL_DATE); // "2016-01-02"
```

4. 날짜와 시간의 형식화에는 위와 같이 format() 사용
   - DateTimeFormatter 뿐만 아니라 LocalDate나 LocalTime 같은 클래스에도 존재

5. DateTimeFormatter에 상수로 정의된 형식들 목록
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/5b88e6d8-98a5-47f8-8d02-f4a6f8f7062d">
</div>

-----
### Locale에 종속된 형식화
-----
1. DateTimeFormatter의 static 메서드 ofLocalizedDate(), ofLocalizedTime(), ofLocalizedDateTime()은 Local에 종속적 Formatter를 생성
```java
DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);

String shortFormat = formatter.format(LocalDate.now());
```

2. FormatStyle 종류에 정의된 상수와 출력 예
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/dd89e1c7-c69a-4721-861f-090e0027d26a">
</div>

-----
### 출력 형식 직접 정의 하기
-----
1. DateTimeFormatter의 ofPattern()으로 원하는 출력 형식 직접 작성 가능
```java
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
```

2. 출력형식에 사용되는 기호 목록 (DateTimeFormatter 패턴에 사용되는 기호)
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/f1a8e725-a410-42f9-9a0f-7b8c2cd8ed2a">
<img src="https://github.com/sooyounghan/Java/assets/34672301/b450fedc-641a-4de0-828b-0cc2d14589cc">
</div>

3. 예제
```java
import java.time.*;
import java.time.format.*;

class DateFormatterEx1 {
	public static void main(String[] args) {
		ZonedDateTime zdateTime = ZonedDateTime.now();

		String[] patternArr = {
				"yyyy-MM-dd HH:mm:ss",         
				"''yy년 MMM dd일 E요일", 	
				"yyyy-MM-dd HH:mm:ss.SSS Z VV",	
				"yyyy-MM-dd hh:mm:ss a",			
				"오늘은 올 해의 D번째 날",     	
				"오늘은 이 달의 d번째 날",     	
				"오늘은 올 해의 w번째 주",     	
				"오늘은 이 달의 W번째 주",     	
				"오늘은 이 달의 W번째 E요일."   	
		};

		for(String p : patternArr) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(p);
			System.out.println(zdateTime.format(formatter));	
		}
	} // main
}
```
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/5256c90c-a581-4862-9447-42d9f7edc78d">
</div>

-----
### 문자열을 날짜와 시간으로 Parsing
-----
1. 문자열을 날짜 또는 시간으로 변환하려면 static 메서드 parse()를 이용
2. 날짜와 시간을 표현하는데 사용되는 클래스는 이 메서드가 거의 다포함되어 있음
3. parse()는 오버로딩 된 메서드가 여러개 있는데, 다음 2개 메서드가 자주 사용
```java
static LocalDateTime parse(CharSequence text)
static LocalDateTime parse(CharSequence text, DateTimeFormatter formatter)
```

4. DateTimeFormatter에 상수로 정의된 형식을 사용할 때는 다음과 같이 사용
```java
LocalDate date = LocalDate.parse("2016-01-02", DateTimeFormatter.ISO_LOCAL_DATE);
```

5. 자주 사용되는 기본적 형식의 문자열은 ISO_LOCAL_DATE와 같은 형식화 상수를 사용하지 않고도 Parsing이 가능
```java
LocalDate newDate = LocalDate.parse("2000-01-01");
LocalTime newTime = LocalTime.parse("23:59:59");
LocalDateTime newDateTime = LocalDateTime.parse("2001-01-01T23:59:59");
```

6. ofPattern()을 이용해서 Parsing 가능
```java
DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

LocalDateTime endOfYear = LocalDateTime.parse("2015-12-31 23:59:59", pattern);
```

7. 예제
```java
import java.time.*;
import java.time.format.*;

class DateFormatterEx2 {
	public static void main(String[] args) {

		LocalDate newYear = LocalDate.parse("2016-01-01", DateTimeFormatter.ISO_LOCAL_DATE);

		LocalDate     date     = LocalDate.parse("2001-01-01");
		LocalTime     time     = LocalTime.parse("23:59:59");
		LocalDateTime dateTime = LocalDateTime.parse("2001-01-01T23:59:59");

		DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime endOfYear   = LocalDateTime.parse("2015-12-31 23:59:59", pattern);

		System.out.println(newYear);
		System.out.println(date);
		System.out.println(time);
		System.out.println(dateTime);
		System.out.println(endOfYear);
	} // main
}
```
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/b6e08fe6-e864-4b9c-bca4-9c26d0d49bde">
</div>




