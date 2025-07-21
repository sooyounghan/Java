-----
### super - 부모 참조
-----
1. 부모와 자식의 필드명이 같거나 메서드가 오버라이딩 되어 있으면, 자식에서 부모의 필드나 메서드를 호출할 수 없음
2. 이 때 super 키워드를 사용하면 부모를 참조할 수 있음
   - super는 이름 그대로 부모 클래스에 대한 참조를 나타냄
3. 예시) 부모의 필드명과 자식의 필드명이 둘다 value로 동일
   - 메서드도 hello()로 자식에서 오버라이딩 되어 있음
   - 이 때 자식 클래스에서 부모 클래스의 value와 hello()를 호출하고 싶다면 super 키워드를 사용하면 됨

<div align="center">
<img src="https://github.com/user-attachments/assets/e9555ebd-cd25-427d-8f8b-5e980a5881e0">
</div>

4. Parent (/extends1/super1)
```java
package extends1.super1;

public class Parent {
    public String value = "parent";
    
    public void hello() {
        System.out.println("Parent.hello");
    }
}
```

5. Child
```java
package extends1.super1;

public class Child extends Parent {
    public String value = "child";

    @Override
    public void hello() {
        System.out.println("Child.hello");
    }

    public void call() {
        System.out.println("this value = " + this.value); // this 생략 가능
        System.out.println("super value = " + super.value);

        this.hello(); // this 생략 가능
        super.hello();
    }
}
```
  - this는 자기 자신의 참조를 뜻함 (this는 생략 가능)
  - super는 부모 클래스에 대한 참조를 뜻함
  - 필드 이름과 메서드 이름이 같지만 super를 사용해서 부모 클래스에 있는 기능을 사용할 수 있음

6. Super1Main
```java
package extends1.super1;

public class Super1Main {
    public static void main(String[] args) {
        Child child = new Child();
        child.call();
    }
}
```

7. 실행 결과
```
this value = child
super value = parent
Child.hello
Parent.hello
```

  - 실행 결과를 보면 super 를 사용한 경우 부모 클래스의 기능을 사용한 것을 확인

8. super 메모리 그림
<div align="center">
<img src="https://github.com/user-attachments/assets/8f840542-6b82-46b6-bae6-b2957b9841d5">
</div>

-----
### super - 생성자
-----
1. 상속 관계의 인스턴스를 생성하면 결국 메모리 내부에는 자식과 부모 클래스가 각각 다 만들어짐
2. Child를 만들면 부모인 Parent까지 함께 만들어지는 것
   - 따라서 각각의 생성자도 모두 호출되어야 함
   - 💡 상속 관계를 사용하면 자식 클래스의 생성자에서 부모 클래스의 생성자를 반드시 호출해야 함 (규칙)
3. 상속 관계에서 부모의 생성자를 호출할 때는 super(...)를 사용
4. ClassA (/extends1/super2)
```java
package extends1.super2;

public class ClassA {
    public ClassA() {
        System.out.println("ClassA 생성자");
    }
}
```
  - ClassA는 최상위 부모 클래스

5. ClassB
```java
package extends1.super2;

public class ClassB extends ClassA {
    public ClassB(int a) {
        super(); // 기본 생성자 생략 가능
        System.out.println("ClassB 생성자 a = " + a);
    }

    public ClassB(int a, int b) {
        super(); // 기본 생성자 생략 가능
        System.out.println("ClassB 생성자 a = " + a + ", b = " + b);
    }
}
```
  - ClassB는 ClassA를 상속 받음
    + 💡 상속을 받으면 생성자의 첫줄에 super(...)를 사용해서 부모 클래스의 생성자를 호출해야 함
    + 예외로 생성자 첫줄에 this(...)를 사용할 수는 있음
    + 하지만 super(...)는 자식의 생성자 안에서 언젠가는 반드시 호출해야 함

  - 💡 부모 클래스의 생성자가 기본 생성자(파라미터가 없는 생성자)인 경우에는 super() 를 생략할 수 있음
    + 상속 관계에서 첫줄에 super(...)를 생략하면 자바는 부모의 기본 생성자를 호출하는 super()를 자동으로 만들어줌
    + 참고로 기본 생성자를 많이 사용하기 때문에 편의상 이런 기능을 제공

6. ClassC
```java
package extends1.super2;

public class ClassC extends ClassB {
    public ClassC() {
        super(10, 20);
        System.out.println("ClassC 생성자");
    }
}
```
   - ClassC는 ClassB를 상속 받았으며, ClassB 다음 두 생성자가 존재
     + ClassB(int a)
     + ClassB(int a, int b)
   - 생성자는 하나만 호출할 수 있으므로, 두 생성자 중에 하나를 선택하면 됨
     + super(10, 20)를 통해 부모 클래스의 ClassB(int a, int b) 생성자를 선택
   - 참고로 ClassC의 부모인 ClassB에는 기본 생성자가 없으므로, 따라서 부모의 기본 생성자를 호출하는 super() 를 사용하거나 생략할 수 없음

7. Super2Main
```java
package extends1.super2;

public class Super2Main {
    public static void main(String[] args) {
        ClassC classC = new ClassC();
    }
}
```

  - 실행 결과
```
ClassA 생성자
ClassB 생성자 a = 10, b = 20
ClassC 생성자
```

  - 실행해보면 ClassA → ClassB → ClassC 순서로 실행
  - 생성자의 실행 순서가 결과적으로 최상위 부모부터 실행되어서 하나씩 아래로 내려오는 것
  - 따라서 초기화는 최상위 부모부터 이루어짐
    + 왜냐하면 자식 생성자의 첫줄에서 부모의 생성자를 호출해야 하기 때문임

<div align="center">
<img src="https://github.com/user-attachments/assets/956c7deb-43be-40fc-8be2-a0bd1cd31fe1">
</div>

8. 1 ~ 3까지의 과정
  - new ClassC()를 통해 ClassC 인스턴스를 생성
  - 이 때 ClassC()의 생성자가 먼저 호출되는 것이 맞음
  - 하지만 ClassC()의 성생자는 가장 먼저 super(..)를 통해 ClassB(...)의 생성자를 호출
  - ClassB()의 생성자도 부모인 ClassA()의 생성자를 가장 먼저 호출

9. 4~6까지의 과정
   - ClassA()의 생성자는 최상위 부모
   - 생성자 코드를 실행하면서 "ClassA 생성자"를 출력
   - ClassA() 생성자 호출이 끝나면 ClassA()를 호출한 ClassB(...) 생성자로 제어권이 돌아감
   - ClassB(...) 생성자가 코드를 실행하면서 "ClassB 생성자 a = 10, b = 20" 를 출력
   - 생성자 호출이 끝나면 ClassB(...)를 호출한 ClassC() 의 생성자로 제어권이 돌아감
   - ClassC()가 마지막으로 생성자 코드를 실행하면서 "ClassC 생성자"를 출력

10. 정리
   - 상속 관계의 생성자 호출은 결과적으로 부모에서 자식 순서로 실행
   - 따라서 부모의 데이터를 먼저 초기화하고 그 다음에 자식의 데이터를 초기화
   - 상속 관계에서 자식 클래스의 생성자 첫줄에 반드시 super(...)를 호출해야 함
   - 단, 기본 생성자(super())인 경우 생략할 수 있음

11. this(...)와 함께 사용
  - 코드의 첫줄에 this(...)를 사용하더라도 반드시 한번은 super(...)를 호출해야 함
  - 코드 변경
```java
package extends1.super2;

public class ClassB extends ClassA {
    public ClassB(int a) {
        // super(); // 기본 생성자 생략 가능
        this(a, 0); // 기본 생성자 생략 가능
        System.out.println("ClassB 생성자 a = " + a);
    }

    public ClassB(int a, int b) {
        super(); // 기본 생성자 생략 가능
        System.out.println("ClassB 생성자 a = " + a + ", b = " + b);
    }
}
```
```java
package extends1.super2;

public class Super2Main {
    public static void main(String[] args) {
        // ClassC classC = new ClassC();
        ClassB classB = new ClassB(10);
    }
}
```
  - 실행 결과
```
ClassA 생성자
ClassB 생성자 a = 10, b = 0
ClassB 생성자 a = 10
```
