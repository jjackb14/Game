package main.entity;

import main.main.CollisionChecker;
import main.main.GamePanel;
import main.main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
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

    /** Keeps track of the number of keys the player has. */
    private int hasKey;

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

        setHasKey(0);

        screenX = gp.getScreenWidth() / 2 - (gp.getTileSize() / 2);
        screenY = gp.getScreenHeight() / 2 - (gp.getTileSize() / 2);

        setSolidArea(new Rectangle(8, 16, gp.getTileSize() - 16, gp.getTileSize() - 16));
        setSolidAreaDefaultX(getSolidArea().x);
        setSolidAreaDefaultY(getSolidArea().y);

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
        boolean moving = false;

        if (keyH.isUpPressed()) {
            setDirection("up");
            moving = true;
        } else if (keyH.isDownPressed()) {
            setDirection("down");
            moving = true;
        } else if (keyH.isLeftPressed()) {
            setDirection("left");
            moving = true;
        } else if (keyH.isRightPressed()) {
            setDirection("right");
            moving = true;
        }

        //Check collisions with tiles
        setCollisionOn(false);
        gp.getcChecker().checkTile(this);

        //Check collisions with objects
        int objIndex = gp.getcChecker().checkObject(this, true);
        pickUpObject(objIndex);

        if (moving && !isCollisionOn()) {
            switch (getDirection()) {
                case "up": setWorldY(getWorldY() - getSpeed()); break;
                case "down": setWorldY(getWorldY() + getSpeed()); break;
                case "left": setWorldX(getWorldX() - getSpeed()); break;
                case "right": setWorldX(getWorldX() + getSpeed()); break;
            }
        }

        entityUpdate();
    }

    /**
     * Picks up an object from in the game.
     * @param index The index of the object.
     */
    public void pickUpObject(int index) {
        if (index != CollisionChecker.DEFAULT_INDEX) {
            String objName = gp.getObj().get(index).getName();

            switch (objName) {
                case "Key":
                    hasKey++;
                    gp.getObj().set(index, null);
                    if (checkObjRemoved(index)) {
                        System.out.println("object picked up");
                    }
                    else {
                        System.out.println("object could not be picked up");
                    }
                    System.out.println("keys: " + hasKey);
                    break;
                case "Door":
                    if (hasKey > 0) {
                        gp.getObj().set(index, null);
                        hasKey--;
                    }
                    System.out.println("keys: " + hasKey);
                    break;
            }
        }
    }

    /**
     * Private helper method to ensure that an object has actually been removed.
     * @param index The index of the specific object.
     * @return true if it was removed and false if it was not removed.
     */
    private boolean checkObjRemoved(int index) {
        ArrayList<main.object.Object> list = gp.getObj();
        if (list.get(index) == null) {
            return true;
        }
        return false;
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

    public int getHasKey() {
        return hasKey;
    }

    public void setHasKey(int hasKey) {
        this.hasKey = hasKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Player player = (Player) o;
        return getScreenX() == player.getScreenX() && getScreenY() == player.getScreenY() && getHasKey() == player.getHasKey() && Objects.equals(getGp(), player.getGp()) && Objects.equals(getKeyH(), player.getKeyH());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getGp(), getKeyH(), getScreenX(), getScreenY(), getHasKey());
    }

    @Override
    public String toString() {
        return "Player{" +
                "gp=" + gp +
                ", keyH=" + keyH +
                ", screenX=" + screenX +
                ", screenY=" + screenY +
                ", hasKey=" + hasKey +
                '}';
    }
}
