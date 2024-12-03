package oncall.domain;

import java.util.ArrayList;
import java.util.List;
import oncall.constants.DayOfTheWeek;
import oncall.constants.ErrorMessage;
import oncall.constants.LastDay;

public class DateInfo {

    private static final int JANUARY = 1;
    private static final int DECEMBER = 12;
    private final int month;
    private final int lastDay;
    private final int dayOfTheWeek;

    public DateInfo(int month, String dayOfTheWeekInput) {
        this.month = validateMonth(month);
        this.lastDay = calculateLastDay(month);
        validateDayOfTheWeek(dayOfTheWeekInput);
        this.dayOfTheWeek = calculateDayOfTheWeek(dayOfTheWeekInput);
    }

    public int getMonth() {
        return month;
    }

    public int getLastDay() {
        return lastDay;
    }

    public int getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    private int validateMonth(int month) {
        if (month < JANUARY || month > DECEMBER) {
            throw new IllegalArgumentException(ErrorMessage.INPUT_ERROR.getMessage());
        }
        return month;
    }

    private int calculateLastDay(int month) {
        LastDay calculate = new LastDay();
        return calculate.calculateMonth(month);
    }

    private void validateDayOfTheWeek(String dayOfTheWeekInput) {
        List<String> days = getDays();
        if (!days.contains(dayOfTheWeekInput)) {
            throw new IllegalArgumentException(ErrorMessage.INPUT_ERROR.getMessage());
        }
    }

    private List<String> getDays() {
        List<String> days = new ArrayList<>();
        for (DayOfTheWeek day : DayOfTheWeek.values()) {
            days.add(day.getDayOfTheWeek());
        }
        return days;
    }

    private int calculateDayOfTheWeek(String dayOfTheWeekInput) {
        for (DayOfTheWeek calculate : DayOfTheWeek.values()) {
            if (calculate.getDayOfTheWeek().equals(dayOfTheWeekInput)) {
                return calculate.getDayNumber();
            }
        }
        return 10;
    }
}
