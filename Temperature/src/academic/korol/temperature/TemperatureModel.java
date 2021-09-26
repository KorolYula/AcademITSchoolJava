package academic.korol.temperature;

public interface TemperatureModel {
    void addTemperatureModelLisrener(TemperatureModelListener listener);

    void convertTemperature(int inputIndex, int outputIndex, double temperature);
}