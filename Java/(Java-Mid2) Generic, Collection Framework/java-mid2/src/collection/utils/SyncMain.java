package collection.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class SyncMain {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        System.out.println("list.getClass() = " + list.getClass());

        List<Integer> synchronizaedList = Collections.synchronizedList(list);
        System.out.println("synchronizaedList.getClass() = " + synchronizaedList.getClass());
    }
}
