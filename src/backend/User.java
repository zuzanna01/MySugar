package backend;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class User {
    private String userName;
    private String password;
    private int typeOfDiabities;
    private int upperTargetRage;
    private int lowerTargetRage;
    private int hipoglycemia;
    private int hiperglycemia;
    private ArrayList<Measurement> listOfUsersMeasurements;

    public ArrayList<Measurement> getListOfUsersMeasurements() {
        return listOfUsersMeasurements;
    }

    public void setListOfUsersMeasurements(ArrayList<Measurement> listOfUsersMeasurements) {
        this.listOfUsersMeasurements = listOfUsersMeasurements;
    }

    public User(String userName, String password, int typeOfDiabities, int upperTargetRage, int lowerTargetRage, int hipoglycemia, int hiperglycemia) {
        this.userName = userName;
        this.password = password;
        this.typeOfDiabities = typeOfDiabities;
        this.upperTargetRage = upperTargetRage;
        this.lowerTargetRage = lowerTargetRage;
        this.hipoglycemia = hipoglycemia;
        this.hiperglycemia = hiperglycemia;
        this.listOfUsersMeasurements = new ArrayList<Measurement>();
    }

    public User(User u){
        this.userName = u.getUserName();
        this.password = u.getPassword();
        this.typeOfDiabities = u.getTypeOfDiabities();
        this.upperTargetRage = u.getUpperTargetRage();
        this.lowerTargetRage = u.getLowerTargetRage();
        this.hipoglycemia = u.getHipoglycemia();
        this.hiperglycemia = u.getHiperglycemia();
        this.listOfUsersMeasurements = u.getListOfUsersMeasurements();
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTypeOfDiabities(int typeOfDiabities) {
        this.typeOfDiabities = typeOfDiabities;
    }

    public void setUpperTargetRage(int upperTargetRage) {
        this.upperTargetRage = upperTargetRage;
    }

    public void setLowerTargetRage(int lowerTargetRage) {
        this.lowerTargetRage = lowerTargetRage;
    }

    public void setHipoglycemia(int hipoglycemia) {
        this.hipoglycemia = hipoglycemia;
    }

    public void setHiperglycemia(int hiperglycemia) {
        this.hiperglycemia = hiperglycemia;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public int getTypeOfDiabities() {
        return typeOfDiabities;
    }

    public int getUpperTargetRage() {
        return upperTargetRage;
    }

    public int getLowerTargetRage() {
        return lowerTargetRage;
    }

    public int getHipoglycemia() {
        return hipoglycemia;
    }

    public int getHiperglycemia() {
        return hiperglycemia;
    }

    // sprawdzenie czy hasło jest poprawne
    public boolean checkPassword(String password){
        if(this.password.equals(password))
        {
            return true;
        }
        return false;
    }

    // zapisanie danych użytkownika do bazy użytkowników
    public void saveUser(){
        try{
            File file = new File("./Users.txt");
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write("\n" + this.userName + " " + this.password + " " + this.typeOfDiabities + " " + this.upperTargetRage + " " + this.lowerTargetRage + " " + this.hipoglycemia + " " + this.hiperglycemia);
            fileWriter.close();
        }catch(Exception e){
        }
    }

    public void saveMeasurementsToUsersFile(ArrayList<Measurement> listOfNewMeasurements){
        try{
            File file = new File(this.userName + ".txt");
            FileWriter fileWriter = new FileWriter(file, true);
            String hour;
            String minute;
            String day;
            String month;
            for(Measurement i: listOfNewMeasurements){
                if(i.getTime().getHour() < 10) {
                    hour = "0" + i.getTime().getHour();
                }else{
                    hour = String.valueOf(i.getTime().getHour());
                }
                if(i.getTime().getMinute() < 10) {
                    minute = "0" + i.getTime().getMinute();
                }else{
                    minute = String.valueOf(i.getTime().getMinute());
                }
                if(i.getDate().getDay() < 10) {
                    day = "0" + i.getDate().getDay();
                }else{
                    day = String.valueOf(i.getDate().getDay());
                }
                if(i.getDate().getMonth() < 10) {
                    month = "0" + i.getDate().getMonth();
                }
                else{
                    month = String.valueOf(i.getDate().getMonth());
                }
                if(file.length() != 0){
                    fileWriter.write('\n');
                }
                fileWriter.write(  i.getSugarLevel() + " " + hour + ":" + minute + " " + day + "-" + month + "-" + i.getDate().getYear());
            }
            fileWriter.close();
        }catch(Exception e){
        }
    }

    public void checkHipoAndHiper() {

        for (Measurement i : this.getListOfUsersMeasurements()) {

            if (i.isHipoglycemia()) {
                i.setHipoglycemia(true);
            }
            if (i.isHiperglycemia()) {
                i.setHipoglycemia(true);
            }
        }
    }
}
