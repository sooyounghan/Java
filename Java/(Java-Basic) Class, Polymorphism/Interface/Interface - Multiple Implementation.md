-----
### 인터페이스 - 다중 구현
-----
1. 자바가 다중 상속을 지원하지 않는 이유 
   - 자바는 다중 상속을 지원하지 않으므로, extends 대상은 하나만 선택할 수 있음
   - 즉, 부모를 하나만 선택할 수 있다는 뜻
   - 물론 부모가 또 부모를 가지는 것은 괜찮음

<div align="center">
<img src="https://github.com/user-attachments/assets/f6491e30-9d94-4ba8-8478-3f4b8d08c0fe">
</div>

   - 만약 비행기와 자동차를 상속 받아서 하늘을 나는 자동차를 만든다고 가정
   - 만약 그림과 같이 다중 상속을 사용하게 되면 AirplaneCar 입장에서 move()를 호출할 때 어떤 부모의 move()를 사용해야 할지 애매한 문제가 발생 : 다이아몬드 문제
   - 다중 상속을 사용하면 클래스 계층 구조가 매우 복잡해질 수 있으며, 이런 문제점 때문에 자바는 클래스의 다중 상속을 허용하지 않음
   - 대신에 인터페이스의 다중 구현을 허용하여 이러한 문제를 피함
   - 인터페이스는 모두 추상 메서드로 이루어져 있기 때문에 다중 구현 허용

2. 인터페이스 다중 구현 그림
<div align="center">
<img src="https://github.com/user-attachments/assets/128ac362-dbd9-4b5a-9e0c-efd8381761ff">
</div>

   - InterfaceA, InterfaceB는 둘다 같은 methodCommon()을 가지고 있음
   - 그리고 Child는 두 인터페이스를 구현
   - 상속 관계의 경우 두 부모 중에 어떤 한 부모의 methodCommon()을 사용해야 할지 결정해야 하는 다이아몬드 문제가 발생
   - 하지만 인터페이스 자신은 구현을 가지지 않음
   - 대신에 인터페이스를 구현하는 곳에서 해당 기능을 모두 구현해야 함
   - 여기서 InterfaceA, InterfaceB 는 같은 이름의 methodCommon()를 제공하지만 이것의 기능은 Child가 구현
   - 그리고 오버라이딩에 의해 어차피 Child 에 있는 methodCommon()이 호출됨
   - 결과적으로 두 부모중에 어떤 한 부모의 methodCommon()을 선택하는 것이 아니라 그냥 인터페이스들을 구현한 Child 에 있는 methodCommon()이 사용
   - 이런 이유로 인터페이스는 다이아몬드 문제가 발생하지 않으며, 따라서 인터페이스의 경우 다중 구현을 허용

3. InterfaceA (/poly/diamond)
```java
package poly.diamond;

public interface InterfaceA {
    void methodA();
    void methodCommon();
}
```

4. InterfaceB
```java
package poly.diamond;

public interface InterfaceB {
    void methodB();
    void methodCommon();
}
```

5. Child
```java
package poly.diamond;

public class Child implements InterfaceA, InterfaceB {
    @Override
    public void methodA() {
        System.out.println("Child.methodA");
    }

    @Override
    public void methodB() {
        System.out.println("Child.methodB");
    }

    @Override
    public void methodCommon() {
        System.out.println("Child.methodCommon");
    }
}
```

   - implements InterfaceA, InterfaceB 와 같이 다중 구현을 할 수 있음
   - implements 키워드 뒤에 , 로 여러 인터페이스를 구분
   - methodCommon()의 경우 양쪽 인터페이스에 다 있지만 같은 메서드이므로 구현은 하나만 하면 됨

6. DiamondMain
```java
package poly.diamond;

// 인터페이스 다중 구현
public class DiamondMain {
    public static void main(String[] args) {
        InterfaceA a = new Child();
        a.methodA();
        a.methodCommon();
        
        InterfaceB b = new Child();
        b.methodB();
        b.methodCommon();
    }
}
```
   - 실행 결과
```
Child.methodA
Child.methodCommon

Child.methodB
Child.methodCommon
```

<div align="center">
<img src="https://github.com/user-attachments/assets/9c08b34a-655f-4859-b172-3069fd7a12ce">
</div>

  - a.methodCommon()을 호출하면 먼저 x001 Child 인스턴스를 찾음
  - 변수 a 가 InterfaceA 타입이므로 해당 타입에서 methodCommon()을 찾음
  - methodCommon()은 하위 타입인 Child에서 오버라이딩 되어 있으므로, 따라서 Child의 methodCommon()이 호출

<div align="center">
<img src="https://github.com/user-attachments/assets/c1ef467c-6a33-4ada-a5de-0394ed15e8ee">
</div>

   - b.methodCommon()을 호출하면 먼저 x002 Child 인스턴스를 찾음
   - 변수 b 가 InterfaceB 타입이므로 해당 타입에서 methodCommon()을 찾음
   - methodCommon()은 하위 타입인 Child에서 오버라이딩 되어 있으므로, 따라서 Child 의 methodCommon()이 호출    
