package network.tcp.exception.close.reset;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;

import static util.MyLogger.log;

public class ResetCloseClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket("localhost", 12345);
        InputStream input = socket.getInputStream();
        OutputStream output = socket.getOutputStream();

        log("소켓 연결: " + socket);

        // client <- server: FIN
        Thread.sleep(1000); // 서버가 close()를 호출할 때 까지 대기

        // client -> server : PUSH[1] (패킷 전송 : PUSH)
        output.write(1); // 이 상태에서 패킷 전송 (FIN 이후 PUSH > TCP 규약에서 벗어남)

        // client <- server : RST 응답
        Thread.sleep(1000); // RST 메세지 전송 대기

        try {
            int read = input.read(); // RST를 받게 되면,
        } catch (SocketException e) { // SokcetException 발생
            e.printStackTrace();
        }

        try {
            output.write(1); // RST를 받게 된 후, 패킷 전송
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}
