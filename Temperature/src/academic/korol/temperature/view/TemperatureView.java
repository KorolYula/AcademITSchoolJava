package academic.korol.temperature.view;

import academic.korol.temperature.model.TemperatureModelListener;

public interface TemperatureView extends TemperatureModelListener {
    void start();
}