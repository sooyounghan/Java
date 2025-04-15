package network.tcp.v5;

import network.tcp.SocketCloseUtil;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static util.MyLogger.log;

public class SessionV5 implements Runnable { // 별도의 Thread에서 구현을 위해 Runnable Implement
    private final Socket socket; // Session 객체 내부에 socket 필요

    public SessionV5(Socket socket) {
        this.socket = socket; // 생성자로 받음
    }

    @Override
    public void run() {
        try(socket;
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream())) {

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
        }

        log("연결 종료: " + socket + " isClosed: " + socket.isClosed());
    }
}
