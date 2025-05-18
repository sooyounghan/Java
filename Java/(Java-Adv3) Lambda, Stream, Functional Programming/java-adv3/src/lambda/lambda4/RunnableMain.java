package lambda.lambda4;

public class RunnableMain {
    public static void main(String[] args) {
        // 익명 클래스
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello Runnable");
            }
        };

        runnable.run();


        // 람다
        Runnable runnable1 = () -> System.out.println("Hello Runnable");
        runnable1.run();
    }
}
