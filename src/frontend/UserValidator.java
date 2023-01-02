package frontend;

import backend.AllUsers;
import backend.User;

import java.io.IOException;

public class UserValidator {

    private AllUsers usersList = new AllUsers();

    public UserValidator(){
        usersList.getUsersFromFile();
    }

    public boolean authenticate(String username, String password) {
        return this.usersList.logIn(username, password);
    }

    public void addNewUserToList(User newUser){
        try {
            usersList.signIn(newUser.getUserName(), newUser.getPassword(), newUser.getTypeOfDiabities(),
                    newUser.getUpperTargetRage(), newUser.getLowerTargetRage(),
                    newUser.getHipoglycemia(), newUser.getHiperglycemia());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public User getUserData(String userName){
        return usersList.findUser(userName);
    }

}
