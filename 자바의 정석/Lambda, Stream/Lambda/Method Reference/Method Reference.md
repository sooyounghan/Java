---

### 메서드 참조 (Method Reference)

---

1. 람다식이 하나의 메서드만 호출하는 경우에는 메서드 참조 (Method Reference)라는 방법으로 람다식 간략화 가능
2. 예) 문자열을 정수로 반환하는 람다식

```java
Function<String, Integer> f = (String s) -> Integer.parseInt(s);
```

- 이 람다식을 메서드로 표현하면,

```java
Integer wrapper(String s) {
    return Integer.parseInt(s);
}
```

- 다음과 같이 간략화 가능

```java
Function<String, Integer> f = (String s) -> Integer.parseInt(s);

Function<String, Integer> f = Integer::parseInt; // 메서드 참조
```

3. 람다식 일부가 생략되었지만, 컴파일러는 생략된 부분을 우변의 parseInt 메서드의 선언부로부터, 또는 좌변의 Function 인터페이스에 지정된 지네릭 타입으로부터 쉽게 알아낼 수 있음

4. 예) 두 String 문자열이 같은지 확인하는 람다식

```java
BiFunction<String, String, Boolean> f = (s1, s2) -> s1.equals(s2);
```

- 참조변수 f의 타입만 봐도 람다식이 두 개의 String 타입의 매개변수를 받는 다는 것을 알 수 있으므로 람다식의 매개변수들은 없애도 됨
- 메서드 참조로 변경하면,

```java
BiFunction<String, String, Boolean> f = (s1, s2) -> s1.equals(s2);
BiFunction<String, String, Boolean> f = String::equals; // 메서드 참조
```

- 매개변수 s1과 s2를 생략해버리고 나면, equals만 남는데, 두 개의 String을 받아서 Boolean을 반환하는 equals라는 이름의 메서드는 다른 클래스에도 존재 가능
- 따라서, equals 앞에 클래스 이름이 반드시 필요

5. 이미 생성된 객체의 메서드를 람다식에서 사용한 경우에는 클래스 대신 그 객체의 참조변수를 적어줘야 함

```java
MyClass obj = new MyClass();
Function<String, Boolean> f = (x) -> obj.equals(x); // 람다식
Function<String, Boolean> f = obj::equals; // 메서드 참조
```

6. 람다식을 메서드 참조로 변환하는 방법
<div align="center">
<img src="https://github.com/sooyounghan/HTTP/assets/34672301/28bff318-68fe-44d4-8046-2aef8b27a3f2">
</div>

7. 💡 하나의 메서드만 호출하는 람다식 : '클래스이름::메서드이름, 참조변수::메서드이름'으로 변경 가능

---

### 생성자의 메서드 참조

---

1. 생성자를 호출하는 람다식도 메서드 참조로 변경 가능

```java
Supplier<MyClass> s = () -> new MyClass(); // 람다식
Supplier<MyClass> s = MyClass::new; // 메서드 참조
```

2. 매개 변수가 있는 생성자라면, 매개변수 개수에 따라 알맞은 함수형 인터페이스 사용
3. 필요하다면 함수형 인터페이스를 새로 정의해야 함

```java
Function<Integer, MyClass> f = (i) -> new MyClass(i)); // 람다식
Function<Integer, MyClass> f = MyClass::new; // 메서드 참조
```

```java
Function<Integer, String, MyClass> bf = (i, s) -> new MyClass(i, s)); // 람다식
Function<Integer, String, MyClass> bf = MyClass::new; // 메서드 참조
```

4. 배열 생성은 다음과 같음

```java
Function<Integer, int[]> f = x -> new int[x]; // 람다식
Function<Integer, int[]> f = int[]::new; // 람다식
```

5. 메서드 참조는 람다식을 마치 static 변수처럼 다룰 수 있게 해줌

```java
Consumer<T> c = () -> System.out.println(); // 람다식
Consumer<T> c = System.out::println; // 메서드 참조
```
