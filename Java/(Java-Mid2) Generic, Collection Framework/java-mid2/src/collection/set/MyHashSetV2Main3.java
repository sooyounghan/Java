package collection.set;

public class MyHashSetV2Main3 {
    public static void main(String[] args) {
        MyHashSetV3<String> set = new MyHashSetV3<>(10);
        set.add("A");
        set.add("B");
        set.add("C");
        System.out.println(set);
        // 검색
        String searchValue = "A";
        boolean result = set.contains(searchValue);
        System.out.println("set.contains(" + searchValue + ") = " + result);
    }
}
