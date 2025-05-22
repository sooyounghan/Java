package collection.set;

public class MyHashSetMain {
    public static void main(String[] args) {
        MyHashSetV0 set = new MyHashSetV0();
        set.add(1); // O(1)
        set.add(2); // O(n)
        set.add(3); // O(n)
        set.add(4); // O(n)
        System.out.println("set = " + set);

        boolean result = set.add(4); // 중복 데이터 저장
        System.out.println("중복 데이터 저장 결과 = " + result);
        System.out.println("set = " + set);

        System.out.println("set.contains(3) = " + set.contains(3)); // O(n)
        System.out.println("set.contains(4) = " + set.contains(99)); // O(n)
    }
}
