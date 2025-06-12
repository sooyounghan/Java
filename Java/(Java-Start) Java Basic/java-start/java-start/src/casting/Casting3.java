package casting;

public class Casting3 {
    public static void main(String[] args) {
        long maxValue = 2147483647; // int 최고값
        long maxIntOver = 2147483648L; // int 최고값 + 1 (초과)
        int intValue = 0;

        intValue = (int) maxValue; // 형 변환
        System.out.println("maxIntValue Casting = " + intValue); // 출력 : 214783647

        intValue = (int) maxIntOver;
        System.out.println("maxIntOver Casting = " + intValue); // 출력 : -2147483648
    }
}
