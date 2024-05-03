-----
### Collections
-----
1. 컬렉션과 관련된 메서드 등을 제공하는 클래스
2. Arrays와 동일하게 fill(), copy(), sort(), binarySearch() 등의 메서드 등 모두 포함하며 같은 기능을 함

-----
### Collections.synchronized...(...) : 컬렉션의 동기화
-----
1. 멀티 쓰레드(Multi-Thread) 프로그래밍에서는 하나의 객체를 여러 쓰레드가 동시에 접근할 수 있기 때문에 일관성(Consistency)을 유지하기 위해서 공유되는 객체에 동기화(Synchronization)가 필요
2. Vector와 HashTable과 같은 구버전 클래스들은 자체적을 동기화 처리가 되어 있지만, 멀티쓰레드 프로그래밍이 아닌 경우에는 불필요한 기능이 되어 성능을 떨어뜨리는 요인이 됨
3. 새로 추가된 ArrayList나 HashMap과 같은 컬렉션은 동기화를 자체적으로 하지 않고, 필요한 경우에만 java.util.Collections 클래스의 동기화 메서드를 이용해 동기화 처리가 가능하도록 변경
4. 다음과 같이 제공
```java
static Collection synchronizedCollection(Collection c)
static List synchronizedList(List list)
static Set synchronizedSet(Set s)
static Map synchronizedMap(Map m)
static SortedSet synchronizedSortedSet(SortedSet s)
static SortedMap synchronizedSortedMap(SortedMap m)
```

-----
### Collections.unmodifiable...(...) : 변경 불가 컬렉션 만들기
-----
1. 컬렉션에 저장된 데이터를 보호하기 위해서 컬렉션을 변경할 수 없게, 즉 읽기 전용으로 만들어주는 메서드
2. 멀티 쓰레드 프로그래밍에서 여러 쓰레드가 하나의 컬렉션을 공유하다보면, 데이터가 손상될 경우가 있는데 이를 방지
```java
static Collection unmodifiableCollection(Collection c)
static List unmodifiableList(List list)
static Set unmodifiableSet(Set s)
static Map unmodifiableMap(Map m)
static NavigableSet unmodifiableNavigableSet(NavigableSet s)
static SortedSet unmodifiableSortedSet(SortedSet s)
static NavigableMap unmodifiableNavigableMap(NavigableSet s)
static SortedMap unmodifiableSortedMap(SortedMap m)
```

-----
### Collections.singleton...(...) : 싱글톤 컬렉션 만들기
-----
1. 단 하나만의 객체만을 저장하는 컬렉션을 만들 때 사용
2. 매개변수로 저장할 요소를 지정하면, 해당 요소를 저장하는 컬렉션 반환
3. 반환된 컬렉션은 변경할 수 없음
```java
static List singletonList(List list)
static Set singletonSet(Set s)
static Map singletonMap(Map m)
```

-----
### Collections.checked...(...) : 한 종류의 객체만 저장하는 컬렉션 만들기
-----
1. 컬렉션에 모든 종류의 객체를 저장할 수 있는 것은 장점이자, 단점
2. 대부분의 경우 한 종류의 개체를 저장하며, 컬렉션에 지정된 종류의 객체만 저장할 수 있도록 제한하고자 할 때 사용
<div align="center">
<img src="https://github.com/sooyounghan/HTTP/assets/34672301/a6302fe8-5a74-4dbc-ad08-d03b6486a39b">
</div>

3. 사용방법은 두 번쨰 매개변수에 저장할 객체의 클래스를 지정하면 됨
```java
List list = new ArrayList();
List checkedList = checkedList(list, String.class); // String만 저장 가능
checkedList.add("abc");
checkedList.add(new Integer(3)); // Error. ClassCastException 발생
```

