package lang.system;

import java.util.Arrays;

public class SystemMain {
    public static void main(String[] args) {
        // 현재 시간 (밀리초)를 가져옴
        long currentTimeMillis = System.currentTimeMillis();
        System.out.println("currentTimeMillis = " + currentTimeMillis);

        // 현재 시간 (나노초)를 가져옴
        long currentTimeNano = System.nanoTime();
        System.out.println("currentTimeNano = " + currentTimeNano);

        // 환경 변수를 읽음
        System.out.println("getEnv = " + System.getenv());

        // 시스템 속성을 읽음
        System.out.println("properties = " + System.getProperties());
        System.out.println("Java Version = " + System.getProperty("java.version"));

        // 배열을 고속으로 복사
        char[] originArray = {'h', 'e', 'l', 'l', 'o'};
        char[] copiedArray = new char[5];
        System.arraycopy(originArray, 0, copiedArray, 0, originArray.length);

        // 배열 출력
        System.out.println("copiedArray = " + copiedArray);
        System.out.println("Arrays.toString(copiedArray) = " + Arrays.toString(copiedArray));

        // 프로그램 종료
        System.exit(0);
    }
}
