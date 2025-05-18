package was.httpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static util.MyLogger.log;

public class HttpRequest {
    private String method;
    private String path;
    // ?key1=value1&key2=value2
    private final Map<String, String> queryParameters = new HashMap<>();
    private final Map<String, String> headers = new HashMap<>();

    public HttpRequest(BufferedReader reader) throws IOException {
        parseRequestLine(reader);
        parseHeaders(reader);

        // Message Body Parsing
        parseBody(reader);
    }

    // GET /search?q=hello HTTP/1.1
    // Host : localhost:12345

    private void parseRequestLine(BufferedReader reader) throws IOException {
        String requestLine = reader.readLine();

        if(requestLine == null) {
            throw new IOException("EOF : No Request Line Recevied");
        }

        String[] parts = requestLine.split(" ");
        // 총 3개의 split
        if(parts.length != 3) {
            throw new IOException("Invalid Request Line : " + requestLine);
        }

        // GET
        method = parts[0];

        // /search?q=hello
        String[] pathParts = parts[1].split("\\?");
        path = pathParts[0]; // /search

        // q=hello
        if (pathParts.length > 1) { // 내용이 있다면
            parseQueryParameters(pathParts[1]);
        }
    }
    // key1=value1&key2=value2
    // 한글 가능 존재 : % 인코딩 처리

    private void parseQueryParameters(String queryString) {
       for(String param : queryString.split("&")) {
           String[] keyValue = param.split("=");
           String key = URLDecoder.decode(keyValue[0], StandardCharsets.UTF_8); // key
           // q= 형식으로 Value값이 없을 경우도 고려 (그럴 경우, 빈 문자)
           String value = keyValue.length > 1 ? URLDecoder.decode(keyValue[1], StandardCharsets.UTF_8) : ""; // value

           queryParameters.put(key, value);
       }
    }
    // Host: localhost:12345

    private void parseHeaders(BufferedReader reader) throws IOException {
        String line;

        // 값이 계속 있을 때까지
        while(!(line = reader.readLine()).isEmpty()) {
            String[] headerParts = line.split(":");

            // : 뒤 공백 있을 경우 대비 trim()으로 공백 제거
            headers.put(headerParts[0].trim(), headerParts[1].trim());
        }
    }

    private void parseBody(BufferedReader reader) throws IOException {
        if (!headers.containsKey("Content-Length")) {  // content-length가 없다면 body가 없는 것
            return;
        }

        int contentLength = Integer.parseInt(headers.get("Content-Length"));
        char[] bodyChars = new char[contentLength];

        int read = reader.read(bodyChars);
        if(read != contentLength) { // 읽은 것과 Content-Length의 길이가 다르면, 예외 발생
            throw new IOException("Fail to read entire body. Expected " + contentLength + "bytes, but read = " + read);
        }

        String body = new String(bodyChars);
        log("HTTP Message Body = " + body);

        String contentType = headers.get("Content-Type");
        if ("application/x-www-form-urlencoded".equals(contentType)) {
            // id=id3&name=name3&age=40
            parseQueryParameters(body); // 쿼리 파라미터 Parse
        }
    }

    // Getter

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public String getParameter(String name) {
        return queryParameters.get(name);
    }

    public String getHeader(String name) {
        return headers.get(name);
    }

    @Override
    public String toString() {
        return "HttpRequest{" +
                "method='" + method + '\'' +
                ", path='" + path + '\'' +
                ", queryParameters=" + queryParameters +
                ", headers=" + headers +
                '}';
    }
}
