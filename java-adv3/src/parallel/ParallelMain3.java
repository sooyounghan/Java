package parallel;

import java.util.concurrent.*;

import static util.MyLogger.log;

public class ParallelMain3 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        // 스레드 풀 준비
        ExecutorService es = Executors.newFixedThreadPool(2);// 2개로 스레드 풀 제한

        long startTime = System.currentTimeMillis();

        // 1. Fork 작업을 분할
        SumTask task1 = new SumTask(1, 4);
        SumTask task2 = new SumTask(5, 8);

        // 2. 분할한 작업 처리
        Future<Integer> future1 = es.submit(task1); // Future<R> 타입 반환
        Future<Integer> future2 = es.submit(task2);

        // 3. Join 작업 - 처리한 결과를 합침 (get() : 결과가 나올 때까지 대기 (join()과 유사 / Blocking)
        Integer result1 = future1.get();
        Integer result2 = future2.get();
        log("Main 스레드 대기 완료");

        int sum = result1 + result2;

        long endTime = System.currentTimeMillis();

        log("time : " + (endTime - startTime) + "ms, sum : " + sum);
    }

    static class SumTask implements Callable<Integer> { // 반환값이 있는 경우 Runnable이 아닌 Callable<R> (반환값)
        int startValue;
        int endValue;
        int result;

        public SumTask(int startValue, int endValue) {
            this.startValue = startValue;
            this.endValue = endValue;
        }

        @Override
        public Integer call() { // Integer(<R>) 반환 (Override : call)
            log("작업 시작");
            int sum = 0;
            for (int i = startValue; i <= endValue; i++) {
                int calculated = HeavyJob.heavyTask(i);
                sum += calculated;
            }
            log("작업 완료, result = " + sum);
            return sum;
        }
    }
}
