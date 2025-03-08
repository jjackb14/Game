package core.main;

import core.object.OBJ_Boots;
import core.object.OBJ_Chest;
import core.object.OBJ_Door;
import core.object.OBJ_Key;

import java.util.Objects;

/**
 * Handles setting up in-game assets.
 */
public final class AssetSetter {
    /** The instance of GamePanel. */
    private GamePanel gp;

    /**
     * Constructs a new AssetSetter object.
     * @param gp The instance of GamePanel.
     */
    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    /**
     * Sets up all the objects in the game.
     */
    public void setObject() {
        //Key 1
        gp.getObj().add(0, new OBJ_Key());
        gp.getObj().get(0).setWorldX(23 * gp.getTileSize());
        gp.getObj().get(0).setWorldY(7 * gp.getTileSize());

        //Key 2
        gp.getObj().add(1, new OBJ_Key());
        gp.getObj().get(1).setWorldX(23 * gp.getTileSize());
        gp.getObj().get(1).setWorldY(40 * gp.getTileSize());

        //Key 3
        gp.getObj().add(2, new OBJ_Key());
        gp.getObj().get(2).setWorldX(38 * gp.getTileSize());
        gp.getObj().get(2).setWorldY(8 * gp.getTileSize());

        //Door 1
        gp.getObj().add(3, new OBJ_Door());
        gp.getObj().get(3).setWorldX(10 * gp.getTileSize());
        gp.getObj().get(3).setWorldY(11 * gp.getTileSize());

        //Door 2
        gp.getObj().add(4, new OBJ_Door());
        gp.getObj().get(4).setWorldX(8 * gp.getTileSize());
        gp.getObj().get(4).setWorldY(28 * gp.getTileSize());

        //Door 3
        gp.getObj().add(5, new OBJ_Door());
        gp.getObj().get(5).setWorldX(12 * gp.getTileSize());
        gp.getObj().get(5).setWorldY(22 * gp.getTileSize());

        //Chest 1
        gp.getObj().add(6, new OBJ_Chest());
        gp.getObj().get(6).setWorldX(10 * gp.getTileSize());
        gp.getObj().get(6).setWorldY(7 * gp.getTileSize());

        //Boots 1
        gp.getObj().add(7, new OBJ_Boots());
        gp.getObj().get(7).setWorldX(37 * gp.getTileSize());
        gp.getObj().get(7).setWorldY(42 * gp.getTileSize());
    }

    public GamePanel getGp() {
        return gp;
    }

    public void setGp(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssetSetter that = (AssetSetter) o;
        return Objects.equals(getGp(), that.getGp());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getGp());
    }

    @Override
    public String toString() {
        return "AssetSetter{" +
                "gp=" + gp +
                '}';
    }
}
