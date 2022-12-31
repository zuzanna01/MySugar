package backend;

public class Measurement {
    @Override
    public String toString() {
        return "sugarLevel: " + sugarLevel +
                ", date: " + date +
                ", time: " + time ;
    }

    private int sugarLevel;
    private Date date;

    public void setTime(Time time) {
        this.time = time;
    }

    private Time time;
    private boolean hipoglycemia = false;
    private boolean hiperglycemia = false;

    public Measurement(int sugarLevel, int day, int month, int year, int hour, int minute){
        this.time = new Time(hour, minute);
        this.date = new Date(day, month, year);
        this.sugarLevel = sugarLevel;
    }
    public Measurement(int sugarLevel, Time time, Date date){
        this.time = time;
        this.date = date;
        this.sugarLevel = sugarLevel;
    }

    public Measurement(){
        this.time=new Time(0,0);
        this.date = new Date(0,0,0);
        this.sugarLevel = 0;
    }

    public Time getTime() {
        return time;
    }

    public double getSugarLevel() {
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

    public void checkHipoAndHiperGlycemia(int hipoglycemia, int hiperglicemia){
        if(sugarLevel <= hipoglycemia){
            this.hipoglycemia = true;
        }
        if(sugarLevel >= hiperglicemia){
            this.hiperglycemia = true;
        }
    }
}
