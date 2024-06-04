-----
### 프로그램 오류
-----
1. 프로그램 에러 / 오류 : 프로그램 실행 중 어떤 원인에 의해서 오작동 하거나 비정상적을 종료되는 결과를 초래하는 원인
2. 발생 시점에 따라 컴파일 에러(Complie Error)와 런타임 에러(Runtime Error)로 나뉠 수 있음
```
A. 컴파일 에러(Complie Error) : 컴파일 시 발생하는 에러
B. 런타임 에러(Runtime Error) : 프로그램 실행 도중 발생하는 에러
C. 논리적 에러(Logical Error) : 컴파일도 잘되고, 실행도 잘 되지만, 의도한 것과 다르게 동작하는 에러
```
3. 컴파일러는 소스 코드의 기본적인 사항은 컴파일 시 오류를 걸러낼 수 있지만, 실행 도중 발생할 수 있는 잠재적 오류까지 검사할 수 없음
   - 따라서, 컴파일이 잘 되었어도 실행 중 에러에 의해 잘못된 결과를 얻거나 프로그램이 비정상적 종료가 될 수 있음

4. 런타임 에러를 방지하기 위해 프로그램 실행 도중 발생할 수 있는 모든 경우의 수를 고려해 이에 대비하는 것이 필요
5. 자바에서는 실행(Runtime) 시 발생할 수 있는 오류를 '에러(Error)'와 '예외(Exception)'로 구분

-----
### 에러(Error)와 예외(Exception)
-----
1. 에러(Error) : 메모리 부족(OutOfMemoryError)이나 스택오버플로우(StackOverflowError)와 같이 일단 발생하면 복구할 수 없는 심각한 오류
2. 예외(Exception) : 발생하더라도 수습할 수 있는 비교적 덜 심각한 오류
   - 즉, 프로그래머가 이에 대해 적절한 코드를 미리 작성해놓음으로써, 프로그램의 비정상적 종료를 막을 수 있음
```
A. 에러 : 프로그램 코드에 의해서 수습될 수 없는 심각한 오류
B. 예외 : 프로그램 코드에 의해서 수습될 수 있는 다소 미약한 오류
```

-----
### 예외 클래스의 계층 구조
-----
1. 자바에서는 실행 시 발생할 수 있는 오류(Exception, Error)를 클래스로 정의
2. Exception, Error 클래스 모두 Object의 자손
<div align="center">
<img src="https://github.com/sooyounghan/HTTP/assets/34672301/79690086-212a-4f13-ae89-739a33190a11">
</div>

3. 모든 예외의 최고 조상은 Exception 클래스이며, 상속 계층도를 Exception 클래스로부터 도식화하면 다음과 같음
<div align="center">
<img src="https://github.com/sooyounghan/HTTP/assets/34672301/81d4ecad-75d7-49ce-8698-300a721304c1">
</div>

  - Exception 클래스와 그 자손들
  - RuntimeException 클래스와 그 자손들

4. RuntimeException 클래스들은 주로 프로그래머의 실수에 의해 발생될 수 있는 예외로 자바의 프로그래밍 요소들과 관계가 깊음
   - 배열의 범위를 벗어나는 경우 (ArrayIndexOutOfBoundsException)
   - 값이 null인 참조변수의 멤버를 호출 (NullPointerException)
   - 클래스간 형변환을 잘못 (ClassCastException)
   - 정수를 0으로 나누는 경우 (ArithmeticException)

5. Exception 클래스들은 주로 외부의 영향을 발생할 수 있는 것으로, 프로그램의 사용자들의 동작에 의해 발생하는 경우가 많음
   - 존재하지 않는 파일의 이름을 입력 (FileNotFoundException)
   - 실수로 클래스의 이름을 잘못 입력 (ClassNotFoundException)
   - 입력한 데이터의 형식이 잘못 (DataFormatException)

```
A. Exception 클래스 : 사용자의 실수와 같은 외적 요인에 의해 발생하는 예외
B. RuntimeException 클래스 : 프로그래머의 실수로 발생하는 예외
```
