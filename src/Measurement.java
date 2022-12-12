public class Measurement {
    private double sugarLevel;
    private String date;


    private String time;

    public Measurement(double sugarLevel, String date, String time) {
        this.sugarLevel = sugarLevel;
        this.date = date;
        this.time = time;
    }


    public double getSugarLevel() {
        return sugarLevel;
    }

    public void setSugarLevel(double sugarLevel) {
        this.sugarLevel = sugarLevel;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
