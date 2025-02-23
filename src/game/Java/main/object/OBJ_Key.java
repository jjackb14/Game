package main.object;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * A class for the in-game Key object.
 */
public class OBJ_Key extends Object {

    /**
     * Constructs a new key object.
     */
    public OBJ_Key() {
        super();

        setName("Key");
        try {
            setImage(ImageIO.read(getClass().getResourceAsStream("/objects/key.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "OBJ_Key{}";
    }
}
