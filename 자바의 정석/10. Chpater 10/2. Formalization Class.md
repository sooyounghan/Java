-----
### 형식화 클래스 (java.text Package)
-----
1. 숫자, 날짜, 텍스트 데이터를 일정한 형식에 맞게 표현할 수 있는 방법을 객체지향적으로 설계하여 표준화
2. 형식화에 사용될 패턴을 정의
3. 데이터를 정의된 패턴에 맞춰 형식화할 수 있을 뿐 아니라 형식화된 데이터에서 원래 데이터를 얻어낼 수 있음

-----
### DecimalFormat
-----
1. 숫자를 형식화하는데 사용
2. 숫자 데이터를 정수, 부동 소수점, 금액 등 다양한 형식으로 표현
3. 반대로 일정한 형식의 텍스트 데이터를 숫자로 쉽게 변환하는 것도 가능
4. DecimalFormat 패턴에 사용되는 기호
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/1061ead5-d16b-43a6-a953-1b61fbae48f3">
<img src="https://github.com/sooyounghan/Java/assets/34672301/e4ae221d-7303-4c60-8d45-ac760ca9eca4">
</div>

5. 원하는 출력 형식의 패턴을 작성하여 DecimalFormat 인스턴스를 생성한 다음, 출력하고자 하는 문자열로 format 메서드를 호출하면 원하는 패턴에 맞게 변환된 문자열을 얻음
```java
double number = 1234567.89;
DecimalFormat df = new DecimalFormat("#.#E0");
String result = df.format(number);
```

6. 예제
```java
import java.text.*;

class DecimalFormatEx1 {
	public static void main(String[] args) throws Exception {
		double number  = 1234567.89;

		String[] pattern = {
			"0",
			"#",
			"0.0",
			"#.#",
			"0000000000.0000",
			"##########.####",
			"#.#-",
			"-#.#",
			"#,###.##",
			"#,####.##",
			"#E0",
			"0E0",
			"##E0",
			"00E0",
			"####E0",
			"0000E0",
			"#.#E0",
			"0.0E0",
			"0.000000000E0",
			"00.00000000E0",
			"000.0000000E0",
			"#.#########E0",
			"##.########E0",
			"###.#######E0",
			"#,###.##+;#,###.##-",
			"#.#%",
			"#.#\u2030",
			"\u00A4 #,###",
			"'#'#,###",
			"''#,###",
		};
		
		for(int i=0; i < pattern.length; i++) {
		    DecimalFormat df = new DecimalFormat(pattern[i]);
		    System.out.printf("%19s : %s\n",pattern[i], df.format(number));
		}
	} // main
}
```
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/d8da1ffd-1c2a-4204-8625-3846ac639641">
</div>

```java
import java.text.*;

class DecimalFormatEx2 {
	public static void main(String[] args) {
		DecimalFormat df  = new DecimalFormat("#,###.##");
		DecimalFormat df2 = new DecimalFormat("#.###E0");

		try {
			Number num = df.parse("1,234,567.89");
			System.out.print("1,234,567.89" + " -> ");

			double d = num.doubleValue(); 
			System.out.print(d + " -> ");

			System.out.println(df2.format(num));
		} catch(Exception e) {}
	} // main
}
```
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/157ed9fb-64c8-4928-a986-c5ef4e780dba">
</div>

  - parse 메서드를 이용하면, 기호와 문자가 포함된 문자열을 숫자로 쉽게 변환 가능 (단, Integer.parseInt 메서드는 콤마(,)가 포함된 문자열을 숫자로 변환하지 못함)
  - parse(String source)는 DecimalFormat의 조상인 NumberFormat에 정의된 메서드
```java
public Number parse(String soruce) throws ParseException
```
  - Number 클래스는 Integer, Double과 같은 숫자를 저장하는 래퍼 클래스의 조상

-----
### SimpleDateFormat
-----
1. DateFormat은 추상클래스로 SimpleDateFormat의 조상이며, 추상클래스이므로 인스턴스를 생성하기 위해 getDateInstance()와 같은 static 메서드를 사용
2. 생성되는 인스턴스는 DataFormat을 상속받아 완전하게 구현한 SimpleDateFormat 인스턴스
3. SimpleDateFormat의 패턴에 사용되는 기호
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/0b60a7dc-d840-4754-98f3-f9bcead599e7">
<img src="https://github.com/sooyounghan/Java/assets/34672301/d63205e1-c867-4880-9f88-b79c25614b6b"></div>

  - 원하는 출력형식의 패턴을 작성해 SimpleDateFormat의 인스턴스를 생성한 다음, 출력하고자 하는 Date 인스턴스를 가지고 format(Date d)를 호출하면 지정한 출력형식에 맞게 변환한 문자열 얻음

```java
Date today = new Date();
SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

// 오늘 날짜를 yyyy-MM-dd 형태로 변환해 출력
String result = df.format(today);
```

4. 예제
```java
import java.util.*;
import java.text.*;

class DateFormatEx1 {
	public static void main(String[] args) {
		Date today = new Date();

		SimpleDateFormat sdf1, sdf2, sdf3, sdf4;
		SimpleDateFormat sdf5, sdf6, sdf7, sdf8, sdf9;

		sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		sdf2 = new SimpleDateFormat("''yy년 MMM dd일 E요일");
		sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		sdf4 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");

		sdf5 = new SimpleDateFormat("오늘은 올 해의 D번째 날");
		sdf6 = new SimpleDateFormat("오늘은 이 달의 d번째 날");
		sdf7 = new SimpleDateFormat("오늘은 올 해의 w번째 주");
		sdf8 = new SimpleDateFormat("오늘은 이 달의 W번째 주");
		sdf9 = new SimpleDateFormat("오늘은 이 달의 F번째 E요일");

		System.out.println(sdf1.format(today));	// format(Date d)
		System.out.println(sdf2.format(today));
		System.out.println(sdf3.format(today));
		System.out.println(sdf4.format(today));
		System.out.println();
		System.out.println(sdf5.format(today));
		System.out.println(sdf6.format(today));
		System.out.println(sdf7.format(today));
		System.out.println(sdf8.format(today));
		System.out.println(sdf9.format(today));
	}
}
```
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/82aa8177-074f-4b63-ad8e-c9088d50b3a8">
</div>

-----
### Calendar 인스턴스와 Date 인스턴스의 변환
-----
```java
import java.util.*;
import java.text.*;

class DateFormatEx2 {
	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		cal.set(2005, 9, 3);	// 2005년 10월 3일

		Date day = cal.getTime(); // Calendar를 Date로 변환

		SimpleDateFormat sdf1, sdf2, sdf3, sdf4;
		sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		sdf2 = new SimpleDateFormat("yy-MM-dd E요일");
		sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		sdf4 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
		
		System.out.println(sdf1.format(day)); // format(Date d)
		System.out.println(sdf2.format(day));
		System.out.println(sdf3.format(day));
		System.out.println(sdf4.format(day));
	}
}
```
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/1f5f26db-9ffd-4647-b9fc-d629c2c6e36c">
</div>

1. Date 인스턴스만 format 메서드에 사용할 수 있으므로 Calendar 인스턴스를 Date 인스턴스로 변환해야 함
2. Date 인스턴스를 Calendar 인스턴스로 변환하기 위해서는 Calendar 인스턴스의 setTime() 메서드 사용
3. Calendar 인스턴스를 Date 인스턴스로 변환하기 위해서는 Calendar 인스턴스의 getTime() 메서드 사용

-----
### DateFormat의 parse 메서드
-----
```java
import java.util.*;
import java.text.*;

class DateFormatEx3 {
	public static void main(String[] args) {
		DateFormat df  = new SimpleDateFormat("yyyy년 MM월 dd일");
		DateFormat df2 = new SimpleDateFormat("yyyy/MM/dd");

		try {
			Date d = df.parse("2015년 11월 23일");
			System.out.println(df2.format(d));
		} catch(Exception e) {}
	} // main
}
```
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/e31abafc-fbef-4be0-b458-3157a97fb950">
</div>

1. parse(String source)를 사용해 날짜 데이터의 출력형식을 변환하는 방법을 보여주는 예제
   - parse(String source)는 SimpleDateFormat의 조상인 DateFormat에 정의
2. SimpleDateFomrat의 parse(String source)는 문자열 source를 날짜 Date 인스턴스로 변환
3. 지정된 형식과 입력된 형식이 일치하지 않은 경우에는 예외가 발생하므로 적절한 예외처리가 필요

-----
### ChoiceFormat
----
1. 특정 범위에 속하는 값을 문자열로 변환
```java
import java.util.*;
import java.text.*;

class n {
	public static void main(String[] args) {
		double[] limits = {60, 70, 80, 90};	// 낮은 값부터 큰 값의 순서로 적어야 함
		// limits, grades 간의 순서와 개수를 맞춰야 함
		String[] grades = {"D", "C", "B", "A"};	
		
		int[] scores = { 100, 95, 88, 70, 52, 60, 70};

		ChoiceFormat form = new ChoiceFormat(limits, grades);

		for(int i=0;i<scores.length;i++) {
			System.out.println(scores[i]+":"+form.format(scores[i]));		
		}
	} // main
}
```
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/e066af16-a5c7-49fb-b48d-9932a8ed5b70">
</div>

2. 두 개의 배열이 사용
   - 하나(limits)는 범위의 경계값을 지정하는데 사용
   - 또 다른 하나(grades)는 범위에 포함된 값을 치환할 문자열을 저장하는데 사용
3. 경계값은 double형으로 반드시 모두 오름차순으로 정렬되어야 하며, 치환 될 문자열의 개수는 경계값에 의해 정의된 범위의 개수와 일치해야 함
4. 그렇지 않으면, IllegalArgumentException 발생

```java
import java.util.*;
import java.text.*;

class ChoiceFormatEx2 {
	public static void main(String[] args) {
		String pattern = "60#D|70#C|80<B|90#A";
		int[] scores = { 91, 90, 80, 88, 70, 52, 60};

		ChoiceFormat form = new ChoiceFormat(pattern);

		for(int i=0;i<scores.length;i++) {
			System.out.println(scores[i]+":"+form.format(scores[i]));	
		}
	} // main
}
```
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/e6f6c21a-e635-4906-be9f-6bc23956c1f7">
</div>

5. 패턴 구분자로 '#'와 '<' 두 가지 제공
   - '#'은 경계값을 범위에 포함
   - '<'는 경계값을 포함시키지 않음

-----
### MessageFormat
-----
1. 데이터를 정해진 양식에 맞게 출력하도록 도와줌
2. 데이터가 들어갈 자리를 마련해놓은 양식을 미리 작성하고 프로그램을 이용해 다수의 데이터를 같은 양식으로 출력할 때 사용
3. MessageFormat의 parse를 이용해 지정된 양식에서 필요한 데이터만을 손쉽게 추출 가능
4. 예제
```java
import java.util.*;
import java.text.*;

class MessageFormatEx1 {
	public static void main(String[] args) {
		String msg = "Name: {0} \nTel: {1} \nAge:{2} \nBirthday:{3}";

		Object[] arguments = {
			"이자바","02-123-1234", "27", "07-09"
		};

		String result = MessageFormat.format(msg, arguments);
		System.out.println(result);
	}
}
```
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/eaf97a4c-0573-4b45-a54b-8c993fdd56ff">
</div>

  - MessageFormat에 사용할 양식인 문자열 msg를 작성할 때 {숫자}로 표시된 부분이 데이터가 출력될 자리
  - 이 자리는 순차적일 필요는 없고, 여러 번 반복해서 사용 가능
  - 여기에 사용되는 숫자는 배열처럼 인덱스가 0부터 시작하며, 양식에 들어갈 데이터는 객체 배열인 arguments에 지정되어 있음
  - 객체 배열이기 때문에 String 이외에도 다른 객체들이 지정 가능하며, 이 경우 보다 세부적인 옵션들이 사용될 수 있음

```java
import java.text.*;

class MessageFormatEx3 {
	public static void main(String[] args) throws Exception {
		String[] data = {
		  "INSERT INTO CUST_INFO VALUES ('이자바','02-123-1234',27,'07-09');",
		  "INSERT INTO CUST_INFO VALUES ('김프로','032-333-1234',33,'10-07');" 
		};

		String pattern = "INSERT INTO CUST_INFO VALUES ({0},{1},{2},{3});";
		MessageFormat mf = new MessageFormat(pattern);

		for(int i=0; i < data.length;i++) {
			Object[] objs = mf.parse(data[i]);
			for(int j=0; j < objs.length; j++) {
				System.out.print(objs[j] + ",");
			}
			System.out.println();
		}
	} // main
}
```
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/54958fbe-41e1-4cc9-9df8-135580ac3f3d">
</div>

  - parse(String source)를 이용해 출력된 데이터로부터 필요한 데이터를 뽑아내는 예제
