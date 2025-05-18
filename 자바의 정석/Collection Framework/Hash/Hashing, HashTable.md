-----
### 해싱과 해시함수
-----
1. 해싱이란 해시함수(Hash Function)를 이용해서 데이터를 해시테이블(Hash Table)에 저장하고 검색하는 기법
2. 해시함수는 데이터가 저장되어 있는 곳을 알려주기 때문에 다량의 데이터 중에서도 원하는 데이터를 빠르게 찾을 수 있음
3. 해싱을 구현한 컬렉션 클래스 : HashSet, HashMap, HashTable 등
   - HashTable은 컬렉션 프레임워크가 도입되면서 HashMap으로 대체되었으나 이전 소스와의 호환성 문제로 유지
4. 해싱에서 사용하는 자료구조 : 배열과 LinkedList
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/6bc7a5de-1a58-4c98-847a-e7d6feedbab0">
</div>

5. 저장할 데이터의 키를 해시함수에 넣으면 배열의 한 요소를 얻게 되고, 다시 그 곳에 연결되어 있는 LinkedList에 저장
6. 예시) HashMap에 저장된 데이터를 찾는 과정
   - 주민등록번호의 맨 앞자리인 생년을 기준으로 데이터를 분류
   - 예) 79년생 환자의 주민번호를 키로 해시함수를 통해 7이라는 해시코드를 얻은 다음, 배열의 7번째 요소에 저장된 LinkedList에서 원하는 데이터를 검색하는 과정
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/b0cb7d19-c3b8-4d24-b164-d6ceb1be99f4">
</div>

```
A. 검색하고자 하는 값의 키로 해시함수를 호출
B. 해시함수의 계산 결과(해시코드)로 해당 값이 저장되어 있는 LinkedList를 찾음
C. LinkedList에서 검색한 키와 일치하는 데이터를 찾음
```

  - LinkedList는 검색에 불리한 자료구조이기 때문에 LinkedList의 크기가 커질수록, 검색속도가 떨어짐
  - 하지만, 배열은 크기가 커져도 원하는 요소가 몇 번째에 있는지만 알면, 다음과 같이 원하는 값을 빠르게 찾을 수 있음
```
배열의 인덱스가 n인 요소의 주소 = 배열의 시작 주소 + type의 size * (n - 1)
```

7. 해시코드의 성능이 좋지 않은 경우와 성능이 좋은 경우
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/ddc63882-fa7d-420b-8a64-aec67444f96b">
</div>

  - 하나의 LinkedList에 최소한 데이터만 저장되려면, 저장될 데이터의 크기를 고려해 HashMap의 크기를 적절하게 지정해줘야 함
  - 해시함수가 서로 다른 키에 대해 중복된 해시코드의 반환을 최소화해야, HashMap에서 빠른 검색 시간을 얻을 수 있음
  - 따라서, 해싱을 구현하는 과정에서 제일 중요한 것은 해시함수의 알고리즘
  - 해당 예제에서는 다음과 같이 해시코드화
```java
int hashFunction(String key) {
    return Integer.parseInt(key.substring(0, 1);
}
```

8. 알고리즘이 간단한 만큼 성능은 좋지 않아서 서로 다른 키에 대해서 중복된 해시코드를 반환하는 경우가 많음

-----
### 해시함수 (HashFunction)
-----
1. 해싱을 구현한 컬렉션 클래스에서는 Object클래스에 정의된 hashCode()를 해시함수로 사용
2. Object 클래스에 정의된 hashCode()는 객체의 주소를 이용하는 알고리즘으로 해시코드를 만들어 내기 떄문에, 모든 객체에 대해 hashCode()를 호출한 결과가 서로 유일
3. String 클래스의 경우 Object로부터 상속받은 hashCode()를 오버라이딩해서 문자열의 내용으로 해시코드를 만들어냄
  - 따라서, 서로 다른 String 인스턴스일지라도 같은 내용의 문자열을 가졌다면 hashCode()를 호출하면 같은 해시코드를 얻음
4. 서로 다른 두 객체에 대해 equals()로 비교한 결과가 true인 동시에 hashCode()의 반환값이 같아야 같은 객체로 인식
5. HashMap에서도 같은 방법으로 객체를 구별하며, 이미 존재하는 키에 대한 값을 저장하면 기존의 값을 새로운 값으로 덮어씀
6. 따라서, 새로운 클래스를 정의할 때, equals()를 재정의 오버라이딩해야 한다면, hashCode()도 같이 재정의해서 equals()의 결과가 true인 두 객체의 hashCode()의 결과가 항상 같도록 해줘야함
  - 그렇지 않으면, HashMap과 같이 해싱을 구현한 컬렉션 클래스에서는 equals()의 호출결과가 true지만, 해시코드가 다른 두 객체를 서로 다른 것으로 인식하고 따로 저장
