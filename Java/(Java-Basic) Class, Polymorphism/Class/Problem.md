-----
### 문제와 풀이
-----
1. 문제 : 영화 리뷰 관리하기 1
   - 문제 설명 : 영화 리뷰 정보를 관리하려고 함
   - 먼저, 영화 리뷰 정보를 담을 수 있는 MovieReview 클래스를 생성
   - 요구 사항
     + MovieReview 클래스는 다음과 같은 멤버 변수를 포함해야 함
        * 영화 제목 (title)
        * 리뷰 내용 (review)
     + MovieReviewMain 클래스 안에 main() 메서드를 포함하여, 영화 리뷰 정보를 선언하고 출력

   - MovieReview (/class1/ex)
```java
package class1.ex;

public class MovieReview {
    String title;
    String review;
}
```
  - MovieReviewMain
```java
package class1.ex;

public class MovieReviewMain2 {
    public static void main(String[] args) {
        MovieReview movieReview1 = new MovieReview();

        movieReview1.title = "인셉션";
        movieReview1.review = "인생은 무한 루프";

        MovieReview movieReview2 = new MovieReview();

        movieReview2.title = "어바웃 타임";
        movieReview2.review = "인생 시간 영화";

        System.out.println("영화 제목 : " + movieReview1.title + ", 리뷰 : " + movieReview2.review);
        System.out.println("영화 제목 : " + movieReview1.title + ", 리뷰 : " + movieReview2.review);
    }
}
```
  - 실행 결과
```
영화 제목 : 인셉션, 리뷰 : 인생은 무한 루프
영화 제목 : 어바웃 타임, 리뷰 : 인생 시간 영화
```

2. 문제 : 영화 리뷰 관리하기 2
    - 기존 문제에 배열을 도입하고, 영화 리뷰를 배열에 관리
    - 리뷰를 출력할 때 배열과 for 문을 사용해서 System.out.println()을 한번만 사용
    - MovieReviewMain2
```java
package class1.ex;

public class MovieReviewMain2 {
    public static void main(String[] args) {
        MovieReview[] movieReviews = new MovieReview[2];

        MovieReview movieReview1 = new MovieReview();

        movieReview1.title = "인셉션";
        movieReview1.review = "인생은 무한 루프";

        movieReviews[0] = movieReview1;

        MovieReview movieReview2 = new MovieReview();

        movieReview2.title = "어바웃 타임";
        movieReview2.review = "인생 시간 영화";

        movieReviews[1] = movieReview2;

        for (MovieReview movieReview : movieReviews) {
            System.out.println("영화 제목 : " + movieReview.title + ", 리뷰 : " + movieReview.review);
        }
    }
}
```

3. 문제 : 상품 주문 시스템 개발
    - 문제 설명 : 온라인 상점의 주문 관리 시스템을 만들려고 함
       + 먼저, 상품 주문 정보를 담을 수 있는 ProductOrder 클래스를 생성
    - 요구 사항
       + ProductOrder 클래스는 다음과 같은 멤버 변수를 포함해야 함
         * 상품명 (productName)
         * 가격 (price)
         * 주문 수량 (quantity)

       + ProductOrderMain 클래스 안에 main() 메서드를 포함하여, 여러 상품의 주문 정보를 배열로 관리하고, 그 정보들을 출력하고, 최종 결제 금액을 계산하여 출력
       + 출력 예시와 같도록 출력

   - ProductOrder
```java
package class1.ex;

public class ProductOrder {
    String productName;
    int price;
    int quantity;
}
```

  - ProductOrderMain
```java
package class1.ex;

public class ProductOrderMain {
    public static void main(String[] args) {
        ProductOrder[] productOrders = new ProductOrder[3];

        ProductOrder productOrder1 = new ProductOrder();
        productOrder1.productName = "두부";
        productOrder1.price = 2000;
        productOrder1.quantity = 2;
        productOrders[0] = productOrder1;

        ProductOrder productOrder2 = new ProductOrder();
        productOrder2.productName = "김치";
        productOrder2.price = 5000;
        productOrder2.quantity = 1;
        productOrders[1] = productOrder2;

        ProductOrder productOrder3 = new ProductOrder();
        productOrder3.productName = "콜라";
        productOrder3.price = 1500;
        productOrder3.quantity = 2;
        productOrders[2] = productOrder3;

        int totalPrice = 0;

        for (ProductOrder productOrder : productOrders) {
            System.out.println("상품명 : " + productOrder.productName + ", 가격 : " + productOrder.price + ", 수량 : " + productOrder.quantity);
            totalPrice += (productOrder.price * productOrder.quantity);
        }

        System.out.println("총 결제 금액 : " + totalPrice);
    }
}
```
   - 출력 결과
```
상품명 : 두부, 가격 : 2000, 수량 : 2
상품명 : 김치, 가격 : 5000, 수량 : 1
상품명 : 콜라, 가격 : 1500, 수량 : 2
총 결제 금액 : 12000
```
