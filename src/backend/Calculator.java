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


    // średnia z dowolnej ilości dni
    public double calculateAverage(ArrayList<Measurement> listOfMeasurements){
       /* ArrayList<Measurement> listOfMeasurementsFromXDays = new ArrayList<>();

        LocalDate dateFrom = calculateDate(numberOfDays);

        for(Measurement i : listOfMeasurements){
            if(i.getDate().getYear() >= dateFrom.getYear() && i.getDate().getMonth()>= dateFrom.getMonthValue() && i.getDate().getDay() >= dateFrom.getDayOfMonth()){
                listOfMeasurementsFromXDays.add(i);
            }
        }*/
        int sum = 0;
        for( Measurement i: listOfMeasurements){
            sum += i.getSugarLevel();
        }
        this.average = sum / listOfMeasurements.size();
        return this.average;
    }

    // obliczanie średniej z dnia
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

    // szacownanie hemoglobiny glikowanej z max 30 dni
    public double calculateGlycatedHemoglobin(ArrayList<Measurement> listOfMeasurements){
        this.listOfMeasurements = listOfMeasurements;
        double average = 0;
       // average = calculateAverage(listOfMeasurements, 30);

        this.glycatedHemoglobin = (average * CONSTANT_FIRST) / CONSTANT_SECOND;

        return Math.round(this.glycatedHemoglobin * 100) / 100; // zwraca zaokrągloną wartość do 2 miejsc po przecinku
    }

    // wyznaczanie
    public LocalDate calculateDate (int numberOfDays){
        LocalDate dateNow = LocalDate.now();
        LocalDate dateFrom = dateNow.minusDays(numberOfDays);
        return dateFrom;
    }

    // wycinanie danych dla x dni od dzisiaj
    public ArrayList<Measurement> getDataFromPeriod (int numberOfDays, ArrayList<Measurement> listOfMeasurements){
        LocalDate date = calculateDate(numberOfDays);
        ArrayList<Measurement> listOfMeasurementsFromXDays = new ArrayList<>();
        for(Measurement i : listOfMeasurements){
            if(i.getDate().getYear() >= date.getYear() && i.getDate().getMonth()>= date.getMonthValue() && i.getDate().getDay() >= date.getDayOfMonth()){
                listOfMeasurementsFromXDays.add(i);
            }
        }
        return listOfMeasurementsFromXDays;
    }

    // wyciannie danych dla dowolnego zakresu
    public ArrayList<Measurement> getDataFromGivenPeriod (Date date1, Date date2, ArrayList<Measurement> listOfMeasurements){
        ArrayList<Measurement> listOfMeasurementsFromXDays = new ArrayList<>();
        for(Measurement i : listOfMeasurements){
            if(i.getDate().getYear() == date1.getYear()){
                if(i.getDate().getMonth() == date1.getMonth()){
                    if(i.getDate().getDay() >= date1.getDay()){
                        if(i.getDate().getYear() == date2.getYear()){
                            if(i.getDate().getMonth() == date2.getMonth()) {
                                if (i.getDate().getDay() <= date2.getDay()) {
                                    listOfMeasurementsFromXDays.add(i);
                                }
                            }else if(i.getDate().getMonth() < date2.getMonth()){
                                listOfMeasurementsFromXDays.add(i);
                            }
                        }else if(i.getDate().getYear() < date2.getMonth()){
                            listOfMeasurementsFromXDays.add(i);
                        }
                    }
                }else if(i.getDate().getMonth() > date1.getMonth()){
                    if(i.getDate().getYear() == date2.getYear()){
                        if(i.getDate().getMonth() == date2.getMonth()) {
                            if (i.getDate().getDay() <= date2.getDay()) {
                                listOfMeasurementsFromXDays.add(i);
                            }
                        }else if(i.getDate().getMonth() < date2.getMonth()){
                            listOfMeasurementsFromXDays.add(i);
                        }
                    }else if(i.getDate().getYear() < date2.getMonth()){
                        listOfMeasurementsFromXDays.add(i);
                    }
                }
            }else if(i.getDate().getYear() > date1.getYear()) {
                if(i.getDate().getYear() == date2.getYear()){
                    if(i.getDate().getMonth() == date2.getMonth()){
                        if(i.getDate().getDay() <= date2.getDay()){
                            listOfMeasurementsFromXDays.add(i);
                        }
                    }else if(i.getDate().getMonth() < date2.getMonth()){
                        listOfMeasurementsFromXDays.add(i);
                    }
                }else if(i.getDate().getYear() < date2.getMonth()){
                    listOfMeasurementsFromXDays.add(i);
                }
            }
        }
        return listOfMeasurementsFromXDays;
    }
}
