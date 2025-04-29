package thread.cas;

import java.util.concurrent.atomic.AtomicInteger;

import static thread.util.MyLogger.log;

public class CasMainV2 {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger();
        System.out.println("Start value = " + atomicInteger.get());

        // incrementAndGet 구현
        int resultValue1 = incrementAndGet(atomicInteger);
        System.out.println("resultValue1 = " + resultValue1);

        int resultValue2 = incrementAndGet(atomicInteger);
        System.out.println("resultValue2 = " + resultValue2);
    }

    private static int incrementAndGet(AtomicInteger atomicInteger) {
        int getValue;
        boolean result;

        do {
            getValue = atomicInteger.get(); // 현재 값을 읽음 (예) 1. thread1 : 0 -> 3-1. thread1 : 1)
            log("getValue = " + getValue);

            result = atomicInteger.compareAndSet(getValue, getValue + 1);// 2. 그 값을 Update하는 시점에, 읽은 값과 getValue가 같다면 CAS 연산을 통해 하나 증가 (0일 때만 증가) (thread1: 1)
            // 3. if, thread2가 value -> 1로 설정해버린다면 false가 되어버리므로 반복문 다시 실행
            // 3-2. 이 때는 참이 되므로 thread1 : 2)
            log("result = " + result);
        } while (!result);

        return getValue + 1;
    }

}
