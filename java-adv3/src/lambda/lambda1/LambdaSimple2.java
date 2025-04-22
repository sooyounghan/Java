package lambda.lambda1;

import lambda.Procedure;

public class LambdaSimple2 {
    public static void main(String[] args) {
        Procedure procedure = () -> {
            System.out.println("Hello! Lambda");
        };

        procedure.run();

        // 단일 표현식은 중괄식 생략 가능
        Procedure procedure1 = () -> System.out.println("Hello! Lambda");
        procedure.run();
    }
}
