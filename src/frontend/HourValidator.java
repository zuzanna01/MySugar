package frontend;

import backend.Date;
import backend.Time;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * The HourValidator class is used to
 * checks if inputted hour is valid
 * for 24-hours time format
 *
 * @author Zuzanna Popławska
 */
public class HourValidator {
    LocalDate lDate;
    LocalDate todayDate;
    LocalTime nowTime;

    /**
     * Checks if hour is valid
     * Hour is valid if :
     * 1)hour is between 0-23 2) minutes are between 0-59
     * 3)if there is a current date then the measurement time cannot
     * be later than the current time
     * It doesn't accept 24:00. Use 00:00.
     *
     * @param time time of measurement
     * @param date date of measurement
     * @return true if hour is valid and false otherwise.
     */
    public boolean isHourValid(Time time, Date date) {
        lDate = date.toLocalDate();
        todayDate = LocalDate.now();
        nowTime = LocalTime.now();

        if (time.getHour() > 23 || time.getHour() < 0 || time.getMinute() > 59 || time.getMinute() < 0)
            return false;

        if (lDate.equals(todayDate)) {
            if (time.getHour() <= nowTime.getHour() && time.getHour() <= nowTime.getMinute()) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }
}
