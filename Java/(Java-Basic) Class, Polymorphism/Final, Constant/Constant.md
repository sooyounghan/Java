-----
### final 변수와 상수 2
-----
1. 상수(Constant)
   - 상수는 변하지 않고, 항상 일정한 값을 갖는 수
   - 자바에서는 보통 단 하나만 존재하는 변하지 않는 고정된 값을 상수라 함
   - 이런 이유로 상수는 static final 키워드를 사용

2. 자바 상수 특징
   - static final 키워드를 사용
   - 대문자를 사용하고 구분은 _ (언더스코어)로 사용 (관례)
     + 일반적인 변수와 상수를 구분하기 위해 사용
   - 필드를 직접 접근해서 사용
     + 상수는 기능이 아니라 고정된 값 자체를 사용하는 것이 목적
   - 상수는 값을 변경할 수 없으므로, 따라서 필드에 직접 접근해도 데이터가 변하는 문제가 발생하지 않음

3. Constant
```java
package final1;

// 상수
public class Constant {
    // 수학 상수
    public static final double PI = 3.14;
    
    // 시간 상수
    public static final int HOURS_IN_DAY = 24;
    public static final int MINUTES_IN_HOUR = 60;
    public static final int SECONDS_IN_MINUTE = 60;
    
    // 애플리케이션 설정 상수
    public static final int MAX_USERS = 1000;
}
```
   - 애플리케이션 안에는 다양한 상수가 존재할 수 있음
   - 수학, 시간 등등 실생활에서 사용하는 상수부터, 애플리케이션의 다양한 설정을 위한 상수들도 있음
   - 보통 이런 상수들은 애플리케이션 전반에서 사용되기 때문에 public를 자주 사용 : 물론 특정 위치에서만 사용된다면 다른 접근 제어자를 사용하면 됨
   - 상수는 중앙에서 값을 하나로 관리할 수 있다는 장점도 있음
   - 상수는 런타임에 변경할 수 없음
     + 상수를 변경하려면 프로그램을 종료하고, 코드를 변경한 다음에 프로그램을 다시 실행해야 함
     + 추가로 상수는 중앙에서 값을 하나로 관리할 수 있다는 장점도 있음

4. ConstantMain1 - 상수 없음
```java
package final1;

public class ConstantMain1 {
    public static void main(String[] args) {
        System.out.println("프로그램 최대 참여자 수 : " + 1000);
        int currentUserCount = 999;

        process(currentUserCount++);
        process(currentUserCount++);
        process(currentUserCount++);
    }

    private static void process(int currentUserCount) {
        System.out.println("참여자 수 : " + currentUserCount);

        if(currentUserCount > 1000) {
            System.out.println("대기자로 등록합니다.");
        } else {
            System.out.println("게임에 참가합니다.");
        }
    }
}
```
  - 코드에는 다음과 같은 문제가 있음
  - 만약 프로그램 최대 참여자 수를 현재 1000명에서 2000명으로 변경해야 하면 2곳의 변경 포인트가 발생
  - 만약 애플리케이션의 100곳에서 이 숫자를 사용했다면 100곳을 모두 변경해야 함
  - 매직 넘버 문제 발생 : 이 값만 보고 이해하기 어려움
  - 실행 결과
```
프로그램 최대 참여자 수 : 1000
참여자 수 : 999
게임에 참가합니다.
참여자 수 : 1000
게임에 참가합니다.
참여자 수 : 1001
대기자로 등록합니다.
```

4. ConstantMain2 - 상수 사용
```java
package final1;

public class ConstantMain2 {
    public static void main(String[] args) {
        System.out.println("프로그램 최대 참여자 수 : " + Constant.MAX_USERS);
        int currentUserCount = 999;

        process(currentUserCount++);
        process(currentUserCount++);
        process(currentUserCount++);
    }

    private static void process(int currentUserCount) {
        System.out.println("참여자 수 : " + currentUserCount);

        if(currentUserCount > Constant.MAX_USERS) {
            System.out.println("대기자로 등록합니다.");
        } else {
            System.out.println("게임에 참가합니다.");
        }
    }
}
```
   - Constant.MAX_USERS 상수를 사용
     + 만약 프로그램 최대 참여자 수를 변경해야 하면 Constant.MAX_USERS 의 상수 값만 변경하면 됨
   - 매직 넘버 문제를 해결
     + 숫자 1000이 아니라 사람이 인지할 수 있게 MAX_USERS 라는 변수명으로 코드로 이해할 수 있음
     
