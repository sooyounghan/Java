-----
### 컬렉션 유틸
-----
1. 컬렉션을 편리하게 다룰 수 있는 다양한 기능
2. 정렬 - CollectionSortMain (/collection/utils)
```java
package collection.utils;

import java.util.ArrayList;
import java.util.Collections;

public class CollectionSortMain {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        Integer max = Collections.max(list);
        Integer min = Collections.min(list);

        System.out.println("max = " + max);
        System.out.println("min = " + min);
        System.out.println("list = " + list);

        Collections.shuffle(list);
        System.out.println("Shffule list = " + list);

        Collections.sort(list);
        System.out.println("list = " + list);

        Collections.reverse(list);
        System.out.println("list = " + list);
    }
}
```
  - 실행 결과
```
max = 5
min = 1
list = [1, 2, 3, 4, 5]
Shffule list = [4, 2, 1, 5, 3]
list = [1, 2, 3, 4, 5]
list = [5, 4, 3, 2, 1]
```

   - Collections 정렬 관련 메서드
      + max : 정렬 기준으로 최대 값을 찾아서 반환
      + min : 정렬 기준으로 최소 값을 찾아서 반환
      + shuffle : 컬렉션을 랜덤하게 섞음
      + sort : 정렬 기준으로 컬렉션을 정렬
      + reverse : 정렬 기준의 반대로 컬렉션을 정렬 (컬렉션에 들어있는 결과를 반대로 정렬)

3. 편리한 컬렉션 생성 - OfMain
```java
package collection.utils;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class OfMain {
    public static void main(String[] args) {
        // 편리한 불변 컬렉션 생성
        List<Integer> list = List.of(1, 2, 3);
        Set<Integer> set = Set.of(1, 2, 3);
        Map<Integer, String> map = Map.of(1, "one", 2, "two", 3, "three");

        System.out.println("list = " + list);
        System.out.println("set = " + set);
        System.out.println("map = " + map);
        System.out.println("list.getClass() = " + list.getClass());

        // java.lang.UnsupportedOperationException 예외 발생
        // list.add(4);
    }
}
```
  - 실행 결과
```
list = [1, 2, 3]
set = [1, 2, 3]
map = {1=one, 2=two, 3=three}
list.getClass() = class java.util.ImmutableCollections$ListN
```

   - List.of(...) : 컬렉션을 편리하게 생성할 수 있음
     + 단 이때는 가변이 아니라 불변 컬렉션이 생성
   - List , Set , Map 모두 of() 메서드를 지원
   - 불변 컬렉션은 변경할 수 없으며, 변경 메서드를 호출하면 UnsupportedOperationException 예외가 발생

4. 불변 컬렉션과 가변 컬렉션 전환 - ImmutableMain
```java
package collection.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ImmutableMain {
    public static void main(String[] args) {
        // 불변 리스트 생성
        List<Integer> list = List.of(1, 2, 3);

        // 가변 리스트
        ArrayList<Integer> mutableList = new ArrayList<>(list);
        mutableList.add(4);

        System.out.println("mutableList = " + mutableList);
        System.out.println("mutableList.getClass() = " + mutableList.getClass());

        // 불변 리스트
        List<Integer> unmodifiableList = Collections.unmodifiableList(mutableList);
        System.out.println("unmodifiableList.getClass() = " + unmodifiableList.getClass());

        // 예외 발생 java.lang.UnsupportedOperationException
        // unmodifiableList.add(5)
    }
}
```
  - 실행 결과
```
mutableList = [1, 2, 3, 4]
mutableList.getClass() = class java.util.ArrayList
unmodifiableList.getClass() = class java.util.Collections$UnmodifiableRandomAccessList
```

5. 빈 리스트 생성 - EmptyListMain
```java
package collection.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class EmptyListMain {
    public static void main(String[] args) {
        // 빈 리스트 생성
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new LinkedList<>();
        
        // 빈 불변 리스트 생성
        List<Integer> list3 = Collections.emptyList(); // 자바 5
        List<Integer> list4 = List.of(); // 자바 9

        System.out.println("list3.getClass() = " + list3.getClass());
        System.out.println("list4.getClass() = " + list4.getClass());
    }
}
```
  - 실행 결과
```
list3.getClass() = class java.util.Collections$EmptyList
list4.getClass() = class java.util.ImmutableCollections$ListN
```

   - 빈 가변 리스트는 원하는 컬렉션의 구현체를 직접 생성
   - 빈 불변 리스트 2가지 생성 방법
      + Collections.emptyList() : 자바5 부터 제공되는 기능
      + List.of() : 자바9부터 제공되는 최신 기능
   - List.of() 가 더 간결하고, List.of(1,2,3) 도 불변이기 때문에 사용법에 일관성이 있음
   - 자바 9 이상을 사용한다면 이 기능을 권장

6. Arrays.asList()
    - Arrays.asList 메서드를 사용해도 다음과 같이 리스트를 생성 가능
    - 참고로 이 메서드는 자바 1.2부터 존재
    - 자바 9를 사용한다면 List.of() 를 권장
```java
List<Integer> list = Arrays.asList(1, 2, 3);
List<Integer> list = List.of(1, 2, 3);
```

   - Arrays.asList() 로 생성된 리스트는 고정된 크기를 가지지만, 요소들은 변경할 수 있음
     + 즉, 리스트의 길이는 변경할 수 없지만, 기존 위치에 있는 요소들을 다른 요소로 교체할 수 있음
     + set() 을 통해 요소를 변경할 수 있음
     + add() , remove() 같은 메서드를 호출하면 예외가 발생 (크기를 변경할 수 없음)
        * java.lang.UnsupportedOperationException 발생
   - 고정도 가변도 아닌 애매한 리스트
   - 정리하면 일반적으로 List.of() 를 사용하는 것을 권장
   - 다음과 같은 경우 Arrays.asList() 를 선택할 수 있음
      + 변경 가능한 요소 : 리스트 내부의 요소를 변경해야 하는 경우 (단, 리스트의 크기는 변경할 수 없음)
      + 하위 호환성 : Java 9 이전 버전에서 작업해야 하는 경우

7. 멀티스레드 동기화 - SyncMain
```java
package collection.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class SyncMain {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        System.out.println("list.getClass() = " + list.getClass());

        List<Integer> synchronizaedList = Collections.synchronizedList(list);
        System.out.println("synchronizaedList.getClass() = " + synchronizaedList.getClass());
    }
}
```
   - 실행 결과
```
list.getClass() = class java.util.ArrayList
synchronizaedList.getClass() = class java.util.Collections$SynchronizedRandomAccessList
```

   - Collections.synchronizedList를 사용하면 일반 리스트를 멀티스레드 상황에서 동기화 문제가 발생하지 않는 안전한 리스트로 만들 수 있음
   - 동기화 작업으로 인해 일반 리스트보다 성능은 더 느림
