package thread.executor.future;

import java.util.concurrent.*;

import static thread.util.MyLogger.log;
import static thread.util.ThreadUtils.sleep;

public class FutureCancelMain {
    private static boolean mayInterruptIfRunning = true; // 변경
    // private static boolean mayInterruptIfRunning = false; // 변경

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(1);
        Future<String> future = es.submit(new MyTask());
        log("Future.state = " + future.state());

        // 일정 시간 후 취소 시도
        sleep(3000);

        // cancel() 호출
        log("future.cancel(" + mayInterruptIfRunning + ") 호출");
        boolean cancelResult = future.cancel(mayInterruptIfRunning);
        log("future.state = " + future.state());
        log("future.cancel(" + mayInterruptIfRunning + ") result = " + cancelResult);

        try {
            log("Future Result = " + future.get());
        } catch (CancellationException e) { // 런타임 예외
            log("Future는 이미 취소되었습니다.");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        // Executor 종료
        es.close();
    }

    static class MyTask implements Callable<String> {

        @Override
        public String call() {
            try {
                for(int i = 0; i < 10; i++) {
                    log("작업 중 : " + i);
                    Thread.sleep(1000); // 1초 동안 Sleep
                }
            } catch (InterruptedException e) {
                log("인터럽트 발생");
                return "Interrupted";
            }

            return "Completed";
        }
    }
}
