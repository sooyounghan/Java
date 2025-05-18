package was.v6;

import was.httpserver.HttpServer;
import was.httpserver.ServletManager;
import was.httpserver.servlet.DiscardServlet;
import was.httpserver.servlet.reflection.ReflectionServlet;
import was.v5.servlet.HomeServlet;

import java.io.IOException;
import java.util.List;

public class ServerMainV6 {

    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        List<Object> controllers = List.of(new SiteController(), new SearchController());

        ReflectionServlet reflectionServlet = new ReflectionServlet(controllers);

        ServletManager servletManager = new ServletManager();
        servletManager.setDefaultServlet(reflectionServlet); // Servlet Manager의 Default Servlet을 Reflection Servlet으로 설정
        servletManager.add("/", new HomeServlet()); // Home Servlet 추가
        servletManager.add("/favicon.ico", new DiscardServlet()); // /favicon 관련 Discard Servlet 추가

        HttpServer server = new HttpServer(PORT, servletManager);
        server.start();;
    }
}
