package frontend;

import backend.Date;
import backend.Time;

import java.time.LocalDate;
import java.time.LocalTime;

public class HourValidator {
    LocalDate lDate;
    LocalDate todayDate;
    LocalTime nowTime;

    public boolean isHourValid(Time time, Date date) {
        lDate = date.toLocalDate();
        todayDate = LocalDate.now();
        nowTime = LocalTime.now();

        if(time.getHour()>23||time.getHour()<0||time.getMinute()>59||time.getMinute()<0)
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
