package academic.korol.temperature.model;

import java.util.ArrayList;
import java.util.List;

public class MyTemperatureModel implements TemperatureModel {
    public double inputTemperature;
    public double celsiusTemperature;
    public double outputTemperature;
    private final List<TemperatureModelListener> listeners = new ArrayList<>();
    private final ArrayList<TemperatureScale> temperatureScales;

    public MyTemperatureModel(ArrayList<TemperatureScale> temperatureScale) {
        this.temperatureScales = temperatureScale;
    }

    public void convertTemperature(int inputIndex, int outputIndex, double temperature) {
        inputTemperature = temperature;
        celsiusTemperature = temperatureScales.get(inputIndex).getConversionToCelsiusFactors()[0] * inputTemperature + temperatureScales.get(inputIndex).getConversionToCelsiusFactors()[1];
        outputTemperature = temperatureScales.get(outputIndex).getConversionFromCelsiusFactors()[0] * celsiusTemperature + temperatureScales.get(outputIndex).getConversionFromCelsiusFactors()[1];

        for (TemperatureModelListener listener : listeners) {
            listener.temperatureChanged(outputTemperature);
        }
    }

    @Override
    public void addTemperatureModelListener(TemperatureModelListener listener) {
        listeners.add(listener);
    }
}