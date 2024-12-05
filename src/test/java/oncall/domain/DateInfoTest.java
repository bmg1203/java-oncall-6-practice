package oncall.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DateInfoTest {

    @Test
    void 월_저장_테스트() {
        //given
        int month = 1;
        String dayOfTheWeek = "일";

        //when
        DateInfo dateInfo = new DateInfo(month, dayOfTheWeek);

        //then
        assertThat(dateInfo.getMonth()).isEqualTo(1);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, 7, 8, 10, 12})
    void 달의_마지막날이_31일인_월_테스트(int month) {
        //given
        String dayOfTheWeek = "월";

        //when
        DateInfo dateInfo = new DateInfo(month, dayOfTheWeek);

        //then
        assertThat(dateInfo.getLastDay()).isEqualTo(31);
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 6, 9, 11})
    void 달의_마지막날이_30일인_월_테스트(int month) {
        //given
        String dayOfTheWeek = "월";

        //when
        DateInfo dateInfo = new DateInfo(month, dayOfTheWeek);

        //then
        assertThat(dateInfo.getLastDay()).isEqualTo(30);
    }

    @ParameterizedTest
    @ValueSource(ints = {2})
    void 달의_마지막날이_28일인_월_테스트(int month) {
        //given
        String dayOfTheWeek = "월";

        //when
        DateInfo dateInfo = new DateInfo(month, dayOfTheWeek);

        //then
        assertThat(dateInfo.getLastDay()).isEqualTo(28);
    }

    @Test
    void 월요일() {
        //given
        int month = 1;
        String dayOfTheWeek = "월";

        //when
        DateInfo dateInfo = new DateInfo(month, dayOfTheWeek);

        //then
        assertThat(dateInfo.getDayOfTheWeek()).isEqualTo(1);
    }

    @Test
    void 화요일() {
        //given
        int month = 1;
        String dayOfTheWeek = "화";

        //when
        DateInfo dateInfo = new DateInfo(month, dayOfTheWeek);

        //then
        assertThat(dateInfo.getDayOfTheWeek()).isEqualTo(2);
    }

    @Test
    void 수요일() {
        //given
        int month = 1;
        String dayOfTheWeek = "수";

        //when
        DateInfo dateInfo = new DateInfo(month, dayOfTheWeek);

        //then
        assertThat(dateInfo.getDayOfTheWeek()).isEqualTo(3);
    }

    @Test
    void 목요일() {
        //given
        int month = 1;
        String dayOfTheWeek = "목";

        //when
        DateInfo dateInfo = new DateInfo(month, dayOfTheWeek);

        //then
        assertThat(dateInfo.getDayOfTheWeek()).isEqualTo(4);
    }

    @Test
    void 금요일() {
        //given
        int month = 1;
        String dayOfTheWeek = "금";

        //when
        DateInfo dateInfo = new DateInfo(month, dayOfTheWeek);

        //then
        assertThat(dateInfo.getDayOfTheWeek()).isEqualTo(5);
    }

    @Test
    void 토요일() {
        //given
        int month = 1;
        String dayOfTheWeek = "토";

        //when
        DateInfo dateInfo = new DateInfo(month, dayOfTheWeek);

        //then
        assertThat(dateInfo.getDayOfTheWeek()).isEqualTo(6);
    }

    @Test
    void 일요일() {
        //given
        int month = 1;
        String dayOfTheWeek = "일";

        //when
        DateInfo dateInfo = new DateInfo(month, dayOfTheWeek);

        //then
        assertThat(dateInfo.getDayOfTheWeek()).isEqualTo(7);
    }
}