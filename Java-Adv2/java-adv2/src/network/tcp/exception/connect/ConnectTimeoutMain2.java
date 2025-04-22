package network.tcp.exception.connect;

import java.io.IOException;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

/*
    Windows : 약 21초
    Linux : 약 75초에서 180초 사이
    MAC : 약 75초
    -> java.net.Connection: Operation timed out
 */
public class ConnectTimeoutMain2 {
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();

        try {
            Socket socket = new Socket(); // 객체만 생성 시, 아직 연결되지 않음
            socket.connect(new InetSocketAddress("192.168.1.250", 45678), 1000); // connect() 메서드를 사용하면 해당 Hostname과 port로 연결되며, 2번째 인자는 timeout 설정
        } catch (SocketTimeoutException e) { // 해당 예외는 SocketTimeoutException
            // ConnectException: Operation timed out
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();

        System.out.println("time: = " + (end - start));
    }
}
