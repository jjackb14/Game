package core.object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Boots extends Object {

    /**
     * Constructs a new boots object.
     */
    public OBJ_Boots() {
        super();

        setName("Boots");
        try {
            setImage(ImageIO.read(getClass().getResourceAsStream("/objects/boots.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "OBJ_Boots{}";
    }
}
