package academic.korol.temperature;

import java.util.ArrayList;

public interface TemperatureModel {

          double getFahrenheitTemperature() ;
     double getCelsiusTemperature();
     void setCelsiusTemperature(double celsiusTemperature);
     void addTemperatureModelLisrener(TemperatureModelListener listener);
}
