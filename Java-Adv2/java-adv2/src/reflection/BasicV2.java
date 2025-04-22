package reflection;

import reflection.data.BasicData;

import java.lang.reflect.Modifier;
import java.util.Arrays;

public class BasicV2 {
    public static void main(String[] args) {
        Class<BasicData> basicData = BasicData.class;

        System.out.println("basicData.getName() = " + basicData.getName());
        System.out.println("basicData.getSimpleName() = " + basicData.getSimpleName()); // 클래스명만 추출
        System.out.println("basicData.getPackage() = " + basicData.getPackage()); // 패키지명만 추출
        System.out.println("basicData.getSuperclass() = " + basicData.getSuperclass()); // 부모 클래스

        System.out.println("basicData.getInterfaces() = " + Arrays.toString(basicData.getInterfaces())); // 인터페이스
        System.out.println("basicData.isInterface() = " + basicData.isInterface()); // 인터페이스 여부 확인

        System.out.println("basicData.isEnum() = " + basicData.isEnum()); // Enum 여부 확인
        System.out.println("basicData.isAnnotation() = " + basicData.isAnnotation()); // 애너테이션 여부 확인

        int modifiers = basicData.getModifiers(); // 수정자 추출
        System.out.println("basicData.getModifiers() = " + modifiers); // 수정자 추출 (int 형태)
        System.out.println("isPublic = " + Modifier.isPublic(modifiers)); // 수정자 public 확인
        System.out.println("Modifier.toString() = " + Modifier.toString(modifiers)); // 문자열로 출력 (1 -> public)
    }
}
