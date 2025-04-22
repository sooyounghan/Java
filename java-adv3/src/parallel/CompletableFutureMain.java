package parallel;

import util.MyLogger;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static util.MyLogger.*;

public class CompletableFutureMain {
    public static void main(String[] args) throws InterruptedException {
        // CompletableFuture - 비동기로 실행
        CompletableFuture.runAsync(() -> log("Fork/Join")); // Fork/Join 공용 풀
        Thread.sleep(1000);

        ExecutorService es = Executors.newFixedThreadPool(100);
        CompletableFuture.runAsync(() -> log("Custom Pool"), es); // 커스텀 풀 사용
        Thread.sleep(1000);
        es.close();
    }
}
