package modal;

import academic.korol.temperature.TemperatureModel;
import academic.korol.temperature.TemperatureModelListener;

import java.util.ArrayList;
import java.util.List;

public class TemperatureModelImp implements TemperatureModel {
    public double inputTemperature;
    public double celsiusTemperature;
    public double outputTemperature;
    private final List<TemperatureModelListener> listeners = new ArrayList<>();

    public void convertTemperature(int inputIndex, int outputIndex, double temperature) {
        ArrayList<double[]> conversionToCelsiusFactors = new ArrayList<>();
        conversionToCelsiusFactors.add(new double[]{1, 0});
        conversionToCelsiusFactors.add(new double[]{5.0 / 9.0, -160.0 / 9.0});
        conversionToCelsiusFactors.add(new double[]{1, -273, 15});

        ArrayList<double[]> conversionFromCelsiusFactors = new ArrayList<>();
        conversionFromCelsiusFactors.add(new double[]{1, 0});
        conversionFromCelsiusFactors.add(new double[]{9.0 / 5.0, 32});
        conversionFromCelsiusFactors.add(new double[]{1, 273, 15});

        inputTemperature = temperature;
        celsiusTemperature = conversionToCelsiusFactors.get(inputIndex)[0] * inputTemperature + conversionToCelsiusFactors.get(inputIndex)[1];
        outputTemperature = conversionFromCelsiusFactors.get(outputIndex)[0] * celsiusTemperature + conversionFromCelsiusFactors.get(outputIndex)[1];

        for (TemperatureModelListener listener : listeners) {
            listener.temperatureChanded(outputTemperature);
        }
    }

    @Override
    public void addTemperatureModelLisrener(TemperatureModelListener listener) {
        listeners.add(listener);
    }
}