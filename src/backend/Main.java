package backend;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        TxtMeasurementsReader txtMeasurementsReader = new TxtMeasurementsReader("Ania_Kowalska.txt");
        txtMeasurementsReader.getMeasurements();
        Calculator calculator = new Calculator();
        System.out.println(calculator.calculateDeviation(txtMeasurementsReader.getListOfMeasurements()));
    }
}
