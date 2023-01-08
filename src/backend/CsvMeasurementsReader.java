package backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CsvMeasurementsReader implements MeasurementsReader {
    private ArrayList<Measurement> listOfMeasurements;
    private Measurement measurement;
    private String fileName;
    private User currentUser;

    public String getFileName() {
        return fileName;
    }

    public CsvMeasurementsReader(String fileName, User currentUser) {
        this.listOfMeasurements = new ArrayList<>();
        this.fileName = fileName;
        this.currentUser = currentUser;
    }

    public void setMeasurement(Measurement measurement) {
        this.measurement = measurement;
    }

    public void setListOfMeasurements(ArrayList<Measurement> listOfMeasurements) {
        this.listOfMeasurements = listOfMeasurements;
    }

    public Measurement getMeasurement() {
        return measurement;
    }

    public ArrayList<Measurement> getListOfMeasurements() {
        return listOfMeasurements;
    }

    public Measurement getMeasurementFromFile(Scanner reader) {
        String data = reader.next();
        String[] tab = data.split(",");
        String sugarLevel = tab[0];
        String hour = tab[1];
        String minute = tab[2];
        String day = tab[3];
        String month = tab[4];
        String year = tab[5];

        Measurement measurement = new Measurement(Integer.parseInt(sugarLevel), Integer.parseInt(day), Integer.parseInt(month),
                Integer.parseInt(year), Integer.parseInt(hour), Integer.parseInt(minute));

        return measurement;
    }

    public String correctData(String data) {
        if (data.startsWith("0")) {
            return data.replaceFirst("0", "");
        }
        return data;
    }

    public void saveMeasurements(Measurement measurement){
        this.listOfMeasurements.add(measurement);
    }

    @Override
    public void saveNewMeasurements(){
        getMeasurements();
        currentUser.saveMeasurementsToUsersFile(this.listOfMeasurements);
    }

    public void getMeasurements(){
        try {
            Scanner reader = new Scanner(new File(fileName));
            while(reader.hasNextLine()) {
                Measurement measurement = getMeasurementFromFile(reader);
                saveMeasurements(measurement);
            }
        }catch (FileNotFoundException e) {
        }catch (NoSuchElementException e){
        }
    }
}
