-----
### 기계 중심의 시간 - Instant
-----
1. Instant는 UTC(협정 세계시)를 기준으로 하는, 시간의 한 지점을 나타냄
   + 날짜와 시간을 나노초 정밀도로 표현
   + 1970년 1월 1일 0시 0분 0초(UTC 기준)를 기준으로 경과한 시간으로 계산

2. 쉽게 이야기해서 Instant 내부에는 초 데이터만 들어있음 (나노초 포함)
3. 따라서 날짜와 시간을 계산에 사용할 때는 적합하지 않음
4. Instant 클래스
```java
public class Instant {
     private final long seconds;
     private final int nanos;
     ...
}
```
   - UTC 기준 1970년 1월 1일 0시 0분 0초 : seconds에 0이 들어감
   - UTC 기준 1970년 1월 1일 0시 0분 10초 : seconds에 10이 들어감
   - UTC 기준 1970년 1월 1일 0시 1분 0초 : seconds에 60이 들어감

5. 참고 - Epoch 시간
   - Epoch time(에포크 시간), 또는 Unix timestamp는 컴퓨터 시스템에서 시간을 나타내는 방법 중 하나
   - 이는 ```1970년 1월 1일 00:00:00 UTC```부터 현재까지 경과된 시간을 초 단위로 표현한 것
   - 즉, Unix 시간은 1970년 1월 1일 이후로 경과한 전체 초의 수로, 시간대에 영향을 받지 않는 절대적인 시간 표현 방식
   - 참고로 Epoch라는 뜻은 어떤 중요한 사건이 발생한 시점을 기준으로 삼는 시작점을 뜻하는 용어
   - Instant : Epoch 시간을 다루는 클래스

6. Instant 특징
   - 장점
      + 시간대 독립성: Instant는 UTC를 기준으로 하므로, 시간대에 영향을 받지 않으며, 이는 전 세계 어디서나 동일한 시점을 가리키는데 유용
      + 고정된 기준점: 모든 Instant는 1970년 1월 1일 UTC를 기준으로 하기 때문에, 시간 계산 및 비교가 명확하고 일관됨
   - 단점
      + 사용자 친화적이지 않음 : Instant 는 기계적인 시간 처리에는 적합하지만, 사람이 읽고 이해하기에는 직관적이지 않음 (예를 들어, 날짜와 시간을 계산하고 사용하는데 필요한 기능이 부족)
      + 시간대 정보 부재 : Instant에는 시간대 정보가 포함되어 있지 않아, 특정 지역의 날짜와 시간으로 변환하려면 추가적인 작업이 필요

7. 사용 예
   - 전 세계적인 시간 기준 필요 시 : Instant 는 UTC를 기준으로 하므로, 전 세계적으로 일관된 시점을 표현할 때 사용하기 좋음
      + 예를 들어, 로그 기록이나, 트랜잭션 타임스탬프, 서버 간의 시간 동기화 등이 이에 해당
   - 시간대 변환 없이 시간 계산 필요 시 : 시간대의 변화 없이 순수하게 시간의 흐름(예: 지속 시간 계산)만을 다루고 싶을 때 Instant가 적합
     + 이는 시간대 변환의 복잡성 없이 시간 계산을 할 수 있게 해줌
   - 데이터 저장 및 교환 : 데이터베이스에 날짜와 시간 정보를 저장하거나, 다른 시스템과 날짜와 시간 정보를 교환할 때 Instant를 사용하면, 모든 시스템에서 동일한 기준점(UTC)을 사용하게 되므로 데이터의 일관성을 유지하기 쉬움
     + 일반적으로 날짜와 시간을 사용할 때는 LocalDateTime, ZonedDateTime 등을 사용하면 됨
     + Instant는 날짜를 계산하기 어렵기 때문에 앞서 사용 예와 같은 특별한 경우에 한정해서 사용하면 됨

8. InstantMain
```java
package time;

import java.time.Instant;
import java.time.ZonedDateTime;

public class InstantMain {
    public static void main(String[] args) {
        // 생성
        Instant now = Instant.now(); // UTC 기준
        System.out.println("now = " + now);

        ZonedDateTime zdt = ZonedDateTime.now();
        Instant from = Instant.from(zdt);
        System.out.println("from = " + from);

        Instant epochStart = Instant.ofEpochSecond(0);
        System.out.println("epochStart = " + epochStart);
        
        // 계산
        Instant later = epochStart.plusSeconds(3600);
        System.out.println("later = " + later);
        
        // 조회
        long laterEpochSecond = later.getEpochSecond();
        System.out.println("laterEpochSecond = " + laterEpochSecond);
    }
}
```
   - 실행 결과
```
now = 2025-07-24T08:03:10.681040300Z
from = 2025-07-24T08:03:10.701614600Z
epochStart = 1970-01-01T00:00:00Z
later = 1970-01-01T01:00:00Z
laterEpochSecond = 3600
```

  - 생성
    + now() : UTC를 기준 현재 시간의 Instant 생성
    + from() : 다른 타입의 날짜와 시간을 기준으로 Instant를 생성 (참고로 Instant는 UTC를 기준으로 하기 때문에 시간대 정보가 필요하므로, LocalDateTime 은 사용할 수 없음)
    + ofEpochSecond() : 에포크 시간을 기준으로 Instant를 생성 (0초를 선택하면 에포크 시간인 1970년 1월 1일 0시 0분 0초로 생성)

  - 계산
    + plusSeconds() : 초를 더함 (초, 밀리초, 나노초 정도만 더하는 간단한 메서드가 제공)

  - 조회
    + getEpochSecond() : 에포크 시간인 UTC 1970년 1월 1일 0시 0분 0초를 기준으로 흐른 초를 반환
    + 여기서는 앞서 에포크 시간에 3600초를 더했기 때문에 3600이 반환

9. Instant 정리
    - 조금 특별한 시간, 기계 중심, UTC 기준
    - 에포크 시간으로부터 흐른 시간을 초 단위로 저장
    - 전세계 모든 서버 시간을 똑같이 맞출 수 있음, 항상 UTC 기준이므로 한국에 있는 Instant, 미국에 있는 Instant 의 시간이 똑같음
    - 서버 로그, epoch 시간 기반 계산이 필요할 때, 간단히 두 시간의 차이를 구할 때 사용
    - 단점 : 초 단위의 간단한 연산 가능, 복잡한 연산 못함
    - 대안 : 날짜 계산 필요하면 LocalDateTime 또는, ZonedDateTime 사용
