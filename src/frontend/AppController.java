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
 * events coming from the GUI mainPanel and subpanels.
 * Links the user and the system.
 *
 * @author Zuzanna Popławska
 */

public class AppController implements ActionListener {

    private AppView mView = null;
    private AppModel mModel = null;

    public AppController(AppView view, AppModel model) {
        this.mView = view;
        this.mModel = model;
    }

    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-uuuu", Locale.US)
            .withResolverStyle(ResolverStyle.STRICT);
    DateValidator checkDate = new DateValidator(dateFormatter);

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("Today")) {
            this.paintButton(mView.getMbutton1());
            mView.getPlotPanel().setDataset(null);
            mModel.setCurrentDataSet(mModel.getTodayDate(),mModel.getTodayDate(), mModel.getCurrentUser());
            mView.getPlotPanel().setDataset(mModel.getCurrentDataSet());
            System.out.print("TODAY: ");
            System.out.println(mModel.getCurrentDataSet());
            mView.getPlotPanel().repaint();

           // mView.setLabelAverage("Today average: " + mModel.countAverage("Today") + mModel.getSugarUnit());
           // mView.setLabelDeviation("Today deviation: " + mModel.countDeviation("Today") + mModel.getSugarUnit());
           // mView.setLabelHiper("Today hiper: " + mModel.countHiper("Today"));
           // mView.setLabelHipo("Today hipo: " + mModel.countHipo("Today"));

        }
        if (e.getActionCommand().equals("Yesterday")) {
            this.paintButton(mView.getMbutton2());
            mView.getMbutton2().getBackground();
            //mView.setLabelAverage("Yesterday average: " + mModel.countAverage("Yesterday") + mModel.getSugarUnit());
            //mView.setLabelDeviation("deviation: " + mModel.countDeviation("Yesterday") + mModel.getSugarUnit());
            //mView.setLabelHiper("hiper: " + mModel.countHiper("Yesterday"));
            //mView.setLabelHipo("hipo: " + mModel.countHipo("Yesterday"));

            mView.getPlotPanel().setDataset(null);
            mModel.setCurrentDataSet(mModel.getYesterdayDate(),mModel.getYesterdayDate(),mModel.getCurrentUser());
            mView.getPlotPanel().setDataset(mModel.getCurrentDataSet());
            System.out.print("YESTERDAY: ");
            System.out.println(mModel.getCurrentDataSet());
            mView.getPlotPanel().repaint();
        }
        if (e.getActionCommand().equals("Last 7 days")) {
            this.paintButton(mView.getMbutton3());
            //mView.setLabelAverage("Last 7 days average: " + mModel.countAverage("Last 7 days") + mModel.getSugarUnit());
            //mView.setLabelDeviation("deviation: " + mModel.countDeviation("Last 7 days") + mModel.getSugarUnit());
            //mView.setLabelHiper("hiper: " + mModel.countHiper("Last 7 days"));
            //mView.setLabelHipo("hipo: " + mModel.countHipo("Last 7 days"));

            mView.getPlotPanel().setDataset(null);
            mModel.setCurrentDataSet(mModel.getWeekAgoDate(),mModel.getTodayDate(),mModel.getCurrentUser());
            System.out.println("FROM: "+mModel.getTodayDate()+"TO: "+mModel.getWeekAgoDate());
            System.out.println(mModel.getCurrentDataSet());

            mView.getPlotPanel().setDataset(mModel.getCurrentDataSet());
            mView.getPlotPanel().repaint();
        }
        if (e.getActionCommand().equals("Your date (range)")) {
            this.paintButton(mView.getMbutton4());
            mView.getChooseDateRangeDialog().setVisible(true);
        }
        if (e.getActionCommand().equals("Show")) {
            String fromdate_str = mView.getChooseDateRangeDialog().getFrom();
            String todate_str = mView.getChooseDateRangeDialog().getTo();
            if (checkDate.isValid(fromdate_str) && checkDate.isValid(todate_str)) {
                Date fromdate = new Date(fromdate_str);
                Date todate = new Date(todate_str);

                mView.getChooseDateRangeDialog().dispose();

                mView.getPlotPanel().setDataset(null);
                mModel.setCurrentDataSet(fromdate,todate,mModel.getCurrentUser());
                mView.getPlotPanel().setDataset(mModel.getCurrentDataSet());
                System.out.println("FROM: "+fromdate+"TO: "+todate);
                System.out.println(mModel.getCurrentDataSet());
                mView.getPlotPanel().repaint();

                //mView.getPlotPanel().setDataset(null);
                //mModel.setCurrentDataSet(mModel.getCurrentUser().getMeasurementsFromFileTxt().getListOfMeasurements());
                //mView.getPlotPanel().setDataset(mModel.getCurrentDataSet());
                //mView.getPlotPanel().repaint();
                }
            else{
                JOptionPane.showMessageDialog(mView.getLoginDialog(),
                        "Invalid date",
                        "",
                        JOptionPane.ERROR_MESSAGE);

                // reset date TO DO:

            }


        }

        if (e.getActionCommand().equals("Login")) {
            if (mModel.getUserValidator().authenticate(mView.getLoginDialog().getUsername(), mView.getLoginDialog().getPassword())) {
                JOptionPane.showMessageDialog(mView.getLoginDialog(),
                        "Hi " + mView.getLoginDialog().getUsername() + "! You have successfully logged in.",
                        "Login",
                        JOptionPane.INFORMATION_MESSAGE);
                mView.getLoginDialog().setSucceeded(true);
                mView.getLoginDialog().dispose();

                User currentUser = mModel.getUserValidator().getUserData(mView.getLoginDialog().getUsername());
                currentUser.getDataFromUsersFile();
                mModel.setCurrentUser(currentUser);
                mView.getPlotPanel().setLines(currentUser);
                mView.getPlotPanel().repaint();
                //mModel.getAllMeasurements().getMeasurements(mModel.getUserFileName());

            } else {
                JOptionPane.showMessageDialog(mView.getLoginDialog(),
                        "Invalid username or password",
                        "Login",
                        JOptionPane.ERROR_MESSAGE);

                // reset username and password
                mView.getLoginDialog().setPasswordField("");
                mView.getLoginDialog().setUserNameField("");
                mView.getLoginDialog().setSucceeded(false);

            }
        }
        if (e.getActionCommand().equals("Exit")) {
            mView.getLoginDialog().dispose();
            mView.dispose();
        }
        if (e.getActionCommand().equals("New")) {
            mView.getNewUserDialog().setVisible(true);
        }
        if (e.getActionCommand().equals("Create account")) {
            User newUser = new User(mView.getNewUserDialog().getTxtUsername().getText().trim(),
                    mView.getNewUserDialog().getTxtPassword().getText().trim(),
                    Integer.parseInt(mView.getNewUserDialog().getTxtTypeOfDiabetes().getText().trim()),
                    Integer.parseInt(mView.getNewUserDialog().getTxtUpperTargetRange().getText().trim()),
                    Integer.parseInt(mView.getNewUserDialog().getTxtLowerTargetRange().getText().trim()),
                    Integer.parseInt(mView.getNewUserDialog().getTxtHipoglycemia().getText().trim()),
                    Integer.parseInt(mView.getNewUserDialog().getTxtHiperglycemia().getText().trim()));

            mModel.getUserValidator().addNewUserToList(newUser);
            mModel.setCurrentUser(newUser);
            mView.getPlotPanel().setLines(newUser);
            mView.getPlotPanel().repaint();

            mView.getLoginDialog().dispose();
            JOptionPane.showMessageDialog(mView.getLoginDialog(),
                    "Hi " + mView.getNewUserDialog().getTxtUsername().getText().trim() + "! You have successfully created account.",
                    "Create account",
                    JOptionPane.INFORMATION_MESSAGE);
            mView.getNewUserDialog().dispose();

        }

    }
     private void paintButton(JButton button){
         Color background = mView.getLoginDialog().getLoginButton().getBackground();
         Color newbackground =new Color(100, 100, 255);
         mView.getMbutton1().setBackground(background);
         mView.getMbutton2().setBackground(background);
         mView.getMbutton3().setBackground(background);
         mView.getMbutton4().setBackground(background);
         button.setBackground(newbackground);

     }

}