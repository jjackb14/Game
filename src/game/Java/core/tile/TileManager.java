package core.tile;

import core.main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * Manages the tiles in the game.
 */
public final class TileManager {
    /** An instance of the game panel. */
    private GamePanel gp;
    /** An array to hold the tiles. */
    private ArrayList<Tile> tile;
    /** Maps the numbers of the respective tiles to be displayed. */
    private int[][] mapTileNum;

    /** The first map in the game. */
    public static final String MAP_01 = "/maps/map01.txt";
    /** The first world in the game. */
    public static final String WORLD_01 = "/maps/world01.txt";

    /** Default number of tiles. */
    public static final int DEFAULT_CAPACITY = 10;
    /** Value for grass tiles. */
    public static final int GRASS = 0;
    /** Value for wall tiles. */
    public static final int WALL = 1;
    /** Value for water tiles. */
    public static final int WATER = 2;
    /** Value for earth tiles. */
    public static final int EARTH = 3;
    /** Value for tree tiles. */
    public static final int TREE = 4;
    /** Value for sand tiles. */
    public static final int SAND = 5;

    /**
     * Constructs a new TileManager.
     * @param gp The instance of game panel.
     */
    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new ArrayList<>(DEFAULT_CAPACITY);

        mapTileNum = new int[gp.getMaxWorldCol()][gp.getMaxWorldRow()];

        getTileImage();
        loadMap(WORLD_01);
    }

    /**
     * Loads the images for the tiles into the array.
     */
    public void getTileImage() {
        try {
            tile.add(GRASS, new Tile());
            tile.get(GRASS).setImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/grass.png"))));

            tile.add(WALL, new Tile());
            tile.get(WALL).setImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/wall.png"))));
            tile.get(WALL).setCollision(true);

            tile.add(WATER, new Tile());
            tile.get(WATER).setImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water.png"))));
            tile.get(WATER).setCollision(true);

            tile.add(EARTH, new Tile());
            tile.get(EARTH).setImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/earth.png"))));

            tile.add(TREE, new Tile());
            tile.get(TREE).setImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tree.png"))));
            tile.get(TREE).setCollision(true);

            tile.add(SAND, new Tile());
            tile.get(SAND).setImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/sand.png"))));
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

            while (col < gp.getMaxWorldCol() && row < gp.getMaxWorldRow()) {
                String line = br.readLine();

                while (col < gp.getMaxWorldCol()) {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.getMaxWorldCol()) {
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
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.getMaxWorldCol() && worldRow < gp.getMaxWorldRow()) {
            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.getTileSize();
            int worldY = worldRow * gp.getTileSize();
            int screenX = worldX - gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX();
            int screenY = worldY - gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY();

            if (worldX + gp.getTileSize() > gp.getPlayer().getWorldX() - gp.getPlayer().getScreenX() &&
                    worldX - gp.getTileSize() < gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX() &&
                    worldY + gp.getTileSize() > gp.getPlayer().getWorldY() - gp.getPlayer().getScreenY() &&
                    worldY - gp.getTileSize() < gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY()) {
                g2.drawImage(tile.get(tileNum).getImage(), screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);
            }

            worldCol++;

            if (worldCol == gp.getMaxWorldCol()) {
                worldCol = 0;
                worldRow++;
            }
        }
    }

    public GamePanel getGp() {
        return gp;
    }

    public void setGp(GamePanel gp) {
        this.gp = gp;
    }

    public ArrayList<Tile> getTile() {
        return tile;
    }

    public void setTile(ArrayList<Tile> tile) {
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
        return Objects.equals(getGp(), that.getGp()) && Objects.equals(getTile(), that.getTile()) && Objects.deepEquals(getMapTileNum(), that.getMapTileNum());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGp(), getTile(), Arrays.deepHashCode(getMapTileNum()));
    }

    @Override
    public String toString() {
        return "TileManager{" +
                "gp=" + gp +
                ", tile=" + tile +
                ", mapTileNum=" + Arrays.toString(mapTileNum) +
                '}';
    }
}
