package frontend;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
            JAddingDialog dialog  = new JAddingDialog();
            dialog.setVisible(true);
        }
        else if(e.getActionCommand()=="from file"){
            JFileChooser chooser = new JFileChooser();
            chooser.showOpenDialog(null);
           // chooser.setVisible(true);
        }
    }
}
