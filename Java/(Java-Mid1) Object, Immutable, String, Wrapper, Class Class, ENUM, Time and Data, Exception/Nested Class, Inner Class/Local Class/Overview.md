-----
### 지역 클래스
-----
1. 지역 클래스(Local class)는 내부 클래스의 특별한 종류의 하나
2. 따라서 내부 클래스의 특징을 그대로 가짐
3. 예를 들어서 지역 클래스도 내부 클래스이므로 바깥 클래스의 인스턴스 멤버에 접근할 수 있음
4. 지역 클래스는 지역 변수와 같이 코드 블럭 안에서 정의
5. 예시
```java
class Outer {
     public void process() {
         // 지역 변수
         int localVar = 0;

         // 지역 클래스
         class Local {...}
         Local local = new Local();
     }
}
```

6. 특징
   - 지역 변수처럼 코드 블럭 안에 클래스를 선언
   - 지역 변수에 접근 가능

7. 예제 1 - LocalOuterV1 (/nested/local)
```java
package nested.local;

public class LocalOuterV1 {
    private int outInstanceVar = 3;
    
    public void process(int paramVar) {
        int localVar = 1;
        
        class LocalPrinter {
            int value = 0;
            
            public void printData() {
                System.out.println("value = " + value);
                System.out.println("localVar = " + localVar);
                System.out.println("paramVar = " + paramVar);
                System.out.println("outInstanceVar = " + outInstanceVar);
            }
        }
        
        LocalPrinter printer = new LocalPrinter();
        printer.printData();
        
    }

    public static void main(String[] args) {
        LocalOuterV1 localOuter = new LocalOuterV1();
        localOuter.process(2);
    }
}
```
   - 실행 결과
```
value = 0
localVar = 1
paramVar = 2
outInstanceVar = 3
```
   - 지역 클래스의 접근 범위
      + 자신의 인스턴스 변수인 value에 접근할 수 있음
      + 자신이 속한 코드 블럭의 지역 변수인 localVar에 접근할 수 있음
      + 자신이 속한 코드 블럭의 매개변수인 paramVar에 접근할 수 있음 (참고로 매개변수도 지역 변수의 한 종류)
      + 바깥 클래스의 인스턴스 멤버인 outInstanceVar에 접근할 수 있음 (지역 클래스도 내부 클래스의 한 종류)

   - 💡 단, 지역 클래스는 지역 변수처럼 접근 제어자 사용 불가

8. 예제 2
   - 내부 클래스를 포함한 중첩 클래스들도 일반 클래스처럼 인터페이스를 구현하거나, 부모 클래스를 상속할 수 있음
   - Printer
```java
package nested.local;

public interface Printer {
    void print();
}
```
   - LocalOuterV2
```java
package nested.local;

public class LocalOuterV2 {
    private int outInstancVar = 3;
    
    public void process(int paramVar) {
        int localVar = 1;
        
        class LocalPrinter implements Printer {
            int value = 0;
            
            @Override
            public void print() {
                System.out.println("value = " + value);
                System.out.println("localVar = " + localVar);
                System.out.println("paramVar = " + paramVar);
                System.out.println("outInstancVar = " + outInstancVar);
            }
        }
        Printer printer = new LocalPrinter();
        printer.print();
    }

    public static void main(String[] args) {
        LocalOuterV2 localOuterV2 = new LocalOuterV2();
        localOuterV2.process(2);
    }
}
```
  - 실행 결과
```
value = 0
localVar = 1
paramVar = 2
outInstanceVar = 3
```
