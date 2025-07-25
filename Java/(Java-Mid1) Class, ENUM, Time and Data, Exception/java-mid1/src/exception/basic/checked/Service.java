package exception.basic.checked;

/**
 * Checked 예외는 예외를 잡아서 처리 또는 던지거나 둘 중 하나를 필수적으로 선택
 */
public class Service {
    Client client = new Client();

    /**
     * 예외를 잡아서 처리하는 코드
     */
    public void callCatch() {
        try {
            client.call();
        } catch (MyCheckedException e) {
            // 예외 처리 로직
            System.out.println("예외 처리, message = " + e.getMessage());
        }
        System.out.println("정상 흐름");
    }

    /**
     * 체크 예외를 밖으로 던지는 코드 : 밖으로 던지려면 throws 예외를 메서드에 필수로 선언
     */
    public void callThrow() throws MyCheckedException {
        client.call();
    }
}
