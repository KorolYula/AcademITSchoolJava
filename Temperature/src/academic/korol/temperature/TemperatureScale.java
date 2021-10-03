package academic.korol.temperature;

import java.util.ArrayList;

public class TemperatureScale {
    private String[] temperatureScalesNames;
    private ArrayList<double[]> conversionToCelsiusFactors;
    private ArrayList<double[]> conversionFromCelsiusFactors;

    public String[] getTemperatureScalesNames() {
        return temperatureScalesNames;
    }

    public void setTemperatureScalesNames(String[] temperatureScalesNames) {
        this.temperatureScalesNames = temperatureScalesNames;
    }

    public ArrayList<double[]> getConversionFromCelsiusFactors() {
        return conversionFromCelsiusFactors;
    }

    public void setConversionFromCelsiusFactors(ArrayList<double[]> conversionFromCelsiusFactors) {
        this.conversionFromCelsiusFactors = conversionFromCelsiusFactors;
    }

    public ArrayList<double[]> getConversionToCelsiusFactors() {
        return conversionToCelsiusFactors;
    }

    public void setConversionToCelsiusFactors(ArrayList<double[]> conversionToCelsiusFactors) {
        this.conversionToCelsiusFactors = conversionToCelsiusFactors;
    }
}