-----
### 지네릭 메서드 (Generic Method)
-----
1. 메서드의 선언부에 지네릭 타입이 선언된 메서드
2. 지네릭 타입 선언 위치는 반환 타입 바로 앞
```java
static <T> void sort(List<T> list, Comparaotr<? super T> c)
```

3. 지네릭 클래스에 정의된 타입 매개변수와 지네릭 메서드에 정의된 타입 매개변수는 전혀 별개의 것
  - 같은 문자 T를 사용해도 같은 것이 아니라는 것 주의
  - 지네릭 메서드는 지네릭 클래스가 아닌 클래스에도 정의 가능

```java
class FruitBox<T> {
    ...
    static <T> void sort(List<T> list, Comparator<? super T> c){
        ...
    }
}
```

4. 위의 코드에서 지네릭 클래스 FruitBox에 선언된 타입 매개변수 T와 지네릭 메서드 sort()에 선언된 타입 매개변수 T는 타입 문자만 같을 뿐, 서로 다른 것
  - sort()는 static 메서드임을 주목
  - static 멤버에는 타입 매개변수를 사용할 수 없지만, 이처럼 '메서드에 지네릭 타입을 선언하고 사용하는 것은 가능'

5. 메서드에 선언된 지네릭 타입은 지역 변수를 선언한 것과 같다고 생각하면 쉬움
   - 이 타입 매개변수는 메서드 내에서만 지역적으로 사용될 것이므로 메서드가 static이건 아니건 상관이 없음
```java
static Juice makeJuice(FruitBox<? extends Fruit> box) {
      String tmp = "";
      for(Fruit f : box.getList(0) tmp += f + " ";
      return new Juice(tmp);
}
```
```java
static <T extends Fruit> Juice makeJuice(FruitBox<T> box) {
      String tmp = "";
      for(Fruit f : box.getList(0) tmp += f + " ";
      return new Juice(tmp);
}
```

6. 이제 이 메서드를 호출할 때, 아래와 같이 타입 변수에 타입을 대입해야 함
```java
FruitBox<Fruit> fruitBox = new FruitBox<Fruit>();
FruitBox<Apple> appleBox = new FruitBox<Apple>();
...
System.out.println(Juicer.<Fruit>makeJucie(fruitBox));
System.out.println(Juicer.<Apple>makeJucie(appleBox));
```

7. 대부분의 경우 컴파일러가 타입을 추정할 수 있기 때문에, 생략 가능
```java
System.out.println(Juicer.makeJucie(fruitBox));
System.out.println(Juicer.makeJucie(appleBox));
```

8. 한 가지 주의할 점은 지네릭 메서드를 호출할 때, 대입된 타입을 생략할 수 없는 경우에는 참조변수나 클래스 이름을 생략할 수 없다는 것
```java
System.out.println(<Fruit>makeJucie(fruitBox)); // Error. 클래스 이름 생략 불가
System.out.println(this.<Fruit>makeJucie(fruitBox)); // Ok
System.out.println(Juicer.<Fruit>makeJucie(fruitBox)); // Ok
```

9. 같은 클래스 내 있는 멤버들끼리는 참조변수나 클래스이름, 즉, 'this.'나 '클래스이름.'을 생략하고 메서드 이름만으로 호출 가능
10. 단, 대입된 타입이 있을 때는 반드시 써줘야함

11. 지네릭 메서드는 매개변수의 타입이 복잡할 때도 유용
```java
public static void printAll(ArrayList<? extends Product> list, ArrayList<? extends Product> list2) {
    for(Unit u : list) {
        System.out.println(u);
    }
}
```

```java
public static <T extends Product> void printAll(ArrayList<T> list, ArrayList<T> list2) {
    for(Unit u : list) {
        System.out.println(u);
    }
}
```

-----
### 지네릭 메서드 (Generic Method) 예
-----
```java
public static <T extends Comparable<? super T>> void sort(List<T> list)
```
1. 위 메서드에서 와일드 카드를 제외해서 보면,
```java
public static <T extends Comparable<T>> void sort(List<T> list)
```

2. List<T>의 요소가 Comparable 인터페이스를 구현해야 한다는 뜻

3. 즉, 타입 T를 요소로 하는 List를 매개변수로 허용
4. T는 Comparable을 구현한 클래스이어야 하며(```<T extends Comparable>```), T 또는 그 조상의 타입을 비교하는 Comparable이어야 한다는 것을 의미(```Comparable <? super T>```) 
