-----
### 문제와 풀이 4
-----
1. 문제 - 입력한 숫자의 합계와 평균
     - 사용자로부터 여러 개의 숫자를 입력 받고, 그 숫자들의 합계와 평균을 계산하는 프로그램을 작성
     - 사용자는 숫자를 입력하고, 마지막에는 -1을 입력하여 숫자 입력을 종료한다고 가정
     - 모든 숫자의 입력이 끝난 후에는, 숫자들의 합계 sum과 평균 average를 출력 : 평균은 소수점 아래까지 계산  
     - 다음 실행 결과 예시를 참고
     - 실행 결과 예시
```
숫자를 입력하세요. 입력을 중단하려면 -1을 입력하세요:
1
2
3
4
-1
입력한 숫자들의 합계: 10
입력한 숫자들의 평균: 2.5
```

  - ScannerWhileEx3
```java
package scanner.ex;

import java.util.Scanner;

public class ScannerWhileEx3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int sum = 0;
        int count = 0;
        int num;

        // 반복문 예제 1
        while(true) {
            System.out.print("숫자를 입력하세요. 입력을 중단하려면 -1을 입력하세요. : ");
            num = scanner.nextInt();

            if (num == -1) {
                System.out.println("프로그램을 종료합니다.");
                break;
            }

            sum += num;
            count++;
        }

        // 반복문 예제 2
        /*
             while ((input = scanner.nextInt()) != -1) {
             sum += input;
             count++;
         }
        */
        
        double average = (double) sum / count;
        System.out.println("입력한 숫자들의 합계  : " + sum);
        System.out.println("입력한 숫자들의 평균  : " + average);
    }
}
```
   - while문은 주석으로 처리한 것 처럼 축약 가능
   - 반복문 실행
```java
while ((input = scanner.nextInt()) != -1) // 사용자 3입력

while ((input = 3) != -1) // input에 3 대입

while ((input(3)) != -1) // input의 값 읽기

while ((3) != -1) // () 제거

while (3 != -1) // 부등식 연산

while (true) // while문 실행
```
   - 반복문 종료
```java
while ((input = scanner.nextInt()) != -1) // 사용자 -1입력

while ((input = -1) != -1) // input에 -1 대입

while ((input(-1)) != -1) // input의 값 읽기

while ((-1) != -1) // () 제거

while (-1 != -1) // 부등식 연산

while (false) // while문 종료
```

2. 문제 - 상품 구매
   - 사용자로부터 상품 정보(상품명, 가격, 수량)를 입력받고, 이들의 총 가격을 출력하는 프로그램을 작성
   - 사용자는 여러 상품을 추가하고 결제할 수 있으며, 프로그램을 언제든지 종료할 수 있음
   - 사용자에게 다음 세 가지 옵션을 제공
     + 1 : 상품 입력, 2 : 결제, 3 : 프로그램 종료
     + 옵션은 정수로 입력받으며, 옵션을 저장하는 변수의 이름은 option 이어야 함
   - 상품 입력 옵션을 선택하면, 사용자에게 상품명과 가격, 수량을 입력
   - 결제 옵션을 선택하면, 총 비용을 출력하고 총 비용을 0 으로 초기화 (사용자가 총 비용을 확인하고, 결제를 완료했다고 가정. 따라서 다음 사용자를 위해 총 비용을 다시 0으로 초기화)
   - 프로그램 종료 옵션을 선택하면, "프로그램을 종료합니다."라는 메시지를 출력하고 프로그램을 종료
   - 위의 세 가지 옵션 외에 다른 값을 입력하면, "올바른 옵션을 선택해주세요."라는 메시지를 출력
   - 실행 결과 예시
```
1 : 상품 입력, 2 : 결제, 3 : 프로그램 종료
1
상품명을 입력하세요: 스프링
상품의 가격을 입력하세요: 30000
구매 수량을 입력하세요: 1
상품명 : 스프링, 가격 : 30000, 수량 : 1, 합계 : 30000

1 : 상품 입력, 2 : 결제, 3 : 프로그램 종료
1
상품명을 입력하세요: JPA
상품의 가격을 입력하세요: 40000
구매 수량을 입력하세요: 2
상품명 : JPA, 가격 : 40000, 수량 : 2, 합계  :80000

1 : 상품 입력, 2 : 결제, 3 : 프로그램 종료
2
총 비용: 110000

1 : 상품 입력,  2: 결제, 3 : 프로그램 종료
3
프로그램을 종료합니다.
```
   - ScannerWhileEx4
```java
package scanner.ex;

import java.util.Scanner;

public class ScannerWhileEx4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int totalCost = 0;

        while (true) {
            System.out.println("1 : 상품 입력, 2 : 결제, 3 : 프로그램 종료");
            int option = scanner.nextInt();

            switch (option) {
                case 1 :
                    scanner.nextLine(); // 이전 입력된 개행문자 제거
                    System.out.print("상품명을 입력하세요. : ");
                    String product = scanner.nextLine();

                    System.out.print("상품의 가격을 입력하세요. : ");
                    int price = scanner.nextInt();

                    System.out.print("구매 수량을 입력하세요. : ");
                    int quantity = scanner.nextInt();

                    int totalPrice = 0;
                    totalPrice = price * quantity;
                    totalCost += totalPrice;
                    System.out.println("상품명 : " + product + ", 가격 : " + price + ", 수량 : " + quantity + ", 합계 : " + totalPrice);
                    break;

                case 2 :
                    System.out.println("총 비용 : " + totalCost);
                    totalCost = 0; // 결제 후 총 비용 초기화
                    break;

                case 3 :
                    System.out.println("프로그램을 종료합니다.");
                    return;

                default :
                    System.out.println("올바른 옵션 값을 선택해주세요.");
                    break;
            }
        }
    }
}
```
