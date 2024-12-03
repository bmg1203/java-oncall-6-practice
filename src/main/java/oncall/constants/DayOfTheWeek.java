package oncall.constants;

import java.time.DayOfWeek;

public enum DayOfTheWeek {

    MONDAY("월", DayOfWeek.MONDAY.getValue()),
    TUESDAY("화", DayOfWeek.TUESDAY.getValue()),
    WEDNESDAY("수", DayOfWeek.WEDNESDAY.getValue()),
    THURSDAY("목", DayOfWeek.THURSDAY.getValue()),
    FRIDAY("금", DayOfWeek.FRIDAY.getValue()),
    SATURDAY("토", DayOfWeek.SATURDAY.getValue()),
    SUNDAY("일", DayOfWeek.SUNDAY.getValue());

    private final String dayOfTheWeek;
    private final int dayNumber;

    DayOfTheWeek(String dayOfTheWeek, int dayNumber) {
        this.dayOfTheWeek = dayOfTheWeek;
        this.dayNumber = dayNumber;
    }

    public String getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    public int getDayNumber() {
        return dayNumber;
    }
}
