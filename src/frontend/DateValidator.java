package frontend;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateValidator {
    private DateTimeFormatter dateFormatter;

    private LocalDate todayDate ;

    public DateValidator(DateTimeFormatter dateFormatter) {
        this.dateFormatter = dateFormatter;
        todayDate = LocalDate.now();
    }

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



