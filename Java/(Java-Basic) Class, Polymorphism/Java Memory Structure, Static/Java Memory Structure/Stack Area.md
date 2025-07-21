-----
### 스택 영역
-----
1. JavaMemoryMain1 (/memory)
```java
package memory;

public class JavaMemoryMain1 {
    public static void main(String[] args) {
        System.out.println("Main Start");
        method1(10);
        System.out.println("Main End");
    }

    static void method1(int m1) {
        System.out.println("Method1 Start");

        int cal = m1 * 2;
        method2(cal);

        System.out.println("Method1 End");
    }

    static void method2(int m2) {
        System.out.println("Method2 Start");
        System.out.println("Method2 End");
    }
}
```
   - 실행 결과
```
Main Start
Method1 Start
Method2 Start
Method2 End
Method1 End
Main End
```
  - 호출 그림
<div align="center">
<img src="https://github.com/user-attachments/assets/d52cfbeb-ff4f-45d5-9a5c-10ac4516c708">
</div>

   - 처음 자바 프로그램을 실행하면 main()을 실행하는데, 이 때 main()을 위한 스택 프레임이 하나 생성
     + main() 스택 프레임은 내부에 args라는 매개변수를 가짐
   - main()은 method1()을 호출 : method1() 스택 프레임이 생성
     + method1()는 m1, cal 지역 변수(매개변수 포함)를 가지므로 해당 지역 변수들이 스택 프레임에 포함
   - method1()은 method2()를 호출 : method2() 스택 프레임이 생성
     + method2()는 m2 지역 변수(매개변수 포함)를 가지므로 해당 지역 변수가 스택 프레임에 포함
    
   - 종료 그림
<div align="center">
<img src="https://github.com/user-attachments/assets/ac0939ee-3157-4e94-b78d-443a0b0183ae">
</div>

   - method2() 가 종료
     + 이때 method2() 스택 프레임이 제거되고, 매개변수 m2도 제거
     + method2() 스택 프레임이 제거 되었으므로 프로그램은 method1()로 돌아감
     + 💡 물론 method1() 을 처음부터 시작하는 것이 아니라 method1()에서 method2()를 호출한 지점으로 돌아감

   - method1()이 종료 : 이 때 method1() 스택 프레임이 제거되고, 지역 변수(매개변수 포함) m1, cal 도 제거
     + 프로그램은 main()으로 돌아감

   - main()이 종료 : 더 이상 호출할 메서드가 없고, 스택 프레임도 완전히 비워졌으며, 자바는 프로그램을 정리하고 종료

2. 정리
   - 자바는 스택 영역을 사용해서 메서드 호출과 지역 변수(매개변수 포함)를 관리
   - 메서드를 계속 호출하면 스택 프레임이 계속 쌓임
   - 지역 변수(매개변수 포함)는 스택 영역에서 관리
   - 스택 프레임이 종료되면 지역 변수도 함께 제거
   - 스택 프레임이 모두 제거되면 프로그램도 종료
