import javax.swing.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        //ArrayList<Measurement> data = new ArrayList<>();
        //data.add(new Measurement(120.00,"11/12/2022","12:35"));
        //data.add(new Measurement(110.00,"11/12/2022","12:47"));

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