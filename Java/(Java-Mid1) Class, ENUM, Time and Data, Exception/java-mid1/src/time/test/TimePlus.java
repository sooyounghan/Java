package time.test;

import java.time.LocalDateTime;

public class TimePlus {
    public static void main(String[] args) {
        LocalDateTime dateTime = LocalDateTime.of(2024, 1, 1, 0, 0, 0);
        LocalDateTime futureDateTime = dateTime.plusYears(1).plusMonths(2).plusDays(3).plusHours(4);

        System.out.println("기준 시간 : " + dateTime);
        System.out.println("1년 2개월 3일 4시간 후 시각 : " + futureDateTime);
    }
}
