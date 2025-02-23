package main.object;

import main.main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

/**
 * Defines the basics for an Object in the game.
 */
public abstract class Object {
    /** The object's image. */
    private BufferedImage image;
    /** The name of the object. */
    private String name;
    /** If the object has collisions. */
    private boolean collision;
    /** X coord of the object in the world. */
    private int worldX;
    /** Y coord of the object in the world. */
    private int worldY;
    /** Invisible rectangle to represent the collision area of an object. */
    private Rectangle solidArea = new Rectangle(0, 0, 48, 48);

    /**
     * Constructs an Object and disables collisions by default.
     */
    public Object() {
        this.collision = false;
    }

    /**
     * Draws an object on the screen.
     * @param g2 The instance of Graphics2D.
     * @param gp The instance of GamePanel.
     */
    public void draw(Graphics2D g2, GamePanel gp) {
        int screenX = worldX - gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX();
        int screenY = worldY - gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY();

        if (worldX + gp.getTileSize() > gp.getPlayer().getWorldX() - gp.getPlayer().getScreenX() &&
                worldX - gp.getTileSize() < gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX() &&
                worldY + gp.getTileSize() > gp.getPlayer().getWorldY() - gp.getPlayer().getScreenY() &&
                worldY - gp.getTileSize() < gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY()) {
            g2.drawImage(image, screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);
        }
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCollision() {
        return collision;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }

    public int getWorldX() {
        return worldX;
    }

    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }

    public int getWorldY() {
        return worldY;
    }

    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }

    public Rectangle getSolidArea() {
        return solidArea;
    }

    public void setSolidArea(Rectangle solidArea) {
        this.solidArea = solidArea;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Object that = (Object) o;
        return isCollision() == that.isCollision() && getWorldX() == that.getWorldX() && getWorldY() == that.getWorldY() && Objects.equals(getImage(), that.getImage()) && Objects.equals(getName(), that.getName()) && Objects.equals(getSolidArea(), that.getSolidArea());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getImage(), getName(), isCollision(), getWorldX(), getWorldY(), getSolidArea());
    }

    @Override
    public String toString() {
        return "Object{" +
                "image=" + image +
                ", name='" + name + '\'' +
                ", collision=" + collision +
                ", worldX=" + worldX +
                ", worldY=" + worldY +
                ", solidArea=" + solidArea +
                '}';
    }
}
