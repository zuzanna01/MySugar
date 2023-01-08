package backend;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This class allows to calculate data.
 * @author Zuzanna Krupska
 */
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

    /**
     * This method simply calculates average sugar level.
     * @param listOfMeasurements    list of measurements from which the average should be calculated
     * @return                      calculated average sugar level
     */
    public double calculateAverage(ArrayList<Measurement> listOfMeasurements) {
        int sum = 0;
        for (Measurement i : listOfMeasurements) {
            sum += i.getSugarLevel();
        }
        this.average = sum / listOfMeasurements.size();
        return this.average;
    }

    /**
     * This method simply calculates glycated hemoglobin from last thirty days.
     * @param listOfMeasurements    list of measurements from which the glycated hemoglobin should be calculated
     * @return                      calculated glycated hemoglobin rounded up to two decimal places
     */
    public double calculateGlycatedHemoglobin(ArrayList<Measurement> listOfMeasurements) {
        this.listOfMeasurements = listOfMeasurements;
        double average = 0;
        LocalDate today = LocalDate.now();
        LocalDate thirtyDaysBeforeToday = today.minusDays(30);
        Date dateToday = new Date(today);
        Date dateThirtyDaysBeforeToday = new Date(thirtyDaysBeforeToday);

        average = calculateAverage(getDataFromGivenPeriod(dateThirtyDaysBeforeToday, dateToday, listOfMeasurements));

        this.glycatedHemoglobin = (average + CONSTANT_FIRST) / CONSTANT_SECOND;
        this.glycatedHemoglobin *=100;
        this.glycatedHemoglobin = Math.round(this.glycatedHemoglobin);
        this.glycatedHemoglobin /= 100;
        return this.glycatedHemoglobin;
    }

    /**
     * This method simply calculates the sugar level deviation.
     * @param listOfMeasurements    list of measurements from which the deviation should be calculated
     * @return                      calculated deviation rounded up to two decimal places
     */
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

    /**
     * This method cuts out measurements from list of measurements that are from given period.
     * @param date1                 begining of the period date
     * @param date2                 end of the period date
     * @param listOfMeasurements    list of measurements from which measurements should be cut
     * @return                      list of measurements from given period
     */
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

    /**
     * This method counts hypoglycemia.
     * @param listOfMeasurements    list of measurements from which hypoglycemia should be counted.
     * @return                      number of hypoglycemia
     */
    public int countHipoglycemia(ArrayList<Measurement> listOfMeasurements) {
        this.counterHipo = 0;
        for (Measurement i : listOfMeasurements) {
            if (i.isHipoglycemia()) {
                this.counterHipo++;
            }
        }
        return counterHipo;
    }

    /**
     * This method counts hyperglycemia.
     * @param listOfMeasurements    list of measurements from which hyperglycemia should be counted.
     * @return                      number of hyperglycemia
     */
    public int countHiperglycemia(ArrayList<Measurement> listOfMeasurements) {
        this.counterHiper = 0;
        for (Measurement i : listOfMeasurements) {
            if (i.isHiperglycemia()) {
                this.counterHiper++;
            }
        }
        return counterHiper;
    }

    public int findCurrentSugarLevel(ArrayList<Measurement> listOfMeasurements){
        int currentSugarLevel = 0;
        Collections.sort(this.listOfMeasurements);
        int lastIdx = listOfMeasurements.size() - 1;
        Measurement lastElement = listOfMeasurements.get(lastIdx);
        currentSugarLevel = lastElement.getSugarLevel();
        return currentSugarLevel;
    }

}
