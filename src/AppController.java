import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppController implements ActionListener {
    private AppView mView= null;
    private AppModel mModel =null;

    public AppController(AppView view,AppModel model){
        this.mView=view;
        this.mModel=model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Today")){

            //JOptionPane.showMessageDialog(null,"i am the funny button","Zdarzenie",JOptionPane.INFORMATION_MESSAGE);
            mView.setLabelAverage("Today average: "+mModel.countAverage("Today"));
        }

        if(e.getActionCommand().equals("Yesterday")){
            mView.setLabelAverage("Yesterday average: "+mModel.countAverage("Yesterday"));
        }

        if(e.getActionCommand().equals("Last 7 days")){
            mView.setLabelAverage("Last 7 days average: "+mModel.countAverage("Last 7 days"));
        }
    }
}
