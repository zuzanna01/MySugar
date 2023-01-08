package backend;

/**
 * This class represnts Time.
 * @author Zuzanna Krupska
 */
public class Time implements Comparable<Time>{
    private int hour;
    private int minute;
    private String hour_str;
    private String minute_str;

    /**
     * constructor
     * @param hour
     * @param minute
     */
    public Time(int hour, int minute){
        this.hour = hour;
        this.minute = minute;
    }

    /**
     * constructor
     * @param time
     */
    public Time(String time){
        String[] splittime = time.split(":") ;
        this.hour = Integer.parseInt(splittime[0]);
        this.minute = Integer.parseInt(splittime[1]);
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    /**
     * This method overrides toString method. It allows to change time to String format
     * @return time in String format
     */
    @Override
    public String toString() {
        hour_str = String.valueOf(hour);
        if(hour_str.length()==1)hour_str="0"+hour_str;
        minute_str= String.valueOf(minute);
        if(minute_str.length()==1)minute_str="0"+minute_str;
        return hour_str + ":" + minute_str;
    }

    /**
     * This method overrides compareTo method. It allows to compare two Time objects.
     * @param       time the object to be compared.
     * @return      0 if objects are equal
     *              1 if given object is smaller
     *             -1 if given object is bigger
     */
    @Override
    public int compareTo(Time time){
        if(this.hour > time.getHour())
            return 1;
        if(this.hour < time.getHour())
            return -1;
        if(this.minute > time.getMinute())
            return 1;
        if(this.minute < time.getMinute())
            return -1;
        return 0;
    }
}
