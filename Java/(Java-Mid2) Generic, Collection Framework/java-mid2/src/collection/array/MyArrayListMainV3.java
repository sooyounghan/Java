package collection.array;

public class MyArrayListMainV3 {
    public static void main(String[] args) {
        MyArrayListV3 list = new MyArrayListV3();

        // 마지막에 추가 : O(1)
        list.add("a");
        list.add("b");
        list.add("c");

        System.out.println("list = " + list);

        // 원하는 위치에 추가
        System.out.println("addLast");
        list.add(3, "addList");
        System.out.println("list = " + list); // O(1)

        System.out.println("addFirst");
        list.add(0, "addList"); // O(n)
        System.out.println("list = " + list);

        // 삭제
        Object removed1 = list.remove(4);// removeLast : O(1)
        System.out.println("removed(4) = " + removed1);
        System.out.println("list = " + list);

        Object removed2 = list.remove(0);// removeFirst : O(n)
        System.out.println("removed(0) = " + removed2);
        System.out.println("list = " + list);
    }
}
