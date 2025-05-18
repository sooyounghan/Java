package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ConstructV2 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        Class<?> targetClass = Class.forName("reflection.data.BasicData");

        Constructor<?> constructor = targetClass.getDeclaredConstructor(String.class);// String을 인자로 받는 생성자 호출
        constructor.setAccessible(true); // private 생성자이므로 접근할 수 있도록 설정
        Object instance = constructor.newInstance("hello");// hello는 인자 값
        System.out.println("instance = " + instance); // hello 문자열을 생성자로 한 객체 생성

        Method method = targetClass.getDeclaredMethod("call");
        method.invoke(instance); // 인자가 없으면 생략 가능
    }
}
