package academic.korol.temperature.main;

import academic.korol.temperature.model.TemperatureScale;
import academic.korol.temperature.controller.MyTemperatureController;
import academic.korol.temperature.controller.TemperatureController;
import academic.korol.temperature.model.MyTemperatureModel;
import academic.korol.temperature.model.TemperatureModel;
import academic.korol.temperature.view.MainWindow;
import academic.korol.temperature.view.TemperatureView;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<TemperatureScale> temperatureScales = new ArrayList<>();

        TemperatureScale celsiusScale = new TemperatureScale("шкала Цельсия");
        celsiusScale.setConversionFromCelsiusFactors(new double[]{1.0, 0});
        celsiusScale.setConversionToCelsiusFactors(new double[]{1.0, 0});
        temperatureScales.add(celsiusScale);

        TemperatureScale kelvinScale = new TemperatureScale("шкала Кельвина");
        kelvinScale.setConversionFromCelsiusFactors(new double[]{1.0, 273.15});
        kelvinScale.setConversionToCelsiusFactors(new double[]{1.0, -273.15});
        temperatureScales.add(kelvinScale);

        TemperatureScale fahrenheitScale = new TemperatureScale("шкала Фаренгейта");
        fahrenheitScale.setConversionFromCelsiusFactors(new double[]{9.0 / 5.0, 32});
        fahrenheitScale.setConversionToCelsiusFactors(new double[]{5.0 / 9.0, -160 / 9.0});
        temperatureScales.add(fahrenheitScale);

        String[] scalesName = temperatureScales.stream()
                .map(TemperatureScale::getName).toArray(String[]::new);

        TemperatureModel model = new MyTemperatureModel(temperatureScales);
        TemperatureController controller = new MyTemperatureController(model);
        TemperatureView mainWindow = new MainWindow(controller, scalesName);
        model.addTemperatureModelListener(mainWindow);
        mainWindow.start();
    }
}