package oncall.utils;

import java.util.List;

public class Split {

    private static final String COMMA = ",";

    public static List<String> commaSplit(String input) {
        input = input.replaceAll(" ", "");
        return List.of(input.split(COMMA));
    }
}
