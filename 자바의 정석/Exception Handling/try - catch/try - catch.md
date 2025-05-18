-----
### 예외처리 - try ~ catch
-----
1. 예외 처리(Exception Handling) : 프로그램 실행 시 발생할 수 있는 예기치 못한 예외의 발생에 대비한 코드를 작성
2. 예외 처리의 목적 : 예외의 발생으로 인한 실행 중인 프로그램의 갑작스런 비정상 종료를 막고, 정상적 실행 상태를 유지할 수 있도록 하는 것
3. 발생한 예외를 처리하지 못하면, 프로그램은 비정상적으로 종료되며, 처리되지 못한 예외(Uncaught Exception)는 JVM의 예외 처리기(UncaughtExceptionHandler)가 받아서 예외의 원인을 화면에 출력
4. 예외 처리를 위한 try ~ catch문 구조
```java
try {
  // 예외가 발생할 가능성이 있는 문장
} catch(Exception1 e1) {
  // Exception1이 발생했을 경우, 이를 처리하기 위한 문장
} catch(Exception2 e2) {
  // Exception2이 발생했을 경우, 이를 처리하기 위한 문장
} catch(ExceptionN eN) {
  // ExceptionN이 발생했을 경우, 이를 처리하기 위한 문장
}
```

5. 하나의 try 블럭 다음에는 여러 종류의 예외처리를 할 수 있도록 하나 이상의 catch 블럭이 올 수 있음
6. 이 중, 발생한 예외의 종류와 일치하는 단 한 개의 catch 블럭만 수행
7. 발생한 예외의 종류와 일치하는 catch 블럭이 없으면 예외는 처리되지 않음
8. try블럭, catch블럭 내 포함된 문장이 하나 뿐이어도 괄호 { } 생략 불가

-----
### 예외처리 - try ~ catch 예시
-----
```java
class ExceptionEx1 {
	public static void main(String[] args) 
   {
		try  {
			try	{	} catch (Exception e)	{ }
		} catch (Exception e)	{
			try	{	} catch (Exception e) { }	// 에러. 변수 e가 중복 선언
		} // try-catch 

		try  {

		} catch (Exception e)	{

		} // try-catch
	}	// main
}
```

1. 위 코드처럼 하나의 메서드 내에 여러 개의 try ~ catch 문 사용 가능
2. try 블럭 또는 catch 블럭 내 또 다른 try ~ catch 문 사용 가능 (catch 블럭 내의 코드에서도 예외가 발생할 수 있기 때문임)
3. catch 블럭의 괄호 내 선언된 변수는 catch 블럭 내에서만 유효하므로, 위의 모든 catch 블럭에 참조변수 'e' 하나만 사용해도 됨
4. 단, catch 블럭 내 또 하나의 try ~ catch 문이 포함된 경우, 같은 이름의 참조변수를 사용해서는 안 됨
5. 각 catch 블럭에 선언된 두 참조변수 영역이 서로 겹치므로 다른 이름을 사용해야 서로 구별되기 때문임

```java
class ExceptionEx2 {
	public static void main(String args[]) {
		int number = 100;
		int result = 0;

		for(int i=0; i < 10; i++) {
			result = number / (int)(Math.random() * 10); // 7번쨰 라인
			System.out.println(result);
		}
	} // main
}
```
<div align="center">
<img src="https://github.com/sooyounghan/HTTP/assets/34672301/2326d5eb-9e78-4e99-ade9-18010585f958">
</div>

  - 0으로 나누려 했기 때문에, ArithmeticException이 발생했으며, 위치는 main 메서드의 7번째 라인
  - ArithmeticException은 산술 연산 과정 중 오류가 있을 때 발생하는 예외
  - 정수는 0으로 나누는 것이 금지되어있기 때문에 발생
  - 하지만, 실수를 0으로 나누는 것은 금지되어 있지 않기 때문에, 예외가 발생하지 않음

```java
class ExceptionEx3 {
	public static void main(String args[]) {
		int number = 100;
		int result = 0;

		for(int i=0; i < 10; i++) {
			try {
				result = number / (int)(Math.random() * 10);
				System.out.println(result);
			} catch (ArithmeticException e)	{ // ArithmeticException이 발생하면 실행되는 코드
				System.out.println("0");	
			} // try-catch
		} // for
	} 
}
```
<div align="center">
<img src="https://github.com/sooyounghan/HTTP/assets/34672301/c81dfa11-d697-473a-b6db-9f34705f2fbf">
</div>

  - ArithmeticException이 발생하면 해당하는 catch 블럭을 찾아서 그 블럭 내 문장들을 실행한 다음 try ~ catch 문을 벗어나 for문의 다음 반복을 계속 수행하여 작업을 모두 마치고 정상적 종료

-----
### try ~ catch 흐름
-----
1. try 블럭 내 예외 발생 경우
   - 발생한 예외가 일치하는 catch 블럭이 있는지 확인
   - 일치하는 catch 블럭을 찾게 되면, 그 catch 블럭 내 문장들을 수행하고, 전체 try ~ catch 문을 빠져나가서 다음 문장 수행
   - 일치하는 catch 블럭을 찾지 못하게 되면, 예외는 처리되지 못함

2. try 블럭 내 예외가 발생하지 않은 경우
   - catch 블럭을 거치지 않고, 전체 try ~ catch 문을 빠져나가서 다음 문장 수행

```java
class ExceptionEx4 {
	public static void main(String args[]) {
			System.out.println(1);			
			System.out.println(2);
			try {
				System.out.println(3);
				System.out.println(4);
			} catch (Exception e)	{
				System.out.println(5); // 실행되지 않음
			} // try-catch
			System.out.println(6);
	}	// main
}
```
<div align="center">
<img src="https://github.com/sooyounghan/HTTP/assets/34672301/a60097d3-7914-4dbe-b0ad-c9d5c0dd80e7">
</div>

```java
class ExceptionEx5 {
	public static void main(String args[]) {
			System.out.println(1);			
			System.out.println(2);
			try {
				System.out.println(3);
				System.out.println(0/0);	// 0으로 나눠 고의로 ArithmeticException 발생
				System.out.println(4); 	// 실행되지 않음
			} catch (ArithmeticException ae)	{
				System.out.println(5);
			}	// try-catch
			System.out.println(6);
	}	// main
}
```
<div align="center">
<img src="https://github.com/sooyounghan/HTTP/assets/34672301/418f3ce7-ce5e-41ab-bc9e-eb2cbcaf4382">
</div>

  - 1, 2, 3을 출력한 다음 try블럭에서 예외가 발생했기 때문에, try 블럭을 바로 벗어나므로 4 미출력
  - 발생한 예외에 해당하는 catch 블럭으로 이동하여 문장 수행
  - 다음에는 전체 try~catch문을 벗어나 그 다음 문장을 실행하여 6을 출력

-----
### 예외의 발생과 catch 블럭
-----
1. catch 블럭은 괄호 ()와 블럭 {} 두 부분으로 나눠짐
2. 괄호 () 내에는 처리하고자 하는 예외와 같은 타입의 참조변수 하나를 선언해야함
3. 예외가 발생하면, 발생한 예외에 해당하는 클래스의 인스턴스가 만들어짐
   - 예를 들어, ArithmeticException이 발생하면 해당 인스턴스가 생성
   - 그리고 예외가 발생한 문장이 try 블럭에 포함되면, 이 예외를 처리할 catch 블럭이 있는지 찾음
4. 첫 번째 catch 블럭부터 차례로 내려가면서, catch 블럭의 괄호 () 내 선언된 참조변수의 종류와 생성된 예외클래스의 인스턴스에 instanceof 연산자를 이용해 검사하게 됨
   - 검사결과가 true인 catch블럭을 만날때 까지 계속 검사
5. 검사 결과가 true인 catch 블럭을 찾게 되면, 블럭에 있는 문장을 모두 수행한 후 try ~ catch 문을 빠져나가고 예외는 처리되지만, 하나도 없으면 예외는 처리되지 않음
6. 모든 예외 클래스는 Exception 클래스의 자손이므로, catch블럭의 괄호()에 Exception 클래스 타입의 참조변수를 선언해놓으면, 어떤 종류의 예외가 발생하더라도 catch 블럭에 의해 처리
```java
class ExceptionEx6 {
	public static void main(String args[]) {
		System.out.println(1);			
		System.out.println(2);
		try {
			System.out.println(3);
			System.out.println(0/0);
			System.out.println(4); 	// 실행되지 않음
		} catch (Exception e)	{	// ArithmeticException대신 Exception사용.
			System.out.println(5);
		}	// try-catch
		System.out.println(6);
	}	// main
}
```
<div align="center">
<img src="https://github.com/sooyounghan/HTTP/assets/34672301/418f3ce7-ce5e-41ab-bc9e-eb2cbcaf4382">
</div>

```java
class ExceptionEx7 {
	public static void main(String args[]) {
		System.out.println(1);			
		System.out.println(2);
		try {
			System.out.println(3);
			System.out.println(0/0); // 0으로 나누어 ArithmeticException 발생
			System.out.println(4); // 실행되지 않음
		} catch (ArithmeticException ae)	{
			if (ae instanceof ArithmeticException) 
				System.out.println("true");	
			System.out.println("ArithmeticException");
		} catch (Exception e)	{ // ArithmeticException을 제외한 모든 예외가 처리
			System.out.println("Exception");
		}	// try-catch
		System.out.println(6);
	}	// main
}
```
<div align="center">
<img src="https://github.com/sooyounghan/HTTP/assets/34672301/0b03195c-00a8-4368-8b3e-0703bd9e9221">
</div>

  - try 블럭에서 ArithmeticException이 발생했으므로, 하나씩 catch 블럭을 검사
  - 첫 번째 검사에서 일치하는 catch블럭을 찾았으므로 두 번째 catch 블럭은 검사하지 않음
  - 만일, try 블럭 내에서 위 예외가 아닌 다른 종류의 예외가 발생한 경우 두 번째 catch 블럭인 Exception 클래스 타입의 참조변수를 선언한 곳에서 처리

-----
### printStackTrace()과 getMessage()
-----
1. 예외가 발생했을 때 생성되는 예외 클래스의 인스턴스에는 발생한 예외에 대한 정보가 담겨 있으며, printStackTrace()과 getMessage()를 통해 정보를 얻을 수 있음
2. catch블럭의 괄호()에 선언된 참조 변수를 통해 이 인스턴스에 접근 가능하며, 참조변수는 선언된 catch 블럭 내에서만 사용 가능하며, 예외의 발생 원인을 알 수 있음
```
A. printStackTrace() : 예외 발생 당시 호출 스택(Call Stack)에 있었던 메서드 정보와 예외 메세지를 화면에 출력
B. getMessage() : 발생한 예외 클래스의 인스턴스에 저장된 메세지를 얻을 수 있음
```
```java
class ExceptionEx8 {
	public static void main(String args[]) {
		System.out.println(1);			
		System.out.println(2);
		try {
			System.out.println(3);
			System.out.println(0/0); // 예외 발생
			System.out.println(4); 	 // 실행되지 않음
		} catch (ArithmeticException ae)	{
			ae.printStackTrace(); // 참조변수 ae를 통해, 생성된 ArithmeticException 인스턴스에 접근 가능
			System.out.println("예외 메세지 : " + ae.getMessage());
		}	// try-catch
		System.out.println(6);
	}	// main
}
```
<div align="center">
<img src="https://github.com/sooyounghan/HTTP/assets/34672301/b65e3fb4-ba82-43e6-8e42-594a1b43f3ed">
</div>

-----
### 멀티 catch 블럭
-----
1. JDK1.7부터 여러 catch 블럭을 | 기호를 통해 하나의 catch블럭으로 합칠 수 있게 됨
2. 따라서, 중복된 코드를 줄일 수 있으며, | 기호로 연결할 수 있는 예외 클래스의 개수는 제한이 없음

```java
try {
  ...
} catch(ExceptionA e) {
  e.printStackTrace();
} catch(ExceptionB e2) {
  e2.printStackTrace();
}
```
```java
try {
  ...
} catch(ExceptionA | ExceptionB e) {
  e.printStackTrace();
}
```

3. 만약, 멀티 catch블럭의 | 기호로 연결된 예외 클래스가 조상과 자손 관계에 있다면, 컴파일 에러 발생
```java
try {
    ...
} catch(ParentException | ChildException e) { // Error
    e.printStackTrace();
}
```

  - 결국 조상 클래스만 써주는 것과 같은 의미이기 때문임
```java
try {
    ...
} catch(ParentException e) { 
    e.printStackTrace();
}
```

4. 멀티 catch는 하나의 catch 블럭으로 여러 예외를 처리하는 것이므로, 이로 처리하게 되었을 때 멀티 catch 블럭 내 실제 어떤 예외가 발생했는지 알 수 없음
   - 따라서, 참조변수 e로 멀티 catch 블럭에 | 기호로 연결된 예외 클래스들의 공통 분모인 조상 예외 클래스에 선언된 멤버만 사용 가능
```java
try {
    ...
} catch(ExceptionA | ExceptionB e) {
    e.methodA(); // Error. ExceptionA에 선언된 methodA() 호출 불가

    if(e instanceof ExceptionA) {
        ExceptionA e1 = (ExceptionA) e;
        e1.methodA(); // Ok. ExceptionA에 선언된 메서드 호출 가능
    } else { // if(e instanceof ExceptionB)
        ...
    }

    e.printStackTrace();
}
```
  - 필요하다면, 위와 같이 instanceof 연산자로 어떤 에외가 발생한 것인지 확인하고 개별적 처리 가능

5. 멀티 catch 블럭에 선언된 참조변수 e는 상수이므로 값을 변경할 수 없는 제약 존재
   - 이는 여러 catch 블럭이 하나의 참조변수를 공유하기 떄문에 생기는 제약으로 실제로 참조변수의 값을 변경할 일은 거의 없음
