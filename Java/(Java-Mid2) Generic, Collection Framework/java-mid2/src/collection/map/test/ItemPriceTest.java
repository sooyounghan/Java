package collection.map.test;
import java.util.*;
public class ItemPriceTest {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("사과", 500);
        map.put("바나나", 500);
        map.put("망고", 1000);
        map.put("딸기", 1000);

        List<String> list = new ArrayList<>();

        // 코드 작성
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        for (Map.Entry<String, Integer> entry : entries) {
            if(entry.getValue().equals(1000)) {
                list.add(entry.getKey());
            }
        }

        System.out.println("list = " + list);
    }
}