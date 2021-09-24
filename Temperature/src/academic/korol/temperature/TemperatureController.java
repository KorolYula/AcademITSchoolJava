package academic.korol.temperature;

public class TemperatureController {
    private final TemperatureModel model;

        public TemperatureController(TemperatureModel model) {
        this.model = model;
    }
    public void convertTemperature(double celsiusTemperature){
            model.setCelsiusTemperature(celsiusTemperature);
    }
    //что-то было ,?
}
