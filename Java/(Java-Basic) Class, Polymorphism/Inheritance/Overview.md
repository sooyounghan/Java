-----
### 상속 - 시작
-----
1. 예제 코드 (패키지 위치에 주의)
   - ElectricCar (/extends1/ex1)
```java
package extends1.ex;

public class ElectricCar {
    public void move() {
        System.out.println("차를 이동합니다.");
    }

    public void charge() {
        System.out.println("충전합니다.");
    }
}
```

  - GasCar
```java
package extends1.ex;

public class GasCar {
    public void move() {
        System.out.println("차를 이동합니다.");
    }

    public void fillUp() {
        System.out.println("기름을 주유합니디.");
    }
}
```

  - CarMain
```java
package extends1.ex;

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

<div align="center">
<img src="https://github.com/user-attachments/assets/fd9f761f-5393-499a-a569-feac8f81c3fa">
</div>

   - 전기차(ElectricCar)와 가솔린차(GasCar)를 생성
   - 전기차는 이동(move()), 충전(charge()) 기능이 있고, 가솔린차는 이동(move()), 주유(fillUp()) 기능 존재
   - 전기차와 가솔린차는 자동차(Car)의 좀 더 구체적인 개념
   - 반대로 자동차(Car)는 전기차와 가솔린차를 포함하는 추상적인 개념
   - 둘의 공통 기능으로, 이동(move()) 존재
     + 전기차든 가솔린차든 주유하는 방식이 다른 것이지 이동하는 것은 똑같음
     + 이런 경우 상속 관계를 사용하는 것이 효과적
