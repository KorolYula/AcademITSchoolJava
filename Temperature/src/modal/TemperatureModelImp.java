package modal;

import academic.korol.temperature.TemperatureModel;
import academic.korol.temperature.TemperatureModelListener;

import java.util.ArrayList;
import java.util.List;

public class TemperatureModelImp  implements TemperatureModel {
    public double celsiusTemperature;
   private final List<TemperatureModelListener> listeners =new ArrayList<>();

    public double getFahrenheitTemperature() {
        //return fahrenheitTemperature;
        return celsiusTemperature*1.8+32;
    }

    public double getCelsiusTemperature() {
         return celsiusTemperature;
    }


    public void setCelsiusTemperature(double celsiusTemperature) {
        this.celsiusTemperature = celsiusTemperature;
        double fahriengeitTemperature = getFahrenheitTemperature();
        for (TemperatureModelListener listener:listeners){
            listener.temperatureChanded(fahriengeitTemperature);
        }

    }

    @Override
    public void addTemperatureModelLisrener(TemperatureModelListener listener) {
        listeners.add(listener);

    }

    /*private static double convertToFahrenheit( double celsiusTemperature){
        return celsiusTemperature*1.8+32;
    }
*/
}
