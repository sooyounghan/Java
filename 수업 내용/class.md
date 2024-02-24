-----
### 클래스
-----
1. 객체를 생성하기 위한 필드와 메소드로 정의
2. 일종의 설계도, 객체를 구현할 수 있는 틀
  - 자바 : 클래스(설계도) → 객체(객체)

-----
### 객체
-----
1. 인스턴스(Instance) : 객체와 동일한 의미며, 클래스로부터 만들어진 객체   
2. 하나의 클래스로부터 여러 개의 인스턴스를 만들 수 있음
3. 속성(필드)과 동작(메소드)을 가지는 것으로 물리적으로 존재하는 것(예) 사람, 책, 자동차)
4. 동일한 클래스 내에서 다수의 객체를 생성하더라도, 독립적인 객체임
    - 객체는 Heap영역에서 생성되며, 생성하게 되면 다른 주소를 가지고 있으므로 서로 다른 객체임
      
5. static : 객체가 서로 달라도 이를 구성하는 틀에서 공통적으로 공유해서 쓰게 할 수 있는 키워드   
   → 클래스에서 공유하는 것이므로 어떠한 객체에서도 공유 가능
  - static method / field → 클래스명.메소드명(); 클래스명.필드; 으로 객체를 생성하지 않고 참조 가능

-----
### 객체 간의 관계
-----
1. 사용 관계 : 객체가 다른 객체를 사용하는 것 (예) 사람이 자동차를 이용한다.)
  
2. 포함 관계(집합 관계) ( ~ has a ~ : ~는 ~을 가지고 있다.) (예) Car는 Tire를 가지고 있다.)

3. 상속 관계( ~ is a ~ : ~은 ~이다. ) : 부모와 자식 간의 관계
  > 예) 동물 (나이 / 먹는다(), 움직이다()) [추상적] = 동물 Class   
    1. 사람은 동물이다. (품종, 몸무게, 나이 / 소리내다(), 언어를 사용한다()[고유의 기능] … ) [구체적] = 사람 Class   
    2. 고양이는 동물이다. (품종, 몸무게, 나이 / 소리내다(), 집을 지키다()[고유의 기능] … ) [구체적] = 고양이 Class   
    3. 강아지는 동물이다. (품종, 몸무게, 나이 / 소리내다(), 쥐를 잡다()[고유의 기능] … ) [구체적] = 강아지 Class

<div align = "center">
<img src = "https://github.com/sooyounghan/JAVA/assets/34672301/c499b20b-92ae-4aa7-b812-5421cd92e094">
</div>

-----
### 클래스 선언
-----
1. 소스 파일 생성 : 클래스이름.java  
2. 소스 작성 → 컴파일러(javac.exe) → 클래스이름.class   
3. 소스 파일당 하나의 클래스 선언하는 것이 관례
    - 두 개 이상 클래스도 선언이 가능, 그 중 소스 파일 이름과 동일한 클래스만 public으로 선언 가능

4. 선언한 개수만큼 바이트 코드 파일 생성
5. 동일 패키지 내에서는 같은 이름의 클래스는 존재할 수 없으나, 다른 패키지 내에서는 같은 이름의 클래스 존재 가능

-----
### new 연산자
-----
<div align = "center">
<img width="349" alt="다운로드 (20)" src="https://github.com/sooyounghan/JAVA/assets/34672301/e535d18e-c181-4f0b-9df1-ae93b9e4bca1">
</div>

1. 객체 생성 역할 → 객체 생성 후 객체 생성 번지 리턴
2. 클래스() : 생성자 호출 코드
3. 생성된 객체는 힙 메모리 영역에 생성

-----
### 클래스 변수
-----
1. new 연산자에 의해 리턴된 객체 번지 저장 (참조 타입 변수)
2. 형태
  > 클래스 변수;   
  > 변수 = new 클래스();   
  > (= 클래스 변수 = new 클래스();)

-----
### 클래스의 용도
-----
1. 라이브러리 용(API : Application Program Interface)용
  - 자체적으로 실행되지 않고, 다른 클래스에서 이용할 목적으로 만든 클래스

2. 실행용 : main() 메소드를 가지고 있는 클래스로 실행 목적으로 만든 클래스

3. 1개의 애플리케이션 = 1개의 실행클래스 + n개의 라이브러리 클래스

-----
### 클래스의 구성 멤버
-----
1. 필드(Field) : 객체의 데이터가 저장 → 초기값이 지정되지 않은 필드는 객체 생성시 자동 기본값 초기화
<div align = "center">
<img width="349" src="https://github.com/sooyounghan/JAVA/assets/34672301/01d486b3-571c-4b79-a099-36b955ffe46d">
</div>

 - 타입 필드 [= 초기값]; → [Access Modifier : 접근제한자][Modifier : 제한자] 데이터타입 필드명[= 초기값];
    + 객체 내부 : 필드이름으로 접근
    + 객체 외부 : 객체변수.필드이름;
    + 외부에서 데이터를 받아 처리 : 생성자(Constructor) 또는 setter 이용

2. 메소드(Method) : 객체의 동작에 해당하는 실행 블록
  + 형태
    > 인스턴스 메소드 : 인스턴스변수.메소드();   
    > Static 메소드 : 클래스명.메소드();

```java
ReturnType Method_name(Argument..) {
                             실행문;
                             return ~; // return 표현식 : 변수명(또는 리터럴, 연산식, method 등) 가능
   } // ({} : method 블록)
```

  + return문의 반환 값과 return type은 데이터타입이 일치해야함 (자동 형변환은 자동 처리, 강제 형변환은 형변환 필요)
      - Reutrn 값은 존재, 존재하지 않을 수 있음
      - 매개변수는 존재, 존재하지 않을 수 있음

3. 생성자(Constructor) : 객체 생성시 초기화 역할 담당
  + 외부에서 객체를 생성한 후, 외부 데이터를 통해 객체의 필드 초기화 역할 (그 외의 기능도 처리할 수 있음)
  + 생성자를 생략되어 있다면, Default Constructor(기본 생성자)가 추가되어 호출 → Default Constructor : 클래스명() { }

<div align = "center">
<img width="402" alt="다운로드 (22)" src="https://github.com/sooyounghan/JAVA/assets/34672301/b1392d78-8fe8-48a0-a8fd-aa0897da680a">
</div>

-----
### 클래스 예제
-----
1. 클래스를 통한 객체 생성
<div align = "center">
<img width="333" alt="다운로드 (23)" src="https://github.com/sooyounghan/JAVA/assets/34672301/d3d12246-6346-489c-b6a6-55ee9a1e5325">
</div>

2. method의 형태와 예
<div align = "center">
<img width="396" alt="다운로드 (24)" src="https://github.com/sooyounghan/JAVA/assets/34672301/40755cac-2a37-4286-b8d7-14f2be3a1a28">
</div>

-----
### 접근 제한자 (Access Modifier)
-----
1. public : 모든 클래스에서 접근 가능
2. protected : 같은 패키지 내, 그리고 다른 패키지 내 자식 클래스까지만 접근 가능
3. (default) : 같은 패키지 내 접근 가능
4. private : 같은 클래스 내에서만 접근 가능

        접근범위 : public > protected > (default) > private

5. 클래스에서의 접근 제한자

		A. 필드 : [Access Modifier] [(속성) Modifier] Type field_name[= Initial Value];
		B. 생성자 : : [Access Modifier] Class_name(argument list) { [Statement;] }
			- 생성자는 필드의 초기화를 주로 담당
			- Default Constructor : 기본 생성자로, 매개변수가 없는 기본 생성자 (생성자를 생성하지 않으면 컴파일러 자동으로 추가)
                        - 매개변수가 있는 생성자를 생성하면, 기본 생성자는 컴파일러에 의해 생성되지 않음 (생성자가 있는 것으로 간주)
                        - 매개변수가 있는 생성자로 객체를 생성하면, 객체를 생성할 때 그 생성자의 매개변수에 맞게 그 매개변수 순서, 타입, 개수에 맞게 설정해야함
  		C. 메서드 : [Access Modifier] [(속성) Modifier] ReturnType method_name(argument list) { Statement; return ~; }
< Book Class >

```java
import java.util.Date; // Date Class (java.util package)

/*
 * 접근제어자 class 클래스명 {
 * 		Field - 속성(data)
 *       * 외부에서 Handliing : Constructor 또는 setter 이용
 *       
 *      Constructor(생성자) - 객체 생성 (외부에서 객체를 생성 -> 객체의 필드 초기값 부여) 
 *                         - 여러 기능도 추가 가능
 *      
 * 		Method - 기능(fuction), 동작
 * 
 *      -> member
 * }
 * 
 * 클래스에 경우에 따라 메소드 생략, 필드 생략 가능
 */

/*
 * 책(Book)에 대한 Class
 *   - 제목 : JAVA잡자
 *   - 저자명 : 세종대왕
 *   - 출판사명 : 좋은출판사
 *   - 출판일 : 2022-08-30
 *   - 가격 : 35000
 */

public class Book01 { // 책 Class
	/*
	 * Field
	 * [Access modifier : 접근 제한자][제한자 : Modifier]데이터타입 필드명[= 초기값];
	 */

	String title = "JAVA잡자"; // 책 제목
	String writer = "세종대왕"; // 책 저자명
	String publisher = "좋은출판사"; // 책 출판사명
	Date pubdate = new Date(2022-1900, 8-1, 30);
	/*
	 *  Date 타입 가능 (단, 클래스이므로 참조변수의 초기값으로 null값)
	 */
	// String pubdate; // String 타입으로도 가능
	int price = 35000; // 책 가격
	private double margin = 0.3; // 마진률
	
	/*
	 * Constructor
	 *  : 생성자가 생략되어 있다면, 기본 생성자 호출
	 */
	Book01() {
		System.out.println("Constuctor Call");
	}
	
	/*
	 * Method
	 */
	
	/*
	 * 매개변수 없음, Return값 없음
	 * 	void method_name() {
	 * 
	 *  }
	 */
	void braille() { // 점자로 읽는다는 메서드
		System.out.println("braile method() start");
	}

	/*
	 * 매개변수 없음, Return값 있음
	 *  ReturnType method_name() {
	 *  	return 값;
	 *  }
	 */
	String soundBook() { // 책을 소리내어 읽어주는 메서드
		return "soundBook() : " + this.title + " 책을 읽어줍니다.";
	}

	/*
	 * 매개변수 있음, Return값 없음
	 * 	void method_name(argument 1, argument 2, ...) {
	 * 
	 *  }
	 */
	void codeRun(String code) { // 코드를 입력받아 출력하는 메서드
		System.out.println("codeRun() : " + code);
	}
	
	/*
	 * 매개변수 있음, Return값 있음
	 * 	ReturnType method_name(argument 1, argument 2, ...) {
	 *  	return 값;
	 *  }
	 */
	int timer(int decibel) { // 특정 시간 후에 소리의 10배를 키워 알람을 울려주는 메서드
		decibel *= 10;
		return decibel;
	}
}
```
< Main >
```java
import java.text.*;

/*
 * Book1 Class의 실행 클래스
 */

public class Book01_Main {
	public static void main(String[] args) {
		/*
		 * Book01 클래스를 사용하기 위한 Book1 객체 생성
		 *  - new Book01() : Heap영역에 Book1 클래스의 정보를 가진 객체를 생성자를 통해 생성 -> 객체를 담는 주소 반환
		 *  - 참조 변수를 받되, 클래스 타입의 참조 변수를 받아야함
		 *  - 클래스타입 참조변수 = new 클래스(); 
		 *  
		 */
		Book01 my_book = new Book01();
		System.out.println("my_book_new = " + my_book); // my_book 객체의 주소
		/*
		 * Book01 my_book = null; null <- new Book01();
		 * = Book01 my_book = new Book01();
		 */
		/*
		 * Book01 클래스를 통해 생성된 Book1 객체의 field 출력 (객체변수명.field)
		 */
		System.out.println("my_book Name = " + my_book.title);
		System.out.println("my_book Writer = " + my_book.writer);
		System.out.println("my_book Publish = " + my_book.publisher);
		System.out.println("my_book Price = " + my_book.price);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String pub_date = sdf.format(my_book.pubdate);
		System.out.println("my_book Pubdate = " + pub_date);
		
		// method 1 : argument, return x
		my_book.braille(); // Class에서 생된 my_book내 braille() 메소드 사용
		
		// method 2 : argument x, return o
		String read_sound = my_book.soundBook(); // soundBook()의 return type : String
		System.out.println(read_sound);
		
		// method 3 : argument o, return x
		my_book.codeRun("System.out.println();");
		
		// method 4 : argument o, return o
		int sound_size = my_book.timer(10); // timer()의 return type : int
		System.out.println("Sound Size : " + sound_size + " Alert!");
		
		/*
		 * Access Modifier
		 *  : private
		 */
 		// System.out.println(my_book.margin); // Book01 클래스의 필드 margin은 private 접근으로 자신의 클래스에서만 사용 가능
		System.out.println();
		System.out.println("-------New Object------");
		System.out.println();
		
		/*
		 * 새로운 Book1 객체 my_book_new 생성
		 */
		Book01 my_book_new = new Book01();
		System.out.println("my_book_new = " + my_book_new); // my_book과 다른 객체 (주소가 다름)
		System.out.println("my_book_new Name = " + my_book_new.title);
	}
}
```
-----
### getter와 setter 
-----

1. getter : 접근 제한자로 제한이 설정된 field의 값을 전달하는 함수 (예) Date Class : getHour(), getMinute())
2. setter : 접근 제한자로 제한이 설정된 filed의 값을 전달받는 함수 (예) Date Class : setHour(int hour), setMinute(int minutes))

-----
### 캡슐화와 정보은닉
-----
1. 캡슐화(Encapsulation) : 클래스 안에 서로 연관있는 속성과 기능들을 하나의 캡슐(capsule)로 만들어 데이터를 외부로부터 보호하는 것
2. 정보 은닉 : 객체 내부 구현을 숨김으로 객체가 반드시 정해진 메소드를 통해 상호작용하도록 유도

-----
### this
-----
1. 객체(인스턴스) 자신의 참조(번지)를 가지고 있는 키워드
2. 객체 내부에서 인스턴스 멤버임을 확실하게 하기 위해 this. 사용
3. 매개변수와 필드명이 동일할 때 인스턴스 필드임을 명확하게 하기 위해 사용

-----
### this()
-----
1. 자신의 생성자 호출
2. 생성자는 또 다른 생성자를 호출할 수 없음 → this()로 호출
	- 생성자가 오버로딩되면 생성자 간의 중복된 코드가 발생
	- 초기화 내용이 비슷한 생성자들에서 발생하는 있는 현상으로, 초기화 내용을 한 생성자에 몰아 작성
	- 다른 생성자는 초기화 내용을 작성한 생성자를 this(..)로 호출 (예제 참고)
	- 생성자 this()는 항상 첫 문장에 실행되어야 함  (중요)

			생성자 내에서 초기화 작업 도중 다른 생성자를 호출하게 되면, 호출이 다른 생성자 내에서 멤버변수를 초기화 할 것이므로
   			 다른 생성자를 호출 할 수 있기 때문에 이전 초기화 작업이 무의미해짐 (중요)

<div align = "center">
<img width="505" alt="img (1)" src="https://github.com/sooyounghan/JAVA/assets/34672301/3bbcf333-9402-4853-8f8c-b21b4f31734b">
</div>

-----
### (생성자/메서드) 오버로딩 = (Constructor / Method) Overloading
-----
1. 클래스 내 같은 이름의 메소드를 여러 개 선언하는 것
2. 하나의 메소드 이름으로 다양한 매개변수를 받기 위해 (메소드/생성자)를 오버로딩
3. 조건 : 매개변수의 개수, 순서, 타입
4. 동일 클래스 내 생성자 또는 메소드에서 동일한 메소드가 존재해서는 안되는데, 매개변수의 개수나 타입, 순서를 다르게 해서 메소드를 정의하는 것

   		< 생성자 오버로딩 (Constructor Overloading) >
  		: 객체를 생성할 때 외부값으로 객체를 초기화할 필요가 있으나 외부 값이 어떤 타입으로 몇 개 제공할지 모르므로
		  생성자 오버로딩을 통해 다양화

< Car01 Class >
```java
import java.util.*;

/*
 * Car Class (자동차 관련 정보, 기능 제공 클래스)
 */

public class Car01 {
	/*
	 * field
	 *   : [Access Modifier] [(속성) Modifier] Type field_name[= Initial Value];
	 */
	
	// 모든 field에 대해 private 접근 제한자 설정
	private String brand; // 브랜드 
	private int price; // 가격
	private String color; // 색상
	private double fuel; // 연비
	private Date mDate; // 제조일자 
	private char grade; // 등급
	private boolean isAirbag; // 에어백 장착 유무

	/*
	 * Constructor : 필드의 초기화를 주로 담당
	 *   : [Access Modifier] Class_name(argument list) {
	 *   			[Statement;]
	 *   	}
	 *     * Default Constructor : 매개변수가 없는 기본 생성자
	 *     	  : Car01() { // Default Constructor
	 *
  	 *	      }
	 */
	
	Car01() { // Default Constructor
		this(null, 0, null, 0.0, null, ' ', false); // 매개변수 크기, 타입, 순서에 맞는 오버로딩된 생성자로 이동
		 // Line 59 생성자 호출
	}
	
	/*
	 * (Method/Constructor) Overloading(오버로딩)
	 *   - 동일 클래스 내 생성자 또는 메소드에서 동일한 이름의 메소드가 존재하지 못하는데,
	 *     매개변수의 개수, 타입, 순서를 다르게 하여 메소드를 정의하는 것
	 */
	Car01(String brand) { // 매개변수가 있는 생성자 (=> 기본생성자가 생성되지 않음) (생성자 오버로딩 : Constructor Overloading)
		this(brand, 0); // Car01(String brand, int price) 호출
	}

	
	Car01(String brand, int price) { // 매개변수가 다른 생성자 (생성자 오버로딩 : Constructor Overloading)
		this(brand, price, null); // Car01(String brand, int price, String color) 호출
		//this.brand = brand; // brand 값 입력
		//this.price = price; // price 값 입력
	}
	
	Car01(String brand, int price, String color) { // 매개변수가 3개인 생성자 (생성자 오버로딩 : Constructor Overloading)
		this(brand, price, color, 0.0, null, ' ', true); // Line 59 생성자 호출
	}
	
	Car01(String brand, int price, String color, double fuel, Date mDate, char grade, boolean isAirbag) {
		this.brand = brand;
		this.price = price;
		this.color = color;
		this.fuel = fuel;
		this.mDate = mDate;
		this.grade = grade;
		this.isAirbag = isAirbag;
	}
	/*
	 * method 
	 *   : [Access Modifier] [(속성) Modifier] ReturnType method_name(argument list) {
	 *   		Statement;
	 *   		return ~;
	 *   	}
	 */
	
	/*
	 * getter와 setter
	 */
	
	// Getter : 접근 제한자로 제한이 설정된 field의 값을 전달해주는 메서드
	// method 1 : return o, argument x
	/*
	 * brand값을 얻어오는 메서드 
	 */
	String get_brand() {
		return brand;
	}
	
	/*
	 * price값을 얻어오는 메서드
	 */
	int get_price() {
		return price;
	}
	
	/*
	 * color값을 얻어오는 메서드
	 */
	String get_color() {
		return color;
	}
	
	/*
	 * fuel값을 얻어오는 메서드
	 */
	double get_fuel() {
		return fuel;
	}
	
	/*
	 * mDate값을 얻어오는 메서드
	 */
	Date get_mDate() {
		return mDate;
	}
	
	/*
	 * grade값을 얻어오는 메서드
	 */
	char get_grade() {
		return grade;
	}
	
	/*
	 * isAirbag값을 얻어오는 메서드
	 */
	boolean isAirbag() { // boolean형은 get 표기보다는 is(--) 형태로 표기하는 것이 관례
		return isAirbag;
	}
	
	// Setter : 접근 제한자로 제한이 설정된 field의 값을 전달받는 메서드
	// method 2 : return x, argument x
	/*
	 * color값을 설정하는 메서드
	 */
	void set_color() {
		color = "Silver"; // color 값을 초기값인 null에서 sliver로 변경
	}
	/*
	 * 매개변수 col 값을 전달받아 color 값을 설정하는 메서드
	 * method 3 : return x, argument o
	 */
	 
	 /* 	  
	  *  brand 값을 설정하는 메서드
	  */  
	 void set_brand(String bra) {
	  	   brand = bra; // 매개변수로 전달받은 bra을 brand field에 저장 (메소드를 통해서 저장하므로)

	 }
	 
	 /* 	  
	  *  price 값을 설정하는 메서드
	  */  
     void set_price(int pri) {
  	   	   price = pri; // 매개변수로 전달받은 pri을 price field에 저장 (메소드를 통해서 저장하므로)
	  	   // 매개변수명과 필드명이 같아질 경우 : price = price;로 작성하게 되면, 매개변수 brand로 인식하게 되어 매개변수에 값 저장
	  	   // this.brand = brand; 로 작성 (this)
      }
     
     /* 
      * this 
	  *  - 객체(인스턴스) 자신의 참조(번지)를 가지고 있는 키워드
	  *  - 객체 내부에서 인스턴스 멤버임을 확실하게 하기 위해 this. 사용
      *  - 매개변수와 필드명이 동일할 때 인스턴스 필드임을 명확하게 하기 위해 사용
      */
     
	 /* 	  
	  *  color 값을 설정하는 메서드
	  */  
     void set_color(String color) {
  	   	   this.color = color; // 매개변수로 전달받은 color을 클래스로부터 생성된 객체(이를 가리키는 this)에 저장 (메소드를 통해서 저장하므로)
     }
     
	 /* 	  
	  *  fuel 값을 설정하는 메서드
	  */  
     void set_fuel(double fuel) {
  	   	   this.fuel = fuel; // 매개변수로 전달받은 fuel을 클래스로부터 생성된 객체(이를 가리키는 this)에 저장 (메소드를 통해서 저장하므로)
      }
     
	 /* 	  
	  *  grade 값을 설정하는 메서드
	  */  
     void set_grade(char grade) {
  	   	   this.grade = grade; // 매개변수로 전달받은 grade을 클래스로부터 생성된 객체(이를 가리키는 this)에 저장 (메소드를 통해서 저장하므로)
      }

	 /* 	  
	  *  isAirbag 값을 설정하는 메서드
	  */  
     void set_isAirbag(boolean isAirbag) {
  	   	   this.isAirbag = isAirbag; // 매개변수로 전달받은 isAirbag을 클래스로부터 생성된 객체(이를 가리키는 this)에 저장 (메소드를 통해서 저장하므로)
      }

	/*
	 *    Date 객체 d의 값을 받아서 설정하는 메서드
	 */
	 void set_mDate(Date mDate) {
	  	   this.mDate = mDate; // Date 객체 mDate를 받아 클래스로부터 생성된 객체(이를 가리키는 this)의 mDate에 참조시킴
	 }
	 
	// Overloading
	// method 3 : return x, argument o
	/*
	 * color값을 설정하는 메서드
	 */
	
	/*
	 * private field들을 출력하는 method 
	 */
	void get_field() {
		System.out.println("Car Class Brand : " + brand);
		System.out.println("Car Class Price : " + price);
		System.out.println("Car Class Color : " + color);
		System.out.println("Car Class Fuel : " + fuel);
		System.out.println("Car Class Date : " + mDate);
		System.out.println("Car Class Grade : " + grade);
		System.out.println("Car Class Airbag (True : o, False : x) : " + isAirbag);
	}
	
}
```

< Main >
```java
import java.util.Date;

/*
 * Car Class - Main method
 */
public class Car01_Main {
	public static void main(String[] args) {
		/*
		 * Car Class Instance 생성
		 */
		Car01 car = new Car01(); // Car01() { } 생성자로 이동 - this(...)로 해당 생성자로 이동
		// Car01 car = new Car01("C"); // 매개변수가 String brand인 생성자 호출(기본 생성자는 매개변수가 있는 생성자에 의해 컴파일러가 생성하지 않음)
		// Car01 car = new Car01("B", 1500); // 매개변수가 String brand, int price인 생성자가 호출
		// Car01 car = new Car01("A", 2500, "White"); // 매개변수가 String brand, int price, String Color인 생성자 호출
		// Car01 car = new Car01("A", 2500, "White", 0.5, null, 'B', true); // 매개변수가 String brand, int price, String Color인 생성자 호출
		System.out.println(car); // Car 클래스에서 생성한 객체의 주소 (ch06.Car01[패키지.클래스]@76a3e297[16진수]) : HashCode
		
		/*
		 * Class Field에 private 접근제한자 설정 : field는 Car01 클래스 내에서만 사용 가능하므로 다른 클래스인 Main()에서 사용 불가
		 *  -> Car01 Class getter method()를 통해 데이터 확인 가능 : 같은 클래스 내에서 사용 가능하므로 이를 이용
		 */
		
		// String brand = car.brand; // brand는 Main method()에서 선언한 변수, car.brand는 car 클래스의 필드

		/*
		 * System.out.println("Car Brand : " + car.brand); // String 초기값 : null
		 * System.out.println("Car Price : " + car.price); // int 초기값 : 0
		 * System.out.println("Car Color : " + car.color); // String 초기값 : null
		 * System.out.println("Car Fuel : " + car.fuel); // double 초기값 : 0.0
		 * System.out.println("Car Date : " + car.mDate); // Class의 초기값 : null
		 * System.out.println("Car Grade : " + car.grade); // char 초기값 : ' '
		 * System.out.println("Car Airbag (True : o, False : x) : " + car.isAirbag); // boolean 초기값 : false
		 * 
		 */
		
		// Car class getter 함수
		car.get_field(); // Car Class private field에 접근하여 값을 얻어오는 메서드 출력
		System.out.println();
		System.out.println("------getter method------");
		System.out.println("Car Class Brand : " + car.get_brand()); // Car Class 내 Brand를 출력해주는 메서드 출력
		System.out.println("Car Class Price : " + car.get_price()); // Car Class 내 Price를 출력해주는 메서드 출력
		System.out.println("Car Class Color : " + car.get_color()); // Car Class 내 Color를 출력해주는 메서드 출력
		System.out.println("Car Class Fuel : " + car.get_fuel()); // Car Class 내 Fuel를 출력해주는 메서드 출력
		System.out.println("Car Class Date : " + car.get_mDate()); // Car Class 내 mDate를 출력해주는 메서드 출력
		System.out.println("Car Class Grade : " + car.get_grade()); // Car Class 내 Grade를 출력해주는 메서드 출력
		System.out.println("Car Class isAirbag(True : o, False : x) : " + car.isAirbag()); // Car Class 내 isAirbag를 출력해주는 메서드 출력
		
		System.out.println();
		System.out.println("------get_color method------");
		System.out.println("Car Class Color Before : " + car.get_color()); // 변경 전 Car Class 내 Color를 출력해주는 메서드 출력 (null)
		car.set_color();
		System.out.println("Car Class Color After : " + car.get_color()); // 변경 후 Car Class 내 Color를 출력해주는 메서드 출력 (Silver)
		car.set_color("Black");
		System.out.println("Car Class Color After 2 : " + car.get_color()); // 변경 후 Car Class 내 Color를 출력해주는 메서드 출력 (Silver)
		System.out.println();
		
		// Date Setter Method
		System.out.println("------setter method------");
		car.set_brand("A");
		car.set_price(3000);
		car.set_fuel(15.9);
		car.set_grade('B');
		car.set_isAirbag(true);
		System.out.println("Car Class Brand After : " + car.get_brand()); // 초기값 : null
		System.out.println("Car Class Price After : " + car.get_price()); // 초기값 : null
		System.out.println("Car Class Color After : " + car.get_color()); // 초기값 : null
		System.out.println("Car Class Fuel After : " + car.get_fuel()); // 초기값 : null
		System.out.println("Car Class Grade After : " + car.get_grade()); // 초기값 : null
		System.out.println("Car Class isAirbag After : " + car.isAirbag()); // 초기값 : null

		Date mdate = new Date(); // Date클래스 객체 생성하여 참조변수 mDate에 참조
		car.set_mDate(mdate); // Date클래스를 통해 생성된 객체를 참조하는 참조변수 mdate를 인자로 Car Class mDate 값으로 설정
		System.out.println("Car Class Date After : " + car.get_mDate());
	}
}
```

< Car02 Class>
```java
public class Car02 {
	/*
	 * field
	 *  = [Access Modifier][Modifier]DateType Field_name[=value];
	 */
	String brand; // 브랜드 
	int price; // 가격
	double fuel; // 연비
	
	/*
	 * Constructor
	 *  = [Access Modifier] Class_name(argument_list) {
	 *
	 * 		}
	 */
	Car02() { // 기본 생성자
		this(null, 0, 0.0); // Line 22 호출 (this() 생성자)
	}
	
	Car02(String brand) { // 매개변수 1개(brand) 생성자
		this(brand, 0, 0.0); // Line 27 호출 (this() 생성자)
	}
	
	Car02(String brand, int price) { // 매개변수 2개(brand, price) 생성자
		this(brand, price, 0.0); // Line 30 호출 (this() 생성자)
	}
	
	Car02(String brand, int price, double fuel) { // 매개변수 3개(brand, price, fuel) 생성자
		this.brand = brand;
		this.price = price;
		this.fuel = fuel;  // 엄연히 따지면, 최종 초기화를 시켜주는 생성자
	}
    
    	Car02(int price, String brand, double fuel) { // 매개변수 3개(brand, price, fuel)이나 Line 30과 매개변수 순서가 다르므로 생성자 오버로딩
		this.brand = brand;
		this.price = price;
		this.fuel = fuel;  // 엄연히 따지면, 최종 초기화를 시켜주는 생성자
	}
    
	/*
	 * method
	 *   = [Access Modifier][Modifier] Method_name(argument_list) {
	 * 			Statement;
	 * 			return ~;
	 * 		}

	 */
}
```

< Car02 Main >
```java
package ch06;

/*
 * Car02 Class Main
 */
public class Car02_Main {
	public static void main(String[] args) {
		Car02 car = new Car02(); // Car02 Class의 Car02() 생성자 실행
		Car02 car_1 = new Car02("A"); // Car02 Class의 Car02(String brand) 생성자 실행
		Car02 car_2 = new Car02("B", 2300); // Car02 Class의 Car02(String brand, int price) 생성자 실행
		Car02 car_3 = new Car02("C", 2400, 0.4); // Car02 Class의 Car02(String brand, int price, double fuel) 생성자 실행
		
		System.out.println("------------Car02 Constructor()---------------");
		System.out.println("Car02() brand = " + car.brand);
		System.out.println("Car02() price = " + car.price);
		System.out.println("Car02() fuel = " + car.fuel);
		System.out.println();
		System.out.println("------------Car02 Constructor(String brand)---------------");
		System.out.println("Car02(String brand) brand = " + car_1.brand);
		System.out.println("Car02(String brand) price = " + car_1.price);
		System.out.println("Car02(String brand) fuel = " + car_1.fuel);
		System.out.println();
		System.out.println("------------Car02 Constructor(String brand, int price)---------------");
		System.out.println("Car02(String brand, int price) brand = " + car_2.brand);
		System.out.println("Car02(String brand, int price) price = " + car_2.price);
		System.out.println("Car02(String brand, int price) fuel = " + car_2.fuel);
		System.out.println();
		System.out.println("------------Car02 Constructor(String brand, int price, double fuel)---------------");	
		System.out.println("Car02(String brand, int price, double fuel) brand = " + car_3.brand);
		System.out.println("Car02(String brand, int price, double fuel) price = " + car_3.price);
		System.out.println("Car02(String brand, int price, double fuel) fuel = " + car_3.fuel);
		System.out.println();
	}
}
```

