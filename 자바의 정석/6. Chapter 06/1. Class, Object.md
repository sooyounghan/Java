-----
### 객체 지향 언어 (Object Oriented Language)의 특징
----- 
1. 코드의 재사용성이 높음
   - 즉, 새로운 코드 작성 시 기존의 코드를 이용해 쉽게 작성 가능
2. 코드의 관리 용이
   - 즉, 코드간의 관계를 이용해 적은 노력으로 쉽게 코드 변경 가능
3. 신뢰성이 높은 프로그래밍 가능
   - 제어자와 메서드를 이용해 데이터를 보호하고 올바른 값 유지 가능
   - 코드 중복 제거해 코드 불일치로 인한 오동작 방지 가능
  
-----
### 클래스(Class)와 객체(Object)
----- 
1. 클래스 (Class)
   - 정의 : 객체를 정의해놓은 것, 객체의 설계도 또는 틀
   - 용도 : 객체를 생성하는데 사용

2. 객체 (Object)
   - 정의 : 실제로 존재하는 것, 사물 또는 개념
   - 용도 : 객체가 가지고 있는 기능과 속성에 따라 다름
  
3. 클래스는 단지 객체를 생성하는데 사용될 뿐, 객체 그 자체는 아님
4. 즉, 객체를 사용하기 위해서는 먼저 클래스로부터 객체를 생성하는 과정이 선행되어야 함

-----
### 객체(Object)와 인스턴스(Instance)
----- 
<div align="center">
<img src="https://github.com/sooyounghan/JavaScript/assets/34672301/11001276-b53b-4753-be4f-543049300398">
</div>

1. 클래스의 인스턴스화 : 클래스로부터 객체를 만드는 과정
2. 인스턴스(Instance) : 클래스로부터 만들어진 객체
3. 객체의 구성 요소 - 속성과 기능
   - 객체는 다수의 속성과 기능을 가짐
   - 객체의 멤버(Member) : 객체가 가지고 있는 속성과 기능

4. 속성(Property) : 멤버 변수(Member Variable), 특성(Attribute), 필드(Field), 상태(State)
5. 기능(Function) : 메서드(Method), 함수(Function), 행위(Behavior)

-----
### 인스턴스의 생성과 사용
----- 
```java
클래스명 변수명; // 클래스의 객체를 참조하기 위한 참조 변수 선언
변수명 = new 클래스명(); // 클래스의 객체 생성 후, 객체의 주소를 참조 변수에 저장

Tv t; // Tv 클래스 타입의 참조 변수 t 선언
t = new Tv(); // Tv 인스턴스 생성 후, 생성된 Tv 인스턴스의 주소를 t에 저장
```

```java
class Tv { 
     // Tv 속성 (멤버변수)
     String color;           	// 색상
     boolean power;         	// 전원 상태 (On / Off) 
     int channel;           	// 채널

     // Tv 기능 (메서드)
     void power()   { power = !power; }  // TV를 켜거나 끄는 기능을 하는 메서드
     void channelUp()   {  ++channel; }  // TV의 채널을 높이는 기능을 하는 메서드 
     void channelDown() { --channel; }   // TV의 채널을 낮추는 기능을 하는 메서드  
}

class TvTest2 { 
      public static void main(String args[]) { 
            Tv t;                  // Tv인스턴스를 참조하기 위한 변수 t 선언       
            t = new Tv();          // Tv인스턴스 생성
            t.channel = 7;         // Tv인스턴스의 멤버변수 channel 값을 7로 설정
            t.channelDown();       // Tv인스턴스의 메서드 channelDown() 호출 
            System.out.println("현재 채널은 " + t.channel + " 입니다."); 
      } 
} 
```
<div align="center">
<img src="https://github.com/sooyounghan/JavaScript/assets/34672301/077d6393-9b74-4931-ac0c-cd11fab99c00">
<img src="https://github.com/sooyounghan/JavaScript/assets/34672301/e2ebc576-b27b-47c8-8320-f5de3cecb7e6">
</div>


1. 인스턴스는 오직 참조변수를 통해서만 접근할 수 있음
2. 즉, 참조변수의 타입은 인스턴스 타입과 일치해야 함
```java
class Tv { 
     // Tv 속성 (멤버변수)
     String color;           	// 색상
     boolean power;         	// 전원 상태 (On / Off) 
     int channel;           	// 채널

     // Tv 기능 (메서드)
     void power()   { power = !power; }  // TV를 켜거나 끄는 기능을 하는 메서드
     void channelUp()   {  ++channel; }  // TV의 채널을 높이는 기능을 하는 메서드 
     void channelDown() { --channel; }   // TV의 채널을 낮추는 기능을 하는 메서드  
}

class TvTest { 
      public static void main(String args[]) { 
          Tv t1 = new Tv();  // Tv t1; t1 = new Tv(); 을 한 문장으로 표현 가능
          Tv t2 = new Tv();
          System.out.println("t1의 channel값은 " + t1.channel + "입니다.");
          System.out.println("t2의 channel값은 " + t2.channel + "입니다.");
          
          t1.channel = 7;	// channel : 7.
	      	System.out.println("t1의 channel값을 7로 변경하였습니다.");
          
          System.out.println("t1의 channel값은 " + t1.channel + "입니다.");
          System.out.println("t2의 channel값은 " + t2.channel + "입니다.");
      } 
} 
```

<div align="center">
<img src="https://github.com/sooyounghan/JavaScript/assets/34672301/db90c5dd-7fe4-4f8b-9d79-e97c8efb5c1e">
</div>

  - 같은 클래스로부터 생성되었을지라도, 각 인스턴스의 속성(멤버변수)은 서로 다른 값을 유지 가능
  - 메서드의 내용은 모든 인스턴스에 대해 동일

```java
class Tv { 
  	// Tv 속성 (멤버변수)
  	String color;           	// 색상
  	boolean power;         	// 전원 상태 (On / Off) 
  	int channel;           	// 채널
  
  	// Tv 기능 (메서드)
  	void power()   { power = !power; }  // TV를 켜거나 끄는 기능을 하는 메서드
  	void channelUp()   {  ++channel; }  // TV의 채널을 높이는 기능을 하는 메서드 
  	void channelDown() { --channel; }   // TV의 채널을 낮추는 기능을 하는 메서드  
}

class TvTest3 {
  	public static void main(String args[]) { 
    		Tv t1 = new Tv();  // Tv t1; t1 = new Tv(); 을 한 문장으로 표현 가능
    		Tv t2 = new Tv();
    		System.out.println("t1의 channel값은 " + t1.channel + "입니다.");
    		System.out.println("t2의 channel값은 " + t2.channel + "입니다.");
    		
    		t2 = t1; // t1이 저장하고 있는 값(주소)를 t2에 저장
    		t1.channel = 7;	// channel : 7.
    		System.out.println("t1의 channel값을 7로 변경하였습니다.");
    		
    		System.out.println("t1의 channel값은 " + t1.channel + "입니다.");
    		System.out.println("t2의 channel값은 " + t2.channel + "입니다.");
    } 
}
```
<div align="center">
<img src="https://github.com/sooyounghan/JavaScript/assets/34672301/910c8a75-10a7-4ae9-937b-51dfa9a18894">
<img src="https://github.com/sooyounghan/JavaScript/assets/34672301/6e1b8e7b-3880-4394-8f4c-d64705629efb">
</div>

```
t2 = t1; // t1이 저장하고 있는 값(주소)를 t2에 저장
```
  - t1은 참조변수이므로, 인스턴스의 주소를 저장
  - 이 t2가 가지고 있던 값은 잃어버리고, t1에 저장된 값이 t2에 저장
  - 그러므로 t2가 원래 참조하고 있던 인스턴스는 더 이상 사용할 수 없음
  - 자신을 참조하고 있는 참조변수가 하나도 없는 인스턴스는 더 이상 사용 불가 : Garbage Collector에 의해 자동적으로 메모리에서 제거

```
t1.channel = 7;
```
  - t1, t2 모두 같은 Tv클래스의 인스턴스를 가리키므로 값은 동일하게 7

3. 참조변수에는 하나의 값(주소)만 저장이 가능
   - 둘 이상의 참조변수가 하나의 인스턴스를 가리키는 (참조하는) 것은 가능
   - 하나의 참조 변수로 여러 개의 인스턴스를 가리키는 것은 불가능

<div align="center">
<img src="https://github.com/sooyounghan/JavaScript/assets/34672301/5fe4ce80-4255-4f82-9dce-26f5c7c7a6e5">
</div>

-----
### 객체 배열
----- 
1. 객체 역시 배열로 다루는 것이 가능
2. 객체 배열 안에 객체가 저장되는 것이 아닌 객체의 주소가 저장 (즉, 참조변수들을 하나로 묶은 참조 변수 배열)

```java
Tv tv1, tv2, tv3;
Tv[] tvArr = new TV[3]; // 길이가 3인 Tv타입 참조변수 배열
```
  - 즉, 길이가 3인 객체 배열, tvArr을 생성하면 각 요소는 참조변수의 기본 값인 null로 자동 초기화
  - 이 객체 배열은 3개의 객체, 정확히는 객체의 주소를 저장 가능
<div align="center">
<img src="https://github.com/sooyounghan/JavaScript/assets/34672301/04846adc-1cac-4077-b65c-8b674f0d7ae6">
</div>

```java
Tv[] tvArr = new Tv[3]; // 참조변수 배열(객체 배열) 생성

// 객체를 생성해 배열의 각 요소에 저장
tvArr[0] = new Tv();
tvArr[1] = new Tv();
tvArr[2] = new Tv();
```
```java
// 배열의 초기화 블록 이용
Tv[] tvArr = { new Tv(), new Tv(), new Tv() };
```

```java
// for문 가능
Tv[] tvArr = new Tv[100];

for(int i = 0; i < tvArr.length; i++) {
    tvArr[i] = new Tv();
}
```

```java
class TvTest4 {
  	public static void main(String args[]) {
    		Tv[] tvArr = new Tv[3]; // 참조변수 배열(객체 배열) 생성
    
    		// Tv객체를 생성해 Tv객체 배열의 각 요소에 저장
    		for(int i=0; i < tvArr.length;i++) {
      			tvArr[i] = new Tv();
      			tvArr[i].channel = i+10; // tvArr[i]의 channel에 i+10을 저장
    		}
    
    		for(int i=0; i < tvArr.length;i++) {
      			tvArr[i].channelUp();  // tvArr[i]의 메서드를 호출, 채널이 1 증가
      			System.out.printf("tvArr[%d].channel=%d%n",i,tvArr[i].channel);
    		}
  	} // main 끝
}

class Tv { 
    // Tv 속성 (멤버변수)
    String color;           	// 색상
    boolean power;         	// 전원 상태 (On / Off) 
    int channel;           	// 채널
    
    // Tv 기능 (메서드)
    void power()   { power = !power; }  // TV를 켜거나 끄는 기능을 하는 메서드
    void channelUp()   {  ++channel; }  // TV의 채널을 높이는 기능을 하는 메서드 
    void channelDown() { --channel; }   // TV의 채널을 낮추는 기능을 하는 메서드  
}
```
<div align="center">
<img src="https://github.com/sooyounghan/JavaScript/assets/34672301/c2345d0a-f0cb-4c86-85bf-b5f011e978c5">
</div>

-----
### 클래스의 또 다른 정의
----- 
        A. 변수 : 하나의 데이터를 저장할 수 있는 공간
        B. 배열 : 같은 종류의 여러 데이터를 하나의 집합으로 저장할 수 있는 공간
        C. 구조체 : 서로 관련된 여러 데이터를 종류에 관계 없이 하나의 집합으로 저장할 수 있는 공간
        D. 클래스 : 데이터와 함수의 결합 (구조체 + 함수)
<div align="center">
<img src="https://github.com/sooyounghan/JavaScript/assets/34672301/e34682a7-e774-451c-821a-e148981e1e5a">
</div>

1. 클래스는 데이터와 함수의 결합
2. 클래스는 사용자 정의 타입 (User-Defined Type)
   - 사용자 정의 타입 : 프로그래밍에서 제공하는 자료형(Primitive Type) 외 프로그래머가 서로 관련된 변수들을 묶어 하나의 타입으로 새로 추가하는 것
   - 객체지향 언어에서는 클래스가 곧 사용자 정의 타입

<div align="center">
<img src="https://github.com/sooyounghan/JavaScript/assets/34672301/c092c13b-a3d9-4d39-bd5e-387b8448f3b6">
</div>

