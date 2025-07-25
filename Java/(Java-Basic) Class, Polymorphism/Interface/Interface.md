------
### 인터페이스
------
1. 자바는 순수 추상 클래스를 더 편리하게 사용할 수 있는 인터페이스라는 기능을 제공
2. 순수 추상 클래스
```java
public abstract class AbstractAnimal {
   public abstract void sound();
   public abstract void move();
}
```

3. 인터페이스는 class 가 아니라 interface 키워드를 사용하면 됨
   - 인터페이스
```java
public interface InterfaceAnimal {
   public abstract void sound();
   public abstract void move();
}
```

  - public abstract 키워드 생략 가능
```java
public interface InterfaceAnimal {
     void sound();
     void move();
}
```

4. 순수 추상 클래스는 다음과 같은 특징을 가짐
   - 인스턴스를 생성할 수 없음
   - 상속 시 모든 메서드를 오버라이딩 해야 함
   - 주로 다형성을 위해 사용
   - 인터페이스는 앞서 설명한 순수 추상 클래스와 같지만, 여기에 약간의 편의 기능이 추가
      + 인터페이스의 메서드는 모두 public, abstract
      + 메서드에 public abstract를 생략할 수 있음 (참고로 생략이 권장)
      + 인터페이스는 다중 구현(다중 상속)을 지원
   - 인터페이스와 멤버 변수
```java
public interface InterfaceAnimal {
     public static final double MY_PI = 3.14;
}
```
   - 인터페이스에서 멤버 변수는 public, static, final이 모두 포함되었다고 간주
   - final은 변수의 값을 한번 설정하면 수정할 수 없다는 뜻
   - 자바에서 static final을 사용해 정적이면서 고칠 수 없는 변수를 상수라 하고, 관례상 상수는 대문자에 언더스코어(_)로 구분
   - 해당 키워드는 다음과 같이 생략할 수 있음 (생략이 권장)
```java
public interface InterfaceAnimal {
     double MY_PI = 3.14;
}
```

5. 예제 5
<div align="center">
<img src="https://github.com/user-attachments/assets/57b03cb2-ac5c-4c58-af24-33d4138d6aef">
</div>

  - 클래스 상속 관계는 UML에서 실선을 사용하지만, 인터페이스 구현(상속) 관계는 UML에서 점선을 사용
  - InterfaceAnimal
```java
package poly.ex5;

public interface InterfaceAnimal {
    void sound();
    void move();
}
```
   - 인터페이스는 class 대신에 interface로 선언
   - sound(), move()는 앞에 public abstract가 생략되어 있음
   - 따라서 상속 받는 곳에서 모든 메서드를 오버라이딩 해야함

   - Dog
```java
package poly.ex5;

public class Dog implements InterfaceAnimal {
    @Override
    public void sound() {
        System.out.println("멍멍");
    }

    @Override
    public void move() {
        System.out.println("개 이동");
    }
}
```
   - 인터페이스를 상속 받을 때는 extends 대신에 implements라는 구현이라는 키워드를 사용
   - 인터페이스는 그래서 상속이라 하지 않고 구현이라 부름

   - Cat
```java
package poly.ex5;

public class Cat implements InterfaceAnimal {
    @Override
    public void sound() {
        System.out.println("냐옹");
    }

    @Override
    public void move() {
        System.out.println("고양이 이동");
    }
}
```

   - Cow
```java
package poly.ex5;

public class Cow implements InterfaceAnimal {
    @Override
    public void sound() {
        System.out.println("음매");        
    }

    @Override
    public void move() {
        System.out.println("소 이동");
    }
}
```

   - InterfaceMain
```java
package poly.ex5;

public class InterfaceMain {
    public static void main(String[] args) {
        // 인터페이스 생성 불가
        // InterfaceAnimal interfaceMain1 = new InterfaceAnimal();
        
        Cat cat = new Cat();
        Dog dog = new Dog();
        Cow cow = new Cow();
        
        soundAnimal(cat);
        soundAnimal(dog);
        soundAnimal(cow);
    }
    
    // 동물이 추가 되어도 변하지 않는 코드
    private static void soundAnimal(InterfaceAnimal animal) {
        System.out.println("동물 소리 테스트 시작");
        animal.sound();
        System.out.println("동물 소리 테스트 종료");
    }
}
```
   - 실행 결과
```
동물 소리 테스트 시작
냐옹
동물 소리 테스트 종료

동물 소리 테스트 시작
멍멍
동물 소리 테스트 종료

동물 소리 테스트 시작
음매
동물 소리 테스트 종료
```
<div align="center">
<img src="https://github.com/user-attachments/assets/00785689-e69a-428e-bc26-da30082d7431">
</div>

   - 앞서 설명한 순수 추상 클래스 예제와 거의 유사 (순수 추상 클래스가 인터페이스가 되었을 뿐임)
   - 클래스, 추상 클래스, 인터페이스는 모두 동일
      + 클래스, 추상 클래스, 인터페이스는 프로그램 코드, 메모리 구조상 모두 똑같음
      + 모두 자바에서는 .class 로 다루어지며, 인터페이스를 작성할 때도 .java 에 인터페이스를 정의
      + 인터페이스는 순수 추상 클래스와 비슷하다고 생각하면 됨

6. 상속 VS 구현
   - 부모 클래스의 기능을 자식 클래스가 상속 받을 때, 클래스는 상속 받는다고 표현하지만, 부모 인터페이스의 기능을 자식이 상속 받을 때는 인터페이스를 구현한다고 표현
   - 서로 다르게 표현하는 이유
     + 상속은 이름 그대로 부모의 기능을 물려 받는 것이 목적
     + 하지만 인터페이스는 모든 메서드가 추상 메서드로, 물려받을 수 있는 기능이 없고, 오히려 인터페이스에 정의한 모든 메서드를 자식이 오버라이딩 해서 기능을 구현해야 하므로, 구현한다고 표현
     + 인터페이스는 메서드 이름만 있는 설계도이고, 이 설계도가 실제 어떻게 작동하는지는 하위 클래스에서 모두 구현해야 함
     + 따라서 인터페이스의 경우 상속이 아니라 해당 인터페이스를 구현한다고 표현
     + 상속과 구현은 사람이 표현하는 단어만 다를 뿐이지 자바 입장에서는 똑같으며, 일반 상속 구조와 동일하게 작동

7. 인터페이스를 사용해야 하는 이유
    - 모든 메서드가 추상 메서드인 경우 순수 추상 클래스를 만들어도 되고, 인터페이스를 만들어도 됨
    - 제약 : 인터페이스를 만드는 이유는 인터페이스를 구현하는 곳에서 인터페이스의 메서드를 반드시 구현해라는 규약(제약)을 주는 것
      + USB 인터페이스를 생각 :  USB 인터페이스에 맞추어 키보드, 마우스를 개발하고 연결해야 하며, 그렇지 않으면 작동하지 않음
      + 인터페이스의 규약(제약)은 반드시 구현해야 하는 것
      + 그런데 순수 추상 클래스의 경우 미래에 누군가 그곳에 실행 가능한 메서드를 끼워 넣을 수 있음
      + 이렇게 되면 추가된 기능을 자식 클래스에서 구현하지 않을 수도 있고, 또 더는 순수 추상 클래스가 아니게 됨
      + 인터페이스는 모든 메서드가 추상 메서드이므로, 따라서 이런 문제를 원천 차단할 수 있음

   - 다중 구현 : 자바에서 클래스 상속은 부모를 하나만 지정할 수 있음
      + 반면에 인터페이스는 부모를 여러명 두는 다중 구현(다중 상속)이 가능

8. 참고
   - 자바 8에 등장한 default 메서드를 사용하면 인터페이스도 메서드를 구현할 수 있음
   - 하지만 이것은 예외적으로 아주 특별한 경우에만 사용해야 함
   - 자바 9에서 등장한 인터페이스의 private 메서드도 동일
