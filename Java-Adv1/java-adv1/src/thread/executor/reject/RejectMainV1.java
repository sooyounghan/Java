package thread.executor.reject;

import thread.executor.RunnableTask;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static thread.util.MyLogger.log;

public class RejectMainV1 {
    public static void main(String[] args) {
        ThreadPoolExecutor es = new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS, new SynchronousQueue<>(), new ThreadPoolExecutor.AbortPolicy());

        es.submit(new RunnableTask("task1"));
        try {
            es.submit(new RunnableTask("task2"));
        } catch (RejectedExecutionException e) {
            log("요청 초과");
            // 포기 또는 다시 시도 로직 추가하면 됨
            log(e);
        }

        es.close();
    }
}
