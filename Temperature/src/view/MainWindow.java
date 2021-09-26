package view;

import academic.korol.temperature.TemperatureController;
import academic.korol.temperature.TemperatureView;
import javax.swing.*;

public class MainWindow implements TemperatureView {
    private JFrame frame;
    private final TemperatureController controller;
    private JLabel outputTemperatureLabel;

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

            JTextField temperatureTextField = new JTextField(10);
            panel.add(temperatureTextField);

            JLabel temperatureLabel = new JLabel("Ведите температуру: ");
            panel.add(temperatureLabel);

            String [] temperatureScales ={ "шкала Цельсия",
                    "шкала Фаренгейта",
                    "шкала Кельвина"};

            JLabel choiseInputScale = new JLabel();
            panel.add(choiseInputScale);
            choiseInputScale.setText("Выберите температуры какой шкалы вы вводите:");
            JComboBox <String> inputScale = new JComboBox<>(temperatureScales);
            panel.add(inputScale);
            inputScale.addActionListener(e -> {

               // int inputIndex = inputScale.getSelectedIndex();
             // controller.setInputScale(inputIndex);
            });

            JLabel choiseOutputScale = new JLabel();
            panel.add(choiseOutputScale);
            choiseOutputScale.setText("Выберите температуру какой шкалы вы хотите расчитать^");

            JComboBox <String> outputScale = new JComboBox<>(temperatureScales);
            panel.add(outputScale);
            outputScale.addActionListener(e -> {

                // int outputIndex = outputScale.getSelectedIndex();
               // controller.setOutputScale(outputIndex);
            });

            JButton convertTemperatureButton = new JButton("Перевести");
            convertTemperatureButton.addActionListener(e -> {
                try {
                    double temperature = Double.parseDouble(temperatureTextField.getText());
                    int outputIndex = outputScale.getSelectedIndex();
                    int inputIndex = inputScale.getSelectedIndex();
                    //controller.setInputScale(inputIndex);
                   // controller.setOutputScale(outputIndex);
                    controller.convertTemperature(inputIndex,outputIndex,temperature);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Ошибка ввода данных", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
            panel.add(convertTemperatureButton);
            outputTemperatureLabel = new JLabel();
            panel.add(outputTemperatureLabel);


            frame.add(panel);
            frame.setVisible(true);

        });
    }

    @Override
    public void temperatureChanded(double outputTemperature) {
        outputTemperatureLabel.setText("Температура " + outputTemperature);
    }
}


