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
    private MenuBar mMenuBar = null;
    private AppView mView= null;
    private AppModel mModel =null;

    public MenuBarController(AppView view, AppModel model, MenuBar menu){
        this.mMenuBar= menu;
        this.mView=view;
        this.mModel=model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand()=="from keyboard"){
            JAddingNewMeasurementDialog dialog  = new JAddingNewMeasurementDialog(mView);
            dialog.setVisible(true);
        }
        if(e.getActionCommand()=="from file"){
            mMenuBar.getChooser().showOpenDialog(null);
        }
        if (e.getActionCommand().equals("Add")){
            mMenuBar.getAdd().setNewMeasurement(new Measurement(mMenuBar.getAdd().getSugar(),mMenuBar.getAdd().getTime(),mMenuBar.getAdd().getDate()));
        }
    }
}
