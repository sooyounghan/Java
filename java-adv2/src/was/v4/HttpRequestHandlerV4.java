package was.v4;

import was.httpserver.HttpRequest;
import was.httpserver.HttpResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import static util.MyLogger.log;

public class HttpRequestHandlerV4 implements Runnable {
    private final Socket socket;

    public HttpRequestHandlerV4(Socket socket) {
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

            HttpRequest request = new HttpRequest(reader);
            HttpResponse response = new HttpResponse(writer);

            // GET /favicon.ico HTTP/1.1
            if(request.getPath().contains(("/favicon.ico"))) { // Favicon 요청
                log("favicon 요청");
                return;
            }

            log("HTTP 요청 정보 출력");
            System.out.println("request = " + request);

            log("HTTP 응답 생성중 ...");
            if (request.getPath().equals("/site1")) {
                site1(response);
            } else if (request.getPath().equals("/site2")) {
                site2(response);
            } else if (request.getPath().equals("/search")) {
                search(request, response);
            } else if (request.getPath().equals("/")) {
               home(response);
            } else {
                notFound(response);
            }
            response.flush();
            log("HTTP 응답 전달 완료");
        }
    }

    private void site1(HttpResponse response) {
        response.writeBody("<h1>site1</h1>");
    }

    private void site2(HttpResponse response) {
        response.writeBody("<h1>site2</h1>");
    }

    // GET /search?q=hello
    private void search(HttpRequest request, HttpResponse response) {
        String query = request.getParameter("q");

        response.writeBody("<h1>search</h1>");
        response.writeBody("<ul>");
        response.writeBody("<li>query: " + query + "</li>");
        response.writeBody("</ul>");
    }

    private void home(HttpResponse response) {
        // 원칙적으로는 Content-Length를 계산해서 전달해야 하지만, 단순성을 위해 생략
        response.writeBody("<h1>home</h1>");
        response.writeBody("<ul>");
        response.writeBody("<li><a href='/site1'>site1</a></li>");
        response.writeBody("<li><a href='/site2'>site2</a></li>");
        response.writeBody("<li><a href='/search?q=hello'>search</a></li>");
        response.writeBody("</ui>");
    }

    private void notFound(HttpResponse response) {
        response.setStatusCode(404);
        response.writeBody("<h1>404 페이지를 찾을 수 없습니다.</h1>");
    }
}
