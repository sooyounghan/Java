package collection.set.test;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SetOperationsTest {
    public static void main(String[] args) {
        Set<Integer> set1 = new HashSet<>(List.of(1, 2, 3, 4, 5));
        Set<Integer> set2 = new HashSet<>(List.of(3, 4, 5, 6, 7));

        Set<Integer> unionSet = new HashSet<>(set1);
        unionSet.addAll(set2);

        System.out.println("합집합 : " + unionSet);

        Set<Integer> intersectionSet = new HashSet<>(set1);
        intersectionSet.retainAll(set2);

        System.out.println("교집합 : " + intersectionSet);

        Set<Integer> differenceSet = new HashSet<>(set1);
        differenceSet.removeAll(set2);

        System.out.println("차집합 : " + differenceSet);
    }
}