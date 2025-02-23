package main.object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Chest extends SuperObject {

    /**
     * Constructs a new chest object.
     */
    public OBJ_Chest() {
        super();

        setName("Chest");
        try {
            setImage(ImageIO.read(getClass().getResourceAsStream("/objects/chest.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "OBJ_Chest{}";
    }
}
