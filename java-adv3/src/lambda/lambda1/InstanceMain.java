package lambda.lambda1;

import lambda.Procedure;

public class InstanceMain {
    public static void main(String[] args) {
        Procedure procedure = new Procedure() {
            @Override
            public void run() {
                System.out.println("Hello! Lambda!");
            }
        };

        System.out.println("Class.class = " + procedure.getClass());
        System.out.println("Class.instance = " + procedure);

        Procedure procedure1 = () -> {
            System.out.println("Hello! Lambda!");
        };

        System.out.println("Class.class = " + procedure1.getClass());
        System.out.println("Class.instance = " + procedure1);
    }
}
