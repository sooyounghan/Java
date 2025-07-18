-----
### 다형성 시작
-----
1. 객체지향 프로그래밍의 대표적인 특징으로는 캡슐화, 상속, 다형성이 있음
2. 그 중에서 다형성은 객체지향 프로그래밍의 꽃이라 불림
   - 앞서 학습한 캡슐화나 상속은 직관적으로 이해하기 쉬움
   - 반면에 다형성은 제대로 이해하기도 어렵고, 잘 활용하기는 더 어려움
3. 다형성(Polymorphism)은 이름 그대로 "다양한 형태", "여러 형태"를 를 뜻함
   - 💡 프로그래밍에서 다형성은 한 객체가 여러 타입의 객체로 취급될 수 있는 능력
   - 보통 하나의 객체는 하나의 타입으로 고정되어 있음
   - 그런데 다형성을 사용하면 하나의 객체가 다른 타입으로 사용될 수 있다는 뜻

4. 다형적 참조
  - 다음과 같은 간단한 상속 관계를 코드
<div align="center">
<img src="https://github.com/user-attachments/assets/6e7e5fa5-e7ba-4705-9105-cfafff9b14f7">
</div>

  - 부모와 자식이 있으며, 각 다른 메서드를 가짐
  - Parent (/poly/basic)
```java
package poly.basic;

public class Parent {
    public void parentMethod() {
        System.out.println("Parent.parentMethod");
    }
}
```

  - Child
```java
package poly.basic;

public class Child extends Parent {
    public void childMethod() {
        System.out.println("Child.childMethod");
    }
}
```

  - PolyMain
```java
package poly.basic;

/**
 * 다형적 참조 : 부모는 자식을 품을 수 있음
 */
public class PolyMain {
    public static void main(String[] args) {
        // 부모 변수가 부모 인스턴스 참조
        System.out.println("Parent -> Parent");
        Parent parent = new Parent();
        parent.parentMethod();

        // 자식 변수가 자식 인스턴스 참조
        System.out.println("Child -> Child");
        Child child = new Child();
        child.parentMethod();
        child.childMethod();

        // 부모 변수가 자식 인스턴스 참조 (다형적 참조)
        System.out.println("Parent -> Child");
        Parent poly = new Child();
        poly.parentMethod();

        // Child child1 = new Parent(); // 자식은 부모를 담을 수 없음

        // 자식의 기능은 호출 불가. 컴파일 오류 발생
        // poly.childMethod();
    }
}
```
  - 실행 결과
```
Parent -> Parent
Parent.parentMethod

Child -> Child
Parent.parentMethod
Child.childMethod

Parent -> Child
Parent.parentMethod
```

5. 부모 타입의 변수가 부모 인스턴스 참조 - Parent → Parent : parent.parentMethod()
<div align="center">
<img src="https://github.com/user-attachments/assets/66691b5e-91e4-4149-af72-6a4c29409405">
</div>

   - 부모 타입의 변수가 부모 인스턴스를 참조 : Parent parent = new Parent()
   - Parent 인스턴스를 생성 : 이 경우 부모 타입인 Parent를 생성했기 때문에 메모리 상에 Parent만 생성 (자식은 생성되지 않음)
   - 생성된 참조값을 Parent 타입의 변수인 parent에 저장
   - parent.parentMethod()를 호출하면 인스턴스의 Parent 클래스에 있는 parentMethod()가 호출

6. 자식 타입의 변수가 자식 인스턴스 참조 - Child → Child : child.childMethod()
<div align="center">
<img src="https://github.com/user-attachments/assets/0dd07768-a167-4f53-a332-cef9e7bfcda1">
</div>

  - 자식 타입의 변수가 자식 인스턴스를 참조 : Child child = new Child()
  - Child 인스턴스를 생성
  - 이 경우 자식 타입인 Child를 생성했기 때문에 메모리 상에 Child와 Parent가 모두 생성
  - 생성된 참조값을 Child 타입의 변수인 child에 저장
  - child.childMethod()를 호출하면 인스턴스의 Child 클래스에 있는 childMethod()가 호출

7. 💡 다형적 참조 - 부모 타입의 변수가 자식 인스턴스 참조 (Parent → Child : poly.parentMethod())
<div align="center">
<img src="https://github.com/user-attachments/assets/0dd07768-a167-4f53-a332-cef9e7bfcda1">
</div>

  - 부모 타입의 변수가 자식 인스턴스를 참조 : Parent poly = new Child()
  - Child 인스턴스를 생성
    + 이 경우 자식 타입인 Child를 생성했기 때문에 메모리 상에 Child와 Parent가 모두 생성
    + 생성된 참조값을 Parent 타입의 변수인 poly에 저장
  - 부모는 자식을 담을 수 있으며, 부모 타입은 자식 타입을 담을 수 있음

  - Parent poly는 부모 타입
  - new Child() 를 통해 생성된 결과는 Child 타입
  - 자바에서 부모 타입은 자식 타입을 담을 수 있음 (Parent poly = new Child()) : 성공
  - 반대로 자식 타입은 부모 타입을 담을 수 없음 (Child child1 = new Parent()) : 컴파일 오류 발생

8. 다형적 참조
```java
Parent parent = new Parent()
Child child = new Child()
```

  - Parent 타입의 변수는 다음과 같이 자신인 Parent는 물론이고, 자식 타입까지 참조할 수 있음
  - 만약 손자가 있다면 손자도 그 하위 타입도 참조할 수 있음
```java
Parent poly = new Parent()
Parent poly = new Child()
Parent poly = new Grandson() : Child 하위에 손자가 있다면 가능
```

  - 자바에서 부모 타입은 자신은 물론이고, 자신을 기준으로 모든 자식 타입을 참조할 수 있음
  - 이것이 바로 다양한 형태를 참조할 수 있다고 해서 다형적 참조로 불림

9. 다형적 참조와 인스턴스 실행
    - poly.parentMethod()를 호출하면 먼저 참조값을 사용해서 인스턴스를 찾음
    - 그리고 다음으로 인스턴스 안에서 실행할 타입도 찾아야 함
    - poly 는 Parent 타입이므로, 따라서 Parent 클래스부터 시작 해서 필요한 기능을 찾음
    - 인스턴스의 Parent 클래스에 parentMethod()가 있으므로, 따라서 해당 메서드가 호출

10. 다형적 참조의 한계
<div align="center">
<img src="https://github.com/user-attachments/assets/84962884-0e55-49b2-8a02-47133e2d2b31">
</div>

  - Parent poly = new Child() 이렇게 자식을 참조한 상황에서 poly가 자식 타입인 Child에 있는 childMethod()를 호출할 경우
    + poly.childMethod()를 실행하면 먼저 참조값을 통해 인스턴스를 찾음
    + 그리고 다음으로 인스턴스 안에서 실행할 타입을 찾아야 함
    + 호출자인 poly 는 Parent 타입 : 따라서 Parent 클래스부터 시작해서 필요한 기능을 찾음
    + 그런데 상속 관계는 부모 방향으로 찾아 올라갈 수는 있지만 자식 방향으로 찾아 내려갈 수는 없음
    + Parent는 부모 타입이고 상위에 부모가 없으므로, 따라서 childMethod()를 찾을 수 없으므로 컴파일 오류가 발생

11. 다형적 참조의 핵심은 부모는 자식을 품을 수 있다는 것이다.
