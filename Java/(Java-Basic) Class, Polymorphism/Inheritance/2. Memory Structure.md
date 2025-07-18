-----
### 💡 상속과 메모리 구조
-----
1. 상속 관계를 객체로 생성할 때 메모리 구조를 확인
```java
ElectricCar electricCar = new ElectricCar();
```
<div align="center">
<img src="https://github.com/user-attachments/assets/3600f883-19dd-4243-86d7-4d3ddeb8b3c9">
</div>

   - new ElectricCar()를 호출하면 ElectricCar뿐만 아니라 상속 관계에 있는 Car까지 함께 포함해서 인스턴스를 생성
   - 참조값은 x001로 하나이지만 실제로 그 안에서는 Car, ElectricCar라는 두가지 클래스 정보가 공존하는 것
   - 상속이라고 해서 단순하게 부모의 필드와 메서드만 물려 받는게 아닌, 상속 관계를 사용하면 부모 클래스도 함께 포함해서 생성
   - 외부에서 볼때는 하나의 인스턴스를 생성하는 것 같지만 내부에서는 부모와 자식이 모두 생성되고 공간도 구분

2. electricCar.charge() 호출
<div align="center">
<img src="https://github.com/user-attachments/assets/55dff9d1-82c8-401b-ac47-390b25daddc6">
</div>

   - electricCar.charge()를 호출하면 참조값을 확인해서 x001.charge()를 호출
   - 따라서 x001을 찾아서 charge()를 호출하면 되는 것
   - 그런데 상속 관계의 경우에는 내부에 부모와 자식이 모두 존재
   - 이때 부모인 Car를 통해서 charge()를 찾을지 아니면 ElectricCar를 통해서 charge()를 찾을지 선택해야 함
   - 💡 이 때는 호출하는 변수의 타입(클래스)을 기준으로 선택
      + electricCar 변수의 타입이 ElectricCar 이므로 인스턴스 내부에 같은 타입인 ElectricCar를 통해서 charge()를 호출

3. electricCar.move() 호출
<div align="center">
<img src="https://github.com/user-attachments/assets/d16cb741-a29e-4932-85f7-c6eddd56f32e">
</div>

   - electricCar.move()를 호출하면 먼저 x001 참조로 이동
   - 내부에는 Car, ElectricCar 두가지 타입 존재
   - 이 때 호출하는 변수인 electricCar의 타입이 ElectricCar이므로 이 타입을 선택
   - 그런데 ElectricCar에는 move() 메서드가 없음
      + 상속 관계에서는 자식 타입에 해당 기능이 없으면 부모 타입으로 올라가서 찾음
      + 이 경우 ElectricCar의 부모인 Car로 올라가서 move()를 찾음
      + 부모인 Car에 move()가 있으므로 부모에 있는 move() 메서드를 호출
   - 만약 부모에서도 해당 기능을 찾지 못하면 더 상위 부모에서 필요한 기능을 찾아봄
   - 부모에 부모로 계속 올라가면서 필드나 메서드를 찾는 것
   - 물론 계속 찾아도 없으면 컴파일 오류가 발생

4. 정리
   - 상속 관계의 객체를 생성하면 그 내부에는 부모와 자식이 모두 생성  
   - 상속 관계의 객체를 호출할 때, 대상 타입을 정해야 함
   - 이때 호출자의 타입을 통해 대상 타입을 찾음
   - 현재 타입에서 기능을 찾지 못하면 상위 부모 타입으로 기능을 찾아서 실행
   - 기능을 찾지 못하면 컴파일 오류가 발생
