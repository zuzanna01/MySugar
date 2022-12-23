package frontend;

import java.sql.*;

public class AppModel {
    private double average =0;
    private double deviation=0;
    private int hipo=0;
    private int hiper=0;

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

    public double countDeviation(String date){
        if (date.equals("Today")){
            this.deviation =10;
        }
        else if(date.equals("Yesterday")){
            this.deviation=12;
        }
        else if(date.equals("Last 7 days")){
            this.deviation=13;
        }
        return this.deviation;
    }

    public int countHipo(String date){
        if (date.equals("Today")){
            this.hipo =0;
        }
        else if(date.equals("Yesterday")){
            this.hipo=1;
        }
        else if(date.equals("Last 7 days")){
            this.hipo=5;
        }
        return this.hipo;
    }

    public int countHiper(String date){
        if (date.equals("Today")){
            this.hiper =0;
        }
        else if(date.equals("Yesterday")){
            this.hiper=1;
        }
        else if(date.equals("Last 7 days")){
            this.hiper=5;
        }
        return this.hiper;
    }
}
