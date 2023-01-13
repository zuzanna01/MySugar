package frontend;

import backend.Measurement;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class JListDialog extends JDialog {
    private JTextArea txtArea;
    private JScrollPane zawartosc;

    public JListDialog(Frame parent) {
        super(parent, "List of measurements", true);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setSize(350, 200);
        setLocationRelativeTo(parent);

        txtArea = new JTextArea();
        zawartosc = new JScrollPane(txtArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        setContentPane(zawartosc);
    }

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
