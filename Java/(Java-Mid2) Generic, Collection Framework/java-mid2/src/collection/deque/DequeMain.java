package collection.deque;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class DequeMain {
    public static void main(String[] args) {
        Deque<Integer> deque = new ArrayDeque<>();
        // Deque<Integer> deque = new LinkedList<>();

        // 데이터 추가
        deque.offerFirst(1);
        System.out.println("deque = " + deque);
        deque.offerFirst(2);
        System.out.println("deque = " + deque);
        deque.offerLast(3);
        System.out.println("deque = " + deque);
        deque.offerLast(4);
        System.out.println("deque = " + deque);

        // 다음 꺼낼 요소 확인 (꺼내지 않고, 단순히 조회) - peek
        System.out.println("deque.peekFirst() = " + deque.peekFirst());
        System.out.println("deque.peekLast() = " + deque.peekLast());

        // 데크 요소 추출
        System.out.println("deque.pollFirst() = " + deque.pollFirst());
        System.out.println("deque.pollFirst() = " + deque.pollFirst());
        System.out.println("deque.pollLast() = " + deque.pollLast());
        System.out.println("deque.pollLast() = " + deque.pollLast());
        System.out.println("deque = " + deque);

    }
}
