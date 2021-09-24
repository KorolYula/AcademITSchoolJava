package academic.korol.temperature;

public interface TemperatureModel {


     double getFahrenheitTemperature() ;
     double getCelsiusTemperature();
     void setCelsiusTemperature(double celsiusTemperature);
     void addTemperatureModelLisrener(TemperatureModelListener listener);
}
