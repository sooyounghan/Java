-----
### 메서드 체인닝 - Method Chaining
-----
1. ValueAdder (/lang/string/chaining)
```java
package lang.string.chaining;

public class ValueAdder {
    private int value;

    public ValueAdder add(int addValue) {
        value += addValue;
        return this;
    }
    
    public int getValue() {
        return value;
    }
}
```
   - 단순히 값을 누적해서 더하는 기능을 제공하는 클래스
   - add() 메서드를 호출할 때 마다 내부의 value에 값을 누적
   - add() 메서드를 보면 자기 자신(this)의 참조값을 반환

2. MethodChainingMain1
```java
package lang.string.chaining;

public class MethodChainingMain1 {
    public static void main(String[] args) {
        ValueAdder adder = new ValueAdder();
        
        adder.add(1);
        adder.add(2);
        adder.add(3);
        
        int result = adder.getValue();
        System.out.println("result = " + result);
    }
}
```
   - 실행 결과
```
result = 6
```
   - add() 메서드를 여러번 호출해서 값을 누적해서 더하고 출력
   - 여기서는 add() 메서드의 반환값은 사용하지 않음

3. MethodChainingMain2
```java
package lang.string.chaining;

public class MethodChainingMain2 {
    public static void main(String[] args) {
        ValueAdder adder = new ValueAdder();

        ValueAdder adder1 = adder.add(1);
        ValueAdder adder2 = adder.add(2);
        ValueAdder adder3 = adder.add(3);
        
        int result = adder3.getValue();

        System.out.println("result = " + result);
    }
}
```
   - 실행 결과
```
result = 6
```

<div align="center">
<img src="https://github.com/user-attachments/assets/4985db85-35a5-461b-bf2e-403cfa3b4eba">
</div>

   - adder.add(1)을 호출
   - add() 메서드는 결과를 누적하고 자기 자신의 참조값인 this (x001)를 반환
   - adder1 변수는 adder와 같은 x001 인스턴스를 참조

<div align="center">
<img src="https://github.com/user-attachments/assets/7d259498-0153-4381-b9d5-d25d71628fd1">
</div>

   - add() 메서드는 자기 자신( this )의 참조값을 반환 : 이 반환값을 adder1, adder2, adder3에 보관
   - 따라서 adder, adder1, adder2, adder3 은 모두 같은 참조값을 사용
   - 왜냐하면 add() 메서드가 자기 자신(this)의 참조값을 반환

4. MethodChainingMain3
```java
package lang.string.chaining;

public class MethodChainingMain3 {
    public static void main(String[] args) {
        ValueAdder adder = new ValueAdder();

        int result = adder.add(1).add(2).add(3).getValue();

        System.out.println("result = " + result);
    }
}
```
   - 실행 결과
```
result = 6
```

  - 실행 순서
     + add() 메서드를 호출하면 ValueAdder 인스턴스 자신의 참조값(x001)이 반환
     + 이 반환된 참조값을 변수에 담아두지 않아도 됨
     + 대신에 반환된 참조값을 즉시 사용해서 바로 메서드를 호출할 수 있음
     + 다음과 같은 순서로 실행된다.
```java
adder.add(1).add(2).add(3).getValue() // value = 0

x001.add(1).add(2).add(3).getValue() // value = 0, x001.add(1)을 호출하면 그 결과로 x001을 반환

x001.add(2).add(3).getValue() // value = 1, x001.add(2)을 호출하면 그 결과로 x001을 반환

x001.add(3).getValue() // value = 3, x001.add(3)을 호출하면 그 결과로 x001을 반환

x001.getValue() // value = 6

6
```
   - 메서드 호출의 결과로 자기 자신의 참조값을 반환하면, 반환된 참조값을 사용해서 메서드 호출을 계속 이어갈 수 있음
   - 코드를 보면 . 을 찍고 메서드를 계속 연결해서 사용 : 마치 메서드가 체인으로 연결된 것 처럼 보이는데, 이러한 기법을 메서드 체이닝
   - 물론 실행 결과도 기존과 동일
   - 기존에는 메서드를 호출할 때 마다 계속 변수명에 . 을 찍어야 했음 (예) adder.add(1) , adder.add(2))
   - 메서드 체이닝 방식은 메서드가 끝나는 시점에 바로 . 을 찍어서 변수명을 생략할 수 있음
   - 메서드 체이닝이 가능한 이유는 자기 자신의 참조값을 반환하기 때문임
   - 이 참조값에 . 을 찍어서 바로 자신의 메서드를 호출할 수 있음
   - 메서드 체이닝 기법은 코드를 간결하고 읽기 쉽게 만들어줌

5. StringBuilder와 메서드 체인(Chain)
   - StringBuilder는 메서드 체이닝 기법을 제공
   - StringBuilder의 append() 메서드를 보면 자기 자신의 참조값을 반환
```java
public StringBuilder append(String str) {
     super.append(str);
     return this;
}
```
  - StringBuilder에서 문자열을 변경하는 대부분의 메서드도 메서드 체이닝 기법을 제공하기 위해 자기 자신을 반환 (예) insert(), delete(), reverse())
  - StringBuilderMain1_2
```java
package lang.string.chaining;

public class StringBuilderMain1_2 {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();

        String string = sb.append("A").append("B").append("C").append("D")
                .insert(4, "Java")
                .delete(4, 8)
                .reverse()
                .toString();

        System.out.println("String = " + string);
    }
}
```
  - 실행 결과
```
String = DCBA
```

  - 참고로 자바의 라이브러리와 오픈 소스들은 메서드 체이닝 방식을 종종 사용
