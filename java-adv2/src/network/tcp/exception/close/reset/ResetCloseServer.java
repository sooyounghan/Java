package network.tcp.exception.close.reset;

import util.MyLogger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static util.MyLogger.*;

public class ResetCloseServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(12345);

        Socket socket = serverSocket.accept();

        log("소켓 연결: " + socket);

        socket.close();
        serverSocket.close();

        log("소켓 종료");
    }
}
