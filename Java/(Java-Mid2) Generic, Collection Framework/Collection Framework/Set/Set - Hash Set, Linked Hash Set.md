-----
### 자바가 제공하는 Set 1 - HashSet, LinkedHashSet
-----
1. Set (셋, 세트) 자료 구조
<div align="center">
<img src="https://github.com/user-attachments/assets/cbcc4d63-7df9-4bb5-a9bc-c44173e4af50">
</div>

  - 셋은 중복을 허용하지 않고, 순서를 보장하지 않는 자료 구조

2. 컬렉션 프레임워크 - Set
<div align="center">
<img src="https://github.com/user-attachments/assets/5a158d0b-2e7d-4466-9164-088d3ff75008">
</div>

3. Collection 인터페이스
    - Collection 인터페이스는 java.util 패키지의 컬렉션 프레임워크의 핵심 인터페이스 중 하나이
    - 이 인터페이스는 자바에서 다양한 컬렉션, 즉 데이터 그룹을 다루기 위한 메서드를 정의
    - Collection 인터페이스는 List, Set, Queue와 같은 다양한 하위 인터페이스와 함께 사용되며, 이를 통해 데이터를 리스트, 세트, 큐 등의 형태로 관리할 수 있음
  
4. Set 인터페이스
    - 자바의 Set 인터페이스는 java.util 패키지의 컬렉션 프레임워크에 속하는 인터페이스 중 하나
    - Set 인터페이스는 중복을 허용하지 않는 유일한 요소의 집합을 나타냄
    - 즉, 어떤 요소도 같은 Set 내에 두 번 이상 나타날 수 없음
    - Set 은 수학적 집합 개념을 구현한 것으로, 순서를 보장하지 않으며, 특정 요소가 집합에 있는지 여부를 확인하는데 최적화
    - Set 인터페이스는 HashSet, LinkedHashSet, TreeSet 등의 여러 구현 클래스를 가지고 있으며, 각 클래스는 Set 인터페이스를 구현하며 각각의 특성을 가지고 있음

5. Set 인터페이스 주요 메서드
<div align="center">
<img src="https://github.com/user-attachments/assets/4e6a7c02-3725-4372-90cc-aac1b6d9d76f">
</div>

6. HashSet
   - 구현 : 해시 자료 구조를 사용해서 요소를 저장
   - 순서 : 요소들은 특정한 순서 없이 저장됨. 즉, 요소를 추가한 순서를 보장하지 않음
   - 시간 복잡도 : HashSet 의 주요 연산(추가, 삭제, 검색)은 평균적으로 O(1) 시간 복잡도를 가짐
   - 용도 : 데이터의 유일성만 중요하고, 순서가 중요하지 않은 경우에 적합

<div align="center">
<img src="https://github.com/user-attachments/assets/a9ec4505-b00b-4534-ac2c-7e8105e719ec">
</div>

   - hashCode() , equals() 를 모두 사용

7. LinkedHashSet
   - 구현 : LinkedHashSet 은 HashSet 에 연결 리스트를 추가해서 요소들의 순서를 유지
   - 순서 : 요소들은 추가된 순서대로 유지. 즉, 순서대로 조회 시 요소들이 추가된 순서대로 반환
   - 시간 복잡도 : LinkedHashSet도 HashSet 과 마찬가지로 주요 연산에 대해 평균 O(1) 시간 복잡도를 가짐
   - 용도 : 데이터의 유일성과 함께 삽입 순서를 유지해야 할 때 적합
   - 참고 : 연결 링크를 유지해야 하기 때문에 HashSet 보다는 조금 더 무거움

<div align="center">
<img src="https://github.com/user-attachments/assets/40f1ba40-2223-45aa-9eb2-628d96c53cf7">
</div>

  - LinkedHashSet은 HashSet 에 연결 링크만 추가한 것
    + HashSet에 LinkedList를 합친 것
   - 이 연결 링크는 데이터를 입력한 순서대로 연결
   - head(first) 부터 순서대로 링크를 따라가면 입력 순서대로 데이터를 순회할 수 있음
   - 양방향으로 연결 (그림에서는 이해를 돕기 위해 화살표는 다음 순서로만 표시, 실제로는 양방향)
   - 여기서는 1, 2, 5, 8, 14, 99 순서대로 입력되므로, 링크를 보면 1, 2, 5, 8, 14, 99 순서로 연결 되어 있는 것을 확인 가능
   - 이 링크를 first 부터 순서대로 따라가면서 출력하면 순서대로 출력할 수 있음
