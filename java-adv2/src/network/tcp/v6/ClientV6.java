package network.tcp.v6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import static util.MyLogger.log;

public class ClientV6 {
    private static final int PORT = 12345; // PORT

    public static void main(String[] args) throws IOException {
        log(" 클라이언트 시작");

        // Socket, InputStream, OutputStream 모두 Autocloseable 구현
        try(Socket socket = new Socket("localhost", PORT);// Host : Localhost > TCP/IP를 통해 localhost:port로 접속
            DataInputStream input = new DataInputStream(socket.getInputStream()); // Socket의 InputStream을 DataInputStream 보조스트림으로 사용
            DataOutputStream output = new DataOutputStream(socket.getOutputStream())) { // Socket의 OutputStream을 DataOutputStream 보조스트림으로 사용

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
        }
    }
}
