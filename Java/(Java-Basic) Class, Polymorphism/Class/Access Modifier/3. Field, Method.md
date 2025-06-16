-----
### 접근 제어자 사용 - 필드, 메서드
-----
1. 다양한 상황에 따른 접근 제어자를 확인
2. 필드, 메서드 레벨의 접근 제어자
   - AccessData (/access/a)
```java
package access.a;

public class AccessData {
    
    public int publicField;
    int defaultField;
    private int privateField;
    
    public void publicMethod() {
        System.out.println("publicMethod 호출 : " + publicField);
    }
    
    void defaultMethod() {
        System.out.println("defaultMethod 호출 : " + defaultField);
    }
    
    private void privateMethod() {
        System.out.println("privateMethod 호출 : " + privateField);
    }
    
    public void innerAccess() {
        System.out.println("내부 호출");
        
        publicField = 100;
        defaultField = 200;
        privateField = 300;
        
        publicMethod();
        defaultMethod();
        privateMethod();
    }
}
```
   - 패키지 위치는 package access.a 
   - 순서대로 public, default, private을 필드와 메서드에 사용
   - innerAccess() : 이 메서드는 내부 호출을 보여줌
      + 내부 호출은 자기 자신에게 접근하는 것
      + 따라서 private을 포함한 모든 곳에 접근할 수 있음

3. 외부에서 이 클래스에 접근 : AccessInnerMain
```java
package access.a;

public class AccessInnerMain {
    public static void main(String[] args) {
        AccessData data = new AccessData();

        // public 호출 가능
        data.publicField = 1;
        data.publicMethod();

        // 같은 패키지 default 호출 가능
        data.defaultField = 2;
        data.defaultMethod();

        // private 호출 불가
        // data.privateField = 3;
        // data.privateMethod();

        data.innerAccess();
    }
}
```
  - 실행 결과
```
publicMethod 호출 : 1
defaultMethod 호출 : 2
내부 호출
publicMethod 호출 : 100
defaultMethod 호출 : 200
privateMethod 호출 : 300
```

   - 패키지 위치는 package access.a
   - public : 모든 접근을 허용하기 때문에 필드, 메서드 모두 접근 가능
   - default : 같은 패키지에서 접근할 수 있음 (AccessInnerMain은 AccessData와 같은 패키지 : 따라서 default 접근 제어자에 접근할 수 있음)
   - private : AccessData 내부에서만 접근할 수 있으므로, 따라서 호출 불가
   - AccessData.innerAccess() 메서드 : public으로, 따라서 외부에서 호출할 수 있음
      + 💡 innerAccess() 메서드는 외부에서 호출되었지만 innerAccess() 메서드는 AccessData에 포함 : 이 메서드는 자신의 private 필드와 메서드에 모두 접근할 수 있음

4. AccessOuterMain (/access/b)
```java
package access.b;

import access.a.AccessData;

public class AccessOuterMain {
    public static void main(String[] args) {
        AccessData data = new AccessData();

        // public 호출 가능
        data.publicField = 1;
        data.publicMethod();

        // 다른 패키지 default 호출 불가
        // data.defaultField = 2;
        // data.defaultMethod();

        // private 호출 불가
        // data.privateField = 3;
        // data.privateMethod();

        data.innerAccess();
    }
}
```
  - 실행 결과
```
publicMethod 호출 : 1
내부 호출
publicMethod 호출 : 100
defaultMethod 호출 : 200
privateMethod 호출 : 300
```
  - 패키지 위치는 package access.b
   - public : 모든 접근을 허용하기 때문에 필드, 메서드 모두 접근 가능
   - default : 같은 패키지에서 접근할 수 있음 (access.b.AccessOuterMain은 access.a.AccessData 와 다른 패키지 : 따라서 default 접근 제어자에 접근할 수 없음)
   - private : AccessData 내부에서만 접근할 수 있으므로, 따라서 호출 불가
   - AccessData.innerAccess() 메서드 : public으로, 따라서 외부에서 호출할 수 있음
      + 💡 innerAccess() 메서드는 외부에서 호출되었지만 innerAccess() 메서드는 AccessData에 포함 : 이 메서드는 자신의 private 필드와 메서드에 모두 접근할 수 있음

5. 참고 : 생성자도 접근 제어자 관점에서 메서드와 같음
