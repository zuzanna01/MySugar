package backend;

import java.time.LocalDate;
import java.util.ArrayList;

public class Calculator {
    final double CONSTANT_FIRST = 46.7;
    final double CONSTANT_SECOND = 28.7;
    private double average;
    private double glycatedHemoglobin;
    private double deviation;
    private int counterHipo;
    private int counterHiper;
    private ArrayList<Measurement> listOfMeasurements = new ArrayList<>();

    public double getDeviation() {
        return deviation;
    }

    public void setDeviation(double deviation) {
        this.deviation = deviation;
    }

    public int getCounterHipo() {
        return counterHipo;
    }

    public int getCounterHiper() {
        return counterHiper;
    }

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
    public double calculateAverage(ArrayList<Measurement> listOfMeasurements) {
        int sum = 0;
        for (Measurement i : listOfMeasurements) {
            sum += i.getSugarLevel();
        }
        this.average = sum / listOfMeasurements.size();
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

    // szacownanie hemoglobiny glikowanej z max 30 dni TUTAJ MUSISZ DAĆ LISTĘ Z NIEWYCIĘTEGO ZAKRESU
    public double calculateGlycatedHemoglobin(ArrayList<Measurement> listOfMeasurements) {
        this.listOfMeasurements = listOfMeasurements;
        double average = 0;

        average = calculateAverage(getDataFromPeriod(30, listOfMeasurements));

        this.glycatedHemoglobin = (average * CONSTANT_FIRST) / CONSTANT_SECOND;

        return Math.round(this.glycatedHemoglobin * 100) / 100; // zwraca zaokrągloną wartość do 2 miejsc po przecinku
    }

    public double calculateDeviation(ArrayList<Measurement> listOfMeasurements) {
        this.listOfMeasurements = listOfMeasurements;
        double average = 0;
        double sum = 0;
        average = calculateAverage(listOfMeasurements);
        for (Measurement i : listOfMeasurements) {
            sum += Math.pow((i.getSugarLevel() - average), 2);
        }
        this.deviation = Math.sqrt(sum);
        this.deviation /= listOfMeasurements.size();
        return Math.round(this.deviation * 100) / 100; // zwraca zaokrągloną wartość do 2 miejsc po przecinku
    }


    // wyznaczanie
    public LocalDate calculateDate(int numberOfDays) {
        LocalDate dateNow = LocalDate.now();
        LocalDate dateFrom = dateNow.minusDays(numberOfDays);
        return dateFrom;
    }

    // wycinanie danych dla x dni od dzisiaj
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

    // wyciannie danych dla dowolnego zakresu
    public ArrayList<Measurement> getDataFromGivenPeriod(Date date1, Date date2, ArrayList<Measurement> listOfMeasurements) {
        LocalDate localStartDate = date1.toLocalDate();
        LocalDate localEndDate = date2.toLocalDate();
        ArrayList<Measurement> listOfMeasurementsFromXDays = new ArrayList<>();

        LocalDate measurementDate;

        for (Measurement i : listOfMeasurements) {
            measurementDate = i.getDate().toLocalDate();
            if ((measurementDate.isAfter(localStartDate) && measurementDate.isBefore(localEndDate)) || measurementDate.equals(localEndDate) || measurementDate.equals(localStartDate)) {
                listOfMeasurementsFromXDays.add(i);
            }
        }

        return listOfMeasurementsFromXDays;
    }

    public void countHipoAndHiper(ArrayList<Measurement> listOfMeasurements) {

        this.counterHiper = 0;
        this.counterHipo = 0;

        for (Measurement i : listOfMeasurements) {

            if (i.isHipoglycemia()) {
                this.counterHipo++;
            }
            if (i.isHiperglycemia()) {
                this.counterHiper++;
            }
        }
    }
}
