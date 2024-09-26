package modernjavainaction.chap12;

import java.time.*;
import java.time.temporal.*;
import java.util.TimeZone;

public class DateTimeMain {
    public static void main(String[] args) {
//        extracted();
//        extracted1();
//        extracted2();
//        extracted3();
//        extracted4();

        LocalDate date = LocalDate.now();
        date = date.plus(1, ChronoUnit.DAYS);
        LocalDate workingDay = date.with(new NextWorkingDay());
        System.out.println(workingDay);
        System.out.println(DayOfWeek.FRIDAY == workingDay.getDayOfWeek());
    }

    private static void extracted4() {
        LocalDate date1 = LocalDate.of(2014, 3, 18);
        DayOfWeek dayOfWeek = date1.getDayOfWeek();
        System.out.println(dayOfWeek);
        LocalDate monday = date1.with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY));
        System.out.println(monday.getDayOfWeek());
        System.out.println(monday.getDayOfMonth());
        System.out.println(monday.getDayOfYear());
    }

    private static void extracted3() {
        LocalDateTime startDateTime = LocalDateTime.of(2020, 1, 1, 10, 30, 0);
        LocalDateTime endDateTime = LocalDateTime.of(2023, 9, 26, 15, 45, 30);
        startDateTime.with(ChronoField.MONTH_OF_YEAR, 2);

        // 연, 월, 일 차이 계산
        Period period = Period.between(startDateTime.toLocalDate(), endDateTime.toLocalDate());

        // 시간 부분의 차이 계산
        LocalDateTime adjustedStartDateTime = startDateTime.plusYears(period.getYears())
                .plusMonths(period.getMonths())
                .plusDays(period.getDays());
        Duration duration = Duration.between(adjustedStartDateTime, endDateTime);

        // 결과 출력
        System.out.println("차이: " + period.getYears() + "년 "
                + period.getMonths() + "개월 "
                + period.getDays() + "일 "
                + duration.toHoursPart() + "시간 "
                + duration.toMinutesPart() + "분 "
                + duration.toSecondsPart() + "초");
    }

    private static void extracted2() {
        LocalTime startTime = LocalTime.of(10, 0);
        LocalTime endTime = LocalTime.of(12, 30);

        Duration duration = Duration.between(startTime, endTime);
        System.out.println("시간 차이: " + duration.toHours() + "시간 " + duration.toMinutesPart() + "분");

        LocalDate startDate = LocalDate.of(2020, 2, 1);
        LocalDate endDate = LocalDate.of(2023, 1, 26);

        Period period = Period.between(startDate, endDate);
        System.out.println("기간 차이: " + period.getYears() + "년 " + period.getMonths() + "개월 " + period.getDays() + "일");
    }

    private static void extracted1() {
        Instant instant = Instant.ofEpochSecond(3);
        Instant instant2 = Instant.ofEpochSecond(3, 1_000_000_000);
        Instant instant3 = Instant.ofEpochSecond(3, 1);
        System.out.println(instant);
        System.out.println(instant2);
        System.out.println(instant3);
        Instant now = Instant.now();
        System.out.println(now);
        //현재의 시간대를 어떻게 알까?
//        Instant.now().get(ChronoField.YEAR);
        ZoneId zoneId = ZoneId.systemDefault();
        TimeZone aDefault = TimeZone.getDefault();
        System.out.println(zoneId);
        System.out.println(aDefault);
    }

    private static void extracted() {
        LocalDate date = LocalDate.now();
        int i = date.get(ChronoField.YEAR);
        System.out.println(i);

        LocalTime time = LocalTime.now();
        time.getHour();
        int s1 = time.getSecond();
        LocalDateTime dateTime = LocalDateTime.of(date, time);
        dateTime.getHour();
        int s2 = dateTime.getSecond();


        System.out.println(s1 == s2);

        LocalTime time2 = dateTime.toLocalTime();
        System.out.println(time == time2);
        System.out.println(time);
        System.out.println(time2);

        System.out.println(1000 == 1_00_0);
    }

    private static class NextWorkingDay implements TemporalAdjuster {
        @Override
        public Temporal adjustInto(Temporal temporal) {
            DayOfWeek dayOfWeek = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
            if (dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY) {
                return temporal.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
            }
            return temporal.plus(1, ChronoUnit.DAYS);
        }
    }

    private interface Test {
        default void print() {
            System.out.println("default");
        }
    }

    private static class TestImpl implements Test {
//        @Override
        public void print() {
            System.out.println("default impl");
            Test.super.print();
        }
    }
}
