package backend;

public class User {
    private String login;
    private String password;
    private int typeOfDiabities;
    private int upperTargetRage;
    private int lowerTargetRage;
    private int hipoglycemia;
    private int hiperglycemia;

    public User(String login, String password, int typeOfDiabities, int upperTargetRage, int lowerTargetRage, int hipoglycemia, int hiperglycemia) {
        this.login = login;
        this.password = password;
        this.typeOfDiabities = typeOfDiabities;
        this.upperTargetRage = upperTargetRage;
        this.lowerTargetRage = lowerTargetRage;
        this.hipoglycemia = hipoglycemia;
        this.hiperglycemia = hiperglycemia;
    }


    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTypeOfDiabities(int typeOfDiabities) {
        this.typeOfDiabities = typeOfDiabities;
    }

    public void setUpperTargetRage(int upperTargetRage) {
        this.upperTargetRage = upperTargetRage;
    }

    public void setLowerTargetRage(int lowerTargetRage) {
        this.lowerTargetRage = lowerTargetRage;
    }

    public void setHipoglycemia(int hipoglycemia) {
        this.hipoglycemia = hipoglycemia;
    }

    public void setHiperglycemia(int hiperglycemia) {
        this.hiperglycemia = hiperglycemia;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public int getTypeOfDiabities() {
        return typeOfDiabities;
    }

    public int getUpperTargetRage() {
        return upperTargetRage;
    }

    public int getLowerTargetRage() {
        return lowerTargetRage;
    }

    public int getHipoglycemia() {
        return hipoglycemia;
    }

    public int getHiperglycemia() {
        return hiperglycemia;
    }

    public boolean checkPassword(String password){
        if(this.password.equals(password))
        {
            return true;
        }
        return false;
    }

}
