package time.test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Scanner;

public class TestCalendarPrinter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("연도를 입력하세요 : ");
        int year = scanner.nextInt();

        System.out.println("월을 입력하세요 : ");
        int month = scanner.nextInt();

        printCalnder(year, month);
    }

    public static void printCalnder(int year, int month) {
        LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
        LocalDate firstDayOfnNextMonth = firstDayOfMonth.plusMonths(1);

        // 월요일 (1 % 7 = 1), ..., 일요일 (7 % 7 = 0)
        int offsetWeekDays = firstDayOfMonth.getDayOfWeek().getValue() % 7;

        System.out.println("Su Mo Tu We Th Fr Sa");

        for(int i = 0; i < offsetWeekDays; i++) {
            System.out.print("   ");
        }

        LocalDate dayIterator = firstDayOfMonth;

        while(dayIterator.isBefore(firstDayOfnNextMonth)) {
            System.out.printf("%2d ", dayIterator.getDayOfMonth());
            if(dayIterator.getDayOfWeek() == DayOfWeek.SATURDAY) {
                System.out.println();
            }

            dayIterator = dayIterator.plusDays(1);
        }
    }
}
