-----
### 클래스와 인터페이스 활용
-----
1. 클래스 상속과 인터페이스 구현을 함께 사용하는 예
<div align="center">
<img src="https://github.com/user-attachments/assets/0c02f25b-68cf-4865-aa85-3eb312b6e3d8">
</div>

  - AbstractAnimal은 추상 클래스
    + sound() : 동물의 소리를 내기 위한 sound() 추상 메서드를 제공
    + move() : 동물의 이동을 표현하기 위한 메서드 (이 메서드는 추상 메서드가 아니며, 상속을 목적으로 사용)

  - Fly는 인터페이스
    + 나는 동물은 이 인터페이스를 구현할 수 있음
    + Bird, Chicken 은 날 수 있는 동물이므로, fly() 메서드를 구현해야 함

2. AbstractAnimal (/poly/ex6)
```java
package poly.ex6;

public abstract class AbstractAnimal {
    public abstract void sound();
    public void move() {
        System.out.println("동물이 이동합니다.");
    }
}
```

3. Fly
```java
package poly.ex6;

public interface Fly {
    void fly();
}
```

4. Dog
```java
package poly.ex6;

public class Dog extends AbstractAnimal{
    @Override
    public void sound() {
        System.out.println("멍멍");
    }
}
```
   - Dog는 AbstractAnimal만 상속 받음 

5. Bird
```java
package poly.ex6;

public class Bird extends AbstractAnimal implements Fly {
    @Override
    public void sound() {
        System.out.println("짹짹");
    }

    @Override
    public void fly() {
        System.out.println("새 날기");
    }
}
```
   - Bird는 AbstractAnimal 클래스를 상속하고 Fly 인터페이스를 구현

6. 하나의 클래스 여러 인터페이스 예시
```java
public class Bird extends AbstractAnimal implements Fly, Swim {
```
  - 💡 extends를 통한 상속은 하나만 할 수 있고 implements를 통한 인터페이스는 다중 구현 할 수 있기 때문에 둘이 함께 나온 경우 extends가 먼저 나와야 함

7. Chicken
```java
package poly.ex6;

public class Chicken extends AbstractAnimal implements Fly {
    @Override
    public void sound() {
        System.out.println("꼬끼오");
    }

    @Override
    public void fly() {
        System.out.println("닭 날기");
    }
}
```

8. SoundFlyMain
```java
package poly.ex6;

public class SoundFlyMain {
    public static void main(String[] args) {
        Dog dog = new Dog();
        Bird bird = new Bird();
        Chicken chicken = new Chicken();
        
        soundAnimal(dog);
        soundAnimal(bird);
        soundAnimal(chicken);
        
        flyAnimal(bird);
        flyAnimal(chicken);
    }
    
    // AbstractAnimal 사용 가능
    private static void soundAnimal(AbstractAnimal animal) {
        System.out.println("동물 소리 테스트 시작");
        animal.sound();
        System.out.println("동물 소리 테스트 종료");
    }
    
    // Fly 인터페이스가 있으면 사용 가능
    private static void flyAnimal(Fly fly) {
        System.out.println("날기 테스트 시작");
        fly.fly();
        System.out.println("날기 테스트 종료");
    }
}
```
  - 실행 결과
```
동물 소리 테스트 시작
멍멍
동물 소리 테스트 종료

동물 소리 테스트 시작
짹짹
동물 소리 테스트 종료

동물 소리 테스트 시작
꼬끼오
동물 소리 테스트 종료

날기 테스트 시작
새 날기
날기 테스트 종료

날기 테스트 시작
닭 날기
날기 테스트 종료
```

<div align="center">
<img src="https://github.com/user-attachments/assets/cf29ca31-4a87-45a0-9244-22fa1812754a">
</div>

   - soundAnimal(AbstractAnimal animal) : AbstractAnimal를 상속한 Dog, Bird, Chicken을 전달해서 실행할 수 있음  
   - 실행 과정
      + soundAnimal(bird)를 호출한다고 가정
      + 메서드 안에서 animal.sound()를 호출하면 참조 대상인 x001 Bird 인스턴스를 찾음
      + 호출한 animal 변수는 AbstractAnimal 타입
      + 따라서 AbstractAnimal.sound()를 찾으며, 해당 메서드는 Bird.sound()에 오버라이딩 되어 있음
      + Bird.sound()가 호출

<div align="center">
<img src="https://github.com/user-attachments/assets/8ec75472-ef15-4762-a566-266fa6da1e1b">
</div>

   - flyAnimal(Fly fly) : Fly 인터페이스를 구현한 Bird, Chicken을 전달해서 실행할 수 있음
   - 실행 과정
      + fly(bird)를 호출한다고 가정
      + 메서드 안에서 fly.fly() 를 호출하면 참조 대상인 x001 Bird 인스턴스를 찾음
      + 호출한 fly 변수는 Fly 타입
      + 따라서 Fly.fly()를 찾으며, 해당 메서드는 Bird.fly()에 오버라이딩 되어 있음
      + Bird.fly()가 호출
