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

    @Override
    public String toString() {
        return "day: " + day + ", month: " + month + ", year: " + year;
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
