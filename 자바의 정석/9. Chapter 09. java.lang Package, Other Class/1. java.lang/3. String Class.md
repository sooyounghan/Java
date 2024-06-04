-----
### String클래스
-----
1. 기존 다른 언어는 문자열을 char형 배열로 다루었으나 자바에서는 문자열을 위한 클래스를 String 클래스로제공
2. String 클래스는 문자열을 저장하고 이를 다루는데 필요한 메서드 제공

-----
### 변경 불가능한 (Immnutable) 클래스
-----
1. String 클래스에는 문자열을 저장하기 위해 문자형 배열 참조변수(char[]) value를 인스턴스 변수로 정의
2. 인스턴스 생성 시 생성자의 매개변수로 입력받는 문자열은 이 인스턴스변수 (value)에 문자형 배열(char[])로 저장되는 것
```java
public final class String implements java.io.Serializable, Comparable {
    private char[] value;
      ...
}
```

3. 한 번 생성된 String 인스턴스가 갖고 있는 문자열은 읽어올 수 만 있고, 변경할 수 없음
   - 아래 코드와 같이 '+'연산자를 이용해서 문자열을 결합하는 경우, 인스턴스 내 문자열이 바뀌는 것이 아니라 새로운 문자열("ab")이 담긴 String 인스턴스가 생성
```java
String a = "a";
String b = "b";
a = a + b;
```
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/6eda0e41-1620-49a5-94f2-3d7e3abd1539">
</div>

  - 덧셈연산자 +를 사용해서 문자열을 결합하는 것은 매 연산 시 마다 새로운 문자열을 가진 String 인스턴스가 생성되어 메모리 공간을 차지하게 되므로 가능한 결합횟수를 줄이는 것이 좋음

4. 문자열 간 결합이나 추출 등 문자열 다루는 작업이 많이 필요한 경우, StringBuffer 클래스를 사용하는 것이 좋음
   - StringBuffer 인스턴스에 저장된 문자열은 변경이 가능하므로 하나의 StringBuffer 인스턴스 만으로도 문자열 다루는 것 가능

-----
### 문자열의 비교
-----
1. 문자열 리터럴을 지정하는 방법과 String 클래스의 생성자를 사용해서 만드는 방법 존재
```java
String str1 = "abc"; // 문자열 리터럴 "abc" 주소가 str1에 저장
String str2 = "abc"; // 문자열 리터럴 "abc" 주소가 str2에 저장
String str3 = new String("abc"); // 새로운 String 인스턴스를 생성
String str4 = new String("abc"); // 새로운 String 인스턴스를 생성
```

2. String 클래스의 생성자를 이용한 경우 new 연산자에 의해 메모리 할당이 이루어지기 때문에, 항상 새로운 String 인스턴스가 생성하지만, 문자열 리터럴은 이미 존재하는 것을 재사용
   - 문자열 리터럴은 클래스가 메모리에 로드될 때, 자동적으로 미리 생성
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/94c73a01-ed01-4461-822b-dd8a0b67c4d0">
</div>

3. equals()를 사용했을 때, 두 문자열 내용을 비교하기 때문에 두 경우 모두 true가 나옴
4. 하지만, String 인스턴스의 주소를 등가비교연산자 ==로 비교했을 때 결과가 다름
```java
class StringEx1 {
	public static void main(String[] args) {
		String str1 = "abc";
		String str2 = "abc";

		System.out.println("String str1 = \"abc\";");
		System.out.println("String str2 = \"abc\";");

		System.out.println("str1 == str2 ?  " + (str1 == str2));
		System.out.println("str1.equals(str2) ? " + str1.equals(str2));
		System.out.println();

		String str3 = new String("\"abc\"");
		String str4 = new String("\"abc\"");

		System.out.println("String str3 = new String(\"abc\");");
		System.out.println("String str4 = new String(\"abc\");");

		System.out.println("str3 == str4 ? " + (str3 == str4));
		System.out.println("str3.equals(str4) ? " + str3.equals(str4));
	}
}
```
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/54617285-4ff9-4505-a327-8c7dfca41108">
</div>

-----
### 문자열 리터럴
-----
1. 자바 소스파일에 포함된 모든 문자열 리터럴은 컴파일 시 클래스 파일에 저장
2. 즉, 같은 내용의 문자열 리터럴은 한번만 저장
3. 문자열 리터럴도 String 인스턴스이고, 한번 생성하면 내용을 변경할 수 없으니 하나의 인스턴스를 공유하면 되기 때문임
```java
class StringEx2 {
	public static void main(String args[]) {
		String s1 = "AAA";
		String s2 = "AAA";
		String s3 = "AAA";
		String s4 = "BBB";
	}
}
```

4. 위 코드를 컴파일하면 StringEx2.class 파일이 생성되며, 내용을 확인하면 다음과 같음
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/146bc110-8a06-4987-8e0f-eecd1e905a7c">
</div>

   - 이 중 'AAA'와 'BBB'를 찾을 수 있는데, 이는 String 리터럴들이 컴파일 시 클래스 파일에 저장된 것
   - 즉, "AAA"를 담고 있는 String 인스턴스가 하나 생성된 후, 참조 변수 s1, s2, s3 모두 이 String 인스턴스를 참조하게 됨
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/ede998ed-a29d-458b-80b9-2c9383eeabb7">
</div>

5. 클래스 파일에는 소스파일에 포함된 모든 리터럴의 목록이 있음
   - 해당 클래스 파일이 클래스 로더에 의해 메모리에 올라갈 때, 이 러터럴 목록에 있는 리터럴들이 JVM 내에 있는 상수 저장소(Constant Pool)에 저장
   - 이 때, 문자열 리터럴이 자동적으로 생성

-----
### 빈 문자열 (Empty String)
-----
1. String s = "";과 같은 문장에 대해, 참조변수 s가 참조하고 있는 String 인스턴스는 내부에 new char[0]과 같이 길이가 0인 char 배열을 저장하고 있는 것
   - C언어에서는 길이가 0인 배열 생성 불가

```java
char[] chArr = new char[0]; // 길이가 0인 char 배열
int[] iArr = {}; // 길이가 0인 int 배열
```

2. 그러나, char형 변수는 반드시 하나의 문자를 지정해야 함
   - C언어에서는 문자열 끝에 널 문자를 항상 넣어야하지만, 자바에서는 널 문자를 사용하지 않으며, 대신 문자열의 길이 정보를 따로 저장
```java
String s = null; → String s = ""; // 빈 문자열로 초기화
char c = '\u0000'; → char c = ' '; // 공백으로 초기화
```

3. String 타입의 기본값은 null 보다는 빈 문자열로, char형은 기본값인 '\u0000' 대신 공백으로 초기화하는 것이 일반적
   - \u0000 : 아무런 문자도 지정되지 않은 빈 문자 (유니코드)
  
```java
class StringEx3 {
	public static void main(String[] args) {
		// 길이가 0인 char배열을 생성
		char[] cArr = new char[0];   // char[] cArr = {};
		String s = new String(cArr); // String s = new String("");

		System.out.println("cArr.length="+cArr.length);
		System.out.println("@@@"+s+"@@@");
	}
}
```
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/c9eac38a-a258-4716-ab18-b7980429d679">
</div>

-----
### String 클래스의 생성자와 메서드
-----
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/066a1589-86e1-4690-8fcc-6f88b97d7e50">
<img src="https://github.com/sooyounghan/Java/assets/34672301/76816a22-e696-4960-8a20-e2709793e587">
<img src="https://github.com/sooyounghan/Java/assets/34672301/18e9d49e-871f-4ed2-a53d-e26c23317daf">
<img src="https://github.com/sooyounghan/Java/assets/34672301/22e7a272-67c8-4e62-8018-1d6fb47339f9">
</div>

   - CharSequence는 JDK1.4부터 추가된 인터페이스로 String, StringBuilder 등 클래스가 구현
     
-----
### join()과 StringJoiner
-----
1. join() : 여러 문자열 사이 구분자를 넣어서 결합 (구분자로 문자열을 자르는 split()과 반대 작업)
```java
String animals = "dog,cat,bear";
String[] arr = animal.split(","); // 문자열을 ',' 구분자로 나눠서 배열에 저장
String str = String.join("-", arr); // 배열의 문자열을 '-'로 구분해서 결합
System.out.println(str); // dog-cat-bear
```

2. java.util.StringJoiner 클래스를 사용해서 문자열을 결합할 수 있음
```java
StringJoiner sj = new StringJoiner(",", "[", "]");
String[] strArr = { "aaa", "bbb", "ccc" };

for(String s : strArr) {
	sj.add(s.toUpperCase());
}

System.out.println(sj.toString()); // [AAA, BBB, CCC]
```

```java
import java.util.StringJoiner;

class StringEx4 {
	public static void main(String[] args) {
		String animals = "dog,cat,bear";
		String[] arr   = animals.split(",");

		System.out.println(String.join("-", arr));

		StringJoiner sj = new StringJoiner("/","[","]");
		for(String s : arr)
			sj.add(s);

		System.out.println(sj.toString());
	}
}
```
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/b8762d16-ee50-415e-885b-78fecbc8db6d">
</div>

-----
### 유니코드의 보충문자
-----
1. 유니코드는 본래 2byte, 즉 16bit 문자체계인데, 이로 부족하여 20비트로 확장
2. 하나의 문자를 char 타입으로 다루지 못하고, int 타입으로 다룰 수 밖에 없음
3. 확장에 의해 새로 추가된 문자들을 보충 문자(Supplementary Characters)라고 함
4. String 클래스의 메서드 중에는 보충 문자를 지원하는 것도 있고, 지원하지 않는 것도 있음
5. 매개변수가 'int ch'인 것들은 보충 문자 지원, 'char ch'인 것들은 지원하지 않음

-----
### 문자 인코딩 변환
-----
1. getBytes(String charsetName)를 사용하면, 문자열의 문자 인코딩을 다른 인코딩으로 변경 가능
2. 자바가 UTF-16을 사용하지만, 문자열 리터럴에 포함되는 문자열은 OS의 인코딩을 사용
   - 사용가능한 문자 인코딩 목록 : System.out.println(java.nio.charset.Charset.availableCharsets());
   - 한글 윈도우즈 : 문자 인코딩으로 CP949 사용하며, UTF-8로 변경하려면 다음과 같음
```java
byte[] utf8_str = "가".getBytes("UTF-8"); // 문자열을 UTF-8로 변환
String str = new String(utf8_str, "UTF-8"); // byte 배열을 문자열로 변환
```

3. 서로 다른 문자 인코딩을 사용하는 컴퓨터 간 데이터를 주고받을 때 적절한 문자 인코딩이 필요

```java
import java.util.StringJoiner;

class StringEx5 {
	public static void main(String[] args) throws Exception {
		String str = "가";

		byte[] bArr  = str.getBytes("UTF-8");
		byte[] bArr2 = str.getBytes("CP949");

		System.out.println("UTF-8:" + joinByteArr(bArr));
		System.out.println("CP949:" + joinByteArr(bArr2));

		System.out.println("UTF-8:" + new String(bArr,  "UTF-8"));
		System.out.println("CP949:" + new String(bArr2, "CP949"));
	}

	static String joinByteArr(byte[] bArr) {
		 StringJoiner sj = new StringJoiner(":", "[", "]");

		for(byte b : bArr)
			sj.add(String.format("%02X",b));

		return sj.toString();
	}
}
```

<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/f3a2e026-446e-438d-b05d-814bc50a681d">
</div>

4. UTF-8은 한글 한 글자를 3byte로 표현하고, CP949는 2byte로 표현
   - 따라서, '가'는 UTF-8로 '0xEAB080', CP949로 '0xB0A1'

-----
### String.format()
-----
1. format() : 형식화된 문자열을 만들어내는 방법
2. printf()와 사용법이 동일
```java
String str = String.format("%d 더하기 %d는 %d입니다.", 3, 5, 3+5);
System.out.println(str); // 3 더하기 5는 8입니다.
```

-----
### 기본형 값을 String으로 변환
-----
1. 기본형을 문자열로 변경하는 방법은 숫자에 빈 문자열 ""을 더해주면 됨
2. 또는, valueOf()를 사용
3. 성능적으로는 valueOf()가 더 좋지만, 빈 문자열을 더하는 방법이 더 간단하고 편하기 때문에, 성능 향상이 필요한 경우에만 valueOf() 사용
```java
int i  = 100;
String str1 = i + ""; // 100을 "100"으로 만드는 방법
String str2 = String.valueOf(i); // 100을 "100"으로 만드는 방법
```

-----
### String을 기본형 값으로 변환
-----
1. valueOf()를 사용하거나 parse...() 사용
```java
int i = Integer.parseInt("100"); // "100"을 100으로 변환
int i2 = Integer.valueOf("100");/ // "100"을 100으로 변환
```

2. valueOf()의 반환 타입은 int가 아니라 Integer이지만, Auto-Boxing에 의해 Integer가 int로 형변환
3. 기존에는 parse...()와 같은 메서드를 사용했으나, 메서드 이름 통일을 위해 valueOf()가 나중에 추가
   - valueOf(String s)는 메서드 내부에서 그저 parse...(String s)를 호출할 뿐이므로, 두 메서드는 반환 타입만 다른 메서드
```java
public static Integer valueOf(String s) throws NumberFormatException {
	return Integer.valueOf(parseInt(s, 10)); // 여기서 10은 10진수 의미
}
```

4. 기본형과 문자열 간 변환 방법
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/3b5261ca-967d-43a8-a7b1-0b38e1511eed">
</div>

   - byte, short을 문자열로 변경할 때는 String.valueOf(int i) 사용
   - 문자열 "A"를 문자 'A'로 변환 : char ch = "A".charAt(0);

5. Boolean, Byte와 같이 기본형 타입의 이름 첫 글자가 대문자 : 래퍼 클래스 (Wrapper Class)
   - 기본형 값을 감싸고 있는 클래스라는 뜻

```java
class StringEx6 {
	public static void main(String[] args) {
		int iVal = 100;
		String strVal = String.valueOf(iVal);	// int를 String으로 변환.
		
		double dVal = 200.0;
		String strVal2 = dVal + "";	// int를 String으로 변환

		double sum  = Integer.parseInt("+"+strVal)+Double.parseDouble(strVal2);
		double sum2 = Integer.valueOf(strVal) + Double.valueOf(strVal2);

		System.out.println(String.join("",strVal,"+",strVal2,"=")+sum);
		System.out.println(strVal+"+"+strVal2+"="+sum2);
	}
}
```
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/762a815b-4d76-48d3-955e-9fabaae89d1d">
</div>

6. parse...() 메서드는 문자열에 공백 또는 문자가 포함되어 있는 경우 NumberFormatException 예외 발생하므로 주의
7. 따라서, 문자열 양끝의 공백을 제거해주는 trim()을 습관적으로 같이 사용하는 것이 좋음
```java
int val = Integer.parseInt(" 123 ".trim()); // 문자열 양 끝 공백 제거 후 변환
```

8. 그러나 부호를 의미하는 '+'나 소수점을 의미하는 '.'과 float형 값을 뜻하는 f와 같은 자료형 접미사는 허용하나, 자료형에 알맞는 변환을 하는 경우만 허용
   - 1.0f을 int형 변환 메서드인 Integer.parseInt(Stirng s)에 사용하면 예외 발생, Float.parseFloat(String s)에 사용하면 문제 발생하지 않음
9. Integer클래스의 static int parseInt(String s, int radix)
    - int result = Integer.parseInt("a", 16); : reuslt에는 정수 값 10이 저장 (16진수로 a는 10진수로 10)

-----
### indexOf(), substring() 예제
-----
```java
class StringEx7 {
	public static void main(String[] args) {
		String fullName = "Hello.java";

		// fullName에서 '.'의 위치를 찾음
		int index = fullName.indexOf('.');

		// fullName의 첫 글자부터 '.'이 있는 곳까지 문자열 추출
		String fileName = fullName.substring(0, index);

 	    // '.'의 다음 문자부터 시작해 문자열 끝까지 추출
		// fullName.substring(index+1, fullName.length());와 동일
		String ext = fullName.substring(index+1);

		System.out.println(fullName + "의 확장자를 제외한 이름은 " + fileName);
		System.out.println(fullName + "의 확장자는 " + ext);
	}
}
```
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/8de4c1b5-6b57-4a66-b8a4-10a8b3531dbc">
</div>

1. substring(int start, int end)에서 index는 0부터 시작하며, start와 end의 범위 중 end의 위치에 있는 문자는 결과에 포함되지 않음
2. 즉, end에서 start 값을 빼면 substring에 의해 추출될 글자의 수
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/21aa36c3-20ce-4202-b862-f33e1b3fcdb1">
</div>
