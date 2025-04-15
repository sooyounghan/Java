package network.tcp.v6;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static util.MyLogger.log;

public class ServerV6 {
    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        log(" 서버 시작"); // MyLogger Static Import

        SessionManagerV6 sessionManager = new SessionManagerV6(); // SessionManager

        ServerSocket serverSocket = new ServerSocket(PORT); // 서버 생성 (PORT 개방)

        log("서버 소켓 시작 - Listening Port: " + PORT);

        // Shutdown Hook 등록
        ShutdownHook shutdownHook = new ShutdownHook(serverSocket, sessionManager);

        Runtime.getRuntime().addShutdownHook(new Thread(shutdownHook, "shutdown")); // Shutdown Hook 등록 (shutdown이라는 스레드로 등록)

        try {
            while(true) {
                Socket socket = serverSocket.accept(); // 블로킹
                log("소켓 연결: " + socket);

                SessionV6 session = new SessionV6(socket, sessionManager); // Session 객체 생성

                Thread thread = new Thread(session);// 별도의 thread 생성
                thread.start(); // thread 실행
            }
        } catch (IOException e) {
            log("서버 소켓 종료: " + e);
        }
    }

    // Shutdown Hook : 자바가 종료될 때 자동 호출되도록 설정
    static class ShutdownHook implements Runnable {

        private final ServerSocket serverSocket;
        private final SessionManagerV6 sessionManager;

        public ShutdownHook(ServerSocket serverSocket, SessionManagerV6 sessionManager) {
            this.serverSocket = serverSocket;
            this.sessionManager = sessionManager;
        }

        @Override
        public void run() {
            log("Shutdown Hook 실행");

            try {

                sessionManager.closeAll(); // 세션 매니저를 통해 세션의 모든 자원 닫기
                serverSocket.close(); // 서버 소켓 닫기

                Thread.sleep(1000); // 자원 정리 대기 (다른 스레드의 로그를 남기는 것을 위해 설정)

            } catch(Exception e) {
                e.printStackTrace();
                System.out.println("e = " + e);
            }
        }
    }
}
