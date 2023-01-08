package frontend;

import javax.swing.text.MaskFormatter;
import java.text.ParseException;
/**
 * The DateMask class extends MaskFormatter
 * Is used in program to format date-input fields
 *
 * @author Zuzanna Popławska
 */

class DateMask extends MaskFormatter {

    /**
     * Class constructor.
     * sets placeholder as 00-00-0000
     * only numbers input
     */
    public DateMask() {

        try {
            setMask("##-##-####");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        setPlaceholder("00-00-0000");
        setOverwriteMode(true);
    }

}