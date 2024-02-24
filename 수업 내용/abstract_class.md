-----
### 추상 클래스
-----
1. 클래스가 설계도라면 추상클래스는 ‘미완성 설계도’
2. 일반메서드가 추상메서드를 호출할 수 있음.(호출할 때 필요한 건 선언부)
3. 완성된 설계도가 아니므로 인스턴스를 생성할 수 없음
4. 여러 클래스에 공통적으로 사용될 수 있는 추상클래스를 바로 작성하거나 기존클래스의 공통 부분을 추출한 클래스

5. 추상(Abstract) : 실체들 간 공통되는 특성을 추출한 것

       예) 새, 곤충, 물고기 (구체적) → 동물 (추상)

6. 추상 클래스(Abstract Class) : 추상 메서드를 가지는 클래스

    + 실체 클래스들의 공통되는 필드와 메소드를 정의한 클래스
    + 추상 클래스는 실체 클래스의 부모 클래스 역할 (단독적인 객체는 될 수 없음)

7. 주로 실체 클래스의 설계 규격을 만들고자 할 때, 실체 클래스가 가져야 할 필드와 메서드를 추상 클래스에 미리 정의
8. 실체 클래스는 추상 클래스를 무조건 상속 받아 작성

<div align = "center">
<img width="306" alt="다운로드 (7)" src="https://github.com/sooyounghan/JAVA/assets/34672301/059b8ed4-094d-4626-82e9-09918c578489">
</div>

-----
### 추상 클래스 선언
-----
1. 클래스 선언에 abstract 키워드 사용
2. new 연산자로 객체를 생성하지 못하고 상속을 통해 자식 클래스만 생성 가능

-----
### 추상 메서드
-----
1. 선언부만 있고 구현부(몸통, body)가 없는 메서드
2. 메서드 이름은 동일하지만, 실행 내용이 실체 클래스마다 다른 메서드
3. 구현 방법
    - 추상 클래스에서는 메서드의 선언부만 작성 (추상 메서드)
    - 실체 클래스에서는 메서드의 실행 내용 작성 (Overriding)

<div align = "center">
<img width="442" alt="다운로드 (8)" src="https://github.com/sooyounghan/JAVA/assets/34672301/a1fad136-a45f-4458-a82b-b6724d7ae420">
</div>

< 예제 >
```java
/*
 * Animal Class (Abstract Class)
 */
public abstract class Animal {
	/*
	 * Field
	 */
	String kind; // 종류
	
	/*
	 * Abstract Class
	 */
	abstract void sound(); // 울음소리
}
```
```java
/*
 * Cat Class : inherited abstract Animal class
 */
public class Cat extends Animal {
	/*
	 * Field
	 */
	
	/*
	 * Constructor
	 */
	Cat() {
		this.kind = "Cat";
	}
	
	/*
	 * Method : Method, Abstract Method를 구현해야 되는 메서드
	 */
	
	void breathe() {
		System.out.println("Cat Breathe()");
	}
	
	@Override 
	public void sound() {
		System.out.println("Mww");
	}
}
```
```java
/*
 * Dog Class : inherited abstract Animal class
 */
public class Dog extends Animal {
	/*
	 * Field
	 */
	
	/*
	 * Constructor
	 */
	Dog() {
		this.kind = "Dog";
	}
	
	/*
	 * Method : Method, Abstract Method를 구현해야 되는 메서드
	 */
	
	@Override 
	public void sound() {
		System.out.println("Bawl");
	}
}
```

```java
/*
 * Cat, Dog (inherited abstract Animal Class) Class의 실행 클래스
 */
public class Animal_Main {
	public static void main(String[] args) {
		/*
		 * main Method는 static method이므로 객체를 생성하지 않고, animal Sound를 호출하려면, animal sound도 static 설정
		 */
		animalSound(); // static method
		
		Animal_Main animal_main = new Animal_Main(); // animal_sound는 instance method 이므로 이에 해당하는 클래스의 객체 생성 후 메서드 사용 가능
		animal_main.animal_sound(); // Instance Method
		
		Cat cat = new Cat();
		System.out.println("Cat Kind = " + cat.kind);
		cat.breathe();
		cat.sound();
		Animal_Main.animalSound(cat); // Cat 클래스 타입 참조변수이며, Cat 객체로 가리킴 static method (Line 46)
		Animal animal_cat = new Cat(); // Animal 클래스타입 참조변수로, Cat 객체를 가리킴 
		Animal_Main.animalSound(animal_cat); // Animal 클래스타입 참조변수로, Cat 객체로 가지는 static method (Line 58)
		
		Dog dog = new Dog();
		System.out.println("Dog Kind = " + dog.kind);
		dog.sound();
		Animal_Main.animalSound(dog); // Dog 클래스 타입 참조변수이며, Dog 객체로 가리킴 static method (Line 53)
		Animal animal_dog = new Dog();// Animal 클래스타입 참조변수로, Dog 객체를 가리킴 
		Animal_Main.animalSound(animal_dog); // Animal 클래스타입 참조변수로, Dog 객체로 가지는 static method	
	}
	
	void animal_sound() {
		/*
		 * Instance Method
		 */
		System.out.println("Animal Sound");
	}
	public static void animalSound() {
		/*
		 * Static Method
		 */
		System.out.println("Animal Sound");
	}
	
	// 매개변수 : Cat 객체
	public static void animalSound(Cat cat) {
	 	 System.out.println("Mww");
	}
	 
	 
	// 매개변수 : Dog 객체
	public static void animalSound(Dog dog) {
	 	 System.out.println("Bawl");
	}

	
	// 매개변수 : Animal 객체 (다형성)
	public static void animalSound(Animal animal) {
		animal.sound();
	}
}
```
-----
### 인터페이스 (Interface)
-----
<div align = "center">
<img width="285" alt="다운로드 (10)" src="https://github.com/sooyounghan/JAVA/assets/34672301/725eeb13-21e6-4982-be24-a4f29650036e">
</div>

1. 개발 코드와 객체가 서로 통신하는 접점으로, 개발 코드는 인터페이스의 메서드만 알고 있으면 됨
2. 인터페이스는 생성자 / 일반 메서드를 가질 수 없음
3. 구현 키워드 : implements

        class Class_name implements Interface_name {
          @Override
          추상메서드 ->  모두 구현 필요 (하나라도 구현되지 않으면, 이 클래스는 abstract class가 됨)
        }

4. 접근제어자 :  public / (default)
5. 상수 부분(선택) : [public static final] DataType Field_name(UpperCase) [= initial Value];
6. 추상 메서드 부분 : [public abstract] return_type abstract_method_name(argument list);
     - 인터페이스의 메소드는 기본적으로 실행 블록이 없는 추상 메서드로 선언
     - Interface의 메서드는 추상 메서드로 인식하며, 구현부(Body)를 가질 수 없음

7. 특징
     - 코드의 종속성(각 메서드간의 결합도를 의미)을 낮춰줌
       > 인터페이스를 활용하면, 하나의 메서드를 수정하면 다른 메서드를 변경해야하는 상황을 줄여줌

     - 인터페이스는 다중 상속이 가능 (implements Interface1, Interface2, ... Interface n)
       > class Class_name extends Parent_Class_name implements Interface1, Interface2, ... Interface n { ... }
<div align = "center">
<img width="346" alt="다운로드 (12)" src="https://github.com/sooyounghan/JAVA/assets/34672301/a5b5d1f7-48aa-4921-881c-744975d52187">
</div>

     - 외부적으로는 interface의 기능을 호출하면, interface의 메서드를 호출하나, 

     - 내부적으로 인터페이스의 구현클래스의 구현 메서드가 호출(동작)

-----
### 추상 클래스와 인터페이스 
-----
<div align = "center">
<img width="526" alt="다운로드 (9)" src="https://github.com/sooyounghan/JAVA/assets/34672301/076f9995-706f-4324-8ae6-b387ac53dffc">
</div>

-----
### Default Method
-----
<div align = "center">
<img width="274" alt="다운로드 (14)" src="https://github.com/sooyounghan/JAVA/assets/34672301/09ed637f-4dc5-4243-be94-7b4a08b1b55f">
</div>

1. 인터페이스에서 제공해주는 기본적인 구현 기능을 가진 메서드

       < 이름이 중복되어 충돌되는 경우 >
        1. 여러 인터페이스의 디폴트 메서드 충돌 : 인터페이스를 구현한 클래스에서 Default Method 오버라이딩
        2. 디폴트 메서드와 조상 클래스 메서드 간의 충돌 : 조상 클래스 메서드가 상속되고, 디폴트 메서드 무시

2. 실행 블록을 가지고 있는 메서드이지만, default 키워드를 반드시 붙이고 있어야함

        기본적으로 public 접근 제한이며, 생략하더라도 컴파일 과정에서 자동적으로 붙음

3. 인터페이스만으로 사용 불가 (Default Method는 인스턴스 메서드)

       인터페이스를 구현한 클래스 타입의 객체로 호출 가능
       (즉, 인터페이스의 기본 구현을 담당하는 것이긴 하나 이는 static이 아니므로, 이를 구현한 클래스에서
        객체를 생성해야 접근 가능)

-----
### Static Method
-----
<div align = "center">
<img width="320" alt="다운로드 (15)" src="https://github.com/sooyounghan/JAVA/assets/34672301/32002dff-c263-4589-83fd-32fba876b000">
</div>

1. 인터페이스 명을 이용해 직접 호출 가능 : 인터페이스명.메서드명();

       예) RemoteControl.changeBattery();

2. static method는 이미 메서드가 정의되어 있고, 클래스 로더에 의해 이미 로딩되었으므로 재정의 불가

< 예제 >
```java
/*
 * RemoteControl Interface 
 */
public interface RemoteControl {
	// 인터페이스는 생성자를 가질 수 없음
	
	/*
	 * 상수 (public static final Field)
	 * - 상수 : [public static final] DataType Field_name(UpperCase) [= initial Value];
	 */
	public static final int MAX_VOLUME = 10;
	public static final int MIN_VOLUME = 0;
	
	/*
	 * 추상 메서드(public abstract)
	 *  - 추상메서드 : [public abstract] return_type abstract_method_name(argument list);
	 *  - Interface의 메서드는 추상 메서드로 인식하며, 구현부(Body)를 가질 수 없음
	 */
	public abstract void setVolume(int volume);
	public abstract void turnOn();
	public abstract void turnOff();
	
	/*
	 * 정적 메서드, Static Method : [public] static ReturnType method_name(arguement_list) { ... }
	 *   - 인터페이스명.메서드명()
	 *   - static method이므로 메서드가 이미 정의되어있으므로 이를 반드시 재정의할 필요는 없음
	 */
	public static void changeBattery() {
		System.out.println("Battery Change");
	}
    
    /*
	 * Default Method : [public] default ReturnType Method_name(argument_list) { ... }
	 *   - 인터페이스에서 제공해주는 기본적인 구현 기능을 가진 메서드
	 */
	default void defaultMethod() {
		System.out.println("Remote Control defaultMethod()");
	}
}
```
```java
public interface Searchable {
	/*
	 *  < Interface >
	 * 1. 상수(Constant) : [public static final] DataType FIELD_NAME [= initial value];
	 * 2. 생성자 / 일반 메서드는 존재하지 않음
	 * 3. 추상 메서드 : [public abstract] ReturnType abstract_method_name(argument_list);
	 * 
	 */
	
	void search(String url); // 추상 메서드 [public abstract]
}
```
```java
/*
 * RemoteControl Interface의 구현하는 Tv Class
 *  - 인터페이스는 다중 상속이 가능 (implements Interface1, Interface2, ... Interface n)
 *  - class Class_name extends Parent_Class_name implements Interface1, Interface2, ... Interface n { ... }
 */
public class Tv implements RemoteControl, Searchable {
	/*
	 * RemoteControl에 존재하는 추상메서드 setVolume, turnOn, turnOff 메서드를 반드시 Overriding
	 * 추가적으로, Tv Class는 Searchable Interface도 상속받음
	 */
	// 인터페이스를 이 클래스로부터 구현되기 때문에, RemoteControl interface의 상수도 상속받음
	public int volume; // Tv Class int 변수 volume 선언
	
	// RemoteControl Interface의 모든 abstract method에 대해 구현되는 클래스에서 Overriding (하나라도 되지 않으면, 이 클래스는 abstract class로 취급)

	@Override
	public void setVolume(int volume) {
		if(volume > RemoteControl.MAX_VOLUME) { // 이 클래스에서 생성된 객체에 볼륨에 대해 RemoteControl.MAX_VOLUME(이를 포함하고 있는 인터페이스의 상수)보다 크면,
			this.volume = RemoteControl.MAX_VOLUME; // RemoteControl.MAX_VOLUME보다 더욱 커질 수 없으므로 최대 볼륨으로 설정
		}
		else if(volume < RemoteControl.MIN_VOLUME) { // 이 클래스에서 생성된 객체에 볼륨에 대해 RemoteControl.MIN_VOLUME(이를 포함하고 있는 인터페이스 상수)보다 작으면,
			this.volume = RemoteControl.MIN_VOLUME; // RemoteControl.MAX_VOLUME보다 더욱 작아질 수 없으므로 최소 볼륨으로 설정
		}
		else {
			// RemoteControl.MIN_VOLUME <= this.volume <= RemoteControl.MAX_VOLUME 이면,
			// 매개변수로 받은 volume을 클래스 내 참조하는 객체에 volume에 저장
			this.volume = volume;
		}
	}
	
	@Override
	public void turnOn() {
		System.out.println("TV turnOn()");
	}
	
	@Override
	public void turnOff() {
		System.out.println("TV turnOff()");
	}
	
	// Searchable Interface의 abstract method인 search method 오버라이딩
	@Override
	public void search(String url) {
		System.out.println("Search URL = " + url);
	}
    
	@Override
	public void defaultMethod() {
		// RemoteControl.super.defaultMethod();
		System.out.println("Tv Class DefaultMethod()");
	}
}
```
```java
/*
 * RemoteControl Interface의 구현하는 Tv Class
 *  - 인터페이스는 다중 상속이 가능 (implements Interface1, Interface2, ... Interface n)
 *  - class Class_name extends Parent_Class_name implements Interface1, Interface2, ... Interface n { ... }
 */
public class Audio implements RemoteControl {
	/*
	 * RemoteControl에 존재하는 추상메서드 setVolume, turnOn, turnOff 메서드를 반드시 Overriding
	 */
	// 인터페이스를 이 클래스로부터 구현되기 때문에, RemoteControl interface의 상수도 상속받음
	public int volume; // Tv Class int 변수 volume 선언
	
	// Interface의 모든 abstract method에 대해 구현되는 클래스에서 Overriding (하나라도 되지 않으면, 이 클래스는 abstract class로 취급)

	@Override
	public void setVolume(int volume) {
		if(volume > RemoteControl.MAX_VOLUME) { // 이 클래스에서 생성된 객체에 볼륨에 대해 RemoteControl.MAX_VOLUME(이를 포함하고 있는 인터페이스의 상수)보다 크면,
			this.volume = RemoteControl.MAX_VOLUME; // RemoteControl.MAX_VOLUME보다 더욱 커질 수 없으므로 최대 볼륨으로 설정
		}
		else if(volume < RemoteControl.MIN_VOLUME) { // 이 클래스에서 생성된 객체에 볼륨에 대해 RemoteControl.MIN_VOLUME(이를 포함하고 있는 인터페이스 상수)보다 작으면,
			this.volume = RemoteControl.MIN_VOLUME; // RemoteControl.MAX_VOLUME보다 더욱 작아질 수 없으므로 최소 볼륨으로 설정
		}
		else {
			// RemoteControl.MIN_VOLUME <= this.volume <= RemoteControl.MAX_VOLUME 이면,
			// 매개변수로 받은 volume을 클래스 내 참조하는 객체에 volume에 저장
			this.volume = volume;
		}
	}
	
	@Override
	public void turnOn() {
		System.out.println("Audio turnOn()");
	}
	
	@Override
	public void turnOff() {
		System.out.println("Audio turnOff()");
	}
}
```
```java
/*
 * RemoteControl / Searchable Interface의 기능을 사용하는 실행클래스 (Main Class)
 */
public class RemoteControl_Main {
	public static void main(String[] args) {
		// 인터페이스타입 인터페이스참조변수명 = new 인터페이스구현클래스();
		RemoteControl remote_control = new Tv();
		
		System.out.println("remote_control.MIN_VOLUME : " + remote_control.MIN_VOLUME);
		System.out.println("remote_control.MAX_VOLUME : " + remote_control.MAX_VOLUME);
		remote_control.turnOn();
		
		// volume은 Tv 클래스 내의 필드이므로, 인터페이스 참조 변수 remote_control를 tv 클래스 타입으로 강제 형변환
		Tv tv = (Tv)remote_control;
		
		// 외부적으로는 interface의 기능을 호출하면, interface의 메서드를 호출하나, 내부적으로 인터페이스의 구현클래스의 구현 메서드가 호출(동작)
		remote_control.setVolume(-123);
		System.out.println(tv.volume);
		remote_control.setVolume(100);
		System.out.println(tv.volume);
		remote_control.setVolume(5);
		System.out.println(tv.volume);
		
		remote_control.turnOff();
		
		// 인터페이스 참조 변수 rc는 Audio 클래스에서 생성된 객체를 가리킴
		RemoteControl rc = new Audio();
		// volume 변수는 Audio 클래스 내 Field이므로 이에 접근하기 위해서는 Audio 구현 클래스 타입으로 Down-Casting
		Audio audio = (Audio) rc;
		
		rc.turnOn();
		
		rc.setVolume(-122);
		System.out.println(audio.volume);
		rc.setVolume(120);
		System.out.println(((Audio)rc).volume); // Line 36과 같은 의미
		rc.setVolume(7);
		System.out.println(audio.volume);
		
		rc.turnOff();
		
		System.out.println("---------------------------------------");
		Searchable sc = new Tv();
		RemoteControl smartTv = new Tv();
		
		smartTv.turnOn();
		smartTv.turnOff();
		
		// 프로토콜://ip주소:포트번호/컨텍스트패스/디렉토리
        // 프로토콜://ip주소:포트번호/경로/파일.확장자
		sc.search("http://www.naver.com");
		
		// 인터페이스 내 static method이므로 인터페이스명.메서드명()으로 접근
		RemoteControl.changeBattery();
		
		// defaultMethod를 구현한 클래스 타입의 참조 변수로 호출 가능 (인터페이스의 default method vs 구현된 클래스에서 default method overriding -> 후자 출력)
 		smartTv.defaultMethod();
	}
}
```

-----
### 익명 객체
-----
1. 명시적인 구현 클래스 작성을 생략하고, 바로 객체를 생성하는 방법
2. 이름 없는 구현 클래스 선언과 동시에 객체 생성        
3. 인터페이스 추상 메서드들을 모두 재정의하는 실체 메서드가 있어야함
4. 추가적으로 필드와 메서드 선언은 가능하나 익명 객체 안에서만 사용 가능
5. 인터페이스 변수로 접근 불가
   
<div align = "center">
<img width="249" alt="다운로드 (13)" src="https://github.com/sooyounghan/JAVA/assets/34672301/a82495e0-0d05-4b5d-a3af-88966b37a7fd">
</div>

-----
### 응집도와 결합도
-----
1. 응집도 
    - 클래스의 모듈 내부의 기능이 얼마나 밀접하게 관련되어 있는가?
    - 응집도가 높을수록, 각 클래스나 모듈이 하나의 기능을 가짐

2. 결합도
    - 클래스-클래스 간의 관계 또는 메서드 - 메서드 간의 관계들이 얼마나 강하게 연결되어있는가?
    - 결합도는 낮을 수록 각 클래스는 독립적, 변경이 발생되면 다른 클래스에 미치는 영향 적어짐
      * 좋은 소프트웨어 : 응집도는 높이고, 결합도는 낮춤

-----
### 인터페이스의 장점
----- 
1. 인터페이스는 변경에 강하고, 확장에는 열려있고 변경에는 닫혀있음 (개방-폐쇄의 원칙)
2. 호환성을 높임

< 예제 >
```java
/*
 * Payment interface (카드결제, 현금결제 등 결제 시스템 구현)
 */
public interface Payment {
	/*
	 * Constant
	 */
	
	/*
	 * Abstract Method
	 */
	public abstract void pay(int amount); // 금액을 지불하는 추상메서드
}
```
```java
/*
 * Card 결제 클래스
 */

public class CardPay implements Payment {
	@Override
	public void pay(int amount) {
		System.out.println("Card Pay = " + amount);
	}
}
```
```java
/*
 * 현금 결제
 */
public class CashPay implements Payment{
	@Override
	public void pay(int amount) {
		System.out.println("Cash Pay = " + amount);
	}
}
```
< Shop 클래스 - 인터페이스의 개방/폐쇄의 원칙과 낮은 결합도를 보여주는 예 >
```java
/*
 * 상점 클래스
 *   - 판매, 구매 기능을 필요
 *   - Payment Interface에 의존 (Dependency)해 결합도를 낮춤 
 *   - 인터페이스를 구현한 클래스의 메서드가 오버라이딩 되지 않더라도 이를 이용하는 입장에서는 영향을 받지 않음
 *   - User : Shop / - 중개자 : Payment Interface - / Provider : (CardPay, CashPay)
 */
public class Shop {	
	// Field
	private Payment payment; // 지불 방식 (null) : Interface 타입을 통해 이를 구현한 다양한 클래스 객체 접근 가능
	
	// Constructor
	public Shop(Payment payment) {
		this.payment = payment;
	}
	
	// Method 
	public void pay(int amount) {
		payment.pay(amount); // 결합도가 낮아짐 (Interface를 구현한 CardPay와 CashPay의 내용이 바뀌더라도 본 메서드에서는 영향을 받지 않음)
	}
}
```
