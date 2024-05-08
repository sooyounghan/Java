-----
### 열거형의 이해
-----
1. 만약 열거형 Direction이 다음과 같이 정의되었다고 하자
```java
enum Direction { EAST, SOUTH, WEST, NORTH }
```
  - 사실은 열거형 상수 하나하나가 Direction 객체

2. 즉, 다음과 같음
```java
class Direction {
    static final Direction EAST = new Direction("EAST");
    static final Direction SOUTH = new Direction("SOUTH");
    static final Direction WEST = new Direction("WEST");
    static final Direction NORTH = new Direction("NORTH");

    private String name;
    private Direction(String name) {
        this.name = name;
    }
}
```

3. Direction 클래스의 static 상수 EAST, SOUTH, WEST, NORTH의 값은 객체의 주소이며, 이 값은 바뀌지 않는 값이므로 '=='가 비교가 가능
4. 모든 열거형은 추상 클래스 Enum의 자손
   - 다음과 같이 MyEnum이라는 클래스를 통해 Enum을 표현해보자.
```java
abstract class MyEnum<T extends MyEnum<T>> implements Comparable<T> {
    static int id = 0; // 객체에 붙일 일련 번호 (0부터 시작)

    int ordinal;
    String name = "";

    public int ordinal() { return ordinal; }

    MyEnum(String name) {
        this.name = name;
        ordinal = id++; // 객체를 생성할 때마다 id의 값 증가
    }

    public int compareTo(T t) {
        return ordinal - t.ordinal(0;
    }
}
```
  - Comparable 인터페이스를 구현해 열거형 상수간 비교가 가능하도록 설정
  - ```MyEnum<T extends MyEnum<T>>```과 같이 선언해, 타입 T가 ```<MyEnum<T>>```의 자손임을 의미
  - 타입 T가 MyEnum의 자손이므로 ordinal()가 정의되어 있는 것이 분명하므로 형변환 없이 에러가 발생하지 않음
  - 그리고 추상 메서드를 새로 추가하면, 클래스 앞에도 abstract를 붙여줘야 하고, 각 static 상수들도 추상 메서드를 구현해줘야 함
```java
abstract class Direction extends MyEnum {
    abstract Point move(Point p); // 추상 메서드

    static final Direction EAST = new Direction("EAST") { // 익명 클래스
          Point move(Point p) { /* 내용 생략 */ }
    };
    static final Direction SOUTH = new Direction("SOUTH") { // 익명 클래스
          Point move(Point p) { /* 내용 생략 */ }
    };
    static final Direction WEST = new Direction("WEST") { // 익명 클래스
          Point move(Point p) { /* 내용 생략 */ }
    };
    static final Direction NORTH = new Direction("NORTH") { // 익명 클래스
          Point move(Point p) { /* 내용 생략 */ }
    };

    private String name;
    private Direction(String name) {
        this.name = name;
    }
}
```

```java
abstract class MyEnum<T extends MyEnum<T>> implements Comparable<T> {
	static int id = 0;
		
	int ordinal;
	String name = "";

	public int ordinal() { return ordinal; }
	
	MyEnum(String name) {
		this.name = name;
		ordinal = id++;	
	}

	public int compareTo(T t) {
		return ordinal - t.ordinal();
	}
}

abstract class MyTransportation extends MyEnum {
	static final MyTransportation BUS   = new MyTransportation("BUS", 100) {
		int fare(int distance) { return distance * BASIC_FARE; }
	};
	static final MyTransportation TRAIN = new MyTransportation("TRAIN", 150) {
		int fare(int distance) { return distance * BASIC_FARE; }
	};
	static final MyTransportation SHIP  = new MyTransportation("SHIP", 100) {
		int fare(int distance) { return distance * BASIC_FARE; }
	};
   static final MyTransportation AIRPLANE = 
                                           new MyTransportation("AIRPLANE", 300) {
		int fare(int distance) { return distance * BASIC_FARE; }
	};

	abstract int fare(int distance); // 추상 메서드

	protected final int BASIC_FARE;

	private MyTransportation(String name, int basicFare) {
		super(name);
		BASIC_FARE = basicFare;
	}

	public String name()     { return name; }
	public String toString() { return name; }
}

class EnumEx4 {
	public static void main(String[] args) {
		MyTransportation t1 = MyTransportation.BUS;
		MyTransportation t2 = MyTransportation.BUS;
		MyTransportation t3 = MyTransportation.TRAIN;
		MyTransportation t4 = MyTransportation.SHIP;
		MyTransportation t5 = MyTransportation.AIRPLANE;
			
		System.out.printf("t1=%s, %d%n", t1.name(), t1.ordinal());
		System.out.printf("t2=%s, %d%n", t2.name(), t2.ordinal());
		System.out.printf("t3=%s, %d%n", t3.name(), t3.ordinal());
		System.out.printf("t4=%s, %d%n", t4.name(), t4.ordinal());
		System.out.printf("t5=%s, %d%n", t5.name(), t5.ordinal());
		System.out.println("t1==t2 ? "+(t1==t2));
		System.out.println("t1.compareTo(t3)="+ t1.compareTo(t3));
	}
}
```
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/400cad80-3491-4f1d-89c0-614fac99fd3d">
</div>
