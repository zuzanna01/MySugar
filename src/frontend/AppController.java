package frontend;

import backend.Measurement;
import backend.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("Today")) {
            mView.getMbutton1().setBackground(new Color(100, 100, 255));
            mView.setLabelAverage("Today average: " + mModel.countAverage("Today") + mModel.getSugarUnit());
            mView.setLabelDeviation("Today deviation: " + mModel.countDeviation("Today") + mModel.getSugarUnit());
            mView.setLabelHiper("Today hiper: " + mModel.countHiper("Today"));
            mView.setLabelHipo("Today hipo: " + mModel.countHipo("Today"));

            mView.getPlotPanel().addmeasurement(new Measurement(100, 2, 12, 2022, 15, 00));
            mView.getPlotPanel().repaint();
        }
        if (e.getActionCommand().equals("Yesterday")) {
            mView.setLabelAverage("Yesterday average: " + mModel.countAverage("Yesterday") + mModel.getSugarUnit());
            mView.setLabelDeviation("deviation: " + mModel.countDeviation("Yesterday") + mModel.getSugarUnit());
            mView.setLabelHiper("hiper: " + mModel.countHiper("Yesterday"));
            mView.setLabelHipo("hipo: " + mModel.countHipo("Yesterday"));
        }
        if (e.getActionCommand().equals("Last 7 days")) {
            mView.setLabelAverage("Last 7 days average: " + mModel.countAverage("Last 7 days") + mModel.getSugarUnit());
            mView.setLabelDeviation("deviation: " + mModel.countDeviation("Last 7 days") + mModel.getSugarUnit());
            mView.setLabelHiper("hiper: " + mModel.countHiper("Last 7 days"));
            mView.setLabelHipo("hipo: " + mModel.countHipo("Last 7 days"));
        }
        if (e.getActionCommand().equals("Your date (range)")) {
            mView.getChooseDateRangeDialog().setVisible(true);
        }
        if (e.getActionCommand().equals("Show")) {
        }


        if (e.getActionCommand().equals("Login")) {
            if (mModel.authenticate(mView.getLoginDialog().getUsername(), mView.getLoginDialog().getPassword())) {
                JOptionPane.showMessageDialog(mView.getLoginDialog(),
                        "Hi " + mView.getLoginDialog().getUsername() + "! You have successfully logged in.",
                        "Login",
                        JOptionPane.INFORMATION_MESSAGE);
                mView.getLoginDialog().setSucceeded(true);
                mView.getLoginDialog().dispose();

                mModel.setCurrentUser(mModel.getUsersList().findUser(mView.getLoginDialog().getUsername()));
                mView.getPlotPanel().setUpperTargetRange(mModel.getCurrentUser().getUpperTargetRage());
                mView.getPlotPanel().setLowerTargetRange(mModel.getCurrentUser().getLowerTargetRage());
                mView.getPlotPanel().repaint();

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

            mModel.getUsersList().addUser(newUser);

            mView.getLoginDialog().dispose();
            JOptionPane.showMessageDialog(mView.getLoginDialog(),
                    "Hi " + mView.getNewUserDialog().getTxtUsername().getText().trim() + "! You have successfully created account.",
                    "Create account",
                    JOptionPane.INFORMATION_MESSAGE);
            mView.getNewUserDialog().dispose();

        }

    }
}