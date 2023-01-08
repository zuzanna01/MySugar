package frontend;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The DateValidator class checks if inputted hour is valid
 *
 * @author Zuzanna Popławska
 */
// Modified code from:
// https://www.baeldung.com/java-string-valid-date

public class DateValidator {
    private DateTimeFormatter dateFormatter;
    private LocalDate todayDate ;

    /**
     * Class constructor.
     * set's date  format that will be used in tested date string
     * check's today date
     * @param dateFormatter date format
     */
    public DateValidator(DateTimeFormatter dateFormatter) {
        this.dateFormatter = dateFormatter;
        todayDate = LocalDate.now();
    }

    /**
     * Checks if date is valid
     * Date is valid if :
     * 1) date exists in calendar 2) is not in future
     *
     * @param dateStr string of date in right format dd-mm-yyyy
     * @return true if date is valid and false otherwise.
     * @exception  DateTimeParseException
     */
    public boolean isValid(String dateStr) {

        try {
            LocalDate.parse(dateStr, this.dateFormatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        if ( LocalDate.parse(dateStr, this.dateFormatter).isAfter(todayDate))
            return false;

        return true;
    }
}



