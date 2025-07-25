package nested.local;

import java.lang.reflect.Field;

public class LocalOuterV4 {
    private int outInstancVar = 3;

    public Printer process(int paramVar) {
        int localVar = 1; // 지역 변수는 스택 프레임이 종료되는 순간 함께 제거

        class LocalPrinter implements Printer {
            int value = 0;

            @Override
            public void print() {
                System.out.println("value = " + value);

                // 인스턴스는 지역 변수보다 더 오래 존재
                System.out.println("localVar = " + localVar);
                System.out.println("paramVar = " + paramVar);
                System.out.println("outInstancVar = " + outInstancVar);
            }
        }
        Printer printer = new LocalPrinter();

        // 만약 localVar의 값을 변경한다면, 컴파일 오류
        // localVar = 10; // 컴파일 오류
        // paramVar = 20; // 컴파일 오류

        // printer.print()를 여기서 실행하지 않고, Printer 인스턴스만 반환
        return printer;
    }

    public static void main(String[] args) {
        LocalOuterV4 localOuter = new LocalOuterV4();

        Printer printer = localOuter.process(2);
        // printer.print()를 나중에 실행 : process()의 스택 프레임이 사라진 후 실행

        printer.print();
    }
}
