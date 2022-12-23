package backend;

import java.time.LocalDate;
import java.util.ArrayList;

public class Calculator {

    final double CONSTANT_FIRST = 46.7;
    final double CONSTANT_SECOND = 28.7;
    private double average;
    private double glycatedHemoglobin;

    private int numberOfDays;
    private ArrayList<Measurement> listOfMeasurements = new ArrayList<>();

    public void setAverage(double average) {
        this.average = average;
    }

    public void setGlycatedHemoglobin(double glycatedHemoglobin) {
        this.glycatedHemoglobin = glycatedHemoglobin;
    }

    public double getAverage() {
        return average;
    }

    public double getGlycatedHemoglobin() {
        return glycatedHemoglobin;
    }

    // TO DO --> średnia ma być z dowolnej ilości dni

    // średnia ze wszystkich pomiarów
    public double calculateAverage(ArrayList<Measurement> listOfMeasurements, int numberOfDays){
        ArrayList<Measurement> listOfMeasurementsFromXDays = new ArrayList<>();

        LocalDate dateNow = LocalDate.now();
        LocalDate dateFrom = dateNow.minusDays(numberOfDays);

        for(Measurement i : listOfMeasurements){
            if(i.getDate().getYear() >= dateFrom.getYear() && i.getDate().getMonth()>= dateFrom.getMonthValue() && i.getDate().getDay() >= dateFrom.getDayOfMonth()){
                listOfMeasurementsFromXDays.add(i);
            }
        }

        int sum = 0;
        for( Measurement i: listOfMeasurementsFromXDays){
            sum += i.getSugarLevel();
        }
        this.average = sum / listOfMeasurementsFromXDays.size();
        return this.average;
    }

    public double calculateAverageFromToday(ArrayList<Measurement> listOfMeasurements){
        ArrayList<Measurement> listOfMeasurementsFromToday = new ArrayList<>();
        for(Measurement i : listOfMeasurements){
            LocalDate dateNow = LocalDate.now();

            if(i.getDate().getDay() == dateNow.getDayOfMonth() && i.getDate().getMonth() == dateNow.getMonthValue() && i.getDate().getYear() == dateNow.getYear()){
                listOfMeasurementsFromToday.add(i);
           }
        }
        int sum = 0;
        for( Measurement i: listOfMeasurementsFromToday ){
            sum += i.getSugarLevel();
        }
        this.average = sum / listOfMeasurementsFromToday.size();
        return this.average;
    }

    // hemoglobina glikowana

    public double calculateGlycatedHemoglobin(ArrayList<Measurement> listOfMeasurements){
        this.listOfMeasurements = listOfMeasurements;
        this.glycatedHemoglobin = (this.average * CONSTANT_FIRST) / CONSTANT_SECOND;
        return this.glycatedHemoglobin; // TO DO --> to trzeba jeszcze zaokrąglić
    }

}
