package frontend;

import backend.Measurement;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * The JListDialog class extends JDialog
 * creates new window with scroll panel with text area
 *
 * @author Zuzanna Popławska
 */

public class JListDialog extends JDialog {
    private JTextArea txtArea;
    private JScrollPane zawartosc;

    /**
     * Class Constructor
     * creates dialog window with scroll panel with text area
     * @param parent parent window for dialog
     */
    public JListDialog(Frame parent) {
        super(parent, "List of measurements", true);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setSize(350, 200);
        setLocationRelativeTo(parent);

        txtArea = new JTextArea();
        zawartosc = new JScrollPane(txtArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        setContentPane(zawartosc);
    }


    /**
     * writes measurements in text area
     * @param listOfMeasurements list of measurements to display
     * @param header first line of text informing  about date range of measurements
     */
    public void writeMeasurements(ArrayList<Measurement> listOfMeasurements, String header) {
        String buffor =header+"\n";
        for(Measurement m :listOfMeasurements) {
           buffor+=m;
           buffor+="\n";
           zawartosc.setVisible(true);
        }
        txtArea.setText(buffor);
    }
}
