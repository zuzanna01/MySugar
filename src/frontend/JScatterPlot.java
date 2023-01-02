package frontend;

import backend.Date;
import backend.Measurement;
import backend.User;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * The  JScatterPlot class
 *
 * @author Zuzanna Popławska
 */

public class JScatterPlot extends JPanel {

    private ArrayList<Measurement> dataset = new ArrayList<Measurement>();
    public void setDataset(ArrayList<Measurement> dataset) {
        this.dataset = dataset;
    }
    public void setDataSet(){

    }

    public void addmeasurement(Measurement m) {
        dataset.add(m);
    }

    private int lowerTargetRange = 300;
    private int upperTargetRange = 300;
    private int hipoLevel = 300;
    private int hiperLevel = 300;

    public void setLines(User currentUser) {
        lowerTargetRange = currentUser.getLowerTargetRage();
        upperTargetRange = currentUser.getUpperTargetRage();
        hipoLevel = currentUser.getHipoglycemia();
        hiperLevel = currentUser.getHiperglycemia();
    }

    public JScatterPlot() {
        setLayout(new BorderLayout());
    }

    double height;
    double step_height;
    int y;

    double width;
    double step_width;
    int x;




    private LocalDate localStartDate;
    private LocalDate localEndDate;
    List<LocalDate> dates;
    public void setDates(Date datefrom,Date dateto) {
        this.localStartDate = LocalDate.of(datefrom.getYear(),datefrom.getMonth(),datefrom.getDay());
        this.localEndDate=LocalDate.of(dateto.getYear(),dateto.getMonth(),dateto.getDay());
        dates = this.getDatesBetween(localStartDate,localEndDate.plusDays(1));
    }
    //https://www.baeldung.com/java-between-dates
    public static List<LocalDate> getDatesBetween(LocalDate startDate, LocalDate endDate) {

        return startDate.datesUntil(endDate)
                .collect(Collectors.toList());
    }

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
            Line2D.Double line = new Line2D.Double(30, y, this.getWidth() - 10, y);
            g2d.draw(line);
            level_str = String.valueOf(level);
            g2d.drawString(level_str, 0, y);
            y -= 20 * step_height;
            level += 20;
        } while (level <= 200);

        Line2D.Double line;
        g2d.setStroke(new BasicStroke(5));
        g2d.setPaint(Color.RED);
        line = new Line2D.Double(30, this.county(hiperLevel), this.getWidth() - 10, this.county(hiperLevel));
        g2d.draw(line);
        line = new Line2D.Double(30, this.county(hipoLevel), this.getWidth() - 10, this.county(hipoLevel));
        g2d.draw(line);
        g2d.setPaint(Color.YELLOW);
        line = new Line2D.Double(30, this.county(lowerTargetRange), this.getWidth() - 10, this.county(lowerTargetRange));
        g2d.draw(line);
        line = new Line2D.Double(30, this.county(upperTargetRange), this.getWidth() - 10, this.county(upperTargetRange));
        g2d.draw(line);

        width = this.getWidth()-55;
        step_width = width / (60 * 24);

        g2d.setPaint(new Color(100, 100, 100));
        textFont = new Font(Font.MONOSPACED, Font.BOLD, 13);
        g2d.setFont(textFont);

        this.paintAxisX(g2d);

        for (Measurement idx : dataset) {

            if (idx.getSugarLevel() > hiperLevel||idx.getSugarLevel()<hipoLevel)
                g2d.setPaint(Color.RED);
            else if (idx.getSugarLevel() > upperTargetRange||idx.getSugarLevel()<lowerTargetRange)
                g2d.setPaint(Color.YELLOW);
            else g2d.setPaint(Color.GREEN);

            g2d.setStroke(new BasicStroke(1));
            g2d.fillOval((int) this.countx(idx.getTime().getHour(), idx.getTime().getMinute(),idx.getDate())-6, (int) this.county(idx.getSugarLevel())-6, 12, 12);
            g2d.setPaint(Color.BLACK);
            g2d.drawOval((int) this.countx(idx.getTime().getHour(), idx.getTime().getMinute(),idx.getDate())-6, (int) this.county(idx.getSugarLevel())-6, 12, 12);
        }
    }

    private double county(double level) {
        int y = this.getHeight() - 30;
        y -= level * step_height;
        return y;
    }

    private double countx(int hour, int minutes, Date date) {
        int x = (int) (this.width - 45);
        x -= ((24 * 60) - ((hour * 60) + minutes)) * step_width;
        return x;
    }
    String[] hours0 = {"00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00",
            "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00",
            "21:00", "22:00", "23:00", "00:00"};//25>24

    String[]hours1={"00:00", "02:00", "04:00", "06:00", "08:00", "10:00",  "12:00",
            "14:00",  "16:00",  "18:00",  "20:00", "22:00",  "00:00"}; //13>12
    String[]hours2 ={"00:00", "03:00", "06:00", "09:00",  "12:00",  "15:00",  "18:00", "21:00", "00:00"};//9>8
    String[] hours3 ={"00:00","06:00","12:00","18:00","00:00"};//5>4
    String [] hours4 ={"00:00","12:00","00:00"};//3>2
    String[] hours;
    Integer[] deviders ={24,12,8,4,2};

    List<String> hoursList;

    public void setHours(int i) {
        if (i == 0) hours = hours0;
        else if (i == 1) hours = hours1;
        else if (i == 2) hours = hours2;
        else if (i == 3) hours = hours3;
        else if (i == 4) hours = hours4;
        else hours = new String[]{"00:00"};
        hoursList = new LinkedList<String>(Arrays.asList(hours));
        if (dates == null) return;
        for (int j = 0; j < dates.size() - 1; j++) {
            int idx=hoursList.size() - 1;
            hoursList.remove(idx);
            hoursList.addAll(Arrays.asList(hours));
        }
    }

    private void paintAxisX(Graphics2D g2d){
        x = (int) (width -17);
        int hourWidth=36;
        int i =0;
        int divider =deviders[i];
        int gapWidth1;
        int smallwidth;


        if(this.dates==null||this.dates.size()==1) {
            gapWidth1 = (int) (width - divider* hourWidth) / divider;
            while (gapWidth1<=0) {
                i++;
                if(i<5)divider=deviders[i];
                else divider =divider/2;
                gapWidth1 = (int) (width - divider* hourWidth) / divider;
                if(i>20)break;
            }
            this.setHours(i);
            for (String idx : hoursList) {

                g2d.drawString(idx, (int) (this.width - x), this.getHeight() - 10);
                x -=gapWidth1+hourWidth;
            }
        }
        else {
            smallwidth= (int) (width/this.dates.size());
            gapWidth1 =  (smallwidth - divider* hourWidth) / divider;
            while (gapWidth1<=0) {
                i++;
                if(i<5)divider=deviders[i];
                else divider =divider/2;
                gapWidth1 = (int) (smallwidth - divider* hourWidth) / divider;
                if(i>20)break;
            }
            this.setHours(i);
            for(int j =0;j<this.dates.size();j++){

                for (String idx : hoursList) {
                    g2d.drawString(idx, (int) (this.width - x), this.getHeight() - 10);
                    x -=gapWidth1+hourWidth;
               }
            }
        }

        if(this.dates==null) return;
        if(this.dates.size()==1)return;

        Font textFont = new Font(Font.MONOSPACED, Font.BOLD, 11);
        g2d.setFont(textFont);

        x = (int) (width -35);
        int dateWidth =62;
        int gapWidth2= (int) ((width-dateWidth*dates.size())/dates.size());
        x -= gapWidth2/2;
        for (LocalDate idx : dates){
            g2d.drawString(idx.format(DateTimeFormatter.ofPattern("dd-MM-uuuu")), (int) (this.width-x),this.getHeight()-40);
            x-=dateWidth+gapWidth2;
        }
    }


}

//uwzglednić daty w położeniu kropek