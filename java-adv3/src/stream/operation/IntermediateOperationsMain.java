package stream.operation;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class IntermediateOperationsMain {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 2, 3, 4, 5, 5, 5, 6, 7, 8, 9, 10);

        // 1. filter
        System.out.println("1. filter - 짝수만 선택");
        numbers.stream()
                .filter(n -> n % 2 == 0)
                .forEach(n -> System.out.print(n + " "));
        System.out.println("\n");

        // 2. map
        System.out.println("2. map - 숫자 제곱");
        numbers.stream()
                .map(n -> n * n)
                .forEach(n -> System.out.print(n + " "));
        System.out.println("\n");

        // 3. distinct
        System.out.println("3. distinct - 중복 제거");
        numbers.stream()
                .distinct()
                .forEach(n -> System.out.print(n + " "));
        System.out.println("\n");

        // 4. sorted (기본 정렬)
        System.out.println("4. sorted - 기본 정렬");
        Stream.of(3, 1, 4, 1, 5, 9, 2, 6, 5)
                .sorted()
                .forEach(n -> System.out.print(n + " "));
        System.out.println("\n");

        // 5. sorted (커스텀 정렬)
        System.out.println("5. sorted with Comparator - 내림차순 정렬");
        Stream.of(3, 1, 4, 1, 5, 9, 2, 6, 5)
                .sorted(Comparator.reverseOrder())
                .forEach(n -> System.out.print(n + " "));
        System.out.println("\n");

        // 6. peek
        System.out.println("6. peek - 동작 확인용");
        numbers.stream()
                .peek(n -> System.out.print("before : " + n + ", "))
                .map(n -> n * n)
                .peek(n -> System.out.print("after : " + n + ", "))
                .limit(5)
                .forEach(n -> System.out.print("최종값 : " + n + "\n"));
        System.out.println();

        // 7. limit
        System.out.println("7. limit - 처음 5개 요소만 추출");
        numbers.stream()
                .limit(5)
                .forEach(n -> System.out.print(n + " "));
        System.out.println("\n");

        // 8. skip
        System.out.println("8. skip - 처음 5개 요소 건너뛰기");
        numbers.stream()
                .skip(5)
                .forEach(n -> System.out.print(n + " "));
        System.out.println("\n");

        // 9. takeWhile (Java 9+)
        List<Integer> numbers2 = List.of(1, 2, 3, 4, 5, 1, 2, 3);
        System.out.println("9. takeWhile - 5보다 작은 동안만 실행");
        numbers2.stream()
                .takeWhile(n -> n < 5) // 5가 작은 동안만 실행되며, 그 이후 미 실행 (따라서, 정렬에서 사용 효과적)
                .forEach(n -> System.out.print(n + " "));
        System.out.println("\n");


        // 10. dropWhile (Java 9+)
        System.out.println("10. dropWhile - 5보다 작은 동안 건너뛰기");
        numbers2.stream()
                .dropWhile(n -> n < 5)
                .forEach(n -> System.out.print(n + " "));
        System.out.println("\n");
    }
}
