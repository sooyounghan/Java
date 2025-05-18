-----
### 상속 (Inheritance)
-----
1. 기존의 클래스를 재사용하여 새로운 클래스를 작성하는 것
   - 적은 양의 코드로 새로운 코드 작성 가능
   - 코드를 공통적으로 관리할 수 있으므로 코드 추가 및 변경에 용이

2. 새로 작성하고자 하는 클래스 이름 뒤에 상속받고자 하는 클래스 이름을 키워드 'extends'와 함께 작성
3. 예시
```java
class Child extends Parent {
  // ...
}
```
  - 이 두 클래스는 서로 상속 관계에 있음
  - 상속해주는 클래스를 '조상 클래스', 상속 받는 클래스 '자손 클래스'

4. 조상 클래스 : 부모(Parent) 클래스, 상위(Super) 클래스, 기반(Base) 클래스
5. 자식 클래스 : 자식(Child) 클래스, 하위(Sub) 클래스, 파생된(Deprived) 클래스

```java
class Parent { }
class Child extends Parent { }
```
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/607528f5-d6c9-4b00-877b-346230a471f2">
</div>


    A. 클래스는 타원으로 표현
    B. 클래스 간의 상속 관계는 화살표로 표시
    C. 클래스 간의 상속 관계를 그림으로 표현한 것 : 상속 계층도(Class Hierarchy)

   - 만약, Parent 클래스에 age라는 변수를 멤버변수로 추가하면, 자손 클래스의 멤버도 동일하게 모두 상속
   - 즉, Child 클래스는 자동적으로 age라는 멤버변수를 가지는 효과 

```java
class Parent {
    int age;
}

class Child extends Parent { }
```
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/b2c9f79f-1d80-4abe-a213-8b3dd47c1013">
</div>

  - 반대로 자손인 Child 클래스에 새로운 멤버로 play()를 메서드를 추가하면?
```java
class Parent {
    int age;
}

class Child extends Parent {
    void play() {
        System.out.println("놀자!");
    }
}
```
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/5c9f2717-7884-4198-9497-8e28576a8e70">
</div>

  - Child 클래스에 새로운 코드가 추가되어도 조상인 Parent 클래스는 아무런 영향을 받지 않음

6. 조상 클래스가 변경되면, 자손 클래스는 자동적으로 영향을 받게 되지만, 자손 클래스가 변경되는 것은 조상 클래스에 아무런 영향을 주지 못함
7. 자손 클래스는 조상 클래스의 모든 멤버를 상속 받으므로 항상 조상 클래스보다 같거나 많은 멤버를 가짐
8. 💡 단, 생성자와 초기화 블럭은 상속되지 않음 (멤버만 상속)
9. 접근 제어자(Access Modifier)가 private 또는 default인 멤버들은 상속되지 않는 것이 아니라 상속은 받지만, 자손 클래스로부터 접근이 제한됨을 의미

```java
class Parent { }
class Child extends Parent { }
class Child2 extends Parent { }
```
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/abee4c81-afe9-4459-9922-945ed21184dc">
</div>

  - 클래스 Child와 Child2는 모두 Parent로 상속받고 있으므로 Parent 클래스와는 서로 상속 관계
  - 그러나, Child와 Child 클래스 간에는 서로 아무런 관계도 성립하지 않음
  - 즉, 부모와 자식의 상속 관계만이 존재

```java
class Parent { }
class Child extends Parent { }
class Child2 extends Parent { }
class GrandChild extends Child { }
```
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/96d408bd-05b7-45c1-8123-e0f5c5ede2b5">
</div>

  - Child 클래스는 GrandChild의 직접 조상, Parent 클래스는 간접 조상이 됨

```java
class Parent {
    int age;
}
class Child extends Parent { }
class Child2 extends Parent { }
class GrandChild extends Child { }
```
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/dba89959-de33-49af-8ee0-867b56da2b2d">
</div>

  - Parent는 각 자손 클래스의 조상이므로 추가된 멤버변수 age는 모든 자손에 추가
  - 만약, 이 멤버변수를 제거한다면 자손 클래스에서도 제거
  - 이처럼, 조상 클래스만 변경해도 모든 자손 클래스에, 자손의 자손 클래스까지 영향을 미치기 때문에 클래스 간 상속 관계를 맺어주면 자손 클래스들의 공통적 부분은 조상 클래스에서 관리
  - 자손 클래스는 자신에 정의된 멤버들만 관리하면 되므로 각 클래스의 코드가 적어져 관리가 용이

```java
class Tv {
	boolean power; 	// 전원 상태 (on/off)
	int channel;	// 채널

	void power()        {   power = !power; }
	void channelUp()    { 	 ++channel;     }
	void channelDown()  {	 --channel;	    }
}

class CaptionTv extends Tv {
	boolean caption;		// Caption 상태 (on/off)
	void displayCaption(String text) {
		if (caption) {	// 캡션 상태가 on(true)일 때만, text 출력.
			System.out.println(text);
		}
	}
}

class CaptionTvTest {
	public static void main(String args[]) {
		CaptionTv ctv = new CaptionTv();
		ctv.channel = 10;				// 조상 클래스로부터 상속받은 멤버
		ctv.channelUp();				// 조상 클래스로부터 상속받은 멤버
		System.out.println(ctv.channel);
		ctv.displayCaption("Hello, World");	
		ctv.caption = true;				    // 캡션(자막) 기능을 켬
		ctv.displayCaption("Hello, World");	
	}
}
```
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/b4014df4-dc7f-464b-953a-d660666f0bd0">
<img src="https://github.com/sooyounghan/Java/assets/34672301/fcc82eb3-4a2f-438e-99bc-f58eb15ce050">
</div>

  - 자손 클래스의 인스턴스를 생성하면 조상 클래스의 멤버도 함께 생성되므로 따로 조상 클래스의 인스턴스를 생성하지 않고도 조상 클래스 멤버 사용 가능
  - 즉, 자손 클래스의 인스턴스를 생성하면, 조상 클래스의 멤버와 자손 클래스의 멤버가 합쳐진 하나의 인스턴스로 생성


