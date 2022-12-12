
public class AppModel {
    private double average;
    private double derivation;
    private int timeshipo;
    private int timeshyper;


    public double getDayAverage() {
        return this.average;
    }

    public double countAverage(String date) {
        if (date.equals("Today")){
            this.average= 95;
            return this.average;
        }
        else if(date.equals("Yesterday")){
            this.average=100;
            return this.average;
        }
        else if(date.equals("Last 7 days")){
            this.average=130;
            return this.average;
        }
        else return 0;
    }
}
