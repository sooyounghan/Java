-----
### 재귀 호출 (Recursive Call)
-----
1. 메서드의 내부에서 메서드 자신을 다시 호출하는 것
2. 재귀 메서드 : 재귀호출을 하는 메서드
```java
void method() {
    method(); // 재귀 호출, 메서드 자신을 호출
}
```
3. 호출된 메서드는 '값에 의한 호출 (Call by Value)'를 통해 원래의 값이 아닌 복사된 값으로 작업
4. 호출한 메서드와 관계없이 독립적 작업 수행 가능
5. 자기 자신을 재귀호출 하면, 무한 반복에 빠지므로 중단 조건이 필수적
```java
void methodI(int n) {
    if(n == 0) return; // n이 값이 0일 때, 메서드 종료

    System.out.println(n);

    method(--n);
}
```
```java
void methodI(int n) {
    while(n != 0) {
      System.out.println(n);
    }
}
```
  - 매개변수 n을 1씩 감소시켜가면서 재귀호출을 하다가 n의 값이 0이 되면 재귀호출 중단

6. 재귀호출은 논리적 간결함 목적으로 사용

-----
### 대표적인 재귀 호출 - 팩토리얼(Factorial)
-----
```
f(n) = n * f(n - 1), 단 f(1) = 1
```
1. 팩토리얼 : 한 숫자가 1이 될 때 까지 1씩 감소시켜가면서 계속해서 곱해 나가는 것 (n!)

```java
class FactorialTest {
	public static void main(String args[]) {
		System.out.println(factorial(4)); // FactorialTest.factorial(4)
	}

	static long factorial(int n) {
		long result=0;

		if (n == 1) return 1;		

		return n * factorial(n-1); // 다시 메서드 자신을 호출
	}
}
```
<div align="center">
<img src="https://github.com/sooyounghan/JavaScript/assets/34672301/f429d7f6-5279-44e8-86c7-2290e51abb2b">
</div>

2. factorial 메서드가 static 메서드이므로 인스턴스를 생성하지 않고 직접 호출
3. main 메서드와 같은 클래스에 존재하므로 static 메서드를 호출할 때 클래스 이름 생략 가능
4. 예시) factorial(2)를 호출할 때의 진행 과정
   - factorial(2)를 호출하면 매개변수 n에 2가 복사
   - return 2 * factorial(1);을 계산하려면 factorial(1)을 호출한 결과 필요하므로, factorial(1)이 호출되고 매개변수 n에 1이 복사
   - if문의 조건식이 참이므로 1을 반환하면서 메서드 종료. factorial(1)을 호출한 곳으로 되돌아감
   - factorial(1)의 결과값인 1을 얻었으므로, return 문이 다음과 같이 변경
```java
return 2 * factorial(1);
= return 2 * 1;
= return 2;
```

-----
### StackOverflow
-----
1. 계속적인 재귀호출만 일어나면 메서드가 종료가 되지 않으므로 스택에 계속 데이터가 쌓임
2. 결국, 어느 시점에 이르러서는 결국 스택의 저장한계를 넘게 되어 StackOverflow Error 발생
3. 해결 방법 : 매개변수의 유효성 검사
```java
static int factorial(int n) {
    if(n <= 0 || n > 12 ) return -1; // 매개변수 n의 유효성 검사 추가
    if(n == 1) return 1;

    return n * factorial(n - 1);
}
```

  - 매개변수 n의 상한값을 12로 정한 이유 : 13!의 값이 int 타입의 최댓값보다 크기 때문임
  - 반복문으로 작성한 경우
```java
int factorial(int n) {
    int result = 1;

    while(n != 0) {
        result *= n--;

    return result;
}
```
    + 스택 오버플로우 에러와 같은 메모리 부족문제를 겪지 않으며, 속도가 더 빨라짐

```java
class FactorialTest2 {
	static long factorial(int n) {
		if(n <= 0 || n > 20) return -1;  // 매개 변수 유효성 검사
		if(n <= 1) 
			 return 1;
	    return n * factorial(n - 1); 
	}

	public static void main(String args[]) {
		int  n = 21;
		long result = 0;

		for(int i = 1; i <= n; i++) {
			result = factorial(i);

			if(result == -1) {
				System.out.printf("유효하지 않은 값입니다.(0<n<=20) : %d%n", n);
				break;
			}

			System.out.printf("%2d! = %20d%n", i, result);
		}
	} // main 끝
}
```
<div align="center">
<img src="https://github.com/sooyounghan/JavaScript/assets/34672301/c6dc654f-59dd-4e09-8e86-dba1ee31aef6">
</div>


-----
### 재귀호출 예제
-----
1. main메서드 역시 메서드이므로 자기 자신 호출 가능
   - 단, main 메서드가 종료되지 않고 호출스택에 계속 쌓이게 되므로 결국 호출 스택의 메모리 한계를 넘게 되어 StackOverflowError 발생
```java
class MainTest {
	public static void main(String args[]) {
		main(null);		// 재귀호출. 자기 자신을 다시 호출.
	}
}
```

2. x^1부터 x^n까지 합을 구하는 예제
```java
class PowerTest { 
	public static void main(String[] args) { 
		int x = 2; 
		int n = 5; 
		long result = 0; 

		for(int i = 1; i <= n; i++) { 
			result += power(x, i); 
		} 

		System.out.println(result); 
	} 

	static long power(int x, int n) { 
		if(n == 1) return x; 

		return x * power(x, n-1);
	} 
}
```
<div align="center">
<img src="https://github.com/sooyounghan/JavaScript/assets/34672301/34eb16d3-441e-4a3b-bf31-5f1cbf0f5026">
</div>
