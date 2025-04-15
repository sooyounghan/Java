package was.httpserver;

import was.httpserver.servlet.InternalErrorServlet;
import was.httpserver.servlet.NotFoundServlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ServletManager {
    private final Map<String, HttpServlet> servletMap = new HashMap<>();
    private HttpServlet defaultServlet;
    private HttpServlet notFoundErrorServlet = new NotFoundServlet();
    private HttpServlet internalServerErrorServlet = new InternalErrorServlet();

    public ServletManager() {

    }

    // 외부에서 서블릿을 등록
    public void add(String path, HttpServlet servlet) {
        servletMap.put(path, servlet);
    }

    public void execute(HttpRequest request, HttpResponse response) throws IOException {
        try {
            HttpServlet servlet = servletMap.getOrDefault(request.getPath(), defaultServlet);// Path는 Key 값, 없으면 default Servlet

            if(servlet == null) { // Servlet이 Null일 때,
                throw new PageNotFoundException("Request URL= " + request.getPath());
            }

            servlet.service(request, response); // 존재하면 해당 서블릿 실행

        } catch (PageNotFoundException e) {
            e.printStackTrace(); // PageNotFoundException
            notFoundErrorServlet.service(request, response); // 해당 서블릿 호출
        } catch (Exception e) {
            e.printStackTrace(); // 그 외
            internalServerErrorServlet.service(request, response); // InternalServerErrorServlet 호출
        }
    }

    // Setter
    public void setDefaultServlet(HttpServlet defaultServlet) {
        this.defaultServlet = defaultServlet;
    }

    public void setNotFoundErrorServlet(HttpServlet notFoundErrorServlet) {
        this.notFoundErrorServlet = notFoundErrorServlet;
    }

    public void setInternalServerErrorServlet(HttpServlet internalServerErrorServlet) {
        this.internalServerErrorServlet = internalServerErrorServlet;
    }
}
