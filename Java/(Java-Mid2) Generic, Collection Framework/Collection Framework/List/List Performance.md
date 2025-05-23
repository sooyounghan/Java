-----
### 직접 구현한 리스트의 성능 비교
-----
1. MyListPerformanceTest
```java
package collection.link;

import collection.list.MyArrayList;
import collection.list.MyLinkedList;
import collection.list.MyList;

public class MyListPerformanceTest {
    public static void main(String[] args) {
        int size = 50_000;

        System.out.println("== MyArrayList 추가 ==");
        addFirst(new MyArrayList<>(), size);
        addMid(new MyArrayList<>(), size);
        MyArrayList<Integer> arrayList = new MyArrayList<>(); // 조회용 데이터로 사용
        addLast(arrayList, size);

        System.out.println("== MyLinkedList 추가 ==");
        addFirst(new MyLinkedList<>(), size);
        addMid(new MyLinkedList<>(), size);
        MyLinkedList<Integer> linkedList = new MyLinkedList<>(); // 조회용 데이터로 사용
        addLast(linkedList, size);

        int loop = 10_000;
        System.out.println("== MyArrayList 조회 ==");
        getIndex(arrayList, loop, 0);
        getIndex(arrayList, loop, size / 2);
        getIndex(arrayList, loop, size - 1);

        System.out.println("== MyLinkedList 조회 ==");
        getIndex(linkedList, loop, 0);
        getIndex(linkedList, loop, size / 2);
        getIndex(linkedList, loop, size - 1);

        System.out.println("== MyArrayList 검색 ==");
        search(arrayList, loop, 0);
        search(arrayList, loop, size / 2);
        search(arrayList, loop, size - 1);

        System.out.println("== MyLinkedList 조회 ==");
        search(linkedList, loop, 0);
        search(linkedList, loop, size / 2);
        search(linkedList, loop, size - 1);
    }

    private static void addFirst(MyList<Integer> list, int size) {
        long startTime = System.currentTimeMillis();
        for(int i = 0; i < size; i++) {
            list.add(0, i);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("앞에 추가 - 크기 : " + size + ", 계산 시간  " + (endTime - startTime) + "ms");
    }

    private static void addMid(MyList<Integer> list, int size) {
        long startTime = System.currentTimeMillis();
        for(int i = 0; i < size; i++) {
            list.add(i / 2, i);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("평균 추가 - 크기 : " + size + ", 계산 시간  " + (endTime - startTime) + "ms");
    }

    private static void addLast(MyList<Integer> list, int size) {
        long startTime = System.currentTimeMillis();
        for(int i = 0; i < size; i++) {
            list.add(i);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("뒤에 추가 - 크기 : " + size + ", 계산 시간  " + (endTime - startTime) + "ms");
    }

    private static void getIndex(MyList<Integer> list, int loop, int index) {
        long startTime = System.currentTimeMillis();
        for(int i = 0; i < loop; i++) {
            list.get(index);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("index : " + index + ", 반복 : " + loop +", 계산 시간  " + (endTime - startTime) + "ms");
    }

    private static void search(MyList<Integer> list, int loop, int findValue) {
        long startTime = System.currentTimeMillis();
        for(int i = 0; i < loop; i++) {
            list.indexOf(findValue);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("findValue : " + findValue + ", 반복 : " + loop +", 계산 시간  " + (endTime - startTime) + "ms");
    }
}
```
  - 실행 결과
```
== MyArrayList 추가 ==
앞에 추가 - 크기 : 50000, 계산 시간  2281ms
평균 추가 - 크기 : 50000, 계산 시간  1334ms
뒤에 추가 - 크기 : 50000, 계산 시간  3ms
== MyLinkedList 추가 ==
앞에 추가 - 크기 : 50000, 계산 시간  3ms
평균 추가 - 크기 : 50000, 계산 시간  792ms
뒤에 추가 - 크기 : 50000, 계산 시간  1487ms
== MyArrayList 조회 ==
index : 0, 반복 : 10000, 계산 시간  0ms
index : 25000, 반복 : 10000, 계산 시간  0ms
index : 49999, 반복 : 10000, 계산 시간  0ms
== MyLinkedList 조회 ==
index : 0, 반복 : 10000, 계산 시간  1ms
index : 25000, 반복 : 10000, 계산 시간  294ms
index : 49999, 반복 : 10000, 계산 시간  589ms
== MyArrayList 검색 ==
findValue : 0, 반복 : 10000, 계산 시간  1ms
findValue : 25000, 반복 : 10000, 계산 시간  210ms
findValue : 49999, 반복 : 10000, 계산 시간  454ms
== MyLinkedList 조회 ==
findValue : 0, 반복 : 10000, 계산 시간  0ms
findValue : 25000, 반복 : 10000, 계산 시간  331ms
findValue : 49999, 반복 : 10000, 계산 시간  659ms
```
  - 실행 결과는 시스템 환경 마다 다를 수 있음

2. 직접 만든 배열 리스트와 연결 리스트 - 성능 비교 표
<div align="center">
<img src="https://github.com/user-attachments/assets/e847e500-a581-4d41-930c-e972242c44de">
</div>

3. 추가, 삭제
   - 배열 리스트는 인덱스를 통해 추가나 삭제할 위치를 O(1)로 빠르게 찾지만, 추가나 삭제 이후에 데이터를 한칸씩 밀어야 함 : 이 부분이 O(n)으로 오래 걸림
   - 연결 리스트는 인덱스를 통해 추가나 삭제할 위치를 O(n)으로 느리게 찾지만, 실제 데이터의 추가는 간단한 참조 변경으로 빠르게 O(1)로 수행

4. 앞에 추가(삭제)
   - 배열 리스트 : 추가나 삭제할 위치는 찾는데 O(1), 데이터를 한칸씩 이동 O(n) → O(n)
   - 연결 리스트 : 추가나 삭제할 위치는 찾는데 O(1), 노드를 변경하는데 O(1) → O(1)

5. 평균 추가(삭제)
   - 배열 리스트 : 추가나 삭제할 위치는 찾는데 O(1), 인덱스 이후의 데이터를 한칸씩 이동 O(n/2) → O(n)
   - 연결 리스트 : 추가나 삭제할 위치는 찾는데 O(n/2), 노드를 변경하는데 O(1) → O(n)

6. 뒤에 추가(삭제)
   - 배열 리스트 : 추가나 삭제할 위치는 찾는데 O(1), 이동할 데이터 없음 → O(1)
   - 연결 리스트 : 추가나 삭제할 위치는 찾는데 O(n), 노드를 변경하는데 O(1) → O(n)

7. 인덱스 조회
   - 배열 리스트 : 배열에 인덱스를 사용해서 값을 O(1)로 찾을 수 있음 → O(1)
   - 연결 리스트 : 노드를 인덱스 수 만큼 이동해야함 → O(n)
     
8. 검색
   - 배열 리스트 : 데이터를 찾을 때 까지 배열을 순회 → O(n)
   - 연결 리스트 : 데이터를 찾을 때 까지 노드를 순회 → O(n)

9. 시간 복잡도와 실제 성능
   - 이론적으로 MyLinkedList의 평균 추가(중간 삽입) 연산은 MyArrayList보다 빠를 수 있음
   - 그러나 실제 성능은 요소의 순차적 접근 속도, 메모리 할당 및 해제 비용, CPU 캐시 활용도 등 다양한 요소에 의해 영향을 받음
   - MyArrayList는 요소들이 메모리 상에서 연속적으로 위치하여 CPU 캐시 효율이 좋고, 메모리 접근 속도가 빠름
   - 반면, MyLinkedList는 각 요소가 별도의 객체로 존재하고 다음 요소의 참조를 저장하기 때문에 CPU 캐시 효율이 떨어지고, 메모리 접근 속도가 상대적으로 느릴 수 있음
   - MyArrayList의 경우 CAPACITY 를 넘어서면 배열을 다시 만들고 복사하는 과정이 추가
    + 하지만 한번에 2배씩 늘어나기 때문에 이 과정은 가끔 발생하므로, 전체 성능에 큰 영향을 주지는 않음
   - 정리하면 이론적으로 MyLinkedList가 평균 추가(중간 삽입)에 있어 더 효율적일 수 있지만, 현대 컴퓨터 시스템의 메모리 접근 패턴, CPU 캐시 최적화 등을 고려할 때 MyArrayList 가 실제 사용 환경에서 더 나은 성능을 보여주는 경우가 많음

10. 배열 리스트 vs 연결 리스트
    - 대부분의 경우 배열 리스트가 성능상 유리 : 실무에서는 주로 배열 리스트를 기본으로 사용
    - 만약 데이터를 앞쪽에서 자주 추가하거나 삭제할 일이 있다면 연결 리스트를 고려
