-----
### 불변 객체 - 값 변경
-----
1. 불변 객체를 사용하지만 그래도 값을 변경해야 하는 메서드가 필요할 경우
   - 예를 들어서 기존 값에 새로운 값을 더하는 add()와 같은 메서드가 존재
   - 먼저 변경 가능한 객체에서 값을 변경하는 간단한 예 : MutableObj (/lang/immutable/change)
```java
package lang.immutable.change;

public class MutableObj {
    private int value;

    public MutableObj(int value) {
        this.value = value;
    }

    
    public void add(int addValue) {
        value = value + addValue;
    }
    
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
```

   - MutableMain
```java
package lang.immutable.change;

public class MutableMain {
    public static void main(String[] args) {
        MutableObj obj = new MutableObj(10);

        obj.add(20);
        
        // 계산 이후 기존 값 사라짐
        System.out.println("obj = " + obj.getValue());
    }
}
```
   - 실행 결과
```
obj = 30
```
  - MutableObj을 10이라는 값으로 생성
  - 이후에 obj.add(20)을 통해서 10 + 20을 수행
  - 계산 이후에 기존에 있던 10이라는 값은 사라짐
  - MutableObj의 상태(값)가 10에서 30 으로 변경
  - obj.getValue()를 호출하면 30이 출력

2. 이번에는 불변 객체에서 add() 메서드 구현 방법 (불변 객체는 변하지 않아야 함)
   - ImmutableObj
```java
package lang.immutable.change;

public class ImmutableObj {
    private final int value;

    public ImmutableObj(int value) {
        this.value = value;
    }
    
    public ImmutableObj add(int addValue) {
        int result = value + addValue;
        
        return new ImmutableObj(result);
    }

    public int getValue() {
        return value;
    }
}
```
   - 불변 객체는 값을 변경하면 안 됨 (하지만 여기서는 기존 값에 새로운 값을 더해야 함)
   - 불변 객체는 기존 값은 변경하지 않고 대신에 계산 결과를 바탕으로 새로운 객체를 만들어서 반환
   - 이렇게 하면 불변도 유지하면서 새로운 결과도 만들 수 있음

   - ImmutableMain1
```java
package lang.immutable.change;

public class ImmutableMain1 {
    public static void main(String[] args) {
        ImmutableObj obj1 = new ImmutableObj(10);
        ImmutableObj obj2 = obj1.add(20);
        
        // 계산 이후 기존값과 신규 값 모두 확인
        System.out.println("obj1 = " + obj1.getValue());
        System.out.println("obj2 = " + obj2.getValue());
    }
}
```
  - 실행 결과
```
obj1 = 10
obj2 = 30
```
   - 불변 객체를 설계할 때 기존 값을 변경해야 하는 메서드가 필요할 수 있음
   - 이때는 기존 객체의 값을 그대로 두고 대신에 변경된 결과를 새로운 객체에 담아서 반환하면 됨
   - 결과를 보면 기존 값이 그대로 유지되는 것을 확인할 수 있음

<div align="center">
<img src="https://github.com/user-attachments/assets/46b333be-911a-411c-872a-193ac8910dc9">
</div>

   - add(20) 호출
   - 기존 객체에 있는 10과 인수로 입력한 20을 더함 : 이 때 기존 객체의 값을 변경하면 안되므로 계산 결과를 기반으로 새로운 객체를 만들어서 반환
   - 새로운 객체는 x002 참조를 가짐 : 새로운 객체의 참조값을 obj2 에 대입

3. ImmutableMain2
```java
package lang.immutable.change;

public class ImmutableMain2 {
    public static void main(String[] args) {
        ImmutableObj obj1 = new ImmutableObj(10);
        obj1.add(20);

        // 계산 이후 기존값과 신규 값 모두 확인
        System.out.println("obj1 = " + obj1.getValue());
    }
}
```
   - 실행 결과
```
obj1 = 10
```
   - 실행 결과처럼 아무것도 처리되지 않은 것 처럼 보임
   - 불변 객체에서 변경과 관련된 메서드들은 보통 객체를 새로 만들어서 반환하기 때문에 꼭! 반환 값을 받아야 함
