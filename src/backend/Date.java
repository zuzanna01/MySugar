package backend;

import java.time.LocalDate;

/**
 * This class represents date.
 */
public class Date implements Comparable<Date> {
    private int day;
    private int month;
    private int year;
    private String day_str;
    private String  month_str;
    private LocalDate localDateFormat;

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

    /**
     * constructor
     * @param day
     * @param month
     * @param year
     */
    public Date(int day, int month, int year){
        this.day = day;
        this.month= month;
        this.year = year;
    }

    /**
     * constructor
     * @param localDate date in localDate format
     */
    public Date (LocalDate localDate){
        this.day = localDate.getDayOfMonth();
        this.month =localDate.getMonthValue();
        this.year=localDate.getYear();
    }

    /**
     * constructor
     * @param date  date in String format
     */
    public Date(String date){
        String[] splitdate = date.split("-") ;
        this.day = Integer.parseInt(splitdate[0]);
        this.month = Integer.parseInt(splitdate[1]);
        this.year = Integer.parseInt(splitdate[2]);
    }

    /**
     * This method changes date to localDate format
     * @return  date in localDate format
     */
    public LocalDate toLocalDate(){
        localDateFormat = LocalDate.of(this.getYear(),this.getMonth(),this.getDay());
        return localDateFormat;
    }

    /**
     * This method overrides toString method. It allows to change date to String format
     * @return date in String format
     */
    @Override
    public String toString() {
        day_str= String.valueOf(day);
        if(day_str.length()==1)day_str="0"+day_str;
        month_str= String.valueOf(month);
        if(month_str.length()==1)month_str="0"+month_str;

        return day_str + "-" + month_str + "-" + year;
    }

    /**
     * This method overrides compareTo method. It allows to compare two Date objects.
     * @param date the object to be compared.
     * @return     0 if objects are equal
     *             1 if given object is smaller
     *             -1 if given object is bigger
     */
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
