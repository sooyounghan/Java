package chat.server;

import network.tcp.v6.SessionManagerV6;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static util.MyLogger.log;

public class Server {
    private final int port;
    private final CommandManager commandManager;
    private final SessionManager sessionManager;

    private ServerSocket serverSocket;

    public Server(int port, CommandManager commandManager, SessionManager sessionManager) {
        this.port = port;
        this.commandManager = commandManager;
        this.sessionManager = sessionManager;
    }

    public void start() throws IOException {
        log("서버 시작: " + commandManager.getClass());

        serverSocket = new ServerSocket(port);
        log("서버 소켓 시작 - 리스닝 포트: " + port);

        // 셧다운 훅 등록
        addShutdownHook();

        // 프로그램 작동
        running();
    }

    private void running() {
        try {
            while (true) {
                Socket socket = serverSocket.accept(); // Blocking

                log("소켓 연결: " + socket);

                Session session = new Session(socket, commandManager, sessionManager);
                Thread thread = new Thread(session);
                thread.start();
            }
        } catch (IOException e) {
            log("서버 소켓 종료: " + e);
        }
    }

    private void addShutdownHook() {
        ShutdownHook target = new ShutdownHook(serverSocket, sessionManager);
        Runtime.getRuntime().addShutdownHook(new Thread(target, "shutdown"));
    }

    // Shutdown Hook : 자바가 종료될 때 자동 호출되도록 설정
    static class ShutdownHook implements Runnable {

        private final ServerSocket serverSocket;
        private final SessionManager sessionManager;

        public ShutdownHook(ServerSocket serverSocket, SessionManager sessionManager) {
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
