package backend;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CsvRaportWriter implements RaportWriter {
    @Override
    public void writeRaport(User user, Calculator calculator){
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
