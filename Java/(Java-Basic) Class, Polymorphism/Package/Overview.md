-----
### 패키지 - 시작
-----
1. 쇼핑몰 시스템을 개발한다고 가정
2. 다음과 같이 프로그램이 매우 작고 단순해서 클래스가 몇게 없다면 크게 고민할 거리가 없겠지만, 기능이 점점 추가되어서 프로그램이 아주 커지게 된다면 어떻게 해야할까?
  - 아주 작은 프로그램
```
Order
User
Product
```
  - 큰 프로그램
```
User
UserManager
UserHistory
Product
ProductCatalog
ProductImage
Order
OrderService
OrderHistory
ShoppingCart
CartItem
Payment
PaymentHistory
Shipment
ShipmentTracker
```

3. 매우 많은 클래스가 등장하면서 관련 있는 기능들을 분류해서 관리하고 싶을 것
   - 컴퓨터는 보통 파일을 분류하기 위해 폴더, 디렉토리라는 개념을 제공
   - 자바도 이런 개념을 제공하는데, 이것이 바로 패키지

4. 다음과 같이 카테고리를 만들고 분류
```
* user
   * User
   * UserManager
   * UserHistory
* product
   * Product
   * ProductCatalog
   * ProductImage
* order
   * Order
   * OrderService
   * OrderHistory
* cart
   * ShoppingCart
   * CartItem
* payment
   * Payment
   * PaymentHistory
* shipping
   * Shipment
   * ShipmentTracker
```
   - 여기서 user, product 등이 바로 패키지
   - 그리고 해당 패키지 안에 관련된 자바 클래스들을 넣으면 됨
  
5. 패키지(package) : 이름 그대로 물건을 운송하기 위한 포장 용기나 그 포장 묶음을 뜻함
6. 패키지 사용
   - 패키지를 먼저 만들고 그 다음에 클래스를 만들어야 함
   - 패키지 위치에 주의
   - pack.Data
```java
package pack;

public class Data {
    public Data() {
        System.out.println("패키지 pack Data 생성");
    }
}
```
   - 💡 패키지를 사용하는 경우 항상 코드 첫줄에 package pack과 같이 패키지 이름을 적어주어야 함
   - 여기서는 pack 패키지에 Data 클래스를 만듬
   - 이후에 Data 인스턴스가 생성되면 생성자를 통해 정보를 출력

   - pack.a.User
```java
package pack.a;

public class User {
    public User() {
        System.out.println("패키지 pack.a 회원 생성");
    }
}
```
   - pack 하위에 a 라는 패키지를 생성 : pack.a 패키지에 User 클래스를 생성
   - 이후에 User 인스턴스가 생성되면 생성자를 통해 정보를 출력

   - 참고 : 생성자에 public 을 사용
     + 다른 패키지에서 이 클래스의 생성자를 호출하려면 public 을 사용해야 함

   - pack.PackageMain1
```java
package pack;

public class PackageMain1 {
    public static void main(String[] args) {
        Data data = new Data();
        pack.a.User user = new pack.a.User();
    }
}
```
  - pack 패키지 위치에 PackageMain1 클래스를 생성
  - 실행 결과
```
패키지 pack Data 생성
패키지 pack.a 회원 생성
```

7. 사용자와 같은 위치 : PackageMain1과 Data는 같은 pack 이라는 패키지에 소속 : 이렇게 같은 패키지에 있는 경우에는 패키지 경로를 생략 가능
8. 사용자와 다른 위치 : PackageMain1과 User는 서로 다른 패키지 : 이렇게 패키지가 다르면 pack.a.User 와 같이 패키지 전체 경로를 포함해서 클래스를 적어주어야 함
