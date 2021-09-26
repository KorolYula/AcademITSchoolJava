package academic.korol.temperature;

import modal.TemperatureModelImp;
import view.MainWindow;

public class Main {
    public static void main(String[] args) {
        TemperatureModel model = new TemperatureModelImp();
        TemperatureController controller = new TemperatureController(model);
        TemperatureView mainWindow = new MainWindow(controller);
        model.addTemperatureModelLisrener(mainWindow);
        mainWindow.start();
    }
}