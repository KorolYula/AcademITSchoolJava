package view;

import academic.korol.temperature.TemperatureController;
import academic.korol.temperature.TemperatureModelListener;
import academic.korol.temperature.TemperatureView;

import javax.swing.*;

public class MainWindow implements TemperatureView  {
    private JFrame frame;
    private final TemperatureController controller;
    private JLabel fahrienheitTemperatureLabel;

    public MainWindow(TemperatureController controller) {
        this.controller = controller;
    }

    public  void start() {
        SwingUtilities.invokeLater(() -> {

            JFrame frame = new JFrame("Перев");
            frame.setSize(600, 500);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel();

            JTextField celsiusTemperatureTextField = new JTextField(10);
            panel.add(celsiusTemperatureTextField);

            JLabel celsiusTemperatureLabel = new JLabel("введите апппппп");
            panel.add(celsiusTemperatureLabel);

            JButton convertTemperatureButton = new JButton("Convert");
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
fahrienheitTemperatureLabel.setText("Температура в фаригнейтах" +fahrenheitTemperature);
    }
}


