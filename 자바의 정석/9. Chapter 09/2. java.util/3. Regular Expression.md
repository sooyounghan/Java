-----
### 정규식(Regular Expression) - java.util.regex Package
-----
1. 정규식 : 텍스트 데이터 중 원하는 조건(패턴, Pattern)과 일치하는 문자열을 찾아내기 위해 사용되는 것
2. 미리 정의된 기호와 문자를 이용해 작성한 문자열
3. 원래 Unix에서 사용하던 것이고, Perl의 강력한 기능이었음
4. 정규식을 이용하면 많은 양의 텍스트 파일 중 원하는 데이터를 손쉽게 뽑아낼 수 있고, 입력한 데이터가 형식이 맞는지 확인 가능

```java
import java.util.regex.*;	// Pattern과 Matcher가 속한 Package

class RegularEx1 {
	public static void main(String[] args) 
	{
		String[] data = {"bat", "baby", "bonus",
				    "cA","ca", "co", "c.", "c0", "car","combat","count",
				    "date", "disc"};		
		Pattern p = Pattern.compile("c[a-z]*");	// c로 시작하는 소문자 영단어

		for(int i=0; i < data.length; i++) {
			Matcher m = p.matcher(data[i]);
			if(m.matches())
				System.out.print(data[i] + ",");
		}
	}
}
```
<div align="center">
<img src="https://github.com/sooyounghan/Data-Base/assets/34672301/b23543ba-5eb7-43e0-b98d-13945f0c9ad4">
</div>

  - Pattern : 정규식 정의
  - Matcher : 정규식 (패턴)을 데이터와 비교하는 역할
```java
A. 정규식을 매개변수로 Pattern 클래스의 static 메서드인 Pattern.complie(String regex)를 호출해 Pattern 인스턴스를 얻음
    Pattern p = Pattern.compile("c[a-z]*");
B. 정규식을 비교할 대상으로 매개변수로 Pattern 클래스의 matcher(CharSequence input)를 호출해 Matcher 인스턴스를 얻음
    Matcher m = p.matcher(data[i]);
C. Matcher 인스턴스에 boolean matches()를 호출해 정규식에 부합하는지 확인
    if(m.matches())
```

```java
import java.util.regex.*;	// Pattern과 Matcher가 속한 Package

class RegularEx2 {
	public static void main(String[] args) {
		String[] data = {"bat", "baby", "bonus", "c", "cA",
				        "ca", "co", "c.", "c0", "c#",
					"car","combat","count", "date", "disc"
						};		
		String[] pattern = {".*","c[a-z]*","c[a-z]", "c[a-zA-Z]",
                        "c[a-zA-Z0-9]","c.","c.*","c\\.","c\\w",
                        "c\\d","c.*t", "[b|c].*", ".*a.*", ".*a.+",
                        "[b|c].{2}" };

		for(int x=0; x < pattern.length; x++) {
			Pattern p = Pattern.compile(pattern[x]);
			System.out.print("Pattern : " + pattern[x] + "  결과: ");
			for(int i=0; i < data.length; i++) {
				Matcher m = p.matcher(data[i]);
				if(m.matches())
					System.out.print(data[i] + ",");
			}
			System.out.println();
		}
	} // public static void main(String[] args) 
}
```
<div align="center">
<img src="https://github.com/sooyounghan/Data-Base/assets/34672301/8a2539bc-2850-4ae8-817f-e30b8569473e">
</div>

5. 자주 사용되는 정규식 패턴 예
<div align="center">
<img src="https://github.com/sooyounghan/Data-Base/assets/34672301/b7613e03-881c-4b00-b8b1-e929370ad079">
</div>

-----
### 정규식(Regular Expression) 그룹화
-----
```java
import java.util.regex.*;	// Pattern과 Matcher가 속한 패키지

class RegularEx3{
	public static void main(String[] args) {
		String source  = "HP:011-1111-1111, HOME:02-999-9999 ";
		String pattern = "(0\\d{1,2})-(\\d{3,4})-(\\d{4})";

		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(source);

		int i=0;

		while(m.find()) {
			System.out.println( ++i + ": " + m.group() + " -> "+ m.group(1) +", "+ m.group(2)+", "+ m.group(3));		
		}
	} // main
}
```
<div align="center">
<img src="https://github.com/sooyounghan/Data-Base/assets/34672301/b3d1e7d0-6ebb-4fec-abb0-13ad143091fd">
</div>

1. 정규식의 일부를 괄호로 나누어 묶어서 그룹화(Grouping) 가능
2. 그룹화된 부분은 하나의 단위로 묶이는 셈이 되어서 한 번 또는 그 이상의 반복을 의미하는 '+', '*'가 뒤에 나오면 그룹화된 부분이 적용 대상
3. 그룹화된 부분은 group(int i)로 나누어 얻을 수 있음
4. 위 예제에서는 (0\\d{1,2})-(\\d{3,4})-(\\d{4}) : 괄호를 이용해서 정규식을 세 부분으로 나눔
   - 문자열에서 첫 그룹은 group(1)
   - 두 번째 그룹은 group(2)
   - 세 번쨰 그룹은 group(3)
   - group() 또는 group(0)은 그룹에 매칭된 문자열 전체를 나누어지지 않은 채로 반환
5. group(int i)를 실제 호출할 때, i가 실제 그룹 수보다 많으면 java.lang.IndexOutOfBoundsException 발생

```
A. 0\\d{1,2} : 0으로 시작하는 최소 2자리, 최대 3자리 숫자 (0 포함)
B. \\d{3,4} : 최소 3자리, 최대 4자리 숫자
C. \\d{4} : 4자리의 숫자
```

6. find() : 주어진 소스 내 패턴과 일치하는 부분을 찾아내면 true, 찾지 못하면 false
   - find()를 호출해서 패턴과 일치하는 부분을 찾아낸 다음, 다시 find()를 호출하면 이전에 발견한 패턴과 일치하는 부분부터 다음부터 다시 패턴 매칭 시작

```java
import java.util.regex.*;	// Pattern과 Matcher가 속한 Package

class RegularEx4 {
	public static void main(String[] args) {
		String source  = "A broken hand works, but not a broken heart.";
		String pattern = "broken";

		StringBuffer sb = new StringBuffer();

		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(source);
		System.out.println("source:"+source);

		int i=0;

		while(m.find()) {
			System.out.println(++i + "번째 매칭:" + m.start() + "~"+ m.end());

      // broken을 drunken으로 치환하여 sb에 저장
			m.appendReplacement(sb, "drunken");  
		}

		m.appendTail(sb);
		System.out.println("Replacement count : " + i);
		System.out.println("result:"+sb.toString());
	}
}
```
<div align="center">
<img src="https://github.com/sooyounghan/Data-Base/assets/34672301/d281f8c8-035d-4d7b-acf6-12ba800524b5">
</div>

  - Matcher의 find()로 정규식과 일치하는 부분을 찾으면, 그 위치를 start()와 end()로 알아낼 수 있음
  - appendReplacement(StringBuilder sb, String replacement)를 이용해 원하는 문자열(replacement)로 치환 가능
  - 치환된 결과는 StringBuilder인 sb에 저장

```java
1. 문자열 source에서 "broken"을 m.find()로 찾은 후 처음으로 m.appendReplacement(sb, "broken");가 호출되면, source의 시작부터, "broken"을 찾은 위치까지의 내용을 "drunken"을 더해서 저장
  - sb에 저장된 내용 : "A drunken"

2. m.find()는 첫번째로 발견된 위치의 끝에서부터 다시 검색을 시작해 두 번째 "broken"을 찾게 됨. 다시 m.appendReplacement(sb, "drunken"); 가 호출
  - sb에 저장된 내용 : "A drunken hand works, but not a drunken"

3. m.appendTail(sb);이 호출되면, 마지막 치환된 이후 부분을 sb에 덧붙임
  - sb에 저장된 내용 : "A drunken hand works, but not a drunken heart."
```
