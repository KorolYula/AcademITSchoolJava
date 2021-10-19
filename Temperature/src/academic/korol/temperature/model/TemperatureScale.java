package academic.korol.temperature.model;

public class TemperatureScale {
    private String name;
    private double conversionToCelsiusFactor1;
    private double conversionToCelsiusFactor2;
    private double conversionFromCelsiusFactor1;
    private double conversionFromCelsiusFactor2;

    public TemperatureScale(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getConversionToCelsiusFactor1() {
        return conversionToCelsiusFactor1;
    }

    public double getConversionToCelsiusFactor2() {
        return conversionToCelsiusFactor2;
    }

    public void setConversionToCelsiusFactor1(double conversionToCelsiusFactors) {
        this.conversionToCelsiusFactor1 = conversionToCelsiusFactors;
    }

    public void setConversionToCelsiusFactor2(double conversionToCelsiusFactors) {
        this.conversionToCelsiusFactor2 = conversionToCelsiusFactors;
    }

    public double getConversionFromCelsiusFactor1() {
        return conversionFromCelsiusFactor1;
    }

    public double getConversionFromCelsiusFactor2() {
        return conversionFromCelsiusFactor2;
    }

    public void setConversionFromCelsiusFactor1(double conversionFromCelsiusFactors) {
        this.conversionFromCelsiusFactor1 = conversionFromCelsiusFactors;
    }

    public void setConversionFromCelsiusFactor2(double conversionFromCelsiusFactors) {
        this.conversionFromCelsiusFactor2 = conversionFromCelsiusFactors;
    }
}