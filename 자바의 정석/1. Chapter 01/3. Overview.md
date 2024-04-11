-----
### Hello.java
-----
```java
class Hello { 
      public static void main(String[] args) { 
            System.out.println("Hello, world."); // 화면에 글자를 출력한
      }
}
```

1. 실행과정
   - Hello.java 작성
   - 자바 컴파일러 (javac.exe)를 사용해 컴파일하여 소스파일(Hello.java)로부터 클래스파일(Hello.class)
   - 자바 인터프리터 (java.exe)를 통해 클래스파일(Hello.class) 실행
   - Hello, World. 출력
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/8f4a6de6-92d1-4816-9a6e-47a5869d9d94">
</div>

2. 자바에서 모든 코드는 반드시 클래스 안에 존재해야 함
```js
class 클래스이름 {
  /*
    주석을 제외한 모든 코드는 클래스의 블럭 { } 내에 작성
  */
}
```

3. public static void main(Stirng[] args) : main 메서드의 선언부
```js
class 클래스이름 {
  public static void main(String[] args) // main 메서드 선언부
  {
    // 실행될 문자 작성
  }
}
```
  - main 메서드의 선언부 다음에 나오는 괄호 { }는 메서드의 시작과 끝을 알림
  - Java 애플리케이션은 main 메서드의 호출로 시작해, main 메서드의 첫 문장부터 마지막 문장까지 수행을 마치면 종료
  - 모든 클래스가 main 메서드를 가지고 있어야 하는 것은 아님
  - 하지만, 하나의 Java 애플리케이션에는 main 메서드를 포함한 클래스가 반드시 하나는 있어야 함
  - 즉, main 메서드는 Java 애플리케이션의 시작점이므로 main 메서드 없이는 Java 애플리케이션이 실행될 수 없음

-----
### 자바프로그램의 실행 과정
-----
1. 다음과 같이 Java 애플리케이션이 실행된다고 하자.
```
c:\jdk1.8\work>java Hello
```

2. 내부 진행 순서는 다음과 같음
   - 프로그램의 실행에 필요한 클래스 (*.class)를 로드
   - 클래스 파일을 검사 (파일형식, 악성코드 체크)
   - 지정된 클래스(Hello)에서 main(String[] args)를 호출
  
-----
### 주석 (Comment)
-----     
1. 한 줄 주석 : // ~
2. 범위 주석 : /* ~ */
3. 단, 큰 따옴표(") 안에 주석이 있을 때는 주석이 아닌 문자열로 인식
```java
class Hello {
  public static void main(String[] args) {
    // Comment 1.
    /*
     * Comment 2.
     */
    System.out.println("Hello, /* 이것은 주석 아님 */ World.");
    System.out.println("Hello, World. // 이것도 주석 아님");
  }
}
```
