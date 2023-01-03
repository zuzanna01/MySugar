package frontend;

import backend.User;

import javax.swing.*;
import java.awt.*;

/**
 * The JAddingNewUserDialog class
 *
 * @author Zuzanna Popławska
 */

public class JAddingNewUserDialog extends JDialog {

    private JPanel mainPanel;
    private JPanel addingPanel;
    private JPanel buttonPanel;

    public JButton getSaveButton() {
        return saveButton;
    }

    private JButton saveButton;

    private JTextField txtUsername;
    private JLabel lblUsername;
    private JTextField txtPassword;
    private JLabel lblPassword;
    private JTextField txtTypeOfDiabetes;
    private JLabel lblTypeofDiabetes;
    private JTextField txtUpperTargetRange;
    private JLabel lblUpperTargetRange;
    private JTextField txtLowerTargetRange;

    public JTextField getTxtUsername() {
        return txtUsername;
    }

    public JTextField getTxtPassword() {
        return txtPassword;
    }

    public JTextField getTxtTypeOfDiabetes() {
        return txtTypeOfDiabetes;
    }

    public JTextField getTxtUpperTargetRange() {
        return txtUpperTargetRange;
    }

    public JTextField getTxtLowerTargetRange() {
        return txtLowerTargetRange;
    }

    public JTextField getTxtHipoglycemia() {
        return txtHipoglycemia;
    }

    public JTextField getTxtHiperglycemia() {
        return txtHiperglycemia;
    }

    private JLabel lblLowerTargetRange;
    private JTextField txtHipoglycemia;
    private JLabel lblHipoglycemia;
    private JTextField txtHiperglycemia;
    private JLabel lblHiperglycemia;

    public JAddingNewUserDialog(Frame parent) {
        super(parent, "Adding new user", true);

        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setSize(400, 270);
        this.setLocationRelativeTo(null);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        addingPanel = new JPanel();
        addingPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GroupLayout layout = new GroupLayout(addingPanel);
        addingPanel.setLayout(layout);

        lblUsername = new JLabel("User name: ");
        lblPassword = new JLabel("Password: ");
        lblTypeofDiabetes = new JLabel("Type of diabetes: ");
        lblLowerTargetRange = new JLabel("Lower target range: ");
        lblUpperTargetRange = new JLabel("Upper target range: ");
        lblHiperglycemia = new JLabel("Hiperglycemia: ");
        lblHipoglycemia = new JLabel("Hipoglycemia: ");

        txtUsername = new JTextField();
        txtPassword = new JTextField();
        txtTypeOfDiabetes = new JTextField();
        txtLowerTargetRange = new JTextField();
        txtUpperTargetRange = new JTextField();
        txtHiperglycemia = new JTextField();
        txtHipoglycemia = new JTextField();

        layout.setAutoCreateGaps(true);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(lblUsername)
                        .addComponent(lblPassword)
                        .addComponent(lblTypeofDiabetes)
                        .addComponent(lblLowerTargetRange)
                        .addComponent(lblUpperTargetRange)
                        .addComponent(lblHipoglycemia)
                        .addComponent(lblHiperglycemia))

                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(txtUsername)
                        .addComponent(txtPassword)
                        .addComponent(txtTypeOfDiabetes)
                        .addComponent(txtLowerTargetRange)
                        .addComponent(txtUpperTargetRange)
                        .addComponent(txtHipoglycemia)
                        .addComponent(txtHiperglycemia)));
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblUsername)
                        .addComponent(txtUsername))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblPassword)
                        .addComponent(txtPassword))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblTypeofDiabetes)
                        .addComponent(txtTypeOfDiabetes))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblLowerTargetRange)
                        .addComponent(txtLowerTargetRange))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblUpperTargetRange)
                        .addComponent(txtUpperTargetRange))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblHipoglycemia)
                        .addComponent(txtHipoglycemia))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblHiperglycemia)
                        .addComponent(txtHiperglycemia)));

        buttonPanel = new JPanel();
        saveButton = new JButton("Create account");
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(saveButton);

        this.add(mainPanel);
        mainPanel.add(addingPanel);
        mainPanel.add(buttonPanel);



    }
    User newUser;
    public User getNewUserData(){
        newUser = new User(getUserName(),getPassword(),getTypeOfDiabetes(),getUpperTargetRange(),getLowerTargetRange(),
                getHipoglycemiaLevel(),getHiperglycemiaLevel());
        return newUser;
    }
    public String getUserName(){
        return getTxtUsername().getText().trim();
    }
    public String getPassword(){
        return getTxtPassword().getText().trim();
    }
    public int getTypeOfDiabetes(){
        return Integer.parseInt(getTxtTypeOfDiabetes().getText().trim());
    }
    public int  getUpperTargetRange(){
        return Integer.parseInt(getTxtUpperTargetRange().getText().trim());
    }
    public int  getLowerTargetRange(){
        return Integer.parseInt(getTxtUpperTargetRange().getText().trim());
    }
    public int  getHipoglycemiaLevel(){
        return Integer.parseInt(getTxtHipoglycemia().getText().trim());
    }
    public int  getHiperglycemiaLevel(){
        return Integer.parseInt(getTxtHiperglycemia().getText().trim());
    }


}
