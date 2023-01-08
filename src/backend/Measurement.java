package backend;

/**
 * This class represents measurement which contains Date, Time, Sugar Level, hipoglycemia and hiperglycemia.
 * @author Zuzanna Krupska
 */
public class Measurement implements Comparable<Measurement> {
    private int sugarLevel;
    private Date date;
    private Time time;
    private boolean hipoglycemia = false;
    private boolean hiperglycemia = false;

    /**
     * constructor
     * @param sugarLevel
     * @param day
     * @param month
     * @param year
     * @param hour
     * @param minute
     */
    public Measurement(int sugarLevel, int day, int month, int year, int hour, int minute){
        this.time = new Time(hour, minute);
        this.date = new Date(day, month, year);
        this.sugarLevel = sugarLevel;
    }

    /**
     * constructor
     * @param sugarLevel
     * @param time
     * @param date
     */
    public Measurement(int sugarLevel, Time time, Date date){
        this.time = time;
        this.date = date;
        this.sugarLevel = sugarLevel;
    }

    /**
     * constructor
     * @param m
     */
    public Measurement(Measurement m){
        this.time = m.getTime();
        this.date = m.getDate();
        this.sugarLevel= m.getSugarLevel();
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Time getTime() {
        return time;
    }

    public int getSugarLevel() {
        return sugarLevel;
    }

    public void setSugarLevel(int sugarLevel) {
        this.sugarLevel = sugarLevel;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isHiperglycemia() {
        return hiperglycemia;
    }

    public void setHiperglycemia(boolean hiperglycemia) {
        this.hiperglycemia = hiperglycemia;
    }

    public boolean isHipoglycemia() {
        return hipoglycemia;
    }

    public void setHipoglycemia(boolean hipoglycemia) {
        this.hipoglycemia = hipoglycemia;
    }

    /**
     * This method overrides toString method. It allows to change measurement to String format
     * @return measurement in String format
     */
    @Override
    public String toString() {
        return  sugarLevel + " " + time + " " + date ;
    }

    /**
     * This method overrides compareTo method. It allows to compare two Measurement objects.
     * @param measurement the object to be compared.
     * @return            0 if objects are equal
     *                    1 if given object is smaller
     *                   -1 if given object is bigger
     */
    @Override
    public int compareTo(Measurement measurement){
        int compared = this.getDate().compareTo(measurement.getDate());
        if(compared == 0)
            return this.getTime().compareTo(measurement.getTime());
        else return compared;
    }
}
