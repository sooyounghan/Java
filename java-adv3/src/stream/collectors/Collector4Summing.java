package stream.collectors;

import java.util.IntSummaryStatistics;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Collector4Summing {
    public static void main(String[] args) {
        // 다운 스트림 컬렉터에서 유용하게 사용
        Long count1 = Stream.of(1, 2, 3)
                .collect(Collectors.counting());
        System.out.println("count1 = " + count1);

        long count2 = Stream.of(1, 2, 3)
                .count();
        System.out.println("count2 = " + count2);


        // 다운 스트림 컬렉터에서 유용하게 사용
        Double average1 = Stream.of(1, 2, 3)
                .collect(Collectors.averagingInt(i -> i));
        System.out.println("average1 = " + average1);

        // 기본형 특화 스트림으로 변환
        double average2 = Stream.of(1, 2, 3)
                .mapToInt(i -> i)
                .average()
                .getAsDouble();
        System.out.println("average2 = " + average2);

        // 기본형 특화 스트림 사용
        double average3 = IntStream.of(1, 2, 3)
                .average()
                .getAsDouble();
        System.out.println("average3 = " + average3);


        // 통계
        IntSummaryStatistics collect = Stream.of("Apple", "Banana", "Tomato")
                .collect(Collectors.summarizingInt(String::length));// 문자열 길이로 통계

        System.out.println("Apple, Banana, Tomato");
        System.out.println("collect.count = " + collect.getCount());
        System.out.println("collect.sum = " + collect.getSum());
        System.out.println("collect.min = " + collect.getMin());
        System.out.println("collect.max = " + collect.getMax());
        System.out.println("collect.average = " + collect.getAverage());
    }
}
