package backend;

public interface ReadMeasurements {
    void getMeasurements();
    String correctData(String data);
    void saveMeasurements(Measurement measurement);
}
