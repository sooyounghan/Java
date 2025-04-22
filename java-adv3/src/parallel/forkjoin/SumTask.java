package parallel.forkjoin;

import parallel.HeavyJob;
import util.MyLogger;

import java.util.List;
import java.util.concurrent.RecursiveTask;

import static util.MyLogger.log;

public class SumTask extends RecursiveTask<Integer> {
    // private static final int THRESHOLD = 4;
    private static final int THRESHOLD = 2; // 임계값 변경

    private final List<Integer> list;

    public SumTask(List<Integer> list) {
        this.list = list;
    }

    @Override
    protected Integer compute() {
        // 작업 범위가 작으면 직접 계산
        if (list.size() <= THRESHOLD) {
            log("[처리 시작] " + list);
            int sum = list.stream()
                    .mapToInt(HeavyJob::heavyTask)
                    .sum();
            log("[처리 완료] " + list + " -> sum : " + sum);

            return sum;
        } else {
            // 작업 범위가 크면 나누어 병렬 처리
            int mid = list.size() / 2;
            List<Integer> leftList = list.subList(0, mid);
            List<Integer> rightList = list.subList(mid, list.size());
            log("[분할] " + list + ", LEFT  " + leftList + ", RIGHT " + rightList);

            // 두 개로 분할
            SumTask leftTask = new SumTask(leftList);
            SumTask rightTask = new SumTask(rightList);

            // 왼쪽 작업은 다른 스레드에서 처리, 오른쪽 작업은 현재 스레드에서 처리
            leftTask.fork(); // [1 ~ 4] (비동기)
            Integer rightResult = rightTask.compute(); // [5 ~ 8] -> 260

            // 왼쪽 작업을 기다린 후 결과 받기
            Integer leftResult = leftTask.join();

            int joinSum = leftResult + rightResult;
            log("LEFT[" + leftResult + "] + RIGHT[" + rightResult + "] -> sum : " + joinSum);
            return joinSum;
        }
    }
}
