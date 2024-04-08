-----
### 참조 타입 (Referenct Type)
-----

1. 배열 타입, 열거 타입, 클래스, 인터페이스
2. 변수에 주소를 저장하여 이를 통해 객체를 참조하는 타입

-----
### 배열 (Array)
-----

 1. 같은 타입의 데이터를 연속된 공간에 저장하는 자료구조
 2. 각 데이터 저장 위치는 인덱스를 부여해 접근 (인덱스의 시작은 0이며, 1씩 증가)
  - 장점
    + 중복된 변수 선언을 줄여서 효율적 사용 가능
    + 반복문 이용해 요소 쉽게 처리

  - 단점
    + 동일한 데이터 타입이어야만 가능
    + 배열의 크기를 변경할 수 없음 (초기 생성된 크기에서 더 줄이거나 크게할 수 없음) : 컴파일 에러는 없으나 ArrayIndexOutOfBoundsException 발생  
    + 순차적으로 앞에서부터 접근해야함
      
```java
public class ArrayEx02 {
	public static void main(String[] args) {
		/*
		 * 배열의 단점
		 */
		
		/*
		 * 1. 동일한 데이터 타입만 가능
		 *   byte[] arr1 = { 1, 100, 3.14 }; // 3.14 = float(double) type -> type mismatch.
		 *   byte[] arr1 = { 1, 100, 127L }; // 127L = long -> type mismatch.
		 *   char[] arr2 = { 'A', 'B', 'a', 3.24, true }; // 3.24 = float(double) / true = boolean -> type mismatch.
		 *   boolean[] arr3 = { true, false, 3.14, 'a' }; // 3.14 = float(double) / 'a' = char -> type mismatch.
		 */
		byte[] arr1 = { 1, 100, 127 }; // byte array
		char[] arr2 = { 'A', 'a' }; // char array
		boolean[] arr3 = { true, false }; // boolean array

		/*
		 * 2. 배열의 크기 변경 불가 -> 배열 인덱스를 초과하여 데이터 삽입 시 ArrayIndexOutOfBoundsException 발생
		 */
		String[] names = { "홍길동", "세종대왕" }; // String array
		// names.length() = 2 -> index : 0 ~ 1
		// names[2] = "김구"; // ArrayIndexOutOfBoundsException 발생
		int[] arr4 = { 100, 200, 300 };
		// arr[4] = 400; // ArrayIndexOutOfBoundsException 발생
		
		/*
		 * new 연산자를 통한 배열 생성
		 *   : 타입[] 배열명 = new 타입[배열의 크기]; // 배열의 크기 지정
		 */
		double[] arr5 = new double[1]; // 배열의 크기 : 1 (index : 0)
		arr5[0] = 100; // arr5[0]에 100 저장
		// arr5[1] = 200; // ArrayIndexOutOfBoundsException 발생
		
		char[] arr6 = new char[] { 'A', 'b', 'C' };
		//char[] arr6 = new char[2] { 'A', 'B', 'C' }; // 값이 제공되면, 배열의 크기는 제시 불가
	}
}
```

-----
# 배열 선언
-----
1. 타입[] 변수; 또는 타입 변수[];

```java
int[] intArray; or int intArray[];
double[] doubleArray; or double doubleArray[];
String[] strArray; or String strArray[];
```

2. 배열은 참조변수이므로 배열 생성되기 전 null로 초기화 가능 ( 타입[] 변수 = null; )

< 타입 별 항목의 기본값 >

<div align = "center">
  <img src = "https://github.com/sooyounghan/JAVA/assets/34672301/94452dd8-fad7-463f-9c0a-3f3dfb8b3c18">
</div>

3. 변수 선언과 동시에 값 목록 대입 (초기값)
 - 값 목록으로 배열 생성 : 데이터타입[] 변수 = { 값0, 값1, ..., 값n };
 - 변수 선언 후 값 목록 대입 (값 목록에 대한 크기를 확인)
    > 데이터타입[] 변수;
    > 변수 = new 데이터타입[]{ 값0, 값1, ..., 값n };

  - new 연산자로 배열 생성
    + 배열 생성 시 값 목록을 가지고 있지 않음
    + 향후 값들을 저장할 배열을 미리 생성하고 싶은 경우 (명시적 크기 선언)
      > 데이터타입[] 변수 = new 데이터타입[배열의 크기];   
      > 데이터타입[] 변수   
      > 변수 = new 데이터타입[배열의 크기];

    + 값 목록을 대입하면, 배열의 크기를 제시할 수 없음 = 값을 제공하면 배열의 크기를 자동적 계산하므로 크기 제시 불가
      > int[] num = new int[5]{ 2, 3, 4 }; (X)   
      > int[] temp = new int[2]{ 3, 4, 5 }; (X)   
      > temp 배열 (4byte 공간 2개 할당했으나 4byte 공간 3개 선언 -> 오류 발생)

-----
### 배열의 길이
-----
1. 배열에 저장할 수 있는 전체 항목 수 (배열명.length) → return type : int [읽기만 가능]
2. ArrayIndexOutOfBoundsException : 배열의 인덱스를 초과하여 접근할 경우 생기는 예외

-----
### 향상된 for문
-----
  - 일반적인 for문과 달리 첫 요소부터 마지막 요소까지 산출 (일반적인 배열 : 조건에 따라 일부 요소만 산출 가능)
    
```java
public class ArrayEx01_p177 {
	public static void main(String[] args) {
		/*
		 * 배열의 선언 
		 *   - 타입[] 배열명;
		 *   - 타입 배열명[];
		 *   
		 *   - 값 목록 대입 :
		 *     * 타입[] 배열명 = { 값1, 값2, ..., 값n };
		 *     * 타입[] 배열명;
		 *       배열명 = new 타입[]{ 값1, ..., 값n };
		 */
		// 배열 선언
 		// int[] scores; // (= int scores[];)

		// 배열 선언과 동시에 값을 초기화하여 배열 생성
		// 배열은 동일한 타입의 데이터만 가능하며 초기 생성된 크기에서 늘리거나 줄이기 불가
		int[] scores = { 100, 90, 80, 70, 60, 40, 50 }; 
		System.out.println("---------------------");
		System.out.println();
		scores[4] = 50; // 배열의 index를 통해 값 변경 가능
		
		// 생성된 배열의 길이 확인 : 배열명.length - return type - int
		int arrLen = scores.length;
		System.out.println("Array scores's length = " + arrLen);
		
		System.out.println();
		System.out.println("---------------------");
		System.out.println();
		
		/*
		 * 배열값 추출
		 *  1. index를 통한 배열값 추출
		 */
		int score = scores[0]; // score 배열의 0번 index의 값 저장(100)
		System.out.println("score(Variable) = " + score); 
		System.out.println("socres[0](Array's index) = " + scores[0]); // 배열의 index
		score = scores[1]; // score 배열의 1번 index의 값 저장(90)
		System.out.println("score(Variable) = " + score);
		System.out.println("socres[1](Array's index) = " + scores[1]); // 배열의 index
		
		System.out.println();
		System.out.println("----------- for ----------");
		System.out.println();
		
		/*
		 * 2. 반복문을 통한 조건에 해당하는 배열값 추출
		 */
		for(int i = 0; i < scores.length; i++) { // 배열의 첫 인덱스(0, 첫 원소)부터 배열의 길이 - 1(마지막 원소)까지
			System.out.println("scores[" + i + "] = " + scores[i]);
		}
		
		System.out.println();
		System.out.println("----------- 향상된 for ----------");
		System.out.println();
		
		/* 
		 * 향상된 for문 
		 *   for(타입 변수명 : 배열명, 컬렉션명 등) {
		 *   	실행문;
		 *   }
		 */
		
		for(int i : scores) {
			System.out.println("scores[" + i + "] = " + scores[i]);
		}
		
		/*
		 * ArrayIndexOutOfBoundsException (배열의 인덱스를 초과하여 접근할 때 발생하는 예외)
		 *    int[] score = { 20, 30, 40, 60}; // scores.length = 4
		 *    for(int i = 0; i < 5; i++) { // scores 배열에 총 5번 접근 (scores 배열의 인덱스 초과)
		 * 		   System.out.println(score[i]); // ArrayIndexOutOfBoundsException 예외 발생
		 *    }
		 */
		
		System.out.println();
		System.out.println("---------------------");
		System.out.println();
		
	}
}
```

-----
### 배열 생성 시 메모리 영역 중 Heap영역과 Stack 영역의 변화
-----

<div align = "center">
<img width="501" alt="다운로드 (10)" src="https://github.com/sooyounghan/JAVA/assets/34672301/4dbf530c-9932-4f1e-99a3-be6bc17e4ac9">
</div>

-----
### String 배열
-----
1. String(문자열을 저장하는 Class)를 변수로 담는 String배열
2. String형 : 문자열을 저장하는 Class :
  > String name = new String(“홍”); (= String name = “홍”) [new 연산자 생략 가능]
3. String형은 읽을 수 있지만 값 수정은 불가

```java
String result = "Java";
result = result + 8; // (result = new String(result+8)) = 즉, 새로운 문자열이 생성
```
<div align = "center">
<img src = "https://github.com/sooyounghan/JAVA/assets/34672301/9cc1ebce-3f02-4168-b0c0-ca9fbd0490fe">
</div>

```java
import java.util.ArrayList; // List Collection import
import java.util.List;

/*
 * 변수 타입
 *  - 기본 타입 (Primitive Type) : Literal값 저장
 *    : 정수[byte(1) < char(2) < short(2) < int(4, base) < long(8)] < 실수 : [float(4) < double(8, base)]
 *      논리형(boolean(1))
 *  - 참조 타입 (Reference Type) : 주소 값을 저장
 *    : Array, Enum, Class, Interface
 */

public class ArrayEx03 {
	public static void main(String[] args) {
		/*
		 * Reference Type
		 */
		int[] arr1 = new int[5]; // int type, 5개의 value이 저장할 수 있는 배열 선언
		/*
		 * = int[] arr1 = null; 
		 *   arr1 = new int[5]; (new int[5] : 내부적 실행 순서 1 -> arr1에 할당 : 내부적 실행 순서 2)
		 */
		
		/*
		 *  배열의 내부 주소 출력
		 *   = [I@a38d7a3 : [I@16진수 ([ : array, I : integer, @ : at)
		 *   = HashCode
		 */

		System.out.println(arr1);

		for(int i = 0; i < arr1.length; i++) { // for문
			System.out.println(arr1[i]); // 배열 선언 후 각 요소에 초기값으로 초기화되어 있음
		}
		
		System.out.println();
		System.out.println("-----------");
		System.out.println();
		
		for(int arr : arr1) { // 향상된 for문
			System.out.println(arr);
		}
		
		System.out.println();
		System.out.println("-----------");
		System.out.println();
		
		double[] arr2 = new double[5]; // double형 array
		
		for(int i = 0; i < arr2.length; i++) { // for문
			System.out.println(arr2[i]); // 배열 선언 후 각 요소에 초기값으로 초기화되어 있음
		}
		
		System.out.println();
		System.out.println("-----------");
		System.out.println();
		
		for(double arr : arr2) { // 향상된 for문
			System.out.println(arr);
		}
		
		System.out.println();
		System.out.println("-----------");
		System.out.println();
		
		boolean[] arr3 = new boolean[5]; // boolean형 array
		
		for(int i = 0; i < arr3.length; i++) { // for문
			System.out.println(arr3[i]); // 배열 선언 후 각 요소에 초기값으로 초기화되어 있음
		}
		
		System.out.println();
		System.out.println("-----------");
		System.out.println();
		
		for(boolean arr : arr3) { // 향상된 for문
			System.out.println(arr);
		}
		
		System.out.println();
		System.out.println("-----------");
		System.out.println();
		
		char[] arr4 = new char[5];
		
		for(int i = 0; i < arr4.length; i++) { // for문
			System.out.println(arr4[i]); // 배열 선언 후 각 요소에 초기값으로 초기화되어 있음
		}
		
		System.out.println();
		System.out.println("-----------");
		System.out.println();
		
		for(char arr : arr4) { // 향상된 for문
			System.out.println(arr);
		}
		
		/*
		 * String Class
		 */
		
		String[] arr5 = new String[5];
		
		String name = "홍"; // = (String name = new String("홍"))
		
		for(int i = 0; i < arr5.length; i++) { // for문
			System.out.println(arr5[i]); // 배열 선언 후 각 요소에 초기값으로 초기화되어 있음
		}
		
		System.out.println();
		System.out.println("-----------");
		System.out.println();
		
		for(String arr : arr5) { // 향상된 for문
			System.out.println(arr); // String의 초기값은 null(String : Class)
		}
		
		System.out.println();
		System.out.println("-----------");
		System.out.println();
		
		/*
		 * List Interface : List는 java.util Package에 존재 > import java.util.*;
		 */
		
		/*
		 * List Interface -> 구현 클래스 : ArrayList
		 */
		List[] arr6 = new ArrayList[5]; // List(Interface) <- ArrayList(Class)
		System.out.println(arr6);
		System.out.println();
		/*
		 *  [Ljava.util.ArrayList;@77f99a05
		 *    java.util.ArrayList클래스;@(주소)16진수
		 */
		
		for(int i = 0; i < arr6.length; i++) { // for문
			System.out.println(arr6[i]); // 배열 선언 후 각 요소에 초기값으로 초기화되어 있음
		} // Interface의 초기값 : null
		
		System.out.println();
		System.out.println("-----------");
		System.out.println();
		
		for(List arr : arr6) { // 향상된 for문
			System.out.println(arr); // String의 초기값은 null(String : Class)
		}
	}
}
```

# 다차원 배열 : 2차원 배열
  - 2차원 배열 : 배열안에 배열이 들어감 ([] : 차원의 개수)
     
<div align = "center">
<img width="403" alt="제목 없음" src="https://github.com/sooyounghan/JAVA/assets/34672301/ce9c2e89-a622-487b-bf79-ed7bbed090db">
</div>

<div align = "center">
<img width="452" alt="다운로드 (12)" src="https://github.com/sooyounghan/JAVA/assets/34672301/98a1440e-2ec0-4fea-996d-aeee905a2308">
</div>

```java
/*
 * 다차원 배열
 *   - 2차원 이상의 배열
 *   - []의 개수 : 차원의 수
 *   - 형식 : 타입[][] 배열명;
 *           타입[] 배열명[];
 *           타입 배열명[][];
 */
public class ArrayEx05_p190 {
	public static void main(String[] args) {
		/*
		 * 학생 1명당 5과목의 시험 성적을 입력하되, 총 3명의 시험성적을 배열로 저장
		 */
		// int[] scores = { 50, 70, 60, 50, 30 }; // 1차원 배열
		
		int[][] scores = {  { 100, 60, 50, 60, 50 },
				{ 40, 60, 70, 80, 76 },
				{ 40, 50, 60, 64, 64 } };
		// = int[][] score = new int[][] { {..동일..}, {...}, {...} };
                // = int[][] score = new int[3][5];
		int scoresLen = scores.length; // scores의 1차원 배열(학생 수) 크기 : 3
		System.out.println("student = " + scores.length); // 학생수를 의미
		System.out.println("scores address = " + scores); // [I@a38d7a3 -> [[I@a38d7a3 : [[I@16진수 형태 ([[ : 2차원 배열, I : Integer, @ : 주소)
		System.out.println("scores[0] address = " + scores[0]); // score[0]의 주소 (score[3][5] 주소와 다름
		System.out.println("scores[0] count = " + scores[0].length); // score[0][]의 요소 개수
		System.out.println("scores[1] address = " + scores[1]); // score[1]의 주소 (sscore[3][5] 주소와 다름
		System.out.println("scores[1] count = " + scores[1].length); // score[1][]의 요소 개수
		System.out.println("scores[2] address = " + scores[2]); // score[2]의 주소 (score[3][5] 주소와 다름
		System.out.println("scores[2] count = " + scores[2].length); // score[0][2]의 요소 개수
		 /*
		  * score[i]의 주소는 각각 다름
		  */
		
		System.out.println();
		
		/*
		 * 2차원 배열의 원소 추출
		 */

		for(int i = 0; i < scores.length; i++) { 
			/*
			 *  1차원 배열 부분 해당되는 반복문(i = 0, 1, 2)
			 *  (scores[0] ~ scores[2])
			 */
			System.out.println("scores[" + i + "] = " + scores[i]); // scores[i]는 scores[][]를 참조하는 주소 저장
			for(int j = 0; j < scores[i].length; j++) { 
				/*
				 *  2차원 배열 부분 해당되는 반복문
				 *  i = 0 : scores[0], j = (scores[0][0] ~ scores[0][5])
				 *  i = 1 : scores[1], j = (scores[1][0] ~ scores[1][5])
				 *  i = 2 : scores[2], j =(scores[2][0] ~ scores[2][5])
				 */
				System.out.print("scores[" + i + "]" + "[" + j +"] =" + scores[i][j] + ", ");
			}
			System.out.println();
			System.out.println();
		}
	}
}
```

-----
# 가변 배열
-----
- 다차원 배열에서의 마지막 차수 크기를 지정하지 않고 각각 다르게 설정

<div align = "center">
<img width="470" alt="다운로드 (14)" src="https://github.com/sooyounghan/JAVA/assets/34672301/6901ec40-04fe-4458-935f-9eeff599b693">
</div>

```java
/*
 * 가변 배열 
 *   : 다차원 배열에서 마지막 차수의 크기를 지정하지 않고, 각각 다르게 지정
 */
public class ArrayEx06 {
	public static void main(String[] args) {
		/*
		 * 가변 배열 (3명의 시험 성적을 배열에 지정하되, 그 성적은 수는 자유롭다.)
		 */
		// 3명의 값을 입력받되, 마지막 차수의 크기는 지정하지 않아 자유롭게 크기 지정 가능
		int[][] scores = {  { 100, 60, 50, 60, 50 },
				{ 40, 60 },
				{ 40, 50, 60, 64} }; // 값 목록으로 할당
		/* = int[][] scores = new int[3][];
		*    scores[0] = new int[5];
		*	 scores[1] = new int[2];
		*        scores[2] = new int[4]; (new 연산자 이용)
		*/
		
		System.out.println("student = " + scores.length); // scores의 1차원 배열(학생 수) 크기 : 3
		System.out.println("scores address = " + scores); 
		System.out.println("scores[0] address = " + scores[0]); // score[0]의 주소 (score[3][5] 주소와 다름
		System.out.println("scores[0] count = " + scores[0].length); // score[0][]의 요소 개수 : 5
		System.out.println("scores[1] address = " + scores[1]); // score[1]의 주소 (sscore[3][5] 주소와 다름
		System.out.println("scores[1] count = " + scores[1].length); // score[1][]의 요소 개수 : 2
		System.out.println("scores[2] address = " + scores[2]); // score[2]의 주소 (score[3][5] 주소와 다름
		System.out.println("scores[2] count = " + scores[2].length); // score[0][2]의 요소 개수 : 4
		 /*
		  * score[i]의 주소는 각각 다름
		  */
		
		System.out.println();
		
		/*
		 * 2차원 배열의 원소 추출
		 */

		for(int i = 0; i < scores.length; i++) { 
			/*
			 *  1차원 배열 부분 해당되는 반복문(i = 0, 1, 2)
			 *  (scores[0] ~ scores[2])
			 */
			for(int j = 0; j < scores[i].length; j++) { 
				/*
				 *  2차원 배열 부분 해당되는 반복문 (가변배열이므로 항상 그 값이 다름)
				 *  i = 0 : scores[0], j = 5 (scores[0][0] ~ scores[0][4])
				 *  i = 1 : scores[1], j = 2 (scores[1][0] ~ scores[1][1])
				 *  i = 2 : scores[2], j = 4 (scores[2][0] ~ scores[2][3])
				 */
				System.out.print("scores[" + i + "]" + "[" + j +"] =" + scores[i][j] + ", ");
			}
			System.out.println();
			System.out.println();
		}
	}
}
```
-----
### Swap (교환)과 버블 정렬
-----
1. Swap
```java
		/*
		 * Swap
		 */
		int a = 100;
		int b = 10;
		System.out.println("a = " + a + ", " + "b = " + b);
		
		int temp; // 두 값을 바꾸는데 사용할 임시 변수
		
		/*
		 * 조건문 사용
		 */
		if(a > b) temp = a;
		else temp = b;
		System.out.println("a = " + a + ", " + "b = " + b);
		System.out.println();
		
		/*
		 * Swap
		 */
		temp = 0;
		a = b;
		b = temp;
		System.out.println("a = " + a + ", " + "b = " + b);
		System.out.println();
```

2. 버블정렬
- 버블 정렬은 첫 번째 자료와 두 번째 자료를, 두 번째 자료와 세 번째 자료를, 세 번째와 네 번째를, … (마지막-1)번째 자료와 마지막 자료를 비교하여 교환하면서 자료를 정렬
- 1회전을 수행하고 나면 가장 큰 자료가 맨 뒤로 이동하므로 2회전에서는 맨 끝에 있는 자료는 정렬에서 제외
- 2회전을 수행하고 나면 끝에서 두 번째 자료까지는 정렬에서 제외
- 이렇게 정렬을 1회전 수행할 때마다 정렬에서 제외되는 데이터가 하나씩 늘어남

<div align = "center">
<img src = "https://github.com/sooyounghan/JAVA/assets/34672301/20927634-e9f0-42e3-95e9-a68355538d1b">
</div>

```java
/*
 * Bubble Sort
 */
public class Swap {
	public static void main(String[] args) {
		/*
		 * 10개의 데이터를 랜덤하게 배열하게 저장한 후 가장 큰 수를 출력하는 프로그램 구현
		 */
		int[] data = new int[10];
		int max = Integer.MIN_VALUE;
		
		for(int i = 0; i < data.length; i++) {
			int x = (int)(Math.random() * 10) + 1;
		
			data[i] = x;
		}
		
		System.out.print("Data : ");
		for(int i = 0; i < data.length; i++) {
			System.out.print(data[i] + " ");
		}
		System.out.println();

		// Bubble Sort
		for(int i = 0; i < data.length - 1; i++) { // 총 data.length - 1만큼 반복
			for(int j = 0; j < data.length-1-i; j++) { // 1회전 시 마지막 인덱스는 제외됨
				if(data[j] <= data[j+1]) {
					int temp_num = data[j];
					data[j] = data[j+1];
					data[j+1] = temp_num;
				}
			}
			
			if(data[i] > max) {
				max = data[i];
			}
		}
		
		System.out.print("Sorted Data : ");
		for(int i = 0; i < data.length; i++) {
			System.out.print(data[i] + " ");
		}
		System.out.println();
		System.out.println("max = " + max);
	}
}
```
-----
### 배열의 복사
-----
- 배열은 한 번 생성하면 크기 변경 불가 → 더 많은 저장 공간이 필요하다면, 보다 큰 배열을 새로 만들고 이전 배열로 항목 값들을 복사

- 1. for문 이용
- 2. System.arrayCopy() method 이용
- 3. Arrays Class 이용 [java.util package]

- Arrays.copyOf(int[] original, int newLength) : 원본 배열을 새로운 길이만큼 복사 → return type : int[] (값이 복사)

 < 얕은 복사(Swallow Copy) / 깊은 복사(Deep Copy) >
1. 얕은 복사 (Shallow Copy) : 원본 배열의 크기가 클 때 시간과 메모리 공간 절약
  - 원본 배열의 주소값을 참조 (동일 주소 참조)
  - 메모리 주소 값을 복사
  - 동일한 메모리 주소 값을 참조하였으므로 데이터 값이 변경되면, 데이터 같이 변경 (데이터 공유)
<div align = "center>
  <img width="447" alt="다운로드 (16)" src="https://github.com/sooyounghan/JAVA/assets/34672301/458affdf-6d84-49f2-9c7c-91600bff82d0">
</div>

```java
/*
 *  깊은 복사(Deep Copy) : 실제 값을 메모리 공간에 복사
 */
public class ArrayCopyEx02 {
	public static void main(String[] args) {
		/*
		 * Shallow Copy
		 */
		int[] a = null; // Main {} 내 local variable : a
		{ 
			/*
			 * Block
			 */
			int[] b; // { }내 local variable : b
			b = new int[5];	// heap 영역 내 메모리 할당 <- 참조 변수 b가 주소 참조
			/*
			 * heap영역은 프로그램이 종료되면, 메모리에서 해제
			 */
			b[0] = 100; 
			a = b; // Shallow Copy (주소값 복사)
			// 변수 a를 통해서 b의 주소를 참조해 작업 실행
		}
		
		System.out.println("a = " + a); // a : null -> shallow copy로 b의 주소 값 참조
		System.out.println("a[0] = " + a[0]); // a = b의 주소를 참조하는 배열이므로, b에서 선언했던 0번째 배열의 값 접근 가능
 		// System.out.println("b = " + b); // error. b는 { }내 local variable이므로 사용 영역을 벗어남
	}
}
```

2. 깊은 복사 (Deep Copy) : 원본 배열의 크기가 작거나 복사된 배열을 변경할 때 사용
  - 원본 배열의 모든 요소를 새로운 배열로 복사
  - 실제 값을 메모리 공간에 복사 (System.arrayCopy(), Arrays.copyOf() method) → 메모리 공간을 활용하여 메모리 낭비 문제 발생
  - 값을 복사하였으므로 원본 요소의 값이 변경되더라도 복사된 배열의 값은 변경되지 않음
<div align = "center>
<img width="451" alt="다운로드 (17)" src="https://github.com/sooyounghan/JAVA/assets/34672301/53ce0767-3f1e-4582-9587-5a60c934d587">
</div>

```java
import java.util.*; // Arrays Class

/*
 * Array Copy 
 *  - for문 이용
 *  - System.arrayCopy() 이용
 *  - Arrays Class 이용 : java.util Package
 *  
 *  얕은 복사(Shallow Copy) / 깊은 복사 (Deep Copy)
 *    - 얕은 복사(Shallow Copy) : 주소 값을 복사
 *    - 깊은 복사(Deep Copy) : 실제 값을 메모리 공간에 복사
 */
public class ArrayCopyEx01_p195 {
	public static void main(String[] args) {
		/*
		 * 11~15를 저장하는 정수 배열을 복사
		 */
		int[] arr = { 11, 12, 13, 14, 15 }; // Original

		System.out.println("Original Address = " + arr); // 배열 arr의 주소
		// Arrays.toString() : static method(클래스명.메소드명()) 배열을 문자열로 변환 -> return type : String
		String result = Arrays.toString(arr);
		System.out.println("Original = " + result); // [11, 12, 13, 14, 15]
		System.out.println();
		
		/*
		 * Shallow Copy : 주소 값을 복사
		 */
		int[] shallowCopy = arr; // Copy Array : shallowCopy (arr의 주소를 통해 복사가 됨 - Shallow Copy)
		System.out.println("Shallow Copy Address = " + shallowCopy); // shallowCopy 배열 주소
		System.out.println("Shallow Copy = " + Arrays.toString(shallowCopy)); // arr배열과 동일한 값 출력
		System.out.println();
		
		/*
		 * Deep Copy : 실제 값을 복사
		 * 
		 * Arrays Class
		 *  - Arrays.copyOf(int[] Original, int new Length);
		 *  - Arrays.copyOf(원본 배열, 배열 길이);
		 */
		int[] deepCopy = Arrays.copyOf(arr, arr.length);
		System.out.println("Deep Copy Address = " + deepCopy); // deepCopy 배열 주소
		System.out.println("Deep Copy = " + Arrays.toString(deepCopy)); // arr배열과 동일한 값 출력
		System.out.println();
		
		System.out.println("------ 원본 배열 값 변경 ------"); // arr배열과 동일한 값 출력
		arr[0] = 100; // arr의 첫 번째 요소를 100으로 변경
		
		System.out.println();
		System.out.println("------ 값 변경 후 결과 ------");
		System.out.println("Original = " + result); // [100, 12, 13, 14, 15]
		System.out.println("Shallow Copy = " + Arrays.toString(shallowCopy)); // 변경된 arr 배열의 값 출력
		System.out.println("Deep Copy = " + Arrays.toString(deepCopy)); // 기존의 arr배열과 동일한 값 출력
	}
}
```

< System.arrayCopy()를 이용한 Deep Copy – System Class >
  - System.arrayCopy(Obejct src, int srcPos, Object dest, int destPos, int length)
    + src : source array (원본 배열)
    + srcPos : starting position in the source array (원본 배열에 복사가 시작될 위치)
    + dest : destination array (복사 배열)
    + destPos : starting position in the destination array (복사 배열에 값을 넣을 시작 위치)
    + length : the number of array elements to be copied (원본 배열에 복사를 할 요소 갯수)
```java
import java.util.*; // Arrays Class

/*
 * 깊은 복사(Deep Copy) : 실제 값을 메모리 공간에 복사
 */
public class ArrayCopyEx01_p195 {
	public static void main(String[] args) {
		/*
		 * 11~15를 저장하는 정수 배열을 복사
		 */
		int[] arr = { 11, 12, 13, 14, 15 }; // Original

		// Arrays.toString() : static method(클래스명.메소드명()) 배열을 문자열로 변환 -> return type : String
		System.out.println("Original = " + Arrays.toString(arr)); // [11, 12, 13, 14, 15]
		System.out.println();
		
		/*
		 * Deep Copy : 실제 값을 복사
		 * 
		 * System.arrayCopy(Obejct src, int srcPos, Object dest, int destPos, int length)
		 *   - src : source array (원본 배열)
		 *   - srcPos : starting position in the source array (원본 배열에 복사가 시작될 위치)
		 *   - dest : destination array (복사 배열)
		 *   - destPos : starting position in the destination array (복사 배열에 값을 넣을 시작 위치)
		 *   - length : the number of array elements to be copied (원본 -> 복사 배열로 복사를 할 요소 갯수)
		 */
		int[] deepCopy = new int[10];
		System.out.println("------ Deep Copy ------");
		System.out.println("Before Deep Copy = " + Arrays.toString(deepCopy)); // 기존의 arr배열과 동일한 값 출력
		
		System.arraycopy(arr, 0, deepCopy, 0, arr.length); // 배열 arr의 0번 인덱스부터 arr.length(=5)개만큼 복사하여 deepCopy 배열 0번 인덱스에 저장
		System.out.println("After Deep Copy = " + Arrays.toString(deepCopy)); // 기존의 arr배열과 동일한 값 출력
	}
}
```

-----
### 열거타입 (Enumeration Type – Enum type)
-----
- 상수를 저장하는 타입

```java
 public enum Week {
            MONDAY,
            TUESDAY,
            WEDNESDAY,
            THURSDAY,
            FRIDAY,
            SATURDAY,
            SUNDAY
        }
```

- 열거타입 열거타입변수명 = 열거타입.열거상수; (열거상수는 열거타입에 나열된 상수만 가능)
```java
Week today;
today = Week.MONDAY;
```
