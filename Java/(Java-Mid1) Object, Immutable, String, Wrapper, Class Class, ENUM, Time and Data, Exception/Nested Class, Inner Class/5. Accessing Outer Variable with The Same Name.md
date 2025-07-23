-----
### 같은 이름의 바깥 변수 접근
-----
1. 바깥 클래스의 인스턴스 변수 이름과 내부 클래스의 인스턴스 변수 이름이 같을 경우
2. ShadowningMain (/nested)
```java
package nested;

public class ShadowingMain {
    public int value = 1;
    
    class Inner {
        public int value = 2;
        
        void go() {
            int value = 3;

            System.out.println("value = " + value);
            System.out.println("this.value = " + this.value);
            System.out.println("ShadowingMain.this.value = " + ShadowingMain.this.value);
        }
    }

    public static void main(String[] args) {
        ShadowingMain shadowingMain = new ShadowingMain();
        Inner inner = shadowingMain.new Inner();
        inner.go();
    }
}
```
  - 실행 결과
```
value = 3
this.value = 2
ShadowingMain.this.value = 1
```

2. 변수의 이름이 같기 때문에 어떤 변수를 먼저 사용할지 우선순위가 필요
3. 프로그래밍에서 우선순위는 대부분 더 가깝꺼나, 더 구체적인 것이 우선권을 가짐
   - 쉽게 이야기해서 사람이 직관적으로 이해하기 쉬운 방향으로 우선순위를 설계
   - 메서드 go()의 경우 지역 변수인 value가 가장 가까움 : 따라서 우선순위가 가장 높음

4. 이렇게 다른 변수들을 가려서 보이지 않게 하는 것을 섀도잉(Shadowing)
   - 다른 변수를 가리더라도 인스턴스의 참조를 사용하면 외부 변수에 접근할 수 있음

5. this.value는 내부 클래스의 인스턴스에 접근하고, 바깥클래스이름.this는 바깥 클래스의 인스턴스에 접근할 수 있음
6. 프로그래밍에서 가장 중요한 것은 명확성
   - 이렇게 이름이 같은 경우 처음부터 이름을 서로 다르게 지어서 명확하게 구분하는 것이 더 나은 방법
