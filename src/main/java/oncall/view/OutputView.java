package oncall.view;

import java.time.DayOfWeek;
import java.util.Deque;
import oncall.constants.DayOfTheWeek;
import oncall.constants.Holidays;
import oncall.constants.OutputPrompts;
import oncall.domain.DateInfo;
import oncall.domain.WorkOrder;

public class OutputView {

    private static final int SUNDAY = DayOfWeek.SUNDAY.getValue();

    public void workOrderOutput(WorkOrder workOrder, DateInfo dateInfo) {
        int startDay = dateInfo.getDayOfTheWeek();
        Holidays holidays = new Holidays();
        Deque<String> workers = workOrder.getWorkOrder();

        for (int i = 1, day = startDay; i <= dateInfo.getLastDay(); i++, day++) {
            if (day > SUNDAY) {
                day = day % SUNDAY;
            }

            String curWorker = workers.remove();
            String dayOfTheWeek = getDayOfTheWeek(day);
            //법정 공휴일
            if (holidays.isHoliday(dateInfo.getMonth(), i)) {
                System.out.printf(OutputPrompts.DAY_AND_EMPLOYEE_HOLIDAY_OUTPUT.getPrompt(), dateInfo.getMonth(), i, dayOfTheWeek, curWorker);
            }
            if (!holidays.isHoliday(dateInfo.getMonth(), i)) {
                System.out.printf(OutputPrompts.DAY_AND_EMPLOYEE_OUTPUT.getPrompt(), dateInfo.getMonth(), i, dayOfTheWeek, curWorker);
            }
        }
    }

    private String getDayOfTheWeek(int day) {
        for (DayOfTheWeek dayOfTheWeek : DayOfTheWeek.values()) {
            if (dayOfTheWeek.getDayNumber() == day) {
                return dayOfTheWeek.getDayOfTheWeek();
            }
        }
        return "";
    }
}
