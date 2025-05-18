-----
### 와일드 카드
-----
1. 매개변수에 과일박스를 대입하면 주스를 만들어서 반환하는 Juicer라는 클래스가 있으며, 이 클래스에는 과일을 주스로 만들어서 반환하는 makeJuice()라는 static 메서드 존재한다고 가정
```java
class Juicer {
    static Juice makeJuice(FruitBox<Fruit> box) { // <Fruit>으로 지정
        String tmp = "";
        for(Fruit f : box.getList()) tmp += f + " ";
        return new Juice(tmp);
    }
}
```

2. Juicer 클래스는 지네릭 클래스가 아닌데다, 지네릭 클래스라해도 static 메서드에는 타입 매개변수 T를 매개변수로 사용할 수 없음
```java
FruitBox<Fruit> fruitBox = new FruitBox<Fruit>();
FruitBox<Apple> appleBox = new FruitBox<Apple>();

...

System.out.println(Juicer.makeJuice(fruitBox)); // Ok. FruitBox<Fruit>
System.out.println(Juicer.makeJuice(appleBox)); // Error. FruitBox<Apple>
```

3. 이렇게 지네릭 타입을 '```FruitBox<Fruit>```'으로 고정해 놓으면, ```FruitBox<Apple>``` 타입의 객체는 makeJuice()의 매개변수가 될 수 없으므로, 다음과 같이 여러 가지 타입의 매개변수를 갖는 makeJuice()를 만들 수 밖에 없음
```java
static Juice makeJuice(FruitBox<Fruit> box) {
    String tmp = "";
    for(Fruit f : box.getList()) tmp += f + " ";
    return new Juice(tmp);
}

static Juice makeJuice(FruitBox<Apple> box) {
    String tmp = "";
    for(Fruit f : box.getList()) tmp += f + " ";
    return new Juice(tmp);
}
```

4. 하지만, 위와 같이 오버로딩 하면, 컴파일 에러가 발생
   - 💡 지네릭 타입이 다른 것만으로는 오버로딩이 성립하지 않음
   - 지네릭 타입은 컴파일러가 컴파일 할 때만 사용하고 제거해버림. 따라서, 메서드 중복 정의가 되어버림

5. 와일드 카드는 이러한 문제를 해결하기 위해 등장 (기호 ?로 표현하며, 와일드 카드는 어떠한 타입도 가능)

-----
### 와일드 카드의 제한
-----
1. ? 만으로는 Object 타입과 다를게 없으므로, extends와 super로 상한(Upper Bound)과 하한(Lower Bound)을 제한할 수 있음
```
<? extends T> : 와일드 카드의 상한 제한. T와 그 자손들만 가능
<? super T> : 와일드 카드의 하한 제한. T와 그 조상들만 가능
<?> : 제한 없음. 모든 타입이 가능, 즉 <? extends Object>와 동일
```
  - 단, 와일드 카드에는 & 사용 불가 (즉, <? extends T & E> 불가)

2. 따라서, makeJuice()의 매개변수 타입을 다음과 같이 변경 가능
```java
static Juice makeJuice(FruitBox<? extends Fruit> box) {
    String tmp = "";
    for(Fruit f : box.getList()) tmp += f + " ";
    return new Juice(tmp);
}
```

3. 이제 해당 메서드는 매개변수로 ```FruitBox<Fruit>``` 뿐만 아니라, ```FruitBox<Apple>```와 ```FruitBox<Grape>```도 가능
```java
FruitBox<Fruit> fruitBox = new FruitBox<Fruit>();
FruitBox<Apple> appleBox = new FruitBox<Apple>();

...

System.out.println(Juicer.makeJuice(fruitBox)); // Ok. FruitBox<Fruit>
System.out.println(Juicer.makeJuice(appleBox)); // Ok. FruitBox<Apple>
```

4. 매개변수 타입을 ```FruitBox<? extends Object>```로 하면, 모든 종류의 FruitBox가 이 메서드의 매개변수로 가능
   - 대신, 전과 달리 box의 요소가 Fruit의 자손이라는 보장이 없으므로 아래의 for문에서 box에 저장된 요소를 Fruit 타입의 참조변수로 받지 못함
```java
static Juice makeJuice(FruitBox<? extends Fruit> box) {
    String tmp = "";
    for(Fruit f : box.getList()) tmp += f + " "; // Error. Fruit이 아닐 수 있음
    return new Juice(tmp);
}
```

5. 하지만, 실제로 문제 없이 컴파일이 되는데, 이유는 바로 지네릭 클래스 FruitBox로 제한했기 때문임
```java
class FruitBox<T extends Fruit> extends Box<T> { }
```

6. 즉, 컴파일러는 위 문장으로부터 모든 FruitBox의 요소들이 Fruit의 자손이라는 것을 알고 있으므로 문제가 되지 않음

-----
### 와일드 카드 - ? extends
-----

```java
import java.util.ArrayList;

class Fruit		          { public String toString() { return "Fruit";}}
class Apple extends Fruit { public String toString() { return "Apple";}}
class Grape extends Fruit { public String toString() { return "Grape";}}

class Juice {
	String name;

	Juice(String name)	     { this.name = name + "Juice"; }
	public String toString() { return name;		 }
}

class Juicer {
	static Juice makeJuice(FruitBox<? extends Fruit> box) {
		String tmp = "";
		
		for(Fruit f : box.getList()) 
			tmp += f + " ";
		return new Juice(tmp);
	}
}

class FruitBoxEx3 {
	public static void main(String[] args) {
		FruitBox<Fruit> fruitBox = new FruitBox<Fruit>();
		FruitBox<Apple> appleBox = new FruitBox<Apple>();

		fruitBox.add(new Apple());
		fruitBox.add(new Grape());
		appleBox.add(new Apple());
		appleBox.add(new Apple());

		System.out.println(Juicer.makeJuice(fruitBox));
		System.out.println(Juicer.makeJuice(appleBox));
	}  // main
}

class FruitBox<T extends Fruit> extends Box<T> {}

class Box<T> {
//class FruitBox<T extends Fruit> {
	ArrayList<T> list = new ArrayList<T>();
	void add(T item) { list.add(item);      }
	T get(int i)     { return list.get(i); }
	ArrayList<T> getList() { return list;  }
	int size()       { return list.size(); }
	public String toString() { return list.toString();}
}
```
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/d3ed9695-0946-49f1-a93a-99436bc991fd">
</div>

-----
### 와일드 카드 - ? super
-----
```java
import java.util.*;

class Fruit	{
	String name;
	int weight;
	
	Fruit(String name, int weight) {
		this.name   = name;
		this.weight = weight;
	}

	public String toString() { return name+"("+weight+")";}
	
}

class Apple extends Fruit {
	Apple(String name, int weight) {
		super(name, weight);
	}
}

class Grape extends Fruit {
	Grape(String name, int weight) {
		super(name, weight);
	}
}

class AppleComp implements Comparator<Apple> {
	public int compare(Apple t1, Apple t2) {
		return t2.weight - t1.weight;
	}
}

class GrapeComp implements Comparator<Grape> {
	public int compare(Grape t1, Grape t2) {
		return t2.weight - t1.weight;
	}
}

class FruitComp implements Comparator<Fruit> {
	public int compare(Fruit t1, Fruit t2) {
		return t1.weight - t2.weight;
	}
}

class FruitBoxEx4 {
	public static void main(String[] args) {
		FruitBox<Apple> appleBox = new FruitBox<Apple>();
		FruitBox<Grape> grapeBox = new FruitBox<Grape>();

		appleBox.add(new Apple("GreenApple", 300));
		appleBox.add(new Apple("GreenApple", 100));
		appleBox.add(new Apple("GreenApple", 200));

		grapeBox.add(new Grape("GreenGrape", 400));
		grapeBox.add(new Grape("GreenGrape", 300));
		grapeBox.add(new Grape("GreenGrape", 200));

		Collections.sort(appleBox.getList(), new AppleComp());
		Collections.sort(grapeBox.getList(), new GrapeComp());
		System.out.println(appleBox);
		System.out.println(grapeBox);
		System.out.println();
		Collections.sort(appleBox.getList(), new FruitComp());
		Collections.sort(grapeBox.getList(), new FruitComp());
		System.out.println(appleBox);
		System.out.println(grapeBox);
	}  // main
}

class FruitBox<T extends Fruit> extends Box<T> {}

class Box<T> {
	ArrayList<T> list = new ArrayList<T>();

	void add(T item) {
		list.add(item);
	}

	T get(int i) {
		return list.get(i);
	}

	ArrayList<T> getList() { return list; }

	int size() {
		return list.size();
	}

	public String toString() {
		return list.toString();
	}
}
```
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/efd2cbb4-026a-4d1d-844c-dd07e4a99c05">
</div>

1. Collection.sort()의 선언부
```java
static <T> void sort(List<T> list, Comparator<? super T> c)
```
   - static 옆 ```<T>``` : 메서드에 선언된 지네릭 타입 (지네릭 메서드를 의미)
   - Comparator 지네릭 타입에 하한 제한이 걸려있는 와일드 카드 사용

2. 와일드 카드를 사용하지 않았다면,
```java
static <T> void sort(List<T> list, Comparator<T> c)
```

3. 만일, 타입 매개변수 T에 Apple이 대입된다면,
```java
static void sort(List<Apple> list, Comparator<Apple> c)
```

   - ```List<Apple>```을 정렬하기 위해 ```Comparator<Apple>```이 필요하다는 것을 의미

```java
class AppleComp implements Comparator<Apple> {
	public int compare(Apple t1, Apple t2) {
		return t2.weight - t1.weight;
	}
}
```

4. 하지만, Apple 대신 Grape를 대입한다면, ```List<Grape>``` 정렬을 위해, ```Comparator<Grape>```가 필요하므로, 또 다시 재정의
```java
class GrapeComp implements Comparator<Grape> {
	public int compare(Grape t1, Grape t2) {
		return t2.weight - t1.weight;
	}
}
```

5. AppleCompe와 GrapeComp는 타입만 다를 뿐 완전히 같은 코드
   - 코드 중복도 문제지만, 새로운 Fruit의 자손이 생길때마다 위와 같은 코드를 반복해서 만들어야함
   - 따라서, 이 문제를 해결하기 위해 타입 매개변수에 하한 제한의 와일드 카드를 적용
```java
static <T> void sort(List<T> list, Comparator<? super T> c)
```

   - 따라서, 타입 매개변수 T에 Apple이 대입되면,
```java
static void sort(List<Apple> list, Comparator<? super Apple> c)
```
   - 즉, Comparator의 타입 매개변수로 Apple과 그 조상이 가능함을 의미 (```Comparator<Apple>```, ```Comparator<Fruit>```, ```Comparator<Object>``` 중 하나 가능)

6. 정리
```java
Comparator<? super Apple> : Comparator<Apple>, Comparator<Fruit>, Comparator<Object>
Comparator<? super Grape> : Comparator<Grape>, Comparator<Fruit>, Comparator<Object>
```

7. 따라서 아래와 같이 FruitComp를 만들면 ```List<Apple>```과 ```List<Grape>```를 모두 정렬할 수 있음
   - 비교 대상이 되는 weight는 Apple과 Grape 조상인 Fruit에 정의되어 있기 때문에 가능
```java
class FruitComp implements Comparator<Fruit> {
	public int compare(Fruit t1, Fruit t2) {
		return t1.weight - t2.weight;
	}
}
...
// List<Apple>과 List<Grape> 모두 Comparator<Fruit>로 정렬
Collections.sort(appleBox.getList(), new FruitComp());
Collections.sort(grapeBox.getList(), new FruitComp());
```

   - 따라서, Comparator에는 항상 ```<? super T>```가 습관적으로 따라 붙음
