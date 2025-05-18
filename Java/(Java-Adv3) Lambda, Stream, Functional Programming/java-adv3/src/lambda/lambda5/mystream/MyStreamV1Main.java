package lambda.lambda5.mystream;

import java.util.List;

public class MyStreamV1Main {
    public static void main(String[] args) {
        // 짝수만 남기고, 남은 값 2배 반환
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        returnValue(numbers);
        methodChain(numbers);
    }

    static void methodChain(List<Integer> numbers) {
        MyStreamV1 stream = new MyStreamV1(numbers);

        List<Integer> result = stream.filter(number -> number % 2 == 0)
                .map(number -> number * 2)
                .toList();

        System.out.println("result = " + result);
    }

    private static void returnValue(List<Integer> numbers) {
        MyStreamV1 stream = new MyStreamV1(numbers);

        MyStreamV1 filteredStream = stream.filter(number -> number % 2 == 0);

        MyStreamV1 mappedStream = filteredStream.map(number -> number * 2);

        List<Integer> result = mappedStream.toList();
        System.out.println("result = " + result);
    }
}
