package chat.client;

import util.MyLogger;

import java.io.DataInputStream;
import java.io.IOException;

import static util.MyLogger.*;

public class ReadHandler implements Runnable {
    private final DataInputStream input; // 콘솔을 통해 메세지를 전송
    private final Client client; // 클라이언트 객체
    private boolean closed = false; // 닫힘 여부

    public ReadHandler(DataInputStream input, Client client) {
        this.input = input;
        this.client = client;
    }

    @Override
    public void run() {
            try {
                while(true) {
                    String received = input.readUTF();
                    System.out.println(received);
                }
            } catch (IOException e) {
                log(e);
            } finally {
                client.close();
        }
    }

    public synchronized void close() { // 동시 호출 가능성 존재 (Client 종료 또는 ReadHandler 예외)
        if(closed) {
            return;
        }

        // 종료 로직 필요시 작성
        closed = true;
        log("ReadHandler 종료");
    }
}
