package operator;

public class Operator3 {
    public static void main(String[] args) {
        int sum1 = 1 + 2 * 3; // 1 + ( 2 * 3 )과 동일
        int sum2 = (1 + 2) * 3;

        System.out.println("sum1 = " + sum1); // sum1 = 7
        System.out.println("sum2 = " + sum2); // sum2 = 9
    }
}
