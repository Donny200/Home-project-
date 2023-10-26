package ProblemDescription;

import java.time.Duration;
import java.time.LocalTime;

public class TimeTrackingProgram {
    public static void main(String[] args) {
        // Время начала работы сотрудника для каждого рабочего дня
        LocalTime[] startTimes = {
                LocalTime.of(8, 0),   // Monday
                LocalTime.of(8, 0),   // Tuesday
                LocalTime.of(8, 0),   // Wednesday
                LocalTime.of(8, 0),   // Thursday
                LocalTime.of(8, 0)    // Friday
        };

        //
        //Время окончания сотрудника для каждого рабочего дня
        LocalTime[] endTimes = {
                LocalTime.of(17, 0),  // Monday
                LocalTime.of(17, 0),  // Tuesday
                LocalTime.of(17, 0),  // Wednesday
                LocalTime.of(17, 0),  // Thursday
                LocalTime.of(18, 30)  // Friday
        };

        // Предопределенный недельный порог для обычных часов
        int weeklyThreshold = 40;

        Duration totalRegularHours = Duration.ZERO;
        Duration totalOvertimeHours = Duration.ZERO;

        for (int i = 0; i < startTimes.length; i++) {
            Duration workHours = Duration.between(startTimes[i], endTimes[i]);
            if (i != startTimes.length - 1) {
                totalRegularHours = totalRegularHours.plus(workHours);
            } else {
                Duration regularWorkHours = Duration.ofHours(weeklyThreshold).minus(totalRegularHours);
                if (workHours.compareTo(regularWorkHours) <= 0) {
                    totalRegularHours = totalRegularHours.plus(workHours);
                } else {
                    totalRegularHours = totalRegularHours.plus(regularWorkHours);
                    totalOvertimeHours = workHours.minus(regularWorkHours);
                }
            }
        }

        System.out.println("Всего обычных часов: " + totalRegularHours.toHours() + " часов");
        System.out.println("Сверхурочные часы: " + totalOvertimeHours.toHours() + " часов");
    }
}

