package frontend;

import javax.swing.text.MaskFormatter;
import java.text.ParseException;

class DateMask extends MaskFormatter {

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