-----
### 내부 클래스
-----
1. 정적 중첩 클래스는 바깥 클래스와 서로 관계가 없지만, 하지만 내부 클래스는 바깥 클래스의 인스턴스를 이루는 요소가 됨
2. 쉽게 이야기해서 내부 클래스는 바깥 클래스의 인스턴스에 소속
   - 정적 중첩 클래스
      + static이 붙음
      + 바깥 클래스의 인스턴스에 소속되지 않음
   - 내부 클래스
      + static이 붙지 않음
      + 바깥 클래스의 인스턴스에 소속

3. InnerOuter (/nested/inner)
```java
package nested.inner;

public class InnerOuter {
    private static int outClassValue = 3;
    private int outInstanceValue = 2;
    
    class Inner {
        private int innerInstanceValue = 1;
        
        public void print() {
            // 자신의 멤버에 접근
            System.out.println(innerInstanceValue);
            
            // 외부 클래스 인스턴스 멤버 접근 가능 : private도 접근 가능
            System.out.println(outInstanceValue);
            
            // 외부 클래스 클래스 멤버 접근 가능 : private도 접근 가능
            System.out.println(InnerOuter.outClassValue);
        }
    }
}
```
  - 내부 클래스는 앞에 static이 붙지 않음 : 쉽게 이야기해서 인스턴스 멤버가 됨
  - 내부 클래스는 자신의 멤버에는 당연히 접근할 수 있으며, 바깥 클래스의 인스턴스 멤버에 접근할 수 있고, 바깥 클래스의 클래스 멤버에 접근할 수 있음
  - private 접근 제어자
    + private 접근 제어자는 같은 클래스 안에 있을 때만 접근할 수 있음
    + 내부 클래스도 바깥 클래스와 같은 클래스 안에 있으므로, 따라서 내부 클래스는 바깥 클래스의 private 접근 제어자에 접근할 수 있음

4. InnerOuterMain
```java
package nested.inner;

public class InnerOuterMain {
    public static void main(String[] args) {
        InnerOuter outer = new InnerOuter();
        InnerOuter.Inner inner = outer.new Inner();
        inner.print();

        System.out.println("inner.getClass() = " + inner.getClass());
    }
}
```
   - 내부 클래스는 바깥 클래스의 인스턴스에 소속 : 따라서 바깥 클래스의 인스턴스 정보를 알아야 생성할 수 있음
   - 💡 내부 클래스는 바깥클래스의 인스턴스 참조.new 내부클래스() 로 생성할 수 있음
      + 내부 클래스는 바깥 클래스의 인스턴스에 소속되어야 함
      + 따라서 내부 클래스를 생성할 때, 바깥 클래스의 인스턴스 참조가 필요
   - outer.new Inner()에서 outer는 바깥 클래스의 인스턴스 참조를 가짐
   - outer.new Inner()로 생성한 내부 클래스는 개념상 바깥 클래스의 인스턴스 내부에 생성
   - 따라서 바깥 클래스의 인스턴스를 먼저 생성해야 내부 클래스의 인스턴스를 생성할 수 있음
   - 실행 결과
```
1
2
3
inner.getClass() = class nested.inner.InnerOuter$Inner
```

   - 개념 - 내부 클래스의 생성
<div align="center">
<img src="https://github.com/user-attachments/assets/77d6d832-2f0c-47a9-9a73-7e06ee237f8e">
</div>

   - 개념상 바깥 클래스의 인스턴스 내부에서 내부 클래스의 인스턴스가 생성
   - 따라서 내부 인스턴스는 바깥 인스턴스를 알기 때문에 바깥 인스턴스의 멤버에 접근할 수 있음

   - 실제 - 내부 클래스 생성
<div align="center">
<img src="https://github.com/user-attachments/assets/eacb5182-00ed-4dcb-9c35-079de4ae1838">
</div>

   - 실제로 내부 인스턴스가 바깥 인스턴스 안에 생성되는 것은 아님
   - 💡 실제로는 내부 인스턴스는 바깥 인스턴스의 참조를 보관 : 이 참조를 통해 바깥 인스턴스의 멤버에 접근할 수 있음

5. 정리
   - 정적 중첩 클래스와 다르게 내부 클래스는 바깥 인스턴스에 소속
   - 중첩(Nested) : 어떤 다른 것이 내부에 위치하거나 포함되는 구조적인 관계
   - 내부(Inner) : 나의 내부에 있는 나를 구성하는 요소
   - 중첩(Nested)은 나의 안에 있지만 내것이 아닌 것을 말함 (단순히 위치만 안에 있는 것)
   - 반면에 여기서 의미하는 내부(Inner)는 나의 내부에서 나를 구성하는 요소
   - 정적 중첩 클래스는 다른 클래스를 그냥 중첩해 둔 것일 뿐임 : 쉽게 이야기해서 둘은 아무런 관계가 없음
   - 반면에 내부 클래스는 바깥 클래스의 인스턴스 내부에서 구성 요소로 사용
