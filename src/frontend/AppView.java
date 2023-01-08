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
    private JPanel labelsPanel2;

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
    private JLabel mglycatedHemoglobin;
    private JLabel mcurrentSugarLevel;

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

    /**
     * Class constructor.
     * Creates mainPanel which uses BoxLayout
     * and ands to buttonPanel, plotPanel, labelsPanel, labelsPanel2
     *
     * buttonPanel consists of four buttons named
     * 'Today', 'Yesterday','This week' and 'Your date range'
     * each of them allows user to choose time(date) range
     * from which measurements are displayed
     *
     * plotPanel is an object of  JScatterPlot class
     * which is responsible for  the graphical representation
     * of the chosen measurements set
     * go to JScatterPlot class documentation to get more information
     *
     * labelsPanel consist of four labels displaying:
     * 1) average sugar level from chosen measurements set
     * 2) deviation shows maximum deviation from the current average
     * 3) Hipo shows how many measurements in current set
     * have exceeded the hypoglycemic level
     * 4) Hiper shows how many measurements in current set
     * have exceeded the hyperglycemia level
     *
     * labelsPanel2 consist of two labels displaying:
     * 5) current sugar level - the last measurement
     * 6) predicted glycated hemoglobin (HbA1C) based
     * on past measurements
     *
     * @see JScatterPlot
     *
     */
    //donut icon source:
    //https://www.flaticon.com/free-icon/donut_3173398?term=donut&page=1&position=55&origin=search&related_id=3173398
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
        maverage.setPreferredSize(new Dimension(250, 40));
        maverage.setFont(new Font(maverage.getFont().getName(), maverage.getFont().getStyle(), 17));
        maverage.setBackground(new Color(200, 230, 250));
        maverage.setBorder(BorderFactory.createLineBorder(new Color(100, 200, 250), 2));
        maverage.setOpaque(true);

        mmaxdeviation = new JLabel("Deviation: --");
        mmaxdeviation.setPreferredSize(new Dimension(250, 40));
        mmaxdeviation.setFont(new Font(mmaxdeviation.getFont().getName(), mmaxdeviation.getFont().getStyle(), 17));
        mmaxdeviation.setBackground(new Color(200, 230, 250));
        mmaxdeviation.setBorder(BorderFactory.createLineBorder(new Color(100, 200, 250), 2));
        mmaxdeviation.setOpaque(true);

        mtimesHyper = new JLabel("Hyper: --");
        mtimesHyper.setPreferredSize(new Dimension(100, 40));
        mtimesHyper.setBackground(new Color(250, 200, 200));
        mtimesHyper.setBorder(BorderFactory.createLineBorder(new Color(255, 0, 0), 2));
        mtimesHyper.setOpaque(true);

        mtimesHipo = new JLabel("Hipo: --");
        mtimesHipo.setPreferredSize(new Dimension(100, 40));
        mtimesHipo.setBackground(new Color(250, 200, 200));
        mtimesHipo.setBorder(BorderFactory.createLineBorder(new Color(255, 0, 0), 2));
        mtimesHipo.setOpaque(true);

        labelsPanel.add(maverage);
        labelsPanel.add(mmaxdeviation);
        labelsPanel.add(mtimesHipo);
        labelsPanel.add(mtimesHyper);

        mainPanel.add(labelsPanel);

        labelsPanel2 = new JPanel();
        labelsPanel2.setLayout(new FlowLayout());

        mglycatedHemoglobin = new JLabel("HbA1C: --");
        mglycatedHemoglobin.setPreferredSize(new Dimension(100, 40));
        mglycatedHemoglobin.setBackground(new Color(150, 255, 200));
        mglycatedHemoglobin.setBorder(BorderFactory.createLineBorder(new Color(100, 200, 200), 2));
        mglycatedHemoglobin.setOpaque(true);

        mcurrentSugarLevel =new JLabel("Current sugar level: --");
        mcurrentSugarLevel.setPreferredSize(new Dimension(300, 40));
        mcurrentSugarLevel.setBackground(new Color(150, 255, 200));
        mcurrentSugarLevel.setBorder(BorderFactory.createLineBorder(new Color(100, 200, 200), 2));
        mcurrentSugarLevel.setOpaque(true);

        labelsPanel2.add(mcurrentSugarLevel);
        labelsPanel2.add(mglycatedHemoglobin);

        mainPanel.add(labelsPanel2);

        loginDialog = new JLoginDialog(this);
        newUserDialog = new JAddingNewUserDialog(this);
        chooseDateRangeDialog = new JChooseDateRangeDialog(this);
    }

    /**
     * Adds action listeners to all buttons.
     */
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
    /**
     * Changes the glycatedHemoglobinLabel displayed text
     * @param glycatedHemoglobin new glycatedHemoglobin value
     */
    public void setLabelGlycatedHemoglobin(Double glycatedHemoglobin) {
        this.mglycatedHemoglobin.setText("HbA1C: "+String.valueOf(glycatedHemoglobin));
    }
    /**
     * Changes the currentSugarLevelLabels displayed text
     * @param currentSugarLevel new glycatedHemoglobin value
     */
    public void setCurrentSugarLevelLabel (Double currentSugarLevel){
        this.mcurrentSugarLevel.setText("Current sugar level: "+currentSugarLevel);
    }

    /**
     * Changes the labels displayed text
     * should be used after changing measurements set to display
     * @param average  average from new measurements set
     * @param deviation deviation from new measurement set
     * @param hipo number of measurement which exceeded the hypoglycemic level in new set
     * @param hiper number of measurement which exceeded the hyperglycemia level in new set
     * @param sugarUnit sugar level unit (we add it to the average and deviation information)
     */

    public void setLabelsInfo(double average,double deviation,int hipo, int hiper,String sugarUnit){
     setLabelAverage("average: " + average + sugarUnit);
     setLabelDeviation("deviation: " +deviation+ sugarUnit);
     setLabelHiper("hiper: " + hiper);
     setLabelHipo("hipo: " + hipo);

}
}
