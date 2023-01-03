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

        String[] tab1 = data.split(",");
        String sugarLevel = tab1[0];
        String time = tab1[1];
        String date = tab1[2];

        String[] tab2 = time.split(":");
        String hour = correctData(tab2[0]);
        String minute = correctData(tab2[1]);


        String[] tab3 = date.split("-");
        String day = correctData(tab3[0]);
        String month = correctData(tab3[1]);
        String year = correctData(tab3[2]);

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
