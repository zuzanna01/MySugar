package frontend;

import javax.swing.text.MaskFormatter;
import java.text.ParseException;

/**
 * The HourMask class extends MaskFormatter
 * Is used in program to format time-input fields
 *
 * @author Zuzanna Popławska
 * @see MaskFormatter
 */

public class HourMask extends MaskFormatter {
    /**
     * Class constructor.
     * sets placeholder as 00:00
     * only numbers input
     */
    public HourMask() {
        try {
            setMask("##:##");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        setPlaceholder("00:00");
        setOverwriteMode(true);
    }
}
