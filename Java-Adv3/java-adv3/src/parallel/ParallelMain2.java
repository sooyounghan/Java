package parallel;

import util.MyLogger;

import java.util.stream.IntStream;

import static util.MyLogger.log;

public class ParallelMain2 {
    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();

        // 1. Fork 작업을 분할
        SumTask task1 = new SumTask(1, 4);
        SumTask task2 = new SumTask(5, 8);

        Thread thread1 = new Thread(task1, "thread-1");
        Thread thread2 = new Thread(task2, "thread-2");

        // 2. 분할한 작업 실행
        thread1.start();
        thread2.start();

        // 3. Join 작업 - 처리한 결과를 합침
        thread1.join(); // 다른 스레드가 처리가 끝날때 까지 Main Thread 대기
        thread2.join(); // 다른 스레드가 처리가 끝날때 까지 Main Thread 대기
        log("Main 스레드 대기 완료");

        int sum = task1.result + task2.result;

        long endTime = System.currentTimeMillis();

        log("time : " + (endTime - startTime) + "ms, sum : " + sum);
    }

    static class SumTask implements Runnable {
        int startValue;
        int endValue;
        int result;

        public SumTask(int startValue, int endValue) {
            this.startValue = startValue;
            this.endValue = endValue;
        }

        @Override
        public void run() {
            log("작업 시작");
            int sum = 0;
            for (int i = startValue; i <= endValue; i++) {
                int calculated = HeavyJob.heavyTask(i);
                sum += calculated;
            }
            result = sum;
            log("작업 완료, result = " + result);
        }
    }
}
