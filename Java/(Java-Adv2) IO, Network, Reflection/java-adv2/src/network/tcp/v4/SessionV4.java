package network.tcp.v4;

import network.tcp.SocketCloseUtil;
import util.MyLogger;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static util.MyLogger.*;
import static util.MyLogger.log;

public class SessionV4 implements Runnable { // 별도의 Thread에서 구현을 위해 Runnable Implement
    private final Socket socket; // Session 객체 내부에 socket 필요

    public SessionV4(Socket socket) {
        this.socket = socket; // 생성자로 받음
    }

    @Override
    public void run() {

        // fianlly 블록에서 변수에 접근해야 하는데, try 블록 안에서 선언하면 접근이 불가능하므로, Scope 문제로 선언
        DataInputStream input = null;
        DataOutputStream output = null;

        try {
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());

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
            // 자원 정리
            SocketCloseUtil.closeAll(socket, input, output);
            log("연결 종료: " + socket);
        }
    }
}
