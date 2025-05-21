-----
### 문제와 풀이 1 - Map 1
-----
1. 문제1 - 배열을 맵으로 전환
  - 문제 설명
    + 상품의 이름과 가격이 2차원 배열로 제공
    + 다음 예제를 참고해서 2차원 배열의 데이터를 ```Map<String, Integer>```로 변경
    + 그리고 실행 결과를 참고해서 Map 을 출력
    + 출력 순서는 상관없음

  - ArrayToMapTest (/collection/map/test)
```java
package collection.map.test;

import java.util.HashMap;
import java.util.Map;

public class ArrayToMapTest {
    public static void main(String[] args) {
        String[][] productArr = {{"Java", "10000"}, {"Spring", "20000"}, {"JPA", "30000"}};

        // 주어진 배열로부터 Map 생성 - 코드 작성
        Map<String, Integer> productMap = new HashMap<>();

        for (String[] product : productArr) {
            productMap.put(product[0], Integer.parseInt(product[1]));
        }

        // Map의 모든 데이터 출력 - 코드 작성
        for (String key : productMap.keySet()) {
            System.out.println("key = " + key + ", value = " + productMap.get(key));
        }
    }
}
```
  - 실행 결과
```
key = Java, value = 10000
key = JPA, value = 30000
key = Spring, value = 20000
```

2. 문제 2 - 공통의 합
   - 문제 설명 : map1과 map2 에 공통으로 들어있는 키를 찾고, 그 값의 합
   - CommonKeyValueSum1
```java
package collection.map.test;

import java.util.HashMap;
import java.util.Map;

public class CommonKeyValueSum1 {
    public static void main(String[] args) {
        Map<String, Integer> map1 = new HashMap<>();
        map1.put("A", 1);
        map1.put("B", 2);
        map1.put("C", 3);

        Map<String, Integer> map2 = new HashMap<>();
        map2.put("B", 4);
        map2.put("C", 5);
        map2.put("D", 6);

        // Map<String, Integer> map1 = Map.of("A", 1, "B", 2, "C", 3);
        // Map<String, Integer> map2 = Map.of("B", 4, "C", 5, "D", 6);
        
        Map<String, Integer> result = new HashMap<>();

        for (String key : map1.keySet()) {
            if(map2.containsKey(key)) {
                result.put(key, map1.get(key) + map2.get(key));
            }
        }

        System.out.println(result);
    }
}
```
  - 실행 결과
```
{B=6, C=8}
```
  - Map을 생성할 때 Map.of()를 사용하면 편리하게 Map 을 생성할 수 있음
  - Map.of()를 사용해서 생성한 Map은 불변
  - 따라서 내용을 변경할 수 없음

3. 문제 3 - 같은 단어가 나타난 수
   - 각각의 단어가 나타난 수를 출력
   - WordFrequencyTest1
```java
package collection.map.test;

import java.util.HashMap;
import java.util.Map;

public class WordFrequencyTest1 {
    public static void main(String[] args) {
        String text = "orange banana apple apple banana apple";

        // 코드 작성
        Map<String, Integer> map = new HashMap<>();

        String[] word = text.split(" ");
        for (String keyword : word) {
            if(map.containsKey(keyword)) {
                map.put(keyword, map.get(keyword) + 1);
            } else {
                map.put(keyword, 1);
            }
        }

        /*
        for (String word : words) {
             map.put(word, map.getOrDefault(word, 0) + 1);
        }
        */

        System.out.println("map = " + map);
    }
}
```
  - 실행 결과

```
{orange=1, banana=2, apple=3}
```
  - getOrDefault() 메서드를 사용하면 키가 없는 경우 대신 사용할 기본 값을 지정할 수 있음

4. 문제 4 - 값으로 검색
   - 문제 설명
     + 다음 예제에서 Map 에 들어있는 데이터 중에 값이 1000원인 모든 상품을 출력    
     + 실행 결과를 참고
   - ItemPriceTest
```java
package collection.map.test;
import java.util.*;
public class ItemPriceTest {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("사과", 500);
        map.put("바나나", 500);
        map.put("망고", 1000);
        map.put("딸기", 1000);

        List<String> list = new ArrayList<>();

        // 코드 작성
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        for (Map.Entry<String, Integer> entry : entries) {
            if(entry.getValue().equals(1000)) {
                list.add(entry.getKey());
            }
        }

        System.out.println("list = " + list);
    }
}
```

  - 실행 결과
```
list = [망고, 딸기]
```
