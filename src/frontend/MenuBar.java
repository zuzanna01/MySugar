package frontend;

import javax.swing.*;
import java.awt.event.ActionListener;
/**
 * The MenuBar class extends JMenuBar
 * Gives user several action choices
 *
 * @author Zuzanna Popławska
 *
 */
public class MenuBar extends JMenuBar {
    JMenuItem pzFromFile= null;
    JMenuItem pzFromKeyboard= null;

    private JFileChooser chooser;
    private JAddingNewMeasurementDialog add;
    public JAddingNewMeasurementDialog getAddDialog() {
        return add;
    }

    public JFileChooser getChooser() {
        return chooser;
    }

    public MenuBar(){
        JMenu mnAddMeasurement = new JMenu("Add measurement");
        JMenu mnReport = new JMenu("Report");
        JMenu mnSettings = new JMenu("Settings");
        JMenu mnHelp = new JMenu("Help");

        pzFromKeyboard = new JMenuItem("from keyboard");
        pzFromFile= new JMenuItem("from file");
        mnAddMeasurement.add(pzFromKeyboard);
        mnAddMeasurement.add(pzFromFile);

        JMenuItem pzDailyRaport = new JMenuItem("daily");
        JMenuItem pzWeeklyRaport = new JMenuItem("weekly");
        JMenuItem pzBeginningRaport = new JMenuItem("from the beginning");
        mnReport.add(pzDailyRaport);
        mnReport.add(pzWeeklyRaport);
        mnReport.add(pzBeginningRaport);

        JMenuItem pzBloodUnit = new JMenuItem("Blood sugar units");
        JMenuItem pzTargetRange = new JMenuItem("Target range");
        mnSettings.add(pzBloodUnit);
        mnSettings.add(pzTargetRange);

        JMenuItem pzUserGuide = new JMenuItem("User guide");
        JMenuItem pzInformation = new JMenuItem("Information");
        mnHelp.add(pzInformation);
        mnHelp.add(pzUserGuide);

        add(mnAddMeasurement);
        add(mnSettings);
        add(mnReport);
        add(mnHelp);

        chooser = new JFileChooser();
        add = new JAddingNewMeasurementDialog(null);

    }

    public void setMenuBarController(ActionListener c) {
        this.pzFromKeyboard.addActionListener(c);
        this.pzFromFile.addActionListener(c);
        this.getAddDialog().getAddButton().addActionListener(c);
    }
}
