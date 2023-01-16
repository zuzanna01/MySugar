package backend;

/**
 * This interface allows classes to write raports.
 * @author Zuzanna Krupska
 */
public interface ReportWriter {
    /**
     * This method writes the raport.
     * @param user          based on User's data report is written
     * @param calculator    based on Calculator data report is written
     */
    void writeReport(User user, Calculator calculator);
}
