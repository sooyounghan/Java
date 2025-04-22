package stream.collectors;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DownStreamMain1 {
    public static void main(String[] args) {
        List<Student> students = List.of(
                new Student("Kim", 1, 85),
                new Student("Park", 1, 70),
                new Student("Lee", 2, 70),
                new Student("Han", 2, 90),
                new Student("Hoon", 3, 90),
                new Student("Ha", 3, 89)
        );

        // 1단계 : 학년별 학생들을 그룹화
        Map<Integer, List<Student>> collect1_1 = students.stream()
                .collect(Collectors.groupingBy(
                        Student::getGrade, // 그룹화 기준 : 학년
                        Collectors.toList() // 다운 스트림 : 학생을 리스트로 수집
                ));

        System.out.println("collect1_1 = " + collect1_1);


        // 1-2 단계 : 다운 스트림에서 toList() 생략 가능
        Map<Integer, List<Student>> collect1_2 = students.stream()
                .collect(Collectors.groupingBy(
                        Student::getGrade // 그룹화 기준 : 학년
                ));

        System.out.println("collect1_2 = " + collect1_2);

        // 2단계 : 학년별로 학생들 이름 출력
        Map<Integer, List<String>> collect2_1 = students.stream()
                .collect(Collectors.groupingBy(
                        Student::getGrade, // 그룹화 기준 : 학년
                        Collectors.mapping(
                                Student::getName, // 다운스트림 1 : 학년별로 그룹화 한 이후 그룹화 기준 : 학생들 이름
                                Collectors.toList() // 다운 스트림 2 : 학생을 리스트로 수집
                        )
                ));
        System.out.println("collect2_1 = " + collect2_1);

        // 3단계 : 학년별로 학생들 수 출력
        Map<Integer, Long> collect3 = students.stream()
                .collect(Collectors.groupingBy(
                        Student::getGrade,
                        Collectors.counting()
                ));
        System.out.println("collect3 = " + collect3);

        // 4단계 : 학년별로 학생들 평균 성적 출력
        Map<Integer, Double> collect4 = students.stream()
                .collect(Collectors.groupingBy(
                        Student::getGrade,
                        Collectors.averagingInt(Student::getScore)
                ));
        System.out.println("collect4 = " + collect4);
    }
}
