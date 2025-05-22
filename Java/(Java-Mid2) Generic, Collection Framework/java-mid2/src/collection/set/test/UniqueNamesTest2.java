package collection.set.test;

import java.util.*;

public class UniqueNamesTest2 {
    public static void main(String[] args) {
        Set<Integer> treeSet = new TreeSet<>(List.of(30, 20, 20, 10, 10));

        for (Integer integer : treeSet) {
            System.out.println(integer);
        }
    }
}