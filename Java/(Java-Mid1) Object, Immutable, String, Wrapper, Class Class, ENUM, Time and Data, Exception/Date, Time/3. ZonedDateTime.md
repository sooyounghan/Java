-----
### 타임존 - ZonedDateTime
-----
1. "Asia/Seoul" 같은 타임존 안에는 일광 절약 시간제에 대한 정보와 UTC+9:00와 같은 UTC로 부터 시간 차이인 오프셋 정보를 모두 포함하고 있음
2. 타임존 목록 예시
   - Europe/London
   - GMT
   - UTC
   - US/Arizona -07:00
   - America/New_York -05:00
   - Asia/Seoul +09:00
   - Asia/Dubai +04:00
   - Asia/Istanbul +03:00
   - Asia/Shanghai +08:00
   - Europe/Paris +01:00
   - Europe/Berlin +01:00
  
3. ZoneId : 자바는 타임존을 ZoneId 클래스로 제공
  - ZoneIdMain
```java
package time;

import java.time.ZoneId;

public class ZoneIdMain {
    public static void main(String[] args) {
        for (String availableZoneId : ZoneId.getAvailableZoneIds()) {
            ZoneId zoneId = ZoneId.of(availableZoneId);
            System.out.println(zoneId + " | " + zoneId.getRules());
        }

        ZoneId zoneId = ZoneId.systemDefault();
        System.out.println("ZoneId.systemDefault() = " + zoneId);

        ZoneId seoulZoneId = ZoneId.of("Asia/Seoul");
        System.out.println("seoulZoneId = " + seoulZoneId);
    }
}
```
  - 실행 결과
```
Europe/London | ZoneRules[currentStandardOffset=Z]
UTC | ZoneRules[currentStandardOffset=Z]
GMT | ZoneRules[currentStandardOffset=Z]
Asia/Seoul | ZoneRules[currentStandardOffset=+09:00]
Asia/Dubai | ZoneRules[currentStandardOffset=+04:00]
US/Arizona | ZoneRules[currentStandardOffset=-07:00]
Asia/Istanbul | ZoneRules[currentStandardOffset=+03:00]
Asia/Shanghai | ZoneRules[currentStandardOffset=+08:00]
...
Europe/Paris | ZoneRules[currentStandardOffset=+01:00]

ZoneId.systemDefault = Asia/Seoul
seoulZoneId = Asia/Seoul
```

  - 생성
    + ZoneId.systemDefault() : 시스템이 사용하는 기본 ZoneId 반환
    + 각 PC 환경 마다 다른 결과가 나올 수 있음
    + ZoneId.of() : 타임존을 직접 제공해서 ZoneId 반환
    + ZoneId는 내부에 일광 절약 시간 관련 정보, UTC와의 오프셋 정보를 포함하고 있음

4. ZonedDateTime
   - LocalDateTime에 시간대 정보인 ZoneId가 합쳐진 것
   - ZonedDateTime 클래스
```java
public class ZonedDateTime {
     private final LocalDateTime dateTime;
     private final ZoneOffset offset;
     private final ZoneId zone;
}
```
   - ZonedDateTime : 시간대를 고려한 날짜와 시간을 표현할 때 사용 (여기에는 시간대를 표현하는 타임존이 포함)
      + 예) 2013-11-21T08:20:30.213+9:00[Asia/Seoul]
      + +9:00은 UTC(협정 세계시)로 부터의 시간대 차이 : 오프셋 (한국은 UTC보다 +9:00 시간)
      + Asia/Seoul은 타임존 : 이 타임존을 알면 오프셋도 알 수 있음 (+9:00 같은 오프셋 정보도 타임존에 포함)
      + 추가로 ZoneId를 통해 타임존을 알면 일광 절약 시간제에 대한 정보도 알 수 있으며, 따라서 일광 절약 시간제가 적용

   - ZoneDateTimeMain
```java
package time;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ZoneDateTimeMain {
    public static void main(String[] args) {
        ZonedDateTime nowZdt = ZonedDateTime.now();
        System.out.println("nowZdt = " + nowZdt);

        LocalDateTime ldt = LocalDateTime.of(2030, 1, 1, 13, 30, 50);
        ZonedDateTime zdt1 = ZonedDateTime.of(ldt, ZoneId.of("Asia/Seoul"));
        System.out.println("zdt1 = " + zdt1);

        ZonedDateTime zdt2 = ZonedDateTime.of(2030, 1, 1, 13, 30, 50, 0, ZoneId.of("Asia/Seoul"));
        System.out.println("zdt2 = " + zdt2);

        ZonedDateTime utcZdt = zdt2.withZoneSameInstant(ZoneId.of("UTC"));
        System.out.println("utcZdt = " + utcZdt);
    }
}
```
   - 실행 결과
```
nowZdt = 2025-07-24T16:48:08.787415300+09:00[Asia/Seoul]
zdt1 = 2030-01-01T13:30:50+09:00[Asia/Seoul]
zdt2 = 2030-01-01T13:30:50+09:00[Asia/Seoul]
utcZdt = 2030-01-01T04:30:50Z[UTC]
```

  - 생성
      + now() : 현재 날짜와 시간을 기준으로 생성 (이때 ZoneId는 현재 시스템을 따름) (ZoneId.systemDefault())
      + of(...) : 특정 날짜와 시간을 기준으로 생성 (ZoneId를 추가해야 함)
        * LocalDateTime에 ZoneId를 추가해서 생성할 수 있음

   - 타임존 변경 : withZoneSameInstant(ZoneId)
     + 타임존에 맞추어 시간도 함께 변경
     + 이 메서드를 사용하면 지금 다른 나라는 몇 시 인지 확인일 수 있음
     + 예를 들어서 서울이 지금 9시라면, UTC 타임존으로 변경하면 0시를 확인할 수 있음

5. OffsetDateTime
   - LocalDateTime에 UTC 오프셋 정보인 ZoneOffset이 합쳐진 것
   - OffsetDateTime 클래스
```java
public class OffsetDateTime {
     private final LocalDateTime dateTime;
     private final ZoneOffset offset;
}
```
   - 시간대를 고려한 날짜와 시간을 표현할 때 사용
   - 여기에는 타임존은 없고, UTC로 부터의 시간대 차이인 고정된 오프셋만 포함 (예) 2013-11-21T08:20:30.213+9:00)
      + ZoneId 가 없으므로 일광 절약 시간제가 적용되지 않음
   - OffsetDateTimeMain
```java
package time;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class OffsetDateTimeMain {
    public static void main(String[] args) {
        OffsetDateTime nowOdt = OffsetDateTime.now();
        System.out.println("nowOdt = " + nowOdt);

        LocalDateTime ldt = LocalDateTime.of(2030, 1, 1, 13, 30, 50);
        System.out.println("ldt = " + ldt);

        OffsetDateTime odt = OffsetDateTime.of(ldt, ZoneOffset.of("+01:00"));
        System.out.println("odt = " + odt);
    }
}
```
   - 실행 결과
```
nowOdt = 2025-07-24T16:52:37.205112400+09:00
ldt = 2030-01-01T13:30:50
odt = 2030-01-01T13:30:50+01:00
```
  - ZoneOffset은 +01:00 처럼 UTC와의 시간 차이인 오프셋 정보만 보관

6. ZonedDateTime vs OffsetDateTime
   - ZonedDateTime : 구체적인 지역 시간대를 다룰 때 사용
     + 일광 절약 시간을 자동으로 처리할 수 있음
     + 사용자 지정 시간대에 따른 시간 계산이 필요할 때 적합

   - OffsetDateTime : UTC와의 시간 차이만을 나타낼 때 사용
     + 지역 시간대의 복잡성을 고려하지 않음
     + 시간대 변환 없이 로그를 기록하고, 데이터를 저장하고 처리할 때 적합

7. 참고
   - ZonedDateTime 이나 OffsetDateTime은 글로벌 서비스를 하지 않으면 잘 사용하지 않음
