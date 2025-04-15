package network.tcp.v1;

import util.MyLogger;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientV1 {
    private static final int PORT = 12345; // PORT

    public static void main(String[] args) throws IOException {
        MyLogger.log(" 클라이언트 시작");

        Socket socket = new Socket("localhost", PORT);// Host : Localhost > TCP/IP를 통해 localhost:port로 접속

        DataInputStream input = new DataInputStream(socket.getInputStream()); // Socket의 InputStream을 DataInputStream 보조스트림으로 사용
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());// Socket의 OutputStream을 DataOutputStream 보조스트림으로 사용

        MyLogger.log("소켓 연결: " + socket);

        // 서버로부터 문자 보내기
        String toSend = "Hello";
        output.writeUTF(toSend);
        MyLogger.log("client -> server: " + toSend);

        // 서버로부터 문자 받기
        String received = input.readUTF();
        MyLogger.log("client <- server: " + received);

        // 자원 정리
        MyLogger.log("연결 종료: " + socket);
        input.close();
        output.close();
        socket.close();
    }
}
