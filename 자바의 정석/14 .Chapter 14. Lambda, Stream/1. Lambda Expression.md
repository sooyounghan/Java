-----
### 람다식 (Lambda Expression)
-----
1. 메서드를 하나의 식(Expression)으로 표현한 것
2. 메서드를 람다식으로 표현하면, 메서드의 이름과 반환값이 없어지므로, 익명 함수 (Anonymous Function)이라고 함
```java
int[] arr = new int[5];
Arrays.setAll(arr, (i) -> (int)(Math.random() * 5) + 1);
```

3. '() -> (int)(Math.random() * 5) + 1' 람다식의 메서드 형태는 다음과 같음
```java
int method() {
    return (int)(Math.random() * 5) + 1;
}
```

4. 모든 메서드들은 클래스에 포함되어야하므로 클래스도 새로 만들어야하고, 객체도 생성해야만 비로소 메서드 호출 가능
5. 그러나 람다식은 이 모든 과정 없이 오직 람다식 자체만으로도 이 메서드의 역할 수행 가능
6. 또한, 람다식은 메서드의 매개변수로 전달되어지는 것이 가능하고, 메서드의 결과로 반환될 수 있음. 즉, 변수처럼 다루는 것 가능

-----
### 람다식 작성하기
-----
1. 익명 함수이므로, 메서드에서 이름과 반환 타입을 제거하고 매개변수 선언부와 몸통 { } 사이에 '->'를 추가
```java
반환타입 메서드이름(매개변수 선언) {
    문장들
}
```
```java
(매개변수 선언) -> {
    문장들
}
```

2. 예를 들어, 두 값 중 큰 값을 반환하는 메서드 max를 람다식으로 변경하면 다음과 같음
```java
int max(int a, int b) {
    return a > b ? a : b;
}
```
```java
(int a, int b) -> {
    return a > b ? a : b;
}
```

3. 반환값이 있는 메서드의 경우, return 대신 식(expression)으로 대신 가능하며, 식의 연산결과가 자동적으로 반환값이 됨
   - 대신, 이 떄는 문장이 아닌 '식'이므로 끝에 ';'를 붙이지 않음
```java
(int a, int b) -> { return a > b ? a : b; }
```
```java
(int a, int b) -> a > b ? a : b
```

4. 람다식에 선언된 매개변수 타입은 추론이 가능한 경우는 생략 가능
   - 반환타입이 없는 이유도 추론이 가능하기 때문임
```java
(int a, int b) -> a > b ? a : b
```
```java
(a, b) -> a > b ? a : b
```
  - 단, 두 매개변수 중 어느 하나의 타입만 생략하는 것은 허용되지 않음

5. 선언된 매개변수가 하나뿐인 경우에는 괄호() 생략 가능
   - 단, 매개변수 타입이 있으면 괄호 () 생략 불가
```java
(a) -> a * a
(int a) -> a * a
```
```java
a -> a * a // OK
int a -> a * a // Error
```

6. 괄호 { } 안 문장이 하나일 때 괄호 { } 생략 가능하지만, 대신 ';'을 붙이지 않아야 함
```java
(String name, int i) -> {
    System.out.println(name + " = " + i);
}
```
```java
(String name, int i) -> System.out.println(name + " = " + i)
```

7. 그러나 괄호 { } 안의 문장이 return문이면, 괄호 { } 생략 불가
```java
(int a, int b) -> { return a > b ? a : b; } // Ok
(int a, int b) -> return a > b ? a : b // Error
```

8. 람다식 예시
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/fe0d092a-bbe7-4d30-bf14-edac1ecd0ca8">
</div>

