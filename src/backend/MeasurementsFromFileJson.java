package backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MeasurementsFromFileJson implements MeasurementsReader {
    private Measurement measurement;
    private ArrayList<Measurement> listOfMeasurements;
    private String fileName;

    public MeasurementsFromFileJson(String fileName){
        this.fileName = fileName;
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

    @Override
    public void getMeasurements() {
        try {
            Scanner reader = new Scanner(new File(this.fileName));
            do {
                Measurement measurement = getMeasurementFromFile(reader);
                saveMeasurements(measurement);
            }while(reader.hasNextLine());
        }catch (FileNotFoundException e) {
        }
    }


    public void saveMeasurements(Measurement measuremet) {
        this.listOfMeasurements.add(measurement);
    }

    public String correctData(String data) {
        if(data.startsWith("0")){
            return data.replaceFirst("0", "");
        }
        return data;
    }
}
