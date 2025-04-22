package lambda.lambda1;

public class LambdaSimple4 {
    public static void main(String[] args) {
        MyCall myCall = (int value) -> value * 2; // 기본
        MyCall myCall2 = (value) -> value * 2; // 타입 추론
        MyCall myCall3 = value -> value * 2; // 매개변수가 1개일 때, () 생략 가능

        System.out.println("call3 = " + myCall3.call(10));
    }

    interface MyCall {
        int call(int value);
    }
}
