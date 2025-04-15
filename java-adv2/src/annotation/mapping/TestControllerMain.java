package annotation.mapping;

import java.lang.reflect.Method;

public class TestControllerMain {
    public static void main(String[] args) {
        TestController testController = new TestController();

        Class<? extends TestController> testClass = testController.getClass();

        Method[] declaredMethods = testClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            SimpleMapping simpleMapping = declaredMethod.getAnnotation(SimpleMapping.class); // 클래스 / 필드 / 메서드에 존재하는 Annotation 추출

            if(simpleMapping != null) {
                System.out.println("[" + simpleMapping.value() + "] -> " + declaredMethod);
            }
        }
    }
}
