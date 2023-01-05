package backend;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        AllUsers allUsers = new AllUsers();
        allUsers.getUsersFromFile();
        allUsers.verifyUserNameAndPassword("zkrupska", "321");
        User currentUser = allUsers.findUser("zkrupska");
        allUsers.logIn(currentUser);
        MeasurementsReader measurementsReader = new UserMeasurementsReader("199", "12:30", "05-01-2023", currentUser);
        measurementsReader.saveNewMeasurements();
    }
}
