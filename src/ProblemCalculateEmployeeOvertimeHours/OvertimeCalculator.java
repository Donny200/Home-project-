package ProblemCalculateEmployeeOvertimeHours;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class OvertimeCalculator {

    public static void main(String[] args) {
        // Пример графика работы сотрудников
        LocalTime regularStartTime = LocalTime.of(9, 0);
        LocalTime regularEndTime = LocalTime.of(17, 0);
        int weeklyThresholdHours = 40;

        // Пример рабочего времени сотрудников
        LocalDateTime startDateTime = LocalDateTime.of(2021, 6, 14, 9, 0);
        LocalDateTime endDateTime = LocalDateTime.of(2021, 6, 18, 18, 0);

        // Расчет штатных и сверхурочных часов
        Duration totalWorkHours = Duration.between(startDateTime, endDateTime);
        long totalHours = totalWorkHours.toHours();

        long regularHours = calculateRegularHours(startDateTime, endDateTime, regularStartTime, regularEndTime);

        long overtimeHours = Math.max(totalHours - regularHours, 0);

        System.out.println("Общее количество рабочих часов: " + totalHours);
        System.out.println("Общие часы: " + regularHours);
        System.out.println("Сверхурочные часы: " + overtimeHours);
    }

    private static long calculateRegularHours(LocalDateTime startDateTime, LocalDateTime endDateTime,
                                              LocalTime regularStartTime, LocalTime regularEndTime) {
        long regularHours = 0;

        // Перебирать каждый день между датой начала и окончания.
        for (LocalDate date = startDateTime.toLocalDate(); date.isBefore(endDateTime.toLocalDate());
             date = date.plusDays(1)) {

            LocalDateTime startOfDay = LocalDateTime.of(date, regularStartTime);
            LocalDateTime endOfDay = LocalDateTime.of(date, regularEndTime);


            if (date.equals(startDateTime.toLocalDate())) {
                startOfDay = startDateTime;
            }

            if (date.equals(endDateTime.toLocalDate())) {
                endOfDay = endDateTime;
            }

            regularHours += Duration.between(startOfDay, endOfDay).toHours();
        }

        return regularHours;
    }
}

