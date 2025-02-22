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

    /** X position of the screen. */
    private final int screenX;
    /** Y position of the screen. */
    private final int screenY;

    /** The default player speed. */
    public static final int DEFAULT_SPEED = 4;
    /** Default conversion factor for x. */
    public static final int X_DEFAULT_CONVERSION = 23;
    /** Default conversion factor for y. */
    public static final int Y_DEFAULT_CONVERSION = 21;
    /** The default direction of the player. */
    public static final String DEFAULT_DIRECTION = "down";

    /**
     * Constructs a new Player object.
     * @param gp Instance of a game panel.
     * @param keyH Instance of handler.
     */
    public Player(GamePanel gp, KeyHandler keyH) {
        setGp(gp);
        setKeyH(keyH);

        screenX = gp.getScreenWidth() / 2 - (gp.getTileSize() / 2);
        screenY = gp.getScreenHeight() / 2 - (gp.getTileSize() / 2);

        setDefaultValues();
        getPlayerImage();
    }

    /**
     * Sets the default values for the player.
     */
    public void setDefaultValues() {
        setWorldX(gp.getTileSize() * X_DEFAULT_CONVERSION);
        setWorldY(gp.getTileSize() * Y_DEFAULT_CONVERSION);
        setSpeed(DEFAULT_SPEED);
        setDirection(DEFAULT_DIRECTION);
    }

    /**
     * Updates components on the screen.
     */
    public void update() {
        if (keyH.isUpPressed()) {
            setDirection("up");
            setWorldY(getWorldY() - getSpeed());
        } else if (keyH.isDownPressed()) {
            setDirection("down");
            setWorldY(getWorldY() + getSpeed());
        } else if (keyH.isLeftPressed()) {
            setDirection("left");
            setWorldX(getWorldX() - getSpeed());
        } else if (keyH.isRightPressed()) {
            setDirection("right");
            setWorldX(getWorldX() + getSpeed());
        }

        entityUpdate();
    }

    /**
     * Paints components onto the panel.
     * @param g2 the <code>Graphics2D</code> object to protect
     */
    public void draw(Graphics2D g2) {
//        g2.setColor(Color.white);
//        g2.fillRect(getX(), getWorldY(), gp.getTileSize(), gp.getTileSize());

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
        g2.drawImage(image, screenX, screenY,  gp.getTileSize() ,gp.getTileSize(), null);
    }

    /**
     * Gets the images required for the player to function.
     */
    public void getPlayerImage() {
        try {
            setUp1(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_up_1.png"))));
            setUp2(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_up_2.png"))));
            setDown1(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_down_1.png"))));
            setDown2(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_down_2.png"))));
            setLeft1(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_left_1.png"))));
            setLeft2(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_left_2.png"))));
            setRight1(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_right_1.png"))));
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

    public int getScreenX() {
        return screenX;
    }

    public int getScreenY() {
        return screenY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Player player = (Player) o;
        return getScreenX() == player.getScreenX() && getScreenY() == player.getScreenY() && Objects.equals(getGp(), player.getGp()) && Objects.equals(getKeyH(), player.getKeyH());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getGp(), getKeyH(), getScreenX(), getScreenY());
    }

    @Override
    public String toString() {
        return "Player{" +
                "gp=" + gp +
                ", keyH=" + keyH +
                ", screenX=" + screenX +
                ", screenY=" + screenY +
                '}';
    }
}
