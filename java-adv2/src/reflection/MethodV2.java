package reflection;

import reflection.data.BasicData;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodV2 {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // 정적 메서드 호출 - 일반적인 메서드 호출
        BasicData helloInstance = new BasicData();

        helloInstance.call(); // 코드를 변경하지 않는 이상 정적

        // 동적 메서드 호출 - 리플렉션 사용
        Class<? extends BasicData> helloClass = helloInstance.getClass();

        String methodName = "hello";

        // 메서드 이름을 변수로 변경 가능
        Method method1 = helloClass.getDeclaredMethod(methodName, String.class);// 메서드 이름, 매개변수 타입
        System.out.println("method1 = " + method1); // 메서드 정보 추출 (어떤 객체인지 알 수 없음)
        Object returnValue = method1.invoke(helloInstance, "hi");// hello(String) 실행하되, 해당 인스턴스 정보와 인자(args) 제공해줘야 함
        System.out.println("returnValue = " + returnValue);
    }
}
