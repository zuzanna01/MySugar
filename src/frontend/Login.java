package frontend;

public class Login {

    // jakas klasa pomocnicza która weryfikuje logowanie //możesz przenieś do backendu

    public static boolean authenticate(String username, String password) {
        // hardcoded username and password
        if (username.equals("bob") && password.equals("secret")) {
            return true;
        }
        return false;
    }


}
