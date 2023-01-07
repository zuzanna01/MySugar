package backend;

import java.time.LocalDate;

public class Date implements Comparable<Date> {
    private int day;
    private int month;
    private int year;

    public Date(int day, int month, int year){
        this.day = day;
        this.month= month;
        this.year = year;
    }

    public Date (LocalDate localDate){
        this.day = localDate.getDayOfMonth();
        this.month =localDate.getMonthValue();
        this.year=localDate.getYear();
    }

    private LocalDate localDateFormat;
    public LocalDate toLocalDate(){
        localDateFormat = LocalDate.of(this.getYear(),this.getMonth(),this.getDay());
        return localDateFormat;
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

    @Override
    public int compareTo(Date date){
        if(this.year > date.getYear())
            return 1;
        if(this.year < date.getYear())
            return -1;
        if(this.month > date.getMonth())
            return 1;
        if(this.month < date.getMonth())
            return -1;
        if(this.day > date.getDay())
            return 1;
        if(this.day < date.getDay())
            return -1;
        return 0;
    }
}
