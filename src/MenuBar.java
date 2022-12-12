import javax.swing.*;
import java.awt.event.ActionListener;

public class MenuBar extends JMenuBar {
    JMenuItem pzFromFile= null;
    JMenuItem pzFromKeyboard= null;

    public MenuBar(){
        JMenu mnAddMeasurement = new JMenu("Add measurement");
        JMenu mnRaport = new JMenu("Raport");
        JMenu mnUstawienia = new JMenu("Settings");
        JMenu mnHelp = new JMenu("Help");

        pzFromKeyboard = new JMenuItem("from keyboard");
        pzFromFile= new JMenuItem("from file");
        mnAddMeasurement.add(pzFromKeyboard);
        mnAddMeasurement.add(pzFromFile);

        JMenuItem pzRaportDobowy = new JMenuItem("dobowy");
        JMenuItem pzRaportTygodniowy = new JMenuItem("tygodniowy");
        JMenuItem pzRaportodPoczatku = new JMenuItem("od początku");
        mnRaport.add(pzRaportDobowy);
        mnRaport.add(pzRaportTygodniowy);
        mnRaport.add(pzRaportodPoczatku);

        JMenuItem pzBloodUnit = new JMenuItem("Blood sugar units");
        JMenuItem pzTargetRange = new JMenuItem("Target range");
        JMenuItem pzCarbsUnit = new JMenuItem("Carbs Unit");
        mnUstawienia.add(pzBloodUnit);
        mnUstawienia.add(pzTargetRange);
        mnUstawienia.add(pzCarbsUnit);

        JMenuItem pzUserGuide = new JMenuItem("User guide");
        JMenuItem pzInformation = new JMenuItem("Information");
        mnHelp.add(pzInformation);
        mnHelp.add(pzUserGuide);

        add(mnAddMeasurement);
        add(mnUstawienia);
        add(mnRaport);
        add(mnHelp);


    }
    public void setMenuBarController(ActionListener c) {
        this.pzFromKeyboard.addActionListener(c);
        this.pzFromFile.addActionListener(c);

    }
}
