-----
### 문제와 풀이 1
-----
1. time.test 패키지를 사용
2. 문제 1 - 날짜 더하기
   - 문제 설명
      + 2024년 1월 1일 0시 0분 0초에 1년 2개월 3일 4시간 후의 시각을 찾아라.
      + TestPlus
```java
package time.test;

import java.time.LocalDateTime;

public class TimePlus {
    public static void main(String[] args) {
        LocalDateTime dateTime = LocalDateTime.of(2024, 1, 1, 0, 0, 0);
        LocalDateTime futureDateTime = dateTime.plusYears(1).plusMonths(2).plusDays(3).plusHours(4);

        System.out.println("기준 시간 : " + dateTime);
        System.out.println("1년 2개월 3일 4시간 후 시각 : " + futureDateTime);
    }
}
```
   - 실행 결과
```
기준 시간 : 2024-01-01T00:00
1년 2개월 3일 4시간 후 시각 : 2025-03-04T04:00
```

3. 문제 2 - 날짜 간격 반복 출력하기
  - 문제 설명
    + 2024년 1월 1일 부터 2주 간격으로 5번 반복하여 날짜를 출력하는 코드를 작성
    + TestLoopPlus
```java
package time.test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class TestLoopPlus {
    public static void main(String[] args) {
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        
        for(int i = 0; i < 5;i ++) {
            LocalDate nextDate = startDate.plus(2 * i, ChronoUnit.WEEKS);
            System.out.println("날짜 " + (i + 1) + ": " + nextDate);
        }
    }
}
```
   - 실행 결과
```
날짜 1: 2024-01-01
날짜 2: 2024-01-15
날짜 3: 2024-01-29
날짜 4: 2024-02-12
날짜 5: 2024-02-26
```

4. 문제3 - 디데이 구하기
    - 문제 설명
      + 시작 날짜와 목표 날짜를 입력해서 남은 기간과 디데이를 구할거
      + 남은 기간 : x년 x개월 x일 형식으로 출력
      + 디데이 : x일 남은 형식으로 출력한다

    - TestBetween
```java
package time.test;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

public class TestBetween {
    public static void main(String[] args) {
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 11, 21);

        Period period = Period.between(startDate, endDate);
        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);

        System.out.println("시작 날짜 : " + startDate);
        System.out.println("목표 날짜 : " + endDate);
        System.out.println("남은 기간 : " + period.getYears() + "년 " + period.getMonths() + "개월 " + period.getDays() + "일");
        System.out.println("디데이 : " + daysBetween + "일 남음");
    }
}
```
   - 실행 결과
```
시작 날짜 : 2024-01-01
목표 날짜 : 2024-11-21
남은 기간 : 0년 10개월 20일
디데이 : 325일 남음

```

5. 문제 4 - 시작 요일, 마지막 요일 구하기
   - 입력 받은 월의 첫날 요일과 마지막날 요일을 구할 것
   - TestAdjusters
```java
package time.test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class TestAdjusters {
    public static void main(String[] args) {
        // 입력 받은 월의 첫날 요일과 마지막 날 요일을 구할 것
        int year = 2024;
        int month = 1;

        LocalDate date = LocalDate.of(year, month, 1);
        DayOfWeek firstDayOfWeek = date.getDayOfWeek();
        DayOfWeek lastDayOfWeek = date.with(TemporalAdjusters.lastDayOfMonth()).getDayOfWeek();
        System.out.println("firstDayOfWeek = " + firstDayOfWeek);
        System.out.println("lastDayOfWeek = " + lastDayOfWeek);
    }
}
```
   - 실행 결과
```
firstDayOfWeek = MONDAY
lastDayOfWeek = WEDNESDAY
```

6. 문제5 - 국제 회의 시간
  - 서울의 회의 시간은 2024년 1월 1일 오전 9시일 때, 이때 런던, 뉴욕의 회의 시간을 구할 것
  - TestZone
```java
package time.test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TestZone {
    public static void main(String[] args) {
        ZonedDateTime seoulTime = ZonedDateTime.of(LocalDate.of(2024, 1, 1), LocalTime.of(9, 0), ZoneId.of("Asia/Seoul"));

        ZonedDateTime londonTime = seoulTime.withZoneSameInstant(ZoneId.of("Europe/London"));
        ZonedDateTime nyTime = seoulTime.withZoneSameInstant(ZoneId.of("America/New_York"));

        System.out.println("서울 회의 시간 : " + seoulTime);
        System.out.println("런던 회의 시간 : " + londonTime);
        System.out.println("뉴욕 회의 시간 : " + nyTime);
    }
}
```
   - 실행 결과
```
서울 회의 시간 : 2024-01-01T09:00+09:00[Asia/Seoul]
런던 회의 시간 : 2024-01-01T00:00Z[Europe/London]
뉴욕 회의 시간 : 2023-12-31T19:00-05:00[America/New_York]
```

-----
### 문제와 풀이 2
-----
1. 문제 6 : 달력 출력하기
   - 실행 결과를 참고해서 달력을 출력
   - 입력 조건 : 년도, 월
  
2. TestCalendarPrinter
```java
package time.test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Scanner;

public class TestCalendarPrinter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("연도를 입력하세요 : ");
        int year = scanner.nextInt();

        System.out.println("월을 입력하세요 : ");
        int month = scanner.nextInt();

        printCalnder(year, month);
    }

    public static void printCalnder(int year, int month) {
        LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
        LocalDate firstDayOfnNextMonth = firstDayOfMonth.plusMonths(1);

        // 월요일 (1 % 7 = 1), ..., 일요일 (7 % 7 = 0)
        int offsetWeekDays = firstDayOfMonth.getDayOfWeek().getValue() % 7;

        System.out.println("Su Mo Tu We Th Fr Sa");

        for(int i = 0; i < offsetWeekDays; i++) {
            System.out.print("   ");
        }

        LocalDate dayIterator = firstDayOfMonth;

        while(dayIterator.isBefore(firstDayOfnNextMonth)) {
            System.out.printf("%2d ", dayIterator.getDayOfMonth());
            if(dayIterator.getDayOfWeek() == DayOfWeek.SATURDAY) {
                System.out.println();
            }

            dayIterator = dayIterator.plusDays(1);
        }
    }
}
```
   - 실행 결과
```
년도를 입력하세요: 2024
월을 입력하세요: 1
Su Mo Tu We Th Fr Sa
    1  2  3  4  5  6
 7  8  9 10 11 12 13
14 15 16 17 18 19 20
21 22 23 24 25 26 27
28 29 30 31
```
```
년도를 입력하세요: 2025
월을 입력하세요: 1
Su Mo Tu We Th Fr Sa
          1  2  3  4 
 5  6  7  8  9 10 11
12 13 14 15 16 17 18
19 20 21 22 23 24 25
26 27 28 29 30 31 
```
