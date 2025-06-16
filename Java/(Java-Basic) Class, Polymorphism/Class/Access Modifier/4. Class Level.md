-----
### 접근 제어자 사용 - 클래스 레벨
-----
1. 💡 클래스 레벨의 접근 제어자 규칙
   - 클래스 레벨의 접근 제어자
      + public, default만 사용 가능
      + private, protected 는 사용할 수 없음

   - public 클래스는 반드시 파일명과 이름이 같아야 함    
      + 하나의 자바 파일에 public 클래스는 하나만 등장할 수 있음
      + 하나의 자바 파일에 default 접근 제어자를 사용하는 클래스는 무한정 만들 수 있음

2. PublicClass.java (/access/a)
```java
package access.a;

public class PublicClass {
    public static void main(String[] args) {
        PublicClass publicClass = new PublicClass();
        DefaultClass1 defaultClass1 = new DefaultClass1();
        DefaultClass2 defaultClass2 = new DefaultClass2();
    }
}

class DefaultClass1 {

}

class DefaultClass2 {

}
```
   - 패키지 위치는 package access.a 
   - PublicClass라는 이름의 클래스를 생성
     + public 접근 제어자 : 따라서 파일명과 이 클래스의 이름이 반드시 같아야 함
     + 이 클래스는 public 이기 때문에 외부에서 접근할 수 있음

   - DefaultClass1, DefaultClass2는 default 접근 제어자
     + default 이기 때문에 같은 패키지 내부에서만 접근할 수 있음
  
   - PublicClass의 main() 을 보면 각각의 클래스를 사용하는 예
     + PublicClass는 public 접근 제어자이므로, 따라서 어디서든 사용할 수 있음
     + DefaultClass1, DefaultClass2 와는 같은 패키지에 있으므로 사용할 수 있음

3. PublicClassInnerMain
```java
package access.a;

public class PublicClassInnerMain {
    public static void main(String[] args) {
        PublicClass publicClass = new PublicClass();
        DefaultClass1 class1 = new DefaultClass1();
        DefaultClass2 class2 = new DefaultClass2();
    }
}
```
   - 패키지 위치는 package access.a
   - PublicClass : public 클래스 (따라서 외부에서 접근할 수 있음)
   - PublicClassInnerMain와 DefaultClass1, DefaultClass2는 같은 패키지 (따라서 접근할 수 있음)

4. PublicClassOuterMain (/access/b)
```java
package access.b;

// import access.a.DefaultClass1;
// import access.a.DefaultClass2;
import access.a.PublicClass;

public class PublicClassOuterMain {
    public static void main(String[] args) {
        PublicClass publicClass = new PublicClass();

        // 다른 패키지 접근 불가
        // DefaultClass1 class1 = new DefaultClass1();
        // DefaultClass2 class2 = new DefaultClass2();
    }
}
```
   - 패키지 위치는 package access.b
   - PublicClass : public 클래스이다 (따라서 외부에서 접근할 수 있음)
   - PublicClassOuterMain와 DefaultClass1, DefaultClass2는 다른 패키지 (따라서 접근할 수 없음)
