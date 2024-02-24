-----
### 조건문 : if문
-----
1. 조건식 결과에 따라 실행 여부 결정
2. 조건식 결과가 true이면 블록 실행, false이면 블록 탈출
3. 조건식 : true or false 산출할 수 있는 조건식

4. if ~ else문 : if의 조건식이 true이면, if문 내 실행문 실행 / false면 else문 내 실행문 실행
5. 중첩 if문 : if문 안에 if문 중첩
<div align = "center">
<img src = https://github.com/sooyounghan/JAVA/assets/34672301/094697df-24c3-4251-8baa-ca3ae19361bf">
</div>

```java
public class If_Ex01 {
	/*
	 * Field : Class Variable
	 * (= Global Variable)
	 */
	int score_global = 100; // Global Variable = Field
	public static void main(String[] args) {
		/* 
 		 * Score range : 0 ~ 100
 		 */
		int score = 53; // Local Variable : 변수가 사용된 Block내에서만 사용가능
						 // Local Variable는 반드시 초기값 설정
						 // score variable's scope : main method
		/*
		 * Logic Operator
		 *  - AND : &&
		 *  - OR : ||
		 *  - NOT : !
		 */
		if(score >= 0 && score <= 100) { // 0 <= Score <= 100
			/* Grade partitioned by score.
			 *   - 90 <= Score and Score <= 100 : Grade 'A'
			 *   - 80 <= Score < 90 : Grade 'B'
			 *   - 70 <= Score < 80 : Grade 'C'
			 *   - 60 <= Score < 70 : Grade 'D'
			 *   - Score < 60 : Grade 'F'
			 */
			/*
			 * Nested if Statement 
			 * if(condition) {
			 *   if(condition 1) {
			 *      statement;
			 *   }   
			 *   else if(condition 2) {
			 *      statement;
			 *   }
			 *   ...
			 *   else if(condition n) {
			 *      statement;
			 *   }
			 *   else {
			 *      statement;
			 *   }
			 * }
			 */
			String result = ""; // result score : if statement 
			char grade = ' '; // char type default type : ' '(Space)
			/*
			 * String class Default type : null (= String ex = "";)
			 * Character type Default type : space (= char ex = ' ';)
			 */
			if(score >= 90) { // 90 <= Score <= 100
				result = "A";
				grade = 'A';
			}
			else if(score >= 80){ // 80 <= Score < 90
				result = "B";
				grade = 'B';
			}
			else if(score >= 70) { // 70 <= Score < 80
				result = "C";
				grade = 'C';
			}
			else if(score >= 60) { // 60 <= Score < 70
				result = "D";
				grade = 'D';
			}
			else if(score >= 0){ // 0 <= Score < 60
				result = "F";
				grade = 'F';
			}
			System.out.println("String result :" + result + "Grade");
			System.out.println("char grade : " + grade + "Grade");
		}
		else { // 0 < Score OR Score > 100
			System.out.println("Wrong Score");
		}
	}
}
```
-----
### Switch문
-----

<div align = "center">
   <img src = "https://github.com/sooyounghan/JAVA/assets/34672301/321ec008-54a4-4974-a851-3048c387d09b">
</div>   


```java
import java.util.Scanner;

/* 
 * Condition Statement : if, switch
 * Loop Statement : for, while, do~while 
 */

/*
 * Switch Statement 
 * switch(변수명) {
 * 		case 값1 : // 해당 조건이 값1과 일치할 때 (==)
 * 			statement;
 * 			break; // switch문 탈출
 * 		case 값2 : // 해당 조건이 값2과 일치할 때 (==)
 * 			statement;
 * 			break; 
 *      ...
 *      case 값n : // 해당 조건이 값n과 일치할 때 (==)
 *      	statement;
 *      	break;
 *      default : // 값1부터 값n까지 모두 일치하지 않을 때
 *      	statement;
 *      	break; // 생략해도 switch문이 끝나지만, 명확하게 하기위해 사용
 * }
 * 
 */

public class Switch_Ex01 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		// not import statement : (= java.util.Scanner scanner = new java.util.Scanner(System.in);)
	
		System.out.print("Input Age(Integer) : ");
		int num = scanner.nextInt(); // num : 1~6, if input data is real number, type error.
		String result = null;
		
		/*
		 * Random Number(난수 발생)
		 *  : Math Class의 random() Method
		 */
		
		int num_random = (int)(Math.random() * 6) + 1;
		switch(num_random) { // condition : Integer type
			case 1 : result = "num : 1"; break; // case [Integer]
			case 2 : result = "num : 2"; break;
			case 3 : result = "num : 3"; break;
			case 4 : result = "num : 4"; break;
			case 5 : result = "num : 5"; break;
			case 6 : result = "num : 6"; break;
			default : result = "num is not matched 1~6"; break;
		}
		System.out.println(result);
		
		System.out.println("-------------------------");
		
		System.out.print("Input Grade : ");
		String grade = scanner.nextLine();
		switch(grade) { // condition = Character type
			case "A" : result = "Grade : A"; break; // case [char]
			case "B" : result = "Grade : B"; break;
			case "C" : result = "Grade : C"; break;
			case "D" : result = "Grade : D"; break;
			case "F" : result = "Grade : F"; break;
			default : result = "Grade is not matched A, B, C, D, F"; break;
		}
		System.out.println(result);
	}
}
```
