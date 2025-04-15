package network.tcp.exception.connect;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ConnectMain {
    public static void main(String[] args) throws IOException {
        unknownHostEx1();
        unknownHostEx2();
        connectionRefused();
    }

    private static void unknownHostEx1() throws IOException {
        try {
            Socket socket = new Socket("999.999.999.999", 80); // IP 대역 오류
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
    private static void unknownHostEx2() throws IOException {
        try {
            Socket socket = new Socket("google.gogo", 80); // 도메인 이름 오류
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    private static void connectionRefused() throws IOException {
        try {
            Socket socket = new Socket("localhost", 45678); // 연결 오류
        } catch (ConnectException e) {
            e.printStackTrace();
        }
    }
}
