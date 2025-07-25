-----
### 지역 클래스 - 지역 변수 캡처 3
-----
1. 지역 클래스가 접근하는 지역 변수는 절대로 중간에 값이 변하면 안 됨
2. 따라서 final 로 선언하거나 또는 사실상 final 이어야 함
3. 용어 - 사실상 final
   - 영어로 effectively final
   - 사실상 final 지역 변수는 지역 변수에 final 키워드를 사용하지는 않았지만, 값을 변경하지 않는 지역 변수를 뜻함
   - final 키워드를 넣지 않았을 뿐이지, 실제로는 final 키워드를 넣은 것 처럼 중간에 값을 변경하지 않은 지역 변수
   - 따라서 사실상 final 지역 변수는 final 키워드를 넣어도 동일하게 작동해야 함

4. LocalOuterV4
```java
package nested.local;

import java.lang.reflect.Field;

public class LocalOuterV4 {
    private int outInstancVar = 3;

    public Printer process(int paramVar) {
        int localVar = 1; // 지역 변수는 스택 프레임이 종료되는 순간 함께 제거

        class LocalPrinter implements Printer {
            int value = 0;

            @Override
            public void print() {
                System.out.println("value = " + value);

                // 인스턴스는 지역 변수보다 더 오래 존재
                System.out.println("localVar = " + localVar);
                System.out.println("paramVar = " + paramVar);
                System.out.println("outInstancVar = " + outInstancVar);
            }
        }
        Printer printer = new LocalPrinter();

        // 만약 localVar의 값을 변경한다면, 컴파일 오류
        // localVar = 10; // 컴파일 오류
        // paramVar = 20; // 컴파일 오류

        // printer.print()를 여기서 실행하지 않고, Printer 인스턴스만 반환
        return printer;
    }

    public static void main(String[] args) {
        LocalOuterV4 localOuter = new LocalOuterV4();

        Printer printer = localOuter.process(2);
        // printer.print()를 나중에 실행 : process()의 스택 프레임이 사라진 후 실행

        printer.print();
    }
}
```
  - 실행 결과
```
value = 0
localVar = 1
paramVar = 2
outInstancVar = 3
```

  - Printer printer = new LocalPrinter();
    + LocalPrinter를 생성하는 시점에 지역 변수인 localVar, paramVar를 캡처
    + 그런데 이후에 캡처한 지역 변수의 값을 다음과 같이 변경할 경우
```java
Printer printer = new LocalPrinter()

// 만약 localVar의 값을 변경한다면? 다시 캡처해야 하나??
localVar = 10; // 컴파일 오류
paramVar = 20; // 컴파일 오류
```
   - 이렇게 되면 스택 영역에 존재하는 지역 변수의 값과 인스턴스에 캡처한 캡처 변수의 값이 서로 달라지는 문제가 발생 : 동기화 문제
   - 물론 자바 언어를 설계할 때 지역 변수의 값이 변경되면 인스턴스에 캡처한 변수의 값도 함께 변경하도록 설계
   - 하지만 이로 인해 수 많은 문제들이 파생될 수 있음

5. 캡처 변수의 값을 변경하지 못하는 이유
   - 지역 변수의 값을 변경하면 인스턴스에 캡처한 변수의 값도 변경해야 함
   - 반대로 인스턴스에 있는 캡처 변수의 값을 변경하면 해당 지역 변수의 값도 다시 변경해야 함
   - 개발자 입장에서 보면 예상하지 못한 곳에서 값이 변경될 수 있으므로, 이는 디버깅을 어렵게 함
   - 지역 변수의 값과 인스턴스에 있는 캡처 변수의 값을 서로 동기화 해야 하는데, 멀티쓰레드 상황에서 이런 동기화는 매우 어렵고, 성능에 나쁜 영향을 줄 수 있음
   - 이 모든 문제는 캡처한 지역 변수의 값이 변하기 때문에 발생
   - 자바는 캡처한 지역 변수의 값을 변하지 못하게 막아서 이런 복잡한 문제들을 근본적으로 차단

6. 참고
   - 변수 캡처에 대한 내용이 이해가 어렵다면 단순하게 지역 클래스가 접근하는 지역 변수의 값은 변경하면 안 됨
