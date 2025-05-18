-----
### Comparable, Comparator
-----
1. Comparable와 Comparator는 모두 인터페이스로 컬렉션을 정렬하는데 필요한 메서드를 정의
2. Comparable을 구현하고 있는 클래스들은 같은 타입의 인스턴스끼리 서로 비교할 수 있는 클래스들을 기본적으로 오름차순(작은 값에서 큰 값)으로 정렬되도록 구현
   - Wrapper클래스, String, Date, File 등과 같은 서로 비교할 수 있는 클래스
3. 따라서, Comparable을 구현한 클래스는 정렬이 가능함을 의미
4. Comparable과 Comparator의 실제 소스
  - Comparable Interface (java.lang Package)
```java
public interface Comparable {
    public int compareTo(Object o);
}
```

  - Comparator Interface (java.util Package)
```java
public interface Comparator {
    int compare(Object o1, Object o2);
    boolean equals(Object obj);
}
```

  - compare()와 compareTo()는 선언형태와 이름만 약간 다를 뿐 두 객체를 비교한다는 같은 목적으로 고안

5. compareTo(), compare()의 반환값은 int
   - 실제 비교하는 두 객체가 같으면 0
   - 비교하는 값보다 작으면 음수
   - 비교하는 값보다 크면 양수

6. equals() 메서드는 모든 클래스가 가지고 있는 공통적인 메서드이지만, Comparator를 구현한 클래스는 오버라이딩이 필요할 수 있다는 것을 알리기 위해 정의
7. Integer Wrapper Class의 예
```java
public final class Integer extends Number implements Comparable {
      ...
      public int compareTo(Object o) {
            return compareTo((Integer)o);
      }

      public int compareTo(Integer anotherInteger) {
            int thisVal = this.value;
            int anotherVal = anotherInteger.value;

            // 비교하는 값이 크면 -1, 같으면 0, 작으면 1을 반환
            return (thisVal < anotherVal ? -1 : (thisVal == anotherVal ? 0 : 1));
      }
      ...
}
```

8. Comparable을 구현한 클래스들은 기본적으로 오름차순으로 정렬
9. 내림차순으로 정렬 또는 다른 기준에 의해 정렬되도록 하고 싶다면, Comparator를 구현해서 정렬 기준 제공
```
Comparable : 기본 정렬기준 구현하는데 사용
Comparator : 기본 정렬기준 외에 다른 기준으로 정렬하고자할 때 사용
```

```java
import java.util.*;

class ComparatorEx {
	public static void main(String[] args) {
		String[] strArr = {"cat", "Dog", "lion", "tiger"};

		Arrays.sort(strArr); // String의 Comparable구현에 의한 정렬
		System.out.println("strArr=" + Arrays.toString(strArr));

		Arrays.sort(strArr, String.CASE_INSENSITIVE_ORDER); // 대소문자 구분 안함
		System.out.println("strArr=" + Arrays.toString(strArr));

		Arrays.sort(strArr, new Descending()); // 역순 정렬
		System.out.println("strArr=" + Arrays.toString(strArr));
	}
}

class Descending implements Comparator { 
	public int compare(Object o1, Object o2){
		if( o1 instanceof Comparable && o2 instanceof Comparable) {
			Comparable c1 = (Comparable)o1;
			Comparable c2 = (Comparable)o2;
			return c1.compareTo(c2) * -1 ; // -1을 곱해서 정렬방식의 역으로 변경
										   // 또는 c2.compareTo(c1)와 같이 순서를 바꿔도 됨.

		}
		return -1;
	} 
} 
```
<div align="Center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/ed43ef52-ffeb-4d31-a9c6-32ec9a3f03c0">
</div>

  - Arrays.sort()는 배열을 정렬할 때, Comparator를 지정해주지 않으면 저장하는 객체(주로 Comparable을 구현한 클래스의 객체)에 구현된 내용에 따라 정렬
```java
static void sort(Object[] a) // 객체 배열에 저장된 객체가 구현한 Comparable에 의한 정렬
static void sort(Object[] a, Comparator c) // 지정한 Comparator에 의한 정렬
```

10. String의 Comparable 구현은 문자열이 사전 순으로 정렬되도록 작성
    - 문자열의 오름차순 정렬 : 공백 - 숫자 - 대문자 - 소문자순 (유니코드 순서가 작은 값에서 큰 값으로 정렬)
    - 대소문자를 구분하지 않고 비교하는 Compartor를 상수 형태로 제공
    - 해당 Comparator를 사용하면 문자열을 대소문자 구분없이 정렬 가능
```java
public static final Comparator CASE_INTENSIVE_ORDER

Arrays.sort(strArr, String.CASE_INTENSIVE_ORDER); // 대소문자 구분없이 정렬
```

   - String의 기본 정렬을 반대(내림차순(Descending Order)) 구현은 String에 구현된 compareTo()의 결과에 -1을 곱하거나 또는 비교하는 객체의 위치를 바꿔 c2.compareTo(c1)로 하면됨
   - 다만, compare()의 매개변수가 Obejct 타입이므로 compareTo()를 바로 호출 할 수 없으며, 먼저 Comparable로 형변환해야 함
```java
class Descending implements Comparator { 
	public int compare(Object o1, Object o2){
		if( o1 instanceof Comparable && o2 instanceof Comparable) {
			Comparable c1 = (Comparable)o1;
			Comparable c2 = (Comparable)o2;
			return c1.compareTo(c2) * -1 ; // -1을 곱해서 정렬방식의 역으로 변경
										   // 또는 c2.compareTo(c1)와 같이 순서를 바꿔도 됨.

		}
		return -1;
	} 
}
```
