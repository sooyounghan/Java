-----
### Null
-----
1. 참조형 변수에는 항상 객체가 있는 위치를 가리키는 참조값이 들어감
2. 참조형 변수에서 아직 가리키는 대상이 없다면 null 이라는 특별한 값을 넣어둘 수 있다. null 은 값이 존재하지 않는, 없다는 뜻
3. Null 값 할당 - Data
```java
package ref;

public class Data {
    int value;
}
```

4. NullMain
```java
package ref;

public class NullMain {
    public static void main(String[] args) {
        Data data = null;
        System.out.println("1. data = " + data);

        data = new Data();
        System.out.println("2. data = " + data);

        data = null;
        System.out.println("3. data = " + data);
    }
}
```
  - 실행 결과
```
1. data = null
2. data = ref.Data@1d81eb93
3. data = null
```
  - Data data = null
<div align="center">
<img src="https://github.com/user-attachments/assets/878b6c34-b0e7-41b0-a15e-3c3b21e6d93d">
</div>

  - Data 타입을 받을 수 있는 참조형 변수 data를 선언
  - 그리고 여기에 null 값을 할당
  - 따라서 data 변수에는 아직 가리키는 객체가 없다는 뜻

  - data = new Data()
<div align="center">
<img src="https://github.com/user-attachments/assets/e425019c-4a64-4e42-a6f8-eed0727e76c9">
</div>

  - 이후에 새로운 Data 객체를 생성해서 그 참조값을 data 변수에 할당
  - 이제 data 변수가 참조할 객체가 존재

  - data = null
<div align="center">
<img src="https://github.com/user-attachments/assets/4d835384-cabf-433f-9853-95fd6847213e">
</div>

  - 마지막에는 data에 다시 null값을 할당
  - 이렇게 하면 data 변수는 앞서 만든 Data 인스턴스를 더는 참조하지 않음

5. GC : 아무도 참조하지 않는 인스턴스의 최후
<div align="center">
<img src="https://github.com/user-attachments/assets/eaa6d81e-eac9-492c-a603-866957365928">
</div>

   - data에 null을 할당
   - 따라서 앞서 생성한 x001 Data 인스턴스를 더는 아무도 참조하지 않음
   - 이렇게 아무도 참조하지 않게 되면 x001 이라는 참조값을 다시 구할 방법이 없으므로, 해당 인스턴스에 다시 접근할 방법이 없음
   - 이렇게 아무도 참조하지 않는 인스턴스는 사용되지 않고 메모리 용량만 차지할 뿐임
   - C와 같은 과거 프로그래밍 언어는 직접 명령어를 사용해서 인스턴스를 메모리에서 제거해야 했음
     + 만약 실수로 인스턴스 삭제를 누락하면 메모리에 사용하지 않는 객체가 가득해져서 메모리 부족 오류가 발생
     + 자바는 이런 과정을 자동으로 처리 : 아무도 참조하지 않는 인스턴스가 있으면 JVM의 GC(가비지 컬렉션)가 더 이상 사용하지 않는 인스턴스라 판단하고 해당 인스턴스를 자동으로 메모리에서 제거
   - 객체는 해당 객체를 참조하는 곳이 있으면, JVM이 종료할 때 까지 계속 생존
   - 그런데 중간에 해당 객체를 참조하는 곳이 모두 사라지면 그때 JVM은 필요 없는 객체로 판단다고 GC(가비지 컬렉션)를 사용해서 제거
