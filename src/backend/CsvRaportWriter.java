package backend;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

public class CsvRaportWriter implements RaportWriter {
    private int numberOfDays;
    private ArrayList<Measurement> listOfMeasurements;

    public CsvRaportWriter(int numberOfDays){
        this.numberOfDays = numberOfDays;
        this.listOfMeasurements = new ArrayList<>();
    }

    @Override
    public void writeRaport(User user, Calculator calculator){
        LocalDate today = LocalDate.now();
        LocalDate xDaysBeforeToday = today.minusDays(this.numberOfDays);
        Date dateToday = new Date(today);
        Date dateXDaysBeforeToday = new Date(xDaysBeforeToday);
        this.listOfMeasurements = calculator.getDataFromGivenPeriod(dateXDaysBeforeToday, dateToday, user.getListOfUsersMeasurements());
        Collections.sort(this.listOfMeasurements);

        String pathname = "Raport_Measurements" + user.getUserName() + ".csv";
        File file = new File(pathname);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            for(Measurement i : user.getListOfUsersMeasurements()){
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
