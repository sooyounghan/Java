-----
### 접근 제어자 (Access Modifier)
-----
1. 멤버 또는 클래스에 사용되어, 외부로부터 접근을 제한 → 외부로부터 데이터를 보호(외부에 불필요한, 내부적으로 사용되는 데이터를 감추기 위함) [캡슐화]
2. 접근 제어자가 사용될 수 있는 곳 : 클래스, 멤버변수, 메서드, 생성자

        1. private : 같은 클래스 내에서 접근 가능
        2. default : 같은 패키지 내에서 접근 가능
        3. protected : 같은 패키지 내, 다른 패키지 자손 클래스에서 접근 가능
          * 같은 패키지 : default와 동일
          * 다른 패키지 : 자식 클래스만 접근 가능 (= 자식 클래스에 대한 접근은 가능하지만, 객체에 대한 참조 변수에 대한 접근은 제한)
        4. public : 접근 제한이 전혀 없음

3. 접근 제어자 선언

       클래스 앞에 접근 제어자 선언 : public, (default)만 가능
       필드 앞에 접근 제어자 선언 : public, protected, (default), private 가능
       메서드 앞 접근 제어자 선언 : public, protected, (default), private 가능
       생성자 앞 접근 제어자 선언 : public, portected, (default), private 가능 → 일반적으로 클래스의 접근 제어자와 일치

4. 생성자의 접근제어자 : 일반적으로 생성자의 접근 제어자는 클래스의 접근 제어자와 일치하나 생성자에 접근 제어자를 사용해 인스턴스 생성 제한 가능

<Singleton>
<div align = "center">
<img width="423" alt="다운로드 (2)" src="https://github.com/sooyounghan/JAVA/assets/34672301/6f279d4e-a16c-41b4-b7a3-9b5a09fa7119">
</div>
  
< 예제 >
```java
package ch06_ch07;

/*
 * Access Modifier
 *  - private : 같은 클래스 내 접근 가능
 *  
 *  - (default) : 같은 패키지 내 접근 가능
 *  
 *  - protected : 같은 패키지 내, 다른 패키지에서는 자손 클래스에서 가능
 *   * 같은 패키지 : (default)와 동일
 *   * 다른 패키지 : 자식 클래스만 접근 가능
 *   
 *  - public : 모든 클래스 내 접근 가능
 */
public class AccessModifier {
	/*
	 * Field Access Modifier : public, (default), protected, private
	 * 
	 * 인스턴스 필드
	 */
	public int pub = 100;
	protected int pro = 100;
	int pack = 100; // default 접근 제어자는 생략
	private int pri = 100;
	
	void method() { // 인스턴스 메서드
		pub = 200; // ok
		pro = 200; // ok
		pack = 200; // ok
		pri = 200; // ok -> 4개 모두 동일 클래스에서 접근 가능하므로
	}
}
```
```java
class Other extends AccessModifier {
	void method() {
		pro = 300; // 같은 패키지 내 상속 관계로, 자손 클래스에서 수정하였으므로 ok
		
		AccessModifier obj = new AccessModifier(); // AccessModifier 접근 제한자 클래스 객체 생성
		obj.pub = 200; // ok
		obj.pro = 200; // ok
		obj.pack = 200; // ok : 같은 패키지 내 존재
		// obj.pri = 200; // error : pri는 private 접근 제한자로 같은 클래스 내에서만 사용 가능
	}
}
```
```java
package etc; // 다른 패키지

import ch06_ch07.AccessModifier; // ch06_ch07 package import

class Other2 { // AccessModifier의 자식 클래스가 아님
	void method() {
		// pro = 300; // 다른 패키지 내 자손 클래스가 아님
		
		AccessModifier obj = new AccessModifier(); // AccessModifier 접근 제한자 클래스 객체 생성
		obj.pub = 200; // ok
		// obj.pro = 200; // error : pro는 protected에 대한 접근 제어자로 다른 패키지 내 자식 클래스까지 접근 가능한 것이지, 생성한 객체로 접근하는 것은 제한
		// obj.pack = 200; // error : pack은 default 접근 제어자로 같은 패키지에서 접근 가능
		// obj.pri = 200; // error : pri는 private 접근 제한자로 같은 클래스 내에서만 사용 가능
	}
}

class Other3 extends AccessModifier { // AccessModifier의 자식 클래스
	void method() {
		pro = 300; // 다른 패키지 내 자식 클래스에서 수정하였으므로 ok
		
		AccessModifier obj = new AccessModifier(); // AccessModifier 접근 제한자 클래스 객체 생성
		obj.pub = 200; // ok
		// obj.pro = 200; // error : pro는 protected는 다른 패키지 내 자식 클래스까지 접근 가능한 것이지, 생성한 객체에서 접근하는 것이 아님
		// obj.pack = 200; // error : pack은 default 접근 제어자로 같은 패키지에서 접근 가능
		// obj.pri = 200; // error : pri는 private 접근 제한자로 같은 클래스 내에서만 사용 가능
	}
}
```

-----
### 상속(Inheritance)
-----
1. 기존 클래스를 재사용해서 새로운 클래스를 작성
2. 두 클래스를 조상과 자손 관계로 맺어주며, 자손은 조상의 모든 멤버를 물려받음 (단, 생성자와 초기화 블럭은 제외)
3. 자손의 멤버변수는 조상의 멤버변수보다 적을 수 없음 (같거나 많음)
4. 공통부분은 조상에서 관리하고, 개별 부분은 자손에서 관리
5. 조상의 변경은 자손에 영향을 미치지만, 자손의 변경은 조상에 아무런 영향을 미치지 않음
6. 모든 클래스는 최상위 조상 클래스로 Object Class를 상속 받으며, extends Object가 생략

-----
### 상속 대상 제한
-----
1. 부모 클래스의 private 접근 제어자를 가지는 필드와 메서드 제외
2. 부모 클래스가 다른 패키지에 있을 경우, default 접근을 갖는 필드와 메서드도 제외

-----
### extends 키워드
-----
1. 자식 클래스가 상속할 부모 클래스를 지정하는 키워드
2. 자바에서는 단일 상속 (Single Inheritance) 만 가능 (C++은 다중 상속 가능)
  > class 자식클래스명 extends 부모클래스명

<div align = "center">
<img width="392" alt="제목 없음" src="https://github.com/sooyounghan/JAVA/assets/34672301/ea274562-c9a2-43ea-8978-a3d9ee2e292d">
</div>

< Animal Class >
```java
/*
 * Animal Class
 */
public class Animal03 {
	// Field
	int age;
	String type;
	
	// Constructor
	
	void makeSound(String sound) {
		/*
		 * 울음소리나 말을 하는 메서드
		 */
		System.out.println("Animal Class Method - makeSound() = " + sound);
	}
	
	/*
	 * Overloading & 포함 관계
	 */
	void makeSound(Animal02 animal) { // 메서드의 매개변수로 클래스타입(Animal02)도 가능
		System.out.println("매개변수 obj = " + animal);	
	}
	
	void makeSound(Tiger tiger) { // 메서드의 매개변수로 클래스타입(Tiger)도 가능
		System.out.println("매개변수 obj = " + tiger);	
	}
	
	void makeSound(Bird bird) { // 메서드의 매개변수로 클래스타입(Bird)도 가능
		System.out.println("매개변수 obj = " + bird);	
	}
}
```

< Main >
```java
public class Animal03_Main {
	public static void main(String[] args) {
		/* 
		 * Animal03 Class의 객체
		 */
		Animal03 animal = new Animal03();
		String str = new String("동물"); // String은 Class이므로 객체 생성
		animal.makeSound(str); // String Class로부터 생성한 객체의 주소 전달
		Animal02 animal02 = new Animal02(); // Animal02 Class 객체 생성
		animal.makeSound(animal02); // makeSound(Animal02 obj)이므로 매개변수는 Animal02 클래스 타입이어야함 -> 객체를 전달했으므로 주소 출력
		animal.makeSound(new Tiger()); // makeSound(Tiger tiger)이므로 매개변수는 Tiger 클래스 타입이어야함 -> 객체를 전달했으므로 주소 출력
		animal.makeSound(new Bird()); // makeSound(Bird bird)이므로 매개변수는 bird 클래스 타입이어야함 -> 객체를 전달했으므로 주소 출력

		/*
         * 매개변수의 다형성
         */ 
		Animal03 animal1 = new Animal03();		
		animal.makeSound(animal1);
		animal1 = new Tiger(); // Animal 클래스 참조변수는 Tiger 클래스의 객체 변수를 가리킴
		animal.makeSound(animal1); // animal1은 현재 Tiger 클래스 객체를 가리키고 있으므로 makeSound(Tiger tiger)의 매개변수로 가능
		
		animal1 = new Bird(); // Animal 클래스 참조변수는 Bird 클래스의 객체 변수를 가리킴
		animal.makeSound(animal1); // animal1은 현재 Bird 클래스 객체를 가리키고 있으므로 makeSound(Bird bird)의 매개변수로 가능
		System.out.println();
	}
}
```

-----
### 오버라이딩 (Overriding)
-----
1. 조상클래스로부터 상속받은 메서드의 내용을 상속받는 클래스에 맞게 변경하는 것
2. 조건

        A. 반환, 메소드명, 매개 변수가 동일   
        B. 접근 제어자를 좁은 범위로 변경할 수 없음 (public > protected > (default) > private)   
        C. 조상클래스의 메서드보다 많은 수의 예외를 둘 수 없음

-----
### 에너테이션(Annotation)
-----
1. 프로그램에 추가적인 정보를 제공해주는 메타데이터(Metadata)
2. 컴파일러에게 코드 작성 문법 에러를 체크하도록 정보 제공
3. @Override : 오버라이딩을 올바르게 했는지 컴파일러가 확인

< 예제 >

<div align = "center">
<img width="392" alt="제목 없음" src="https://github.com/sooyounghan/JAVA/assets/34672301/07f26bc2-7821-40a6-a2fd-d31451bb84db">
</div>

- 하나의 java 파일에는 public class는 1개만 존재해야함
< Animal Class >
```java
/*
 * Inheritance
 */
class Animal01 {
/*
 * Field - 속성
 */
	int age; // 나이
	int iq; // 지능
	double weight; // 무게
	String gender; // 성별
	
/*
 *  Constructor - Field의 초기화를 주로 담당, 그 외 기능도 담당
 */
	
 /* Method - 기능, 동작
  *
  */
	void move() { System.out.println("Animal Move."); } // 움직이는 메서드
	void eat() { System.out.println("Animal Eat."); } // 먹는 메서드
	void breath() { System.out.println("Animal Breath"); } // 숨을 쉬는 메서드
	void makeSound() { System.out.println("Animal Sound."); } // 소리를 내는 메서드
}
```

< Human Class >
```java
/*
 * 모든 클래스는 Object 클래스를 상속
 *  (= public class Human01 extends Object)
 *  
 *  사람은 동물이므로, 부모-자식 관계
 */

public class Human01 extends Animal01{
	/*
	 * Animal01 클래스로부터 상속받음
	 * 이미 부모클래스로부터 field와 method를 상속받음
	 */
	
	/*
	 * Field
	 */
	int iq = 130; // 조상클래스(Animal Class로부터 상속받은 변수의 값 재정의
	String ssn = "991111-1234567"; // 새로운 변수 추가 가능
	
	/* 
	 * Method
	 */
	void study() { // Human01 Class의 고유 method
		System.out.println("Human01 study()");
	}
	
	/*
	 * 오버라이딩(Overriding)
	 *  : 상속받은 클래스가 조상클래스로부터 상속받은 메서드의 내용을 재정의
	 *  1. 조건 : 반환타입, 메서드명, 매개변수는 동일해야함
	 *  2. 접근제어자는 조상 클래스의 접근제어자보다 좁은 범위로 할 수 없음
	 *  3. 조상클래스의 메서드보다 많은 수의 예외를 둘 수 없음
	 */
	@Override
	void move() { // Overriding
		System.out.println("Human01 move() - 2 legs");
	}
	
	/*
	 * Constructor
	 */
}
```

< Cat Class >
```java
/*
 * 모든 클래스는 Object 클래스를 상속
 *  (= public class Human01 extends Object
 *  
 *  고양이는 동물이므로, 부모-자식 관계
 */

public class Cat01 extends Animal01 {
	/*
	 * Animal01 클래스로부터 상속받음
	 * 이미 부모클래스로부터 field와 method를 상속받음
	 */
	
	/*
	 * Field
	 */
	int age = 1; // Animal Class로부터 상속받은 age 필드를 재정의
	
	/* 
	 * Method
	 */
	void catchMouse() { // Cat01 Class의 고유 method
		System.out.println("Cat01 catchMouse()");
	}
	
	/*
	 * 오버라이딩(Overriding)
	 *  : 상속받은 클래스가 조상클래스로부터 상속받은 메서드의 내용을 재정의
	 *  1. 조건 : 반환타입, 메서드명, 매개변수는 동일해야함
	 *  2. 접근제어자는 조상 클래스의 접근제어자보다 좁은 범위로 할 수 없음
	 *  3. 조상클래스의 메서드보다 많은 수의 예외를 둘 수 없음
	 */
	@Override
	void move() { // Overriding
		System.out.println("Human01 move() - 4 legs");
	}
	
	/*
	 * Constructor
	 */
}
```

< Main >
```java
/*
 * Inheritance Example Class
 */
public class Inheritance01_Main {
	public static void main(String[] args) {
		/*
		 * 부모 클래스 : Animal01
		 * 자식 클래스 : Humna01, Cat01
		 */
		Animal01 animal = new Animal01();
		Human01 human = new Human01();
		Cat01 cat = new Cat01();
		
		/*
		 * 부모 클래스로부터 상속받은 자식클래스는 부모 클래스의 멤버(필드)들을 참조할 수 있음
		 */
		System.out.println("---------Animal Object---------");
		System.out.println("Animal Object = " + animal); // human, cat과 다른 객체
		System.out.println("Animal Age = " + animal.age);
		System.out.println("Animal IQ = " + animal.iq);
		System.out.println("Animal Gender = " + animal.gender);
		System.out.println("Animal Weight = " + animal.weight);
		animal.move();
		animal.eat();
		animal.breath();
		animal.makeSound();
		
		System.out.println("---------Human Object---------");
		System.out.println("Human(inherited Animal Class) Object = " + human); // animal, cat과 다른 객체
		System.out.println("Human(inherited Animal Class) Age = " + human.age); // animal class로부터 상속을 받았으므로 사용 가능
		System.out.println("Human(inherited Animal Class) IQ = " + human.iq); // animal class로부터 상속받은 필드 iq 값을 사람 클래스에서 재정의
		System.out.println("Human(inherited Animal Class) Gender = " + human.gender); 
		System.out.println("Human(inherited Animal Class) Weight = " + human.weight); 
		System.out.println("Human(inherited Animal Class) Personal Number = " + human.ssn);  // Human01 Class 고유 field
		human.move(); // animal class로부터 상속을 받았으므로 사용 가능
		human.eat();
		human.breath();
		human.makeSound();
		human.study(); // Human01 Class 고유 method
		
		System.out.println("---------Cat Object---------");
		System.out.println("Cat(inherited Animal Class) Object = " + cat); // animal, human과 다른 객체
		System.out.println("Cat(inherited Animal Class) Age = " + cat.age); // animal class로부터 상속받은 필드 age 값을 고양이 클래스에서 재정의
		System.out.println("Cat(inherited Animal Class) IQ = " + cat.iq); // animal class로부터 상속을 받았으므로 사용 가능
		System.out.println("Cat(inherited Animal Class) Gender = " + cat.gender); 
		System.out.println("Cat(inherited Animal Class) Weight = " + cat.weight); 
		cat.move(); // animal class로부터 상속을 받았으므로 사용 가능
		cat.eat();
		cat.breath();
		cat.makeSound();
		cat.catchMouse(); // Cat01 Class 고유 Method
	}
}
```
-----
### super와 super()
-----
1. 자손 클래스의 객체를 생성하면, 이를 상속하는 부모 클래스(의 객체 생성 후)를 상속
2. 부모 객체는 또한 초기값 → 명시적 초기화 → 인스턴스 초기화 블록 → 생성자 순으로 생성됨
3. 이에 따라 자식 객체도 또한 초기값 → 명시적 초기화 → 인스턴스 초기화 블록 → 생성자 순으로 생성됨
4. 즉, 이에 따라 자식 클래스의 객체는 부모 클래스의 멤버를 참조할 수 있음) <- 중요!

A. super : 조상 클래스의 참조변수   
B. super() : 조상 클래스의 객체의 기본 생성자(Default Constrcutor)
 
     super()는  조상 클래스에서 기본적인 Default Constructor만 하였을 때, 그것의 사용은 
     묵시적으로 생략되어 자동적으로 조상 클래스 생성자를 호출 가능

<div align = "center">
<img width="489" alt="다운로드 (4)" src="https://github.com/sooyounghan/JAVA/assets/34672301/c7283b07-2c42-435a-a98e-1de4f8d4f187">
</div>


     조상 클래스에서 기본 생성자 외 다른 생성자가 존재하면, 자식 클래스 생성자 첫줄에 
     조상 클래스의 생성자를 의미하는 super() 명시해야함 (생략 불가)
      → 항상 자식 클래스 생성자 첫 줄에 super() 명시 : 자식 클래스의 객체를 생성할 때,
        이를 상속받는 조상 클래스에 정의된 멤버를 사용할 수 있을 수 있기 때문에 조상 클래스의 
        멤버들이 먼저 초기화되어야 함

<div align = "center">
<img width="411" alt="다운로드 (5)" src="https://github.com/sooyounghan/JAVA/assets/34672301/dbc14bba-99a2-4cf0-a0ed-1516905442ff">
</div>

-----
### this와 super
-----
1. this : 자기 자신의 객체 참조 변수 -> this.필드 = 값, this.메서드명()
2. this() : 자신의 (또 다른) 생성자 호출
3. super : 부모 클래스의 객체 참조 변수 -> super.필드 = 값, super.메서드명()
4. super() : 부모 클래스의 (또 다른) 생성자 호출

<div align = "center">
<img src = "https://github.com/sooyounghan/JAVA/assets/34672301/18994590-5073-4919-ad3e-540ef63e8e57">
</div>

< 예제 >
```java
/*
 * Inheritance
 * Mother Class(자손) - Animal Class(조상)
 */

/*
 * Inheritance02.java -> Compile -> Inheritance02.class
 * 
 * 하나의 java 파일에 public class는 1개만 있어야함
 */
public class Inheritance02 {
	
}

class GrandMother02 {
	// Field
	String name = "할머니";
	
	// Constructor 
	GrandMother02() {
		System.out.println("GrandMother02() Default Constructor"); // 출력 1
	}

	
	GrandMother02(int a) {
		System.out.println("GrandMother02() Default Constructor = " + a); // 출력 1
	}
	
	// Method
}
/*
 * Mother02.java -> Compile -> Mother02.class
 */
class Mother02 extends GrandMother02 {
	// Field
	String name = "엄마";
	
	// Constructor

	Mother02() {
		// 기본 생성자 구현
	 	// super() 생략 가능 - GrandMother02() 생성자 호출
	 	System.out.println("Mother02() Default Constructor"); // 출력 2
	  }
	
	Mother02(int a) { // 기본 생성자는 생성되지 않음
		super(a);
		System.out.println("Mother02() Default Constructor = " + a);
	}
	
	// Method
	void sing() { // Mother method 생성
		System.out.println("Mother02 sing() method");
	}
}

/*
 * Daughter02는 Mother02 클래스를 상속 받음(부모 클래스 : Mother02, 자식 클래스 : Daughter02)
 * Daughter02.java -> Compile -> Daughter02.class
 */
class Daughter02 extends Mother02 { 
	// Field
	String name = "딸";
	
	// Constructor
	/*
	 * 자손 클래스의 객체를 생성하게 되면, 이를 상속하는 부모 클래스의 객체도 생성하게 되며, 이에 따라 부모 클래스의 생성자도 호출됨
	 */
	Daughter02() { 
		// 생성자 확인
		// super(); // 기본 생성자에서는 생략 - 컴파일러가 자동적 추가 (부모클래스의 객체에 대한 기본 생성자를 호출) - Mother02 생성자 호출
		super(100); // 생략 불가 - 조상클래스의 객체에 대한 기본 생성자가 없음 : 조상 클래스인 Mother02에서 매개변수가 1개인 Mother02(int a) 호출 
		System.out.println("Daughter02() Default Constructor"); // 출력 3
	}
	
	// Method
	void method() {
		System.out.println("Daughter02 method() / Name = " + name); // 본인의 참조 변수인 this 생략
		// = System.out.println("Daughter02 method() / Name = " + this.name); // this 생략
		System.out.println("Daughter02 method() / Name = " + super.name); // 조상클래스인 mother class의 객체의 field의 name으로 접근
	}
	
	void mother_song() {
		super.sing(); // super는 조상 클래스인 Mother02 클래스의 객체를 의미 -> 메서드 sing() 호출
	}
}
```
< Main >
```java
/*
 * Inheritance02 Main Class
 */
public class Inheritance02_Main {
	public static void main(String[] args) {
		/*
		 * Mother02, Daughter02 클래스 객체 생성
		 */
		// Mother02 mother = new Mother02(); // Mother02 클래스 객체 생성 - 생성자 호출

		Daughter02 daughter = new Daughter02(); // Daughter02 클래스 객체 생성 - 생성자 호출
		System.out.println(daughter.name); // 최종 상속을 받고, 재정의 된 name 출력
		daughter.method();
		daughter.mother_song();
	}
}
```
