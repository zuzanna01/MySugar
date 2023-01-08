package backend;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class contains list of Users and their data loaded from the database.
 * @author Zuzanna Krupska
 */
public class AllUsers {
    private ArrayList<User> listOfUsers;
    private TxtMeasurementsReader dataFromUsersFileReader;

    /**
     * Costructor
     */
    public AllUsers(){
        this.listOfUsers = new ArrayList<>();
        this.dataFromUsersFileReader = new TxtMeasurementsReader();
    }

    /**
     * This method serches the list for a User.
     * @param userName  name of the requested User
     * @return          user that has the required name
     */
    public User findUser(String userName){
        for(User i : listOfUsers){
            if(i.getUserName().equals(userName)){
                return i;
            }
        }
        return null;
    }

    /**
     * This method reades Users and their data (login, password, type of diabities, lower target rage, upper target rage,
     * hipoglycemia and hiperglycemia) from file Users.txt which is a database for the program and adds users to the list od users.
     */
    public void getUsersFromFile(){
        try {
            File file = new File("Users.txt");

            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String userName = reader.next();
                String password = reader.next();
                int typeOfDiabities = Integer.parseInt(reader.next());
                int upperTargetRage = Integer.parseInt(reader.next());
                int lowerTargetRage = Integer.parseInt(reader.next());
                int hipoglycemia = Integer.parseInt(reader.next());
                int hiperglycemia = Integer.parseInt(reader.next());
                User user = new User(userName, password, typeOfDiabities, upperTargetRage, lowerTargetRage, hipoglycemia, hiperglycemia);
                this.listOfUsers.add(user);
            }
        }catch(Exception e){
        }
    }

    /**
     * This method checks wheter given username and password are correct.
     * @param userName  name of eqisting User
     * @param password  User's password
     * @return          true if user eqists and given password is correct
     *                  false if user does not eqist or given password is incorrect
     */
    public boolean verifyUserNameAndPassword(String userName, String password){
        User user = findUser(userName);
        boolean authentication = false;
        if(user != null)
        {
            authentication = user.checkPassword(password);
        }
        return authentication;
    }

    /**
     * This method logs the user: it reads the data from Users file and adds red measurements to users list of measurements.
     * @param user  user that is being logged in
     */
    public void logIn(User user){
        this.dataFromUsersFileReader.setFileName(user.getUserName() + ".txt");
        this.dataFromUsersFileReader.setCurrentUser(user);
        dataFromUsersFileReader.readMeasurements();
        user.setListOfUsersMeasurements(dataFromUsersFileReader.getListOfMeasurements());
    }

    /**
     * This methid checks wheter there already eqists user with the same username.
     * @param currentUser   user who's userName is being checked
     * @return              true if there does not eqist any other user with the same username
     *                      false if there eqists other user with the same username
     */
    public boolean verifyUserName(User currentUser){
        User user = currentUser;
        for (User i : this.listOfUsers) {
            if (user.getUserName().equals(i.getUserName())) {
                return false;
            }
        }
        return true;
    }

    /**
     * This method sign in user: adds user to the list od users, saves user to database and creates user's file to store measurements.
     * @param currentUser   user that is being sign in
     */
    public void signIn(User currentUser){
        User user = currentUser;

        this.listOfUsers.add(user);
        user.saveUser();
        String pathname = user.getUserName() + ".txt";
        File file = new File(pathname);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
