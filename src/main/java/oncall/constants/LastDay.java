package oncall.constants;

import java.util.List;

public class LastDay {

    private static final int LAST_DAY_THIRTY_ONE = 31;
    private static final int LAST_DAY_THIRTY = 30;
    private static final int LAST_DAY_TWENTY_EIGHT = 28;
    private final List<Integer> thirty = List.of(4, 6, 9, 11);
    private final List<Integer> twentyEight = List.of(2);

    public int calculateMonth(int month) {
        if (twentyEight.contains(month))
            return LAST_DAY_TWENTY_EIGHT;
        if (thirty.contains(month))
            return LAST_DAY_THIRTY;
        return LAST_DAY_THIRTY_ONE;
    }
}
