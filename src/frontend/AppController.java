package frontend;

import backend.Date;
import backend.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Locale;

/**
 * The AppController class is responsible for reacting to
 * events coming from the GUI using methods from AppModel class
 * Links the user and the system.
 *
 * @author Zuzanna Popławska
 */

public class AppController implements ActionListener {

    private AppView mView = null;
    private AppModel mModel = null;

    /**
     * Class constructor.
     * We need to store references to the model and view in the controller so that the
     * communication between them is possible
     *
     * @param view  AppView class responsible for GUI from which comes ActionEvents
     *              that we want to handle
     * @param model AppModel class  that stores application current state
     */
    public AppController(AppView view, AppModel model) {
        this.mView = view;
        this.mModel = model;
    }

    /**
     * Class constructor.
     * We need to store references to the model and view in the controller so that the
     * communication between them is possible
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("Today")) {
            this.paintButton(mView.getMbutton1());
            mView.getPlotPanel().setDataset(null);
            mModel.setCurrentDataSet(mModel.getTodayDate(), mModel.getTodayDate(), mModel.getCurrentUser());
            mView.getPlotPanel().setDataset(mModel.getCurrentDataSet());
            mView.getPlotPanel().setDates(mModel.getTodayDate(), mModel.getTodayDate());
            System.out.print("TODAY: ");
            System.out.println(mModel.getCurrentDataSet());
            if (mModel.getCurrentDataSet().size() == 0) return;
            mView.getPlotPanel().repaint();
            mModel.setLabelsInfo();
            mView.setLabelsInfo(mModel.getAverage(), mModel.getDeviation(), mModel.getTimesHipo(), mModel.getTimesHiper(), mModel.getSugarUnit());
        }
        if (e.getActionCommand().equals("Yesterday")) {
            this.paintButton(mView.getMbutton2());
            mView.getMbutton2().getBackground();

            mView.getPlotPanel().setDataset(null);
            mModel.setCurrentDataSet(mModel.getYesterdayDate(), mModel.getYesterdayDate(), mModel.getCurrentUser());
            mView.getPlotPanel().setDataset(mModel.getCurrentDataSet());
            mView.getPlotPanel().setDates(mModel.getYesterdayDate(), mModel.getYesterdayDate());
            System.out.print("YESTERDAY: ");
            System.out.println(mModel.getCurrentDataSet());
            if (mModel.getCurrentDataSet().size() == 0) return;
            mView.getPlotPanel().repaint();
            mModel.setLabelsInfo();
            mView.setLabelsInfo(mModel.getAverage(), mModel.getDeviation(), mModel.getTimesHipo(), mModel.getTimesHiper(), mModel.getSugarUnit());

        }
        if (e.getActionCommand().equals("Last 7 days")) {
            this.paintButton(mView.getMbutton3());

            mView.getPlotPanel().setDataset(null);
            mModel.setCurrentDataSet(mModel.getWeekAgoDate(), mModel.getTodayDate(), mModel.getCurrentUser());
            System.out.println("FROM: " + mModel.getWeekAgoDate() + "TO: " + mModel.getTodayDate());
            System.out.println(mModel.getCurrentDataSet());
            if (mModel.getCurrentDataSet().size() == 0) return;
            mView.getPlotPanel().setDataset(mModel.getCurrentDataSet());
            mView.getPlotPanel().setDates(mModel.getWeekAgoDate(), mModel.getTodayDate());
            mView.getPlotPanel().repaint();

            mModel.setLabelsInfo();
            mView.setLabelsInfo(mModel.getAverage(), mModel.getDeviation(), mModel.getTimesHipo(), mModel.getTimesHiper(), mModel.getSugarUnit());

        }
        if (e.getActionCommand().equals("Your date (range)")) {
            this.paintButton(mView.getMbutton4());
            mView.getChooseDateRangeDialog().setVisible(true);
        }
        if (e.getActionCommand().equals("Show")) {
            String fromdate_str = mView.getChooseDateRangeDialog().getFrom();
            String todate_str = mView.getChooseDateRangeDialog().getTo();
            if (mView.getChooseDateRangeDialog().areDataValid()) {
                Date fromdate = new Date(fromdate_str);
                Date todate = new Date(todate_str);

                mView.getChooseDateRangeDialog().dispose();

                mView.getPlotPanel().setDataset(null);
                mModel.setCurrentDataSet(fromdate, todate, mModel.getCurrentUser());
                mView.getPlotPanel().setDataset(mModel.getCurrentDataSet());
                mView.getPlotPanel().setDates(fromdate, todate);
                System.out.println("FROM: " + fromdate + "TO: " + todate);
                System.out.println(mModel.getCurrentDataSet());
                mView.getPlotPanel().repaint();

                mModel.setLabelsInfo();
                mView.setLabelsInfo(mModel.getAverage(), mModel.getDeviation(), mModel.getTimesHipo(), mModel.getTimesHiper(), mModel.getSugarUnit());

            } else {
                JOptionPane.showMessageDialog(mView.getLoginDialog(),
                        "Invalid date",
                        "",
                        JOptionPane.ERROR_MESSAGE);

            }


        }
        if (e.getActionCommand().equals("Login")) {
            loginAction();
        }
        if (e.getActionCommand().equals("Exit")) {
            mView.getLoginDialog().dispose();
            mView.dispose();
            System.exit(0);
        }
        if (e.getActionCommand().equals("New")) {
            mView.getNewUserDialog().setVisible(true);
        }
        if (e.getActionCommand().equals("Create account")) {
            createNewAccountAction();
        }

    }

    private void paintButton(JButton button) {
        Color background = mView.getLoginDialog().getLoginButton().getBackground();
        Color newbackground = new Color(100, 100, 255);
        mView.getMbutton1().setBackground(background);
        mView.getMbutton2().setBackground(background);
        mView.getMbutton3().setBackground(background);
        mView.getMbutton4().setBackground(background);
        button.setBackground(newbackground);
    }

    private void loginAction() {
        //sprawdzamy czy użytkownik istnieje w Users.txt

        if (mModel.getAllUsers().verifyUserNameAndPassword(mView.getLoginDialog().getUsername(), mView.getLoginDialog().getPassword())) {
            JOptionPane.showMessageDialog(mView.getLoginDialog(),
                    "Hi " + mView.getLoginDialog().getUsername() + "! You have successfully logged in.",
                    "Login",
                    JOptionPane.INFORMATION_MESSAGE);
            //jeśli tak gasimy okienko logowania
            mView.getLoginDialog().setSucceeded(true);
            mView.getLoginDialog().dispose();
            //tworzymy currentUsera pobierając jego wszytkie ustawienia z pliku
            User currentUser = new User(mModel.getAllUsers().findUser(mView.getLoginDialog().getUsername()));
            //ładowanie pomiarów z pliku użytkownika -> a User nie ma już tego TxtReadera który miał listę wczytanych pomiarów :/
            mModel.getAllUsers().logIn(currentUser);
            //zapamiętujemy currentUsera w AppModel
            mModel.setCurrentUser(currentUser);
            //usawiamy położenie lini targetRange i hipoLevel i hiperLevel w panelu rysującym wykres
            // i robimy repaint() żeby linie się pojawiły
            mView.getPlotPanel().setLines(currentUser);
            mView.getPlotPanel().repaint();
            //set HbA1C if more than 30 measurements
            mView.setLabelGlycatedHemoglobin(mModel.getCalculator().calculateGlycatedHemoglobin(mModel.getCurrentUser().getListOfUsersMeasurements()));


        } else {
            //jeśli nie to wyświetlamy komunikat o niepoprawnych danych
            JOptionPane.showMessageDialog(mView.getLoginDialog(),
                    "Invalid username or password", "Login", JOptionPane.ERROR_MESSAGE);
            //czyścimy pola tekstowe w LoginDialog i możemy jeszcze raz się zalogować
            mView.getLoginDialog().setPasswordField("");
            mView.getLoginDialog().setUserNameField("");
            mView.getLoginDialog().setSucceeded(false);

        }
    }

    private void createNewAccountAction() {
        // tworzymy newUser pobierając dane z okienka newUserDialog
        User newUser = mView.getNewUserDialog().getNewUserData();
        //sprawdzamy czy użytkownik o podanym imieniu się nie powtarza
        if (mModel.getAllUsers().verifyUserName(newUser)) {
            // nastęnie dopisujemy newUsera do pliku i
            // dodajemy do listy w obiekcie AllUser (który jest w AppModel)
            mModel.getAllUsers().signIn(newUser);
            //ustawiamy currentUsera (który jest w AppModel)
            mModel.setCurrentUser(newUser);
            //usawiamy położenie lini targetRange i hipoLevel i hiperLevel w panelu rysującym wykres
            // i robimy repaint() żeby linie się pojawiły
            mView.getPlotPanel().setLines(newUser);
            mView.getPlotPanel().repaint();

            //zamykamy okno  LoginDialog i NewUserDialog i wyświtlamy komunikat że udało się zalogować
            mView.getLoginDialog().dispose();
            JOptionPane.showMessageDialog(mView.getNewUserDialog(),
                    "Hi " + mView.getNewUserDialog().getTxtUsername().getText().trim() + "! You have successfully created account.",
                    "Create account",
                    JOptionPane.INFORMATION_MESSAGE);
            mView.getNewUserDialog().dispose();
        } else {
            JOptionPane.showMessageDialog(mView.getNewUserDialog(),
                    "This User name is taken", "AddingNewUser", JOptionPane.ERROR_MESSAGE);
        }
    }

}

