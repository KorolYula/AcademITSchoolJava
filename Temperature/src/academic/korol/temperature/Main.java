package academic.korol.temperature;

import model.TemperatureModelImpl;
import view.MainWindow;

public class Main {
    public static void main(String[] args) {
        TemperatureModel model = new TemperatureModelImpl();
        TemperatureController controller = new TemperatureController(model);
        TemperatureView mainWindow = new MainWindow(controller);
        model.addTemperatureModelLisrener(mainWindow);
        mainWindow.start();
    }
}