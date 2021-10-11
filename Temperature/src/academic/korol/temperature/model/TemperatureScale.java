package academic.korol.temperature.model;

public class TemperatureScale {
    private String name;
    private double[] conversionToCelsiusFactors;
    private double[]conversionFromCelsiusFactors;

    public TemperatureScale(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double[] getConversionToCelsiusFactors() {
        return conversionToCelsiusFactors;
    }

    public void setConversionToCelsiusFactors(double[] conversionToCelsiusFactors) {
        this.conversionToCelsiusFactors = conversionToCelsiusFactors;
    }

    public double[] getConversionFromCelsiusFactors() {
        return conversionFromCelsiusFactors;
    }

    public void setConversionFromCelsiusFactors(double[] conversionFromCelsiusFactors) {
        this.conversionFromCelsiusFactors = conversionFromCelsiusFactors;
    }
}