-----
### Iterator, ListIterator, Enumeration
-----
1. 컬렉션에 저장된 요소에 접근하는데 사용되는 인터페이스
2. Enumeration은 Iteration의 구버전
3. ListIterator는 Iterator의 기능을 향상

-----
### Iterator
-----
1. 컬렉션에 저장된 각 요소에 접근하는 기능을 가진 Iterator 정의
2. Collection 인터페이스는 Iterator(Iterator를 구현한 클래스의 인스턴스)를 반환하는 iterator()를 정의

```java
public interface Iterator {
    boolean hasNext();
    Object next();
    void remove();
}

public interface Collection {
    ...
    public Iterator iterator();
    ...
}
```

3. iterator()는 Collection 인터페이스에 정의된 메서드이므로 Collection 인터페이스의 자손인 List와 Set에도 포함
4. 컬렉션 클래스에 대해 iterator()를 호출하여 Iterator를 얻은 다음, 주로 while문을 사용해 컬렉션 클래스의 요소들을 읽어올 수 있음
<div align="center">
<img src="https://github.com/sooyounghan/Data-Base/assets/34672301/4dcbc34d-e753-48a4-ab0c-4a3175b941a7">
</div>

4. 예시) ArrayList에 저장된 요소를 출력하기 위한 코드
```java
Collection c = new ArrayList(); // 다른 컬렉션으로 변경 시 해당 부분만 변경

Iterator it = c.iterator();

while(it.hasNext()) {
    System.out.println(it.next());
}
```

5. Iterator를 이용해서 컬렉션의 요소를 읽어오는 방법을 표준화하였음 (즉, 코드의 재사용성을 높이는 것이 가능해짐)
6. Map 인터페이스를 구현한 클래스의 경우
   - 키(Key)와 값(Value)을 쌍으로 저장하고 있기 때문에, iterator()를 직접 호출할 수 없음
   - keySet()이나 entrySet()과 같은 메서드를 통해 키와 값을 따로 Set 형태로 얻어온 후 다시 iterator()를 호출해야 Iterator를 얻을 수 있음
```java
Map map = new HashMap();
...
Iterator it = map.entrySet().iterator();
```
  - 즉, 이 문장은 다음과 같음
```java
Set eSet = map.entrySet();
Iterator it = eSet.iterator();
```
  - 실행 순서는 다음과 같음
<div align="center">
<img src="https://github.com/sooyounghan/Data-Base/assets/34672301/d6deb072-0c53-42a4-92f8-21e365ce0160">
</div>

```
A. map.entrySet()의 실행결과 : Set
  - Iterator it = map.entrySet().iterator(); = Iterator it = Set인스턴스.iterator();
B. map.entrySet()를 통해 얻은 Set 인스턴스의 iterator()를 호출해 iterator의 인스턴스를 얻음
  - Iterator it = Set인스턴스.iterator(); = Iterator it = Iterator인스턴스;
C. 마지막으로 Iterator 인스턴스의 참조가 it에 저장
```

```java
import java.util.*;

class IteratorEx1 {
	public static void main(String[] args) {
		ArrayList list = new ArrayList();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");

		Iterator it = list.iterator();
		while(it.hasNext()) {
			Object obj = it.next();
			System.out.println(obj);
		}
	} // main
}
```
<div align="center">
<img src="https://github.com/sooyounghan/Data-Base/assets/34672301/7129e1ca-d3a0-4bcc-913d-79300b449c87">
</div>

  - List 클래스들은 저장순서를 유지하므로 Iterator를 이용한 결과는 저장 순서와 동일
  - Set 클래스들은 각 요소 간 순서가 유지되지 않으므로 Iterator를 이용해 저장된 요소들을 읽어 와도 처음 저장된 순서와 같지 않음


-----
### ListIterator, Enumeration
-----
1. Enumeration : 컬렉션 프레임워크가 만들어지기 이전에 사용하던 것으로 Iterator의 구버전 (호환성을 위해 남겨둠)
2. ListIterator : Iterator를 상속받아 기능을 추가
   - Iterator는 단방향으로만 이동 가능
   - ListIterator는 양방향으로 이동이 가능
   - 다만, ArrayList나 LinkedList와 같이 List 인터페이스를 구현한 컬렉션에서만 가능
3. Enumeration 인터페이스와 메서드
<div align="center">
<img src="https://github.com/sooyounghan/Data-Base/assets/34672301/560cb52b-b991-4f1c-89fc-9fa34069c24d">
</div>

4. ListIterator 인터페이스와 메서드
<div align="center">
<img src="https://github.com/sooyounghan/Data-Base/assets/34672301/843236c2-c776-4b2a-9c8d-e7cdbd8552a9">
</div>

```java
import java.util.*;

class ListIteratorEx1 {
	public static void main(String[] args) {
		ArrayList list = new ArrayList();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");

		ListIterator it = list.listIterator();

		while(it.hasNext()) {
			System.out.print(it.next()); // 순방향으로 진행하면서 읽어옴
		}
		System.out.println();

		while(it.hasPrevious()) {
			System.out.print(it.previous()); // 역방향으로 진행하면서 읽어옴
		}
		System.out.println();
	}
}
```
<div align="center">
<img src="https://github.com/sooyounghan/Data-Base/assets/34672301/6b1cfa1a-53bc-44d3-8704-5fab101b89e5">
</div>

  - Iterator는 단방향으로 이동하기 때문에, 컬렉션 마지막 요소에 다다르면 더 이상 사용 불가
  - ListIterator는 양방향으로 이동하기 때문에, 각 요소 간 이동이 자유로움
  - 다만, 이동하기 전 반드시 hasNext()나 hasPrevious()를 호출해 이동 여부 파악

-----
### 선택적 기능 (Optional Operation)
-----
1. 반드시 구현하지 않아도 되는 기능
2. 구현하지 않는다 하더라도, 인터페이스로부터 상속받은 메서드는 추상메서드이므로 메서드의 몸통(Body)를 반드시 만들어줘야함
```java
public void remove() {
    throw new UnsupportedOperationException();
}
```
  - 이처럼, 예외를 던져서 구현되지 않은 기능이란 것을 메서드를 호출하는 쪽에 알리는 것이 좋음
  - 그렇지 않으면, 호출하는 쪽에서는 소스를 구하기 전까지는 동작하지 않는 이유를 알 방법이 없음

3. Java API에서 remove() 메서드의 상세 내용을 보면, remove 메서드를 지원하지 않는 Iterator는 UnsupportedOprationException을 발생시킴 (즉, 이 예외가 발생시키도록 구현하라는 뜻)
4. 선언부에 예외처리를 하지 않은 이유는 UnsupportedOperationException은 RuntimeException의 자손
5. 또한, Iterator의 remove()는 단독으로 쓰일 수 없고, next()와 같이 사용해야 함
   - 즉, 특정 위치의 요소를 삭제하는 것이 아니라 읽어 온 것을 삭제
   - next() 호출 없이 remove()를 호출하면 IllegalStateException 발생
```java
import java.util.*;

public class IteratorEx2 {
	public static void main(String[] args) {
		ArrayList original = new ArrayList(10);
		ArrayList copy1    = new ArrayList(10);		
		ArrayList copy2    = new ArrayList(10);		
		
		for(int i=0; i < 10; i++)
			original.add(i+"");

		Iterator it = original.iterator();
		
		while(it.hasNext())
			copy1.add(it.next());

		System.out.println("= Original에서 copy1로 복사(copy) =");		
		System.out.println("original:"+original);
		System.out.println("copy1:"+copy1);
		System.out.println();

		it = original.iterator(); // Iterator는 재사용이 되지 않으므로, 다시 얻어와야 함
		
		while(it.hasNext()){
			copy2.add(it.next());
			it.remove();
		}
		
		System.out.println("= Original에서 copy2로 이동(move) =");		
		System.out.println("original:"+original);
		System.out.println("copy2:"+copy2);		
	} // main
} // class
```
<div align="center">
<img src="https://github.com/sooyounghan/Data-Base/assets/34672301/1c7c86b2-64ae-4edb-9f28-c12589c26c44">
</div>

-----
### Iterator의 구현
-----
```java
public Iterator iterator() {
    cursor=0;		// cursor와 lastRet를 초기화.
    lastRet = -1;
    return this;		
}	

public boolean hasNext() {
    return cursor != size();
}

public Object next(){
    Object next = get(cursor);
    lastRet = cursor++;
    return next;
}

public void remove() {
    // 더 이상 삭제할 것이 없다면, IllegalStateException 발생.
    if(lastRet==-1) {  
        throw new IllegalStateException();
    } else {
        remove(lastRet);   // 최근에 읽어온 요소 삭제
        cursor--;           // 삭제 후에 cursor 위치를 -1 감소
        lastRet = -1;		// 읽어온 요소가 삭제되었으므로 lastRet의 값을 초기화
    }
}
```

1. cursor : 앞으로 읽어 올 요소의 위치를 저장하는데 사용
2. lastRet : 마지막으로 읽어온 요소의 위치(index)를 저장
3. 따라서, lastRet은 cursor보다 항상 1이 작은 값이 저장
4. remove()를 호출하면 next()를 통해 읽은 위치의 요소, 즉 lastRet에 저장된 값의 위치에 있는 요소를 삭제하고, lastRet의 값을 -1로 초기화
   - remove메서드를 호출하고 나서 객체를 삭제하고나면, 삭제된 위치 이후의 객체들이 빈 공간을 채우기 위해 자동적으로 이동하기 때문임
   - 따라서, cursor의 위치도 같이 이동시켜줘야함
   - lastRet의 값이 -1인 이유 : 읽어온 값이 없다는 뜻
5. 만일, next()를 호출하지 않고, remove()를 호출하면 lastRet 값은 -1이 되어 IllegalStateException 발생
6. remove()는 next()로 읽어 온 객체를 삭제하는 것이므로 remove() 호출 전에는 반드시 next()가 호출된 상태이어야 함

7. 예시
```java
MyVector2 v = new MyVector2(5);

v.add("0");
v.add("1");
v.add("2");
v.add("3");
v.add("4");

Iterator it = v.iterator();
```
  - 0 ~ 4 사이의 값이 저장되어 있는 MyVector2의 Iterator를 얻어오면 다음과 같음 상태
<div align="center">
<img src="https://github.com/sooyounghan/Data-Base/assets/34672301/08f28d49-46c3-41ba-9875-ce1982c38c64">
</div>

  - next()를 두 번 호출하면 다음과 같이 되며, 첫 번째 요소 0을 읽고 두 번째 요소인 1까지 읽은 상태
<div align="center">
<img src="https://github.com/sooyounghan/Data-Base/assets/34672301/96fd0acb-829e-49ad-8b41-0cafc7e46607">
</div>

  - remove()를 호출하면 마지막으로 읽어 온 요소 1이 삭제
  - lastRet의 값은 -1로 초기화되고, cursor의 값은 1 감소
  - 데이터가 삭제되어 데이터들이 한자리씩 이동하였기 때문에, cursor의 위치도 한자리 이동
<div align="center">
<img src="https://github.com/sooyounghan/Data-Base/assets/34672301/1e73b333-049d-44e2-8f7e-e883b76b0911">
</div>


