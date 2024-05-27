-----
### Calendar 클래스
-----
1. 추상 클래스이기 때문에, 직접 객체를 생성할 수 없고, 메서드를 통해 완전히 구현된 클래스의 인스턴스를 얻어야 함
```java
Calendar cal = new Calendar(); // Error. 추상 클래스는 인스턴스를 생성 불가

Calendar cal = Calendar.getInstance(); // Ok. getInstance() 메서드는 Calendar 클래스를 구현한 클래스의 인스턴스 반환
```

2. Calendar를 상속받아 완전히 구현한 클래스로는 GregorianCalendar와 BuddhistCalendar가 존재
   - getInstance()는 시스템의 국가와 지역설정을 확인해 태국이면, BuddistCalendar 인스턴스 반환
   - 그 외에는 GregorianCalendar(오늘날 전세계 공통으로 사용하고 있는 그레고리력에 맞게 구현) 인스턴스 반환
   - 메서드를 통해 인스턴스를 반환하게 하는 이유는 최소한의 변경으로 프로그램이 동작할 수 있도록 하기 위함
```java
class MyApplication {
    public static void main(String[] args) {
        // Calendar cal = new GregorianCalendar; // 경우에 따라 이 부분을 변경해야 함
        Calendar cal = Calendar.getInstance();
    }
}
```

3. Calendar에 정의된 필드
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/9da1cbcd-87b5-4e27-ae44-ef9f3a6c8d52">
</div>

-----
### Date와 Calendar 간 변환
-----
1. Data는 대부분의 메서드가 'deprecated'되었으므로 잘 사용하지 않음
2. Date를 필요로 하는 메서드가 있기 때문에, Calendar를 Date로 또는 그 반대로 변환하는 경우 발생
  - Calendar를 Date로 변환
```java
Calendar cal = Calendar.getInstance();

...

Date d = new Date(cal.getTimeInMillis()); // Date(long date)
```

  - Date를 Calendar로 변환
```java
Date d = new Date();

...

Calendar cal = Calendar.getInstance();
cal.setTime(d);
```

```java
import java.util.*;

class  CalendarEx1 {
	public static void main(String[] args) 
	{      
		// 기본적으로 현재날짜와 시간으로 설정
		Calendar today = Calendar.getInstance();	
		System.out.println("이 해의 연도 : " + today.get(Calendar.YEAR));
		System.out.println("월(0~11, 0:1월) : "	+ today.get(Calendar.MONTH));
		System.out.println("이 해의 몇 째주 : "		+ today.get(Calendar.WEEK_OF_YEAR));
		System.out.println("이 달의 몇 째주 : "		+ today.get(Calendar.WEEK_OF_MONTH));
		// DATE와 DAY_OF_MONTH는 같음
		System.out.println("이 달의 몇 일 : "		+ today.get(Calendar.DATE));
		System.out.println("이 달의 몇 일 : "		+ today.get(Calendar.DAY_OF_MONTH));
		System.out.println("이 해의 몇일 : "		+ today.get(Calendar.DAY_OF_YEAR));
		System.out.println("요일(1~7, 1:일요일): " + today.get(Calendar.DAY_OF_WEEK)); // 1:일요일, 2:월요일, ... 7:토요일
		System.out.println("이 달의 몇 째 요일 : "	+ today.get(Calendar.DAY_OF_WEEK_IN_MONTH));
		System.out.println("오전_오후(0:오전, 1:오후): " + today.get(Calendar.AM_PM));
		System.out.println("시간(0~11): "	+ today.get(Calendar.HOUR));
		System.out.println("시간(0~23): "	+ today.get(Calendar.HOUR_OF_DAY));
		System.out.println("분(0~59): "	+ today.get(Calendar.MINUTE));
		System.out.println("초(0~59): "	+ today.get(Calendar.SECOND));
		System.out.println("1000분의 1초(0~999): " + today.get(Calendar.MILLISECOND));
        
		// 천분의 1초를 시간으로 표현하기 위해 3600000�으로 나눔. (1시간 = 60 * 60초)
		System.out.println("TimeZone(-12~+12): " + (today.get(Calendar.ZONE_OFFSET)/(60*60*1000))); 
		System.out.println("이 달의 마지막 날: " + today.getActualMaximum(Calendar.DATE) ); // 이 달의 마지막 일을 찾음
	}
}
```
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/05f3b651-8667-4e20-ae8d-f0cdbde2cf2d">
</div>

  - getInstance()를 통해 얻은 인스턴스는 기본적으로 현재 시스템의 날짜와 시간에 대한 정보를 가짐
  - 원하는 날짜와 시간을 설정하려면 set메서드 사용
  - get 메서드의 매개변수로 사용되는 int 값들은 Calendar에 정의된 static 상수값
  - get(Calendar.MONTH)로 얻어오는 값의 범위는 1 ~ 12가 아닌 0 ~ 11임

-----
### 예제
-----
```java
import java.util.*;

class  CalendarEx2 {
	public static void main(String[] args) {
		// 요일은 1부터 시작하기 때문에, DAY_OF_WEEK[0]은 비워둠
		final String[] DAY_OF_WEEK = {"", "일", "월", "화", "수", "목", "금", "토"};

		Calendar date1 = Calendar.getInstance();
		Calendar date2 = Calendar.getInstance();

		// month의 경우 0부터 시작하기 때문에 8월인 경우, 7로 지정
		// date1.set(2015, Calendar.AUGUST, 15);와 같이 가능
		date1.set(2015, 7, 15); // 2015년 8월 15일로 날짜를 설정 
		System.out.println("date1은 "+ toString(date1) + DAY_OF_WEEK[date1.get(Calendar.DAY_OF_WEEK)]+"요일 이고");
		System.out.println("오늘(date2)은 " + toString(date2) + DAY_OF_WEEK[date2.get(Calendar.DAY_OF_WEEK)]+"요일입니다.");

		// 두 날짜간 차이를 얻으려면, getTimeInMillis() 천분의 일초 단위로 변환해야함
    		long difference = (date2.getTimeInMillis() - date1.getTimeInMillis())/1000;
		System.out.println("그 날(date1)부터 지금(date2)까지 " + difference +"초가 지남");
		System.out.println("일(day)로 계산하면 " + difference/(24*60*60) +"일");	// 1일 = 24 * 60 * 60
	}

	public static String toString(Calendar date) {
		return date.get(Calendar.YEAR)+"년 "+ (date.get(Calendar.MONTH)+1) +"월 " + date.get(Calendar.DATE) + "일 ";
	}
}
```
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/07075875-39a7-40c4-a978-b1ee8eeea430">
</div>

1. 날짜와 시간을 원하는 값으로 변경하려면 set 메서드 이용
```java
void set(int field, int value)
void set(int year, int month, int date)
void set(int year, int month, int date, int hourOfDay, int minute)
void set(int year, int month, int date, int hourOfDay, int minute, int second)
```
  - clear()는 모든 필드의 값을, clear(int field)는 지정된 필드의 값을 기본값으로 초기화

2. 두 날짜간의 차이를 구하기 위해서는 두 날짜를 최소 단위인 초단위로 변경한 다음, 그 차이를 구하면 됨
   - getTimeInMillis()는 1/1000초 단위로 값을 반환하므로 초 단위를 얻기 위해서는 1000을 나눠야함
   - 일 단위로 얻기 위해서는 24(시간) * 60(분) * 60(초) * 1000으로 나눠야 함

3. 시간 상 전후를 알고 싶을 때는, 두 날짜간의 차이가 양수인지 음수인지 판단해야 함
```java
booelan after(Object when)
boolean before(Object when)
```

```java
import java.util.*;

class  CalendarEx3 {
	public static void main(String[] args) {
		final int[] TIME_UNIT = {3600, 60, 1}; // 큰 단위를 앞에 놓음
		final String[] TIME_UNIT_NAME = {"시간 ", "분 ", "초 "};

		Calendar time1 = Calendar.getInstance();
		Calendar time2 = Calendar.getInstance();

		// time1을 10시 20분 30초로 설정
		time1.set(Calendar.HOUR_OF_DAY, 10);
		time1.set(Calendar.MINUTE, 20);
		time1.set(Calendar.SECOND, 30);

		// time2을 20시 30분 10초로 설정
		time2.set(Calendar.HOUR_OF_DAY, 20);
		time2.set(Calendar.MINUTE, 30);
		time2.set(Calendar.SECOND, 10);

		System.out.println("time1 :"+time1.get(Calendar.HOUR_OF_DAY)+"시 " + time1.get(Calendar.MINUTE) +"분 " + time1.get(Calendar.SECOND) + "초");
		System.out.println("time2 :"+time2.get(Calendar.HOUR_OF_DAY)+"시 " + time2.get(Calendar.MINUTE) +"분 " + time2.get(Calendar.SECOND) + "초");

		long difference =  Math.abs(time2.getTimeInMillis() - time1.getTimeInMillis())/1000;
		System.out.println("time1과 time2의 차이는 "+ difference +"초");

		String tmp = "";

		for(int i=0; i < TIME_UNIT.length;i++) { 
			  tmp += difference/TIME_UNIT[i]+ TIME_UNIT_NAME[i]; 
			  difference %= TIME_UNIT[i];
		} 

		System.out.println("시분초로 변환하면 " + tmp);
	}
}
```
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/28d7917c-6b15-425b-9671-3f2374f33337">
</div>

```java
import java.util.*;

class  CalendarEx4 {
	public static void main(String[] args) {
		Calendar date = Calendar.getInstance();

		date.set(2005, 7, 31);	// 2005년 8월 31일
		
		System.out.println(toString(date));
		System.out.println("= 1일 후 (add) =");
		date.add(Calendar.DATE, 1);
		System.out.println(toString(date));

		System.out.println("= 6개월 전 (add) =");
		date.add(Calendar.MONTH, -6);
		System.out.println(toString(date));

		System.out.println("= 31일 후 (roll) =");
		date.roll(Calendar.DATE, 31);
		System.out.println(toString(date));

		System.out.println("= 31일 후 (add) =");
		date.add(Calendar.DATE, 31);
		System.out.println(toString(date));
	}

	public static String toString(Calendar date) {
		return date.get(Calendar.YEAR)+"년 "+ (date.get(Calendar.MONTH)+1) +"월 " + date.get(Calendar.DATE) + "일";
	}
}
```
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/3044e88c-131c-4761-985e-a1becfadea8c">
</div>

  - add(int filed, int amount)를 사용하면 지정한 필드의 값을 원하는 만큼 증가 또는 감소시킬 수 있음
  - 따라서, add 메서드를 이용하면 특정 날짜 또는 시간을 기점으로 하여 일정 기간 전후의 날짜와 시간을 알아낼 수 있음
  - roll(int field, int amount)도 지정한 필드의 값을 증가 또는 감소 시킬 수 있는데, add와 다르게 다른 필드에 영향을 미치지 않음
  - 예를 들어, add 메서드로 날짜 필드(Calendar.DATE)의 값을 31만큼 증가시키면 다음 달로 넘어가므로, 월 필드(Calendar.MONT)의 값도 1 증가하지만, roll 메서드는 월 필드의 값은 변하지 않고, 일 필드의 값만 바뀜
  - 💡 한 가지 예외로, 일 필드(Calendar.DATE)가 말일(End of Month)일 때, roll 메서드를 이용해서 월 필드(Calendar.MONTH)를 변경하면 일 필드(Calendar.DATE)에 영향을 미침

```java
import java.util.*;

class  CalendarEx5 {
	public static void main(String[] args) {
		Calendar date = Calendar.getInstance();

		date.set(2015, 0, 31);	// 2005년 1월 31일
		System.out.println(toString(date));
		date.roll(Calendar.MONTH, 1);
		System.out.println(toString(date));
	}

	public static String toString(Calendar date) {
		return date.get(Calendar.YEAR)+"년 "+ (date.get(Calendar.MONTH)+1) +"월 " + date.get(Calendar.DATE) + "일";
	}
}
```
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/83ce4355-88a8-4db6-9088-28899e94266a">
</div>

  - 2015년 1월 31일에 대해 roll()을 호출해 월 필드를 1 증가시켰을 때, 월 필드는 2월이 되지만, 2월에는 31일이 없기 때문에 2월의 말일인 28일로 자동 변경
  - add()도 동일하게 마찬가지로 자동 변경

```java
class CalendarEx8 { 
      public static void main(String[] args){ 
		  String date1 = "201508";
		  String date2 = "201405";
		
		  // 년과 월을 substring으로 잘라서 정수 변환
		  // 년에 12를 곱해서 월로 변환한 다음에 뺼셈을 하면 개월치를 구할 수 있음
		  int month1 = Integer.parseInt(date1.substring(0,4))* 12 + Integer.parseInt(date1.substring(4));
		  int month2 = Integer.parseInt(date2.substring(0,4))* 12 + Integer.parseInt(date2.substring(4));

		  System.out.println(date1 +"과 " + date2 + "의 차이는 " + Math.abs(month1-month2) + "개월 입니다.");
      } 
} 
```
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/0ee93b0c-e007-42a6-945b-5ac0594bb9bb">
</div>

```java
class CalendarEx9 { 
	public static void main(String[] args) { 
		System.out.println("2014. 5. 31 :" + getDayOfWeek(2014, 5, 31)); 
		System.out.println("2012. 6. 1 :" + getDayOfWeek(2012, 6, 1)); 
		System.out.println("2014. 5. 1 - 2014.4.28 :" + dayDiff(2014,5,1,2014,4,28)); 
		System.out.println("2015. 6. 29 : "+convertDateToDay(2015, 6, 29)); 
		System.out.println("735778 : "+convertDayToDate(735778)); 
	} 

	// 각 달의 마지막 일
	public static int[] endOfMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}; 

	public static boolean isLeapYear(int year) { 
		return ((year%4==0)&&(year%100!=0)||(year%400==0)); 
	} 

	public static int dayDiff(int y1, int m1, int d1, int y2, int m2, int d2) {
		return convertDateToDay(y1, m1, d1) - convertDateToDay(y2, m2, d2); 
	} 

	public static int getDayOfWeek(int year, int month, int day) { 
		// 1~7의 값을 반환. 결과가 1이면 일요일
		return convertDateToDay(year, month, day)%7 + 1; 
	} 

	public static String convertDayToDate(int day) { 
		int year=1; 
		int month=0; 

		while(true) { 
			int aYear = isLeapYear(year)? 366 :365; 
			if (day > aYear ) { 
				day-= aYear; 
				year++; 
			} else { 
				break; 
			} 
		} 

		while(true) { 
			int endDay = endOfMonth[month]; 
		        // 윤년이고 윤달이 포함되어 있으면, 1일을 더함 
			if(isLeapYear(year) && month == 1) endDay++;    

			if(day > endDay) { 
				day -= endDay; 
				month++; 
			} else { 
				break;                         
			} 
		} 

		return year+"-"+(month+1)+"-"+day; 
	} 

	public static int convertDateToDay(int year, int month, int day) { 
		int numOfLeapYear =0; // 윤년의 수 

		// 전년도 까지의 윤년의 수를 구함 
		for(int i=1;i < year; i++) { 
			if(isLeapYear(i)) 
				numOfLeapYear++; 
		} 

		// 전년도까지의 일수를 구함
		int toLastYearDaySum = (year-1) * 365 + numOfLeapYear; 

		// 올해의 현재월 까지의 일수 계산
		int thisYearDaySum =0; 

		for(int i=0; i < month-1; i++) { 
			thisYearDaySum+=endOfMonth[i]; 
		} 

		// 윤년이고, 2월이 포함되어 있지 않으면 1일을 증가 
		if (month > 2 && isLeapYear(year))
			thisYearDaySum++; 

		thisYearDaySum+=day; 

		return toLastYearDaySum+thisYearDaySum; 
	} 
} 
```
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/b8ad44fa-c73d-449a-bd1a-0d06731e1651">
</div>


```java
boolean isLeapYear(int year) // 매개변수 year가 윤년이면 ture, 그렇지 않으면 false를 반환

int dayDiff(int y1, int m1, int d1, int y2, int m2, int d2) // 두 날짜간의 차이를 일단위로 반환

int getDayOfWeek(int year, int month, int day) // 지정한 날짜의 요일 반환 (1~7. 1이 일요일)

String convertDayToDate(int day) // 일단위의 값을 년월일의 형태의 문자열로 변환하여 반환

int convertDateToDay(int year, int month, int day) // 년월일을 입력받아 일단위로 반환
```
  - Calendar의 경우 1970년 1월 1일을 기준으로 계산하며, 이전 날짜에 대해서는 getTimeInMillis()를 호출하면 음수를 결과로 얻음
