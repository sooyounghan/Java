-----
### java.lang 패키지 소개
-----
1. 자바가 기본으로 제공하는 라이브러리(클래스 모음) 중에 가장 기본이 되는 것이 바로 java.lang 패키지
2. 여기서 lang 은 Language (언어)의 줄임말
   - 쉽게 이야기해서 자바 언어를 이루는 가장 기본이 되는 클래스들을 보관하는 패키지를 뜻함
   - java.lang 패키지의 대표적인 클래스들
      + Object : 모든 자바 객체의 부모 클래스
      + String : 문자열
      + Integer, Long, Double : 래퍼 타입, 기본형 데이터 타입을 객체로 만든 것
      + Class : 클래스 메타 정보
      + System : 시스템과 관련된 기본 기능들을 제공

3. import 생략 가능
   - java.lang 패키지는 모든 자바 애플리케이션에 자동으로 임포트(import)된
   - 따라서 임포트 구문을 사용하지 않아도 됨
   - 다른 패키지에 있는 클래스를 사용하려면 다음과 같이 임포트를 사용해야 함
```java
package lang;

import java.lang.System;

public class LangMain {
    public static void main(String[] args) {
        System.out.println("hello java");
    }
}
```
   - System 클래스는 java.lang 패키지 소속 : 다음과 같이 임포트를 생략 가능
```java
package lang;

public class LangMain {
    public static void main(String[] args) {
        System.out.println("hello java");
    }
}
```
   - import java.lang.System; 코드를 삭제해도 정상 동작

