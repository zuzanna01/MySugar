package backend;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TxtRaportWriter implements RaportWriter {

    @Override
    public void writeRaport(User user, Calculator calculator) {
        String pathname = "Raport_User_Data" + user.getUserName() + ".txt";
        File file = new File(pathname);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write("User: " + user.getUserName() + '\n' + '\n' + '\n' + "Type of diabities: "
                    + user.getTypeOfDiabities() + '\n' + '\n' + "Number of hipoglycemia: " + calculator.getCounterHipo()
                    + '\n' + "Numebr of hiperglycemia: " + calculator.getCounterHiper() + '\n'
                    + '\n' + "Average: " + calculator.getAverage() + '\n' + "Deviation: " + calculator.getDeviation()
                    + '\n' + "Glycated Hemoglobin: " + calculator.getGlycatedHemoglobin());
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
