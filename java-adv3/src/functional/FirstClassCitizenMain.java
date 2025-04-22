package functional;

import java.util.function.Function;

public class FirstClassCitizenMain {
    public static void main(String[] args) {
        // 함수를 변수에 저장
        Function<Integer, Integer> func = x -> x * 2;

        // 함수를 인자로 전달
        applyFunction(10, func);

        // 함수르 반환
        getFunc().apply(10);
    }

    // 고차 함수 : 함수를 인자로 받음
    private static Integer applyFunction(int input, Function<Integer, Integer> func) {
        return func.apply(input);
    }

    // 고차 함수 : 함수를 반환
    private static Function<Integer, Integer> getFunc() {
        return x -> x * 2;
    }
}
