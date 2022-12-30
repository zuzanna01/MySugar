package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The AppView class is responsible for the presentation layer (GUI).
 * This part of the application displays objects that convey information
 * and represent actions that can be taken by the user.
 * Top-Level Container is JFrame
 *
 * @author Zuzanna Popławska
 */

public class AppView extends JFrame {

    private JPanel mainPanel;
    private JPanel buttonPanel;
    private JScatterPlot plotPanel;
    private JPanel labelsPanel;

    private JLoginDialog loginDialog;
    private JAddingNewUserDialog newUserDialog;
    private JChooseDateRangeDialog chooseDateRangeDialog;

    private JButton mbutton1;
    private JButton mbutton2;
    private JButton mbutton3;
    private JButton mbutton4;

    public JButton getMbutton1() {
        return mbutton1;
    }
    public JButton getMbutton2() {
        return mbutton2;
    }
    public JButton getMbutton3() {
        return mbutton3;
    }
    public JButton getMbutton4() {
        return mbutton4;
    }


    private JLabel maverage;
    private JLabel mmaxdeviation;
    private JLabel mtimesHipo;
    private JLabel mtimesHyper;

    private ImageIcon icon;

    public JLoginDialog getLoginDialog() {
        return loginDialog;
    }

    public JAddingNewUserDialog getNewUserDialog() {
        return newUserDialog;
    }

    public JChooseDateRangeDialog getChooseDateRangeDialog() {
        return chooseDateRangeDialog;
    }

    public JScatterPlot getPlotPanel() {
        return plotPanel;
    }

    public AppView() {

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        this.getContentPane().add(mainPanel);

        this.setTitle("mySugar");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 500);
        this.setLocationRelativeTo(null);
        this.icon = new ImageIcon("donut.png");
        this.setIconImage(icon.getImage());

        this.buttonPanel = new JPanel();

        this.buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 1));
        this.mainPanel.add(buttonPanel);

        this.mbutton1 = new JButton("Today");
        buttonPanel.add(this.mbutton1);
        this.mbutton2 = new JButton("Yesterday");
        buttonPanel.add(this.mbutton2);
        this.mbutton3 = new JButton("Last 7 days");
        buttonPanel.add(this.mbutton3);
        this.mbutton4 = new JButton("Your date (range)");
        buttonPanel.add(this.mbutton4);

        plotPanel = new JScatterPlot();
        mainPanel.add(plotPanel);

        labelsPanel = new JPanel();
        labelsPanel.setLayout(new FlowLayout());

        maverage = new JLabel("Average: --");
        maverage.setPreferredSize(new Dimension(250, 50));
        maverage.setFont(new Font(maverage.getFont().getName(), maverage.getFont().getStyle(), 17));
        maverage.setBackground(new Color(200, 230, 250));
        maverage.setBorder(BorderFactory.createLineBorder(new Color(100, 200, 250), 1));
        maverage.setOpaque(true);

        mmaxdeviation = new JLabel("Deviation: --");
        mmaxdeviation.setPreferredSize(new Dimension(250, 50));
        mmaxdeviation.setFont(new Font(mmaxdeviation.getFont().getName(), mmaxdeviation.getFont().getStyle(), 17));
        mmaxdeviation.setBackground(new Color(200, 230, 250));
        mmaxdeviation.setBorder(BorderFactory.createLineBorder(new Color(100, 200, 250), 1));
        mmaxdeviation.setOpaque(true);

        mtimesHyper = new JLabel("Hyper: --");
        mtimesHyper.setPreferredSize(new Dimension(100, 50));
        mtimesHyper.setBackground(new Color(250, 50, 50));
        mtimesHyper.setBorder(BorderFactory.createLineBorder(new Color(255, 0, 0), 1));
        mtimesHyper.setOpaque(true);

        mtimesHipo = new JLabel("Hipo: --");
        mtimesHipo.setPreferredSize(new Dimension(100, 50));
        mtimesHipo.setBackground(new Color(255, 250, 100));
        mtimesHipo.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 0), 1));
        mtimesHipo.setOpaque(true);

        labelsPanel.add(maverage);
        labelsPanel.add(mmaxdeviation);
        labelsPanel.add(mtimesHipo);
        labelsPanel.add(mtimesHyper);
        mainPanel.add(labelsPanel);

        loginDialog = new JLoginDialog(this);
        newUserDialog = new JAddingNewUserDialog(this);
        chooseDateRangeDialog = new JChooseDateRangeDialog(this);
    }

    public void setController(ActionListener c) {
        this.mbutton1.addActionListener(c);
        this.mbutton2.addActionListener(c);
        this.mbutton3.addActionListener(c);
        this.mbutton4.addActionListener(c);
        this.loginDialog.getLoginButton().addActionListener(c);
        this.loginDialog.getCancelButton().addActionListener(c);
        this.loginDialog.getNewAccountButton().addActionListener(c);
        this.newUserDialog.getSaveButton().addActionListener(c);
        this.chooseDateRangeDialog.getShowButton().addActionListener(c);
    }

    public void setLabelAverage(String average) {
        this.maverage.setText(average);
    }

    public void setLabelDeviation(String deviation) {
        this.mmaxdeviation.setText(deviation);
    }

    public void setLabelHipo(String hipo) {
        this.mtimesHipo.setText(hipo);
    }

    public void setLabelHiper(String hiper) {
        this.mtimesHyper.setText(hiper);
    }


}
