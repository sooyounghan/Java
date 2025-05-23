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
