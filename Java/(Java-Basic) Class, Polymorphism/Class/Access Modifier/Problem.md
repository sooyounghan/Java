-----
### 문제와 풀이
-----
1. 문제 - 최대 카운터와 캡슐화
   - MaxCounter 클래스를 생성
   - 이 클래스는 최대값을 지정하고 최대값까지만 값이 증가하는 기능을 제공
      + int count : 내부에서 사용하는 숫자 (초기값은 0)
      + int max : 최대값 (생성자를 통해 입력)
      + increment() : 숫자를 하나 증가
      + getCount() : 지금까지 증가한 값을 반환

    - 요구사항
      + 접근 제어자를 사용해서 데이터를 캡슐화
      + 해당 클래스는 다른 패키지에서도 사용할 수 있어야 함

   - MaxCount (/access/ex)
```java
package access.ex;

public class MaxCounter {
    private int count = 0;
    private int max;

    public MaxCounter(int max) {
        this.max = max;
    }

    public void increment() {
        if(count >= max) {
            System.out.println("최대값을 초과할 수 없습니다.");
            return;
        }

        count++;
    }

    public int getCount() {
        return count;
    }
}
```

   - CounterMain
```java
package access.ex;

public class CounterMain {
    public static void main(String[] args) {
        MaxCounter counter = new MaxCounter(3);

        counter.increment();
        counter.increment();
        counter.increment();
        counter.increment();

        int count = counter.getCount();

        System.out.println(count);
    }
}
```

  - 실행 결과
```
최대값을 초과할 수 없습니다.
3
```

2. 문제 - 쇼핑 카트
   - ShoppingCartMain 코드가 작동하도록 Item, ShoppingCart 클래스를 완성
   - 요구사항
      + 접근 제어자를 사용해서 데이터를 캡슐화
      + 해당 클래스는 다른 패키지에서도 사용할 수 있어야 함
      + 장바구니에는 상품을 최대 10개만 담을 수 있음
      + 10개 초과 등록시 : "장바구니가 가득 찼습니다." 출력
    
   - ShoppingCartMain
```java
package access.ex;

public class ShoppingCartMain {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();

        Item item1 = new Item("마늘", 2000, 2);
        Item item2 = new Item("상추", 3000, 4);

        cart.addItem(item1);
        cart.addItem(item2);

        cart.displayItems();
    }
}
```
   - Item
```java
package access.ex;

public class Item {
    private String name;
    private int price;
    private int quantity;

    public Item(String name, int price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getTotalPrice() {
        return price * quantity;
    }
}
```
   - 각각의 Item 의 가격과 수량을 곱하면 각 상품별 합계를 구할 수 있음
   - price와 quantity를 외부에 반환한 다음에 외부에서 곱해서 상품별 합계를 구해도 되지만, getTotalPrice() 메서드를 제공하면 외부에서는 단순히 이 메서드를 호출하면 됨
   - 이 메서드의 핵심은 자신이 가진 데이터를 사용한다는 점

   - ShoppingCart
```java
package access.ex;

public class ShoppingCart {
    private Item[] items = new Item[10];
    private int itemCount;

    public void addItem(Item item) {
        if(itemCount >= items.length) {
            System.out.println("장바구니가 꽉 찼습니다");
            return;
        }

        items[itemCount] = item;
        itemCount++;
    }

    public void displayItems() {
        System.out.println("장바구니 상품 출력");
        for(int i = 0; i < itemCount; i++) {
            Item item = items[i];
            System.out.println("상품명 : " + item.getName() + ", 합계 : " + item.getTotalPrice());
        }
        System.out.println("전체 가격 합 : " + calculateTotalPrice());
    }

    private int calculateTotalPrice() {
        int totalPrice = 0;

        for(int i = 0; i < itemCount; i++) {
            Item item = items[i];
            totalPrice += item.getTotalPrice();
        }

        return totalPrice;
    }
}
```
  - calculateTotalPrice() : 이 메서드 내부에서만 사용되므로 private 접근 제어자를 사용
