-----
### 추상 클래스 1
-----
1. 추상 클래스
   - 동물(Animal)과 같이 부모 클래스는 제공하지만, 실제 생성되면 안되는 클래스를 추상 클래스
   - 추상 클래스는 이름 그대로 추상적인 개념을 제공하는 클래스
   - 따라서 실체인 인스턴스가 존재하지 않음
   - 대신에 상속을 목적으로 사용되고, 부모 클래스 역할을 담당
```java
abstract class AbstractAnimal {...}
```

2. 추상 클래스는 클래스를 선언할 때 앞에 추상이라는 의미의 abstract 키워드를 붙여주면 됨
   - 추상 클래스는 기존 클래스와 완전히 같음
   - 다만 new AbstractAnimal()와 같이 직접 인스턴스를 생성하지 못하는 제약이 추가된 것

3. 추상 메서드
   - 부모 클래스를 상속 받는 자식 클래스가 반드시 오버라이딩 해야 하는 메서드를 부모 클래스에 정의할 수 있음
   - 이것을 추상 메서드라 함
   - 추상 메서드는 이름 그대로 추상적인 개념을 제공하는 메서드
   - 따라서 실체가 존재하지 않고, 메서드 바디가 없음
```java
public abstract void sound();
```

4. 추상 메서드는 선언할 때 메서드 앞에 추상이라는 의미의 abstract 키워드를 붙여주면 됨
   - 추상 메서드가 하나라도 있는 클래스는 추상 클래스로 선언해야 함
   - 그렇지 않으면 컴파일 오류가 발생
     + 추상 메서드는 메서드 바디가 없음
     + 따라서 작동하지 않는 메서드를 가진 불완전한 클래스로 볼 수 있음
     + 따라서 직접 생성하지 못하도록 추상 클래스로 선언해야 함
   - 추상 메서드는 상속 받는 자식 클래스가 반드시 오버라이딩 해서 사용해야 함
     + 그렇지 않으면 컴파일 오류가 발생
     + 추상 메서드는 자식 클래스가 반드시 오버라이딩 해야 하기 때문에 메서드 바디 부분이 없음
     + 바디 부분을 만들면 컴파일 오류가 발생

   - 오버라이딩 하지 않으면 자식도 추상 클래스가 되어야 함
     + 추상 메서드는 기존 메서드와 완전히 같음
     + 다만 메서드 바디가 없고, 자식 클래스가 해당 메서드를 반드시 오버라이딩 해야 한다는 제약이 추가된 것

4. 예제
<div align="center">
<img src="https://github.com/user-attachments/assets/6f021c68-a49a-4b8a-b041-efc6394baf67">
</div>

   - AbstractAnimal (/poly/ex3)
```java
package poly.ex3;

public abstract class AbstractAnimal {
    public abstract void sound();
    
    public void move() {
        System.out.println("동물이 움직입니다.");
    }
}
```
   - AbstractAnimal은 abstract가 붙은 추상 클래스 : 이 클래스는 직접 인스턴스를 생성할 수 없음
   - sound()는 abstract가 붙은 추상 메서드 : 이 메서드는 자식이 반드시 오버라이딩 해야 함
   - 이 클래스는 move() 라는 메서드를 가지고 있는데, 이 메서드는 추상 메서드가 아니므로, 따라서 자식 클래스가 오버라이딩 하지 않아도 됨
   - 참고로 추상 클래스라고 AbstractAnimal 처럼 클래스 이름 앞에 꼭 Abstract를 써야하는 것은 아님
   - Animal 이라는 클래스 이름으로도 충분

   - Dog
```java
package poly.ex3;

public class Dog extends AbstractAnimal{
    @Override
    public void sound() {
        System.out.println("멍멍");
    }
}
```

   - Cat
```java
package poly.ex3;

public class Cat extends AbstractAnimal {
    @Override
    public void sound() {
        System.out.println("냐옹");
    }
}
```

   - Cow
```java
package poly.ex3;

public class Cow extends AbstractAnimal {
    @Override
    public void sound() {
        System.out.println("음메");
    }
}
```

  - AbstractMain
```java
package poly.ex3;

public class AbstractMain {
    public static void main(String[] args) {
        // 추상 클래스 생성 불가
        // AbstractAnimal animal = new AbstractAnimal();
        
        Dog dog = new Dog();
        Cat cat = new Cat();
        Cow cow = new Cow();
        
        cat.sound();   
        cat.move();
        
        soundAnimal(dog);
        soundAnimal(cat);
        soundAnimal(cow);
    }
    
    // 동물이 추가 되어도 변하지 않는 코드
    private static void soundAnimal(AbstractAnimal animal) {
        System.out.println("동물 소리 테스트 시작");
        animal.sound();
        System.out.println("동물 소리 테스트 종료");
    }
}
```
  - 실행 결과
```
냐옹
동물이 움직입니다.

동물 소리 테스트 시작
멍멍
동물 소리 테스트 종료

동물 소리 테스트 시작
냐옹
동물 소리 테스트 종료

동물 소리 테스트 시작
음메
동물 소리 테스트 종료
```

   - 추상 클래스는 생성이 불가능
   - 다음 코드의 주석을 풀고 실행하면 컴파일 오류가 발생
```java
// 추상클래스 생성 불가
AbstractAnimal animal = new AbstractAnimal();
```

   - 컴파일 오류 - 인스턴스 생성
```
java: poly.ex3.AbstractAnimal is abstract; cannot be instantiated
```

   - AbstractAnimal 가 추상이어서 인스턴스 생성이 불가능하다는 뜻
   - 추상 메서드는 반드시 오버라이딩 해야 함
     + 만약 자식에서 오버라이딩 메서드를 만들지 않으면 다음과 같이 컴파일 오류가 발생
       
   - Dog 의 sound() 메서드를 주식처리
```java
package poly.ex3;
public class Dog extends AbstractAnimal {
    /*
     @Override
     public void sound() {
         System.out.println("멍멍");
     }
    */
}
```

   - 컴파일 오류 - 오버라이딩 X
```
java: poly.ex3.Dog is not abstract and does not override abstract method
sound() in poly.ex3.AbstractAnimal
```

   - Dog 는 추상클래스가 아닌데 sound()가 오버라이딩 되지 않았다는 뜻
   - 지금까지 설명한 제약을 제외하고 나머지는 모두 일반적인 클래스와 동일
   - 추상 클래스는 제약이 추가된 클래스일 뿐이며, 메모리 구조, 실행 결과 모두 동일

<div align="center">
<img src="https://github.com/user-attachments/assets/c1860d87-9afb-404f-ac3d-a54c15851c7f">
</div>

5. 정리
    - 추상 클래스 덕분에 실수로 Animal 인스턴스를 생성할 문제를 근본적으로 방지
    - 추상 메서드 덕분에 새로운 동물의 자식 클래스를 만들때 실수로 sound()를 오버라이딩 하지 않을 문제를 근본적으로 방지
