package core.main;

import core.object.OBJ_Key;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

/**
 * A class for managing the on-screen UI of the game.
 */
public final class UI {

    private GamePanel gp;

    private Font arial40;

    private Font arial80B;

    private BufferedImage keyImage;

    private boolean messageOn = false;

    private String message = "";

    private int messageCounter = 0;

    private boolean gameFinished = false;

    private double playTime;

    private DecimalFormat format = new DecimalFormat("#0.00");

    public UI(GamePanel gp) {
        this.gp = gp;

        arial40 = new Font("Arial", Font.PLAIN, 40);
        OBJ_Key key = new OBJ_Key();
        keyImage = key.getImage();

        arial80B = new Font("Arial", Font.BOLD, 80);
    }

    public void draw(Graphics2D g2) {
        if (gameFinished) {
            g2.setFont(arial40);
            g2.setColor(Color.WHITE);

            String text;
            int textLength;
            int x;
            int y;

            text = "You found the treasure!";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();

            x = (gp.getScreenWidth() / 2) - (textLength / 2);
            y = (gp.getScreenWidth() / 2) - (gp.getTileSize() * 3);

            g2.drawString(text, x, y);

            g2.setFont(arial80B);
            g2.setColor(Color.YELLOW);

            text = "Congratulations!";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();

            x = (gp.getScreenWidth() / 2) - (textLength / 2);
            y = (gp.getScreenHeight() / 2) + (gp.getTileSize() * 2);

            g2.drawString(text, x, y);

            gp.setGameThread(null); //terminates the thread that runs the game
        }
        else {
            g2.setFont(arial40);
            g2.setColor(Color.WHITE);
            g2.drawImage(keyImage, gp.getTileSize() / 2, gp.getTileSize() / 2, gp.getTileSize(),
                    gp.getTileSize(), null);
            g2.drawString("x " + gp.getPlayer().getHasKey(), 74, 65);

            playTime += 1.0 / 60.0;

            g2.drawString("Time: " + format.format(playTime), gp.getTileSize() * 11, 65);

            if (messageOn) {
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message, gp.getTileSize() / 2, gp.getTileSize() * 5);

                messageCounter++;

                if (messageCounter > 120) {
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }
    }

    public void showMessage(String message) {
        this.message = message;
        messageOn = true;
    }

    public void setGameFinished(boolean finished) {
        this.gameFinished = finished;
    }

}
