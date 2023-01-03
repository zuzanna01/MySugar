package frontend;

import backend.Measurement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The MenuBarController class is responsible for reacting to
 * events coming from the GUI MenuBar and subpanels
 *
 * @author Zuzanna Popławska
 *
 */
public class MenuBarController implements ActionListener {
    private MenuBar mMenuBar;
    private AppView mView;
    private AppModel mModel;

    public MenuBarController(AppView view, AppModel model, MenuBar menu){
        this.mMenuBar= menu;
        this.mView=view;
        this.mModel=model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("from keyboard")){
            mMenuBar.getAddDialog().setVisible(true);
        }
        if(e.getActionCommand().equals("from file")){
            mMenuBar.getChooser().showOpenDialog(null);
        }
        if (e.getActionCommand().equals("Add")){
            mMenuBar.getAddDialog().saveNewMeasurement();
            Measurement newMeasurement = new Measurement(mMenuBar.getAddDialog().getNewMeasurement());
            mModel.getCurrentUser().getMeasurementsFromFileTxt().saveMeasurement(newMeasurement);
            mMenuBar.getAddDialog().setVisible(false);
        }
        if (e.getActionCommand().equals("Target range")){

        }
        if (e.getActionCommand().equals("daily")){

        }
        if(e.getActionCommand().equals("weekly")){

        }
        if(e.getActionCommand().equals("from the beginning")){

        }
    }
}
