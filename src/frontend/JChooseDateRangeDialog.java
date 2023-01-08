package frontend;

import backend.Date;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Locale;


/**
 * The JChooseDateRangeDialog class
 *
 * @author Zuzanna Popławska
 */

public class JChooseDateRangeDialog extends JDialog {

    private JButton showButton;

    private JFormattedTextField txtFrom;
    private JLabel lblFrom;
    private JFormattedTextField txtTo;
    private JLabel lblTo;

    private JPanel mainPanel;
    private JPanel buttonPanel;
    private JPanel choosePanel;

    public JButton getShowButton() {
        return showButton;
    }

    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-uuuu", Locale.US)
            .withResolverStyle(ResolverStyle.STRICT);
    DateValidator checkDate = new DateValidator(dateFormatter);

    public JChooseDateRangeDialog(Frame parent) {
        super(parent, "Choose date range", true);

        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setSize(350, 100);
        this.setLocationRelativeTo(null);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        choosePanel = new JPanel();
        choosePanel.setLayout(new FlowLayout());
        lblFrom = new JLabel("FROM:");
        lblTo = new JLabel("TO:");
        txtFrom = new JFormattedTextField(new DateMask());
        txtFrom.setColumns(10);
        txtTo = new JFormattedTextField(new DateMask());
        txtTo.setColumns(10);

        choosePanel.add(lblFrom);
        choosePanel.add(txtFrom);
        choosePanel.add(lblTo);
        choosePanel.add(txtTo);

        buttonPanel = new JPanel();
        showButton = new JButton("Show");
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(showButton);

        this.add(mainPanel);
        mainPanel.add(choosePanel);
        mainPanel.add(buttonPanel);

    }

    public String getFrom() {
        return txtFrom.getText().trim();
    }

    public String getTo() {
        return txtTo.getText().trim();
    }

    public Date getToDate(){
        return new Date(getTo());
    }
    public Date getFromDate(){
        return new Date(getFrom());
    }

    public boolean areDataValid(){
        if(!checkDate.isValid(getTo())) return false;
        if(!checkDate.isValid(getFrom())) return false;
        if(getToDate().toLocalDate().isBefore(getFromDate().toLocalDate()))return false;

        return true;
    }
}
