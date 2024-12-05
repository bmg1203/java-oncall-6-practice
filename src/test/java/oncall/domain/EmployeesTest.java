package oncall.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import oncall.constants.ErrorMessage;
import org.junit.jupiter.api.Test;

class EmployeesTest {

    @Test
    void 근무자_저장_테스트() {
        //given
        List<String> week = List.of("허브", "쥬니", "말랑", "라온", "헤나", "우코", "에단");
        List<String> holiday = List.of("오션", "로이스", "애쉬", "푸만능", "우가", "아이크", "첵스");

        //when
        Employees employees = new Employees(week, holiday);

        //then
        assertThat(employees.getWeekWorker()).isEqualTo(week);
        assertThat(employees.getHolidayWorker()).isEqualTo(holiday);
    }

    @Test
    void 이름_길이_예외_테스트() {
        //given
        List<String> week = List.of("허브브브브브", "쥬니", "말랑", "라온", "헤나", "우코", "에단");
        List<String> holiday = List.of("오션", "로이스", "애쉬", "푸만능", "우가", "아이크", "첵스");

        //when, then
        assertThatThrownBy(() -> new Employees(week, holiday))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INPUT_ERROR.getMessage());
    }

    @Test
    void 인원_길이_5이하_예외_테스트() {
        //given
        List<String> week = List.of("허브", "쥬니", "말랑", "라온");
        List<String> holiday = List.of("오션", "로이스", "애쉬", "푸만능", "우가", "아이크", "첵스");

        //when, then
        assertThatThrownBy(() -> new Employees(week, holiday))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INPUT_ERROR.getMessage());
    }

    @Test
    void 인원_길이_35이상_예외_테스트() {
        //given
        List<String> week = List.of("허브", "쥬니", "말랑", "라온", "헤나", "우코", "에단",
                "에이", "비", "씨", "디", "이", "에프", "지",
                "에이치", "아이", "제이", "케이", "엘", "엠", "엔",
                "오", "피", "큐", "알", "에스", "티", "유",
                "브이", "더블유", "엑스", "와이", "제트", "가", "나",
                "다", "라", "마");
        List<String> holiday = List.of("오션", "로이스", "애쉬", "푸만능", "우가", "아이크", "첵스");

        //when, then
        assertThatThrownBy(() -> new Employees(week, holiday))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INPUT_ERROR.getMessage());
    }

    @Test
    void 이름_중복_예외_테스트() {
        //given
        List<String> week = List.of("허브", "쥬니", "말랑", "라온", "헤나", "우코", "에단");
        List<String> holiday = List.of("오션", "로이스", "애쉬", "푸만능", "우가", "아이크", "첵스", "우가");

        //when, then
        assertThatThrownBy(() -> new Employees(week, holiday))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INPUT_ERROR.getMessage());
    }
}