package lambda.start;

import lambda.Procedure;

import java.util.Random;

// 익명 클래스 사용
public class Ex1RefMainV2 {
    public static void main(String[] args) {
        Procedure dice = new Procedure() { // 익명 클래스
            @Override
            public void run() {
                //코드 조각 시작
                int randomValue = new Random().nextInt(6) + 1;
                System.out.println("주사위 = " + randomValue);
                //코드 조각 종료
            }
        };

        Procedure sum = new Procedure() { // 익명 클래스
            @Override
            public void run() {
                //코드 조각 시작
                for (int i = 1; i <= 3; i++) {
                    System.out.println("i = " + i);
                }
                //코드 조각 종료
            }
        };

        hello(dice);
        hello(sum);
    }

    public static void hello(Procedure procedure) {
        long startNs = System.nanoTime();

        procedure.run();

        long endNs = System.nanoTime();
        System.out.println("실행 시간: " + (endNs - startNs) + "ns");
    }
}