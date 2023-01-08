package backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class TxtMeasurementsReader implements MeasurementsReader {
    private ArrayList<Measurement> listOfMeasurements;
    private String fileName;
    private User currentUser;

    public TxtMeasurementsReader(String fileName, User currentUser){
        this.listOfMeasurements = new ArrayList<>();
        this.fileName = fileName;
        this.currentUser = currentUser;
    }

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

    public Measurement getMeasurementFromFile(Scanner reader) {

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

    public void getMeasurements() {
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

    @Override
    public void saveNewMeasurements(){
        getMeasurements();
        currentUser.saveMeasurementsToUsersFile(this.listOfMeasurements);
        currentUser.getListOfUsersMeasurements().addAll(this.listOfMeasurements);
    }

    public void saveMeasurement(Measurement measurement){
        this.listOfMeasurements.add(measurement);
    }

    public String correctData(String data){
        if(data.startsWith("0")){
            return data.replaceFirst("0", "");
        }
        return data;
    }
}

