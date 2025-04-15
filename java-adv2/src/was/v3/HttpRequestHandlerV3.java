package was.v3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import static util.MyLogger.log;

public class HttpRequestHandlerV3 implements Runnable {
    private final Socket socket;

    public HttpRequestHandlerV3(Socket socket) {
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
            if (requestString.startsWith("GET /site1")) {
                site1(writer);
            } else if (requestString.startsWith("GET /site2")) {
                site2(writer);
            } else if (requestString.startsWith("GET /search")) {
                search(writer, requestString);
            } else if (requestString.startsWith("GET / ")) { // GET / <- 필수
               home(writer);
            } else {
                notFound(writer);
            }
            log("HTTP 응답 전달 완료");
        }
    }

    private void site1(PrintWriter writer) {
        writer.println("HTTP/1.1 200 OK");
        writer.println("Content-Type: text/html; charset=UTF-8");
        writer.println();
        writer.println("<h1>site1</h1>");
        writer.flush();
    }

    private void site2(PrintWriter writer) {
        writer.println("HTTP/1.1 200 OK");
        writer.println("Content-Type: text/html; charset=UTF-8");
        writer.println();
        writer.println("<h1>site2</h1>");
        writer.flush();
    }

    // GET /search?q=hello
    private void search(PrintWriter writer, String requestString) {
        int startIndex = requestString.indexOf("q="); // q=의 시작 위치 추출

        int endIndex = requestString.indexOf(" ", startIndex + 2); // q=에서 = 이후부터, 공백이 발생하기 전까지 위치 추출

        String query = requestString.substring(startIndex + 2, endIndex);
        String decode = URLDecoder.decode(query, StandardCharsets.UTF_8);// 한글 처리 URL Decoder

        writer.println("HTTP/1.1 200 OK");
        writer.println("Content-Type: text/html; charset=UTF-8");
        writer.println();
        writer.println("<h1>search</h1>");
        writer.println("<ul>");
        writer.println("<li>query: " + query + "</li>");
        writer.println("<li>decode: " + decode + "</li>");
        writer.println("</ul>");
        writer.flush();
    }

    private void home(PrintWriter writer) {
        // 원칙적으로는 Content-Length를 계산해서 전달해야 하지만, 단순성을 위해 생략
        writer.println("HTTP/1.1 200 OK");
        writer.println("Content-Type: text/html; charset=UTF-8");
        writer.println();
        writer.println("<h1>home</h1>");
        writer.println("<ul>");
        writer.println("<li><a href='/site1'>site1</a></li>");
        writer.println("<li><a href='/site2'>site2</a></li>");
        writer.println("<li><a href='/search?q=hello'>search</a></li>");
        writer.println("</ui>");
        writer.flush();
    }

    private void notFound(PrintWriter writer) {
        writer.println("HTTP/1.1 404 Not Found");
        writer.println("Content-Type: text/html; charset=UTF-8");
        writer.println();
        writer.println("<h1>404 페이지를 찾을 수 없습니다.</h1>");
        writer.flush();
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

}
