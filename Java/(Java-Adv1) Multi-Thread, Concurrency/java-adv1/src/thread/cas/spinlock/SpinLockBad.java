package thread.cas.spinlock;

import static thread.util.MyLogger.log;
import static thread.util.ThreadUtils.sleep;

public class SpinLockBad {
    private volatile boolean lock = false; // Lock 유무

    public void lock() {
        log("락 획득 시도");

        while(true) {
            if (!lock) { // 1. 락 사용 여부 확인 (락 미사용)
                sleep(100); // 문제 상황 확인용, 스레드 대기
                lock = true; // 2. 락의 값 변경 (락 획득)
                break;
            } else {
                // 락을 획득할 때 까지 스핀 대기 (바쁜 대기)
                log("락 획득 실패 - 스핀 대기");
            }
        }
        log("락 획득 완료");
    }

    public void unlock() {
        lock = false;
        log("락 반납 완료");
    }
}
