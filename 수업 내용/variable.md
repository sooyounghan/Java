-----
### 변수 : 하나의 값을 저장할 수 있는 메모리 공간(의 이름)
-----
  1. 변수 명명 규칙
     
    * 변수의 첫글자는 소문자가 관례이며, 특수문자 중 _, $만 가능하되, 숫자로 시작 불가
    * 영어 대소문자 구분
    * 다른 단어가 붙을 때는 첫자를 대문자로 하는 것이 관례
    
  2. 문자 수 제한 없으며, 자바 예약어는 사용 불가
  3. 선언 및 할당
    > 데이터타입 변수명;   
    > 변수명 = 값;  (= : 대입연산자)
  4. 변수의 선언은 한 번만 선언
  5. 초기값(Default Value) : 변수 선언 후 최초에 대입하는 값 → 변수의 초기화
    + 값 할당은 선언 이후 변경 가능
     
      
```java
/*
 * 변수의 선언 및 할당
 */
public class Ex02 {
	public static void main(String[] args) {
		/* 변수 선언 : 데이터타입 변수명;
		 * 값 할당 : 변수명 = 값;
		 */
		String name;
		String id = "HongID"; // = : 대입 연산자
		name = "홍길동"; // 초기값(Default Value) - 변수 초기화
        
		// 변수 선언 및 입력 :  데이터타입 변수명 = 값;
		// 임의의 비밀번호를 pwd변수에 저장 및 출력
		String pwd = "yeong0211";

		System.out.println("Not Changed Member Name : " + name); 
                // +(문자열 + 문자열) - 문자열 연결 연산자 
		System.out.println("Member ID : " + id); // Console에 출력
		System.out.print("pwd : " + pwd + "\n");
		
		name = "홍자바";
		System.out.println("Changed Member Name : " + name);		
	}
}
```
-----
### 리터럴과 상수
-----
   1. 리터럴(Literal) : 소스 코드 내 직접 입력된 변수의 초기값 
   2. 상수(Constant) : 값을 한 번만 저장할 수 있는 공간으로, 선언과 동시에 초기화 필요
      + final 키워드 (final int MAX_SPEED = 10;)
-----
### 데이터타입 : 데이터를 저장할 수 있는 유형
-----
  1 기본타입 (Primitive Type) : 리터럴 값(정수, 실수, 논리값)이 직접 저장하는 데이터타입
    + 메모리의 최소 기억 단위 : byte(1 byte = 8 bit)
    
<div align = "center">
<img width="493" alt="제목 없음" src="https://github.com/sooyounghan/JAVA/assets/34672301/b42d7449-8565-44a0-98d0-1e2ce398bc1d">
</div>

```java
/* 
 * DataType 
 * 데이터타입 변수명;
 */

public class Ex03 {
	public static void main(String[] args) {
		// 데이터타입 변수 = 초기값;
		byte v1 = -128;
		byte v2 = -127;
		byte v3 = 0;
		byte v4 = 100;
		byte v5 = 127;
		byte v6 = (byte)128; /* type error (int -> byte x) : Casting
                          overflow!    solution : (byte) casting or int */
		short v7 = 128;
		int v8 = 128;
		long v9 = 128;
		
		System.out.println("v1 = " + v1);
		System.out.println("v2 = " + v2);
		System.out.println("v3 = " + v3);
		System.out.println("v4 = " + v4);
		System.out.println("v5 = " + v5);
		System.out.println("v6 = " + v6);
		System.out.println("v7 = " + v7);
		System.out.println("v8 = " + v8);
		System.out.println("v9 = " + v9);
	}
}
```

---
### 타입 변환 : 데이터 타입을 다른 타입으로 변환
---
1. 리터럴 형변환
```java
/* 
 * DataType 
 * 데이터타입 변수명;
 */

public class Ex03 {
	public static void main(String[] args) {
		int x1 = 300000000; // int
		long x2 = 300000000; // int -> long
		long x3 = 3000000000L; 
                // int range 초과 -> long type으로 지정(long형임을 알리는 접미사 L, l) 
                // 정수의 기본형은 int [리터럴 형변환]
		
		double n1 = 3.14; // 실수 기본형 : double형
		float n2 = 3.14f; // double -> float type error : 접미사(f, F) 
                                // or casting [리터럴 형변환]
		float n3 = (float)n1; // casting
	}
}
```

2. 자동(묵시적) 타입 변환(Promotion) : 작은 타입을 큰 타입으로 자동 변환 가능
3. 강제(명시적) 타입 변환(Casting) : 큰 타입을 작은 타입으로 변환 → 끝의 한 부분만 작은타입으로 강제적 변환
   > 예) 작은크기타입 = (작은크기타입) 큰크기타입

```java
int intValue = 103029770;
byte byteValue = (byte) intValue;
```
<div align = "center">
  <img src = "https://github.com/sooyounghan/JAVA/assets/34672301/1fae478b-64f0-407f-9564-4be91f2494f4">
</div>

```java
// Promotion & Casting
public class Ex04_p73 {
	public static void main(String[] args) {
		// byte(1) < short(2) < int(4, base) < long(8) < float(4) < double(8, base)
		// 큰크기타입 = 작은타입 -> 자동적으로 큰크기타입으로 묵시적 변환
		byte v1 = 100;
		int v2 = v1; // byte -> int
		long v3 = v2; // int -> long
		
		float f1 = 3.14f; // double : basic type -> literal type 변환
		double f2 = f1; // float -> double
		
		float x1 = v2; // int -> float 
		double x2 = v2; // int -> double
		
		// Casting : 작은크기타입 = (작은크기타입)큰크기타입 (Overflow(값의 손실) 주의)
		// x3 = f1; (type convert error : 실수 -> 정수 자동 형변환)
		long x3 = (long)f1; // float -> double (casting)

		int n1 = 123456;
		short n2 = (short)n1; // int -> short casting
		System.out.println("int n1 = " + n1);
		System.out.println("short n2 = " + n2); // overflow.
		
		double n3 = 3.14;
		float n4 = (float)n3; // double -> float casting
		System.out.println("double n3 = " + n3);
		System.out.println("float n4 = " + n4); // 소수점 정밀도 15->7자리 축소
		
		int n5 = (int)n3; // double -> int casting
		System.out.println("int n5 = " + n5); // 소수점 이하 버림(실수 -> 정수)
	}
}
```

4. 정수 및 실수 연산에서의 자동 타입 변환
  - 서로 다른 타입 피연산자 → 같은 타입 변환
  - 두 피연산자 중 크기가 큰 타입으로 자동 변환
```java
// Promotion & Casting - Auto type conversion
public class Ex04_p76 {
	public static void main(String[] args) {
		// 정수 연산에서 자동 타입 변환
		// byte(1) < short(2) < int(4, base) < long(8) < float(4) < double(8, base)
		// 큰크기타입 = 작은타입 -> 자동적으로 큰크기타입으로 묵시적 변환
		int a = 10;
		int b = 20;
		int result = a + b; // long type ok, int + int -> int (promotion)
		System.out.println(result);
		
		double result1 = a + b; // int + int -> double type (promotion)
		System.out.println(result1); 
		
		System.out.println("-------------------------");
		byte n1 = 10;
		byte n2 = 20;
		// byte/short r1 = n1 + n2;  
                /* error. byte(short)->int +-*/% byte(short)->int = int -> type mismatch
		* byte/short r1 = (byte/short)(n1 + n2); ok (casting) */
		int r1 = n1 + n2; // long~double ok
		System.out.println(r1);
		
		// 정수 연산에서의 자동 타입 변환
		System.out.println("-------------------------");
		byte v1 = 101;
		short v2 = 2;
		// byte/short r2 = v1 / v2; // error : line 20
		// byte/short r2 = (byte/short)(v1 / v2); ok (casting)
		double r2 = v1 / v2; // int~double ok, / : divide opeartor
		System.out.println(r2);
		int r3 = v1 % v2; // int~double ok, % : remainder operator
		System.out.println(r3);
	
		// 실수 연산에서의 자동 타입 변환
		System.out.println("-------------------------");
		int x = 100;
		double y = 3.14;
		double z = x + y; // int(->double) + double -> double
		/* byte/short/int/float z = (byte/short/int/float)(x + y); 
                * float 정밀도 하락, byte~long 소수점 탈락 및 overflow. */
		System.out.println(z);
	}
}
```

5. + 연산에서의 문자열 자동 변환
```java
/*
 * + operator -> String auto conversion.
 */
public class Ex05_82 {
	public static void main(String[] args) {
		int n = 100;
		System.out.println(n); // int type variable
		System.out.println("Age : " + n); // int type variable
		// System.out.println("Age : " + n – 1); 
                // String + integer - integer = String- integer = not match type.
		System.out.println("Age : " + (n-1)); 
                // String +(연결 연산자) (integer - integer) = String + String = String 
		/* "Age : "+ (100-1)
		 * "Age : " + 99
		   "Age : 99" */
		
		System.out.println(n + 1); // int + int = int + int
	}
}
```

6. boolean형
```java
/*
 * boolean type : true or false (1 byte)
 */
public class Ex02_03_p69 {
	public static void main(String[] args) {
		boolean stop = true;
		boolean stop2 = false;
		System.out.println("stop ? " + stop); // true;
		System.out.println("stop2 ? " + stop2); // false;
		
		/*
		 * Condition Statement : if statement
		 * if(Condition is true) ~ else(=Condition is false)
		 * ==(equal), !=(not[!] equal[=]) : 비교연산자 (두 개의 값을 비교) 
		 */
		if(stop == true) { // (=if(stop))
			System.out.println("stop"); // stop is true
		} 
		if(stop == false) { // = else
			System.out.println("non-stop"); // stop is false
		}
		if(stop == true) { 
			System.out.println("true"); // answer
		}  
		else {
		  	System.out.println("false");
 		}
		if(stop != true) {  // stop = false -> if(!stop)
			System.out.println("true");
		}  
		else {
		  	System.out.println("false"); // answer
 		}
		if(stop) {  // stop = true -> if(true) = if(!false)
			System.out.println("true"); // answer
		}  
		else {
		  	System.out.println("false"); 
 		}
		if(!stop) {  // stop == false -> if(false) = if(!true)
			System.out.println("true");
		}  
		else {
		  	System.out.println("false"); // answer
 		}
	}
}
```

7. char형 (문자형)
  1. 글자 수가 1인 하나의 문자를 작은 따옴표(‘’)로 감싼 것
  2. 유니코드(Unicode)로 변환되어 저장 (범위 : 0~65535)
     + 유니코드(Unicode) : 세계 각국 문자를 2byte로 표현할 수 있는 숫자로 표현할 수 있는 국제적 코드 규약   
  3. short형과 byte크기는 동일하나 그 크기가 완전히 동일하다고 볼 수 없음.

```java
/*
 * Character Primitive type - char
 * (Primitive Type :
 *   Integer - byte < short < int < long
 *   Real Number - float < double
 *    * Size : byte < short, char < int < long < float < double
 *   Logic - boolean
 *   Character - char)
 */

public class Ex02_p60 {
	public static void main(String[] args) {
		char v1 = 'A';
		int n1 = v1; // char (0~65535) < int
		char v2 = 'a'; 
		int n2 = v2;

		int n3 = 'b'; // Character 'b' is Unicode -> Mapping to integer type internally.
		
		System.out.println("char v1 = " + v1); 
		System.out.println("int n1 = " + n1);
		
		System.out.println("char v2 = " + v2);
		System.out.println("int n2 = " + n2);
		
		System.out.println("int n3 = " + n3);
		System.out.println("int n3 -> char n3 = " + (char)n3); // char x = (char)n3;

		/*
		 * UpperCase <-> LowerCase (String)
		 */
		String x = new String("A"); // (= String x = "A";) -> String x : "A"'s address.
		System.out.println(x); // UpperCase
		System.out.println(x.toLowerCase()); // UpperCase -> LowerCase : String.toLowerCase(String variable);
		System.out.println("a".toUpperCase()); // LowerCase -> UpperCase : "a" is String.

		/*
		 * UpperCase <-> LowerCase (char)
		 * v1 = 'A', v2 = 'a'
		 */
		System.out.println((char)(v1 + 32)); // UpperCase -> LowerCase : (= char u = (char)(v1+32);)
		System.out.println((char)(v2 - 32)); // LowerCase -> UpperCase : (= char u = (char)(v1+32);)
		System.out.println((char)('A' + 32)); // = Line 38
		System.out.println((char)('a' - 32)); // = Line 38
	}
}
```
-----
### 참조타입 (Reference Type) : 객체의 주소를 저장하는 데이터타입으로 8개의 기본형을 제외한 나머지 타입
-----
  1. String 타입 : 참조 타입
  2. String Class Object는 출력할 때, 주소가 아닌 객체에 저장된 문자열을 반환

-----
### Wrapper Class
-----
1. 자바의 기본 타입의 값을 갖는 객체 생성 (기본 타입의 값을 내부에 저장)
2. 외부에서 변경 불가
3. 문자열을 기본 타입 값으로 변환할 때 자주 사용

-----
### Wrapper Class (Boxing과 Unboxing)
-----
1. Boxing : Wrapper Class Type(Field)에 기본값이 대입될 경우 발생 (Integer obj = 100;)   
   → 힙 영역에 Integer 객체 생성 → Primitive Type → Wrapper Class (Field)   

2. Unboxing : 기본 타입에 포장 객체가 대입되는 경우 (Integer obj = new Integer(200); int value = obj;)   
   → Integer객체 int 값 연산시 발생 → Wrapper Class (Field) → Primitive Type   

3. Wrapper Class는 Auto-Boxing/Unboxing 제공

<div align = "center">
<img width="505" alt="제목 없음" src="https://github.com/sooyounghan/JAVA/assets/34672301/e69121af-d4ba-4921-8562-9ea82d6b04d5">
</div>

4. 문자열을 기본 타입으로 강제 타입 변환
<div align = "center">
<img width="511" alt="1" src="https://github.com/sooyounghan/JAVA/assets/34672301/2b555685-14aa-4d52-b95f-c78b6f097c78">
</div>

6. 기본 타입을 문자열로 강제 타입 변환
<div align = "center">
<img width="498" alt="2" src="https://github.com/sooyounghan/JAVA/assets/34672301/46fb5ee2-4e37-49c9-9340-42ba7d24c88b">
</div>

```java
/*
 *  Boxing(Wrapper Class : Auot-Boxing) : Primitive type -> Wrapper Class (field) 
 *  Unboxing : Wrapper Class (field) -> Primitive type
 */
public class BoxingUnboxing_p500 {
	public static void main(String[] args) {
		int n = 100;
		/*
		* Integer Class의 variable : obj1 Create 
                * (Class Classname = new Constructor())
		* new : 객체 생성 예약어 / Class : filed + method
	        * int -> Integer Wrapper Class Boxing 
		*/		
		Integer obj1 = new Integer(n); 
		System.out.println(obj1);
		
		Integer obj2 = n; // Auto-Boxing (Primitive Type -> Wrapper Class)		
		System.out.println(obj2); // Wrapper Class Filed

		int x = obj1; // Auto-Unboxing (Wrapper Class -> Primitive)
		System.out.println(x); // Primitive Type
	}
}
```
