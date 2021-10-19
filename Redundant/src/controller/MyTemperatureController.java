package controller;

import academic.korol.temperature.model.TemperatureModel;

public class MyTemperatureController implements TemperatureController {
    private final TemperatureModel model;

    public MyTemperatureController(TemperatureModel model) {
        this.model = model;
    }

    public void convertTemperature(int inputIndex, int outputIndex, double temperature) {
        model.convertTemperature(inputIndex, outputIndex, temperature);
    }
}