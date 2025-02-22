package main.entity;

import java.awt.image.BufferedImage;
import java.util.Objects;

/**
 * Super class for all Entities in the game.
 */
public class Entity {
    /** X and Y coordinates. */
    private int x, y;
    /** Speed of an entity. */
    private int speed;
    /** All the images to be used to make an entity. */
    private BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    /** The current direction of the entity. */
    private String direction;
    /** Counter of sprites in the game. */
    private int spriteCounter;
    /** Number of the sprite. */
    private int spriteNum;

    /**
     * Creates a new Entity.
     */
    public Entity() {
        setSpriteCounter(0);
        setSpriteNum(1);
    }

    public void entityUpdate() {
        spriteCounter++;
        if (spriteCounter > 12) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    /**
     * Allows for spriteCounter to be incremented without directly accessing the field.
     */
    public void incrementSpriteCounter() {
        spriteCounter++;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public BufferedImage getRight2() {
        return right2;
    }

    public void setRight2(BufferedImage right2) {
        this.right2 = right2;
    }

    public BufferedImage getRight1() {
        return right1;
    }

    public void setRight1(BufferedImage right1) {
        this.right1 = right1;
    }

    public BufferedImage getLeft2() {
        return left2;
    }

    public void setLeft2(BufferedImage left2) {
        this.left2 = left2;
    }

    public BufferedImage getLeft1() {
        return left1;
    }

    public void setLeft1(BufferedImage left1) {
        this.left1 = left1;
    }

    public BufferedImage getDown2() {
        return down2;
    }

    public void setDown2(BufferedImage down2) {
        this.down2 = down2;
    }

    public BufferedImage getDown1() {
        return down1;
    }

    public void setDown1(BufferedImage down1) {
        this.down1 = down1;
    }

    public BufferedImage getUp2() {
        return up2;
    }

    public void setUp2(BufferedImage up2) {
        this.up2 = up2;
    }

    public BufferedImage getUp1() {
        return up1;
    }

    public void setUp1(BufferedImage up1) {
        this.up1 = up1;
    }

    public int getSpriteCounter() {
        return spriteCounter;
    }

    public void setSpriteCounter(int spriteCounter) {
        this.spriteCounter = spriteCounter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return getX() == entity.getX() && getY() == entity.getY() && getSpeed() == entity.getSpeed();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY(), getSpeed());
    }

    @Override
    public String toString() {
        return "Entity{" +
                "x=" + x +
                ", y=" + y +
                ", speed=" + speed +
                '}';
    }

    public int getSpriteNum() {
        return spriteNum;
    }

    public void setSpriteNum(int spriteNum) {
        this.spriteNum = spriteNum;
    }
}
