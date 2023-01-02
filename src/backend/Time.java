package backend;

public class Time {
    private int hour;

    private String hour_str;
    private String minute_str;
    @Override
    public String toString() {
        hour_str = String.valueOf(hour);
        if(hour_str.length()==1)hour_str="0"+hour_str;
        minute_str= String.valueOf(minute);
        if(minute_str.length()==1)minute_str="0"+minute_str;
        return hour_str + ":" + minute_str;
    }

    private int minute;

    public Time(int hour, int minute){
        this.hour = hour;
        this.minute = minute;
    }

    public Time(String time){
        String[] splittime = time.split(":") ;
        this.hour = Integer.parseInt(splittime[0]);
        this.minute = Integer.parseInt(splittime[1]);
    }

    public void setHour(int hour) {
        this.hour = hour;
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
}
