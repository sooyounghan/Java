package was.httpserver.servlet.annotation;

import was.httpserver.HttpRequest;
import was.httpserver.HttpResponse;
import was.httpserver.HttpServlet;
import was.httpserver.PageNotFoundException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class AnnotationServletV2 implements HttpServlet {

    private final List<Object> controllers;

    public AnnotationServletV2(List<Object> controllers) {
        this.controllers = controllers;
    }

    @Override
    public void service(HttpRequest request, HttpResponse response) throws IOException {
        String path = request.getPath();

        for (Object controller : controllers) {
            Method[] methods = controller.getClass().getDeclaredMethods();

            for (Method method : methods) {
                if(method.isAnnotationPresent(Mapping.class)) {
                    Mapping mapping = method.getAnnotation(Mapping.class);

                    String value = mapping.value();

                    if(value.equals(path)) {
                        invoke(controller, method, request, response);
                        return;
                    }
                }
            }
        }
        throw new PageNotFoundException("request = " + path);
    }

    private static void invoke(Object controller, Method method, HttpRequest request, HttpResponse response) {
        Class<?>[] parameterTypes = method.getParameterTypes();// 메서드의 파라미터 타입 추출

        Object[] args = new Object[parameterTypes.length]; // 파라미터 타입 개수만큼 Object 배열 생성

        for(int i = 0; i < parameterTypes.length; i++) { // 파라미터 타입 개수만큼
            if(parameterTypes[i] == HttpRequest.class) { // HttpRequest 타입이면
                args[i] = request; // request 정보 넣기
            } else if(parameterTypes[i] == HttpResponse.class) { // HttpResponse 타입이면
                args[i] = response; // response 정보 넣기
            } else { // 아니면 예외 발생
                throw new IllegalArgumentException("Unsupported Parameter Type : " + parameterTypes[i]);
            }
        }

        try {
            method.invoke(controller, args);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
