-----
### Class 클래스
-----
1. 자바에서 Class 클래스는 클래스의 정보(메타데이터)를 다루는데 사용
2. Class 클래스를 통해 개발자는 실행 중인 자바 애플리케이션 내에서 필요한 클래스의 속성과 메서드에 대한 정보를 조회하고 조작할 수 있음
3. Class 클래스의 주요 기능
   - 타입 정보 얻기 : 클래스의 이름, 슈퍼클래스, 인터페이스, 접근 제한자 등과 같은 정보를 조회할 수 있음
   - 리플렉션 : 클래스에 정의된 메서드, 필드, 생성자 등을 조회하고, 이들을 통해 객체 인스턴스를 생성하거나 메서드를 호출하는 등의 작업을 할 수 있음
   - 동적 로딩과 생성 : Class.forName() 메서드를 사용하여 클래스를 동적으로 로드하고, newInstance() 메서드를 통해 새로운 인스턴스를 생성할 수 있음
   - 애노테이션 처리 : 클래스에 적용된 애노테이션(annotation)을 조회하고 처리하는 기능을 제공
  - 예를 들어, String.class 는 String 클래스에 대한 Class 객체를 나타내며, 이를 통해 String 클래스에 대한 메타데이터를 조회하거나 조작할 수 있음

4. ClassMetaMain (/lang/clazz)
```java
package lang.clazz;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ClassMetaMain {
    public static void main(String[] args) throws Exception {
        // Class 조회
        Class clazz = String.class; // 1. 클래스에서 조회

        // Class clazz = new String().getClass(); // 2. 인스턴스에서 조회

        // Class clazz = class.forName("java.lang.String"); // 3. 문자열로 조히

        // 모든 필드 출력
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            System.out.println("Field = " + field.getType() + " " + field.getName());
        }

        // 모든 메서드 출력
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println("Method : " + method);
        }

        // 상위 클래스 정보 출력
        System.out.println("Superclass : " + clazz.getSuperclass().getName());

        // 인터페이스 정보 출력
        Class[] interfaces = clazz.getInterfaces();
        for (Class i : interfaces) {
            System.out.println("Interface : " + i.getName());
        }
    }
}
```
   - class vs clazz - class는 자바의 예약어 : 따라서 패키지명, 변수명으로 사용할 수 없음
       + 이런 이유로 자바 개발자들은 class 대신 clazz라는 이름을 관행으로 사용
       + clazz는 class와 유사하게 들리고, 이 단어가 class를 의미한다는 것을 쉽게 알 수 있음

   - 💡 주의 : main() 옆에 throws Exception 이 추가된 부분에 주의 (이 코드가 없으면 컴파일 오류가 발생)
   - 실행 결과
```
Field = class [B value
...
Method : byte[] java.lang.String.value()
Method : public boolean java.lang.String.equals(java.lang.Object)
Method : public int java.lang.String.length()
...
Superclass : java.lang.Object
Interface : java.io.Serializable
Interface : java.lang.Comparable
...
```

5. Class 클래스는 다음과 같이 3가지 방법으로 조회
```java
Class clazz = String.class; // 1. 클래스에서 조회
Class clazz = new String().getClass(); // 2. 인스턴스에서 조회
Class clazz = Class.forName("java.lang.String"); // 3. 문자열로 조회
```

6. Class 클래스의 주요 기능
   - getDeclaredFields() : 클래스의 모든 필드를 조회
   - getDeclaredMethods(): 클래스의 모든 메서드를 조회
   - getSuperclass() : 클래스의 부모 클래스를 조회
   - getInterfaces() : 클래스의 인터페이스들을 조회

7. 클래스 생성하기
   - Class 클래스에는 클래스의 모든 정보가 들어있음
   - 이 정보를 기반으로 인스턴스를 생성하거나, 메서드를 호출하고, 필드의 값도 변경할 수 있음
   - 간단하게 인스턴스를 생성
   - Hello
```java
package lang.clazz;

public class Hello {
    public String hello() {
        return "hello!";
    }
}
```
   - ClassCreateMain
```java
package lang.clazz;

public class ClassCreateMain {
    public static void main(String[] args) throws Exception {
        // Class helloClass = Hello.class
        Class helloClass = Class.forName("lang.clazz.Hello");
        
        Hello hello = (Hello) helloClass.getDeclaredConstructor().newInstance();

        String result = hello.hello();
        
        System.out.println("result = " + result);
    }
}
```
   - 실행 결과
```
result = hello!
```

   - getDeclaredConstructor().newInstance()
      + getDeclaredConstructor() : 생성자를 선택
      + newInstance() : 선택된 생성자를 기반으로 인스턴스를 생성

8. 리플렉션 - reflection
   - Class를 사용하면 클래스의 메타 정보를 기반으로 클래스에 정의된 메서드, 필드, 생성자 등을 조회하고, 이들을 통해 객체 인스턴스를 생성하거나 메서드를 호출하는 작업을 할 수 있는데, 이런 작업을 리플렉션
   - 추가로 애노테이션 정보를 읽어서 특별한 기능을 수행할 수 도 있음
   - 최신 프레임워크들은 이런 기능을 적극 활용
