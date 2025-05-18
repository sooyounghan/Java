package network.tcp.autocloseable;

public class ResourceCloseMainV4 {
    public static void main(String[] args) {
        try {
            logic();
        } catch (CallException e) {
            System.out.println("CallException 예외 처리");

            Throwable[] suppressed = e.getSuppressed(); // Suppressed 된 예외 추출 [e.addSuppressed() : 핵심 예외에 부가 예외를 넣어줌]
            for (Throwable throwable : suppressed) {
                System.out.println("Suppressed Ex = " + throwable);
                /*
                    결과 : Suppressed Ex = network.tcp.autocloseable.CloseException: resource2 ex
                          Suppressed Ex = network.tcp.autocloseable.CloseException: resource1 ex
                 */
            }

            throw new RuntimeException(e); // 발생 예외
        } catch (CloseException e) { // CloseException을 받음
            System.out.println("CloseException 예외 처리");
            throw new RuntimeException(e);
        }
    }

    private static void logic() throws CallException, CloseException {
        try (ResourceV2 resource1 = new ResourceV2("resource1");
             ResourceV2 resource2 = new ResourceV2("resource2");) {
            resource1.call();
            resource2.callEx();

            /*
                try ~ with ~ resource문 : AutoCloseable로 구현된 클래스 또는 AutoCloseable의 close() 호출
                    - 예외 발생 전에 자원 정리 먼저 실행
                    - resource2 -> resource1 순으로 정리
                    - 자원 정리 중에 발생한 예외는 부가 예외로 처리 (Suppressed)
             */
            // resource2.close(); 자동 처리
            // resource1.close(); 자동 처리

        } catch (CallException e) { // 핵심 예외는 CallException (Caused by), Java에서 CallException 안에 자원 정리 간 발생 예외를 넣음
            System.out.println("ex: " + e);
            throw e;
        }
    }
}
