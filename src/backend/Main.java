package backend;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        AllUsers allUsers = new AllUsers();
        allUsers.getUsersFromFile();
        allUsers.verifyUserNameAndPassword("zkrupska", "321");
        User currentUser = allUsers.findUser("zkrupska");
        allUsers.logIn(currentUser);
        Calculator calculator = new Calculator();
        calculator.countHipoglycemia(currentUser.getListOfUsersMeasurements());
        RaportWriter raportWriter = new CsvRaportWriter(30);
        raportWriter.writeRaport(currentUser, calculator);
    }
}
