package backend;

public class Time {
    private int hour;

    @Override
    public String toString() {
        return hour + ":" + minute;
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
