package main.tile;

import main.main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
    /** Maps the numbers of the respective tiles to be displayed. */
    private int[][] mapTileNum;

    /** The first map in the game. */
    public static final String MAP_01 = "/maps/map01.txt";

    /**
     * Constructs a new TileManager.
     * @param gp The instance of game panel.
     */
    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];

        mapTileNum = new int[gp.getMaxScreenCols()][gp.getMaxScreenRows()];

        getTileImage();
        loadMap(MAP_01);
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
     * Loads in a map from the tile mapping txt file.
     * @param fileName The name of the map file to be loaded.
     * @throws RuntimeException if there is a problem reading the mapping file.
     */
    public void loadMap(String fileName) {
        try {
            InputStream is = getClass().getResourceAsStream(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.getMaxScreenCols() && row < gp.getMaxScreenRows()) {
                String line = br.readLine();

                while (col < gp.getMaxScreenCols()) {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.getMaxScreenCols()) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Paints components onto the panel.
     * @param g2 the <code>Graphics2D</code> object to protect
     */
    public void draw(Graphics2D g2) {
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gp.getMaxScreenCols() && row < gp.getMaxScreenRows()) {
            int tileNum = mapTileNum[col][row];

            g2.drawImage(tile[tileNum].getImage(), x, y, gp.getTileSize(), gp.getTileSize(), null);
            col++;
            x += gp.getTileSize();

            if (col == gp.getMaxScreenCols()) {
                col = 0;
                x = 0;
                row++;
                y += gp.getTileSize();
            }
        }
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

    public int[][] getMapTileNum() {
        return mapTileNum;
    }

    public void setMapTileNum(int[][] mapTileNum) {
        this.mapTileNum = mapTileNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TileManager that = (TileManager) o;
        return Objects.equals(getGp(), that.getGp()) && Objects.deepEquals(getTile(), that.getTile()) && Objects.deepEquals(getMapTileNum(), that.getMapTileNum());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGp(), Arrays.hashCode(getTile()), Arrays.deepHashCode(getMapTileNum()));
    }

    @Override
    public String toString() {
        return "TileManager{" +
                "gp=" + gp +
                ", tile=" + Arrays.toString(tile) +
                ", mapTileNum=" + Arrays.toString(mapTileNum) +
                '}';
    }
}
