package backend;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AllUsers {
    private ArrayList<User> listOfUsers;
    private TxtMeasurementsReader dataFromUsersFileReader;

    public AllUsers(){
        this.listOfUsers = new ArrayList<>();
        this.dataFromUsersFileReader = new TxtMeasurementsReader();
    }

    public void addUser(User newUser){
        this.listOfUsers.add(newUser);
    }

    public User findUser(String userName){
        for(User i : listOfUsers){
            if(i.getUserName().equals(userName)){
                return i;
            }
        }
        return null;
    }

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

    public boolean verifyUserNameAndPassword(String userName, String password){
        User user = findUser(userName);
        boolean authentication = false;
        if(user != null)
        {
            authentication = user.checkPassword(password);
        }
        return authentication;
    }

    public void logIn(User user){
        this.dataFromUsersFileReader.setFileName(user.getUserName() + ".txt");
        this.dataFromUsersFileReader.setCurrentUser(user);
        dataFromUsersFileReader.getMeasurements();
        user.setListOfUsersMeasurements(dataFromUsersFileReader.getListOfMeasurements());
    }

    public boolean verifyUserName(User currentUser){
        User user = currentUser;
        for (User i : this.listOfUsers) {
            if (user.getUserName().equals(i.getUserName())) {
                return false;
            }
        }
        return true;
    }

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
