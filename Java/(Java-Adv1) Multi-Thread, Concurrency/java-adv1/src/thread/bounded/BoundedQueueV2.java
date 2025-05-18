package thread.bounded;

import java.util.ArrayDeque;
import java.util.Queue;

import static thread.util.MyLogger.log;
import static thread.util.ThreadUtils.sleep;

public class BoundedQueueV2 implements BoundedQueue{
    private final Queue<String> quque = new ArrayDeque<>();
    private final int max;

    public BoundedQueueV2(int max) {
        this.max = max;
    }

    @Override
    public synchronized void put(String data) {
        while (quque.size() == max) {
            log("[put] 큐가 가득참, 생산자 대기");
            sleep(1000);
        }

        quque.offer(data);
    }

    @Override
    public synchronized String take() {
        while (quque.isEmpty()) {
            log("[take] 큐에 데이터가 없음, 소비자 대기");
            sleep(1000);
        }
        return quque.poll();
    }

    @Override
    public String toString() {
        return quque.toString();
    }
}
