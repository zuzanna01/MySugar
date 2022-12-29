package frontend;

import javax.swing.*;

/**
 * In the Main class takes place creation and connection
 * of the application elements
 *
 * @author Zuzanna Popławska
 *
 */

public class Main {
    public static void main(String[] args) {

        Runnable thread = new Runnable()
         {
            @Override
            public void run() {
                AppView view = new AppView();
                AppModel model = new AppModel();
                MenuBar menu = new MenuBar();
                view.setJMenuBar(menu);

                AppController ctrl = new AppController(view,model);
                view.setController(ctrl);
                MenuBarController menuctrl = new MenuBarController(view, model, menu);
                menu.setMenuBarController(menuctrl);

                view.setVisible(true);
                view.getLoginDialog().setVisible(true);

            }
        };
        SwingUtilities.invokeLater(thread);

    }

}