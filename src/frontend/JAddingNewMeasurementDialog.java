package frontend;

import backend.Measurement;
import backend.Time;
import backend.Date;

import javax.swing.*;
import java.awt.*;

/**
 * The  JAddingNewMeasurementDialog class
 *
 * @author Zuzanna Popławska
 */

public class JAddingNewMeasurementDialog extends JDialog {

    private Measurement newMeasurement = null;
    public Measurement getNewMeasurement() {return newMeasurement;}

    private JLabel lblSugarLevel;
    private JTextField txtSugarLevel;
    private JLabel lblDate;
    private JFormattedTextField txtDate;
    private JLabel lblTime;
    private JFormattedTextField txtTime;
    private JButton addButton;

    public JAddingNewMeasurementDialog(Frame parent) {
        super(parent, "Login", true);

        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setTitle("Adding new measurement");
        this.setSize(400, 160);
        this.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        JPanel addingPanel = new JPanel();
        addingPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GroupLayout layout = new GroupLayout(addingPanel);
        addingPanel.setLayout(layout);

        lblSugarLevel = new JLabel("Sugar level: ");
        txtSugarLevel = new JTextField();
        lblSugarLevel.setLabelFor(txtSugarLevel);
        lblDate = new JLabel("Date: ");
        txtDate = new JFormattedTextField(new DateMask());
        lblTime = new JLabel("Time: ");
        txtTime = new JFormattedTextField(new HourMask());

        layout.setAutoCreateGaps(true);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(lblSugarLevel)
                        .addComponent(lblDate)
                        .addComponent(lblTime))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(txtSugarLevel)
                        .addComponent(txtDate)
                        .addComponent(txtTime)));
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblSugarLevel)
                        .addComponent(txtSugarLevel))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblDate)
                        .addComponent(txtDate))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblTime)
                        .addComponent(txtTime)));

        JPanel buttonPanel = new JPanel();
        addButton = new JButton("Add");
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(addButton);
        newMeasurement = new Measurement();

        this.add(mainPanel);
        mainPanel.add(addingPanel);
        mainPanel.add(buttonPanel);

    }

    public void saveNewMeasurement() {
        this.newMeasurement.setSugarLevel(Integer.parseInt(txtSugarLevel.getText().trim()));
        this.newMeasurement.setDate(new Date(txtDate.getText().trim()));
        this.newMeasurement.setTime(new Time(txtTime.getText().trim()));
    }

    public JButton getAddButton() {
        return addButton;
    }
}
