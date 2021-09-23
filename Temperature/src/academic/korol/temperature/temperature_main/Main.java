package academic.korol.temperature.temperature_main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Перевод температуры");
            frame.setSize(600, 500);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            JButton button= new JButton("OK");

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });
            frame.add(button);



            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });
    }
}
