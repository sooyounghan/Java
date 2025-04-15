package was.httpserver.servlet.reflection;

import was.httpserver.HttpRequest;
import was.httpserver.HttpResponse;
import was.httpserver.HttpServlet;
import was.httpserver.PageNotFoundException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class ReflectionServlet implements HttpServlet {

    private final List<Object> controllers;

    public ReflectionServlet(List<Object> controllers) {
        this.controllers = controllers;
    }

    @Override
    public void service(HttpRequest request, HttpResponse response) throws IOException {
        String path = request.getPath(); // site1

        // SiteController, SearchController
        for (Object controller : controllers) {
            Class<?> target = controller.getClass(); // SiteController

            Method[] methods = target.getDeclaredMethods(); // site1(), site2()
            for (Method method : methods) {
                String methodName = method.getName();

                if(path.equals("/" + methodName)) { // /site1.equals(/ + site1)
                    invoke(controller, method, request, response); // 일치하면, 인스턴스의 해당 메서드 실행
                    return; // 실행 후 Return 하여 종료
                }
            }
        }
        throw new PageNotFoundException("request = " + path); // 못 찾으면, PageNotFoundException
    }

    private static void invoke(Object controller, Method method, HttpRequest request, HttpResponse response) {
        try {
            method.invoke(controller, request, response); // SiteController 인스턴스, request, response
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
