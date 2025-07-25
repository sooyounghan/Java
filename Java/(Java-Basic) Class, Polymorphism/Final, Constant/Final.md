-----
### final 변수와 상수 1
-----
1. final 키워드는 이름 그대로 끝 이라는 뜻
2. 변수에 final 키워드가 붙으면 더는 값을 변경할 수 없음
   - 참고로 final은 class, method를 포함한 여러 곳에 붙을 수 있음

3. final - 지역 변수 : FinalLocalMain(/final1)
```java
package final1;

public class FinalLocalMain {
    public static void main(String[] args) {
        // final 지역 변수 1
        final int data1;
        data1 = 10; // 최초 한 번만 할당 가능
        // data1 = 20; // 컴파일 오류
        
        // final 지역 변수 2
        final int data2 = 10;
        // data2 = 20; // 컴파일 오류
        
        method(10);
    }
    
    // final 매개변수
    static void method(final int parameter) {
        // parameter = 20; // 컴파일 오류
    }
}
```
   - final을 지역 변수에 설정할 경우 최초 한번만 할당할 수 있으며, 이후에 변수의 값을 변경하려면 컴파일 오류가 발생
   - final을 지역 변수 선언시 바로 초기화 한 경우 이미 값이 할당되었기 때문에 값을 할당할 수 없음
   - 매개변수에 final이 붙으면 메서드 내부에서 매개변수의 값을 변경할 수 없으므로, 따라서 메서드 호출 시점에 사용된 값이 끝까지 사용

4. final - 필드 (멤버 변수)
   - ConstructInit
```java
package final1;

// final 필드 - 생성자 초기화
public class ConstructInit {
    final int value;
    
    public ConstructInit(int value) {
        this.value = value;
    }
}
```
   - final을 필드에 사용할 경우 해당 필드는 생성자를 통해 한 번만 초기화 가능
   - FieldInit
```java
package final1;

// final 필드 - 필드 초기화
public class FieldInit {
    static final int CONST_VALUE = 10;
    final int value = 10;
}
```
   - final 필드를 필드에서 초기화하면 이미 값이 설정되었기 때문에 생성자를 통해서도 초기화 할 수 없음
   - 코드에서 보는 것 처럼 static 변수에도 final 을 선언할 수 있음

  - FinalFieldMain
```java
package final1;

public class FinalFieldMain {
    public static void main(String[] args) {
        // final 필드 - 생성자 초기화
        System.out.println("생성자 초기화");
        ConstructInit constructInit1 = new ConstructInit(10);
        ConstructInit constructInit2 = new ConstructInit(20);
        System.out.println("constructInit1.value = " + constructInit1.value);
        System.out.println("constructInit2.value = " + constructInit2.value);

        // final 필드 - 필드 초기화
        System.out.println("필드 초기화");
        FieldInit fieldInit1 = new FieldInit();
        FieldInit fieldInit2 = new FieldInit();
        FieldInit fieldInit3 = new FieldInit();

        System.out.println("fieldInit1.value = " + fieldInit1.value);
        System.out.println("fieldInit2.value = " + fieldInit2.value);
        System.out.println("fieldInit3.value = " + fieldInit3.value);

        // 상수
        System.out.println("상수");
        System.out.println(FieldInit.CONST_VALUE);

    }
}
```
  - 실행 결과
```
생성자 초기화
constructInit1.value = 10
constructInit2.value = 20
필드 초기화
fieldInit1.value = 10
fieldInit2.value = 10
fieldInit3.value = 10
상수
10
```

  - ConstructInit과 같이 생성자를 사용해서 final 필드를 초기화 하는 경우, 각 인스턴스마다 final 필드에 다른 값을 할당할 수 있음
  - 물론 final을 사용했기 때문에 생성 이후에 이 값을 변경하는 것은 불가능

<div align="center">
<img src="https://github.com/user-attachments/assets/b994260e-19b3-414f-b07f-9e1c478ae148">
</div>

   - FieldInit과 같이 final 필드를 필드에서 초기화 하는 경우, 모든 인스턴스가 다음 오른쪽 그림과 같이 같은 값을 가짐
   - 여기서는 FieldInit 인스턴스의 모든 value 값은 10이 됨
      + 왜냐하면 생성자 초기화와 다르게 필드 초기화는 필드의 코드에 해당 값이 미리 정해져있기 때문임
   - 모든 인스턴스가 같은 값을 사용하기 때문에 결과적으로 메모리를 낭비하게 됨
     + 물론 JVM에 따라서 내부 최적화를 시도할 수 있음
     + 또 메모리 낭비를 떠나서 같은 값이 계속 생성되는 것은 개발자가 보기에 명확한 중복
     + 이럴 때 사용하면 좋은 것이 바로 static 영역

   - static final
     + FieldInit.MY_VALUE는 static 영역에 존재
     + 그리고 final 키워드를 사용해서 초기화 값이 변하지 않음
     + static 영역은 단 하나만 존재하는 영역
     + MY_VALUE 변수는 JVM 상에서 하나만 존재하므로 앞서 설명한 중복과 메모리 비효율 문제를 모두 해결할 수 있음
     + 이런 이유로 필드에 final + 필드 초기화를 사용하는 경우 static 을 붙여서 사용하는 것이 효과적

  
