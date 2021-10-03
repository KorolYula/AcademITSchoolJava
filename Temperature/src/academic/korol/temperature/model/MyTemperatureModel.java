package academic.korol.temperature.model;

import academic.korol.temperature.TemperatureModel;
import academic.korol.temperature.TemperatureModelListener;
import academic.korol.temperature.TemperatureScale;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyTemperatureModel implements TemperatureModel {
    public double inputTemperature;
    public double celsiusTemperature;
    public double outputTemperature;
    private final List<TemperatureModelListener> listeners = new ArrayList<>();
    private TemperatureScale temperatureScale;

    public MyTemperatureModel(TemperatureScale temperatureScale) {
        this.temperatureScale = temperatureScale;
    }

    public void convertTemperature(int inputIndex, int outputIndex, double temperature) {
       inputTemperature = temperature;
        celsiusTemperature =  temperatureScale.getConversionToCelsiusFactors().get(inputIndex)[0] *inputTemperature + temperatureScale.getConversionToCelsiusFactors().get(inputIndex)[1];
        outputTemperature =temperatureScale.getConversionFromCelsiusFactors().get(outputIndex)[0] * celsiusTemperature + temperatureScale.getConversionFromCelsiusFactors().get(outputIndex)[1];

        for (TemperatureModelListener listener : listeners) {
            listener.temperatureChanged(outputTemperature);
        }
    }

    @Override
    public void addTemperatureModelLisrener(TemperatureModelListener listener) {
        listeners.add(listener);
    }
}