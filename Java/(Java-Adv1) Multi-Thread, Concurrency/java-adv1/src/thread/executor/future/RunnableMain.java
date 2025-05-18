package thread.executor.future;

import java.util.Random;

import static thread.util.MyLogger.log;
import static thread.util.ThreadUtils.sleep;

public class RunnableMain {
    public static void main(String[] args) throws InterruptedException {
        MyRunnable task = new MyRunnable();
        Thread thread = new Thread(task, "Thread-1");
        thread.start();
        thread.join();
        int result = task.value;
        log("Result Value = " + result);
    }

    static class MyRunnable implements Runnable {
        int value;

        @Override
        public void run() {
            log("Runnable 시작");
            sleep(2000);
            value = new Random().nextInt(10);// 0 ~ 9까지 무작위 값 하나 생성
            log("Create Value = " + value);
            log("Runnable 종료");
        }
    }
}
