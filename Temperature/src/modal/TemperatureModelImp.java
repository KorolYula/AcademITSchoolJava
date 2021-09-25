package modal;

import academic.korol.temperature.TemperatureModel;
import academic.korol.temperature.TemperatureModelListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TemperatureModelImp  implements TemperatureModel {
    public double celsiusTemperature;
    private final List<TemperatureModelListener> listeners =new ArrayList<>();



     public double setCelsiusTemperature(double temperature) {
     ArrayList<double[]> conversionFactors= new ArrayList<>();
     conversionFactors.add(new double[]{1,0});
     conversionFactors.add(new double[]{5/9, -160/9});
     conversionFactors.add(new double[]{1,-273,15});


      return celsiusTemperature*1.8+32;
    }



    public void setTemperature(double temperature){

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
}
