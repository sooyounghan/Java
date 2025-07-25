-----
### 지역 클래스 - 지역 변수 캡처 1
-----
1. 변수들의 생명 주기
<div align="center">
<img src="https://github.com/user-attachments/assets/420f48f9-4096-4b5c-a894-554a575b84fa"">
</div>
  
   - 클래스 변수 : 프로그램 종료 까지, 가장 김 (메서드 영역)
      + 클래스 변수(static 변수)는 메서드 영역에 존재하고, 자바가 클래스 정보를 읽어 들이는 순간부터 프로그램 종료까지 존재  
   - 인스턴스 변수 : 인스턴스의 생존 기간 (힙 영역)
      + 인스턴스 변수는 본인이 소속된 인스턴스가 GC 되기 전까지 존재하므로 생존 주기가 긴 편
   - 지역 변수 : 메서드 호출이 끝나면 사라짐 (스택 영역)
      + 지역 변수는 스택 영역의 스택 프레임 안에 존재
      + 따라서 메서드가 호출 되면 생성되고, 메서드 호출이 종료되면 스택 프레임이 제거되면서 그 안에 있는 지역 변수도 모두 제거되므로, 생존 주기가 아주 짧음
      + 참고로 매개변수도 지역 변수의 한 종류

2. 지역 클래스 예제 3
    - LocalOuterV3
```java
package nested.local;

public class LocalOuterV3 {
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
        
        // printer.print()를 여기서 실행하지 않고, Printer 인스턴스만 반환
        return printer;
    }

    public static void main(String[] args) {
        LocalOuterV3 localOuter = new LocalOuterV3();
        
        Printer printer = localOuter.process(2);
        // printer.print()를 나중에 실행 : process()의 스택 프레임이 사라진 후 실행
        
        printer.print();
    }
}
```
   - process()는 Printer 타입을 반환 (여기서는 LocalPrinter 인스턴스를 반환)
   - 여기서는 LocalPrinter.print() 메서드를 process() 안에서 실행하는 것이 아니라 process() 메서드가 종료된 이후에 main() 메서드에서 실행
   - 실행 결과
```
value = 0
localVar = 1
paramVar = 2
outInstancVar = 3
```

  - 지역 클래스 인스턴스의 생존 범위
    + 지역 클래스로 만든 객체도 인스턴스이기 때문에 힙 영역에 존재하므로, 따라서 GC 전까지 생존
    + 💡 LocalPrinter 인스턴스는 process() 메서드 안에서 생성 : 그리고 process()에서 main()으로 생성한 LocalPrinter 인스턴스를 반환하고 printer 변수에 참조를 보관
      * 따라서 LocalPrinter 인스턴스는 main()이 종료될 때 까지 생존
    + 💡 paramVar, localVar 와 같은 지역 변수는 process() 메서드를 실행하는 동안에만 스택 영역에서 생존
    + process() 메서드가 종료되면 process() 스택 프레임이 스택 영역에서 제거 되면서 함께 제거
   
3. 💡 LocalPrinter.print() 접근 메모리 그림
<div align="center">
<img src="https://github.com/user-attachments/assets/9bb59f4f-be62-41b1-9458-868fcf0bbfe0">
</div>

   - LocalPrinter 인스턴스는 print() 메서드를 통해 힙 영역에 존재하는 바깥 인스턴스의 변수인 outInstanceVar에 접근 : 이 부분은 인스턴스의 필드를 참조하는 것이기 때문에 특별한 문제가 없음
   - LocalPrinter 인스턴스는 print() 메서드를 통해 스택 영역에 존재하는 지역 변수도 접근하는 것 처럼 보임
   - 하지만 스택 영역에 존재하는 지역 변수를 힙 영역에 있는 인스턴스가 접근하는 것은 생각처럼 단순하지 않음

4. process() 메서드 종료
<div align="center">
<img src="https://github.com/user-attachments/assets/7729f1f3-f99f-42e3-9d7f-843024b4f051">
</div>

   - 지역 변수의 생명주기는 매우 짧지만, 반면에 인스턴스의 생명주기는 GC 전까지 생존할 수 있음
   - 지역 변수인 paramVar, localVar 는 process() 메서드가 실행되는 동안에만 생존할 수 있음
   - process() 메서드가 종료되면 process()의 스택 프레임이 제거되면서 두 지역 변수도 함께 제거
   - 💡 여기서 문제는 process() 메서드가 종료되어도 LocalPrinter 인스턴스는 계속 생존할 수 있다는 점

5. process() 메서드가 종료된 이후 지역 변수 접근
<div align="center">
<img src="https://github.com/user-attachments/assets/32d496d9-2f6b-461f-b2ee-5248b88886d7">
</div>

   - process() 메서드가 종료된 이후에 main() 메서드 안에서 LocalPrinter.print() 메서드를 호출
   - LocalPrinter 인스턴스에 있는 print() 메서드는 지역 변수인 paramVar, localVar 에 접근
   - 하지만 process() 메서드가 이미 종료되었으므로 해당 지역 변수들도 이미 제거된 상태인데, 실행 결과를 보면 localVar , paramVar 와 같은 지역 변수의 값들이 모두 정상적으로 출력되는 것을 확인할 수 있음
```
value = 0
localVar = 1
paramVar = 2
outInstancVar = 3
```

6. 참고
   - LocalPrinter.print() 메서드를 실행하면 이 메서드도 당연히 스택 프레임에 올라가서 실행
   - main() 에서 print() 를 실행했으므로 main() 스택 프레임 위에 print() 스택 프레임이 올라감
   - 물론 process() 스택 프레임은 이미 제거된 상태이므로 지역 변수인 localVar, paramVar 도 함께 제거되어서 접근할 수 없음

-----
### 지역 클래스 - 지역 변수 캡처 2
-----
1. 지역 클래스는 지역 변수에 접근할 수 있음
   - 그런데 앞서 본 것 처럼 지역 변수의 생명주기는 짧고, 지역 클래스를 통해 생성한 인스턴스의 생명 주기는 김
   - 지역 클래스를 통해 생성한 인스턴스가 지역 변수에 접근해야 하는데, 둘의 생명 주기가 다르기 때문에 인스턴스는 살아있지만, 지역 변수는 이미 제거된 상태일 수 있음

2. 💡 지역 변수 캡처
   - 💡 자바는 이런 문제를 해결하기 위해 지역 클래스의 인스턴스를 생성하는 시점에 필요한 지역 변수를 복사해서 생성한 인스턴스에 함께 넣어둠 : 이런 과정을 변수 캡처(Capture)
     + 캡처라는 단어는 스크린 캡처를 떠올려 보면 바로 이해가 될 것 : 인스턴스를 생성할 때 필요한 지역 변수를 복사해서 보관해 두는 것
   - 💡 물론 모든 지역 변수를 캡처하는 것이 아니라 접근이 필요한 지역 변수만 캡처

3. 지역 클래스의 인스턴스 생성과 지역 변수 캡처 과정 1
<div align="center">
<img src="https://github.com/user-attachments/assets/373dd77a-d24c-4971-8e92-cda87f36a1df">
</div>

   - LocalPrinter 인스턴스 생성 시도 : 지역 클래스의 인스턴스를 생성할 때 지역 클래스가 접근하는 지역 변수를 확인
      + LocalPrinter 클래스는 paramVar, localVar 지역 변수에 접근
   - 사용하는 지역 변수 복사 : 지역 클래스가 사용하는 지역 변수를 복사 (매개변수도 지역 변수의 한 종류)
      + 여기서는 paramVar, localVar 지역 변수를 복사

4. 지역 클래스의 인스턴스 생성과 지역 변수 캡처 과정 2
<div align="center">
<img src="https://github.com/user-attachments/assets/ead5fb41-3382-4c50-9a97-0049e708ad2d">
</div>

   - 지역 변수 복사 완료 : 복사한 지역 변수를 인스턴스에 포함
   - 인스턴스 생성 완료 : 복사한 지역 변수를 포함해서 인스턴스 생성이 완료
     + 이제 복사한 지역 변수를 인스턴스를 통해 접근할 수 있음

<div align="center">
<img src="https://github.com/user-attachments/assets/d2522574-a2a6-4a35-ae1f-a402a033b3b1">
</div>

   - 💡 LocalPrinter 인스턴스에서 print() 메서드를 통해 paramVar, localVar에 접근하면 사실은 스택 영역에 있는 지역 변수에 접근하는 것이 아니며, 대신에 인스턴스에 있는 캡처한 변수에 접근
   - 캡처한 paramVar, localVar 의 생명주기는 LocalPrinter 인스턴스의 생명주기와 같음 : 따라서 LocalPrinter 인스턴스는 지역 변수의 생명주기와 무관하게 언제든지 paramVar, localVar 캡처 변수에 접근할 수 있음
   - 이렇게 해서 지역 변수와 지역 클래스를 통해 생성한 인스턴스의 생명주기가 다른 문제를 해결

5. 코드로 캡처 변수 확인
   - LocalOuterV3 - 추가
```java
package nested.local;

import java.lang.reflect.Field;

public class LocalOuterV3 {
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

        // printer.print()를 여기서 실행하지 않고, Printer 인스턴스만 반환
        return printer;
    }

    public static void main(String[] args) {
        LocalOuterV3 localOuter = new LocalOuterV3();

        Printer printer = localOuter.process(2);
        // printer.print()를 나중에 실행 : process()의 스택 프레임이 사라진 후 실행

        printer.print();
        
        // 추가
        System.out.println("필드 확인");
        Field[] fields = printer.getClass().getDeclaredFields();

        for (Field field : fields) {
            System.out.println("field = " + field);
        }
    }
}
```
  - 실행 결과
```
value = 0
localVar = 1
paramVar = 2
outInstancVar = 3

필드 확인

// 인스턴스 변수
field = int nested.local.LocalOuterV3$1LocalPrinter.value

// 캡처 변수
field = final int nested.local.LocalOuterV3$1LocalPrinter.val$localVar
field = final int nested.local.LocalOuterV3$1LocalPrinter.val$paramVar

// 바깥 클래스 참조
field = final nested.local.LocalOuterV3 nested.local.LocalOuterV3$1LocalPrinter.this$0
```

  - 실행 결과를 통해 LocalPrinter 클래스의 캡처 변수를 확인할 수 있음
  - 추가로 바깥 클래스를 참조하기 위한 필드도 확인할 수 있음
  - 참고로 이런 필드들은 자바가 내부에서 만들어 사용하는 필드들임

6. 정리
   - 지역 클래스는 인스턴스를 생성할 때 필요한 지역 변수를 먼저 캡처해서 인스턴스에 보관
   - 그리고 지역 클래스의 인스턴스를 통해 지역 변수에 접근하면, 실제로는 지역 변수에 접근하는 것이 아니라 인스턴스에 있는 캡처한 캡처 변수에 접근
