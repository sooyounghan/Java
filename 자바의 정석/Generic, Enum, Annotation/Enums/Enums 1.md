-----
### 열거형 (Enums)
-----
1. 서로 관련된 상수를 편리하게 선언하기 위한 것으로 여러 상수를 정의할 때 사용하면 유용 (JDK 1.5부터 새로 추가)
2. 자바의 열거형은 C언어의 열거형보다 더 향상된 것
3. 열거형이 갖는 값뿐만 아니라 타입도 관리하므로 논리적 오류를 줄일 수 있음
```java
class Card {
  static final int CLOVER = 0;
  static final int HEART = 1;
  static final int DIAMOND = 2;
  static final int SPADE = 3;

  static final int TWO = 0;
  static final int THREE = 1;
  static final int FOUR = 2;

  final int kind;
  final int num;
}
```
```java
class Card {
  enum Kind { CLOVER, HEART, DIAMOND, SPADE } // 열거형 Kind 정의
  enum Value { TWO, THREE, FOUR } // 열거형 Value 정의

  final Kind kind; // 타입이 int형이 아닌 Kind
  final Value value; // 타입이 int형이 아닌 Value
}
```

4. 자바의 열거형은 '타입에 안전한 열거형(Typesafe Enum)'이라서 실제값이 같아도 타입이 다르면 컴파일 에러 발생
5. 이처럼, 값 뿐만 아니라 타입까지 체크하므로 타입에 안전하다고 할 수 있음
```java
if(Card.CLOVER == Card.TWO) // true지만, false이어야 의미상 맞음
if(Card.Kind.CLOVER == Card.Value.TWO) // 컴파일 에러. 값은 같지만, 타입이 다름
```

6. 상수의 값이 바뀌면, 해당 상수를 참조하는 모든 소스를 다시 컴파일해야 하지만, 열거형 상수를 사용하면, 기존 소스를 다시 컴파일 하지 않아도 됨

-----
### 열거형의 정의와 사용
-----
1. 괄호 { } 안에 상수 이름을 나열
```java
enum 열거형이름 { 상수명1, 상수명2, ... }
```
2. 예) 동서남북 4방향을 상수로 정의하는 열거형 Direction
```java
enum Direction { EAST, SOUTH, WEST, NORTH }
```

3. 열거형에 정의된 상수를 사용하는 방법 : 열거형이름.상수명이름 (클래스의 static 변수 참조와 동일)
```java
class Unit {
  int x, y; // 유닛 위치
  Direction dir; // 열거형을 인스턴스 변수로 선언

  void init() {
      dir = Direction.EAST; // 유닛 방향을 EAST로 초기화
  }
}
```

4. 열거형 상수 비교 간에는 '==' 사용 가능
   - equals()가 아닌, '=='로 비교가 가능하다는 것은 빠른 성능 제공 의미
5. 단, <, >와 같은 비교 연산자는 사용 불가
   - compareTo() 사용 가능하며, 비교 대상이 같으면 0, 왼쪽이 크면 양수, 오른쪽이 크면 음수를 반환
```java
if(dir == Direction.EAST) {
  x++;
} else if(dir > Direction.WEST) { // Error. 열거형 상수에 비교연산자 사용 불가
  ...
} else if (dir.compareTo(Direction.WEST) > 0) // compareTo()는 사용 가능
  ....
}
```

6. 또한, switch문의 조건식에도 열거형 사용 가능
```java
void move() {
    switch(dir) {
        case EAST : x++; break; // 단, Direction.EAST라고 쓰면 안 됨
        case WEST : X--; break;
        case SOUTH : y++; break;
        case NORTH : y--; break;
    }
}
```
  - 단, case문에 열거형의 이름은 적지 않고, 상수의 이름만 적어야함

-----
### 모든 열거형의 조상 - java.lang.Enum
-----
1. Enum 클래스에 정의된 메서드는 다음과 같음
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/66a1f3ee-b9e6-4950-b132-691626aacd89">
</div>

2. 예시) 열거형 Direction에 정의된 모든 상수 출력
```java
Direction[] dArr = Direction.values();

for(Direction d : dArr) // for(Direction d : Direction.values();
    System.out.println("%s = %d%n", d.name(), d.ordinal());
```
  - values()는 모든 열거형의 모든 상수를 배열로 담아 반환 (모든 열거형이 가지고 있는 것으로 컴파일러가 자동 추가)

3. values()외에 또한 컴파일러가 자동으로 추가해주는 메서드가 존재
```java
static E values()
static E valuesOf(String name)
```
  - 이 메서드는 상수의 이름으로 문자열 상수에 대한 참조를 얻을 수 있게 해줌
```java
Direction d = Direction.valueOf("WEST");

System.out.println(d); // WEST
System.out.println(Direction.WEST == Direction.valueOf("WEST")); // true
```

```java
enum Direction { EAST, SOUTH, WEST, NORTH }

class EnumEx1 {
	public static void main(String[] args) {
		Direction d1 = Direction.EAST;
		Direction d2 = Direction.valueOf("WEST");
		Direction d3 = Enum.valueOf(Direction.class, "EAST");

		System.out.println("d1="+d1);
		System.out.println("d2="+d2);
		System.out.println("d3="+d3);

		System.out.println("d1==d2 ? "+ (d1==d2));
		System.out.println("d1==d3 ? "+ (d1==d3));
		System.out.println("d1.equals(d3) ? "+ d1.equals(d3));
//		System.out.println("d2 > d3 ? "+ (d1 > d3)); // Error
		System.out.println("d1.compareTo(d3) ? "+ (d1.compareTo(d3)));
		System.out.println("d1.compareTo(d2) ? "+ (d1.compareTo(d2)));

		switch(d1) {
			case EAST: // Direction.EAST 라고 쓸 수 없음
				System.out.println("The direction is EAST."); 
				break;
			case SOUTH:
				System.out.println("The direction is SOUTH."); 
				break;
			case WEST:
				System.out.println("The direction is WEST."); 
				break;
			case NORTH:
				System.out.println("The direction is NORTH."); 
				break;
			default:
				System.out.println("Invalid direction."); 
				break;
		}

		Direction[] dArr = Direction.values();

		for(Direction d : dArr)  // for(Direction d : Direction.values()) 
			System.out.printf("%s=%d%n", d.name(), d.ordinal()); 
	}
}
```
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/da67eaf3-fa85-4673-865c-e99d4900c2eb">
</div>

-----
### 열거형에 멤버 추가하기
-----
1. Enum 클래스에 정의된 ordinal()이 열거형 상수가 정의된 순서를 반환하지 않지만, 이 값을 열거형 상수의 값으로 상수의 값으로 사용하지 않는 것이 좋음 (이 값은 내부적 용도로만 사용되기 위한 것)
2. 열거형 상수의 값이 불연속적인 경우에는 다음과 같이 열거형 상수 이름 옆에 원하는 값을 괄호()와 함께 적어주면 됨
```java
enum Direction { EAST(1), SOUTH(5), WEST(-1), NORTH(10) }
```
3. 이 때 지정된 값을 저장할 수 있는 인스턴스 변수와 생성자를 새로 추가해줘야함
   - 단, 먼저 열거형 상수를 모두 정의한 다음에 다른 멤버들을 추가해야함
   - 그리고 열거형 상수의 마지막에 ';'을 붙여야함
```java
enum Direction {
    EAST(1), SOUTH(5), WEST(-1), NORTH(10); // 끝에 ';'를 추가

    private final int value; // 정수를 저장할 필드 (인스턴스 변수) 추가
    Direction(int value) { this.value = value; } // 생성자 추가

    public int getValue() { return value; }
}
```

4. 열거형의 인스턴스 변수는 반드시 final이어야 한다는 제약은 없지만, value는 열거형 상수의 값을 저장하기 위한 것으로 final을 붙임
   - 또한, 외부에서 이 값을 얻을 수 있도록 getValue()도 추가
  
```java
Direction d = new Direction(1); // Error. 열거형의 생성자는 외부에서 호출 불가
```
5. 열거형 Direction에 새로운 생성자가 추가되었지만, 위처럼 열거형 객체를 생성 불가
   - 💡 열거형의 생성자는 묵시적으로 제어자가 private
```java
enum Direction {
      ...
      Direction(int value) { // private Direction(int value)와 동일
      ...
}
```

6. 다음과 같이 하나의 열거형 상수에 여러 값 지정 가능하나, 그에 맞게 인스턴스 변수와 생성자들을 새로 추가해줘야함
```java
enum Direction {
    EAST(1, ">"), SOUTH(5, "V"), WEST(-1, "<"), NORTH(10, "^"); // 끝에 ';'를 추가

    private final int value; // 정수를 저장할 필드 (인스턴스 변수) 추가
    private final String symbol; // 문자열을 저장할 필드 (인스턴스 변수) 추가
    Direction(int value, String symbol) {  // 생성자 추가 (접근제어자 private 생략)
        this.value = value;
        this.symbol = symbol
    }

    public int getValue() { return value; }
    public String getSymbol() { return symbol; }
}
```

```java
enum Direction { 
	EAST(1, ">"), SOUTH(2,"V"), WEST(3, "<"), NORTH(4,"^");

	private static final Direction[] DIR_ARR = Direction.values();
	private final int value;
	private final String symbol;

	Direction(int value, String symbol) { // private Direction(int value)
		this.value  = value;
		this.symbol = symbol;
	}

	public int getValue()      { return value;  }
	public String getSymbol()  { return symbol; }

	public static Direction of(int dir) {
	        if (dir < 1 || dir > 4) {
	            throw new IllegalArgumentException("Invalid value :" + dir);
	        }
	        return DIR_ARR[dir - 1];		
	}	

	// 방향을 회전시키는 메서드. num 값만큼 90도씩 시계방향으로 회전
	public Direction rotate(int num) {
		num = num % 4;

		if(num < 0) num +=4; // num이 음수일 때는 시계 반대 방향으로 회전

		return DIR_ARR[(value-1+num) % 4];
	}

	public String toString() {
		return name()+getSymbol();
	}
} // enum Direction

class EnumEx2 {
	public static void main(String[] args) {
		for(Direction d : Direction.values()) 
			System.out.printf("%s=%d%n", d.name(), d.getValue()); 

		Direction d1 = Direction.EAST;
		Direction d2 = Direction.of(1);

		System.out.printf("d1=%s, %d%n", d1.name(), d1.getValue());
		System.out.printf("d2=%s, %d%n", d2.name(), d2.getValue());

		System.out.println(Direction.EAST.rotate(1));
		System.out.println(Direction.EAST.rotate(2));
		System.out.println(Direction.EAST.rotate(-1));
		System.out.println(Direction.EAST.rotate(-2));
	}
}
```
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/f1447a17-0d47-448b-abd5-b9d0dfb11250">
</div>


-----
### 열거형에 추상 메서드 추가
-----
1. 예) 열거형 Transportation은 운송 수단 종류 별로 상수를 정의하고 있으며, 각 운송 수단에는 기본 요금(BASIC_FARE)가 책정
```java
enum Transportation {
	BUS(100), TRAIN(150), SHIP(100), AIRPLANE(300);

	private final int BASIC_FARE;

	private Transportation(int basicFare) {
		BASIC_FARE = basicFare;
	}

	int fare() { // 운송 요금 반환
		return BASIC_FARE;
	}
}
```

2. 거리에 따라 요금을 계산하는 방식이 각 운송 수단마다 다를 것이므로 열거형에 추상 메서드 fare(int distance) 선언하여 각 열거형 상수가 이 추상 메서드를 반드시 구현
```java
enum Transportation {
	BUS(100) { int fare(int distance) { return distance * BASIC_FARE; }},
	TRAIN(150) { int fare(int distance) { return distance * BASIC_FARE; }},
	SHIP(100) { int fare(int distance) { return distance * BASIC_FARE; }},
	AIRPLANE(300)  { int fare(int distance) { return distance * BASIC_FARE; }};

	abstract int fare(int distance); // 거리에 따른 요금을 계산하는 추상 메서드
	protected final int BASIC_FARE; // protected로 설정해야 각 상수에서 접근 가능

	private Transportation(int basicFare) {
		BASIC_FARE = basicFare;
	}

	int fare() { // 운송 요금 반환
		return BASIC_FARE;
	}
}
```

```java
enum Transportation { 
	BUS(100)      { int fare(int distance) { return distance*BASIC_FARE;}},
	TRAIN(150)    { int fare(int distance) { return distance*BASIC_FARE;}},
	SHIP(100)     { int fare(int distance) { return distance*BASIC_FARE;}},
	AIRPLANE(300) { int fare(int distance) { return distance*BASIC_FARE;}};

	protected final int BASIC_FARE; // protected로 해야 각 상수에서 접근 가능
	
	Transportation(int basicFare) { // private Transportation(int basicFare) {
		BASIC_FARE = basicFare;
	}

	public int getBasicFare() { return BASIC_FARE; }

	abstract int fare(int distance); // 거리에 따른 요금 계산
}

class EnumEx3 {
	public static void main(String[] args) {
		System.out.println("bus fare="     +Transportation.BUS.fare(100));
		System.out.println("train fare="   +Transportation.TRAIN.fare(100));
		System.out.println("ship fare="    +Transportation.SHIP.fare(100));
	 	System.out.println("airplane fare="+Transportation.AIRPLANE.fare(100));
	}
}
```
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/c5074f74-35a3-4f89-91dc-a8992976e7cc">
</div>
