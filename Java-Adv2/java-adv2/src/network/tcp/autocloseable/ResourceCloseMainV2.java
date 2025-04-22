package network.tcp.autocloseable;

public class ResourceCloseMainV2 {
    public static void main(String[] args) {
        try {
            logic();
        } catch (CallException e) {
            System.out.println("CallException 예외 처리");
            throw new RuntimeException(e);
        } catch (CloseException e) { // CloseException을 받음
            System.out.println("CloseException 예외 처리");
            throw new RuntimeException(e);
        }
    }

    private static void logic() throws CallException, CloseException {
        ResourceV1 resource1 = null; // 초기 선언은 null로 초기화
        ResourceV1 resource2 = null; // 초기 선언은 null로 초기화

        try {
            // 객체를 생성하는 경우에도 예외 발생 가능성 존재하므로 try ~ catch ~ fianlly 문 안에 선언해야 함
            resource1 = new ResourceV1("resource1"); // 만약 예외가 발생하면,
            resource2 = new ResourceV1("resource2"); // resource1의 예외로 인해 생성되지 않음

            resource1.call();
            resource2.callEx(); // CallException
        } catch (CallException e) { // CallException 예외 처리
            System.out.println("ex: " + e);
            throw e; // 예외 던짐
        } finally {  // CallException 예외 처리 후, fianlly로 이동
            if(resource2 != null) { // 위의 객체 생성 예외 경우를 대비해, 조건문
                resource2.closeEx(); // 자원 정리 단계에서 CloseException 발생 -> CloseException이 던져짐 (CallException이 아님) -> resoruce1 자원 정리 불가
            }

            if(resource1 != null) { // 위의 객체 생성 예외 경우를 대비해, 조건문
                resource1.closeEx(); // 호출되지 않음
            }
        }
    }
}
