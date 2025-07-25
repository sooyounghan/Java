-----
### 날짜와 시간 문자열 파싱과 포맷팅
-----
1. 포맷팅 : 날짜와 시간 데이터를 원하는 포맷의 문자열로 변경하는 것 (Date → String)
2. 파싱 : 문자열을 날짜와 시간 데이터로 변경하는 것 (String → Date)
3. FormattingMain1
```java
package time;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FormattingMain1 {
    public static void main(String[] args) {
        // 포맷팅 : 날짜를 문자로
        LocalDate date = LocalDate.of(2024, 12, 31);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");

        String formattedDate = date.format(formatter);
        System.out.println("날짜와 시간 포맷팅 = " + formattedDate);
        
        // 파싱 : 문자를 날짜로
        String input = "2030년 01월 01일";
        LocalDate parsedDate = LocalDate.parse(input, formatter);
        System.out.println("문자열 파싱 날짜와 시간 = " +parsedDate);
    }
}
```
   - LocalDate과 같은 날짜 객체를 원하는 형태의 문자로 변경하려면 DateTimeFormatter 사용
   - ofPattern()으로 원하는 포맷을 지정 (여기서는 yyyy년 MM월 dd일 포맷을 지정)
<div align="center">
<img src="https://github.com/user-attachments/assets/94f1bc2d-a755-4d8d-94ab-01f10b5a4f54">
</div>

   - DateTimeFormatter 패턴 공식 사이트 : ```https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html#patterns```

-----
### 문자열을 날짜와 시간으로 파싱
-----
1. 문자열을 읽어서 날짜와 시간 객체로 만드는 것을 파싱
2. 이때 문자열의 어떤 부분이 년이고, 월이고 일인지 각각의 위치를 정해서 읽어야 함
   - 2030년 01월 01일
```java
LocalDate.parse(input, formatter)
```
   - 이때도 앞서 설명한 DateTimeFormatter 를 사용
   - 2030년 01월 01일 의 경우 yyyy년 MM월 dd일 포맷으로 읽어들이면 됨

3. FormattingMain2
```java
package time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FormattingMain2 {
    public static void main(String[] args) {
        // 포맷팅 : 날짜와 시간을 문자로
        LocalDateTime now = LocalDateTime.of(2024, 12, 31, 13, 30, 59);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String formattedDateTime = now.format(formatter);
        System.out.println("날짜와 시간 포맷팅 = " + formattedDateTime);

        // 파싱 : 문자를 날짜와 시간으로
        String dateTimeString = "2030-01-01 11:30:30";
        LocalDateTime parsedDateTime = LocalDateTime.parse(dateTimeString, formatter);
        System.out.println("문자열 파싱 날짜와 시간 = " + parsedDateTime);
    }
}
```
   - 실행 결과
```
날짜와 시간 포맷팅 = 2024-12-31 13:30:59
문자열 파싱 날짜와 시간 = 2030-01-01T11:30:30
```
   - LocalDateTime과 같은 날짜와 시간 객체를 원하는 형태의 문자로 변경하려면 DateTimeFormatter 사용
   - ofPattern()으로 원하는 포맷을 지정 (여기서는 ```yyyy-MM-dd HH:mm:ss``` 포맷을 지정)

5. 문자열을 읽어서 날짜와 시간으로 파싱할 때는 년, 월, 일, 시, 분, 초의 위치를 정해서 읽어야 함
   - ```2030-01-01 11:30:00```
```java
LocalDateTime.parse(dateTimeString, formatter)
```
  - 이때도 앞서 설명한 DateTimeFormatter 사용
  - ```2030-01-01 11:30:00```의 경우 ```yyyy-MM-dd HH:mm:ss``` 포맷으로 읽어들이면 됨

