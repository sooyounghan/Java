-----
### Object 클래스
-----
1. 자바에서 모든 클래스의 최상위 부모 클래스는 항상 Object 클래스
<div align="center">
<img src="https://github.com/user-attachments/assets/f3e2a131-bc38-492d-804e-436ed8dbc2b2">
</div>

2. Parent (/lang/object)
```java
package lang.object;

// 부모가 없으면 묵시적으로 Object 클래스를 상속받음
public class Parent {
    public void parentMethod() {
        System.out.println("Parent.parentMethod");
    }
}
```
   - 다음과 같음
```java

package lang.object;

// 부모가 없으면 묵시적으로 Object 클래스를 상속받음
public class Parent extends Object {
    public void parentMethod() {
        System.out.println("Parent.parentMethod");
    }
}
```

   - 클래스에 상속 받을 부모 클래스가 없으면 묵시적으로 Object 클래스를 상속 받음
     + 쉽게 이야기해서 자바가 extends Object 코드를 넣어줌
     + 따라서 extends Object는 생략하는 것을 권장

```java
package lang.object;

public class Child extends Parent {
    public void childMethod() {
        System.out.println("Child.childMethod");
    }
}
```

   - 클래스에 상속 받을 부모 클래스를 명시적으로 지정하면 Object를 상속 받지 않음
   - 쉽게 이야기해서 이미 명시적으로 상속했기 때문에 자바가 extends Object 코드를 넣지 않음

3. 묵시적(Implicit) vs 명시적(Explicit)
   - 묵시적 : 개발자가 코드에 직접 기술하지 않아도 시스템 또는 컴파일러에 의해 자동으로 수행되는 것을 의미
   - 명시적 : 개발자가 코드에 직접 기술해서 작동하는 것을 의미

4. ObjectMain
```java
package lang.object;

public class ObjectMain {
    public static void main(String[] args) {
        Child child = new Child();
        child.childMethod();
        child.parentMethod();
        
        // toString()은 Object 클래스의 메서드
        String string = child.toString();
        System.out.println(string);
    }
}
```
   - toString()은 Object 클래스의 메서드 : 이 메서드는 객체의 정보를 제공
   - 실행 결과
```
Child.childMethod
Parent.parentMethod
lang.object.Child@4e50df2e
```
<div align="center">
<img src="https://github.com/user-attachments/assets/5e9e4899-84ad-4fde-a6ba-915506a314d4">
</div>

   - Parent는 Object를 묵시적으로 상속 받았기 때문에 메모리에도 함께 생성
     + child.toString()을 호출
     + 먼저 본인의 타입인 Child에서 toString()을 찾음 : 하지만, 없으므로 부모 타입으로 올라가서 찾음
     + 부모 타입인 Parent에서 찾음 : 없으므로 부모 타입으로 올라가서 찾음
     + 부모 타입인 Object에서 찾음 : Object 에 toString() 이 있으므로 이 메서드를 호출

5. 정리 : 자바에서 모든 객체의 최종 부모는 Object
6. 자바에서 Object 클래스가 최상위 부모 클래스인 이유
   - 모든 클래스가 Object 클래스를 상속 받는 이유
      + 공통 기능 제공
         * 객체의 정보를 제공하고, 이 객체가 다른 객체와 같은지 비교하고, 객체가 어떤 클래스로 만들어졌는지 확인하는 기능은 모든 객체에게 필요한 기본 기능
         * 이런 기능을 객체를 만들 때 마다 항상 새로운 메서드를 정의해서 만들어야 한다면 상당히 번거로울 것
         * 그리고 서로 다른 이름의 메서드를 만들어서 일관성이 없을 것
         * 예를 들어서 객체의 정보를 제공하는 기능을 만든다고 하면 어떤 개발자는 toString()으로 또 어떤 개발자는 objectInfo()와 같이 서로 다른 이름으로 만들 수 있음
         * 객체를 비교하는 기능을 만들 때도 어떤 개발자는 equals()로, 어떤 개발자는 same()으로 만들 수 있음
         * Object는 모든 객체에 필요한 공통 기능을 제공
         * Object는 최상위 부모 클래스이기 때문에 모든 객체는 공통 기능을 편리하게 제공(상속) 받을 수 있음

     + Object가 제공하는 기능
         * 객체의 정보를 제공하는 toString()
         * 객체의 같음을 비교하는 equals()
         * 객체의 클래스 정보를 제공하는 getClass()
         * 기타 여러가지 기능

     + 개발자는 모든 객체가 앞서 설명한 메서드를 지원한다는 것을 알고 있음
     + 따라서 프로그래밍이 단순화되고, 일관성을 가짐

     + 다형성의 기본 구현
         * 부모는 자식을 담을 수 있음
         * Object는 모든 클래스의 부모 클래스이므로, 따라서 모든 객체를 참조할 수 있음
         * Object 클래스는 다형성을 지원하는 기본적인 메커니즘을 제공
         * 모든 자바 객체는 Object 타입으로 처리될 수 있으며, 이는 다양한 타입의 객체를 통합적으로 처리할 수 있게 해줌
         * 쉽게 이야기해서 Object는 모든 객체를 다 담을 수 있으며, 타입이 다른 객체들을 어딘가에 보관해야 한다면 바로 Object에 보관하면 됨

    
