package academic.korol.temperature.model;

import java.util.ArrayList;
import java.util.List;

public class TemperatureConversionModel implements TemperatureModel {
    private final List<TemperatureModelListener> listeners = new ArrayList<>();
    private final ArrayList<TemperatureScale> temperatureScales;

    public ArrayList<TemperatureScale> getTemperatureScales() {
        return temperatureScales;
    }

      public TemperatureConversionModel(ArrayList<TemperatureScale> temperatureScale) {
        this.temperatureScales = temperatureScale;
    }

    public void convertTemperature(int inputIndex, int outputIndex, double temperature) {
        TemperatureScale inputScale = temperatureScales.get(inputIndex);
        double celsiusTemperature = inputScale.getConversionToCelsiusFactor1() * temperature + inputScale.getConversionToCelsiusFactor2();

        TemperatureScale outputScale = temperatureScales.get(outputIndex);
        double outputTemperature = outputScale.getConversionFromCelsiusFactor1() * celsiusTemperature + outputScale.getConversionFromCelsiusFactor2();

        for (TemperatureModelListener listener : listeners) {
            listener.temperatureChanged(outputTemperature);
        }
    }

    @Override
    public void addTemperatureModelListener(TemperatureModelListener listener) {
        listeners.add(listener);
    }
}