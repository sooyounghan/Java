-----
### 정적 중첩 클래스 (Static Nested Class)
-----
1. NestOuter (/nested/nested)
```java
package nested.nested;

public class NestedOuter {
    private static int outClassValue = 3;
    private int outInstanceValue = 2;
    
    static class Nested {
        private int nestedInstanceValue = 1;
        
        public void print() {
            // 자신의 멤버에 접근
            System.out.println(nestedInstanceValue);
            
            // 바깥 클래스 인스턴스 멤버에는 접근 불가
            // System.out.println(outInstanceValue);
            
            // 바깥 클래스 클래스 멤버에는 접근 가능 (private도 접근 가능)
            System.out.println(NestedOuter.outClassValue);
        }
    }
}
```
   - 정적 중첩 클래스는 앞에 static이 붙음
   - 정적 중첩 클래스는 자신의 멤버에는 당연히 접근할 수 있지만, 바깥 클래스의 인스턴스 멤버에는 접근할 수 없지만, 바깥 클래스의 클래스 멤버에는 접근할 수 있음
   - 참고로 NestedOuter.outClassValue를 outClassValue와 같이 줄여서 사용해도 됨 : 이 경우 바깥 클래스에 있는 필드를 찾아서 사용

2. private 접근 제어자
   - private 접근 제어자는 같은 클래스 안에 있을 때만 접근할 수 있음
   - 중첩 클래스도 바깥 클래스와 같은 클래스 안에 있음 :  따라서 중첩 클래스는 바깥 클래스의 private 접근 제어자에 접근할 수 있음
   - NestedOuterMain
```java
package nested.nested;

public class NestedOuterMain {
    public static void main(String[] args) {
        NestedOuter outer = new NestedOuter();
        NestedOuter.Nested nested = new NestedOuter.Nested();
        nested.print();

        System.out.println("nestedClass = " + nested.getClass());
    }
}
```
   - 💡 정적 중첩 클래스는 new 바깥클래스.중첩클래스()로 생성 가능
   - 중첩 클래스는 NestedOuter.Nested와 같이 바깥 클래스.중첩클래스로 접근 가능
   - 여기서 new NestedOuter()로 만든 바깥 클래스의 인스턴스와 new NestedOuter.Nested()로 만든 정적 중첩 클래스의 인스턴스는 서로 아무 관계가 없는 인스턴스 : 단지 클래스 구조상 중첩해 두었을 뿐임
   - 참고로 둘이 아무런 관련이 없으므로 정적 중첩 클래스의 인스턴스만 따로 생성해도 됨

   - 실행 결과
```
1
3
nestedClass = class nested.nested.NestedOuter$Nested
```
   - 중첩 클래스를 출력해보면 중첩 클래스의 이름은 NestedOuter$Nested와 같이 바깥 클래스, $, 중첩 클래스의 조합으로 만들어짐
   - 인스턴스가 생성된 상태
<div align="center">
<img src="https://github.com/user-attachments/assets/c5db6036-46f8-46b8-824c-4ff12153a9e8">
</div>

   - 바깥 클래스 멤버에 접근
<div align="center">
<img src="https://github.com/user-attachments/assets/8b9a57ae-50ff-4610-80e7-d86f8998257e">
</div>

   - Nested.print()
      + 정적 중첩 클래스는 바깥 클래스의 정적 필드에는 접근할 수 있음
      + 하지만 바깥 클래스가 만든 인스턴스 필드에는 바로 접근할 수 없음 : 바깥 인스턴스의 참조가 없기 때문임

3. 정리
   - 정적 중첩 클래스는 사실 다른 클래스를 그냥 중첩해 둔 것일 뿐임
   - 쉽게 이야기해서 둘은 아무런 관계가 없음
     + NestedOuter.outClassValue와 같은 정적 필드에 접근하는 것은 중첩 클래스가 아니어도 어차피 클래스명.정적 필드명 으로 접근할 수 있음
     + 쉽게 이야기해서 다음과 같이 정적 중첩 클래스를 만들지 않고, 그냥 클래스 2개를 따로 만든것과 같음
```java
class NestedOuter {

}

class Nested {

}
```
   - 이 코드와 정적 중첩 클래스의 유일한 차이는 같은 클래스에 있으니 private 접근 제어자에 접근할 수 있다는 정도임

