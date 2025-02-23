package main.main;

import main.entity.Player;
import main.tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

import static main.tile.TileManager.*;

/**
 * The physical UI of the game. Will handle tile sizes, resolution, etc.
 */
public final class GamePanel extends JPanel implements Runnable {
    /** 16x16 tile. */
    private final int originalTileSize = 16;
    /** Scales up the pixels to fit the resolution of modern displays. */
    private final int scale = 3;

    /** Creates a 48X48 tile for use in the game. */
    private final int tileSize = originalTileSize * scale;
    /** Maximum usable columns for the game. */
    private final int maxScreenCols = 16;
    /** Maximum usable rows for the game. */
    private final int maxScreenRows = 12;
    /** Width of the usable screen. (768 px) */
    private final int screenWidth = tileSize * maxScreenCols;
    /** Height of the usable screen. (576 px) */
    private final int screenHeight = tileSize * maxScreenRows;

    /** Maximum columns in the world. */
    private final int maxWorldCol = 50;
    /** Maximum rows in the world. */
    private final int maxWorldRow = 50;
    /** Width of the world. */
    public final int worldWidth = tileSize * maxWorldCol;
    /** Height of the world. */
    public final int worldHeight = tileSize * maxWorldRow;

    /** Constant for converting time to seconds. */
    private static final int SECOND_CONVERSION = 1000000000;
    /** Constant for converting time to milliseconds from seconds. */
    private static final int MILLI_CONVERSION = 1000000;

    /** FPS the game will run at. */
    private int FPS = 60;

    /** An instance of the tile manager. */
    TileManager tileManager = new TileManager(this);
    /** A main.main.KeyHandler to handle the input from the keyboard. */
    private KeyHandler keyH = new KeyHandler();
    /** The thread to run the game and maintain the clock. */
    private Thread gameThread;
    /** An instance of the player in the game. */
    private Player player = new Player(this, keyH);
    /** The instance of AssetSetter to help with managing in-game assets. */
    private AssetSetter assetSetter = new AssetSetter(this);
    /** An instance of the CollisionChecker object. */
    private CollisionChecker cChecker = new CollisionChecker(this);
    /** An ArrayList to hold all the objects. */
    private ArrayList<main.object.Object> obj = new ArrayList<>(DEFAULT_CAPACITY);

    /**
     * Constructs a new main.main.GamePanel for use running the game.
     */
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame() {
        assetSetter.setObject();
    }

    /**
     * Starts the thread needed to run the game.
     */
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * When an object implementing interface {@code Runnable} is used
     * to create a thread, starting the thread causes the object's
     * {@code run} method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method {@code run} is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        //noinspection IntegerDivisionInFloatingPointContext
        double drawInterval = SECOND_CONVERSION / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if (timer >= SECOND_CONVERSION) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    /**
     * Updates components on the screen.
     */
    public void update() {
        player.update();
    }

    /**
     * Paints components onto the panel.
     * @param g the <code>Graphics</code> object to protect
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        //---- TILES MUST BE DRAWN IN THIS ORDER ---- //

        tileManager.draw(g2);

        //ArrayList improves performance since it won't iterate through the whole 10-array indexes.
        for (int i = 0; i < obj.size(); i++) {
            if (obj.get(i) != null) {
                obj.get(i).draw(g2, this);
            }
        }

        player.draw(g2);

        g2.dispose();
    }

    public int getOriginalTileSize() {
        return originalTileSize;
    }

    public int getScale() {
        return scale;
    }

    public int getTileSize() {
        return tileSize;
    }

    public int getMaxScreenCols() {
        return maxScreenCols;
    }

    public int getMaxScreenRows() {
        return maxScreenRows;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public int getFPS() {
        return FPS;
    }

    public void setFPS(int FPS) {
        this.FPS = FPS;
    }

    public KeyHandler getKeyH() {
        return keyH;
    }

    public void setKeyH(KeyHandler keyH) {
        this.keyH = keyH;
    }

    public Thread getGameThread() {
        return gameThread;
    }

    public void setGameThread(Thread gameThread) {
        this.gameThread = gameThread;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getMaxWorldCol() {
        return maxWorldCol;
    }

    public int getMaxWorldRow() {
        return maxWorldRow;
    }

    public int getWorldWidth() {
        return worldWidth;
    }

    public int getWorldHeight() {
        return worldHeight;
    }

    public TileManager getTileManager() {
        return tileManager;
    }

    public void setTileManager(TileManager tileManager) {
        this.tileManager = tileManager;
    }

    public CollisionChecker getcChecker() {
        return cChecker;
    }

    public void setcChecker(CollisionChecker cChecker) {
        this.cChecker = cChecker;
    }

    public AssetSetter getAssetSetter() {
        return assetSetter;
    }

    public void setAssetSetter(AssetSetter assetSetter) {
        this.assetSetter = assetSetter;
    }

    public ArrayList<main.object.Object> getObj() {
        return obj;
    }

    public void setObj(ArrayList<main.object.Object> obj) {
        this.obj = obj;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GamePanel gamePanel = (GamePanel) o;
        return getOriginalTileSize() == gamePanel.getOriginalTileSize() && getScale() == gamePanel.getScale() && getTileSize() == gamePanel.getTileSize() && getMaxScreenCols() == gamePanel.getMaxScreenCols() && getMaxScreenRows() == gamePanel.getMaxScreenRows() && getScreenWidth() == gamePanel.getScreenWidth() && getScreenHeight() == gamePanel.getScreenHeight() && getMaxWorldCol() == gamePanel.getMaxWorldCol() && getMaxWorldRow() == gamePanel.getMaxWorldRow() && getWorldWidth() == gamePanel.getWorldWidth() && getWorldHeight() == gamePanel.getWorldHeight() && getFPS() == gamePanel.getFPS() && Objects.equals(getTileManager(), gamePanel.getTileManager()) && Objects.equals(getKeyH(), gamePanel.getKeyH()) && Objects.equals(getGameThread(), gamePanel.getGameThread()) && Objects.equals(getPlayer(), gamePanel.getPlayer()) && Objects.equals(getAssetSetter(), gamePanel.getAssetSetter()) && Objects.equals(getcChecker(), gamePanel.getcChecker()) && Objects.equals(getObj(), gamePanel.getObj());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOriginalTileSize(), getScale(), getTileSize(), getMaxScreenCols(), getMaxScreenRows(), getScreenWidth(), getScreenHeight(), getMaxWorldCol(), getMaxWorldRow(), getWorldWidth(), getWorldHeight(), getFPS(), getTileManager(), getKeyH(), getGameThread(), getPlayer(), getAssetSetter(), getcChecker(), getObj());
    }

    @Override
    public String toString() {
        return "GamePanel{" +
                "originalTileSize=" + originalTileSize +
                ", scale=" + scale +
                ", tileSize=" + tileSize +
                ", maxScreenCols=" + maxScreenCols +
                ", maxScreenRows=" + maxScreenRows +
                ", screenWidth=" + screenWidth +
                ", screenHeight=" + screenHeight +
                ", maxWorldCol=" + maxWorldCol +
                ", maxWorldRow=" + maxWorldRow +
                ", worldWidth=" + worldWidth +
                ", worldHeight=" + worldHeight +
                ", FPS=" + FPS +
                ", tileManager=" + tileManager +
                ", keyH=" + keyH +
                ", gameThread=" + gameThread +
                ", player=" + player +
                ", assetSetter=" + assetSetter +
                ", cChecker=" + cChecker +
                ", obj=" + obj +
                ", ui=" + ui +
                ", listenerList=" + listenerList +
                ", accessibleContext=" + accessibleContext +
                '}';
    }
}
