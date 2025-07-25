-----
### String 클래스 - 주요 메서드
-----
1. 문자열 정보 조회
    - length() : 문자열의 길이를 반환
    - isEmpty() : 문자열이 비어 있는지 확인 (길이가 0)
    - isBlank() : 문자열이 비어 있는지 확인한 (길이가 0이거나 공백(Whitespace)만 있는 경우) (자바 11)
    - charAt(int index) : 지정된 인덱스에 있는 문자를 반환
    - StringInfoMain (/lang/string/method)
```java
package lang.string.method;

public class StringInfoMain {
    public static void main(String[] args) {
        String str = "Hello, Java!";

        System.out.println("문자열의 길이 : " + str.length());
        System.out.println("문자열이 비어 있는지 : " + str.isEmpty());
        System.out.println("문자열이 비어 있거나 공백인지1 : " + str.isBlank()); // Java 11
        System.out.println("문자열이 비어 있거나 공백인지2 : " + " ".isBlank());

        char c = str.charAt(7);
        System.out.println("7번 인덱스의 문자: " + c);
    }
}
```
   - 실행 결과
```
문자열의 길이 : 12
문자열이 비어 있는지 : false
문자열이 비어 있거나 공백인지1 : false
문자열이 비어 있거나 공백인지2 : true
7번 인덱스의 문자: J
```

2. 문자열 비교
    - equals(Object anObject) : 두 문자열이 동일한지 비교
    - equalsIgnoreCase(String anotherString) : 두 문자열을 대소문자 구분 없이 비교
    - compareTo(String anotherString) : 두 문자열을 사전 순으로 비교
    - compareToIgnoreCase(String str) : 두 문자열을 대소문자 구분 없이 사전적으로 비교
    - startsWith(String prefix) : 문자열이 특정 접두사로 시작하는지 확인
    - endsWith(String suffix) : 문자열이 특정 접미사로 끝나는지 확인
    - StringComparsionMain
```java
package lang.string.method;

public class StringComparisonMain {
    public static void main(String[] args) {
        String str1 = "Hello, Java!"; // 대문자 일부 있음
        String str2 = "hello, java!"; // 대문자 없음 모두 소문자
        String str3 = "Hello, World!";
        System.out.println("str1 equals str2: " + str1.equals(str2));
        System.out.println("str1 equalsIgnoreCase str2: " + str1.equalsIgnoreCase(str2));
        
        System.out.println("'b' compareTo 'a': " + "b".compareTo("a"));
        System.out.println("str1 compareTo str3: " + str1.compareTo(str3));
        System.out.println("str1 compareToIgnoreCase str2: " + str1.compareToIgnoreCase(str2));
        System.out.println("str1 starts with 'Hello': " + str1.startsWith("Hello"));
        System.out.println("str1 ends with 'Java!': " + str1.endsWith("Java!"));
    }
}  
```
   - 실행 결과
```
str1 equals str2: false
str1 equalsIgnoreCase str2: true
'b' compareTo 'a': 1
str1 compareTo str3: -13
str1 compareToIgnoreCase str2: 0
str1 starts with 'Hello': true
str1 ends with 'Java!': true
```

3. 문자열 검색
    - contains(CharSequence s) : 문자열이 특정 문자열을 포함하고 있는지 확인
    - indexOf(String ch) / indexOf(String ch, int fromIndex) : 문자열이 처음 등장하는 위치를 반환
    - lastIndexOf(String ch) : 문자열이 마지막으로 등장하는 위치를 반환
    - StringSearchMain
```java
package lang.string.method;

public class StringSearchMain {
    public static void main(String[] args) {
        String str = "Hello, Java! Welcome to Java world.";
        System.out.println("문자열에 'Java'가 포함되어 있는지: " + str.contains("Java"));
        System.out.println("'Java'의 첫 번째 인덱스: " + str.indexOf("Java"));
        System.out.println("인덱스 10부터 'Java'의 인덱스: " + str.indexOf("Java", 10));
        System.out.println("'Java'의 마지막 인덱스: " + str.lastIndexOf("Java"));
    }
}
```
   - 실행 결과
```
문자열에 'Java'가 포함되어 있는지: true
'Java'의 첫 번째 인덱스: 7
인덱스 10부터 'Java'의 인덱스: 24
'Java'의 마지막 인덱스: 24
```

4. 문자열 조작 및 변환
    - substring(int beginIndex) / substring(int beginIndex, int endIndex) : 문자열의 부분 문자열을 반환
    - concat(String str) : 문자열의 끝에 다른 문자열을 붙임
    - replace(CharSequence target, CharSequence replacement) : 특정 문자열을 새 문자열로 대체
    - replaceAll(String regex, String replacement) : 문자열에서 정규 표현식과 일치하는 부분을 새 문자열로 대체
    - replaceFirst(String regex, String replacement) : 문자열에서 정규 표현식과 일치하는 첫 번째 부분을 새 문자열로 대체
    - toLowerCase() / toUpperCase() : 문자열을 소문자나 대문자로 변환
    - trim() : 문자열 양쪽 끝의 공백을 제거 (단순 Whitespace만 제거할 수 있음)
    - strip() : Whitespace와 유니코드 공백을 포함해서 제거 (자바 11)
    - StringChangeMain1
```java
package lang.string.method;

public class StringChangeMain {
    public static void main(String[] args) {
        String str = "Hello, Java! Welcome to Java";
        System.out.println("인덱스 7부터의 부분 문자열: " + str.substring(7));
        System.out.println("인덱스 7부터 12까지의 부분 문자열: " + str.substring(7, 12));
        System.out.println("문자열 결합: " + str.concat("!!!"));
        System.out.println("'Java'를 'World'로 대체: " + str.replace("Java", "World"));
        System.out.println("첫 번째 'Java'를 'World'으로 대체: " + str.replaceFirst("Java", "World"));
    }
}
```
   - 실행 결과
```
인덱스 7부터의 부분 문자열: Java! Welcome to Java
인덱스 7부터 12까지의 부분 문자열: Java!
문자열 결합: Hello, Java! Welcome to Java!!!
'Java'를 'World'로 대체: Hello, World! Welcome to World
첫 번째 'Java'를 'World'으로 대체: Hello, World! Welcome to Java
```

   - StringChangeMain2
```java
package lang.string.method;

public class StringChangeMain2 {
    public static void main(String[] args) {
        String strWithSpaces = " Java Programming ";

        System.out.println("소문자로 변환: " + strWithSpaces.toLowerCase());
        System.out.println("대문자로 변환: " + strWithSpaces.toUpperCase());

        System.out.println("공백 제거(trim): '" + strWithSpaces.trim() + "'");
        System.out.println("공백 제거(strip): '" + strWithSpaces.strip() + "'");
        System.out.println("앞 공백 제거(strip): '" + strWithSpaces.stripLeading() + "'");
        System.out.println("뒤 공백 제거(strip): '" + strWithSpaces.stripTrailing() + "'");
    }
}
```
   - 실행 결과
```
소문자로 변환:  java programming 
대문자로 변환:  JAVA PROGRAMMING 
공백 제거(trim): 'Java Programming'
공백 제거(strip): 'Java Programming'
앞 공백 제거(strip): 'Java Programming '
뒤 공백 제거(strip): ' Java Programming'
```

5. 문자열 분할 및 조합
    - split(String regex) : 문자열을 정규 표현식을 기준으로 분할
    - join(CharSequence delimiter, CharSequence... elements) : 주어진 구분자로 여러 문자열을 결합
    - StringSplitJoinMain
```java
package lang.string.method;

public class StringSplitJoinMain {
    public static void main(String[] args) {
        String str = "Apple,Banana,Orange";

        // split()
        String[] splitStr = str.split(",");
        for(String s : splitStr) {
            System.out.println(s);
        }
        
        // join()
        String joinedStr = String.join("-", "A", "B", "C");
        System.out.println("연결된 문자열: " + joinedStr);

        // 문자열 배열 연결
        String result = String.join("-", splitStr);
        System.out.println("result = " + result);
    }
}
```
   - 실행 결과
```
Apple
Banana
Orange
연결된 문자열: A-B-C
result = Apple-Banana-Orange
```

6. 기타 유틸리티
    - valueOf(Object obj) : 다양한 타입을 문자열로 변환
    - toCharArray() : 문자열을 문자 배열로 변환
    - format(String format, Object... args) : 형식 문자열과 인자를 사용하여 새로운 문자열을 생성
    - matches(String regex) : 문자열이 주어진 정규 표현식과 일치하는지 확인
    - StringUtilsMain1
```java
package lang.string.method;

public class StringUtilsMain {
    public static void main(String[] args) {
        int num = 100;
        boolean bool = true;
        Object obj = new Object();
        String str = "Hello, Java!";

        // valueOf 메서드
        String numString = String.valueOf(num);
        System.out.println("숫자의 문자열 값: " + numString);

        String boolString = String.valueOf(bool);
        System.out.println("불리언의 문자열 값: " + boolString);

        String objString = String.valueOf(obj);
        System.out.println("객체의 문자열 값: " + objString);

        // 다음과 같이 간단히 변환할 수 있음 (문자 + x -> 문자x)
        String numString2 = "" + num;
        System.out.println("빈문자열 + num:" + numString2);

        // toCharArray 메서드
        char[] strCharArray = str.toCharArray();
        System.out.println("문자열을 문자 배열로 변환: " + strCharArray);
        
        for (char c : strCharArray) {
            System.out.print(c);
        }
        System.out.println();
    }
}
```
   - 실행 결과
```
숫자의 문자열 값: 100
불리언의 문자열 값: true
객체의 문자열 값: java.lang.Object@4e50df2e
빈문자열 + num:100
문자열을 문자 배열로 변환: [C@1d81eb93
Hello, Java!
```

   - StringUtilsMain2
```java
package lang.string.method;

public class StringUtilsMai2 {
    public static void main(String[] args) {
        int num = 100;
        boolean bool = true;
        Object obj = new Object();
        String str = "Hello, Java!";

        // format 메서드
        String format1 = String.format("num: %d, bool: %b, str: %s", num, bool, str);
        System.out.println(format1);
        
        String format2 = String.format("숫자: %.2f", 10.1234);
        System.out.println(format2);
        
        // printf
        System.out.printf("숫자: %.2f\n", 10.1234);
        
        // matches 메서드
        String regex = "Hello, (Java!|World!)";
        System.out.println("'str'이 패턴과 일치하는가? " + str.matches(regex));

    }
}
```
   - 실행 결과
```
num: 100, bool: true, str: Hello, Java!
숫자: 10.12
숫자: 10.12
'str'이 패턴과 일치하는가? true
```
   - format 메서드에서 %d는 숫자, %b는 boolean , %s는 문자열을 뜻함

7. 참고
    - CharSequence는 String , StringBuilder의 상위 타입
    - 문자열을 처리하는 다양한 객체를 받을 수 있음

