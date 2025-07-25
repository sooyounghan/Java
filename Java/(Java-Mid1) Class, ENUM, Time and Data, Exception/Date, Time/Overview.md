-----
### 날짜와 시간 라이브러리가 필요한 이유
-----
1. 날짜와 시간을 계산하는 것은 단순하게 생각하면 쉬워보이지만, 실제로는 매우 어렵고 복잡함
2. 날짜와 시간 차이 계산
   - 특정 날짜에서 다른 날짜까지의 정확한 일수를 계산하는 것은 생각보다 복잡함
   - 윤년, 각 달의 일수 등을 모두 고려해야 하며, 간단한 뺄셈 연산으로는 정확한 결과를 얻기 어려움
   - 예시) 2024년 1월 1일에서 2024년 2월 1일까지의 계산은 1월이 31일 까지라는 점을 고려해야 하며, 각각의 월 마다 날짜가 다름

3. 윤년 계산
   - 지구가 태양을 한 바퀴 도는 데 걸리는 평균 시간은 대략 365.2425일, 즉 365일 5시간 48분 45초 정도
   - 그레고리력(현재 대부분의 세계가 사용하는 달력)은 1년이 보통 365일로 설정
   - 따라서 둘의 시간이 정확히 맞지 않는데, 이런 문제를 해결하기 위해 4년마다 하루(2월 29일)를 추가하는 윤년(leap year)을 도입
   - 쉽게 이야기해서 2월은 보통 2월 28일까지 있는데, 4년마다 한번은 2월이 29일까지 하루 더 있음
   - 윤년 계산은 간단해 보이지만 실제로는 매우 복잡함 : 윤년은 보통 4년마다 한 번씩 발생하지만, 100년 단위일 때는 윤년이 아니며, 400년 단위일 때는 다시 윤년
   - 이 규칙에 따라, 2000년과 2020년은 윤년이지만, 1900년과 2100년은 윤년이 아님 : 이러한 규칙을 사용함으로써 달력 연도는 태양 연도에 매우 가깝게 유지될 수 있음
   - 예시) 2024년 1월 1일에서 2024년 3월 1일까지 계산은 2024년이 윤년으로 2월이 29일 까지라는 점을 고려해야 함

3. 일광 절약 시간(Daylight Saving Time, DST) 변환
   - 보통 3월에서 10월은 태양이 일찍 뜨고, 나머지는 태양이 상대적으로 늦게 뜸
   - 시간도 여기에 맞추어 1시간 앞당기거나 늦추는 제도를 일광 절약 시간제 또는 썸머타임
   - 일광 절약 시간은 국가나 지역에 따라 적용 여부와 시작 및 종료 날짜가 다름
   - 이로 인해 날짜와 시간 계산 시 1시간의 오차가 발생할 수 있으며, 이를 정확히 계산하는 것은 복잡함
   - 줄여서 DST는 각 나라마다 다르지만 보통 3월 중순 ~ 11월 초 정도까지 시행
   - 참고로 대한민국에서는 1988년 이후로는 시행하지 않음
   - 예시) 특정 지역에서는 3월의 마지막 일요일에 DST가 시작되어 10월의 마지막 일요일에 종료 : 이 기간 동안 발생하는 모든 날짜와 시간 계산은 1시간을 추가하거나 빼는 로직을 포함해야 함

4. 타임존 계산
   - 세계는 다양한 타임존으로 나뉘어 있으며, 각 타임존은 UTC(협정 세계시)로부터의 시간 차이로 정의
   - 타임존 간의 날짜와 시간 변환을 정확히 계산하는 것은 복잡함
   - 타임존 목록
      + Europe/London
      + GMT
      + UTC
      + US/Arizona -07:00
      + America/New_York -05:00
      + Asia/Seoul +09:00
      + Asia/Dubai +04:00
      + Asia/Istanbul +03:00
      + Asia/Shanghai +08:00
      + Europe/Paris +01:00
      + Europe/Berlin +01:00
      + GMT, UTC
    - London/ UTC / GMT는 세계 시간의 기준이 되는 00:00 시간대
    - GMT (그리니치 평균시, Greenwich Mean Time)
      + 처음 세계 시간을 만들 때 영국 런던에 있는 그리니치 천문대를 기준 : 태양이 그리니치 천문대를 통과할 때를 정오로 함
    - UTC(협정 세계시, Universal Time Coordinated)
      + 역사적으로 GMT가 국제적인 시간 표준으로 사용되었고, UTC가 나중에 이를 대체하기 위해 도입
      + 둘 다 경도 0°에 위치한 영국의 그리니치 천문대를 기준으로 하며, 이로 인해 실질적으로 같은 시간대를 나타냄
      + 그러나 두 시간 체계는 시간을 정의하고 유지하는 방법에서 차이가 있음
      + UTC는 원자 시계를 사용하여 측정한 국제적으로 합의된 시간 체계
      + 지구의 자전 속도가 변화하는 것을 고려하여 윤초를 추가하거나 빼는 방식으로 시간을 조정함으로써, 보다 정확한 시간을 유지
      + 우리가 일반적으로 사용할 때는 GMT와 UTC는 거의 차이가 없기 때문에 GMT와 UTC가 종종 같은 의미로 사용될 수 있지만, 정밀한 시간 측정과 국제적인 표준에 관해서는 UTC가 선호
    - 예시)
      + 상황 : 서울에 있는 사람이 독일 베를린에 있는 사람과 미팅을 계획중인데, 서울의 타임존은 Asia/Seoul, UTC+9에 위치해 있고, 베를린의 타임존은 Europe/Berlin, UTC+1에 위치해 있음
      + 서울에서 오후 9:00에 미팅을 하려면 베를린에서는 타임존 차이는 서울(UTC+9)와 베를린(UTC+1) 사이의 8시간 : 이는 서울의 시간이 베를린의 시간보다 8시간 더 앞서있다는 것을 의미
      + 계산 : 서울 시간은 오후 9시이며, 베를린 시간은 오후 9시 - 8시간 = 오후 1시

5. 주의할 점
   - 일광 절약 시간(DST) : 일광 절약 시간이 적용되는 경우, 타임존 차이가 변할 수 있음
      + 예를 들어, 베를린에서 DST가 적용되면 UTC+1 UTC+2가 되어, 타임존 차이는 7시간으로 줄어듬
      + 예를 들어, 베를린의 경우 3월 마지막 일요일에서 10월 마지막 일요일까지 DST가 적용됨
      + 이러한 복잡성 때문에 대부분의 현대 개발 환경에서는 날짜와 시간을 처리하기 위해 잘 설계된 라이브러리를 사용해야 함
      + 이러한 라이브러리는 위에서 언급한 복잡한 계산을 추상화하여 제공하므로, 개발자는 보다 안정적이고 정확하며 효율적인 코드를 작성할 수 있음

6. 자바 날짜와 시간 라이브러리의 역사
   - 자바는 날짜와 시간 라이브러리를 지속해서 업데이트
   - JDK 1.0 (java.util.Date)
      + 문제점
        * 타임존 처리 부족 : 초기 Date 클래스는 타임존(time zone)을 제대로 처리하지 못함
        * 불편한 날짜 시간 연산 : 날짜 간 연산이나 시간의 증감 등을 처리하기 어려웠음
        * 불변 객체 부재 : Date 객체는 변경 가능(mutable)하여, 데이터가 쉽게 변경될 수 있었고 이로 인해 버그가 발생하기 쉬웠음
      + 해결책
        * JDK 1.1에서 java.util.Calendar 클래스 도입으로 타임존 지원 개선
        * 날짜 시간 연산을 위한 추가 메소드 제공
   - JDK 1.1 (java.util.Calendar)
      + 문제점
        * 사용성 저하 : Calendar는 사용하기 복잡하고 직관적이지 않았음
        * 성능 문제 : 일부 사용 사례에서 성능이 저하되는 문제가 있었음
        * 불변 객체 부재 : Calendar 객체도 변경 가능하여, 사이드 이펙트, 스레드 안전성 문제가 있었음
      + 해결책
        * Joda-Time 오픈소스 라이브러리의 도입으로 사용성, 성능, 불변성 문제 해결
        * Joda-Time 문제점 - 표준 라이브러리가 아닌, 외부 라이브러리로, 자바 표준에 포함되지 않아 프로젝트에 별도로 추가해야 했음
        * 따라서, 자바 8에서 java.time 패키지(JSR-310)를 표준 API로 도입
   - JDK 8(1.8) (java.time 패키지)
      + java.time 패키지는 이전 API의 문제점을 해결하면서, 사용성, 성능, 스레드 안전성, 타임존 처리 등에서 크게 개선됨
      + 변경 불가능한 불변 객체 설계로 사이드 이펙트, 스레드 안전성 보장, 보다 직관적인 API 제공으로 날짜와 시간 연산을 단순화
      + LocalDate, LocalTime, LocalDateTime, ZonedDateTime, Instant 등의 클래스를 포함
      + Joda-Time의 많은 기능을 표준 자바 플랫폼으로 가져옴

7. 참고
   - 자바가 표준으로 제공했던 Date, Calendar 는 사용성이 너무 떨어지고, 문제가 많은 라이브러리
   - 이런 문제를 해결하기 위해 Joda-Time이라는 오픈소스 라이브러리가 등장
   - Joda-Time의 편리함과 사용성 덕분에 이 라이브러리는 크게 대중화 됨
   - 자바는 기존 날짜와 시간의 설계 대신 JSR-310(java.time)이라는 새로운 자바 표준 날짜와 시간 라이브러리를 정의
   - 실용적인 Joda-Time에 많은 자바 커뮤니티의 의견을 반영해서 좀 더 안정적이고 표준적인 날짜와 시간 라이브러리인 java.time 패키지가 성공적으로 완성
   - java.time는 자바의 메인 표준 기술로 완전히 자리 잡음
