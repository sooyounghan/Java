-----
### for문
-----
  1. 일정 수행할 문장을 반복시키기 위해 사용하는 문장 (반복할 횟수를 알면 유용한 반복문)
  2. 형태 (for(초기식; 조건식; 증감식) { 실행문; })
  - 초기식에 선언되는 변수는 for문 Block내에서만 사용되는 지역 변수이므로 for문 내에서만 사용
    
     + A. 조건식에 사용될 변수 : 초기에 변수를 선언 후 초기값 선언
     + B. 조건식을 확인 후 조건에 해당하면 실행문을 실행
     + C. 조건식 변수의 값에 대해 증감식에 따라 조건식 변수를 증감
     + D. for문의 조건식을 확인 후, 조건식에 부합하면 실행문 실행 / 부합하지 않다면 for문 탈출 (반복)

  3. 조건식이 없다면 무한 루프
```java
public class ForEx01_p148 {
	public static void main(String[] args) {
		/* 
		 * Loop
		 */
		for(int i = 1; i <= 10; i++) {
			System.out.println(i + " : Loop");		// 1 ~ 10 Loop	
		}
		System.out.println("-----------------");
		
		/*
		 * 1 ~ 5 print / 5 ~ 1 print
		 */
		for(int i = 1; i < 6; i++) { // i = 1 ~ 5 (i는 for문 내에서 사용되는 지역변수)
			System.out.print(i + " "); // 1 2 3 4 5 print
		}
		System.out.println();
		for(int i = 5; i > 0; i--) { // i = 5 ~ 1 (i는 for문 내에서 사용되는 지역변수이므로 또 사용 가능)
			System.out.print(i + " "); // 5 4 3 2 1 print
		}
		System.out.println();
		System.out.println("-----------------");
		
		/*
		 * Sum : 1 + 2 + 3 + 4 + 5 = ?
		 */
		int sum = 0; // 1 ~ 5 누적 합계 변수 sum
		for(int i = 1; i <= 5; i++) { // i는 for문 내에서 사용되는 지역변수이므로 또 사용 가능
			sum = sum + i; // (= sum += i; [대입연산자] -> +=, -=, *=, /=, %=)
			// System.out.println(i + "'s sum : " + sum); [중간 연산값 결과 확인 가능]
		}
		System.out.println("1 + 2 + 3 + 4 + 5 = " + sum);

		System.out.println("-----------------");
		
		/*
		 * Sum : 1 + 2 + ... + 100 = ?
		 */
		int sum_100 = 0; // 1 ~ 100 누적 합계 변수 sum_100
		for(int i = 1; i <= 100; i++) { // i는 for문 내에서 사용되는 지역변수이므로 또 사용 가능
			sum_100 = sum_100 + i; // (= sum += i; [대입연산자])
			// System.out.println(i + "'s sum : " + sum); [중간 연산값 결과 확인 가능]
		}
		System.out.println("1 + 2 + ... + 100 = " + sum_100);

	}
}
```

-----
### 다중 for문
-----
```java
public class ForEx02_p153 {
	public static void main(String[] args) {
		/*
		 * 특정한 정수 n의 단 출력
		 */
		int num = 5; // 5단 출력
		System.out.println("-----" + num + "단 (for문)-----");
		for(int i = 1; i <= 9; i++) { // i : 1 ~ 9
			System.out.println(num + " * " + i + " = " + (num * i));
		}
		System.out.println();
		System.out.println("-----" + num + "단 (while문)-----");
		int i = 1;
		while(i <= 9) {
			System.out.println(num + " * " + i + " = " + (num * i));
			i++;
		}
	
		System.out.println();
		System.out.println("----------------------------");
		System.out.println();
		/*
		 * 2~9단 출력
		 */
		for(int j = 2; j <= 9; j++) {
			System.out.println("----- "+ j + "단 -----");
			for(int k = 1; k <= 9; k++) {
				System.out.println(j + " * " + k + " = " + (j * k));
			} // inner for loop (k)
			System.out.println();
		} // outer for loop (j)	
	}
}
```
-----
### while문
-----
 1. 반복횟수가 예측하기 불가한 경우 사용하는 반복문
 2. 조건에 사용되는 변수 선언 및 초기화 // for문에서의 초기식
```java
/*
 * Loop (while)
 *   - 반복횟수가 예측 불가한 경우의 사용하는 반복문
 *      * 조건에 사용되는 변수식 선언 및 초기화 (for문의 초기식)
 * 		while(조건식) { // for문에서의 조건식
 * 			Statement; // condition = true -> 반복 실행문
 * 			증감식 // * 조건에 사용되는 증감식 (for문의 증감식)
 * 		}
 */

public class WhileEx01_p153 {
	public static void main(String[] args) {
		/*
		 * 1 ~ 5 print (for)
		 */
		for(int i = 1; i <= 5; i++) { // for문의 i변수 : for block
			System.out.println("for i : " + i);
		}
		
		System.out.println("-----------");
		
		/*
		 * 1 ~ 5 print (while)
		 */
		int i = 1; // for문의 초기식, i는 지역변수(Local Variable)
		while(i <= 5) { // for문의 조건식
			System.out.println("while i : " + i);
			i++; // for문의 증감식
		}

		System.out.println("-----------");
		
		/*
		 * 5 ~ 1 print (while)
		 */
		i = 5;
		while(i >= 1) {
			System.out.println("while i : " + i);
			i--;
		}

		System.out.println("-----------");
		
		/*
		 * 1 ~ 5 Sum (while)
		 */
		i = 1;
		int sum = 0;
		while(i <= 5) {
			sum += i;
			i++;
		}
		System.out.println("1 + 2 + 3 + 4 + 5 = " + sum);
	}
}
```

-----
### do ~ while 문
-----
<div align = "center">
  <img width="402" alt="다운로드 (6)" src="https://github.com/sooyounghan/JAVA/assets/34672301/ea6db1c8-c772-4475-a365-c1f7fff3d745">
</div>
1. 조건에 따라 반복을 계속할 지 사용하는 것은 while문과 동일하나, 무조건적으로 처음 실행문을 최초 실행후,   

2. 조건식에 따른 조건 확인 후 반복 → 무조건 중괄호 {} 블록을 한 번 실행한 후, 조건 검사
   
4. do ~ while(); (세미콜론 주의)

```java
/*
 * do ~ while Statement
 * 
 *  - while :
 *      while(조건식) {
 *      	실행문; // 조건식이 true
 *      }
 *      
 *  - do ~ while :
 *  	do {
 *  		실행문;  // 실행문 최초 먼저 실행
 *  	} while(조건식); // 조건식 확인에 따라 조건 확인 후, true -> 실행문 반복
 *  
 */

public class DoWhileEx_p155 {
	public static void main(String[] args) {
		/*
		 * do ~ while
		 *   : 임의의 수를 입력 받으면 양수이면, 메세지 출력
		 */

		int input = 0;
		/*
		 * while
		 */
		
		/*
		 * while(0 != -1) { 
		 *    System.out.println("Not -1, Execute"); 
		 *  } // Infinity Loop -> 아래 코드는 unreachable code
		 */

		System.out.println("------- while -------");
		System.out.println();
		
		while(input != -1) {
			System.out.println("Not -1, Execute"); // input = 0
			input--; // input : -1 -> 0 -> Loop Exit
		}
		
		System.out.println();
		System.out.println("------- do while -------");
		System.out.println();
		
		/*
		 * do ~ while
		 */
		
		input = -1;
		do {
			// System.in.read() : 데이터를 계속적 입력, 입력할 데이터가 없으면 -1 return
			System.out.println("Not -1, Execute"); // input = -1, But do~while으로 실행문 한번 실행
		} while(input != -1); // Loop Exit
		
		input = 0;
		do {
			System.out.println("Not -1, Execute"); // input = -1, But do~while으로 실행문 한번 실행
			input--; // input = 0 -> -1
		} while(input != -1); // input = -1 -> Loop Exit
	}
}
```
-----
### break 문
-----
1. for문, while문, do~while문에서의 반복문 종료 (반복 취소)
2. switch문의 종료시에도 사용
3. if문과 주로 사용 → if문의 조건식에 따라 for문과 while문을 종료할 때 사용

<div align = "center">
<img src = "https://github.com/sooyounghan/JAVA/assets/34672301/cf82b4c7-7dec-4398-bc53-518623b0fcfb">
</div>

-----
### continue문
-----
1. 현재 반복을 제외하고 다음 반복을 진행
2. for문, while문, do~while문에서 사용
3. for문 : 증감식으로 이동
4. while문, do~while문 : 조건식으로 이동
5. continue이하 문장은 continue문에 의해 미실행 → for문은 증감식, while 및 do ~ while문은 조건식으로 이동
   
<div align = "center">
<img src = "https://github.com/sooyounghan/JAVA/assets/34672301/16db534c-2b77-43ee-99d4-0a68075b100f">
</div>

```java
/*
 * break Statement (break;)
 * 	- for, while, do~while문에서의 반복문 종료(즉, 반복문 취소)
 *      - ** switch문에서 종료를 위해 사용
 *      - if문에 융합하여 사용 -> if문의 조건에 따라 for문과 while문 종료 시 사용
 */

/*
 * Continue Statement (continue;)
 *       - for, while, do~whole문에서 사용
 *       - for문 : 증감식으로 이동
 *       - while, do~while문 : 조건식으로 이동
 *       - continue 이하 문장은 continue에 의해 미실행되어 for문은 증감식, while / do~while문은 조건식으로 이동
 */
public class BreakEx01_p156 {
	public static void main(String[] args) {	
		System.out.println("----break문----");
		/*
		 * 1 2 3 ... 9 10 print -> while
		 */
		int i = 1;
		while(i <= 10) {
			System.out.println("i = " + i);
			if(i == 5) { // i는 5일 때 밑에 if문 실행문 출력
				// System.out.println("\t if Statement i = " + i);
				break; // 1 ~ 5까지 출력 후, (주석 처리 : if문 실행문 실행 후) 반복문 종료
			}
			i++;
		}
		
		System.out.println("----continue문----");
		i = 1;
		while(i <= 10) {
			i++; // 출력하기 전에 i는 1이 증가하므로 처음 2부터 출력, 10에서 i++를 통해 11까지 출력
			System.out.println("i = " + i);
			if(i == 5) { // i는 5일 때 밑에 if문 실행문 출력
				System.out.println("\t if Statement i = " + i);
				continue; // 1 ~ 5까지 출력 후, (주석 처리 : if문 실행문 실행 후) continue문에 따라 하위 문장 미실행 후, while문의 조건식으로 이동
			}
			System.out.println("While Loop"); // i = 5일 때 제외하고, 출력 (총 9번 출력)
		}	
	}
}
```
