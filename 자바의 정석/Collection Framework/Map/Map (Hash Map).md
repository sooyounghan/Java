-----
### HashMap
-----
1. Map을 구현했으므로, Map의 특징인 키(Key)와 값(Value)을 묶어서 하나의 데이터(Entry)로 저장한다는 특징을 가짐
2. 해싱(Hashing)을 사용하므로 많은 양의 데이터를 검색하는데 뛰어난 성능을 보임
```java
public class HashMap extends AbstractMap implements Map, Clonable, Serializable {
    transient Entry[] table;
    ...
    static class Entry implements Map.Entry {
        final Object key;
        Object value;
        ...
    }
}
```

3. HashMap은 Entry 라는 내부 클래스를 정의하고, 다시 Entry 타입의 배열을 선언
4. 키(Key)와 값(Value)은 별개의 값이 아니라 서로 관련된 값이므로 각 배열로 선언하기 보다 하나의 클래스로 정의해서 하나의 배열로 다루는 것이 데이터 무결성(Integrity)적 측면에서 더 바람직
<div align="center">
<img src="https://github.com/sooyounghan/HTTP/assets/34672301/36a85065-336e-4212-8f7d-d8981dccde05">
</div>

  - Map.Entry는 Map 인터페이스에 정의된 'static inner interface'

5. HashMap은 키와 값을 각각 Object타입으로 저장 (즉, (Object, Object)의 형태로 저장)하므로 어떠한 객체도 저장할 수 있으나 주로 Key는 String을 대문자 또는 소문자로 통일해서 사용하곤 함
```
키(Key) : 컬렉션 내 키(Key) 중에서 유일해야 함 (즉, HashMap에 저장된 데이터를 하나의 키로 검색했을 때, 결과가 단 하나이어야 함)
값(Value) : 키(Key)와 달리 데이터 중복 허용
```

6. HashMap의 생성자와 메서드
<div align="center">
<img  src="https://github.com/sooyounghan/HTTP/assets/34672301/50f05fc7-cd7c-42da-bd2b-91c2cac5cc38">
</div>

-----
### HashMap 예제
-----
```java
import java.util.*;

class HashMapEx1 {
	public static void main(String[] args) {
		HashMap map = new HashMap();
		map.put("myId", "1234");
		map.put("asdf", "1111");
		map.put("asdf", "1234");

		Scanner s = new Scanner(System.in);	// 화면으로부터 라인 단위로 입력받음

		while(true) {
			System.out.println("id와 password를 입력해주세요.");
			System.out.print("id :");
			String id = s.nextLine().trim();

			System.out.print("password :");
			String password = s.nextLine().trim();
			System.out.println();

			if(!map.containsKey(id)) {
				System.out.println("입력하신 id는 존재하지 않습니다. 다시 입력해주세요.");
				continue;
			} else {
				if(!(map.get(id)).equals(password)) {
					System.out.println("비밀번호가 일치하지 않습니다. 다시 입력해주세요.");
				} else {
					System.out.println("id와 비밀번호가 일치합니다.");						
					break;
				}
			}
		} // while
	} // main
}
```

```java
map.put("asdf", "1111");
map.put("asdf", "1234");
```
<div align="center">
<img src="https://github.com/sooyounghan/HTTP/assets/34672301/b2bf4d62-da28-46c8-99ae-5cb79105695d">
</div>

  - 3개의 데이터를 저장했지만, 실제로 2개 밖에 저장이 되지 않음 (중복된 키 때문임)
  - 이미 'asdf'라는 키가 존재하므로 새로 추가되는 대신 기존의 값을 덮어씀
  - Map의 값은 중복을 허용하지만, 키는 중복을 허용하지 않기 때문에 저장하려는 두 데이터 중 어느 쪽을 키로 할 것인지 결정해야 함
  - HashTable은 키(Key)나 값(Value)을 null로 허용하지 않으나, HashMap은 허용하여 map.put(null, null); 이나 map.get(null); 가능

```java
import java.util.*;

class HashMapEx2 {
	public static void main(String[] args) {
		HashMap map = new HashMap();
		map.put("김자바", new Integer(100));
		map.put("이자바", new Integer(100));
		map.put("강자바", new Integer(80));
		map.put("안자바", new Integer(90));

		Set set = map.entrySet();
		Iterator it = set.iterator();

		while(it.hasNext()) {
			Map.Entry e = (Map.Entry)it.next();
			System.out.println("이름 : "+ e.getKey() + ", 점수 : " + e.getValue());
		}

		set = map.keySet();
		System.out.println("참가자 명단 : " + set);

		Collection values = map.values();
		it = values.iterator();

		int total = 0;

		while(it.hasNext()) {
			Integer i = (Integer)it.next();
			total += i.intValue();
		}

		System.out.println("총점 : " + total);
		System.out.println("평균 : " + (float)total/set.size());
		System.out.println("최고점수 : " + Collections.max(values));
		System.out.println("최저점수 : " + Collections.min(values));
	}
}
```
<div align="center">
<img src="https://github.com/sooyounghan/HTTP/assets/34672301/e4239fff-1792-4308-96e2-b1c21e43b64a">
</div>

  - entrySet()을 이용해 키와 값을 함께 읽어올 수 있고, keySet()이나 values()를 이용해 키와 값을 따로 읽어올 수 있음

```java
import java.util.*;

class HashMapEx4 {
	public static void main(String[] args) {
		String[] data = { "A","K","A","K","D","K","A","K","K","K","Z","D" };

		HashMap map = new HashMap();

		for(int i=0; i < data.length; i++) {
			if(map.containsKey(data[i])) {
				Integer value = (Integer)map.get(data[i]);
				map.put(data[i], new Integer(value.intValue() + 1));
			} else {
				map.put(data[i], new Integer(1));			
			}
		}

		Iterator it = map.entrySet().iterator();

		while(it.hasNext()) {
			Map.Entry entry = (Map.Entry)it.next();
			int value = ((Integer)entry.getValue()).intValue();
			System.out.println(entry.getKey() + " : " + printBar('#', value) + " " + value );
		}
	} // main

	public static String printBar(char ch, int value) { 
		char[] bar = new char[value]; 

		for(int i=0; i < bar.length; i++) { 
			bar[i] = ch; 
		} 

		return new String(bar); 	// String(char[] chArr)
	}
}
```
<div align="center">
<img src="https://github.com/sooyounghan/HTTP/assets/34672301/25b0f138-2092-4dda-80b3-104a691e949c">
</div>

  - 문자열 배열에 담긴 문자열을 하나씩 읽어서 HashMap에 키로 저장하고, 값으로 1을 저장
  - 같은 문자열로 키로 저장되어 있는지 containsKey()로 확인하고, 이미 저장된 문자열이면 값을 1 증가
  - 한정된 범위 내 있는 순차적인 값들의 빈도수는 배열을 이용하지만, 한정되지 않은 범위의 비순차적인 값들의 빈도수는 HashMap을 이용해 구할 수 있음
