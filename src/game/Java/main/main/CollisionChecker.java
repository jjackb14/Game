package main.main;

import main.entity.Entity;

import java.util.Objects;

/**
 * Checks the collisions of an entity with objects around it.
 */
public class CollisionChecker {
    /** The instance of GamePanel. */
    private GamePanel gp;

    /**
     * Constructs a new CollisionChecker object.
     * @param gp The instance of GamePanel.
     */
    public CollisionChecker(GamePanel gp) {
        setGp(gp);
    }

    /**
     * Checks the collisions of an entity with a tile.
     * @param entity The entity to check.
     */
    public void checkTile(Entity entity) {
        int entityLeftWorldX = entity.getWorldX() + entity.getSolidArea().x;
        int entityRightWorldX = entity.getWorldX() + entity.getSolidArea().x + entity.getSolidArea().width;
        int entityTopWorldY = entity.getWorldY() + entity.getSolidArea().y;
        int entityBottomWorldY = entity.getWorldY() + entity.getSolidArea().y + entity.getSolidArea().height;

        int entityLeftCol = entityLeftWorldX / gp.getTileSize();
        int entityRightCol = entityRightWorldX / gp.getTileSize();
        int entityTopRow = entityTopWorldY / gp.getTileSize();
        int entityBottomRow = entityBottomWorldY / gp.getTileSize();

        int tileNum1, tileNum2;

        switch (entity.getDirection()) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.getSpeed()) / gp.getTileSize();
                tileNum1 = gp.getTileManager().getMapTileNum()[entityLeftCol][entityTopRow];
                tileNum2 = gp.getTileManager().getMapTileNum()[entityRightCol][entityTopRow];
                if (gp.getTileManager().getTile().get(tileNum1).isCollision() || gp.getTileManager().getTile().get(tileNum2).isCollision()) {
                    entity.setCollisionOn(true);
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.getSpeed()) / gp.getTileSize();
                tileNum1 = gp.getTileManager().getMapTileNum()[entityLeftCol][entityBottomRow];
                tileNum2 = gp.getTileManager().getMapTileNum()[entityRightCol][entityBottomRow];
                if (gp.getTileManager().getTile().get(tileNum1).isCollision() || gp.getTileManager().getTile().get(tileNum2).isCollision()) {
                    entity.setCollisionOn(true);
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.getSpeed()) / gp.getTileSize();
                tileNum1 = gp.getTileManager().getMapTileNum()[entityLeftCol][entityTopRow];
                tileNum2 = gp.getTileManager().getMapTileNum()[entityLeftCol][entityBottomRow];
                if (gp.getTileManager().getTile().get(tileNum1).isCollision() || gp.getTileManager().getTile().get(tileNum2).isCollision()) {
                    entity.setCollisionOn(true);
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.getSpeed()) / gp.getTileSize();
                tileNum1 = gp.getTileManager().getMapTileNum()[entityRightCol][entityTopRow];
                tileNum2 = gp.getTileManager().getMapTileNum()[entityRightCol][entityBottomRow];
                if (gp.getTileManager().getTile().get(tileNum1).isCollision() || gp.getTileManager().getTile().get(tileNum2).isCollision()) {
                    entity.setCollisionOn(true);
                }
                break;
        }
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
        CollisionChecker that = (CollisionChecker) o;
        return Objects.equals(getGp(), that.getGp());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getGp());
    }

    @Override
    public String toString() {
        return "CollisionChecker{" +
                "gp=" + gp +
                '}';
    }
}
