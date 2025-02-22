import javax.swing.*;
import java.awt.*;

/**
 * The physical UI of the game. Will handle tile sizes, resolution, etc.
 */
public class GamePanel extends JPanel {
    /** 16x16 tile. */
    final int originalTileSize = 16;
    /** Scales up the pixels to fit the resolution of modern displays. */
    final int scale = 3;
    /** Creates a 48X48 tile for use in the game. */
    final int tileSize = originalTileSize * scale;
    /** Maximum usable columns for the game. */
    final int maxScreenCols = 16;
    /** Maximum usable rows for the game. */
    final int maxScreenRows = 12;
    /** Width of the usable screen. (768px) */
    final int screenWidth = tileSize * maxScreenCols;
    /** Height of the usable screen. (576px) */
    final int screenHeight = tileSize * maxScreenRows;

    /**
     * Constructs a new GamePanel for use running the game.
     */
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
    }

}
