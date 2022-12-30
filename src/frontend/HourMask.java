package frontend;

import javax.swing.text.MaskFormatter;
import java.text.ParseException;

public class HourMask extends MaskFormatter {
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
