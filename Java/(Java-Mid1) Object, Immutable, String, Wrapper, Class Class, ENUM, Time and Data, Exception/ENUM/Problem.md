-----
### 문제와 풀이 1
-----
1. 문제 1 - 인증 등급 만들기
   - 문제 설명
     + 패키지의 위치는 enumeration.test를 사용
     + 회원의 인증 등급을 AuthGrade 라는 이름의 열거형으로 만들것
     + 인증 등급은 다음 3가지이고, 인증 등급에 따른 레벨과 설명을 가짐 : 레벨과 설명을 getXxx() 메서드로 조회할 수 있음

   - GUEST(손님)
     + level = 1
     + description = 손님
   - LOGIN(로그인 회원)
     + level = 2
     + description = 로그인 회원
   - ADMIN(관리자)
     + level = 3
     + description = 관리자
    
   - AuthGrade (enumeration/test/ex1)
```java
package enumeration.test;

public enum AuthGrade {
    GUEST(1, "손님"),
    LOGIN(2, "로그인 회원"),
    ADMIN(3, "관리자");

    private final int level;
    private final String description;

    AuthGrade(int level, String description) {
        this.level = level;
        this.description = description;
    }
    
    public int getLevel() {
        return level;
    }
    
    public String getDescription() {
        return description;
    }
}
```

2. 문제 2 - 인증 등급 열거형 조회하기
    - 문제 설명
      + AuthGradeMain1이라는 클래스를 만들고 다음 결과가 출력되도록 코드를 작성
      + 앞서 만든 AuthGrade을 활용
```
grade = GUEST, level = 1, 설명 = 손님
grade = LOGIN, level = 2, 설명 = 로그인 회원
grade = ADMIN, level = 3, 설명 = 관리자
```

   - AuthGradeMain1
```java
package enumeration.test;

public class AuthGradeMain1 {
    public static void main(String[] args) {
        AuthGrade[] values = AuthGrade.values();

        for (AuthGrade value : values) {
            System.out.println("grade = " + value.name() + ", level = " + value.getLevel() + ", 설명 = " + value.getDescription());
        }
    }
}
```

3. 문제 3 - 인증 등급 열거형 활용하기
   - 문제 설명
     + AuthGradeMain2 클래스에 코드를 작성
     + 인증 등급을 입력 받아서 앞서 만든 AuthGrade 열거형으로 변환
     + 인증 등급에 따라 접근할 수 있는 화면이 다름
     + 예를 들어 GUEST 등급은 메인 화면만 접근할 수 있고, ADMIN 등급은 모든 화면에 접근할 수 있음
     + 각각의 등급에 따라서 출력되는 메뉴 목록이 달라짐

   - GUEST 입력 예
```
당신의 등급을 입력하세요[GUEST, LOGIN, ADMIN]: GUEST
당신의 등급은 손님입니다.
== 메뉴 목록 ==
- 메인 화면
```

   - LOGIN 입력 예
```
당신의 등급을 입력하세요[GUEST, LOGIN, ADMIN]: LOGIN
당신의 등급은 로그인 회원입니다.
== 메뉴 목록 ==
- 메인 화면
- 이메일 관리 화면
```

   - ADMIN 입력 예
```
당신의 등급을 입력하세요[GUEST, LOGIN, ADMIN]: ADMIN
당신의 등급은 관리자입니다.
== 메뉴 목록 ==
- 메인 화면
- 이메일 관리 화면
- 관리자 화면
```

   - 잘못된 값 입력 경우
```
당신의 등급을 입력하세요[GUEST, LOGIN, ADMIN]: x
Exception in thread "main" java.lang.IllegalArgumentException: No enum constant enumeration.test.AuthGrade.X
    at java.base/java.lang.Enum.valueOf(Enum.java:293)
    at enumeration.test.AuthGrade.valueOf(AuthGrade.java:3)
    at enumeration.test.AuthGradeMain2.main(AuthGradeMain2.java:12)
```
  - 참고 : Enum.valueOf()를 사용할 때 잘못된 값이 입력되면 예와 같이 IllegalArgumentException 예외가 발생

   - AuthGradeMain2
```java
package enumeration.test;

import java.util.Scanner;

public class AuthGradeMain2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("당신의 등급을 입력하세요[GUEST, LOGIN, ADMIN] : ");
        String grade = scanner.nextLine();

        AuthGrade authGrade = AuthGrade.valueOf(grade.toUpperCase());
        System.out.println("당신의 등급은 " + authGrade.getDescription() + "입니다.");

        System.out.println("== 메뉴 목록 == ");
        
        if(authGrade.getLevel() > 0) {
            System.out.println("- 메인 화면");
        }
        
        if (authGrade.getLevel() > 1) {
            System.out.println("- 이메일 관리 화면");
        }
        
        if (authGrade.getLevel() > 2) {
            System.out.println("- 관리자 화면");
        }
    }
}
```

-----
### 문제와 풀이 2
-----
1. 문제 설명
   - enumeration.test.http 패키지를 사용
   - HttpStatus 열거형을 만들것
   - HTTP 상태 코드 정의
     + OK
       * code : 200
       * message : "OK"
     + BAD_REQUEST
       * code : 400
       * message : "Bad Request"
     + NOT_FOUND
       * code : 404
       * message : "Not Found"
     + INTERNAL_SERVER_ERROR
       * code : 500
       * message : "Internal Server Error"

2. 참고: HTTP 상태 코드는 200 ~ 299 사이의 숫자를 성공으로 인정
3. HttpStatus 열거형 (/enumeration/test/http)
```java
package enumeration.test.http;

public enum HttpStatus {
    OK(200, "OK"),
    BAD_REQUEST(400, "Bad Request"),
    NOT_FOUND(404, "Not Found"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error");

    private final int code;
    private final String message;

    HttpStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static HttpStatus findByCode(int code) {
        for (HttpStatus status : values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        
        return null;
    }
    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
```

5. HttpStatusMain
```java
package enumeration.test.http;

import java.util.Scanner;

public class HttpStatusMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("HTTP CODE : ");
        int httpCodeInput = scanner.nextInt();

        HttpStatus status = HttpStatus.findByCode(httpCodeInput);

        if(status == null) {
            System.out.println("정의되지 않은 코드");
        } else {
            System.out.println(status.getCode() + " " + status.getMessage());
            System.out.println("isSuccess = " + status.isSuccess());
        }
    }
}
```
  - 실행 결과
```
HTTP CODE: 200
200 OK
isSuccess = true
```
```
HTTP CODE: 400
400 Bad Request
isSuccess = false
```
```
HTTP CODE: 404
404 Not Found
isSuccess = false
```
```
HTTP CODE: 500
500 Internal Server Error
isSuccess = false
```
