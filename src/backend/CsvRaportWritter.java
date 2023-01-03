package backend;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CsvRaportWritter implements RaportWritter{
    @Override
    public void writeRaport(User user, Calculator calculator){
        String pathname = "Raport" + user.getUserName() + ".txt";
        File file = new File(pathname);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file, true);

            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
