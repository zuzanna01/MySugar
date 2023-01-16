package backend;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This class allows to create report in .csv file.
 * @author Zuzanna Krupska
 */
public class CsvReportWriter implements ReportWriter {
    private int numberOfDays;
    private ArrayList<Measurement> listOfMeasurements;

    /**
     * constructor
     * @param numberOfDays  number of days from which report should be generated
     */
    public CsvReportWriter(int numberOfDays){
        this.numberOfDays = numberOfDays;
        this.listOfMeasurements = new ArrayList<>();
    }

    /**
     * This method writes the report of measurements (sugar level, time, date).
     * @param user
     * @param calculator
     */
    @Override
    public void writeReport(User user, Calculator calculator){
        LocalDate today = LocalDate.now();
        LocalDate xDaysBeforeToday = today.minusDays(this.numberOfDays);
        Date dateToday = new Date(today);
        Date dateXDaysBeforeToday = new Date(xDaysBeforeToday);
        this.listOfMeasurements = calculator.getDataFromGivenPeriod(dateXDaysBeforeToday, dateToday, user.getListOfUsersMeasurements());
        Collections.sort(this.listOfMeasurements);

        String pathname = "Raport_Measurements" + "_" + user.getUserName() + ".csv";
        File file = new File(pathname);
        int counter = 1;
        while(file.exists()){
            pathname = "Raport_Measurements" + "_" + user.getUserName() + "_" + "(" + counter + ")" + ".csv";
            file = new File(pathname);
        }

        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            for(Measurement i : this.listOfMeasurements){
                fileWriter.write(i.getSugarLevel() + "," + i.getTime().getHour()
                        + "," + i.getTime().getMinute() + "," + i.getDate().getDay() + ","
                        + i.getDate().getMonth() + "," + i.getDate().getYear()  + '\n' );
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
