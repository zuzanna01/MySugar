
    package backend;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

    public class UserMeasurementsReader implements MeasurementsReader {
        private Measurement measurement;
        private ArrayList<Measurement> listOfMeasurements;

        public UserMeasurementsReader(){
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

        // zapytanie użytkownika o pomiar
        // utorzenie obiektu Pomiar z wczytanych danych
        public Measurement askForMeasurements(){
            Scanner scanner = new Scanner(System.in);
            int answer = 0;
            try {
                answer = scanner.nextInt();
            }catch(InputMismatchException e){
            }

            String answer1 = scanner.next();
            String[] tab1 = answer1.split(":");
            String hour = correctData(tab1[0]);
            String minute = correctData(tab1[1]);

            String answer2 = scanner.next();
            String[] tab2 = answer2.split("-");
            String day = correctData(tab2[0]);
            String month = correctData(tab2[1]);
            String year = correctData(tab2[2]);



            Measurement measurement = new Measurement(answer, Integer.parseInt(day), Integer.parseInt(month),
                    Integer.parseInt(year), Integer.parseInt(hour), Integer.parseInt(minute));
            return measurement;
        }


        // główna funkcja wczytująca pomiary od użytkownika
        // metoda wyżej jest tutaj użyta

        @Override
        public void getMeasurements(){
            ArrayList<Measurement> listOfMeasurements = new ArrayList<>();
            boolean end = false;
                Measurement measurement = askForMeasurements();
                saveMeasurements(measurement);
        }

        // zapis pojedyńczego pomiaru do listy pomiarów
        public void saveMeasurements(Measurement measurement) {
            this.listOfMeasurements.add(measurement);
        }

        // tak samo jak z .txt trzeba poprawić jeśli przed liczbą jest 0, np. 09 to 9
        public String correctData(String data){
            if(data.startsWith("0")){
                return data.replaceFirst("0", "");
            }
            return data;
        }

    }


