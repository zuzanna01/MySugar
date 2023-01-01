package backend;

public class Date {
    private int day;
    private int month;
    private int year;

    public Date(int day, int month, int year){
        this.day = day;
        this.month= month;
        this.year = year;
    }


    private String day_str;
    private String  month_str;
    @Override
    public String toString() {
        day_str= String.valueOf(day);
        if(day_str.length()==1)day_str="0"+day_str;
        month_str= String.valueOf(month);
        if(month_str.length()==1)month_str="0"+month_str;

        return day_str + "-" + month_str + "-" + year;
    }

    public Date(String date){
      String[] splitdate = date.split("-") ;
      this.day = Integer.parseInt(splitdate[0]);
      this.month = Integer.parseInt(splitdate[1]);
      this.year = Integer.parseInt(splitdate[2]);
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
