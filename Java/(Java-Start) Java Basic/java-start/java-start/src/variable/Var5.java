package variable;

public class Var5 {
    public static void main(String[] args) {
        // 1. 변수 선언과 초기화를 별도로 지정
        int a;
        a = 1;
        System.out.println(a);

        int b = 2; // 2. 변수 선언과 초기화를 동시에 진행
        System.out.println(b);

        int c = 3, d = 4; // 3. 여러 변수를 선언과 초기화 동시 진행
        System.out.println(c);
        System.out.println(d);
    }
}
