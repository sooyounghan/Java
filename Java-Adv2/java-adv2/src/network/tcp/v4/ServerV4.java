package network.tcp.v4;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static util.MyLogger.log;

public class ServerV4 {
    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        log(" 서버 시작"); // MyLogger Static Import

        ServerSocket serverSocket = new ServerSocket(PORT); // 서버 생성 (PORT 개방)

        log("서버 소켓 시작 - Listening Port: " + PORT);

        while(true) {
            Socket socket = serverSocket.accept(); // 블로킹
            log("소켓 연결: " + socket);

            SessionV4 session = new SessionV4(socket); // Session 객체 생성

            Thread thread = new Thread(session);// 별도의 thread 생성
            thread.start(); // thread 실행
        }
    }
}
