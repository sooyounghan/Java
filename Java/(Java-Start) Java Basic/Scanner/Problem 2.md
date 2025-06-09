-----
### 문제와 풀이 2
-----
1. 문제 - 변수 값 교환
   - 변수 a = 10 이 들어있고, b = 20 존재
   - 변수 a의 값과 변수 b의 값을 서로 바꿀 것
   - 다음 코드에서 시작과 종료 부분 사이에 변수의 값을 교환하는 코드를 작성하면 됨
   - 힌트: temp 변수를 활용
   - 출력 결과
```
a = 20
b = 10
```
   - ChangeVarEx
```java
package scanner.ex;

public class ChangeVarEx {
    public static void main(String[] args) {
        int a = 10;
        int b = 20;
        int temp;

        temp = a;
        a = b;
        b = temp;

        System.out.println("a = " + a);
        System.out.println("b = " + b);
    }
}
```
   - 풀이
     + a와 b를 한번에 서로 교환 불가
     + a = b 라고 하면 a의 값인 10은 사라져 버리므로, 따라서 a, b 둘 다 20이 되어버림
     + a = b 라고 하기 전에 a의 값을 어딘가에 보관해두어야 함
     + 여기서는 임시로 사용할 변수인 temp 에 보관

   - 진행 과정 - 초기 상태
<div align="center">
<img src="https://github.com/user-attachments/assets/11a3e20f-ee46-4acd-b933-b73eaa8e22cb">
</div>

   - 진행 과정 1
<div align="center">
<img src="https://github.com/user-attachments/assets/83a70ed4-f8e1-4f70-b45b-70642b60aa87">
</div>

   - 진행 과정 2
<div align="center">
<img src="https://github.com/user-attachments/assets/50790158-ec63-4627-879c-6a5a9b241608">
</div>

   - 진행 과정 3
<div align="center">
<img src="https://github.com/user-attachments/assets/d5786046-fc8a-43b2-b114-5ee3fa0647c4">
</div>

2. 문제 - 사이 숫자
   - 사용자로부터 두 개의 정수를 입력받고, 이 두 정수 사이의 모든 정수를 출력하는 프로그램을 작성
   - 사용자에게 첫 번째 숫자를 입력 : 변수의 이름은 num1
   - 사용자에게 두 번째 숫자를 입력 : 변수의 이름은 num2
   - 만약 첫 번째 숫자 num1이 두 번째 숫자 num2 보다 크다면, 두 숫자를 교환
   - 참고 : 교환을 위해 임시 변수 사용을 고려
   - num1부터 num2까지의 각 숫자를 출력
   - 출력 결과에 유의 : 다음 예시와 같이 2, 3, 4, 5 처럼 , 로 구분해서 출력
   - 실행 결과 예시
```
첫 번째 숫자를 입력하세요:2
두 번째 숫자를 입력하세요:5
두 숫자 사이의 모든 정수:2, 3, 4, 5
```
```
첫 번째 숫자를 입력하세요:7
두 번째 숫자를 입력하세요:5
두 숫자 사이의 모든 정수:5, 6, 7
```
   - ScannerEx5
```java
package scanner.ex;

import java.util.Scanner;

public class ScannerEx5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("첫 번째 숫자를 입력하세요. : ");
        int num1 = scanner.nextInt();

        System.out.print("두 번째 숫자를 입력하세요. : ");
        int num2 = scanner.nextInt();
        int temp;

        if (num1 > num2) {
            temp = num1;
            num1 = num2;
            num2 = temp;
        }

        System.out.print("두 숫자 사이의 모든 정수 : ");
        for(int i = num1; i <= num2; i++) {
            System.out.print(i != num2 ? i + ", " : i);
        }
    }
}
```
