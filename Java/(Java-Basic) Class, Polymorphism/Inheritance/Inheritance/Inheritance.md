-----
### 상속 관계
-----
1. 상속은 객체 지향 프로그래밍의 핵심 요소 중 하나로, 기존 클래스의 필드와 메서드를 새로운 클래스에서 재사용하게 해줌
2. 이름 그대로 기존 클래스의 속성과 기능을 그대로 물려받는 것
   - 상속을 사용하려면 extends 키워드를 사용
   - extends 대상은 하나만 선택할 수 있음

3. 용어 정리
   - 부모 클래스 (슈퍼 클래스) : 상속을 통해 자신의 필드와 메서드를 다른 클래스에 제공하는 클래스
   - 자식 클래스 (서브 클래스) : 부모 클래스로부터 필드와 메서드를 상속받는 클래스

4. Car (/extends1/ex2)
```java
package extends1.ex2;

public class Car {
    public void move() {
        System.out.println("차를 이동합니다.");
    }
}
```
  - Car는 부모 클래스가 되며, 자동차의 공통 기능인 move() 포함

  - GasCar
```java
package extends1.ex2;

public class GasCar extends Car {
    public void fillUp() {
        System.out.println("기름을 주유합니다.");
    }
}
```
   - 가솔린차도 전기차와 마찬가지로 extends Car를 사용해서 부모 클래스인 Car를 상속 받음
   - 상속 덕분에 여기서도 move() 를 사용할 수 있음
   - CarMain
```java
package extends1.ex2;

import extends1.ex.ElectricCar;
import extends1.ex.GasCar;

public class CarMain {
    public static void main(String[] args) {
        ElectricCar electricCar = new ElectricCar();
        electricCar.move();
        electricCar.charge();

        GasCar gasCar = new GasCar();
        gasCar.move();
        gasCar.fillUp();
    }
}
```
  - 실행 결과
```
차를 이동합니다.
충전합니다.
차를 이동합니다.
기름을 주유합니디.
```

  - 상속 구조도
<div align="center">
<img src="https://github.com/user-attachments/assets/c2e9b876-df7a-4535-8cce-35b2db480790">
</div>

   - 전기차와 가솔린차가 Car를 상속 받은 덕분에 electricCar.move() , gasCar.move()를 사용할 수 있음
   - 참고로 당연한 이야기지만 상속은 부모의 기능을 자식이 물려 받는 것
     + 따라서 자식이 부모의 기능을 물려 받아서 사용할 수 있음
     + 반대로 부모 클래스는 자식 클래스에 접근할 수 없음
     + 자식 클래스는 부모 클래스의 기능을 물려 받기때문에 접근할 수 있지만, 그 반대는 아님
     + 부모 코드를 보면, 자식에 대한 정보가 하나도 없음
     + 반면에 자식 코드는 extends Car 를 통해서 부모를 알고 있음

5. 단일 상속
    - 참고로 자바는 다중 상속을 지원하지 않으므로, 그래서 extend 대상은 하나만 선택할 수 있음
    - 부모를 하나만 선택할 수 있다는 뜻
    - 물론 부모가 또 다른 부모를 하나 가지는 것은 괜찮음
    - 다중 상속 그림
<div align="center">
<img src="https://github.com/user-attachments/assets/db37c6fd-8208-4c46-99d3-2abb9b49b35a">
</div>

   - 만약 비행기와 자동차를 상속 받아서 하늘을 나는 자동차를 만든다고 가정
   - 만약 그림과 같이 다중 상속을 사용하게 되면 AirplaneCar 입장에서 move()를 호출할 때 어떤 부모의 move()를 사용해야 할지 애매한 문제가 발생
   - 이것을 다이아몬드 문제라 함
   - 그리고 다중 상속을 사용하면 클래스 계층 구조가 매우 복잡해질 수 있으므로, 이런 문제점 때문에 자바는 클래스의 다중 상속을 허용하지 않음
   - 대신에 이후에 설명한 인터페이스의 다중 구현을 허용해서 이러한 문제를 피함
