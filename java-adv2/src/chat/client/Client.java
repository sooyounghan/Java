package chat.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static network.tcp.SocketCloseUtil.closeAll;
import static util.MyLogger.log;

public class Client {

    private final String host;
    private final int port;

    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;

    private ReadHandler readHandler;
    private WriteHandler writeHandler;
    private boolean closed = false;


    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws IOException {
        log("클라이언트 시작");
        socket = new Socket(host, port);
        input = new DataInputStream(socket.getInputStream());
        output = new DataOutputStream(socket.getOutputStream());

        readHandler = new ReadHandler(input, this);
        writeHandler = new WriteHandler(output, this);

        Thread readThread = new Thread(readHandler, "readHandler"); // 별도의 Thread에서 ReadHandler 가동
        Thread writeThread = new Thread(writeHandler, "writeHandler"); // 별도의 Thread에서 WriteHandler 가동

        readThread.start();
        writeThread.start();
    }

    // 동시 호출 예방을 위해 동시성 처리
    public synchronized void close() {
        if (closed) {
            return;
        }

        writeHandler.close();
        readHandler.close();
        closeAll(socket, input,output); // 전체 자원 정리

        closed = true;
        log("연결 종료: " + socket);
    }
}
