package thread.executor.poolsize;

import thread.executor.ExecutorUtils;
import thread.executor.RunnableTask;

import java.util.concurrent.*;

import static thread.util.MyLogger.log;
import static thread.util.ThreadUtils.sleep;

public class PoolSizeMainV1 {
    public static void main(String[] args) {
        ArrayBlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(2);

        ExecutorService es = new ThreadPoolExecutor(2, 4, 3000, TimeUnit.MILLISECONDS, workQueue);

        ExecutorUtils.printState(es);

        es.execute(new RunnableTask("task1"));
        ExecutorUtils.printState(es, "task1");

        es.execute(new RunnableTask("task2"));
        ExecutorUtils.printState(es, "task2");

        es.execute(new RunnableTask("task3")); // ququedTasks 1 증가
        ExecutorUtils.printState(es, "task3");

        es.execute(new RunnableTask("task4")); // ququedTasks 1 증가
        ExecutorUtils.printState(es, "task4");

        es.execute(new RunnableTask("task5")); // ququedTasks가 가득 찬 상황 -> pool이 1개 증가 (maximumPoolSize 1개 증가 : 1)
        ExecutorUtils.printState(es, "task5");

        es.execute(new RunnableTask("task6")); // ququedTasks가 가득 찬 상황 -> pool이 1개 증가 (maximumPoolSize 1개 증가 : 2 - maximumPoolSize 초과)
        ExecutorUtils.printState(es, "task6");

        try {
            es.execute(new RunnableTask("task7")); // ququedTasks가 가득 찬 상황 -> maximumPoolSize 초과 -> 예외 발생 : java.util.concurrent.RejectedExecutionExceptionn
            ExecutorUtils.printState(es, "task7");
        } catch (RejectedExecutionException e) {
            log("task7 실행 거절 예외 발생 : " + e);
        }

        sleep(3000);
        log("== 작업 수행 완료 ==");
        ExecutorUtils.printState(es);

        sleep(3000);
        log("== maximumPoolSize 대기 시간 초과 ==");
        ExecutorUtils.printState(es);

        es.close();
        log("== shutdown 완료 ==");
        ExecutorUtils.printState(es);
    }
}
