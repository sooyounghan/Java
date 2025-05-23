-----
### 타입 이레이저 (Type Erasere)
-----
1. 이레이저(Erasere) : 지우개 등으로 지우다라는 뜻
2. 제네릭은 자바 컴파일 단계에서만 사용되고, 컴파일 이후에는 제네릭 정보가 삭제
   - 제네릭에 사용한 타입 매개변수가 모두 사라지는 것
   - 쉽게 이야기해서 컴파일 전인 .java에는 제네릭의 타입 매개변수가 존재하지만, 컴파일 이후인 자바 바이트코드 .class에는 타입 매개변수가 존재하지 않는 것
  
3. 제네릭 타입 선언
   - GenericBox.java
```java
public class GenericBox<T> {
    private T value;

    public void set(T value) {
        this.value = value;
    }
 
    public T get() {
         return value;
    }
}
```

  - 제네릭 타입에 Integer 타입 인자 전달 (Main.java)
```java
void main() {
    GenericBox<Integer> box = new GenericBox<Integer>();
    box.set(10);
    Integer result = box.get();
}
```

  - 자바 컴파일러는 컴파일 시점에 타입 매개변수와 타입 인자를 포함한 제네릭 정보를 활용해서 ```new GenericBox<Integer>()```에 대해 다음과 같이 이해
```java
public class GenericBox<Integer> {
    private Integer value;

    public void set(Integer value) {
        this.value = value;
    }

    public Integer get() {
        return value;
    }
}
```
  - 컴파일이 모두 끝나면 자바는 제네릭과 관련된 정보를 삭제
  - 컴파일 후 .class에 생성된 정보 : GenericBox.class
```java
public class GenericBox {
    private Object value;

    public void set(Object value) {
        this.value = value;
    }

    public Object get() {
        return value;
    }
}
```
  - 상한 제한 없이 선언한 타입 매개변수 T는 Object로 변환
  - Main.class
```java
void main() {
    GenericBox box = new GenericBox();
    box.set(10);
    Integer result = (Integer) box.get(); // 컴파일러가 캐스팅 추가
}
```
  - 값을 반환 받는 부분을 Object로 받으면 안 됨
  - 자바 컴파일러는 제네릭에서 타입 인자로 지정한 Integer로 캐스팅하는 코드를 추가
  - 이렇게 추가된 코드는 자바 컴파일러가 이미 검증하고 추가했기 때문에 문제가 발생하지 않음

4. 타입 매개변수 제한의 경우
   - 컴파일 전 (AnimalHospitalV3.java)
```java
package generic.ex3;

import generic.animal.Animal;

public class AnimalHospitalV3<T extends Animal> {
    private T animal;

    public void set(T animal) {
        this.animal = animal;
    }

    public T get() {
        return animal;
    }

    public void checkup() {
        System.out.println("동물 이름 : " + animal.getName());
        System.out.println("동믈 크기 : " + animal.getSize());
        animal.sound();
    }

    public T bigger(T target) {
        return animal.getSize() > target.getSize() ? animal : target;
    }
}
```
```java
// 사용 코드 예시
AnimalHospitalV3<Dog> hospital = new AnimalHospitalV3<>();
...
Dog dog = animalHospitalV3.getBigger(new Dog());
```

  - 컴파일 후 (AnimalHospitalV3.class)
```java
public class AnimalHospitalV3 {
    private Animal animal;

    public void set(Animal animal) {
        this.animal = animal;
    }

    public void checkup() {
        System.out.println("동물 이름: " + animal.getName());
        System.out.println("동물 크기: " + animal.getSize());
        animal.sound();
    }

    public Animal getBigger(Animal target) {
        return animal.getSize() > target.getSize() ? animal : target;
    }
}
```
  - T의 타입 정보가 제거되어도 상한으로 지정한 Animal 타입으로 대체되기 때문에 Animal 타입의 메서드를 사용하는데는 아무런 문제가 없음

```java
// 사용 코드 예시
AnimalHospitalV3 hospital = new AnimalHospitalV3();
...
Dog dog = (Dog) animalHospitalV3.getBigger(new Dog());
```
  - 반환 받는 부분을 Animal로 받으면 안되기 때문에 자바 컴파일러가 타입 인자로 지정한 Dog로 캐스팅하는 코드 추가
  - 자바의 제네릭은 직접 캐스팅 하는 코드를 컴파일러가 대신 처리해주는 것
  - 자바는 컴파일 시점에 제네릭을 사용한 코드에 문제가 없는지 완벽하게 검증하기 때문에 자바 컴파일러가 추가하는 다운 캐스팅에는 문제가 발생하지 않음
  - 자바의 제네릭 타입은 컴파일 시점에만 존재하고, 런타임 시에는 제네릭 정보가 지워지는데, 이를 타입 이레이저라고 함
 
5. 타입 이레이저 방식의 한계
   - 컴파일 이후에는 제네릭의 타입 정보가 존재하지 않음
   - .class로 자바를 실행하는 런타임에는 우리가 지정한 ```Box<Integer>```, ```Box<String>``` 의 타입 정보가 모두 제거
  - 따라서 런타임에 타입을 활용하는 다음과 같은 코드는 작성할 수 없음
```java
class EraserBox<T> {
    public boolean instanceCheck(Object param) {
        return param instanceof T; // 오류
    }

    public T create() {
        return new T(); // 오류
    }
}
```

  - 런타임
```java
class EraserBox {
    public boolean instanceCheck(Object param) {
        return param instanceof Object; // 오류
    }

    public T create() {
        return new Object(); // 오류
    }
}
```
  - T는 런타임에 모두 Object
  - instanceof는 항상 Object와 비교 : 항상 참이 반환되는 문제가 발생
    + 자바는 이런 문제 때문에 타입 매개변수에 instanceof를 허용하지 않음
  - new T는 항상 new Object : 자바는 타입 매개변수에 new를 허용하지 않음
