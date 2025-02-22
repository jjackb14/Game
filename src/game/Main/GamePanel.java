import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * The physical UI of the game. Will handle tile sizes, resolution, etc.
 */
public class GamePanel extends JPanel implements Runnable {
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
    /** Width of the usable screen. (768px) */
    private final int screenWidth = tileSize * maxScreenCols;
    /** Height of the usable screen. (576px) */
    private final int screenHeight = tileSize * maxScreenRows;

    /** Constant for converting time to seconds. */
    private static final int SECOND_CONVERSION = 1000000000;
    /** Constant for converting time to milliseconds from seconds. */
    private static final int MILLI_CONVERSION = 1000000;

    /** FPS the game will run at. */
    private int FPS = 120;

    /** A KeyHandler to handle the input from the keyboard. */
    private KeyHandler keyH = new KeyHandler();
    /** The thread to run the game and maintain the clock. */
    private Thread gameThread;

    /** X coord of the player. */
    private int playerX = 100;
    /** Y coord of the player. */
    private int playerY = 100;
    /** Speed of the player. */
    private int playerSpeed = 2;

    /**
     * Constructs a new GamePanel for use running the game.
     */
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
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
        if (keyH.isUpPressed()) {
            playerY -= playerSpeed;
        } else if (keyH.isDownPressed()) {
            playerY += playerSpeed;
        } else if (keyH.isLeftPressed()) {
            playerX -= playerSpeed;
        } else if (keyH.isRightPressed()) {
            playerX += playerSpeed;
        }
    }

    /**
     * Paints components onto the panel.
     * @param g the <code>Graphics</code> object to protect
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.white);
        g2.fillRect(playerX, playerY, tileSize, tileSize);

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

    public int getPlayerX() {
        return playerX;
    }

    public void setPlayerX(int playerX) {
        this.playerX = playerX;
    }

    public int getPlayerY() {
        return playerY;
    }

    public void setPlayerY(int playerY) {
        this.playerY = playerY;
    }

    public int getPlayerSpeed() {
        return playerSpeed;
    }

    public void setPlayerSpeed(int playerSpeed) {
        this.playerSpeed = playerSpeed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GamePanel gamePanel = (GamePanel) o;
        return getOriginalTileSize() == gamePanel.getOriginalTileSize() && getScale() == gamePanel.getScale() && getTileSize() == gamePanel.getTileSize() && getMaxScreenCols() == gamePanel.getMaxScreenCols() && getMaxScreenRows() == gamePanel.getMaxScreenRows() && getScreenWidth() == gamePanel.getScreenWidth() && getScreenHeight() == gamePanel.getScreenHeight() && getFPS() == gamePanel.getFPS() && getPlayerX() == gamePanel.getPlayerX() && getPlayerY() == gamePanel.getPlayerY() && getPlayerSpeed() == gamePanel.getPlayerSpeed() && Objects.equals(getKeyH(), gamePanel.getKeyH()) && Objects.equals(getGameThread(), gamePanel.getGameThread());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOriginalTileSize(), getScale(), getTileSize(), getMaxScreenCols(), getMaxScreenRows(), getScreenWidth(), getScreenHeight(), getFPS(), getKeyH(), getGameThread(), getPlayerX(), getPlayerY(), getPlayerSpeed());
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
                ", FPS=" + FPS +
                ", keyH=" + keyH +
                ", gameThread=" + gameThread +
                ", playerX=" + playerX +
                ", playerY=" + playerY +
                ", playerSpeed=" + playerSpeed +
                ", ui=" + ui +
                ", listenerList=" + listenerList +
                ", accessibleContext=" + accessibleContext +
                '}';
    }
}
