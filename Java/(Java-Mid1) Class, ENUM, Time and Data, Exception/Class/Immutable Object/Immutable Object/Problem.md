-----
### 문제와 풀이
-----
1. 문제 설명
   - MyDate 클래스는 불변이 아니어서 공유 참조시 사이드 이펙트가 발생하므로, 이를 불변 클래스로 변경
   - 새로운 불변 클래스는 ImmutableMyDate  
   - 새로운 실행 클래스는 ImmutableMyDateMain
   - MyDate (/lang/immutable/test)
```java
package lang.immutable.test;

public class MyDate {
    private int year;
    private int month;
    private int day;

    public MyDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setDay(int day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return year + "-" + month + "-" + day;
    }
}
```
   - MyDateMain
```java
package lang.immutable.test;

public class MyDateMain {
    public static void main(String[] args) {
        MyDate date1 = new MyDate(2024, 1, 1);
        MyDate date2 = date1;

        System.out.println("date1 = " + date1);
        System.out.println("date2 = " + date2);

        System.out.println("2025 -> date1");
        date1.setYear(2025);
        System.out.println("date1 = " + date1);
        System.out.println("date2 = " + date2);
    }
}
```

   - 실행 결과
```
date1 = 2024-1-1
date2 = 2024-1-1
2025 -> date1
date1 = 2025-1-1
date2 = 2025-1-1
```

  - ImmutableMyDate
```java
package lang.immutable.test;

public class ImmutableMyDate {
    private final int year;
    private final int month;
    private final int day;

    public ImmutableMyDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }
    
    public ImmutableMyDate withYear(int newYear) {
        return new ImmutableMyDate(newYear, month, day);
    }
    
    public ImmutableMyDate withMonth(int newMonth) {
        return new ImmutableMyDate(year, newMonth, day);
    }
    
    public ImmutableMyDate withDay(int newDay) {
        return new ImmutableMyDate(year, month, newDay);
    }

    @Override
    public String toString() {
        return year + "-" + month + "-" + day;
    }
}
```
   - ImmutableMyDateMain
```java
package lang.immutable.test;

public class ImmutableMyDateMain {
    public static void main(String[] args) {
        ImmutableMyDate date1 = new ImmutableMyDate(2024, 1, 1);
        ImmutableMyDate date2 = date1;

        System.out.println("date1 = " + date1);
        System.out.println("date2 = " + date2);

        System.out.println("2025 -> date1");
        date1 = date1.withYear(2025);
        System.out.println("date1 = " + date1);
        System.out.println("date2 = " + date2);
    }
}
```
   - 실행 결과
```
date1 = 2024-1-1
date2 = 2024-1-1
2025 -> date1
date1 = 2025-1-1
date2 = 2024-1-1
```

2. 참고 - withXxx()
   - 불변 객체에서 값을 변경하는 경우 withYear() 처럼 "with"로 시작하는 경우가 많음
   - 예를 들어 "coffee with sugar"라고 하면, 커피에 설탕이 추가되어 원래의 상태를 변경하여 새로운 변형을 만든다는 것을 의미
   - 이 개념을 프로그래밍에 적용하면, 불변 객체의 메서드가 "with"로 이름 지어진 경우, 그 메서드가 지정된 수정사항을 포함하는 객체의 새 인스턴스를 반환한다는 사실을 뜻함
   - 정리하면 "with"는 관례처럼 사용되는데, 원본 객체의 상태가 그대로 유지됨을 강조하면서 변경사항을 새 복사본에 포함하는 과정을 간결하게 표현
