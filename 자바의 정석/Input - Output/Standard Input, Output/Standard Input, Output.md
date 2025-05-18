-----
### 표준 입출력
-----
1. 표준 입출력은 콘솔(Console, 도스창)을 통한 데이터 입력과 콘솔로의 데이터 출력 의미
2. 자바에서는 표준 입출력(Standard I/O)을 위해 3가지 입출력 스트림 (System.in, System.out, System.err) 제공
3. 자바 애플리케이션 실행과 동시에 사용할 수 있게 자동적으로 생성되므로 개발자가 별도로 스트림을 생성하는 코드를 작성하지 않아도 됨

```java
A. System.in : 콘솔로부터 데이터를 입력받는데 사용
B. System.out : 콘솔로 데이터를 출력하는데 사용
C. System.err : 콘솔로 데이터를 출력하는데 사용
```

<div align="center">
<img src="https://github.com/sooyounghan/HTTP/assets/34672301/3c8e6dc0-3ec5-4591-a889-effe48568832">
</div>

4. in, out, err은 System 클래스의 선언된 클래스 변수 (static변수)
5. out, in, err의 타입은 InputStream과 PrintStream
   - 실제로는 버퍼를 사용하는 BufferedInputStream과 BufferedOutputStream의 인스턴스 사용
```java
public final class System {
    public final static InputStream in = nullInputStream();
    public final static PrintStream out = nullPrintStream();
    public final static PrintStream err = nullPrintStream();
    ...
}
```

```java
import java.io.*;

class StandardIOEx1 {
	public static void main(String[] args) {
		try {
			int input = 0;

			while((input=System.in.read())!=-1) {
				System.out.println("input :" + input + ", (char)input :" + (char)input);
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	} // main
}
```

6. 콘솔 입력은 버퍼를 가지고 있기 때문에, Backspace 키를 이용해 편집이 가능하며, 한 번에 버퍼의 크기만큼 입력 가능
7. Enter 키나 입력의 끝(EOF)을 알리는 Ctrl + Z키를 누르기 전까지는 아직 데이터가 입력 중인 것으로 간주되어 커서가 계속 입력을 기다리는 상태 (Blocking 상태)로 존재
8. 콘솔에 데이터를 입력하고 Enter 키를 누르면, 입력 대기 상태에서 벗어나 입력된 데이터를 읽기 시작하고 입력된 데이터를 모두 읽으면 다시 입력 대기 상태
9. 이러한 과정을 반복하다가 Ctrl + z 키를 입력하면, read()는 입력이 종료되었음을 인식하고 -1을 반환하고 while문을 벗어나 프로그램 종료
10. 윈도우의 콘솔은 한 번에 255자 까지만 입력 가능
11. Enter 키를 누르는 것은 특수문자 '\r'(Carriage Return), '\n'(New Line) 이 입력된 것으로 간주
    - 즉, 커서를 현재 라인의 첫 번째 컬럼으로 이동 시키고, 커서를 다음주로 이동 시킴
12. Enter 또한 사용자 입력으로 간주되어 매 입력마다 이 둘이 붙기 때문에 제거해줘야 하는 불편함 존재
    - System.in에 BufferedReader를 이용해 readLine()을 통해 라인 단위로 입력 받으면 됨
    
-----
### 표준 입출력의 대상 변경 - setOut(), setErr(), setIn()
-----
1. setIn(), setOut(), setErr()를 사용하면 입출력을 콘솔 이외에 다른 입출력 대상으로 변경 가능
<div align="center">
<img src="https://github.com/sooyounghan/Data-Base/assets/34672301/b62b2846-c4df-4c49-8d01-e8e0793276b4">
</div>

2. JDK1.5부터 Scanner 클래스가 제공되면서 System.in으로부터 데이터를 입력받아 작업 가능
```java
class StandardIOEx2 {
	public static void main(String[] args) {
		System.out.println("out : Hello World!");
		System.err.println("err : Hello World!");
	}
}
```
   - System.out, System.err 모두 출력대상이 콘솔이므로 System.out 대신 System.err를 사용해도 같은 결과

```java
import java.io.*;

class StandardIOEx3 {
	public static void main(String[] args) {
		PrintStream      ps  = null;
		FileOutputStream fos = null;

		try {
			fos = new FileOutputStream("test.txt");
			ps  = new PrintStream(fos);
			System.setOut(ps);    //  System.out의 출력 대상을 test.txt파일로 변경
		} catch(FileNotFoundException e) {
			System.err.println("File not found.");
		}

		System.out.println("Hello by System.out");		
		System.err.println("Hello by System.err");		
	}
}
```
   - System.out의 출력 소스를 test.txt로 변경하였으므로, System.out을 이용한 출력은 모두 test.txt 파일에 저장
   - 따라서, 실행 결과는 System.err를 이용한 출력만 나타남
   - 만약, 기존의 test.txt 파일이 있었다면, 기존의 내용은 삭제됨
```java
... > StandardIOEx2 > test.txt
...
```

   - '>' 대신 '>>'를 사용하면 기존 내용의 마지막에 새로운 내용 추가
```java
... > StandardIOEx2 >> test.txt
...
```

   - 반대로 표준 입력을 test.txt로 변경하고 싶다면, '<'를 사용하면, 콘솔이 아닌 test.txt에서 입력 받음
```java
... > StandardIOEx2 < test.txt
...
```
