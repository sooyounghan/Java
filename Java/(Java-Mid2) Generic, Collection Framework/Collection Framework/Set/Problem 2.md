-----
### 문제와 풀이 2
-----
1. 문제4 - 합집합, 교집합, 차집합
  - 문제 설명
    + 두 숫자의 집합이 제공
    + 집합 1 : 1, 2, 3, 4, 5
    + 집합 2 : 3, 4, 5, 6, 7

  - 두 집합의 합집합, 교집합, 차집합을 구할 것. 출력 순서는 관계없음
  - 합집합 : 두 집합의 합,, 참고로 중복은 제거 :  [1, 2, 3, 4, 5, 6, 7]
  - 교집합 : 두 집합의 공통 값, 참고로 중복은 제거 : [3, 4, 5]
  - 차집합 : 집합 1에서 집합 2와 같은 값을 뺀 나머지 : [1, 2]
  - 다음 실행 결과를 참고
  - SetOperationsTest
```java
package collection.set.test;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SetOperationsTest {
    public static void main(String[] args) {
        Set<Integer> set1 = new HashSet<>(List.of(1, 2, 3, 4, 5));
        Set<Integer> set2 = new HashSet<>(List.of(3, 4, 5, 6, 7));

        Set<Integer> unionSet = new HashSet<>(set1);
        unionSet.addAll(set2);

        System.out.println("합집합 : " + unionSet);

        Set<Integer> intersectionSet = new HashSet<>(set1);
        intersectionSet.retainAll(set2);

        System.out.println("교집합 : " + intersectionSet);

        Set<Integer> differenceSet = new HashSet<>(set1);
        differenceSet.removeAll(set2);

        System.out.println("차집합 : " + differenceSet);
    }
}
```
  - 실행 결과
```
합집합: [1, 2, 3, 4, 5, 6, 7]
교집합: [3, 4, 5]
차집합: [1, 2]
```

2. 문제5 - Equals, HashCode
  - 문제 설명
    + RectangleTest, 실행 결과를 참고해서 다음 Rectangle 클래스를 완성
    + Rectangle 클래스는 width, height 가 모두 같으면 같은 값으로 정의

  - Rectangle
```java
package collection.set.test;

import java.util.Objects;

public class Rectangle {
    private int width;
    private int height;

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
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

    @Override
    public String toString() {
        return "Rectangle{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }
}
```

  - RectangleTest
```java
package collection.set.test;

import java.util.HashSet;
import java.util.Set;

public class RectangleTest {
    public static void main(String[] args) {
        Set<Rectangle> rectangleSet = new HashSet<>();
        rectangleSet.add(new Rectangle(10, 10));
        rectangleSet.add(new Rectangle(20, 20));
        rectangleSet.add(new Rectangle(20, 20)); //중복
        for (Rectangle rectangle : rectangleSet) {
            System.out.println("rectangle = " + rectangle);
        }
    }
}
```

  - 실행 결과
```
rectangle = Rectangle{width=10, height=10}
rectangle = Rectangle{width=20, height=20}
```

  - HashSet 자료 구조에 저장
  - 해시 알고리즘을 사용하므로 반드시 hashCode(), equals()를 재정의해야 함
