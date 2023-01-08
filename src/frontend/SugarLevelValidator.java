package frontend;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The SugarLevelValidator class checks
 * if inputted sugar level is valid
 *
 * @author Zuzanna Popławska
 */

public class SugarLevelValidator {
    Pattern pattern = Pattern.compile("^[1-9][0-9]*$");
    Matcher matcher;

    /**
     * Checks if inputted sugar level is valid.
     * Is valid if starts witch number from 1-9
     * and have after just numbers
     *
     * @param sugarLevel string from Sugar Level field from JAddingNewMeasurement
     * @return true if sugar level is valid and false otherwise.
     *
     */
    public boolean isSugarLevelValid(String sugarLevel) {
        matcher = pattern.matcher(sugarLevel);
        return matcher.find();
    }
}
