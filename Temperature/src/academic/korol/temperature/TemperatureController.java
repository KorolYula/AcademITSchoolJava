package academic.korol.temperature;

public class TemperatureController {
    private final TemperatureModel model;

    public TemperatureController(TemperatureModel model) {
        this.model = model;
    }

    public void convertTemperature(int inputIndex, int outputIndex, double temperature) {
        model.convertTemperature(inputIndex, outputIndex, temperature);
    }
}