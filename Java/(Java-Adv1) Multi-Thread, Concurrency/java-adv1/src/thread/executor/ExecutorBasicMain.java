package thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static thread.executor.ExecutorUtils.*;
import static thread.util.MyLogger.log;
import static thread.util.ThreadUtils.sleep;

public class ExecutorBasicMain {
    public static void main(String[] args) {
        // ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit, BlockingQueue)
        ExecutorService es = new ThreadPoolExecutor(2, 2, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());

        log("== 초기 상태 ==");
        printState(es); // ExecutorUtils

        // 작업이 들어와서 실행되어야 그 시점에서 스레드 생성, 이후 재사용
        es.execute(new RunnableTask("TaskA"));
        es.execute(new RunnableTask("TaskB"));
        es.execute(new RunnableTask("TaskC"));
        es.execute(new RunnableTask("TaskD"));

        log("== 작업 수행 중 ==");
        printState(es);

        sleep(3000);

        log("== 작업 수행 완료==");
        printState(es);

        es.close(); // 스레드 풀 종료
        log("== shutdown 완료 ==");
        printState(es);
    }
}
