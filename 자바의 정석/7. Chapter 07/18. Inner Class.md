-----
### 내부 클래스 (Inner Class)
-----
1. 클래스 내에 선언된 클래스 (두 클래스는 서로 긴밀한 관계에 있음)
2. 한 클래스를 다른 클래스의 내부 클래스로 선언하면 두 클래스 멤버들 간 서로 쉽게 접근 가능
3. 외부에는 불필요한 클래스를 감춤으로 코드의 복잡성을 줄일 수 있음 (캡슐화)
```java
class A { // 외부 클래스
  ...
  class B { // 내부 클래스
    ...
  }
  ...
}
```
4. 이 때, 내부 클래스 B는 외부 클래스 A를 제외하고 다른 클래스에서 잘 사용되지 않아야 함

-----
### 내부 클래스 (Inner Class) 종류와 특징
-----
1. 변수의 선언위치에 따른 종류와 같음
2. 내부 클래스의 종류와 특징
<div align="center">
<img src="https://github.com/sooyounghan/HTTP/assets/34672301/22e10c22-fde5-4251-9e29-129140a4412a">
</div>

-----
### 내부 클래스 (Inner Class) 선언
-----
1. 변수가 선언된 위치에 따라 인스턴스변수, 클래스변수(Static 변수), 지역변수로 나뉘듯 내부 클래스도 동일하게 선언된 위치에 따라 나뉨
2. 각 내부 클래스의 선언위치에 따라 같은 선언위치의 변수와 동일한 유효 범위(Scope)와 접근성(Accessibility)을 가짐
```java
class Outer {
  class InstanceInner { } // 인스턴스 클래스
  static class StaticInner { } // static 클래스

  void myMethod() {
    class LocalInner { } // 지역 클래스
  }
}
```

-----
### 내부 클래스 (Inner Class) 제어자와 접근성
-----
1. 인스턴스 클래스와 스태틱 클래스는 외부 클래스의 멤버변수 (인스턴스 변수와 클래스 변수)와 같은 위치에 선언하며, 멤버변수와 같은 성질을 가짐
2. 즉, 내부 클래스가 외부 클래스의 멤버와 같이 간주되고, 인스턴스 멤버와 static 멤버 간의 규칙이 내부 클래스에도 동일하게 적용
```java
class Outer {
  private class InstanceInner { } // 인스턴스 클래스
  protected static class StaticInner { } // static 클래스

  void myMethod() {
    class LocalInner { } // 지역 클래스
  }
}
````
3. 내부 클래스도 클래스이므로 abstract, final같은 제어자 사용 가능
4. 멤버변수들처럼 private, protected와 같은 접근 제어자 사용 가능
```java
class InnerEx1 { 
      class InstanceInner { 
            int iv = 100; 
//          static int cv = 100;            // Error. static 변수를 선언할 수 없음 
            final static int CONST = 100;   // final static은 상수 이므로 가능 
      } 

      static class StaticInner { 
            int iv = 200; 
            static int cv = 200;       // static클래스만 static 멤버 정의 가능 
      } 

      void myMethod() { 
            class LocalInner { 
                  int iv = 300; 
//                static int cv = 300;          // Error. static변수를 선언할 수 없음 
                  final static int CONST = 300; // final static은 상수이므로 허용
            } 
      } 

      public static void main(String args[]) { 
            System.out.println(InstanceInner.CONST); 
            System.out.println(StaticInner.cv); 
      } 
} 
```
<div align="center">
<img src="https://github.com/sooyounghan/HTTP/assets/34672301/583f3d6e-299a-4142-af8b-6513f053ceaf">
</div>

  - 내부 클래스 중 Static 클래스만 static 멤버를 가질 수 있음
  - static final, 즉 상수는 모든 내부 클래스에서 정의 가능

-----
### 내부 클래스 (Inner Class) 예제 
-----
```java
class InnerEx2 {
	class InstanceInner {}
	static class StaticInner {}

	// 인스턴스 멤버 간에는 서로 직접 접근이 가능
	InstanceInner iv = new InstanceInner();
	// static 멤버 간에는 서로 직접 접근이 가능
	static StaticInner cv = new StaticInner();

	static void staticMethod() {
    // static멤버는 인스턴스 멤버에 직접 접근 할수 없음
		// InstanceInner obj1 = new InstanceInner();	
		StaticInner obj2 = new StaticInner();

    // 접근하려면 아래와 같이 객체 생성
    // 인스턴스 클래스는 외부 클래스를 먼저 생성해야 생성 가능
		InnerEx2 outer = new InnerEx2();
		InstanceInner obj1 = outer.new InstanceInner();
	}

	void instanceMethod() {
    // 인스턴스 메서드에는 인스턴스 멤버와 static 모두 접근 가능
		InstanceInner obj1 = new InstanceInner();
		StaticInner obj2 = new StaticInner();
		// 메서드 내에 지역적으로 선언된 내부 클래스는 외부에서 접근할 수 없음
		// LocalInner lv = new LocalInner();
	}

	void myMethod() {
		class LocalInner {}
		LocalInner lv = new LocalInner();
	}
}
```
1. 인스턴스 멤버는 같은 클래스에 있는 인스턴스 멤버와 static 멤버 모두 직접 호출 가능 = 인스턴스 클래스 또한, 외부 클래스의 인스턴스 멤버를 객체 생성 없이 바로 사용 가능
2. static 멤버는 인스턴스 멤버를 직접 호출할 수 없음 = 외부 클래스의 인스턴스 멤버를 객체 생성 없이 사용 불가
3. 인스턴스 클래스는 Static 클래스의 멤버들을 객체 생성 없이 사용 가능
4. Staitc 클래스는 인스턴스 클래스의 멤버들을 객체 생성 없이 바로 사용 불가

-----
### 내부 클래스 (Inner Class) 예제 
-----
```java
class InnerEx3 {
	private int outerIv = 0;
	static int outerCv = 0;

	class InstanceInner {
		int iiv  = outerIv;  // 외부 클래스의 private 멤버도 접근 가능
		int iiv2 = outerCv;
	}

	static class StaticInner {
		// Static 클래스는 외부 클래스의 인스턴스 멤버에 접근 불가
		// int siv = outerIv;
		static int scv = outerCv;
	}

	void myMethod() {
		int lv = 0;
		final int LV = 0;  // JDK1.8부터 final 생략 가능
	
		class LocalInner {
			int liv = outerIv;
			int liv2 = outerCv;
			// 외부 클래스 지역 변수는 final이 붙은 변수(상수)만 접근 가능.
			// int liv3 = lv;	// 에러 아님(JDK1.8 이후) : 컴파일러가 자동으로 final 부여
			int liv4 = LV;	// OK
		}
	}
}
```
1. 인스턴스 클래스는 외부클래스의 인스턴스 멤버이기 때문에, 인스턴수 변수 및 static 변수 모두 사용 가능 (심지어 인스턴스 변수의 접근제어자가 private 이어도 가능)
2. Static 클래스는 외부 클래스의 static 멤버이기 때문에, 외부 클래스의 인스턴스 멤버를 사용할 수 없음, 오직 static 멤버만 사용 가능
3. 지역 클래스는 외부 클래스의 인스턴스 멤버와 static 멤버 모두 사용 가능하며, 지역 클래스가 포함된 메서드에 정의된 지역변수도 사용 가능
    - 본래 final이 붙은 지역변수만 접근이 가능했음 : 메서드가 수행을 마쳐서 지역변수가 소멸된 시점에도 지역 클래스의 인스턴스가 소멸된 지역변수를 참조하려는 경우 발생할 수 있기 때문임
    - JDK1.8부터 지역 클래스 지역 변수 앞에 fianl 생략 가능 : 컴파일러가 자동으로 붙여줌 (만약, 값이 바뀌는 문장 존재하면 컴파일 에러 발생)

-----
### 내부 클래스 (Inner Class) 예제 
-----
```java
class Outer { // 외부 클래스
	class InstanceInner { // 내부 클래스
		int iv=100; // 인스턴스 변수
	}
	static class StaticInner { // Static 클래스
		int iv=200; // Static 클래스 내 인스턴스 변수
		static int cv=300; // Static 변수
	}

	void myMethod() { 
		class LocalInner { // 지역 클래스
			int iv=400; // 지역 변수
		}
	}
}

class InnerEx4 {
	public static void main(String[] args) {
		// 인스턴스 클래스의 인스턴스를 생성하려면, 외부 클래스의 인스턴스를 먼저 생성해야함
		Outer oc = new Outer();
		Outer.InstanceInner ii = oc.new InstanceInner();

		System.out.println("ii.iv : "+ ii.iv);
		System.out.println("Outer.StaticInner.cv : " + Outer.StaticInner.cv);

	  // Static 내부 클래스의 인스턴스는 외부 클래스를 먼저 생성하지 않아도 됨
		Outer.StaticInner si = new Outer.StaticInner();
		System.out.println("si.iv : "+ si.iv);
	}
}
```
<div align="center">
<img src="https://github.com/sooyounghan/HTTP/assets/34672301/42a75582-9e06-4669-bd6b-6eb62b30663e">
</div>

1. 컴파일 시 생성되는 클래스 파일
```
InnerEx4.class
Outer.class
Outer$InstanceInner.class
Outer$StaticInner.class
Outer$1LocalInner.class
```

2. 컴파일을 하면 생성되는 파일명은 '외부클래스명$내부클래스명.class' 형식
3. 다만, 지역 내부 클래스는 다른 메서드의 같은 이름의 내부 클래스가 존재할 수 있으므로 내부 클래스 명 앞에 숫자가 붙음
```java
class Outer {
  void myMethod1() {
      class LocalInner { }
  }

  void myMethod2() {
      class LocalInner { }
  }
}
```
```
Outer.calss
Outer$1LocalInner.class
Outer$2LocalInner.class
```

-----
### 내부 클래스 (Inner Class) 예제 
-----
```java
class Outer {
	int value=10;	// Outer.this.value				

	class Inner {
		int value=20;	// this.value

		void method1() {
			int value=30; // value
			System.out.println("           value :" + value);
			System.out.println("      this.value :" + this.value);
			System.out.println("Outer.this.value :" + Outer.this.value);
		}
	} // Inner
} // Outer

class InnerEx5 {
	public static void main(String args[]) {
		Outer outer = new Outer();
		Outer.Inner inner = outer.new Inner();
		inner.method1();
	}
} 
```
<div align="center">
<img src="https://github.com/sooyounghan/HTTP/assets/34672301/c2538caf-3c81-4bf2-9c02-f7cac78a72ba">
</div>

: 내부 클래스와 외부 클래스에 선언된 변수의 이름이 같을 때는 변수 앞에 'this'(내부 클래스), '외부클래스명.this'(외부 클래스)를 붙여서 구별

-----
### 익명 클래스 (Anonymous Class)
-----
1. 다른 내부 클래스들과 다르게 이름이 없음
2. 즉, 클래스의 선언과 객체의 생성을 동시에 하므로, 단 한번만 사용될 수 있고 오직 하나의 객체만을 생성하는 일회용 클래스
```java
new 조상클래스이름() {
    // 멤버 선언
}

또는

new 구현인터페이스이름() {
    // 멤버 선언
}
```

3. 이름이 없기 때문에 생성자를 가질 수 없음
4. 조상클래스 이름이나 구현하고자 하는 인터페이스의 이름을 사용해서 정의
   - 하나의 클래스로 상속받는 동시에 인터페이스를 구현하거나 둘 이상의 인터페이스를 구현 불가
   - 오로지 단 하나의 클래스를 상속받거나 단 하나의 인터페이스만을 구현 가능
```java
class InnerEx6 {
	Object iv = new Object(){ void method(){} };		// 익명 클래스 (Scope : Instance)
	static Object cv = new Object(){ void method(){} };	// 익명 클래스 (Scope : Static)

	void myMethod() {
		Object lv = new Object(){ void method(){} };	// 익명 클래스 (Scope : 지역)
	}
}
```

```
InnerEx6.class
InnerEx6$1.class
InnerEx6$2.class
InnerEx6$3.class
````
5. 익명 클래스는 이름이 없으므로 '외부클래스명$숫자.class' 형식으로 클래스 파일명 결정

6. 익명 클래스 예제
```java
import java.awt.*;
import java.awt.event.*;

class  InnerEx7{
	public static void main(String[] args) {
		Button b = new Button("Start");
		b.addActionListener(new EventHandler());
	}
}

class EventHandler implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		System.out.println("ActionEvent occurred!!!");
	}
}
```
```java
import java.awt.*;
import java.awt.event.*;

class  InnerEx8 {
	public static void main(String[] args) {
		Button b = new Button("Start");
		b.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("ActionEvent occurred!!!");
				}
			} // 익명 클래스 끝
		);
	} // main 끝
} // InnerEx8 끝
```
