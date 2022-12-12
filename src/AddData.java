import javax.swing.*;
import java.awt.*;

public class AddData extends JDialog {

    private double sugarLevel;

    public AddData() {

        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setTitle("Adding new measurement");
        this.setBounds(300,200,300,90);
        setLayout(new FlowLayout());

        JLabel lblSugarLevel = new JLabel("Enter sugar level: ");
        JTextField txtSugarLevel = new JTextField();
        lblSugarLevel.setLabelFor(txtSugarLevel);

       lblSugarLevel.setPreferredSize(new Dimension(150,20));
       txtSugarLevel.setPreferredSize(new Dimension(100,20));

        lblSugarLevel.setOpaque(true);
        txtSugarLevel.setOpaque(true);

        this.add(lblSugarLevel);
        this.add(txtSugarLevel);



       // this.setSize(300,200);

    }

    public double getSugar(){
        return this.sugarLevel;
    }

}
