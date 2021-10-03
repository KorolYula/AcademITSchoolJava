package academic.korol.temperature;

import academic.korol.temperature.model.MyTemperatureModel;
import academic.korol.temperature.view.MainWindow;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        TemperatureScale temperatureScale = new TemperatureScale();
        temperatureScale.setTemperatureScalesNames(new String[]{
                "шкала Цельсия",
                "шкала Фаренгейта",
                "шкала Кельвина"});
        temperatureScale.setConversionFromCelsiusFactors(new ArrayList<>(Arrays.asList(
                new double[]{1, 0},
                new double[]{9.0 / 5.0, 32},
                new double[]{1, 273.15})));
        temperatureScale.setConversionToCelsiusFactors(new ArrayList<>(Arrays.asList(
                new double[]{1, 0},
                new double[]{5.0 / 9.0, -160.0 / 9.0},
                new double[]{1, -273.15})));
        TemperatureModel model = new MyTemperatureModel(temperatureScale);
        TemperatureController controller = new MyTemperatureController(model);
        TemperatureView mainWindow = new MainWindow(controller, temperatureScale.getTemperatureScalesNames());
        model.addTemperatureModelLisrener(mainWindow);
        mainWindow.start();
    }
}