package frontend;

import backend.*;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * The AppModel class holds and manages
 * the data of the application.
 *
 * @author Zuzanna Popławska
 */

public class AppModel {

    private String sugarUnit = "mg/dL";

    public String getSugarUnit() {
        return sugarUnit;
    }

    private Calculator calculator = new Calculator();
    public Calculator getCalculator() {
        return calculator;
    }
    private AllUsers allUsers = new AllUsers();

    public AllUsers getAllUsers() {
        return allUsers;
    }

    public RaportWriter csvraportWriter;
    public RaportWriter txtraportWriter;

    private LocalDate localTodayDate;
    private LocalDate localYesterdayDate;
    private LocalDate localWeekAgoDate;
    private Date todayDate;
    private Date yesterdayDate;
    private Date weekAgoDate;

    public Date getTodayDate() {
        return todayDate;
    }

    public Date getYesterdayDate() {
        return yesterdayDate;
    }

    public Date getWeekAgoDate() {
        return weekAgoDate;
    }
    /**
     * Class constructor.
     * loads list all users in order to make login action possible
     * checks today date, and counts yesterday date and week ago date and saves them
     */
    public AppModel() {
        allUsers.getUsersFromFile();
        localTodayDate = LocalDate.now();
        todayDate = new Date(localTodayDate.getDayOfMonth(), localTodayDate.getMonthValue(), localTodayDate.getYear());
        localYesterdayDate = localTodayDate.minusDays(1);
        yesterdayDate = new Date(localYesterdayDate.getDayOfMonth(), localYesterdayDate.getMonthValue(), localYesterdayDate.getYear());
        localWeekAgoDate = localTodayDate.minusDays(6);
        weekAgoDate = new Date(localWeekAgoDate.getDayOfMonth(), localWeekAgoDate.getMonthValue(), localWeekAgoDate.getYear());
    }

    private User currentUser;

    public User getCurrentUser() {
        return currentUser;
    }
    /**
     * Creates new current user
     * @param anyUser should be user who successfully logs in
     */
    public void setCurrentUser(User anyUser) {
        this.currentUser = new User(anyUser);
    }

    private Measurement currentMeasurement;
    public Measurement getCurrentMeasurement() {
        return currentMeasurement;
    }
    public void setCurrentMeasurement(Measurement currentMeasurement) {
        this.currentMeasurement = currentMeasurement;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    private String header;


    private ArrayList<Measurement> currentDataSet = new ArrayList<>();

    public ArrayList<Measurement> getCurrentDataSet() {
        return currentDataSet;
    }

    /**
     * Sets current data set according to time period chosen by user
     * @param dateFrom date of the begging of the period
     * @param dateTo date of the end of the period
     */
    public void setCurrentDataSet(Date dateFrom, Date dateTo) {
        this.currentDataSet = calculator.getDataFromGivenPeriod(dateFrom, dateTo, currentUser.getListOfUsersMeasurements());
    }

    private double average = 0;
    private double deviation = 0;
    private int timesHipo = 0;
    private int timesHiper = 0;

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

    /**
     *Counts average, deviation, timesHipo and timesHiper
     *using calculator object from current measurements (data) set
     */
    public void countLabelsInfo() {
        this.average = calculator.calculateAverage(this.currentDataSet);
        this.deviation = calculator.calculateDeviation(this.currentDataSet);
        this.timesHipo = calculator.countHipoglycemia(this.currentDataSet);
        this.timesHiper = this.calculator.countHiperglycemia(this.currentDataSet);
        this.timesHiper = calculator.getCounterHiper();
        this.timesHipo = calculator.getCounterHipo();
    }


    public void setCurrentDataSet(ArrayList<Measurement> listOfUsersMeasurements) {
    }
}
