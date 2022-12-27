package frontend;

import javax.swing.*;
import java.awt.*;

/**
 * The JChooseDateRangeDialog class
 *
 * @author Zuzanna Popławska
 */

public class JChooseDateRangeDialog extends JDialog {

    private JButton showButton;
    private JTextField txtFrom;
    private JLabel lblFrom;
    private JTextField txtTo;
    private JLabel lblTo;

    private JPanel mainPanel;
    private JPanel buttonPanel;
    private JPanel choosePanel;

    public JButton getShowButton() {
        return showButton;
    }

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
        txtFrom = new JTextField();
        txtFrom.setColumns(10);
        txtTo = new JTextField();
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

}
