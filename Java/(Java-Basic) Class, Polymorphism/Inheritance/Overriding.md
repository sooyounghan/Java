-----
### 상속과 메서드 오버라이딩
-----
1. 부모 타입의 기능을 자식에서는 다르게 재정의 하고 싶을 수 있음
   - 예를 들어서 자동차의 경우 Car.move()라는 기능이 있음
   - 이 기능을 사용하면 단순히 "차를 이동합니다."라고 출력
   - 전기차의 경우 보통 더 빠르기 때문에 전치가가 move()를 호출한 경우에는 "전기차를 빠르게 이동합니다."라고 출력을 변경하고 싶음
   - 이렇게 부모에게서 상속 받은 기능을 자식이 재정의 하는 것을 메서드 오버라이딩(Overriding)이라 함

2. Car (/extends1/overriding)
```java
package extends1.overriding;

public class Car {
    public void move() {
        System.out.println("차를 이동합니다.");
    }
    
    public void openDoor() {
        System.out.println("문을 엽니다.");
    }
}
```

3. GasCar
```java
package extends1.overriding;

public class GasCar extends Car {
    public void fillUp() {
        System.out.println("기름을 주유합니다.");
    }
}
```

4. ElectricCar
```java
package extends1.overriding;

public class ElectricCar extends Car {
    @Override
    public void move() {
        System.out.println("전기차를 빠르게 이동합니다.");
    }

    public void charge() {
        System.out.println("충전합니다.");
    }
}
```

  - ElectricCar는 부모인 Car의 move() 기능을 그대로 사용하고 싶지 않음
  - 메서드 이름은 같지만 새로운 기능을 사용하고 싶으므로, ElectricCar의 move() 메서드를 새로 생성
  - 💡 이렇게 부모의 기능을 자식이 새로 재정의하는 것을 메서드 오버라이딩
     + 이제 ElectricCar의 move()를 호출하면 Car의 move()가 아니라 ElectricCar의 move()가 호출

  - @Override
    + @이 붙은 부분을 애노테이션
    + 애노테이션은 주석과 비슷한데, 프로그램이 읽을 수 있는 특별한 주석이라 생각
    + 이 애노테이션은 상위 클래스의 메서드를 오버라이드하는 것임을 나타냄
    + 이름 그대로 오버라이딩한 메서드 위에 이 애노테이션을 붙여야 함
    + 컴파일러는 이 애노테이션을 보고 메서드가 정확히 오버라이드 되었는지 확인
    + 오버라이딩 조건을 만족시키지 않으면 컴파일 에러를 발생시킴
    + 따라서 실수로 오버라이딩을 못하는 경우를 방지해줌
    + 예를 들어서 이 경우에 만약 부모에 move() 메서드가 없다면 컴파일 오류가 발생
    + 참고로 이 기능은 필수는 아니지만 코드의 명확성을 위해 붙여주는 것이 좋음
   
  - CarMain
```java
package extends1.overriding;

public class CarMain {
    public static void main(String[] args) {
        ElectricCar electricCar = new ElectricCar();
        electricCar.move();

        GasCar gasCar = new GasCar();
        gasCar.move();
    }
}
```
  - 실행 결과
```
전기차를 빠르게 이동합니다.
차를 이동합니다.
```
  - 실행 결과를 보면 electricCar.move() 를 호출했을 때 오버라이딩한 ElectricCar.move() 메서드가 실행된
것을 확인할 수 있음

5. 오버라이딩과 클래스
<div align="center">
<img src="https://github.com/user-attachments/assets/cd4f228d-dc32-42d3-bf0a-c3b58119037b">
</div>

  - Car의 move() 메서드를 ElectricCar에서 오버라이딩

6. 오버라이딩과 메모리 구조
<div align="center">
<img src="https://github.com/user-attachments/assets/9ed3e397-9998-4a39-b65a-e696d853ef9e">
</div>

  - electricCar.move()를 호출
  - 호출한 electricCar의 타입은 ElectricCar : 따라서 인스턴스 내부의 ElectricCar 타입에서 시작
  - ElectricCar 타입에 move() 메서드가 존재 : 해당 메서드를 실행하며, 이 때 실행할 메서드를 이미 찾았으므로 부모 타입을 찾지 않음

7. 💡 오버로딩(Overloading)과 오버라이딩(Overriding)
   - 메서드 오버로딩
     + 메서드 이름이 같고 매개변수(파라미터)가 다른 메서드를 여러개 정의하는 것을 메서드 오버로딩(Overloading)
     + 오버로딩은 번역하면 과적인데, 과하게 물건을 담았다는 뜻
     + 따라서 같은 이름의 메서드를 여러개 정의했다고 이해하면 됨

   - 메서드 오버라이딩
     + 메서드 오버라이딩은 하위 클래스에서 상위 클래스의 메서드를 재정의하는 과정을 의미
     + 따라서 상속 관계에서 사용
     + 부모의 기능을 자식이 다시 정의하는 것
     + 오버라이딩을 단순히 해석하면 무언가를 넘어서 타는 것을 말함
     + 자식의 새로운 기능이 부모의 기존 기능을 넘어 타서 기존 기능을 새로운 기능으로 덮어버린다고 이해하면 됨
     + 오버라이딩을 우리말로 번역하면 무언가를 다시 정의한다고 해서 재정의라 함
     + 상속 관계에서는 기존 기능을 다시 정의한다고 이해하면 됨
     + 실무에서는 메서드 오버라이딩, 메서드 재정의 둘 다 사용

8. 메서드 오버라이딩 조건
   - 메서드 오버라이딩은 다음과 같은 까다로운 조건을 가지고 있음
   - 메서드 이름 : 메서드 이름이 같아야 함
   - 메서드 매개변수(파라미터) : 매개변수(파라미터) 타입, 순서, 개수가 같아야 함
   - 반환 타입 : 반환 타입이 같아야 함 (단, 반환 타입이 하위 클래스 타입일 수 있음)
   - 접근 제어자 : 오버라이딩 메서드의 접근 제어자는 상위 클래스의 메서드보다 더 제한적이어서는 안 됨
     + 예를 들어, 상위 클래스의 메서드가 protected로 선언되어 있으면 하위 클래스에서 이를 public 또는 protected로 오버라이드할 수 있지만, private 또는 default로 오버라이드 할 수 없음
     + 예외 : 오버라이딩 메서드는 상위 클래스의 메서드보다 더 많은 체크 예외를 throws로 선언할 수 없음
     + 하지만 더 적거나 같은 수의 예외, 또는 하위 타입의 예외는 선언할 수 있음

   - static, final, private : 키워드가 붙은 메서드는 오버라이딩 될 수 없음
      + static은 클래스 레벨에서 작동하므로 인스턴스 레벨에서 사용하는 오버라이딩이 의미가 없음 (쉽게 이야기해서 그냥 클래스 이름을 통해 필요한 곳에 직접 접근하면 됨)
      + final 메서드는 재정의를 금지
      + private 메서드는 해당 클래스에서만 접근 가능하기 때문에 하위 클래스에서 보이지 않음 (따라서 오버라이딩 할 수 없음)
 
   - 생성자 오버라이딩 : 생성자는 오버라이딩 할 수 없음
