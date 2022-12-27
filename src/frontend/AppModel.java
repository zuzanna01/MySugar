package frontend;

import backend.AllUsers;
import backend.Measurement;
import backend.MeasurementsFromFileTxt;
import backend.User;

import java.util.ArrayList;

/**
 * The AppModel class holds and manages
 * the data of the application.
 *
 * @author Zuzanna Popławska
 */

public class AppModel {

    private AllUsers UsersList = new AllUsers();
    public AllUsers getUsersList() {
        return UsersList;
    }

    private MeasurementsFromFileTxt MeasurementsList = new MeasurementsFromFileTxt();
    public MeasurementsFromFileTxt getMeasurementsTable() {
        return MeasurementsList;
    }

    private ArrayList<Measurement> currentMeasurementSet = new ArrayList<>();

    public User getCurrentUser() {
        return currentUser;
    }

    private User currentUser;

    public void setCurrentUser(User anyUser) {
        this.currentUser = anyUser;
        this.lowerRange = anyUser.getLowerTargetRage();
        this.upperRange = anyUser.getUpperTargetRage();
    }

    private int lowerRange = 0;
    private int upperRange = 0;
    private double average = 0;
    private double deviation = 0;
    private int hipo = 0;
    private int hiper = 0;

    private String sugarUnit = "mg/dL";

    public String getSugarUnit() {
        return sugarUnit;
    }

    public void setSugarUnit(String sugarUnit) {
        this.sugarUnit = sugarUnit;
    }

    public AppModel() {
        this.getUsersList().addUser(new User("Zuzanna1", "haslo1", 1, 130, 70, 2, 2));
        this.getUsersList().addUser(new User("Zuzanna2", "haslo2", 1, 150, 80, 2, 2));
    }

    public double countAverage(String date) {
        if (date.equals("Today")) {
            this.average = 95;
            return this.average;
        } else if (date.equals("Yesterday")) {
            this.average = 100;
            return this.average;
        } else if (date.equals("Last 7 days")) {
            this.average = 130;
            return this.average;
        } else return 0;
    }

    public double countDeviation(String date) {
        if (date.equals("Today")) {
            this.deviation = 10;
        } else if (date.equals("Yesterday")) {
            this.deviation = 12;
        } else if (date.equals("Last 7 days")) {
            this.deviation = 13;
        }
        return this.deviation;
    }

    public int countHipo(String date) {
        if (date.equals("Today")) {
            this.hipo = 0;
        } else if (date.equals("Yesterday")) {
            this.hipo = 1;
        } else if (date.equals("Last 7 days")) {
            this.hipo = 5;
        }
        return this.hipo;
    }

    public int countHiper(String date) {
        if (date.equals("Today")) {
            this.hiper = 0;
        } else if (date.equals("Yesterday")) {
            this.hiper = 1;
        } else if (date.equals("Last 7 days")) {
            this.hiper = 5;
        }
        return this.hiper;
    }

    public static boolean authenticate(String username, String password) {

        if ((username.equals("Zuzanna1") && password.equals("haslo1"))||(username.equals("Zuzanna2") && password.equals("haslo2"))) {
            return true;
        }
        return false;
    }
}
