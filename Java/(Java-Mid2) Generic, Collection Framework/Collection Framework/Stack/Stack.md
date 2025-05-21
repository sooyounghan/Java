-----
### 스택 자료 구조
-----
1. 스택(Stack) 구조
  - 다음과 같은 1, 2, 3 이름표가 붙은 블록이 있다고 가정
<div align="center">
<img src="https://github.com/user-attachments/assets/215c5273-df1a-43df-98c8-60c46d583489">
</div>

  - 이 블록을 다음과 같이 아래쪽은 막혀 있고, 위쪽만 열려있는 통에 넣는다고 생각
  - 위쪽만 열려있기 때문에 위쪽으로 블록을 넣고, 위쪽으로 블록을 빼야 함
  - 쉽게 이야기해서 넣는 곳과 빼는 곳이 같음

<div align="center">
<img src="https://github.com/user-attachments/assets/9bdd1b6a-7143-439e-831f-edecf048f70b">
</div>

  - 블록은 1 → 2 → 3 순서대로 넣는다고 가정
  - 넣은 블록을 뺀다고 가
<div align="center">
<img src="https://github.com/user-attachments/assets/811bc00b-1e19-4e6c-8c3a-c532ec66a9f5">
</div>

  - 블록을 빼려면 위에서부터 순서대로 빼야함
  - 블록은 3 → 2 → 1 순서로 뺄 수 있음
  - 1(넣기) → 2(넣기) → 3(넣기) → 3(빼기) → 2(빼기) → 1(빼기)

2. 후입 선출(LIFO, Last In First Out)
   - 나중에 넣은 것이 가장 먼저 나오는 것을 후입 선출이라 하고, 이런 자료 구조를 스택
   - 전통적으로 스택에 값을 넣는 것을 push 라 하고, 스택에서 값을 꺼내는 것을 pop

3. StackMain (/collection/deque)
```java
package collection.deque;

import java.util.Stack;

// Stack은 사용하면 안 됨 : Deque 사용
public class StackMain {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();

        stack.push(1);
        stack.push(2);
        stack.push(3);

        System.out.println("stack = " + stack);

        // 다음 꺼낼 요소 확인 (꺼내지 않고, 단순히 조회) - peek
        System.out.println("stack.peek() = " + stack.peek());

        // 스택 요소 추출
        System.out.println("stack.pop() = " + stack.pop());
        System.out.println("stack.pop() = " + stack.pop());
        System.out.println("stack.pop() = " + stack.pop());
        System.out.println("stack = " + stack);
    }
}
```
  - 실행 결과
```
stack = [1, 2, 3]
stack.peek() = 3
stack.pop() = 3
stack.pop() = 2
stack.pop() = 1
stack = []
```

  - 실행 결과를 보면, 1, 2, 3으로 입력하면 3, 2, 1로 출력되는 것을 확인 가능
  - 나중에 입력한 값이 가장 먼저 나옴

4. 💡 주의 : Stack 클래스는 사용하지 말 것
  - 자바의 Stack 클래스는 내부에서 Vector 라는 자료 구조를 사용
  - 이 자료 구조는 자바 1.0에 개발되었는데, 지금은 사용되지 않고 하위 호환을 위해 존재
  - 따라서 Vector를 사용하는 Stack도 사용하지 않는 것을 권장
  - 대신에 이후에 설명할 Deque를 사용하는 것이 좋음
