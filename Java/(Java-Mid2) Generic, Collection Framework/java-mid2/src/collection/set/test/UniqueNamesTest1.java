package collection.set.test;

import java.util.HashSet;
import java.util.Set;

public class UniqueNamesTest1 {
    public static void main(String[] args) {
        Integer[] inputArr = {30, 20, 20, 10, 10};

        HashSet<Integer> hashSet = new HashSet<>();

        for (Integer integer : inputArr) {
            hashSet.add(integer);
        }

        for (Integer integer : hashSet) {
            System.out.println(integer);
        }
    }
}