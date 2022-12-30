package frontend;

import javax.swing.*;
import java.awt.*;

/**
 * The  JLoginDialog class
 *
 * @author Zuzanna Popławska
 */

public class JLoginDialog extends JDialog {
    private boolean succeeded;

    private JPanel mainPanel;
    private JPanel loginPanel;
    private JPanel buttonPanel_2;

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JLabel lblUsername;
    private JLabel lblPassword;

    private JButton loginButton;
    private JButton cancelButton;
    private JButton newAccountButton;

    public boolean isSucceeded() {
        return succeeded;
    }

    public String getUsername() {
        return txtUsername.getText().trim();
    }

    public String getPassword() {
        return new String(txtPassword.getPassword());
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public JButton getNewAccountButton() {
        return newAccountButton;
    }

    public void setUserNameField(String text) {
        this.txtUsername.setText(text);
    }

    public void setPasswordField(String text) {
        this.txtPassword.setText(text);
    }

    public void setSucceeded(boolean succeeded) {
        this.succeeded = succeeded;
    }


    public JLoginDialog(Frame parent) {

        super(parent, "Login", true);
        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        this.setSize(300, 140);
        setLocationRelativeTo(parent);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        loginPanel = new JPanel();

        lblUsername = new JLabel("Username: ");
        txtUsername = new JTextField();
        lblPassword = new JLabel("Password: ");
        txtPassword = new JPasswordField();

        GroupLayout layout = new GroupLayout(loginPanel);
        loginPanel.setLayout(layout);
        loginPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        layout.setAutoCreateGaps(true);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(lblUsername)
                        .addComponent(lblPassword))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(txtUsername)
                        .addComponent(txtPassword)));
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblUsername)
                        .addComponent(txtUsername))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblPassword)
                        .addComponent(txtPassword)));

        loginButton = new JButton("Login");
        cancelButton = new JButton("Exit");
        newAccountButton = new JButton("New");

        buttonPanel_2 = new JPanel();
        buttonPanel_2.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        buttonPanel_2.setLayout(new BoxLayout(buttonPanel_2, BoxLayout.X_AXIS));
        buttonPanel_2.add(Box.createHorizontalGlue());
        buttonPanel_2.add(loginButton);
        buttonPanel_2.add(Box.createHorizontalGlue());
        buttonPanel_2.add(cancelButton);
        buttonPanel_2.add(Box.createHorizontalGlue());
        buttonPanel_2.add(newAccountButton);
        buttonPanel_2.add(Box.createHorizontalGlue());

        this.add(mainPanel);
        mainPanel.add(loginPanel);
        mainPanel.add(buttonPanel_2);


    }


}