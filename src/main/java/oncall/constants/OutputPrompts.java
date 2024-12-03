package oncall.constants;

public enum OutputPrompts {

    DAY_AND_EMPLOYEE_OUTPUT("%d월 %d일 %s %s\n"),
    DAY_AND_EMPLOYEE_HOLIDAY_OUTPUT("%d월 %d일 %s(휴일) %s\n");

    private final String prompt;

    OutputPrompts(String prompt) {
        this.prompt = prompt;
    }

    public String getPrompt() {
        return prompt;
    }
}
