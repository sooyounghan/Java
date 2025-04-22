package stream.collectors;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Collectors2Map {
    public static void main(String[] args) {
        Map<String, Integer> map1 = Stream.of("Apple", "Banana", "Tomato")
                .collect(Collectors.toMap(
                        name -> name, // KeyMapper
                        name -> name.length()) // ValueMapper
                );
        System.out.println("map1 = " + map1);

        // Key 중복 : 키 중복 예외 발생 (java.lang.IllegalStateException: Duplicate key)
        /*
        Map<String, Integer> map2 = Stream.of("Apple", "Apple", "Tomato")
                .collect(Collectors.toMap(
                        name -> name, // KeyMapper
                        name -> name.length()) // ValueMapper
                );
        System.out.println("map2 = " + map2); 
        */
        
        // 키 중복 대안 (병합)
        Map<String, Integer> map3 = Stream.of("Apple", "Apple", "Tomato")
                .collect(Collectors.toMap(
                        name -> name, // KeyMapper
                        name -> name.length(), // ValueMapper
                        (oldVal, newVal) -> oldVal + newVal // 중복될 경우 기존 값 + 새 값을 처리하는 mergeFunction
                ));
        System.out.println("map3 = " + map3);
        
        // Map 타입 지정
        Map<String, Integer> map4 = Stream.of("Apple", "Apple", "Tomato")
                .collect(Collectors.toMap(
                        name -> name, // KeyMapper
                        name -> name.length(), // ValueMapper
                        (oldVal, newVal) -> oldVal + newVal, // 중복될 경우 기존 값 + 새 값을 처리하는 mergeFunction
                        LinkedHashMap::new // MapFactory (Map에 원하는 타입 지정 가능)
                ));
        System.out.println("map2 = " + map4);
        System.out.println("map4.getClass() = " + map4.getClass());
    }
}
