package oncall.domain;

import java.time.DayOfWeek;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Stack;
import oncall.constants.Holidays;

public class WorkOrder {

    private static final int SATURDAY = DayOfWeek.SATURDAY.getValue();
    private static final int SUNDAY = DayOfWeek.SUNDAY.getValue();
    private final Deque<String> workOrder;
    private int weekIndex = 0;
    private int holidayIndex = 0;

    public WorkOrder(DateInfo dateInfo, Employees employees) {
        this.workOrder = calculateWorkOrder(dateInfo, employees);
    }

    public Deque<String> getWorkOrder() {
        return workOrder;
    }

    private Deque<String> calculateWorkOrder(DateInfo dateInfo, Employees employees) {
        int startDay = dateInfo.getDayOfTheWeek();
        Holidays holidays = new Holidays();
        Deque<String> workOrder = new ArrayDeque<>();
        Stack<String> week = new Stack<>();
        Stack<String> holiday = new Stack<>();
        for (int i = 1, day = startDay; i <= dateInfo.getLastDay(); i++, day++) {
            if (day > SUNDAY) {
                day = day % SUNDAY;
            }

            //휴일
            if (day == SATURDAY || day == SUNDAY || holidays.isHoliday(dateInfo.getMonth(), i)) {
                addHolidayWorker(workOrder, holiday, employees.getHolidayWorker());
            }
            //평일
            if (day != SATURDAY && day != SUNDAY && !holidays.isHoliday(dateInfo.getMonth(), i)) {
                addWeekWorker(workOrder, week, employees.getWeekWorker());
            }
        }
        return workOrder;
    }

    private void addHolidayWorker(Deque<String> workOrder, Stack<String> holiday, List<String> holidayWorker) {
        String curWorker = holidayWorker.get(holidayIndex);
        if (!workOrder.isEmpty()) {
            String lastName = workOrder.peekLast();
            if (lastName.equals(curWorker)) {
                holiday.add(curWorker);
                holidayIndexAdder(holidayWorker);
                workOrder.add(holidayWorker.get(holidayIndex));
                return;
            }
        }

        if (!holiday.isEmpty()) {
            holidayIndexAdder(holidayWorker);
            workOrder.add(holiday.pop());
            return;
        }

        workOrder.add(curWorker);
        holidayIndexAdder(holidayWorker);
    }

    private void holidayIndexAdder(List<String> holidayWorker) {
        holidayIndex++;
        if (holidayIndex >= holidayWorker.size()) {
            holidayIndex = holidayIndex % holidayWorker.size();
        }
    }

    private void addWeekWorker(Deque<String> workOrder, Stack<String> week, List<String> weekWorker) {
        String curWorker = weekWorker.get(weekIndex);
        if (!workOrder.isEmpty()) {
            String lastName = workOrder.peekLast();
            if (lastName.equals(curWorker)) {
                week.add(curWorker);
                weekIndexAdder(weekWorker);
                workOrder.add(weekWorker.get(holidayIndex));
                return;
            }
        }

        if (!week.isEmpty()) {
            weekIndexAdder(weekWorker);
            workOrder.add(week.pop());
            return;
        }

        workOrder.add(curWorker);
        weekIndexAdder(weekWorker);
    }

    private void weekIndexAdder(List<String> weekWorker) {
        weekIndex++;
        if (weekIndex >= weekWorker.size()) {
            weekIndex = weekIndex % weekWorker.size();
        }
    }
}
