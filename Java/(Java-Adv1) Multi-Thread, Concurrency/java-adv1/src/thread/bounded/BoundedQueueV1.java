package thread.bounded;

import java.util.ArrayDeque;
import java.util.Queue;

import static thread.util.MyLogger.log;

public class BoundedQueueV1 implements BoundedQueue{
    private final Queue<String> quque = new ArrayDeque<>();
    private final int max;

    public BoundedQueueV1(int max) {
        this.max = max;
    }

    @Override
    public synchronized void put(String data) {
        if(quque.size() == max) {
            log("[put] 큐가 가득참, 버림 = " + data);
            return;
        }

        quque.offer(data);
    }

    @Override
    public synchronized String take() {
        if(quque.isEmpty()) {
            return null;
        }
        return quque.poll();
    }

    @Override
    public String toString() {
        return quque.toString();
    }
}
