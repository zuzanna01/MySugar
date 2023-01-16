package backend;

/**
 * This interface allows class to read measurements from files.
 * @author Zuzanna Krupska
 */
public interface MeasurementsReader {

    /**
     * This method reads and saves measurements from file.
     */
    void saveNewMeasurements();
}
