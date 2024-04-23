-----
### 컬렉션 프레임워크 (Colleciton Framework)
-----
1. 데이터 군(群)을 저장하는 클래스들을 표준화한 설계
2. 컬렉션 (Collection) : 다수의 데이터, 즉 데이터 그룹
3. 프레임워크 (Framework) : 표준화된 프로그래밍 방식

-----
### 컬렉션 프레임워크 (Colleciton Framework)의 핵심 인터페이스
-----
1. 컬렉션을 다루는데 필요한 기능을 가진 3개의 인터페이스를 정의
2. 인터페이스 List, Set의 공통 부분을 다시 뽑아서 새로운 인터페이스인 Colleciton을 추가로 정의
<div align="center">
<img src="https://github.com/sooyounghan/Data-Base/assets/34672301/246b40b8-4cd4-4cd9-bbfc-347b92ef8465">
</div>

3. 인터페이스 List와 Set을 구현한 컬렉션 클래스는 서로 많은 공통 부분이 존재하여, 공통된 부분을 뽑아 Collection 인터페이스를 정의
4. Map 인터페이스는 전혀 다른 형태로 컬렉션을 다루므로 같은 상속 계층도에 포함되지 못함
5. 컬렉션 프레임워크의 핵심 인터페이스의 특징
<div align="center">
<img src="https://github.com/sooyounghan/Data-Base/assets/34672301/a7f30c07-415e-44ab-a3e4-69fba46ddf6f">
</div>

  - 키(Key) : 데이터 집합 중 어떤 값을 찾는 열쇠가 된다는 의미에서 붙여진 이름으로, 중복을 허용하지 않음

6. 컬렉션 프레임워크의 모든 컬렉션 클래스는 List, Set, Map 중 하나를 구현하고 있음
7. 구현한 인터페이스의 이름이 클래스 이름에 포함되어 있어 이름만으로도 클래스의 특징을 쉽게 알 수 있도록 되어있음
   - 단, Vector, Stack, Hashtable, Properties와 같은 클래스들은 컬렉션 프레임워크가 만들어지기부터 존재하여 명명법에 따르지 않음
   - 이들은 기존 컬렉션 클래스들과 호환을 위해, 설계를 변경해서 남겨두었지만 되도록 사용하지 않는 것이 좋으며, ArrayList, HashMap 사용 권장
<div align="center">
<img src="https://github.com/sooyounghan/Data-Base/assets/34672301/fc6f22eb-8b57-43e7-a3c0-a1965c04ec28">
</div>

-----
### Collection 인터페이스
-----
1. List와 Set의 조상인 Collection 인터페이스는 다음과 같은 메서드 정의 (여기서는 편의상 Object로 표기, 본래 Generics로 표기)
<div align="center">
<img src="https://github.com/sooyounghan/Data-Base/assets/34672301/6cc2311b-1138-495f-a55e-d408b4e4426d">
</div>

2. Colleciton 인터페이스는 컬렉션 클래스에 저장된 데이터를 읽고, 추가하고 삭제하는 등 컬렉션을 다루는데 가장 기본적인 메서드 정의
3. 집합과 관련된 메서드 (Collection에 변화가 있으면 true, 없으면 false)
<div align="center">
<img src="https://github.com/sooyounghan/Data-Base/assets/34672301/9381194d-7513-4065-b2f8-fe35ad82c6d0">
</div>

-----
### List 인터페이스
-----
1. 중복을 허용하면서 저장순서가 유지되는 컬렉션을 구현하는데 사용
<div align="center">
<img src="https://github.com/sooyounghan/Data-Base/assets/34672301/bdacee03-b03d-4073-bd79-d33bcab0349e">
</div>

2. List 인터페이스에 정의된 메서드 (Colleciton 인터페이스로부터 상속받은 것은 제외)
<div align="center">
<img src="https://github.com/sooyounghan/Data-Base/assets/34672301/798b46e9-5e05-4651-922e-2d10009fbe41">
</div>

-----
### Set 인터페이스
-----
1. 중복을 허용하지 않고, 저장순서가 유지되지 않는 컬렉션 클래스를 구현하는데 사용
2. HashSet, TreeSet 등의 클래스가 Set 인터페이스를 구현한 구현체
<div align="center">
<img src="https://github.com/sooyounghan/Data-Base/assets/34672301/d0759a36-bb7d-4c64-ad53-118d72284257">
</div>

3. Set 인터페이스의 정의된 메서드와 Colleciton 인터페이스의 메서드와 동일

-----
### Map 인터페이스
-----
* Map : 어떤 두 값을 연결한다는 의미
1. 키(Key)와 값(Value)을 하나의 쌍으로 묶어서 저장하는 컬렉션 클래스를 구현하는데 사용
2. 키는 중복될 수 없지만, 값은 중복을 허용
3. 기존에 저장된 데이터와 중복된 키와 값을 저장하면 기존의 값은 없어지고 마지막에 저장된 값이 남게됨
4. Map 인터페이스를 구현한 클래스 : HashTable, Properties, HashMap, TreeMap, LinkHashMap, SortedMap
<div align="center">
<img src="https://github.com/sooyounghan/Data-Base/assets/34672301/f64dce96-0373-4270-b1ee-188968936415">
</div>

5. Map 인터페이스에 정의된 메서드
<div align="center">
<img src="https://github.com/sooyounghan/Data-Base/assets/34672301/d8f95362-e54c-495d-bab6-78cf16f22163">
</div>

  - values()에서 반환타입이 Collection, keySet()에서 반환타입이 Set
  - Map인터페이스에서 값(Value)은 중복을 허용하기 때문에 Colleciton 타입
  - 키(Key)는 중복을 허용하지 않기 때문에, Set 타입 반환

6. Map.Entry 인터페이스
   - Map 인터페이스의 내부 인터페이스
   - 인터페이스도 인터페이스 안에 인터페이스를 정의 가능 (내부 인터페이스 (Inner Interface))
   - 즉, Map에 저장되는 Key-Value 쌍을 다루기 위해 내부적으로 Entry 인터페이스를 정의
   - 따라서, Map 인터페이스를 구현하는 클래스는 Map.Entry 인터페이스도 함께 구현해야 함
   - Map 인터페이스의 소스코드 일부
```java
public interface Map {
    ...
    public static interface Entry {
        Object getKey();
        Object getValue();
        Object setValue();
        boolean equals(Object o);
        int hashCode();
        ...
    }
}
```
<div align="center">
<img src="https://github.com/sooyounghan/Data-Base/assets/34672301/35367418-75cf-412f-9e58-875849bf851f">
</div>
