package backend;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        AllUsers allUsers = new AllUsers();

        allUsers.getUsersFromFile();
        allUsers.signIn("ania", "234", 1, 140, 90, 80, 150);
    }
}
