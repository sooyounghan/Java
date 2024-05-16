-----
### 스트림 (Stream)
-----
1. java.util.Stream Package에 존재
2. 데이터 소스를 추상화하고, 데이터를 다루는데 자주 사용되는 메서드들을 사용
   - 데이터 소스를 추상화 : 데이터 소스가 무엇이던 간에 같은 방식으로 다룰 수 있도록 되었다는 것과 코드의 재사용성이 높아진다는 것을 의미
3. 배열이나 컬렉션뿐만 아니라 파일에 저장된 데이터도 같은 방식으로 다룰 수 있음
4. 예) 문자열 배열과 같은 내용의 문자열을 저장하는 List가 있다고 하자
```java
String[] strArr = { "aaa", "bbb", "ccc" };
List<String> strList = Arrays.toList(strArr);
```

   - 다음과 같이 두 데이터 소스를 기반으로 하는 스트림을 생성 가능
```java
Stream<String> strStream1 = strList.stream(); // 스트림 생성 (Collection)
Stream<String> strStream2 = Arrays.stream(strArr); // 스트림 생성 (Array)
```

   - 이 두 스트림으로부터 데이터 소스의 데이터를 읽어서 정렬하고 화면에 출력하는 방법 (단, 데이터 소스가 정렬되는 것이 아님)
```java
strStream1.sorted().forEach(System.out::println);
strStream2.sorted().forEach(System.out::println);
```

5. 스트림을 사용한 코드가 간결하고 이해하기 쉬우며, 재사용이 높은 걸 알 수 있음

-----
### 스트림의 특징
-----
1. 데이터 소스를 변경하지 않음
   - 데이터 소스로부터 데이터를 읽기만 할 뿐, 데이터 소스를 변경하지 않음
   - 필요하다면, 정렬된 결과를 Collction이나 배열에 담아 반환 가능

```java
// 정렬 결과를 새로운 List에 담아 반환
List<String> sortedList = str2Stream.sorted().collect(Collectors.toList());
```

2. 일회용임
   - Iterator처럼 일회용
   - Iterator로 컬렉션의 요소를 읽고 나면, 다시 사용할 수 없는 것처럼, 스트림 또한 한 번 사용하면 다시 사용할 수 없으며, 다시 생성해야함
```java
strStream1.sorted().forEach(System.out::println);
int numOfStr = strStream1.count(); // Error. 스트림이 이미 닫힘
```

3. 작업을 내부 반복으로 처리
  - 즉, 예를 들어 반복문을 메서드 내부에 숨길 수 있다는 것을 의미
  - 예) forEach() : 매개변수에 대입된 람다식을 데이터 소스의 모든 요소에 적용
```java
stream.forEach(System.out::println);
```
  - forEach()의 실제 코드 (forEach()는 메서드 안으로 for문을 넣은 것)
```java
void forEach(Consumer<? Super T> action) {
    Objects.requireNonNull(action); // 매개변수의 Null 체크

    for(T t : src) {
        action.accept(T); // 내부 반복
    }
}
```

-----
### 스트림의 연산
-----
1. 연산(Operation) : 스트림에 정의된 메서드 중 데이터 소스를 다루는 작업을 수행하는 것
2. 중간 연산과 최종 연산을 나눠짐
```
A. 중간 연산 : 연산 결과가 스트림인 연산. 스트림에 연속해서 중간 연산 가능
B. 최종 연산 : 연산 결과가 스트림이 아닌 연산. 스트림의 요소를 소모하므로 단 한 번만 가능
```
<div align="center">
<img src="https://github.com/sooyounghan/HTTP/assets/34672301/b2ebc1d1-2b12-49a8-b7ff-a1fbd07787b6">
</div>

3. 모든 중간 연산의 결과가 스트림이지만, 연산 전 스트림과 같은 것은 아님
```java
String[] strArr = { "dd", "aaa", "CC", "cc", "b" };
Stream<String> stream = Stream.of(strArr); // 문자열 배열이 소스인 스트림
Stream<String> filteredStream = stream.filter(); // 걸러내기 (중간 연산)
Stream<String> distinctedStream = stream.distinct(); // 중복제거 (중간 연산)
Stream<String> sortedStream = stream.sort(); // 정렬 (중간 연산)
Stream<String> limitedStream = stream.limit(5); // 스트림 자르기 (중간 연산)
int total = stream.count(); // 요소 개수 세기 (최종 연산)
```

4. 스트림의 중간 연산 목록
<div align="center">
<img src="https://github.com/sooyounghan/HTTP/assets/34672301/bff678d5-b001-4bd0-ab9b-4a1f34ba921e">
</div>

5. 스트림의 최종 연산 목록
<div align="center">
<img src="https://github.com/sooyounghan/HTTP/assets/34672301/9e150f71-0495-406d-b18c-baddd69c9690">
</div>

-----
### 지연된 연산
-----
1. 최종 연산이 수행되기 전까지는 중간 연산이 수행되지 않음
2. 즉, 중간 연산을 호출해도 즉각적 연산이 수행되는 것이 아님
3. 중간 연산을 호출하는 것은 단지 어떤 작업이 수행되어야하는지를 지정해주는 것
4. 최종 연산이 수행되어야 비로소 스트림의 요소들이 중간 연산을 거쳐 최종 연산에서 소모

-----
### Stream<Integer>와 IntStream
-----
1. 요소의 타입이 T인 스트림은 기본적으로 ```Stream<T>```이지만, 오토박싱&언박싱으로 인한 비효율을 줄이기 위해 데이터 소스의 요소를 기본형으로 다루는 스트림이 등장
  - IntStream, LongStream, DoubleStream 등 제공
2. 일반적으로 Stream<Integer> 대신 IntStream을 사용하는 것이 효율적
3. IntStream에는 int 타입의 값으로 작업하는데 유용한 메서드들이 포함

-----
### 병렬 스트림
-----
1. 스트림으로 데이터를 다룰 때 장점 중 하나가 바로 병렬 처리가 쉬움
2. 병렬 스트림은 내부적으로 fork&join 프레임워크를 이용해서 자동적으로 연산을 병렬로 수행
3. parallel()이라는 메서드를 호출해 병렬로 연산을 수행하도록 하면 됨
4. 반대로 병렬로 처리되지 않게 하려면 sequential()를 호출하면 됨
5. 스트림은 기본적으로 병렬 스트림이 아니므로 sequential()을 호출할 필요가 없음
6. parallel()와 sequential()은 새로운 스트림을 생성하는 것이 아니라, 그저 스트림의 속성을 변경할 뿐임
```java
int sum = strStream.parallel() // strStream을 병렬 스트림으로 전환
            .mapToInt(s -> s.length())
            .sum();
```
  - 단, 병렬 처리가 항상 더 빠른 결과를 얻게 해주는 것은 아님
