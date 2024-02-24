-----
### 다형성 (Polymorphism)
-----
1. 다양한 객체를 이용해 다양한 실행 결과가 나오도록 하는 것
2. 같은 타입이지만, 실행 결과가 다양한 객체 대입(이용)을 이용한 성질
3. 부모 타입에서는 모든 자식 객체가 대입 가능
4. 자식 타입은 부모 타입으로 자동 타입 변환

        객체를 배열로도 생성 가능 (포함관계, 다형성 가능)
        : 클래스타입[] 변수명 = new 클래스타입[크기]; 
          (초기화를 시키지 않으면, 초기값으로 할당 : 클래스 이므로 null값 초기화)

-----
### 매개변수의 다형성 (Polymorphism)
-----
1. 매개변수가 클래스 타입일 경우, 해당 클래스의 객체 대입이 원칙이나 자식 객체 대입하는 것도 허용
2. 적용 예) 재정의된 메소드(오버라이딩)
< 예제 1 >
```java
/*
 * Car05's Tire Class/
 */
public class Tire {
	/*
	 * Field
	 */
	String location; // Tire Location
	int maxRotation; // 최대 회전수
	int accumulateRotation; // 누적 회전수
	
	/*
	 * Constructor
	 */
	Tire(String location, int maxRotation) {
		this.location = location;
		this.maxRotation = maxRotation;
	}
	
	/*
	 * Field
	 */
	public boolean roll() {
		/*
		 * 바퀴가 굴러가는 메서드
		 */
		
		++accumulateRotation; // 바퀴는 굴러가므로 누적회전수 1증가
		
		if(accumulateRotation < maxRotation) { // 누적 회전수 < 최대 회전수
			System.out.println(location + " Tire 수명 : " + (maxRotation - accumulateRotation));
			return true; // true
		}
		else { // 누적 회전수 >= 최대 회전수
			System.out.println(location + " Location Punk");
			return false;
		}
	}
}
```
```java
/*
 * Hankook Tire (inherited Tire Class) Class/
 */
public class HankookTire extends Tire {
	/*
	 * Field
	 */
	
	/*
	 * Constructor
	 */
	HankookTire(String location, int maxRotation) {
		super(location, maxRotation);
		System.out.println(this.location + " Hankook New Tire");
	}
	
	/*
	 * Field
	 */
	@Override
	public boolean roll() {
		/*
		 * 바퀴가 굴러가는 메서드
		 */
		
		++accumulateRotation; // 바퀴는 굴러가므로 누적회전수 1증가
		
		if(accumulateRotation < maxRotation) { // 누적 회전수 < 최대 회전수
			System.out.println(location + " Hankook Tire 수명 : " + (maxRotation - accumulateRotation));
			return true; // true
		}
		else { // 누적 회전수 >= 최대 회전수
			System.out.println(location +" Location Hankook Tire Punk");
			return false;
		}
	}
}
```
```java
/*
 * Hankook Tire (inherited Tire Class) Class/
 */
public class GumhoTire extends Tire {
	/*
	 * Field
	 */
	
	/*
	 * Constructor
	 */
	GumhoTire(String location, int maxRotation) {
		super(location, maxRotation);
		System.out.println(this.location + " Gumho New Tire");
	}
	
	/*
	 * Field
	 */
	@Override
	public boolean roll() {
		/*
		 * 바퀴가 굴러가는 메서드
		 */
		
		++accumulateRotation; // 바퀴는 굴러가므로 누적회전수 1증가
		
		if(accumulateRotation < maxRotation) { // 누적 회전수 < 최대 회전수
			System.out.println(location + " Gumho Tire 수명 : " + (maxRotation - accumulateRotation));
			return true; // true
		}
		else { // 누적 회전수 >= 최대 회전수
			System.out.println(location +" Location Gumho Tire Punk");
			return false;
		}
	}
}
```
```java
/*
 * Car05 Class
 */
public class Car05 {
	// Field
	/*
	 * 클래스 내 다른 타입의 클래스 타입을 Field로 선언 (Composite 관계)
	 */
	// Tire 클래스를 통해 바퀴 객체 4개 생성
	/*
	 * 다형성
	 */
	Tire frontLeftTire = new HankookTire("frontLeft", 6); // frontLeftTire
	Tire frontRightTire = new GumhoTire("frontRearTire", 2); // frontRightTire
	Tire rearLeftTire = new HankookTire("rearLeftTire", 3); // rearLeftTire
	Tire rearRightTire = new GumhoTire("rearRightTire", 4); // rearRightTire
	// Constructor
	
	// Method
	public int run() {
		/*
		 * 차가 움직이는 메서드
		 */
		System.out.println("Car05 Running()");
		if(frontLeftTire.roll() == false) { // Front-Left Tire 문제 발생
			stop(); 
			return 1; 
		}
		if(frontRightTire.roll() == false) { // Front-Right Tire 문제 발생
			stop(); 
			return 2; 
		}
		if(rearLeftTire.roll() == false) { // Rear-Left Tire 문제 발생
			stop(); 
			return 3; 
		}
		if(rearRightTire.roll() == false) { // Rear-Right Tire 문제 발생
			stop();
			return 4; 
		}
		return 0;
	}
	
	public void stop() {
		/*
		 * 차가 멈추는 메서드
		 */
		System.out.println("Stop!");
	}
}
```
```java
/*
 * Car05 Class
 */
public class Car05_Main {
	public static void main(String[] args) {
		Car05 myCar = new Car05(); // Car05 객체 생성
		
		for(int i = 0; i < 5; i++) { // 5번 운전 시행
			int problem = myCar.run(); // 운행 중 문제 발생 시
			
			switch(problem) { // 문제 발생 숫자에 따라 조건문 처리
				case 1 :
					System.out.println("Front-Left Tire Change");
					myCar.frontLeftTire = new HankookTire("frontLeft", 15); // 새로 myCar 인스턴스에 새로운 Tire 객체를 생성해 교체
					break;
				case 2 :
					System.out.println("Front-Right Tire Change");
					myCar.frontRightTire = new HankookTire("frontRight", 13); // 새로 myCar 인스턴스에 새로운 Tire 객체를 생성해 교체
					break;
				case 3 :
					System.out.println("Rear-Left Tire Change");
					myCar.rearLeftTire = new GumhoTire("rearLeft", 15); // 새로 myCar 인스턴스에 새로운 Tire 객체를 생성해 교체
					break;
				case 4 :
					System.out.println("Rear-Right Tire Change");
					myCar.rearRightTire = new HankookTire("rearRight", 15); // 새로 myCar 인스턴스에 새로운 Tire 객체를 생성해 교체
					break;
			}
			System.out.println("------------------------------");
		}
		
	}
}
```

< 예제 2 >
```java
/*
 * Vehicle Class는 Bus / Taxi Class의 Parent Class
 */
public class Vehicle {
	/*
	 * Field : [Access Modifier][Modifier] DataType Field_name [= value];
	 *   -> 클래스 또는 객체 생성 시 데이터, 값의 역할
	 */
	
	/*
	 * Constructor : [Access Modifier] Class_name (argument list ..) { statement; }
	 *   -> 클래스로부터 객체 생성 시 필드의 초기화를 주로 담당하는 역할(생성자)
	 */
	
	/*
	 * Method : [Access Modifier][Modifier] Return_Type Method_name (Argument list) { 
	 *                    statement;
	 *                    return expression;
	 *          }
	 *   -> 클래스 또는 객체 생성 시 클래스나 객체의 동작, 기능을 구현하는 부분
	 */
	
	public void run() {
		/*
		 * Vehicle이 run하는 메서드
		 */
		System.out.println("Vehicle run()");
	}
}
```
```java
/*
 * Taxi Class (inherited Vehicle Class)
 */
public class Taxi extends Vehicle {
	@Override
	public void run() {
		System.out.println("Taxi run()");
	}
}
```
```java
/*
 * Bus Class (inherited Vehicle Class)
 */
public class Bus extends Vehicle {
	@Override
	public void run() {
		System.out.println("Bus run()");
	}
}
```
```java
/*
 * Vehicle을 사용하기 위한 Class
 */
public class Driver {
	/*
	 * 같은 메서드를 중복으로 선언
	 *  해결 방법) 1. 메서드의 이름을 각각 설정
	 *           2. 다형성 이용
	 */
	
	// Vehicle 이용
	public void driveVehicle(Vehicle vehicle) {
		vehicle.run();
	}
	
	// Taxi 이용
	public void driveTaxi(Taxi taxi) {
		taxi.run();
	}
	
	// Bus 이용
	public void driveBus(Bus bus) {
		bus.run();
	}
	
	// 다형성 이용 (Vehicle(부모 클래스) - Bus, Taxi(자식 클래스))  (매개 변수의 다형성)
	public void drive(Vehicle vehicle) {
		vehicle.run();
	}
}
```

```java
/*
 * Driver Class의 Main Class
 */
public class DriverEx {
	public static void main(String[] args) {
		Driver driver = new Driver();
		
		/*
		 * 각각의 구현한 메서드로 출력
		 */
		driver.driveVehicle(new Vehicle());
		driver.driveTaxi(new Taxi());
		driver.driveBus(new Bus());
		
		/*
		 * 다형성을 이용해 한 메서드로 출력 (매개 변수의 다형성)
		 */
		driver.drive(new Vehicle());
		driver.drive(new Taxi());
		driver.drive(new Bus());
	}
}
```

-----
### 참조변수의 형변환
-----
1. 업 캐스팅(Up-Casting) [자손클래스타입 참조변수 -> 조상클래스타입 참조변수]
  - 자식클래스 참조변수 = new 자식클래스();
  - 조상클래스 참조변수 = 자식클래스 참조변수; [Up-Casting] (= 조상클래스 참조변수 = new 자식클래스();)
  - 상위클래스타입의 참조변수에 하위클래스 객체가 할당 : 자식 타입이 부모 타입으로 자동 형변환되면, 부모에서 선언된 필드와 메서드만 사용 가능

2. 다운 캐스팅(Down-Casting) [조상클래스타입 참조변수-> 자손클래스타입 참조변수]
  - 조상클래스 참조변수 = new 자식클래스();
  - 자식클래스 참조변수 = (자식클래스타입) 조상클래스의참조변수; [Down-Casting]
  - 자식에 선언된 필드와 메서드를 사용하고 싶다면, 강제타입변환을 하여 다시 자식 타입을 변환 -> 자식에서 선언된 필드와 메서드를 사용하면 됨

-----
### instacneof 연산자
-----
1. 인스턴스의 실제 타입을 알아보기 위한 연산자
2. instanceof의 왼쪽은 참조변수, 오른쪽에는 타입(클래스명)이 피연산자
3. 연산의 결과 : boolean 값(true : 참조변수가 검사한 타입의 형변환이 가능 / false : 형변환이 불가)

< 예제 1 >
```java
/*
 * Parent Class
 */
public class Parent {
	public String ssn; // 주민 번호 (Parent Class의 고유 필드)
	
	public void drink_soju() { // Parent Class의 고유 메서드
		System.out.println("Parent Class's drink_soju()");
	}
	
	public void drive() { // Parent Class의 고유 메서드
		System.out.println("Parent Class's drive()");
	}
}
```

```java
/*
 * Child Class
 */

public class Child extends Parent {
	public int allowance; // 용돈 (Child Class의 고유 필드)
	
	public void vacation() { // 방학 (Child Class의 고유 메서드)
		System.out.println("Child Class's vacation()");
	}
}
```
```java
/*
 * 객체 간의 타입 변환
 *   1. 업 캐스팅(Up-Casting) [자손클래스타입 참조변수 -> 조상클래스타입 참조변수]
 *     - 자식클래스 참조변수 = new 자식클래스(); 
 *     - 조상클래스 참조변수 = 자식클래스 참조변수;  [Up-Casting]
 *       (= 조상클래스 참조변수 = new 자식클래스();)
 *     - 상위클래스타입의 참조변수에 하위클래스 객체가 할당
 *       : 자식 타입이 부모 타입으로 자동 형변환되면, 부모에서 선언된 필드와 메서드만 사용 가능
 *       
 *   2. 다운 캐스팅(Down-Casting) [조상클래스타입 참조변수-> 자손클래스타입 참조변수]
 *     - 조상클래스 참조변수 = new 자식클래스();
 *     - 자식클래스 참조변수 = (자식클래스타입) 조상클래스의참조변수; [Down-Casting]
 *     - 자식에 선언된 필드와 메서드를 사용하고 싶다면, 강제타입변환을 하여 다시 자식 타입을 변환
 *         -> 자식에서 선언된 필드와 메서드를 사용하면 됨
 */
 
public class Parent_Child_Main {
	public static void main(String[] args) {
		Parent parent = new Parent();
		parent.ssn = "900000-1234567";
		parent.drink_soju();
		parent.drive();
		
		Child child = new Child();
		child.allowance = 50000;
		child.vacation();
		
		/*
		 * 상속 클래스 간 형 변환
		 */
		Parent parent02 = new Child(); // Up-casting
		/*
		 * 자손타입이 부모타입으로 자동 형변환되면, 부모에서 선언된 필드와 메서드만 사용 가능
		 */
		parent02.drive();
		parent02.drink_soju();
		// parent02.allowance; // error
		// parent02.vacation(); // error
		
        /*
         * instacneof 연산자
         *   - 인스턴스의 실제 타입을 알아보기 위한 연산자
         *   - instanceof의 왼쪽은 참조변수, 오른쪽에는 타입(클래스명)이 피연산자
         *   - 연산의 결과 : boolean 값(true : 참조변수가 검사한 타입의 형변환이 가능 / false : 형변환이 불가)
         */
		if(parent02 instanceof Child) { // parent02 참조변수에 할당된 주소가 Child클래스의 객체라면,
			Child child02 = (Child)parent02; // Down-casting
			/*
			 * 자식에 선언된 필드와 메서드 사용하고 싶다면, 강제타입 변환을 통해 자식에서 선언된 필드와 메서드 사용 가능
			 */
			System.out.println(child02.allowance);
			child02.vacation();
			
			/*
			 * 상속받은 것이므로 사용 가능 (조상 클래스의 ssn과 drive & drink_soju)
			 */
			System.out.println(child02.ssn);
			child02.drink_soju();
			child02.drive();
		}
		else {
			System.out.println("parent02 is not Child Class");
		}
	}
}
```

< 예제 2 >
```java
// Machine Class
public class Machine {
	public void turnOnOff(){
		System.out.println("Machine Class turnOnOff()");
	}
}

// Tel Class (inherited Machine Class)
class Tel extends Machine{
	@Override
	public void turnOnOff(){
		System.out.println("Tel Class turnOnOff()");
	}
}

// HandPhone Class (inherited Tel Class)
class HandPhone extends Tel{
	public void Call() { 
		System.out.println("Hand Phone Class Call()"); 
	}

	@Override
	public void turnOnOff(){
		System.out.println("HandPhone Class turnOnOff()");
	}
}

// CoffeeMachine Class (inherited Machine Class)
class CoffeeMachine extends Machine{
	public void extractCoffee() {
		System.out.println("CoffeeMachine Class extractCoffee()");
	}

	@Override
	public void turnOnOff(){
		System.out.println("CoffeeMachine Class turnOnOff()");
	}
}
```

```java
/*
 * Machine Class Main Class
 */
public class Machine_Main {
	public static void main(String[] args) {
		// Machine Class 객체 생성
		Machine  machine = new Machine();
		machine.turnOnOff();// Machine turnOnOff()
		
		// HandPhone Class 객체 생성
		HandPhone hp = new HandPhone();
		hp.Call(); // HandPhone 메서드의 고유 기능
		hp.turnOnOff(); // HandPhone turnOnOff() [오버라이딩]
		
		// CoffeeMachine Class 객체 생성
		CoffeeMachine cm = new CoffeeMachine();
		cm.extractCoffee(); // CoffeeMachine 메서드의 고유 기능
		cm.turnOnOff(); // Coffee turnOnOff() [오버라이딩]
		
		// Tel Class 객체 생성
		Tel t = new Tel();
		t.turnOnOff(); // Tel turnOnOff() [오버라이딩]
		machine = t; // Up-casting
		machine.turnOnOff(); // Tel turnOnOff() [오버라이딩]
		
		System.out.println("-------------------");
		// machine 참조변수에 HandPhone 클래스 객체 hp 주소 할당 [Up-casting]
		machine = hp; // Machine 클래스타입 참조 변수 machine은 HandPhone 클래스 타입의 객체를 참조하는 hp를 가리키므로, machine은 HandPhone 클래스타입을 가리킴
		machine.turnOnOff(); // HandPhone turnOnOff() [오버라이딩]
		// machine.Call(); // HandPhone 객체에 대해 machine 클래스타입으로 참조하고 있으므로, HandPhone 고유 메서드인 Call()에 접근 불가

		if(machine instanceof HandPhone) { // Line 30에 의해 true
			HandPhone a1 = (HandPhone)machine; // Down Casting
			a1.Call(); // Handphone 클래스타입으로 형변환되어으므로 사용 가능
		}
		
       	 	if(machine instanceof Tel) { // machine : 현재 HandPhone 클래스 객체를 가리키므로, 상속하는 조상클래스인 Tel 클래스에 대해 true
			// Down-Casting
			Tel t1 = (Tel)machine; // machine은 현재 HandPhone 클래스 객체를 가리키고 있으므로, Tel 클래스 참조변수로 이 클래스 객체를 가리키면, 
			t1.turnOnOff(); // trunOnOff는 HandPhone의 turnOnOff가 호출
			
			System.out.println(t1.name);
			System.out.println(((Tel)machine).name);
		}
			
		// machine 참조변수에 CoffeeMachine 클래스 객체 cm 주소 할당 [Up-casting]
		machine = cm; // Machine 클래스타입 참조 변수 machine은 CoffeeMachine 클래스 타입의 객체를 참조하는 cm를 가리키므로, machine은 CoffeeMachine 클래스타입을 가리킴
		// machine.extractCoffee(); // CoffeeMachine 객체에 대해 machine 클래스타입으로 참조하고 있으므로, CoffeeMachine 고유 메서드인 extractCoffee()에 접근 불가	
	}
}
```
