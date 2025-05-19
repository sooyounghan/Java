-----
### 문제와 풀이 2
-----
1. 준비
   - 게임속 케릭터를 클래스로 생성
   - BioUnit은 유닛들의 부모 클래스 : BioUnit의 자식 클래스로 Marine, Zealot, Zergling
  
2. BioUnit (/generic/test/ex3/unit)
```java
package generic.test.ex3.unit;

public class BioUnit {
    private String name;
    private int hp;

    public BioUnit(String name, int hp) {
        this.name = name;
        this.hp = hp;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    @Override
    public String toString() {
        return "BioUnit{" +
                "name='" + name + '\'' +
                ", hp=" + hp +
                '}';
    }
}
```

3. Marine
```java
package generic.test.ex3.unit;

public class Marine extends BioUnit {
    public Marine(String name, int hp) {
        super(name, hp);
    }
}
```

4. Zealot
```java
package generic.test.ex3.unit;

public class Zealot extends BioUnit {
    public Zealot(String name, int hp) {
        super(name, hp);
    }
}
```

5. Zergling
```java
package generic.test.ex3.unit;

public class Zergling extends BioUnit {
    public Zergling(String name, int hp) {
        super(name, hp);
    }
}
```

6. 문제와 풀이 1 - 제네릭 메서드의 상한
   - 문제 설명
     + 다음 코드와 실행 결과를 참고해서 UnitUtil 클래스를 생성
     + UnitUtil.maxHp() 메서드의 조건은 다음
        * 두 유닛을 입력 받아서 체력이 높은 유닛을 반환한다. 체력이 같은 경우 둘 중 아무나 반환해도 됨
        * 제네릭 메서드를 사용
        * 입력하는 유닛의 타입과 반환하는 유닛의 타입이 같아야 함

   - UnitUtilTest(/generic/test/ex3)
```java
package generic.test.ex3;

import generic.test.ex3.unit.Marine;
import generic.test.ex3.unit.Zealot;

public class UnitUtilTest {
    public static void main(String[] args) {
        
        Marine m1 = new Marine("마린1", 40);
        Marine m2 = new Marine("마린2", 50);
        Marine resultMarine = UnitUtil.maxHp(m1, m2);
        System.out.println("resultMarine = " + resultMarine);
        
        Zealot z1 = new Zealot("질럿1", 100);
        Zealot z2 = new Zealot("질럿2", 150);
        Zealot resultZealot = UnitUtil.maxHp(z1, z2);
        System.out.println("resultZealot = " + resultZealot);
    }
}
```
  - 실행 결과
```
resultMarine = BioUnit{name='마린2', hp=50}
resultZealot = BioUnit{name='질럿2', hp=150}
```
  - UnitUtil
```java
package generic.test.ex3;

import generic.test.ex3.unit.BioUnit;

public class UnitUtil {
    public static <T extends BioUnit> T maxHp(T t1, T t2) {
        return t1.getHp() >= t2.getHp() ? t1 : t2;
    }
}
```

7. 문제와 풀이 2 - 제네릭 타입과 상한
   - 다음 코드와 실행 결과를 참고해서 Shuttle 클래스 생성
   - Shuttle 클래스의 조건
      + 제네릭 타입을 사용
      + showInfo() 메서드를 통해 탑승한 유닛의 정보를 출력

   - ShuttleTest
```java
package generic.test.ex3;

import generic.test.ex3.unit.Marine;
import generic.test.ex3.unit.Zealot;
import generic.test.ex3.unit.Zergling;

public class ShuttleTest {
    public static void main(String[] args) {
        Shuttle<Marine> shuttle1 = new Shuttle<>();
        shuttle1.in(new Marine("마린", 40));
        shuttle1.showInfo();

        Shuttle<Zergling> shuttle2 = new Shuttle<>();
        shuttle2.in(new Zergling("저글링", 35));
        shuttle2.showInfo();

        Shuttle<Zealot> shuttle3 = new Shuttle<>();
        shuttle3.in(new Zealot("질럿", 100));
        shuttle3.showInfo();
    }
}
```

  - Shuttle
```java
package generic.test.ex3;

import generic.test.ex3.unit.BioUnit;

public class Shuttle<T extends BioUnit> {
    private T unit;

    public void in(T unit) {
        this.unit = unit;
    }

    public T out() {
        return unit;
    }

    public void showInfo() {
        System.out.println("이름 : " + unit.getName() + ", HP : " + unit.getHp());
    }
}
```

8. 문제와 풀이 3 - 제네릭 메서드와 와일드카드
   - 앞서 문제에서 만든 Shuttle을 활용
   - 다음 코드와 실행 결과를 참고해서 UnitPrinter 클래스를 생성
   - UnitPrinter 클래스의 조건
      + UnitPrinter.printV1()은 제네릭 메서드로 구현
      + UnitPrinter.printV2()는 와일드카드로 구현
      + 이 두 메서드는 셔틀에 들어있는 유닛의 정보를 출력

   - UnitPrinter
```java
package generic.test.ex3;

import generic.test.ex3.unit.Marine;
import generic.test.ex3.unit.Zealot;
import generic.test.ex3.unit.Zergling;

public class UnitPrinterTest {
    public static void main(String[] args) {
        Shuttle<Marine> shuttle1 = new Shuttle<>();
        shuttle1.in(new Marine("마린", 40));
        
        Shuttle<Zergling> shuttle2 = new Shuttle<>();
        shuttle2.in(new Zergling("저글링", 35));
        
        Shuttle<Zealot> shuttle3 = new Shuttle<>();
        shuttle3.in(new Zealot("질럿", 100));
        
        UnitPrinter.printV1(shuttle1);
        UnitPrinter.printV2(shuttle1);
    }
}
```
  - 실행 결과
```
이름 : 마린, HP : 40
이름 : 마린, HP : 40
```

  - UnitPrinter
```java
package generic.test.ex3;

import generic.test.ex3.unit.BioUnit;

public class UnitPrinter {
    public static <T extends BioUnit> void printV1(Shuttle<T> t) {
        T unit = t.out();
        System.out.println("이름 : " + unit.getName() + ", HP : " + unit.getHp());
    }

    public static void printV2(Shuttle<? extends BioUnit> t) {
        BioUnit unit = t.out();
        System.out.println("이름 : " + unit.getName() + ", HP : " + unit.getHp());
    }
}
```

