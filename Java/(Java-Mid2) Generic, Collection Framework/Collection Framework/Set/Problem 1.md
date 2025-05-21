-----
### 문제와 풀이 1
-----
1. 문제1 - 중복 제거
  - 문제 설명
    + 여러 정수가 입력, 여기서 중복 값을 제거하고 값을 출력
    + 30, 20, 20, 10, 10이 출력되면 중복을 제거하고 출력
    + 출력 순서는 관계없다.
    + 출력 예): 30, 20, 10 또는 10, 20, 30 또는 20, 10, 30등과 같이 출력 순서는 관계 없음

  - UniqueNamesTest1 (/collection/set/test)
```java
package collection.set.test;

import java.util.HashSet;
import java.util.Set;

public class UniqueNamesTest1 {
    public static void main(String[] args) {
        Integer[] inputArr = {30, 20, 20, 10, 10};

        HashSet<Integer> hashSet = new HashSet<>();

        for (Integer integer : inputArr) {
            hashSet.add(integer);
        }

        for (Integer integer : hashSet) {
            System.out.println(integer);
        }
    }
}
```
  - 실행 결과
```
20
10
30
```

  - HashSet 을 사용하면 중복 데이터는 저장되지 않음
  - 단순히 HashSet 에 값을 입력하고 HashSet을 출력  
  - HashSet은 순서를 보장하지 않음

2. 문제2 - 중복 제거와 입력 순서 유지
   - 문제 설명
     + 여러 정수가 입력, 여기서 중복 값을 제거하고 값을 출력
     + 30, 20, 20, 10, 10이 출력되면 중복을 제거하고 출력
     + 단 입력 순서대로 출력
     + 출력 예): 30, 20, 10
- UniqueNamesTest2
```java
package collection.set.test;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class UniqueNamesTest2 {
    public static void main(String[] args) {
        Integer[] inputArr = {30, 20, 20, 10, 10};

        Set<Integer> linkedHashSet = new LinkedHashSet<>(List.of(inputArr));

        for (Integer integer : linkedHashSet) {
            System.out.println(integer);
        }
    }
}
```

  - 실행 결과
```
30
20
10
```

  - 입력 순서대로 출력하려면 LinkedHashSet을 사용
  - 배열을 Set에 입력할 때 직접 배열을 반복하면서 Set 에 입력하는 방법도 있지만 더 간단히 해결하는 방법
    + 💡 Set 구현체의 생성자에 배열은 전달할 수 없지만 List는 전달할 수 있음
    + 다음과 같이 배열을 List로 변환

  - 배열을 리스트로 변환하기
```java
List<Integer> list = Arrays.asList(inputArr);
List<Integer> list = List.of(inputArr);
```

  - 편리한 리스트 생성
```java
List<Integer> list = Arrays.asList(1, 2, 3);
List<Integer> list = List.of(1, 2, 3);
```

  - Arrays.asList() 메서드는 자바 1.2부터 존재
  - 자바 9 이상을 사용한다면 List.of()를 권장
    
3. 문제3 - 중복 제거와 데이터 순서 유지
   - 문제 설명
     + 여러 정수가 입력, 여기서 중복 값을 제거하고 값을 출력
     + 30, 20, 20, 10, 10이 출력되면 중복을 제거하고 출력
     + 데이터의 값 순서로 출력
     + 출력 예): 10, 20, 30
- UniqueNamesTest23
```java
package collection.set.test;

import java.util.*;

public class UniqueNamesTest2 {
    public static void main(String[] args) {
        Set<Integer> treeSet = new TreeSet<>(List.of(30, 20, 20, 10, 10));

        for (Integer integer : treeSet) {
            System.out.println(integer);
        }
    }
}
```

  - 실행 결과
```
10
20
30
```
