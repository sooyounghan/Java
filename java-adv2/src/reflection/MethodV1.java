package reflection;

import reflection.data.BasicData;

import java.lang.reflect.Method;

public class MethodV1 {
    public static void main(String[] args) {
        Class<BasicData> helloClass = BasicData.class;

        System.out.println("===== methods() ======");
        Method[] methods = helloClass.getMethods();

        for (Method method : methods) {
            System.out.println("method = " + method); // public method만 추출 (즉, 나와 부모의 클래스 중 public method만 추출)
        }


        System.out.println("===== declaredMethods() ======");
        Method[] declaredMethods = helloClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println("declaredMethod = " + declaredMethod); // 선언한 모든 메서드 (private, protected, public, default 추출), 단, 상속받은 메서드는 제외
        }
    }
}
