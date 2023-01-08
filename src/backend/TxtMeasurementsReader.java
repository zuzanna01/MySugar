package backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This class contains list of measurements red from the .txt file
 * @author Zuzanna Krupska
 */
public class TxtMeasurementsReader implements MeasurementsReader {
    private ArrayList<Measurement> listOfMeasurements;
    private String fileName;
    private User currentUser;

    /**
     * constructor
     * @param fileName
     * @param currentUser
     */
    public TxtMeasurementsReader(String fileName, User currentUser){
        this.listOfMeasurements = new ArrayList<>();
        this.fileName = fileName;
        this.currentUser = currentUser;
    }

    /**
     * constructor
     */
    public TxtMeasurementsReader() {
        this.listOfMeasurements = new ArrayList<>();
        this.fileName = null;
        this.currentUser = null;
    }

    public void setListOfMeasurements(ArrayList<Measurement> listOfMeasurements) {
        this.listOfMeasurements = listOfMeasurements;
    }

    public ArrayList<Measurement> getListOfMeasurements() {
        return listOfMeasurements;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    private Measurement getMeasurementFromFile(Scanner reader) {

        int suggarLevel = reader.nextInt();

        String Time = reader.next();
        String[] tab1 = Time.split(":");
        String hour = correctData(tab1[0]);
        String minute = correctData(tab1[1]);

        String Date = reader.next();
        String[] tab2 = Date.split("-");
        String day = correctData(tab2[0]);
        String month = correctData(tab2[1]);
        String year = correctData(tab2[2]);

        Measurement measurement = new Measurement(suggarLevel, Integer.parseInt(day), Integer.parseInt(month),
                Integer.parseInt(year), Integer.parseInt(hour), Integer.parseInt(minute));

        return measurement;
    }

    /**
     * This method reads measurements from file and checks wheter red measurements is hipo- or hiperglycemia.
     */
    public void readMeasurements() {
       try {
            Scanner reader = new Scanner(new File(fileName));
            while(reader.hasNextLine()) {
                Measurement measurement = getMeasurementFromFile(reader);
                saveMeasurement(measurement);
            }
        }catch (FileNotFoundException e) {
        }catch (NoSuchElementException e){
       }
       currentUser.checkHipoAndHiper(this.listOfMeasurements);
    }

    /**
     * This method reads measurements from file and saves them to user's file and users' list of measurements.
     */
    @Override
    public void saveNewMeasurements(){
        readMeasurements();
        currentUser.saveMeasurementsToUsersFile(this.listOfMeasurements);
        currentUser.getListOfUsersMeasurements().addAll(this.listOfMeasurements);
    }

    private void saveMeasurement(Measurement measurement){
        this.listOfMeasurements.add(measurement);
    }

    private String correctData(String data){
        if(data.startsWith("0")){
            return data.replaceFirst("0", "");
        }
        return data;
    }
}

