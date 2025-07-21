-----
### 상속과 접근 제어
-----
<div align="center">
<img src="https://github.com/user-attachments/assets/3373a90c-ab67-4801-882e-8eae85bfbf2f">
</div>

1. 접근 제어자를 표현하기 위해 UML 표기법을 일부 사용
    - ```+``` : public
    - ```#``` : protected
    - ```~``` : default
    - ```-``` : private

2. 접근 제어자의 종류
    - private : 모든 외부 호출을 막음
    - default (package-private) : 같은 패키지 안에서 호출은 허용
    - protected : 같은 패키지안에서 호출은 허용, 패키지가 달라도 상속 관계의 호출은 허용
    - public : 모든 외부 호출을 허용
    - 순서대로 private 이 가장 많이 차단하고, public 이 가장 많이 허용 (private → default → protected → public)

3. Parent (/extends1/access/parent)
```java
package extends1.access.parent;

public class Parent {
    public int publicValue;
    protected int protectedValue;
    int defaultValue;
    private int privateValue;

    public void publicMethod() {
        System.out.println("Parent.publicMethod");
    }

    protected void protectedMethod() {
        System.out.println("Parent.protectedMethod");
    }

    void defaultMethod() {
        System.out.println("Parent.defaultMethod");
    }

    private void privateMethod() {
        System.out.println("Parent.privateMethod");
    }
    
    public void printParent() {
        System.out.println("== Parent 메서드 안 ==");
        System.out.println("publicValue : " + publicValue);
        System.out.println("protectedValue : " + protectedValue);
        System.out.println("defaultValue : " + defaultValue); // 부모 메서드 안에서 접근 가능
        System.out.println("privateValue : " + privateValue); // 부모 메서드 안에서 접근 가능
        
        defaultMethod(); // 부모 메서드 안에서 모두 접근 가능
        privateMethod(); // 부모 메서드 안에서 모두 접근 가능ㄴ
        
        
    }
}
```
  - 부모 클래스인 Parent에는 public, protected, default, private과 같은 모든 접근 제어자가 필드와 메서드에 모두 존재


4. Child (/extends1/access/child)
```java
package extends1.access.child;

import extends1.access.parent.Parent;

public class Child extends Parent {
    public void call() {
        publicValue = 1;
        protectedValue = 1; // 상속 관계 또는 다른 패키지
        // defaultValue = 1; // 다른 패키지 접근 불가, 컴파일 오류
        // privateValue = 1; // 접근 불가, 컴파일 오류
        
        publicMethod();
        protectedMethod(); // 상속 관계 또는 같은 패키지
        // defaultMethod(); // 다른 패키지 접근 불가, 컴파일 오류
        // privateMethod(); // 접근 불가, 컴파일 오류

        printParent();
    }
}
```
  - 둘의 패키지가 다르다는 부분 유의
  - 자식 클래스인 Child에서 부모 클래스인 Parent에 얼마나 접근할 수 있는지 확인
    + publicValue = 1 : 부모의 public 필드에 접근 (public이므로 접근할 수 있음)
    + protectedValue = 1 : 부모의 protected 필드에 접근 (자식과 부모는 다른 패키지이지만, 상속 관계이므로 접근할 수 있음)
    + defaultValue = 1 : 부모의 default 필드에 접근 (자식과 부모가 다른 패키지이므로 접근할 수 없음)
    + privateValue = 1 : 부모의 private 필드에 접근 (private은 모든 외부 접근을 막으므로 자식이라도 호출할 수 없음)
  - 메서드의 경우도 앞서 설명한 필드와 동일

5. ExtendsAccessMain (/extends1/access)
```java
package extends1.access;

import extends1.access.child.Child;

public class ExtendsAccessMain {
    public static void main(String[] args) {
        Child child = new Child();
        child.call();
    }
}
```
  - 실행 결과
```
Parent.publicMethod
Parent.protectedMethod
== Parent 메서드 안 ==
publicValue : 1
protectedValue : 1
defaultValue : 0
privateValue : 0
Parent.defaultMethod
Parent.privateMethod
```

  - 코드를 실행해보면 Child.call() → Parent.printParent() 순서로 호출
  - Child는 부모의 public, protected 필드나 메서드만 접근할 수 있음
  - 반면에 Parent.printParent()의 경우 Parent 안에 있는 메서드이기 때문에 Parent 자신의 모든 필드와 메서드에 얼마든지 접근할 수 있음

6. 접근 제어와 메모리 구조
<div align="center">
<img src="https://github.com/user-attachments/assets/8bf75119-9deb-4c9c-8a87-38fba5b10128">
</div>

  - 본인 타입에 없으면 부모 타입에서 기능을 찾는데, 이 때 접근 제어자가 영향을 줌
  - 왜냐하면 객체 내부에서는 자식과 부모가 구분되어 있기 때문임
  - 결국 자식 타입에서 부모 타입의 기능을 호출할 때, 부모 입장에서 보면 외부에서 호출한 것과 같음
