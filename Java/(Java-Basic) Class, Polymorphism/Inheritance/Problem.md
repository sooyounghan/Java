-----
### 문제와 풀이
-----
1. 문제 : 상속 관계 상품
  - 쇼핑몰의 판매 상품을 만들 것
  - Book, Album, Movie 이렇게 3가지 상품을 클래스로 만들어야 함
  - 코드 중복이 없게 상속 관계를 사용
  - 부모 클래스는 Item이라는 이름을 사용

2. 공통 속성 : name, price
  - Book : 저자(author), isbn(isbn)
  - Album : 아티스트(artist)
  - Movie : 감독(director), 배우(actor)

3. ShopMain (/extends1/ex)
```java
package extends1.ex;

public class ShopMain {
    public static void main(String[] args) {
        Book book = new Book("JAVA", 10000, "han", "12345");
        Album album = new Album("앨범1", 15000,"seo");
        Movie movie = new Movie("영화1", 18000,"감독1", "배우1");

        book.print();
        album.print();
        movie.print();

        int sum = book.getPrice() + album.getPrice() + movie.getPrice();

        System.out.println("상품 가격의 합: " + sum);
    }
}
```

4. Item
```java
package extends1.ex;

public class Item {
    private String name;
    private int price;

    public Item(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void print() {
        System.out.println("이름 : " + name + ", 가격 : " + price);
    }
}
```

5. Book
```java
package extends1.ex;

public class Book extends Item {
    private String author;
    private String isbn;

    public Book(String title, int price, String author, String isbn) {
        super(title, price);
        this.author = author;
        this.isbn = isbn;
    }

    @Override
    public void print() {
        super.print();
        System.out.println("- 저자 : " + author +", isbn : " + isbn);
    }
}
```

6. Album
```java
package extends1.ex;

public class Album extends Item{
    private String artist;

    public Album(String name, int price, String artist) {
        super(name, price);
        this.artist = artist;
    }

    @Override
    public void print() {
        super.print();
        System.out.println("- 아티스트 = " + artist);
    }
}
```

7. Movie
```java
package extends1.ex;

public class Movie extends Item {
    private String director;
    private String actor;

    public Movie(String name, int price, String director, String actor) {
        super(name, price);
        this.director = director;
        this.actor = actor;
    }

    @Override
    public void print() {
        super.print();
        System.out.println("- 감독 : " + director + ", 배우 : " + actor);
    }
}
```

