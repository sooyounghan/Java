-----
### 커맨드 라인을 통해 입력받기
-----
1. 프로그램을 실행할 때 클래스 이름 뒤 공백 문자로 구분해 여러 개 문자열을 프로그램에 전달 가능
2. 예) main 메서드에 담긴 클래스 이름이 MainTest
```java
c:\jdk1.8\work\ch5>java MainTest abc 123
```
3. 커맨드라인을 통해 입력된 두 문자열은 String 배열에 담겨서 MainTest 클래스의 main 메서드의 매개변수(args)에 전달
   - main 메서드 내 args[0], args[1]와 같은 방식으로 커맨드라인으로 부터 전달받은 문자열에 접근 가능
   - args[0] = "abc", args[1] = "123"
4. 커맨드 라인에 입력되는 매개변수는 공백문자로 구분되므로 입력될 값에 공백이 있으면 큰따옴표(")로 감싸줘야함
```java
c:\jdk1.8\work\ch5>java MainTest abc 123 "Hello World"
```

5. 커맨드라인에 매개변수를 입력하지 않으면, 크기가 0인 배열이 생성되어 args.length의 값은 0이 됨
6. 만약, 배열을 생성하지 않으면 참조변수 args의 값은 null이 될 것이고, 이를 사용하는 모든 코드에서 에러가 발생할 것이므로 따라서 이에 대한 유효성 처리 필요
```java
public static void main(String[] args) {
    if(args != null) { // args가 null이 아닐떄만 괄호 { }안의 문장 수행
        System.out.println("매개변수의 개수 : " + args.length);
        for(int i = 0; i < args.length; i++) {
            ...
        }
    }
}
```

7. 그러나 JVM이 입력된 매개변수가 없을 때, null 대신 크기가 0인 배열을 생성
