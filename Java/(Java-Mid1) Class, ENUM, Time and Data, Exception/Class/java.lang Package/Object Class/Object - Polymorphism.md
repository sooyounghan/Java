-----
### Object 다형성
-----
1. Object는 모든 클래스의 부모 클래스
2. 따라서 Object 는 모든 객체를 참조할 수 있음
<div align="center">
<img src="https://github.com/user-attachments/assets/18e11849-b634-4613-b725-cb98c3702893">
</div>

3. Dog와 Car은 서로 아무런 관련이 없는 클래스 : 둘 다 부모가 없으므로 Object를 자동으로 상속 받음
4. Car (/lang/object/poly)
```java
package lang.object.poly;

public class Car {
    public void move() {
        System.out.println("자동차 이동");
    }
}
```

5. Dog
```java
package lang.object.poly;

public class Dog {
    public void sound() {
        System.out.println("멍멍");
    }
}
```

6. ObjectPolyExample1
```java
package lang.object.poly;

public class ObjectPolyExample1 {
    public static void main(String[] args) {
        Dog dog = new Dog();
        Car car = new Car();

        action(dog);
        action(car);
    }

    private static void action(Object obj) {
        // obj.sound(); // 컴파일 오류, Object는 sound() 없음
        // obj.move(); // 컴파일 오류, Object는 move()가 없음
        
        // 객체에 맞는 다운캐스팅 필요
        if(obj instanceof Dog dog) {
            dog.sound();
        } else if (obj instanceof Car car) {
            car.move();
        }
    }
}
```
  - 실행 결과
```
멍멍
자동차 이동
```

   - Object는 모든 타입의 부모
   - 부모는 자식을 담을 수 있으므로 앞의 코드를 다음과 같이 변경해도 됨
```java
Object dog = new Dog(); // Dog -> Object
Object car = new Car(); // Car -> Object
```

7. Object 다형성의 장점
   - action(Object obj) 메서드를 분석
   - 이 메서드는 Object 타입의 매개변수를 사용
   - 그런데 Object 는 모든 객체의 부모로, 따라서 어떤 객체든지 인자로 전달할 수 있음
```java
action(dog) // main에서 dog 전달

void action(Object obj = dog(Dog)) //Object는 자식인 Dog 타입을 참조할 수 있음
```
```java
action(car) //main에서 car 전달

void action(Object obj = car(Car)) // Object는 자식인 Car 타입을 참조할 수 있음
```

8. Object 다형성의 한계
```java
action(dog) // main에서 dog 전달

private static void action(Object obj) {
     obj.sound(); // 컴파일 오류, Object는 sound()가 없음
}
```
   - action() 메서드안에서 obj.sound()를 호출하면 오류가 발생
   - 왜냐하면 매개변수인 obj는 Object 타입이기 때문임 (Object에는 sound() 메서드가 없음)

<div align="center">
<img src="https://github.com/user-attachments/assets/99ab26ab-497f-4e5c-9b49-b07d9f4d3ef6">
</div>

   - obj.sound() 호출
      + obj.sound()를 호출
      + obj는 Object 타입이므로 Object 타입에서 sound()를 찾음
      + Object에서 sound()를 찾을 수 없음
      + Object 는 최종 부모이므로 더는 올라가서 찾을 수 없으므로, 따라서 오류가 발생
  
   - Dog 인스턴스의 sound()를 호출하려면 다음과 같이 다운캐스팅을 해야함
```java
if (obj instanceof Dog dog) {
     dog.sound();
}
```

<div align="center">
<img src="https://github.com/user-attachments/assets/85aa0596-01f6-49d8-8f63-3df75f35f010">
</div>

   - Object obj의 참조값을 Dog dog로 다운캐스팅 하면서 전달
   - dog.sound()를 호출하면 Dog 타입에서 sound()를 찾아서 호출

9. Object를 활용한 다형성의 한계
   - Object는 모든 객체를 대상으로 다형적 참조를 할 수 있음
      + 쉽게 이야기해서 Object는 모든 객체의 부모이므로 모든 객체를 담을 수 있음
   - Object를 통해 전달 받은 객체를 호출하려면 각 객체에 맞는 다운캐스팅 과정이 필요
      + Object가 세상의 모든 메서드를 알고 있는 것이 아님

10. Object 는 모든 객체의 부모이므로 모든 객체를 대상으로 다형적 참조를 할 수 있음
    - 하지만 Object에는 Dog.sound(), Car.move()와 같은 다른 객체의 메서드가 정의되어 있지 않음
    - 따라서 메서드 오버라이딩을 활용할 수 없음
    - 결국 각 객체의 기능을 호출하려면 다운캐스팅을 해야 함
    - 참고로 Object 본인이 보유한 toString() 같은 메서드는 당연히 자식 클래스에서 오버라이딩 할 수 있음
    - 여기서 이야기하는 것은 앞서 설명한 Object에 속하지 않은 메서드를 의미
    - 결과적으로 다형적 참조는 가능하지만, 메서드 오버라이딩이 안되기 때문에 다형성을 활용하기에는 한계가 있음
