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
    JMenuItem pzFromFile;
    JMenuItem pzFromKeyboard;
    JMenuItem pzDailyRaport ;
    JMenuItem pzWeeklyRaport;
    JMenuItem pzBeginningRaport;

    private JFileChooser chooser;
    private JAddingNewMeasurementDialog add;
    //private JChooseDateRangeDialog chooseDateRangeDialog;
    public JAddingNewMeasurementDialog getAddDialog() {
        return add;
    }
    public JFileChooser getChooser() {
        return chooser;
    }
    //public JChooseDateRangeDialog getChooseDateRangeDialog() {
    //    return chooseDateRangeDialog;
    //}
    /**
     * Class constructor.
     * menu has two main tabs:
     * 1)'Add measurement' with options 'from keyboard' and 'from file'
     * and 'Report' with options ?
     *
     */
    public MenuBar(){
        JMenu mnAddMeasurement = new JMenu("Add measurement");
        JMenu mnReport = new JMenu("Report");
        JMenu mnHelp = new JMenu("Help");

        pzFromKeyboard = new JMenuItem("from keyboard");
        pzFromFile= new JMenuItem("from file");
        mnAddMeasurement.add(pzFromKeyboard);
        mnAddMeasurement.add(pzFromFile);

        pzDailyRaport = new JMenuItem("daily");
        pzWeeklyRaport = new JMenuItem("weekly");
        pzBeginningRaport = new JMenuItem("monthly");
        mnReport.add(pzDailyRaport);
        mnReport.add(pzWeeklyRaport);
        mnReport.add(pzBeginningRaport);

        //chooseDateRangeDialog = new JChooseDateRangeDialog(null);
        //chooseDateRangeDialog.getShowButton().setText("Generate");

        add(mnAddMeasurement);
        add(mnReport);
        add(mnHelp);

        chooser = new JFileChooser();
        add = new JAddingNewMeasurementDialog(null);

    }
    /**
     * Adds action listeners to menu positions
     */
    public void setMenuBarController(ActionListener c) {
        this.pzFromKeyboard.addActionListener(c);
        this.pzFromFile.addActionListener(c);
        this.getAddDialog().getAddButton().addActionListener(c);
        this.pzDailyRaport.addActionListener(c);
        this.pzBeginningRaport.addActionListener(c);
        this.pzWeeklyRaport.addActionListener(c);
        //this.chooseDateRangeDialog.getShowButton().addActionListener(c);
    }
}
