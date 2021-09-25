package view;

import academic.korol.temperature.TemperatureController;
import academic.korol.temperature.TemperatureModelListener;
import academic.korol.temperature.TemperatureView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class MainWindow implements TemperatureView {
    private JFrame frame;
    private final TemperatureController controller;
    private JLabel fahrienheitTemperatureLabel;

    public MainWindow(TemperatureController controller) {
        this.controller = controller;
    }

    public void start() {
        SwingUtilities.invokeLater(() -> {

            JFrame frame = new JFrame("Перевод температур");
            frame.setSize(600, 500);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel();

            JTextField celsiusTemperatureTextField = new JTextField(10);
            panel.add(celsiusTemperatureTextField);

            JLabel celsiusTemperatureLabel = new JLabel("Ведите температуру: ");
            panel.add(celsiusTemperatureLabel);

            String [] temperatureScales ={ "шкала Цельсия",
                    "шкала Фаренгейта",
                    "шкала Кельвина"};

            JLabel choiseInputScale = new JLabel();
            panel.add(choiseInputScale);
            choiseInputScale.setText("Выберите температуры какой шкалы вы вводите:");

            JComboBox <String> inputScale = new JComboBox<>(temperatureScales);
            panel.add(inputScale);
            inputScale.addActionListener(e -> {

                int inputIndex = inputScale.getSelectedIndex();
                controller.setInputScale(inputIndex);
            });
            JLabel choiseOutputScale = new JLabel();
            panel.add(choiseOutputScale);
            choiseOutputScale.setText("Выберите температуру какой шкалы вы хотите расчитать^");

            JComboBox <String> outputScale = new JComboBox<>(temperatureScales);
            panel.add(outputScale);
            outputScale.addActionListener(e -> {

                 int outputIndex = outputScale.getSelectedIndex();
                controller.setOutputScale(outputIndex);
            });


            JButton convertTemperatureButton = new JButton("Перевести");
            convertTemperatureButton.addActionListener(e -> {
                try {
                    double celsiusTemperature = Double.parseDouble(celsiusTemperatureTextField.getText());
                    //TODO metod controllera
                    controller.convertTemperature(celsiusTemperature);


                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Ошибка ввода данных", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
            panel.add(convertTemperatureButton);
            fahrienheitTemperatureLabel = new JLabel();
            panel.add(fahrienheitTemperatureLabel);


            frame.add(panel);
            frame.setVisible(true);

        });
    }

    @Override
    public void temperatureChanded(double fahrenheitTemperature) {
        fahrienheitTemperatureLabel.setText("Температура в фаригнейтах" + fahrenheitTemperature);
    }
}


