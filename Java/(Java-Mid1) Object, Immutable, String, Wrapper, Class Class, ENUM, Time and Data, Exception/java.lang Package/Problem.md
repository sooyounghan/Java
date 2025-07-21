-----
### 문제와 풀이
-----
1. 문제 : toString(), equals() 구현하기
  - 문제 설명
    + 다음 코드와 실행 결과를 참고해서 Rectangle 클래스를 생성
    + Rectangle 클래스에 IDE의 기능을 사용해서 toString(), equals() 메서드를 실행 결과에 맞도록 재정의
    + rect1과 rect2는 너비(width)와 높이(height)를 가지며, 너비와 높이가 모두 같다면 동등성 비교에 성공

2. RectangleMain (/lang/object/test)
```java
package lang.object.equals.test;

public class RectangleMain {
    public static void main(String[] args) {
        Rectangle rect1 = new Rectangle(100, 20);
        Rectangle rect2 = new Rectangle(100, 20);

        System.out.println(rect1);
        System.out.println(rect2);

        System.out.println(rect1 == rect2);
        System.out.println(rect1.equals(rect2));
    }
}
```
   - 실행 결과
```
Rectangle{width=100, height=20}
Rectangle{width=100, height=20}
false
true
```

3. Rectangle
```java
package lang.object.equals.test;

import java.util.Objects;

public class Rectangle {
    private int width;
    private int height;

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return width == rectangle.width && height == rectangle.height;
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, height);
    }
}
```
