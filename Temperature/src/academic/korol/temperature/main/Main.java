package academic.korol.temperature.main;

import academic.korol.temperature.model.TemperatureScale;
import academic.korol.temperature.controller.MyTemperatureController;
import academic.korol.temperature.controller.TemperatureController;
import academic.korol.temperature.model.TemperatureConversionModel;
import academic.korol.temperature.model.TemperatureModel;
import academic.korol.temperature.view.MainWindow;
import academic.korol.temperature.view.TemperatureView;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<TemperatureScale> temperatureScales = new ArrayList<>();

        TemperatureScale celsiusScale = new TemperatureScale("шкала Цельсия");
        celsiusScale.setConversionFromCelsiusFactor1(1.0);
        celsiusScale.setConversionFromCelsiusFactor2( 0);

        celsiusScale.setConversionToCelsiusFactor1(1.0);
        celsiusScale.setConversionToCelsiusFactor2(0);

        temperatureScales.add(celsiusScale);

        TemperatureScale kelvinScale = new TemperatureScale("шкала Кельвина");
        kelvinScale.setConversionFromCelsiusFactor1(1.0);
        kelvinScale.setConversionFromCelsiusFactor2(273.15);

        kelvinScale.setConversionToCelsiusFactor1(1.0);
        kelvinScale.setConversionToCelsiusFactor2( -273.15);

        temperatureScales.add(kelvinScale);

        TemperatureScale fahrenheitScale = new TemperatureScale("шкала Фаренгейта");
        fahrenheitScale.setConversionFromCelsiusFactor1(9.0/5.0);
        fahrenheitScale.setConversionFromCelsiusFactor2( 32);

        fahrenheitScale.setConversionToCelsiusFactor1(5.0 / 9.0);
        fahrenheitScale.setConversionToCelsiusFactor2( -160 / 9.0);

        temperatureScales.add(fahrenheitScale);

        TemperatureModel model = new TemperatureConversionModel(temperatureScales);
        TemperatureController controller = new MyTemperatureController(model);
        TemperatureView mainWindow = new MainWindow(controller, temperatureScales);
        model.addTemperatureModelListener(mainWindow);
        mainWindow.start();
    }
}