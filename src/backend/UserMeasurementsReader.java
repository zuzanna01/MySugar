package backend;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

    public class UserMeasurementsReader implements MeasurementsReader {
        private ArrayList<Measurement> listOfMeasurements;
        private User currentUser;
        private String dataFirst;
        private String dataSecond;
        private String dataThird;

        public UserMeasurementsReader(String dataFirst, String dataSecond, String dataThird, User currentUser){
            this.listOfMeasurements = new ArrayList<Measurement>();
            this.currentUser = currentUser;
            this.dataFirst = dataFirst;
            this.dataSecond = dataSecond;
            this.dataThird = dataThird;
        }

        public void getMeasurement(){
            String[] time = dataSecond.split(":");
            String hour = correctData(time[0]);
            String minute = correctData(time[1]);

            String[] date = dataThird.split("-");
            String day = correctData(date[0]);
            String month = correctData(date[1]);
            String year = correctData(date[2]);

            Measurement measurement = new Measurement(Integer.parseInt(this.dataFirst), Integer.parseInt(day), Integer.parseInt(month),
                    Integer.parseInt(year), Integer.parseInt(hour), Integer.parseInt(minute));

            saveMeasurement(measurement);
            currentUser.checkHipoAndHiper(this.listOfMeasurements);
        }

        @Override
        public void saveNewMeasurements(){
            getMeasurement();
            currentUser.saveMeasurementsToUsersFile(this.listOfMeasurements);
            currentUser.getListOfUsersMeasurements().addAll(this.listOfMeasurements);
        }

        public void saveMeasurement(Measurement measurement){
            this.listOfMeasurements.add(measurement);
        }


        // tak samo jak z .txt trzeba poprawić jeśli przed liczbą jest 0, np. 09 to 9
        public String correctData(String data){
            if(data.startsWith("0")){
                return data.replaceFirst("0", "");
            }
            return data;
        }

    }


