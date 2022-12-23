package backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MeasurementsFromFileTxt implements ReadMeasurements {
    private Measurement measurement;
    private ArrayList<Measurement> listOfMeasurements;
    public MeasurementsFromFileTxt(){
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

            while (reader.hasNextLine()) {
                int suggarLevel = reader.nextInt();

                String Time = reader.next();
                String[] tab1 = Time.split(":");
                String hour = correctData(tab1[0]);
                String minute = correctData(tab1[1]);

                String Date = reader.next();
                String[] tab2 = Date.split("-");
                String year = correctData(tab2[0]);
                String month = correctData(tab2[1]);
                String day = correctData(tab2[2]);

                Measurement measurement = new Measurement(suggarLevel, Integer.parseInt(day), Integer.parseInt(month),
                        Integer.parseInt(year), Integer.parseInt(hour), Integer.parseInt(minute));
                return measurement;
            }
        return measurement;
    }


    @Override
    public void getMeasurements() {
        System.out.println("podaj nazwę pliku .txt");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.next();
        try {
            Scanner reader = new Scanner(new File(answer));
            while(reader.hasNextLine()) {
                Measurement measurement = getMeasurementFromFile(reader);
                saveMeasurements(measurement);
            }
        }catch (FileNotFoundException e) {
            System.out.println("nie ma takiego pliku");
        }
    }

    @Override
    public void saveMeasurements(Measurement measurement){
        this.listOfMeasurements.add(measurement);
    }
    public String correctData(String data){
        if(data.startsWith("0")){
            return data.replaceFirst("0", "");
        }
        return data;
    }
}

