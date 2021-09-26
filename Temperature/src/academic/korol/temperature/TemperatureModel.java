package academic.korol.temperature;

public interface TemperatureModel {
   // void setInputTemperature(double temperature);

   // void convertToCelsius(int inputIndex);

   // void convertFromCelsius(int outputIndex);

   void addTemperatureModelLisrener(TemperatureModelListener listener);

    void convertTemperature(int inputIndex, int outputIndex, double temperature);
}
