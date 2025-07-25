-----
### 내부 클래스 활용
-----
1. 내부 클래스 리팩토링 전 - Engine (/nested/inner/ex1)
```java
package nested.inner.ex1;

// Car에서만 사용
public class Engine {
    private Car car;

    public Engine(Car car) {
        this.car = car;
    }
    
    public void start() {
        System.out.println("충전 레벨 확인 : " + car.getChargeLevel());
        System.out.println(car.getModel() + "의 엔진을 구동합니다.");
    }
}
```
   - 엔진은 Car 클래스에서만 사용
   - 엔진을 시작하기 위해서는 차의 충전 레벨과 차량의 이름이 필요
      + Car 인스턴스의 참조를 생성자에서 보관
      + 엔진은 충전 레벨을 확인하기 위해 Car.getChargeLevel() 필요
      + 엔진은 차량의 이름을 확인하기 위해 Car.getModel() 필요

   - Car
```java
package nested.inner.ex1;

public class Car {
    private String model;
    private int chargeLevel;
    private Engine engine;

    public Car(String model, int chargeLevel) {
        this.model = model;
        this.chargeLevel = chargeLevel;
        this.engine = new Engine(this);
    }

    // Engine에서만 사용하는 메서드
    public String getModel() {
        return model;
    }

    // Engine에서만 사용하는 메서드
    public int getChargeLevel() {
        return chargeLevel;
    }

    public void start() {
        engine.start();
        System.out.println(model + " 시작 완료");
    }
}
```
   - Car 클래스는 엔진에 필요한 메서드들을 제공해야 함 : 다음 메서드는 엔진에서만 사용하고, 다른 곳에서는 사용하지 않음
      + getModel()
      + getChargeLevel()
   - 결과적으로 Car 클래스는 엔진에서만 사용하는 기능을 위해 메서드를 추가해서, 모델 이름과 충전 레벨을 외부에 노출해야 함
   - CarMain
```java
package nested.inner.ex1;

public class CarMain {
    public static void main(String[] args) {
        Car myCar = new Car("Model Y", 100);
        myCar.start();
    }
}
```
   - 실행 결과
```
충전 레벨 확인 : 100
Model Y의 엔진을 구동합니다.
Model Y 시작 완료
```

2. 내부 클래스 리팩토링 후
   - 엔진은 차의 내부에서만 사용됨
   - 엔진을 차의 내부 클래스로 생성 : 또한 엔진은 차의 충전 레벨과 모델명에 접근해야 함
   - Car (/nested/inner/ex2)
```java
package nested.inner.ex2;

public class Car {
    private String model;
    private int chargeLevel;
    private Engine engine;

    public Car(String model, int chargeLevel) {
        this.model = model;
        this.chargeLevel = chargeLevel;
        this.engine = new Engine();
    }

    public void start() {
        engine.start();
        System.out.println(model + " 시작 완료");
    }

    private class Engine {
        public void start() {
            System.out.println("충전 레벨 확인 : " + chargeLevel);
            System.out.println(model + " 의 엔진을 구동합니다");
        }
    }
}
```
   - 엔진을 내부 클래스로 생성
   - Engine.start()를 기존과 비교
      + Car의 인스턴스 변수인 chargeLevel에 직접 접근할 수 있음
      + Car의 인스턴스 변수인 model에 직접 접근할 수 있음

3. 내부 클래스의 생성
    - 바깥 클래스에서 내부 클래스의 인스턴스를 생성할 때는 바깥 클래스 이름을 생략할 수 있음 (예) new Engine())
    - 바깥 클래스에서 내부 클래스의 인스턴스를 생성할 때 내부 클래스의 인스턴스는 자신을 생성한 바깥 클래스의 인스턴스를 자동으로 참조 : 여기서 new Engine()로 생성된 Engine 인스턴스는 자신을 생성한 바깥의 Car 인스턴스를 자동으로 참조
    - CarMain
```java
package nested.inner.ex2;

public class CarMain {
    public static void main(String[] args) {
        Car myCar = new Car("Model Y", 100);
        myCar.start();
    }
}
```
   - 실행 결과
```
충전 레벨 확인 : 100
Model Y 의 엔진을 구동합니다
Model Y 시작 완료
```

4. 리팩토링 전의 문제
   - Car 클래스는 엔진에 필요한 메서드들을 제공해야 함 : 다음 메서드는 엔진에서만 사용하고, 다른 곳에서는 사용하지 않음
      + getModel()
      + getChargeLevel()

   - 결과적으로 엔진에서만 사용하는 기능을 위해 메서드를 추가해서, 모델 이름과 충전 레벨을 외부에 노출해야 함
   - 리팩토링 전에는 결과적으로 모델 이름과 충전 레벨을 외부에 노출 : 불필요한 Car 클래스의 정보들이 추가로 외부에 노출되는 것이기 때문에 캡슐화를 떨어뜨림
   - 리팩토링 후에는 getModel(), getChargeLevel() 과 같은 메서드를 모두 제거 : 결과적으로 꼭 필요한 메서드만 외부에 노출함으로써 Car의 캡슐화를 더 높일 수 있음
   
