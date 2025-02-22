package main.tile;

import java.awt.image.BufferedImage;
import java.util.Objects;

/**
 * A Java object to represent tiles in the game.
 */
public class Tile {
    /** The tile image. */
    private BufferedImage image;
    /** If the tiles have collisions or not. */
    private boolean collision;

    /**
     * Constructs a new tile object.
     */
    public Tile() {
        collision = false;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public boolean isCollision() {
        return collision;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return isCollision() == tile.isCollision() && Objects.equals(getImage(), tile.getImage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getImage(), isCollision());
    }

    @Override
    public String toString() {
        return "Tile{" +
                "image=" + image +
                ", collision=" + collision +
                '}';
    }
}
