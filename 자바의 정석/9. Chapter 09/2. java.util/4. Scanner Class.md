-----
### Scanner Class (java.util.Scanner)
-----
1. Scanner 클래스는 화면, 파일, 문자열과 같은 입력 소스로부터 문자 데이터를 읽어오는 데 사용 (JDK1.5부터 추가)
2. 다음과 같은 다양한 생성자를 지원하므로 다양한 입력 소스로부터 데이터를 읽을 수 있음
```java
Scanner(String source)
Scanner(File source)
Scanner(InputStream source)
Scanner(Readable source)
Scanner(ReadableByteChannel source)
Scanner(Path source) // JDK 1.7부터 추가
```

3. 또한, 정규식 표현(Regular Expresssion)을 이용한 라인 단위 검색을 지원
4. 구분자(Delimeter)에도 정규식 표현을 사용할 수 있어 복잡한 형태의 구분자로 처리 가능
```java
Scanner useDelimeter(Pattern pattern)
Scanner useDelimeter(String pattern)
```

5. 입력받을 값이 숫자라면, nextLine() 대신 nextInt(), nextLong()와 같은 메서드 사용 가능
```java
boolean nextBoolean()
byte nextByte()
short nextShort()
int nextInt()
long nextLong()
double nextDouble()
float nextFloat()
String nextLine()
```
  - 실제 입력된 데이터 형식에 맞는 메서드를 사용하지 않으면, InputMismatchException 발생

```java
import java.util.*; 

class ScannerEx1 { 
	public static void main(String[] args) { 
		Scanner s = new Scanner(System.in);
		String[] argArr = null;

		while(true) { 
			String prompt = ">>"; 
			System.out.print(prompt); 

			// 화면으로부터 라인단위로 입력받음
			String input = s.nextLine(); 

			input = input.trim();        // 입력받은 값에서 불필요한 앞 뒤 공백 제거
			argArr = input.split(" +");  // 입력받은 내용을 공백을 구분자로 자름

			String command = argArr[0].trim(); 

			if("".equals(command)) continue; 

			// 명령어를 소문자로 변경
			command = command.toLowerCase();
  
 			// q 또는 Q를 입력하면 실행 종료
			if(command.equals("q")) { 
				System.exit(0); 
			} else { 
				for(int i=0; i < argArr.length;i++) 
					System.out.println(argArr[i]); 
			} 
		} // while(true) 
	} // main 
}
```
  - 💡 입력받은 라인의 단어는 공백이 여러 개 일 수 있으므로 정규식을 " +"로 해결 (하나 이상의 공백 의미)
```java
argArr = input.split(" +");
```

```java
import java.util.Scanner;
import java.io.File;

class ScannerEx3 {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new File("data3.txt"));
		int cnt = 0;
		int totalSum = 0;

		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			Scanner sc2 = new Scanner(line).useDelimiter(",");
			int sum = 0;

			while(sc2.hasNextInt()) {
				sum += sc2.nextInt();
			}
			System.out.println(line + ", sum = "+ sum);
			totalSum += sum;
			cnt++;
		}
		System.out.println("Line: " + cnt + ", Total: " + totalSum);
	}
}
```
  - ','을 구분자로 한 라인에 여러 데이터가 저장
  - 파일의 내용을 먼저 라인별로 읽은 다음, ','를 구분자로 하는 Scanner를 이용해 각 데이터를 읽음
