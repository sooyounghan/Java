-----
### 문제와 풀이 3 - 상품 관리 프로그램 만들기
-----
1. 자바를 이용하여 상품 관리 프로그램을 만들어 보자. 이 프로그램은 다음의 기능이 필요  
    - 상품 등록 : 상품 이름과 가격을 입력받아 저장
    - 상품 목록 : 지금까지 등록한 모든 상품의 목록을 출력
    - 다음과 같이 동작
      + 첫 화면에서 사용자에게 세 가지 선택을 제시 : "1. 상품 등록", "2. 상품 목록", "3. 종료"
      + "1. 상품 등록"을 선택하면, 사용자로부터 상품 이름과 가격을 입력받아 배열에 저장
      + "2. 상품 목록"을 선택하면, 배열에 저장된 모든 상품을 출력
      + "3. 종료"를 선택하면 프로그램을 종료

2. 제약 조건
  - 상품은 최대 10개까지 등록할 수 있음
  - 다음은 사용해야 하는 변수 및 구조
    + Scanner scanner : 사용자 입력을 받기 위한 Scanner 객체
    + String[] productNames : 상품 이름을 저장할 String 배열
    + int[] productPrices : 상품 가격을 저장할 int 배열
    + int productCount : 현재 등록된 상품의 개수를 저장할 int 변수

3. 실행 결과 예시
```
1. 상품 등록 | 2. 상품 목록 | 3. 종료
메뉴를 선택하세요 : 1
상품 이름을 입력하세요 : JAVA
상품 가격을 입력하세요 : 10000

1. 상품 등록 | 2. 상품 목록 | 3. 종료
메뉴를 선택하세요 : 1
상품 이름을 입력하세요 : SPRING
상품 가격을 입력하세요 : 20000

1. 상품 등록 | 2. 상품 목록 | 3. 종료
메뉴를 선택하세요 : 2
JAVA : 10000원
SPRING : 20000원

1. 상품 등록 | 2. 상품 목록 | 3. 종료
메뉴를 선택하세요 : 3

프로그램을 종료합니다.
```

4. 상품을 더 등록할 수 없는 경우
```
1. 상품 등록 | 2. 상품 목록 | 3. 종료
메뉴를 선택하세요 : 1
더 이상 상품을 등록할 수 없습니다.
```

5. 등록된 상품이 없는 경우
```
1. 상품 등록 | 2. 상품 목록 | 3. 종료
메뉴를 선택하세요 : 2
등록된 상품이 없습니다.
```

6. ProductAdminEx
```java
package array.ex;

import java.util.Scanner;

public class ProductAdminEx {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int maxProducts = 10;
        String[] productNames = new String[maxProducts];
        int[] productPrices = new int[maxProducts];
        int productCount = 0;

        while(true) {
            System.out.println("1. 상품 등록 | 2. 상품 목록 | 3. 종료");
            System.out.print("메뉴를 선택하세요. : ");
            int menu = scanner.nextInt();

            switch(menu) {
                case 1 :
                    if(productCount <= maxProducts) {
                        System.out.print("상품 이름을 입력하세요 : ");
                        productNames[productCount] = scanner.next();
                        scanner.nextLine();

                        System.out.print("상품 가격을 입력하세요 : ");
                        productPrices[productCount] = scanner.nextInt();
                        scanner.nextLine();

                        productCount++;
                    } else {
                        System.out.println("더 이상 상품을 등록할 수 없습니다.");
                        continue;
                    }

                    break;

                case 2 :
                    if(productCount == 0) {
                        System.out.println("등록된 상품이 없습니다.");
                        continue;
                    } else {
                        for(int i = 0 ; i < productCount; i++) {
                            System.out.println(productNames[i] + " : " + productPrices[i]);
                        }
                    }

                    break;

                case 3 :
                    System.out.println("프로그램을 종료합니다.");
                    return;

                default :
                    System.out.println("잘못된 메뉴를 선택하셨습니다.");
                    break;
            }
        }
    }
}
```
