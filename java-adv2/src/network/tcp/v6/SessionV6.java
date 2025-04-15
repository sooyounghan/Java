package network.tcp.v6;

import network.tcp.SocketCloseUtil;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static util.MyLogger.log;

public class SessionV6 implements Runnable { // 별도의 Thread에서 구현을 위해 Runnable Implement
    private final Socket socket; // Session 객체 내부에 socket 필요
    private final DataInputStream input; // close()에서 사용하기 위해 외부에 선언
    private final DataOutputStream output; // close()에서 사용하기 위해 외부에 선언
    private final SessionManagerV6 sessionManager;
    private boolean closed = false; // 세션에서 사용하는 close() 사용 유무

    public SessionV6(Socket socket, SessionManagerV6 sessionManager) throws IOException {
        this.socket = socket;

        this.input = new DataInputStream(socket.getInputStream());
        this.output = new DataOutputStream(socket.getOutputStream());

        this.sessionManager = sessionManager;
        this.sessionManager.add(this); // 세션 매니저에 생성된 Socket 추가
    }

    @Override
    public void run() {
        // try - with resource 사용 보다 try ~ catch ~ finally
        try {

            while (true) {
                // 클라이언트로부터 문자 받기
                String received = input.readUTF(); // 서버 입장에서는 클라이언트의 요청 메세지를 받음
                log("client -> server: "  + received);

                // 만약, 받은 메세지가 exit라면 종료
                if(received.equals("exit")) {
                    break;
                }

                // 클라이언트에게 문자 보내기
                String toSend = received + " World!";
                output.writeUTF(toSend);
                log("client <- server: " + toSend);
            }

        } catch (IOException e) {
            log(e);
        } finally {
            sessionManager.remove(this); // 먼저, SessionManager에서 해당 Session 제거
            close(); // 예외가 발생하거나 발생하지 않더라도 최종적으로 finally 구문에서 close() 호출
        }

    }

    // 세션 종료와 동시에 서버 종료 시 동시 호출 될 수 있으므로 동시성 처리 필요
    public synchronized void close() { // 모든 자원 닫기

        // 동시에 호출은 되지 않지만, 두 번 호출될 수 있으므로 이를 처리
        if (closed) { // 이미 close()가 호출되었다면, return
            return;
        }

        SocketCloseUtil.closeAll(socket, input, output); // SessionManagerV6에서 자원을 다 닫을 때 사용
        closed = true; // 호출되었으므로 true로 변경되므로 중복 호출 불가

        log("연결 종료: " + socket + " isClosed: " + socket.isClosed());
    }
}
