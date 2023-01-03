package frontend;

import backend.*;

import java.io.IOException;
import java.security.PrivilegedAction;
import java.time.LocalDate;
import java.util.ArrayList;


/**
 * The AppModel class holds and manages
 * the data of the application.
 *
 * @author Zuzanna Popławska
 */

public class AppModel {

    private Calculator calculator = new Calculator();

    private AllUsers allUsers = new AllUsers();
    public AllUsers getAllUsers() {
        return allUsers;
    }

    private LocalDate localTodayDate;
    private LocalDate localYesterdayDate ;
    private LocalDate localWeekAgoDate ;
    private Date todayDate;
    private Date yesterdayDate;
    private Date weekAgoDate;
    public Date getTodayDate() {
        return todayDate;
    }
    public Date getYesterdayDate() {
        return yesterdayDate;
    }
    public Date getWeekAgoDate(){return weekAgoDate;}

    public AppModel() {
        localTodayDate = LocalDate.now();
        todayDate = new Date(localTodayDate.getDayOfMonth(), localTodayDate.getMonthValue(), localTodayDate.getYear());
        localYesterdayDate = localTodayDate.minusDays(1);
        yesterdayDate = new Date(localYesterdayDate.getDayOfMonth(), localYesterdayDate.getMonthValue(), localYesterdayDate.getYear());
        localWeekAgoDate = localTodayDate.minusDays(6);
        weekAgoDate = new Date(localWeekAgoDate.getDayOfMonth(),localWeekAgoDate.getMonthValue(), localWeekAgoDate.getYear());
    }

    private User currentUser;
    public User getCurrentUser() {
        return currentUser;
    }
    public void setCurrentUser(User anyUser) {
        this.currentUser = anyUser;
    }

    private ArrayList<Measurement> currentDataSet = new ArrayList<>();
    public ArrayList<Measurement> getCurrentDataSet() {
        return currentDataSet;
    }
    public void setCurrentDataSet(Date dateFrom, Date dateTo, User currentUser) {
        this.currentDataSet = calculator.getDataFromGivenPeriod(dateFrom, dateTo, currentUser.getMeasurementsFromFileTxt().getListOfMeasurements());
    }


    private double average = 0;
    private double deviation = 0;
    private int timesHipo = 0;
    private int timesHiper= 0;
    public double getAverage() {
        return average;
    }
    public double getDeviation() {
        return deviation;
    }
    public int getTimesHipo() {
        return timesHipo;
    }
    public int getTimesHiper() {
        return timesHiper;
    }

    public void setLabelsInfo(){
        this.average = calculator.calculateAverage(this.currentDataSet);
        this.deviation = calculator.calculateDeviation(this.currentDataSet);
        this.calculator.countHipoAndHiper(this.currentDataSet);
        this.timesHiper= calculator.getCounterHiper();
        this.timesHipo = calculator.getCounterHipo();
    }

    private String sugarUnit = "mg/dL";
    public String getSugarUnit() {
        return sugarUnit;
    }
    public void setSugarUnit(String sugarUnit) {
        this.sugarUnit = sugarUnit;
    }


}
