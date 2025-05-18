-----
### 클래스간의 관계 - 포함 (Composite) 관계
-----
1. 한 클래스의 멤버변수로 다른 클래스 타입의 참조 변수를 선언하는 것
2. 원(Circle)을 표현하기 위한 클래스
```java
class Circle {
    int x; // 원점의 x 좌표
    int y; // 원점의 y 좌표
    int r; // 반지름 (Radius)
}
```
3. 좌표 상 한 점을 다루기 위한 Point 클래스
```java
class Point {
    int x; // x 좌표
    int y; // y 좌표
}
```

4. Point 클래스를 재사용하여 Circle 클래스를 작성한다면?
```java
class Circle {
    Point p = new Point(); // 원점
    int r;
}
```

 5. 예시
```java
class Car {
    Engine e = new Engine(); // 엔진
    Door[] d = new Door[4]; // 문(4개라 가정하고 배열로 처리)
    // ...
}
```
  - 위와 같이 Car 클래스의 단위 구성요소인 Engine, Door와 같은 클래스를 미리 작성
  - 이들을 Car 클래스의 멤버변수로 선언하여 포함관계

-----
### 클래스간의 관계 결정
-----
1. 상속 관계 ( ~ is a ~ ) : '~는 ~이다' 가 성립
2. 포함 관계 ( ~ has a ~ ) : '~는 ~를 가지고 있다.' 가 성립
```java
class DrawShape {
	public static void main(String[] args) {
		Point[] p = {   new Point(100, 100),
                        new Point(140,  50),
                        new Point(200, 100)
					};

		Triangle t = new Triangle(p);
		Circle   c = new Circle(new Point(150, 150), 50);

		t.draw(); // 삼각형을 그림
		c.draw(); // 원을 그림
	}
}

class Shape {
	String color = "black";
	void draw() {
		System.out.printf("[color=%s]%n", color);
	}
}

class Point {
	int x;
	int y;

	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	Point() {
		this(0,0);
	}

	String getXY() {  
		return "("+x+","+y+")"; // x와 y의 값을 문자열로 반환
	}
}

class Circle extends Shape { // Circle과 Shape는 상속 관계
	Point center;	// 원의 중심 좌표 (Circle과 Point는 포함 관계)
	int r;			// 반지름

	Circle() {		
		this(new Point(0, 0), 100); // Circle(Point center, int r) 호출
	}

	Circle(Point center, int r) {
		this.center = center;
		this.r = r;
	}

	void draw() { // 원을 그리는 대신에 원의 정보 출력 (Shape클래스로부터 상속 받음) [오버라이딩]
		System.out.printf("[center=(%d, %d), r=%d, color=%s]%n", center.x, center.y, r, color);
	}
}

class Triangle extends Shape {
	Point[] p = new Point[3]; // 3개의 Point 인스턴스를 담을 배열 생성

	Triangle(Point[] p) {
		this.p = p;
	}

	void draw() { 
		System.out.printf("[p1=%s, p2=%s, p3=%s, color=%s]%n", p[0].getXY(), p[1].getXY(), p[2].getXY(), color);
	}
}
```

<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/25a844d8-54d1-4c91-aad4-e44a6ce02a34">
</div>


```
1. 원은 도형이다. (O) (상속)
2. 원은 점이다. (X)

3. 원은 도형을 가지고 있다. (X)
4. 원은 점을 가지고 있다. (O) (포함)
```

  - 오버라이딩 (Overriding) : 조상 클래스에 정의된 메서드와 같은 메서드를 자손 클래스에서 정의하는 것

-----
### 예제
-----
```java
class DeckTest {
	public static void main(String args[]) {
		Deck d = new Deck();	   // 카드 한 벌(Deck) 생성
		Card c = d.pick(0);	   // 섞기 전 가장 제일 위의 카드를 뽑음
		System.out.println(c); // System.out.println(c.toString());와 동일

		d.shuffle();			   // 카드를 섞음
		c = d.pick(0);		   // 섞은 후 가장 제일 위의 카드를 뽑음
		System.out.println(c);
	}
}

// Deck 클래스
class Deck {
	final int CARD_NUM = 52;	// 카드의 개수
	Card cardArr[] = new Card[CARD_NUM];  // Card 객체 배열을 포함

	Deck () {	// Deck 카드를 초기화
		int i=0;

		for(int k=Card.KIND_MAX; k > 0; k--)
			for(int n=0; n < Card.NUM_MAX ; n++)
				cardArr[i++] = new Card(k, n+1);
	}

	Card pick(int index) {	// 지정된 위치 (index)에 있는 카드 하나를 꺼내서 반환
		return cardArr[index];
	}

	Card pick() {			// Deck에서 카드 하나를 꺼냄
		int index = (int)(Math.random() * CARD_NUM);
		return pick(index);
	}

	void shuffle() { // 카드의 순서를 섞음
		for(int i=0; i < cardArr.length; i++) {
			int r = (int)(Math.random() * CARD_NUM);

			Card temp = cardArr[i];	
			cardArr[i] = cardArr[r];
			cardArr[r] = temp;
		}
	}
} // Deck 클래스 끝

// Card 클래스
class Card {
	static final int KIND_MAX = 4;	// 카드 무늬 수
	static final int NUM_MAX  = 13;	// 무늬별 카드 수

	static final int SPADE   = 4;
	static final int DIAMOND = 3;
	static final int HEART   = 2;
	static final int CLOVER  = 1;

	int kind;
	int number;

	Card() {
		this(SPADE, 1);
	}

	Card(int kind, int number) {
		this.kind = kind;
		this.number = number;
	}

	public String toString() {
		String[] kinds = {"", "CLOVER", "HEART", "DIAMOND", "SPADE"};
		String numbers = "0123456789XJQK"; // 숫자 10은 X로 표현

		return "kind : " + kinds[this.kind] 
			+ ", number : " + numbers.charAt(this.number);
	} // toString()의 끝
} // Card클래스의 끝
```
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/ab3a1003-f2f7-4542-a4ae-afdd70d05e08">
</div>

1. Deck 클래스를 작성하는데 Card 클래스를 재사용하여 포함관계로 설정
2. Deck은 모두 52장의 카드로 구성, Card클래스를 크기가 52인 배열로 처리
3. shuffle()은 카드 한 벌의 첫 번째 카드부터 순서대로 임의 위치에 있는 카드와 위치를 서로 바꾸는 방식으로 카드를 섞음
4. pick()은 Card 객체 배열 cardArr에 저장된 Card 객체 중 하나를 꺼내서 반환
   - Card 객체 배열은 참조변수 배열, 이 배열에 실제로 값이 저장된 것이 아니라 객체의 주소가 저장

```java
Card c = d.pick(0); // pick(int index) 호출

Card pick(int index) {
	return cardArr[index];
}
```

   - pick(0)을 호출하면, 매개변수 index의 값이 0이 되므로, cardArr[0]에 저장된 Card 객체의 주소가 참조변수 c에 저장
   - 즉, 참조변수 c에 cardArr[0]에 저장된 Card인스턴스 주소가 저장

5. 참조변수의 출력이나 덧셈연산자를 이용한 참조변수와 문자열의 결합에는 toString()이 자동적으로 호출되어 참조변수를 문자열로 대치한 후 처리
   - toString()은 모든 클래스의 조상인 Object 클래스에서 정의된 것
   - 어떤 종류에 대해서도 toString()을 호출하는 것이 가능

-----
### 단일 상속 (Single Inheritance)
-----
1. C++ : 조상 클래스로부터 상속받는 것이 가능한 '다중 상속'을 허용
2. 자바 : 오직 단일 상속만을 허용
   - 다중 상속을 허용하면 여러 클래스로부터 상속받을 수 있기에 복합적 기능을 가진 클래스를 쉽게 작성이 가능
   - 그러나, 클래스 간의 관계가 매우 복잡해지고, 서로 다른 클래스로부터 상속받은 멤버 간 이름이 같은 경우 구별할 수 없다는 단점 존재
3. 자바에서는 이를 해결하기 위해 단일 상속을 허용하고, 필요한 클래스는 포함 관계로 포함시켜서 사용
4. 예제
```java
class Tv {
	boolean power; 	// 전원 상태 (on/off)
	int channel;		// 채널

	void power()       { 	power = !power; }
	void channelUp()   { 	++channel; }
	void channelDown() {	--channel; }
}

class VCR {
	boolean power; 	// 전원 상태 (on/off)
   int counter = 0;
	void power() { 	power = !power; }
	void play()  { /* 내용 생략 */ }
	void stop()  { /* 내용 생략 */ }
	void rew()   { /* 내용 생략 */ }
	void ff()    { /* 내용 생략 */ }
}

class TVCR extends Tv {
	VCR vcr = new VCR(); // VCR 클래스를 포함시켜서 사용
	int counter = vcr.counter;

	void play() {
		vcr.play();
	}

	void stop() {
		vcr.stop();
   }

	void rew() {
		vcr.rew();
   }

	void ff() {
		vcr.ff();	
   }
}
```
   - 자바에서는 다중 상속을 허용하지 않으므로 Tv 클래스를 조상으로하고, VCR클래스는 TVCR 클래스에 포함
   - TVCR 클래스에 VCR 클래스의 메서드와 일치하는 선언부를 가진 메서드를 선언하고, 내용은 VCR클래스의 것을 호출해서 사용
   - 즉, 외부적으로 TVCR클래스의 인스턴스를 사용하는 것처럼 보이지만, 내부적으로 VCR 클래스의 인스턴스를 생성해서 사용
