package oncall.constants;

import java.util.List;

public class Holidays {

    List<int[]> holidays = List.of(new int[] {1, 1}, new int[] {3, 1}, new int[] {5, 5}, new int[] {6, 6},
            new int[] {8, 15}, new int[] {10, 3}, new int[] {10, 9}, new int[] {12, 25});

    public boolean isHoliday(int month, int day) {
        for (int[] holiday : holidays) {
            int holidayMonth = holiday[0];
            int holidayDay = holiday[1];
            if (holidayMonth == month && holidayDay == day) {
                return true;
            }
        }
        return false;
    }
}
