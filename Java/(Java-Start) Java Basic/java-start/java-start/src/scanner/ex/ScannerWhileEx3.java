package scanner.ex;

import java.util.Scanner;

public class ScannerWhileEx3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int sum = 0;
        int count = 0;
        int num;

        // 반복문 예제 1
        while(true) {
            System.out.print("숫자를 입력하세요. 입력을 중단하려면 -1을 입력하세요. : ");
            num = scanner.nextInt();

            if (num == -1) {
                System.out.println("프로그램을 종료합니다.");
                break;
            }

            sum += num;
            count++;
        }

        // 반복문 예제 2
        /*
             while ((input = scanner.nextInt()) != -1) {
             sum += input;
             count++;
         }
        */

        double average = (double) sum / count;
        System.out.println("입력한 숫자들의 합계  : " + sum);
        System.out.println("입력한 숫자들의 평균  : " + average);
    }
}
