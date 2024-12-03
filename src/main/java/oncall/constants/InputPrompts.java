package oncall.constants;

public enum InputPrompts {

    MONTH_AND_DAY_OF_THE_WEEK_INPUT("비상 근무를 배정할 월과 시작 요일을 입력하세요> "),
    WEEKDAY_EMPLOYEE_INPUT("평일 비상 근무 순번대로 사원 닉네임을 입력하세요> "),
    WEEKEND_EMPLOYEE_INPUT("휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> ");

    private final String prompt;

    InputPrompts(String prompt) {
        this.prompt = prompt;
    }

    public String getPrompt() {
        return prompt;
    }
}
