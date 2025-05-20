-----
### 직접 구현하는 Set - 시작
-----
1. 셋을 구현하는 것은 아주 단순
   - 인덱스가 없기 때문에 단순히 데이터를 넣고, 데이터가 있는지 확인하고, 데이터를 삭제하는 정도면 충분
   - 그리고 데이터를 추가할 때 중복 여부만 체크해주면 됨
      + add(value) : 셋에 값을 추가, 중복 데이터는 저장하지 않음
      + contains(value) : 셋에 값이 있는지 확인
      + remove(value) : 셋에 있는 값을 제거

2. MyHashSetV0 (/collection/set)
```java
package collection.set;

import java.util.Arrays;

public class MyHashSetV0 {
    private int[] elementData = new int[10];
    private int size = 0;

    public boolean add(int value) {
        if(contains(value)) {
            return false;
        }

        elementData[size] = value;
        size++;

        return true;
    }

    public boolean contains(int value) {
        for (int data : elementData) {
            if(data == value) {
                return true;
            }
        }
        return false;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        return "MyHashSerV0{" +
                "elementData=" + Arrays.toString(Arrays.copyOf(elementData, size)) +
                ", size=" + size +
                '}';
    }
}
```
  - 예제에서는 단순함을 위해 배열에 데이터를 저장 (배열의 크기도 10으로 고정)
  - add(value)
    + 셋에 중복된 값이 있는지 체크하고, 중복된 값이 있으면 false를 반환
    + 중복된 값이 없으면 값을 저장하고 true를 반환
  - contains(value) : 셋에 값이 있는지 확인
    + 값이 있으면 true 를 반환하고, 값이 없으면 false를 반환
  - toString() : 배열을 출력하는 부분에 Arrays.copyOf() 를 사용해서 배열에 데이터가 입력된 만큼만 출력

3. MyHashSetMain
```java
package collection.set;

public class MyHashSetMain {
    public static void main(String[] args) {
        MyHashSetV0 set = new MyHashSetV0();
        set.add(1); // O(1)
        set.add(2); // O(n)
        set.add(3); // O(n)
        set.add(4); // O(n)
        System.out.println("set = " + set);

        boolean result = set.add(4); // 중복 데이터 저장
        System.out.println("중복 데이터 저장 결과 = " + result);
        System.out.println("set = " + set);

        System.out.println("set.contains(3) = " + set.contains(3)); // O(n)
        System.out.println("set.contains(4) = " + set.contains(99)); // O(n)
    }
}
```
  - 실행 결과
```
set = MyHashSerV0{elementData=[1, 2, 3, 4], size=4}
중복 데이터 저장 결과 = false
set = MyHashSerV0{elementData=[1, 2, 3, 4], size=4}
set.contains(3) = true
set.contains(4) = false
```
  - add() 로 데이터를 추가할 때 셋에 중복 데이터가 있는지 전체 데이터를 항상 확인 : O(n)으로 입력 성능이 나쁨
    + 중복 데이터 검색 O(n) + 데이터 입력 O(1) → O(n)
  - contains() 로 데이터를 찾을 때는 배열에 있는 모든 데이터를 찾고 비교해야 하므로 평균 O(n)
  
4. 정리
   - 데이터 추가, 검색 모두 O(n)으로 성능이 좋지 않음
   - 특히 데이터가 많을 수록 효율은 매우 떨어짐
   - 검색의 경우 이전에 보았던 ArrayList, LinkedList 도 O(n)이어서 어느정도 받아들 수 있지만, 데이터의 추가가 특히 문제
   - 데이터를 추가할 때마다 중복 데이터가 있는지 체크하기 위해 셋의 전체 데이터를 확인해야 하는데, 이때 O(n)으로 성능이 떨어짐
   - 데이터를 추가할 때마다 성능이 느린 자료 구조는 사용하기 어려움
