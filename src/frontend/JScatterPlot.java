package frontend;

import backend.Measurement;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

/**
 * The  JScatterPlot class
 *
 * @author Zuzanna Popławska
 *
 */

public class JScatterPlot extends JPanel {

    private List<Measurement> dataset = new ArrayList<Measurement>();

    public void addmeasurement(Measurement m) {
        dataset.add(m);
    }

    public void addingmeasurements() {
        this.addmeasurement(new Measurement(30, 2, 12, 2022, 10, 30));
        this.addmeasurement(new Measurement(130, 2, 12, 2022, 20, 30));
        this.addmeasurement(new Measurement(200, 2, 12, 2022, 10, 00));
        this.addmeasurement(new Measurement(150, 2, 12, 2022, 7, 20));
        this.addmeasurement(new Measurement(130, 2, 12, 2022, 12, 00));
        this.addmeasurement(new Measurement(120, 2, 12, 2022, 17, 40));
        this.addmeasurement(new Measurement(90, 2, 12, 2022, 22, 00));
    }

    Measurement example = new Measurement(30, 2, 12, 2022, 10, 35);


    private int hipoValue = 70;
    private int hyperValue = 130;

    public JScatterPlot() {

        this.addingmeasurements();
        setLayout(new BorderLayout());

    }

    double height;
    double step_height;
    int y;

    double width;
    double step_width;
    int x;

    String[] hours = {"24:00", "23:00", "22:00", "21:00", "20:00", "19:00", "18:00", "17:00", "16:00",
            "15:00", "14:00", "13:00", "12:00", "11:00", "10:00", "09:00", "08:00", "07:00", "06:00", "05:00", "04:00",
            "03:00", "02:00", "01:00", "00:00"};

    @Override
    public void paintComponent(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g2d);

        height = (this.getHeight() - 40) - ((this.getHeight() - 40) % 11);
        step_height = height / 200;
        y = this.getHeight() - 30;

        int level = 0;
        String level_str;

        g2d.setPaint(new Color(100, 100, 100));
        Font textFont = new Font(Font.MONOSPACED, Font.BOLD, 14);
        g2d.setFont(textFont);
        do {
            Line2D.Double line = new Line2D.Double(30, y, this.getWidth() - 5, y);
            g2d.draw(line);
            level_str = String.valueOf(level);
            g2d.drawString(level_str, 0, y);
            y -= 20 * step_height;
            level += 20;
        } while (level <= 200);


        g2d.setStroke(new BasicStroke(5));
        g2d.setPaint(Color.RED);
        Line2D.Double line = new Line2D.Double(30, this.county(hyperValue), this.getWidth() - 5, this.county(hyperValue));
        g2d.draw(line);
        g2d.setPaint(Color.YELLOW);
        line = new Line2D.Double(30, this.county(hipoValue), this.getWidth() - 5, this.county(hipoValue));
        g2d.draw(line);


        width = (this.getWidth() - 55) - ((this.getWidth() - 55) % 24);
        step_width = width / (60 * 24);

        x = this.getWidth() - 40;

        g2d.setPaint(new Color(100, 100, 100));
        textFont = new Font(Font.MONOSPACED, Font.BOLD, 13);
        g2d.setFont(textFont);

        int i =0;
        for (String idx : hours) {
            if (this.getWidth() < 1200 && i%2==1) {
                i++; x -= 60 * step_width;continue;
            }
            g2d.drawString(idx, x, this.getHeight() - 10);
            x -= 60 * step_width;
            i++;
        }

        for (Measurement idx : dataset) {

            if (idx.getSugarLevel() > 130)
                g2d.setPaint(Color.RED);
            else if (idx.getSugarLevel() > 70)
                g2d.setPaint(Color.GREEN);
            else g2d.setPaint(Color.YELLOW);

            g2d.setStroke(new BasicStroke(1));
            g2d.fillOval((int) this.countx(idx.getTime().getHour(), idx.getTime().getMinute()), (int) this.county(idx.getSugarLevel()), 11, 11);
            g2d.setPaint(Color.BLACK);
            g2d.drawOval((int) this.countx(idx.getTime().getHour(), idx.getTime().getMinute()), (int) this.county(idx.getSugarLevel()), 11, 11);
        }
    }

    private double county(double level) {
        int y = this.getHeight() - 30;
        y -= level * step_height;
        return y;
    }

    private double countx(int hour, int minutes) {
        int x = this.getWidth() - 55;
        x -= ((24 * 60) - ((hour * 60) + minutes)) * step_width;
        return x;
    }

}
