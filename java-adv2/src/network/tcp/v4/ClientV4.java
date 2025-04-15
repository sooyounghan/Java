package network.tcp.v4;

import network.tcp.SocketCloseUtil;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import static util.MyLogger.log;

public class ClientV4 {
    private static final int PORT = 12345; // PORT

    public static void main(String[] args) throws IOException {
        log(" 클라이언트 시작");

        // fianlly 블록에서 변수에 접근해야 하는데, try 블록 안에서 선언하면 접근이 불가능하므로, Scope 문제로 선언
        Socket socket = null;
        DataInputStream input = null;
        DataOutputStream output = null;
        try {
            socket = new Socket("localhost", PORT);// Host : Localhost > TCP/IP를 통해 localhost:port로 접속

            input = new DataInputStream(socket.getInputStream()); // Socket의 InputStream을 DataInputStream 보조스트림으로 사용
            output = new DataOutputStream(socket.getOutputStream());// Socket의 OutputStream을 DataOutputStream 보조스트림으로 사용

            log("소켓 연결: " + socket);

            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.print("전송 문자: ");
                String toSend = scanner.nextLine();

                // 서버로부터 문자 보내기
                output.writeUTF(toSend);
                log("client -> server: " + toSend);

                // exit라고 입력하면 종료
                if(toSend.equals("exit")) {
                    break;
                }

                // 서버로부터 문자 받기
                String received = input.readUTF();
                log("client <- server: " + received);
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
