package academic.korol.temperature;


public class TemperatureController {
    private final TemperatureModel model;

    public TemperatureController(TemperatureModel model) {
        this.model = model;
    }

    public void convertTemperature(double temperature) {
        model.setTemperature(temperature);
    }

    public void setInputScale(int inputIndex) {
        model(inputIndex);
    }

    public void setOutputScale(int outputIndex) {
        System.out.println(outputIndex);
    }

}
