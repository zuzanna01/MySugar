package backend;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

/**
 * This class allows to create report in .txt file.
 * @author Zuzanna Krupska
 */
public class TxtReportWriter implements ReportWriter {
    private int numberOfDays;

    /**
     * constructor
     * @param numberOfDays
     */
    public TxtReportWriter(int numberOfDays){
        this.numberOfDays = numberOfDays;
    }

    /**
     * This method writes the report of user's data.
     * @param user
     * @param calculator
     */
    @Override
    public void writeReport(User user, Calculator calculator) {
        LocalDate today = LocalDate.now();
        LocalDate xDaysBeforeToday = today.minusDays(this.numberOfDays);
        Date dateToday = new Date(today);
        Date dateXDaysBeforeToday = new Date(xDaysBeforeToday);
        user.setListOfUsersMeasurements(calculator.getDataFromGivenPeriod(dateXDaysBeforeToday, dateToday, user.getListOfUsersMeasurements()));


        String pathname = "Report_User_Data" + "_" + user.getUserName() + ".txt";
        File file = new File(pathname);
        int counter = 1;
        while(file.exists()){
            pathname = "Report_User_Data" + "_" + user.getUserName() + "_" + "(" + counter + ")" + ".txt";
            file = new File(pathname);
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write("User: " + user.getUserName() + '\n' + '\n' + '\n' + "Type of diabetes: "
                    + user.getTypeOfDiabetes() + '\n' + '\n' + "Number of hypoglycemia: " + calculator.countHipoglycemia(user.getListOfUsersMeasurements())
                    + '\n' + "Number of hyperglycemia: " + calculator.countHiperglycemia(user.getListOfUsersMeasurements()) + '\n'
                    + '\n' + "Average: " + calculator.calculateAverage(user.getListOfUsersMeasurements())+ '\n' + "Deviation: " + calculator.calculateDeviation(user.getListOfUsersMeasurements())
                    + '\n' + "Glycated Hemoglobin: " + calculator.calculateGlycatedHemoglobin(user.getListOfUsersMeasurements()));
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
