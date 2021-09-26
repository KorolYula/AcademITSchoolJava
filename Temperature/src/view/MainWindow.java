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
            frame.setSize(600, 200);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel();
            JTextField temperatureTextField = new JTextField(10);
            JLabel temperatureLabel = new JLabel("Ведите температуру: ");
           // panel.add(temperatureLabel);
            String[] temperatureScales = {"шкала Цельсия",
                    "шкала Фаренгейта",
                    "шкала Кельвина"};

            JLabel choiseInputScale = new JLabel();
            // panel.add(choiseInputScale);
            choiseInputScale.setText("Шкала вводы температура :");
            JComboBox<String> inputScale = new JComboBox<>(temperatureScales);
            // panel.add(inputScale);

            JLabel choiseOutputScale = new JLabel();
            //  panel.add(choiseOutputScale);
            choiseOutputScale.setText("Шкала вывода температуры:");

            JComboBox<String> outputScale = new JComboBox<>(temperatureScales);
            // panel.add(outputScale);
            JButton convertTemperatureButton = new JButton("Перевести температуру");
            outputTemperatureLabel = new JLabel();

            //_______________________________________выставляю layout
            GroupLayout layout = new GroupLayout(panel);
            panel.setLayout(layout);
            layout.setAutoCreateGaps(true);
            layout.setAutoCreateContainerGaps(true);
            GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();

            // layout.setHorizontalGroup(layout.createSequentialGroup()
            hGroup.addGroup(layout.createParallelGroup()
                    .addComponent(temperatureLabel)
                    .addComponent(choiseInputScale)
                    .addComponent(inputScale)
                    .addComponent(outputTemperatureLabel));
            hGroup.addGroup(layout.createParallelGroup()
                    .addComponent(temperatureTextField)
                    .addComponent(choiseOutputScale)
                    .addComponent(outputScale));
            hGroup.addComponent(convertTemperatureButton);

            layout.setHorizontalGroup(hGroup);
            GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
            vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(temperatureLabel)
                    .addComponent(temperatureTextField));
            vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(choiseInputScale)
                    .addComponent(choiseOutputScale));
            vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(inputScale)
                    .addComponent(outputScale));
            vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(convertTemperatureButton));
            vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(outputTemperatureLabel));
            layout.setVerticalGroup(vGroup);


            convertTemperatureButton.addActionListener(e -> {
                try {
                    double temperature = Double.parseDouble(temperatureTextField.getText());
                    int outputIndex = outputScale.getSelectedIndex();
                    int inputIndex = inputScale.getSelectedIndex();
                    controller.convertTemperature(inputIndex, outputIndex, temperature);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Ошибка ввода данных", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
            // panel.add(convertTemperatureButton);

            // panel.add(outputTemperatureLabel, BorderLayout.SOUTH);
            frame.add(panel);
            frame.setVisible(true);
        });
    }

    @Override
    public void temperatureChanded(double outputTemperature) {
        outputTemperatureLabel.setText(String.format("Температура после перевода:  %.2f", outputTemperature));
    }
}
