package oncall.domain;

import java.util.ArrayList;
import java.util.List;
import oncall.constants.ErrorMessage;

public class Employees {

    private static final int NAME_LENGTH = 5;
    private static final int MIN_NUMBER = 5;
    private static final int MAX_NUMBER = 35;
    private final List<String> weekWorker;
    private final List<String> holidayWorker;

    public Employees(List<String> weekWorker, List<String> holidayWorker) {
        validateWeekWorker(weekWorker);
        validateHolidayWorker(holidayWorker);
        this.weekWorker = weekWorker;
        this.holidayWorker = holidayWorker;
    }

    public List<String> getWeekWorker() {
        return weekWorker;
    }

    public List<String> getHolidayWorker() {
        return holidayWorker;
    }

    private void validateWeekWorker(List<String> weekWorker) {
        validateName(weekWorker);
        validateDuplicate(weekWorker);
        validateNumber(weekWorker);
    }

    private void validateHolidayWorker(List<String> holidayWorker) {
        validateName(holidayWorker);
        validateDuplicate(holidayWorker);
        validateNumber(holidayWorker);
    }

    private void validateName(List<String> worker) {
        for (String name : worker) {
            if (name.length() > NAME_LENGTH || name.isEmpty()) {
                throw new IllegalArgumentException(ErrorMessage.INPUT_ERROR.getMessage());
            }
        }
    }

    private void validateDuplicate(List<String> worker) {
        List<String> names = new ArrayList<>();
        for (String name : worker) {
            if (names.contains(name)) {
                throw new IllegalArgumentException(ErrorMessage.INPUT_ERROR.getMessage());
            }
            names.add(name);
        }
    }

    private void validateNumber(List<String> worker) {
        if (worker.size() < MIN_NUMBER || worker.size() > MAX_NUMBER) {
            throw new IllegalArgumentException(ErrorMessage.INPUT_ERROR.getMessage());
        }
    }
}
