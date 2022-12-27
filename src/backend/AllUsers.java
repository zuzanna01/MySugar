package backend;

import java.util.ArrayList;
import java.util.Scanner;

public class AllUsers {
    private ArrayList<User> listOfUsers;

    public AllUsers(){
        this.listOfUsers = new ArrayList<>();
        listOfUsers.add(new User("Monika","haslo",1,130,70,1,1));
        listOfUsers.add(new User("Anna","HASLO",1,150,30,3,3));
    }

    public void addUser(){
        Scanner answerLogin = new Scanner(System.in);
        String login = answerLogin.next();
        Scanner answerPassword = new Scanner(System.in);
        String password = answerPassword.next();
        Scanner answerTypeOfDiabities= new Scanner(System.in);
        int typeOfDiabities = answerTypeOfDiabities.nextInt();
        Scanner answerUpperTargetRage = new Scanner(System.in);
        int upperTargetRage = answerUpperTargetRage.nextInt();
        Scanner answerLowerTargetRage = new Scanner(System.in);
        int lowerTargetRage = answerLowerTargetRage.nextInt();
        Scanner answerHipoglycemia = new Scanner(System.in);
        int hipogycemia = answerHipoglycemia.nextInt();
        Scanner answerHiperglycemia = new Scanner(System.in);
        int hiperglycemia = answerHiperglycemia.nextInt();

        this.listOfUsers.add(new User(login, password, typeOfDiabities, upperTargetRage, lowerTargetRage, hipogycemia, hiperglycemia));
    }

    public void addUser(User newUser){
        this.listOfUsers.add(newUser);
    }

    public User findUser(String login){
        for(User i : listOfUsers){
            if(i.getLogin().equals(login)){
                return i;
            }
        }
        return null;
    }




}
