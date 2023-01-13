package frontend;

import backend.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;

/**
 * The MenuBarController class is responsible for reacting to
 * events coming from the GUI MenuBar
 *
 * @author Zuzanna Popławska
 */
public class MenuBarController implements ActionListener {
    private MenuBar mMenuBar;
    private AppView mView;
    private AppModel mModel;

    /**
     * Class constructor.
     * We need to store references to the model and view and menu bar in the controller
     * so that the communication between them is possible
     *
     * @param view  AppView class responsible for GUI from which comes ActionEvents
     *              that we want to handle
     * @param model AppModel class  that stores application current state
     * @param menu  MenuBar class responsible for menu bar in GUI
     *
     * @see AppModel
     * @see AppView
     * @see MenuBar
     */

    public MenuBarController(AppView view, AppModel model, MenuBar menu) {
        this.mMenuBar = menu;
        this.mView = view;
        this.mModel = model;
    }
    /**
     * Invoked when an action from menu bar occurs.
     * Responsible for handling it
     *
     * @param e the event to be processed
     */
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
            mModel.csvraportWriter = new CsvRaportWriter(1);
            mModel.csvraportWriter.writeRaport(mModel.getCurrentUser(),mModel.getCalculator());
            mModel.txtraportWriter = new TxtRaportWriter(1);
            mModel.txtraportWriter.writeRaport(mModel.getCurrentUser(),mModel.getCalculator());
        }
        if (e.getActionCommand().equals("weekly")) {
            mModel.csvraportWriter = new CsvRaportWriter(7);
            mModel.csvraportWriter.writeRaport(mModel.getCurrentUser(),mModel.getCalculator());
            mModel.txtraportWriter = new TxtRaportWriter(7);
            mModel.txtraportWriter.writeRaport(mModel.getCurrentUser(),mModel.getCalculator());
        }

        if(e.getActionCommand().equals("monthly")){
            mModel.csvraportWriter = new CsvRaportWriter(30);
            mModel.csvraportWriter.writeRaport(mModel.getCurrentUser(),mModel.getCalculator());
            mModel.txtraportWriter = new TxtRaportWriter(30);
            mModel.txtraportWriter.writeRaport(mModel.getCurrentUser(),mModel.getCalculator());
        }
        //if (e.getActionCommand().equals("your date range")) {
//            mMenuBar.getChooseDateRangeDialog().setVisible(true);
//
  //      }
    //    if (e.getActionCommand().equals("Generate")){
      //      Date fromDate = mMenuBar.getChooseDateRangeDialog().getFromDate();
        //    Date toDate=mMenuBar.getChooseDateRangeDialog().getToDate();
     //   }
    }

    /**
     * responsible for adding new measurement which
     * was added by user from JAddingNewMeasurementDialog
     * saves it in program and also in user file
     */
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

            if((newMeasurement.getDate().toLocalDate().isAfter(mModel.getCurrentMeasurement().getDate().toLocalDate()))) {
                mModel.setCurrentMeasurement(newMeasurement);
                mView.setCurrentSugarLevelLabel(newMeasurement);
            }

            LocalTime oldMeasurementTime =LocalTime.of(mModel.getCurrentMeasurement().getTime().getHour(), mModel.getCurrentMeasurement().getTime().getMinute());
            LocalTime newMeasurementTime = LocalTime.of(newMeasurement.getTime().getHour(),newMeasurement.getTime().getMinute());
            if((newMeasurement.getDate().toLocalDate().isEqual(mModel.getCurrentMeasurement().getDate().toLocalDate()))&&
                    (newMeasurementTime.isAfter(oldMeasurementTime))){
                mModel.setCurrentMeasurement(newMeasurement);
                mView.setCurrentSugarLevelLabel(newMeasurement);
            }

            mView.setLabelGlycatedHemoglobin(mModel.getCalculator().calculateGlycatedHemoglobin(mModel.getCurrentUser().getListOfUsersMeasurements()));
            mMenuBar.getAddDialog().cleanFields();
        } else {
            JOptionPane.showMessageDialog(mView.getLoginDialog(),
                    "Incorrect data ",
                    "",
                    JOptionPane.ERROR_MESSAGE);

        }

    }

    /**
     * responsible for adding new measurements which
     * were added by user from another .txt file
     */
    private void addingNewMeasurementsFromFileAction() {

        mMenuBar.getChooser().showOpenDialog(null);
        String path = mMenuBar.getChooser().getSelectedFile().getAbsolutePath();
        MeasurementsReader measurementsReader = new TxtMeasurementsReader(path, mModel.getCurrentUser());
        measurementsReader.saveNewMeasurements();

    }
}
