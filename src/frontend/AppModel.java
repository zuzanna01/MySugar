package frontend;

import backend.*;

import java.io.IOException;
import java.util.ArrayList;


/**
 * The AppModel class holds and manages
 * the data of the application.
 *
 * @author Zuzanna Popławska
 */

public class AppModel {
    private Calculator calculator = new Calculator();

    private AllUsers UsersList = new AllUsers();
    public AllUsers getUsersList() {
        return UsersList;
    }

    public AppModel() {
        UsersList.getUsersFromFile();
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
    public void setCurrentDataSet(ArrayList<Measurement> currentDataSet) {
        this.currentDataSet = currentDataSet;
    }

    public boolean authenticate(String username, String password) {
        return this.UsersList.logIn(username, password);
    }
    public void addNewUserToFile(User newUser) {
        try {
            UsersList.signIn(newUser.getLogin(), newUser.getPassword(), newUser.getTypeOfDiabities(),
                    newUser.getUpperTargetRage(), newUser.getLowerTargetRage(),
                    newUser.getHipoglycemia(), newUser.getHiperglycemia());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

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


}
