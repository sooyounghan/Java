package was.httpserver.servlet.annotation;

import was.httpserver.HttpRequest;
import was.httpserver.HttpResponse;
import was.httpserver.HttpServlet;
import was.httpserver.PageNotFoundException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnnotationServletV3 implements HttpServlet {

    private final Map<String, ControllerMethod> pathMap;

    public AnnotationServletV3(List<Object> controllers) {
        this.pathMap = new HashMap<>();
        initializePathMap(controllers); // 생성자 인자로 받은 Controller 값으로 PathMap 초기화
    }

    private void initializePathMap(List<Object> controllers) {
        for (Object controller : controllers) {
            Method[] methods = controller.getClass().getDeclaredMethods();

            for (Method method : methods) {
                if(method.isAnnotationPresent(Mapping.class)) {
                    String path = method.getAnnotation(Mapping.class).value();

                    // 중복 경로 확인
                    if(pathMap.containsKey(path)) {
                        ControllerMethod controllerMethod = pathMap.get(path);
                        throw new IllegalArgumentException("경로 중복 등록, path = " + path + ", method = " + method + ", 이미 등록된 메서드 = " + controllerMethod.method);
                    }

                    pathMap.put(path, new ControllerMethod(controller, method)); // 애플리케이션 로딩 시점에 컨트롤러의 매핑 정보를 확인하여 저장
                }
            }
        }
    }

    @Override
    public void service(HttpRequest request, HttpResponse response) throws IOException {
        String path = request.getPath();

        ControllerMethod controllerMethod = pathMap.get(path); // pathMap에서 path의 값을 제공하면 해당 controllerMethod 추출 가능

        if(controllerMethod == null) {
            throw new PageNotFoundException("request = " + path);
        }

        controllerMethod.invoke(request, response);
    }

    private static class ControllerMethod {
        private final Object controller;
        private final Method method;

        public ControllerMethod(Object controller, Method method) {
            this.controller = controller;
            this.method = method;
        }

        public void invoke(HttpRequest request, HttpResponse response) {
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
}
