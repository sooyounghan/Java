-----
### 추상 클래스 2
-----
1. 순수 추상 클래스 : 모든 메서드가 추상 메서드인 추상 클래스
   - 앞서 만든 예제에서 move()도 추상 메서드로 만들어야 한다고 가정
   - 이 경우 AbstractAnimal 클래스의 모든 메서드가 추상 메서드가 됨
   - 이런 클래스를 순수 추상 클래스라 함

2. move()가 추상 메서드가 되었으니 자식들은 AbstractAnimal의 모든 기능을 오버라이딩 해야 함
<div align="center">
<img src="https://github.com/user-attachments/assets/fe371145-2ec8-43bb-99a3-a38215e02d27">
</div>

3. AbstractAnimal (/poly/ex4)
```java
package poly.ex4;

public abstract class AbstractAnimal {
    public abstract void sound();

    public abstract void move();
}
```

4. Dog
```java
package poly.ex4;

public class Dog extends AbstractAnimal {
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

5. Cat
```java
package poly.ex4;

public class Cat extends AbstractAnimal {
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

6. Cow
```java
package poly.ex4;

public class Cow extends AbstractAnimal {
    @Override
    public void sound() {
        System.out.println("음메");
    }

    @Override
    public void move() {
        System.out.println("소 이동");
    }
}
```

7. AbstractMain
```java
package poly.ex4;

public class AbstractMain {
    public static void main(String[] args) {
        Dog dog = new Dog();
        Cat cat = new Cat();
        Cow cow = new Cow();
        
        soundAnimal(dog);
        soundAnimal(cat);
        soundAnimal(cow);
        
        moveAnimal(cat);
        moveAnimal(dog);
        moveAnimal(cow);
    }

    // 동물이 추가 되어도 변하지 않는 코드
    private static void soundAnimal(AbstractAnimal animal) {
        System.out.println("동물 소리 테스트 시작");
        animal.sound();
        System.out.println("동물 소리 테스트 종료");
    }
    
    // 동물이 추가 되어도 변하지 않는 코드
    private static void moveAnimal(AbstractAnimal animal) {
        System.out.println("동물 이동 테스트 시작");
        animal.move();
        System.out.println("동물 이동 테스트 종료");
    }
}
```

  - 실행 결과
```
동물 소리 테스트 시작
멍멍
동물 소리 테스트 종료

동물 소리 테스트 시작
냐옹
동물 소리 테스트 종료

동물 소리 테스트 시작
음메
동물 소리 테스트 종료

동물 이동 테스트 시작
고양이 이동
동물 이동 테스트 종료

동물 이동 테스트 시작
개 이동
동물 이동 테스트 종료

동물 이동 테스트 시작
소 이동
동물 이동 테스트 종료
```
<div align="center">
<img src="https://github.com/user-attachments/assets/a9841153-0062-4bfa-af38-6381d96e68ee">
</div>

8. 순수 추상 클래스
  - 모든 메서드가 추상 메서드인 순수 추상 클래스는 코드를 실행할 바디 부분이 전혀 없음
```java
public abstract class AbstractAnimal {
   public abstract void sound();
   public abstract void move();
}
```
   - 이러한 순수 추상 클래스는 실행 로직을 전혀 가지고 있지 않음
   - 단지 다형성을 위한 부모 타입으로써 껍데기 역할만 제공할 뿐임
   - 순수 추상 클래스는 다음과 같은 특징을 가짐
      + 인스턴스를 생성할 수 없음
      + 상속 시 자식은 모든 메서드를 오버라이딩 해야 함
      + 주로 다형성을 위해 사용
      + 상속하는 클래스는 모든 메서드를 구현해야 함

   - "상속시 자식은 모든 메서드를 오버라이딩 해야 한다."라는 특징은 상속 받는 클래스 입장에서 보면 부모의 모든 메서드를 구현해야 하는 것
   - 이런 특징을 잘 생각해보면 순수 추상 클래스는 마치 어떤 규격을 지켜서 구현해야 하는 것 처럼 느껴짐
      + AbstractAnimal의 경우 sound(), move() 라는 규격에 맞추어 구현을 해야 함
      + 이것은 일반적으로 이야기하는 인터페이스와 같이 느껴짐
      + 예를 들어서 USB 인터페이스를 생각
         * USB 인터페이스는 분명한 규격이 존재
         * 이 규격에 맞추어 제품을 개발해야 연결이 됨
         * 순수 추상 클래스가 USB 인터페이스 규격이라고 한다면 USB 인터페이스에 맞추어 마우스, 키보드 같은 연결 장치들을 구현할 수 있음
         * 이런 순수 추상 클래스의 개념은 프로그래밍에서 매우 자주 사용
         * 자바는 순수 추상 클래스를 더 편리하게 사용할 수 있도록 인터페이스라는 개념을 제공
