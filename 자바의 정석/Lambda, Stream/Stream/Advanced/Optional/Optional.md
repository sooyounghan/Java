-----
### Optional<T> (java.util.Optional) - JDK1.8부터 추가
-----
1. 지네릭 클래스로 'T'타입의 객체를 감싸는 래퍼 클래스
2. 따라서, Optional 타입 객체는 모든 타입의 참조변수를 담을 수 있음
```java
public final class Optional<T> {
    private final T value; // T타입 참조변수
    ...
}
```

3. 최종 연산의 결과를 그냥 반환하는 것이 아니라 Optional 객체에 담아서 반환
4. 이처럼 객체에 담아서 반환하면, 반환된 결과가 null인지 매번 if문으로 체크하는 대신 Optional에 정의된 메서드를 통해 간단히 처리 가능

-----
### Optional 객체 생성
-----
1. of() 또는 ofNullable()을 사용
```java
String str = "abc";
Optional<String> optVal = Optional.of(str);
Optional<String> optVal = Optional.of("abc");
Optional<String> optVal = Optional.of(new String("abc"));
```

2. 만일 참조변수의 값이 null일 가능성이 있다면, of() 대신 ofNullable()을 사용해야 함
3. of()는 매개변수의 값이 null이면 NullPointerException 발생
```java
Optional<String> optVal = Optional.of(null); // NullPointerException 발생
Optional<String> optVal = Optional.ofNullable(null); // Ok
```

4. ```Optional<T>``` 타입의 참조변수를 기본값으로 초기화할 때는 empty()를 사용
   - null로 초기화하는 것이 가능하지만, empty()로 초기화하는 것이 바람직
   - empty()는 지네릭 메서드이므로 앞에 ```<T>```를 붙일 수 있음. 그러나 추정 가능하므로 생략 가능
```java
Optional<String> optVal = null; // Null로 초기화
Optional<String> optVal = Optional.<String>empty(); // 빈 객체로 초기화
```

-----
### Optional 객체의 값 가져오기
-----
1. Optional 객체에 저장된 값을 가져올 때는 get()을 사용
   - 값이 null 일떄는 NoSuchElementException 발생
2. 값이 null일 때를 대비해 orElse()로 대체할 값을 지정 가능
```java
Optional<String> optVal = Optional.of("abc");
String str1 = optVal.get(); // optVal에 저장된 값 반환. null이면 에러 발생
String str2 = optVal.orElse(""); // optVal에 저장된 값이 null일 때는, "" 반환
```

3. orElse()의 변형으로 null을 대체할 값을 반환하는 람다식을 지정할 수 있는 orElseGet()과 null일 떄 지정된 예외를 발생시키는 orElseThrow() 존재
```java
T orElseGet(Supplier<? extends T> other)
T orElseThrow(Supplier? extends X> exceptionSupplier)
```
```java
String str3 = optVal.orElseGet(String::new); // () -> new String()와 동일
String str4 = optVal.orElseThrow(NullPointerException::new) // null이면 예외 발생
```

4. Stream처럼 Optional 객체에도 filter(), map(), flatMap() 사용 가능
   - map()의 연산결과가 ```Optional<Optional<T>>```일 때, flatMap()을 사용하면 Optional<T>를 결과로 얻음
   - 만약, Optional 객체의 값이 null이면, 이 메서드들은 아무 일도 하지 않음
```java
int result = Optional.of("123")
              .filter(x -> x.length() > 0)
              .map(Integer::parseInt).orElse(-1); // result = 123
```
```java
int result = Optional.of("")
              .filter(x -> x.length() > 0)
              .map(Integer::parseInt).orElse(-1); // result = -1
```

  - parseInt()는 예외가 발생하기 쉬운 메서드이므로 예외처리 메서드를 만들면 다음과 같음
```java
static int optStrToInt(Optionak<String> optStr, int defaultValue) {
    try {
        return optStr.map(Integer::parseInt).get();
    } catch(Exception e) {
        return defaultValue;
    }
}
```

5. isPresent()는 Optional 객체의 값이 null이면 false, 아니면 true 반환
6. ifPresent(```Consumer<T> block```)은 값이 있으면 주어진 람다식 실행하고, 없으면 아무 일도 하지 않음
```java
if(str != null) {
    System.out.println(str);
}
```
```java
if(Optional.ofNullable(str).isPresent()) {
    System.out.println(str);
}
```
  - 이 코드를 ifPresent()를 이용해 바꾸면 다음과 같음
```java
Optional.ofNullable(str).ifPresent(System.out::println);
```

7. Stream 클래스에 정의된 메서드 중에서 ```Optional<T>```를 반환하는 메서드는 다음과 같음
```java
Optional<T> findAny();
Optional<T> findFirst();
Optional<T> max(Comparator<? super T> comparator)
Optional<T> min(Comparator<? super T> comparator)
Optional<T> reduce(BinaryOperator<T> accmulator)
```

-----
### OptionalInt, OptionalLong, OptionalDouble
-----
1. 기본형 스트림에는 Optional도 기본형을 값으로 하는 OptionalInt, OptionalLong, OptionalDouble을 반환
2. 예) IntStream에 정의된 메서드
```java
OptionalInt findAny()
OptionalInt findFirst()
OptionalInt reduce(IntBinaryOperator op)
OptionalInt max()
OptionalInt min()
OptionalDouble average()
```

3. Optional 클래스와 값을 반환하는 메서드
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/a262ea1b-7b12-4050-9e48-4b713f8142e5">
</div>

4. OptionalInt의 실제 내부 코드
```java
public final class OptionalInt {
    ...
    private final boolean isPresent; // 값이 저장되어 있으면 true
    private final int value; // int 타입 변수
    ...
}
```

5. 기본형 int의 기본값은 0이므로 아무런 값도 갖지 않는 OptionalInt에 저장되는 값은 0이면, 두 Optional은 같은 값인가?
```java
OptionalInt opt = OptioanlInt.of(0); // OptionalInt에 0을 저장
OptionalInt opt2 = OptioanlInt.empty(); // OptionalInt에 0을 저장
```
  - 저장된 값이 없는 겂과 0이 저장된 것은 isPresent라는 인스턴스 변수로 구분 가능
```java
System.out.println(opt.isPresent()); // true
System.out.println(opt2.isPresent()); // false

System.out.println(opt.getAsInt()); // 0
System.out.println(opt2.getAsInt()); // NoSuchElementException 예외 발생

System.out.println(opt.equals(opt2)); // false
```

  - 그러나 Optional 객체에 null을 저장하면, 비어있는 것과 동일하게 취급
```java
Optional<String> opt = Optional.ofNullable(null);
Optional<String> opt2 = Optional.empty();

System.out.println(opt.equals(opt2)); // true
```

```java
import java.util.*;
import java.util.stream.*;

class OptionalEx1 {
	public static void main(String[] args) {
		Optional<String>  optStr = Optional.of("abcde");
		Optional<Integer> optInt = optStr.map(String::length);
		System.out.println("optStr="+optStr.get());
		System.out.println("optInt="+optInt.get());

		int result1 = Optional.of("123")
				.filter(x->x.length() >0)
				.map(Integer::parseInt).get();

		int result2 = Optional.of("")
				.filter(x->x.length() >0)
				.map(Integer::parseInt).orElse(-1);

		System.out.println("result1="+result1);
		System.out.println("result2="+result2);

		Optional.of("456").map(Integer::parseInt)
				  .ifPresent(x->System.out.printf("result3=%d%n",x));

		OptionalInt optInt1  = OptionalInt.of(0);   // 0을 저장
		OptionalInt optInt2  = OptionalInt.empty(); // 빈 객체를 생성

		System.out.println(optInt1.isPresent());   // true
		System.out.println(optInt2.isPresent());   // false
		
		System.out.println(optInt1.getAsInt());   // 0
	//	System.out.println(optInt2.getAsInt());   // NoSuchElementException
		System.out.println("optInt1 ="+optInt1);
		System.out.println("optInt2="+optInt2);
	     	System.out.println("optInt1.equals(optInt2)?"+optInt1.equals(optInt2));
	
		Optional<String> opt  = Optional.ofNullable(null); // null 저장
		Optional<String> opt2 = Optional.empty();          // 빈 객체를 생성
		System.out.println("opt ="+opt);
		System.out.println("opt2="+opt2);
		System.out.println("opt.equals(opt2)?"+opt.equals(opt2)); // true

		int result3 = optStrToInt(Optional.of("123"), 0);
		int result4 = optStrToInt(Optional.of(""), 0);

		System.out.println("result3="+result3);
		System.out.println("result4="+result4);
	}

	static int optStrToInt(Optional<String> optStr, int defaultValue) {
		try {
			return optStr.map(Integer::parseInt).get();
		} catch (Exception e){
			return defaultValue;
		}			
	}
}
```
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/ff66c407-76c6-46e7-a70c-74e5a0c23991">
</div>
