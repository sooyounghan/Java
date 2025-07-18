-----
### final 변수와 참조
-----
1. final은 변수의 값을 변경하지 못하게 막음
   - 변수는 크게 기본형 변수와 참조형 변수가 있음
   - 기본형 변수는 10, 20 같은 값을 보관하고, 참조형 변수는 객체의 참조값을 보관

2. final을 기본형 변수에 사용하면 값을 변경할 수 없음
3. final을 참조형 변수에 사용하면 참조값을 변경할 수 없음
4. Data
```java
package final1;

public class Data {
    public int value;
}
```
  - int value : final이 아닌 변경할 수 있는 변수

5. FinalRefMain
```java
package final1;

public class FinalRefMain {
    public static void main(String[] args) {
        final Data data = new Data();
        // data = new Data(); // final 변경 불가 컴파일 오류

        // 참조 대상의 값은 변경 가능
        data.value = 10;
        System.out.println("data.value = " + data.value);
        data.value = 20;
        System.out.println("data.value = " + data.value);
    }
}
```
```java
final Data data = new Data();
// data = new Data(); // final 변경 불가 컴파일 오류
```
  - 참조형 변수 data에 final이 붙었으므로, 변수 선언 시점에 참조값을 할당했으므로 더는 참조값을 변경할 수 없음
```java
data.value = 10
data.value = 20
```
  - 그런데 참조 대상의 객체 값은 변경할 수 있음
     + 참조형 변수 data에 final이 붙어있는데, 이 경우 참조형 변수에 들어있는 참조값을 다른 값으로 변경하지 못함
     + 쉽게 이야기해서 이제 다른 객체를 참조할 수 없음
     + 이는, 참조형 변수에 들어있는 참조값만 변경하지 못한다는 뜻이며, 이 변수 이외에 다른 곳에 영향을 주는 것이 아님
     + Data.value는 final이 아니므로 값을 변경할 수 있음

<div align="center">
<img src="https://github.com/user-attachments/assets/a7aa66f4-03b7-4e57-9a0e-742d84f9a82d">
</div>

   - 정리하면 참조형 변수에 final이 붙으면 참조 대상을 자체를 다른 대상으로 변경하지 못하는 것이지, 참조하는 대상의 값은 변경할 수 있음
