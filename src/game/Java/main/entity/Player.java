package main.entity;

import main.main.GamePanel;
import main.main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * A specific type of entity, the player. Will be the center of the game.
 */
public class Player extends Entity {
    /** An instance of the game panel. */
    private GamePanel gp;
    /** An instance of the KeyHandler. */
    private KeyHandler keyH;

    /** The default player speed. */
    public static final int DEFAULT_SPEED = 2;

    /**
     * Constructs a new Player object.
     * @param gp Instance of gamepanel.
     * @param keyH Instance of keyhandler.
     */
    public Player(GamePanel gp, KeyHandler keyH) {
        setGp(gp);
        setKeyH(keyH);

        setDefaultValues();
        getPlayerImage();
    }

    /**
     * Sets the default values for the player.
     */
    public void setDefaultValues() {
        setX(100);
        setY(100);
        setSpeed(DEFAULT_SPEED);
        setDirection("down");
    }

    /**
     * Updates components on the screen.
     */
    public void update() {
        if (keyH.isUpPressed()) {
            setDirection("up");
            setY(getY() - getSpeed());
        } else if (keyH.isDownPressed()) {
            setDirection("down");
            setY(getY() + getSpeed());
        } else if (keyH.isLeftPressed()) {
            setDirection("left");
            setX(getX() - getSpeed());
        } else if (keyH.isRightPressed()) {
            setDirection("right");
            setX(getX() + getSpeed());
        }
    }

    /**
     * Paints components onto the panel.
     * @param g2 the <code>Graphics2D</code> object to protect
     */
    public void draw(Graphics2D g2) {
//        g2.setColor(Color.white);
//        g2.fillRect(getX(), getY(), gp.getTileSize(), gp.getTileSize());

        BufferedImage image = null;

        switch (getDirection()) {
            case "up":
                if (getSpriteNum() == 1) {
                    image = getUp1();
                }
                if (getSpriteNum() == 2) {
                    image = getUp2();
                }
                break;
            case "down":
                if (getSpriteNum() == 1) {
                    image = getDown1();
                }
                if (getSpriteNum() == 2) {
                    image = getDown2();
                }
                break;
            case "left":
                if (getSpriteNum() == 1) {
                    image = getLeft1();
                }
                if (getSpriteNum() == 2) {
                    image = getLeft2();
                }
                break;
            case "right":
                if (getSpriteNum() == 1) {
                    image = getRight1();
                }
                if (getSpriteNum() == 2) {
                    image = getRight2();
                }
                break;
        }
        g2.drawImage(image, getX(), getY(),  gp.getTileSize() ,gp.getTileSize(), null);
    }

    public void getPlayerImage() {
        try {
            setUp1(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_up_1.png"))));
            setUp2(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_up_2.png"))));
            setDown1(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_down_1.png"))));
            setDown2(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_down_2.png"))));
            setLeft1(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_left_1.png"))));
            setLeft2(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_left_2.png"))));
            setRight1(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_right_2.png"))));
            setRight2(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_right_2.png"))));
        } catch (IOException e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
    }

    public GamePanel getGp() {
        return gp;
    }

    public void setGp(GamePanel gp) {
        this.gp = gp;
    }

    public KeyHandler getKeyH() {
        return keyH;
    }

    public void setKeyH(KeyHandler keyH) {
        this.keyH = keyH;
    }
}
