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
    private Stack<String> tempWeekSave = new Stack<>();
    private Stack<String> tempHolidaySave = new Stack<>();
    private static final Holidays holidays = new Holidays();

    public WorkOrder(DateInfo dateInfo, Employees employees) {
        this.workOrder = calculateWorkOrder(dateInfo, employees);
    }

    public Deque<String> getWorkOrder() {
        return workOrder;
    }

    private Deque<String> calculateWorkOrder(DateInfo dateInfo, Employees employees) {
        int startDay = dateInfo.getDayOfTheWeek();
        Deque<String> workOrder = new ArrayDeque<>();

        for (int i = 1, day = startDay; i <= dateInfo.getLastDay(); i++, day++) {
            if (day > SUNDAY)
                day = day % SUNDAY;

            if (day == SATURDAY || day == SUNDAY || holidays.isHoliday(dateInfo.getMonth(), i))
                addHolidayWorker(workOrder, employees.getHolidayWorker());

            if (day != SATURDAY && day != SUNDAY && !holidays.isHoliday(dateInfo.getMonth(), i))
                addWeekWorker(workOrder, employees.getWeekWorker());
        }
        return workOrder;
    }

    private void addHolidayWorker(Deque<String> workOrder, List<String> holidayWorker) {
        String curWorker = holidayWorker.get(holidayIndex);
        if (!workOrder.isEmpty()) {
            String lastName = workOrder.peekLast();
            if (lastName.equals(curWorker)) {
                holidayDuplicateProcess(workOrder, holidayWorker, curWorker);
                return;
            }
        }
        if (!tempHolidaySave.isEmpty()) {
            holidayStackProcess(workOrder, holidayWorker);
            return;
        }
        holidayNormalProcess(workOrder, holidayWorker, curWorker);
    }

    private void holidayNormalProcess(Deque<String> workOrder, List<String> holidayWorker, String curWorker) {
        workOrder.add(curWorker);
        holidayIndexAdder(holidayWorker);
    }

    private void holidayStackProcess(Deque<String> workOrder, List<String> holidayWorker) {
        holidayIndexAdder(holidayWorker);
        workOrder.add(tempHolidaySave.pop());
    }

    private void holidayDuplicateProcess(Deque<String> workOrder, List<String> holidayWorker, String curWorker) {
        tempHolidaySave.add(curWorker);
        holidayIndexAdder(holidayWorker);
        workOrder.add(holidayWorker.get(holidayIndex));
    }

    private void holidayIndexAdder(List<String> holidayWorker) {
        holidayIndex++;
        if (holidayIndex >= holidayWorker.size()) {
            holidayIndex = holidayIndex % holidayWorker.size();
        }
    }

    private void addWeekWorker(Deque<String> workOrder, List<String> weekWorker) {
        String curWorker = weekWorker.get(weekIndex);
        if (!workOrder.isEmpty()) {
            String lastName = workOrder.peekLast();
            if (lastName.equals(curWorker)) {
                weekDuplicateProcess(workOrder, weekWorker, curWorker);
                return;
            }
        }
        if (!tempWeekSave.isEmpty()) {
            weekStackProcess(workOrder, weekWorker);
            return;
        }
        weekNormalProcess(workOrder, weekWorker, curWorker);
    }

    private void weekNormalProcess(Deque<String> workOrder, List<String> weekWorker, String curWorker) {
        workOrder.add(curWorker);
        weekIndexAdder(weekWorker);
    }

    private void weekStackProcess(Deque<String> workOrder, List<String> weekWorker) {
        weekIndexAdder(weekWorker);
        workOrder.add(tempWeekSave.pop());
    }

    private void weekDuplicateProcess(Deque<String> workOrder, List<String> weekWorker, String curWorker) {
        tempWeekSave.add(curWorker);
        weekIndexAdder(weekWorker);
        workOrder.add(weekWorker.get(holidayIndex));
    }

    private void weekIndexAdder(List<String> weekWorker) {
        weekIndex++;
        if (weekIndex >= weekWorker.size()) {
            weekIndex = weekIndex % weekWorker.size();
        }
    }
}
