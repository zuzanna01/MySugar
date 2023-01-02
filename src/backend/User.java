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
    private int hipoglycemiaCounter;
    private int hiperglycemiaCounter;

    private TxtMeasuremetsReader measurementsFromFileTxt;

    public TxtMeasuremetsReader getMeasurementsFromFileTxt() {
        return measurementsFromFileTxt;
    }

    public User(String userName, String password, int typeOfDiabities, int upperTargetRage, int lowerTargetRage, int hipoglycemia, int hiperglycemia) {
        this.userName = userName;
        this.password = password;
        this.typeOfDiabities = typeOfDiabities;
        this.upperTargetRage = upperTargetRage;
        this.lowerTargetRage = lowerTargetRage;
        this.hipoglycemia = hipoglycemia;
        this.hiperglycemia = hiperglycemia;
        this.measurementsFromFileTxt = new TxtMeasuremetsReader();
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

    //pobieranie zapisanych wcześniej pomiarów dla użytkownika z jego pliku
    public void  getDataFromUsersFile(){
        this.measurementsFromFileTxt.getMeasurements(this.userName + ".txt");
    }

    // zliczanie hipoglikemi i hiperglikemi dla pacjenta ze wszystkich jego pomiarów,
    // jako argument trzeba podać listę pomiarów użytkownika
    // można zliczyć z konkretengo zakredu używając wcześniej calculator getDataFromPeriod
    public void countHipoAndHiper(ArrayList<Measurement> listOfMeasurements) {

        for (Measurement i : listOfMeasurements) {
            i.checkHipoAndHiperGlycemia(this.hipoglycemia, this.hiperglycemia);
            if (i.isHipoglycemia()) {
                this.hipoglycemiaCounter++;
            }
            if (i.isHiperglycemia()) {
                this.hiperglycemiaCounter++;
            }
        }
    }

    public void saveMeasurementsToUsersFile(ArrayList<Measurement> listOfNewMeasurements){
        try{
            File file = new File(this.userName + ".txt");
            FileWriter fileWriter = new FileWriter(file, true);
            for(Measurement i: listOfNewMeasurements){
                fileWriter.write( i.getSugarLevel() + " " + i.getTime().getHour() + ":" + i.getTime().getMinute() + " " + i.getDate().getYear() + "-" + i.getDate().getMonth() + "-" + i.getDate().getDay());
            }
            fileWriter.close();
        }catch(Exception e){
        }
    }
}
