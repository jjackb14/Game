package main.entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

/**
 * Super class for all Entities in the game.
 */
public abstract class Entity {
    /** X and Y coordinates. */
    private int worldX, worldY;
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
    /** A solid rectangle to represent the collision area of an entity. */
    private Rectangle solidArea;
    /** Default X and Y for a solid area. */
    private int solidAreaDefaultX, solidAreaDefaultY;
    /** Flag for if collisions are on for an entity. */
    private boolean collisionOn;

    /**
     * Creates a new Entity.
     */
    public Entity() {
        setSpriteCounter(0);
        setSpriteNum(1);
    }

    /**
     * Updates the entity to generate animations.
     */
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

    public int getWorldY() {
        return worldY;
    }

    public void setWorldY(int worldY) {
        this.worldY = worldY;
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

    public int getSpriteNum() {
        return spriteNum;
    }

    public void setSpriteNum(int spriteNum) {
        this.spriteNum = spriteNum;
    }

    public int getWorldX() {
        return worldX;
    }

    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }

    public Rectangle getSolidArea() {
        return solidArea;
    }

    public void setSolidArea(Rectangle solidArea) {
        this.solidArea = solidArea;
    }

    public boolean isCollisionOn() {
        return collisionOn;
    }

    public void setCollisionOn(boolean collisionOn) {
        this.collisionOn = collisionOn;
    }

    public int getSolidAreaDefaultX() {
        return solidAreaDefaultX;
    }

    public void setSolidAreaDefaultX(int solidAreaDefaultX) {
        this.solidAreaDefaultX = solidAreaDefaultX;
    }

    public int getSolidAreaDefaultY() {
        return solidAreaDefaultY;
    }

    public void setSolidAreaDefaultY(int solidAreaDefaultY) {
        this.solidAreaDefaultY = solidAreaDefaultY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return getWorldX() == entity.getWorldX() && getWorldY() == entity.getWorldY() && getSpeed() == entity.getSpeed() && getSpriteCounter() == entity.getSpriteCounter() && getSpriteNum() == entity.getSpriteNum() && getSolidAreaDefaultX() == entity.getSolidAreaDefaultX() && getSolidAreaDefaultY() == entity.getSolidAreaDefaultY() && isCollisionOn() == entity.isCollisionOn() && Objects.equals(getUp1(), entity.getUp1()) && Objects.equals(getUp2(), entity.getUp2()) && Objects.equals(getDown1(), entity.getDown1()) && Objects.equals(getDown2(), entity.getDown2()) && Objects.equals(getLeft1(), entity.getLeft1()) && Objects.equals(getLeft2(), entity.getLeft2()) && Objects.equals(getRight1(), entity.getRight1()) && Objects.equals(getRight2(), entity.getRight2()) && Objects.equals(getDirection(), entity.getDirection()) && Objects.equals(getSolidArea(), entity.getSolidArea());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getWorldX(), getWorldY(), getSpeed(), getUp1(), getUp2(), getDown1(), getDown2(), getLeft1(), getLeft2(), getRight1(), getRight2(), getDirection(), getSpriteCounter(), getSpriteNum(), getSolidArea(), getSolidAreaDefaultX(), getSolidAreaDefaultY(), isCollisionOn());
    }

    @Override
    public String toString() {
        return "Entity{" +
                "worldX=" + worldX +
                ", worldY=" + worldY +
                ", speed=" + speed +
                ", up1=" + up1 +
                ", up2=" + up2 +
                ", down1=" + down1 +
                ", down2=" + down2 +
                ", left1=" + left1 +
                ", left2=" + left2 +
                ", right1=" + right1 +
                ", right2=" + right2 +
                ", direction='" + direction + '\'' +
                ", spriteCounter=" + spriteCounter +
                ", spriteNum=" + spriteNum +
                ", solidArea=" + solidArea +
                ", soldiAreaDefaultX=" + solidAreaDefaultX +
                ", solidAreaDefaultY=" + solidAreaDefaultY +
                ", collisionOn=" + collisionOn +
                '}';
    }
}
