package lambda.lambda1;

import lambda.MyFunction;

public class LambdaSimple1 {
    public static void main(String[] args) {
        // 기본
        MyFunction myFunction = (int a, int b) -> {
            return a + b;
        };

        System.out.println("function1 : " + myFunction.apply(1, 2));

        // 단일 표현식의 경우 중괄호와 리턴 생략 가능
        MyFunction myFunction1 = (int a, int b) -> a + b;
        System.out.println("function2 : " + myFunction1.apply(1, 2));

        // 단일 표현식이 아닐 경우, 중괄호와 리턴 모두 필수
        MyFunction myFunction2 = (int a, int b) -> {
            System.out.println("람다 실행");
            return a + b;
        };
        System.out.println("function3 : " + myFunction2.apply(1, 2));


    }
}
