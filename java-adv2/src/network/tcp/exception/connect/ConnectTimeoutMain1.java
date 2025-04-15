package network.tcp.exception.connect;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;

/*
    Windows : 약 21초
    Linux : 약 75초에서 180초 사이
    MAC : 약 75초
    -> java.net.Connection: Operation timed out
 */
public class ConnectTimeoutMain1 {
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();

        try {
            Socket socket = new Socket("192.168.1.250", 45678); // 192.168.1.250에 SYN 전달
        } catch (ConnectException e) {
            // ConnectException: Operation timed out
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();

        System.out.println("time: = " + (end - start));
    }
}
