package collection.deque;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class QueueMain {
    public static void main(String[] args) {
        Queue<Integer> queue = new ArrayDeque<>();
        // Queue<Integer> stack = new LinkedList<>();

        // 데이터 추가
        queue.add(1);
        queue.add(2);
        queue.add(3);
        System.out.println("queue = " + queue);

        // 다음 꺼낼 요소 확인 (꺼내지 않고, 단순히 조회) - peek
        System.out.println("queue.peek() = " + queue.peek());

        // 큐 요소 추출
        System.out.println("queue.poll() = " + queue.poll());
        System.out.println("queue.poll() = " + queue.poll());
        System.out.println("queue.poll() = " + queue.poll());
        System.out.println("queue = " + queue);

    }
}
