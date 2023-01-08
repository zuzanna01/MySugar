package frontend;

import backend.Measurement;
import backend.MeasurementsReader;
import backend.TxtMeasurementsReader;
import backend.UserMeasurementsReader;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The MenuBarController class is responsible for reacting to
 * events coming from the GUI MenuBar and subpanels
 *
 * @author Zuzanna Popławska
 */
public class MenuBarController implements ActionListener {
    private MenuBar mMenuBar;
    private AppView mView;
    private AppModel mModel;

    public MenuBarController(AppView view, AppModel model, MenuBar menu) {
        this.mMenuBar = menu;
        this.mView = view;
        this.mModel = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("from keyboard")) {
            mMenuBar.getAddDialog().setVisible(true);
        }
        if (e.getActionCommand().equals("from file")) {
            addingNewMeasurementsFromFileAction();
        }
        if (e.getActionCommand().equals("Add")) {
            addingNewMeasurementFromKeyboardAction();
        }

        if (e.getActionCommand().equals("daily")) {

        }
        if (e.getActionCommand().equals("weekly")) {

        }
        if (e.getActionCommand().equals("from the beginning")) {

        }
    }

    private void addingNewMeasurementFromKeyboardAction() {
        if (mMenuBar.getAddDialog().areDataValid()) {
            //pobiermay dane z okienka od użytkownika i tworzymy newMeasurement

            Measurement newMeasurement = mMenuBar.getAddDialog().getNewMeasurement();
            //ustawiamy measurementsReader
            MeasurementsReader measurementsReader = new UserMeasurementsReader(String.valueOf(newMeasurement.getSugarLevel()),
                    newMeasurement.getTime().toString(), newMeasurement.getDate().toString(), mModel.getCurrentUser());
            //zapis pomiaru do pliku użytkownika
            measurementsReader.saveNewMeasurements();
            //zamykamy okno dodawania pomiaru;
            mMenuBar.getAddDialog().dispose();

            mView.setLabelGlycatedHemoglobin(mModel.getCalculator().calculateGlycatedHemoglobin(mModel.getCurrentUser().getListOfUsersMeasurements()));
        } else {
            JOptionPane.showMessageDialog(mView.getLoginDialog(),
                    "Incorrect data ",
                    "",
                    JOptionPane.ERROR_MESSAGE);
        }

    }
    private void addingNewMeasurementsFromFileAction() {

        mMenuBar.getChooser().showOpenDialog(null);
        String path = mMenuBar.getChooser().getSelectedFile().getAbsolutePath();
        MeasurementsReader measurementsReader = new TxtMeasurementsReader(path, mModel.getCurrentUser());
        measurementsReader.saveNewMeasurements();

    }
}
