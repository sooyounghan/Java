package was.v2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import static util.MyLogger.log;

public class HttpRequestHandlerV2 implements Runnable {
    private final Socket socket;

    public HttpRequestHandlerV2(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            process();
        } catch (Exception e) {
            log(e);
        }
    }

    private void process() throws IOException {
        try (socket;
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8)); // 한 줄 읽기 편리
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), false, StandardCharsets.UTF_8)) { // Writer 기능 용

            StringBuilder sb = new StringBuilder();
            String line;

            String requestString = requestToString(reader, sb);

            // GET /favicon.ico HTTP/1.1
            if(requestString.contains(("/favicon.ico"))) { // Favicon 요청
                log("favicon 요청");
                return;
            }

            log("HTTP 요청 정보 출력");
            System.out.println("requestString = " + requestString);

            log("HTTP 응답 생성중 ...");
            getResponseToClient(writer);
            log("HTTP 응답 전달 완료");
        }
    }


    private static void getResponseToClient(PrintWriter writer) {
        // 웹 브라우저에 전달하는 내용

        String body = "<h1>Hello World</h1>";

        int length = body.getBytes(StandardCharsets.UTF_8).length; // Byte 크기

        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/1.1 200 OK\r\n"); // Start-Line
        sb.append("Content-Type: text/html\r\n");
        sb.append("Content-Length: ").append(length).append("\r\n");
        sb.append("\r\n"); // CRLF (Header, Body 구분 라인)
        sb.append(body);


        log("HTTP 응답 정보 출력");
        sleep(5000); // 5초간 대기
        System.out.println(sb); // Body

        writer.println(sb); // Buffer에 저장 (Autoflush : false)
        writer.flush(); // Buffer 내용 전송

        log("HTTP 응답 정보 완료");
    }


    private static String requestToString(BufferedReader reader, StringBuilder sb) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) { // readLine() : 개행 삭제
            if(line.isEmpty()) {
                break;
            }
            sb.append(line).append("\n");  // 개행 추가
        }

        return sb.toString();
    }


    private static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
