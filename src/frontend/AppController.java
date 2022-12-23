package frontend;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppController implements ActionListener {
    private AppView mView= null;
    private AppModel mModel =null;

    private String sugarUnit="mg/dL";

    public AppController(AppView view, AppModel model){
        this.mView=view;
        this.mModel=model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Today")){
            mView.setLabelAverage("Today average: "+mModel.countAverage("Today")+sugarUnit);
            mView.setLabelDeviation("Today deviation: "+mModel.countDeviation("Today")+sugarUnit);
            mView.setLabelHiper("Today hiper: "+mModel.countHiper("Today"));
            mView.setLabelHipo("Today hipo: "+mModel.countHipo("Today"));
        }

        if(e.getActionCommand().equals("Yesterday")){
            mView.setLabelAverage("Yesterday average: "+mModel.countAverage("Yesterday")+sugarUnit);
            mView.setLabelDeviation("deviation: "+mModel.countDeviation("Yesterday")+sugarUnit);
            mView.setLabelHiper("hiper: "+mModel.countHiper("Yesterday"));
            mView.setLabelHipo("hipo: "+mModel.countHipo("Yesterday"));
        }

        if(e.getActionCommand().equals("Last 7 days")){
            mView.setLabelAverage("Last 7 days average: "+mModel.countAverage("Last 7 days")+sugarUnit);
            mView.setLabelDeviation("deviation: "+mModel.countDeviation("Last 7 days")+sugarUnit);
            mView.setLabelHiper("hiper: "+mModel.countHiper("Last 7 days"));
            mView.setLabelHipo("hipo: "+mModel.countHipo("Last 7 days"));
        }
    }
}