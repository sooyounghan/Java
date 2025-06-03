-----
### 문제와 풀이
-----
1. 문제 1
   - 반복해서 나오는 숫자 4, 3 을 다른 숫자로 한번에 변경할 수 있도록 다음을 변수 num1, num2 를 사용하도록 변경
   - VarEx1 (/variable/ex)
```java
package variable.ex;

public class VarEx1 {
    public static void main(String[] args) {
        int num1 = 4;
        int num2 = 3;
        
        System.out.println(num1 + num2);
        System.out.println(num1 - num2);
        System.out.println(num1 * num2);
    }
}
```
  - 실행 결과
```
7
1
12
```

2. 문제 2
   - 다음과 같은 작업을 수행하는 프로그램을 작성
      + 클래스 이름 : VarEx2
      + 변수 num1을 선언하고, 이에 10을 할당
      + 변수 num2를 선언하고, 이에 20을 할당
      + 두 변수의 합을 구하고, 그 결과를 새로운 변수 sum 에 저장
      + sum 변수의 값을 출력
   - VarEx2
```java
package variable.ex;

public class VarEx2 {
    public static void main(String[] args) {
        int num1 = 10;
        int num2 = 20;
        int sum = num1 + num2;

        System.out.println(sum);
    }
}
```
  - 계산 과정 (int sum = num1 + num2)
<div align="center">
<img src="https://github.com/user-attachments/assets/a85c8d11-6247-40c5-8f6b-4fec9e5e4c06">
</div>


3. 문제 3 - long, boolean 데이터 타입
   - 클래스 이름 : VarEx3
      + long 타입의 변수를 선언하고, 그 변수를 10000000000(백억)으로 초기화한 후 출력하는 프로그램을 작성
      + boolean 타입의 변수를 선언하고, 그 변수를 true 로 초기화한 후 출력하는 프로그램을 작성
```java
package variable.ex;

public class VarEx3 {
    public static void main(String[] args) {
        long longVar = 10000000000L;
        System.out.println(longVar);

        boolean booleanVar = true;
        System.out.println(booleanVar);
    }
}
```
  - 실행 결과
```
10000000000
true
```
