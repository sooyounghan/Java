-----
### StringTokenizer Class (java.util.StringTokenizer)
-----
1. 긴 문자열을 지정된 구분자(delimeter)을 기준으로 토큰(Token)이라는 여러 개의 문자열로 잘라내는데 사용
2. 예) "100,200,300,400"이라는 문자열에 대해 구분자 ","로 잘라내면 "100", "200", "300", "400"이라는 4개의 문자열(토큰)을 얻음
3. String split(String regex)나 Scanner의 useDelimeter(String pattern)을 사용하는 방법도 존재
```java
String[] result = "100,200,300,400".split(",");
Scanner sc2 = new Scanner("100,200,300,400").useDelimeter(",");
```
  - 하지만, 이 두 가지 방법은 정규식 표현을 사용해야함

4. 구분자로 단 하나의 문자 밖에 사용하지 못하므로 보다 복잡한 형태의 구분자로 문자열을 나눠야할 때에는 정규식 사용

-----
### StringTokenizer의 생성자와 메서드
-----
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/03403416-24c0-4165-b074-333a8a6ea8b8">
</div>

-----
### StringTokenizer 예제
-----
```java
import java.util.*; 

class StringTokenizerEx1 { 
	public static void main(String[] args){ 
		String source = "100,200,300,400";
		StringTokenizer st = new StringTokenizer(source, ",");

		while(st.hasMoreTokens()){
			System.out.println(st.nextToken());
		}
	} // main
} 
```
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/9c590459-976e-4e2e-a26a-1708dc665b03">
</div>

  - "," 구분자로 하여 StringTokenizer를 생성해 문자열을 나누어 출력

```java
import java.util.*; 

class StringTokenizerEx2 { 
	public static void main(String[] args) { 
		String expression = "x=100*(200+300)/2";
		StringTokenizer st = new StringTokenizer(expression, "+-*/=()", true);

		while(st.hasMoreTokens()){
			System.out.println(st.nextToken());
		}
	} // main
} 
```
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/a7385deb-44f5-4fe4-a30a-a5c2e2126a16">
</div>

  - 생성자 StringTokenizer(String str, String delim, boolean returnDelims)를 사용해 구분자도 토큰으로 간주
```java
StringTokenizer st = new StringTokenizer(expression, "+-*/=()", true);
```
  - StringTokenizer는 단 한 문자의 구분자만 사용할 수 있기 때문에, "+-*/=()" 전체가 하나의 구분자가 아닌 각 문자가 모두 구분자
  - 구분자가 만약 두 문자 이상이라면, Scanner나 String 클래스의 split 메서드 이용

```java
import java.util.*; 

class StringTokenizerEx3 { 
	public static void main(String args[]) { 
		String source = "1,김천재,100,100,100|2,박수재,95,80,90|3,이자바,80,90,90";
		StringTokenizer st = new StringTokenizer(source, "|"); 

		while(st.hasMoreTokens()) {
			String token = st.nextToken();

			StringTokenizer st2 = new StringTokenizer(token, ","); 
			while(st2.hasMoreTokens()) {
				System.out.println(st2.nextToken());
			}
			System.out.println("------");
		}
	} // main
}
```
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/04221b91-4085-48b0-bb84-26fbcaa9a99f">
</div>

  - 두 가지 종류의 구분자로 나뉘어져 있을 때, 두 개의 StringTokenizer와 이중 반복문을 사용해 처리

```java
import java.util.*;

class StringTokenizerEx5 {
	public static void main(String[] args) {
		String data = "100,,,200,300";

		String[] result = data.split(",");
		StringTokenizer st = new StringTokenizer(data, ",");

		for(int i=0; i < result.length;i++)
			System.out.print(result[i]+"|");

		System.out.println("개수:"+result.length);

		int i=0;
		for(;st.hasMoreTokens();i++)
			System.out.print(st.nextToken()+"|");

		System.out.println("개수:"+i);
	} // main
}
```
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/435f2d15-1d0f-47a6-8cbc-e1ac179ad34b">
</div>

  - 구분자 ','로 하는 문자열 데이터를 String클래스의 split()과 StringTokenizer로 잘라낸 결과 비교
  - split() : 빈 문자열도 토큰으로 인식
  - StringTokenizer : 빈 문자열을 토큰으로 인식하지 않음
  - split()는 데이터를 토큰으로 잘라낸 결과를 배열에 담아서 반환하므로 바로 잘라서 반환하는 StringTokenizer보다 성능이 떨어질 수 밖에 없음
