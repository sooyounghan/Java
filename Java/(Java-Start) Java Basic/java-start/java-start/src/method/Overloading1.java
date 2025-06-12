package method;

public class Overloading1 {
    public static void main(String[] args) {
        System.out.println("1 : " + add(1, 2));
        System.out.println("2 : " + add(1, 2, 3 ));
    }

    // 첫 번째 add 메서드 : 두 정수를 받아 합을 반환
    public static int add(int a, int b) {
        System.out.println("1번 호출");
        return a + b;
    }

    // 두 번째 add 메서드 : 세 정수를 받아 합을 반환
    // 첫 번째 메서드와 이름은 동일하지만, 매개변수 목록이 다름
    public static int add(int a, int b, int c) {
        System.out.println("2번 호출");
        return a + b + c;
    }
}
