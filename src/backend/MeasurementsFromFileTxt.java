package backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.NoSuchElementException;
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

    // pobiera jeden pomiar z pliku
    // to jest używane w innej funkcji i samodzielnie tego nie będziemy używać
    public Measurement getMeasurementFromFile(Scanner reader){

            while (reader.hasNextLine()) {
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
        return measurement;
    }

    // o tutaj jest użyta ta funkcja do pobierania jednego pomiaru
    // ta metoda to pobranie wszystkich pomiarów z pliku
    public void getMeasurements(String fileName) {
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


    // zapisuje pojedyńczy pomiar do listy pomiarów
    public void saveMeasurements(Measurement measurement){
        this.listOfMeasurements.add(measurement);
    }
    // Z.P zapisuje pomiar do listy i pliku konkretnego użytkownika
    public void saveMeasurements(Measurement measurement, User currentUser){
        this.listOfMeasurements.add(measurement);

        try{
            File file = new File("./"+currentUser.getUserName()+".txt");
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write("\n" + measurement);
            fileWriter.close();
        }catch(Exception e){
        }

        //tu można by dać jakieś sortownaie
    }

    // to poprawia dane bo np. 02 to nie int więc zamienia na 2
    public String correctData(String data){
        if(data.startsWith("0")){
            return data.replaceFirst("0", "");
        }
        return data;
    }
}

