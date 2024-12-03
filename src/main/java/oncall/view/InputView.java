package oncall.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import oncall.constants.InputPrompts;
import oncall.domain.DateInfo;
import oncall.domain.Employees;
import oncall.utils.Parser;
import oncall.utils.Split;

public class InputView {

    public DateInfo dateInfoInput() {
        while(true) {
            try {
                System.out.println(InputPrompts.MONTH_AND_DAY_OF_THE_WEEK_INPUT.getPrompt());
                String input = Console.readLine();
                List<String> inputSplit = Split.commaSplit(input);
                int month = Parser.stringToInt(inputSplit.get(0));
                String dayOfTheWeek = inputSplit.get(1);
                return new DateInfo(month, dayOfTheWeek);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public Employees employeeInput() {
        while(true) {
            try {
                System.out.println(InputPrompts.WEEKDAY_EMPLOYEE_INPUT.getPrompt());
                String weekEmployeesInput = Console.readLine();
                List<String> weekEmployees = Split.commaSplit(weekEmployeesInput);
                System.out.println(InputPrompts.WEEKEND_EMPLOYEE_INPUT.getPrompt());
                String holidayEmployeesInput = Console.readLine();
                List<String> holidayEmployees = Split.commaSplit(holidayEmployeesInput);
                return new Employees(weekEmployees, holidayEmployees);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
