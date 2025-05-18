-----
### 지네릭스 (Generics)
-----
1. 다양한 타입의 객체들을 다루는 메서드나 컬렉션 클래스에 컴파일 시의 타입을 체크 (Complie-time Type Check)를 해주는 기능
2. 객체의 타입을 컴파일 시에 체크하기 때문에 객체의 타입 안정성을 높이고 형 변환의 번거로움이 줄어듬
3. 지네릭스의 장점
```
A. 타입 안정성을 제공
  - 의도하지 않은 타입의 객체가 저장된 것을 막음
  - 저장된 객체를 꺼내올 때 원래의 타입과 다른 타입으로 잘못 형변환이 발생할 수 있는 오류를 줄여줌
B. 타입 체크와 형변환을 생략할 수 있으므로 코드가 간결해짐
```

----- 
### 지네릭 클래스 선언
-----
1. 지네릭 타입은 클래스에 메서드에 선언 가능
2. 예를 들어, 다음과 같이 클래스 Box가 다음과 같이 정의되어 있다고 가정
```java
class Box {
    Object item;

    void setItem(Obejct item) { this.item = item; }
    Object getItem() { return item; }
}
```

3. 이 클래스를 지네릭 클래스로 변경하면, 클래스 옆에 <T>를 붙여주면 되며, Object를 모두 T로 변경
```java
class Box<T> {
    T item;

    void setItem(T item) { this.item = item; }
    T getItem() { return item; }
}
```

4. Box<T>에서 T : 타입 변수 (Type Variable)
  - Type의 첫 글자를 따온 것
  - 타입 변수는 T가 아닌 다른 것을 사용해도 됨
  - 예) ArrayList<E>의 경우, 타입 변수 E는 Element(요소)의 첫 글자를 따서 사용

5. 타입 변수가 여러 개인 경우에는 Map<K, V>와 같이 콤마(,)를 구분자로 나열
   - K는 Key(키)를 의미, V는 Value(값)을 의미

6. 상황에 맞게 의미있는 문자를 선택해서 사용하는 것이 좋음
7. 이처럼 기호의 종류만 다를 뿐, '임의의 참조형 타입'을 의미
8. 따라서, 지네릭 클래스가 된 Box클래스의 객체를 생성할 때는 다음과 같이 참조변수와 생성자에 타입 T대신 사용될 실제 타입 지정
```java
Box<String> b = new Box<String>(); // 타입 T 대신, 실제 타입을 지정
b.setItem(new Object()); // Error. String 이외의 타입 지정 불가
b.setItem("ABC"); // Ok. String 타입이므로 가능
String item = b.getItem(); // 형변환이 필요 없음
```

  - 위 코드에서 T대신 Stirng 타입을 지정했으므로, 지네릭 클래스 Box<T>는 다음과 같이 변경
```java
class Box<String> {
    String item;

    void setItem(String item) { this.item = item; }
    String getItem() { return item; }
}
```

9. 지네릭 클래스인데도 예전 방식으로 객체 생성하는 것은 허용하지만, 지네릭 타입을 지정하지 않아 안전하지 않다는 경고 발생
```java
Box b = new Box(); // OK. T는 Object 타입으로 간주
b.setItem("ABC"); // 경고. Unchecked or unsafe operation
b.setItem(new Object()); // 경고. Unchecked or unsafe operation
```

10. 또한, 타입 변수 T에 Object 타입을 지정하면, 타입을 지정하지 않은 것이 아니라 알고 적은 것이므로 경고는 발생하지 않음
```java
Box<Object> b = new Box<Object>(); 
b.setItem("ABC"); // 경고 발생 안함
b.setItem(new Object()); // 경고 발생 안함
```
11. 지네릭스가 도입되기 전 이전 코드와 호환성을 유지하기 위해 지네릭스를 사용하지 않은 코드를 허용하는 것일 뿐, 지네릭 클래스를 사용할 때는 반드시 타입을 지정해서 지네릭스 관련된 경고가 나오지 않도록 하는 것이 좋음

----- 
### 지네릭스 용어
-----
<div align="center">
<img src="https://github.com/sooyounghan/HTTP/assets/34672301/4f98d36e-be9e-4510-a7d1-777abb04c6c1">
</div>

1. 타입 문자 T는 지네릭 클래스의 ```Box<T>```의 타입 변수 또는 타입 매개변수라고 하는데, 메서드의 매개변수와 유사한 면이 있음
2. 따라서, 타입 매개변수에 타입을 지정하는 것을 '지네릭 타입 호출'이라고 함
   - 지정된 타입 'String'을 '매개변수화된 타입(Parameterized Type)' 또는 '대입된 타입'
3. 예를 들어, ```Box<String>```과 ```Box<Integer>```는 지네릭 클래스 Box<T>에 서로 다른 타입을 대입하여 호출한 것일 뿐, 이 둘이 별개의 클래스를 의미하는 것이 아님
4. 컴파일 후에는 ```Box<String>```과 ```Box<Integer>```는 이들의 원시 타입인 Box로 바뀜. 즉, 지네릭 타입이 제거됨


----- 
### 지네릭스의 제한
-----
1. 지네릭 클래스의 Box의 객체를 생성할 때, 객체별로 다른 타입을 지정하는 것은 적절함
```java
Box<Apple> appleBox = new Box<Apple>(); // Ok. Apple 객체만 저장 가능
Box<Grape> appleBox = new Box<Grape>(); // Ok. Grape 객체만 저장 가능
```

2. 그러나 모든 객체에 대해 동일하게 동작해야하는 static 멤버의 경우 타입 변수 T를 사용할 수 없음.
   - T는 인스턴스 변수로 간주
   - static 멤버는 인스턴스 변수를 참조할 수 없음
```java
class Box<T> {
    static T item; // 에러
    static int compare(T t1, T t2) { ... } // 에러
    ...
}
```

3. static 멤버는 타입 변수에 지정된 타입, 즉 대입된 타입 종류에 관계 없이 동일한 것이어야 함
   - 즉, ```Box< Apple>.item```과 ```Box<Grape>.item```이 다른 것이어서는 안 됨

4. 또한, 지네릭 타입의 배열을 생성하는 것도 허용되지 않음
   - 지네릭 배열 타입의 참조변수를 선언하는 것은 가능하지만, new T[10]와 같이 배열을 생성하는 것은 안 됨
```java
class Box<T> {
    T[] itemArr; // Ok. T타입의 배열을 위한 참조변수

    ...

    T[] toArray() {
        T[] tmpArr = new T[itemArr.length]; // Error. 지네릭 배열 생성 불가
        ...
        return tmpArr;
    }
    ...
}
```
   - 지네릭 배열을 생성할 수 없는 것은 new 연산자 때문임
   - new 연산자는 컴파일 시점에 타입 T가 무엇인지 명확하게 알아야 하는데, 위 코드에서 컴파일을 하는 시점에는 T가 어떤 타입인지 명확히 알 수 없음
   - instanceof 연산자도 new 연산자와 같은 이유로 T를 피연산자로 사용할 수 없음

5. 지네릭 배열을 생성할 필요가 있을 때는, new 연산자 대신 'Reflection API'의 newInstance()와 같이 동적으로 객체를 생성하는 메서드로 배열을 생성하거나, Object 배열을 생성해 복사한 다음 T[]로 형변환하는 방법 등 사용

----- 
### 지네릭 클래스의 객체 생성과 사용
-----
1. 지네릭 클래스 Box<T>가 다음과 같이 정의되어 있다고 가정 (Box<T> 객체에는 한 가지 종류, 즉 T타입 객체만 저장 가능)
```java
class Box<T> {
    ArrayList<T> list = new ArrayList<T>();

    void add(T item) { list.add(item); }
    T get(int i) { return list.get(i); }
    ArrayList<T> getList() { return list; }
    int size() { return list.size(); }
    public String toString() { return list.toString(); }
}
```

2. Box<T>의 객체를 생성할 때는 다음과 같이, 참조 변수와 생성자에 대입된 타입(매개변수화된 타입)이 일치해야 하며, 일치하지 않으면 에러 발생
```java
Box<Apple> appleBox = new Box<Apple>(); // Ok
Box<Apple> appleBox = new Box<Grape>(); // Error
```

3. 두 타입이 상속 관계에 있다고 해당 동일 (Apple이 Fruit의 자손이라고 가정)
```java
Box<Fruit> appleBox = new Box<Grape>(); // Error
```

4. 단, 두 지네릭 클래스의 타입이 상속 관계에 있고, 대입된 타입이 같은 것은 괜찮음 (FruitBox은 Box의 자손)
```java
Box<Apple> appleBox = new FruitBox<Apple>(); // Ok. 다형성
```

5. JDK1.7부터는 추정이 가능한 경우, 타입 생략 가능
   - 참조변수의 타입으로부터 Box가 Apple 타입의 객체만 저장한다는 것을 알 수 있기 때문에, 생성자에 반복해서 타입을 지정해주지 않아도 됨
```java
Box<Apple> appleBox = new Box<Apple>();
Box<Apple> appleBox = new Box<>(); // JDK1.7부터 생략 가능
```

6. 생성된 Box<T>의 객체에 void add(T item)으로 객체를 추가할 때, 대입된 타입과 다른 타입의 객체 추가 불가
```java
Box<Apple> appleBox = new Box<Apple>();
appleBox.add(new Apple()); // Ok.
appleBox.add(new Grape()); // Error. Box<Apple>에는 Apple 객체만 추가 가능
```

7. 그러나, 타입 T가 'Fruit'인 경우, void add(Fruit item)이 되므로 Fruit 타입의 자손들은 이 메서드의 매개변수 가능 (Apple은 Fruit의 자손이라고 가정)
```java
Box<Fruit> fruitBox = new Box<Fruit>();
fruitBox.add(new Fruit()); // Ok.
fruitBox.add(new Apple()); // Ok. void add(Fruit item)
```

```java
import java.util.ArrayList;

class Fruit				  { public String toString() { return "Fruit";}}
class Apple extends Fruit { public String toString() { return "Apple";}}
class Grape extends Fruit { public String toString() { return "Grape";}}
class Toy		          { public String toString() { return "Toy"  ;}}

class FruitBoxEx1 {
	public static void main(String[] args) {
		Box<Fruit> fruitBox = new Box<Fruit>();
		Box<Apple> appleBox = new Box<Apple>();
		Box<Toy>   toyBox   = new Box<Toy>();
//		Box<Grape> grapeBox = new Box<Apple>(); // Error. 타입 불일치

		fruitBox.add(new Fruit());
		fruitBox.add(new Apple()); // OK. void add(Fruit item)

		appleBox.add(new Apple());
		appleBox.add(new Apple());
//		appleBox.add(new Toy()); // Error. Box<Apple>에는 Apple만 담을 수 있음

		toyBox.add(new Toy());
//		toyBox.add(new Apple()); // Error. Box<Toy>에는 Apple을 담을 수 없음

		System.out.println(fruitBox);
		System.out.println(appleBox);
		System.out.println(toyBox);
	}  // main
}

class Box<T> {
	ArrayList<T> list = new ArrayList<T>();
	void add(T item)  { list.add(item); }
	T get(int i)      { return list.get(i); }
	int size() { return list.size(); }
	public String toString() { return list.toString();}
}
```
<div align="center">
<img src="https://github.com/sooyounghan/HTTP/assets/34672301/748c4d4f-583f-4905-840c-62a3169e5928">
</div>

----- 
### 제한된 지네릭 클래스
-----
1. 타입 매개변수 T에 지정할 수 있는 타입의 종류를 제한하는 방법 ?
```java
FruitBox<Toy> fruitBox = new FruitBox<Toy>();
fruitBox.add(new Toy()); // Ok. 과일 상자에 장난감을 담을 수 있음
```

2. 다음과 같이 지네릭 타입에 'extends'를 사용하면, 특정 타입 자손들만 대입할 수 있게 제한 가능
```java
class FruitBox<T extends Fruit> { // Fruit의 자손만 타입으로 지정 가능
    ArrayList<T> list = new ArrayList<T>();
    ...
}
```

3. 여전히 한 종류의 타입만 담을 수 있지만, Fruit 클래스의 자손들만 담을 수 있다는 제한이 더 추가된 것
```java
FruitBox<Apple> appleBox = new FruitBox<Apple>(); // Ok
FruitBox<Toy> toyBox = new FruitBox<Toy>(); // Error. Toy는 Fruit의 자손이 아님
```

4. 게다가 add()의 매개변수의 타입 T도 Fruit와 그 자손 타입이 될 수 있으므로, 다음과 같이 가능
```java
FruitBox<Fruit> fruitBox = new FruitBox<Fruit>();
fruitBox.add(new Apple()); // Ok. Apple이 Fruit의 자손
fruitBox.add(new Grape()); // Ok. Grape이 Fruit의 자손
```

5. 다형성에서 조상 타입의 참조변수로 자손 타입의 객체를 가리킬 수 있는 것 처럼, 매개변수화된 타입의 자손 타입도 가능함
   - 타입 매개변수 T에 Object를 대입하면, 모든 종류의 개체를 저장할 수 있게 됨

6. 만일, 클래스가 아니라 인터페이스를 구현해야 한다는 제약이 필요하다면, 동일하게 extends 사용 (implements 사용하지 않음)
```java
interface Eatable { }
class FruitBox<T extends Eatable> { ... }
```

7. 만약, 클래스 Fruit의 자손이면서 Eatable 인터페이스도 구현해야한다면, 아래와 같이 '&' 기호 연결
```java
class FruitBox<T extends Fruit & Eatable> { ... }
```
  - FruitBox에는 Fruit 자손이면서 Eatable을 구현한 클래스만 타입 매개변수 T에 대입 가능

```java
import java.util.ArrayList;

class Fruit implements Eatable {
	public String toString() { return "Fruit";}
}
class Apple extends Fruit { public String toString() { return "Apple";}}
class Grape extends Fruit { public String toString() { return "Grape";}}
class Toy		          { public String toString() { return "Toy"  ;}}

interface Eatable {}

class FruitBoxEx2 {
	public static void main(String[] args) {
		FruitBox<Fruit> fruitBox = new FruitBox<Fruit>();
		FruitBox<Apple> appleBox = new FruitBox<Apple>();
		FruitBox<Grape> grapeBox = new FruitBox<Grape>();
//		FruitBox<Grape> grapeBox = new FruitBox<Apple>(); // Error. 타입 불일치
//		FruitBox<Toy>   toyBox    = new FruitBox<Toy>();   // Error.

		fruitBox.add(new Fruit());
		fruitBox.add(new Apple());
		fruitBox.add(new Grape());
		appleBox.add(new Apple());
//		appleBox.add(new Grape());  // Error. Grape은 Apple의 자손이 아님
		grapeBox.add(new Grape());

		System.out.println("fruitBox-"+fruitBox);
		System.out.println("appleBox-"+appleBox);
		System.out.println("grapeBox-"+grapeBox);
	}  // main
}

class FruitBox<T extends Fruit & Eatable> extends Box<T> {}

class Box<T> {
	ArrayList<T> list = new ArrayList<T>();
	void add(T item)  { list.add(item);      }
	T get(int i)      { return list.get(i); }
	int size()        { return list.size();  }
	public String toString() { return list.toString();}
}
```
<div align="center">
<img src="https://github.com/sooyounghan/HTTP/assets/34672301/9e48bf07-70b3-4238-ac46-8607bcd81114">
</div>



