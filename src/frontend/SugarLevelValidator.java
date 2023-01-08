package frontend;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SugarLevelValidator {
    Pattern pattern = Pattern.compile("^[1-9][0-9]*$");
    Matcher matcher;

    public boolean isSugarLevelValid(String sugarLevel) {
        matcher = pattern.matcher(sugarLevel);
        return matcher.find();
    }
}
