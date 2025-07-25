-----
### 기본 날짜와 시간 - LocalDateTime
-----
1. 가장 기본이 되는 날짜와 시간 클래스 : LocalDate , LocalTime , LocalDateTime
   - LocalDate : 날짜만 표현할 때 사용 / 년, 월, 일을 다룸 (예) ```2013-11-21```)
   - LocalTime : 시간만을 표현할 때 사용 / 시, 분, 초를 다룸 (예) ```08:20:30.213```)
      + 초는 밀리초, 나노초 단위도 포함할 수 있음
   - LocalDateTime : LocalDate와 LocalTime을 합한 개념 (예) ```2013-11-21T08:20:30.213```)
      + 앞에 Local(현지의, 특정 지역의)이 붙는 이유 : 세계 시간대를 고려하지 않아서 타임존이 적용되지 않기 때문임
   - 특정 지역의 날짜와 시간만 고려할 때 사용
   - 예시) 애플리케이션 개발시 국내 서비스만 고려할 때 나의 생일은 2016년 8월 16일
  
2. LocalDate (/time)
```java
package time;

import java.time.LocalDate;

public class LocalDateMain {
    public static void main(String[] args) {
        LocalDate nowDate = LocalDate.now();
        LocalDate ofDate = LocalDate.of(2013, 11, 21);

        System.out.println("오늘 날짜 = " + nowDate);
        System.out.println("지정 날짜 = " + ofDate);
        
        // 계산 (불변)
        LocalDate plusDays = ofDate.plusDays(10);
        System.out.println("지정 날짜 + 10d = " + plusDays);
    }
}
```
   - 실행 결과
```
오늘 날짜 = 2025-07-24
지정 날짜 = 2013-11-21
지정 날짜 + 10d = 2013-12-01
```

  - 생성
    + now() : 현재 시간을 기준으로 생성
    + of(...) : 특정 날짜를 기준으로 생성 (년, 월, 일을 입력할 수 있음)

  - 계산
    + plusDays() : 특정 일을 더함 (다양한 plusXxx() 메서드가 존재)
  
  - 💡 주의 : 불변
    + 모든 날짜 클래스는 불변
    + 따라서 변경이 발생하는 경우 새로운 객체를 생성해서 반환하므로 반환값을 꼭 받아야 함

3. LocalTime
```java
package time;

import java.time.LocalTime;

public class LocalTimeMain {
    public static void main(String[] args) {
        LocalTime nowTime = LocalTime.now();
        LocalTime ofTime = LocalTime.of(9, 10, 30);

        System.out.println("현재 시간 = " + nowTime);
        System.out.println("지정 시간 = " + ofTime);
        
        // 계산 (불변)
        LocalTime ofTimePlus = ofTime.plusSeconds(30);
        System.out.println("지정 시간 + 30s = " + ofTimePlus);
    }
}
```
   - 실행 결과
```
현재 시간 = 16:29:10.116314900
지정 시간 = 09:10:30
지정 시간 + 30s = 09:11
```

  - 생성  
    + now() : 현재 시간을 기준으로 생성
    + of(...) : 특정 시간을 기준으로 생성 (시, 분, 초, 나노초를 입력할 수 있음)

  - 계산
    + plusSeconds() : 특정 초를 더함 (다양한 plusXxx() 메서드가 존재)

  - 주의 : 불변
    + 모든 날짜 클래스는 불변
    + 따라서 변경이 발생하는 경우 새로운 객체를 생성해서 반환하므로 반환값을 꼭 받아야 함

4. LocalDateTime
   - LocalDateTime은 LocalDate와 LocalTime을 내부에 가지고 날짜와 시간을 모두 표현
   - LocalDateTime 클래스
```java
public class LocalDateTime {
     private final LocalDate date;
     private final LocalTime time;
     ...
}
```
   - LocalDateTimeMain
```java
package time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class LocalDateTimeMain {
    public static void main(String[] args) {
        LocalDateTime nowDt = LocalDateTime.now();
        LocalDateTime ofDt = LocalDateTime.of(2016, 8, 16, 8, 10, 1);

        System.out.println("현재 날짜 시간 = " + nowDt);
        System.out.println("지정 날짜 시간 = " + ofDt);
        
        // 날짜와 시간 분리
        LocalDate localDate = ofDt.toLocalDate();
        LocalTime localTime = ofDt.toLocalTime();

        System.out.println("localDate = " + localDate);
        System.out.println("localTime = " + localTime);
        
        // 날짜와 시간 합체
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
        System.out.println("localDateTime = " + localDateTime);
        
        // 계산 (불변)
        LocalDateTime ofDtPlus = ofDt.plusDays(1000);
        System.out.println("지정 날짜 시간 + 1000d = " + ofDtPlus);

        LocalDateTime ofDtPlus1Year = ofDt.plusYears(1);
        System.out.println("지정 날짜 시간 + 1년 = " + ofDtPlus1Year);
        
        // 비교
        System.out.println("현재 날짜 시간이 지정 날짜 시간보다 이전인가? " + nowDt.isBefore(ofDt));
        System.out.println("현재 날짜 시간이 지정 날짜 시간보다 이후인가? " + nowDt.isAfter(ofDt));
        System.out.println("현재 날짜 시간과 지정 날짜 시간이 같은가? " + nowDt.isEqual(ofDt));
    }
}
```
   - 실행 결과
```
현재 날짜 시간 = 2025-07-24T16:35:14.662766600
지정 날짜 시간 = 2016-08-16T08:10:01

localDate = 2016-08-16
localTime = 08:10:01
localDateTime = 2016-08-16T08:10:01

지정 날짜 시간 + 1000d = 2019-05-13T08:10:01
지정 날짜 시간 + 1년 = 2017-08-16T08:10:01

현재 날짜 시간이 지정 날짜 시간보다 이전인가? false
현재 날짜 시간이 지정 날짜 시간보다 이후인가? true
현재 날짜 시간과 지정 날짜 시간이 같은가? false
```

   - 생성
      + now() : 현재 날짜와 시간을 기준으로 생성
      + of(...) : 특정 날짜와 시간을 기준으로 생성
  - 분리 : 날짜(LocalDate)와 시간(LocalTim )을 toXxx() 메서드로 분리할 수 있음
  - 합체
      + LocalDateTime.of(localDate, localTime) : 날짜와 시간을 사용해서 LocalDateTime 생성
  - 계산
      + plusXxx() : 특정 날짜와 시간을 더함 (다양한 plusXxx() 메서드가 존재)
  - 비교
      + isBefore() : 다른 날짜시간과 비교 - 현재 날짜와 시간이 이전이라면 true를 반환
      + isAfter() : 다른 날짜시간과 비교 - 현재 날짜와 시간이 이후라면 true를 반환
      + isEqual() : 다른 날짜시간과 시간적으로 동일한지 비교 - 시간이 같으면 true를 반환

  - isEqual() vs equals()
      + isEqual() : 단순히 비교 대상이 시간적으로 같으면 true를 반환 : 객체가 다르고, 타임존이 달라도 시간적으로 같으면 true 반환 (쉽게 이야기해서 시간을 계산해서 시간으로만 둘을 비교)
        * 예) 서울의 9시와 UTC의 0시는 시간적으로 같으므로, 이 둘을 비교하면 true 반환
      + equals() : 객체의 타입, 타임존 등등 내부 데이터의 모든 구성요소가 같아야 true 반환
        * 예) 서울의 9시와 UTC의 0시는 시간적으로 같지만, 이 둘을 비교하면 타임존의 데이터가 다르기 때문에 false 반환
  
