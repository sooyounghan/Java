-----
### 상속과 기능 추가
-----
1. 이번에는 상속 관계의 장점을 알아보기 위해, 상속 관계에 다음 기능을 추가
   - 모든 차량에 문열기(openDoor()) 기능을 추가
   - 새로운 수소차(HydrogenCar)를 추가
   - 수소차는 fillHydrogen() 기능을 통해 수소를 충전할 수 있음

2. Car (/extends1/ex3)
```java
package extends1.ex3;

public class Car {
    public void move() {
        System.out.println("차를 이동합니다.");
    }
    
    // 추가
    public void openDoor() {
        System.out.println("문을 엽니다.");
    }
}
```
  - 모든 차량에 문열기 기능을 추가할 때는 상위 부모인 Car에 openDoor() 기능을 추가하면 됨
  - 이렇게 하면 Car의 자식들은 해당 기능을 모두 물려받게 됨
  - 만약 상속 관계가 아니었다면 각각의 차량에 해당 기능을 모두 추가해야 함

3. ElectricCar
```java
package extends1.ex3;

public class ElectricCar extends Car {
    public void charge() {
        System.out.println("충전합니다.");
    }
}
```

4. GasCar
```java
package extends1.ex3;

public class GasCar extends Car {
    public void fillUp() {
        System.out.println("기름을 주유합니다.");
    }
}
```

5. HydrogenCar
```java
package extends1.ex3;

// 추가
public class HydrogenCar extends Car {
    public void fillHydrogen() {
        System.out.println("수소를 충전합니다.");
    }
}
```
   - 수소차를 추가
   - Car를 상속받은 덕분에 move(), openDoor()와 같은 기능을 바로 사용할 수 있음
   - 수소차는 전용 기능인 수소 충전(fillHydrogen()) 기능을 제공

6. CarMain
```java
package extends1.ex3;

public class CarMain {
    public static void main(String[] args) {
        ElectricCar electricCar = new ElectricCar();
        electricCar.move();
        electricCar.charge();
        electricCar.openDoor();

        GasCar gasCar = new GasCar();
        gasCar.move();
        gasCar.fillUp();
        gasCar.openDoor();

        HydrogenCar hydrogenCar = new HydrogenCar();
        hydrogenCar.move();
        hydrogenCar.fillHydrogen();
        hydrogenCar.openDoor();
    }
}
```
  - 실행 결과
```
차를 이동합니다.
충전합니다.
문을 엽니다.
차를 이동합니다.
기름을 주유합니다.
문을 엽니다.
차를 이동합니다.
수소를 충전합니다.
문을 엽니다.
```

7. 기능 추가와 클래스 확장
<div align="center">
<img src="https://github.com/user-attachments/assets/9c22c1a6-6849-435d-a98d-c60c5f7efa41">
</div>

  - 상속 관계 덕분에 중복은 줄어들고, 새로운 수소차를 편리하게 확장(extend)한 것을 알 수 있음
