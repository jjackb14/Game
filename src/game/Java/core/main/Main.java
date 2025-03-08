package core.main;

import javax.swing.*;

/**
 * The main class to execute the core code necessary for the game to run.
 */
public final class Main {
    /**
     * The main method. Executes all the code.
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {

        JFrame window = new JFrame();

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D Game");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);

        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();
    }

}
