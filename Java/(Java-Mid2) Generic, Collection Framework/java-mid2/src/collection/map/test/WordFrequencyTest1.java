package collection.map.test;

import java.util.HashMap;
import java.util.Map;

public class WordFrequencyTest1 {
    public static void main(String[] args) {
        String text = "orange banana apple apple banana apple";

        // 코드 작성
        Map<String, Integer> map = new HashMap<>();

        String[] word = text.split(" ");
        for (String keyword : word) {
            if(map.containsKey(keyword)) {
                map.put(keyword, map.get(keyword) + 1);
            } else {
                map.put(keyword, 1);
            }
        }

        /*
        for (String word : words) {
             map.put(word, map.getOrDefault(word, 0) + 1);
        }
        */

        System.out.println("map = " + map);
    }
}