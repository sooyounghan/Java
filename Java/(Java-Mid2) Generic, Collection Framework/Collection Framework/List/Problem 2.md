-----
### 문제와 풀이2
-----
1. 문제 - 리스트를 사용한 쇼핑 카트
   - ShoppingCartMain 코드가 작동하도록 ShoppingCart 클래스를 완성
   - ShoppingCart 는 내부에 리스트를 사용

   - Item 클래스 (/collection/list/test/ex2)
```java
package collection.list.test.ex2;
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
   - ShoppingCartMain
```java
package collection.list.test.ex2;

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
   - ShoppingCart
```java
package collection.list.test.ex2;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<Item> items = new ArrayList<>();

    public void addItem(Item item) {
        items.add(item);
    }
    
    public void displayItems() {
        System.out.println("장바구니 상품 출력");

        for (Item item : items) {
            System.out.println("상품명 : " + item.getName() + ", 합계 : " + item.getTotalPrice());
        }
        System.out.println("전체 가격 합 = " + calculateTotalPrice());
    }

    private int calculateTotalPrice() {
        int totalPrice = 0;
        for (Item item : items) {
            totalPrice += item.getTotalPrice();
        }

        return totalPrice;
    }
}
```

   - 배열과 비교한 리스트의 이점
     + 자료 구조의 크기가 동적으로 증가
     + 따라서 배열처럼 입력 가능한 크기를 미리 정하지 않아도 됨
     + itemCount와 같이 배열에 몇게의 데이터가 추가 되었는지 추척하는 변수를 제거할 수 있음
     + 리스트는 size() 메서드를 통해 입력된 데이터의 크기를 제공

2. 참고 - 배열을 사용한 코드와 비교
```java
public class ShoppingCart {
     private Item[] items = new Item[10];
     private int itemCount;

     public void addItem(Item item) {
         if (itemCount >= items.length) {
             System.out.println("장바구니가 가득 찼습니다.");
             return;
         }
         items[itemCount] = item;
         itemCount++;
     }

     public void displayItems() {
         System.out.println("장바구니 상품 출력");

         for (int i = 0; i < itemCount; i++) {
             Item item = items[i];
             System.out.println("상품명:" + item.getName() + ", 합계:" + item.getTotalPrice());
         }

         System.out.println("전체 가격 합:" + calculateTotalPrice());
     }

     private int calculateTotalPrice() {
         int totalPrice = 0;

         for (int i = 0; i < itemCount; i++) {
             Item item = items[i];
             totalPrice += item.getTotalPrice();
         }
         return totalPrice;
     }
}
```
