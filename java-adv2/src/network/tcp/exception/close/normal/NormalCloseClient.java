package network.tcp.exception.close.normal;

import util.MyLogger;

import java.io.*;
import java.net.Socket;

import static util.MyLogger.*;

public class NormalCloseClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 12345);

        log("소켓 연결: " + socket);
        InputStream input = socket.getInputStream();

        readByInputStream(input, socket);
        readByBufferedReader(input, socket);
        readByDataInputStream(input, socket);

        log("연결 종료: " + socket.isClosed());
    }

    private static void readByInputStream(InputStream input, Socket socket) throws IOException {
        int read = input.read(); // 상대측에서 연결을 끝게 되면, read()를 할 때의 값은 -1 (EOF)

        log("read = " + read);

        if(read == -1) { // 연결 종료되면,
            input.close(); // 자원 닫기
            socket.close(); // 소켓 닫기 : 상대방에게 FIN 패킷 전송
        }

        log("연결 종료");
    }


    private static void readByBufferedReader(InputStream input, Socket socket) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(input)); // 보조 스트림 BufferedReader는 InputStreamReader를 생성자로 받음

        String readString = br.readLine(); // BufferedReader는 readLine 메서드 사용, 반환 값 String

        log("readString = " + readString);

        if (readString == null) { // readString, 즉 BufferedReader의 경우 EOF에 다다르면, 그 값은 null (즉, 연결 종료되면 null)
            br.close(); // 자원 닫기
            socket.close(); // 소켓 닫기 : 상대방에게 FIN 패킷 전송
        }
    }

    private static void readByDataInputStream(InputStream input, Socket socket) throws IOException {
        DataInputStream dis = new DataInputStream(input);

        try {
            dis.readUTF(); // DataInputStream은 정수, 문자열 등 다양하게 올 수 있음.
            // EOF에 다다르면 EOFException 발생, 즉 연결 종료되면 EOFException 발생
        } catch (EOFException e) {
            log(e);
        } finally {
            dis.close(); // 자원 닫기
            socket.close(); // 소켓 닫기 : 상대방에게 FIN 패킷 전송
        }
    }
}
