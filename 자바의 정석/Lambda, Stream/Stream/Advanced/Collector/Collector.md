-----
### Collector 구현
-----
1. 컬렉터(Collector)를 작성한다는 것은 Collector 인터페이스를 구현한다는 것을 의미
2. Collector 인터페이스 정의
```java
pulibc interface Collector(T, A, R) {
    Supplier<A>  supplier();
    BiCounsumer<A, T>   accumulator();
    BinaryOperator<A>   combiner();
    Function<A, R>      finisher();

    Set<Characteristics>  characteristics(); // 컬렉터의 특성이 담긴 Set 반환

    ...
}
```

3. 직접 구현해야하는 것은 위 5개 메서드인데, characteristics()를 제외하면 모두 반환 타입이 함수형 인터페이스 (즉, 4개의 람다식 작성하면 됨)
```java
A. supplier() : 수집 결과(즉, 작업 결과)를 저장할 공간을 제공하기 위한 것
B. accumulator() : 스트림의 요소를 어떻게 supplier()가 제공한 공간에 누적할 것인지, 즉 수집(collect)할 방법을 제공
C. combiner() : 병렬 스트림인 경우, 여러 쓰레드에 의해 처리된 결과를 어떻게 합칠 것인지, 즉, 두 저장공간을 병합할 방법을 제공
D. finisher() : 작업 결과를 변환하는 일을 하는데, 변환이 필요없다면, 항등 함수인 Function.identity()를 반환 (즉, 결과를 최종적으로 변환할 방법 제공)
```
```java
public Function finisher() {
      return Fuction.identity(); // 항등 함수 반환. return x -> x; 와 동일
}
```

4. 마지막으로 characteristics()는 컬렉터가 수행하는 작업의 속성에 대한 정보를 제공하기 위한 것
```java
Characteristics.CONCUREENT // 병렬로 처리할 수 있는 작업
Characteristics.UNORDERED // 스트림의 요소의 순서가 유지될 필요가 없는 작업
Characteristics.IDENTITY_FINISH // finisher()가 항등 함수인 작업
```
  - 위 3가지 속성 중 해당하는 것을 Set에 담아 반환하도록 구현하면 되며, 열거형 Characteristics는 Collector 내에 정의되어 있음
```java
public Set<Characteristics> characteristics() {
    return Collections.unmodifiableSet(EnumSet.of(Collector.Characteristics.CONCURRENT, Collector.Characterstics.UNORDERED));
}
```

  - 아무런 속성도 지정하고 싶지 않다면, 다음과 같이 하면 됨
```java
Set<Characteristics> characteristics() {
    return Collections.emptySet(); // 지정할 특성이 없는 경우 비어있는 Set 반환
}
```

5. Collector도 내부적으로 처리하는 과정이 리듀싱과 동일
6. collect()는 그룹화와 분할, 집계 등에 유용하게 쓰이고, 병렬화에 있어서 reduce()보다 collect()가 유리함

7. 예제 - Stream<String>의 모든 문자열을 결합해서 String으로 반환하는 ConcatCollector 구현
```java
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

class CollectorEx1 {
	public static void main(String[] args) {
		String[] strArr = { "aaa","bbb","ccc" };
		Stream<String> strStream = Stream.of(strArr);

		String result = strStream.collect(new ConcatCollector());

		System.out.println(Arrays.toString(strArr));
		System.out.println("result="+result);
	}
}

class ConcatCollector implements Collector<String, StringBuilder, String> {
	@Override
	public Supplier<StringBuilder> supplier() {
		return () -> new StringBuilder();
//		return StringBuilder::new;
	}

	@Override
	public BiConsumer<StringBuilder, String> accumulator() {
		return (sb, s) -> sb.append(s);
//		return StringBuilder::append;
	}

	@Override
	public Function<StringBuilder, String> finisher() {
		return sb -> sb.toString();
//		return StringBuilder::toString;
	}

	@Override
	public BinaryOperator<StringBuilder> combiner() {
		return (sb, sb2)-> {
			sb.append(sb2);
			return sb;
		};
	}

	@Override
	public Set<Characteristics> characteristics() {
		return Collections.emptySet();
	}
}
```
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/3fa8aab7-7159-4df4-a5ff-fa06cb4f37af">
</div>

