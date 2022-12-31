package backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MeasurementsFromFileJson implements ReadMeasurements {
    private Measurement measurement;
    private ArrayList<Measurement> listOfMeasurements;

    public MeasurementsFromFileJson(){
        this.listOfMeasurements = new ArrayList<>();
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
    public Measurement getMeasurementFromFile(Scanner reader){

        return null;
    }

    public void getMeasurements(String fileName) {
        try {
            Scanner reader = new Scanner(new File(fileName));
            do {
                Measurement measurement = getMeasurementFromFile(reader);
                saveMeasurements(measurement);
            }while(reader.hasNextLine());
        }catch (FileNotFoundException e) {
        }
    }

    @Override
    public void saveMeasurements(Measurement measuremet) {
        this.listOfMeasurements.add(measurement);
    }

    @Override
    public String correctData(String data) {
        if(data.startsWith("0")){
            return data.replaceFirst("0", "");
        }
        return data;
    }
}
