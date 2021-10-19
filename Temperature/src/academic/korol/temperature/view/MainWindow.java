package academic.korol.temperature.view;

import academic.korol.temperature.model.TemperatureConversionModel;
import academic.korol.temperature.model.TemperatureScale;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainWindow implements TemperatureView {
    private JFrame frame;
    private final TemperatureConversionModel model;
    private JLabel outputTemperatureLabel;
    private final String[] temperatureScalesNames;

    public MainWindow(TemperatureConversionModel model, ArrayList<TemperatureScale> temperatureScales) {
        this.model = model;
        temperatureScalesNames = temperatureScales.stream().map(TemperatureScale::getName).toArray(String[]::new);
    }

    public void start() {
        SwingUtilities.invokeLater(() -> {
            frame = new JFrame("Перевод температур");
            frame.setSize(800, 170);
            frame.setMinimumSize(new Dimension(800, 170));//не работает(
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel();

            JLabel temperatureLabel = new JLabel("Введите температуру: ");

            JTextField temperatureTextField = new JTextField(10);
            JLabel choiceInputScale = new JLabel("Шкала вводы температура :");
            JComboBox<String> inputScale = new JComboBox<>(temperatureScalesNames);

            JLabel choiceOutputScale = new JLabel("Шкала вывода температуры:");
            JComboBox<String> outputScale = new JComboBox<>(temperatureScalesNames);

            JButton convertTemperatureButton = new JButton("Перевести температуру");

            outputTemperatureLabel = new JLabel();

            GroupLayout layout = new GroupLayout(panel);
            panel.setLayout(layout);
            layout.setAutoCreateGaps(true);
            layout.setAutoCreateContainerGaps(true);
            GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();

            hGroup.addGroup(layout.createParallelGroup()
                    .addComponent(temperatureLabel)
                    .addComponent(choiceInputScale)
                    .addComponent(inputScale)
                    .addComponent(outputTemperatureLabel));
            hGroup.addGroup(layout.createParallelGroup()
                    .addComponent(temperatureTextField)
                    .addComponent(choiceOutputScale)
                    .addComponent(outputScale));
            hGroup.addComponent(convertTemperatureButton);

            layout.setHorizontalGroup(hGroup);
            GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
            vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(temperatureLabel)
                    .addComponent(temperatureTextField));
            vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(choiceInputScale)
                    .addComponent(choiceOutputScale));
            vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(inputScale)
                    .addComponent(outputScale)
                    .addComponent(convertTemperatureButton));
            vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(outputTemperatureLabel));
            layout.setVerticalGroup(vGroup);

            convertTemperatureButton.addActionListener(e -> {
                try {
                    double temperature = Double.parseDouble(temperatureTextField.getText());
                    int outputIndex = outputScale.getSelectedIndex();
                    int inputIndex = inputScale.getSelectedIndex();
                    model.convertTemperature(inputIndex, outputIndex, temperature);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Ошибка ввода данных", "Ошибка!", JOptionPane.ERROR_MESSAGE);
                }
            });
            frame.add(panel);
            frame.setVisible(true);
        });
    }

    @Override
    public void temperatureChanged(double outputTemperature) {
        outputTemperatureLabel.setText(String.format("Температура после перевода:  %.2f", outputTemperature));
    }
}