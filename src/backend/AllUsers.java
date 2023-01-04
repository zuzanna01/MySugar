package backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AllUsers {
    private ArrayList<User> listOfUsers;

    private TxtMeasurementsReader dataFromUsersFileReader;

    public AllUsers(){
        this.listOfUsers = new ArrayList<>();
    }

    // Z.P.
    // listofUser jest private i nie udostępnia getListOfUser
    // żeby nie można było napisać klasy która np. wypisze wszytkich użytkowników i haseł
    // jedyny obiekt tej klasy tworzymy w UserValidator

    //public ArrayList<User> getListOfUsers() {
    //    return listOfUsers;
    //}

    // dodawanie użytkownika do listy użytkowników
    // po kliknięciu signIn program powinien zapytać o te dane
    /*public void addUserFromString(){
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
    }*/

    public void addUser(User newUser){
        this.listOfUsers.add(newUser);
    }

    // weryfikacja czy użytkownik o danym loginie jest w bazie
    // trzeba tego użyć jeśli ktoś chce się zalogować
    public User findUser(String userName){
        for(User i : listOfUsers){
            if(i.getUserName().equals(userName)){
                return i;
            }
        }
        return null;
    }

    // pobranie danych użytkownika z bazy użytkowników
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

    // weryfikacja czy użytkownik o tym loginie istnieje i czy podano prawidłowe hasło
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
        this.dataFromUsersFileReader = new TxtMeasurementsReader(user.getUserName() + ".txt", user);
        dataFromUsersFileReader.getMeasurements();
        user.setListOfUsersMeasurements(dataFromUsersFileReader.getListOfMeasurements());

    }

    //weryfikacja czy dany użytkownik o tym loginie już istnieje przed REJESTRACJĄ
    public boolean verifyUserName(User currentUser){
        User user = currentUser;
        for (User i : this.listOfUsers) {
            if (user.getUserName().equals(i.getUserName())) {
                return false;
            }
        }
        return true;
    }
    // rejestracja nowego użytkownika
    // dodaje do listy użytkowników, zapisuje w bazie użytkowników i tworzy bazę na pomiary użytkownika (plik.txt o nawie użytkownika)
    public void signIn(User currentUser){
        User user = currentUser;

        this.listOfUsers.add(user);
        user.saveUser();
        //utworzenie pliku o nazwie użytkownika
        String pathname = user.getUserName() + ".txt";
        File file = new File(pathname);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
