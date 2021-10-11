package academic.korol.temperature.model;

public interface TemperatureModel {
    void addTemperatureModelListener(TemperatureModelListener listener);

    void convertTemperature(int inputIndex, int outputIndex, double temperature);
}