-----
### 문제와 풀이 1
-----
1. 다음 클래스를 간단히 생성
   - 정적 중첩 클래스
   - 내부 클래스
   - 지역 클래스
   - 익명 클래스

2. 문제 1 - 정적 중첩 클래스를 완성
   - OuterClass1 (/nested/test)
```java
package nested.test;

public class OuterClass1 {
    static class NestedClass {
        public void hello() {
            System.out.println("NestedClass.hello");
        }
    }
}
```
   - OuterClass1Main
```java
package nested.test;

public class OuterClass1Main {
    public static void main(String[] args) {
        OuterClass1.NestedClass nested = new OuterClass1.NestedClass();
        nested.hello();
    }
}
```
  - 실행 결과
```
NestedClass.hello
```

3. 문제 2 - 내부 클래스를 완성
   - OuterClass2
```java
package nested.test;

public class OuterClass2 {
    class InnerClass {
        public void hello() {
            System.out.println("InnerClass.hello");
        }
    }
}
```

   - OuterClass2Main
```java
package nested.test;

public class OuterClass2Main {
    public static void main(String[] args) {
        OuterClass2 outer = new OuterClass2();
        OuterClass2.InnerClass inner = outer.new InnerClass();
        
        inner.hello();
    }
}
```

   - 실행 결과
```
InnerClass.hello
```

4. 문제 3 - 지역 클래스를 완성
   - OuterClass3
```java
package nested.test;

public class OuterClass3 {
    public void myMethod() {
        class LocalClass {
            public void hello() {
                System.out.println("LocalClass.hello");
            }
        }
        
        LocalClass local = new LocalClass();
        local.hello();
    }
}
```
  - OuterClass3Main
```java
package nested.test;

public class OuterClass3Main {
    public static void main(String[] args) {
        OuterClass3 outerClass3 = new OuterClass3();
        outerClass3.myMethod();
    }
}
```

5. 문제 4 - 익명 클래스 완성
   - Hello
```java
package nested.test;

public interface Hello {
    void hello();
}
```

   - AnonymousMain
```java
package nested.test;

public class AnonymousMain {
    public static void main(String[] args) {
        Hello hello = new Hello() {
            @Override
            public void hello() {
                System.out.println("Hello.hello");
            }
        };
        
        hello.hello();
    }
}
```

   - 실행 결과
```
Hello.hello
```

-----
### 문제와 풀이 2
-----
1. 문제 : 도서 관리 시스템
   - 도서관에서 사용할 수 있는 간단한 도서 관리 시스템
   - 이 시스템은 여러 권의 도서 정보를 관리할 수 있어야 함
   - 각 도서는 도서 제목(title)과 저자명(author)을 가지고 있음
   - 시스템은 도서를 추가하고, 모든 도서의 정보를 출력하는 기능을 제공해야 함

2. Library 클래스를 완성
   - LibraryMain과 실행 결과를 참고
   - Book 클래스는 Library 내부에서만 사용
   - Library 클래스 외부로 노출하면 안됨
   - Library 클래스는 Book 객체 배열을 사용해서 도서 목록을 관리

3. Library (/nested/test/ex1)
```java
package nested.test1;

public class Library {
    private Book[] books;
    private int bookCount;

    public Library(int size) {
        books = new Book[size];
        bookCount = 0;
    }

    public void addBook(String title, String author) {
        if(bookCount < books.length) {
            books[bookCount++] = new Book(title, author);
        } else {
            System.out.println("도서관 저장 공간이 부족합니다.");
        }
    }

    public void showBooks() {
        System.out.println("== 책 목록 출력 ==");

        for(int i = 0; i < bookCount; i++) {
            System.out.println("도서 제목 : " + books[i].title + ", 저자 : " + books[i].author);
        }
    }

    private static class Book {
        private String title;
        private String author;

        public Book(String title, String author) {
            this.title = title;
            this.author = author;
        }
    }
}
```

4. LibraryMain
```java
package nested.test1;

public class LibraryMain {
    public static void main(String[] args) {
        Library library = new Library(4); // 최대 4권 도서 저장할 수 있는 도서관 생성

        library.addBook("책1", "저자1");
        library.addBook("책2", "저자2");
        library.addBook("책3", "저자3");
        library.addBook("자바 ORM 표준 JPA 프로그래밍", "김영한");
        library.addBook("OneMoreThing", "잡스");

        library.showBooks();
    }
}
```

5. 실행 결과
```
도서관 저장 공간이 부족합니다.
== 책 목록 출력 ==
도서 제목 : 책1, 저자 : 저자1
도서 제목 : 책2, 저자 : 저자2
도서 제목 : 책3, 저자 : 저자3
도서 제목 : 자바 ORM 표준 JPA 프로그래밍, 저자 : 김영한
```
