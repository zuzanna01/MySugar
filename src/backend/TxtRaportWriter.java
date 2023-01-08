package backend;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

public class TxtRaportWriter implements RaportWriter {
    private int numberOfDays;

    public TxtRaportWriter(int numberOfDays){
        this.numberOfDays = numberOfDays;
    }

    @Override
    public void writeRaport(User user, Calculator calculator) {
        LocalDate today = LocalDate.now();
        LocalDate xDaysBeforeToday = today.minusDays(this.numberOfDays);
        Date dateToday = new Date(today);
        Date dateXDaysBeforeToday = new Date(xDaysBeforeToday);
        user.setListOfUsersMeasurements(calculator.getDataFromGivenPeriod(dateXDaysBeforeToday, dateToday, user.getListOfUsersMeasurements()));


        String pathname = "Raport_User_Data" + "_" + user.getUserName() + ".txt";
        File file = new File(pathname);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write("User: " + user.getUserName() + '\n' + '\n' + '\n' + "Type of diabities: "
                    + user.getTypeOfDiabities() + '\n' + '\n' + "Number of hipoglycemia: " + calculator.countHipoglycemia(user.getListOfUsersMeasurements())
                    + '\n' + "Numebr of hiperglycemia: " + calculator.countHiperglycemia(user.getListOfUsersMeasurements()) + '\n'
                    + '\n' + "Average: " + calculator.calculateAverage(user.getListOfUsersMeasurements())+ '\n' + "Deviation: " + calculator.calculateDeviation(user.getListOfUsersMeasurements())
                    + '\n' + "Glycated Hemoglobin: " + calculator.calculateGlycatedHemoglobin(user.getListOfUsersMeasurements()));
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
