-----
### 문제와 풀이 1
-----
1. 문제 1 - startsWith
   - 문제 설명 : startsWith() 를 사용해서 url 이 https:// 로 시작하는지 확인
   - TestString1 (/lang/string/test)
```java
package lang.string.test;

public class TestString1 {
    public static void main(String[] args) {
        String url = "https://www.example.com";

        boolean result = url.startsWith("https://");

        System.out.println(result);
    }
}
```
   - 실행 결과
```
true
```

2. 문제2 - length()
  - 문제 설명 : length()를 사용해서 arr 배열에 들어있는 모든 문자열의 길이 합을 구할 것
  - TestString2
```java
package lang.string.test;

public class TestString2 {
    public static void main(String[] args) {
        String[] arr = {"hello", "java", "jvm", "spring", "jpa"};

        int sum = 0;
        for (String string : arr) {
            System.out.println(string + " : " + string.length());
            sum += string.length();
        }

        System.out.println("sum = " + sum);
    }
}
```
  - 실행 결과
```
hello : 5
java:  4
jvm : 3
spring : 6
jpa : 3
sum = 21
```

3. 문제3 - indexOf()
  - 문제 설명 : str에서 ".txt" 문자열이 언제부터 시작하는지 위치를 찾아서 출력 (indexOf()를 사용)
  - TestString3
```java
package lang.string.test;

public class TestString3 {
    public static void main(String[] args) {
        String str = "hello.txt";

        System.out.println("index = " + str.indexOf(".txt"));
    }
}
```
   - 실행 결과
```
index = 5
```

4. 문제4 - substring()
  - 문제 설명 : substring()을 사용해서, hello 부분과 .txt 부분을 분리
  - TestString4
```java
package lang.string.test;

public class TestString4 {
    public static void main(String[] args) {
        String str = "hello.txt";

        String fileName = str.substring(0, 5);
        String extName = str.substring(5, 9);
        System.out.println("fileName = " + fileName);
        System.out.println("extName = " + extName);
    }
}
```
  - 실행 결과
```
fileName = hello
extName = .txt
```

5. 문제 5 - indexOf, substring 조합
   - 문제 설명
     + str 에는 파일의 이름과 확장자가 주어짐
     + ext 에는 파일의 확장자가 주어짐
     + 파일명과 확장자를 분리해서 출력
   - TestString5
```java
package lang.string.test;

public class TestString5 {
    public static void main(String[] args) {
        String str = "hello.txt";
        String ext = ".txt";

        int extIndex = str.indexOf(ext);
        String fileName = str.substring(0, extIndex);
        String extName = str.substring(extIndex);
        System.out.println("fileName = " + fileName);
        System.out.println("extName = " + extName);
    }
}
```
   - 실행 결과
```
fileName = hello
extName = .txt
```

6. 문제 6 - 검색 count
   - 문제 설명 : str에서 key로 주어지는 문자를 찾고, 찾은 문자의 수를 출력
   - TestString6
```java
package lang.string.test;

public class TestString6 {
    public static void main(String[] args) {
        String str = "start hello java, hello spring, hello jpa";
        String key = "hello";

        int count = 0;
        int index = str.indexOf(key);
        
        while(index >= 0) {
            index = str.indexOf(key, index + 1);
            count++;
        }

        System.out.println("count = " + count);
    }
}
```
  - 실행 결과
```
count = 3
```

7. 문제 7 - 공백 제거
  - 문제 설명 : 문자의 양쪽 공백을 제거 (예) " Hello Java " "Hello Java")
  - TestString7
```java
package lang.string.test;

public class TestString7 {
    public static void main(String[] args) {
        String original = " Hello Java ";
        String trimmed = original.trim();
        System.out.println(trimmed);
    }
}
```
   - 실행 결과
```
Hello Java
```

8. 문제 8 - replace
   - 문제 설명 : replace()를 사용해서 java라는 단어를 jvm으로 변경
   - TestString8
```java
package lang.string.test;

public class TestString8 {
    public static void main(String[] args) {
        String input = "hello java spring jpa java";

        String replace = input.replace("java", "jvm");

        System.out.println(replace);
    }
}
```
  - 실행 결과
```
hello jvm spring jpa jvm
```

9. 문제 9 - split()
   - 문제 설명 : split()를 사용해서 이메일의 ID 부분과 도메인 부분을 분리해
   - TestString9
```java
package lang.string.test;

public class TestString9 {
    public static void main(String[] args) {
        String email = "hello@example.com";

        String[] split = email.split("@");
        System.out.println("ID : " + split[0]);
        System.out.println("Domain : " + split[1]);
    }
}
```
  - 실행 결과
```
ID : hello
Domain : example.com
```

10. 문제10 - split(), join()
   - 문제 설명 : split()를 사용해서 fruits를 분리하고, join()을 사용해서 분리한 문자들을 하나로 합칠 것
   - TestString10
```java
package lang.string.test;

public class TestString10 {
    public static void main(String[] args) {
        String fruits = "apple,banana,mango";

        String[] splited = fruits.split(",");

        for (String split : splited) {
            System.out.println(split);
        }

        String joinedString = String.join("->", splited);
        System.out.println("joinedString = " + joinedString);
    }
}
```
  - 실행 결과
```
apple
banana
mango
joinedString = apple->banana->mango
```

11. 문제 11 - reverse()
   - 문제 설명 : str 문자열을 반대로 뒤집기
   - TestString11
```java
package lang.string.test;

public class TestString11 {
    public static void main(String[] args) {
        String str = "Hello Java";

        StringBuilder sb = new StringBuilder(str);
        String result = sb.reverse().toString();

        System.out.println(result);
    }
}
```
   - 실행 결과
```
avaJ olleH
```
