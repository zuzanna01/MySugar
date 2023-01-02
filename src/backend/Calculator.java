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
    public double calculateAverage(ArrayList<Measurement> listOfMeasurements, int numberOfDays) {
        ArrayList<Measurement> listOfMeasurementsFromXDays = new ArrayList<>();

        LocalDate dateNow = LocalDate.now();
        LocalDate dateFrom = dateNow.minusDays(numberOfDays);

        for (Measurement i : listOfMeasurements) {
            if (i.getDate().getYear() >= dateFrom.getYear() && i.getDate().getMonth() >= dateFrom.getMonthValue() && i.getDate().getDay() >= dateFrom.getDayOfMonth()) {
                listOfMeasurementsFromXDays.add(i);
            }
        }
        int sum = 0;
        for (Measurement i : listOfMeasurementsFromXDays) {
            sum += i.getSugarLevel();
        }
        this.average = sum / listOfMeasurementsFromXDays.size();
        return this.average;
    }

    // obliczanie średniej z dnia
    public double calculateAverageFromToday(ArrayList<Measurement> listOfMeasurements) {
        ArrayList<Measurement> listOfMeasurementsFromToday = new ArrayList<>();
        for (Measurement i : listOfMeasurements) {
            LocalDate dateNow = LocalDate.now();

            if (i.getDate().getDay() == dateNow.getDayOfMonth() && i.getDate().getMonth() == dateNow.getMonthValue() && i.getDate().getYear() == dateNow.getYear()) {
                listOfMeasurementsFromToday.add(i);
            }
        }
        int sum = 0;
        for (Measurement i : listOfMeasurementsFromToday) {
            sum += i.getSugarLevel();
        }
        this.average = sum / listOfMeasurementsFromToday.size();
        return this.average;
    }

    public double calculateAverage(ArrayList<Measurement> listOfMeasurements) {
        int sum = 0;
        for (Measurement i : listOfMeasurements) {
            sum += i.getSugarLevel();
        }
        this.average = sum / listOfMeasurements.size();
        return this.average;
    }

    // szacownanie hemoglobiny glikowanej ze wszystkich pomiarów
    public double calculateGlycatedHemoglobin(ArrayList<Measurement> listOfMeasurements) {
        this.listOfMeasurements = listOfMeasurements;
        this.glycatedHemoglobin = (this.average * CONSTANT_FIRST) / CONSTANT_SECOND;
        return this.glycatedHemoglobin; // TO DO --> to trzeba jeszcze zaokrąglić
    }

    // wyznaczanie
    public LocalDate calculateDate(int numberOfDays) {
        LocalDate dateNow = LocalDate.now();
        LocalDate dateFrom = dateNow.minusDays(numberOfDays);
        return dateFrom;
    }

    // wycinanie danych dla dowolnego zakresu
    public ArrayList<Measurement> getDataFromPeriod(int numberOfDays, ArrayList<Measurement> listOfMeasurements) {
        LocalDate date = calculateDate(numberOfDays);
        ArrayList<Measurement> listOfMeasurementsFromXDays = new ArrayList<>();
        for (Measurement i : listOfMeasurements) {
            if (i.getDate().getYear() >= date.getYear() && i.getDate().getMonth() >= date.getMonthValue() && i.getDate().getDay() >= date.getDayOfMonth()) {
                listOfMeasurementsFromXDays.add(i);
            }
        }
        return listOfMeasurementsFromXDays;
    }

    //Z.P
    public ArrayList<Measurement> getDataFromGivenPeriod(Date date1, Date date2, ArrayList<Measurement> listOfMeasurements) {

        ArrayList<Measurement> listOfMeasurementsFromXDays = new ArrayList<>();
        LocalDate localStartDate = date1.toLocalDate();
        LocalDate localEndDate = date2.toLocalDate();

        LocalDate measurementDate;

        for (Measurement i : listOfMeasurements) {
            measurementDate = i.getDate().toLocalDate();
            if ((measurementDate.isAfter(localStartDate) && measurementDate.isBefore(localEndDate))||measurementDate.equals(localEndDate)||measurementDate.equals(localStartDate)) {
                listOfMeasurementsFromXDays.add(i);
            }
        }

        return listOfMeasurementsFromXDays;
    }
}
