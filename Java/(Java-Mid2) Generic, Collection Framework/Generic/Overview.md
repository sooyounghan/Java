-----
### 제네릭이 필요한 이유
-----
1. 대부분의 최신 프로그래밍 언어는 제네릭 (Generic) 개념 제공
2. 제네릭이 필요한 이유
   - IntegerBox (/generic/ex1) : 숫자를 보내고 꺼낼 수 있는 단순한 기능 제공
```java
package generic.ex1;

public class IntegerBox {
    private Integer value;

    public void set(Integer value) {
        this.value = value;
    }

    public Integer get() {
        return value;
    }
}
```

   - StringBox : 문자열을 보관하고 꺼낼 수 있는 단순한 기능 제공
```java
package generic.ex1;

public class StringBox {
    private String value;
    
    public void set(String value) {
        this.value = value;
    }
    
    public String get() {
        return value;
    }
}
```
  
   - BoxMain1
```java
package generic.ex1;

public class BoxMain1 {
    public static void main(String[] args) {
        IntegerBox integerBox = new IntegerBox();
        integerBox.set(10); // Auto-Boxing
        Integer integer = integerBox.get();
        System.out.println("integer = " + integer);

        StringBox stringBox = new StringBox();
        stringBox.set("hello");
        String str = stringBox.get();
        System.out.println("str = " + str);
    }
}
```
  
   - 실행 결과
```
integer = 10
str = hello
```
  - 먼저 숫자를 보관하는 IntegerBox를 생성하고, 숫자 10을 보관하고, 꺼낸 다음에 출력 (참고로 오토 박싱에 의해 int가 Integer 로 자동 변환)
  - 문자열을 보관하는 StringBox를 생성하고 문자열 "hello"를 보관하고, 꺼낸 다음에 출력
   
3. 문제
   - 이후에 Double, Boolean을 포함한 다양한 타입을 담는 박스가 필요하다면 각각의 타입별로 DoubleBox, BooleanBox와 같이 클래스를 새로 만들어야 함
   - 더불어, 담는 타입이 수십개라면, 수십개의 XxxBox 클래스를 만들어야 함
  
-----
### 다형성을 통한 중복 해결 시도
-----
1. Object 타입은 모든 타입의 부모이므로, 다형성(다형적 참조)를 사용해 문제 해결 가능할 것으로 보임
   - ObjectBox : 내부에 Object value를 가지고 있으며, Object는 모든 타입의 부모로, 부모는 자식을 담을 수 있으므로 세상의 모든 타입을 ObjectBox에 보관 가능
```java
package generic.ex1;

public class ObjectBox {
    private Object value;

    public void set(Object object) {
        this.value = object;
    }

    public Object get() {
        return value;
    }
}
```

   - BoxMain2
```java
package generic.ex1;

public class BoxMain2 {
    public static void main(String[] args) {
        ObjectBox integerBox = new ObjectBox();
        integerBox.set(10);
        Integer integer = (Integer) integerBox.get(); // Object -> Integer Casting
        System.out.println("integer = " + integer);

        ObjectBox stringBox = new ObjectBox();
        stringBox.set("hello");
        String str = (String) stringBox.get(); // Object -> String Casting
        System.out.println("str = " + str);

        // 잘못된 타입의 인수 전달 시
        integerBox.set("문자100");
        Integer result = (Integer) integerBox.get(); // String -> Integer Casting Error
        System.out.println("result = " + result);
    }
}
```
  - 실행 결과
```
integer = 10
str = hello
Exception in thread "main" java.lang.ClassCastException: class java.lang.String cannot be cast to class java.lang.Integer (java.lang.String and java.lang.Integer are in module java.base of loader 'bootstrap') at generic.ex1.BoxMain2.main(BoxMain2.java:17)
```

2. 반환 타입이 맞지 않는 문제
   - integerBox를 만들어서 숫자 10을 보관
   - 숫자를 입력하는 부분에는 문제가 없어 보이지만, integerBox.get()을 호출할 때 문제 발생
   - integerBox.get() 의 반환 타입은 Object
```java
Object obj = integerBox.get();
```

   - Integer = Object는 성립하지 않음 (자식은 부모를 담을 수 없음)
   - 따라서 다음과 같이 (Integer) 타입 캐스팅 코드를 넣어서 Object 타입을 Integer 타입으로 직접 다운 캐스팅
```java
Integer integer = (Integer) integerBox.get() // 1
Integer integer = (Integer) (Object)value // 2
Integer integer = (Integer)value // 3
```

   - stringBox의 경우도 마찬가지로, stringBox.get()이 Object를 반환하므로 다음과 같이 다운 캐스팅
```java
String str = (String) stringBox.get()
```

3. 잘못된 타입의 인수 전달 문제
```java
integerBox.set("문자100");
```
  - integerBox에는 변수 이름과 같이 숫자 타입이 입력되기를 기대
  - 하지만 set(Object ...) 메서드는 모든 타입의 부모인 Object를 매개변수로 받기 때문에 어떤 데이터도 입력받을 수 있음
  - 따라서 이렇게 문자열을 입력해도 자바 언어 입장에서는 아무런 문제가 되지 않음
  - 하지만, 잘못된 타입의 값을 전달하면 꺼낼 때 문제 발생
```java
Integer result = (Integer) integerBox.get(); // 1
Integer result = (Integer) "문자100"; // 2
Integer result = (Integer) "문자100"; // 3. 예외 발생 String을 Integer로 캐스팅할 수 없음
```

  - 숫자가 들어가 있을 것으로 예상한 박스에는 문자열이 들어가 있었으므로, 결과적으로 다운 캐스팅시에 String을 Integer 로 캐스팅 할 수 없다는 예외가 발생하고 프로그램이 종료

4. 정리
  - 다형성을 활용한 덕분에 코드의 중복을 제거하고, 기존 코드를 재사용 가능
  - 하지만 입력할 때 실수로 원하지 않는 타입이 들어갈 수 있는 타입 안전성 문제가 발생
      + 예를 들어서 integerBox에는 숫자만 넣어야 하고, stringBox에는 문자열만 입력할 수 있어야 함
      + 하지만 박스에 값을 보관하는 set()의 매개변수가 Object이기 때문에 다른 타입의 값을 입력할 수 있다.
      + 그리고 반환 시점에도 Object를 반환하기 때문에 원하는 타입을 정확하게 받을 수 없고, 항상 위험한 다운 캐스팅을 시도해야 함
      + 결과적으로 이 방식은 타입 안전성이 떨어짐
  
   - 코드 재사용을 늘리기 위해 Object와 다형성을 사용하면 타입 안전성이 떨어지는 문제가 발생
      + BoxMain1 : 각각의 타입별로 IntegerBox, StringBox와 같은 클래스를 모두 정의
        * 코드 재사용 X    
        * 타입 안전성O
          
      + BoxMain2 : ObjectBox를 사용해서 다형성으로 하나의 클래스만 정의
        * 코드 재사용 O
        * 타입 안전성 X
