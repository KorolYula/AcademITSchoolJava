package academic.korol.minesweeper.gui;

import javax.swing.*;
import java.awt.*;

public class MainWindow implements MinesweeperView {

    public void start() {
        int fieldDimension = 9;
        JFrame frame = new JFrame("Игра Сапер");
        frame.setSize(800, 170);
        frame.setMinimumSize(new Dimension(800, 170));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();

        JButton newGame = new JButton("New Game");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;
        c.weighty = 1.0;
        c.anchor = GridBagConstraints.PAGE_END;
        c.insets = new Insets(10, 0, 0, 0);  //top padding
        c.gridx = 1;
        c.gridwidth = 2;
        c.gridy = 2;
        panel.add(newGame, c);
        layout.setConstraints(newGame, c);

        JButton about = new JButton("About");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        panel.add(about, c);


        JButton scores = new JButton("High Scores");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        panel.add(scores, c);

        JButton exit = new JButton("Exit");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 0;
        panel.add(exit, c);


        //GridLayout gameLayout = new GridLayout();
        JPanel gamePanel = new JPanel();
        c.fill = GridBagConstraints.HORIZONTAL;
        //c.ipady = 40;
        c.weightx = 1.0;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 1;
        panel.add(gamePanel, c);

        GridLayout gameLayout = new GridLayout(fieldDimension, fieldDimension,1,1);
        gamePanel.setLayout(gameLayout);
        for (int i = 0; i < fieldDimension; i++) {
            gamePanel.add(new JButton("Кнопка " + i));
        }


        panel.setLayout(layout);

        //layout.setConstraints(about, c);
        frame.add(panel);
        frame.setVisible(true);
    }


}
