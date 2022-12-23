package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class AppView extends JFrame {

    private JPanel mainPanel = null;

    private JButton mbutton1 = null;
    private JButton mbutton2 = null;
    private JButton mbutton3 = null;

    private JLabel mchart = null;
    private JLabel maverage = null;
    private JLabel mmaxdeviation = null;
    private JLabel mtimesHipo = null;
    private JLabel mtimesHyper = null;

    public AppView() {

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        this.getContentPane().add(mainPanel);

        this.setTitle("mySugar");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(100, 50, 800, 500);
        ImageIcon icon = new ImageIcon("donut.png");
        this.setIconImage(icon.getImage());

        JPanel buttonPanel = new JPanel();

        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,1));
        mainPanel.add(buttonPanel);

        this.mbutton1 = new JButton("Today");
        buttonPanel.add(this.mbutton1);
        this.mbutton2 = new JButton("Yesterday");
        buttonPanel.add(this.mbutton2);
        this.mbutton3 = new JButton("Last 7 days");
        buttonPanel.add(this.mbutton3);

        //JPanel charPanel = new JPanel();
        //charPanel.setBackground(new Color(250, 100, 100));
        //mchart= new JLabel("Here will be graph. ");
        //charPanel.add(mchart);
        //mainPanel.add(charPanel);

        JScatterPlot polotPanel = new JScatterPlot();
        mainPanel.add(polotPanel) ;


        JPanel labelsPanel = new JPanel();
        labelsPanel.setLayout(new FlowLayout());

        maverage = new JLabel("Average: --");
        maverage.setPreferredSize(new Dimension(250, 50));
        maverage.setHorizontalTextPosition(JLabel.CENTER);       //nie wiem dlaczego nie działa i tekst zostaje z prawej
        maverage.setFont(new Font(maverage.getFont().getName(), maverage.getFont().getStyle(), 17));
        maverage.setBackground(new Color(200, 230, 250));
        maverage.setBorder(BorderFactory.createLineBorder(new Color(100,200,250), 1));
        maverage.setOpaque(true);

        mmaxdeviation = new JLabel("Deviation: --");
        mmaxdeviation.setPreferredSize(new Dimension(250, 50));
        mmaxdeviation.setFont(new Font(mmaxdeviation.getFont().getName(), mmaxdeviation.getFont().getStyle(), 17));
        mmaxdeviation.setBackground(new Color(200, 230, 250));
        mmaxdeviation.setBorder(BorderFactory.createLineBorder(new Color(100,200,250), 1));
        mmaxdeviation.setOpaque(true);


        mtimesHyper = new JLabel("Hyper: --");
        mtimesHyper.setPreferredSize(new Dimension(100,50));
        mtimesHyper.setBackground(new Color(250,50,50));
        mtimesHyper.setBorder(BorderFactory.createLineBorder(new Color(255,0,0), 1));
        mtimesHyper.setOpaque(true);

        mtimesHipo = new JLabel("Hipo: --");
        mtimesHipo.setPreferredSize(new Dimension(100,50));
        mtimesHipo.setBackground(new Color(255,250,100));
        mtimesHipo.setBorder(BorderFactory.createLineBorder(new Color(255,255,0), 1));
        mtimesHipo.setOpaque(true);

        labelsPanel.add(maverage);
        labelsPanel.add(mmaxdeviation);
        labelsPanel.add(mtimesHipo);
        labelsPanel.add(mtimesHyper);

        mainPanel.add(labelsPanel);


    }

    public void setController(ActionListener c) {
        this.mbutton1.addActionListener(c);
        this.mbutton2.addActionListener(c);
        this.mbutton3.addActionListener(c);

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
