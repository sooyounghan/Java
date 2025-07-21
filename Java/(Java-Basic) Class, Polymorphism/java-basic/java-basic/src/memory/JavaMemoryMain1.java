package memory;

public class JavaMemoryMain1 {
    public static void main(String[] args) {
        System.out.println("Main Start");
        method1(10);
        System.out.println("Main End");
    }

    static void method1(int m1) {
        System.out.println("Method1 Start");

        int cal = m1 * 2;
        method2(cal);

        System.out.println("Method1 End");
    }

    static void method2(int m2) {
        System.out.println("Method2 Start");
        System.out.println("Method2 End");
    }
}
