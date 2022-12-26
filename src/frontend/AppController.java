package frontend;

import javax.swing.*;
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
            mView.setLabelAverage("Today average: " + mModel.countAverage("Today") + mModel.getSugarUnit());
            mView.setLabelDeviation("Today deviation: " + mModel.countDeviation("Today") + mModel.getSugarUnit());
            mView.setLabelHiper("Today hiper: " + mModel.countHiper("Today"));
            mView.setLabelHipo("Today hipo: " + mModel.countHipo("Today"));
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
        if (e.getActionCommand().equals("Your date (range)")){
            mView.getChooseDateRangeDialog().setVisible(true);
        }
        if (e.getActionCommand().equals("Login")) {
            if (Login.authenticate( mView.getLoginDialog().getUsername(),  mView.getLoginDialog().getPassword())) {
                JOptionPane.showMessageDialog( mView.getLoginDialog(),
                        "Hi " +  mView.getLoginDialog().getUsername() + "! You have successfully logged in.",
                        "Login",
                        JOptionPane.INFORMATION_MESSAGE);
                mView.getLoginDialog().setSucceeded(true);
                mView.getLoginDialog().dispose();
            } else {
                JOptionPane.showMessageDialog( mView.getLoginDialog(),
                        "Invalid username or password",
                        "Login",
                        JOptionPane.ERROR_MESSAGE);
                // reset username and password
                mView.getLoginDialog().setPasswordField("");
                mView.getLoginDialog().setUserNameField("");
                mView.getLoginDialog().setSucceeded(false);

            }
        }
        if (e.getActionCommand().equals("New")) {
            mView.getNewUserDialog().setVisible(true);
        }
        if (e.getActionCommand().equals("Exit")) {
            mView.getLoginDialog().dispose();
            mView.dispose();
        }
    }
}