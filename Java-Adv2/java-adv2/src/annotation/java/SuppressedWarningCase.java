package annotation.java;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SuppressedWarningCase {

    @SuppressWarnings("unused")
    public void unusedWarning() {
        // 사용되지 않는 변수 경고 억제
        int unusedVariable = 10;
    }

    @SuppressWarnings("deprecation")
    public void deprecatedMethod() {
        // 더 이상 사용되지 않는 메서드 호출
        Date date = new Date();
        int date1 = date.getDate();
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public void uncheckedCast() {
        // Generic Type Casting 경고 억제, Raw Type 사용 권고
        List list = new ArrayList<>();

        // Generic Type 타입과 과련된 Unchekced 경고
        List<String> stringList = (List<String>)list;
    }

    @SuppressWarnings("all") // 모든 경고 억제
    public void suppressAllWarnings() {
        Date date = new Date();
        int date1 = date.getDate();

        List list = new ArrayList<>();

        List<String> stringList = (List<String>)list;
    }
}
