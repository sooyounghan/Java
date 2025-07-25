-----
### toString()
-----
1. Object.toString() 메서드는 객체의 정보를 문자열 형태로 제공
2. 그래서 디버깅과 로깅에 유용하게 사용
3. 이 메서드는 Object 클래스에 정의되므로 모든 클래스에서 상속받아 사용할 수 있음
   - ToStringMain1 (/lang/object/tostring)
```java
package lang.object.tostring;

public class ToStringMai1 {
    public static void main(String[] args) {
        Object object = new Object();
        String string = object.toString();
        
        // toString() 반환값 출력
        System.out.println(string);
        
        // object 직접 출력
        System.out.println(object);
    }
}
```
  - 실행 결과
```
java.lang.Object@b4c966a
java.lang.Object@b4c966a
```

4. Object.toString()
```java
public String toString() {
    return getClass().getName() + "@" + Integer.toHexString(hashCode());
}
```
   - Object가 제공하는 toString() 메서드는 기본적으로 패키지를 포함한 객체의 이름과 객체의 참조값(해시 코드)를 16진수로 제공

5. println()과 toString()
  - 그런데 toString() 의 결과를 출력한 코드와 object를 println()에 직접 출력한 코드의 결과가 완전히 같음
```java
// toString() 반환값 출력
String string = object.toString();
System.out.println(string);

// object 직접 출력
System.out.println(object);
```

   - System.out.println() 메서드는 사실 내부에서 toString()을 호출
   - Object 타입(자식 포함)이 println()에 인수로 전달되면 내부에서 obj.toString() 메서드를 호출해서 결과를 출력
```java
public void println(Object x) {
   String s = String.valueOf(x);
   // ...
}
```
```java
public static String valueOf(Object obj) {
   return (obj == null) ? "null" : obj.toString();
}
```
   - 따라서 println()을 사용할 때, toString()을 직접 호출할 필요 없이 객체를 바로 전달하면 객체의 정보를 출력할 수 있음

6. toString() 오버라이딩
   - Object.toString() 메서드가 클래스 정보와 참조값을 제공하지만 이 정보만으로는 객체의 상태를 적절히 나타내지 못함
   - 보통 toString()을 재정의(오버라이딩)해서 보다 유용한 정보를 제공하는 것이 일반적
<div align="center">
<img src="https://github.com/user-attachments/assets/f71733bf-1d82-4c77-b97a-98aa5e4cc604">
</div>

   - Car
```java
package lang.object.tostring;

public class Car {
    private String carName;

    public Car(String carName) {
        this.carName = carName;
    }
}
```
   - toString()을 재정의하지 않음

   - Dog
```java
package lang.object.tostring;

public class Dog {
    private String dogName;
    private int age;

    public Dog(String dogName, int age) {
        this.dogName = dogName;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "dogName='" + dogName + '\'' +
                ", age=" + age +
                '}';
    }
}
```
   - Dog는 toString()을 재정의
   - toString() 메서드는 IDE의 도움을 받아서 작성하는 것이 매우 편리 (generator 단축키: ⌘ + N (macOS) / Alt + Insert (Windows/Linux))

   - ObjectPrinter
```java
package lang.object.tostring;

public class ObjectPrinter {
    public static void print(Object obj) {
        String string = "객체 정보 출력 : " + obj.toString();
        System.out.println(string);
    }
}
```
  - "객체 정보 출력 : " 이라는 문자와 객체의 toString() 결과를 합해서 출력하는 단순한 기능을 제공

  - toStringMain2
```java
package lang.object.tostring;

public class ToStringMain2 {
    public static void main(String[] args) {
        Car car = new Car("ModelY");
        Dog dog1 = new Dog("멍멍이1", 2);
        Dog dog2 = new Dog("멍멍이2", 5);

        System.out.println("1. 단순 toString 호출");
        System.out.println(car.toString());
        System.out.println(dog1.toString());
        System.out.println(dog2.toString());

        System.out.println("2. println 내부에서 toString 호출");
        System.out.println(car);
        System.out.println(dog1);
        System.out.println(dog2);

        System.out.println("3. Object 다형성 활용");
        ObjectPrinter.print(car);
        ObjectPrinter.print(dog1);
        ObjectPrinter.print(dog2);
    }
}
```
   - 실행 결과
```
1. 단순 toString 호출
lang.object.tostring.Car@4e50df2e
Dog{dogName='멍멍이1', age=2}
Dog{dogName='멍멍이2', age=5}

2. println 내부에서 toString 호출
lang.object.tostring.Car@4e50df2e
Dog{dogName='멍멍이1', age=2}
Dog{dogName='멍멍이2', age=5}

3. Object 다형성 활용
객체 정보 출력 : lang.object.tostring.Car@4e50df2e
객체 정보 출력 : Dog{dogName='멍멍이1', age=2}
객체 정보 출력 : Dog{dogName='멍멍이2', age=5}
```
   - Car 인스턴스는 toString()을 재정의 하지 않았으므로, 따라서 Object가 제공하는 기본 toString() 메서드를 사용
   - Dog 인스턴스는 toString()을 재정의 한 덕분에 객체의 상태를 명확하게 확인할 수 있음

   - ObjectPrinter.print(Object obj) 분석 - Car 인스턴스
<div align="center">
<img src="https://github.com/user-attachments/assets/dd029cea-81b2-437c-924e-3b4717d8af3c">
</div>

```java
ObjectPrinter.print(car) // main에서 호출

void print(Object obj = car(Car)) { // 인수 전달
     String string = "객체 정보 출력: " + obj.toString();
}
```
   - Object obj의 인수로 car(Car)가 전달
   - 메서드 내부에서 obj.toString()을 호출
   - obj는 Object 타입 : 따라서 Object에 있는 toString()을 찾음
   - 이 때, 자식에 재정의(오버라이딩)된 메서드가 있는지 찾아봄 : 재정의된 메서드가 없으므로, Object.toString()을 실행

   - ObjectPrinter.print(Object obj) 분석 - Dog 인스턴스
<div align="center">
<img src="https://github.com/user-attachments/assets/4079fec8-ff70-4639-af2f-04db8b595b2f">
</div>

```java
ObjectPrinter.print(dog) // main에서 호출

void print(Object obj = dog(Dog)) { // 인수 전달
   String string = "객체 정보 출력: " + obj.toString();
}
```
   - Object obj의 인수로 dog(Dog)가 전달
   - 메서드 내부에서 obj.toString()을 호출
   - obj는 Object 타입 : 따라서 Object에 있는 toString()을 찾음
   - 이 때, 자식에 재정의(오버라이딩)된 메서드가 있는지 찾아봄 : Dog에 재정의된 메서드가 있으므로, Dog.toString()을 실행

7. 참고 - 객체의 참조값 직접 출력
   - toString()은 기본으로 객체의 참조값을 출력
   - 그런데 toString()이나 hashCode()를 재정의하면 객체의 참조값을 출력할 수 없음
   - 이 때는, 다음 코드를 사용하면 객체의 참조값을 출력할 수 있음
```java
String refValue = Integer.toHexString(System.identityHashCode(dog1));
System.out.println("refValue = " + refValue);
```
   - 실행 결과
```
refValue = 72ea2f77
```
