-----
### 문제와 풀이
-----
1. 문제 : 상품 주문 시스템 개발 - 리팩토링
   - 문제 설명 : 앞서 만들었던 다음 클래스에 있는 "상품 주문 시스템"을 리팩토링 (class1.ex.ProductOrderMain)
     + 온라인 상점의 주문 관리 시스템 개발
     + 먼저, 상품 주문 정보를 담을 수 있는 ProductOrder 클래스를 생성 
   - 요구 사항
     + ProductOrder 클래스는 다음과 같은 멤버 변수를 포함해야 함
       * 상품명 (productName)
       * 가격 (price)    
       * 주문 수량 (quantity)
     
   - 예시 코드 구조
```java
package class1.ex;

public class ProductOrder {
    String productName;
    int price;
    int quantity;
}
```

   - 다음으로 ref.ex.ProductOrderMain2 클래스 안에 main() 메서드를 포함하여, 여러 상품의 주문 정보를 배열로 관리하고, 그 정보들을 출력하고, 최종 결제 금액을 계산하여 출력
   - 이 클래스에서는 다음과 같은 메서드를 포함
      + static ProductOrder createOrder(String productName, int price, int quantity) : ProductOrder를 생성하고 매개변수로 초기값을 설정하며, 마지막으로 생성한 ProductOrder를 반환
      + static void printOrders(ProductOrder[] orders) : 배열을 받아서 배열에 들어있는 전체 ProductOrder의 상품명, 가격, 수량을 출력
      + static int getTotalAmount(ProductOrder[] orders) : 배열을 받아서 배열에 들어있는 전체 ProductOrder의 총 결제 금액을 계산하고, 계산 결과를 반환

   - ProductOrderMain2 (/ref/ex)
```java
package ref.ex;

public class ProductOrderMain2 {
    public static void main(String[] args) {
        ProductOrder[] orders = new ProductOrder[3];

        orders[0] = createOrder("두부", 2000, 2);
        orders[1] = createOrder("김치", 5000, 1);
        orders[2] = createOrder("콜라", 1500, 2);

        printOrders(orders);

        int totalAmount = getTotalAmount(orders);

        System.out.println("총 결제 금액 : " + totalAmount);
    }

    static ProductOrder createOrder(String productName, int price, int quantity) {
        ProductOrder order = new ProductOrder();

        order.productName = productName;
        order.price = price;
        order.quantity = quantity;

        return order;
    }

    static void printOrders(ProductOrder[] orders) {
        for (ProductOrder order : orders) {
            System.out.println("상품명 : " + order.productName + ", 가격 : " + order.price + ", 수량 : " + order.quantity);
        }
    }

    static int getTotalAmount(ProductOrder[] orders) {
        int totalAmount = 0;

        for (ProductOrder order : orders) {
            totalAmount += order.quantity * order.price;
        }

        return totalAmount;
    }
}
```

2. 문제 : 상품 주문 시스템 개발 - 사용자 입력
  - 문제 설명 : 앞서 만든 상품 주문 시스템을 사용자 입력을 받도록 개선
  - 요구 사항 : 아래 입력, 출력 예시를 참고해서 다음 사항을 적용
    + 주문 수량을 입력 (예) 입력할 주문의 개수를 입력하세요 : )
    + 가격, 수량, 상품명을 입력 :  입력 시 상품 순서를 알 수 있게 "n번째 주문 정보를 입력하세요." 라는 메시지를 출력
    + 입력이 끝나면 등록한 상품과 총 결제 금액을 출력

   - 입력 / 출력 예시
```
입력할 주문의 개수를 입력하세요 : 3
1번째 주문 정보를 입력하세요.
상품명 : 두부
가격 : 2000
수량 : 2
2번째 주문 정보를 입력하세요.
상품명 : 김치
가격 : 5000
수량 : 1
3번째 주문 정보를 입력하세요.
상품명 : 콜라
가격 : 1500
수량 : 2
상품명 : 두부, 가격 : 2000, 수량 : 2
상품명 : 김치, 가격 : 5000, 수량 : 1
상품명 : 콜라, 가격 : 1500, 수량 : 2
총 결제 금액 : 12000
```

  - ProductOrderMain3
```java
package ref.ex;

import java.util.Scanner;

public class ProductOrderMain3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("입력할 주문의 개수를 입력하세요 : ");
        int n = scanner.nextInt();
        scanner.nextLine();

        ProductOrder[] orders = new ProductOrder[n];

        for(int i = 0; i < n; i++) {
            System.out.println((i + 1) + "번째 주문 정보를 입력하세요.");

            System.out.print("상품명 : ");
            String productName = scanner.nextLine();

            System.out.print("가격 : ");
            int price = scanner.nextInt();

            System.out.print("수량 : ");
            int quantity = scanner.nextInt();
            scanner.nextLine();

            orders[i] = createOrder(productName, price, quantity);
        }

        printOrders(orders);

        int totalAmount = getTotalAmount(orders);

        System.out.println("총 결제 금액 : " + totalAmount);
    }

    static ProductOrder createOrder(String productName, int price, int quantity) {
        ProductOrder order = new ProductOrder();

        order.productName = productName;
        order.price = price;
        order.quantity = quantity;

        return order;
    }

    static void printOrders(ProductOrder[] orders) {
        for (ProductOrder order : orders) {
            System.out.println("상품명 : " + order.productName + ", 가격 : " + order.price + ", 수량 : " + order.quantity);
        }
    }

    static int getTotalAmount(ProductOrder[] orders) {
        int totalAmount = 0;

        for (ProductOrder order : orders) {
            totalAmount += order.quantity * order.price;
        }

        return totalAmount;
    }
}
```
