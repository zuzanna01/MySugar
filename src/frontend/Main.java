package frontend;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
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
            }
        });
    }
}