-----
### 문제와 풀이 2 - Map 2
-----
1. 문제5 - 영어 사전 만들기
  - 문제 설명
    + 영어 단어를 입력하면 한글 단어를 찾아주는 영어 사전을 만들 것
    + 먼저 영어 단어와 한글 단어를 사전에 저장하는 단계를 거침
    + 이후에 단어를 검색

  - 실행 결과를 참고
  - DictionaryTest (/collection/map/test)
```java
package collection.map.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DictionaryTest {
    public static void main(String[] args) {
        Map<String, String> dictionary = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("== 단어 입력 단계 ==");
        while(true) {
            System.out.println("영어 단어를 입력하세요. (종료는 'q') :");
            String searchWordWord = scanner.nextLine();
            if(searchWordWord.equals("q")) {
                break;
            }

            System.out.println("한글 뜻을 입력하세요. : ");
            String koreanMeaning = scanner.nextLine();

            dictionary.put(searchWordWord, koreanMeaning);
        }

        System.out.println("== 단어 검색 단계 ==");
        while(true) {
            System.out.println("찾을 영어 단어를 입력하세요. (종료는 'q') :");
            String searchWord = scanner.nextLine();
            if(searchWord.equals("q")) {
                break;
            }

            if(dictionary.containsKey(searchWord)) {
                System.out.println(searchWord + "의 뜻 : " + dictionary.get(searchWord));
            } else {
                System.out.println(searchWord + "은(는) 사전에 없는 단어입니다.");
            }
        }
    }
}
```

  - 단어 입력 단계
```java
==단어 입력 단계==
영어 단어를 입력하세요 (종료는 'q'): apple
한글 뜻을 입력하세요: 사과
영어 단어를 입력하세요 (종료는 'q'): banana
한글 뜻을 입력하세요: 바나나
영어 단어를 입력하세요 (종료는 'q'): q

==단어 검색 단계==
찾을 영어 단어를 입력하세요 (종료는 'q'): apple
apple의 뜻: 사과
찾을 영어 단어를 입력하세요 (종료는 'q'): banana
banana의 뜻: 바나나
찾을 영어 단어를 입력하세요 (종료는 'q'): hello
hello은(는) 사전에 없는 단어입니다.
찾을 영어 단어를 입력하세요 (종료는 'q'): q
```

2. 문제 6 - 회원 관리 저장소
  - 문제 설명
    + Map을 사용해서 회원을 저장하고 관리하는 MemberRepository 코드를 완성
    + Member, MemberRepositoryMain 코드와 실행 결과를 참고

  - Member
```java
package collection.map.test.member;

public class Member {
    private String id;
    private String name;

    public Member(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
```

  - MemberRepositoryMain
```java
package collection.map.test.member;

public class MemberRepositoryMain {
    public static void main(String[] args) {
        Member member1 = new Member("id1", "회원1");
        Member member2 = new Member("id2", "회원2");
        Member member3 = new Member("id3", "회원3");

        // 회원 저장
        MemberRepository repository = new MemberRepository();

        repository.save(member1);
        repository.save(member2);
        repository.save(member3);

        // 회원 조회
        Member findMember1 = repository.findById("id1");
        System.out.println("findMember1 = " + findMember1);

        Member findMember3 = repository.findByName("회원3");
        System.out.println("findMember3 = " + findMember3);

        // 회원 삭제
        repository.remove("id1");
        Member removedMember1 = repository.findById("id1");
        System.out.println("removedMember1 = " + removedMember1);
    }
}
```

  - MemberRepository
```java
package collection.map.test.member;

import java.util.HashMap;
import java.util.Map;

public class MemberRepository {
    private Map<String, Member> memberMap = new HashMap<>();

    public void save(Member member) {
        // 코드 작성
        memberMap.put(member.getId(), member);
    }
    public void remove(String id) {
        // 코드 작성
        memberMap.remove(id);
    }
    public Member findById(String id) {
        // 코드 작성
        return memberMap.get(id);
    }

    public Member findByName(String name) {
        // 코드 작성
        for(Member member : memberMap.values()) {
            if(member.getName().equals(name)) {
                return member;
            }
        }
        return null;
    }
}
```

3. 문제 7 - 장바구니
  - 장바구니 추가 - add()
    + 장바구니에 상품과 수량을 담으며, 상품의 이름과 가격이 같으면 같은 상품
    + 장바구니에 이름과 가격이 같은 상품을 추가하면 기존에 담긴 상품에 수량만 추가
    + 장바구니에 이름과 가격이 다른 상품을 추가하면 새로운 상품이 추가

  - 장바구니 제거 - minus()
    + 장바구니에 담긴 상품의 수량을 줄일 수 있으며, 만약 수량이 0보다 작다면 상품이 장바구니에서 제거
    + CartMain 과 실행 결과를 참고해서 Product, Cart 클래스를 완성
    + Cart 클래스는 Map을 통해 상품을 장바구니에 보관
    + Map 의 Key는 Product가 사용되고, Value는 장바구니에 담은 수량이 사용

  - CartMain (/collection/map/test/cart)
```java
package collection.map.test.cart;

public class CartMain {
    public static void main(String[] args) {
        Cart cart = new Cart();

        cart.add(new Product("사과", 1000), 1);
        cart.add(new Product("바나나", 500), 1);
        cart.printAll();

        cart.add(new Product("사과", 1000), 2);
        cart.printAll();

        cart.minus(new Product("사과", 1000), 3);
        cart.printAll();
    }
}
```
  - 실행 결과
```
==모든 상품 출력==
상품: Product{name='사과', price=1000} 수량: 1
상품: Product{name='바나나', price=500} 수량: 1

==모든 상품 출력==
상품: Product{name='사과', price=1000} 수량: 3
상품: Product{name='바나나', price=500} 수량: 1

==모든 상품 출력==
상품: Product{name='바나나', price=500} 수량: 1
```

  - Cart
```java
package collection.map.test.cart;

import java.util.Objects;

public class Product {
    private String name;
    private int price;

    // 코드 작성
    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return price == product.price && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
```
  - Map의 Key로 Product가 사용
  - 따라서 반드시 hashCode() , equals()를 재정의

  - Cart
```java
package collection.map.test.cart;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Cart {
    private Map<Product, Integer> cartMap = new HashMap<>();

    // 코드 작성
    public void add(Product product, int addQuantity) {
        int existingQuantity = cartMap.getOrDefault(product, 0);
        cartMap.put(product, existingQuantity + addQuantity);
    }

    public void minus(Product product, int minusQuantity) {
        int existingQuantity = cartMap.getOrDefault(product, 0);

        int newQuantity = existingQuantity - minusQuantity;
        
        if (newQuantity <= 0) {
            cartMap.remove(product);
        } else {
            cartMap.put(product, newQuantity);
        }
    }

    public void printAll() {
        System.out.println("== 모든 상품 출력 ==");
        for (Map.Entry<Product, Integer> entry : cartMap.entrySet()) {
            System.out.println("상품 : " + entry.getKey() + " 수량 : " + entry.getValue());
        }
    }
}
```
