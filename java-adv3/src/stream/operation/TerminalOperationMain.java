package stream.operation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TerminalOperationMain {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 2, 3, 4, 5, 5, 6, 7, 8, 9, 10);

        // 1. Collectors는 복잡한 수집이 필요할 때 사용
        System.out.println("1. collect - List 수집");
        List<Integer> evenNumber = numbers.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        System.out.println("evenNumber = " + evenNumber);
        System.out.println();

        // 2. toList
        System.out.println("2. toList() - Java16+");
        List<Integer> evenNumber2 = numbers.stream()
                .filter(n -> n % 2 == 0)
                .toList();
        System.out.println("evenNumber2 = " + evenNumber2);
        System.out.println();

        // 3. toArray() - 배열로 변환
        System.out.println("3. toArray() - 배열로 변환");
        Integer[] arr = numbers.stream()
                .filter(n -> n % 2 == 0)
                .toArray(Integer[]::new);
        System.out.println("arr = " + Arrays.toString(arr));
        System.out.println();

        // 4. forEach() - 각 요소 처리
        System.out.println("4. forEach() - 각 요소 처리");
        numbers.stream()
                .limit(5)
                .forEach(n -> System.out.print(n + " "));
        System.out.println("\n");

        // 5. count() - 요소 개수
        System.out.println("5. count() - 요소 개수");
        long count = numbers.stream()
                .filter(n -> n > 5)
                .count();
        System.out.println("count = " + count);
        System.out.println();

        // 6. reduce() - 요소들의 합
        System.out.println("6. reduce() - 요소들의 합");
        System.out.println("초기 값이 없는 reduce");
        Optional<Integer> sum1 = numbers.stream() // 초기값이 아예 없다면 값이 없을 수 있으므로 Optional
                .reduce((a, b) -> a + b);
        System.out.println("합계 (초기값 없음) = " + sum1.get());
        System.out.println();
        System.out.println("초기 값이 있는 reduce");
        Integer sum2 = numbers.stream()
                .reduce(100, (a, b) -> a + b); // 아무 값이 없으면 초기값 반환하
        System.out.println("합계 (초기값 100) = " + sum2);
        System.out.println();

        // 7. min() - 최소값
        System.out.println("7. min() - 최소값");
        Optional<Integer> min = numbers.stream()
                .min(Integer::compareTo); // (integer, anotherInteger) -> integer.compareTo(anotherInteger)
        System.out.println("min = " + min.get());
        System.out.println();

        // 8. max() - 최대값
        System.out.println("8. max() - 최대값");
        Optional<Integer> max = numbers.stream()
                .max(Integer::compareTo);
        System.out.println("max = " + max.get());
        System.out.println();

        // 9. findFirst() - 첫 번째 요소 추출
        System.out.println("9. findFirst() - 첫 번쨰 요소 추출");
        Optional<Integer> first = numbers.stream()
                .filter(n -> n > 5)
                .findFirst();
        System.out.println("first = " + first.get());
        System.out.println();

        // 10. findAny() - 아무 요소나 하나 추출
        System.out.println("10. findFirst() - 첫 번쨰 요소 추출");
        Optional<Integer> any = numbers.stream()
                .filter(n -> n > 5)
                .findAny();
        System.out.println("first = " + any.get());
        System.out.println();

        // 11. anyMatch() - 조건을 만족하는 요소 존재 여부 반환
        System.out.println("12. anyMatch() - 조건을 만족하는 요소 존재 여부");
        boolean hasEven = numbers.stream()
                .anyMatch(n -> n % 2 == 0);
        System.out.println("hasEven = " + hasEven);
        System.out.println();

        // 12. allMatch() - 모든 요소가 조건 만족 여부 반환
        System.out.println("13. allMatch() - 모든 요소 조건 만족 여부");
        boolean allPositive = numbers.stream()
                .allMatch(n -> n > 0);
        System.out.println("allPositive = " + allPositive);
        System.out.println();

        // 13. noneMatch() - 조건을 만족하는 요소가 없는지 확인
        System.out.println("13. noneMatch() - 조건을 만족하는 요소가 없는지 여부");
        boolean noNegative = numbers.stream()
                .noneMatch(n -> n < 0);
        System.out.println("noNegative = " + noNegative);
        System.out.println();
    }
}
