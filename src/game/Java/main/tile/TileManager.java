package main.tile;

import main.main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

/**
 * Manages the tiles in the game.
 */
public class TileManager {
    /** An instance of the game panel. */
    private GamePanel gp;
    /** An array to hold the tiles. */
    private Tile[] tile;

    /**
     * Constructs a new TileManager.
     * @param gp The instance of game panel.
     */
    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];

        getTileImage();
    }

    /**
     * Loads the images for the tiles into the array.
     */
    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].setImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/grass.png"))));

            tile[1] = new Tile();
            tile[1].setImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/wall.png"))));

            tile[2] = new Tile();
            tile[2].setImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water.png"))));
        } catch (IOException e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
    }

    /**
     * Paints components onto the panel.
     * @param g2 the <code>Graphics2D</code> object to protect
     */
    public void draw(Graphics2D g2) {
        g2.drawImage(tile[0].getImage(), 0, 0, gp.getTileSize(), gp.getTileSize(), null);
    }

    public GamePanel getGp() {
        return gp;
    }

    public void setGp(GamePanel gp) {
        this.gp = gp;
    }

    public Tile[] getTile() {
        return tile;
    }

    public void setTile(Tile[] tile) {
        this.tile = tile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TileManager that = (TileManager) o;
        return Objects.equals(getGp(), that.getGp()) && Objects.deepEquals(getTile(), that.getTile());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGp(), Arrays.hashCode(getTile()));
    }

    @Override
    public String toString() {
        return "TileManager{" +
                "gp=" + gp +
                ", tile=" + Arrays.toString(tile) +
                '}';
    }
}
