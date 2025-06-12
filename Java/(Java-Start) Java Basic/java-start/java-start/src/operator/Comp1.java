package operator;

public class Comp1 {
    public static void main(String[] args) {
        int a = 2;
        int b = 3;

        System.out.println(a == b); // false, a와 b는 같지 않음
        System.out.println(a != b); // true, a와 b는 다름
        System.out.println(a > b); // false, a는 b보다 크지 않음
        System.out.println(a < b); // true, a는 b보다 작음
        System.out.println(a >= b); // false, a는 b보다 크거나 같지 앟음
        System.out.println(a <= b); // true, a는 b보다 작거나 같음

        // 결과물 boolean 변수에 담김
        boolean result = a == b; // a == b (false)
        System.out.println(result);
    }
}
